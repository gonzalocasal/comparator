package com.comparator.common.model;

import com.comparator.common.exception.UniformAlphaException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.comparator.common.util.Constants.*;
import static com.comparator.common.util.Messages.errorNoUniformAlphaInput;
import static java.lang.Character.getType;

/**
 * Represents a homogeneous type of Alphanumeric String
 */
@Getter
public class UniformAlpha implements Comparable<UniformAlpha> {

    private final String uniformAlpha;
    private final boolean isDigit;

    public UniformAlpha(String uniformAlpha) {
        this.uniformAlpha = validate(uniformAlpha.replaceAll(ALPHANUMERIC_REGEX, ""));
        this.isDigit = !uniformAlpha.isEmpty() && Character.isDigit(uniformAlpha.charAt(0));
    }

    private String validate (String uniformAlpha) {
        if (!uniformAlpha.isEmpty()) {
            int type = getType(uniformAlpha.charAt(0));
            for (char c : uniformAlpha.toCharArray()) {
                if (getType(c) != type) {
                    throw new UniformAlphaException(errorNoUniformAlphaInput);
                }
            }
        }
        return uniformAlpha;
    }

    /**
     * @return a List of homogeneous alphanumeric type SubString,
     * e.g. 17bc8 as input String, will return the list [17,bc,8]
     */
    public static List<UniformAlpha> split(String input) {
        List<UniformAlpha> uniformAlphas = new ArrayList<>();
        buildList(uniformAlphas, Alpha.parse(input));
        return uniformAlphas;
    }

    private static void buildList(List<UniformAlpha> uniformAlphas, String alphanumeric) {
        if (!alphanumeric.isEmpty()) {
            int index = 0;
            int type = getType(alphanumeric.charAt(index));
            while (index < alphanumeric.length() && type == getType(alphanumeric.charAt(index))) {
                index ++;
            }
            String uniformAlpha = alphanumeric.substring(0, index);
            uniformAlphas.add(new UniformAlpha(uniformAlpha, Character.isDigit(uniformAlpha.charAt(0))));

            buildList(uniformAlphas, alphanumeric.replace(uniformAlpha, ""));
        }
    }

    private UniformAlpha(String uniformAlpha, boolean isDigit) {
        this.uniformAlpha = uniformAlpha;
        this.isDigit = isDigit;
    }

    @Override
    public int compareTo(UniformAlpha ua) {
        if (this.isDigit && ua.isDigit()) {
            return Integer.compare(Integer.parseInt(this.getUniformAlpha()), Integer.parseInt(ua.getUniformAlpha()));
        }
        if (!this.isDigit && ua.isDigit()) {
            return COMPARABLE_HIGHER;
        }
        if (this.isDigit && !ua.isDigit()) {
            return COMPARABLE_LOWER;
        }
        if (!this.isDigit && !ua.isDigit()) {
            return this.uniformAlpha.compareTo(ua.getUniformAlpha());
        }

        return COMPARABLE_EQUAL;
    }
}