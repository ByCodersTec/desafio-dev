package br.com.bycoders.cnab.core.usecase;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.gateway.CnabGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageCnabUseCaseImpl implements ManageCnabUseCase {

    private final CnabGateway cnabGateway;

    public ManageCnabUseCaseImpl(CnabGateway cnabGateway) {
        this.cnabGateway = cnabGateway;
    }

    @Override
    public List<Cnab> findAll() {
        return cnabGateway.findAll();
    }
}
