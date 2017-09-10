package com.msd.interfaces;

import java.util.List;

import com.msd.queryengine.model.Author;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommitteeAuthorInterface.
 */
public interface CommitteeAuthorInterface {
	
	/**
	 * Adds the committee members.
	 *
	 * @param authors the authors
	 * @return true, if successful
	 */
	// add author to the committee
	public boolean addCommitteeMembers(List<Author> authors);
	
	/**
	 * Removes the members.
	 *
	 * @param authors the authors
	 * @return true, if successful
	 */
	// remove author from committee
	public boolean removeMembers(List<Author> authors);
}

