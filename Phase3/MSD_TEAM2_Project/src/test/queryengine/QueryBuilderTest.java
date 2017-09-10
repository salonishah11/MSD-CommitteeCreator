package test.queryengine;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.queryengine.Author;
import main.queryengine.QueryBuilder;
import main.userinterface.SearchParameters;

public class QueryBuilderTest {
	
	/**************************** CREATE QUERY TESTS ****************************************/

	//Search by author name, served in committee and served in either OOPSLA or PLDI
	@Test
	public void testCreateQueryWithAuthorCommitteeSearchParams() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		ArrayList<String> confList = new ArrayList<String>();
		confList.add("OOPSLA");
		confList.add("PLDI");
		searchParamObj.setCommitteeConferenceNameList(confList);
		searchParamObj.setServedInCommitteeBefore("Yes");
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where pa.authorName like '%thomas%' "
				+ "and  pa.authorName in "
				+ "(select distinct com.memberName "
				+ "from dblp.committee com "
				+ "where (com.conferenceName = 'OOPSLA' or com.conferenceName = 'PLDI')) "
				+ "and p.keyVal = pa.keyVal";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	@Test
	//Search by author name, served in committee and served in either OOPSLA or PLDI for years 2015 and 2016
	public void testCreateQueryWithAuthorCommitteeYearSearchParams() {
		
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		ArrayList<String> confList = new ArrayList<String>();
		confList.add("OOPSLA");
		confList.add("PLDI");
		searchParamObj.setCommitteeConferenceNameList(confList);
		searchParamObj.setServedInCommitteeBefore("Yes");
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);		
		searchParamObj.setServedInCommitteeStartYear(2015);
		searchParamObj.setServedInCommitteeEndYear(2016);
				
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
						+ "from dblp.paperauthor pa, dblp.paper p "
						+ "where pa.authorName like '%thomas%' "
						+ "and  pa.authorName in "
						+ "(select distinct com.memberName "
						+ "from dblp.committee com "
						+ "where com.year >= 2015 "
						+ "and com.year <= 2016 "
						+ "and (com.conferenceName = 'OOPSLA' or com.conferenceName = 'PLDI')) "
						+ "and p.keyVal = pa.keyVal";
				
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);				
	}
	
	
	@Test
	//Search by school names, and papers published in conference or journals
	public void testCreateQueryWithAuthorSchoolPaperSearchPapers(){
		
		SearchParameters searchParamObj = new SearchParameters();
				searchParamObj.setServedInCommitteeStartYear(-1);
				searchParamObj.setServedInCommitteeEndYear(-1);
				searchParamObj.setExactNumberOfPapers(-1);
				searchParamObj.setNumberOfPapersStartRange(-1);
				searchParamObj.setNumberOfPapersEndRange(-1);
				searchParamObj.setStartYearOfPublication(-1);
				searchParamObj.setEndYearOfPublication(-1);		
				ArrayList<String> schoolList = new ArrayList<String>();
				schoolList.add("northeastern");
				schoolList.add("mit");
				searchParamObj.setSchoolNameList(schoolList);
				ArrayList<String> paperConfList = new ArrayList<String>();
				paperConfList.add("OOPSLA");
				searchParamObj.setPaperConferenceNameList(paperConfList);
				ArrayList<String> paperJournalList = new ArrayList<String>();
				paperJournalList.add("TOPLAS");
				searchParamObj.setPaperJournalNameList(paperJournalList);
				
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where exists "
				+ "(select 1 from dblp.thesis thes inner "
				+ "join dblp.paperauthor spa "
				+ "on thes.keyVal = spa.keyVal  "
				+ "and spa.authorName like '%null%' "
				+ "where spa.authorName=pa.authorName "
				+ "and (thes.school like '%northeastern%' or thes.school like '%mit%')) "
				+ "and p.type in ('inproceedings','article') "
				+ "and p.keyVal = pa.keyVal "
				+ "and (p.keyVal like 'conf/OOPSLA/%' or p.keyVal like 'journals/TOPLAS/%')";
				
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	
	@Test
	//Search by masters thesis, #papers and year of publication
	public void testCreateQueryWithThesisPaperSearchParams(){
		SearchParameters searchParamsObj = new SearchParameters();
		searchParamsObj.setExactNumberOfPapers(2);
		searchParamsObj.setNumberOfPapersStartRange(-1);
		searchParamsObj.setNumberOfPapersEndRange(-1);
		searchParamsObj.setStartYearOfPublication(2010);
		searchParamsObj.setEndYearOfPublication(2016);
		searchParamsObj.setServedInCommitteeStartYear(-1);
		searchParamsObj.setServedInCommitteeEndYear(-1);
		searchParamsObj.setThesisType("mastersthesis");
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where exists (select 1 from dblp.thesis thes "
				+ "inner join dblp.paperauthor spa "
				+ "on thes.keyVal = spa.keyVal  "
				+ "and spa.authorName like '%null%' "
				+ "where spa.authorName=pa.authorName "
				+ "and thes.type = 'mastersthesis' ) "
				+ "and p.keyVal = pa.keyVal "
				+ "and p.year >= 2010 and p.year <= 2016 "
				+ "group by pa.authorName "
				+ "having count(p.id) = 2";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamsObj), resultQuery);
	}
	
	
	@Test
	//Search by phd thesis and papers published between 2 and 4 (inclusive)
	public void testCreateQueryWithPhdThesisPaperSearch(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(2);
		searchParamObj.setNumberOfPapersEndRange(4);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		searchParamObj.setThesisType("phdthesis");
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where exists (select 1 from dblp.thesis thes "
				+ "inner join dblp.paperauthor spa "
				+ "on thes.keyVal = spa.keyVal  "
				+ "and spa.authorName like '%null%' "
				+ "where spa.authorName=pa.authorName "
				+ "and thes.type = 'phdthesis' ) "
				+ "and p.keyVal = pa.keyVal "
				+ "group by pa.authorName "
				+ "having count(p.id) >= 2 "
				+ "and count(p.id) <= 4";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	
	@Test
	//Search by keywords
	public void testCreateQueryWithKeywordsSearchParam(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("pointer");
		keywords.add("analysis");
		searchParamObj.setKeywords(keywords);
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.keyVal = pa.keyVal "
				+ "and p.title like '%pointer%' "
				+ "and p.title like '%analysis%'";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	
	@Test
	//Search by paper title
	public void testCreateQueryWithPaperTitle(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		searchParamObj.setPaperTitle("managing software development");
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.keyVal = pa.keyVal "
				+ "and p.title = 'managing software development'";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	
	@Test
	//Search by publication year end range and journal parameters
	public void testCreateQueryWithPaperPubYearJournalParams(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(2016);
		ArrayList<String> paperJournalList = new ArrayList<String>();
		paperJournalList.add("TOPLAS");
		paperJournalList.add("TSE");
		searchParamObj.setPaperJournalNameList(paperJournalList);
		
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.keyVal = pa.keyVal "
				+ "and p.year <= 2016 "
				+ "and p.type = 'article' "
				+ "and (p.keyVal like 'journals/TOPLAS/%' or p.keyVal like 'journals/TSE/%')";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	
	@Test
	//Search by not served in committee before and conference parameters
	public void testCreateQueryNotServedInCommParams(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		searchParamObj.setServedInCommitteeBefore("No");
		ArrayList<String> paperConfList = new ArrayList<String>();
		paperConfList.add("OOPSLA");
		paperConfList.add("PLDI");
		searchParamObj.setPaperConferenceNameList(paperConfList);
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.type = 'inproceedings' "
				+ "and p.keyVal = pa.keyVal "
				+ "and (p.keyVal like 'conf/OOPSLA/%' or p.keyVal like 'conf/PLDI/%') "
				+ "and  pa.authorName not in (select distinct com.memberName from dblp.committee com)";
		
		assertEquals("Created query correct", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}
	
	@Test
	//Search for incorrect parameters
	public void testCreateQueryWithIncorrectSearchParams() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		ArrayList<String> confList = new ArrayList<String>();
		confList.add("OOPSLA");
		confList.add("PLDI");
		searchParamObj.setCommitteeConferenceNameList(confList);
		searchParamObj.setServedInCommitteeBefore(null);
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		
		
		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where pa.authorName like '%thomas%' "
				+ "and  pa.authorName in "
				+ "(select distinct com.memberName "
				+ "from dblp.Committee com "
				+ "where (com.conferenceName = 'OOPSLA' or com.conferenceName = 'PLDI')) "
				+ "and p.keyVal = pa.keyVal";
		
		assertNotEquals("Created query is incorrect", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}

	/**************************** CREATE QUERY TESTS ****************************************/
	
	/**************************** EXECUTE QUERY TESTS ****************************************/
	@Test
	//Execute query searching by keywords
	public void testExecuteQueryWithKeywordsSearchParam(){
		String query = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.keyVal = pa.keyVal "
				+ "and p.title like '%pointer%' "
				+ "and p.title like '%analysis%'";
		
		int rowsReturned = new QueryBuilder().executeQuery(query).size();
		
		assertEquals("It should return 82 rows", rowsReturned, 82);
	}
	
	@Test
	//Execute query searching by title
	public void testExecuteQueryWithTitleSearchParam(){
		String query = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where p.keyVal = pa.keyVal "
				+ "and p.title = 'managing software development'";
		
		int rowsReturned = new QueryBuilder().executeQuery(query).size();
		
		assertEquals("It should return 0 rows", rowsReturned, 0);
	}
	/**************************** EXECUTE QUERY TESTS ****************************************/
	
	/**************************** SEARCH QUERY TESTS ****************************************/
	@Test
	//Search for paper with keywords pointer and analysis
	public void testSearchWithKeywordsSearchParams(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("pointer");
		keywords.add("analysis");
		searchParamObj.setKeywords(keywords);
		
		int rowsReturned = new QueryBuilder().Search(searchParamObj).size();
		
		assertEquals("It should return 82 rows", rowsReturned, 82);
	}
	
	@Test
	//Search for author like thomas
	public void testSearchWithAuthorNameSearchParams(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		
		int rowsReturned = new QueryBuilder().Search(searchParamObj).size();
		
		assertEquals("It should return 296 rows", rowsReturned, 296);
	}
	
	@Test
	//Search with all parameters
	public void testSearchWithAllParameters(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		searchParamObj.setExactNumberOfPapers(2);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeBefore("Yes");
		searchParamObj.setServedInCommitteeStartYear(2010);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(2010);
		searchParamObj.setEndYearOfPublication(-1);
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("pointer");
		searchParamObj.setKeywords(keywords);
		ArrayList<String> schoolList = new ArrayList<String>();
		schoolList.add("mit");
		searchParamObj.setSchoolNameList(schoolList);
		ArrayList<String> paperConfList = new ArrayList<String>();
		paperConfList.add("OOPSLA");
		searchParamObj.setPaperConferenceNameList(paperConfList);
		ArrayList<String> paperJournalList = new ArrayList<String>();
		paperJournalList.add("TOPLAS");
		searchParamObj.setPaperJournalNameList(paperJournalList);
		ArrayList<String> confList = new ArrayList<String>();
		confList.add("OOPSLA");
		searchParamObj.setCommitteeConferenceNameList(confList);
		searchParamObj.setThesisType("mastersthesis");
		
		ArrayList<Author> resultList = new QueryBuilder().Search(searchParamObj);
		int rowsReturned = 0;
		if(resultList != null){
			rowsReturned = resultList.size();
		}
		
		assertEquals("It should return 0 rows", rowsReturned, 0);
		
	}
	/**************************** SEARCH QUERY TESTS ****************************************/
	
	
	
	/**************************** FRAME THESIS SUB-QUERY TESTS ******************************/
	@Test
	//tests thesis parameters use cases
	public void testThesisSubQuerySearchParams(){
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		ArrayList<String> schoolList = new ArrayList<String>();
		schoolList.add("mit");
		searchParamObj.setSchoolNameList(schoolList);
		searchParamObj.setThesisType("mastersthesis");

		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from dblp.paperauthor pa, dblp.paper p "
				+ "where exists "
				+ "(select 1 from dblp.thesis thes "
				+ "inner join dblp.paperauthor spa "
				+ "on thes.keyVal = spa.keyVal  "
				+ "and spa.authorName like '%null%' "
				+ "where spa.authorName=pa.authorName "
				+ "and thes.type = 'mastersthesis'  "
				+ "and (thes.school like '%mit%')) "
				+ "and p.keyVal = pa.keyVal";
		
		assertEquals("Correct query", new QueryBuilder().createQuery(searchParamObj), resultQuery);		
	}
	/**************************** FRAME THESIS SUB-QUERY TESTS ******************************/
}
