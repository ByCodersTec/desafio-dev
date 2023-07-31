package com.desafiodev.domains;

import com.desafiodev.domains.exceptions.IllegalStateExceptionFactory;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Value
public class Transaction {

  UUID id;
  TransactionType type;
  Instant date;
  double value;
  Cpf cpf;
  CreditCard creditCard;
  String storeOwner;
  String store;
  double total;

  public static Transaction getInstance(
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull Cpf cpf,
      @NonNull CreditCard creditCard,
      @NonNull String storeOwner,
      @NonNull String store) {
    return new Transaction(type, date, value, cpf, creditCard, storeOwner, store);
  }

  private Transaction(
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull Cpf cpf,
      @NonNull CreditCard creditCard,
      @NonNull String storeOwner,
      @NonNull String store) {
    if (value < 0)
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Value must be positive")
          .param("value", value)
          .build();
    if (storeOwner.isEmpty())
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Store Owner can't be empty")
          .param("storeOwner", storeOwner)
          .build();
    if (store.isEmpty())
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Store can't be empty")
          .param("store", store)
          .build();
    this.id = UUID.randomUUID();
    this.type = type;
    this.date = date;
    this.value = value;
    this.cpf = cpf;
    this.creditCard = creditCard;
    this.storeOwner = storeOwner;
    this.store = store;
    this.total = type.getValue(value);
  }
}
