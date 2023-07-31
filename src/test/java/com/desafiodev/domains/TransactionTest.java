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
        Transaction.getInstance(transactionType, instant, 10, cpf, creditCard, "Willian", "Store");
    assertClass(Transaction.class, transaction);
    assertEquals(cpf, transaction.getCpf());
    assertEquals(transactionType, transaction.getType());
    assertEquals(creditCard, transaction.getCreditCard());
    assertEquals(instant, transaction.getDate());
    assertEquals("Store", transaction.getStore());
    assertEquals("Willian", transaction.getStoreOwner());
    assertEquals(10, transaction.getValue());
    assertEquals(-10, transaction.getTotal());
  }

  @Test
  void builderWithError() {
    Cpf cpf = Fixture.getCpf();
    TransactionType transactionType = Fixture.getTransactionType();
    CreditCard creditCard = Fixture.getCreditCard();
    Instant instant = Instant.now();
    assertThrows(
        IllegalStateException.class,
        () ->
            Transaction.getInstance(
                transactionType, instant, -10, cpf, creditCard, "Willian", "Store"));
    assertThrows(
        IllegalStateException.class,
        () -> Transaction.getInstance(transactionType, instant, 10, cpf, creditCard, "", "Store"));
    assertThrows(
        IllegalStateException.class,
        () ->
            Transaction.getInstance(transactionType, instant, 10, cpf, creditCard, "Willian", ""));
  }
}
