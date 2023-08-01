package com.desafiodev.infrastructure.repositories.jpas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.desafiodev.application.domains.Store;
import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionEntityJpaRepositoryTest {
  @Autowired private TransactionEntityJpaRepository transactionEntityJpaRepository;
  @Autowired private StoreEntityJpaRepository storeEntityJpaRepository;

  @Test
  void save() {
    storeEntityJpaRepository.deleteAll();
    Store store = Fixture.getStore();
    TransactionEntity transactionEntity = TransactionEntity.from(Fixture.getTransaction(), store);
    storeEntityJpaRepository.save(StoreEntity.from(store));
    TransactionEntity result = transactionEntityJpaRepository.save(transactionEntity);
    assertEquals(transactionEntity, result);
  }
}
