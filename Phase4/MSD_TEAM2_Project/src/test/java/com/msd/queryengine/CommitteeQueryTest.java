package com.msd.queryengine;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.msd.queryengine.CommitteeQuery;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.Main;
import com.msd.userinterface.User;

public class CommitteeQueryTest {

	// initialize the currentUser and an array list of authors.
	User currentUser = new User(1, "Michelle", "Obama", "Michelle90",
			"Pass@123", "michelle@yahoo.com", "617-8581234", "Program Chair");
	List<Author> authors = new ArrayList<Author>();
	
	// permanentAuthor who is not removed from the table and is used to check the branch where the 
	// committee member already exists in the table
	Author permanentAuthor = new Author("Priyanka", "conf/oopsla", "Network Security","mastersthesis",2015);
	
	// method to initialize the list of authors for performing actions on
	public void setDetails(){
		Author a = new Author();
		a.setKey("conf/infrastructures/2011");
		a.setName("Robert");
		a.setTitle("Analysis of Algorithms");
		a.setType("inproceedings");
		a.setYear(2011);
		authors.add(a);
		authors.add(new Author("Wilson","journals/oopsla","Object oriented principles","phdthesis",2012));
		authors.add(permanentAuthor);
	}
	

	// test for checking adding selected committee members to the shortlist committee table
	@Test
	public void testAddCommitteeMembers() {
		setDetails();
		Main.setCurrentUser(currentUser);
		CommitteeQuery cq = new CommitteeQuery();
		boolean res = cq.addCommitteeMembers(authors);
		assertEquals("Adding member to the commitee", res, false);
		
	}
	
	// test for validating the query generated to insert the author into the committe table
	@Test
	public void testCreateQuery(){
		setDetails();
		Main.setCurrentUser(currentUser);
		CommitteeQuery cq = new CommitteeQuery();
		Author a = new Author();
		a.setKey("journals/ecoop");
		a.setName("Frank Tip");
		a.setTitle("Managing Software Development");
		a.setType("phdthesis");
		a.setYear(2017);
		boolean res = cq.createInsertQuery(a);
		assertEquals("Insert failed", res, true);
		List<Author> li = new ArrayList<Author>();
		li.add(a);
		cq.removeMembers(li);
	}


	// test for validating the removal of authors form the committee table is successful or not
	@Test
	public void testForRemoveMembers(){
		setDetails();
		Main.setCurrentUser(currentUser);
		CommitteeQuery cq = new CommitteeQuery();
		ArrayList<Author> auths = cq.getUsersCommitteeList();
		boolean flag=false;
		if(auths.size() == authors.size()){
			flag=true;
		}
		assertEquals("Getting committeMembers failed",flag, true);
		authors.remove(permanentAuthor);
		boolean bool = cq.removeMembers(authors);
		assertEquals("Removal of committee members",bool,true);
		
		// insert the permanent author again
		List<Author> insertAgain = new ArrayList<Author>();
		insertAgain.add(permanentAuthor);
		boolean res = cq.addCommitteeMembers(insertAgain);
		assertEquals("Adding member to the commitee should fail", res, false);
	}

}