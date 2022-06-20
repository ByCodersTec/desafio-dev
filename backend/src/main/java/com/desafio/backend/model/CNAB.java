package com.desafio.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "cnab")
public class CNAB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "data")
    private Date data;

    @Column(name = "valor")
    private Integer valor;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cartao")
    private String cartao;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "dono_loja")
    private String donoLoja;

    @Column(name = "nome_loja")
    private String nomeLoja;

    public CNAB() {

    }

    public CNAB(String tipo, Date data, Integer valor, String cpf, String cartao, LocalTime hora, String donoLoja, String nomeLoja) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.cpf = cpf;
        this.cartao = cartao;
        this.hora = hora;
        this.donoLoja = donoLoja;
        this.nomeLoja = nomeLoja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDonoLoja() {
        return donoLoja;
    }

    public void setDonoLoja(String donoLoja) {
        this.donoLoja = donoLoja;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }
}
