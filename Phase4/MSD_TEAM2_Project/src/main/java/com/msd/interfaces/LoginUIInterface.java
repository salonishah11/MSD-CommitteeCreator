package com.msd.interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface LoginUIInterface.
 */
public interface LoginUIInterface {

	/**
	 * Valid credentials.
	 *
	 * @return true, if successful
	 */
	// Validate user credentials
	boolean ValidCredentials();
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	// gets user name
	String getUsername();
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	// gets the password of the user
	String getPassword();
	
}
