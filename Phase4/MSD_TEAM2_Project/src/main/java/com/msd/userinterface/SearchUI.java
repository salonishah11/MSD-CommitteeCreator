package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import com.msd.queryengine.QueryBuilder;
import com.msd.queryengine.model.Author;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * The Class SearchUI.
 */
public class SearchUI extends JPanel{

	private SearchParameters searchParamObj = new SearchParameters();

	/** The parent frame. */
	private JFrame parentFrame;

	/** The author name field. */
	private JTextField authorNameField = new JTextField(30);

	/** The school name field. */
	private CustomTextField schoolNameField = new CustomTextField(30);

	/** The thesis btn group. */
	ButtonGroup thesisBtnGroup = new ButtonGroup();

	/** The rdbtn master thesis. */
	private JRadioButton rdbtnMasterThesis = new JRadioButton();

	/** The rdbtn phd thesis. */
	private JRadioButton rdbtnPhdThesis = new JRadioButton();

	/** The served in committee before btn group. */
	ButtonGroup servedInCommitteeBeforeBtnGroup = new ButtonGroup();

	/** The rdbtn yes. */
	private JRadioButton rdbtnYes = new JRadioButton();

	/** The rdbtn no. */
	private JRadioButton rdbtnNo = new JRadioButton();

	/** The committee served start yr. */
	private JTextField committeeServedStartYr = new JTextField(4);

	/** The committee served end yr. */
	private JTextField committeeServedEndYr = new JTextField(4);

	/** The paper title field. */
	private JTextField paperTitleField = new JTextField(30);	

	/** The keywords field. */
	private CustomTextField keywordsField = new CustomTextField(30);

	/** The paper publication start yr. */
	private JTextField paperPublicationStartYr = new JTextField(4);

	/** The paper publication end yr. */
	private JTextField paperPublicationEndYr = new JTextField(4);
	
	/** The exact number of papers field. */
	private JTextField exactNumOfPapersField = new JTextField(2);
	
	/** The number of papers start field. */
	private JTextField numOfPapersStartField = new JTextField(2);
	
	/** The number of papers end field. */
	private JTextField numOfPapersEndField = new JTextField(2);

	/** The committee conf field. */
	private CustomTextField committeeConfField = new CustomTextField(20);

	/** The paper conf field. */
	private CustomTextField paperConfField = new CustomTextField(20);

	/** The paper journal field. */
	private CustomTextField paperJournalField = new CustomTextField(20);
	
	/** The affiliated university field. */
	private JTextField affiliatedUniField = new JTextField(30);

	/** The num of papers radio btn group. */
	ButtonGroup numOfPapersRadioBtnGroup = new ButtonGroup();

	/** The rdbtn exact. */
	public JRadioButton rdbtnExact = new JRadioButton("Exactly", false);

	/** The rdbtn range. */
	public JRadioButton rdbtnRange = new JRadioButton("Range");

	/** The search button. */
	public JButton searchButton = new JButton("Search");

	/** The clear all button. */
	public JButton clearAllButton = new JButton("Clear All");

	/** The resultant author list. */
	public List<Author> resultantAuthorList;

	/**
	 * Instantiates a new search UI.
	 *
	 * @param enteredSearchParams the entered search params
	 */
	public SearchUI(SearchParameters enteredSearchParams){
		parentFrame = Main.getMainFrame();

		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);

		if(enteredSearchParams != null){
			SetFieldsData.setFieldsData(enteredSearchParams,
					authorNameField,schoolNameField, rdbtnMasterThesis,
					rdbtnPhdThesis,
					rdbtnExact, exactNumOfPapersField,
					rdbtnRange, numOfPapersStartField,
					numOfPapersEndField, affiliatedUniField,
					rdbtnYes, rdbtnNo,
					committeeServedStartYr, committeeServedEndYr,
					committeeConfField, paperTitleField,
					keywordsField, paperPublicationStartYr,
					paperPublicationEndYr, paperConfField,
					paperJournalField);
		}
	}

	/**
	 * Gets the author name field.
	 *
	 * @return the author name field
	 */
	public JTextField getAuthorNameField(){
		return this.authorNameField;
	}

	/**
	 * Gets the school name field.
	 *
	 * @return the school name field
	 */
	public CustomTextField getSchoolNameField(){
		return this.schoolNameField;
	}

	/**
	 * Gets the srdbtn master thesis field.
	 *
	 * @return the srdbtn master thesis field
	 */
	public JRadioButton getSrdbtnMasterThesisField(){
		return this.rdbtnMasterThesis;
	}

	/**
	 * Gets the rdbtn phd thesis field.
	 *
	 * @return the rdbtn phd thesis field
	 */
	public JRadioButton getrdbtnPhdThesisField(){
		return this.rdbtnPhdThesis;
	}

	/**
	 * Gets the rrdbtn yes field.
	 *
	 * @return the rrdbtn yes field
	 */
	public JRadioButton getrrdbtnYesField(){
		return this.rdbtnYes;
	}

	/**
	 * Gets the rdbtn no field.
	 *
	 * @return the rdbtn no field
	 */
	public JRadioButton getrdbtnNoField(){
		return this.rdbtnNo;
	}


	/**
	 * Gets the committee served start yr.
	 *
	 * @return the committee served start yr
	 */
	public JTextField getcommitteeServedStartYr(){
		return this.committeeServedStartYr;
	}

	/**
	 * Gets the committee served end yr.
	 *
	 * @return the committee served end yr
	 */
	public JTextField getcommitteeServedEndYr(){
		return this.committeeServedEndYr;
	}

	/**
	 * Gets the paper title field.
	 *
	 * @return the paper title field
	 */
	public JTextField getpaperTitleField(){
		return this.paperTitleField;
	}


	/**
	 * Gets the keywords field.
	 *
	 * @return the keywords field
	 */
	public CustomTextField getkeywordsField(){
		return this.keywordsField;
	}

	/**
	 * Gets the paper publication start yr.
	 *
	 * @return the paper publication start yr
	 */
	public JTextField getpaperPublicationStartYr(){
		return this.paperPublicationStartYr;
	}

	/**
	 * Gets the paper publication end yr.
	 *
	 * @return the paper publication end yr
	 */
	public JTextField getpaperPublicationEndYr(){
		return this.paperPublicationEndYr;
	}

	/**
	 * Sets the ppaper publication end yr.
	 *
	 * @param paperPublicationEndYr the new ppaper publication end yr
	 */
	public void setppaperPublicationEndYr(JTextField paperPublicationEndYr){
		this.paperPublicationEndYr = paperPublicationEndYr;
	}

	/**
	 * Gets the exact num of papers field.
	 *
	 * @return the exact num of papers field
	 */
	public JTextField getexactNumOfPapersField(){
		return this.exactNumOfPapersField;
	}

	/**
	 * Gets the num of papers start field.
	 *
	 * @return the num of papers start field
	 */
	public JTextField getnumOfPapersStartField(){
		return this.numOfPapersStartField;
	}

	/**
	 * Gets the num of papers end fields.
	 *
	 * @return the num of papers end fields
	 */
	public JTextField getnumOfPapersEndFields(){
		return this.numOfPapersEndField;
	}

	/**
	 * Gets the rdbtn exact.
	 *
	 * @return the rdbtn exact
	 */
	public JRadioButton getrdbtnExact(){
		return this.rdbtnExact;
	}

	/**
	 * Gets the rdbtn range.
	 *
	 * @return the rdbtn range
	 */
	public JRadioButton getrdbtnRange(){
		return this.rdbtnRange;
	}


	/**
	 * Inits the fields.
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		
		JPanel panel = new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tabbedPane.add("Author Parameters", AuthorParamtersUI());
		tabbedPane.add("Paper Parameters", PaperParamtersUI());

		panel.add(tabbedPane);

		return panel;
	}

	/**
	 * Creates the Author paramters UI with all the labels and corresponding fields.
	 *
	 * @return the component
	 */
	private Component AuthorParamtersUI() {
		JPanel panel = new JPanel(new MigLayout());
		JPanel authorPanel = new JPanel();
		authorPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Author", 
				TitledBorder.LEFT, TitledBorder.TOP,  new Font("Tahoma",Font.PLAIN,20), new Color(64, 64, 64)));
		authorPanel.setLayout(new MigLayout("", "[]10[]", "[]10[]10[]10[]"));

		SetAuthorSearchParams.setAuthorPanel(authorPanel, authorNameField);

		SetAuthorSearchParams.setSchoolPanel(authorPanel, schoolNameField);

		SetAuthorSearchParams.setThesisPanel(authorPanel, rdbtnMasterThesis, rdbtnPhdThesis, thesisBtnGroup);

		SetAuthorSearchParams.setNumberOfPapersPanel(authorPanel, rdbtnExact, exactNumOfPapersField, rdbtnRange,
				numOfPapersStartField, numOfPapersEndField, numOfPapersRadioBtnGroup);

		SetAuthorSearchParams.setAffiliationPanel(authorPanel, affiliatedUniField);

		JPanel committeePanel = new JPanel();
		committeePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Committee", 
				TitledBorder.LEFT, TitledBorder.TOP,  new Font("Tahoma",Font.PLAIN,20), new Color(64, 64, 64)));
		committeePanel.setLayout(new MigLayout("", "[]10[]", "[]10[]10[]10[]"));

		SetAuthorSearchParams.setServedInCommitteePanel(committeePanel, rdbtnYes, rdbtnNo, servedInCommitteeBeforeBtnGroup);

		SetAuthorSearchParams.setCommitteeYearPanel(committeePanel, committeeServedStartYr, committeeServedEndYr);

		SetAuthorSearchParams.setConfLabelPanel(committeePanel, committeeConfField);

		panel.add(authorPanel, "cell 0 0");
		panel.add(committeePanel, "cell 1 0");

		JLabel blankLabel = new JLabel("");
		blankLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(blankLabel, "cell 0 3");

		return panel;
	}

	/**
	 * Creates the Paper paramters UI.
	 *
	 * @return the component
	 */
	private Component PaperParamtersUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[]10[]90[]10[]", "[]10[]10[]"));

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(titleLabel, "cell 0 0");
		paperTitleField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(paperTitleField, "cell 1 0");

		JLabel keywordsLabel = new JLabel("Keywords");
		keywordsLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(keywordsLabel, "cell 0 1");
		keywordsField.setFont(new Font("Tahoma", Font.PLAIN, 24));    
		keywordsField.setPlaceholder("enter keywords separated by comma");
		panel.add(keywordsField, "cell 1 1");

		JLabel yearOfPaperPubLabel = new JLabel("Year Of Publication");
		yearOfPaperPubLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(yearOfPaperPubLabel, "cell 0 2");
		Box startYearBox = Box.createHorizontalBox();
		JLabel startYearLabel = new JLabel("start range");
		startYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		startYearBox.add(startYearLabel);
		paperPublicationStartYr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		startYearBox.add(paperPublicationStartYr);

		Box endYearBox = Box.createHorizontalBox();
		JLabel endYearLabel = new JLabel("end range");
		endYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		endYearBox.add(endYearLabel);
		paperPublicationEndYr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		endYearBox.add(paperPublicationEndYr);

		Box collectiveBox = Box.createHorizontalBox();
		collectiveBox.add(startYearBox);
		collectiveBox.add(Box.createRigidArea(new Dimension(10, 10)));
		collectiveBox.add(endYearBox);
		panel.add(collectiveBox, "cell 1 2");

		JLabel paperConferenceNameLabel = new JLabel("Conference Name(s)");
		paperConferenceNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(paperConferenceNameLabel, "cell 2 0");  

		paperConfField.setFont(new Font("Tahoma", Font.PLAIN, 24));      
		paperConfField.setPlaceholder("enter names separated by comma");

		JButton viewPaperConfListButton = new JButton("View Conferences");
		viewPaperConfListButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewPaperConfListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PaperConferenceNameListView();

			}
		});

		Box confBox = Box.createHorizontalBox();
		confBox.add(paperConfField);
		confBox.add(Box.createRigidArea(new Dimension(10, 0)));
		confBox.add(viewPaperConfListButton);

		panel.add(confBox, "cell 3 0");

		JLabel paperJournalNamesLabel = new JLabel("Journal Name(s)");
		paperJournalNamesLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(paperJournalNamesLabel, "cell 2 1");      

		paperJournalField.setFont(new Font("Tahoma", Font.PLAIN, 24));      
		paperJournalField.setPlaceholder("enter names separated by comma");

		JButton viewPaperJournalListButton = new JButton("View Journals");
		viewPaperJournalListButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewPaperJournalListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PaperJournalNameListView();

			}
		});

		Box journalBox = Box.createHorizontalBox();
		journalBox.add(paperJournalField);
		journalBox.add(Box.createRigidArea(new Dimension(10, 0)));
		journalBox.add(viewPaperJournalListButton);

		panel.add(journalBox, "cell 3 1");

		return panel;
	}


	/**
	 * Initializes the fields of the Panel.
	 *
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkIfAllFieldsEmpty()){
					JOptionPane jOptionPane = new JOptionPane("Please enter at least 1 search value",
							JOptionPane.ERROR_MESSAGE);
					runDisposeInBackground(jOptionPane);
				}

				else{
					validateDifferentFields();
				}
			}
		});

		clearAllButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		clearAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				authorNameField.setText("");	
				schoolNameField.setPlaceholder("enter name of schools separated by comma");

				thesisBtnGroup.clearSelection();
				rdbtnMasterThesis.setSelected(false);
				rdbtnPhdThesis.setSelected(false);

				numOfPapersRadioBtnGroup.clearSelection();
				rdbtnExact.setSelected(false);
				exactNumOfPapersField.setText("");
				rdbtnRange.setSelected(false);
				numOfPapersStartField.setText("");
				numOfPapersEndField.setText("");

				affiliatedUniField.setText("");

				servedInCommitteeBeforeBtnGroup.clearSelection();
				rdbtnYes.setSelected(false);
				rdbtnNo.setSelected(false);
				committeeServedStartYr.setText("");
				committeeServedEndYr.setText("");
				committeeConfField.setPlaceholder("enter names separated by comma");

				paperTitleField.setText("");
				keywordsField.setPlaceholder("enter keywords separated by comma");

				paperPublicationStartYr.setText("");
				paperPublicationEndYr.setText("");
				paperConfField.setPlaceholder("enter names separated by comma");
				paperJournalField.setPlaceholder("enter names separated by comma");
			}
		});

		panel.add(searchButton);
		panel.add(clearAllButton);

		return panel;
	}


	/** Validates all the fields of the panel.
	 * Validation criteria differs for each of the fields.
	 * Depending on the validation results, appropriate error message is displayed and the application 
	 * does'nt continue to execute the search query. 
	 * 
	 * @return
	 */
	public boolean checkIfAllFieldsEmpty(){
		return (authorNameField.getText().trim().isEmpty()
				&& (schoolNameField.getText().trim().isEmpty() 
						|| schoolNameField.getText().equals("enter name of schools separated by comma"))
				&& thesisBtnGroup.getSelection() == null
				&& (numOfPapersRadioBtnGroup.getSelection() == null
				|| (rdbtnExact.isSelected() && exactNumOfPapersField.getText().trim().isEmpty() && Integer.parseInt(exactNumOfPapersField.getText()) != -1)
				|| (rdbtnRange.isSelected() && numOfPapersStartField.getText().trim().isEmpty()
						&& numOfPapersEndField.getText().trim().isEmpty()))
				&& servedInCommitteeBeforeBtnGroup.getSelection() == null
				&& committeeServedStartYr.getText().trim().isEmpty()
				&& committeeServedEndYr.getText().trim().isEmpty()					
				&& paperTitleField.getText().trim().isEmpty()
				&& (keywordsField.getText().trim().isEmpty()
						||keywordsField.getText().equals("enter keywords separated by comma"))
				&& paperPublicationStartYr.getText().trim().isEmpty()
				&& paperPublicationEndYr.getText().trim().isEmpty()
				&& (committeeConfField.getText().trim().isEmpty() 
						|| committeeConfField.getText().equals("enter names separated by comma"))
				&& (paperConfField.getText().trim().isEmpty() 
						|| paperConfField.getText().equals("enter names separated by comma"))
				&& (paperJournalField.getText().trim().isEmpty() 
						|| paperJournalField.getText().equals("enter names separated by comma"))
				&& affiliatedUniField.getText().trim().isEmpty());
	}

	public boolean validateDifferentFields(){
		String ERR_MSG="Valid";
		if(!exactNumOfPapersField.getText().trim().isEmpty() && !ValidateSearchFields.validateExactNumberOfPapers(exactNumOfPapersField).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateExactNumberOfPapers(exactNumOfPapersField);
		} else if(!numOfPapersStartField.getText().trim().isEmpty() && !ValidateSearchFields.validateNumOfPapersStartField(numOfPapersStartField).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateNumOfPapersStartField(numOfPapersStartField);
		} else if(!numOfPapersEndField.getText().trim().isEmpty() && !ValidateSearchFields.validateNumOfPapersEndField(numOfPapersEndField).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateNumOfPapersEndField(numOfPapersEndField);
		} else if(!paperPublicationStartYr.getText().trim().isEmpty() && !ValidateSearchFields.validatePublicationStartYear(paperPublicationStartYr).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validatePublicationStartYear(paperPublicationStartYr);
		} else if(!paperPublicationEndYr.getText().trim().isEmpty() && !ValidateSearchFields.validatePublicationEndYear(paperPublicationEndYr).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validatePublicationEndYear(paperPublicationEndYr);
		} else if(!committeeServedStartYr.getText().trim().isEmpty() && !ValidateSearchFields.validateCommitteeServedStartYr(committeeServedStartYr).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateCommitteeServedStartYr(committeeServedStartYr);
		} else if(!committeeServedEndYr.getText().trim().isEmpty() && !ValidateSearchFields.validateCommitteeServedEndYr(committeeServedEndYr).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateCommitteeServedEndYr(committeeServedEndYr);
		} else if(!ValidateSearchFields.validateStartEndRange(numOfPapersStartField, numOfPapersEndField).equals("Valid")){
			ERR_MSG = ValidateSearchFields.validateStartEndRange(numOfPapersStartField, numOfPapersEndField);
		}

		if(ERR_MSG.equals("Valid")){
			if(!getSearchParams(ERR_MSG)){
				return false;
			}
	} else {
			JOptionPane jOptionPane = new JOptionPane(ERR_MSG, JOptionPane.ERROR_MESSAGE);
			runDisposeInBackground(jOptionPane);
			return false;
		}
		return true;
	}
		
	
	public boolean getSearchParams(String ERR_MSG){
		if(!addAuthorNameField(ERR_MSG)){
			return false;
		}
		if(!addSchoolNameField()){
			return false;
		}
		addThesisField();

		if(!addPublishedPapers()){
			return false;
		}
		if(!addAffiliation()){
			return false;
		}

		addServedYearRadio();

		searchParamObj.setServedInCommitteeStartYear(-1);
		searchParamObj.setServedInCommitteeEndYear(-1);
		if(!committeeServedStartYr.getText().trim().isEmpty())
			searchParamObj.setServedInCommitteeStartYear(Integer.parseInt(committeeServedStartYr.getText().trim()));
		if(!committeeServedEndYr.getText().trim().isEmpty())
			searchParamObj.setServedInCommitteeEndYear(Integer.parseInt(committeeServedEndYr.getText().trim()));

		if(!addConfList()){
			return false;
		}

		if(!paperTitleField.getText().trim().isEmpty()){
			
			String paperTitleText = paperTitleField.getText().replaceAll("\\s+", " ");
			paperTitleField.setText(paperTitleText);

			searchParamObj.setPaperTitle(paperTitleField.getText().trim());
		}

		if(!addKeywordsList()){
			return false;
		}

		addYearOfPublication();

		if(!addConfListInPaperParams()){
			return false;
		}

		if(!addJournalList()){
			return false;
		}

		//Call Search() from Query Engine
		QueryBuilder queryEngineObj = new QueryBuilder();
		resultantAuthorList = queryEngineObj.Search(searchParamObj);

		parentFrame.getContentPane().removeAll();
		parentFrame.getContentPane().revalidate();
		parentFrame.getContentPane().repaint();

		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(new SearchUI(searchParamObj));
		split.setBottomComponent(new SearchResults(resultantAuthorList));
		split.setDividerLocation(0.8);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tabbedPane.add("Search", split);
		tabbedPane.addTab("Committee List", new CommitteeListUI());
		tabbedPane.addTab("Update Profile", new UpdateProfileUI());
		tabbedPane.add("Logout", new LogoutUI());

		parentFrame.getContentPane().add(tabbedPane);
		return true;
	}
	
	/**  
	 * Adds the Author Name field to the Search Parameters if valid.
	 *  
	 * @param ERR_MSG
	 * @return a boolean flag
	 */
	public boolean addAuthorNameField(String ERR_MSG){
		if(!authorNameField.getText().trim().isEmpty()){
			if(ValidateSearchFields.validateAuthorNameField(authorNameField).equals("Valid")){

				String authorNameText = authorNameField.getText().replaceAll("\\s+", " ");
				authorNameField.setText(authorNameText);

				searchParamObj.setAuthorName(authorNameField.getText().trim());
			} else {
				ERR_MSG = ValidateSearchFields.validateAuthorNameField(authorNameField);
				JOptionPane jOptionPane = new JOptionPane(ERR_MSG, JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds the School Name field to the Search Parameters if it is valid.
	 * 
	 * @return a boolean flag
	 */
	public boolean addSchoolNameField(){
		if(!schoolNameField.getText().trim().isEmpty() && !schoolNameField.getText().equals("enter name of schools separated by comma")){
			if(!schoolNameField.getText().equals(",")
					&& ValidateSearchFields.validateSchoolName(schoolNameField).equals("Valid")){
				
				String schoolNameText = schoolNameField.getText().replaceAll("[,]", "$0 ").replaceAll("\\s+", " ").replaceAll(", ", ",").replaceAll(" ,", ",");
				schoolNameField.setText(schoolNameText);

				searchParamObj.setSchoolNameList(new ArrayList<String>
				(Arrays.asList(schoolNameField.getText().trim().split(","))));

		}	else {
				JOptionPane jOptionPane = new JOptionPane("Enter valid School name", JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}

	
	/**
	 * Adds the Thesis field content to the SearchParameters is it is valid.
	 */
	public void addThesisField(){
		if(thesisBtnGroup.getSelection() != null){
			switch(thesisBtnGroup.getSelection().getActionCommand()){
			case "MastersThesis": 
				searchParamObj.setThesisType("MastersThesis");
				break;
			case "PhdThesis":
				searchParamObj.setThesisType("PhdThesis");
				break;
			}
		}

	}

	
	/**
	 * Adds the number of published papers to the Search Parameters if they are valid.
	 * If Start and End range are empty when the radio button for range is selected, throws error message.
	 * If Start is empty and End has a number entered, add the start range value to the Search Parameters.
	 * If End is empty and start has a number entered, add the end range value to the Search Parameters.
	 * 
	 * @return a boolean flag
	 */
	public boolean addPublishedPapers(){

		searchParamObj.setExactNumberOfPapers(-1);
		searchParamObj.setNumberOfPapersStartRange(-1);
		searchParamObj.setNumberOfPapersEndRange(-1);

		if(numOfPapersRadioBtnGroup.getSelection() != null){
			switch(numOfPapersRadioBtnGroup.getSelection().getActionCommand()){
				case "Exactly":
					if(!exactNumOfPapersField.getText().trim().isEmpty()){
						searchParamObj.setExactNumberOfPapers(Integer.parseInt(exactNumOfPapersField.getText()));
					} else {
						JOptionPane jOptionPane = new JOptionPane("Exact Number of Papers cannot be empty", JOptionPane.ERROR_MESSAGE);
						runDisposeInBackground(jOptionPane);
						return false;
					}
					break;
				case "Range" :				
					if(!numOfPapersStartField.getText().trim().isEmpty() && numOfPapersEndField.getText().trim().isEmpty()){
						searchParamObj.setNumberOfPapersStartRange(Integer.parseInt(numOfPapersStartField.getText()));
					} else if(!numOfPapersEndField.getText().trim().isEmpty() && numOfPapersStartField.getText().trim().isEmpty()){
						searchParamObj.setNumberOfPapersEndRange(Integer.parseInt(numOfPapersEndField.getText()));
					} else if(!numOfPapersStartField.getText().trim().isEmpty() && !numOfPapersEndField.getText().trim().isEmpty()){
						searchParamObj.setNumberOfPapersStartRange(Integer.parseInt(numOfPapersStartField.getText()));
						searchParamObj.setNumberOfPapersEndRange(Integer.parseInt(numOfPapersEndField.getText()));
					} else {
						JOptionPane jOptionPane = new JOptionPane("Both start and end range for papers cannot be empty",
								JOptionPane.ERROR_MESSAGE);
						runDisposeInBackground(jOptionPane);
						return false;
					}
					break;
			}
		}
		return true;
	}
	
	/**
	 * Adds the Affiliation University field content to the Search Parameters if its valid.
	 * 
	 * @return
	 */
	public boolean addAffiliation(){
		if(!affiliatedUniField.getText().trim().isEmpty()){
			if(!affiliatedUniField.getText().equals(",")
					&& ValidateSearchFields.validateAffiliation(affiliatedUniField).equals("Valid")){


				String affiliatedUniText = affiliatedUniField.getText().replaceAll("\\s+", " ");
				affiliatedUniField.setText(affiliatedUniText);

				searchParamObj.setAffiliatedUni(affiliatedUniField.getText().trim());
		}	else {
				JOptionPane jOptionPane = new JOptionPane("Enter valid Affiliation", JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds the detail whether the author has served in committee before or not.
	 */
	public void addServedYearRadio(){
		if(servedInCommitteeBeforeBtnGroup.getSelection() != null){
			switch(servedInCommitteeBeforeBtnGroup.getSelection().getActionCommand()){
			case "Yes": 
				searchParamObj.setServedInCommitteeBefore("Yes");
				break;
			case "No":
				searchParamObj.setServedInCommitteeBefore("No");
				break;
			}
		}
	}

	
	/**
	 * Adds the list of conferences names to the Search Parameters if valid.
	 * 
	 * @return
	 */
	public boolean addConfList(){
		if(!committeeConfField.getText().trim().isEmpty()
				&& !committeeConfField.getText().equals("enter names separated by comma")){
			if(!committeeConfField.getText().equals(",") && ValidateSearchFields.validateConferencesName(committeeConfField).equals("Valid")){
				
				String committeeConfText = committeeConfField.getText().replaceAll("[,]", "$0 ").replaceAll("\\s+", " ").replaceAll(", ", ",").replaceAll(" ,", ",");
				committeeConfField.setText(committeeConfText);

				searchParamObj.setCommitteeConferenceNameList(new ArrayList<String>	
				(Arrays.asList(committeeConfField.getText().trim().split(","))));
			} else {
				JOptionPane jOptionPane  = new JOptionPane("Enter valid conference names",
						JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}

		if(searchParamObj.getCommitteeConferenceNameList() != null
				|| searchParamObj.getServedInCommitteeStartYear() != -1
				|| searchParamObj.getServedInCommitteeEndYear() != -1){
			if(searchParamObj.hasServedInCommitteeBefore() == null)
				searchParamObj.setServedInCommitteeBefore("Yes");
		}
		return true;
	}


	/**
	 * Adds the list of keywords to the Search Paramters if its valid.
	 * @return a boolean flag
	 */
	public boolean addKeywordsList(){
		if(!keywordsField.getText().trim().isEmpty()
				&& !keywordsField.getText().trim().equals("enter keywords separated by comma")){
			if(!keywordsField.getText().equals(",") && ValidateSearchFields.validateKeywords(keywordsField).equals("Valid")){
				
				String keywordText = keywordsField.getText().replaceAll("[,]", "$0 ").replaceAll("\\s+", " ").replaceAll(", ", ",").replaceAll(" ,", ",");
				keywordsField.setText(keywordText);

				searchParamObj.setKeywords(new ArrayList<String>
				(Arrays.asList(keywordsField.getText().trim().split(","))));
			} else {
				JOptionPane.showMessageDialog(null, "Enter valid keywords","Message",JOptionPane.ERROR_MESSAGE);
				JOptionPane jOptionPane = new JOptionPane("Enter valid keywords", JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds the year of paper publication to Search Parameters if its valid.
	 */
	public void addYearOfPublication(){
		searchParamObj.setStartYearOfPublication(-1);
		searchParamObj.setEndYearOfPublication(-1);
		if(!paperPublicationStartYr.getText().trim().isEmpty())
			searchParamObj.setStartYearOfPublication(Integer.parseInt(paperPublicationStartYr.getText().trim()));
		if(!paperPublicationEndYr.getText().trim().isEmpty())
			searchParamObj.setEndYearOfPublication(Integer.parseInt(paperPublicationEndYr.getText().trim()));
	}

	
	/**
	 * Adds the list of journals to the Search Parameters if it is valid.
	 * @return
	 */
	public boolean addJournalList(){
		if(!paperJournalField.getText().trim().isEmpty()
				&& !paperJournalField.getText().equals("enter names separated by comma")){
			if(!paperJournalField.getText().equals(",") && ValidateSearchFields.validateJournalsName(paperJournalField).equals("Valid")){
				
				String paperJournalText = paperJournalField.getText().replaceAll("[,]", "$0 ").replaceAll("\\s+", " ").replaceAll(", ", ",").replaceAll(" ,", ",");
				paperJournalField.setText(paperJournalText);

				searchParamObj.setPaperJournalNameList(new ArrayList<String>
				(Arrays.asList(paperJournalField.getText().trim().split(","))));
			} else {
				JOptionPane jOptionPane = new JOptionPane("Enter valid Journal names",
						JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds the list of conferences from the Paper Paramters UI to the Search parameters if its valid.
	 * @return a boolean flag
	 */
	public boolean addConfListInPaperParams(){
		if(!paperConfField.getText().trim().isEmpty()
				&& !paperConfField.getText().equals("enter names separated by comma")){
			if(!paperConfField.getText().equals(",") && ValidateSearchFields.validateConferencesName(paperConfField).equals("Valid")){
				
				String paperConfName = paperConfField.getText().replaceAll("[,]", "$0 ").replaceAll("\\s+", " ").replaceAll(", ", ",").replaceAll(" ,", ",");
				paperConfField.setText(paperConfName);

				searchParamObj.setPaperConferenceNameList(new ArrayList<String>
				(Arrays.asList(paperConfField.getText().trim().split(","))));	
			} else{
				JOptionPane jOptionPane = new JOptionPane("Enter valid Conference names",
						JOptionPane.ERROR_MESSAGE);
				runDisposeInBackground(jOptionPane);
				return false;
			}
		}
		return true;
	}
	
	public void runDisposeInBackground(JOptionPane jOptionPane){
		final JDialog dialog = jOptionPane.createDialog(parentFrame, "Search Similar Author");

	      new SwingWorker<Void, Void>() {

	         @Override
	         protected Void doInBackground() throws Exception {
	            Thread.sleep(3000); 

	            return null;
	         }

	         protected void done() {
	            dialog.dispose();
	         };
	      }.execute();

	      dialog.setVisible(true);
	}
}


