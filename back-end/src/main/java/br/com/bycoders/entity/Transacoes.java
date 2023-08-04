package br.com.bycoders.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transacoes {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@ManyToOne
		private TipoTransacao tipo;
		private LocalDate data;
		private BigDecimal valor;
		private String cpf;
		private String cartao;
		private LocalTime hora;
		@ManyToOne
		private Loja loja;
}
