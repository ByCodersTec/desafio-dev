package com.desafiodev.api.commons;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  private ResponseEntity<ApiError> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build());
  }

  @ExceptionHandler(IllegalStateException.class)
  private ResponseEntity<ApiError> handleIllegalStateException(IllegalStateException ex) {
    return handleException(ex);
  }

  @ExceptionHandler(IOException.class)
  private ResponseEntity<ApiError> handleIOException(IllegalStateException ex) {
    return handleException(ex);
  }
}
