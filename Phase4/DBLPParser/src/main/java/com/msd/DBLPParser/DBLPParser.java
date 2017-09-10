package com.msd.DBLPParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.Logger;
import java.util.Set;
import java.util.HashSet;

 
/**
 * The Class DBLPParser which parses the xml document based on start tag, end tag and the content between them
 * using SAX parser
 */
public class DBLPParser extends DefaultHandler {
	// 
	/** Declaration of string literals for different types of papers */
	private String recordType = "";
	
	/** The tag content. */
	private StringBuilder tagContent;
	
	/** The inproceedings parser object */
	private InproceedingParser inpParser;
	
	/** The article parser. */
	private ArticleParser artParser;
	
	/** The www parser. */
	private wwwParser wwwparser;
	
	/** The thesis parser. */
	private PhdThesisParser thesisParser;
	
	/** The masters thesis parser. */
	private MasterThesisParser mastersThesisParser;
	
	/** The Constant INPROCEEDINGS. */
	private static final String INPROCEEDINGS = "inproceedings";
	
	/** The Constant ARTICLE. */
	private static final String ARTICLE = "article";
	
	/** The Constant WWW. */
	private static final String WWW = "www";
	
	/** The Constant PHDTHESIS. */
	private static final String PHDTHESIS = "phdthesis";
	
	/** The Constant MASTERSTHESIS. */
	private static final String MASTERSTHESIS = "mastersthesis";
	
	/** The valid tags. */
	private Set<String> validTags;

	/**
	 * Instantiates a new DBLP parser.
	 *
	 * @param conn the database connection
	 * @param logger the logger
	 */
	public DBLPParser(ParserDBConnector conn, Logger logger) {
		ParserFactory parserFactory = new ParserFactory(conn, logger);
		// Instantiates various parsers using factory
		this.inpParser = parserFactory.makeInpParser();
		this.artParser = parserFactory.makeArticleParser();
		this.wwwparser = parserFactory.makewwwParser();
		this.thesisParser = parserFactory.makePhdThesisParser();
		this.mastersThesisParser = parserFactory.makeMasterThesisParser();
		validTags = new HashSet<String>();
		this.validTags.add(ARTICLE);
		this.validTags.add(INPROCEEDINGS);
		this.validTags.add(WWW);
		this.validTags.add(PHDTHESIS);
		this.validTags.add(MASTERSTHESIS);
	}

	// startElement method which gets invoked on encountering of every start
	// element tag
	// sets the key value of the data parsed based on the type of the start element tag identified
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs)
			throws SAXException {
		if (rawName.equals(INPROCEEDINGS)) {
			recordType = INPROCEEDINGS;
			inpParser.setPaper(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if (rawName.equals(ARTICLE)) {
			recordType = ARTICLE;
			artParser.setArticle(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if (rawName.equals(WWW)) {
			recordType = WWW;
			wwwparser.setwwwData(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if (rawName.equals(PHDTHESIS)) {
			recordType = PHDTHESIS;
			thesisParser.setThesis(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if (rawName.equals(MASTERSTHESIS)) {
			recordType = MASTERSTHESIS;
			mastersThesisParser.setMastersThesis(attrs.getValue("key"));
			tagContent = new StringBuilder();
		}

	}

	// method which parses the content between the start and the end tags of the
	// document being parsed to form a database record
	// appends the content to a string builder object which will later be used
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (validTags.contains(recordType)) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		}
	}

	// endElement method which gets invoked when the end element tag is
	// encountered while document is being parsed  increment methods from parsers are invoked
	// upon encountering the end tag, corresponding process, prepare and
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
		if (rawName == INPROCEEDINGS) {
			inpParser.incrementInProceedingCount();
			inpParser.prepareInproceedingRecord();
		} else if (recordType == INPROCEEDINGS) {
			String tagVal = tagContent.toString();
			inpParser.processInproceedingRecord(rawName, tagVal);
		} else if (rawName == ARTICLE) {
			artParser.incrementArticleCount();
			artParser.prepareArticleRecord();
		} else if (recordType == ARTICLE) {
			String tagVal = tagContent.toString();
			artParser.processArticleRecord(rawName, tagVal);
		} else if (rawName == WWW) {
			wwwparser.incrementwwwCount();
			wwwparser.prepareWWWRecord();
		} else if (recordType == WWW) {
			String tagVal = tagContent.toString();
			wwwparser.processWWWRecord(rawName, tagVal);
		} else if (rawName == PHDTHESIS) {
			thesisParser.incrementPhdThesisCount();
			thesisParser.preparePhdThesisRecord();
		} else if (recordType == PHDTHESIS) {
			String tagVal = tagContent.toString();
			thesisParser.processPhdThesisRecord(rawName, tagVal);
		} else if (rawName == MASTERSTHESIS) {
			mastersThesisParser.incrementMastersThesisCount();
			mastersThesisParser.prepareMastersThesisRecord();
		} else if (recordType == MASTERSTHESIS) {
			String tagVal = tagContent.toString();
			mastersThesisParser.processMastersThesisRecord(rawName, tagVal);
		}

		tagContent = new StringBuilder();

	}

}