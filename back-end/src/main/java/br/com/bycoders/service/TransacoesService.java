package br.com.bycoders.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.dto.OperacoesDTO;
import br.com.bycoders.dto.TransacoesDTO;
import br.com.bycoders.entity.Loja;
import br.com.bycoders.entity.TipoTransacao;
import br.com.bycoders.entity.Transacoes;
import br.com.bycoders.error.handler.CustomException;
import br.com.bycoders.repository.LojaRepository;
import br.com.bycoders.repository.TipoTransacoesRepository;
import br.com.bycoders.repository.TransacoesRepository;

@Service
public class TransacoesService {
	@Autowired
	private TipoTransacoesRepository tipoRepository;
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private TransacoesRepository transacoesRepository;

	public List<OperacoesDTO> upload(MultipartFile file) {
		//guardar os IDs das transacoes
		List<Long> idsImportados = new ArrayList<>(1);
		
		 try {
	            // Ler o conteúdo do arquivo
	            InputStream inputStream = file.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

	            String line;
	            int cont = 0;
	            while ((line = reader.readLine()) != null) {
	               idsImportados.add(processaLinha(line, cont++));	
	            }

	            reader.close();
	            inputStream.close();
	            return preparaResponse(idsImportados);	       
	        } catch (Exception e) {
	        	 e.printStackTrace();
	        }
		return new ArrayList<>();		
	}

	@Transactional
	private List<OperacoesDTO> preparaResponse(List<Long> idsImportados) {
		List<Loja> lojas = transacoesRepository.findAllByIdLoja(idsImportados);
		List<OperacoesDTO> listaOperacoes = new ArrayList<>(1);
		for (Loja loja : lojas) {
			listaOperacoes.add(OperacoesDTO.builder()
					.donoLoja(loja.getDonoLoja())
					.nomeLoja(loja.getNomeLoja())
					.transacoes(getListaTransacoesDTO(idsImportados, loja))
					.saldoConta(getSaldoLoja(loja.getNomeLoja()))
			.build());
		}
		
		return listaOperacoes;
	}

	private BigDecimal getSaldoLoja(String nomeLoja) {
		BigDecimal totalCredito = lojaRepository.getTotalCredito(nomeLoja);
		BigDecimal totalDebito = lojaRepository.getTotalDebito(nomeLoja);
		if (totalCredito == null) {
			totalCredito = BigDecimal.ZERO;
		}
		if (totalDebito == null) {
			totalDebito = BigDecimal.ZERO;
		}
		return totalCredito.subtract(totalDebito);
	}

	private List<TransacoesDTO> getListaTransacoesDTO(List<Long> idsImportados, Loja loja) {
		List<Transacoes> operacoes = transacoesRepository.findAllByIdAndLoja(idsImportados, loja);
		List<TransacoesDTO> listaTransacoesDTO = new ArrayList<>(1);
		
		for (Transacoes t : operacoes) {
			listaTransacoesDTO.add(TransacoesDTO.builder()
					.cartao(t.getCartao())
					.cpf(t.getCpf())
					.data(t.getData())
					.id(t.getId())
					.hora(t.getHora())
					.tipo(t.getTipo().getTipo()+" - "+t.getTipo().getNatureza()+" - "+ t.getTipo().getDescricao())
					.valor(t.getValor())					
			.build());
		}
	
		return listaTransacoesDTO;
	}

	private Long processaLinha(String line, int cont) {
		Transacoes transacoes = new Transacoes();
		transacoes.setCartao(line.substring(30,42));
		transacoes.setCpf(line.substring(19,30));
		transacoes.setData(getData(line));
		transacoes.setHora(getHora(line));
		transacoes.setLoja(getLoja(line));
		transacoes.setTipo(getTipoTransacao(line, cont));		
		transacoes.setValor(getValor(line));
	
		Transacoes save = transacoesRepository.save(transacoes);
		
		return save.getId();
	}

	private Loja getLoja(String line) {
		String nomeLoja = line.substring(62);
		String donoLoja = line.substring(48,62);
		Loja loja;
		Optional<Loja> findById = lojaRepository.findById(nomeLoja);
		if (findById.isPresent()) {
			loja = findById.get();					
		} else {
			loja = new Loja();
			loja.setDonoLoja(donoLoja);
			loja.setNomeLoja(nomeLoja);
			
			loja = lojaRepository.save(loja);
		}
		return loja;
	}

	private LocalTime getHora(String line) {
		return LocalTime.of(Integer.valueOf(line.substring(42,44)), Integer.valueOf(line.substring(44,46)), Integer.valueOf(line.substring(46,48)));
	}

	private BigDecimal getValor(String line) {
		return BigDecimal.valueOf((Integer.valueOf(line.substring(9,19)) / 100.00));		
	}

	private LocalDate getData(String line) {
		return LocalDate.of(Integer.valueOf(line.substring(1,5)),Integer.valueOf(line.substring(5,7)), Integer.valueOf(line.substring(7,9)));
	}
	
	private TipoTransacao getTipoTransacao(String line, int cont) {
		int tipo = Integer.parseInt(line.substring(0,1));
		Optional<TipoTransacao> findById = tipoRepository.findById(tipo);
		
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new CustomException("Tipo transação inválida, linha "+cont);
		}
	}
	
	
}
