package com.msd.queryengine;

import java.util.List;

import com.msd.userinterface.SearchParameters;

/**
 * Builds and generates the group by where clause required by QueryBuilder
 */
public class GroupByClauseBuilder {

	/** The having clause. */
	String havingClause;
	
	/** Maintains the list of userinputs which is later used to prepare dynamic preparedstatement */
	List<String> userInputs;
	
	
	/** Maintain type of each userInput 1 means string 0 means integer. */
	List<Integer> userInputType;
	
	/**
	 * Used to set the userinput details
	 * Allocation for the same is in QueryBuilder. So the currently accumulated contents of these are received into this class.
	 *
	 * @param userInputs The current list of user input data
	 * @param userInputType The current list of user input data type
	 */
	public void setParams(List<String> userInputs, List<Integer> userInputType){
		this.userInputs = userInputs;
		this.userInputType = userInputType;
	}

	/**
	 * Forms the group by where clause based on the SearchParameters given as input
	 *
	 * @param searchParams The user input Search parameters based on which the group by having clause is built
	 * @return The string representing the group by having clause
	 */
	public String createGroupByHavingClause(SearchParameters searchParams) {
		String groupbyhavingclause = "";
		String groupByClause = " group by au.name";
		// If select cols required is found in search params, same is used to build the columns used in group by
		if(searchParams.getSelectColumns()!=null){
			groupByClause = " group by "+searchParams.getSelectColumns();
		}
		
		// default having clause
		havingClause = " having count(*) > 0";
		if (searchParams.getExactNumberOfPapers() != -1 || searchParams.getNumberOfPapersStartRange() != -1
				|| searchParams.getNumberOfPapersEndRange() != -1) {
			// If papers count has to be accumulated, then sum up the contributions from two subqueries, one from conferencepaper
			// table and one from journalpaper table
			havingClause = " having sum(au.tot) ";

			// to search for exact number of papers written by paper
			if (searchParams.getExactNumberOfPapers() != -1) {
				havingClause += "= ?";
				userInputs.add(String.valueOf(searchParams.getExactNumberOfPapers()));
				userInputType.add(0);
			} else {
				// to search based on range
				// adds start range to where clause
				if (searchParams.getNumberOfPapersStartRange() != -1) {
					havingClause += ">= ?";
					userInputs.add(String.valueOf(searchParams.getNumberOfPapersStartRange()));
					userInputType.add(0);
				}

				// adds end range to where clause
				if (searchParams.getNumberOfPapersEndRange() != -1) {
					createHavingClauseEndRange(searchParams);
				}
			}
		}
		groupbyhavingclause += groupByClause;
		groupbyhavingclause += havingClause;
		return groupbyhavingclause;
	}

	/**
	 * Helper function to build the having clause end range.
	 *
	 * @param searchParams The search parameters based on which the having clause is built.
	 */
	private void createHavingClauseEndRange(SearchParameters searchParams) {
		if (havingClause.contains(">=")) {
			havingClause += " and sum(au.tot) ";
		}
		havingClause += "<= ?";
		userInputs.add(String.valueOf(searchParams.getNumberOfPapersEndRange()));
		userInputType.add(0);
	}
}
