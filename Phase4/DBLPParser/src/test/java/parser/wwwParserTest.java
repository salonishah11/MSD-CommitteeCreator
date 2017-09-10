package parser;

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

import com.msd.DBLPParser.ParseXMLComponent;
import com.msd.DBLPParser.ParserDBConnector;
import com.msd.DBLPParser.db.DBConnection;

// wwwParserTest to test the wwwParser class
public class wwwParserTest {

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

	public wwwParserTest() {

		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(wwwParserTest.class);
	}

	// test the parsing of xml data with www tag
	@Test
	public void testParseXMLWWWData() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		File dblpXMLFile = new File("testdata/www_sample.xml");
		res = parseXMLCompObj.parseXML(dblpXMLFile);

		assertTrue("Error in loading article Data", res);
	}

	@Test
	public void testParseXMLWWWDataRecord() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int wwwCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			stmnt.executeUpdate("delete from www where keyval='persons/test/Ley2003'");
			stmnt.executeUpdate("delete from paperauthor where keyval='persons/test/Ley2003'");
			File dblpXMLFile = new File("testdata/www_sample.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from www where keyval='persons/test/Ley2003'");
			while (r.next()) {
				wwwCnt = r.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue("Error in loading article Data", Integer.compare(wwwCnt, 1) == 0);
	}

}
