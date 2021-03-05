package com.comparator.version.model;

import com.comparator.version.exception.VersionException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VersionTests {

    @Test
    void VersionCreationTest() {
        Version version = new Version("11.21abc3.3");
        Assert.assertEquals("11.21abc3.3", version.getVersionString());
        Assert.assertEquals(3, version.getSubVersions().size());
        Assert.assertEquals("11", version.getSubVersions().get(0).getAlphanumeric());
        Assert.assertEquals("21abc3", version.getSubVersions().get(1).getAlphanumeric());
        Assert.assertEquals("3", version.getSubVersions().get(2).getAlphanumeric());
    }

    @Test
    void VersionExceptionTests() {
        assertThrows(VersionException.class, () -> new Version("11!#.21abc3.3"));
        assertThrows(VersionException.class, () -> new Version("!#.@"));
    }

}
