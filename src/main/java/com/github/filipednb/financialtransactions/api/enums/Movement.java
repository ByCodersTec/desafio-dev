package com.github.filipednb.financialtransactions.api.enums;

public enum Movement {
    IN("Entrada", '+'),
    OUT("Saída", '-');

    Movement(final String description, final char signal) {

    }
}
