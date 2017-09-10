package main.interfaces;

import main.userinterface.User;

public interface RegisterUIInterface {

	// Validates register details
	boolean ValidateFields();
	
	// get method user object containing user details entered
	User getFieldData();
}
