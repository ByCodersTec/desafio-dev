package com.desafiodev.infrastructure.api.dtos;

import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.Value;

@Value
public class TransactionResponseDTO {

  String transactionId;
  String type;
  String date;
  double transactionValue;
  String cpfNumber;
  String creditCardNumber;

  private TransactionResponseDTO(
      String transactionId,
      String type,
      String date,
      double transactionValue,
      String cpfNumber,
      String creditCardNumber) {
    this.transactionId = transactionId;
    this.type = type;
    this.date = date;
    this.transactionValue = transactionValue;
    this.cpfNumber = cpfNumber;
    this.creditCardNumber = creditCardNumber;
  }

  private static TransactionResponseDTO getInstance(@NonNull TransactionEntity t) {
    return new TransactionResponseDTO(
        t.getId(),
        t.getType().name(),
        t.getDate().toString(),
        t.getValue(),
        t.getCpf(),
        t.getCreditCard());
  }

  public static List<TransactionResponseDTO> asList(
      @NonNull List<TransactionEntity> transactionEntities) {
    return transactionEntities.stream()
        .map(TransactionResponseDTO::getInstance)
        .collect(Collectors.toList());
  }
}
