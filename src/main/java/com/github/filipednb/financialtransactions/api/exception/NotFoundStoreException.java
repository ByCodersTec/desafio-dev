package com.github.filipednb.financialtransactions.api.exception;

public class NotFoundStoreException extends RuntimeException {
    public NotFoundStoreException(String msg) {
        super(msg);
    }
}
