package com.desafiodev.infrastructure.repositories.entities;

import com.desafiodev.application.domains.Store;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "store")
@Getter
@ToString
@EqualsAndHashCode
@Table(
    uniqueConstraints = { // other constraints
      @UniqueConstraint(
          name = "UniqueNameAndOwnerName",
          columnNames = {"name", "ownerName"})
    })
public class StoreEntity {
  @Id @EqualsAndHashCode.Exclude private String id;

  @NotBlank private String name;
  @NotBlank private String ownerName;

  public StoreEntity() {}

  private StoreEntity(String id, String name, String ownerName) {
    this.id = id;
    this.name = name;
    this.ownerName = ownerName;
  }

  public static StoreEntity from(Store store) {
    return new StoreEntity(store.getStoreId().getId(), store.getName(), store.getOwnerName());
  }
}
