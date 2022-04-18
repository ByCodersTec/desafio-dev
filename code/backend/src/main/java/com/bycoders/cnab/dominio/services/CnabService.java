package com.bycoders.cnab.dominio.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.bycoders.cnab.application.controllers.dto.CnabDTO;
import com.bycoders.cnab.application.controllers.dto.CnabResumoDTO;
import com.bycoders.cnab.application.controllers.dto.TipoTransacaoDTO;
import com.bycoders.cnab.dominio.entidades.Cnab;
import com.bycoders.cnab.dominio.entidades.TipoTransacao;
import com.bycoders.cnab.dominio.repositories.CnabRepository;
import com.bycoders.cnab.dominio.repositories.TipoTransacaoRepository;
import com.bycoders.cnab.infraestrutura.utils.DataUtil;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@ApplicationScoped
public class CnabService {
    @Inject CnabRepository repository;
    @Inject TipoTransacaoRepository tipoTransacaoRepositorio;
    
    @Transactional
    public void uploadArquivo(final MultipartFormDataInput input){

        try {

            final InputPart file = input.getFormDataMap().get("file").get(0);
            final InputStream inputStream = file.getBody(InputStream.class, null);
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line;
            
            while ((line = br.readLine()) != null) {
                final BigDecimal valor = new BigDecimal(line.substring(9,19)).movePointLeft(1);
                final BigDecimal valorDivido = valor.divide(new BigDecimal("100"));
                // final Long tipoTransacaoID = Long.parseLong(line.substring(0,1));
                // final TipoTransacao tipoTransacao = tipoTransacaoRepositorio.findById(tipoTransacaoID);
               
                repository.persist(Cnab.builder()
                .tipo(Integer.parseInt(line.substring(0,1)))
                .dataHoraTransacao(DataUtil.converterStringParaLocalDateTimeWithFormato(line.substring(1,9)
                                                .concat(" ")
                                                .concat(line.substring(42, 48)), "yyyyMMdd HHmmss"))
                .valor(valorDivido)
                .cpf(line.substring(19, 30)) 
                .numeroCartao(line.substring(30, 42))
                .representanteLoja(line.substring(48, 62))
                .nomeLoja(line.substring(62))
                .build());

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public List<CnabDTO> obterTodosRegistros(){
        final List<CnabDTO> registrosCnabsDTO = new ArrayList<>();

        final List<Cnab> cnabs = repository.findOrdered();
        cnabs.stream().forEach(cnab -> {
            final TipoTransacao tipoTransacao = tipoTransacaoRepositorio.findByTipoTransacaoID(cnab.getTipo());
            final TipoTransacaoDTO tipoTransacaoDTO = TipoTransacaoDTO.buildDTO(tipoTransacao);
            
            registrosCnabsDTO.add(CnabDTO.builder()
                    .numeroCartao(cnab.getNumeroCartao())
                    .cpf(cnab.getCpf())
                    .dataHoraTransacao(DataUtil.converterLocalDateTimeForStringWithFormatter(cnab.getDataHoraTransacao(), "yyyy-MM-dd HH:mm:ss"))
                    .nomeLoja(cnab.getNomeLoja())
                    .representanteLoja(cnab.getRepresentanteLoja())
                    .valor(cnab.getValor())
                    .tipo(tipoTransacaoDTO)
                    .build());
                });

        return registrosCnabsDTO;
    }
    public List<CnabResumoDTO> obterResumos(){
        final List<CnabResumoDTO> registrosCnabsDTO = new ArrayList<>();

        final List<Cnab> cnabs = repository.findGroupBy();
        
        cnabs.stream().forEach(cnab -> {
            final TipoTransacao tipoTransacao = tipoTransacaoRepositorio.findByTipoTransacaoID(cnab.getTipo());
            final TipoTransacaoDTO tipoTransacaoDTO = TipoTransacaoDTO.buildDTO(tipoTransacao);
            
            registrosCnabsDTO.add(CnabResumoDTO.builder()
                    .nomeLoja(cnab.getNomeLoja())
                    .representanteLoja(cnab.getRepresentanteLoja())
                    .valor(cnab.getValor())
                    .sinal(tipoTransacaoDTO.getSinal())
                    .build());
                });

        return registrosCnabsDTO;
    }

    public List<CnabDTO> obterTodosRegistrosPorNomeLoja(final String nomeLoja){
        final List<CnabDTO> registrosCnabsDTO = new ArrayList<>();

        final List<Cnab> cnabs = repository.findOrdered();

        cnabs.stream().forEach(cnab -> {
            final TipoTransacao tipoTransacao = tipoTransacaoRepositorio.findByTipoTransacaoID(cnab.getTipo());
            final TipoTransacaoDTO tipoTransacaoDTO = TipoTransacaoDTO.buildDTO(tipoTransacao);
            
            registrosCnabsDTO.add(CnabDTO.builder()
                    .numeroCartao(cnab.getNumeroCartao())
                    .cpf(cnab.getCpf())
                    .dataHoraTransacao(DataUtil.converterLocalDateTimeForStringWithFormatter(cnab.getDataHoraTransacao(), "yyyy-MM-dd HH:mm:ss"))
                    .nomeLoja(cnab.getNomeLoja())
                    .representanteLoja(cnab.getRepresentanteLoja())
                    .valor(cnab.getValor())
                    .tipo(tipoTransacaoDTO)
                    .build());
                });

        return registrosCnabsDTO;
    }
}
