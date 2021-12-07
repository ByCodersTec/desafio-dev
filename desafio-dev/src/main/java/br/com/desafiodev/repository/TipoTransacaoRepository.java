package br.com.desafiodev.repository;

import br.com.desafiodev.model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, Long> {
}
