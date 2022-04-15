package com.bycoders.cnab.application.controllers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class CnabDTO {
    private TipoTransacaoDTO tipo;
    private LocalDateTime dataHoraTransacao;
    private BigDecimal valor;
    private String cpf;
    private String numeroCartao;
    private String representanteLoja;
    private String nomeLoja;
}
