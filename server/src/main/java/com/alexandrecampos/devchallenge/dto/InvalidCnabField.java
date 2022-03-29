package com.alexandrecampos.devchallenge.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvalidCnabField {
    private Integer line;
    private String field;
    private String value;
}
