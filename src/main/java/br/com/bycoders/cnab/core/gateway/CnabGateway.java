package br.com.bycoders.cnab.core.gateway;

import br.com.bycoders.cnab.core.domain.Cnab;

import java.util.List;

public interface CnabGateway {
    void save(Cnab cnab);

    List<Cnab> findAll();
}
