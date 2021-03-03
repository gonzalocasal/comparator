package com.comparator.versionchecker.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.comparator.versionchecker.model.Conclusion.*;

@Component
public class SubVersionComparator implements StringComparator {

    @Override
    public Conclusion compare(String inputA, String inputB) {
        Conclusion conclusion = EQUAL;

        if (!inputA.equals(inputB)) {
            List<SubStringType> subStringByTypeA = getSubStringByType(inputA);
            List<SubStringType> subStringByTypeB = getSubStringByType(inputB);

            int index = 0;
            int count = Math.min(subStringByTypeA.size(), subStringByTypeB.size());

            while (EQUAL.equals(conclusion) && index < count) {
                int result = subStringByTypeA.get(index).compareTo(subStringByTypeB.get(index));
                if (result != 0) {
                    conclusion = (result < 0) ? BEFORE : AFTER;
                }
                index ++;
            }
            if (EQUAL.equals(conclusion) && (subStringByTypeA.size() != subStringByTypeB.size())) {
                conclusion = (subStringByTypeA.size() < subStringByTypeB.size()) ? BEFORE : AFTER;
            }

        }
        return conclusion;
    }

    private List<SubStringType> getSubStringByType(String input) {
        List<SubStringType> subStringTypes = new ArrayList<>();
        char[] charsA = input.toCharArray();
        StringBuilder subStr = new StringBuilder();
        int type = Character.getType(charsA[0]);
        for (char c : charsA) {
            if (Character.getType(c) == type) {
                subStr.append(c);
            } else {
                subStringTypes.add(new SubStringType(subStr.toString(), Character.isDigit(subStr.charAt(0))));
                subStr = new StringBuilder();
                subStr.append(c);
                type = Character.getType(c);
            }
        }
        subStringTypes.add(new SubStringType(subStr.toString(), Character.isDigit(subStr.charAt(0))));
        return subStringTypes;
    }
}
