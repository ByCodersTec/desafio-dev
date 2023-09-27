package com.br.cnab.upload.apiuploadfile.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer transactionType;
    private String transactionDate;
    private double transactionValue;
    private String cpf;
    private String card;
    private String occurrenceTime;
    private String storeOwner;
    private String storeName;

    public Transaction() {
    }
}
