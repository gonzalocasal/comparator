package com.comparator.common.model;

import com.comparator.common.exception.UniformAlphaException;
import com.comparator.version.exception.VersionException;
import com.comparator.version.model.Version;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UniformAlphaTests {

    @Test
    void parseTest() {
        List<UniformAlpha> uniformAlphas = UniformAlpha.parse("21abc3");

        Assertions.assertEquals("21", uniformAlphas.get(0).getUniformAlpha());
        Assertions.assertTrue(uniformAlphas.get(0).isDigit());

        Assertions.assertEquals("abc", uniformAlphas.get(1).getUniformAlpha());
        Assertions.assertFalse(uniformAlphas.get(1).isDigit());

        Assertions.assertEquals("3", uniformAlphas.get(2).getUniformAlpha());
        Assertions.assertTrue(uniformAlphas.get(2).isDigit());
    }

    @Test
    void uniformAlphaCreationTest() {
        UniformAlpha uniformAlpha = new UniformAlpha("21");
        Assertions.assertEquals("21", uniformAlpha.getUniformAlpha());
        Assertions.assertTrue(uniformAlpha.isDigit());
    }



    @Test
    void uniformExceptionExceptionTests() {
        assertThrows(UniformAlphaException.class, () -> new UniformAlpha("1a"));
    }

}
