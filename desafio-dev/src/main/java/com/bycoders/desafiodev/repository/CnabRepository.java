package com.bycoders.desafiodev.repository;

import com.bycoders.desafiodev.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnabRepository extends JpaRepository<Transactions, Long> {
}
