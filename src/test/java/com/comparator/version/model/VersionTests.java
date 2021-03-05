package com.comparator.version.model;

import com.comparator.version.exception.VersionException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VersionTests {

    @Test
    void versionCreationTest() {
        Version version = new Version("11.21abc3.3");
        Assertions.assertEquals("11.21abc3.3", version.getVersionString());
        Assertions.assertEquals(3, version.getSubVersions().size());
        Assertions.assertEquals("11", version.getSubVersions().get(0).getAlphanumeric());
        Assertions.assertEquals("21abc3", version.getSubVersions().get(1).getAlphanumeric());
        Assertions.assertEquals("3", version.getSubVersions().get(2).getAlphanumeric());
    }

    @Test
    void versionExceptionTests() {
        assertThrows(VersionException.class, () -> new Version("11!#.21abc3.3"));
        assertThrows(VersionException.class, () -> new Version("!#.@"));
    }

}
