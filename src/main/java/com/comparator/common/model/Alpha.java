package com.comparator.common.model;

import com.comparator.common.service.ListComparator;
import lombok.Getter;

import java.util.List;

import static com.comparator.common.util.Constants.ALPHANUMERIC_REGEX;
import static com.comparator.common.util.Constants.COMPARABLE_EQUAL;

/**
 * Represents an Alphanumeric with the String value and a list of homogeneous type SubString,
 * e.g. 17bc8 Alpha object will have the list [17,bc,8]
 */
@Getter
public class Alpha implements Comparable<Alpha> {

    private final String alphanumeric;
    private final List<UniformAlpha> uniformAlphas;

    public Alpha(String input) {
        this.alphanumeric = parse(input);
        this.uniformAlphas = UniformAlpha.split(alphanumeric);
    }

    public static String parse (String input) {
        return input.replaceAll(ALPHANUMERIC_REGEX, "");
    }

    @Override
    public int compareTo(Alpha s) {
        if (alphanumeric.equals(s.getAlphanumeric())) {
            return COMPARABLE_EQUAL;
        } else {
            ListComparator<UniformAlpha> subVersionsTypesComparator = new ListComparator<>();
            return subVersionsTypesComparator.compare(this.uniformAlphas, s.getUniformAlphas());
        }
    }
}