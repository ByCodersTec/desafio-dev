package com.bycoders.cnab.application.controllers.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CnabResumoDTO {
    private String sinal; 
    private BigDecimal valor;
    private String representanteLoja;
    private String nomeLoja;
}
