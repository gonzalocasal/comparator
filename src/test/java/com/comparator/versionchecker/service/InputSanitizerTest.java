package com.comparator.versionchecker.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class InputSanitizerTest {

    private final InputSanitizer inputSanitizer = new InputSanitizer();

    @Test
    void sameNumericTest() {
        String sanitized = inputSanitizer.sanitize("1.0.3.a");
        Assert.assertEquals("1.0.3.a", sanitized);

        sanitized = inputSanitizer.sanitize("1. # % @0.3.a");
        Assert.assertEquals("1.0.3.a", sanitized);
    }
}
