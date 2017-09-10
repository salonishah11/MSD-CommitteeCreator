package com.msd.DBLPParser;

import org.apache.logging.log4j.Logger;

/**
 * A factory for creating Parser objects.
 */
public class ParserFactory {

	/** The Database connection object. */
	private ParserDBConnector conn;
	
	/** The logger. */
	private Logger logger;
	
	/**
	 * Instantiates a new parser factory.
	 *
	 * @param conn the conn
	 * @param logger the logger
	 */
	public ParserFactory(ParserDBConnector conn,Logger logger){
		this.conn = conn;
		this.logger = logger;
	}
	
	/**
	 * Make inproceeding parser.
	 *
	 * @return the inproceeding parser
	 */
	public InproceedingParser makeInpParser() {
		return new InproceedingParser(conn, logger);
	}
	
	/**
	 * Make phd thesis parser.
	 *
	 * @return the phd thesis parser
	 */
	public PhdThesisParser makePhdThesisParser() {
		return new PhdThesisParser(conn, logger);
	}
	
	/**
	 * Make master thesis parser.
	 *
	 * @return the master thesis parser
	 */
	public MasterThesisParser makeMasterThesisParser() {
		return new MasterThesisParser(conn, logger);
	}
	
	/**
	 * Make CS rankings parser.
	 *
	 * @return the CS rankings parser
	 */
	public CSRankingsParser makeCSRankingsParser() {
		return new CSRankingsParser(logger,conn);
	}
	
	/**
	 * Make committee parser.
	 *
	 * @return the committee parser
	 */
	public CommitteeParser makeCommitteeParser() {
		return new CommitteeParser(logger,conn);
	}
	
	/**
	 * Make www parser.
	 *
	 * @return the www parser
	 */
	public wwwParser makewwwParser(){
		return new wwwParser(conn, logger);
	}
	
	/**
	 * Make article parser.
	 *
	 * @return the article parser
	 */
	public ArticleParser makeArticleParser(){
		return new ArticleParser(conn, logger);
	}
	
	/**
	 * Make CS rankings country parser.
	 *
	 * @return the CS rankings country parser
	 */
	public CSRankingsCountryParser makeCSRankingsCountryParser() {
		return new CSRankingsCountryParser(logger,conn);
	}
}
