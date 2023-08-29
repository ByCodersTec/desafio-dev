package com.bycoders.desafiodev.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_transaction_type")
public class TransactionType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String description;
    private String transaction_type;
    private String signal;
    private OffsetDateTime dt_insert;

}
