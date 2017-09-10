package com.msd.DBLPParser;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Logger;
import com.msd.DBLPParser.db.*;
import com.msd.DBLPParser.model.Paper;
import com.msd.DBLPParser.model.Thesis;
import com.msd.DBLPParser.model.www;

/**
 * The Class ParserDBConnector performs all the parser operation that needs to interact with database.
 */
public class ParserDBConnector {

	/** The database connection. */
	public Connection conn;
	
	/** The logger. */
	private Logger logger;
	
	/** The PreparedStatement for inproceeding record. */
	private PreparedStatement stmntInproceeding;
	
	/** The PreparedStatement for inproceeding author record */
	private PreparedStatement stmntInproceedingAuthor;
	
	/** The PreparedStatement for article author record */
	private PreparedStatement stmntArticleAuthor;
	
	/** The PreparedStatement article. */
	private PreparedStatement stmntArticle;
	
	/** The PreparedStatement www. */
	private PreparedStatement stmntwww;
	
	/** The PreparedStatement author. */
	private PreparedStatement stmntwwwAuthor;
	
	/** The PreparedStatement phd thesis. */
	private PreparedStatement stmntPhdThesis;
	
	/** The PreparedStatement phd thesis author. */
	private PreparedStatement stmntPhdThesisAuthor;
	
	/** The PreparedStatement masters thesis. */
	private PreparedStatement stmntMastersThesis;
	
	/** The PreparedStatement masters thesis author. */
	private PreparedStatement stmntMastersThesisAuthor;
	
	/** The PreparedStatement committee. */
	private PreparedStatement stmntCommittee;
	
	/** The PreparedStatement CS rankings. */
	private PreparedStatement stmntCSRankings;
	
	/** The PreparedStatement CS university region. */
	private PreparedStatement stmntCSUniversityRegion;

	/**
	 * Instantiates a new parser DB connector.
	 *
	 * @param logger the logger
	 */
	public ParserDBConnector(Logger logger) {
		try {

			// create connection to the database using jdbc
			this.conn = DBConnection.getConnection();
			this.conn.setAutoCommit(false);
			this.logger = logger;

			// initialize preparedstatements for inserting inproceeding,
			// article, www, mastersthesis,phdthesis,csrankings data
			stmntInproceeding = conn.prepareStatement("insert ignore into paper(keyVal,title,type,pages"
					+ ",year,crossRef,bookTitle) values(?,?,?,?,?,?,?)");
			stmntInproceedingAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type) " + " values(?,?,?)");
			stmntArticleAuthor = conn
					.prepareStatement("insert ignore into journalauthor(keyVal,authorName,type)" + " values(?,?,?)");
			stmntArticle = conn.prepareStatement("insert ignore into journalpaper(keyVal,title,type,pages"
					+ ",year,number,volume,journal) values(?,?,?,?,?,?,?,?)");
			stmntwww = conn.prepareStatement("insert ignore into www(keyVal,title,year,url) values(?,?,?,?)");
			stmntwwwAuthor = conn
					.prepareStatement("insert ignore into wwwauthor(keyVal,authorName,type)" + " values(?,?,?)");
			stmntPhdThesis = conn.prepareStatement(
					"insert into thesis(keyVal,title,type,pages,year,school) " + " values(?,?,?,?,?,?)");
			stmntPhdThesisAuthor = conn
					.prepareStatement("insert ignore into thesisauthor(keyVal,authorName,type) " + "values(?,?,?)");
			stmntMastersThesis = conn
					.prepareStatement("insert into thesis(keyVal,title,type,year,school) " + "values(?,?,?,?,?)");
			stmntMastersThesisAuthor = conn
					.prepareStatement("insert ignore into thesisauthor(keyVal,authorName,type) " + "values(?,?,?)");
			stmntCommittee = conn.prepareStatement("insert ignore into "
					+ "committee(conferenceName,memberName,memberType,year,type) values(?,?,?,?,?)");
			stmntCSRankings = conn
					.prepareStatement("insert ignore into " + "affiliation(authorname,university) values(?,?)");
			stmntCSUniversityRegion = conn
					.prepareStatement("insert ignore into " + "universitydetails(university,region) values(?,?)");

		} catch (SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * Issue commit to database
	 */
	public void commit() {
		try {
			conn.commit();
		} catch (Exception e) {
			logger.error("Exception while commiting :" + e);
		}
	}

	/**
	 * Execute all batches currently accumulated
	 */
	public void executeAllBatches() {
		try {
			stmntInproceeding.executeBatch();
			stmntInproceeding.clearBatch();
			stmntInproceedingAuthor.executeBatch();
			stmntInproceedingAuthor.clearBatch();
			stmntArticle.executeBatch();
			stmntArticle.clearBatch();
			stmntArticleAuthor.executeBatch();
			stmntArticleAuthor.clearBatch();
			stmntwww.executeBatch();
			stmntwww.clearBatch();
			stmntwwwAuthor.executeBatch();
			stmntwwwAuthor.clearBatch();
			stmntPhdThesis.executeBatch();
			stmntPhdThesisAuthor.clearBatch();
			stmntMastersThesis.executeBatch();

			stmntMastersThesisAuthor.clearBatch();
		} catch (Exception e) {
			logger.error("Error while commiting all batches" + e);
		}
	}

	/**
	 * Preprare inproceeding author record.
	 *
	 * @param author the author
	 * @param paper the paper
	 */
	// method to prepare author record each of the author of inproceedings
	public void preprareInproceedingAuthorRecord(String author, Paper paper) {
		try {
			stmntInproceedingAuthor.setString(1, paper.getKey());
			stmntInproceedingAuthor.setString(2, author);
			stmntInproceedingAuthor.setString(3, "inproceedings");
			stmntInproceedingAuthor.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare inproceedingauthor record:" + e);
		}
	}
 
	/**
	 * method to prepare inproceeding record by setting the values for each of the column parameters
	 *
	 * @param paper the paper
	 */
	public void preprareInproceedingRecord(Paper paper) {
		try {
			stmntInproceeding.setString(1, paper.getKey());
			stmntInproceeding.setString(2, paper.getTitle());
			stmntInproceeding.setString(3, "inproceedings");
			stmntInproceeding.setString(4, paper.getPages());
			stmntInproceeding.setInt(5, paper.getYear());
			stmntInproceeding.setString(6, paper.getCrossRef());
			stmntInproceeding.setString(7, paper.getBookTitle());
			stmntInproceeding.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare inproceedingauthor record:" + e);
		}
	}

	/**
	 * Prepare master thesis author record by setting required columns from the input object
	 *
	 * @param author the author
	 * @param thesis the thesis
	 */
	public void preprareMasterThesisAuthorRecord(String author, Thesis thesis) {
		try {
			stmntMastersThesisAuthor.setString(1, thesis.getKey());
			stmntMastersThesisAuthor.setString(2, author);
			stmntMastersThesisAuthor.setString(3, "mastersthesis");
			stmntMastersThesisAuthor.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare thesis author record:" + e);
		}
	}

	/**
	 * Prepare master thesis record by setting required columns from the input object
	 *
	 * @param thesis the thesis
	 */
	public void preprareMasterThesisRecord(Thesis thesis) {
		try {
			stmntMastersThesis.setString(1, thesis.getKey());
			stmntMastersThesis.setString(2, thesis.getTitle());
			stmntMastersThesis.setString(3, "mastersthesis");
			stmntMastersThesis.setInt(4, thesis.getYear());
			stmntMastersThesis.setString(5, thesis.getSchool());
			stmntMastersThesis.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare master thesis record:" + e);
		}
	}

	/**
	 * Prepare phd thesis author record by setting required columns from the input object
	 *
	 * @param author the author
	 * @param thesis the thesis
	 */
	public void preprarePhdThesisAuthorRecord(String author, Thesis thesis) {
		try {
			stmntPhdThesisAuthor.setString(1, thesis.getKey());
			stmntPhdThesisAuthor.setString(2, author);
			stmntPhdThesisAuthor.setString(3, "phdthesis");
			stmntPhdThesisAuthor.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare phd thesis author record:" + e);
		}

	}

	/**
	 * Prepare phd thesis record by setting required columns from the input object
	 *
	 * @param thesis the thesis
	 */
	public void preprarePhdThesisRecord(Thesis thesis) {
		try {
			stmntPhdThesis.setString(1, thesis.getKey());
			stmntPhdThesis.setString(2, thesis.getTitle());
			stmntPhdThesis.setString(3, "phdthesis");
			stmntPhdThesis.setString(4, thesis.getPages());
			stmntPhdThesis.setInt(5, thesis.getYear());
			stmntPhdThesis.setString(6, thesis.getSchool());
			stmntPhdThesis.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare phd thesis record record:" + e);
		}
	}

	/**
	 * Prepare www author record by setting required columns from the input object
	 *
	 * @param author the author
	 * @param wwwData the www data
	 */
	// method to prepare record for each of the author of www
	public void preparewwwAuthorRecord(String author, www wwwData) {
		try {
			stmntwwwAuthor.setString(1, wwwData.getKey());
			stmntwwwAuthor.setString(2, author);
			stmntwwwAuthor.setString(3, "www");
			stmntwwwAuthor.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare wwwauthor record:" + e);
		}
	}

	/**
	 * Prepar ewww record by setting required columns from the input object
	 *
	 * @param wwwData the www data
	 */
	public void preparewwwRecord(www wwwData) {
		try {
			stmntwww.setString(1, wwwData.getKey());
			stmntwww.setString(2, wwwData.getTitle());
			stmntwww.setInt(3, wwwData.getYear());
			stmntwww.setString(4, wwwData.getUrl());
			stmntwww.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare www record:" + e);
		}
	}

	/**
	 * Prepare committee record by setting required columns from the input object
	 *
	 * @param confName the conf name
	 * @param memberName the member name
	 * @param memberType the member type
	 * @param year the year
	 */
	// method to prepare committee record to be inserted to the committee table
	public void prepareCommitteeRecord(String confName, String memberName, String memberType, int year) {
		try {
			stmntCommittee.setString(1, confName);
			stmntCommittee.setString(2, memberName);
			stmntCommittee.setString(3, memberType);
			stmntCommittee.setInt(4, year);
			stmntCommittee.setString(5, "Conference");
			stmntCommittee.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare committee record:" + e);
		}
	}


	/**
	 * Prepare CS rankings record by setting required columns from the input object
	 *
	 * @param authorname the authorname
	 * @param affiliation the affiliation
	 */
	public void prepareCSRankingsRecord(String authorname, String affiliation) {
		try {
			stmntCSRankings.setString(1, authorname);
			stmntCSRankings.setString(2, affiliation);
			stmntCSRankings.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare committee record:" + e);
		}
	}


	/**
	 * Prepare CS rankings univ region record by setting required columns from the input object
	 *
	 * @param univName The university name
	 * @param region the region
	 */
	public void prepareCSRankingsUnivRegionRecord(String univName, String region) {
		try {
			stmntCSUniversityRegion.setString(1, univName);
			stmntCSUniversityRegion.setString(2, region);
			stmntCSUniversityRegion.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare committee record:" + e);
		}
	}

	/**
	 * Prepare article author record by setting required columns from the input object
	 *
	 * @param author the author
	 * @param article the article
	 */
	public void prepareArticleAuthorRecord(String author, Paper article) {
		try {
			stmntArticleAuthor.setString(1, article.getKey());
			stmntArticleAuthor.setString(2, author);
			stmntArticleAuthor.setString(3, "article");
			stmntArticleAuthor.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare article author record:" + e);
		}
	}

	/**
	 * Prepare article record by setting required columns from the input object
	 *
	 * @param article the article
	 */
	public void prepareArticleRecord(Paper article) {
		try {
			stmntArticle.setString(1, article.getKey());
			if (!isUTF8MisInterpreted(article.getTitle())) {
				stmntArticle.setString(2, null);
			} else {
				stmntArticle.setString(2, article.getTitle());
			}
			stmntArticle.setString(3, "article");
			stmntArticle.setString(4, article.getPages());
			stmntArticle.setInt(5, article.getYear());
			stmntArticle.setString(6, article.getNumber());
			stmntArticle.setString(7, article.getVolume());
			stmntArticle.setString(8, article.getJournal());
			stmntArticle.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare article record:" + e);
		}
	}


	/**
	 * Execute specific batch based on the key which is given as input.
	 *
	 * @param key the key associated with type of record batch to be inserted to database.
	 */
	public void executeBatch(String key) {
		try {
			if (key == "inproceedings") {
				stmntInproceeding.executeBatch();
				stmntInproceeding.clearBatch();
				stmntInproceedingAuthor.executeBatch();
				stmntInproceedingAuthor.clearBatch();
				conn.commit();
			} else if (key == "mastersthesis") {
				stmntMastersThesis.executeBatch();
				stmntMastersThesis.clearBatch();
				stmntMastersThesisAuthor.executeBatch();
				stmntMastersThesisAuthor.clearBatch();
				conn.commit();
			} else if (key == "phdthesis") {
				stmntPhdThesis.executeBatch();
				stmntPhdThesis.clearBatch();
				stmntPhdThesisAuthor.executeBatch();
				stmntPhdThesisAuthor.clearBatch();
				conn.commit();
			} else if (key == "www") {
				stmntwww.executeBatch();
				stmntwww.clearBatch();
				stmntwwwAuthor.executeBatch();
				stmntwwwAuthor.clearBatch();
				conn.commit();
			} else if (key == "committee") {
				stmntCommittee.executeBatch();
				stmntCommittee.clearBatch();
				conn.commit();
			} else if (key == "article") {
				stmntArticle.executeBatch();
				stmntArticle.clearBatch();
				stmntArticleAuthor.executeBatch();
				stmntArticleAuthor.clearBatch();
				conn.commit();
			} else if (key == "csrankings") {
				stmntCSRankings.executeBatch();
				stmntCSRankings.clearBatch();
				conn.commit();
			} else if (key == "csrankingsunivregion") {
				stmntCSUniversityRegion.executeBatch();
				stmntCSUniversityRegion.clearBatch();
				conn.commit();
			}
		} catch (SQLException e) {
			logger.error("Error commiting batch: " + e);
		}
	}


	/**
	 * Method to validate whether the title field contains any characters other than utf-8
	 *
	 * @param input the input
	 * @return true, if is UTF 8 mis interpreted
	 */
	public boolean isUTF8MisInterpreted(String input) {
		String encoding = "Windows-1252";
		CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPLACE);
		decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
		CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
		ByteBuffer tmp;
		try {
			tmp = encoder.encode(CharBuffer.wrap(input));
		} catch (CharacterCodingException e) {
			logger.error("Found unmappable character exception:" + e);
			return false;
		}
		try {
			if (tmp != null) {
				decoder.decode(tmp);
			}
			return true;
		} catch (CharacterCodingException e) {
			return false;
		}
	}
}
