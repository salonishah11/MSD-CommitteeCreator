package com.msd.userinterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SetAuthorSearchParams {

	/**
	 * Sets the panel details for Author Name field and its corresponding label.
	 * 
	 * @param authorPanel
	 * @param authorNameField
	 */
	public static void setAuthorPanel(JPanel authorPanel, JTextField authorNameField){
		JLabel authorNameLabel = new JLabel("Author Name");
		authorNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(authorNameLabel, "flowx,cell 0 0");
		authorNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(authorNameField, "cell 1 0");
	}
	
	/**
	 * Sets the panel for School Name field and its corresponding label
	 * @param authorPanel
	 * @param schoolNameField
	 */
	public static void setSchoolPanel(JPanel authorPanel, CustomTextField schoolNameField){
		JLabel schoolNameLabel = new JLabel("School Name");
		schoolNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(schoolNameLabel, "cell 0 1");
		schoolNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));      
		schoolNameField.setPlaceholder("enter name of schools separated by comma");
		authorPanel.add(schoolNameField, "cell 1 1");
	}
	
	/**
	 * Sets the panel details for thesis with radio buttons to choose the
	 * thesis type and corresponding labels.
	 * @param authorPanel
	 * @param rdbtnMasterThesis
	 * @param rdbtnPhdThesis
	 * @param thesisBtnGroup
	 */
	public static void setThesisPanel(JPanel authorPanel, JRadioButton rdbtnMasterThesis, JRadioButton rdbtnPhdThesis, ButtonGroup thesisBtnGroup){
		JLabel thesisLabel = new JLabel("Thesis Type");
		thesisLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(thesisLabel, "cell 0 2,alignx label");
		rdbtnMasterThesis.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnMasterThesis.setActionCommand("MastersThesis");
		rdbtnMasterThesis.setText("Masters");
		authorPanel.add(rdbtnMasterThesis, "flowx,cell 1 2");
		rdbtnPhdThesis.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnPhdThesis.setActionCommand("PhdThesis");
		rdbtnPhdThesis.setText("PHD");
		authorPanel.add(rdbtnPhdThesis, "cell 1 2");
		
		thesisBtnGroup.add(rdbtnMasterThesis);
		thesisBtnGroup.add(rdbtnPhdThesis);
	}
	
	/**
	 * Sets the panel for Number of papers published.
	 * Sets the Radio buttons for Exact and Range.
	 * Text fields for exact and range values and corresponding labels.
	 *  
	 * @param authorPanel
	 * @param rdbtnExact
	 * @param exactNumOfPapersField
	 * @param rdbtnRange
	 * @param numOfPapersStartField
	 * @param numOfPapersEndField
	 * @param numOfPapersRadioBtnGroup
	 */
	public static void setNumberOfPapersPanel(JPanel authorPanel, JRadioButton rdbtnExact, JTextField exactNumOfPapersField,
			JRadioButton rdbtnRange, JTextField numOfPapersStartField, JTextField numOfPapersEndField,
			ButtonGroup numOfPapersRadioBtnGroup){
		
		JLabel numOfPapersLabel = new JLabel("#Papers Published");
		numOfPapersLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(numOfPapersLabel, "cell 0 3");

		Box numOfPapersBox = Box.createHorizontalBox();
		Box exactPapersBox = Box.createHorizontalBox();
		rdbtnExact.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnExact.setActionCommand("Exactly");
		exactPapersBox.add(rdbtnExact);
		exactNumOfPapersField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		exactNumOfPapersField.setColumns(2);
		exactPapersBox.add(exactNumOfPapersField);
		numOfPapersBox.add(exactPapersBox);

		numOfPapersBox.add(Box.createRigidArea(new Dimension(10, 10)));

		Box rangePapersBox = Box.createHorizontalBox();
		rdbtnRange.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnRange.setActionCommand("Range");
		
		rangePapersBox.add(rdbtnRange);
		JLabel greaterThanLabel = new JLabel(">=");
		greaterThanLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		rangePapersBox.add(greaterThanLabel);
		numOfPapersStartField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		numOfPapersStartField.setColumns(2);
		
		rangePapersBox.add(numOfPapersStartField);
		JLabel lessThanLabel = new JLabel("<=");
		lessThanLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		rangePapersBox.add(lessThanLabel);
		numOfPapersEndField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		numOfPapersEndField.setColumns(2);
		
		rangePapersBox.add(numOfPapersEndField);
		numOfPapersBox.add(rangePapersBox);

		authorPanel.add(numOfPapersBox);

		numOfPapersRadioBtnGroup.add(rdbtnExact);
		numOfPapersRadioBtnGroup.add(rdbtnRange);
	}
	
	/**
	 * Sets the panel for Affiliation University with label and its corresponding text field.
	 * 
	 * @param authorPanel
	 * @param affiliatedUniField
	 */
	public static void setAffiliationPanel(JPanel authorPanel, JTextField affiliatedUniField){
		JLabel affiliatedUniLabel = new JLabel("Affiliated University");
		affiliatedUniLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(affiliatedUniLabel, "cell 0 4");
		affiliatedUniField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(affiliatedUniField, "cell 1 4");
	}
	
	
	/**
	 *  Sets the panel for Committee with radio buttons for choosing whether 
	 *  the Author served in the committee before or not.
	 * 
	 * @param committeePanel
	 * @param rdbtnYes
	 * @param rdbtnNo
	 * @param servedInCommitteeBeforeBtnGroup
	 */
	public static void setServedInCommitteePanel(JPanel committeePanel, JRadioButton rdbtnYes, 
			JRadioButton rdbtnNo, ButtonGroup servedInCommitteeBeforeBtnGroup){
		JLabel servedInCommitteeBeforeLabel = new JLabel("Served in Committee Before?");
		servedInCommitteeBeforeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(servedInCommitteeBeforeLabel, "cell 0 0,alignx label");
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnYes.setActionCommand("Yes");
		rdbtnYes.setText("Yes");
		committeePanel.add(rdbtnYes, "flowx,cell 1 0");
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnNo.setActionCommand("No");
		rdbtnNo.setText("No");
		committeePanel.add(rdbtnNo, "cell 1 0");		

		servedInCommitteeBeforeBtnGroup.add(rdbtnYes);
		servedInCommitteeBeforeBtnGroup.add(rdbtnNo);

	}
	
	/**
	 * Sets the panel for year in which the author served in the committee.
	 * Contains Start and End range for the year with labels and corresponding text fields.
	 * 
	 * @param committeePanel
	 * @param committeeServedStartYr
	 * @param committeeServedEndYr
	 */
	public static void setCommitteeYearPanel(JPanel committeePanel, JTextField committeeServedStartYr,
			JTextField committeeServedEndYr){
		JLabel committeeYearLabel = new JLabel("Served in Year");
		committeeYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(committeeYearLabel, "cell 0 1");
		Box committeeStartYearBox = Box.createHorizontalBox();
		JLabel startYearLabel = new JLabel("start range");
		startYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeStartYearBox.add(startYearLabel);
		committeeServedStartYr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeStartYearBox.add(committeeServedStartYr);
		
		Box committeeEndYearBox = Box.createHorizontalBox();
		JLabel endYearLabel = new JLabel("end range");
		endYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeEndYearBox.add(endYearLabel);
		committeeServedEndYr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeEndYearBox.add(committeeServedEndYr);

		Box collectiveBox = Box.createHorizontalBox();
		collectiveBox.add(committeeStartYearBox);
		collectiveBox.add(Box.createRigidArea(new Dimension(10, 10)));
		collectiveBox.add(committeeEndYearBox);
	
		committeePanel.add(collectiveBox, "cell 1 1");
	}
	
	/**
	 * Sets the panel details for Conference names.
	 * 
	 * @param committeePanel
	 * @param committeeConfField
	 */
	public static void setConfLabelPanel(JPanel committeePanel, CustomTextField committeeConfField){
		JLabel committeeConfLabel = new JLabel("For Conference(s)");
		committeeConfLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(committeeConfLabel, "flowx,cell 0 2");
		
		committeeConfField.setFont(new Font("Tahoma", Font.PLAIN, 24));      
		committeeConfField.setPlaceholder("enter names separated by comma");
		JButton viewConfListButton = new JButton("View Conferences");
		viewConfListButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewConfListButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CommitteeConferenceNameListView();
				
			}
		});
		
		Box confBox = Box.createHorizontalBox();
		confBox.add(committeeConfField);
		confBox.add(Box.createRigidArea(new Dimension(10, 0)));
		confBox.add(viewConfListButton);
		
		committeePanel.add(confBox, "cell 1 2");
	}
}
