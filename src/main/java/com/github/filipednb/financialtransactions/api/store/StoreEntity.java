package com.github.filipednb.financialtransactions.api.store;

import com.github.filipednb.financialtransactions.api.transaction.TransactionEntity;

import javax.persistence.*;

@Entity
@Table(name = "STORES")
public class StoreEntity {

    public StoreEntity() {
    }

    public StoreEntity(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "ID_STORE")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAM_STORE", unique = true)
    private String name;

    @JoinColumn(name="ID_TRANSACTION")
    @OneToOne(fetch = FetchType.LAZY)
    private TransactionEntity transaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
