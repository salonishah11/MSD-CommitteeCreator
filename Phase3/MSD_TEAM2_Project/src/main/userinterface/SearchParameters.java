package main.userinterface;

import java.util.ArrayList;

// SearchParameters class with all the parameters related to search functionality of the product
public class SearchParameters {

	private String authorName;
	private ArrayList<String> schoolNameList;
	private String thesisType;
	private int exactNumberOfPapers;
	private int numberOfPapersStartRange;
	private int numberOfPapersEndRange;
	private String servedInCommitteeBefore;
	private int servedInCommitteeStartYear;
	private int servedInCommitteeEndYear;
	private ArrayList<String> committeeConferenceNameList;	
	private String paperTitle;
	private ArrayList<String> keywords;	
	private int startYearOfPublication;
	private int endYearOfPublication;
	private ArrayList<String> paperConferenceNameList;
	private ArrayList<String> paperJournalNameList;


	// corresponding getter and setter methods for each of the search parameters
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public int getExactNumberOfPapers() {
		return exactNumberOfPapers;
	}
	public void setExactNumberOfPapers(int numberOfPapers) {
		this.exactNumberOfPapers = numberOfPapers;
	}

	public int getStartYearOfPublication() {
		return startYearOfPublication;
	}
	public void setStartYearOfPublication(int startYearOfPublication) {
		this.startYearOfPublication = startYearOfPublication;
	}

	public int getEndYearOfPublication() {
		return endYearOfPublication;
	}
	public void setEndYearOfPublication(int endYearOfPublication) {
		this.endYearOfPublication = endYearOfPublication;
	}

	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}	

	public int getNumberOfPapersStartRange() {
		return numberOfPapersStartRange;
	}
	public void setNumberOfPapersStartRange(int numberOfPapersStartRange) {
		this.numberOfPapersStartRange = numberOfPapersStartRange;
	}

	public int getNumberOfPapersEndRange() {
		return numberOfPapersEndRange;
	}
	public void setNumberOfPapersEndRange(int numberOfPapersEndRange) {
		this.numberOfPapersEndRange = numberOfPapersEndRange;
	}

	public ArrayList<String> getPaperJournalNameList() {
		return paperJournalNameList;
	}
	public void setPaperJournalNameList(ArrayList<String> journalName) {
		this.paperJournalNameList = journalName;
	}

	public ArrayList<String> getPaperConferenceNameList() {
		return paperConferenceNameList;
	}
	public void setPaperConferenceNameList(ArrayList<String> conferenceName) {
		this.paperConferenceNameList = conferenceName;
	}

	public ArrayList<String> getSchoolNameList() {
		return schoolNameList;
	}
	public void setSchoolNameList(ArrayList<String> schoolNameList) {
		this.schoolNameList = schoolNameList;
	}
	public String getThesisType() {
		return thesisType;
	}
	public void setThesisType(String thesisType) {
		this.thesisType = thesisType;
	}
	public String hasServedInCommitteeBefore() {
		return servedInCommitteeBefore;
	}
	public void setServedInCommitteeBefore(String servedInCommitteeBefore) {
		this.servedInCommitteeBefore = servedInCommitteeBefore;
	}
	public int getServedInCommitteeStartYear() {
		return servedInCommitteeStartYear;
	}
	public void setServedInCommitteeStartYear(int servedInCommitteeStartYear) {
		this.servedInCommitteeStartYear = servedInCommitteeStartYear;
	}
	public int getServedInCommitteeEndYear() {
		return servedInCommitteeEndYear;
	}
	public void setServedInCommitteeEndYear(int servedInCommitteeEndYear) {
		this.servedInCommitteeEndYear = servedInCommitteeEndYear;
	}
	public ArrayList<String> getCommitteeConferenceNameList() {
		return committeeConferenceNameList;
	}
	public void setCommitteeConferenceNameList(ArrayList<String> committeeConferenceNameList) {
		this.committeeConferenceNameList = committeeConferenceNameList;
	}
}

