package com.github.filipednb.financialtransactions.api.store.impl;

import com.github.filipednb.financialtransactions.api.store.StoreEntity;
import com.github.filipednb.financialtransactions.api.store.StoreRepository;
import com.github.filipednb.financialtransactions.api.store.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class StoreServiceImplTest {

    @Autowired
    StoreService service;

    @MockBean
    StoreRepository repository;

    @Test
    void shouldReturnNewPersistedOwner() {
        StoreEntity entity = new StoreEntity("PADARIA");
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);

        StoreEntity persistedEntity = service.retrieveStore("PADARIA");

        Assertions.assertEquals(entity, persistedEntity);
        Mockito.verify(repository, Mockito.never()).save(entity);
        Mockito.verify(repository, Mockito.atLeastOnce()).findByName("PADARIA");
    }

    @Test
    void shouldReturnAlreadyPersistedOwner() {
        StoreEntity entity = new StoreEntity("PADARIA");
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(entity);

        StoreEntity persistedEntity = service.retrieveStore("PADARIA");

        Assertions.assertEquals(entity, persistedEntity);
        Mockito.verify(repository, Mockito.never()).save(entity);
        Mockito.verify(repository, Mockito.atLeastOnce()).findByName("PADARIA");

    }

}