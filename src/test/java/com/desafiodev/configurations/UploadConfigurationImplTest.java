package com.desafiodev.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UploadConfigurationImpl.class)
class UploadConfigurationImplTest {

  @Autowired private UploadConfigurationImpl uploadConfigurations;

  @Test
  void getFileName() {
    assertEquals(".uploads\\test.txt", uploadConfigurations.getFileName("test.txt"));
  }
}
