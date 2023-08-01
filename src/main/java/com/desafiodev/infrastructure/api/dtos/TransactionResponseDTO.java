package com.desafiodev.infrastructure.api.dtos;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.Value;

@Value
public class TransactionResponseDTO {

  String transactionId;
  String storeId;
  String type;
  String date;
  double transactionValue;
  String cpfNumber;
  String creditCardNumber;

  String storeName;

  String ownerName;

  double storeBalance;

  private TransactionResponseDTO(
      String transactionId,
      String storeId,
      String type,
      String date,
      double transactionValue,
      String cpfNumber,
      String creditCardNumber,
      String storeName,
      String ownerName,
      double storeBalance) {
    this.transactionId = transactionId;
    this.storeId = storeId;
    this.type = type;
    this.date = date;
    this.transactionValue = transactionValue;
    this.cpfNumber = cpfNumber;
    this.creditCardNumber = creditCardNumber;
    this.storeName = storeName;
    this.ownerName = ownerName;
    this.storeBalance = storeBalance;
  }

  private static TransactionResponseDTO getInstance(@NonNull TransactionEntity t) {
    StoreEntity s = t.getStore();
    return new TransactionResponseDTO(
        t.getId(),
        s.getId(),
        t.getType().name(),
        t.getDate().toString(),
        t.getValue(),
        t.getCpf(),
        t.getCreditCard(),
        s.getName(),
        s.getOwnerName(),
        s.getBalance());
  }

  public static List<TransactionResponseDTO> asList(
      @NonNull List<TransactionEntity> transactionEntities) {
    return transactionEntities.stream()
        .map(TransactionResponseDTO::getInstance)
        .collect(Collectors.toList());
  }
}
