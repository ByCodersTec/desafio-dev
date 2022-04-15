package com.bycoders.cnab.infraestrutura.inicializer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.bycoders.cnab.dominio.repositories.TipoTransacaoRepository;
import com.bycoders.cnab.dominio.services.TipoTransacaoService;

import io.quarkus.runtime.StartupEvent;


@ApplicationScoped
public class PopularBanco {

    @Inject
    TipoTransacaoService transacaoService;

    @Inject
    TipoTransacaoRepository repositorio;
    
    void onStart(@Observes StartupEvent ev){
        System.out.println("Iniciou .....");
        transacaoService.popularBancoTipoTransacao();
    }
}
