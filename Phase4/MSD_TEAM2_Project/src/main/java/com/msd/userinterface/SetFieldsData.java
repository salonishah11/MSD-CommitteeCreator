package com.msd.userinterface;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SetFieldsData {

	/**
	 * Sets the fields data.
	 *
	 * @param enteredSearchParams the new fields data
	 */
	public static void setFieldsData(SearchParameters enteredSearchParams, JTextField authorNameField, JTextField schoolNameField,
			JRadioButton rdbtnMasterThesis,
			JRadioButton rdbtnPhdThesis,
			JRadioButton rdbtnExact, JTextField exactNumOfPapersField,
			JRadioButton rdbtnRange, JTextField numOfPapersStartField,
			JTextField numOfPapersEndField, JTextField affiliatedUniField,
			JRadioButton rdbtnYes, JRadioButton rdbtnNo,
			JTextField committeeServedStartYr, JTextField committeeServedEndYr,
			CustomTextField committeeConfField, JTextField paperTitleField,
			JTextField keywordsField, JTextField paperPublicationStartYr,
			JTextField paperPublicationEndYr, JTextField paperConfField,
			JTextField paperJournalField) {

		if(enteredSearchParams.getAuthorName() != null){
			authorNameField.setText(enteredSearchParams.getAuthorName());
		}

		if(enteredSearchParams.getSchoolNameList() != null){
			schoolNameField.setText(String.join(",", enteredSearchParams.getSchoolNameList()));
		}

		if(enteredSearchParams.getThesisType() != null){
			switch(enteredSearchParams.getThesisType()){
			case "MastersThesis":
				rdbtnMasterThesis.setSelected(true);
				break;
			case "PhdThesis":
				rdbtnPhdThesis.setSelected(true);
				break;
			}
		}

		if(enteredSearchParams.getExactNumberOfPapers() != -1){	
			rdbtnExact.setSelected(true);
			exactNumOfPapersField.setText(String.valueOf(enteredSearchParams.getExactNumberOfPapers()));
		}
		if(enteredSearchParams.getNumberOfPapersStartRange() != -1
				|| enteredSearchParams.getNumberOfPapersEndRange() != -1){
			rdbtnRange.setSelected(true);
			if(enteredSearchParams.getNumberOfPapersStartRange() != -1){
				numOfPapersStartField.setText(String.valueOf(enteredSearchParams.getNumberOfPapersStartRange()));
			}
			if(enteredSearchParams.getNumberOfPapersEndRange() != -1){
				numOfPapersEndField.setText(String.valueOf(enteredSearchParams.getNumberOfPapersEndRange()));
			}			
		}
		
		if(enteredSearchParams.getAffiliatedUni() != null){
			affiliatedUniField.setText(enteredSearchParams.getAffiliatedUni());
		}

		if(enteredSearchParams.hasServedInCommitteeBefore() != null){
			switch(enteredSearchParams.hasServedInCommitteeBefore()){
			case "Yes":
				rdbtnYes.setSelected(true);
				break;
			case "No":
				rdbtnNo.setSelected(true);
				break;
			}
		}

		if(enteredSearchParams.getServedInCommitteeStartYear() != -1){
			committeeServedStartYr.setText(String.valueOf(enteredSearchParams.getServedInCommitteeStartYear()));
		}
		if(enteredSearchParams.getServedInCommitteeEndYear() != -1){
			committeeServedEndYr.setText(String.valueOf(enteredSearchParams.getServedInCommitteeEndYear()));
		}

		if(enteredSearchParams.getCommitteeConferenceNameList() != null){
			committeeConfField.setText(String.join(",", enteredSearchParams.getCommitteeConferenceNameList()));
		}

		if(enteredSearchParams.getPaperTitle() != null){
			paperTitleField.setText(enteredSearchParams.getPaperTitle());
		}

		if(enteredSearchParams.getKeywords() != null){
			keywordsField.setText(String.join(",", enteredSearchParams.getKeywords()));
		}

		if(enteredSearchParams.getStartYearOfPublication() != -1){
			paperPublicationStartYr.setText(String.valueOf(enteredSearchParams.getStartYearOfPublication()));
		}
		if(enteredSearchParams.getEndYearOfPublication() != -1){
			paperPublicationEndYr.setText(String.valueOf(enteredSearchParams.getEndYearOfPublication()));
		}

		if(enteredSearchParams.getPaperConferenceNameList() != null){
			paperConfField.setText(String.join(",", enteredSearchParams.getPaperConferenceNameList()));
		}

		if(enteredSearchParams.getPaperJournalNameList() != null){
			paperJournalField.setText(String.join(",", enteredSearchParams.getPaperJournalNameList()));
		}
	}
}
