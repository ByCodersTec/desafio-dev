package com.desafiodev.domains;

import static com.desafiodev.domains.TransactionType.getTransactionType;
import static com.desafiodev.domains.exceptions.IllegalStateExceptionFactory.builder;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.*;
import java.time.format.DateTimeFormatter;
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
    TransactionType type =
        getTransactionType(cnab.getType())
            .orElseThrow(
                () ->
                    builder(Transaction.class)
                        .message("Transaction type not found")
                        .param("cnab", cnab)
                        .build());
    DateTimeFormatter formatter = ofPattern("yyyyMMddHHmmss");
    Instant date =
        Instant.parse(
                LocalDateTime.parse(cnab.getDate().concat(cnab.getHour()), formatter)
                    .toString()
                    .concat(".00Z"))
            .atZone(ZoneId.of("America/Sao_Paulo"))
            .toInstant();
    double value = Double.parseDouble(cnab.getValue()) / 100;
    Cpf cpf = Cpf.getInstance(cnab.getCpf());
    CreditCard creditCard = CreditCard.getInstance(cnab.getCreditCard());
    String storeOwner = cnab.getOwner();
    String store = cnab.getStoreName();
    return new Transaction(type, date, value, cpf, creditCard, storeOwner, store);
  }
}
