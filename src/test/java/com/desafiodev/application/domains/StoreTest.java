package com.desafiodev.application.domains;

import static org.junit.jupiter.api.Assertions.*;

import com.desafiodev.utils.Fixture;
import com.desafiodev.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StoreTest extends UtilsTest {

  @Test
  void store() {
    Store store = Store.newInstance("Name", "OwnerName");
    assertClass(Store.class, store);
    assertNotNull(store.getStoreId());
    assertEquals("NAME", store.getName());
    assertEquals("OWNERNAME", store.getOwnerName());
    assertEquals(0.0, store.getBalance());
  }

  @Test
  void from() {
    Store store = Store.from(Fixture.getCnab());
    assertNotNull(store.getStoreId());
    assertEquals("BAR DO JOÃO", store.getName());
    assertEquals("JOÃO MACEDO", store.getOwnerName());
    assertEquals(0.0, store.getBalance());
  }

  @ParameterizedTest
  @CsvSource({"DEBITO, 10, 10", "BOLETO, 10, -10"})
  void sum(TransactionType type, double transactionValue, double expected) {
    Store store = Fixture.getStore();
    Transaction transaction = Fixture.getTransaction(type, transactionValue);
    assertEquals(expected, store.sum(transaction).getBalance());
    assertEquals(0, store.getBalance());
  }

  @Test
  void storeWithError() {
    assertThrows(IllegalStateException.class, () -> Store.newInstance("", "OwnerName"));
    assertThrows(IllegalStateException.class, () -> Store.newInstance("Name", ""));
  }
}
