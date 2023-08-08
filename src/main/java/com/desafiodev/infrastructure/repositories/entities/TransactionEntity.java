package com.desafiodev.infrastructure.repositories.entities;

import com.desafiodev.application.domains.Store;
import com.desafiodev.application.domains.Transaction;
import com.desafiodev.application.domains.TransactionType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import javax.persistence.*;
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

  @ManyToOne
  @JoinColumn(name = "fk_store")
  private StoreEntity store;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @NotNull private LocalDateTime date;
  private double value;

  @NotBlank private String cpf;

  @NotBlank private String creditCard;

  public TransactionEntity() {}

  private TransactionEntity(
      @NonNull String id,
      @NonNull TransactionType type,
      @NonNull LocalDateTime date,
      double value,
      @NonNull String cpf,
      @NonNull String creditCard,
      @NonNull StoreEntity store) {
    this.id = id;
    this.type = type;
    this.date = date;
    this.value = value;
    this.cpf = cpf;
    this.creditCard = creditCard;
    this.store = store;
  }

  public static TransactionEntity from(Transaction transaction, Store store) {
    return new TransactionEntity(
        transaction.getTransactionId().getId(),
        transaction.getType(),
        LocalDateTime.ofInstant(
            transaction.getDate().truncatedTo(ChronoUnit.MILLIS), ZoneId.of("America/Sao_Paulo")),
        transaction.getValue(),
        transaction.getCpf().getNumber(),
        transaction.getCreditCard().getNumber(),
        StoreEntity.from(store));
  }
}
