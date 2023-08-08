package com.desafiodev.application.ports.out;

import com.desafiodev.application.domains.Store;
import java.util.Optional;

public interface StoreRepository {
  Optional<Store> findByNameAndOwnerName(String storeName, String ownerName);

  void save(Store store);
}
