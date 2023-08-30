package com.bycoders.desafiodev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTransactionType")
    private TransactionType transactionType;

    private LocalDate insertDate;
    private double movimentValue;
    private String cpf;
    private String card;
    private LocalTime insertHour;
    private String storeName;
    private String storePropertyName;

}