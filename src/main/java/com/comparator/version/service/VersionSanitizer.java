package com.comparator.version.service;

import com.comparator.common.exception.noAlphaInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.comparator.version.util.Constants.ALPHANUMERIC_REGEX;

@Service
public class VersionSanitizer {

    @Value("${error.message.no.alphanumeric.input}")
    private String noAlphanumericMessage;

    public String sanitize(String userInput) {
        String inputSanitized = userInput.replaceAll(ALPHANUMERIC_REGEX, "");
        if (inputSanitized.isEmpty()) {
            throw new noAlphaInput(noAlphanumericMessage);
        }
        return inputSanitized;
    }
}