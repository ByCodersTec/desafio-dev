package com.alexandrecampos.devchallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CnabDto {
    private Integer id;
    private OperationTypeDto operationType;
    private LocalDateTime date;
    private String documentId;
    private BigDecimal value;
    private String cardNumber;
    private String storeOwner;
    private String storeName;
}
