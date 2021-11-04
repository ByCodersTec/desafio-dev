package com.github.filipednb.financialtransactions.api.transaction;

import com.github.filipednb.financialtransactions.api.store.StoreEntity;

import java.math.BigDecimal;
import java.util.List;

public class TransactionsResume {

    public TransactionsResume() {
    }

    private StoreEntity store;
    private List<TransactionEntity> transactions;
    private BigDecimal totalAmount;

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
