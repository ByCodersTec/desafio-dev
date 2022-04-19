package com.bycoders.cnab.application.controllers.dto;

import java.util.List;

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
public class CnabConsultaDTO {
    private String nomeLoja; 
    private List<CnabDTO> cnabsDetalhamento;
}
