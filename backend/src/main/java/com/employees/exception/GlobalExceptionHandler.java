package com.employees.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleAccountExceptions(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                new Response(ex.getMessage(), null)
        );
    }
}
