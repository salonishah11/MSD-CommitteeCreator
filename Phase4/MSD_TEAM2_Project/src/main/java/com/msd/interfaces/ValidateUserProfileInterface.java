package com.msd.interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface ValidateUserProfileInterface.
 */
public interface ValidateUserProfileInterface {

	/**
	 * Validate password.
	 *
	 * @param password the password
	 * @return the string
	 */
	// validate users password
	String validatePassword(String password);
	
	/**
	 * Validate phone.
	 *
	 * @param phone the phone
	 * @return true, if successful
	 */
	//validates users phone number
	boolean validatePhone(String phone);
	
	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	//validates users email
	boolean validateEmail(String email);
	
	/**
	 * Validate last name.
	 *
	 * @param lname the lname
	 * @return true, if successful
	 */
	//validate users last name
	boolean validateLastName(String lname);
	
	/**
	 * Validate first name.
	 *
	 * @param fname the fname
	 * @return true, if successful
	 */
	//validates users first name
	boolean validateFirstName(String fname);
}
