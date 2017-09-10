package com.msd.userinterface;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.msd.queryengine.CommitteeQuery;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.CommitteeListUI;
import com.msd.userinterface.Main;
import com.msd.userinterface.SearchResults;
import com.msd.userinterface.SearchUI;
import com.msd.userinterface.User;

public class SearchResultsCommitteeListUITest {

	ArrayList<Author> authorsList = new ArrayList<Author>();
	
	User currentUser = new User(1, "Mike", "Wein", "team2",
			"Team2_MSD", "mike@gmail.com", "857123456", "Program Chair");
	CommitteeQuery comQuery = new CommitteeQuery();
	
	Author permanentAuthor = new Author("Priyanka", "conf/oopsla", "Network Security","mastersthesis",2015);
	
	// test for displaying records after search and adding author to committee list
	/*@Test
	public void testSearchResultsWithRecords(){	
		setDetails();
		
		Main.setCurrentUser(currentUser);
<<<<<<< HEAD:Phase4/MSD_TEAM2_Project/src/test/ui/testSearchResultsCommitteeListUI.java
=======
		CommitteeQuery comQuery = new CommitteeQuery();
>>>>>>> master:Phase4/MSD_TEAM2_Project/src/test/java/ui/testSearchResultsCommitteeListUI.java
		Main.setCurrentCommittee(comQuery.getUsersCommitteeList());
		
		int committeeListSize = Main.getCurrentCommittee().size();
		
		CommitteeListUI cmui = new CommitteeListUI();		
		int rowCountCommittee = 0;
		
		SearchResults searchResultsObj = new SearchResults(authorsList);
		searchResultsObj.getTable().setRowSelectionInterval(0, 0);
		searchResultsObj.getAddToListButton().doClick();
		committeeListSize++;		
		assertEquals("1 author should be added to Committee List", 
		(comQuery.getUsersCommitteeList()).size(), 
				committeeListSize);
		
		searchResultsObj.getTable().setRowSelectionInterval(0, 0);
		searchResultsObj.getAddToListButton().doClick();		
		assertEquals("Author already exists so can't be added", 
				comQuery.getUsersCommitteeList().size(), 
				committeeListSize);
		
		// after refresh CommitteeLitUI table will be updated with new entry
		cmui.refreshButton.doClick();
		assertEquals("Committee will have 1 author added", cmui.table.getRowCount(), rowCountCommittee + 1);
	}*/
	
	
	// test for zero records for given search parameters
	@Test
	public void testSearchResultsWithNoRecords(){
		SearchResults searchResultsObj = new SearchResults(new ArrayList<Author>());
		
		assertEquals("No search results", searchResultsObj.getTable(), null);
	}
	
	
	// test to remove an author from committee list without selecting any author
	/*@Test
	public void testRemoveAuthorWithoutSelection(){
		testSearchResultsWithRecords();
		
		CommitteeListUI cmui = new CommitteeListUI();
		
		int rowCount = cmui.table.getRowCount();
		cmui.removeButton.doClick();
		
		assertNotEquals("No author should be removed", cmui.table.getRowCount(), rowCount-1);
		
		testRemoveAuthorFromCommitteeListSuccess();
	}*/
	
	
	// test to remove an author from committee list
	/*@Test
	public void testRemoveAuthorFromCommitteeListSuccess(){
		Main.setCurrentUser(currentUser);
		
		CommitteeListUI cmui = new CommitteeListUI();
		
		int rowCount = cmui.table.getRowCount();
		cmui.table.setRowSelectionInterval(0, 0);
		cmui.removeButton.doClick();
		
		assertEquals(cmui.table.getRowCount(), rowCount-1);
		
	}*/
	
	
	// helper function to set list of authors
	private void setDetails(){
		Author a = new Author();
		a.setKey("conf/infrastructures/2011");
		a.setName("Robert");
		a.setTitle("Analysis of Algorithms");
		a.setType("inproceedings");
		a.setYear(2011);
		authorsList.add(a);
	}
}
