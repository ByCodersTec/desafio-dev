package com.github.filipednb.financialtransactions.api.transaction.impl;

import com.github.filipednb.financialtransactions.api.document.DocumentService;
import com.github.filipednb.financialtransactions.api.owner.OwnerService;
import com.github.filipednb.financialtransactions.api.store.StoreService;
import com.github.filipednb.financialtransactions.api.transaction.*;
import com.github.filipednb.financialtransactions.file.FileParser;
import com.github.filipednb.financialtransactions.file.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository repository;
    private final FileParser fileParser;
    private final TransactionMapper mapper;
    private final StoreService storeService;
    private final DocumentService documentService;
    private final OwnerService ownerService;

    public TransactionServiceImpl(final TransactionRepository repository,
                                  final FileParser fileParser,
                                  final TransactionMapper mapper,
                                  final StoreService storeService,
                                  final DocumentService documentService,
                                  final OwnerService ownerService) {
        this.repository = repository;
        this.fileParser = fileParser;
        this.mapper = mapper;
        this.storeService = storeService;
        this.documentService = documentService;
        this.ownerService = ownerService;
    }


    @Override
    public List<TransactionEntity> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) {
        log.info("M=uploadFile, I=Uploading file, fileName={}", file.getOriginalFilename());
        List<TransactionDTO> transactionDTOS = fileParser.parse(file);
        List<TransactionEntity> entities = transactionDTOS.stream()
                .map(mapper::toEntity)
                .peek(t -> {
                    t.setStore(storeService.retrieveStore(t.getStore().getName()));
                    t.setDocument(documentService.retrieveDocument(t.getDocument().getNumDocument()));
                    t.setOwner(ownerService.retrieveOwner(t.getOwner().getName()));
                })
                .collect(Collectors.toList());

        repository.saveAll(entities);
    }
}
