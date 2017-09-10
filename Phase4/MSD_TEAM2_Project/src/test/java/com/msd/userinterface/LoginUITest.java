package com.msd.userinterface;

import static org.junit.Assert.*;

import org.junit.Test;

import com.msd.userinterface.LoginUI;

import com.msd.interfaces.*;

public class LoginUITest {

	LoginUI lui = new LoginUI();
	String responseUnameMsg = "";
	String responsePassMsg = "";
	
	// Test for Username and Password match as entered in respective fields
	 @Test
	public void testLoginSuccess(){
		responseUnameMsg = "team2";
		responsePassMsg = "Team2_MSD";
		 
		testLogin("team2","Team2_MSD");
		assertEquals(responseUnameMsg, lui.userNameField.getText());
		assertEquals(responsePassMsg, String.valueOf(lui.passwordField.getPassword()));
	}
	 
	// Test for Username and Password does not match as entered in respective fields
	 @Test
	public void testLoginFailure(){
		 responseUnameMsg = "team3";
		 responsePassMsg = "ABCD";
		 testLogin("team10","ABCDe123$");
		 
		 assertNotEquals(responseUnameMsg, lui.userNameField.getText());
		 assertNotEquals(responsePassMsg, String.valueOf(lui.passwordField.getPassword()));

	 }
	 
	@Test
	public void testLoginFailure1(){
		 responseUnameMsg = "team3";
		 responsePassMsg = "ABCD";
		 testLogin("","");
		 
		 assertNotEquals(responseUnameMsg, lui.userNameField.getText());
		 assertNotEquals(responsePassMsg, String.valueOf(lui.passwordField.getPassword()));

	 }
	 
	@Test
	public void testLoginFailure2(){
		 responseUnameMsg = "team3";
		 responsePassMsg = "ABCD";
		 testLogin("team10","");
		 
		 assertNotEquals(responseUnameMsg, lui.userNameField.getText());
		 assertNotEquals(responsePassMsg, String.valueOf(lui.passwordField.getPassword()));

	 }
	 
	@Test
	public void testLoginFailure3(){
		 responseUnameMsg = "team3";
		 responsePassMsg = "ABCD";
		 testLogin("","ABCDE");
		 
		 assertNotEquals(responseUnameMsg, lui.userNameField.getText());
		 assertNotEquals(responsePassMsg, String.valueOf(lui.passwordField.getPassword()));

	 }
	
	 public void testLogin(String username, String password){
			lui.userNameField.setText(username);
			lui.passwordField.setText(password);
			lui.signinButton.doClick();
		}

	}


