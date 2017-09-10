package main.queryengine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.sql.PreparedStatement;

import main.interfaces.SystemUserInterface;
import main.parser.db.DBConnection;
import main.userinterface.User;

public class SystemUserQuery implements SystemUserInterface{
  
	// method which forms the query from the passed in username and password,
	// establish a connection to the database and fetch the entry row if the user already exists
	public User loginQuery(String username ,String password){
		String query = "select * from dblp.systemuser where `username` = '"
				+username
				+"' and `password` = '"
				+password+"'";
		
		Connection conn = connectToDB();
		System.out.println("Connected database successfully...");
		User user = getUserData(conn, query);
		return user;
	}

	// connect to the database and assign to the connection object
	public Connection connectToDB(){
		System.out.println("Connecting to a selected database...");
		Wrapper conn = null;
		conn = DBConnection.getConnection();
		return (Connection)conn;
	}

	// get the details of the user from the systemuser table if the user already exists, otherwise return null
	public User getUserData(Connection conn, String query) {
		User user = new User();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setDesignation(rs.getString("usertype"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			st.close();
		}catch(SQLException e){
			//e.printStackTrace();
		}
		return null;
	}	

	// inserts the user to the systemuser table if the user already does not exist and return true
	// otherwise return false
	public boolean addUserToDatabase(User currentUser){
		String query = "select * from dblp.systemuser where `username` = '"+currentUser.getUsername()+"'";
		Connection conn = connectToDB();				
		User user = getUserData(conn, query);
		if(user == null){
			String insertQuery = "insert into dblp.systemuser "
					+ "(`firstname`,`lastname`,`username`,`password`,`email`,`phone`,`usertype`)"
					+ " values('"+currentUser.getFirstName()
					+"','"+currentUser.getLastName()
					+"','"
					+ currentUser.getUsername()
					+"','"+currentUser.getPassword()
					+"','"
					+ currentUser.getEmail()
					+"','"+currentUser.getPhone()
					+"','"+currentUser.getDesgination()
					+"');";

			try{
				conn.createStatement().executeUpdate(insertQuery);
				return true;
			}catch(SQLException e){
				//e.printStackTrace();
			}
		} 
		return false;
	}

	// updates the user details of the currentuser whose profile is being updated
	public boolean updateUserProfile(User currentUser, User mainUser){
		try{		
			String query = "update dblp.systemuser set `firstname` = '"
					+currentUser.getFirstName()+"', `lastname` = '"
					+ currentUser.getLastName()+"', `username` = '"
					+currentUser.getUsername()+"', `password` = '"
					+currentUser.getPassword()+
					"', `email` = '"+ currentUser.getEmail()
					+"', `phone` = '"+currentUser.getPhone()
					+"', `usertype` = '"
					+currentUser.getDesgination()
					+"' where `username` = '" 
					+ mainUser.getUsername() 
					+ "' and `password` = '"
					+mainUser.getPassword()+"'";

			Connection conn = connectToDB();
			conn.createStatement().executeUpdate(query);
			//conn.close();
			return true;
		}catch(SQLException e){
			//e.printStackTrace();
			return false;
		}		
	}
}

