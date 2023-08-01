package com.desafiodev.application.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;

class StoreTest extends UtilsTest {

  @Test
  void store() {
    Store store = Store.newInstance("Name", "OwnerName");
    assertClass(Store.class, store);
    assertNotNull(store.getStoreId());
    assertEquals("NAME", store.getName());
    assertEquals("OWNERNAME", store.getOwnerName());
  }

  @Test
  void storeWithError() {
    assertThrows(IllegalStateException.class, () -> Store.newInstance("", "OwnerName"));
    assertThrows(IllegalStateException.class, () -> Store.newInstance("Name", ""));
  }
}
