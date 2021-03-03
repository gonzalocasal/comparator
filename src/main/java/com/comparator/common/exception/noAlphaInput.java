package com.comparator.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class noAlphaInput extends ResponseStatusException {
    public noAlphaInput(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}