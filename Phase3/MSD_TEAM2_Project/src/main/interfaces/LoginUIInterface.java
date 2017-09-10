package main.interfaces;

public interface LoginUIInterface {

	// Validate user credentials
	boolean ValidCredentials();
	// gets user name
	String getUsername();
	// gets the password of the user
	String getPassword();
	
}
