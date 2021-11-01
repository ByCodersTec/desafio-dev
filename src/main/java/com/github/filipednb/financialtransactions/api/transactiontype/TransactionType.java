package com.github.filipednb.financialtransactions.api.transactiontype;

public enum TransactionType {
    CRE("Crédito"),
    DEB("Débito");

    private String description;

    TransactionType(final String description) {
        this.description = description;
    }
}
