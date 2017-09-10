package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.sql.ResultSet;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

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

public class ParseXMLInterfaceTest {

	@Mock
	private ParserDBConnector conn;

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

	public ParseXMLInterfaceTest() {
	}

	@Test
	public void testParseXML() {
		System.setProperty("entityExpansionLimit", "10000000");
		parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		res = parseXMLCompObj.parseXML(new File("testdata/dblp_sample.xml"));
		assertTrue("Failed to parse DBLP XML Data", res);
	}
	
	@Test
	public void testParseInvalidXML() {
		System.setProperty("entityExpansionLimit", "10000000");
		parseXMLCompObj = new ParseXMLComponent(conn);
		boolean res = false;
		res = parseXMLCompObj.parseXML(new File("testdata/dblp_invalid.xml"));
		assertFalse("Failed to parse DBLP XML Data", res);
	}
	

}
