package com.msd.queryengine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PublicationListQuery.
 */
public class PublicationListQuery {

	/** The database connection. */
	private Connection conn;

	/**
	 * Instantiates a new publication list query.
	 */
	public PublicationListQuery(){
		// Create an instance of SystemQuery class
		SystemUserQuery sq = new SystemUserQuery();

		// Fetch the database connection using JDBC
		conn = sq.connectToDB();
	}

	
	/**
	 * Gets the paper conference name list.
	 *
	 * @return the paper conference name list
	 */
	public List<String> getPaperConferenceNameList(){
		String selectQuery = "SELECT distinct event_name FROM conferencepaper order by event_name;";	

		try {
			PreparedStatement st = conn.prepareStatement(selectQuery);
			ResultSet rs = st.executeQuery();
			ArrayList<String> resultantList = new ArrayList<String>();
			while (rs.next()) {
				String name = rs.getString(1);
				resultantList.add(name.toUpperCase());
			}

			return resultantList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * Gets the paper journal name list.
	 *
	 * @return the paper journal name list
	 */
	public List<String> getPaperJournalNameList(){
		String selectQuery = "SELECT distinct event_name FROM journalpaper order by event_name;";	
		
		try {
			PreparedStatement st = conn.prepareStatement(selectQuery);
			ResultSet rs = st.executeQuery();
			ArrayList<String> resultantList = new ArrayList<String>();
			while (rs.next()) {
				String name = rs.getString(1);
				resultantList.add(name.toUpperCase());
			}

			return resultantList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * Gets the committee conference name list.
	 *
	 * @return the committee conference name list
	 */
	public List<String> getCommitteeConferenceNameList(){
		String selectQuery = "SELECT distinct conferenceName FROM committee order by conferenceName;";	
		
		try {
			PreparedStatement st = conn.prepareStatement(selectQuery);
			ResultSet rs = st.executeQuery();
			ArrayList<String> resultantList = new ArrayList<String>();
			while (rs.next()) {
				String name = rs.getString(1);
				resultantList.add(name.toUpperCase());
			}

			return resultantList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
