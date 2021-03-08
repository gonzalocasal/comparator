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

    private final String value;
    private final boolean isDigit;

    public UniformAlpha(String input) {
        this.value = validate(input);
        this.isDigit = !input.isEmpty() && Character.isDigit(input.charAt(0));
    }

    /**
     * @implNote validates that the input is homogeneous type alphanumeric.
     */
    private String validate (String input) {
        String inputParsed = Alpha.parse(input);
        if (!inputParsed.isEmpty()) {
            int type = getType(inputParsed.charAt(0));
            for (char c : inputParsed.toCharArray()) {
                if (getType(c) != type) {
                    throw new UniformAlphaException(errorNoUniformAlphaInput);
                }
            }
        }
        return inputParsed;
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

    /**
     * @implNote collect and set recursively each substring group with the same type
     */
    private static void buildList(List<UniformAlpha> uniformAlphas, String alphanumeric) {
        if (!alphanumeric.isEmpty()) {
            int index = 0;
            int type = getType(alphanumeric.charAt(index));
            while (index < alphanumeric.length() && type == getType(alphanumeric.charAt(index))) {
                index ++;
            }
            String uniformAlpha = alphanumeric.substring(0, index);
            uniformAlphas.add(new UniformAlpha(uniformAlpha, Character.isDigit(uniformAlpha.charAt(0))));

            buildList(uniformAlphas, alphanumeric.replaceFirst(uniformAlpha, ""));
        }
    }

    /**
     * Private secure constructor to be used by the Split process
     */
    private UniformAlpha(String value, boolean isDigit) {
        this.value = value;
        this.isDigit = isDigit;
    }

    @Override
    public int compareTo(UniformAlpha ua) {
        if (this.isDigit && ua.isDigit()) {
            return Integer.compare(Integer.parseInt(this.getValue()), Integer.parseInt(ua.getValue()));
        }
        if (!this.isDigit && ua.isDigit()) {
            return COMPARABLE_HIGHER;
        }
        if (this.isDigit && !ua.isDigit()) {
            return COMPARABLE_LOWER;
        }
        if (!this.isDigit && !ua.isDigit()) {
            return this.value.compareTo(ua.getValue());
        }

        return COMPARABLE_EQUAL;
    }
}