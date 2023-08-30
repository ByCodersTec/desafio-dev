package com.bycoders.desafiodev.repository;

import com.bycoders.desafiodev.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findBystoreNameContains(String storeName);
}
