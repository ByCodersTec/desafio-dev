package com.github.filipednb.financialtransactions.api.store.impl;

import com.github.filipednb.financialtransactions.api.store.StoreEntity;
import com.github.filipednb.financialtransactions.api.store.StoreRepository;
import com.github.filipednb.financialtransactions.api.store.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private static final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

    private final StoreRepository repository;

    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public StoreEntity retrieveStore(String name) {
        log.info("M=retrieveStore, I=Retrieving store, name={}", name);

        StoreEntity store = repository.findByName(name);

        if(store == null) {
            log.info("M=retrieveStore, I=Will create store, name={}", name);
            return repository.save(new StoreEntity(name));
        }

        return store;
    }
}
