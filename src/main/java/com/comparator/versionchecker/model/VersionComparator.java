package com.comparator.versionchecker.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.comparator.versionchecker.model.Conclusion.*;

@Component
@AllArgsConstructor
public class VersionComparator implements StringComparator {

    private final SubVersionComparator subVersionComparator;

    @Override
    public Conclusion compare(final String inputA, final String inputB) {

        String[] versionArrayA = inputA.split("\\.");
        String[] versionArrayB = inputB.split("\\.");

        Conclusion conclusion = EQUAL;
        int subVersionCount = Math.min(versionArrayA.length, versionArrayB.length);
        int index = 0;
        while (EQUAL.equals(conclusion) && index < subVersionCount) {
            conclusion = subVersionComparator.compare(versionArrayA[index], versionArrayB[index]);
            index ++;
        }
        if (EQUAL.equals(conclusion) && (versionArrayA.length != versionArrayB.length)) {
            conclusion = (inputA.length() < inputB.length()) ? BEFORE : AFTER;
        }
        return conclusion;
    }
}
