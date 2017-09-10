package com.msd.DBLPParser.model;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class Thesis.
 */
// Thesis class with thesis related parameters and corresponding getter and setter methods
public class Thesis {
	
	/** The key. */
	private String key;
	
	/** The author name. */
	private ArrayList<String> authorName;
	
	/** The title. */
	private String title;
	
	/** The type. */
	private String type;
	
	/** The pages. */
	private String pages;
	
	/** The year. */
	private int year;
	
	/** The school. */
	private String school;
	
	/** The url. */
	private String url;
	
	/** The ee. */
	private String ee;
	
	/**
	 * Instantiates a new thesis.
	 */
	public Thesis(){
		this.key="";
		this.authorName = new ArrayList<String>();
		this.title = "";
		this.year = 0;
		this.school = "";
		this.type="";
		this.url = "";
		this.ee = "";
		this.pages="";
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key){
		this.key=key;
	}
	
	/**
	 * Sets the author name.
	 *
	 * @param author the new author name
	 */
	public void setAuthorName(String author){
		authorName.add(author);
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	
	/**
	 * Sets the pages.
	 *
	 * @param pages the new pages
	 */
	public void setPages(String pages){
		this.pages = pages;
	}
	
	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * Sets the school.
	 *
	 * @param school the new school
	 */
	public void setSchool(String school){
		this.school = school;
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Sets the ee.
	 *
	 * @param ee the new ee
	 */
	public void setEe(String ee) {
		this.ee = ee;
	}
	

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey(){
		return this.key;
	}
	
	/**
	 * Gets the author name.
	 *
	 * @return the author name
	 */
	public List<String> getAuthorName(){
		return this.authorName;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public String getPages(){
		return this.pages;
	}
	
	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear(){
		return this.year;
	}
	
	/**
	 * Gets the school.
	 *
	 * @return the school
	 */
	public String getSchool(){
		return this.school;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gets the ee.
	 *
	 * @return the ee
	 */
	public String getEe() {
		return ee;
	}
	
}
