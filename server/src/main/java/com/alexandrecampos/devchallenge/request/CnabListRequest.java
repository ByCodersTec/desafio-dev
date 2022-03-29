package com.alexandrecampos.devchallenge.request;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants(asEnum = true)
public class CnabListRequest {
    private String documentId;
    private String cardNumber;
    private int page = 0;
    private int size = 20;
}
