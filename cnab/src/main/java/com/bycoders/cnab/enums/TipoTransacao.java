package com.bycoders.cnab.enums;

public enum TipoTransacao {

    DEBITO(1, "Entrada"),
    BOLETO(2, "Saída"),
    FINANCIAMENTO(3, "Entrada"),
    CREDITO(4, "Entrada"),
    RECEBIMENTO_EMPRESTIMO(5, "Entrada"),
    VENDAS(6, "Entrada"),
    TED(7, "Entrada"),
    DOC(8, "Entrada"),
    ALUGUEL(9, "Saída");

    private final Integer tipo;
    private final String natureza;

    TipoTransacao(Integer tipo, String natureza) {
        this.tipo = tipo;
        this.natureza = natureza;
    }

}
