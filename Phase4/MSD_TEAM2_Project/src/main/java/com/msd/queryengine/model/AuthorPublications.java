package com.msd.queryengine.model;

/**
 * The Class AuthorPublications.
 */
public class AuthorPublications {

	/** The publication name. */
	private String publicationName;
	
	/** The title. */
	private String title;
	
	/** The year. */
	private int year;
	
	/** The publication type. */
	private String publicationType;
	
	/**
	 * Instantiates a new author publications.
	 *
	 * @param publicationName the publication name
	 * @param title the title
	 * @param year the year
	 * @param publicationType the publication type
	 */
	public AuthorPublications(String publicationName, String title, int year, String publicationType){
		this.publicationName = publicationName;
		this.title = title;
		this.year = year;
		this.publicationType = publicationType;
	}
	
	/**
	 * Gets the publication name.
	 *
	 * @return the publication name
	 */
	public String getPublicationName() {
		return publicationName;
	}
	
	/**
	 * Sets the publication name.
	 *
	 * @param publicationName the new publication name
	 */
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * Gets the publication type.
	 *
	 * @return the publication type
	 */
	public String getPublicationType() {
		return publicationType;
	}

	/**
	 * Sets the publication type.
	 *
	 * @param publicationType the new publication type
	 */
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}
}
