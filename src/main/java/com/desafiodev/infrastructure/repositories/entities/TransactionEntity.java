package com.desafiodev.infrastructure.repositories.entities;

import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.domains.TransactionType;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "transactions")
@Getter
@ToString
@EqualsAndHashCode
public class TransactionEntity {
  @Id @EqualsAndHashCode.Exclude private String id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @NotNull private Instant date;
  private double value;

  @NotBlank private String cpf;

  @NotBlank private String creditCard;
  @NotBlank private String storeOwner;
  @NotBlank private String store;
  private double total;

  public TransactionEntity() {}

  private TransactionEntity(
      @NonNull String id,
      @NonNull TransactionType type,
      @NonNull Instant date,
      double value,
      @NonNull String cpf,
      @NonNull String creditCard,
      @NonNull String storeOwner,
      @NonNull String store,
      double total) {
    this.id = id;
    this.type = type;
    this.date = date;
    this.value = value;
    this.cpf = cpf;
    this.creditCard = creditCard;
    this.storeOwner = storeOwner;
    this.store = store;
    this.total = total;
  }

  public static TransactionEntity from(Transaction transaction) {
    return new TransactionEntity(
        transaction.getId().toString(),
        transaction.getType(),
        transaction.getDate(),
        transaction.getValue(),
        transaction.getCpf().getNumber(),
        transaction.getCreditCard().getNumber(),
        transaction.getStoreOwner(),
        transaction.getStore(),
        transaction.getTotal());
  }
}
