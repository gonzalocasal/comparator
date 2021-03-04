package com.comparator.version.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VersionException extends ResponseStatusException {

    public VersionException(String errorMessage){
        super(HttpStatus.BAD_REQUEST, errorMessage);
    }
}