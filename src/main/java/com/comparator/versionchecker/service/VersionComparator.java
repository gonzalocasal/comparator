package com.comparator.versionchecker.service;

import com.comparator.versionchecker.model.Conclusion;
import com.comparator.versionchecker.model.Version;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.comparator.versionchecker.model.Conclusion.*;

@Service
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
