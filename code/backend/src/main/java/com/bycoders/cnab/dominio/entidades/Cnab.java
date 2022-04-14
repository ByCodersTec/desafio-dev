package com.bycoders.cnab.dominio.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public class Cnab extends PanacheEntity{
    private Long tipo;
    private LocalDateTime dataHoraTransacao;
    private BigDecimal valor;
    private String cpf;
    private String numeroCartao;
    private String representanteLoja;
    private String nomeLoja;

    Cnab(){}
}
