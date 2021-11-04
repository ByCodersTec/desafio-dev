package com.github.filipednb.financialtransactions.api.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.websocket.server.PathParam;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadTransactionsFile(@NotEmpty(message = "O arquivo é obrigatório!")  MultipartFile file) {
        log.info("M=saveAll, I=Uploading transactions file, fileName={}", file.getOriginalFilename());
        service.uploadFile(file);
    }

    @GetMapping("/resume")
    public TransactionsResume getTransactionsResumeByStoreName(@PathParam("storeName") @NotEmpty String storeName) {
        log.info("M=getTransactionsResumeByStoreName, I=Get all transactions from store, storeName={}", storeName);
        return service.getTransactionsResumeByStoreName(storeName);
    }

}
