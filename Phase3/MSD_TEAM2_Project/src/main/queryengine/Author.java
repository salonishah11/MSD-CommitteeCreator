package main.queryengine;
//Author class with author parameters authorName,keyval, title, year and type

//Author class with author parameters authorName,keyval, title, year and type
public class Author {
	private String authorName;
	private String paperKeyVal;
	private String paperTitle;
	private int paperYear;
	private String paperType;
	
	public Author(String name, String keyVal, String title, String type, int year){
		this.authorName = name;
		this.paperKeyVal = keyVal;
		this.paperTitle = title;
		this.paperType = type;
		this.paperYear = year;
	}
	
	public Author(){
		
	}
	
	// Getter and setter methods for all the author parameters

	public String getName(){
		return this.authorName;
	}
	
	public String getKeyVal(){
		return this.paperKeyVal; 
	}
	
	public String getType(){
		return this.paperType;
	}
	
	public int getYear(){
		return this.paperYear;
	}
	
	public String getTitle(){
		return this.paperTitle;
	}
	
	public void setName(String name){
		this.authorName =name;
	}
	
	public void setTitle(String title){
		this.paperTitle = title;
	}
	
	public void setType(String type){
		this.paperType = type;
	}
	
	public void setKey(String key){
		this.paperKeyVal = key;
	}
	
	public void setYear(int year){
		this.paperYear = year;
	}
}
