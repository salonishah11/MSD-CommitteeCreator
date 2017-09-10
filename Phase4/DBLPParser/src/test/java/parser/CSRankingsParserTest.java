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

public class CSRankingsParserTest {

	@Mock
	private ParserDBConnector mockConn;
	

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

	public CSRankingsParserTest() {
		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(CommitteeParserTest.class);
	}

	// test to valdiate the parsing of csrankings data
	@Test
	public void testParseCSRankingsData() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(mockConn);
		boolean res = false;
		File csRankingsFile = new File("testdata/csrankings.csv");
		res = parseXMLCompObj.parseCSRankings(csRankingsFile);
		System.out.println("returned :" + res);
		assertTrue("Error in loading CSRankings Data", res);

	}

	
	@Test
	public void testParseCSRankingsDataLoad() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObjDB = new ParseXMLComponent(conn);

		System.setProperty("testMode", "ON");
		int commCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			stmnt.executeUpdate("delete from affiliation where authorname='Eve Hoggan'");
			File committeeDir = new File("testdata/csrankings.csv");
			parseXMLCompObjDB.parseCSRankings(committeeDir);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from affiliation where authorname='Eve Hoggan'");
			while (r.next()) {
				commCnt = r.getInt("cnt");
			}

			stmnt.close();
		} catch (SQLException e) {
			System.out.println("Exception encountered" + e.getMessage());
		}
		assertTrue("Error in loading author Data", Integer.compare(commCnt, 1) == 0);
	}
	
}
