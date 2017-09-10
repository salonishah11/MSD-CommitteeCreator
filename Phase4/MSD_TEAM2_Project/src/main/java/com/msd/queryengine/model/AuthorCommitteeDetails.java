package com.msd.queryengine.model;


/**
 * The Class AuthorCommitteeDetails.
 */
public class AuthorCommitteeDetails {

	/** The conference name. */
	private String conferenceName;
	
	/** The year. */
	private int year;
		
	/**
	 * Instantiates a new author committee details.
	 *
	 * @param name the name
	 * @param year the year
	 */
	public AuthorCommitteeDetails(String name, int year){
		this.conferenceName = name;
		this.year = year;
	}
	
	/**
	 * Gets the conference name.
	 *
	 * @return the conference name
	 */
	public String getConferenceName() {
		return conferenceName;
	}
	
	/**
	 * Sets the conference name.
	 *
	 * @param conferenceName the new conference name
	 */
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}
}
