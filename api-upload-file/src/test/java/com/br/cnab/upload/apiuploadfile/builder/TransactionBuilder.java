package com.br.cnab.upload.apiuploadfile.builder;

import com.br.cnab.upload.apiuploadfile.entity.Transaction;

public class TransactionBuilder {

    private Integer transactionType;
    private String transactionDate;
    private Double transactionValue;
    private String cpf;
    private String card;
    private String occurrenceTime;
    private String storeOwner;
    private String storeName;

    public TransactionBuilder withTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public TransactionBuilder withTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public TransactionBuilder withTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
        return this;
    }

    public TransactionBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public TransactionBuilder withCard(String card) {
        this.card = card;
        return this;
    }

    public TransactionBuilder withOccurrenceTime(String occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
        return this;
    }

    public TransactionBuilder withStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
        return this;
    }

    public TransactionBuilder withStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(this.transactionType);
        transaction.setTransactionDate(this.transactionDate);
        transaction.setTransactionValue(this.transactionValue);
        transaction.setCpf(this.cpf);
        transaction.setCard(this.card);
        transaction.setOccurrenceTime(this.occurrenceTime);
        transaction.setStoreOwner(this.storeOwner);
        transaction.setStoreName(this.storeName);
        return transaction;
    }

    public static TransactionBuilder create() {
        return new TransactionBuilder();
    }
}
