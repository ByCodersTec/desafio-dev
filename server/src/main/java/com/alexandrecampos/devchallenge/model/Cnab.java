package com.alexandrecampos.devchallenge.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cnab", schema = "core")
public class Cnab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operation_type_id")
    private Integer operationTypeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OperationType operationType;

    private LocalDateTime date;

    @Column(name = "document_id")
    private String documentId;

    private String value;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "store_owner")
    private String storeOwner;

    @Column(name = "store_name")
    private String storeName;
}
