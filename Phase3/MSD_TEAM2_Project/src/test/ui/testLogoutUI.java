package test.ui;

import static org.junit.Assert.*;
import main.userinterface.*;

import org.junit.Test;

public class testLogoutUI {
	
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
