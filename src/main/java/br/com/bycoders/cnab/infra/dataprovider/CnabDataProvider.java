package br.com.bycoders.cnab.infra.dataprovider;

import br.com.bycoders.cnab.core.domain.Cnab;
import br.com.bycoders.cnab.core.gateway.CnabGateway;
import br.com.bycoders.cnab.infra.entity.CnabEntity;
import br.com.bycoders.cnab.infra.repository.CnabRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Cnab> findAll() {
        return cnabRepository.findAll().stream().map(o -> convertToDomain(o)).collect(Collectors.toList());
    }

    private CnabEntity convertToEntity(Cnab cnab) {
        CnabEntity cnabEntity = new CnabEntity();
        cnabEntity.setId(cnab.getId());
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

    private Cnab convertToDomain(CnabEntity cnabEntity) {
        Cnab cnab = new Cnab();
        cnab.setId(cnabEntity.getId());
        cnab.setType(cnabEntity.getType());
        cnab.setDate(cnabEntity.getDate());
        cnab.setValue(cnabEntity.getValue());
        cnab.setCpf(cnabEntity.getCpf());
        cnab.setCard(cnabEntity.getCard());
        cnab.setHour(cnabEntity.getHour());
        cnab.setOwner(cnabEntity.getOwner());
        cnab.setName(cnabEntity.getName());

        return cnab;
    }
}
