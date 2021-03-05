package com.comparator.common.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphaTests {

    @Test
    void alphanumericCreationTest() {
        Alpha alpha = new Alpha("21abc3");
        Assertions.assertEquals("21abc3", alpha.getAlphanumeric());
        Assertions.assertEquals(3, alpha.getUniformAlphas().size());
    }

}
