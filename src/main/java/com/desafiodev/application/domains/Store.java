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
  double balance;

  private Store(
      @NonNull StoreId storeId, @NonNull String name, @NonNull String ownerName, double balance) {
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
    this.balance = balance;
  }

  public Store sum(@NonNull Transaction transaction) {
    double newBalance = balance + transaction.getType().apply(transaction.getValue());
    return getInstance(this, newBalance);
  }

  private static Store getInstance(@NonNull Store store, double balance) {
    return new Store(store.getStoreId(), store.getName(), store.getOwnerName(), balance);
  }

  public static Store getInstance(
      @NonNull String id, @NonNull String name, @NonNull String ownerName, double balance) {
    return new Store(StoreId.getInstance(id), name, ownerName, balance);
  }

  public static Store newInstance(@NonNull String name, @NonNull String ownerName) {
    return new Store(StoreId.newInstance(), name, ownerName, 0.0);
  }

  public static Store from(Cnab cnab) {
    return new Store(StoreId.newInstance(), cnab.getStoreName(), cnab.getOwner(), 0.0);
  }
}
