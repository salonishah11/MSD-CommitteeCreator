package com.msd.queryengine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.queryengine.model.AuthorCommitteeDetails;
import com.msd.queryengine.model.AuthorPublications;


/**
 * The Class AuthorBackgroundDetailsQuery is used to fetch details with respect to affiliation information of author
 * to decide if similar search on specific criteria should be enabled or not.
 */
public class AuthorBackgroundDetailsQuery {
	
	/** Database connection*/
	private Connection conn;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(AuthorBackgroundDetailsQuery.class);
	
	/**
	 * Instantiates a new author background details query.
	 */
	public AuthorBackgroundDetailsQuery(){		
		// Create an instance of SystemQuery class
		SystemUserQuery sq = new SystemUserQuery();

		// Fetch the database connection using JDBC
		conn = sq.connectToDB();
	}

	
	/**
	 * Gets the affiliated university of author.
	 *
	 * @param authorName the author name
	 * @return the affiliated university of author
	 */
	public String getAffiliatedUniversityOfAuthor(String authorName){
		PreparedStatement preparedStatement = null;

		String selectQuery = "SELECT distinct university FROM affiliation "
				+ "where authorname = ?;";

		try {
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, authorName);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String university = rs.getString(1);
				
				return university;
			}
		} catch (SQLException e) {
			logger.error("Failed to obtain affiliated university of author");
		}

		return null;
	}
	
	
	/**
	 * Gets the region of author.
	 *
	 * @param authorName the author name
	 * @return the region of author
	 */
	public String getRegionOfAuthor(String authorName){
		PreparedStatement preparedStatement = null;

		String selectQuery = "SELECT distinct region FROM affiliation "
				+ "where authorname = ?;";

		try {
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, authorName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String region = rs.getString(1);
				return region;
			}
		} catch (SQLException e) {
			logger.error("Failed to obtain author's region");
		}

		return null;
	}
	
	
	/**
	 * Gets the research area of author.
	 *
	 * @param authorName the author name
	 * @return the research area of author
	 */
	public List<String> getResearchAreaOfAuthor(String authorName){
		PreparedStatement preparedStatement = null;

		String selectQuery = "SELECT distinct researcharea FROM researchinfo "
				+ "where authorname = ?;";
		try {
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, authorName);

			ResultSet rs = preparedStatement.executeQuery();

			ArrayList<String> resultantList = new ArrayList<String>();
			while (rs.next()) {
				String researchArea = rs.getString(1);
				resultantList.add(researchArea);
			}

			return resultantList;
		} catch (SQLException e) {
			logger.error("Failed to obtain author's research area");
		}

		return null;
	}
}
