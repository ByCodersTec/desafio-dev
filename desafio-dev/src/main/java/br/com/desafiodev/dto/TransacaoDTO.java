package br.com.desafiodev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {

    private Long idTransaco;
    private TipoTransacaoDTO idTipo;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    private String cpf;
    private String cartao;
    private LocalTime hora;
    private String nomeDono;
    private String nomeLoja;

}
