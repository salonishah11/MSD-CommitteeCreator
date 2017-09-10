package parser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import com.msd.DBLPParser.ParseXMLComponent;
import com.msd.DBLPParser.ParserDBConnector;
import com.msd.DBLPParser.db.DBConnection;

// Class containing test cases for phd parser.
// Uses mockito object to test database connection 
public class PhdThesisParserTest {

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

	public PhdThesisParserTest() {

		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(PhdThesisParserTest.class);
	}

	// test case to validate the parsing of phdthesis_sample.xml data

	@Test
	public void testParseXMLPhdThesis() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;

		File dblpXMLFile = new File("testdata/phdthesis_sample.xml");
		res = parseXMLCompObj.parseXML(dblpXMLFile);

		assertTrue("Error in loading article Data", res);
	}
	
	@Test
	public void testParsePhdThesisDataRecord() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int mastersThesisCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			
			File dblpXMLFile = new File("testdata/phdthesis_sample.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from thesis where keyval='books/test/daglib/0071011'");
			while (r.next()) {
				mastersThesisCnt = r.getInt("cnt");
			}
			stmnt.executeUpdate("delete from thesis where keyval='books/test/daglib/0071011'");
			stmnt.executeUpdate("delete from paperauthor where keyval='books/test/daglib/0071011'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue("Error in loading article Data", Integer.compare(mastersThesisCnt, 1) == 0);
	}

}
