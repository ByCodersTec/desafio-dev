package com.desafiodev.application.ports.in;

import java.io.File;

public interface UploadService {
  void accept(File uploadFile);
}
