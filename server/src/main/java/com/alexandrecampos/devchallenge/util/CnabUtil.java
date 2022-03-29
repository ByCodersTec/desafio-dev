package com.alexandrecampos.devchallenge.util;

import com.alexandrecampos.devchallenge.dto.CnabRawDto;
import com.alexandrecampos.devchallenge.dto.InvalidCnabField;
import com.alexandrecampos.devchallenge.model.Cnab;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public final class CnabUtil {
    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_PARSER = DateTimeFormatter.ofPattern("Hmmss");


    private CnabUtil() {
    }

    public static InvalidCnabField invalidField(String line) {
        return invalidField(line, null);
    }

    public static InvalidCnabField invalidField(String line, Integer lineNumber) {
        CnabRawDto cnabRawDto = new CnabRawDto(line);

        // Single digite
        if (!cnabRawDto.getOperationTypeId().matches("\\d")) {
            return InvalidCnabField.builder().field("operação").line(lineNumber).value(cnabRawDto.getOperationTypeId()).build();
        }

        // Date
        if (!isValidDate(cnabRawDto.getDate())) {
            return InvalidCnabField.builder().field("data").line(lineNumber).value(cnabRawDto.getDate()).build();
        }

        // Only digits
        if (!isOnlyDigits(cnabRawDto.getValue())) {
            return InvalidCnabField.builder().field("valor").line(lineNumber).value(cnabRawDto.getValue()).build();
        }

        // Only digits
        if (!isOnlyDigits(cnabRawDto.getDocumentId())) {
            return InvalidCnabField.builder().field("CPF").line(lineNumber).value(cnabRawDto.getDocumentId()).build();
        }

        if (!isMaskedDigits(cnabRawDto.getCardNumber())) {
            return InvalidCnabField.builder().field("cartão").line(lineNumber).value(cnabRawDto.getCardNumber()).build();
        }

        if (!isValidTime(cnabRawDto.getTime())) {
            return InvalidCnabField.builder().field("hora").line(lineNumber).value(cnabRawDto.getTime()).build();
        }

        if (!notEmpty(cnabRawDto.getStoreOwner())) {
            return InvalidCnabField.builder().field("dono").line(lineNumber).value(cnabRawDto.getStoreOwner()).build();
        }

        if (!notEmpty(cnabRawDto.getStoreName())) {
            return InvalidCnabField.builder().field("nome").line(lineNumber).value(cnabRawDto.getStoreName()).build();
        }

        return null;

    }

    private static boolean notEmpty(String s) {
        return s.length() > 0;
    }

    public static String getFieldByStartAndSize(String line, int start, int size) {
        if (start > line.length()) {
            return "";
        }
        return line.substring(start - 1, Math.min(start - 1 + size, line.length()));
    }

    private static boolean isValidDate(String date) {
        try {
            DATE_PARSER.parse(date);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    private static boolean isValidTime(String date) {
        try {
            TIME_PARSER.parse(date);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    private static boolean isOnlyDigits(String s) {
        return s.matches("\\d+");
    }

    private static boolean isMaskedDigits(String s) {
        return s.matches("[\\d*]+");
    }

    public static Cnab stringToCnab(String line) {
        CnabRawDto raw = new CnabRawDto(line);

        Cnab cnab = new Cnab();
        cnab.setOperationTypeId(Integer.parseInt(raw.getOperationTypeId()));
        cnab.setDate(LocalDateTime.of(LocalDate.parse(raw.getDate(), DATE_PARSER), LocalTime.parse(raw.getTime(), TIME_PARSER)));
        cnab.setValue(raw.getValue());
        cnab.setDocumentId(raw.getDocumentId());
        cnab.setCardNumber(raw.getCardNumber());
        cnab.setStoreOwner(raw.getStoreOwner());
        cnab.setStoreName(raw.getStoreName());
        return cnab;
    }
}
