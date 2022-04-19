package com.bycoders.cnab.application.controllers.dto;

import com.bycoders.cnab.dominio.entidades.TipoTransacao;

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
public class TipoTransacaoDTO {
    private Integer tipo;
    private String descricao;
    private String natureza;
    private String sinal;    

    public static TipoTransacaoDTO buildDTO(TipoTransacao tipoTransacao){
        return TipoTransacaoDTO.builder()
                .descricao(tipoTransacao.getDescricao())
                .tipo(tipoTransacao.getTipo())
                .sinal(tipoTransacao.getSinal())
                .natureza(tipoTransacao.getNatureza())
                .build();
    }
}
