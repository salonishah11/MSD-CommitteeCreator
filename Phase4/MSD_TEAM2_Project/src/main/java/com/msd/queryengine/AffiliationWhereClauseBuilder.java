package com.msd.queryengine;

import java.util.List;

import com.msd.userinterface.SearchParameters;

/**
 * AffiliationWhereClauseBuilder creates where clause that makes uses of tables affiliation,researchinfo which are mainly used
 * for similar search finding and filtering on affiliated university from the main search page.
 */
public class AffiliationWhereClauseBuilder {

	/** Maintains the list of userinputs which is later used to prepare dynamic preparedstatement */
	private List<String> userInputs;
	
	/** Maintation type of each userInput 1 means string 0 means integer. */
	private List<Integer> userInputType;
	
	/** Maintains and accumulates where clauses on the go. Finally whereclause accumulated is built in QueryBuilder */
	private List<String> whereParams;

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
	 * Builds the where clause for affiliation or researcharea based on the SearchParameters passed. Accumulates the built where
	 * clause into where clause built so far.
	 *
	 * @param searchParams The SearchParameters for which the where clause if to be built
	 */
	public void createAffiliationWhereClause(SearchParameters searchParams) {
		// where clause is built only is one of the affiliated search params is set
		if (searchParams.getAffiliatedUni() != null||searchParams.getAffiliatedUniSubQueryAuthorName()!=null
				||searchParams.getAffiliatedRegionSubQueryAuthorName()!=null||searchParams.getAffiliatedResearchAreaSubQueryAuthorName()!=null) {
			StringBuilder subQuery = new StringBuilder();
			if (whereParams.isEmpty()) {
				subQuery.append(" au.id in ");
			} else {
				subQuery.append(" and au.id in ");
			}
			// for affiliated university similar search
			if (searchParams.getAffiliatedUniSubQueryAuthorName() != null) {
				subQuery.append("(select authorid from affiliation where lower(university) = "
						+ "(select lower(university) from affiliation where authorname='"+searchParams.getAffiliatedUniSubQueryAuthorName()+"'))");
			}
			// for same region similar search
			else if(searchParams.getAffiliatedRegionSubQueryAuthorName()!=null){
				subQuery.append("(select authorid from affiliation where lower(region) = "
						+ "(select lower(region) from affiliation where authorname='"+searchParams.getAffiliatedRegionSubQueryAuthorName()+"'))");
			}
			// for same research area similar search
			else if(searchParams.getAffiliatedResearchAreaSubQueryAuthorName()!=null){
				subQuery.append("(select distinct authorid from researchinfo where lower(researcharea) in "
						+ "(select lower(researcharea) from researchinfo where authorname='"+searchParams.getAffiliatedResearchAreaSubQueryAuthorName()+"'))");
			}
			// for normal user filter for affiliation from the main page.
			else {
				subQuery.append("(select authorid from affiliation where lower(university) like ?)");
				userInputs.add("%"+searchParams.getAffiliatedUni().toLowerCase()+"%");
				userInputType.add(1);	
			}
			// Accumulates result into currently built where clause list
			whereParams.add(subQuery.toString());
		}
	}
}
