package com.msd.userinterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import com.msd.queryengine.model.Author;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * The Class Main which launches the application.
 */
public class Main {

	/** The Main frame. */
	public static JFrame MainFrame = new JFrame("MSD Team2");

	/** The Current user. */
	private static User CurrentUser;

	/** The author list. */
	private static List<Author> authorList;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame.setBounds(0,0,screenSize.width, screenSize.height-50);
		MainFrame.setResizable(false);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tabbedPane.add("Login",new LoginUI());
		tabbedPane.add("Register", new RegisterUI());
		MainFrame.getContentPane().add(tabbedPane);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		Rectangle bounds = gc.getBounds();
		MainFrame.setSize(bounds.width, bounds.height-50);
		MainFrame.setLocation(bounds.x, bounds.y);
		MainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		MainFrame.setVisible(true);		

	}

	/**
	 * Gets the main frame.
	 *
	 * @return the main frame
	 */
	public static JFrame getMainFrame() {
		return MainFrame;
	}


	/**
	 * Gets the current user.
	 * Current user maintains the session of the user currently accessing the application.
	 * @return the current user
	 */
	public static User getCurrentUser() {
		return CurrentUser;
	}

	/**
	 * Sets the current user of the application.
	 *
	 * @param currentUser the new current user
	 */
	public static void setCurrentUser(User currentUser) {
		CurrentUser = currentUser;
	}

	/**
	 * Sets the current committee list of the user currently using the application.
	 *
	 * @param authors the new current committee
	 */
	public static void setCurrentCommittee(List<Author> authors){
		authorList = authors;
	}

	/**
	 * Gets the current committee list of the current user.
	 *
	 * @return the current committee
	 */
	public static List<Author> getCurrentCommittee(){
		return authorList;
	}
}
