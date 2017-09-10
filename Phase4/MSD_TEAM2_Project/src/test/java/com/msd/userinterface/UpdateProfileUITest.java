package com.msd.userinterface;

import static org.junit.Assert.*;

import org.junit.Test;

import com.msd.userinterface.Main;
import com.msd.userinterface.UpdateProfileUI;
import com.msd.userinterface.User;
import com.msd.userinterface.ValidateUserProfile;
import com.msd.interfaces.*;

public class UpdateProfileUITest {

	String responseFirstNameMsg = "";
	String responseLastNameMsg = "";
	String responseEmailMsg = "";
	String responsePhoneMsg = "";
	String responseDesignationMsg = "";
	String responseUserNameMsg = "";
	String responsePasswordMsg = "";
	
	// Test for all fields checked match as entered in respective fields
	@Test
	public void testUpdateProfileSuccess(){
		responseFirstNameMsg = "Vineet";
		responseLastNameMsg = "Trivedi";
		responseEmailMsg = "team10@gmail.com";
		responsePhoneMsg = "1234567899";
		responseDesignationMsg = "Program Chair";
		responseUserNameMsg = "team10";
		responsePasswordMsg = "Vineet_1994";
			
		User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
		Main.setCurrentUser(currentUser1);
		UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","team10@gmail.com","1234567899","Progam Chair","team10","Vineet_1994");
		assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
		assertEquals(responseLastNameMsg, upui.lastNameField.getText());
		assertEquals(responseEmailMsg, upui.emailField.getText());
		assertEquals(responsePhoneMsg, upui.phoneField.getText());
		assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
		assertEquals(responseUserNameMsg, upui.usernameField.getText());
		assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
		
	} 
			 
	// Test for password not matching the requirements
	@Test
	public void testUpdateProfileFailure1(){
		responseFirstNameMsg = "Priyanka";
		responseLastNameMsg = "Halesh";
		responseEmailMsg = "team12@gmail.com";
		responsePhoneMsg = "1234567899";
		responseDesignationMsg = "Editor-in-Chief";
		responseUserNameMsg = "team12";
		responsePasswordMsg = "PriyankaHalesh1@";
			
		User currentUser3 = new User(6,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
		Main.setCurrentUser(currentUser3);
					
		UpdateProfileUI upui1 = testUpdateProfile("Priyanka","Halesh","team12@gmail.com","1234567899","Editor-in-Chief","team12","PriyankaHalesh");
		assertEquals(responseFirstNameMsg, upui1.firstNameField.getText());
		assertEquals(responseLastNameMsg, upui1.lastNameField.getText());
		assertEquals(responseEmailMsg, upui1.emailField.getText());
		assertEquals(responsePhoneMsg, upui1.phoneField.getText());
		assertEquals(responseDesignationMsg, upui1.designationField.getSelectedItem());
		assertEquals(responseUserNameMsg, upui1.usernameField.getText());
		assertNotEquals(responsePasswordMsg, String.valueOf(upui1.passwordField.getPassword()));			 
		} 
	
			
	// Test for password length less than 8 characters
	@Test
	public void testUpdateProfileFailure2(){
		responseFirstNameMsg = "Rushabh";
		responseLastNameMsg = "Shah";
		responseEmailMsg = "team2@gmail.com";
		responsePhoneMsg = "1234567899";
		responseDesignationMsg = "Editor-in-Chief";
		responseUserNameMsg = "team2";
		responsePasswordMsg = "Ru_12";
				
		User currentUser2 = new User(5,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
		Main.setCurrentUser(currentUser2);
		UpdateProfileUI upui = testUpdateProfile("Rushabh","Shah","team2@gmail.com","1234567899","Editor-in-Chief","team2","Ru_12");
		assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
		assertEquals(responseLastNameMsg, upui.lastNameField.getText());
		assertEquals(responseEmailMsg, upui.emailField.getText());
		assertEquals(responsePhoneMsg, upui.phoneField.getText());
		assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
		assertEquals(responseUserNameMsg, upui.usernameField.getText());
		assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
	} 
			 
	
	// Test for update profile when username is empty
		@Test
		public void testUpdateProfileEmptyUsername(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
				
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","team10@gmail.com","1234567899","Progam Chair","","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertEquals(responseEmailMsg, upui.emailField.getText());
			assertEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
			
		} 
		
		// Test for update profile when phone is empty
		@Test
		public void testUpdateProfileEmptyPhone(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","team10@gmail.com","","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		// Test for update profile when phone is invalid
		@Test
		public void testUpdateProfileEmptyPPhone(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","team10@gmail.com","+32735675","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		
		// Test for update profile when email is invalid
		@Test
		public void testUpdateProfileInvalidEmail(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","team10","732735675","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		
		// Test for update profile when email is empty
		@Test
		public void testUpdateProfileEmptyEmail(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi","","732735675","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		// Test for update profile when firstname is empty
		@Test
		public void testUpdateProfileEmptyFirstname(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("","Trivedi","vineet@gmail.com","732735675","Progam Chair","team20","Vineet_1994");
			assertNotEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
	
		// Test for update profile when lastname is empty
		@Test
		public void testUpdateProfileEmptyLastname(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","","vineet@gmail.com","732735675","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
	
		// Test for update profile when lastname is invalid
		@Test
		public void testUpdateProfileInvalidLastname(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vineet","Trivedi123","vineet@gmail.com","732735675","Progam Chair","team20","Vineet_1994");
			assertEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		// Test for update profile when firstname is invalid
		@Test
		public void testUpdateProfileInvalidFistname(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vine123et","Trivedi","team10@gmail.com","1234567899","Progam Chair","team20","Vineet_1994");
			assertNotEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertEquals(responseEmailMsg, upui.emailField.getText());
			assertEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		// Test for update profile when user does not exist in database
		@Test
		public void testUpdateProfileFail(){
			responseFirstNameMsg = "Vineet";
			responseLastNameMsg = "Trivedi";
			responseEmailMsg = "team10@gmail.com";
			responsePhoneMsg = "1234567899";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team10";
			responsePasswordMsg = "Vineet_1994";
						
			User currentUser1 = new User(4,responseFirstNameMsg,responseLastNameMsg,responseUserNameMsg,responsePasswordMsg,responseEmailMsg,responsePhoneMsg,responseDesignationMsg );
			Main.setCurrentUser(currentUser1);
			UpdateProfileUI upui = testUpdateProfile("Vine123et","Trivedi","vineet@gmail.com","732735675","Progam Chair","team20","Vineet_1994");
			assertNotEquals(responseFirstNameMsg, upui.firstNameField.getText());
			assertEquals(responseLastNameMsg, upui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, upui.emailField.getText());
			assertNotEquals(responsePhoneMsg, upui.phoneField.getText());
			assertEquals(responseDesignationMsg, upui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, upui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(upui.passwordField.getPassword()));
					
		} 
		
		@Test
		public void testforValidatePassword(){
			ValidateUserProfile vp = new ValidateUserProfile();
			assertEquals(vp.validatePassword(null), "Password is null");
		}
		
		@Test
		public void testforValidatePassword1(){
			ValidateUserProfile vp = new ValidateUserProfile();
			assertEquals(vp.validatePassword("priya12#"), "Password needs an upper case.");
		}
		
		@Test
		public void testforValidatePassword2(){
			ValidateUserProfile vp = new ValidateUserProfile();
			assertEquals(vp.validatePassword("PRI12#YANKA"), "Password needs a lowercase.");
		}
		
		@Test
		public void testforValidatePassword3(){
			ValidateUserProfile vp = new ValidateUserProfile();
			assertEquals(vp.validatePassword("Priya1245"), "Password needs a special character.");
		}
		
		
	public UpdateProfileUI testUpdateProfile(String fname, String lname, String email, String phone, String designation, String username, String password){
		UpdateProfileUI up= new UpdateProfileUI();
		up.firstNameField.setText(fname);
		up.lastNameField.setText(lname);
		up.emailField.setText(email);
		up.phoneField.setText(phone);
		up.designationField.setSelectedItem(designation);
	 	up.usernameField.setText(username);
		up.passwordField.setText(password);
		up.updateButton.doClick();
		return up;
	}
			 
	
}
