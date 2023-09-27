package com.br.cnab.upload.apiuploadfile.enums;

public enum TransactionTypeEnum {
    DEBIT("1", 1.0),
    BOLETO_PAYMENT("2", -1.0),
    FINANCING("3", -1.0),
    CREDIT("4", 1.0),
    LOAN_RECEIPT("5", 1.0),
    SALES("6", 1.0),
    RECEIPT_TED("7", 1.0),
    RECEIPT_DOC("8", 1.0),
    ALUGUEL("9", -1.0);

    private final String code;
    private final double signal;

    TransactionTypeEnum(String code, double signal) {
        this.code = code;
        this.signal = signal;
    }

    public String getCode() {
        return code;
    }

    public double getSignal() {
        return signal;
    }

    public static TransactionTypeEnum fromCode(String code) {
        for (TransactionTypeEnum type : TransactionTypeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

