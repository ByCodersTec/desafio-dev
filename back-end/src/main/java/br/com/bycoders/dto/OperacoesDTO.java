package br.com.bycoders.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperacoesDTO {
	private String nomeLoja;
	private String donoLoja;
	private BigDecimal saldoConta;	
	private List<TransacoesDTO> transacoes;	
}
