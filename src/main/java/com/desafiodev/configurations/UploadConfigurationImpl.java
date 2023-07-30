package com.desafiodev.configurations;

import com.desafiodev.configurations.interfaces.UploadConfiguration;
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
  public String getFileName(@NonNull String fileName) {
    return uploadPath + "\\" + fileName;
  }
}
