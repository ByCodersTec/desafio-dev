package com.bycoders.cnab.repository;

import com.bycoders.cnab.entity.Cnab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnabRepository extends JpaRepository<Cnab, Long> {

}