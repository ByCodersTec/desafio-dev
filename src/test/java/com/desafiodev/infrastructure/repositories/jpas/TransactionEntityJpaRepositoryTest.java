package com.desafiodev.infrastructure.repositories.jpas;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionEntityJpaRepositoryTest {
  @Autowired private TransactionEntityJpaRepository transactionEntityJpaRepository;

  @Test
  void save() {
    TransactionEntity transactionEntity = TransactionEntity.from(Fixture.getTransaction());
    TransactionEntity result = transactionEntityJpaRepository.save(transactionEntity);
    assertEquals(transactionEntity, result);
  }
}
