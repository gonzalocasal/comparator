package com.comparator.common.model;

import com.comparator.common.exception.UniformAlphaException;
import com.comparator.common.util.Messages;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.comparator.common.util.Constants.*;
import static java.lang.Character.getType;

@Getter
public class UniformAlpha implements Comparable<UniformAlpha> {

    private final String uniformAlpha;
    private final boolean isDigit;

    public UniformAlpha(String uniformAlpha) {
        this.uniformAlpha = validate(uniformAlpha);
        this.isDigit = !uniformAlpha.isEmpty() && Character.isDigit(uniformAlpha.charAt(0));
    }

    private String validate (String uniformAlpha) {
        if (!uniformAlpha.isEmpty()) {
        int type = getType(uniformAlpha.charAt(0));
            for (char c : uniformAlpha.toCharArray()) {
                if (getType(c) != type) {
                    throw new UniformAlphaException(Messages.errorNoUniformAlphaInput);
                }
            }
        }
        return uniformAlpha;
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

    public static List<UniformAlpha> parse(String alphanumeric) {
        List<UniformAlpha> uniformAlphas = new ArrayList<>();
        build(uniformAlphas, alphanumeric.replaceAll(ALPHANUMERIC_REGEX, ""));
        return uniformAlphas;
    }

    private static void build(List<UniformAlpha> uniformAlphas, String alphanumeric) {
        if (!alphanumeric.isEmpty()) {
            int index = 0;
            int type = getType(alphanumeric.charAt(index));
            while (index < alphanumeric.length() && type == getType(alphanumeric.charAt(index))) {
                index ++;
            }
            String uniformAlpha = alphanumeric.substring(0, index);
            uniformAlphas.add(new UniformAlpha(uniformAlpha));
            build(uniformAlphas, alphanumeric.replace(uniformAlpha, ""));
        }
    }

}