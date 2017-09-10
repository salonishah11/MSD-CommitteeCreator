package main.interfaces;

import main.userinterface.User;

public interface SystemUserInterface {
	
	// method to check for login credentials of user
	public User loginQuery(String username ,String password);

	// method to register the user if not already present
	public boolean addUserToDatabase(User currentUser);
	
	// method to update the profile of user
	public boolean updateUserProfile(User currentUser, User mainUser);
}
