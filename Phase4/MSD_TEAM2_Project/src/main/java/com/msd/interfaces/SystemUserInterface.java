package com.msd.interfaces;

import com.msd.userinterface.User;

/**
 * The Interface SystemUserInterface.
 */
public interface SystemUserInterface {
	
	/**
	 * Login query.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	// method to check for login credentials of user
	public User loginQuery(String username ,String password);

	/**
	 * Adds the user to database.
	 *
	 * @param currentUser the current user
	 * @return true, if successful
	 */
	// method to register the user if not already present
	public boolean addUserToDatabase(User currentUser);
	
	/**
	 * Update user profile.
	 *
	 * @param currentUser the current user
	 * @param mainUser the main user
	 * @return true, if successful
	 */
	// method to update the profile of user
	public boolean updateUserProfile(User currentUser, User mainUser);
}
