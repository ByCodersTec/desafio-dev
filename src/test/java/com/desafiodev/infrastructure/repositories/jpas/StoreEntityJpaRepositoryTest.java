package com.desafiodev.infrastructure.repositories.jpas;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StoreEntityJpaRepositoryTest {
  @Autowired private StoreEntityJpaRepository storeEntityJpaRepository;

  @Test
  void save() {
    StoreEntity storeEntity = StoreEntity.from(Fixture.getStore());
    StoreEntity result = storeEntityJpaRepository.save(storeEntity);
    assertEquals(storeEntity, result);
  }

  @Test
  void unique() {
    StoreEntity storeEntity = StoreEntity.from(Fixture.getStore());
    storeEntityJpaRepository.save(storeEntity);
    StoreEntity newStoreEntity = StoreEntity.from(Fixture.getStore());
    assertThrows(
        DataIntegrityViolationException.class, () -> storeEntityJpaRepository.save(newStoreEntity));
  }

  @Test
  void findByNameAndOwnerName() {
    StoreEntity storeEntity = StoreEntity.from(Fixture.getStore());
    StoreEntity store = storeEntityJpaRepository.save(storeEntity);
    assertEquals(
        store,
        storeEntityJpaRepository
            .findByNameIgnoreCaseAndOwnerNameIgnoreCase(store.getName(), store.getOwnerName())
            .orElseThrow());
    assertEquals(
        store,
        storeEntityJpaRepository
            .findByNameIgnoreCaseAndOwnerNameIgnoreCase(
                store.getName().toLowerCase(), store.getOwnerName().toLowerCase())
            .orElseThrow());
    assertTrue(
        storeEntityJpaRepository
            .findByNameIgnoreCaseAndOwnerNameIgnoreCase("Not exist", "Not exist")
            .isEmpty());
  }
}
