package com.desafiodev.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.domains.Transaction;
import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;

class TransactionEntityTest extends UtilsTest {

  @Test
  void from() {
    Transaction transaction = Fixture.getTransaction();
    TransactionEntity entity = TransactionEntity.from(transaction);
    assertClass(TransactionEntity.class, entity);
    assertEquals(transaction.getCpf().getNumber(), entity.getCpf());
    assertEquals(transaction.getStore(), entity.getStore());
    assertEquals(transaction.getId().toString(), entity.getId());
    assertEquals(transaction.getDate(), entity.getDate());
    assertEquals(transaction.getType(), entity.getType());
    assertEquals(transaction.getTotal(), entity.getTotal());
    assertEquals(transaction.getCreditCard().getNumber(), entity.getCreditCard());
    assertEquals(transaction.getStoreOwner(), entity.getStoreOwner());
    assertEquals(transaction.getValue(), entity.getValue());
  }
}
