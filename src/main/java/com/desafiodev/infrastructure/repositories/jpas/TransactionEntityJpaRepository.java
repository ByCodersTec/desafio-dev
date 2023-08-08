package com.desafiodev.infrastructure.repositories.jpas;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEntityJpaRepository extends JpaRepository<TransactionEntity, String> {
  List<TransactionEntity> findByStore(StoreEntity store);
}
