package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class TransactionTest extends UtilsTest {

  @Test
  void builder() {
    Cpf cpf = Fixture.getCpf();
    TransactionType transactionType = Fixture.getTransactionType();
    CreditCard creditCard = Fixture.getCreditCard();
    Instant instant = Instant.now();
    Transaction transaction =
        Transaction.builder()
            .cpf(cpf)
            .value(10)
            .type(transactionType)
            .date(instant)
            .creditCard(creditCard)
            .store("Store")
            .storeOwner("Willian")
            .build();
    assertClass(Transaction.class, transaction);
    assertEquals(cpf, transaction.getCpf());
    assertEquals(transactionType, transaction.getType());
    assertEquals(creditCard, transaction.getCreditCard());
    assertEquals(instant, transaction.getDate());
    assertEquals("Store", transaction.getStore());
    assertEquals("Willian", transaction.getStoreOwner());
    assertEquals(-10, transaction.getValue());
  }

  @Test
  void builderWithError() {
    assertThrows(
        IllegalStateException.class,
        () ->
            Transaction.builder()
                .cpf(Fixture.getCpf())
                .value(10)
                .type(Fixture.getTransactionType())
                .date(Instant.now())
                .creditCard(Fixture.getCreditCard())
                .store("")
                .storeOwner("Name")
                .build());
    assertThrows(
        IllegalStateException.class,
        () ->
            Transaction.builder()
                .cpf(Fixture.getCpf())
                .value(10)
                .type(Fixture.getTransactionType())
                .date(Instant.now())
                .creditCard(Fixture.getCreditCard())
                .store("Name")
                .storeOwner("")
                .build());
  }
}
