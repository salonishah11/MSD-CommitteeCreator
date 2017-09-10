package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The Class LogoutUI.
 */
public class LogoutUI extends JPanel {

	/** The parent frame. */
	public JFrame parentFrame;

	/** The logout button. */
	public JButton logoutButton = new JButton("Logout ?");

	/**
	 * Instantiates a new logout UI.
	 */
	public LogoutUI(){
		setLayout(new BorderLayout(5, 5));
		add(initButtons(), BorderLayout.CENTER);
	}

	/**
	 * Inits the buttons and implements the logout functionality onClick
	 * Logs out the user from the session and navigate to the initial screen of the app.
	 * 
	 * @return the j panel
	 */
	private JPanel initButtons() {
		parentFrame = Main.getMainFrame();

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame.getContentPane().removeAll();
				parentFrame.getContentPane().revalidate();
				parentFrame.getContentPane().repaint();

				parentFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
				JTabbedPane tabbedPane = new JTabbedPane();
				tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
				tabbedPane.add("Login",new LoginUI());
				tabbedPane.add("Register", new RegisterUI());
				parentFrame.getContentPane().add(tabbedPane);
				Main.setCurrentUser(null);

			}
		});

		panel.add(logoutButton);
		return panel;
	}
}
