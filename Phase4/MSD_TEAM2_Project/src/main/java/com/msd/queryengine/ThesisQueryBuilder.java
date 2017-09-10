package com.msd.queryengine;

import static com.msd.queryengine.QueryBuilderConstants.THESISAUTHORTABLE;
import static com.msd.queryengine.QueryBuilderConstants.THESISTABLE;

import java.util.ArrayList;
import java.util.List;

import com.msd.userinterface.SearchParameters;

/**
 * The Class ThesisQueryBuilder builds the where clause for filters on thesis tables.
 */
public class ThesisQueryBuilder {

	/** Maintains the list of userinputs which is later used to prepare dynamic preparedstatement */
	List<String> userInputs;
	
	/** Maintain type of each userInput 1 means string 0 means integer. */
	List<Integer> userInputType;
	
	/** Maintains the list of from table params used to build the final query. */
	List<String> fromParams;
	
	/** Maintains the list of tables used in the join condition to build the final query */
	List<String> joinParams;
	
	/** The Maintains the list of on conditions used for joining different tables maintained in JoinParams */
	List<String> onParams;

	/**
	 * /**
	  * Used to set the userinput details and the currently accumulated onParams,fromParams,joinParams built by other
	 * components of the QueryBuilder
	 * Allocation for the same is in QueryBuilder. So the currently accumulated contents of these are received into this class.
	 * @param joinParams Maintains the list of tables used in the join condition to build the final query
	 * @param onParams The Maintains the list of on conditions used for joining different tables maintained in JoinParams
	 * @param fromParams Maintains the list of from table params used to build the final query.
	 * @param userInputs The current list of user input data
	 * @param userInputType The current list of user input data type
	 */
	public void setParams(List<String> joinParams, List<String> onParams, List<String> fromParams,
			List<String> userInputs, List<Integer> userInputType) {
		this.userInputs = userInputs;
		this.userInputType = userInputType;
		this.fromParams = fromParams;
		this.joinParams = joinParams;
		this.onParams = onParams;

	}

	/**
	 * Creates the resultant query conditions to be used to filter on thesis table contents.
	 *
	 * @param searchParams Search Parameters entered by the user to filter details
	 */
	public void createThesisQuery(SearchParameters searchParams) {
		// if thesis type or school name is given, then form the sub-query
		if (searchParams.getThesisType() != null || searchParams.getSchoolNameList() != null) {
			joinParams.add(THESISAUTHORTABLE);
			onParams.add("tau.authorid = au.id");
			joinParams.add(THESISTABLE);
			StringBuilder thesisonParams = new StringBuilder();
			thesisonParams.append("thes.id = tau.paperid");
			// Populates thesis type filter if entered by user
			if (searchParams.getThesisType() != null) {
				thesisonParams.append(" and thes.type = ?");
				userInputs.add(searchParams.getThesisType());
				userInputType.add(1);
			}
			// populates school name filter with or condition among various school names
			if (searchParams.getSchoolNameList() != null) {
				ArrayList<String> schoolNameList = new ArrayList<String>();
				schoolNameList.addAll(searchParams.getSchoolNameList());
				thesisonParams.append(" and (thes.school like ? ");
				userInputs.add("%" + schoolNameList.get(0) + "%");
				userInputType.add(1);
				schoolNameList.remove(0);
				for (String schoolName : schoolNameList) {
					thesisonParams.append(" or thes.school like ? ");
					userInputs.add("%" + schoolName + "%");
					userInputType.add(1);
				}
				thesisonParams.append(")");
			}
			onParams.add(thesisonParams.toString());
		}
	}
}
