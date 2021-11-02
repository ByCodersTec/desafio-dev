package com.github.filipednb.financialtransactions.api.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    private static final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionService service;

    public TransactionResource(final TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionEntity> getAll() {
        log.info("M=getAll, I=Get all transactions");

        return service.getAll();

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<TransactionEntity> uploadTransactionsFile(@RequestParam("file") MultipartFile file) {
        log.info("M=saveAll, I=Uploading transactions file, file={}", file);

        service.uploadFile(file);

        return null;
    }


}
