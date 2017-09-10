package main.userinterface;

// User class which represents the system user with corresponding user parameters, getter and setter methods
public class User {
	private int personId;
	private String firstName;
	private String designation;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String phone;

	public User() {

	}

	public User(int personId, String fName, String lName, String username, String password, 
			String email, String phone, String desg) {
		this.personId = personId;
		this.firstName = fName;
		this.lastName = lName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.designation = desg;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getEmail(){
		return this.email;
	}

	public String getPhone(){
		return this.phone;
	}

	public String getDesgination(){
		return this.designation;
	}

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	public void setFirstName(String fname){
		this.firstName = fname;
	}

	public void setLastName(String lname){
		this.lastName = lname;
	}

	public void setEmail(String mail){
		this.email=mail;
	}

	public void setPhone(String ph){
		this.phone = ph;
	}

	public void setDesignation(String desig){
		this.designation = desig;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
}