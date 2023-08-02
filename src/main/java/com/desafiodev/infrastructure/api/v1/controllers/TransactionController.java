package com.desafiodev.infrastructure.api.v1.controllers;

import com.desafiodev.infrastructure.api.dtos.TransactionResponseDTO;
import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping("api/v1/transaction")
public class TransactionController {

  private final TransactionEntityJpaRepository transactionEntityJpaRepository;
  private final StoreEntityJpaRepository storeEntityJpaRepository;

  @Autowired
  public TransactionController(
      TransactionEntityJpaRepository transactionEntityJpaRepository,
      StoreEntityJpaRepository storeEntityJpaRepository) {
    this.transactionEntityJpaRepository = transactionEntityJpaRepository;
    this.storeEntityJpaRepository = storeEntityJpaRepository;
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<TransactionResponseDTO>> findByStore(@RequestParam String storeId) {

    Optional<StoreEntity> optionalStoreEntity = storeEntityJpaRepository.findById(storeId);

    if (optionalStoreEntity.isEmpty()) return ResponseEntity.noContent().build();

    List<TransactionEntity> entities =
        transactionEntityJpaRepository.findByStore(optionalStoreEntity.get());
    if (entities.isEmpty()) return ResponseEntity.noContent().build();

    return ResponseEntity.ok(TransactionResponseDTO.asList(entities));
  }
}
