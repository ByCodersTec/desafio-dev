package com.github.filipednb.financialtransactions.api.owner.impl;

import com.github.filipednb.financialtransactions.api.owner.OwnerEntity;
import com.github.filipednb.financialtransactions.api.owner.OwnerRepository;
import com.github.filipednb.financialtransactions.api.owner.OwnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class OwnerServiceImplTest {

    @Autowired
    OwnerService service;

    @MockBean
    OwnerRepository repository;

    @Test
    void shouldReturnNewPersistedOwner() {
        OwnerEntity entity = new OwnerEntity("JOAO DA PADARIA");
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);

        OwnerEntity persistedEntity = service.retrieveOwner("JOAO DA PADARIA");

        Assertions.assertEquals(entity, persistedEntity);
        Mockito.verify(repository, Mockito.never()).save(entity);
        Mockito.verify(repository, Mockito.atLeastOnce()).findByName("JOAO DA PADARIA");
    }

    @Test
    void shouldReturnAlreadyPersistedOwner() {
        OwnerEntity entity = new OwnerEntity("JOAO DA PADARIA");
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(entity);

        OwnerEntity persistedEntity = service.retrieveOwner("JOAO DA PADARIA");

        Assertions.assertEquals(entity, persistedEntity);
        Mockito.verify(repository, Mockito.never()).save(entity);
        Mockito.verify(repository, Mockito.atLeastOnce()).findByName("JOAO DA PADARIA");

    }

}