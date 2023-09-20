package br.com.bycoders.cnab.infra.repository;

import br.com.bycoders.cnab.infra.entity.CnabEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnabRepository extends JpaRepository <CnabEntity,Long> {
}
