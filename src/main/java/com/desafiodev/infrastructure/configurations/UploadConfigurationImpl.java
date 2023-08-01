package com.desafiodev.infrastructure.configurations;

import com.desafiodev.infrastructure.configurations.interfaces.UploadConfiguration;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfigurationImpl implements UploadConfiguration {
  private final String uploadPath;

  public UploadConfigurationImpl(@NonNull @Value("${upload.path}") String uploadPath) {
    this.uploadPath = uploadPath;
  }

  @Override
  public String getPathname() {
    return uploadPath + "\\" + UUID.randomUUID();
  }
}
