package com.msd.queryengine.services;
import static com.msd.queryengine.QueryBuilderConstants.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.msd.interfaces.SimilarSearchInterface;
import com.msd.queryengine.QueryBuilder;
import com.msd.queryengine.QueryBuilderFactory;
import com.msd.queryengine.QueryExecutor;
import com.msd.queryengine.model.Author;
import com.msd.userinterface.SearchParameters;

/**
 * SimilarSearch Service provides services to search similarity with currently selected author based
 * on a number of criteria. This class acts as adapter where QueryBuilder acts as adaptee
 * that serves the createQuery(the adaptee interface that is used) purpose for the similarity search.
 */
public class SimilarSearchService implements SimilarSearchInterface{
	
	/**
	 * Searches for authors who are similar to the given author based on if they have coauthored a paper together or is similar
	 * in terms of region,university or research area based on input parameter.
	 * Adapts the createQuery interface provided by the adaptee class QueryBuilder
	 * @param searchType One of the search types supported
	 * @param authorName 
	 * The author Name for which the similarity search is to be done.
	 * @return The list of similar authors that includes the name type title year and the publication name associated with similar authors.
	 */
	public ArrayList<Author> similarSearch(String searchType, String authorName){
		SearchParameters searchParameters = new SearchParameters();
		QueryBuilderFactory queryBuilderFactory = new QueryBuilderFactory();
		if(searchType == "coauthor"){
			searchParameters.setpaperTitleSubQueryAuthorName(authorName);
		}
		else if(searchType == "university"){
			searchParameters.setAffiliatedUniSubQueryAuthorName(authorName);
		}
		else if(searchType == "region"){
			searchParameters.setAffiliatedRegionSubQueryAuthorName(authorName);
		}
		else if(searchType == "researcharea"){
			searchParameters.setAffiliatedResearchAreaSubQueryAuthorName(authorName);
		}
		searchParameters.setSelectColumns("name,type,title,year,event_name");
		QueryBuilder queryBuilder= queryBuilderFactory.makeQueryBuilder();
		QueryExecutor queryExecutor = queryBuilderFactory.makeQueryExecutor();
		PreparedStatement querystmnt = queryBuilder.createQuery(searchParameters);
		return queryExecutor.executeQuery(querystmnt,searchParameters);
	}
	
}
