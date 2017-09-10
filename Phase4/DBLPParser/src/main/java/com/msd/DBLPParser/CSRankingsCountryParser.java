package com.msd.DBLPParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

/**
 * The Class CSRankingsCountryParser to parse the csrankings region affiliation data
 */
public class CSRankingsCountryParser {
	
	/** The logger. */
	private Logger logger;
	
	/** The database operation containing class object. */
	private ParserDBConnector conn;

	/**
	 * Instantiates a new CS rankings country parser.
	 *
	 * @param logger the logger
	 * @param conn Connector to database 
	 */
	public CSRankingsCountryParser(Logger logger, ParserDBConnector conn) {
		this.logger = logger;
		this.conn = conn;
	}

	//
	/**
	 * Parse the content of csrankings file and extract the names of authors and their related affiliation
	 *
	 * @param filename the csrankings data containing filename
	 * @return true, if successful
	 */

	public boolean parseCSRankings(File filename) {
		try {
			String[] universityCountryDetails;
			String univName;
			String regionName;
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String universityCountryData = br.readLine();
			while ((universityCountryData = br.readLine()) != null) {
				universityCountryDetails = universityCountryData.split(",");
				univName = universityCountryDetails[0].trim();
				regionName = universityCountryDetails[1].trim();
				univName = Jsoup.parse(univName).text();
				conn.prepareCSRankingsUnivRegionRecord(univName,regionName);
			}
			conn.executeBatch("csrankingsunivregion");
			br.close();
		} catch (Exception e) {
			logger.error("Encountered exception :" + e);
			return false;
		}
		return true;
	}
}
