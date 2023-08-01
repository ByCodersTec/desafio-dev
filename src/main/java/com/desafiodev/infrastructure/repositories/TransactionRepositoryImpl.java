package com.desafiodev.infrastructure.repositories;

import com.desafiodev.application.domains.Store;
import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.ports.out.TransactionRepository;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

  private final TransactionEntityJpaRepository transactionEntityJpaRepository;

  @Autowired
  public TransactionRepositoryImpl(TransactionEntityJpaRepository transactionEntityJpaRepository) {
    this.transactionEntityJpaRepository = transactionEntityJpaRepository;
  }

  @Override
  public void save(@NonNull Transaction transaction, @NonNull Store store) {
    transactionEntityJpaRepository.save(TransactionEntity.from(transaction, store));
  }
}
