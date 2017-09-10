package com.msd.DBLPParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

/**
 * The Class CSRankingsParser.
 */
// CSRankingsParser class to parse the csrankings affiliation data
public class CSRankingsParser {
	
	/** The logger. */
	private Logger logger;
	
	/** The database connection. */
	private ParserDBConnector conn;

	/**
	 * Instantiates a new CS rankings parser.
	 *
	 * @param logger the logger
	 * @param conn The database connection
	 */
	public CSRankingsParser(Logger logger, ParserDBConnector conn) {
		this.logger = logger;
		this.conn = conn;
	}

	
	/**
	 * Parses the CS rankings university-region mapping results
	 *
	 * @param filename the filename
	 * @return true, if successful
	 */
	public boolean parseCSRankings(File filename) {
		try {
			String[] authorAffiliationDataDetails;
			String authorName;
			String affiliation;
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String authorAffiliationData = br.readLine();
			while ((authorAffiliationData = br.readLine()) != null) {
				authorAffiliationDataDetails = authorAffiliationData.split(",");
				authorName = authorAffiliationDataDetails[0].trim();
				affiliation = authorAffiliationDataDetails[1].trim();
				authorName = Jsoup.parse(authorName).text();
				conn.prepareCSRankingsRecord(authorName,affiliation);
			}
			conn.executeBatch("csrankings");
			br.close();
		} catch (Exception e) {
			logger.error("Encountered exception :" + e);
			return false;
		}
		return true;
	}
}
