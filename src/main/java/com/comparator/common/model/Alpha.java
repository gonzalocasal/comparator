package com.comparator.common.model;

import com.comparator.common.service.ListComparator;
import lombok.Getter;

import java.util.List;

import static com.comparator.common.util.Constants.ALPHANUMERIC_REGEX;
import static com.comparator.common.util.Constants.COMPARABLE_EQUAL;

@Getter
public class Alpha implements Comparable<Alpha> {

    private final String alphanumeric;
    private final List<UniformAlpha> uniformAlphas;

    public Alpha(String alphaNumeric) {
        this.alphanumeric = alphaNumeric.replaceAll(ALPHANUMERIC_REGEX, "");
        this.uniformAlphas = UniformAlpha.parse(alphaNumeric);
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