package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.interfaces.LoginUIInterface;
import com.msd.queryengine.SystemUserQuery;

import javafx.scene.control.Tab;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * The Class LoginUI.
 */
public class LoginUI extends JPanel implements LoginUIInterface {

	/** The parent frame. */
	public JFrame parentFrame;

	/** The user name field. */
	public JTextField userNameField = new JTextField(30);
	
	/** The password field. */
	public JPasswordField passwordField = new JPasswordField(30);
	
	/** The j option pane. */
	public JOptionPane jOptionPane = new JOptionPane();
	
	/** The signin button. */
	public JButton signinButton = new JButton("SignIn");
	
	/** The logger. */
	private Logger logger = LogManager.getLogger(LoginUI.class);
	
	/**
	 * Instantiates a new login UI.
	 * with fields and buttons on the frame
	 */
	public LoginUI() {
		parentFrame = Main.getMainFrame();
		parentFrame.setVisible(true);
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
	}

	/**
	 * Initialized the buttons on the LoginUI frame and implement the onCLick functionality.
	 * Validates the credentials of the user being logged in
	 * If credentials are valid, initialize different panes
	 * search, committee, update profile and logout tabs
	 * If the credentials are invalid, pop ups the error message and resets the text fields.
	 * 
	 * @return the j panel
	 */ 
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		signinButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		signinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { 
				if(ValidCredentials()){
					parentFrame.getContentPane().removeAll();
					parentFrame.getContentPane().revalidate();
					parentFrame.getContentPane().repaint();
					parentFrame.getContentPane().setLayout(new BorderLayout());
					
					JTabbedPane tabbedPane = new JTabbedPane();
					tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
					tabbedPane.addTab("Search", new SearchUI(null));
					tabbedPane.addTab("Committee List", new CommitteeListUI());
					tabbedPane.addTab("Update Profile", new UpdateProfileUI());
					tabbedPane.add("Logout", new LogoutUI());
					
					parentFrame.getContentPane().add(tabbedPane);
				}
				else{
					jOptionPane = new JOptionPane("Invalid username or password",
								jOptionPane.ERROR_MESSAGE);
					  final JDialog dialog = jOptionPane.createDialog(parentFrame, "Login");

				      new SwingWorker<Void, Void>() {

				         @Override
				         protected Void doInBackground() throws Exception {
				            Thread.sleep(3000); 

				            return null;
				         }

				         protected void done() {
				            dialog.dispose();
				         };
				      }.execute();

				      dialog.setVisible(true);
				      
					userNameField.setText("");
					passwordField.setText("");
				}
			}
		});
		panel.add(signinButton);
		return panel;
	}

	/**
	 * Inits the fields.
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		JLabel usernameLabel = new JLabel("UserName");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panel.add(usernameLabel, "align label");
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(userNameField, "wrap");

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordLabel, "align label");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordField, "wrap");
		
		return panel;
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.LoginUIInterface#ValidCredentials()
	 */
	// validate the user credentials by checking against the systemuser database table
	public boolean ValidCredentials() { 
		if(!isEmptyFieldData()){
			SystemUserQuery sq = new SystemUserQuery();
			User user = sq.loginQuery(getUsername(), getPassword());
			
			if(user != null){
				Main.setCurrentUser(user);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Checks if is empty field data.
	 *
	 * @return true, if is empty field data
	 */
	private boolean isEmptyFieldData() {
		return (getUsername().isEmpty() || getPassword().isEmpty());
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.LoginUIInterface#getUsername()
	 */
	public String getUsername() {
		return userNameField.getText().trim();
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.LoginUIInterface#getPassword()
	 */
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
}