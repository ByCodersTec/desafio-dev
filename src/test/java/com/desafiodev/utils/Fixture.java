package com.desafiodev.utils;

import com.desafiodev.domains.Cpf;
import com.desafiodev.domains.CreditCard;
import com.desafiodev.domains.Transaction;
import com.desafiodev.domains.TransactionType;
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
    return Transaction.builder()
        .cpf(getCpf())
        .value(10)
        .type(getTransactionType())
        .date(Instant.now())
        .creditCard(getCreditCard())
        .store("Store")
        .storeOwner("Willian")
        .build();
  }
}
