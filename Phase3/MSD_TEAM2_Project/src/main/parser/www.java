package main.parser;

import java.util.ArrayList;
import java.util.List;

// www class with parameters related to www data being parsed in  dblp.xml with corresponding getter and setter methods
public class www {
	private String key;
	private ArrayList<String> authorName = new ArrayList<String>();
	private String title;
	private int year;
	private String url;

	public String getKey() {
		return this.key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getAuthorName(){
		return authorName;
	}
	public void setAuthorName(String author){
		authorName.add(author);
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
