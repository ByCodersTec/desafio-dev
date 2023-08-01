package com.desafiodev.application.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.UtilsTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class StoreIdTest extends UtilsTest {

  @Test
  void storeId() {
    assertClass(StoreId.class, StoreId.newInstance());
    var id = StoreId.newInstance();
    var uuid = UUID.randomUUID().toString();
    assertNotEquals(id, StoreId.newInstance());
    assertEquals(id, StoreId.getInstance(id.getId()));
    assertEquals(uuid, StoreId.getInstance(uuid).getId());
  }
}
