package com.msd.queryengine;

import java.util.ArrayList;
import java.util.List;

import com.msd.userinterface.SearchParameters;

/**
 * The Class PaperQueryBuilder builts the required join conditions used to built the required query to fetch
 * authors matching the user given search parameters.
 */
public class PaperQueryBuilder {

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
	 * Used to set the userinput details and the currently accumulated onParams,fromParams,joinParams built by other
	 * components of the QueryBuilder
	 * Allocation for the same is in QueryBuilder. So the currently accumulated contents of these are received into this class.
	 *
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
	 * Creates the relevant join and on params expected in the final query from to get details specific to paper like
	 * conference paper information and journal paper information with filters for the user entered search parameters.
	 *
	 * @param publicationType the publication type which is either conference or journal
	 * @param PaperTable the paper table name which is either conferencepaper or journalpaper
	 * @param AuthorTable the author table which can be either conferenceauthor or journalauthor
	 * @param searchParams the search params entered by the user for which query is to be built
	 */
	public void createPaperQuery(String publicationType, String PaperTable, String AuthorTable,
			SearchParameters searchParams) {
		StringBuilder cond = new StringBuilder();
		joinParams.add(PaperTable);
		cond.append("p.id = pa.paperid");
		// create subquery to search for author similarity for coauthors
		if(searchParams.getpaperTitleSubQueryAuthorName()!=null){
			cond.append(" and p.title in (select distinct p.title from "+PaperTable+" inner join "+AuthorTable+" on "
					+ "p.id = pa.paperid and pa.authorid =(select id from author where name='"+searchParams.getpaperTitleSubQueryAuthorName()+"'))");
		}
		// built filter for user entered paper title
		else if (searchParams.getPaperTitle() != null) {
			cond.append(" and p.title = ? ");
			userInputs.add(searchParams.getPaperTitle());
			userInputType.add(1);
		}

		// builts paper title keyword search filter
		if (searchParams.getKeywords() != null) {
			ArrayList<String> keywordsList = new ArrayList<String>();
			keywordsList.addAll(searchParams.getKeywords());
			for (String keyword : keywordsList) {
				cond.append(" and p.title like ? ");
				userInputs.add("%" + keyword + "%");
				userInputType.add(1);

			}
		}

		// adds publication start year clause
		if (searchParams.getStartYearOfPublication() != -1) {
			cond.append(" and p.year >= ? ");
			userInputs.add(String.valueOf(searchParams.getStartYearOfPublication()));
			userInputType.add(0);

		}

		// adds end year publication range clause
		if (searchParams.getEndYearOfPublication() != -1) {
			cond.append(" and p.year <= ? ");
			userInputs.add(String.valueOf(searchParams.getEndYearOfPublication()));
			userInputType.add(0);

		}

		// forms a composite condition for conference or journal name and creates the filter
		ArrayList<String> confNameList = new ArrayList<String>();
		if(publicationType.equals("conference") && searchParams.getPaperConferenceNameList()!=null){
			confNameList.addAll(searchParams.getPaperConferenceNameList());
		}
		else if(publicationType.equals("journal") && searchParams.getPaperJournalNameList()!=null){
			confNameList.addAll(searchParams.getPaperJournalNameList());
		}
		
		// adds conference names selected by users to the filter 
		if (!confNameList.isEmpty()) {		
			cond.append(" and p.event_name in( ?");
			userInputs.add(confNameList.get(0));
			userInputType.add(1);

			confNameList.remove(0);
			for (String confName : confNameList) {
				cond.append(",?");
				userInputs.add(confName);
				userInputType.add(1);
			}
			cond.append(")");
		}
		// adds the current built filter to on parameters which is finally used to build the query.
		onParams.add(cond.toString());

	}
}
