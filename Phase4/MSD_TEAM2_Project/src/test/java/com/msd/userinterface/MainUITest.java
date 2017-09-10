package com.msd.userinterface;

import static org.junit.Assert.*;

import org.junit.Test;

import com.msd.userinterface.Main;

public class MainUITest {

	@Test
	public void testMainFrame(){
		
		Main mainObject = new Main();
		mainObject.main(null);
		
		assertNotEquals("MainFrame should not be empty", 
				mainObject.MainFrame.getLayout(), null);
	}

}
