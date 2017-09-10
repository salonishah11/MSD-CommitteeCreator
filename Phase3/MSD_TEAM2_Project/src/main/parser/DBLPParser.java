package main.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.Logger;


// DBLPParser class which parses the xml document based on start tag, end tag and the content between them
// using SAX parser
public class DBLPParser extends DefaultHandler {
	// declaration of string literals for different types of papers
	// declaration of different parser objects
	private String recordType = "";
	private StringBuilder tagContent;
	private InproceedingParser inpParser;
	private ArticleParser artParser;
	private wwwParser wwwparser;
	private PhdThesisParser thesisParser;
	private MasterThesisParser mastersThesisParser;
	private static final String INPROCEEDINGS = "inproceedings";
	private static final String ARTICLE = "article";
	private static final String WWW = "www";
	private static final String PHDTHESIS = "phdthesis";
	private static final String MASTERSTHESIS = "mastersthesis";

	// contructor which initializes the different parsers created
	public DBLPParser(ParserDBConnector conn,Logger logger) {
        this.inpParser = new InproceedingParser(conn, logger);
        this.artParser = new ArticleParser(conn, logger);
        this.wwwparser = new wwwParser(conn, logger);
        this.thesisParser = new PhdThesisParser(conn, logger);
        this.mastersThesisParser = new MasterThesisParser(conn, logger);
    }
	
	// startElement method which gets invoked on encountering of every start element tag 
	// sets the key value of the data parsed based on the type of the start element tag identified
	@Override
	public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs)
			throws SAXException {
		if (rawName.equals(INPROCEEDINGS)) {
			recordType = INPROCEEDINGS;
			inpParser.setPaper(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if(rawName.equals(ARTICLE)) {
			recordType = ARTICLE;
			artParser.setArticle(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if(rawName.equals(WWW)){
			recordType = WWW;
			wwwparser.setwwwData(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if(rawName.equals(PHDTHESIS)){
			recordType = PHDTHESIS;
			thesisParser.setThesis(attrs.getValue("key"));
			tagContent = new StringBuilder();
		} else if(rawName.equals(MASTERSTHESIS)){
			recordType = MASTERSTHESIS;
			mastersThesisParser.setMastersThesis(attrs.getValue("key"));
			tagContent = new StringBuilder();
		}

	}

	// method which parses the content between the start and the end tags of the document being parsed
	// appends the content to a string builder object which will later be used to form a database record
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (recordType == INPROCEEDINGS) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		} else if(recordType == ARTICLE) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		} else if(recordType == WWW) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		} else if(recordType == PHDTHESIS) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		} else if(recordType == MASTERSTHESIS) {
			String tagVal = new String(ch, start, length).trim();
			tagContent.append(tagVal);
		}
	}


	// endElement method which gets invoked when the end element tag is encountered while document is being parsed
	// upon encountering the end tag, corresponding process, prepare and increment methods from parsers are invoked
	@Override
	public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
		if (rawName == INPROCEEDINGS) {
			inpParser.incrementInProceedingCount();
			inpParser.prepareInproceedingRecord();
		}
		else if (recordType == INPROCEEDINGS) {
			String tagVal = tagContent.toString();
			inpParser.processInproceedingRecord(rawName, tagVal);
		} 
		else if (rawName == ARTICLE) {
			artParser.incrementArticleCount();
			artParser.prepareArticleRecord();
		 } 
		else if(recordType == ARTICLE){
			 String tagVal = tagContent.toString();
			 artParser.processArticleRecord(rawName, tagVal);
		 }
		else if (rawName == WWW) {
			wwwparser.incrementwwwCount();
			wwwparser.prepareWWWRecord();
		}
		else if(recordType == WWW){
			 String tagVal = tagContent.toString();
			 wwwparser.processWWWRecord(rawName, tagVal);
		 }	
		else if (rawName == PHDTHESIS) {
			thesisParser.incrementPhdThesisCount();
			thesisParser.preparePhdThesisRecord();
		}
		else if(recordType == PHDTHESIS){
			 String tagVal = tagContent.toString();
			 thesisParser.processPhdThesisRecord(rawName, tagVal);
		 }
		else if (rawName == MASTERSTHESIS) {
			mastersThesisParser.incrementMastersThesisCount();
			mastersThesisParser.prepareMastersThesisRecord();
		}
		else if(recordType == MASTERSTHESIS){
			 String tagVal = tagContent.toString();
			 mastersThesisParser.processMastersThesisRecord(rawName, tagVal);
		 }
		
		tagContent = new StringBuilder();

	}

	
}