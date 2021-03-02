package com.comparator.versionchecker.service;

import com.comparator.versionchecker.exception.NoAlphanumericInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.comparator.versionchecker.util.Constants.ALPHANUMERIC_REGEX;

@Service
public class InputSanitizer {

    @Value("${error.message.no.alphanumeric.input}")
    private String noAlphanumericMessage;

    public String sanitize(String userInput) {
        String inputSanitized = userInput.replaceAll(ALPHANUMERIC_REGEX, "");
        if (inputSanitized.isEmpty()) {
            throw new NoAlphanumericInputException(noAlphanumericMessage);
        }
        return inputSanitized;
    }
}
