package br.com.bycoders.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TipoTransacao {
	@Id
	private Integer tipo;
	private String descricao;
	private String natureza;
	@Enumerated(EnumType.STRING)
	private Operacao operacao;
	
}
