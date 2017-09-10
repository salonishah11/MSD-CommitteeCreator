package com.msd.DBLPParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.model.Paper;

/**
 * The Class ArticleParser maintains an article object and sets the data
 * associated with that based on the data parsed by DBLPParser
 */
// ArticleParser class associated with a paper of type article
public class ArticleParser {

	/** The article. */
	private Paper article;

	/** The conn. */
	private ParserDBConnector conn;

	/** The article cnt. */
	private int articleCnt;

	/** The logger. */
	private Logger logger;

	// constructor to initialize the article count, logger and db connection
	/**
	 * Instantiates a new article parser.
	 *
	 * @param conn
	 *            the database connection
	 * @param logger
	 *            Logger object to log information
	 */
	// object
	public ArticleParser(ParserDBConnector conn, Logger logger) {
		this.articleCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	/**
	 * Sets the article.
	 *
	 * @param key
	 *            the new article
	 */
	public void setArticle(String key) {
		this.article = new Paper();
		this.article.setKey(key);
	}

	/**
	 * method to keep track of the article count being parsed and insert them at
	 * once when it reaches bat
	 */
	public void incrementArticleCount() {
		articleCnt += 1;
		if (articleCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("article");
		}
	}

	/**
	 * method to prepare article record to be inserted into the paper table
	 */
	public void prepareArticleRecord() {
		for (String author : article.getAuthorName()) {
			conn.prepareArticleAuthorRecord(author, article);
		}
		conn.prepareArticleRecord(article);

	}

	/**
	 * Process article record.
	 *
	 * @param rawName
	 *            the raw name from the xml record parsed
	 * @param tagVal
	 *            the tag val from the xml record parsed
	 */
	public void processArticleRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			article.setAuthorName(tagVal);
		} else if (rawName == "title") {
			article.setTitle(tagVal);
		} else if (rawName == "pages") {
			article.setPages(tagVal);
		} else if (rawName == "year") {
			article.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "journal") {
			article.setJournal(tagVal);
		} else if (rawName == "number") {
			article.setNumber(tagVal);
		} else if (rawName == "volume") {
			article.setVolume(tagVal);
		}
	}

}
