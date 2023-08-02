package com.desafiodev.infrastructure.api.v1.controllers;

import com.desafiodev.infrastructure.api.dtos.StoreResponseDTO;
import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping("api/v1/store")
public class StoreController {

  private final StoreEntityJpaRepository storeEntityJpaRepository;

  @Autowired
  public StoreController(StoreEntityJpaRepository storeEntityJpaRepository) {
    this.storeEntityJpaRepository = storeEntityJpaRepository;
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<StoreResponseDTO>> findAll() {
    List<StoreEntity> entities = storeEntityJpaRepository.findAll();
    if (entities.isEmpty()) return ResponseEntity.noContent().build();

    return ResponseEntity.ok(StoreResponseDTO.asList(entities));
  }
}
