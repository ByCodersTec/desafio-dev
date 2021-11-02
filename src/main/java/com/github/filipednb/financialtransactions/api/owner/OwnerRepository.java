package com.github.filipednb.financialtransactions.api.owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

    OwnerEntity findByName(String name);

}
