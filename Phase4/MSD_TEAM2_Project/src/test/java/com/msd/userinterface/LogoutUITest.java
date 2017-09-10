package com.msd.userinterface;

import static org.junit.Assert.*;

import org.junit.Test;

import com.msd.userinterface.LoginUI;
import com.msd.userinterface.LogoutUI;
import com.msd.interfaces.*;

public class LogoutUITest {
	
	LogoutUI lo = new LogoutUI();
	LoginUI li = new LoginUI();
	public boolean success = false;
	 
	// Test for displaying the Login frame again after user clicks on Logout button
	 @Test
	 public void testLogoutSuccess(){
		 lo.logoutButton.doClick();
		 success = li.parentFrame.isVisible();
		 assertEquals(success, true); 
	 }

}
