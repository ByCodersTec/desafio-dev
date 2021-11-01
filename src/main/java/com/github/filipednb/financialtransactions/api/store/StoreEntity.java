package com.github.filipednb.financialtransactions.api.store;

import javax.persistence.*;

@Entity
@Table(name = "STORES")
public class StoreEntity {

    public StoreEntity() {
    }

    @Id
    @Column(name = "ID_STORE")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAM_STORE")
    private String name;

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
