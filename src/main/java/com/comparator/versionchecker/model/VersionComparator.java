package com.comparator.versionchecker.model;

import org.springframework.stereotype.Component;

import static com.comparator.versionchecker.model.Conclusion.*;

@Component
public class VersionComparator {

    public Conclusion compare(final String inputA, final String inputB) {

        Version versionA = new Version(inputA);
        Version versionB = new Version(inputB);

        int i = versionA.compareTo(versionB);

        Conclusion conclusion;
        if (i == 0) {
            conclusion = EQUAL;
        } else {
            conclusion = (i < 0) ? BEFORE : AFTER;
        }

        return conclusion;
    }
}
