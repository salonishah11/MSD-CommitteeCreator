package com.msd.queryengine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.msd.db.DBConnection;
import com.msd.interfaces.SystemUserInterface;
import com.msd.userinterface.User;
import java.sql.PreparedStatement;

/**
 * The Class SystemUserQuery contains methods relevant to system user 
 */
public class SystemUserQuery implements SystemUserInterface{
  
	/** The database connection. */
	Connection conn;
	
	/** The user params. */
	List<String> userParams;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(SystemUserQuery.class);
	
	/**
	 * Instantiates a new system user query.
	 */
	public SystemUserQuery(){
		conn = connectToDB();
		userParams= new ArrayList<String>();
	}
	
	// method which forms the query from the passed in username and password,
	/* (non-Javadoc)
	 * @see com.msd.interfaces.SystemUserInterface#loginQuery(java.lang.String, java.lang.String)
	 */
	// establish a connection to the database and fetch the entry row if the user already exists
	public User loginQuery(String username ,String password){
		String query = "select * from systemuser where username = ? and password = ?";
		userParams.add(username);
		userParams.add(password);
		User user = getUserData(query);
		userParams.clear();
		if(user!= null && user.getUsername().equals(username) && user.getPassword().equals(password)){
			return user;
		}
		return null;
	}

	/**
	 * Connect to DB.
	 *
	 * @return the connection
	 */
	public Connection connectToDB(){
		Wrapper conn = null;
		conn = DBConnection.getConnection();
		return (Connection)conn;
	}

	/**
	 * Gets the user data.
	 *
	 * @param query Query to fetch user data
	 * @return get the details of the user from the systemuser table if the user already exists, otherwise return null
	 */
	public User getUserData(String query) {
		User user = new User();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			for(int i=1; i<=userParams.size();i++){
				st.setString(i, userParams.get(i-1));
			}
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
			logger.error("Failed to retrieve User data from systemuser table");
		}
		return null;
	}	

	// inserts the user to the systemuser table if the user already does not exist and return true
	/* (non-Javadoc)
	 * @see com.msd.interfaces.SystemUserInterface#addUserToDatabase(com.msd.userinterface.User)
	 */
	public boolean addUserToDatabase(User currentUser){
		PreparedStatement preparedStatement = null;
		String query = "select * from systemuser where username = ?";		
		userParams.add(currentUser.getUsername());
		User user = getUserData(query);
		userParams.clear();
		
		if(user == null){
			String insertQuery = "insert into systemuser "
					+ "(firstname,lastname,username,password,email,phone,usertype)"
					+ " values(?,?,?,?,?,?,?)";
			
			userParams.add(currentUser.getFirstName());
			userParams.add(currentUser.getLastName());
			userParams.add(currentUser.getUsername());
			userParams.add(currentUser.getPassword());
			userParams.add(currentUser.getEmail());
			userParams.add(currentUser.getPhone());
			userParams.add(currentUser.getDesgination());
			
			try{
				preparedStatement = conn.prepareStatement(insertQuery);
				for(int i=1; i<= userParams.size();i++){
					preparedStatement.setString(i, userParams.get(i-1));
				}
				userParams.clear();
				preparedStatement.executeUpdate();
				return true;
			}catch(SQLException e){
				logger.error("Failed to add user to the systemuser table");
			}
		} 
		return false;
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.SystemUserInterface#updateUserProfile(com.msd.userinterface.User, com.msd.userinterface.User)
	 */
	public boolean updateUserProfile(User currentUser, User mainUser){
		PreparedStatement preparedStatment =null;
		
		userParams.add(currentUser.getFirstName());
		userParams.add(currentUser.getLastName());
		userParams.add(currentUser.getUsername());
		userParams.add(currentUser.getPassword());
		userParams.add(currentUser.getEmail());
		userParams.add(currentUser.getPhone());
		userParams.add(currentUser.getDesgination());
		userParams.add(mainUser.getUsername());
		userParams.add(mainUser.getPassword());
		
		try{		
			String query = "update systemuser set firstname = ?, lastname =?, username = ?, password =?,email=?, phone = ?, usertype=? where "
					+ "username=? and password =?";
			preparedStatment = conn.prepareStatement(query);
			for(int i=1;i<=userParams.size();i++){
				preparedStatment.setString(i, userParams.get(i-1));;
			}
			userParams.clear();
			preparedStatment.executeUpdate();
			return true;
		}catch(SQLException e){
			return false;
		}		
	}
	
	
}

