package com.comparator.common.service;

import java.util.List;

public class ListComparator<T extends Comparable<T>> {

    public int compare(List<T> listA, List<T> listB) {
        int result = 0;
        int index = 0;
        int minSubVersionsCount = Math.min(listA.size(), listB.size());

        while (result == 0 && index < minSubVersionsCount) {
            result = listA.get(index).compareTo(listB.get(index));
            index ++;
        }
        if (result == 0 && (listA.size() != listB.size())) {
            result = (listA.size() < listB.size()) ? -1 : 1;
        }
        return result;
    }

}




