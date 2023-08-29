package com.bycoders.desafiodev.utils;

import com.bycoders.desafiodev.domain.Transactions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CnabUtils {

    public static final int NORMALIZE_VALUE = 100;

    public static Transactions fillCnab(String cnab) {

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");

        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HHmmss");

        Transactions transaction = new Transactions();
        transaction.setType(cnab.substring(0, 1));
        String dateStr = cnab.substring(1, 9);
        transaction.setInsertDate(LocalDate.parse(dateStr, formatterDate));
        double value = Double.parseDouble(cnab.substring(9, 19));
        transaction.setMovimentValue( value / NORMALIZE_VALUE);
        transaction.setCpf(cnab.substring(19, 30));
        transaction.setCard(cnab.substring(30, 42));
        String timeStr = cnab.substring(42, 48);
        transaction.setInsertHour(LocalTime.parse(timeStr,formatterHour));
        transaction.setStoreName(cnab.substring(48, 62));
        transaction.setStorePropertyName(cnab.substring(62, 80));

        return transaction;
    }
}
