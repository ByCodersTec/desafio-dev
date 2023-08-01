package com.desafiodev.application.domains;

import static com.desafiodev.application.domains.TransactionType.FINANCIAMENTO;
import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

class CnabTest extends UtilsTest {

  @Test
  void testClass() {
    Cnab cnab = Fixture.getCnab();
    assertClass(Cnab.class, Fixture.getCnab());
    assertEquals(FINANCIAMENTO, cnab.getType());
    assertEquals(Instant.parse("2019-03-01T15:34:53.00Z"), cnab.getInstant());
    assertEquals(142.00, cnab.getValue());
    assertEquals("09620676017", cnab.getCpf());
    assertEquals("4753****3153", cnab.getCreditCard());
    assertEquals("JOÃO MACEDO", cnab.getOwner());
    assertEquals("BAR DO JOÃO", cnab.getStoreName());
  }

  @ParameterizedTest
  @EmptySource
  @CsvSource({
    "6760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ",
    "0201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO      *",
    "3201913010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO      *",
    "3201903010000014200096206760174753****3153283453JOÃO MACEDO   BAR DO JOÃO      *",
    "32019030100000ABC00096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO      *",
  })
  void getInstanceWithError(String line) {
    assertThrows(IllegalStateException.class, () -> Cnab.getInstance(line));
  }

  @Test
  void valueEmpty() {
    Cnab cnab =
        Cnab.getInstance(
            "3201903010000000000096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO      *");
    assertEquals(0, cnab.getValue());
  }

  @Test
  void testHour() {
    Cnab cnab =
        Cnab.getInstance(
            "1201903010000015200096206760171234****7890233000JOÃO MACEDO   BAR DO JOÃO       ");
    assertEquals(Instant.parse("2019-03-01T23:30:00.00Z"), cnab.getInstant());
  }
}
