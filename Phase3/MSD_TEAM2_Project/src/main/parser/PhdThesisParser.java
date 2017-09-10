package main.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;

// class PhdThesisParser to handle the parsing of phdthesis data present in the dblp.xml
public class PhdThesisParser {

	private Thesis thesis;
	private ParserDBConnector conn;
	private PreparedStatement stmntPhdThesis;
	private PreparedStatement stmntPhdThesisAuthor;
	private int thesisCnt;
	private Logger logger;

	public PhdThesisParser(ParserDBConnector conn, Logger logger) {
		this.thesisCnt = 0;
		this.stmntPhdThesis = stmntPhdThesis;
		this.conn = conn;
		this.stmntPhdThesisAuthor = stmntPhdThesisAuthor;
		this.logger = logger;
	}

	// method to set the key val of the phd thesis object
	public void setThesis(String key) {
		this.thesis = new Thesis();
		this.thesis.setKey(key);
	}

	// method to keep track of the number of phd thesis records and insert the
	// set of records once the limit
	// of batch size of 10 is reached
	public void incrementPhdThesisCount() {
		thesisCnt += 1;
		if (thesisCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("phdthesis");
		}
	}

	// prepares the phd thesis record to be inserted into the thesis table
	public void preparePhdThesisRecord() {
		for (String author : thesis.getAuthorName()) {
			conn.preprarePhdThesisAuthorRecord(author, thesis);

		}
		conn.preprarePhdThesisRecord(thesis);

	}

	// processes the record based on the tag element and sets the coresponding
	// values of the thesis object
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
