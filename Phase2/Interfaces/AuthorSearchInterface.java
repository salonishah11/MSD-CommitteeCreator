import java.util.List;
import java.util.ArrayList;
import java.io.File;

public interface AuthorSearchInterface {
	//authors list retrieved as search queries
	public static List<Object> listOfAuthors = new ArrayList<Object>();
	
	//search by paper and parameters
	public List<Object> searchByPaper(List<String> params);
	
	//search by author and parameters
	public List<Object> searchByAuthor(List<String> params);
	
	//search for similar profiles with input given as list of parameters/attributes
	// of a given profile
	public List<Object>searchSimilarProfiles(List<String> params);
	
}
