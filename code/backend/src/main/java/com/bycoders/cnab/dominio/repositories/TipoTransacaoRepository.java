package com.bycoders.cnab.dominio.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.bycoders.cnab.dominio.entidades.TipoTransacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TipoTransacaoRepository implements PanacheRepository<TipoTransacao>{
    
    public TipoTransacao findByTipoTransacaoID(Integer tipoId){
        return find("tipo", tipoId).firstResult();
    }

}
