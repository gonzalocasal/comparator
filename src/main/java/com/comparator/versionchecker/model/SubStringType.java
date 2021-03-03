package com.comparator.versionchecker.model;

public class SubStringType implements Comparable<SubStringType> {

    private final boolean isDigit;
    private final String subString;

    public SubStringType(String subStr, boolean isDigit) {
        this.subString = subStr;
        this.isDigit = isDigit;
    }

    public boolean isDigit() {
        return isDigit;
    }

    public String getSubString() {
        return subString;
    }

    @Override
    public int compareTo(SubStringType o) {
        if (this.isDigit && o.isDigit()) {
            return Integer.compare(Integer.parseInt(this.getSubString()), Integer.parseInt(o.getSubString()));
        }
        if (!this.isDigit && o.isDigit()) {
            return 1;
        }
        if (this.isDigit && !o.isDigit()) {
            return -1;
        }
        if (!this.isDigit && !o.isDigit()) {
            return this.subString.compareTo(o.getSubString());
        }

        return 0;
    }
}

