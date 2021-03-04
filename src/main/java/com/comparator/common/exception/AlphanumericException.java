package com.comparator.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlphanumericException extends ResponseStatusException {

    public AlphanumericException(String errorMessage){
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}