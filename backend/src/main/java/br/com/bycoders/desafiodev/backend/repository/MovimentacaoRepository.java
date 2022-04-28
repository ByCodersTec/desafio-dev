package br.com.bycoders.desafiodev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bycoders.desafiodev.backend.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
    
    
}
