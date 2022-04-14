package com.bycoders.cnab.dominio.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.bycoders.cnab.dominio.entidades.Cnab;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CnabRepository implements PanacheRepository<Cnab> {
    
    
}
