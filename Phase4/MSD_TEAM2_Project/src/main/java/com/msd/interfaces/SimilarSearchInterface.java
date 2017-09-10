package com.msd.interfaces;

import java.util.ArrayList;

import com.msd.queryengine.model.Author;

/**
 * The Interface SimilarSearchInterface.
 */
public interface SimilarSearchInterface {

	/**
	 * Searches similar author based on input search type and for the given author
	 * @param searchType one of the supported search types
	 * @param authorName Author for whom similar search is to be run.
	 * @return List of authors matching the similar search
	 */
	public ArrayList<Author> similarSearch(String searchType, String authorName);
}
