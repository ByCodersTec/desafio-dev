package com.br.cnab.upload.apiuploadfile.service.impl;

import com.br.cnab.upload.apiuploadfile.entity.Transaction;
import com.br.cnab.upload.apiuploadfile.model.StoreOperation;
import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;
import com.br.cnab.upload.apiuploadfile.repository.TransactionFileRepository;
import com.br.cnab.upload.apiuploadfile.service.TransactionFileService;
import com.br.cnab.upload.apiuploadfile.utils.DateTimeUtils;
import com.br.cnab.upload.apiuploadfile.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionFileServiceImpl implements TransactionFileService {

    private final TransactionFileRepository transactionFileRepository;

    public void processTransactionFile(MultipartFile file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Transaction transaction = parseFile(line);
                transactionFileRepository.save(transaction);
            }
        }
    }

    public List<StoreOperationResponse> listStoreOperations() {
        List<StoreOperationResponse> storeOperationsList = new ArrayList<>();
        List<Transaction> allRecords = transactionFileRepository.findAll();

        Map<String, StoreOperation> storeMap = new HashMap<>();

        for (Transaction transaction : allRecords) {
            String storeName = transaction.getStoreName().trim();

            if (!storeMap.containsKey(storeName)) {
                StoreOperation storeOperations = new StoreOperation(storeName);
                storeMap.put(storeName, storeOperations);
            }

            StoreOperation storeOperations = storeMap.get(storeName);
            storeOperations.addOperation(transaction);
        }

        for (StoreOperation storeOperation : storeMap.values()) {
            storeOperationsList.add(storeOperation.toResponse());
        }

        return storeOperationsList;
    }

    private Transaction parseFile(String line) {
        String card = line.substring(30, 42);
        String hour = line.substring(42, 48);
        String owner = line.substring(48, 62);
        String storeName = line.substring(62).trim();
        Integer type = Integer.parseInt(line.substring(0, 1));
        String date = line.substring(1, 9);
        double value = Double.parseDouble(line.substring(9, 19)) / 100;
        String cpf = line.substring(19, 30);

        Transaction transaction = new Transaction();

        transaction.setTransactionType(type);
        transaction.setTransactionDate(DateUtils.formatDateToYYYYMMDD(date));
        transaction.setTransactionValue(value);
        transaction.setCpf(cpf);
        transaction.setCard(card);
        transaction.setOccurrenceTime(DateTimeUtils.formatTimeInUTC3(hour));
        transaction.setStoreOwner(owner);
        transaction.setStoreName(storeName);

        return transaction;
    }

}




