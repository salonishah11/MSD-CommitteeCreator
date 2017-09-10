package com.msd.queryengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class PublicationListQueryTest {
	
	PublicationListQuery queryObj = new PublicationListQuery();

	@Test
	public void testPaperConferenceNameList() {
		assertEquals("There should be 4191 conferences", queryObj.getPaperConferenceNameList().size(), 4191);
	}

	
	@Test
	public void testPaperJournalNameList(){
		assertEquals("There should be 1527 journals", queryObj.getPaperJournalNameList().size(), 1527);
	}
	
	
	@Test
	public void testCommitteeConferenceNameList(){
		assertEquals("There should be 12 committee conferences", queryObj.getCommitteeConferenceNameList().size(), 12);
	}
}
