package com.desafiodev.infrastructure.api.v1.controllers;

import com.desafiodev.application.ports.in.UploadService;
import com.desafiodev.infrastructure.configurations.interfaces.UploadConfiguration;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("api/v1/upload")
public class UploadController {

  private final UploadConfiguration uploadConfiguration;

  private final UploadService uploadService;

  @Autowired
  public UploadController(UploadConfiguration uploadConfiguration, UploadService uploadService) {
    this.uploadConfiguration = uploadConfiguration;
    this.uploadService = uploadService;
  }

  @PostMapping("/cnab")
  public @ResponseBody ResponseEntity<String> cnab(@RequestParam("file") MultipartFile file)
      throws IOException {
    File uploadFile = new File(uploadConfiguration.getPathname());
    file.transferTo(uploadFile);
    uploadService.accept(uploadFile);
    return ResponseEntity.ok("File uploaded successfully.");
  }
}
