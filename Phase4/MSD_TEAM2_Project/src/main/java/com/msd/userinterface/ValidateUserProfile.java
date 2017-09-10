package com.msd.userinterface;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.msd.interfaces.ValidateUserProfileInterface;

/**
 * The Class ValidateUserProfile.
 */
public class ValidateUserProfile implements ValidateUserProfileInterface{
	
	/** The firstname. */
	static String firstname;
	
	/** The lastname. */
	static String lastname;
	
	/** The email. */
	static String email;
	
	/** The password. */
	static String password;
	
	/** The confirmpass. */
	static String confirmpass;
	
	/** The designation. */
	static String designation;

	/* (non-Javadoc)
	 * @see com.msd.interfaces.ValidateUserProfileInterface#validateFirstName(java.lang.String)
	 */
	// method to validate firstname: check whether it contains only characters
	public boolean validateFirstName(String fname){
		for (char c : fname.toCharArray()) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }
		firstname = fname;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.msd.interfaces.ValidateUserProfileInterface#validateLastName(java.lang.String)
	 */
	//method to validate lastname: check whether it contains only characters
	public boolean validateLastName(String lname){
		for (char c : lname.toCharArray()) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }
		lastname = lname;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.msd.interfaces.ValidateUserProfileInterface#validateEmail(java.lang.String)
	 */
	// method to validate email address entered by the user
	public boolean validateEmail(String email){
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher(email);
         return m.matches();
	}
	
	// methd to validate the password entered and check whether 
	/* (non-Javadoc)
	 * @see com.msd.interfaces.ValidateUserProfileInterface#validatePassword(java.lang.String)
	 */
	// Validate the password field
	public String validatePassword(String pass1){
		Pattern hasUppercase = Pattern.compile("[A-Z]");
		Pattern hasLowercase = Pattern.compile("[a-z]");
		Pattern hasNumber = Pattern.compile("\\d");
		Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");

		if (pass1 == null) {
			return "Password is null";
		}

		if (pass1.length() < 8) {
			return "Password is too short, needs to have 8 characters.";
		}

		if (!hasUppercase.matcher(pass1).find()) {
			return "Password needs an upper case.";
		}

		if (!hasLowercase.matcher(pass1).find()) {
			return "Password needs a lowercase.";
		}

		if (!hasNumber.matcher(pass1).find()) {
			return "Password needs a number.";
		}

		if (!hasSpecialChar.matcher(pass1).find()) {
			return "Password needs a special character.";
		}

		return "Success";
	}
	
	/* (non-Javadoc)
	 * @see com.msd.interfaces.ValidateUserProfileInterface#validatePhone(java.lang.String)
	 */
	// method to validate phone number
	public boolean validatePhone(String number){
		String regex = "[0-9]+"; 
		return number.matches(regex);		
	}
}
