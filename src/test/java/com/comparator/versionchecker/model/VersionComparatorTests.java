package com.comparator.versionchecker.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class VersionComparatorTests {

	private final VersionComparator versionComparator = new VersionComparator(new SubVersionComparator());

	@Test
	void alphaNumericTest() {
		Conclusion conclusion = versionComparator.compare("11.21abc3.3", "11.21abc2.3");
		Assert.assertEquals(Conclusion.BEFORE, conclusion);
	}


	@Test
	void sameNumericTest() {
		Conclusion conclusion = versionComparator.compare("1.0.0.1", "1.0.0.1");
		Assert.assertEquals(Conclusion.EQUAL, conclusion);
	}

	@Test
	void diffNumericTest() {
		Conclusion conclusion = versionComparator.compare("18.2", "5.7");
		Assert.assertEquals(Conclusion.AFTER, conclusion);

		conclusion = versionComparator.compare("23.0.2", "22.0.0");
		Assert.assertEquals(Conclusion.AFTER, conclusion);
	}

	@Test
	void diffAlphaTest() {
		Conclusion conclusion = versionComparator.compare("1a.bc", "1a.bcd");
		Assert.assertEquals(Conclusion.BEFORE, conclusion);
	}

	@Test
	void sameAlphaTest() {
		Conclusion conclusion = versionComparator.compare("ter", "ter");
		Assert.assertEquals(Conclusion.EQUAL, conclusion);
	}

}
