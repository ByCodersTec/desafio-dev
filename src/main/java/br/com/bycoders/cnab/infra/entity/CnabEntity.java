package br.com.bycoders.cnab.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cnab")
public class CnabEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Character type;
    @Column
    private String date;
    @Column
    private BigDecimal value;
    @Column
    private String cpf;
    @Column
    private String card;
    @Column
    private String hour;
    @Column
    private String owner;
    @Column
    private String name;

}
