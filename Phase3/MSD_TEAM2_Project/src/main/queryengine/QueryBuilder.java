package main.queryengine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.interfaces.QueryEngineInterface;
import main.userinterface.SearchParameters;

public class QueryBuilder implements QueryEngineInterface {
 
	// initialization of all the tables to be accessed
	private String PaperAuthorTable = "dblp.paperauthor pa";
	private String PaperTable = "dblp.paper p";
	private String CommitteeTable = "dblp.committee com";
	private String ThesisTable = "dblp.thesis thes";
	private String WWWTable = "dblp.www www";

	// initialization of query parameters
	private String selectParams = "pa.authorName, pa.keyVal, p.title, p.year, pa.type";
	private ArrayList<String> fromParams = new ArrayList<String>();
	private ArrayList<String> whereParams = new ArrayList<String>();
	private String groupByClause;
	private String havingClause;

	public ArrayList<Author> Search(SearchParameters searchParams) {

		// Create query
		String query = createQuery(searchParams);

		ArrayList<Author> resultantAuthorList = new ArrayList<Author>();
		
		// Execute Query and converts it into List of Authors
		resultantAuthorList = executeQuery(query);
		return resultantAuthorList;
	}

	
	// method to frame the sub-query when parameters related to thesis table are present 
	// in search parameters
	public void frameThesisSubQuery(SearchParameters searchParams) {
		ArrayList<String> thesisWhereParams = new ArrayList<String>();
		String subQuery = null;
		String subQueryPaperAuthorTable = "dblp.paperauthor spa";
		
		// if thesis type or school name is given, then form the sub-query
		if (searchParams.getThesisType() != null
				|| searchParams.getSchoolNameList() != null) {
			subQuery = "exists (select 1 from " + ThesisTable + " inner join " + subQueryPaperAuthorTable
					+ " on thes.keyVal = spa.keyVal " + " and spa.authorName like '%" + searchParams.getAuthorName()
					+ "%' where spa.authorName=pa.authorName";

			// adds thesis type parameters
			if (searchParams.getThesisType() != null) {
				subQuery += " and thes.type = '" + searchParams.getThesisType().toLowerCase()+"' ";
			}

			// adds school names to query
			if (searchParams.getSchoolNameList() != null) {
				ArrayList<String> schoolNameList = new ArrayList<String>();
				schoolNameList.addAll(searchParams.getSchoolNameList());

				String cond = "(thes.school like '%" + schoolNameList.get(0) + "%'";
				schoolNameList.remove(0);
				for (String schoolName : schoolNameList) {
					cond += " or thes.school like '%" + schoolName + "%'";
				}
				cond += ")";
				//System.out.println("adding condition :" + cond);
				thesisWhereParams.add(cond);
			}
			if (thesisWhereParams.size() > 0) {
				for (String whereCond : thesisWhereParams) {
					subQuery += " and " + whereCond;
				}
			}
			subQuery += ")";
		}
		if(subQuery!=null){
			whereParams.add(subQuery);
		}
	}

	
	// method to create the query based on given search parameters
	public String createQuery(SearchParameters searchParams) {
		String query = "";

		fromParams.add(PaperAuthorTable);
		fromParams.add(PaperTable);

		/********************* AUTHOR PARAMETERS ***********************/
		// adds author name to where clause
		if (searchParams.getAuthorName() != null) {
			whereParams.add("pa.authorName like '%" + searchParams.getAuthorName() + "%'");
		}

		// forms the sub-query if parameters related to Thesis table is present and adds to where clause
		frameThesisSubQuery(searchParams);
		/********************* AUTHOR PARAMETERS ***********************/

		
		
		/********************* PAPER PARAMETERS ***********************/
		// add paper title to where clause
		if (searchParams.getPaperTitle() != null) {
			if (!whereParams.contains("p.keyVal = pa.keyVal")) {
				whereParams.add("p.keyVal = pa.keyVal");
			}

			String cond = "p.title = '" + searchParams.getPaperTitle() + "'";
			whereParams.add(cond);
		}

		// add keywords to where clause
		if (searchParams.getKeywords() != null) {
			if (!whereParams.contains("p.keyVal = pa.keyVal")) {
				whereParams.add("p.keyVal = pa.keyVal");
			}

			ArrayList<String> keywordsList = new ArrayList<String>();
			keywordsList.addAll(searchParams.getKeywords());
			for (String keyword : keywordsList) {
				whereParams.add("p.title like '%" + keyword + "%'");
			}
		}

		// adds start year of publication to where clause
		if (searchParams.getStartYearOfPublication() != -1) {
			if (!whereParams.contains("p.keyVal = pa.keyVal")) {
				whereParams.add("p.keyVal = pa.keyVal");
			}

			String cond = "p.year >= " + searchParams.getStartYearOfPublication();
			whereParams.add(cond);
		}

		// adds end year of publication range to where clause
		if (searchParams.getEndYearOfPublication() != -1) {
			if (!whereParams.contains("p.keyVal = pa.keyVal")) {
				whereParams.add("p.keyVal = pa.keyVal");
			}

			String cond = "p.year <= " + searchParams.getEndYearOfPublication();
			whereParams.add(cond);
		}

		// if conference or journal parameters is present, it adds type parameter to where clause
		if (searchParams.getPaperConferenceNameList() != null && searchParams.getPaperJournalNameList() != null) {
			whereParams.add("p.type in ('inproceedings','article')");
		} else {
			if (searchParams.getPaperConferenceNameList() != null) {
				whereParams.add("p.type = 'inproceedings'");
			}
			if (searchParams.getPaperJournalNameList() != null) {
				whereParams.add("p.type = 'article'");
			}
		}

		// forms a composite condition for conference and journal name and adds it to where clause
		if (searchParams.getPaperConferenceNameList() != null || searchParams.getPaperJournalNameList() != null) {
			if (!whereParams.contains("p.keyVal = pa.keyVal")) {
				whereParams.add("p.keyVal = pa.keyVal");
			}

			String cond = "(";

			// adds conference names to where clause
			if (searchParams.getPaperConferenceNameList() != null) {
				ArrayList<String> confNameList = new ArrayList<String>();
				confNameList.addAll(searchParams.getPaperConferenceNameList());

				cond += "p.keyVal like 'conf/" + confNameList.get(0) + "/%'";
				confNameList.remove(0);
				for (String confName : confNameList) {
					cond += " or p.keyVal like 'conf/" + confName + "/%'";
				}
			}

			// adds journal names to where clause
			if (searchParams.getPaperJournalNameList() != null) {
				ArrayList<String> journalNameList = new ArrayList<String>();
				journalNameList.addAll(searchParams.getPaperJournalNameList());

				if (cond.length() > 1) {
					cond += " or ";
				}

				cond += "p.keyVal like 'journals/" + journalNameList.get(0) + "/%'";
				journalNameList.remove(0);
				for (String journalName : journalNameList) {
					cond += " or p.keyVal like 'journals/" + journalName + "/%'";
				}
			}

			cond += ")";
			whereParams.add(cond);
		}
		/********************* PAPER PARAMETERS ***********************/

		
		
		/********************* COMMITTEE PARAMETERS ***********************/
		// adds committee related parameters to where clause
		if (searchParams.hasServedInCommitteeBefore() != null) {

			String subQuery = "";
			
			// if author has served/not served in committee before
			if (searchParams.hasServedInCommitteeBefore().toLowerCase().equals("yes")) {
				subQuery += " pa.authorName in ";
			} else {
				subQuery += " pa.authorName not in ";
			}

			// composite condition for conference name and year held (for committee) 
			subQuery += "(select distinct com.memberName from " + CommitteeTable;
			ArrayList<String> whereParamsForSubQuery = new ArrayList<String>();
			if (searchParams.getServedInCommitteeStartYear() != -1 || searchParams.getServedInCommitteeEndYear() != -1
					|| searchParams.getCommitteeConferenceNameList() != null) {

				// adds start year range to where clause
				if (searchParams.getServedInCommitteeStartYear() != -1) {
					String cond = "com.year >= " + searchParams.getServedInCommitteeStartYear();
					whereParamsForSubQuery.add(cond);
				}

				// adds end year range to where clause
				if (searchParams.getServedInCommitteeEndYear() != -1) {
					String cond = "com.year <= " + searchParams.getServedInCommitteeEndYear();
					whereParamsForSubQuery.add(cond);
				}

				// adds conference names to where clause
				if (searchParams.getCommitteeConferenceNameList() != null) {
					ArrayList<String> confNameList = new ArrayList<String>();
					confNameList.addAll(searchParams.getCommitteeConferenceNameList());
					String cond = "(com.conferenceName = '" + confNameList.get(0) + "'";
					confNameList.remove(0);
					for (String confName : confNameList) {
						cond += " or com.conferenceName = '" + confName + "'";
					}
					cond += ")";
					whereParamsForSubQuery.add(cond);
				}
			}

			if (whereParamsForSubQuery.size() > 0) {
				subQuery += " where " + whereParamsForSubQuery.get(0);
				whereParamsForSubQuery.remove(0);
				for (String cond : whereParamsForSubQuery) {
					subQuery += " and " + cond;
				}
			}
			subQuery += ")";

			whereParams.add(subQuery);
		}
		/********************* COMMITTEE PARAMETERS ***********************/

		
		
		/********************* #PAPERS PARAMETERS ***********************/
		// adds number of papers search parameters to where clause
		if (searchParams.getExactNumberOfPapers() != -1 || searchParams.getNumberOfPapersStartRange() != -1
				|| searchParams.getNumberOfPapersEndRange() != -1) {
			groupByClause = " group by pa.authorName";
			havingClause = " having count(p.id) ";

			// to search for exact number of papers written by paper
			if (searchParams.getExactNumberOfPapers() != -1) {
				havingClause += "= " + searchParams.getExactNumberOfPapers();
			} else {
				//to search based on range
				
				// adds start range to where clause
				if (searchParams.getNumberOfPapersStartRange() != -1) {
					havingClause += ">= " + searchParams.getNumberOfPapersStartRange();
				}
				
				// adds end range to where clause
				if (searchParams.getNumberOfPapersEndRange() != -1) {
					if (havingClause.contains(">=")) {
						havingClause += " and count(p.id) ";
					}
					havingClause += "<= " + searchParams.getNumberOfPapersEndRange();
				}
			}
		}
		/********************* #PAPERS PARAMETERS ***********************/

		// forms the final query
		
		if (!whereParams.contains("p.keyVal = pa.keyVal")) {
			whereParams.add("p.keyVal = pa.keyVal");
		}

		query = "select " + selectParams + " from " + fromParams.get(0);

		fromParams.remove(0);
		for (String fromTable : fromParams) {
			query += ", " + fromTable;
		}

		if (whereParams.size() > 0) {
			query += " where " + whereParams.get(0);
			whereParams.remove(0);
			for (String whereCond : whereParams) {
				query += " and " + whereCond;
			}
		}

		if (groupByClause != null) {
			query += groupByClause;
		}
		if (havingClause != null) {
			query += havingClause;
		}

		//System.out.println(query);

		return query;
	}

	
	// method that executes the query provided and returns list of authors
	public ArrayList<Author> executeQuery(String query) {
		SystemUserQuery sq = new SystemUserQuery();
		Connection conn = sq.connectToDB();

		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			ArrayList<Author> resultantAuthorList = new ArrayList<Author>();
			while (rs.next()) {
				String keyVal = rs.getString("keyVal");
				String name = rs.getString("authorName");
				String title = rs.getString("title");
				String type = rs.getString("type");
				int year = rs.getInt("year");
				Author author = new Author(name, keyVal, title, type, year);
				resultantAuthorList.add(author);
			}

			return resultantAuthorList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
