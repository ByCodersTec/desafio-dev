package com.desafiodev.infrastructure.repositories.jpas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.desafiodev.application.domains.Store;
import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TransactionEntityJpaRepositoryTest {
  @Autowired private TransactionEntityJpaRepository transactionEntityJpaRepository;
  @Autowired private StoreEntityJpaRepository storeEntityJpaRepository;

  @Test
  void save() {
    Store store = Fixture.getStore();
    TransactionEntity transactionEntity = TransactionEntity.from(Fixture.getTransaction(), store);
    storeEntityJpaRepository.save(StoreEntity.from(store));
    TransactionEntity result = transactionEntityJpaRepository.save(transactionEntity);
    assertEquals(transactionEntity, result);
  }
}
