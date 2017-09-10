package com.msd.DBLPParser.model;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class Paper.
 */
public class Paper {
	
	/** The key. */
	// all the parameters associated with a paper 
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
	
	/** The volume. */
	private String volume;
	
	/** The journal. */
	private String journal;
	
	/** The number. */
	private String number;
	
	/** The cross ref. */
	private String crossRef;
	
	/** The book title. */
	private String bookTitle;
	
	/**
	 * Instantiates a new paper.
	 */
	// constructor initializing default values for all the parameters of paper
	public Paper(){
		this.crossRef = "";
		this.bookTitle = "";
		this.key="";
		this.authorName = new ArrayList<String>();
		this.title = "";
		this.journal = "";
		this.number="";
		this.type="";
		this.pages="";
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	// setter and getter methods for all the paper parameters
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
	 * Sets the volume.
	 *
	 * @param volume the new volume
	 */
	public void setVolume(String volume){
		this.volume = volume;
	}
	
	/**
	 * Sets the journal.
	 *
	 * @param journal the new journal
	 */
	public void setJournal(String journal){
		this.journal = journal;
	}
	
	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number){
		this.number = number;
	}
	
	/**
	 * Sets the cross ref.
	 *
	 * @param crossRef the new cross ref
	 */
	public void setCrossRef(String crossRef){
		this.crossRef = crossRef;
	}
	
	/**
	 * Sets the book title.
	 *
	 * @param bookTitle the new book title
	 */
	public void setBookTitle(String bookTitle){
		this.bookTitle = bookTitle;
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
	 * Gets the volume.
	 *
	 * @return the volume
	 */
	public String getVolume(){
		return this.volume;
	}
	
	/**
	 * Gets the journal.
	 *
	 * @return the journal
	 */
	public String getJournal(){
		return this.journal;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber(){
		return this.number;
	}
	
	/**
	 * Gets the cross ref.
	 *
	 * @return the cross ref
	 */
	public String getCrossRef(){
		return this.crossRef;
	}
	
	
	
	/**
	 * Gets the book title.
	 *
	 * @return the book title
	 */
	public String getBookTitle(){
		return bookTitle;
	}
	
	
}
