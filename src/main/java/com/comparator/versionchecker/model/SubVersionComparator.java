package com.comparator.versionchecker.model;

import org.springframework.stereotype.Component;

import static com.comparator.versionchecker.model.Conclusion.*;

@Component
public class SubVersionComparator implements StringComparator {

    @Override
    public Conclusion compare(String inputA, String inputB) {
        if (inputA.equals(inputB)) {
            return EQUAL;
        } else {
            return (Integer.parseInt(inputA) < Integer.parseInt(inputB)) ? BEFORE : AFTER;
        }
    }
}
