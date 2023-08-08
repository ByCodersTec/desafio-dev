package com.desafiodev.infrastructure.storeges;

import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import com.desafiodev.infrastructure.configurations.interfaces.UploadConfiguration;
import com.desafiodev.infrastructure.storeges.interfaces.StorageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalStorageServiceImpl implements StorageService {

  private final UploadConfiguration uploadConfiguration;

  @Autowired
  public LocalStorageServiceImpl(UploadConfiguration uploadConfiguration) {
    this.uploadConfiguration = uploadConfiguration;
  }

  @Override
  public File save(MultipartFile multipartFile) {
    Path root = Paths.get(this.uploadConfiguration.getPathname());
    try {
      if (!Files.exists(root)) Files.createDirectories(root);
      Path patch = root.resolve(uploadConfiguration.getFilename());
      multipartFile.transferTo(patch);
      return patch.toFile();
    } catch (IOException e) {
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("Could not initialize folder for upload!")
          .build();
    }
  }
}
