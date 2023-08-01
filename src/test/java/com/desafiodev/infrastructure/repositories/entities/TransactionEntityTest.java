package com.desafiodev.infrastructure.repositories.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.desafiodev.application.domains.Store;
import com.desafiodev.application.domains.Transaction;
import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;

class TransactionEntityTest extends UtilsTest {

  @Test
  void from() {
    Transaction transaction = Fixture.getTransaction();
    Store store = Fixture.getStore();
    TransactionEntity entity = TransactionEntity.from(transaction, store);
    assertClass(TransactionEntity.class, entity);
    assertEquals(transaction.getCpf().getNumber(), entity.getCpf());
    assertEquals(transaction.getTransactionId().getId(), entity.getId());
    assertEquals(transaction.getDate(), entity.getDate());
    assertEquals(transaction.getType(), entity.getType());
    assertEquals(transaction.getCreditCard().getNumber(), entity.getCreditCard());
    assertEquals(transaction.getValue(), entity.getValue());
    assertEquals(store.getStoreId().getId(), entity.getStore().getId());
    assertEquals(store.getName(), entity.getStore().getName());
    assertEquals(store.getOwnerName(), entity.getStore().getOwnerName());
  }
}
