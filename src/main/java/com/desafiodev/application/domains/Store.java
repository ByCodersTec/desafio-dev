package com.desafiodev.application.domains;

import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import com.desafiodev.application.domains.ids.StoreId;
import lombok.NonNull;
import lombok.Value;

@Value
public class Store {

  StoreId storeId;
  String name;
  String ownerName;

  private Store(@NonNull StoreId storeId, @NonNull String name, @NonNull String ownerName) {
    if (name.isEmpty())
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Name can't be empty")
          .param("name", name)
          .build();
    if (ownerName.isEmpty())
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Owner name can't be empty")
          .param("ownerName", ownerName)
          .build();
    this.storeId = storeId;
    this.name = name.toUpperCase();
    this.ownerName = ownerName.toUpperCase();
  }

  public static Store newInstance(@NonNull String name, @NonNull String ownerName) {
    return new Store(StoreId.newInstance(), name, ownerName);
  }
}
