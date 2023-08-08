package com.desafiodev.infrastructure.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafiodev.application.ports.in.UploadService;
import com.desafiodev.infrastructure.storeges.interfaces.StorageService;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UploadController.class)
class UploadControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockBean private StorageService storageService;
  @MockBean private UploadService uploadService;

  @BeforeEach
  void setUp() {
    when(storageService.save(any())).thenReturn(new File("src/test/resources/CNAB.txt"));
    doNothing().when(uploadService).accept(any());
  }

  @Test
  void cnab() throws Exception {
    MockMultipartFile file =
        new MockMultipartFile(
            "file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
    mockMvc.perform(multipart("/api/v1/upload/cnab").file(file)).andExpect(status().isAccepted());
    verify(storageService, times(1)).save(any());
    verify(uploadService, times(1)).accept(any());
  }
}
