package com.comparator.common.model;

import com.comparator.common.service.ListComparator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.comparator.version.util.Constants.ALPHANUMERIC_REGEX;
import static java.lang.Character.getType;

@Getter
public class Alpha implements Comparable<Alpha> {

    private final String alphanumeric;
    private final List<UniformAlpha> uniformAlphas;

    public Alpha(String alphaNumeric) {
        this.alphanumeric = alphaNumeric.replaceAll(ALPHANUMERIC_REGEX, "");
        this.uniformAlphas = new ArrayList<>();
        buildUniformAlphas(alphaNumeric);
    }

    private void buildUniformAlphas(String alphanumeric) {
        if (!alphanumeric.isEmpty()) {
            int index = 0;
            int type = getType(alphanumeric.charAt(index));
            while (index < alphanumeric.length() && type == getType(alphanumeric.charAt(index))) {
                index ++;
            }
            String uniformAlpha = alphanumeric.substring(0, index);
            uniformAlphas.add(new UniformAlpha(uniformAlpha));
            buildUniformAlphas(alphanumeric.replace(uniformAlpha, ""));
        }
    }

    @Override
    public int compareTo(Alpha s) {
        if (alphanumeric.equals(s.getAlphanumeric())) {
            return 0;
        } else {
            ListComparator<UniformAlpha> subVersionsTypesComparator = new ListComparator<>();
            return subVersionsTypesComparator.compare(this.uniformAlphas, s.getUniformAlphas());
        }
    }
}

