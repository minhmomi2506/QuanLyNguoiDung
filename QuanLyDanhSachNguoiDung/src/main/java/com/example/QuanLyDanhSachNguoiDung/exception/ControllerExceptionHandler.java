package com.example.QuanLyDanhSachNguoiDung.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Forbidden.class)
    public ResponseEntity<ErrorMessage> forbiddenException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), ex.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FORBIDDEN);
    }
}
