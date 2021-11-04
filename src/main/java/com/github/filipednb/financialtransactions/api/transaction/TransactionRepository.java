package com.github.filipednb.financialtransactions.api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByStoreName(String storeName);
}
