package com.comparator.common.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.comparator.common.util.Constants.*;

public class ListComparatorTests {

    @Test
    void CompareSameListsTests() {
        ListComparator<Integer> comparator = new ListComparator<>();

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        listA.add(1);
        listA.add(2);
        listA.add(3);

        listB.add(1);
        listB.add(2);
        listB.add(3);

        Assertions.assertEquals(COMPARABLE_EQUAL, comparator.compare(listA, listB));
    }


    @Test
    void CompareDiffLengthListsTests() {
        ListComparator<Integer> comparator = new ListComparator<>();

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        listA.add(1);

        listB.add(1);
        listB.add(2);

        Assertions.assertEquals(COMPARABLE_LOWER, comparator.compare(listA, listB));
    }


    @Test
    void CompareDiffListsTests() {
        ListComparator<Integer> comparator = new ListComparator<>();

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        listA.add(9);

        listB.add(1);

        Assertions.assertEquals(COMPARABLE_HIGHER, comparator.compare(listA, listB));
    }

}
