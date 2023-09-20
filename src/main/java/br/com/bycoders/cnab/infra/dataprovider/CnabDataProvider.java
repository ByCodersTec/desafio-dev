package br.com.bycoders.cnab.infra.dataprovider;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.gateway.CnabGateway;
import br.com.bycoders.cnab.infra.entity.CnabEntity;
import br.com.bycoders.cnab.infra.repository.CnabRepository;
import org.springframework.stereotype.Component;

@Component
public class CnabDataProvider implements CnabGateway {

    private final CnabRepository cnabRepository;

    public CnabDataProvider(CnabRepository cnabRepository) {
        this.cnabRepository = cnabRepository;
    }

    @Override
    public void save(Cnab cnab) {
        CnabEntity cnabEntity = convertToEntity(cnab);
        cnabRepository.save(cnabEntity);
    }

    private CnabEntity convertToEntity(Cnab cnab) {
        CnabEntity cnabEntity = new CnabEntity();
        cnabEntity.setType(cnab.getType());
        cnabEntity.setDate(cnab.getDate());
        cnabEntity.setValue(cnab.getValue());
        cnabEntity.setCpf(cnab.getCpf());
        cnabEntity.setCard(cnab.getCard());
        cnabEntity.setHour(cnab.getHour());
        cnabEntity.setOwner(cnab.getOwner());
        cnabEntity.setName(cnab.getName());

        return cnabEntity;
    }
}
