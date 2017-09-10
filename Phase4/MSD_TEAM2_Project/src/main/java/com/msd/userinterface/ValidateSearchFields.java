package com.msd.userinterface;

import java.time.Year;

import javax.swing.JTextField;

public class ValidateSearchFields {

	/**
	 * Validate exact number of papers.
	 *
	 * @return the string
	 */
	public static String validateExactNumberOfPapers(JTextField exactNumOfPapersField){
		if(exactNumOfPapersField.getText().trim().isEmpty())
			return "Exact Number of Fields cannot be empty";
		
		if(!validatePapers(exactNumOfPapersField.getText().toString())){
			return "Invalid number in exact field";
		}
		
		return "Valid";
	}
	
	/**
	 * Validate number of papers.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	public static boolean validatePapers(String number){
			String regex = "[0-9]+"; 
			return number.matches(regex);
		}
	
	/**
	 * Validate number of papers start field.
	 *
	 * @return the string
	 */
	public static String validateNumOfPapersStartField(JTextField numOfPapersStartField){
		if(numOfPapersStartField.getText().trim().isEmpty())
			return "Start range for papers cannot be empty";
		if(!validatePapers(numOfPapersStartField.getText().toString())){
			return "Invalid number in Start range";
		}
		return "Valid";
	}
	
	/**
	 * Validate number of papers end field.
	 *
	 * @return the string
	 */
	public static String validateNumOfPapersEndField(JTextField numOfPapersEndField){
		if(numOfPapersEndField.getText().trim().isEmpty())
			return "End range of papers cannot be empty";
		if(!validatePapers(numOfPapersEndField.getText().toString())){
			return "Invalid Number in End range";
		}
		return "Valid";
	}
	
	/**
	 * Validate whether the start publication year is of 4 numerial digits
	 *
	 * @return the string
	 */
	public static String validatePublicationStartYear(JTextField paperPublicationStartYr){
		if(paperPublicationStartYr.getText().trim().isEmpty())
			return "Start year of publication cannot be empty";
		if(!validateDigits(paperPublicationStartYr.getText().toString())){
			return "Invalid Start year of publication";
		}
		return "Valid";
	}
	
	
	/**
	 * Validate digits.
	 *
	 * @param year the year
	 * @return true, if successful
	 */
	public static boolean validateDigits(String year){
		if(Integer.parseInt(year) > Year.now().getValue()){
			return false;
		}
		String regex = "[0-9][0-9][0-9][0-9]";
		return year.matches(regex);
	}
	
	/**
	 * Validate whether the end publication year is of 4 numerial digits
	 *
	 * @return the string
	 */
	public static String validatePublicationEndYear(JTextField paperPublicationEndYr){
		if(paperPublicationEndYr.getText().trim().isEmpty())
			return "End year of publication cannot be empty";
		if(!validateDigits(paperPublicationEndYr.getText().toString())){
			return "Invalid End year of publication";
		}
		return "Valid";
				
	}
	
	/**
	 * Validate committee served start yr.
	 *
	 * @return the string
	 */
	public static String validateCommitteeServedStartYr(JTextField committeeServedStartYr){
		if(committeeServedStartYr.getText().trim().isEmpty())
			return "Start year for committee cannot be empty";
		if(!validateDigits(committeeServedStartYr.getText().toString())){
			return "Invalid Start year for committee";
		}
		return "Valid";
	}
	
	/**
	 * Validate committee served end yr.
	 *
	 * @return the string
	 */
	public static String validateCommitteeServedEndYr(JTextField committeeServedEndYr){
		if(committeeServedEndYr.getText().trim().isEmpty())
			return "End year for committee cannot be empty";
		if(!validateDigits(committeeServedEndYr.getText().toString())){
			return "Invalid End year for committee";
		}
		return "Valid";
	}
	
	/**
	 * Validate start end range.
	 *
	 * @return the string
	 */
	public static String validateStartEndRange(JTextField numOfPapersStartField, JTextField numOfPapersEndField){
		if(!numOfPapersStartField.getText().trim().isEmpty() && numOfPapersEndField.getText().trim().isEmpty()){
			return "Valid";
		} else if(!numOfPapersEndField.getText().trim().isEmpty() && numOfPapersStartField.getText().trim().isEmpty()){
			return "Valid";
		} else if(!numOfPapersStartField.getText().trim().isEmpty() && !numOfPapersEndField.getText().trim().isEmpty()){ 
			if(Integer.parseInt(numOfPapersEndField.getText()) < Integer.parseInt(numOfPapersStartField.getText())){
				return "Invalid range";
			}
		} 
		return "Valid";
		
	}
	
	/**
	 * Validate author name field.
	 *
	 * @return the string
	 */
	public static String validateAuthorNameField(JTextField authorNameField){
		if(authorNameField.getText().trim().length() < 3){
			return "Minimum length for authorname is 3";
		} else if(authorNameField.getText().trim().contains("$") ||
					authorNameField.getText().trim().contains("=") || 
					authorNameField.getText().trim().contains("%") ||
					authorNameField.getText().trim().contains(",") ||
					authorNameField.getText().trim().contains("?")){
			return "Invalid characters in author name";
		}
			
		return "Valid";
	}
	
	/**
	 * Validate school name.
	 *
	 * @return the string
	 */
	public static String validateSchoolName(JTextField schoolNameField){
		if(schoolNameField.getText().trim().contains("$") ||
				schoolNameField.getText().trim().contains("=") || 
				schoolNameField.getText().trim().contains("%") ||
				schoolNameField.getText().trim().contains("?")){
					return "Invalid characters in school name";
		}
		return "Valid";
	}
	
	/**
	 *  Validate Affiliation.
	 *  
	 *  @return the string
	 */
	
	public static String validateAffiliation(JTextField affiliatedUniField){
		if(affiliatedUniField.getText().trim().contains("$") ||
				affiliatedUniField.getText().trim().contains("=") || 
				affiliatedUniField.getText().trim().contains("%") ||
				affiliatedUniField.getText().trim().contains("?")){
					return "Invalid characters in Affiliation";
		}
		return "Valid";
	}
	
	/**
	 * Validate conferences name.
	 *
	 * @return the string
	 */
	public static String validateConferencesName(JTextField committeeConfField){
		if(committeeConfField.getText().trim().contains("$") ||
				committeeConfField.getText().trim().contains("=") || 
				committeeConfField.getText().trim().contains("%") ||
				committeeConfField.getText().trim().contains("?")){
					return "Invalid characters in conferences name";
		}
		return "Valid";
	}
	
	/**
	 * Validate journals name.
	 *
	 * @return the string
	 */
	public static String validateJournalsName(JTextField paperJournalField){
		if(paperJournalField.getText().trim().contains("$") ||
				paperJournalField.getText().trim().contains("=") || 
				paperJournalField.getText().trim().contains("%") ||
				paperJournalField.getText().trim().contains("?")){
					return "Invalid characters in journals name";
		}
		return "Valid";
	}
	
	/**
	 * Validate keywords.
	 *
	 * @return the string
	 */
	public static String validateKeywords(JTextField keywordsField){
		if(keywordsField.getText().trim().contains("$") ||
				keywordsField.getText().trim().contains("=") || 
				keywordsField.getText().trim().contains("%") ||
				keywordsField.getText().trim().contains("?")){
					return "Invalid characters in keywords";
		}
		return "Valid";
	}
}
