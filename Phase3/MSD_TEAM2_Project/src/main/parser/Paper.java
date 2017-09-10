package main.parser;

import java.util.ArrayList;
import java.util.List;

public class Paper {
	// all the parameters associated with a paper 
	private String key;
	private ArrayList<String> authorName;
	private String title;
	private String type;
	private String pages;
	private int year;
	private String volume;
	private String journal;
	private String number;
	private String crossRef;
	private String bookTitle;
	private String url;
	private String ee;
	
	// constructor initializing default values for all the parameters of paper
	public Paper(){
		this.crossRef = "";
		this.bookTitle = "";
		this.key="";
		this.authorName = new ArrayList<String>();
		this.title = "";
		this.journal = "";
		this.number="";
		this.url = "";
		this.ee = "";
		this.type="";
		this.pages="";
	}
	
	// setter and getter methods for all the paper parameters
	public void setKey(String key){
		this.key=key;
	}
	
	public void setAuthorName(String author){
		authorName.add(author);
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setPages(String pages){
		this.pages = pages;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setVolume(String volume){
		this.volume = volume;
	}
	
	public void setJournal(String journal){
		this.journal = journal;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public void setCrossRef(String crossRef){
		this.crossRef = crossRef;
	}
	
	public void setBookTitle(String bookTitle){
		this.bookTitle = bookTitle;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setEE(String ee){
		this.ee = ee;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public List<String> getAuthorName(){
		return this.authorName;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getPages(){
		return this.pages;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public String getVolume(){
		return this.volume;
	}
	
	public String getJournal(){
		return this.journal;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public String getCrossRef(){
		return this.crossRef;
	}
	
	
	
	public String getBookTitle(){
		return bookTitle;
	}
	public String getUrl(){
		return url;
	}
	public String getEE(){
		return ee;
	}
	
	
}
