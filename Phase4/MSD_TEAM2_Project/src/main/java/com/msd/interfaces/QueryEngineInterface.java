package com.msd.interfaces;


// import java.sql.ResultSet;
import java.util.List;

import java.sql.PreparedStatement;

import com.msd.queryengine.model.Author;
import com.msd.userinterface.SearchParameters;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
// Interface for query engine component

/**
 * The Interface QueryEngineInterface.
 */
public interface QueryEngineInterface {
	
	/**
	 * Search.
	 *
	 * @param searchParams the search params
	 * @return the array list
	 */
	//method to search based on given parameters
	public ArrayList<Author> Search(SearchParameters searchParams);
	
	public PreparedStatement createQuery(SearchParameters searchParameters);
	
}
