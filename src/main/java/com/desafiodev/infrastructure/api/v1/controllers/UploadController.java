package com.desafiodev.infrastructure.api.v1.controllers;

import com.desafiodev.application.ports.in.UploadService;
import com.desafiodev.infrastructure.storeges.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("api/v1/upload")
public class UploadController {
  private final UploadService uploadService;

  private final StorageService storageService;

  @Autowired
  public UploadController(UploadService uploadService, StorageService storageService) {
    this.uploadService = uploadService;
    this.storageService = storageService;
  }

  @PostMapping("/cnab")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public @ResponseBody ResponseEntity<String> cnab(@RequestParam("file") MultipartFile file) {
    uploadService.accept(storageService.save(file));
    return ResponseEntity.accepted().body("File uploaded successfully.");
  }
}
