package com.comparator.versionchecker.model;

import com.comparator.versionchecker.service.ListComparator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SubVersion implements Comparable<SubVersion> {

    private final List<SubVersionType> subVersionTypes;
    private final String subVersionString;

    public SubVersion(String subVersionString) {
        this.subVersionString = subVersionString;
        this.subVersionTypes = new ArrayList<>();

        StringBuilder subStr = new StringBuilder();
        int type = Character.getType(subVersionString.charAt(0));

        for (char c : subVersionString.toCharArray()) {
            if (Character.getType(c) == type) {
                subStr.append(c);
            } else {
                subVersionTypes.add(new SubVersionType(subStr.toString(), Character.isDigit(subStr.charAt(0))));
                subStr = new StringBuilder();
                subStr.append(c);
                type = Character.getType(c);
            }
        }
        subVersionTypes.add(new SubVersionType(subStr.toString(), Character.isDigit(subStr.charAt(0))));
    }

    @Override
    public int compareTo(SubVersion s) {
        if (subVersionString.equals(s.getSubVersionString())) {
            return 0;
        } else {
            ListComparator<SubVersionType> subVersionsTypesComparator = new ListComparator<>();
            return subVersionsTypesComparator.compare(this.subVersionTypes, s.getSubVersionTypes());
        }
    }
}

