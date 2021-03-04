package com.comparator.version.model;

import com.comparator.common.model.Alpha;
import com.comparator.common.service.ListComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.comparator.version.util.Constants.VERSION_SPLIT_REGEX;

@Getter
@AllArgsConstructor
public class Version implements Comparable<Version> {

    private final String versionString;
    private final List<Alpha> subversions;

    public Version(String versionString) {
        this.versionString = versionString;
        this.subversions = new ArrayList<>();
        List<String> subVersionStrings = Arrays.asList(versionString.split(VERSION_SPLIT_REGEX));
        subVersionStrings.forEach((s -> subversions.add(new Alpha(s))));
    }

    @Override
    public int compareTo(Version v) {
        if (versionString.equals(v.getVersionString())) {
            return 0;
        } else {
            ListComparator<Alpha> subVersionsComparator = new ListComparator<>();
            return subVersionsComparator.compare(this.subversions, v.getSubversions());
        }
    }
}

