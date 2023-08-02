package com.desafiodev.infrastructure.api.v1.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private TransactionEntityJpaRepository transactionEntityJpaRepository;

  @MockBean private StoreEntityJpaRepository storeEntityJpaRepository;

  @Test
  void findByStore() throws Exception {
    StoreEntity storeEntity = StoreEntity.from(Fixture.getStore());
    when(storeEntityJpaRepository.findById(any())).thenReturn(Optional.of(storeEntity));
    when(transactionEntityJpaRepository.findByStore(storeEntity))
        .thenReturn(
            Collections.singletonList(
                TransactionEntity.from(Fixture.getTransaction(), Fixture.getStore())));
    mockMvc
        .perform(
            get("/api/v1/transaction?storeId=" + storeEntity.getId())
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(storeEntityJpaRepository, times(1)).findById(storeEntity.getId());
    verify(transactionEntityJpaRepository, times(1)).findByStore(storeEntity);
  }

  @Test
  void findByStoreEmpty() throws Exception {
    StoreEntity storeEntity = StoreEntity.from(Fixture.getStore());
    when(storeEntityJpaRepository.findById(any())).thenReturn(Optional.of(storeEntity));
    when(transactionEntityJpaRepository.findByStore(any())).thenReturn(Collections.emptyList());
    mockMvc
        .perform(
            get("/api/v1/transaction?storeId=" + storeEntity.getId())
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    verify(storeEntityJpaRepository, times(1)).findById(storeEntity.getId());
    verify(transactionEntityJpaRepository, times(1)).findByStore(storeEntity);
  }

  @Test
  void StoreEmpty() throws Exception {
    String uuid = UUID.randomUUID().toString();
    when(storeEntityJpaRepository.findById(any())).thenReturn(Optional.empty());
    when(transactionEntityJpaRepository.findByStore(any())).thenReturn(Collections.emptyList());
    mockMvc
        .perform(
            get("/api/v1/transaction?storeId=" + uuid)
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    verify(storeEntityJpaRepository, times(1)).findById(uuid);
    verify(transactionEntityJpaRepository, times(0)).findByStore(any());
  }
}
