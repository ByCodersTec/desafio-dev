package com.desafiodev.api.v1.controllers;

import com.desafiodev.configurations.interfaces.UploadConfiguration;
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

  @Autowired
  public UploadController(UploadConfiguration uploadConfiguration) {
    this.uploadConfiguration = uploadConfiguration;
  }

  @PostMapping("/cnab")
  public @ResponseBody ResponseEntity<String> cnab(@RequestParam("file") MultipartFile file)
      throws IOException {
    file.transferTo(new File(uploadConfiguration.getFileName()));
    return ResponseEntity.ok("File uploaded successfully.");
  }
}
