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

public class ArticleParserTest {

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

	public ArticleParserTest() {
		dbconn = DBConnection.getConnection();
		logger = LogManager.getLogger(ArticleParserTest.class);
	}

	// test to check whether the parsing of article data is valid or not
	@Test
	public void testParseXMLCheckArticles() {
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		File dblpXMLFile = new File("testdata/article_sample.xml");
		res = parseXMLCompObj.parseXML(dblpXMLFile);
		assertTrue("Error in loading article Data", res);
	}

	@Test
	public void testParseXMLCheckArticlesRecords() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int artCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			// stmnt.executeUpdate("truncate table paper");
			// stmnt.executeUpdate("truncate table paperauthor");
			stmnt.executeUpdate("delete from paper where keyval='journals/tse/GuptaAF91' and type='article'");
			stmnt.executeUpdate("delete from paperauthor where keyval='journals/tse/GuptaAF91'");
			File dblpXMLFile = new File("testdata/article_sample.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery(
					"select count(*) as cnt from paper where type='article' and keyval='journals/tse/GuptaAF91'");
			while (r.next()) {
				artCnt = r.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue("Error in loading article Data", Integer.compare(artCnt, 1) == 0);
	}

	@Test
	public void testParseXMLCheckArticlesRecordUnmappable() {
		ParserDBConnector conn = new ParserDBConnector(logger);
		ParseXMLComponent parseXMLCompObj = new ParseXMLComponent(conn);
		System.setProperty("testMode", "ON");
		int artCnt = 0;
		try {
			Statement stmnt = dbconn.createStatement();
			
			stmnt.executeUpdate("delete from paper where keyval='journals/test/tse/PauleveMR11' and type='article'");
			stmnt.executeUpdate("delete from paperauthor where keyval='journals/test/tse/PauleveMR11'");
			File dblpXMLFile = new File("testdata/article_unmappable.xml");
			parseXMLCompObj.parseXML(dblpXMLFile);
			ResultSet r = stmnt.executeQuery(
					"select count(*) as cnt from paper where type='article' and keyval='journals/test/tse/PauleveMR11'");
			while (r.next()) {
				artCnt = r.getInt("cnt");
			}
			stmnt.executeUpdate("delete from paper where keyval='journals/test/tse/PauleveMR11' and type='article'");
			stmnt.executeUpdate("delete from paperauthor where keyval='journals/test/tse/PauleveMR11'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue("Error in loading article Data", Integer.compare(artCnt, 1) == 0);
	}
	
}
