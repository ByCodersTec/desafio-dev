package com.github.filipednb.financialtransactions.api.transaction;

import com.github.filipednb.financialtransactions.api.document.DocumentEntity;
import com.github.filipednb.financialtransactions.api.enums.TransactionType;
import com.github.filipednb.financialtransactions.api.owner.OwnerEntity;
import com.github.filipednb.financialtransactions.api.store.StoreEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRANSACTIONS", schema = "FINANCIAL_TRANSACTIONS")
public class TransactionEntity {

    public TransactionEntity() {
    }

    @Id
    @Column(name = "ID_TRANSACTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_OWNER")
    private OwnerEntity owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_STORE")
    private StoreEntity store;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DOCUMENT")
    private DocumentEntity document;

    @Column(name = "NUM_CARD")
    private String numCard;

    @Enumerated
    @Column(name = "COD_TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "NUM_AMOUNT")
    private Float amount;

    @Column(name = "DATE_OCCURRENCE")
    private LocalDateTime dateOccurrence;

    @CreatedDate
    @Column(name = "DATE_CREATION")
    private LocalDateTime dateCreation;

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

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOccurrence() {
        return dateOccurrence;
    }

    public void setDateOccurrence(LocalDateTime dateOccurrence) {
        this.dateOccurrence = dateOccurrence;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
