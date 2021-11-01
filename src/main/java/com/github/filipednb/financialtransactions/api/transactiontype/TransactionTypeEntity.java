package com.github.filipednb.financialtransactions.api.transactiontype;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_TYPES")
public class TransactionTypeEntity {

    public TransactionTypeEntity() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACTION_TYPE")
    private Integer id;

    @Column(name = "DESC_TRANSACTION")
    private String description;

    @Column
    private TransactionType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
