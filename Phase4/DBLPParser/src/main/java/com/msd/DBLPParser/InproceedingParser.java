package com.msd.DBLPParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.model.Paper;

/**
 * The Class InproceedingParser includes methods to parse the data related to inproceedings
 */
public class InproceedingParser {

	/** The paper object state maintained */
	private Paper paper;

	/** The database connection object. */
	private ParserDBConnector conn;

	/** The inproceeding record count. */
	private int inproceedingCnt;

	/** The logger. */
	private Logger logger;

	/**
	 * Instantiates a new inproceeding parser.
	 *
	 * @param conn
	 *            the database connection object
	 * @param logger
	 *            the logger
	 */
	public InproceedingParser(ParserDBConnector conn, Logger logger) {
		inproceedingCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	/**
	 * Sets the paper.
	 *
	 * @param key
	 *            the key associated with paper
	 */
	public void setPaper(String key) {
		this.paper = new Paper();
		this.paper.setKey(key);
	}

	/**
	 * Increment in proceeding count and insert the batch of inproceeding records accumulated
	 */
	public void incrementInProceedingCount() {
		inproceedingCnt += 1;
		if (inproceedingCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("inproceedings");
		}
	}


	/**
	 * Prepare inproceeding record to be inserted into the paper table
	 */
	public void prepareInproceedingRecord() {
		for (String author : paper.getAuthorName()) {
			conn.preprareInproceedingAuthorRecord(author, paper);
		}
		conn.preprareInproceedingRecord(paper);

	}


	/**
	 * Process inproceeding record tag and set the same in the object maintained.
	 *
	 * @param rawName
	 *            the raw tag name of the record
	 * @param tagVal
	 *            the tag value of the record
	 */
	// and correspondingly set the parameters of the paper object
	public void processInproceedingRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			paper.setAuthorName(tagVal);
		} else if (rawName == "title") {
			paper.setTitle(tagVal);
		} else if (rawName == "pages") {
			paper.setPages(tagVal);
		} else if (rawName == "year") {
			paper.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "crossref") {
			paper.setCrossRef(tagVal);
		} else if (rawName == "booktitle") {
			paper.setBookTitle(tagVal);
		}
	}
}
