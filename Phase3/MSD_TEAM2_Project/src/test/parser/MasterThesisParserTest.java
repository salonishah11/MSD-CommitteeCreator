package test.parser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.sql.ResultSet;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import main.parser.ParseXMLComponent;
import main.parser.ParserDBConnector;
import main.parser.db.DBConnection;

public class MasterThesisParserTest {

	@Mock
	private ParserDBConnector conn;

	private Connection dbconn;
	private Logger logger;

	@InjectMocks
	private ParseXMLComponent parseXMLCompObj;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	public MasterThesisParserTest() {
		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(InproceedingParserTest.class);

	}

	// test case to validate the parsing of masters thesis data
	@Test
	public void testParseMastersThesisData() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		File dblpXMLFile = new File("testdata/mastersthesis_sample.xml");
		res = parseXMLCompObj.parseXML(dblpXMLFile);
		assertTrue("Error in loading article Data", res);
	}

	@Test
	public void testParseMastersThesisDataRecord() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int mastersThesisCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			
			stmnt.executeUpdate("delete from thesis where keyval='ms/test/Ley2006'");
			stmnt.executeUpdate("delete from paperauthor where keyval='ms/test/Ley2006'");
			File dblpXMLFile = new File("testdata/mastersthesis_sample.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from thesis where keyval='ms/test/Ley2006'");
			while (r.next()) {
				mastersThesisCnt = r.getInt("cnt");
			}
			stmnt.executeUpdate("delete from thesis where keyval='ms/test/Ley2006'");
			stmnt.executeUpdate("delete from paperauthor where keyval='ms/test/Ley2006'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue("Error in loading article Data", Integer.compare(mastersThesisCnt, 1) == 0);
	}

}
