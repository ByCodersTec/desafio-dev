package com.desafiodev.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;

class CnabTest extends UtilsTest {

  @Test
  void testClass() {
    Cnab cnab = Fixture.getCnab();
    assertClass(Cnab.class, Fixture.getCnab());
    assertEquals("3", cnab.getType());
    assertEquals("20190301", cnab.getDate());
    assertEquals("14200", cnab.getValue());
    assertEquals("09620676017", cnab.getCpf());
    assertEquals("4753****3153", cnab.getCreditCard());
    assertEquals("153453", cnab.getHour());
    assertEquals("JOÃO MACEDO", cnab.getOwner());
    assertEquals("BAR DO JOÃO", cnab.getStoreName());
  }
}
