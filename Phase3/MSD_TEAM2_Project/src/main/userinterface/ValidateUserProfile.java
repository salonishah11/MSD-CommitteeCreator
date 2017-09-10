package main.userinterface;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import main.interfaces.ValidateUserProfileInterface;

// class containing methods to validate all the parameters entered by the user on the update profile or register UI
public class ValidateUserProfile implements ValidateUserProfileInterface{
	static String firstname;
	static String lastname;
	static String email;
	static String password;
	static String confirmpass;
	static String designation;

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
	
	// method to validate email address entered by the user
	public boolean validateEmail(String mail){
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(mail);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   if(result){
			   email = mail;
		   }
		   return result;
	}
	
	// methd to validate the password entered and check whether 
	// it is of minimum 8 characters in length and contains atleast one uppercase, lowercase, special character and a numeric digit
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
	
	// method to validate phone number entered by the user
	public boolean validatePhone(String number){
		String regex = "[0-9]+"; 
		return number.matches(regex);		
	}
}
