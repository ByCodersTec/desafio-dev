package com.desafiodev.application.domains.ids;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.UtilsTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class TransactionIdTest extends UtilsTest {

  @Test
  void transactionId() {
    assertClass(TransactionId.class, TransactionId.newInstance());
    var id = TransactionId.newInstance();
    var uuid = UUID.randomUUID().toString();
    assertNotEquals(id, TransactionId.newInstance());
    assertEquals(id, TransactionId.getInstance(id.getId()));
    assertEquals(uuid, TransactionId.getInstance(uuid).getId());
  }
}
