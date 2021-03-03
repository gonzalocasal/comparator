package com.comparator.versionchecker.model;

import org.springframework.stereotype.Component;
import static com.comparator.versionchecker.model.Conclusion.*;

@Component
public class VersionComparator implements StringComparator {

    @Override
    public Conclusion compare(final String inputA, final String inputB) {

        int index = 0;

        String[] inputArray = inputA.split(".");
        String[] inputArrayB = inputA.split(".");

        int charCount = calculateCharCount(inputArray, inputArrayB);

        Conclusion conclusion = EQUAL;
        while (EQUAL.equals(conclusion) && index < charCount) {
            conclusion = compareChar(inputArray, inputArrayB, index);
            index ++;
        }
        if (EQUAL.equals(conclusion) && areDifferentLength(inputA, inputB)) {
            conclusion = (inputA.length() < inputB.length()) ? BEFORE : AFTER;
        }
        return conclusion;
    }


    private boolean areDifferentLength(String inputA, String inputB) {
        return inputA.length() != inputB.length();
    }

    private Conclusion compareChar(String[] inputA, String[] inputB, int index) {
        if (inputA[index].equals(inputB[index])) {
            return EQUAL;
        } else {
            return (inputA[index].compareTo(inputB[index]) < 0) ? BEFORE : AFTER;
        }
    }

    private int calculateCharCount(String[] inputA, String[] inputB) {
        return Math.min(inputA.length, inputB.length);
    }

}
