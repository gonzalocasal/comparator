package com.comparator.versionchecker.service;

import com.comparator.versionchecker.exception.NoAlphanumericInputException;
import org.springframework.stereotype.Service;

import static com.comparator.versionchecker.util.Constants.ALPHANUMERIC_REGEX;

@Service
public class InputSanitizer {

    public String sanitize(String userInput) {
        String inputSanitized = userInput.replaceAll(ALPHANUMERIC_REGEX, "");
        if (inputSanitized.isEmpty()) {
            throw new NoAlphanumericInputException("Input must have at least 1 alphanumeric character.");
        }
        return inputSanitized;
    }
}
