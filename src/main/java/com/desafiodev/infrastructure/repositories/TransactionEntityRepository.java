package com.desafiodev.infrastructure.repositories;

import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.ports.out.TransactionRepository;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionEntityRepository implements TransactionRepository {

  private final TransactionEntityJpaRepository transactionEntityJpaRepository;

  @Autowired
  public TransactionEntityRepository(
      TransactionEntityJpaRepository transactionEntityJpaRepository) {
    this.transactionEntityJpaRepository = transactionEntityJpaRepository;
  }

  @Override
  public void save(Transaction transaction) {
    transactionEntityJpaRepository.save(TransactionEntity.from(transaction));
  }
}
