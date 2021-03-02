package com.comparator.versionchecker.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class VersionComparatorTests {

	private final VersionComparator versionComparator = new VersionComparator();

	@Test
	void sameNumericTest() {
		Conclusion conclusion = versionComparator.compare("1001", "1001");
		Assert.assertEquals(Conclusion.EQUAL, conclusion);
	}

	@Test
	void diffNumericTest() {
		Conclusion conclusion = versionComparator.compare("20", "1001");
		Assert.assertEquals(Conclusion.AFTER, conclusion);
	}

	@Test
	void diffAlphaTest() {
		Conclusion conclusion = versionComparator.compare("abc", "abcd");
		Assert.assertEquals(Conclusion.BEFORE, conclusion);
	}

	@Test
	void sameAlphaTest() {
		Conclusion conclusion = versionComparator.compare("ter", "ter");
		Assert.assertEquals(Conclusion.EQUAL, conclusion);
	}

}
