package br.com.bycoders.cnab.core.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cnab {
    private Long id;
    private Character type;
    private String date;
    private BigDecimal value;
    private String cpf;
    private String card;
    private String hour;
    private String owner;
    private String name;

}
