package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

class CreditCardTest extends UtilsTest {

  @Test
  void getInstance() {
    CreditCard creditCard = CreditCard.getInstance("222222222222");
    assertClass(CreditCard.class, creditCard);
    assertEquals("222222222222", creditCard.getNumber());
  }

  @ParameterizedTest
  @EmptySource
  @CsvSource("2222222222")
  void getInstanceWithError(String number) {
    assertThrows(IllegalStateException.class, () -> CreditCard.getInstance(number));
  }
}
