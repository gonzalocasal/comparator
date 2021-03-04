package com.comparator.version.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class VersionSanitizerTest {

    private final VersionSanitizer inputSanitizer = new VersionSanitizer();

    @Test
    void sanitizerTest() {
        String sanitized = inputSanitizer.sanitize("1.0.3.a");
        Assert.assertEquals("1.0.3.a", sanitized);

        sanitized = inputSanitizer.sanitize("1. # % @0.3.a");
        Assert.assertEquals("1.0.3.a", sanitized);
    }
}
