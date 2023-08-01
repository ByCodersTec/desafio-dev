package com.desafiodev.application.domains.ids;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class StoreId {
  String id;

  private StoreId(@NonNull String id) {
    this.id = id;
  }

  public static StoreId newInstance() {
    return new StoreId(UUID.randomUUID().toString());
  }

  public static StoreId getInstance(@NonNull String id) {
    return new StoreId(id);
  }
}
