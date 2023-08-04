package br.com.bycoders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import br.com.bycoders.entity.Loja;
import br.com.bycoders.entity.Operacao;
import br.com.bycoders.entity.TipoTransacao;
import br.com.bycoders.entity.Transacoes;

public interface Mocks {
	
	static MockMultipartFile getMockFile() {
		StringBuilder sb = new StringBuilder("1201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ");
								   sb.append("2201903010000011200096206760173648****0099234234JOÃO MACEDO   BAR DO JOÃO       ");
								   sb.append("1201903010000015200096206760171234****7890233000JOÃO MACEDO   BAR DO JOÃO       ");

		
		return new MockMultipartFile(
			    "arquivo",
			    "cnab.txt",
			    MediaType.TEXT_PLAIN_VALUE,
			    sb.toString().getBytes()
			);
	}

	static Object getTransacoesUnico() {
		Transacoes transacoes = new Transacoes();
		transacoes.setId(1L);		
		return transacoes;
	}
	
	static Optional<Loja> getOptionalLoja() {
		Loja loja = new Loja();
		loja.setDonoLoja("JOÃO MACEDO   ");
		loja.setNomeLoja("BAR DO JOÃO       ");				
		return Optional.of(loja);		
	}

	static Optional<TipoTransacao> getOptionalTipoTransacaoCredito() {
		TipoTransacao tp = new TipoTransacao();
		tp.setTipo(1);
		tp.setDescricao("Débito");
		tp.setNatureza("Entrada");
		tp.setOperacao(Operacao.SOMAR);
		return Optional.of(tp);	
	}
	
	static Optional<TipoTransacao> getOptionalTipoTransacaoDebito() {
		TipoTransacao tp = new TipoTransacao();
		tp.setTipo(2);
		tp.setDescricao("Boleto");
		tp.setNatureza("Saída");
		tp.setOperacao(Operacao.SUBTRAIR);
		return Optional.of(tp);	
	}

	static List<Loja> getListLojas() {
		List<Loja> lista = new ArrayList<>(1);
		Loja loja = new Loja();
		loja.setDonoLoja("JOÃO MACEDO   ");
		loja.setNomeLoja("BAR DO JOÃO       ");	
		lista.add(loja);
		return lista;
	}

	static List<Transacoes> getListTransacoes() {
		List<Transacoes> listaTransacao = new ArrayList<>(1);
		
		Transacoes t1 = new Transacoes();
		t1.setCartao("4753****3153");
		t1.setCpf("09620676017");
		t1.setData(LocalDate.of(2019, 3, 1));
		t1.setId(424l);
		t1.setHora(LocalTime.of(15, 34, 53));
		
		TipoTransacao tp = new TipoTransacao();
		tp.setTipo(1);
		tp.setDescricao("Débito");
		tp.setNatureza("Entrada");
		tp.setOperacao(Operacao.SOMAR);		
		t1.setTipo(tp);
		t1.setValor(BigDecimal.valueOf(142.0));
		listaTransacao.add(t1);
		
		Transacoes t2 = new Transacoes();
		t2.setCartao("4753****3153");
		t2.setCpf("09620676017");
		t2.setData(LocalDate.of(2019, 3, 1));
		t2.setId(427l);
		t2.setHora(LocalTime.of(23, 42, 34));
		
		TipoTransacao tp2 = new TipoTransacao();
		tp2.setTipo(1);
		tp2.setTipo(2);
		tp2.setDescricao("Boleto");
		tp2.setNatureza("Saída");
		tp2.setOperacao(Operacao.SUBTRAIR);		
		t2.setTipo(tp2);
		t2.setValor(BigDecimal.valueOf(112.0));
		listaTransacao.add(t2);
		
		Transacoes t3 = new Transacoes();
		t3.setCartao("4753****3153");
		t3.setCpf("09620676017");
		t3.setData(LocalDate.of(2019, 3, 1));
		t3.setId(428l);
		t3.setHora(LocalTime.of(23, 30, 00));
		t3.setTipo(tp);
		t3.setValor(BigDecimal.valueOf(152.0));
		listaTransacao.add(t3);
		
		
		return listaTransacao;
	}

	

}
