package com.msd.DBLPParser.model;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class www class with parameters related to www data being parsed in  dblp.xml with corresponding getter and setter methods.
 */
public class www {
	
	/** The key. */
	private String key;
	
	/** The author name. */
	private ArrayList<String> authorName = new ArrayList<String>();
	
	/** The title. */
	private String title;
	
	/** The year. */
	private int year;
	
	/** The url. */
	private String url;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the author name.
	 *
	 * @return the author name
	 */
	public List<String> getAuthorName(){
		return authorName;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
