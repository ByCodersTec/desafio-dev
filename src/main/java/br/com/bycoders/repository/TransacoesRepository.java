package br.com.bycoders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bycoders.entity.Loja;
import br.com.bycoders.entity.Transacoes;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long>{
	@Query("SELECT t FROM Transacoes t WHERE t.id IN :ids AND t.loja = :loja")
	List<Transacoes> findAllByIdAndLoja(@Param("ids") List<Long> ids, @Param("loja") Loja loja);

	@Query("SELECT distinct(t.loja) FROM Transacoes t WHERE t.id IN :ids")
	List<Loja> findAllByIdLoja(@Param("ids") List<Long> ids);	
	
	
}
