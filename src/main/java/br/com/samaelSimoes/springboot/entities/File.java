package br.com.samaelSimoes.springboot.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "File")
public class File {

    public File() {
        super();
    }

    public File(final Integer type, final LocalDateTime dtTransaction, final Float value, final String cpf,
                final String card, final String nmOwner, final String nmStore) {
        super();
        this.type = type;
        this.dtTransaction = dtTransaction;
        this.value = value;
        this.cpf = cpf;
        this.card = card;
        this.nmOwner = nmOwner;
        this.nmStore = nmStore;
    }

    public File(Integer id, Integer type, LocalDateTime dtTransaction, Float value, String cpf, String card, String nmOwner, String nmStore) {
        super();
        this.id = id;
        this.type = type;
        this.dtTransaction = dtTransaction;
        this.value = value;
        this.cpf = cpf;
        this.card = card;
        this.nmOwner = nmOwner;
        this.nmStore = nmStore;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1)
    private Integer type;

    private LocalDateTime dtTransaction;

    private Float value;

    @Column(length = 11)
    private String cpf;

    @Column(length = 12)
    private String card;

    @Column(length = 14)
    private String nmOwner;

    @Column(length = 19)
    private String nmStore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getDtTransaction() {
        return dtTransaction;
    }

    public void setDtTransaction(LocalDateTime dtTransaction) {
        this.dtTransaction = dtTransaction;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getNmOwner() {
        return nmOwner;
    }

    public void setNmOwner(String nmOwner) {
        this.nmOwner = nmOwner;
    }

    public String getNmStore() {
        return nmStore;
    }

    public void setNmStore(String nmStore) {
        this.nmStore = nmStore;
    }
}
