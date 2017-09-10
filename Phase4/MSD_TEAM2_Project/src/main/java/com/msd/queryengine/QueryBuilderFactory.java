package com.msd.queryengine;

/**
 * A factory for creating QueryBuilder components.
 */
public class QueryBuilderFactory {

	/**
	 * Make group by clause.
	 *
	 * @return the group by clause builder
	 */
	public GroupByClauseBuilder makeGroupByClause(){
		return new GroupByClauseBuilder();
	}
	
	/**
	 * Make Committee where clause builder.
	 *
	 * @return the committee where clause builder
	 */
	public CommitteeWhereClauseBuilder makeCommitteWhereClauseBuilder(){
		return new CommitteeWhereClauseBuilder();
	}
	
	/**
	 * Make paper query builder.
	 *
	 * @return the paper query builder
	 */
	public PaperQueryBuilder makePaperQueryBuilder(){
		return new PaperQueryBuilder();
	}
	
	/**
	 * Make thesis query builder.
	 *
	 * @return the thesis query builder
	 */
	public ThesisQueryBuilder makeThesisQueryBuilder(){
		return new ThesisQueryBuilder();
	}
	
	/**
	 * Make affiliation where clause builder.
	 *
	 * @return the affiliation where clause builder
	 */
	public AffiliationWhereClauseBuilder makeAffiliationWhereClauseBuilder(){
		return new AffiliationWhereClauseBuilder();
	}
	
	/**
	 * Make query builder.
	 *
	 * @return the query builder
	 */
	public QueryBuilder makeQueryBuilder(){
		return new QueryBuilder();
	}
	
	/**
	 * Make query executor.
	 *
	 * @return the query executor
	 */
	public QueryExecutor makeQueryExecutor(){
		return new QueryExecutor();
	}
}
