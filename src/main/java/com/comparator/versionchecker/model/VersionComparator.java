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

        String[] inputArray = inputA.split("\\.");
        String[] inputArrayB = inputB.split("\\.");

        Conclusion conclusion = EQUAL;
        int subVersionCount = getSubVersionCount(inputArray, inputArrayB);
        int index = 0;
        while (EQUAL.equals(conclusion) && index < subVersionCount) {
            conclusion = subVersionComparator.compare(inputArray[index], inputArrayB[index]);
            index ++;
        }
        if (EQUAL.equals(conclusion) && areDifferentLength(inputArray, inputArrayB)) {
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
