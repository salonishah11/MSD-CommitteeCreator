import java.util.List;
import java.util.Map;
import java.util.HashMap;


public interface UserInterface {
   //final static variables/data structures
	//list of user and their credentials stored as a HashMap
	public static Map<String,String> userCredentials =new HashMap<String,String>();
	
	//data structure for storing user details
	public static Map<String,List<String>> userDetails = new HashMap<String, List<String>>();
	
	//module for login of a user
	public void login(String username, String password);
	
	//function for validating the credentials
	public boolean validateCredentials(String username, String password);
	
	//function for registering a user
	public void register(String firstName, String lastName, String userName,
			String password1, String password2, long phone,String email);
	
	//validate register details which includes validating username whether it already exists
	// and validating the password and confirm password if they match
	public boolean validateDetails();
	
	//function to update user's profile
	public void updateUserProfile();
}
