package br.com.bycoders.cnab.core.usecase;

import br.com.bycoders.cnab.core.domain.Cnab;

import java.util.List;

public interface ManageCnabUseCase {
    List<Cnab> findAll();
}
