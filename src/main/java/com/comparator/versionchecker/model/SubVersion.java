package com.comparator.versionchecker.model;

import com.comparator.versionchecker.service.ListComparator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getType;
import static java.lang.Character.isDigit;

@Getter
public class SubVersion implements Comparable<SubVersion> {

    private final String subVersionString;
    private final List<SubVersionType> subVersionTypes;

    public SubVersion(String subVersionString) {
        this.subVersionString = subVersionString;
        this.subVersionTypes = new ArrayList<>();

        StringBuilder subStr = new StringBuilder();
        char buildChar = subVersionString.charAt(0);
        int type = getType(buildChar);

        for (char c : subVersionString.toCharArray()) {
            if (getType(c) != type ) {
                subVersionTypes.add(new SubVersionType(subStr.toString()));
                type = getType(c);
                subStr = new StringBuilder();
            }
            subStr.append(c);
        }
        subVersionTypes.add(new SubVersionType(subStr.toString()));
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

