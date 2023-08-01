package com.desafiodev.utils;

import com.desafiodev.application.domains.*;
import java.time.Instant;

public class Fixture {
  public static Cpf getCpf() {
    return Cpf.getInstance("11111111111");
  }

  public static CreditCard getCreditCard() {
    return CreditCard.getInstance("111111111111");
  }

  public static TransactionType getTransactionType() {
    return TransactionType.ALUGUEL;
  }

  public static Transaction getTransaction() {
    return Transaction.getInstance(
        getTransactionType(), Instant.now(), 10, getCpf(), getCreditCard(), "Willian", "Store");
  }

  public static Cnab getCnab() {
    return Cnab.getInstance(
        "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ");
  }
}
