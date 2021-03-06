package com.comparator.version.service;

import com.comparator.version.model.Conclusion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VersionComparatorTests {

	private final VersionComparator versionComparator = new VersionComparator();

	@Test
	void alphaNumericTest() {
		Conclusion conclusion = versionComparator.compare("11.21abc3.3", "11.21abc2.3");
		Assertions.assertEquals(Conclusion.AFTER, conclusion);

		conclusion = versionComparator.compare("11.21aba3.3", "11.21abc2.3");
		Assertions.assertEquals(Conclusion.BEFORE, conclusion);

		conclusion = versionComparator.compare("11.21aba3.3", "11.21z.3");
		Assertions.assertEquals(Conclusion.BEFORE, conclusion);

		conclusion = versionComparator.compare("11", "11.21z.3");
		Assertions.assertEquals(Conclusion.BEFORE, conclusion);

		conclusion = versionComparator.compare("1a", "1");
		Assertions.assertEquals(Conclusion.AFTER, conclusion);
	}

	@Test
	void sameNumericTest() {
		Conclusion conclusion = versionComparator.compare("1.0.0.1", "1.0.0.1");
		Assertions.assertEquals(Conclusion.EQUAL, conclusion);
	}

	@Test
	void diffNumericTest() {
		Conclusion conclusion = versionComparator.compare("18.2", "5.7");
		Assertions.assertEquals(Conclusion.AFTER, conclusion);

		conclusion = versionComparator.compare("23.0.2", "22.0.0");
		Assertions.assertEquals(Conclusion.AFTER, conclusion);
	}

	@Test
	void sameAlphaTest() {
		Conclusion conclusion = versionComparator.compare("ter", "ter");
		Assertions.assertEquals(Conclusion.EQUAL, conclusion);
	}

}
