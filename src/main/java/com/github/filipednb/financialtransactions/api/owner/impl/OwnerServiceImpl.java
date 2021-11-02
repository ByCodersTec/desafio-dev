package com.github.filipednb.financialtransactions.api.owner.impl;

import com.github.filipednb.financialtransactions.api.owner.OwnerEntity;
import com.github.filipednb.financialtransactions.api.owner.OwnerRepository;
import com.github.filipednb.financialtransactions.api.owner.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);

    private final OwnerRepository repository;

    public OwnerServiceImpl(final OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public OwnerEntity retrieveOwner(String name) {
        log.info("M=retrieveOwner, I=Retrieving owner, name={}", name);

        OwnerEntity owner = repository.findByName(name);

        if(owner == null) {
            log.info("M=retrieveOwner, I=Will create owner, name={}", name);
            return repository.save(new OwnerEntity(name));
        }
        return owner;
    }
}
