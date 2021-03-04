package com.comparator.version.rest.dto;

import com.comparator.version.model.Conclusion;

import static com.comparator.version.util.Messages.messageCompareVersions;


public class VersionCompareResponse {

    public String getMessage(String inputA, Conclusion conclusion, String inputB) {
        return String.format(messageCompareVersions, inputA, conclusion, inputB);
    }
}