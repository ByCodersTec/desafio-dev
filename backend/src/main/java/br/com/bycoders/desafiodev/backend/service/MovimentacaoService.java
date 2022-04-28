package br.com.bycoders.desafiodev.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.desafiodev.backend.model.Movimentacao;
import br.com.bycoders.desafiodev.backend.model.TipoTransacao;
import br.com.bycoders.desafiodev.backend.repository.MovimentacaoRepository;
import br.com.bycoders.desafiodev.backend.repository.TipoTransacaoRepository;

@Service
public class MovimentacaoService {
    

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("Hmmss");

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Movimentacao> salvar(MultipartFile arquivoCnab) throws IOException{
        return movimentacaoRepository.saveAll(extrairDados(arquivoCnab));
    }

    public Page<Movimentacao> listar(Pageable paginacao){

        return movimentacaoRepository.findAll(paginacao);

    }

    public List<Movimentacao> extrairDados(MultipartFile arquivoCnab) throws IOException{
        
        List<TipoTransacao> listaTipoTransacoes = tipoTransacaoRepository.findAll();

        List<String> linhas = obterLinhas(arquivoCnab);

        return linhas.stream().map(linha -> this.converterLinhaEmMovimentacao(linha, listaTipoTransacoes)).collect(Collectors.toList());
    }

    public List<String> obterLinhas(MultipartFile arquivoCnab) throws IOException{
        
        InputStream inputStream = arquivoCnab.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return bufferedReader.lines().map(StringUtils::stripAccents).collect(Collectors.toList());

    }


    public Movimentacao converterLinhaEmMovimentacao(String linha, List<TipoTransacao> listaTipoTransacoes){
     
        String tipoTransacaoId = this.recuperarDado(linha, 1, 1);
        String data = this.recuperarDado(linha, 2, 8);
        String valor = this.recuperarDado(linha, 10, 10);
        String cpf = this.recuperarDado(linha, 20, 11).strip();
        String cartao = this.recuperarDado(linha, 31, 12).strip();
        String hora = this.recuperarDado(linha, 43, 6);
        String donoLoja = this.recuperarDado(linha, 49, 14).strip();
        String nomeLoja = this.recuperarDado(linha, 63, 19).strip();

        TipoTransacao tipoTransacao = null;

        Optional<TipoTransacao> tipoTransacaoOptional = listaTipoTransacoes.stream().filter(t -> t.getId().equals(Integer.parseInt(tipoTransacaoId))).findFirst();

        if(tipoTransacaoOptional.isPresent()){
            tipoTransacao = tipoTransacaoOptional.get();
        }

        return Movimentacao.builder()
            .tipo(tipoTransacao)
            .data(LocalDate.parse(data, DATE_FORMAT))
            .valor(new BigDecimal(valor).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))
            .cpf(cpf)
            .cartao(cartao)
            .hora(LocalTime.parse(hora, TIME_FORMAT))
            .donoLoja(donoLoja)
            .nomeLoja(nomeLoja)
            .build();
    }

    private String recuperarDado(String linha, int inicio, int tamanho){
        
        if (inicio > linha.length()) {
            return "";
        }
        
        return linha.substring(inicio - 1, Math.min(inicio - 1 + tamanho, linha.length()));
    }
}
