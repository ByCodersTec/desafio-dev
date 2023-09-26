package com.br.cnab.upload.apiuploadfile.controller;

import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.service.TransactionFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionFileController {

    private final TransactionFileService transactionFileService;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                String errorMessage = "Empty file";
                return ResponseEntity.badRequest().body(errorMessage);
            }

            transactionFileService.processTransactionFile(file);

            String successMessage = "File uploaded successfully.";
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error uploading file.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/store-operations")
    public ResponseEntity<List<StoreOperationResponse>> storeOperations() {
        List<StoreOperationResponse> storeOperationsList = transactionFileService.listStoreOperations();
        return new ResponseEntity<>(storeOperationsList, HttpStatus.OK);
    }

}
