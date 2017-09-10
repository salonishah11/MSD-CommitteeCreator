package test.ui;

import static org.junit.Assert.*;

import org.junit.Test;

import main.userinterface.Main;

public class testMainUI {

	@Test
	public void testMainFrame(){
		
		Main mainObject = new Main();
		mainObject.main(null);
		
		assertNotEquals("MainFrame should not be empty", 
				mainObject.MainFrame.getLayout(), null);
	}

}
