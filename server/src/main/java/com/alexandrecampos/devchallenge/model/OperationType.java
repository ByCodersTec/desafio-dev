package com.alexandrecampos.devchallenge.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "operation_type", schema = "core")
public class OperationType {
    @Id
    private Integer id;

    private String description;

    private String type;
}
