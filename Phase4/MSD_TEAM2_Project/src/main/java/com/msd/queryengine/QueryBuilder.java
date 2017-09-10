package com.msd.queryengine;

import static com.msd.queryengine.QueryBuilderConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.interfaces.QueryEngineInterface;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.SearchParameters;

/**
 * The Class QueryBuilder accepts factory components to build the query. Then frames the final query by making use of the 
 * data structures updated by the query builder factory components.
 */
public class QueryBuilder implements QueryEngineInterface {

	/** Builds the group by clause for the query built by QueryBuilder. */
	private GroupByClauseBuilder groupByClauseBuilder;
	
	/** The committee where clause builder. */
	private CommitteeWhereClauseBuilder committeeWhereClauseBuilder;
	
	/** The affiliation where clause builder. */
	private AffiliationWhereClauseBuilder affiliationWhereClauseBuilder;
	
	/** The paper query builder. */
	private PaperQueryBuilder paperQueryBuilder;
	
	/** The thesis query builder. */
	private ThesisQueryBuilder thesisQueryBuilder;

	/** The select columns for the query being built */
	private String selectParams;
	
	/** The from table details used to build the final table */
	private ArrayList<String> fromParams;
	
	/** The where params used to build the query filter */
	private ArrayList<String> whereParams;
	
	/** The join params used to frame the tables used in join */
	private ArrayList<String> joinParams;
	
	/** The on params used for on condition */
	private ArrayList<String> onParams;
	
	/** The user inputs used to build dynamic prepared statement */
	private ArrayList<String> userInputs;
	
	/** The user input type related to the user inputs */
	private ArrayList<Integer> userInputType;
	
	/** The group by clause. */
	private String groupByClause;
	
	/** The having clause. */
	private String havingClause;
	
	/** The query executor. */
	private QueryExecutor queryExecutor;
	
	/** The logger. */
	private static Logger logger = LogManager.getLogger(QueryBuilder.class);
	
	/** The Database connection object. */
	Connection conn;

	/**
	 * Instantiates a new query builder.
	 */
	public QueryBuilder() {
		// Initialize datastructures used by QueryBuilder to build the query.
		fromParams = new ArrayList<String>();
		whereParams = new ArrayList<String>();
		joinParams = new ArrayList<String>();
		onParams = new ArrayList<String>();
		userInputs = new ArrayList<String>();
		userInputType = new ArrayList<Integer>();
		
		// Factory component instantiation for QueryBuilder components.
		QueryBuilderFactory queryBuilderFactory = new QueryBuilderFactory();
		groupByClauseBuilder = queryBuilderFactory.makeGroupByClause();
		groupByClauseBuilder.setParams(userInputs, userInputType);
		committeeWhereClauseBuilder = queryBuilderFactory.makeCommitteWhereClauseBuilder();
		committeeWhereClauseBuilder.setParams(whereParams, userInputs, userInputType);
		affiliationWhereClauseBuilder = queryBuilderFactory.makeAffiliationWhereClauseBuilder();
		affiliationWhereClauseBuilder.setParams(whereParams, userInputs, userInputType);
		paperQueryBuilder = queryBuilderFactory.makePaperQueryBuilder();
		paperQueryBuilder.setParams(joinParams, onParams, fromParams, userInputs, userInputType);
		thesisQueryBuilder = queryBuilderFactory.makeThesisQueryBuilder();
		thesisQueryBuilder.setParams(joinParams, onParams, fromParams, userInputs, userInputType);
		queryExecutor = queryBuilderFactory.makeQueryExecutor();
		SystemUserQuery sq = new SystemUserQuery();
		conn = sq.connectToDB();
	}

	/* (non-Javadoc)
	 * @see com.msd.interfaces.QueryEngineInterface#Search(com.msd.userinterface.SearchParameters)
	 */
	public ArrayList<Author> Search(SearchParameters searchParams) {
		// Create query
		PreparedStatement stmntquery = createQuery(searchParams);

		ArrayList<Author> resultantAuthorList;

		// Execute Query and converts it into List of Authors
		resultantAuthorList = queryExecutor.executeQuery(stmntquery,searchParams);
		return resultantAuthorList;
	}

	/**
	 * Sets the select columns to be used in the query.
	 *
	 * @param searchParameters Search parameters entered by the user for which the final query is to be built
	 * @param aggregator aggregator column in the select statement either count or sum.
	 */
	private void setSelectCols(SearchParameters searchParameters,String aggregator) {
		if (searchParameters.getSelectColumns() != null) {
			selectParams = searchParameters.getSelectColumns();
		} else {
			selectParams = "au.name as name";
		}
		selectParams += ","+aggregator;
	}
	
	/**
	 * Sets the group by clause for building the group by clause for final query
	 *
	 * @param searchParameters Search Parameters entered by the user.
	 */
	private void setGroupByClause(SearchParameters searchParameters) {
		if (searchParameters.getSelectColumns() != null) {
			groupByClause = " group by "+searchParameters.getSelectColumns();
		}
		else{
			groupByClause = " group by au.name";
		}
	}

	/**
	 * Creates the query based on the user entered search parameters.
	 *
	 * @param searchParams User entered search parameters.
	 * @return Prepared statement representing the final query which is to be executed.
	 */
	// method to create the query based on given search parameters
	public PreparedStatement createQuery(SearchParameters searchParams) {
		setSelectCols(searchParams,"count(*) tot");
		fromParams.add(AUTHORTABLE);
		joinParams.add(CONFERENCEAUTHORTABLE);
		// Sets on condition for author name
		if (searchParams.getAuthorName() != null) {
			onParams.add("au.id = pa.authorid and au.name like ?");
			userInputs.add("%" + searchParams.getAuthorName() + "%");
			userInputType.add(1);
		} else {
			onParams.add("au.id = pa.authorid ");
		}

		// frames the conditions required on paper table query
		paperQueryBuilder.createPaperQuery("conference", PAPERTABLE, CONFERENCEAUTHORTABLE, searchParams);
		thesisQueryBuilder.createThesisQuery(searchParams);
		committeeWhereClauseBuilder.createCommitteeWhereClause(searchParams);
		affiliationWhereClauseBuilder.createAffiliationWhereClause(searchParams);
		setGroupByClause(searchParams);
		
		// builds the subquery for fetching relevant conference information based on currently set datastructures
		String conferencesubquery = buildQuery();
		resetbuildQueryParams();
		
		fromParams.add(AUTHORTABLE);
		joinParams.add(JOURNALAUTHORTABLE);
		if (searchParams.getAuthorName() != null) {
			onParams.add("au.id = pa.authorid and au.name like ?");
			userInputs.add("%" + searchParams.getAuthorName() + "%");
			userInputType.add(1);
		} else {
			onParams.add("au.id = pa.authorid ");
		}

		paperQueryBuilder.createPaperQuery("journal", JOURNALPAPERTABLE, JOURNALAUTHORTABLE, searchParams);
		thesisQueryBuilder.createThesisQuery(searchParams);
		committeeWhereClauseBuilder.createCommitteeWhereClause(searchParams);
		affiliationWhereClauseBuilder.createAffiliationWhereClause(searchParams);
		
		// Builds the journal subquery based on currently set datastructures
		String journalsubquery = buildQuery();
		String groupByHavingClause = groupByClauseBuilder.createGroupByHavingClause(searchParams);
		setSelectCols(searchParams, "sum(au.tot) as tot");
		String finalQuery = "select "+selectParams+" from (" + conferencesubquery + " union " + journalsubquery + ") au ";
		finalQuery += groupByHavingClause;

		PreparedStatement querystmnt = null;
		try {
			querystmnt = conn.prepareStatement(finalQuery);
			int index = 0;
			while (index < userInputs.size()) {
				if (userInputType.get(index) == 1) {
					querystmnt.setString(index + 1, userInputs.get(index));
				} else {
					querystmnt.setInt(index + 1, Integer.parseInt(userInputs.get(index)));
				}
				index += 1;
			}
		} catch (SQLException e) {
			logger.error("Failed to execute search query");
		}
		return querystmnt;

	}

	/**
	 * Resets currently set query params datastructures
	 */
	private void resetbuildQueryParams() {
		fromParams.clear();
		whereParams.clear();
		joinParams.clear();
		onParams.clear();
	}

	/**
	 * Builds the query based on currently set datastructures
	 *
	 * @return the string
	 */
	private String buildQuery() {
		StringBuilder query = new StringBuilder();
		query.append("select " + selectParams + " from " + fromParams.get(0));

		int index = 0;
		for (String joinParam : joinParams) {
			query.append(" inner join " + joinParam);
			query.append(" on " + onParams.get(index));
			index += 1;
		}
		if (!whereParams.isEmpty()) {
			query.append(" where " + whereParams.get(0));
			whereParams.remove(0);
			for (String whereParam : whereParams) {
				query.append(whereParam);
			}
		}
		query.append(groupByClause);
		if (havingClause != null) {
			query.append(havingClause);
		}
		return query.toString();
	}

}
