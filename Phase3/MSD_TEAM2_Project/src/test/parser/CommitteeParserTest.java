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

public class CommitteeParserTest {

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

	public CommitteeParserTest() {
		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(CommitteeParserTest.class);
	}

	// test to valdiate the parsing of committee data
	@Test
	public void testParseCommitteeData() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		File committeeDir = new File("testdata/committees");
		res = parseXMLCompObj.parseCommittee(committeeDir);
		System.out.println("returned :" + res);
		assertTrue("Error in loading Committee Data", res);

	}

	@Test
	public void testParseCommitteeDataMemberType() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);

		System.setProperty("testMode", "ON");
		int commCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			stmnt.executeUpdate("delete from committee where membername in ('John M. Vlissides','Douglas C. Schmidt','Gul Agha','David F. Bacon') "
					+ "and conferenceName='oopsla' and year=2004");
			File committeeDir = new File("testdata/committees");
			parseXMLCompObj.parseCommittee(committeeDir);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from committee where memberType='P' and membername='Douglas C. Schmidt'");
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
