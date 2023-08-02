package com.desafiodev.infrastructure.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import com.desafiodev.infrastructure.repositories.jpas.StoreEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StoreController.class)
class StoreControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockBean private StoreEntityJpaRepository storeEntityJpaRepository;

  @Test
  void findAll() throws Exception {
    when(storeEntityJpaRepository.findAll())
        .thenReturn(Collections.singletonList(StoreEntity.from(Fixture.getStore())));
    mockMvc
        .perform(get("/api/v1/store").content("").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(storeEntityJpaRepository, times(1)).findAll();
  }

  @Test
  void findAllEmpty() throws Exception {
    when(storeEntityJpaRepository.findAll()).thenReturn(Collections.emptyList());
    mockMvc
        .perform(get("/api/v1/store").content("").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    verify(storeEntityJpaRepository, times(1)).findAll();
  }
}
