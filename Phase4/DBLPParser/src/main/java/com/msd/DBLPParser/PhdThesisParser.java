package com.msd.DBLPParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.model.Thesis;

/**
 * The Class PhdThesisParser includes methods to parse phd thesis records.
 */
public class PhdThesisParser {

	/** The thesis. */
	private Thesis thesis;
	
	/** The database connection object. */
	private ParserDBConnector conn;
	
	/** The PreparedStatement phd thesis. */
	private PreparedStatement stmntPhdThesis;
	
	/** The PreparedStatement phd thesis author. */
	private PreparedStatement stmntPhdThesisAuthor;
	
	/** The thesis cnt. */
	private int thesisCnt;
	
	/** The logger. */
	private Logger logger;

	/**
	 * Instantiates a new phd thesis parser.
	 *
	 * @param conn the database connection object
	 * @param logger the logger
	 */
	public PhdThesisParser(ParserDBConnector conn, Logger logger) {
		this.thesisCnt = 0;
		this.stmntPhdThesis = stmntPhdThesis;
		this.conn = conn;
		this.stmntPhdThesisAuthor = stmntPhdThesisAuthor;
		this.logger = logger;
	}

	/**
	 * Sets the thesis record key
	 *
	 * @param key the new thesis
	 */
	public void setThesis(String key) {
		this.thesis = new Thesis();
		this.thesis.setKey(key);
	}


	/**
	 * Increment phd thesis count to keep track of the number of phd thesis records and insert the batch size of 10 is reached
	 */
	public void incrementPhdThesisCount() {
		thesisCnt += 1;
		if (thesisCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("phdthesis");
		}
	}

	/**
	 * Prepares the phd thesis record to be inserted into the thesis table 
	 */ 
	public void preparePhdThesisRecord() {
		for (String author : thesis.getAuthorName()) {
			conn.preprarePhdThesisAuthorRecord(author, thesis);

		}
		conn.preprarePhdThesisRecord(thesis);

	}

	/**
	 * Process phd thesis record.
	 *
	 * @param rawName the raw tag name
	 * @param tagVal the tag val
	 */
	public void processPhdThesisRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			thesis.setAuthorName(tagVal);
		} else if (rawName == "title") {
			thesis.setTitle(tagVal);
		} else if (rawName == "pages") {
			thesis.setPages(tagVal);
		} else if (rawName == "year") {
			thesis.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "school") {
			thesis.setSchool(tagVal);
		}
	}
}
