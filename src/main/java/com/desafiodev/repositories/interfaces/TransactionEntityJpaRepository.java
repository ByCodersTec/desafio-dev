package com.desafiodev.repositories.interfaces;

import com.desafiodev.repositories.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEntityJpaRepository extends JpaRepository<TransactionEntity, String> {}
