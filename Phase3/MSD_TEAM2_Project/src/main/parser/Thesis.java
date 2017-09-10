package main.parser;

import java.util.ArrayList;
import java.util.List;

// Thesis class with thesis related parameters and corresponding getter and setter methods
public class Thesis {
	private String key;
	private ArrayList<String> authorName;
	private String title;
	private String type;
	private String pages;
	private int year;
	private String school;
	private String url;
	private String ee;
	
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
	
	public void setSchool(String school){
		this.school = school;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setEe(String ee) {
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
	
	public String getSchool(){
		return this.school;
	}

	public String getUrl() {
		return url;
	}

	public String getEe() {
		return ee;
	}
	
}
