package com.br.cnab.upload.apiuploadfile.model;

import com.br.cnab.upload.apiuploadfile.entity.Transaction;
import com.br.cnab.upload.apiuploadfile.enums.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class StoreOperation {

    private final String storeName;
    private final List<Transaction> operations;
    private double totalBalance;

    public StoreOperation(String storeName) {
        this.storeName = storeName;
        this.operations = new ArrayList<>();
        this.totalBalance = 0.0;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<Transaction> getOperations() {
        return operations;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void addOperation(Transaction transaction) {
        operations.add(transaction);

        String transactionType = transaction.getTransactionType().toString().trim();

        TransactionTypeEnum type = TransactionTypeEnum.fromCode(transactionType);

        if (type != null) {
            double normalizedValue = transaction.getTransactionValue();
            totalBalance += type.getSignal() * normalizedValue;
        }
    }

    public StoreOperationResponse toResponse() {
        return new StoreOperationResponse(this.storeName, this.totalBalance);
    }
}
