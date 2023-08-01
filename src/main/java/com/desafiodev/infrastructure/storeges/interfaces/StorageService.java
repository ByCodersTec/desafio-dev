package com.desafiodev.infrastructure.storeges.interfaces;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  File save(MultipartFile multipartFile);
}
