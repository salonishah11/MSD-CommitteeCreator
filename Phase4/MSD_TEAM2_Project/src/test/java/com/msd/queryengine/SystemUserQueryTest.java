package com.msd.queryengine;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.msd.queryengine.SystemUserQuery;
import com.msd.userinterface.User;

public class SystemUserQueryTest {

	// initialize the two instances of users
	User currentUser = new User(1, "Michelle", "Obama", "Michelle90",
			"Pass@123", "michelle@yahoo.com", "617-8581234", "Program Chair");
	
	User newUser = new User(1, "Mic", "Obama", "Mic8190","Pass@123",
			"michelle@gmail.com","617-8581234", "Program Chair");
	
	// create an instance of SystemUserQuery class
	SystemUserQuery sq = new SystemUserQuery();		
	
	// test case for login use case with valid credentials
	@Test
	public void testLoginQueryWithValidCredentials() {
		sq.addUserToDatabase(currentUser);
		User user = sq.loginQuery("Michelle90", "Pass@123");
		assertTrue("Login Successfull", user.getUsername().equals("Michelle90"));
	}
	
	// test for update profile in database
	@Test 
	public void testUpdateProfileinDatabase(){
		boolean val = sq.updateUserProfile(newUser, currentUser);
		assertEquals("Update Successful", val,true);
	}
	
	
	//test to ensure registration with same username doesn't occur twice
	@Test
	public void testForSecondTimeRegistration(){
		assertNotEquals("User can't register twice", sq.addUserToDatabase(currentUser), true);
	}
		
	// test for deleting a user from the database table systemuser
	@Test
	public void testDeleteProfileinDatabase(){
		Connection conn = sq.connectToDB();
		try{
			conn.createStatement().executeUpdate("delete from dblp.systemuser"
						+ " where `username` = 'Mic8190' and `password` = 'Pass@123'");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		
}
	
