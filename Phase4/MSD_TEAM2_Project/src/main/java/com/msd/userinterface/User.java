package com.msd.userinterface;

/**
 * The Class User which represents the current user of the application.
 */

public class User {
	
	/** The person id. */
	private int personId;
	
	/** The first name. */
	private String firstName;
	
	/** The designation. */
	private String designation;
	
	/** The last name. */
	private String lastName;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;

	/**
	 * Instantiates a new user.
	 */
	public User(){
		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param personId the person id
	 * @param fName the f name
	 * @param lName the l name
	 * @param username the username
	 * @param password the password
	 * @param email the email
	 * @param phone the phone
	 * @param desg the desg
	 */
	public User(int personId, String fName, String lName, String username, String password, 
			String email, String phone, String desg) {
		this.personId = personId;
		this.firstName = fName;
		this.lastName = lName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.designation = desg;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName(){
		return this.firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName(){
		return this.lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone(){
		return this.phone;
	}

	/**
	 * Gets the desgination.
	 *
	 * @return the desgination
	 */
	public String getDesgination(){
		return this.designation;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword(){
		return this.password;
	}

	/**
	 * Sets the first name.
	 *
	 * @param fname the new first name
	 */
	public void setFirstName(String fname){
		this.firstName = fname;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lname the new last name
	 */
	public void setLastName(String lname){
		this.lastName = lname;
	}

	/**
	 * Sets the email.
	 *
	 * @param mail the new email
	 */
	public void setEmail(String mail){
		this.email=mail;
	}

	/**
	 * Sets the phone.
	 *
	 * @param ph the new phone
	 */
	public void setPhone(String ph){
		this.phone = ph;
	}

	/**
	 * Sets the designation.
	 *
	 * @param desig the new designation
	 */
	public void setDesignation(String desig){
		this.designation = desig;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password){
		this.password = password;
	}
}