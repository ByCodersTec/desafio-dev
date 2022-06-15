package com.desafio.backend.repository;

import com.desafio.backend.model.CNAB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CNABRepository extends JpaRepository<CNAB, Long>{

}
