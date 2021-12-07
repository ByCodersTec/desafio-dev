package br.com.desafiodev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTransacaoDTO {


    private Long idTipo;
    private int tipo;
    private String descricao;
    private String natureza;
    private String sinal;
}
