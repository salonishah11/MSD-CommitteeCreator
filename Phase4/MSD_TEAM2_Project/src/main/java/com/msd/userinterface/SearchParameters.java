package com.msd.userinterface;

import java.util.ArrayList;

/**
 * The Class SearchParameters.
 */
public class SearchParameters {

	/** The author name. */
	private String authorName;
	
	/** The school name list. */
	private ArrayList<String> schoolNameList;
	
	/** The thesis type. */
	private String thesisType;
	
	/** The exact number of papers. */
	private int exactNumberOfPapers;
	
	/** The number of papers start range. */
	private int numberOfPapersStartRange;
	
	/** The number of papers end range. */
	private int numberOfPapersEndRange;
	
	/** The served in committee before. */
	private String servedInCommitteeBefore;
	
	/** The served in committee start year. */
	private int servedInCommitteeStartYear;
	
	/** The served in committee end year. */
	private int servedInCommitteeEndYear;
	
	/** The committee conference name list. */
	private ArrayList<String> committeeConferenceNameList;	
	
	/** The paper title. */
	private String paperTitle;
	
	/** The keywords. */
	private ArrayList<String> keywords;	
	
	/** The start year of publication. */
	private int startYearOfPublication;
	
	/** The end year of publication. */
	private int endYearOfPublication;
	
	/** The paper conference name list. */
	private ArrayList<String> paperConferenceNameList;
	
	/** The paper journal name list. */
	private ArrayList<String> paperJournalNameList;
	
	/** The affiliated uni. */
	private String affiliatedUni;
	
	/** The paper title subquery. */
	private String paperTitleSubquery;
	
	/** The affiliated uni sub query author name. */
	private String affiliatedUniSubQueryAuthorName;
	
	/** The selectcols. */
	private String selectcols;
	
	/** The affiliated region sub query author name. */
	private String affiliatedRegionSubQueryAuthorName;
	
	/** The affiliated research area sub query author name. */
	private String affiliatedResearchAreaSubQueryAuthorName;
	

	/**
	 * Instantiates a new search parameters.
	 */
	public SearchParameters(){
		this.exactNumberOfPapers=-1;
		this.numberOfPapersStartRange=-1;
		this.numberOfPapersEndRange=-1;
		this.servedInCommitteeStartYear=-1;
		this.servedInCommitteeEndYear=-1;
		this.startYearOfPublication=-1;
		this.endYearOfPublication=-1;
	}

	/**
	 * Gets the paper title.
	 *
	 * @return the paper title
	 */
	public String getPaperTitle() {
		return paperTitle;
	}
	
	/**
	 * Sets the paper title.
	 *
	 * @param paperTitle the new paper title
	 */
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	
	/**
	 * Sets the paper title sub query author name.
	 *
	 * @param subQuery the new paper title sub query author name
	 */
	public void setpaperTitleSubQueryAuthorName(String subQuery){
		this.paperTitleSubquery = subQuery;
	}
	
	/**
	 * Gets the paper title sub query author name.
	 *
	 * @return the paper title sub query author name
	 */
	public String getpaperTitleSubQueryAuthorName(){
		return this.paperTitleSubquery;
	}

	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	/**
	 * Gets the exact number of papers.
	 *
	 * @return the exact number of papers
	 */
	public int getExactNumberOfPapers() {
		return exactNumberOfPapers;
	}
	
	/**
	 * Sets the exact number of papers.
	 *
	 * @param numberOfPapers the new exact number of papers
	 */
	public void setExactNumberOfPapers(int numberOfPapers) {
		this.exactNumberOfPapers = numberOfPapers;
	}

	/**
	 * Gets the start year of publication.
	 *
	 * @return the start year of publication
	 */
	public int getStartYearOfPublication() {
		return startYearOfPublication;
	}
	
	/**
	 * Sets the start year of publication.
	 *
	 * @param startYearOfPublication the new start year of publication
	 */
	public void setStartYearOfPublication(int startYearOfPublication) {
		this.startYearOfPublication = startYearOfPublication;
	}

	/**
	 * Gets the end year of publication.
	 *
	 * @return the end year of publication
	 */
	public int getEndYearOfPublication() {
		return endYearOfPublication;
	}
	
	/**
	 * Sets the end year of publication.
	 *
	 * @param endYearOfPublication the new end year of publication
	 */
	public void setEndYearOfPublication(int endYearOfPublication) {
		this.endYearOfPublication = endYearOfPublication;
	}

	/**
	 * Gets the author name.
	 *
	 * @return the author name
	 */
	public String getAuthorName() {
		return authorName;
	}
	
	/**
	 * Sets the author name.
	 *
	 * @param authorName the new author name
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}	

	/**
	 * Gets the number of papers start range.
	 *
	 * @return the number of papers start range
	 */
	public int getNumberOfPapersStartRange() {
		return numberOfPapersStartRange;
	}
	
	/**
	 * Sets the number of papers start range.
	 *
	 * @param numberOfPapersStartRange the new number of papers start range
	 */
	public void setNumberOfPapersStartRange(int numberOfPapersStartRange) {
		this.numberOfPapersStartRange = numberOfPapersStartRange;
	}

	/**
	 * Gets the number of papers end range.
	 *
	 * @return the number of papers end range
	 */
	public int getNumberOfPapersEndRange() {
		return numberOfPapersEndRange;
	}
	
	/**
	 * Sets the number of papers end range.
	 *
	 * @param numberOfPapersEndRange the new number of papers end range
	 */
	public void setNumberOfPapersEndRange(int numberOfPapersEndRange) {
		this.numberOfPapersEndRange = numberOfPapersEndRange;
	}

	/**
	 * Gets the paper journal name list.
	 *
	 * @return the paper journal name list
	 */
	public ArrayList<String> getPaperJournalNameList() {
		return paperJournalNameList;
	}
	
	/**
	 * Sets the paper journal name list.
	 *
	 * @param journalName the new paper journal name list
	 */
	public void setPaperJournalNameList(ArrayList<String> journalName) {
		this.paperJournalNameList = journalName;
	}

	/**
	 * Gets the paper conference name list.
	 *
	 * @return the paper conference name list
	 */
	public ArrayList<String> getPaperConferenceNameList() {
		return paperConferenceNameList;
	}
	
	/**
	 * Sets the paper conference name list.
	 *
	 * @param conferenceName the new paper conference name list
	 */
	public void setPaperConferenceNameList(ArrayList<String> conferenceName) {
		this.paperConferenceNameList = conferenceName;
	}

	/**
	 * Gets the school name list.
	 *
	 * @return the school name list
	 */
	public ArrayList<String> getSchoolNameList() {
		return schoolNameList;
	}
	
	/**
	 * Sets the school name list.
	 *
	 * @param schoolNameList the new school name list
	 */
	public void setSchoolNameList(ArrayList<String> schoolNameList) {
		this.schoolNameList = schoolNameList;
	}
	
	/**
	 * Gets the thesis type.
	 *
	 * @return the thesis type
	 */
	public String getThesisType() {
		return thesisType;
	}
	
	/**
	 * Sets the thesis type.
	 *
	 * @param thesisType the new thesis type
	 */
	public void setThesisType(String thesisType) {
		this.thesisType = thesisType;
	}
	
	/**
	 * Checks for served in committee before.
	 *
	 * @return the string
	 */
	public String hasServedInCommitteeBefore() {
		return servedInCommitteeBefore;
	}
	
	/**
	 * Sets the served in committee before.
	 *
	 * @param servedInCommitteeBefore the new served in committee before
	 */
	public void setServedInCommitteeBefore(String servedInCommitteeBefore) {
		this.servedInCommitteeBefore = servedInCommitteeBefore;
	}
	
	/**
	 * Gets the served in committee start year.
	 *
	 * @return the served in committee start year
	 */
	public int getServedInCommitteeStartYear() {
		return servedInCommitteeStartYear;
	}
	
	/**
	 * Sets the served in committee start year.
	 *
	 * @param servedInCommitteeStartYear the new served in committee start year
	 */
	public void setServedInCommitteeStartYear(int servedInCommitteeStartYear) {
		this.servedInCommitteeStartYear = servedInCommitteeStartYear;
	}
	
	/**
	 * Gets the served in committee end year.
	 *
	 * @return the served in committee end year
	 */
	public int getServedInCommitteeEndYear() {
		return servedInCommitteeEndYear;
	}
	
	/**
	 * Sets the served in committee end year.
	 *
	 * @param servedInCommitteeEndYear the new served in committee end year
	 */
	public void setServedInCommitteeEndYear(int servedInCommitteeEndYear) {
		this.servedInCommitteeEndYear = servedInCommitteeEndYear;
	}
	
	/**
	 * Gets the committee conference name list.
	 *
	 * @return the committee conference name list
	 */
	public ArrayList<String> getCommitteeConferenceNameList() {
		return committeeConferenceNameList;
	}
	
	/**
	 * Sets the committee conference name list.
	 *
	 * @param committeeConferenceNameList the new committee conference name list
	 */
	public void setCommitteeConferenceNameList(ArrayList<String> committeeConferenceNameList) {
		this.committeeConferenceNameList = committeeConferenceNameList;
	}
	
	/**
	 * Gets the affiliated uni.
	 *
	 * @return the affiliated uni
	 */
	public String getAffiliatedUni() {
		return affiliatedUni;
	}
	
	/**
	 * Sets the affiliated uni.
	 *
	 * @param affiliatedUni the new affiliated uni
	 */
	public void setAffiliatedUni(String affiliatedUni) {
		this.affiliatedUni = affiliatedUni;
	}
	
	/**
	 * Sets the affiliated uni sub query author name.
	 *
	 * @param authorName the new affiliated uni sub query author name
	 */
	public void setAffiliatedUniSubQueryAuthorName(String authorName){
		this.affiliatedUniSubQueryAuthorName = authorName;
	}
	
	/**
	 * Gets the affiliated uni sub query author name.
	 *
	 * @return the affiliated uni sub query author name
	 */
	public String getAffiliatedUniSubQueryAuthorName(){
		return this.affiliatedUniSubQueryAuthorName;
	}
	
	/**
	 * Sets the select columns.
	 *
	 * @param selectcols the new select columns
	 */
	public void setSelectColumns(String selectcols){
		this.selectcols = selectcols;
	}
	
	/**
	 * Gets the select columns.
	 *
	 * @return the select columns
	 */
	public String getSelectColumns(){
		return this.selectcols;
	}
	
	/**
	 * Sets the affiliated region sub query author name.
	 *
	 * @param authorName the new affiliated region sub query author name
	 */
	public void setAffiliatedRegionSubQueryAuthorName(String authorName){
		this.affiliatedRegionSubQueryAuthorName = authorName;
	}
	
	/**
	 * Gets the affiliated region sub query author name.
	 *
	 * @return the affiliated region sub query author name
	 */
	public String getAffiliatedRegionSubQueryAuthorName(){
		return this.affiliatedRegionSubQueryAuthorName;
	}
	
	/**
	 * Sets the affiliated research area sub query author name.
	 *
	 * @param authorName the new affiliated research area sub query author name
	 */
	public void setAffiliatedResearchAreaSubQueryAuthorName(String authorName){
		this.affiliatedResearchAreaSubQueryAuthorName = authorName;
	}
	
	/**
	 * Gets the affiliated research area sub query author name.
	 *
	 * @return the affiliated research area sub query author name
	 */
	public String getAffiliatedResearchAreaSubQueryAuthorName(){
		return this.affiliatedResearchAreaSubQueryAuthorName;
	}
	
	
}

