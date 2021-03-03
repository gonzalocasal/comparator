package com.comparator.versionchecker.model;

import com.comparator.versionchecker.service.ListComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.comparator.versionchecker.util.Constants.VERSION_SPLIT_REGEX;

@Getter
@AllArgsConstructor
public class Version implements Comparable<Version> {

    private final String versionString;
    private final List<SubVersion> subVersions;

    public Version(String versionString) {
        this.versionString = versionString;
        this.subVersions = new ArrayList<>();
        List<String> subVersionStrings = Arrays.asList(versionString.split(VERSION_SPLIT_REGEX));
        subVersionStrings.forEach((s -> subVersions.add(new SubVersion(s))));
    }

    @Override
    public int compareTo(Version v) {
        if (versionString.equals(v.getVersionString())) {
            return 0;
        } else {
            ListComparator<SubVersion> subVersionsComparator = new ListComparator<>();
            return subVersionsComparator.compare(this.subVersions, v.getSubVersions());
        }
    }
}

