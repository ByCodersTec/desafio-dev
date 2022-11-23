package com.bycoders.cnab.dto;

import com.bycoders.cnab.entity.Cnab;

import java.math.BigDecimal;
import java.time.LocalTime;

public class FaturamentoDTO {
    private BigDecimal valor;
    private String cpf;
    private String donoLoja;
    private String nomeLoja;

    public FaturamentoDTO(BigDecimal valor, String cpf, String donoLoja, String nomeLoja) {
        this.valor = valor;
        this.cpf = cpf;
        this.donoLoja = donoLoja;
        this.nomeLoja = nomeLoja;
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
