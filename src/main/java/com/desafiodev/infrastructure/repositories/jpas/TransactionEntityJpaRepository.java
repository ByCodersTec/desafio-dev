package com.desafiodev.infrastructure.repositories.jpas;

import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEntityJpaRepository extends JpaRepository<TransactionEntity, String> {}
