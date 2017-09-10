package com.msd.queryengine.model;


/**
 * The Class Author.
 */
//Author class with author parameters authorName,keyval, title, year and type
public class Author {
	
	/** The author name. */
	private String authorName;
	
	/** The paper key val. */
	private String paperKeyVal;
	
	/** The paper title. */
	private String paperTitle;
	
	/** The paper year. */
	private int paperYear;
	
	/** The paper type. */
	private String paperType;
	
	/** The publication name. */
	private String publicationName;
	
	/**
	 * Instantiates a new author.
	 *
	 * @param name the name
	 * @param keyVal the key val
	 * @param title the title
	 * @param type the type
	 * @param year the year
	 */
	public Author(String name, String keyVal, String title, String type, int year){
		this.authorName = name;
		this.paperKeyVal = keyVal;
		this.paperTitle = title;
		this.paperType = type;
		this.paperYear = year;
	}
	
	/**
	 * Instantiates a new author.
	 *
	 * @param name the name
	 * @param title the title
	 * @param type the type
	 * @param year the year
	 * @param publicationName the publication name
	 */
	public Author(String name, String title, String type, int year,String publicationName){
		this.authorName = name;
		this.paperTitle = title;
		this.paperType = type;
		this.paperYear = year;
		this.setPublicationName(publicationName);
	}
	
	
	/**
	 * Instantiates a new author.
	 *
	 * @param name the name
	 */
	public Author(String name){
		this.authorName = name;
		this.paperKeyVal = "";
		this.paperTitle = "";
		this.paperType = "";
		this.paperYear = 0	;
		this.publicationName = "";
	}
	
	/**
	 * Instantiates a new author.
	 */
	public Author(){
		
	}
	
	// Getter and setter methods for all the author parameters

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.authorName;
	}
	
	/**
	 * Gets the key val.
	 *
	 * @return the key val
	 */
	public String getKeyVal(){
		return this.paperKeyVal; 
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType(){
		return this.paperType;
	}
	
	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear(){
		return this.paperYear;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle(){
		return this.paperTitle;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.authorName =name;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title){
		this.paperTitle = title;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type){
		this.paperType = type;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key){
		this.paperKeyVal = key;
	}
	
	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year){
		this.paperYear = year;
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
}
