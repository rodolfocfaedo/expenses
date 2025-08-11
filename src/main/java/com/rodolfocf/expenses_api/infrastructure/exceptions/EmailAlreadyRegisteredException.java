package com.rodolfocf.expenses_api.infrastructure.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

     public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
