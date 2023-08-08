package com.desafiodev.application.domains;

import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import com.desafiodev.application.domains.ids.StoreId;
import com.desafiodev.application.domains.ids.TransactionId;
import java.time.Instant;
import lombok.NonNull;
import lombok.Value;

@Value
public class Transaction {

  TransactionId transactionId;
  StoreId storeId;
  TransactionType type;
  Instant date;
  double value;
  Cpf cpf;
  CreditCard creditCard;

  private Transaction(
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull Cpf cpf,
      @NonNull CreditCard creditCard,
      @NonNull StoreId storeId) {
    if (value < 0)
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Value must be positive")
          .param("value", value)
          .build();
    this.transactionId = TransactionId.newInstance();
    this.type = type;
    this.date = date;
    this.value = value;
    this.cpf = cpf;
    this.creditCard = creditCard;
    this.storeId = storeId;
  }

  public static Transaction newInstance(
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull Cpf cpf,
      @NonNull CreditCard creditCard,
      @NonNull StoreId storeId) {
    return new Transaction(type, date, value, cpf, creditCard, storeId);
  }

  public static Transaction parse(@NonNull Cnab cnab, @NonNull StoreId storeId) {
    Cpf cpf = Cpf.newInstance(cnab.getCpf());
    CreditCard creditCard = CreditCard.newInstance(cnab.getCreditCard());
    return new Transaction(
        cnab.getType(), cnab.getInstant(), cnab.getValue(), cpf, creditCard, storeId);
  }
}
