package com.desafiodev.infrastructure.api.v1.controllers;

import com.desafiodev.infrastructure.api.dtos.TransactionResponseDTO;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/transaction")
public class TransactionController {

  private final TransactionEntityJpaRepository transactionEntityJpaRepository;

  @Autowired
  public TransactionController(TransactionEntityJpaRepository transactionEntityJpaRepository) {
    this.transactionEntityJpaRepository = transactionEntityJpaRepository;
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<TransactionResponseDTO>> findAll() {
    List<TransactionEntity> entities = transactionEntityJpaRepository.findAll();
    if (entities.isEmpty()) return ResponseEntity.noContent().build();

    return ResponseEntity.ok(TransactionResponseDTO.asList(entities));
  }
}
