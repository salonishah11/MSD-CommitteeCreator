package com.msd.queryengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class ViewAuthorDetailsTest {
	
	ViewAuthorDetailsQuery queryObj = new ViewAuthorDetailsQuery();

	@Test
	public void testAuthorPublicationDetails() {
		assertEquals("Dave A. Thomas should have 39 publication"
				, queryObj.getAuthorPublicationDetails("Dave A. Thomas").size(), 39);
	}

	
	@Test
	public void testAuthorCommitteeDetails() {
		assertEquals("Dave A. Thomas should have been part of 6 committees"
				, queryObj.getAuthorCommitteeDetails("Dave A. Thomas").size(), 6);
	}
}
