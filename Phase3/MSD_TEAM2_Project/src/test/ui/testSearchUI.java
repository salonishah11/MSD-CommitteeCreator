package test.ui;

import static org.junit.Assert.*;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.Test;

import main.userinterface.*;

public class testSearchUI {
	SearchUI sui = new SearchUI(null);
	User currentUser = new User(1, "Michelle", "Obama", "Michelle90",
			"Pass@123", "michelle@yahoo.com", "617-8581234", "Program Chair");
	
	
	@Test
	public void testSearchUISuccess1(){
	Main.setCurrentUser(currentUser);	
    sui.getAuthorNameField().setText("Rushabh");
    sui.getSchoolNameField().setText("MIT");
    sui.getSrdbtnMasterThesisField().setSelected(true);
    sui.getrdbtnPhdThesisField().setSelected(false);
    sui.getrrdbtnYesField().setSelected(true);
    sui.getrdbtnNoField().setSelected(false);
    sui.getcommitteeServedStartYr().setText("2012");
    sui.getcommitteeServedEndYr().setText("2016");
    sui.getcommitteeConferenceNameOOPSLA().setSelected(true);
    sui.getcommitteeConferenceNamePLDI().setSelected(true);
    sui.getcommitteeConferenceNameECOOP().setSelected(true);
    sui.getcommitteeConferenceNameICFP().setSelected(true);
    
    sui.searchButton.doClick();
    sui.clearAllButton.doClick();
    
    assertEquals(true, sui.resultantAuthorList == null || sui.resultantAuthorList.size() == 0);
    }
	
	@Test
	public void testSearchUISuccess2(){
	//Main.setCurrentUser(currentUser);	
    sui.getpaperTitleField().setText("Pointer Analysis");
    sui.getkeywordsField().setText("Pointer");
    sui.getcommitteeConferenceNameOOPSLA().setSelected(true);
    sui.getcommitteeConferenceNamePLDI().setSelected(false);
    sui.getcommitteeConferenceNameECOOP().setSelected(true);
    sui.getcommitteeConferenceNameICFP().setSelected(false);
    sui.getkpaperJournalNameTOPLAS().setSelected(true);
    sui.getpaperJournalNameTSE().setSelected(true);
    sui.getpaperPublicationStartYr().setText("2012");
    sui.getpaperPublicationEndYr().setText("2016");
    sui.getexactNumOfPapersField().setText("3");
    sui.getnumOfPapersStartField().setText("2");
    sui.getnumOfPapersEndFields().setText("4");
    sui.getrdbtnExact().setSelected(true);
    sui.getrdbtnRange().setSelected(false);
    
    sui.searchButton.doClick();
    sui.clearAllButton.doClick();
    
    assertEquals(true, sui.resultantAuthorList == null || sui.resultantAuthorList.size() == 0);
    }
	
	

}