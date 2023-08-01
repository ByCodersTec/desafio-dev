package com.desafiodev.infrastructure.api.commons;

import java.time.Instant;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class ApiError {
  Instant timestamp = Instant.now();
  int status;
  String error;
  String message;
}
