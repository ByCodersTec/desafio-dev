package com.desafiodev.application.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.application.domains.ids.StoreId;
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
    StoreId storeId = Fixture.getStoreId();
    Transaction transaction =
        Transaction.newInstance(transactionType, instant, 10, cpf, creditCard, storeId);
    assertClass(Transaction.class, transaction);
    assertNotNull(transaction.getTransactionId());
    assertEquals(cpf, transaction.getCpf());
    assertEquals(transactionType, transaction.getType());
    assertEquals(creditCard, transaction.getCreditCard());
    assertEquals(instant, transaction.getDate());
    assertEquals(10, transaction.getValue());
    assertEquals(storeId, transaction.getStoreId());
  }

  @Test
  void builderWithError() {
    Cpf cpf = Fixture.getCpf();
    TransactionType transactionType = Fixture.getTransactionType();
    CreditCard creditCard = Fixture.getCreditCard();
    Instant instant = Instant.now();
    StoreId storeId = Fixture.getStoreId();
    assertThrows(
        IllegalStateException.class,
        () -> Transaction.newInstance(transactionType, instant, -10, cpf, creditCard, storeId));
  }

  @Test
  void parse() {
    Cpf cpf = Cpf.newInstance("09620676017");
    TransactionType transactionType = TransactionType.FINANCIAMENTO;
    CreditCard creditCard = CreditCard.newInstance("4753****3153");
    Instant instant =
        Instant.parse("2019-03-01T15:34:53.00Z").atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
    StoreId storeId = Fixture.getStoreId();
    Transaction transaction = Transaction.parse(Fixture.getCnab(), storeId);
    assertNotNull(transaction.getTransactionId());
    assertEquals(cpf, transaction.getCpf());
    assertEquals(transactionType, transaction.getType());
    assertEquals(creditCard, transaction.getCreditCard());
    assertEquals(instant, transaction.getDate());
    assertEquals(142.00, transaction.getValue());
    assertEquals(storeId, transaction.getStoreId());
  }
}
