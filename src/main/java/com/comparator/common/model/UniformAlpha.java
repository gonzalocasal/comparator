package com.comparator.common.model;

import lombok.Getter;

import static com.comparator.version.util.Constants.*;

@Getter
public class UniformAlpha implements Comparable<UniformAlpha> {

    private final String uniformAlpha;
    private final boolean isDigit;

    public UniformAlpha(String uniformAlpha) {
        this.uniformAlpha = uniformAlpha;
        this.isDigit = Character.isDigit(uniformAlpha.charAt(0));
    }

    @Override
    public int compareTo(UniformAlpha st) {
        if (this.isDigit && st.isDigit()) {
            return Integer.compare(Integer.parseInt(this.getUniformAlpha()), Integer.parseInt(st.getUniformAlpha()));
        }
        if (!this.isDigit && st.isDigit()) {
            return COMPARABLE_HIGHER;
        }
        if (this.isDigit && !st.isDigit()) {
            return COMPARABLE_LOWER;
        }
        if (!this.isDigit && !st.isDigit()) {
            return this.uniformAlpha.compareTo(st.getUniformAlpha());
        }

        return COMPARABLE_EQUAL;
    }
}