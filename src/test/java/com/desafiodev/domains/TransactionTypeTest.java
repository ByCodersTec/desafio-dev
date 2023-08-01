package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TransactionTypeTest {

  @ParameterizedTest
  @CsvSource({
    "0, false, DEBITO, 0",
    "1, true, DEBITO, 10",
    "2, true, BOLETO, -10",
    "3, true, FINANCIAMENTO, -10",
    "4, true, CREDITO, 10",
    "5, true, RECEBIMENTO_EMPRESTIMO, 10",
    "6, true, VENDAS, 10",
    "7, true, RECEBIMENTO_TED, 10",
    "8, true, RECEBIMENTO_DOC, 10",
    "9, true, ALUGUEL, -10"
  })
  void transactionTypeTest(
      String cnabPosition, boolean expected, TransactionType transactionType, int expectedValue) {
    Optional<TransactionType> optional = TransactionType.getTransactionType(cnabPosition);
    assertEquals(expected, optional.isPresent());
    if (!expected) return;
    TransactionType result = optional.orElseThrow();
    assertEquals(transactionType, result);
    assertEquals(expectedValue, result.getValue(10));
    assertThrows(IllegalStateException.class, () -> result.getValue(-10));
  }
}
