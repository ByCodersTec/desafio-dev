package com.bycoders.cnab.dto;

import com.bycoders.cnab.entity.Cnab;
import com.bycoders.cnab.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CnabDTO {
    private Long id;
    private TipoTransacao tipo;
    private LocalDate data;
    private BigDecimal valor;
    private String cpf;
    private String cartao;
    private LocalTime hora;
    private String donoLoja;
    private String nomeLoja;

    public CnabDTO(Cnab cnab) {
        this.id = cnab.getId();
        this.tipo = cnab.getTipo();
        this.data = cnab.getData();
        this.valor = cnab.getValor();
        this.cpf = cnab.getCpf();
        this.cartao = cnab.getCartao();
        this.hora = cnab.getHora();
        this.donoLoja = cnab.getDonoLoja();
        this.nomeLoja = cnab.getNomeLoja();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
