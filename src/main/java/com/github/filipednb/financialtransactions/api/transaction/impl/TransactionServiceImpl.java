package com.github.filipednb.financialtransactions.api.transaction.impl;

import com.github.filipednb.financialtransactions.api.transaction.TransactionEntity;
import com.github.filipednb.financialtransactions.api.transaction.TransactionRepository;
import com.github.filipednb.financialtransactions.api.transaction.TransactionService;
import com.github.filipednb.financialtransactions.file.FileParser;
import com.github.filipednb.financialtransactions.file.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository repository;
    private final FileParser fileParser;

    public TransactionServiceImpl(final TransactionRepository repository,
                                  final FileParser fileParser) {
        this.repository = repository;
        this.fileParser = fileParser;
    }


    @Override
    public List<TransactionEntity> getAll() {
        return null;
    }

    @Override
    public List<TransactionEntity> uploadFile(MultipartFile file) {
        log.info("M=uploadFile, I=Uploading file, fileName={}", file.getOriginalFilename());
        List<TransactionDTO> transactionDTOS = fileParser.parse(file);

        return null;
    }
}
