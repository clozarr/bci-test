package com.bci.test.demo.domain.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalErrorHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex, HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .reason(ex.getHttpStatus().getReasonPhrase())
                .code(ex.getHttpStatus().value())
                .date(LocalDateTime.now().format(formatter))
                .message(ex.getMessage())
                .path(URI.create(request.getRequestURI()))
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, ex.getHttpStatus());

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleUserException(DataIntegrityViolationException ex, HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .reason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .date(LocalDateTime.now().format(formatter))
                .message("Unique index or primary key violation: 'Correo ya resgistrado'" )
                .path(URI.create(request.getRequestURI()))
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
