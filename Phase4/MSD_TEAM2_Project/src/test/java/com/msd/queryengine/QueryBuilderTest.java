package com.msd.queryengine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.msd.queryengine.QueryBuilder;
import com.msd.queryengine.model.Author;
import com.msd.queryengine.model.AuthorPublications;
import com.msd.queryengine.services.SimilarSearchService;
import com.msd.userinterface.SearchParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class QueryBuilderTest {

	/****************************
	 * CREATE QUERY TESTS
	 ****************************************/

	// Search by author name, served in committee and served in either OOPSLA or
	// PLDI
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

		String querypattern = "(.*: )(.*)";
		Pattern pattern = Pattern.compile(querypattern);
		Matcher m = pattern.matcher(new QueryBuilder().createQuery(searchParamObj).toString());
		String createdQuery = "";
		if (m.find()) {
			createdQuery = m.group(2);
		}
		String resultQuery = "select au.name as name,sum(au.tot) as tot "
				+ "from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid and au.name like '%thomas%' "
				+ "inner join conferencepaper p on p.id = pa.paperid where "
				+ " name in (select distinct com.memberName from committee com "
				+ "where com.conferenceName in ( 'OOPSLA','PLDI')) group by au.name "
				+ "union select au.name as name,count(*) tot from author au "
				+ "inner join journalauthor pa on au.id = pa.authorid and au.name like '%thomas%'"
				+ " inner join journalpaper p on p.id = pa.paperid where "
				+ " name in (select distinct com.memberName from committee com where "
				+ "com.conferenceName in ( 'OOPSLA','PLDI')) group by au.name) au  "
				+ "group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by author name, served in committee and served in either OOPSLA or
	// PLDI for years 2015 and 2016
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

		String querypattern = "(.*: )(.*)";
		Pattern pattern = Pattern.compile(querypattern);
		Matcher m = pattern.matcher(new QueryBuilder().createQuery(searchParamObj).toString());
		String createdQuery = "";
		if (m.find()) {
			createdQuery = m.group(2);
		}
		String resultQuery = "select au.name as name,sum(au.tot) as tot" + " from (select au.name as name,count(*) tot"
				+ " from author au inner join conferenceauthor pa"
				+ " on au.id = pa.authorid and au.name like '%thomas%'"
				+ " inner join conferencepaper p on p.id = pa.paperid where  "
				+ "name in (select distinct com.memberName from committee com "
				+ "where com.year >= 2015  and com.year <= 2016 and" + " com.conferenceName in ( 'OOPSLA','PLDI')) "
				+ "group by au.name union select au.name as name,count(*) tot "
				+ "from author au inner join journalauthor pa on au.id = pa.authorid and au.name like '%thomas%'"
				+ " inner join journalpaper p on p.id = pa.paperid where"
				+ "  name in (select distinct com.memberName from committee com where com.year >= 2015"
				+ "  and com.year <= 2016 and com.conferenceName in ( 'OOPSLA','PLDI')) "
				+ "group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	public String extract_createdQuery(String query) {
		String querypattern = "(.*: )(.*)";
		Pattern pattern = Pattern.compile(querypattern);
		Matcher m = pattern.matcher(query);
		String createdQuery = "";
		if (m.find()) {
			createdQuery = m.group(2);
		}
		return createdQuery;
	}

	@Test
	// Search by school names, and papers published in conference or journals
	public void testCreateQueryWithAuthorSchoolPaperSearchPapers() {

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

		String querypattern = "(.*: )(.*)";
		Pattern pattern = Pattern.compile(querypattern);
		Matcher m = pattern.matcher(new QueryBuilder().createQuery(searchParamObj).toString());
		String createdQuery = "";
		if (m.find()) {
			createdQuery = m.group(2);
		}

		String resultQuery = "select au.name as name,sum(au.tot) as tot "
				+ "from (select au.name as name,count(*) tot from author au"
				+ " inner join conferenceauthor pa on au.id = pa.authorid  "
				+ "inner join conferencepaper p on p.id = pa.paperid and p.event_name in( 'OOPSLA')"
				+ " inner join thesisauthor tau on tau.authorid = au.id inner join thesis thes"
				+ " on thes.id = tau.paperid and (thes.school like '%northeastern%'  "
				+ "or thes.school like '%mit%' ) group by au.name union select au.name as name,count(*) tot from "
				+ "author au inner join journalauthor pa on au.id = pa.authorid  "
				+ "inner join journalpaper p on p.id = pa.paperid and p.event_name in( 'TOPLAS')"
				+ " inner join thesisauthor tau on tau.authorid = au.id inner join thesis thes "
				+ "on thes.id = tau.paperid and (thes.school like '%northeastern%'  or thes.school like '%mit%' )"
				+ " group by au.name) au  group by au.name having count(*) > 0";
		
		

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by masters thesis, #papers and year of publication
	public void testCreateQueryWithThesisPaperSearchParams() {
		SearchParameters searchParamsObj = new SearchParameters();
		searchParamsObj.setExactNumberOfPapers(2);
		searchParamsObj.setNumberOfPapersStartRange(-1);
		searchParamsObj.setNumberOfPapersEndRange(-1);
		searchParamsObj.setStartYearOfPublication(2010);
		searchParamsObj.setEndYearOfPublication(2016);
		searchParamsObj.setServedInCommitteeStartYear(-1);
		searchParamsObj.setServedInCommitteeEndYear(-1);
		searchParamsObj.setThesisType("mastersthesis");

		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamsObj).toString());

		String resultQuery = "select au.name as name,sum(au.tot) as tot"
				+ " from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid "
				+ " inner join conferencepaper p on p.id = pa.paperid and p.year >= 2010  and p.year <= 2016 "
				+ " inner join thesisauthor tau on tau.authorid = au.id "
				+ "inner join thesis thes on thes.id = tau.paperid and thes.type = 'mastersthesis' "
				+ "group by au.name union select au.name as name,count(*) tot from author au"
				+ " inner join journalauthor pa on au.id = pa.authorid  inner join "
				+ "journalpaper p on p.id = pa.paperid and p.year >= 2010  and p.year <= 2016 "
				+ " inner join thesisauthor tau on tau.authorid = au.id inner join thesis thes"
				+ " on thes.id = tau.paperid and thes.type = 'mastersthesis' group by au.name) au"
				+ "  group by au.name having sum(au.tot) = 2";
		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by phd thesis and papers published between 2 and 4 (inclusive)
	public void testCreateQueryWithPhdThesisPaperSearch() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(2);
		searchParamObj.setNumberOfPapersEndRange(4);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		searchParamObj.setThesisType("phdthesis");

		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());

		String resultQuery = "select au.name as name,sum(au.tot) as tot"
				+ " from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid "
				+ " inner join conferencepaper p on p.id = pa.paperid inner join thesisauthor tau "
				+ "on tau.authorid = au.id inner join thesis thes"
				+ " on thes.id = tau.paperid and thes.type = 'phdthesis'"
				+ " group by au.name union select au.name as name,count(*) tot "
				+ "from author au inner join journalauthor pa on au.id = pa.authorid  "
				+ "inner join journalpaper p on p.id = pa.paperid inner join thesisauthor tau "
				+ "on tau.authorid = au.id inner join thesis thes on thes.id = tau.paperid "
				+ "and thes.type = 'phdthesis' group by au.name) au  group by au.name "
				+ "having sum(au.tot) >= 2 and sum(au.tot) <= 4";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by keywords
	public void testCreateQueryWithKeywordsSearchParam() {
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
		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());

		String resultQuery = "select au.name as name,sum(au.tot) as tot "
				+ "from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid "
				+ " inner join conferencepaper p on p.id = pa.paperid "
				+ "and p.title like '%pointer%'  and p.title like '%analysis%' "
				+ " group by au.name union select au.name as name,count(*) tot"
				+ " from author au inner join journalauthor pa on au.id = pa.authorid "
				+ " inner join journalpaper p on p.id = pa.paperid and p.title like '%pointer%' "
				+ " and p.title like '%analysis%'  group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by paper title
	public void testCreateQueryWithPaperTitle() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		searchParamObj.setPaperTitle("managing software development");

		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());
		String resultQuery = "select au.name as name,sum(au.tot) as tot"
				+ " from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid "
				+ " inner join conferencepaper p on p.id = pa.paperid and"
				+ " p.title = 'managing software development'  group by au.name"
				+ " union select au.name as name,count(*) tot from author au"
				+ " inner join journalauthor pa on au.id = pa.authorid  "
				+ "inner join journalpaper p on p.id = pa.paperid and p.title = 'managing software development'"
				+ "  group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by publication year end range and journal parameters
	public void testCreateQueryWithPaperPubYearJournalParams() {
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
		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());

		String resultQuery = "select au.name as name,sum(au.tot) as tot from"
				+ " (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid  "
				+ "inner join conferencepaper p on p.id = pa.paperid and p.year <= 2016" + "  group by au.name "
				+ "union select au.name as name,count(*) tot from author au inner join "
				+ "journalauthor pa on au.id = pa.authorid  inner join journalpaper p on p.id = pa.paperid "
				+ "and p.year <= 2016  and p.event_name in( 'TOPLAS','TSE') group by au.name) au "
				+ " group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search by not served in committee before and conference parameters
	public void testCreateQueryNotServedInCommParams() {
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
		String querypattern = "(.*: )(.*)";
		Pattern pattern = Pattern.compile(querypattern);
		Matcher m = pattern.matcher(new QueryBuilder().createQuery(searchParamObj).toString());
		String createdQuery = "";
		if (m.find()) {
			createdQuery = m.group(2);
		}
		String resultQuery = "select au.name as name,sum(au.tot) as tot "
				+ "from (select au.name as name,count(*) tot from author au " + "inner join conferenceauthor pa "
				+ "on au.id = pa.authorid  " + "inner join conferencepaper p "
				+ "on p.id = pa.paperid and p.event_name in( 'OOPSLA','PLDI')"
				+ " where  name not in (select distinct com.memberName from committee com) " + "group by au.name "
				+ "union" + " select au.name as name,count(*) tot from author au"
				+ " inner join journalauthor pa on au.id = pa.authorid "
				+ " inner join journalpaper p on p.id = pa.paperid "
				+ "where  name not in (select distinct com.memberName from committee com)"
				+ " group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}

	@Test
	// Search for incorrect parameters
	public void testCreateQueryWithIncorrectSearchParams() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("thomas");
		ArrayList<String> confList = new ArrayList<String>();
		confList.add("OOPSLA");
		confList.add("PLDI");
		searchParamObj.setCommitteeConferenceNameList(confList);
		searchParamObj.setServedInCommitteeBefore("yes");
		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);
		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);

		String resultQuery = "select pa.authorName, pa.keyVal, p.title, p.year, pa.type "
				+ "from paperauthor pa, paper p " + "where pa.authorName like '%thomas%' " + "and  pa.authorName in "
				+ "(select distinct com.memberName " + "from Committee com "
				+ "where (com.conferenceName = 'OOPSLA' or com.conferenceName = 'PLDI')) " + "and p.keyVal = pa.keyVal";

		assertNotEquals("Created query is incorrect", new QueryBuilder().createQuery(searchParamObj), resultQuery);
	}

	/****************************
	 * SEARCH QUERY TESTS
	 ****************************************/

	@Test // Search for paper with keywords pointer and analysis public void
	public void testSearchWithKeywordsSearchParams() {
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

		int rowsReturned = new QueryBuilder().Search(searchParamObj).size();

		assertTrue("Expected some results to be returned", rowsReturned > 0);
	}

	/****************************
	 * SEARCH QUERY TESTS
	 ****************************************/

	/****************************
	 * FRAME THESIS SUB-QUERY TESTS
	 ******************************/
	@Test
	// tests thesis parameters use cases
	public void testThesisSubQuerySearchParams() {
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

		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());

		String resultQuery = "select au.name as name,sum(au.tot) as tot"
				+ " from (select au.name as name,count(*) tot from author au "
				+ "inner join conferenceauthor pa on au.id = pa.authorid "
				+ " inner join conferencepaper p on p.id = pa.paperid "
				+ "inner join thesisauthor tau on tau.authorid = au.id "
				+ "inner join thesis thes on thes.id = tau.paperid and thes.type = 'mastersthesis' "
				+ "and (thes.school like '%mit%' ) group by au.name union select au.name as name,count(*) tot"
				+ " from author au inner join journalauthor pa on au.id = pa.authorid "
				+ " inner join journalpaper p on p.id = pa.paperid "
				+ "inner join thesisauthor tau on tau.authorid = au.id inner join "
				+ "thesis thes on thes.id = tau.paperid and thes.type = 'mastersthesis' "
				+ "and (thes.school like '%mit%' ) group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Correct query", createdQuery, resultQuery);
	}
	
	
	@Test
	public void testCreateQueryWithAffiliationSearchParams() {
		SearchParameters searchParamObj = new SearchParameters();
		searchParamObj.setAuthorName("frank");
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
		searchParamObj.setAffiliatedUni("northeastern university");

		String createdQuery = extract_createdQuery(new QueryBuilder().createQuery(searchParamObj).toString());
		String resultQuery = "select au.name as name,sum(au.tot) as tot from"
				+ " (select au.name as name,count(*) tot from author au"
				+ " inner join conferenceauthor pa on au.id = pa.authorid and au.name like '%frank%'"
				+ " inner join conferencepaper p on p.id = pa.paperid where "
				+ " name in (select distinct com.memberName from committee com where com.conferenceName "
				+ "in ( 'OOPSLA','PLDI')) and"
				+ " au.id in (select authorid from affiliation where lower(university) like '%northeastern university%')"
				+ " group by au.name union select au.name as name,count(*) tot "
				+ "from author au inner join journalauthor pa on au.id = pa.authorid"
				+ " and au.name like '%frank%' inner join journalpaper p on p.id = pa.paperid"
				+ " where  name in (select distinct com.memberName from committee com"
				+ " where com.conferenceName in ( 'OOPSLA','PLDI')) and au.id"
				+ " in (select authorid from affiliation where lower(university) like '%northeastern university%')"
				+ " group by au.name) au  group by au.name having count(*) > 0";

		assertEquals("Created query correct", createdQuery, resultQuery);
	}
	
	@Test
	// tests thesis parameters use cases
	public void testSimilarSearchServiceCoAuthor() {
		SimilarSearchService similarSearchService = new SimilarSearchService();
		List<AuthorPublications> authorPublications = new ArrayList<AuthorPublications>();
		ArrayList<Author> authors = similarSearchService.similarSearch("coauthor","Frank Tip");
		assertTrue("Results not found for coauthor search",authors.size()>0);
	}
	
	@Test
	// tests thesis parameters use cases
	public void testSimilarSearchServiceAffiliatedAuthor() {
		SimilarSearchService similarSearchService = new SimilarSearchService();
		ArrayList<Author> authors = similarSearchService.similarSearch("university","Frank Tip");
		assertTrue("Results not found for affiliated author search",authors.size()>0);
	}
	
	@Test
	// tests thesis parameters use cases
	public void testSimilarSearchServiceAffiliatedRegionAuthor() {
		SimilarSearchService similarSearchService = new SimilarSearchService();
		ArrayList<Author> authors = similarSearchService.similarSearch("region","Alan Dorin");
		assertTrue("Results not found for affiliated region author search",authors.size()>0);
	}
	
	@Test
	// tests thesis parameters use cases
	public void testSimilarSearchServiceAffiliatedResearchAuthor() {
		SimilarSearchService similarSearchService = new SimilarSearchService();
		ArrayList<Author> authors = similarSearchService.similarSearch("researcharea","Aaron Striegel");
		assertTrue("Results not found for affiliated research area author search",authors.size()>0);
	}
	/****************************
	 * FRAME THESIS SUB-QUERY TESTS
	 ******************************/
}
