package com.desafiodev.infrastructure.configurations;

import com.desafiodev.infrastructure.configurations.interfaces.UploadConfiguration;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfigurationImpl implements UploadConfiguration {
  private final String uploadPath;
  private final String filename;

  public UploadConfigurationImpl(
      @NonNull @Value("${upload.path}") String uploadPath,
      @NonNull @Value("${upload.file}") String filename) {
    this.uploadPath = uploadPath;
    this.filename = filename;
  }

  @Override
  public String getPathname() {
    return uploadPath;
  }

  @Override
  public String getFilename() {
    return filename;
  }
}
