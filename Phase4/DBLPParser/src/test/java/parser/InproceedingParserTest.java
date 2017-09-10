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

public class InproceedingParserTest {

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

	public InproceedingParserTest() {

		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(InproceedingParserTest.class);

	}

	// test case to validate the parsing of inproceeding data
	@Test
	public void testParseXMLCheckInproceeding() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		File dblpXMLFile = new File("testdata/inproceeding_sample.xml");
		boolean res = false;
		res = parseXMLCompObj.parseXML(dblpXMLFile);
		assertTrue("Error in loading inproceeding Data", res);

	}

	@Test
	public void testParseXMLCheckInproceedingRecord() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int inpCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			stmnt.executeUpdate("delete from paper where keyval='conf/oopsla/JordanSW94' and type='inproceedings'");
			stmnt.executeUpdate("delete from paperauthor where keyval='conf/oopsla/JordanSW94'");
			File dblpXMLFile = new File("testdata/inproceeding_sample.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery("select count(*) as cnt from paper where keyval='conf/oopsla/JordanSW94' and type='inproceedings'");
			while (r.next()) {
				inpCnt = r.getInt("cnt");
			}
			stmnt.close();
		} catch (SQLException e) {
			System.out.println("Exception encountered" + e.getMessage());
		}
		assertTrue("Error in loading inproceeding Data", Integer.compare(inpCnt, 1) == 0);
	}

}
