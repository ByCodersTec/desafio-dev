package com.alexandrecampos.devchallenge.dto;

import com.alexandrecampos.devchallenge.util.CnabUtil;
import lombok.Data;

@Data
public class CnabRawDto {
    private String operationTypeId;
    private String date;
    private String time;
    private String documentId;
    private String value;
    private String cardNumber;
    private String storeOwner;
    private String storeName;

    public CnabRawDto(String line) {
        this.operationTypeId = CnabUtil.getFieldByStartAndSize(line, 1, 1);
        this.date = CnabUtil.getFieldByStartAndSize(line, 2, 8);
        this.value = CnabUtil.getFieldByStartAndSize(line, 10, 10);
        this.documentId = CnabUtil.getFieldByStartAndSize(line, 20, 11);
        this.cardNumber = CnabUtil.getFieldByStartAndSize(line, 31, 12);
        this.time = CnabUtil.getFieldByStartAndSize(line, 43, 6);
        this.storeOwner = CnabUtil.getFieldByStartAndSize(line, 49, 14);
        this.storeName = CnabUtil.getFieldByStartAndSize(line, 63, 19);
    }
}
