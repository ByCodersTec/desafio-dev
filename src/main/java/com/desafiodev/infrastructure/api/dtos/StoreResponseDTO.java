package com.desafiodev.infrastructure.api.dtos;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.Value;

@Value
public class StoreResponseDTO {
  String storeId;

  String storeName;

  String ownerName;

  double storeBalance;

  private StoreResponseDTO(
      String storeId, String storeName, String ownerName, double storeBalance) {
    this.storeId = storeId;
    this.storeName = storeName;
    this.ownerName = ownerName;
    this.storeBalance = storeBalance;
  }

  private static StoreResponseDTO getInstance(@NonNull StoreEntity s) {
    return new StoreResponseDTO(s.getId(), s.getName(), s.getOwnerName(), s.getBalance());
  }

  public static List<StoreResponseDTO> asList(@NonNull List<StoreEntity> storeEntities) {
    return storeEntities.stream().map(StoreResponseDTO::getInstance).collect(Collectors.toList());
  }
}
