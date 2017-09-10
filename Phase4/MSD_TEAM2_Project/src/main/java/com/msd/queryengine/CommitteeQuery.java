package com.msd.queryengine;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.msd.interfaces.CommitteeAuthorInterface;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.Main;
import com.msd.userinterface.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class CommitteeQuery.
 */
public class CommitteeQuery implements CommitteeAuthorInterface {

	/** The database connection. */
	private Connection conn;
	
	/** The committee params. */
	private List<String> committeeParams;
	
	/** The current user. */
	private User currentUser;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(CommitteeQuery.class);

	/**
	 * Instantiates a new committee query.
	 */
	public CommitteeQuery(){
		committeeParams = new ArrayList<String>();	

		// Create an instance of SystemQuery class
		SystemUserQuery sq = new SystemUserQuery();

		// Fetch the database connection using JDBC
		conn = sq.connectToDB();

		// initialize the currentUser from the main class
		currentUser = Main.getCurrentUser();
	}

	/**
	 * Gets the users committee list.
	 *
	 * @return the users committee list
	 */
	public ArrayList<Author> getUsersCommitteeList() {
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from shortlistcommittee where ownerusername =?";

		committeeParams.add(currentUser.getUsername());
		try{
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, committeeParams.get(0));			
		}catch(SQLException e){
			logger.error("Unable to create PreparedStatement for fetching committee members");
		}		

		// fetch the committee members selected by the currentuser from the shortlist table
		ArrayList<Author> committeeList = getAuthorList(preparedStatement);
		committeeParams.clear();
		return committeeList;
	}


	/* (non-Javadoc)
	 * @see com.msd.interfaces.CommitteeAuthorInterface#addCommitteeMembers(java.util.List)
	 */
	// method to add selected members to the shortlistcommittee table
	public boolean addCommitteeMembers(List<Author> authors) {
		boolean allAuthorsAddedFlag = true;
		PreparedStatement preparedStatement = null;

		try {
			// check whether the author already exists in the committee, if not add the author
			for (Author a : authors) {
				committeeParams.add(a.getName());
				committeeParams.add(currentUser.getUsername());

				String query = "select * from shortlistcommittee where authorname =? and ownerusername =?";
				preparedStatement = conn.prepareStatement(query);
				for(int i=1; i <= committeeParams.size();i++){
					preparedStatement.setString(i, committeeParams.get(i-1));
				}

				committeeParams.clear();
				Author user = getAuthorData(preparedStatement);

				if (user == null) {
					createInsertQuery(a);
				} else {
					allAuthorsAddedFlag = allAuthorsAddedFlag && false;
				}
			}
			return allAuthorsAddedFlag;
		} catch (SQLException e) {
			logger.error("Failed to add a new member to the committee");
		}
		return false;
	}

	/**
	 * method to form the query to insert a author to the shortlistcommittee table.
	 * @param a The author to be inserted
	 * @return true, if insert operation was success
	 */
	public boolean createInsertQuery(Author a) {
		PreparedStatement preparedStatement = null;
		committeeParams.add(a.getName());
		committeeParams.add(currentUser.getUsername());

		String query = "insert into shortlistcommittee(authorname, ownerusername) values(?,?)";
		try{
			preparedStatement = conn.prepareStatement(query);
			for(int i=1; i<= committeeParams.size();i++){
				preparedStatement.setString(i, committeeParams.get(i-1));
			}

			preparedStatement.executeUpdate();
			committeeParams.clear();
		}catch(SQLException e){	
			logger.error("Failed to insert an author to the committee");
			return false;
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see com.msd.interfaces.CommitteeAuthorInterface#removeMembers(java.util.List)
	 */
	// method to remove the selected members from the shortlistcommittee table
	public boolean removeMembers(List<Author> authorsRemoved) {
		PreparedStatement preparedStatement = null;
		try {
			for (Author a : authorsRemoved) {
				String deleteQuery = "delete from shortlistcommittee where authorname =? and ownerusername = ?";
				committeeParams.add(a.getName());
				committeeParams.add(currentUser.getUsername());

				preparedStatement = conn.prepareStatement(deleteQuery);
				for(int i=1; i<=committeeParams.size();i++){
					preparedStatement.setString(i, committeeParams.get(i-1));
				}
				preparedStatement.executeUpdate();
				committeeParams.clear();
			}

			return true;
		} catch (SQLException e) {
			logger.error("Failed to remove author from the committee");
		}
		return false;
	}

	/**
	 * Creates other based on the executed query results
	 *
	 * @param st PreparedStatement for fetching author details
	 * @return the author data
	 */
	// method to fetch author from the committee
	private Author getAuthorData(PreparedStatement st) {
		Author author = new Author();
		try {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				author.setName(rs.getString("authorname"));
				return author;
			}
			st.close();
		} catch (SQLException e) {
			logger.error("Failed to retrieve author details from shortlistcommittee table");
		}
		return null;
	}

	/**
	 * Gets the author list after executing the query to get all the relevant author details
	 *
	 * @param st The preparedstatement to fetch author details
	 * @return the author list for the given prepared statement.
	 */
	// fetch the list of authors from committee
	public ArrayList<Author> getAuthorList(PreparedStatement st) {
		ArrayList<Author> authorList = new ArrayList<Author>();
		try {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setName(rs.getString("authorname"));
				authorList.add(author);
			}
			st.close();
			return authorList;
		} catch (SQLException e) {
			logger.error("Unable to fetch list of committee members");
		}
		return null;
	}
}