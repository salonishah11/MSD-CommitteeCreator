package main.parser;

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

import main.parser.db.DBConnection;

public class ParserDBConnector {

	public Connection conn;
	private Logger logger;
	private PreparedStatement stmntInproceeding;
	private PreparedStatement stmntInproceedingAuthor;
	private PreparedStatement stmntArticleAuthor;
	private PreparedStatement stmntArticle;
	private PreparedStatement stmntwww;
	private PreparedStatement stmntwwwAuthor;
	private PreparedStatement stmntPhdThesis;
	private PreparedStatement stmntPhdThesisAuthor;
	private PreparedStatement stmntMastersThesis;
	private PreparedStatement stmntMastersThesisAuthor;
	private PreparedStatement stmntCommittee;

	public ParserDBConnector(Logger logger) {
		try {
			
			// create connection to the database using jdbc
			this.conn = DBConnection.getConnection();
			this.conn.setAutoCommit(false);
			this.logger = logger;
			
			// initialize preparedstatements for inserting inproceeding, article, www, mastersthesis and phdthesis
			// records into paper, thesis and www tables, respectively
			// initialize preparedstatements for inserting authors pertaining to article, www, thesis, and inproceedings
			// into paperAuthor table with the type flag as the differentiator
			stmntInproceeding = conn.prepareStatement("insert ignore into paper(keyVal,title,type,pages"
					+ ",year,crossRef,bookTitle,url,ee) values(?,?,?,?,?,?,?,?,?)");
			stmntInproceedingAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type) " + " values(?,?,?)");
			stmntArticleAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type)" + " values(?,?,?)");
			stmntArticle = conn.prepareStatement("insert ignore into paper(keyVal,title,type,pages"
					+ ",year,number,volume,journal,url,ee) values(?,?,?,?,?,?,?,?,?,?)");
			stmntwww = conn.prepareStatement("insert ignore into www(keyVal,title,year,url) values(?,?,?,?)");
			stmntwwwAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type)" + " values(?,?,?)");
			stmntPhdThesis = conn.prepareStatement(
					"insert into thesis(keyVal,title,type,pages,year,school) " + " values(?,?,?,?,?,?)");
			stmntPhdThesisAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type) " + "values(?,?,?)");
			stmntMastersThesis = conn.prepareStatement(
					"insert into thesis(keyVal,title,type,year,school,url,ee) " + "values(?,?,?,?,?,?,?)");
			stmntMastersThesisAuthor = conn
					.prepareStatement("insert ignore into paperauthor(keyVal,authorName,type) " + "values(?,?,?)");
			stmntCommittee = conn.prepareStatement(
					"insert ignore into " + "committee(conferenceName,memberName,memberType,year,type) values(?,?,?,?,?)");

		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public void commit() {
		try {
			conn.commit();
		} catch (Exception e) {
			logger.error("Exception while commiting :" + e);
		}
	}

	/*
	public void createAuthors() {
		try {
			Statement stmnt = conn.createStatement();
			stmnt.execute("insert into author(name) " + "select distinct authorName from paperauthor");
			stmnt.close();
			conn.commit();
		} catch (SQLException e) {
			logger.error("Failed to normalize tables due to :" + e);
			e.printStackTrace();
		}
	}
	*/

	/*
	public void closedatabaseConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Failed to close database connection :" + e);
		}
	}
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

	// method to prepare inproceeding record by setting the values for each of the column parameters
	// to be inserted into the paper table with type being inproceedings
	public void preprareInproceedingRecord(Paper paper) {
		try {
			stmntInproceeding.setString(1, paper.getKey());
			stmntInproceeding.setString(2, paper.getTitle());
			stmntInproceeding.setString(3, "inproceedings");
			stmntInproceeding.setString(4, paper.getPages());
			stmntInproceeding.setInt(5, paper.getYear());
			stmntInproceeding.setString(6, paper.getCrossRef());
			stmntInproceeding.setString(7, paper.getBookTitle());
			stmntInproceeding.setString(8, paper.getUrl());
			stmntInproceeding.setString(9, paper.getEE());
			stmntInproceeding.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare inproceedingauthor record:" + e);
		}
	}

	// method to prepare record for each of the authors of masters thesis paper
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

	// method to prepare mastersthesis record to be inserted into the thesis table
	public void preprareMasterThesisRecord(Thesis thesis) {
		try {
			stmntMastersThesis.setString(1, thesis.getKey());
			stmntMastersThesis.setString(2, thesis.getTitle());
			stmntMastersThesis.setString(3, "mastersthesis");
			stmntMastersThesis.setInt(4, thesis.getYear());
			stmntMastersThesis.setString(5, thesis.getSchool());
			stmntMastersThesis.setString(6, thesis.getUrl());
			stmntMastersThesis.setString(7, thesis.getEe());
			stmntMastersThesis.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare master thesis record:" + e);
		}
	}

	// method to prepare author record for each of the author of phdthesis type
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

	// method to prepare phd thesis record to be inserted to the thesis table with type being set to phdthesis
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

	// method to prepare www type of record to be inserted into the www table
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

	// method invoked by preparearticle method
	// invoked for each author of the article
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

	// method to prepare record for article type of paper
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
			stmntArticle.setString(9, article.getUrl());
			stmntArticle.setString(10, article.getEE());
			stmntArticle.addBatch();
		} catch (SQLException e) {
			logger.error("Unable to prepare article record:" + e);
		}
	}

	// method to execute the insertion of a batch of records for different types of records
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
			}
		} catch (SQLException e) {
			logger.error("Error commiting batch: " + e);
		}
	}

	// method to validate whether the title field contains any characters other than utf-8
	// since the insertion to the mysql database tables fails if the values are not of utfmb4 type
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
			//e.printStackTrace();
			return false;
		}
	}
}
