package com.msd.queryengine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.SearchParameters;

/**
 * The Class QueryExecutor executes the prepared query by QueryBuilder
 */
public class QueryExecutor {
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(QueryExecutor.class);
	
	/**
	 * Execute the prepared query to fetch the required results
	 *
	 * @param querystmnt The prepared query statement
	 * @param searchParams The search parameters entered by the user.
	 * @return The List of authors satisfying the given prepared query.
	 */
	public ArrayList<Author> executeQuery(PreparedStatement querystmnt, SearchParameters searchParams) {
		try {
			ResultSet rs = querystmnt.executeQuery();
			ArrayList<Author> resultantAuthorList = new ArrayList<Author>();
			while (rs.next()) {
				String name = rs.getString("name");
				Author author;
				if (searchParams.getSelectColumns()!=null && searchParams.getSelectColumns().contains("title,year")) {
					String title = rs.getString("title");
					String type = rs.getString("type");
					int year = rs.getInt("year");
					String publicationName = rs.getString("event_name");
					author = new Author(name, title, type, year,publicationName);
				} else {
					author = new Author(name);
				}
				resultantAuthorList.add(author);
			}

			return resultantAuthorList;
		} catch (SQLException e) {
			logger.error("Failed to execute SQL Query");
		}
		return null;
	}
}
