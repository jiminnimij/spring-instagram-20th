package com.ceos20.instagram.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(final NotFoundException e) {
        logError(e);
        return ResponseEntity.status(NOT_FOUND).body(ExceptionResponse.from(e));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(final BadRequestException e) {
        logError(e);
        return ResponseEntity.status(BAD_REQUEST).body(ExceptionResponse.from(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception e) {
        logError(e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ExceptionResponse.from(e));
    }

    private void logError(final Exception e) {
        log.error(e.getMessage(), e);
    }
}
