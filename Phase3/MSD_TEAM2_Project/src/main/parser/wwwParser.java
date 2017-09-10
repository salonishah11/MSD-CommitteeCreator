package main.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class wwwParser {

	private www wwwData;
	private ParserDBConnector conn;
	private int wwwCnt;
	private static Logger logger = LogManager.getLogger(ParseXMLComponent.class);

	public wwwParser(ParserDBConnector conn, Logger logger) {
		wwwCnt = 0;
		this.conn = conn;
		logger = logger;
	}

	public void setwwwData(String key) {
		this.wwwData = new www();
		this.wwwData.setKey(key);
	}

	// method to insert the www records into the www data table once a limit of
	// 1000 records has reached
	public void incrementwwwCount() {
		wwwCnt += 1;
		if (wwwCnt % 1000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("www");

		}
	}

	// method to prepare www record to be inserted into the www table
	public void prepareWWWRecord() {
		for (String author : wwwData.getAuthorName()) {
			conn.preparewwwAuthorRecord(author, wwwData);
		}
		conn.preparewwwRecord(wwwData);

	}

	// method to process www record and set the corresponding field of the paper
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
