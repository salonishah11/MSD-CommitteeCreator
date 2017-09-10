package com.msd.queryengine;

import java.util.ArrayList;
import java.util.List;

import static com.msd.queryengine.QueryBuilderConstants.*;
import com.msd.userinterface.SearchParameters;


/**
 * CommitteeWhereClauseBuilder builds the where clause required to filter the search results in the main page based on different
 * parameters associated with committee data like served before/not, specific conference or journal committee served
 */
public class CommitteeWhereClauseBuilder {

	/** Maintains the list of userinputs which is later used to prepare dynamic preparedstatement */
	List<String> userInputs;
	
	/** Maintain type of each userInput 1 means string 0 means integer. */
	List<Integer> userInputType;
	
	/** Maintains and accumulates where clauses on the go. Finally whereclause accumulated is built in QueryBuilder */
	List<String> whereParams;
	


	/**
	 * Used to set the userinput details and the currently accumulated whereclause from other whereclause builders
	 * Allocation for the same is in QueryBuilder. So the currently accumulated contents of these are received into this class.
	 *
	 * @param whereParams The currently accumulated where clause from different where clause builders
	 * @param userInputs The current list of user input data
	 * @param userInputType The current list of user input data type
	 */
	public void setParams(List<String> whereParams, List<String> userInputs, List<Integer> userInputType) {
		this.userInputs = userInputs;
		this.userInputType = userInputType;
		this.whereParams = whereParams;
	}

	/**
	  * Builds the where clause for committee based on user search criteria such as served previously or not
	  * specific served years, or specific conference/journal committees served. Accumulates the built where clause 
	  * into where clause built so far.
	 * @param searchParams The user input Search parameters based on which the where clause is built
	 */
	public void createCommitteeWhereClause(SearchParameters searchParams) {
		if (searchParams.hasServedInCommitteeBefore() != null) {
			StringBuilder subQuery = new StringBuilder();
			// if author has served/not served in committee before
			if (searchParams.hasServedInCommitteeBefore().equalsIgnoreCase("yes")) {
				subQuery.append(" name in ");
			} else {
				subQuery.append(" name not in ");
			}

			// composite condition for conference name and year held (for committee)
			subQuery.append("(select distinct com.memberName from " + COMMITTEETABLE);
			ArrayList<String> whereParamsForSubQuery = new ArrayList<String>();

			// adds start year range to where clause
			if (searchParams.getServedInCommitteeStartYear() != -1) {
				userInputs.add(String.valueOf(searchParams.getServedInCommitteeStartYear()));
				userInputType.add(0);
				whereParamsForSubQuery.add("com.year >= ? ");
			}

			// adds end year range to where clause
			if (searchParams.getServedInCommitteeEndYear() != -1) {
				userInputs.add(String.valueOf(searchParams.getServedInCommitteeEndYear()));
				userInputType.add(0);
				whereParamsForSubQuery.add("com.year <= ?");
			}

			// adds conference names to where clause
			if (searchParams.getCommitteeConferenceNameList() != null) {
				ArrayList<String> confNameList = new ArrayList<String>();
				confNameList.addAll(searchParams.getCommitteeConferenceNameList());
				StringBuilder cond = new StringBuilder();
				cond.append("com.conferenceName in ( ?");
				userInputs.add(confNameList.get(0));
				userInputType.add(1);
				confNameList.remove(0);
				for (String confName : confNameList) {
					cond.append(",?");
					userInputs.add(confName);
					userInputType.add(1);
				}
				cond.append(")");
				whereParamsForSubQuery.add(cond.toString());
			}

			// builts the where clause
			if (!whereParamsForSubQuery.isEmpty()) {
				subQuery.append(" where " + whereParamsForSubQuery.get(0));
				whereParamsForSubQuery.remove(0);
				for (String cond : whereParamsForSubQuery) {
					subQuery.append(" and " + cond);
				}
			}
			subQuery.append(")");

			whereParams.add(subQuery.toString());
		}
	}
}
