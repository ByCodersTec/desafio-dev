package com.desafiodev.domains;

import static com.desafiodev.domains.exceptions.IllegalStateExceptionFactory.builder;

import java.time.*;
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

  private Transaction(
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull Cpf cpf,
      @NonNull CreditCard creditCard,
      @NonNull String storeOwner,
      @NonNull String store) {
    if (value < 0)
      throw builder(getClass()).message("Value must be positive").param("value", value).build();
    if (storeOwner.isEmpty())
      throw builder(getClass())
          .message("Store Owner can't be empty")
          .param("storeOwner", storeOwner)
          .build();
    if (store.isEmpty())
      throw builder(getClass()).message("Store can't be empty").param("store", store).build();
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

  public static Transaction parse(@NonNull Cnab cnab) {
    Cpf cpf = Cpf.getInstance(cnab.getCpf());
    CreditCard creditCard = CreditCard.getInstance(cnab.getCreditCard());
    String storeOwner = cnab.getOwner();
    String store = cnab.getStoreName();
    return new Transaction(
        cnab.getType(), cnab.getInstant(), cnab.getValue(), cpf, creditCard, storeOwner, store);
  }
}
