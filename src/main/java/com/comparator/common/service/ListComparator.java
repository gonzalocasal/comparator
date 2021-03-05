package com.comparator.common.service;

import java.util.List;

import static com.comparator.common.util.Constants.*;

public class ListComparator<T extends Comparable<T>> {

    /**
     * @implNote Define which list is bigger comparing each elements until it find a difference.
     * If all compared the elements are equal, the list that have more elements is considered bigger.
     *
     * @return -1 if list A is lower than list B, 0 if are equal, 1 if is bigger.
     */
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