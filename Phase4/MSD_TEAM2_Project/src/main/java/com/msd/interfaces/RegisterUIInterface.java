package com.msd.interfaces;

import com.msd.userinterface.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface RegisterUIInterface.
 */
public interface RegisterUIInterface {

	/**
	 * Validate fields.
	 *
	 * @return the string
	 */
	// Validates register details
	String ValidateFields();
	
	/**
	 * Gets the field data.
	 *
	 * @return the field data
	 */
	// get method user object containing user details entered
	User getFieldData();
}
