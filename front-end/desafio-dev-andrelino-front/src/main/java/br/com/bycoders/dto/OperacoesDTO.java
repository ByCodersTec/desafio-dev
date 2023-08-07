package br.com.bycoders.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OperacoesDTO {
	private String nomeLoja;
	private String donoLoja;
	private BigDecimal saldoConta;	
	private List<TransacoesDTO> transacoes;	
}
