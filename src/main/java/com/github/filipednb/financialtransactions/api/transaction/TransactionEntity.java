package com.github.filipednb.financialtransactions.api.transaction;

import com.github.filipednb.financialtransactions.api.owner.OwnerEntity;
import com.github.filipednb.financialtransactions.api.store.StoreEntity;
import com.github.filipednb.financialtransactions.api.transactiontype.TransactionTypeEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    public TransactionEntity() {
    }

    @Id
    @Column(name = "ID_TRANSACTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private OwnerEntity owner;

    @OneToOne
    private StoreEntity store;

    @OneToOne
    private TransactionTypeEntity transactionType;

    @Column(name = "NUM_AMOUNT")
    private Float amount;

    private Date dateOccurrence;

    @CreatedDate
    private Date dateCreation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public TransactionTypeEntity getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDateOccurrence() {
        return dateOccurrence;
    }

    public void setDateOccurrence(Date dateOccurrence) {
        this.dateOccurrence = dateOccurrence;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
