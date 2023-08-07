package br.com.bycoders.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransacoesDTO {
	private Long id;
	private LocalDate data;
	private LocalTime hora;
	private String tipo;
	private String cpf;
	private String cartao;
	private BigDecimal valor;
}
