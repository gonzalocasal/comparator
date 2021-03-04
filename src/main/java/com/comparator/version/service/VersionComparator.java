package com.comparator.version.service;

import com.comparator.version.model.Conclusion;
import com.comparator.version.model.Version;
import org.springframework.stereotype.Service;

import static com.comparator.version.model.Conclusion.*;

@Service
public class VersionComparator {

    public Conclusion compare(final String inputA, final String inputB) {

        int result = new Version(inputA).compareTo(new Version(inputB));

        Conclusion conclusion;
        if (result == 0) {
            conclusion = EQUAL;
        } else {
            conclusion = (result < 0) ? BEFORE : AFTER;
        }

        return conclusion;
    }
}