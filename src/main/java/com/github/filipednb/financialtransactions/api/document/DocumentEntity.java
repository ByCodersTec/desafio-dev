package com.github.filipednb.financialtransactions.api.document;

import com.github.filipednb.financialtransactions.api.transaction.TransactionEntity;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENTS")
public class DocumentEntity {

    public DocumentEntity() {
    }

    public DocumentEntity(String numDocument) {
        this.numDocument = numDocument;
    }

    @Id
    @Column(name = "ID_DOCUMENT")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NUM_DOCUMENT", unique = true)
    private String numDocument;

    @JoinColumn(name="ID_TRANSACTION")
    @OneToOne(fetch = FetchType.LAZY)
    private TransactionEntity transaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumDocument() {
        return numDocument;
    }

    public void setNumDocument(String numDocument) {
        this.numDocument = numDocument;
    }
}
