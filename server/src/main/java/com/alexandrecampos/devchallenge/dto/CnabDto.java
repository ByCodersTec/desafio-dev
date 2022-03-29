package com.alexandrecampos.devchallenge.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CnabDto {
    private Integer id;
    private Integer operationTypeId;
    private LocalDateTime date;
    private String documentId;
    private Integer value;
    private String cardNumber;
    private String storeOwner;
    private String storeName;
}
