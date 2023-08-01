package com.desafiodev.infrastructure.repositories.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.application.domains.Store;
import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;

class StoreEntityTest extends UtilsTest {

  @Test
  void from() {
    Store store = Fixture.getStore();
    StoreEntity entity = StoreEntity.from(store);
    assertClass(StoreEntity.class, entity);
    assertEquals(store.getStoreId().getId(), entity.getId());
    assertEquals(store.getName(), entity.getName());
    assertEquals(store.getOwnerName(), entity.getOwnerName());
  }
}
