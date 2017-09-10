package com.msd.userinterface;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import com.msd.interfaces.*;

import org.junit.Test;

import com.msd.queryengine.SystemUserQuery;
import com.msd.userinterface.RegisterUI;
import com.msd.userinterface.User;

public class RegisterUITest {

	RegisterUI rui = new RegisterUI();
	
	String responseFirstNameMsg = "";
	String responseLastNameMsg = "";
	String responseEmailMsg = "";
	String responsePhoneMsg = "";
	String responseDesignationMsg = "";
	String responseUserNameMsg = "";
	String responsePasswordMsg = "";
	
	// Test for all fields checked match as entered in respective fields
	@Test
	public void testRegisterSuccess(){
		SystemUserQuery sq = new SystemUserQuery();
		responseFirstNameMsg = "Mike";
		responseLastNameMsg = "Wein";
		responseEmailMsg = "mike@gmail.com";
		responsePhoneMsg = "857123456";
		responseDesignationMsg = "Program Chair";
		responseUserNameMsg = "mikew";
		responsePasswordMsg = "Mike@2017";
			 
		Connection conn = sq.connectToDB();
		String query = "select * from systemuser where `username` = 'mikew'";
		User user = sq.getUserData(query);
		if(user != null){
			try{
				conn.createStatement().executeUpdate("delete from systemuser"
						+ " where `username` = 'mikew' and `password` = 'Mike@2017'");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		testRegister("Mike","Wein","mike@gmail.com","857123456","Program Chair","mikew","Mike@2017");
		assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
		assertEquals(responseLastNameMsg, rui.lastNameField.getText());
		assertEquals(responseEmailMsg, rui.emailField.getText());
		assertEquals(responsePhoneMsg, rui.phoneField.getText());
		assertEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
		assertEquals(responseUserNameMsg, rui.usernameField.getText());
		assertEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
			
	}
		 
	// Test for all fields checked do not match as entered in respective fields
	 @Test
	 public void testRegisterFailure(){
		SystemUserQuery sq = new SystemUserQuery();
		responseFirstNameMsg = "Franky";
		responseLastNameMsg = "Tipsy";
		responseEmailMsg = "franktip123@gmail.com";
		responsePhoneMsg = "+1857123459";
		responseDesignationMsg = "Editor-in-Chief";
		responseUserNameMsg = "franktip12";
		responsePasswordMsg = "Frank@2016-17";
				
		testRegister("Frank","Tip","franktip@gmail.com","+1857123456","Program Chair","franktip","Frank@2017");
		assertNotEquals(responseFirstNameMsg, rui.firstNameField.getText());
		assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
		assertNotEquals(responseEmailMsg, rui.emailField.getText());
		assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
		assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
		assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
		assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
	}
		 
		
	// Test when firstname fiesld is empty
		 @Test
		 public void testRegisterFailure3(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
					
			testRegister("","Tip","franktip@gmail.com","+1857123456","Program Chair","franktip","Frank@2017");
			assertNotEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		 
		 
		// Test when lastname fiesld is empty
		@Test
		 public void testRegisterFailure4(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
							
			testRegister("Franky","","franktip@gmail.com","+1857123456","Program Chair","franktip","Frank@2017");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
			
		// Test when email field is empty
		@Test
		 public void testRegisterFailureEmailEmpty(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
							
			testRegister("Franky","Tip","","+1857123456","Program Chair","franktip","Frank@2017");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		// Test when email field is empty
		@Test
		 public void testRegisterFailurePhoneEmpty(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
									
			testRegister("Franky","Tip","franktip@gmail.com","","Program Chair","franktip","Frank@2017");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		// Test when password field is empty
		@Test
		 public void testRegisterFailurePasswordEmpty(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky","Tip","franktip@gmail.com","2115455","Program Chair","franktip","");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
	
		// Test when firstname field is having other characeters
		@Test
		 public void testRegisterFailureFirstnameInvalid(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky123","Tip","franktip@gmail.com","2115455","Program Chair","franktip","Frank12$");
			assertNotEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
	
		// Test when lastname field is having other characeters
		@Test
		 public void testRegisterFailureLastnameInvalid(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky","Tip123","franktip@gmail.com","2115455","Program Chair","franktip","Frank12$");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		// Test for invalid email field
		@Test
		 public void testRegisterFailureEmailInvalid(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky","Tip","franktipgmail.com","2115455","Program Chair","franktip","Frank12$");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		// Test when username is empty
		@Test
		 public void testRegisterFailureEmptyUsername(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky","Tip","franktip@gmail.com","2115455","Program Chair","","Frank12$");
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		
		// Test when password is null
		@Test
		 public void testRegisterFailurePasswordNull(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "Franky";
			responseLastNameMsg = "Tipsy";
			responseEmailMsg = "franktip123@gmail.com";
			responsePhoneMsg = "+1857123459";
			responseDesignationMsg = "Editor-in-Chief";
			responseUserNameMsg = "franktip12";
			responsePasswordMsg = "Frank@2016-17";
										
			testRegister("Franky","Tip","franktip@gmail.com","2115455","Program Chair","",null);
			assertEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertNotEquals(responseEmailMsg, rui.emailField.getText());
			assertNotEquals(responsePhoneMsg, rui.phoneField.getText());
			assertNotEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertNotEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
		}
		
		
		// Test for already existing user
		@Test
		public void testRegisterFailureIfAuthorAlreadyExists(){
			SystemUserQuery sq = new SystemUserQuery();
			responseFirstNameMsg = "team";
			responseLastNameMsg = "team";
			responseEmailMsg = "mike@gmail.com";
			responsePhoneMsg = "857123456";
			responseDesignationMsg = "Program Chair";
			responseUserNameMsg = "team2";
			responsePasswordMsg = "Team2_MSD";
			
			testRegister("Mike","Wein","mike@gmail.com","857123456","Program Chair","team2","Team2_MSD");
			assertNotEquals(responseFirstNameMsg, rui.firstNameField.getText());
			assertNotEquals(responseLastNameMsg, rui.lastNameField.getText());
			assertEquals(responseEmailMsg, rui.emailField.getText());
			assertEquals(responsePhoneMsg, rui.phoneField.getText());
			assertEquals(responseDesignationMsg, rui.designationField.getSelectedItem());
			assertNotEquals(responseUserNameMsg, rui.usernameField.getText());
			assertEquals(responsePasswordMsg, String.valueOf(rui.passwordField.getPassword()));
				
		}
		
	 public void testRegister(String fname, String lname, String email, String phone, String designation, String username, String password){
		rui.firstNameField.setText(fname);
		rui.lastNameField.setText(lname);
		rui.emailField.setText(email);
		rui.phoneField.setText(phone);
		rui.designationField.setSelectedItem(designation);
	 	rui.usernameField.setText(username);
		rui.passwordField.setText(password);
		rui.registerButton.doClick();
	}
		 

}
