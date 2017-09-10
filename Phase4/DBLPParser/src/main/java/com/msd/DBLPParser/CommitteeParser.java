package com.msd.DBLPParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

/**
 * The Class CommitteeParser to parse the committee data from the provided dblp database
 */
public class CommitteeParser {
	
	/** The logger. */
	private Logger logger;
	
	/** The object of class responsible for database operations. */
	private ParserDBConnector conn;

	/**
	 * Instantiates a new committee parser.
	 *
	 * @param logger the logger
	 * @param conn the database connection object
	 */
	public CommitteeParser(Logger logger, ParserDBConnector conn) {
		this.logger = logger;
		this.conn = conn;
	}

	/**
	 * Parses the committee data.Parse each and every file of committee directory, extract the name and year form each
	 * file name, parse the content of each file and extract the names of authors
	 * @param dirName the dir name
	 * @return true, if successful
	 */
	public boolean parseCommitteeData(File dirName) {
		try {
			String[] memberDetails;
			String memberName;
			String memberType;
			for (File filename : dirName.listFiles()) {
				String[] committeeDetails = parseCommitteeFileName(filename.getName());
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String committeeData;
				committeeData = br.readLine();
				while (committeeData != null && committeeData.trim().length() > 0) {
					memberDetails = committeeData.split(":");
					if (memberDetails.length == 1) {
						memberName = memberDetails[0];
						memberType = "";
					} else {
						memberType = memberDetails[0];
						memberName = memberDetails[1];
					}
					memberName = Jsoup.parse(memberName).text();
					conn.prepareCommitteeRecord(committeeDetails[0], memberName, memberType,
							Integer.parseInt(committeeDetails[1]));

					committeeData = br.readLine();
				}
				conn.executeBatch("committee");
				br.close();
			}
		} catch (Exception e) {
			logger.error("Encountered exception :" + e);
			return false;
		}
		return true;
	}

	// m
	/**
	 * Method to parse the the name of the committee data file and extract the filename
	 * @param filename Committee Filename
	 * @return the string[]
	 */
	// the conferences name and the year of publication
	public static String[] parseCommitteeFileName(String filename) {
		String pattern = "[a-z]+|\\d+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(filename);
		String[] committeeDetails = new String[2];
		if (m.find()) {
			committeeDetails[0] = m.group();
			m.find();
			committeeDetails[1] = m.group();
		}
		return committeeDetails;
	}
}
