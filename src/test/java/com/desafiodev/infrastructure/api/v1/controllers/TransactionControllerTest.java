package com.desafiodev.infrastructure.api.v1.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafiodev.infrastructure.repositories.entities.TransactionEntity;
import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import java.util.Collections;
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

  @Test
  void findAll() throws Exception {
    when(transactionEntityJpaRepository.findAll())
        .thenReturn(
            Collections.singletonList(
                TransactionEntity.from(Fixture.getTransaction(), Fixture.getStore())));
    mockMvc
        .perform(get("/api/v1/transaction").content("").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(transactionEntityJpaRepository, times(1)).findAll();
  }

  @Test
  void findAllEmpty() throws Exception {
    when(transactionEntityJpaRepository.findAll()).thenReturn(Collections.emptyList());
    mockMvc
        .perform(get("/api/v1/transaction").content("").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    verify(transactionEntityJpaRepository, times(1)).findAll();
  }
}
