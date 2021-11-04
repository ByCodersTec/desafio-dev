package com.github.filipednb.financialtransactions.api.transaction.impl;

import com.github.filipednb.financialtransactions.api.document.DocumentService;
import com.github.filipednb.financialtransactions.api.enums.Movement;
import com.github.filipednb.financialtransactions.api.exception.NotFoundStoreException;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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

    @Override
    public TransactionsResume getTransactionsResumeByStoreName(String storeName) {
        log.info("M=getTransactionsResumeByStoreName, I=Searching for store transactions, storeName={}", storeName);

        List<TransactionEntity> transactions = repository.findByStoreName(storeName);

        Optional.ofNullable(transactions).orElseThrow(() -> new NotFoundStoreException("Loja não encontrada"));

        final TransactionsResume resume = new TransactionsResume();

        resume.setStore(transactions.get(0).getStore());
        resume.setTransactions(transactions);
        resume.setTotalAmount(sumTotalAmount(transactions));

        return resume;
    }

    private BigDecimal sumTotalAmount(List<TransactionEntity> transactions) {
        BigDecimal totalIN = BigDecimal.ZERO;
        BigDecimal totalOUT = BigDecimal.ZERO;

        for(TransactionEntity t : transactions) {
            if(Movement.IN.equals(t.getTransactionType().getMovement())) {
                totalIN = totalIN.add(t.getAmount());
            } else if(Movement.OUT.equals(t.getTransactionType().getMovement())) {
                totalOUT = totalOUT.add(t.getAmount());
            }
        }

        return totalIN.subtract(totalOUT);
    }
}
