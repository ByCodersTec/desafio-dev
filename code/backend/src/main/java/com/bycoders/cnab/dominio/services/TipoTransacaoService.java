package com.bycoders.cnab.dominio.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.bycoders.cnab.dominio.repositories.TipoTransacaoRepository;

@ApplicationScoped
public class TipoTransacaoService {
    
    @Inject
    TipoTransacaoRepository tipoTransacaoRepository;
    
    public void popularBancoTipoTransacao() {
        System.out.println("Chegou aqui no tipoTransacaoService");
        // final String insert_sql = "INSERT INTO public.tipotransacao (tipo, descricao, natureza, sinal ) VALUES(?,?, ?, ?)";
        // tipoTransacaoRepository.getEntityManager().createNativeQuery(insert_sql).setParameter(1, 9).setParameter(2, "Aluguel").setParameter(3, "Saída").setParameter(4, "-").executeUpdate();
    }
}
