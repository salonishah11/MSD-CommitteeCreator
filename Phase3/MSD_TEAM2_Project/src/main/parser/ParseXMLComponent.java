package main.parser;

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

import main.interfaces.ParseXMLInterface;
import main.parser.db.DBConnection;

public class ParseXMLComponent implements ParseXMLInterface {

	private ParserDBConnector conn;

	private static Logger logger = LogManager.getLogger(ParseXMLComponent.class);

	public ParseXMLComponent(ParserDBConnector conn) {
		this.conn = conn;
	}

	// main method which handles the parsing of input file being parsed in the
	// command line arguments
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
			// conn.createAuthors();

		} catch (Exception e) {
			logger.error(e);
			return false;
		}

		return true;
	}

	// parseCommittee parases the committee directory being passed inthe command
	// line argument
	// by creating an isntance of CommitteeParser
	public boolean parseCommittee(File committeeDir) {
		boolean runStatus = false;
		CommitteeParser committeeParser = new CommitteeParser(logger, conn);
		runStatus = committeeParser.parseCommitteeData(committeeDir);
		return runStatus;

	}

	public static void main(String[] args) throws IOException {
		System.setProperty("entityExpansionLimit", "10000000");
		ParserDBConnector parserDBConnector = new ParserDBConnector(logger);
		ParseXMLComponent parsexmlCompObj = new ParseXMLComponent(parserDBConnector);
		parsexmlCompObj.parseXML(new File(args[0]));
		parsexmlCompObj.parseCommittee(new File(args[1]));
	}
}
