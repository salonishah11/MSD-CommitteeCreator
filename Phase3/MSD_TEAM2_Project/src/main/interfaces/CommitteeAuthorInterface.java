package main.interfaces;

import java.util.List;

import main.queryengine.Author;

public interface CommitteeAuthorInterface {
	
	// add author to the committee
	public boolean addCommitteeMembers(List<Author> authors);
	
	// remove author from committee
	public boolean removeMembers(List<Author> authors);
}

