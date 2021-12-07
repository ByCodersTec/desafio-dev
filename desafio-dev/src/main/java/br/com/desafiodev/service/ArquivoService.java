package br.com.desafiodev.service;

import br.com.desafiodev.dto.TipoTransacaoDTO;
import br.com.desafiodev.dto.TransacaoDTO;
import br.com.desafiodev.model.Transacao;
import br.com.desafiodev.repository.TipoTransacaoRepository;
import br.com.desafiodev.repository.TransacaoRepository;
import br.com.desafiodev.utils.DataUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArquivoService {

    private TransacaoRepository repository;
    private TipoTransacaoRepository tipoTransacaoRepository;
    private ModelMapper modelMapper;

    public ArquivoService(TransacaoRepository repository, TipoTransacaoRepository tipoTransacaoRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.tipoTransacaoRepository = tipoTransacaoRepository;
        this.modelMapper = modelMapper;
    }

    public void salvaArquivo(MultipartFile file) throws IOException {

        List<TransacaoDTO> transacoes = getTransacaoDTOS(file);

        List<Transacao> entities = transacoes.stream().map(element -> modelMapper.map(element, Transacao.class))
        .collect(Collectors.toList());

        repository.saveAll(entities);
    }



    private List<TransacaoDTO> getTransacaoDTOS(MultipartFile file) throws IOException {

        List<TransacaoDTO> transacoes = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = file.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = br.readLine()) != null) {
            transacoes.add(TransacaoDTO.builder()
                    .idTipo(modelMapper
                            .map(tipoTransacaoRepository.findById(Long.parseLong(line.substring(0, 1))).get(), TipoTransacaoDTO.class))
                    .dataTransacao(DataUtils.parseLocalDate(line.substring(1, 9)))
                    .valor(new BigDecimal(line.substring(9, 19)).divide(BigDecimal.valueOf(100)))
                    .cpf(line.substring(19, 30))
                    .cartao(line.substring(30, 42))
                    .hora(DataUtils.parseLocalTime(line.substring(42, 48)))
                    .nomeDono(line.substring(48, 62))
                    .nomeLoja(line.substring(62, Math.min(line.length(), 81)))
                    .build());
        }
        return transacoes;
    }

}
