package br.com.bycoders.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Loja {
	@Id
	private String nomeLoja;
	private String donoLoja;

}
