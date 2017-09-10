package com.msd.interfaces;
import com.msd.userinterface.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UpdateProfileUIInterface.
 */
public interface UpdateProfileUIInterface {

	/**
	 * Validate fields.
	 *
	 * @return the string
	 */
	// validate details entered by user
	String ValidateFields();
	
	/**
	 * Gets the field data.
	 *
	 * @return the field data
	 */
	// get users ented by user in the page as a user object
	User getFieldData();
}
