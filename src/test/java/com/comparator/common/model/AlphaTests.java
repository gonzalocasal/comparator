package com.comparator.common.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphaTests {

    @Test
    void alphanumericCreationTest() {
        Alpha alpha = new Alpha("21abc1abc3");
        Assertions.assertEquals("21abc1abc3", alpha.getValue());
        Assertions.assertEquals(5, alpha.getUniformAlphas().size());

        alpha = new Alpha("$%&@#/(");
        Assertions.assertTrue(alpha.getValue().isEmpty());
    }

}
