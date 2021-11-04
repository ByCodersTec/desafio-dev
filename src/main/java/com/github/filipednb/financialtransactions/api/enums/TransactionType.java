package com.github.filipednb.financialtransactions.api.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum TransactionType {
    DEBITO (1,"Débito", Movement.IN),
    BOLETO(2,"Boleto", Movement.OUT),
    FINANCIAMENTO (3,"Financiamento", Movement.OUT),
    CREDITO (4,"Crédito", Movement.IN),
    RECEBIMENTO (5,"Recebimento Empréstimo", Movement.IN),
    VENDAS (6,"Vendas",  Movement.IN),
    RECEBIMENTO_TED (7,"Recebimento TED", Movement.IN),
    RECEBIMENTO_DOC(8,"Recebimento DOC", Movement.IN),
    ALUGUEL (9,"Aluguel", Movement.OUT);

    private final Integer code;
    private final String description;
    private final Movement movement;

    TransactionType(final Integer code, final String description, final Movement movement) {
        this.code = code;
        this.description = description;
        this.movement = movement;
    }

    public static TransactionType getByCode(Integer code) {
        return Arrays.stream(TransactionType.values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported transaction type: %s.", code)));
    }

    public Movement getMovement() {
        return this.movement;
    }

}
