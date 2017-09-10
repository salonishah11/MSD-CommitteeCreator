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
 * The Class ViewAuthorDetailsQuery
 */
public class ViewAuthorDetailsQuery {

	/** The database connection object. */
	private Connection conn;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(ViewAuthorDetailsQuery.class);
	
	/**
	 * Instantiates a new view author details query.
	 */
	public ViewAuthorDetailsQuery(){		
		// Create an instance of SystemQuery class
		SystemUserQuery sq = new SystemUserQuery();

		// Fetch the database connection using JDBC
		conn = sq.connectToDB();
	}


	/**
	 * Gets the author publication details.
	 *
	 * @param authorName the author name
	 * @return the author publication details
	 */
	public ArrayList<AuthorPublications> getAuthorPublicationDetails(String authorName){
		PreparedStatement preparedStatement = null;

		String selectQuery = "SELECT jp.event_name, jp.title, jp.year, jp.type" 
				+ " FROM author a" 
				+ " join journalauthor ja on a.id = ja.authorID"
				+ " join journalpaper jp on ja.paperid = jp.id"
				+ " where a.name = ?"
				+ " UNION"
				+ " select cp.event_name, cp.title, cp.year, cp.type"
				+ " from author a"
				+ " join conferenceauthor c on a.id = c.authorId"
				+ " join conferencepaper cp on cp.id = c.paperid"
				+ " where a.name = ?"
				+ " UNION"
				+ " select null, t.title, t.year, t.type"
				+ " from author a"
				+ " join thesisauthor ta on a.id = ta.authorID"
				+ " join thesis t on t.id = ta.paperid"
				+ " where a.name = ?;";

		try {
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, authorName);
			preparedStatement.setString(2, authorName);
			preparedStatement.setString(3, authorName);

			ResultSet rs = preparedStatement.executeQuery();

			ArrayList<AuthorPublications> resultantList = new ArrayList<AuthorPublications>();
			while (rs.next()) {
				String pubName = rs.getString(1);
				String title = rs.getString(2);
				int year = rs.getInt(3);
				String pubType = rs.getString(4);
				AuthorPublications row = new AuthorPublications(pubName, title, year, pubType);
				resultantList.add(row);
			}

			return resultantList;
		} catch (SQLException e) {
			logger.error("Failed to obtain publication details of author");
		}

		return null;
	}


	/**
	 * Gets the author committee details.
	 *
	 * @param authorName the author name
	 * @return the author committee details
	 */
	public ArrayList<AuthorCommitteeDetails> getAuthorCommitteeDetails(String authorName){
		PreparedStatement preparedStatement = null;

		String selectQuery = "SELECT com.conferenceName, com.year "
				+ " FROM committee com "
				+ " where memberName = ?;";

		try {
			preparedStatement = conn.prepareStatement(selectQuery);
			preparedStatement.setString(1, authorName);

			ResultSet rs = preparedStatement.executeQuery();

			ArrayList<AuthorCommitteeDetails> resultantList = new ArrayList<AuthorCommitteeDetails>();
			while (rs.next()) {
				String name = rs.getString(1);
				int year = rs.getInt(2);

				AuthorCommitteeDetails row = new AuthorCommitteeDetails(name, year);
				resultantList.add(row);
			}

			return resultantList;
		} catch (SQLException e) {
			logger.error("Failed to obtain committee details of author");
		}

		return null;
	}
}
