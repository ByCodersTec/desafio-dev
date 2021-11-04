package com.github.filipednb.financialtransactions.api.exception;

import com.github.filipednb.financialtransactions.file.exception.FileParsingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(FileParsingException.class)
    public ResponseEntity<Object> handleUnprocessableFile(FileParsingException ex) {
        Map<String, Object> body = getExceptionBoddy(ex);
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Object> handleDateException(FileParsingException ex) {
        Map<String, Object> body = getExceptionBoddy(ex);
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundStoreException.class)
    public ResponseEntity<Object> handleStoreNotFoundException(FileParsingException ex) {
        Map<String, Object> body = getExceptionBoddy(ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> getExceptionBoddy(FileParsingException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return body;
    }
}
