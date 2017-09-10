package com.msd.userinterface;

import java.awt.BorderLayout;

import com.msd.interfaces.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.msd.interfaces.RegisterUIInterface;
import com.msd.queryengine.SystemUserQuery;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

/**
 * The Class RegisterUI.
 */
public class RegisterUI extends JPanel implements RegisterUIInterface{

	/** The parent frame. */
	public JFrame parentFrame;

	/** The first name field. */
	public JTextField firstNameField = new JTextField(30);
	
	/** The last name field. */
	public JTextField lastNameField = new JTextField(30);
	
	/** The email field. */
	public JTextField emailField = new JTextField(30);
	
	/** The phone field. */
	public JTextField phoneField = new JTextField(30);   
	
	/** The designation field. */
	public JComboBox<String> designationField = new JComboBox<String>();
	
	/** The username field. */
	public JTextField usernameField = new JTextField(30);
	
	/** The password field. */
	public JPasswordField passwordField = new JPasswordField(30);
	
	/** The register button. */
	public JButton registerButton = new JButton("Register");

	/**
	 * Instantiates a new register UI.
	 * Sets the layout, initializes the fields and buttons.
	 */
	public RegisterUI() {
		parentFrame = Main.getMainFrame();
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
	}

	/**
	 * Resets the values of text fields.
	 */ 
	public void clearAllFields(){
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		phoneField.setText("");
		usernameField.setText("");
		passwordField.setText("");
	}
	
	/**
	 * Inits the buttons.
	 * On register button click, validate the field data. 
	 * If all the details are valid, insert the user to the database if it does not already exists.
	 * If the user already exists, display error message. 
	 *
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String REGISTER_MESSAGE = ValidateFields();
				if(REGISTER_MESSAGE.equals("Success")){
					User currentUser = getFieldData();
					SystemUserQuery sq = new SystemUserQuery();
				
					if(!sq.addUserToDatabase(currentUser)){
						String message = "User with name "+currentUser.getUsername()+" already exists.";
						
						JOptionPane jOptionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
						runDisposeInBackground(jOptionPane);
						usernameField.setText("");
						return;
					}
					
					Main.setCurrentUser(currentUser);
					String message = "Registration Successful";
					
					JOptionPane jOptionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
					runDisposeInBackground(jOptionPane);
					
					parentFrame.getContentPane().removeAll();
					parentFrame.getContentPane().revalidate();
					parentFrame.getContentPane().repaint();

					parentFrame.getContentPane().setLayout(new BorderLayout());
					JTabbedPane tabbedPane = new JTabbedPane();
					tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
					tabbedPane.addTab("Search", new SearchUI(null));
					tabbedPane.addTab("Commiitee List", new CommitteeListUI());
					tabbedPane.addTab("Update Profile", new UpdateProfileUI());
					tabbedPane.add("Logout", new LogoutUI());
					parentFrame.getContentPane().add(tabbedPane);
				}
				else{
					JOptionPane jOptionPane = new JOptionPane(REGISTER_MESSAGE, JOptionPane.ERROR_MESSAGE);
					runDisposeInBackground(jOptionPane);
					}
				}
		});
		panel.add(registerButton);
		return panel;
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.RegisterUIInterface#ValidateFields()
	 */
	// method to validate all the user details 
		public String ValidateFields() {
		ValidateUserProfile vp = new ValidateUserProfile();
	
		if(firstNameField.getText().isEmpty() || !vp.validateFirstName(firstNameField.getText())){
			return "Invalid First Name";
		}
		
		if(lastNameField.getText().isEmpty() || !vp.validateLastName(lastNameField.getText())){
			return "Invalid Last Name";
		}
			
		if(emailField.getText().isEmpty() || !vp.validateEmail(emailField.getText())){
			return "Invalid EmailID";
		}
		
		if(phoneField.getText().isEmpty() || !vp.validatePhone(phoneField.getText().toString())){
			return "Invalid Phone Number";
		}
		
		if(usernameField.getText().isEmpty()){
			return "Invalid Username";
		}
		
		if(passwordField.getPassword().toString().isEmpty() ||
		(!vp.validatePassword(new String(passwordField.getPassword())).equals("Success"))){
			return "Invalid Password";
		}
		
		return "Success";
	}


	/**
	 * Inits the fields and labels of the Register UI screen.
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][]"));

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(firstNameLabel, "cell 0 0,alignx label");
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(firstNameField, "cell 1 0");

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lastNameLabel, "cell 0 1,alignx label");
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lastNameField, "cell 1 1");

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(usernameLabel, "cell 0 2");

		usernameField = new JTextField(30);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(usernameField, "cell 1 2,growx");		

		JLabel passwordLabel = new JLabel("Password*");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordLabel, "cell 0 3");      
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordField, "cell 1 3,growx");

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(emailLabel, "cell 0 4,alignx label");
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(emailField, "cell 1 4");

		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(phoneLabel, "cell 0 5,alignx label");
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(phoneField, "cell 1 5");

		JLabel designationLabel = new JLabel("Designation");
		designationLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(designationLabel, "cell 0 6,alignx label");
		designationField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		designationField.addItem("Program Chair");
		designationField.addItem("Editor-in-Chief");
		panel.add(designationField, "cell 1 6");
		
		JLabel lblAPasswordShould = new JLabel("*A password should be atleast 8 characters long, contain alphanumeric characters, ");
		lblAPasswordShould.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		panel.add(lblAPasswordShould, "cell 1 7");
		
		JLabel lblWithOneUppercase = new JLabel("with 1 uppercase, 1 lower case and 1 special character");
		lblWithOneUppercase.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		panel.add(lblWithOneUppercase, "cell 1 8");

		return panel;
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.RegisterUIInterface#getFieldData()
	 */
	public User getFieldData() {
		User user = new User();
		user.setFirstName(firstNameField.getText());
		user.setLastName(lastNameField.getText());
		user.setEmail(emailField.getText());
		user.setPhone(phoneField.getText());
		user.setDesignation(designationField.getSelectedItem().toString());
		user.setUsername(usernameField.getText());
		user.setPassword(new String(passwordField.getPassword()));
		return user;
	}

	public void runDisposeInBackground(JOptionPane jOptionPane){
		final JDialog dialog = jOptionPane.createDialog(parentFrame, "Register");

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
	}
}