package com.comparator.version.rest;

import com.comparator.version.model.Conclusion;
import com.comparator.version.service.VersionComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("versions/compare")
public class VersionsController {

  private final VersionComparator versionComparator;

  @Value("${template.versions.compare.response}")
  private String responseTemplate;

  public VersionsController(VersionComparator versionComparator) {
    this.versionComparator = versionComparator;
  }

  @GetMapping("/{inputA}/with/{inputB}")
  public String compare(@PathVariable String inputA, @PathVariable String inputB) {
    log.info("Checking the versions {} and {}", inputA, inputB);
    Conclusion conclusion = versionComparator.compare(inputA, inputB);
    String result = String.format(responseTemplate, inputA, conclusion, inputB);
    log.info("Result: {}", result);
    return result;
  }
}