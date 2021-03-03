package com.comparator.common.model;

public class UniformAlpha implements Comparable<UniformAlpha> {

    private final String uniformAlpha;
    private final boolean isDigit;

    public UniformAlpha(String homogeneousAlphaNumeric) {
        this.uniformAlpha = homogeneousAlphaNumeric;
        this.isDigit = Character.isDigit(homogeneousAlphaNumeric.charAt(0));
    }

    public boolean isDigit() {
        return isDigit;
    }

    public String getSubString() {
        return uniformAlpha;
    }

    @Override
    public int compareTo(UniformAlpha st) {
        if (this.isDigit && st.isDigit()) {
            return Integer.compare(Integer.parseInt(this.getSubString()), Integer.parseInt(st.getSubString()));
        }
        if (!this.isDigit && st.isDigit()) {
            return 1;
        }
        if (this.isDigit && !st.isDigit()) {
            return -1;
        }
        if (!this.isDigit && !st.isDigit()) {
            return this.uniformAlpha.compareTo(st.getSubString());
        }

        return 0;
    }
}

