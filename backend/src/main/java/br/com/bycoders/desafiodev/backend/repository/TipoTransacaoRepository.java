package br.com.bycoders.desafiodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bycoders.desafiodev.backend.model.TipoTransacao;

public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, Long>{
    
}
