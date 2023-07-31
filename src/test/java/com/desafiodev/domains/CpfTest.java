package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

class CpfTest extends UtilsTest {

  @Test
  void getInstance() {
    Cpf cpf = Cpf.getInstance("22222222222");
    assertClass(Cpf.class, cpf);
    assertEquals("22222222222", cpf.getNumber());
  }

  @ParameterizedTest
  @EmptySource
  @CsvSource("2222222222")
  void getInstanceWithError(String number) {
    assertThrows(IllegalStateException.class, () -> Cpf.getInstance(number));
  }
}
