package com.comparator.versionchecker.model;

import org.springframework.stereotype.Component;
import static com.comparator.versionchecker.model.Conclusion.*;

@Component
public class VersionComparator implements StringComparator {

    @Override
    public Conclusion compare(final String inputA, final String inputB) {
        int charCount = calculateCharCount(inputA, inputB);
        int index = 0;
        Conclusion conclusion = EQUAL;
        while (EQUAL.equals(conclusion) && index < charCount) {
            conclusion = compareChar(inputA, inputB, index);
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

    private Conclusion compareChar(String inputA, String inputB, int index) {
        if (inputA.charAt(index) == inputB.charAt(index)) {
            return EQUAL;
        } else {
            return (inputA.charAt(index) < inputB.charAt(index)) ? BEFORE : AFTER;
        }
    }

    private int calculateCharCount(String inputA, String inputB) {
        return Math.min(inputA.length(), inputB.length());
    }

}
