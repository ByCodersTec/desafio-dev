package br.com.bycoders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bycoders.entity.TipoTransacao;

public interface TipoTransacoesRepository extends JpaRepository<TipoTransacao, Integer> {

}
