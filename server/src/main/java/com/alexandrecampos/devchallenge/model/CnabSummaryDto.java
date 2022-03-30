package com.alexandrecampos.devchallenge.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CnabSummaryDto {
    private BigDecimal totalValue;
    private int totalOperations;
}
