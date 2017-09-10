import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

// Interface for query engine component

public interface QueryEngineInterface {
	// field for Query
	public static String query="";
	
	// field for list of authors where each author is represented by its own representation
	// as a Author Object
	public List<Object> listOfAuthors = new ArrayList<Object>();
		
	// method to generate query from the given fields
	// Object can be of JSON or any other type that will be created
	// from the fields entered by the user on the search page
	public String createQuery(Object searchFields);
	
	// method to execute the query
	public ResultSet executeQuery(String query);
	
	// convert the ResultSet representation to user interface compatible object
	public List<Object> generateAuthorList(ResultSet rs);
	
	// establish connection to the database driver with the database url
	public boolean connectToDatabase(String url);
}
