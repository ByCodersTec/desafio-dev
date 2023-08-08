package com.desafiodev.utils;

import com.desafiodev.application.domains.*;
import com.desafiodev.application.domains.ids.StoreId;
import com.desafiodev.application.domains.ids.TransactionId;
import java.time.Instant;

public class Fixture {
  public static Cpf getCpf() {
    return Cpf.newInstance("11111111111");
  }

  public static CreditCard getCreditCard() {
    return CreditCard.newInstance("111111111111");
  }

  public static TransactionType getTransactionType() {
    return TransactionType.ALUGUEL;
  }

  public static Transaction getTransaction() {
    return Transaction.newInstance(
        getTransactionType(), Instant.now(), 10, getCpf(), getCreditCard(), StoreId.newInstance());
  }

  public static Cnab getCnab() {
    return Cnab.newInstance(
        "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ");
  }

  public static StoreId getStoreId() {
    return StoreId.newInstance();
  }

  public static TransactionId getTransactionId() {
    return TransactionId.newInstance();
  }

  public static Store getStore() {
    return Store.newInstance("NAME", "OWNER NAME");
  }

  public static Transaction getTransaction(TransactionType type, double transactionValue) {
    return Transaction.newInstance(
        type, Instant.now(), transactionValue, getCpf(), getCreditCard(), StoreId.newInstance());
  }
}
