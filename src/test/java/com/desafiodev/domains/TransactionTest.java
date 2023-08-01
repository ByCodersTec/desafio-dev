package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import java.time.Instant;
import java.time.ZoneId;
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
    assertNotNull(transaction.getId());
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

  @Test
  void parse() {
    Cpf cpf = Cpf.getInstance("09620676017");
    TransactionType transactionType = TransactionType.FINANCIAMENTO;
    CreditCard creditCard = CreditCard.getInstance("4753****3153");
    Instant instant =
        Instant.parse("2019-03-01T15:34:53.00Z").atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    Transaction transaction = Transaction.parse(Fixture.getCnab());
    assertNotNull(transaction.getId());
    assertEquals(cpf, transaction.getCpf());
    assertEquals(transactionType, transaction.getType());
    assertEquals(creditCard, transaction.getCreditCard());
    assertEquals(instant, transaction.getDate());
    assertEquals("BAR DO JOÃO", transaction.getStore());
    assertEquals("JOÃO MACEDO", transaction.getStoreOwner());
    assertEquals(142.00, transaction.getValue());
    assertEquals(-142.00, transaction.getTotal());
  }
}
