package com.msd.queryengine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AuthorBackgroundDetailsQueryTest {
	
	AuthorBackgroundDetailsQuery queryObj = new AuthorBackgroundDetailsQuery();

	@Test
	public void testAffiliatedUniOfAuthor() {
		assertEquals("Author should be affiliated with Northeastern"
				, queryObj.getAffiliatedUniversityOfAuthor("Frank Tip"), "Northeastern University");
	}

	
	@Test
	public void testRegionOfAuthor() {
		assertEquals("Author should be affiliated with Europe"
				, queryObj.getRegionOfAuthor("Thomas R. Gross"), "europe");
	}
	
	
	@Test
	public void testResearchAreaOfAuthor() {
		List<String> researchAreaList = queryObj.getResearchAreaOfAuthor("Frank Tip");
		assertTrue("Author should have sot as Research Area", researchAreaList.contains("soft"));
		assertTrue("Author should have plan as Research Area", researchAreaList.contains("plan"));
	}
}
