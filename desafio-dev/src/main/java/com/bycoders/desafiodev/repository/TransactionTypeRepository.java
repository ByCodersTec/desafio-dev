package com.bycoders.desafiodev.repository;

import com.bycoders.desafiodev.domain.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

}
