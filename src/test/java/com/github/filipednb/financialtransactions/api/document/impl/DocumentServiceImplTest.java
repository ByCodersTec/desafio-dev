package com.github.filipednb.financialtransactions.api.document.impl;

import com.github.filipednb.financialtransactions.api.document.DocumentEntity;
import com.github.filipednb.financialtransactions.api.document.DocumentRepository;
import com.github.filipednb.financialtransactions.api.document.DocumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DocumentServiceImplTest {

    @Autowired
    DocumentService service;

    @MockBean
    DocumentRepository repository;

    @Test
    void shouldReturnNewPersistedDocument() {
        DocumentEntity entity = new DocumentEntity("23109005832");
        Mockito.when(repository.findByNumDocument(Mockito.any())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);

        DocumentEntity persistedEntity = service.retrieveDocument("23109005832");

        Assertions.assertEquals(entity, persistedEntity);
        
    }

    @Test
    void shouldReturnAlreadyPersistedDocument() {
        DocumentEntity entity = new DocumentEntity("23109005832");
        Mockito.when(repository.findByNumDocument(Mockito.any())).thenReturn(entity);

        DocumentEntity persistedEntity = service.retrieveDocument("23109005832");

        Assertions.assertEquals(entity, persistedEntity);
        Mockito.verify(repository, Mockito.never()).save(entity);

    }
}