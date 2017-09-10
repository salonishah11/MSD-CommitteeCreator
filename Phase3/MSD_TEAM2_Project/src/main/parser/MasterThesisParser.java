package main.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

public class MasterThesisParser {

	private Thesis thesis;
	private ParserDBConnector conn;
	private int mastersThesisCnt;
	private Logger logger;

	public MasterThesisParser(ParserDBConnector conn, Logger logger) {
		this.mastersThesisCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	public void setMastersThesis(String key) {
		this.thesis = new Thesis();
		this.thesis.setKey(key);
	}
	
	
	public void incrementMastersThesisCount() {
		mastersThesisCnt += 1;
		if (mastersThesisCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("mastersthesis");

		}
	}

	public void prepareMastersThesisRecord() {
		for (String author : thesis.getAuthorName()) {
			conn.preprareMasterThesisAuthorRecord(author, thesis);
		}
		conn.preprareMasterThesisRecord(thesis);

	}

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
