package com.desafiodev.infrastructure.repositories;

import com.desafiodev.application.domains.Store;
import com.desafiodev.application.ports.out.StoreRepository;
import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreRepositoryImpl implements StoreRepository {

  private final StoreEntityJpaRepository storeEntityJpaRepository;

  @Autowired
  public StoreRepositoryImpl(StoreEntityJpaRepository storeEntityJpaRepository) {
    this.storeEntityJpaRepository = storeEntityJpaRepository;
  }

  @Override
  public Optional<Store> findByNameAndOwnerName(
      @NonNull String storeName, @NonNull String ownerName) {
    return storeEntityJpaRepository
        .findByNameIgnoreCaseAndOwnerNameIgnoreCase(storeName, ownerName)
        .map(StoreEntity::getStore);
  }

  @Override
  public void save(@NonNull Store store) {
    storeEntityJpaRepository.save(StoreEntity.from(store));
  }
}
