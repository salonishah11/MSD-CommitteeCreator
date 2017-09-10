package main.interfaces;
import main.userinterface.User;

public interface UpdateProfileUIInterface {

	// validate details entered by user
	boolean ValidateFields();
	
	// get users ented by user in the page as a user object
	User getFieldData();
}
