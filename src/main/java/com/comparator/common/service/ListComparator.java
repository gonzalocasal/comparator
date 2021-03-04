package com.comparator.common.service;

import java.util.List;

import static com.comparator.version.util.Constants.*;

public class ListComparator<T extends Comparable<T>> {

    public int compare(List<T> listA, List<T> listB) {
        int result = COMPARABLE_EQUAL;
        int index = 0;
        int minSubVersionsCount = Math.min(listA.size(), listB.size());

        while (result == COMPARABLE_EQUAL && index < minSubVersionsCount) {
            result = listA.get(index).compareTo(listB.get(index));
            index ++;
        }
        if (result == COMPARABLE_EQUAL && (listA.size() != listB.size())) {
            result = (listA.size() < listB.size()) ? COMPARABLE_LOWER : COMPARABLE_HIGHER;
        }
        return result;
    }

}