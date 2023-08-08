package com.desafiodev.application.domains.ids;

import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class TransactionId {
  String id;

  private TransactionId(@NonNull String id) {
    this.id = id;
  }

  public static TransactionId newInstance() {
    return new TransactionId(UUID.randomUUID().toString());
  }

  public static TransactionId getInstance(@NonNull String id) {
    return new TransactionId(id);
  }
}
