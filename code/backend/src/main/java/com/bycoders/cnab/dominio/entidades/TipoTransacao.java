package com.bycoders.cnab.dominio.entidades;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class TipoTransacao extends PanacheEntity{
// public class TipoTransacao {
    private Long tipo;
    private String descricao;
    private String natureza;
    private String sinal;    

    TipoTransacao(){}
}
