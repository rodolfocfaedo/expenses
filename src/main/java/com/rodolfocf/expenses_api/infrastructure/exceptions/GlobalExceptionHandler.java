package com.rodolfocf.expenses_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException emailAlreadyRegisteredException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(emailAlreadyRegisteredException.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundException emailNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emailNotFoundException.getMessage());
    }
}
