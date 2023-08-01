package com.desafiodev.infrastructure.repositories.jpas;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class StoreEntityJpaRepositoryTest {
  @Autowired private StoreEntityJpaRepository storeEntityJpaRepository;

  @BeforeEach
  void setUp() {
    storeEntityJpaRepository.deleteAll();
  }

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
}
