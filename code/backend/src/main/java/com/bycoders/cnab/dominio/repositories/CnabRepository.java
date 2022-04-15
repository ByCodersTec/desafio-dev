package com.bycoders.cnab.dominio.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.bycoders.cnab.dominio.entidades.Cnab;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CnabRepository implements PanacheRepository<Cnab> {
        
    public List<Cnab> findByNome(String nomeLoja){
        return find("nomeLoja", nomeLoja).list();
    }
        
    public List<Cnab> findOrdered(){
        return find("order by nomeLoja").list();
    }
    
}
