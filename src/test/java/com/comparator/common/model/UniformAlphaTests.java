package com.comparator.common.model;

import com.comparator.common.exception.UniformAlphaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UniformAlphaTests {

    @Test
    void parseTest() {
        List<UniformAlpha> uniformAlphas = UniformAlpha.split("21abc3");

        Assertions.assertEquals("21", uniformAlphas.get(0).getValue());
        Assertions.assertTrue(uniformAlphas.get(0).isDigit());

        Assertions.assertEquals("abc", uniformAlphas.get(1).getValue());
        Assertions.assertFalse(uniformAlphas.get(1).isDigit());

        Assertions.assertEquals("3", uniformAlphas.get(2).getValue());
        Assertions.assertTrue(uniformAlphas.get(2).isDigit());

        List<UniformAlpha> parse = UniformAlpha.split("!@#$%^&1199");
        UniformAlpha uniformAlpha = parse.get(0);
        Assertions.assertEquals("1199", uniformAlpha.getValue());
        Assertions.assertTrue(uniformAlpha.isDigit());
    }

    @Test
    void uniformAlphaCreationTest() {
        UniformAlpha uniformAlpha = new UniformAlpha("21#@!");
        Assertions.assertEquals("21", uniformAlpha.getValue());
        Assertions.assertTrue(uniformAlpha.isDigit());
    }

    @Test
    void uniformExceptionExceptionTests() {
        assertThrows(UniformAlphaException.class, () -> new UniformAlpha("1a"));
    }

}
