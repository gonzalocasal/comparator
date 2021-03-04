package com.comparator.version.model;

import com.comparator.common.model.Alpha;
import com.comparator.common.service.ListComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.comparator.version.util.Constants.VERSION_SPLIT_REGEX;

@Getter
@AllArgsConstructor
public class Version implements Comparable<Version> {

    private final Alpha versionAlpha;
    private final List<Alpha> subVersions;

    public Version(String versionString) {
        this.versionAlpha = new Alpha(versionString);
        this.subVersions = new ArrayList<>();
        List<String> subVersionStrings = Arrays.asList(versionAlpha.getAlphanumeric().split(VERSION_SPLIT_REGEX));
        subVersionStrings.forEach((s -> subVersions.add(new Alpha(s))));
    }

    @Override
    public int compareTo(Version v) {
        if (versionAlpha.getAlphanumeric().equals(v.getVersionAlpha().getAlphanumeric())) {
            return 0;
        } else {
            ListComparator<Alpha> subVersionsComparator = new ListComparator<>();
            return subVersionsComparator.compare(this.subVersions, v.getSubVersions());
        }
    }

}

