package com.comparator.versionchecker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoAlphanumericInputException extends ResponseStatusException {
    public NoAlphanumericInputException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}