package com.msd.DBLPParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.model.www;


/**
 * The Class wwwParser includes methods to parse www records.
 */
public class wwwParser {

	/** The www data. */
	private www wwwData;
	
	/** The conn. */
	private ParserDBConnector conn;
	
	/** The www cnt. */
	private int wwwCnt;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(ParseXMLComponent.class);

	/**
	 * Instantiates a new www parser.
	 *
	 * @param conn the conn
	 * @param logger the logger
	 */
	public wwwParser(ParserDBConnector conn, Logger logger) {
		wwwCnt = 0;
		this.conn = conn;
		logger = logger;
	}

	/**
	 * Sets the www data.
	 *
	 * @param key the new www data
	 */
	public void setwwwData(String key) {
		this.wwwData = new www();
		this.wwwData.setKey(key);
	}

	
	/**
	 * Increment www count and insert the www records into the www data table once a limit of 1000 records has reached
	 */
	public void incrementwwwCount() {
		wwwCnt += 1;
		if (wwwCnt % 1000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("www");

		}
	}

	/**
	 * Method to prepare www record to be inserted into the www table
	 */
	public void prepareWWWRecord() {
		for (String author : wwwData.getAuthorName()) {
			conn.preparewwwAuthorRecord(author, wwwData);
		}
		conn.preparewwwRecord(wwwData);

	}

	/**
	 * Method to process www record and set the corresponding field of the paper
	 *
	 * @param rawName the raw tag name
	 * @param tagVal the tag val
	 */
	// 
	public void processWWWRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			wwwData.setAuthorName(tagVal);
		} else if (rawName == "title") {
			wwwData.setTitle(tagVal);
		} else if (rawName == "year") {
			wwwData.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "url") {
			wwwData.setUrl(tagVal);
		}
	}

}
