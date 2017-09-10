package main.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Logger;

// ArticleParer class associated with a paper of type article
public class ArticleParser {

	private Paper article;
	private ParserDBConnector conn;
	private int articleCnt;
	private Logger logger;

	// constructor to initialize the article count, logger and db connection
	// object
	public ArticleParser(ParserDBConnector conn, Logger logger) {
		this.articleCnt = 0;
		this.conn = conn;
		this.logger = logger;
	}

	// set the key of the article object
	public void setArticle(String key) {
		this.article = new Paper();
		this.article.setKey(key);
	}
	
	// method to keep track of the article count being parsed and insert them at
	// once when it reaches
	// a batch size of 10000
	public void incrementArticleCount() {
		articleCnt += 1;
		if (articleCnt % 10000 == 0 || System.getProperty("testMode") == "ON") {
			conn.executeBatch("article");
		}
	}

	// method to prepare article record to be inserted into the paper table
		public void prepareArticleRecord() {
			if (requiredkeyval(article.getKey())) {
				for (String author : article.getAuthorName()) {
					conn.prepareArticleAuthorRecord(author, article);
				}
				conn.prepareArticleRecord(article);
			}

		}

	// check whether the keyval of the article being parsed has the conferences
	// or journals names expected
	public boolean requiredkeyval(String key) {
		String regex = "(.*/oopsla/.*)|(.*/pldi/.*)|(.*/ecoop/.*)|(.*/icfp/.*)|(.*/toplas/.*)|(.*/tse/.*)";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(key);
		if (m.find()) {
			return true;
		}
		return false;
	}

	// method to process each tag of the xml file, and set the correspponding
	// value using the setters of article
	public void processArticleRecord(String rawName, String tagVal) {
		if (rawName == "author") {
			article.setAuthorName(tagVal);
		} else if (rawName == "title") {
			article.setTitle(tagVal);
		} else if (rawName == "pages") {
			article.setPages(tagVal);
		} else if (rawName == "year") {
			article.setYear(Integer.parseInt(tagVal));
		} else if (rawName == "url") {
			article.setUrl(tagVal);
		} else if (rawName == "ee") {
			article.setEE(tagVal);
		} else if (rawName == "journal") {
			article.setJournal(tagVal);
		} else if (rawName == "number") {
			article.setNumber(tagVal);
		} else if (rawName == "volume") {
			article.setVolume(tagVal);
		}
	}

}
