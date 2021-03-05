package com.comparator.common.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class AlphaTests {

    @Test
    void AlphanumericCreationTest() {
        Alpha alpha = new Alpha("21abc3");
        Assert.assertEquals("21abc3", alpha.getAlphanumeric());

        Assert.assertEquals(3, alpha.getUniformAlphas().size());

        Assert.assertEquals("21", alpha.getUniformAlphas().get(0).getUniformAlpha());
        Assert.assertTrue(alpha.getUniformAlphas().get(0).isDigit());

        Assert.assertEquals("abc", alpha.getUniformAlphas().get(1).getUniformAlpha());
        Assert.assertFalse(alpha.getUniformAlphas().get(1).isDigit());

        Assert.assertEquals("3", alpha.getUniformAlphas().get(2).getUniformAlpha());
        Assert.assertTrue(alpha.getUniformAlphas().get(2).isDigit());
    }

}
