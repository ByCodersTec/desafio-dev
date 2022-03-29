package com.alexandrecampos.devchallenge.model;

import lombok.Data;

import javax.persistence.Id;

@Data
public class OperationType {
    @Id
    private Integer id;

    private String description;

    private String type;

    private String signal;
}
