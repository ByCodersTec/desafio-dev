package com.alexandrecampos.devchallenge.repository;

import com.alexandrecampos.devchallenge.model.Cnab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnabRepository extends JpaRepository<Cnab, Integer> {
}
