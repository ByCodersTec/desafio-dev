package com.desafiodev.infrastructure.storeges;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.desafiodev.infrastructure.configurations.interfaces.UploadConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
class LocalStorageServiceImplTest {

  @Mock private UploadConfiguration uploadConfiguration;

  private LocalStorageServiceImpl localStorageService;

  @BeforeEach
  void setUp() {
    when(uploadConfiguration.getPathname()).thenReturn("upload");
    when(uploadConfiguration.getFilename()).thenReturn("targetFile.tmp");
    localStorageService = new LocalStorageServiceImpl(uploadConfiguration);
  }

  @Test
  void save() {
    MockMultipartFile file =
        new MockMultipartFile(
            "file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
    localStorageService.save(file);
    verify(uploadConfiguration, times(1)).getPathname();
    verify(uploadConfiguration, times(1)).getFilename();
  }

  @Test
  void saveTwice() {
    MockMultipartFile file =
        new MockMultipartFile(
            "file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
    localStorageService.save(file);
    localStorageService.save(file);
    verify(uploadConfiguration, times(2)).getPathname();
    verify(uploadConfiguration, times(2)).getFilename();
  }
}
