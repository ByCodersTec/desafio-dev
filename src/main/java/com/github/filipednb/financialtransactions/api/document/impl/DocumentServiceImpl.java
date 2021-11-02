package com.github.filipednb.financialtransactions.api.document.impl;

import com.github.filipednb.financialtransactions.api.document.DocumentEntity;
import com.github.filipednb.financialtransactions.api.document.DocumentRepository;
import com.github.filipednb.financialtransactions.api.document.DocumentService;
import com.github.filipednb.financialtransactions.api.store.impl.StoreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

    private DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentEntity retrieveDocument(String numDocument) {
        log.info("M=retrieveDocument, I=Retrieving document, numDocument={}", numDocument);

        DocumentEntity document = repository.findByNumDocument(numDocument);

        if(document == null) {
            log.info("M=retrieveDocument, I=Will document, numDocument={}", numDocument);
            return repository.save(new DocumentEntity(numDocument));
        }
        return document;
    }
}
