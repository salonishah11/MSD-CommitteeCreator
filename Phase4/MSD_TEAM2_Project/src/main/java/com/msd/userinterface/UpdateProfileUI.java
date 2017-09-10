package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.msd.interfaces.UpdateProfileUIInterface;
import com.msd.queryengine.SystemUserQuery;
import net.miginfocom.swing.MigLayout;

/**
 * The Class UpdateProfileUI.
 */
public class UpdateProfileUI extends JPanel implements UpdateProfileUIInterface{

	/** The parent frame. */
	public JFrame parentFrame;
	
	/** The current user. */
	public User currentUser;

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
	
	/** The update button. */
	public JButton updateButton = new JButton("Update");

	/**
	 * Instantiates a new update profile UI.
	 */
	public UpdateProfileUI() {
		parentFrame = Main.getMainFrame();
		currentUser = Main.getCurrentUser();
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);

		if(currentUser != null)
			populateUser(currentUser);
	}


	/**
	 * Inits the fields.
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));

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
		panel.add(usernameField, "cell 1 2");
		usernameField.setEditable(false);

		JLabel passwordLabel = new JLabel("Password*");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordLabel, "cell 0 3");      
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(passwordField, "cell 1 3");

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


	/**
	 * Inits the buttons and implements the onCLick functionality.
	 * Validates each of the value entered for the User profile.
	 * Update the user profile and the database with the new details.
	 * 
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String UPDATE_MESSAGE = ValidateFields();
				if(UPDATE_MESSAGE.equals("Success")){ 
					User currentUser = getFieldData();
					SystemUserQuery sq = new SystemUserQuery();
					if(sq.updateUserProfile(currentUser, Main.getCurrentUser())){
						populateUser(currentUser);
						Main.setCurrentUser(currentUser);
						JOptionPane jOptionPane = new JOptionPane("Update Successful",
								JOptionPane.PLAIN_MESSAGE);
						runDisposeInBackground(jOptionPane);
					}
				}
				else{
					JOptionPane jOptionPane = new JOptionPane(UPDATE_MESSAGE,
							JOptionPane.ERROR_MESSAGE);
					runDisposeInBackground(jOptionPane);
				}
			}
		});
		panel.add(updateButton);

		return panel;
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.UpdateProfileUIInterface#ValidateFields()
	 */
	// method to validate each and every field of the updateProfiel UI
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
	 * Populate user fields with the parameter values of the user currently logged in to the application.
	 *
	 * @param currentUser the current user
	 */
	private void populateUser(User currentUser) {
		firstNameField.setText(currentUser.getFirstName());
		lastNameField.setText(currentUser.getLastName());
		emailField.setText(currentUser.getEmail());
		phoneField.setText(currentUser.getPhone());
		designationField.setSelectedItem(currentUser.getDesgination());
		usernameField.setText(currentUser.getUsername());
		passwordField.setText(currentUser.getPassword());
	}


	/* (non-Javadoc)
	 * @see com.msd.interfaces.UpdateProfileUIInterface#getFieldData()
	 */
	// method to create user and initilize the user parameters from the data entered by the user on updateprofile page
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
		final JDialog dialog = jOptionPane.createDialog(parentFrame, "Update Profile");

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
