package main.interfaces;


// import java.sql.ResultSet;
import java.util.List;

import main.queryengine.Author;
import main.userinterface.SearchParameters;

import java.util.ArrayList;

// Interface for query engine component

public interface QueryEngineInterface {
	
	//method to search based on given parameters
	public ArrayList<Author> Search(SearchParameters searchParams);
}
