package com.comparator.versionchecker.rest;

import com.comparator.versionchecker.model.Conclusion;
import com.comparator.versionchecker.service.InputSanitizer;
import com.comparator.versionchecker.model.VersionComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("versions/compare")

public class VersionsController {

  private final InputSanitizer inputSanitizer;
  private final VersionComparator versionComparator;

  @Value("${template.versions.compare.response}")
  private String responseTemplate;

  @Autowired
  public VersionsController(InputSanitizer inputSanitizer, VersionComparator versionComparator) {
    this.inputSanitizer = inputSanitizer;
    this.versionComparator = versionComparator;
  }

  @GetMapping("/{inputA}/with/{inputB}")
  public String compare(@PathVariable String inputA, @PathVariable String inputB) {
    log.info("Checking the versions {} and {}", inputA, inputB);
    Conclusion conclusion = versionComparator.compare(inputSanitizer.sanitize(inputA), inputSanitizer.sanitize(inputB));
    String result = String.format(responseTemplate, inputA, conclusion, inputB);
    log.info("Result: {}", result);
    return result;
  }
}