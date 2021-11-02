package com.github.filipednb.financialtransactions.api.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

    StoreEntity findByName(String name);

}
