package com.comparator.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoAlphaInput extends ResponseStatusException {

    public NoAlphaInput(String errorMessage){
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}