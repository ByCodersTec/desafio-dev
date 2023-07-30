package com.desafiodev.api.v1.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafiodev.configurations.interfaces.UploadConfiguration;
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
  @MockBean private UploadConfiguration uploadConfiguration;

  @BeforeEach
  void setUp() {
    when(uploadConfiguration.getFileName()).thenReturn(".uploads/test");
  }

  @Test
  void cnab() throws Exception {
    MockMultipartFile file =
        new MockMultipartFile(
            "file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
    mockMvc.perform(multipart("/api/v1/upload/cnab").file(file)).andExpect(status().isOk());
    verify(uploadConfiguration, times(1)).getFileName();
  }
}
