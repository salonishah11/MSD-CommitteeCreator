package main.userinterface;

import java.awt.BorderLayout;
import main.interfaces.*;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import main.queryengine.SystemUserQuery;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;



public class RegisterUI extends JPanel implements RegisterUIInterface{

	public JFrame parentFrame;

	public JTextField firstNameField = new JTextField(30);
	public JTextField lastNameField = new JTextField(30);
	public JTextField emailField = new JTextField(30);
	public JTextField phoneField = new JTextField(30);   
	public JComboBox<String> designationField = new JComboBox<String>();
	public JTextField usernameField = new JTextField(30);
	public JPasswordField passwordField = new JPasswordField(30);

	public JButton registerButton = new JButton("Register");


	//constructor for the RegisterUI screen which sets the layout, and initializes the fields/buttons
	public RegisterUI() {
		parentFrame = Main.getMainFrame();
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);
	}

	// method to reset the values of text fields 
	public void clearAllFields(){
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		phoneField.setText("");
		usernameField.setText("");
		passwordField.setText("");
	}
	
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		registerButton.addActionListener(new ActionListener() {

			// upon register button click, validate the field data, 
			// if all the details are valid, insert the user to the database if it does not already exists
			// if the user already exists, display error message
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ValidateFields()){
					User currentUser = getFieldData();
					//add user to DB if it doesn't already exist
					SystemUserQuery sq = new SystemUserQuery();
				
					if(!sq.addUserToDatabase(currentUser)){
						String message = "User with name "+currentUser.getUsername()+" already exists.";
						JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.PLAIN_MESSAGE);
						clearAllFields();
						return;
					}
					
					Main.setCurrentUser(currentUser);
					String message = "Registration Successfull";
					JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.PLAIN_MESSAGE);

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
					JOptionPane.showMessageDialog(RegisterUI.this,
							"Invalid details",
							"Registration",
							JOptionPane.ERROR_MESSAGE);
					// reset username and password
					firstNameField.setText("");
					lastNameField.setText("");
					emailField.setText("");
					phoneField.setText("");   
					usernameField.setText("");
					passwordField.setText("");
				}
			}
		});
		panel.add(registerButton);

		return panel;
	}

	// method to validate all the user details 
	public boolean ValidateFields() {
		ValidateUserProfile vp = new ValidateUserProfile();
	
		if(firstNameField.getText().isEmpty() || !vp.validateFirstName(firstNameField.getText())){
			return false;
		}
		
		if(lastNameField.getText().isEmpty() || !vp.validateLastName(lastNameField.getText())){
			return false;
		}
			
		if(emailField.getText().isEmpty() || !vp.validateEmail(emailField.getText())){
			return false;
		}
		
		if(phoneField.getText().isEmpty() || !vp.validatePhone(phoneField.getText().toString())){
			return false;
		}
		
		if(usernameField.getText().isEmpty()){
			return false;
		}
		
		if(passwordField.getPassword().toString().isEmpty() ||
		(!vp.validatePassword(new String(passwordField.getPassword())).equals("Success"))){
			return false;
		}
		
		return true;
	}

	// method to initialize all the labels and text fields if the Register UI screen
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
		
		JLabel lblWithOneUppercase = new JLabel("with 1 uppercase, 1 lower case and 1 special charcter");
		lblWithOneUppercase.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		panel.add(lblWithOneUppercase, "cell 1 8");

		return panel;
	}

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

}