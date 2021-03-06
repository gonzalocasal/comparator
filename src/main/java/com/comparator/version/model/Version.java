package com.comparator.version.model;

import com.comparator.common.model.Alpha;
import com.comparator.common.service.ListComparator;
import com.comparator.version.exception.VersionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.comparator.common.util.Constants.*;
import static com.comparator.common.util.Messages.errorSpecialCharactersInput;

/**
 * Represents a Version with the String value and a list of each sub version split by dots,
 * e.g. 7.3ab.1 Version object will have a list of Alphanumeric [7,3ab,1]
 */
@Getter
@Slf4j
@AllArgsConstructor
public class Version implements Comparable<Version> {

    private final String versionString;
    private final List<Alpha> subVersions;

    public Version(String input) {
        this.versionString = validate(input);
        this.subVersions = new ArrayList<>();

        List<String> subVersionStrings = Arrays.asList(versionString.split(VERSION_SPLIT_REGEX));
        subVersionStrings.forEach((s -> subVersions.add(new Alpha(s))));
    }

    /**
     * @implNote validates the input String. Version only allows alphanumerics and dots.
     */
    private String validate(String input) {
        String versionParsed = input.replaceAll(VERSION_REGEX, "");
        if (versionParsed.length() != input.length()) {
            log.error(errorSpecialCharactersInput);
            throw new VersionException(errorSpecialCharactersInput);
        }
        return versionParsed;
    }

    @Override
    public int compareTo(Version v) {
        if (versionString.equals(v.getVersionString())) {
            return COMPARABLE_EQUAL;
        } else {
            ListComparator<Alpha> subVersionsComparator = new ListComparator<>();
            return subVersionsComparator.compare(this.subVersions, v.getSubVersions());
        }
    }
}