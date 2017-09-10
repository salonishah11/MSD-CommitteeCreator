package main.interfaces;

public interface ValidateUserProfileInterface {

	// validate users password
	String validatePassword(String password);
	//validates users phone number
	boolean validatePhone(String phone);
	//validates users email
	boolean validateEmail(String email);
	//validate users last name
	boolean validateLastName(String lname);
	//validates users first name
	boolean validateFirstName(String fname);
}
