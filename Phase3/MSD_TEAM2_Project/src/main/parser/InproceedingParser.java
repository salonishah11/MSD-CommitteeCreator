package main.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// class including methods to parse the data related to inproceedings
public class InproceedingParser {

	private Paper paper;
	private ParserDBConnector conn;
	private int inproceedingCnt;
	private Logger logger;

	public InproceedingParser(ParserDBConnector conn, Logger logger) {
		inproceedingCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	// method to set the key value of the inproceeding
	public void setPaper(String key) {
		this.paper = new Paper();
		this.paper.setKey(key);
	}

	// insert the batch of inproceeding records accumulated
	public void incrementInProceedingCount() {
		inproceedingCnt += 1;
		if (inproceedingCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("inproceedings");
		}
	}

	// method to prepare a inproceeding database record to be inserted into the
	// paper table
	public void prepareInproceedingRecord() {
		if (requiredkeyval(paper.getKey())) {
			for (String author : paper.getAuthorName()) {
				conn.preprareInproceedingAuthorRecord(author, paper);
			}
			conn.preprareInproceedingRecord(paper);
		}

	}

	// method to match the type of the article being parsed based on the name of
	// the conferences/journals
	// in the keyVal of the paper
	public boolean requiredkeyval(String key) {
		String regex = "(.*/oopsla/.*)|(.*/pldi/.*)|(.*/ecoop/.*)|(.*/icfp/.*)|(.*/toplas/.*)|(.*/tse/.*)";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(key);
		if (m.find()) {
			return true;
		}
		return false;
	}

	// method to process inproceeding record tag by tag
	// and correspondingly set the parameters of the paper object
	public void processInproceedingRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			paper.setAuthorName(tagVal);
		} else if (rawName == "title") {
			paper.setTitle(tagVal);
		} else if (rawName == "pages") {
			// setPagesInPaper(tagVal);
			paper.setPages(tagVal);
		} else if (rawName == "year") {
			paper.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "crossref") {
			paper.setCrossRef(tagVal);
		} else if (rawName == "booktitle") {
			paper.setBookTitle(tagVal);
		} else if (rawName == "url") {
			paper.setUrl(tagVal);
		} else if (rawName == "ee") {
			paper.setEE(tagVal);
		}
	}
}
