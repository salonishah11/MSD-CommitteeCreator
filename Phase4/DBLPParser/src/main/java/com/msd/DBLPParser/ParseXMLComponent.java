package com.msd.DBLPParser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.DBLPParser.interfaces.*;
import com.msd.DBLPParser.db.DBConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class ParseXMLComponent.
 */
public class ParseXMLComponent implements ParseXMLInterface {

	/** The database connection object. */
	private ParserDBConnector conn;

	/** The logger. */
	private static Logger logger = LogManager.getLogger(ParseXMLComponent.class);

	/** The parser factory. */
	private ParserFactory parserFactory;
	
	/**
	 * Instantiates a new parses the XML component.
	 *
	 * @param conn the conn
	 */
	public ParseXMLComponent(ParserDBConnector conn) {
		this.conn = conn;
		this.parserFactory = new ParserFactory(conn, logger);
	}

	// main method which handles the parsing of input file being parsed in the
	/* (non-Javadoc)
	 * @see com.msd.DBLPParser.interfaces.ParseXMLInterface#parseXML(java.io.File)
	 */
	public boolean parseXML(File filename) {

		try {
			// create an instance of SAXParser
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			// create handler object of DBLPParser
			DBLPParser handler = new DBLPParser(conn, logger);
			parser.getXMLReader().setFeature("http://xml.org/sax/features/validation", true);
			parser.parse(filename, handler);
			conn.executeAllBatches();
			conn.commit();

		} catch (Exception e) {
			logger.error(e);
			return false;
		}

		return true;
	}

	// parseCommittee parases the committee directory being passed inthe command
	// line argument
	/* (non-Javadoc)
	 * @see com.msd.DBLPParser.interfaces.ParseXMLInterface#parseCommittee(java.io.File)
	 */
	public boolean parseCommittee(File committeeDir) {
		boolean runStatus = false;
		CommitteeParser committeeParser = parserFactory.makeCommitteeParser();
		runStatus = committeeParser.parseCommitteeData(committeeDir);
		return runStatus;

	}

	/**
	 * Parses the CS rankings.
	 *
	 * @param csRankingsDir the cs rankings dir
	 * @return true, if successful
	 */
	public boolean parseCSRankings(File csRankingsDir) {
		boolean runStatus = false;
		CSRankingsParser csParser = parserFactory.makeCSRankingsParser();
		runStatus = csParser.parseCSRankings(csRankingsDir);
		return runStatus;
	}
	
	/**
	 * Parses the CS rankings univ region.
	 *
	 * @param csRankingsDir the cs rankings dir
	 * @return true, if successful
	 */
	public boolean parseCSRankingsUnivRegion(File csRankingsDir) {
		boolean runStatus = false;
		CSRankingsCountryParser csParser = parserFactory.makeCSRankingsCountryParser();
		runStatus = csParser.parseCSRankings(csRankingsDir);
		return runStatus;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		System.setProperty("entityExpansionLimit", "10000000");
		ParserDBConnector parserDBConnector = new ParserDBConnector(logger);
		ParseXMLComponent parsexmlCompObj = new ParseXMLComponent(parserDBConnector);
		try {
			parsexmlCompObj.parseXML(new File(args[0]));
			parsexmlCompObj.parseCommittee(new File(args[1]));
			parsexmlCompObj.parseCSRankings(new File(args[2]));
			parsexmlCompObj.parseCSRankingsUnivRegion(new File(args[3]));
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("Expected 4 arguments to be passed one for dblp file path, committee dir, and cs "
					+ "rankings file and univregion mapping file: "+e);
		}
	}
}
