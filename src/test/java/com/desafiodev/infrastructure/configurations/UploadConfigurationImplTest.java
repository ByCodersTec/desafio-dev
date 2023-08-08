package com.desafiodev.infrastructure.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UploadConfigurationImpl.class)
class UploadConfigurationImplTest {

  @Autowired private UploadConfigurationImpl uploadConfigurations;

  @Test
  void getPathname() {
    assertEquals("upload", uploadConfigurations.getPathname());
  }

  @Test
  void getFilename() {
    assertEquals("targetFile.tmp", uploadConfigurations.getFilename());
  }
}
