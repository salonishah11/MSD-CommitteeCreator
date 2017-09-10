package main.queryengine;

// Class for querying committee data
import java.util.ArrayList;
import java.util.List;

import main.interfaces.CommitteeAuthorInterface;
import main.userinterface.Main;
import main.userinterface.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommitteeQuery implements CommitteeAuthorInterface {

	// initialize the currentUser from the main class
	User currentUser = Main.getCurrentUser();

	public static ArrayList<Author> getUsersCommitteeList(User currentUser) {
		// Create an instance of SystemQuery class
		SystemUserQuery sq = new SystemUserQuery();

		// Fetch the database connection using JDBC
		Connection conn = sq.connectToDB();

		String selectQuery = "select * from dblp.shortlistcommittee com" + " where com.ownerusername = '"
				+ currentUser.getUsername() + "'";

		// fetch the committee members selected by the currentuser from the
		// shortlist table
		ArrayList<Author> committeeList = getAuthorList(conn, selectQuery);

		// close the connection of the database and return the retrieved
		// committeelist
		// conn.close();
		return committeeList;

	}

	// method to add selected members to the shortlistcommittee table
	public boolean addCommitteeMembers(List<Author> authors) {
		SystemUserQuery sq = new SystemUserQuery();
		Connection conn = sq.connectToDB();
		boolean allAuthorsAddedFlag = true;

		try {
			// check whether the author already exists in the committee, if not
			// add the author
			for (Author a : authors) {
				String query = "select * from dblp.shortlistcommittee" + " where `authorname` = '" + a.getName()
						+ "' and `ownerusername` = '" + currentUser.getUsername() + "'";
				Author user = getAuthorData(conn, query);
				if (user == null) {
					String insertQuery = createInsertQuery(a);
					conn.createStatement().executeUpdate(insertQuery);
				} else {
					allAuthorsAddedFlag = allAuthorsAddedFlag && false;
				}
			}
			// conn.close();
			return allAuthorsAddedFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// method to form the query to insert a author to the shortlistcommittee
	// table
	public String createInsertQuery(Author a) {
		String query = "insert into dblp.shortlistcommittee "
				+ "(`authorname`, `year`, `ownerusername`, `keyval`, `papertype`)" + " values('" + a.getName() + "','"
				+ a.getYear() + "','" + currentUser.getUsername() + "','" + a.getKeyVal() + "','" + a.getType() + "');";

		return query;
	}

	// method to form query to delete the author from the shortlistcommittee
	// table
	public String createDeleteQuery(Author a) {
		String query = "delete from dblp.shortlistcommittee where `authorname` = '" + a.getName()
				+ "' and `ownerusername` = '" + currentUser.getUsername() + "'";
		return query;
	}

	// method to remove the selected members from the shortlistcommittee table
	public boolean removeMembers(List<Author> authorsRemoved) {
		SystemUserQuery sq = new SystemUserQuery();
		Connection conn = sq.connectToDB();

		try {
			for (Author a : authorsRemoved) {
				String deleteQuery = createDeleteQuery(a);
				conn.createStatement().executeUpdate(deleteQuery);
			}
			// conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// method to fetch author from the committee
	private Author getAuthorData(Connection conn, String query) {
		Author author = new Author();
		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				author.setName(rs.getString("authorname"));
				author.setType(rs.getString("papertype"));
				author.setKey(rs.getString("keyVal"));
				author.setYear(rs.getInt("year"));
				return author;
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// fetch the list of authors from committee
	public static ArrayList<Author> getAuthorList(Connection conn, String selectQuery) {
		ArrayList<Author> authorList = new ArrayList<Author>();
		try {
			PreparedStatement st = conn.prepareStatement(selectQuery);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setName(rs.getString("authorname"));
				author.setType(rs.getString("papertype"));
				author.setKey(rs.getString("keyVal"));
				author.setYear(rs.getInt("year"));

				authorList.add(author);
			}
			st.close();
			return authorList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}