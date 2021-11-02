package com.github.filipednb.financialtransactions.api.transactiontype;

public enum TransactionTypeEnum {
    IN("Entrada"),
    OUT("Saída");

    final private String description;

    TransactionTypeEnum(final String description) {
        this.description = description;
    }
}
