package com.msd.DBLPParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.model.Thesis;

/**
 * The Class MasterThesisParser includes methods to parse master thesis records.
 */
public class MasterThesisParser {

	/** The thesis object state maintained */
	private Thesis thesis;
	
	/** The database connection object. */
	private ParserDBConnector conn;
	
	/** The count of masters thesis records. */
	private int mastersThesisCnt;
	
	/** The logger. */
	private Logger logger;

	/**
	 * Instantiates a new master thesis parser.
	 *
	 * @param conn the database connection object
	 * @param logger the logger
	 */
	public MasterThesisParser(ParserDBConnector conn, Logger logger) {
		this.mastersThesisCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	/**
	 * Sets the masters thesis.
	 *
	 * @param key the new masters thesis key 
	 */
	public void setMastersThesis(String key) {
		this.thesis = new Thesis();
		this.thesis.setKey(key);
	}
	
	
	/**
	 * Increment masters thesis count and run execute batch when batch count is triggered
	 */
	public void incrementMastersThesisCount() {
		mastersThesisCnt += 1;
		if (mastersThesisCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("mastersthesis");

		}
	}

	/**
	 * Prepare masters thesis record by adding to current batch to be executed.
	 */
	public void prepareMastersThesisRecord() {
		for (String author : thesis.getAuthorName()) {
			conn.preprareMasterThesisAuthorRecord(author, thesis);
		}
		conn.preprareMasterThesisRecord(thesis);

	}

	/**
	 * Process masters thesis record and set specific fields based on the input raw tag name
	 *
	 * @param rawName the raw tag name
	 * @param tagVal the tag val
	 */
	public void processMastersThesisRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			thesis.setAuthorName(tagVal);
		} else if (rawName == "title") {
			thesis.setTitle(tagVal);
		} else if (rawName == "year") {
			thesis.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "school") {
			thesis.setSchool(tagVal);
		} else if (rawName == "url") {
			thesis.setUrl(tagVal);
		} else if (rawName == "ee") {
			thesis.setEe(tagVal);
		}
	}

}
