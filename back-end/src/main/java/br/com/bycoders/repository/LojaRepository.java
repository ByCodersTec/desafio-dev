package br.com.bycoders.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bycoders.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, String> {
	@Query("Select sum(t.valor) "
			+ "from Loja l, Transacoes t, TipoTransacao tp "
			+ "where l.nomeLoja=t.loja.nomeLoja "
			+ "and t.tipo.tipo=tp.tipo "
			+ "and l.nomeLoja=:loja "
			+ "and tp.operacao=\'SOMAR\'")
	BigDecimal getTotalCredito(String loja);
	
	@Query("Select sum(t.valor) "
			+ "from Loja l, Transacoes t, TipoTransacao tp "
			+ "where l.nomeLoja=t.loja.nomeLoja "
			+ "and t.tipo.tipo=tp.tipo "
			+ "and l.nomeLoja=:loja "
			+ "and tp.operacao=\'SUBTRAIR\'")
	BigDecimal getTotalDebito(String loja);

}
