package com.bycoders.cnab.entity;

import com.bycoders.cnab.enums.TipoTransacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "CNAB")
public class Cnab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="TIPO_TRANSACAO")
    private TipoTransacao tipo;
    @Column(name="DATA_TRANSACAO")
    private LocalDate data;
    @Column(name="VALOR_TRANSACAO")
    private BigDecimal valor;
    @Column(name="CPF")
    private String cpf;
    @Column(name="CARTAO")
    private String cartao;
    @Column(name="HORA_TRANSACAO")
    private LocalTime hora;
    @Column(name="DONO")
    private String donoLoja;
    @Column(name="NOME")
    private String nomeLoja;

    public Cnab() {

    }

    public Cnab(TipoTransacao tipo, LocalDate data, BigDecimal valor, String cpf, String cartao, LocalTime hora, String donoLoja, String nomeLoja) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
        this.cpf = cpf;
        this.cartao = cartao;
        this.hora = hora;
        this.donoLoja = donoLoja;
        this.nomeLoja = nomeLoja;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}