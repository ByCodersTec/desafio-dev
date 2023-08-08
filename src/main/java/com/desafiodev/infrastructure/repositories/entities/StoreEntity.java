package com.desafiodev.infrastructure.repositories.entities;

import com.desafiodev.application.domains.Store;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "store")
@Getter
@ToString
@EqualsAndHashCode
@Table(
    uniqueConstraints = {
      @UniqueConstraint(
          name = "UniqueNameAndOwnerName",
          columnNames = {"name", "ownerName"})
    })
public class StoreEntity {
  @Id @EqualsAndHashCode.Exclude private String id;

  @NotBlank private String name;
  @NotBlank private String ownerName;

  @NotNull private double balance;

  public StoreEntity() {}

  private StoreEntity(String id, String name, String ownerName, double balance) {
    this.id = id;
    this.name = name;
    this.ownerName = ownerName;
    this.balance = balance;
  }

  public Store getStore() {
    return Store.getInstance(id, name, ownerName, balance);
  }

  public static StoreEntity from(Store store) {
    return new StoreEntity(
        store.getStoreId().getId(), store.getName(), store.getOwnerName(), store.getBalance());
  }
}
