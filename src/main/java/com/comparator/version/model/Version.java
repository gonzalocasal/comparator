package com.comparator.version.model;

import com.comparator.version.exception.VersionException;
import com.comparator.common.model.Alpha;
import com.comparator.common.service.ListComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.comparator.version.util.Constants.*;
import static com.comparator.version.util.Messages.errorNoAlphanumericInput;
import static com.comparator.version.util.Messages.errorSpecialCharactersInput;


@Getter
@Slf4j
@AllArgsConstructor
public class Version implements Comparable<Version> {

    private final String versionString;
    private final List<Alpha> subVersions;

    public Version(String versionInput) {
        this.versionString = buildVersionString(versionInput);
        this.subVersions = new ArrayList<>();
        List<String> subVersionStrings = Arrays.asList(versionString.split(VERSION_SPLIT_REGEX));
        subVersionStrings.forEach((s -> subVersions.add(new Alpha(s))));
    }

    @Override
    public int compareTo(Version v) {
        if (versionString.equals(v.getVersionString())) {
            return 0;
        } else {
            ListComparator<Alpha> subVersionsComparator = new ListComparator<>();
            return subVersionsComparator.compare(this.subVersions, v.getSubVersions());
        }
    }

    private String buildVersionString(String versionInput) {

        if (versionInput.replaceAll(ALPHANUMERIC_REGEX, "").isEmpty()) {
            log.error(errorNoAlphanumericInput);
            throw new VersionException(errorNoAlphanumericInput);
        }

        if (versionInput.replaceAll(VERSION_REGEX, "").length() != versionInput.length()) {
            log.error(errorSpecialCharactersInput);
            throw new VersionException(errorSpecialCharactersInput);
        }

        return versionInput.replaceAll(VERSION_REGEX, "");
    }

}