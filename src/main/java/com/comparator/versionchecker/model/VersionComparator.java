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
        int subVersionCount = getSubVersionCount(versionArrayA, versionArrayB);
        int index = 0;
        while (EQUAL.equals(conclusion) && index < subVersionCount) {
            conclusion = subVersionComparator.compare(versionArrayA[index], versionArrayB[index]);
            index ++;
        }
        if (EQUAL.equals(conclusion) && areDifferentLength(versionArrayA, versionArrayB)) {
            conclusion = (inputA.length() < inputB.length()) ? BEFORE : AFTER;
        }
        return conclusion;
    }

    private boolean areDifferentLength(String[] inputA, String[] inputB) {
        return inputA.length != inputB.length;
    }

    private int getSubVersionCount(String[] inputA, String[] inputB) {
        return Math.min(inputA.length, inputB.length);
    }

}
