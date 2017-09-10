package main.userinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import main.queryengine.Author;
import main.queryengine.CommitteeQuery;

import java.awt.Frame;
import java.awt.Toolkit;

// Main class to launch the application
public class Main {

	public static JFrame MainFrame = new JFrame("MSD Team2");
	private static User CurrentUser;
	private static List<Author> authorList;

	public static void main(String[] args) {
//		MainFrame = new JFrame("MSD Team2");
		MainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tabbedPane.add("Login",new LoginUI());
		tabbedPane.add("Register", new RegisterUI());

		MainFrame.getContentPane().add(tabbedPane);

//		MainFrame.pack();
//		MainFrame.setLocationRelativeTo(null);
		MainFrame.setVisible(true);
	}

	// method to access the mainframe
	public static JFrame getMainFrame() {
		return MainFrame;
	}

	// method which sets the value of mainframe
//	public static void setMainFrame(JFrame mainFrame) {
//		MainFrame = mainFrame;
//	}

	// method to fetch the current user. Current user maintains the session of the user currently accessing the application
	public static User getCurrentUser() {
		return CurrentUser;
	}

	// setter method to set the current user of the application
	public static void setCurrentUser(User currentUser) {
		CurrentUser = currentUser;
	}
	
	// maintains the current committee list of the user currently using the application
	public static void setCurrentCommittee(List<Author> authors){
		authorList = authors;
	}
	
	// method to fetch the current committee list of the current user
	public static List<Author> getCurrentCommittee(){
		return authorList;
	}
}
