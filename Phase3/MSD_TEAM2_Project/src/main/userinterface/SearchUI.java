package main.userinterface;

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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;

import main.queryengine.Author;
import main.queryengine.QueryBuilder;

import javax.swing.UIManager;
import java.awt.Color;

public class SearchUI extends JPanel{

	private JFrame parentFrame;

	private JTextField authorNameField = new JTextField(30);
	private CustomTextField schoolNameField = new CustomTextField(30);
	ButtonGroup thesisBtnGroup = new ButtonGroup();
	private JRadioButton rdbtnMasterThesis = new JRadioButton();
	private JRadioButton rdbtnPhdThesis = new JRadioButton();
	ButtonGroup servedInCommitteeBeforeBtnGroup = new ButtonGroup();

	private JRadioButton rdbtnYes = new JRadioButton();
	private JRadioButton rdbtnNo = new JRadioButton();
	private JTextField committeeServedStartYr = new JTextField(4);
	private JTextField committeeServedEndYr = new JTextField(4);
	private JCheckBox committeeConferenceNameOOPSLA = new JCheckBox("OOPSLA");
	private JCheckBox committeeConferenceNamePLDI = new JCheckBox("PLDI");
	private JCheckBox committeeConferenceNameECOOP = new JCheckBox("ECOOP");
	private JCheckBox committeeConferenceNameICFP = new JCheckBox("ICFP");	
	private JTextField paperTitleField = new JTextField(30);	
	private CustomTextField keywordsField = new CustomTextField(30);
	private JCheckBox paperConferenceNameOOPSLA = new JCheckBox("OOPSLA");
	private JCheckBox paperConferenceNamePLDI = new JCheckBox("PLDI");
	private JCheckBox paperConferenceNameECOOP = new JCheckBox("ECOOP");
	private JCheckBox paperConferenceNameICFP = new JCheckBox("ICFP");
	private JCheckBox paperJournalNameTOPLAS = new JCheckBox("TOPLAS");
	private JCheckBox paperJournalNameTSE = new JCheckBox("TSE");
	private JTextField paperPublicationStartYr = new JTextField(4);
	private JTextField paperPublicationEndYr = new JTextField(4);
	private JTextField exactNumOfPapersField = new JTextField(2);
	private JTextField numOfPapersStartField = new JTextField(2);
	private JTextField numOfPapersEndField = new JTextField(2);

	ButtonGroup numOfPapersRadioBtnGroup = new ButtonGroup();
	public JRadioButton rdbtnExact = new JRadioButton("Exactly", false);
	public JRadioButton rdbtnRange = new JRadioButton("Range");

	public JButton searchButton = new JButton("Search");
	public JButton clearAllButton = new JButton("Clear All");
	
	public List<Author> resultantAuthorList;

	public SearchUI(SearchParameters enteredSearchParams){
		parentFrame = Main.getMainFrame();

		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		add(initButtons(), BorderLayout.CENTER);

		if(enteredSearchParams != null){
			setFieldsData(enteredSearchParams);
		}
	}

	public JTextField getAuthorNameField(){
		return this.authorNameField;
	}
	
	/*public void setAuthorNameField(JTextField authornamefield){
		this.authorNameField = authornamefield;
	}*/
	
	public CustomTextField getSchoolNameField(){
		return this.schoolNameField;
	}
	
	/*public void setSchoolNameField(CustomTextField schoolnamefield){
		this.schoolNameField = schoolnamefield;
	}*/
	
	public JRadioButton getSrdbtnMasterThesisField(){
		return this.rdbtnMasterThesis;
	}
	
	/*public void setSrdbtnMasterThesisField(JRadioButton rdbtnMasterThesis){
		this.rdbtnMasterThesis = rdbtnMasterThesis;
	}*/
	
	public JRadioButton getrdbtnPhdThesisField(){
		return this.rdbtnPhdThesis;
	}
	
	/*public void setrdbtnPhdThesisField(JRadioButton rdbtnPhdThesis){
		this.rdbtnPhdThesis = rdbtnPhdThesis;
	}*/
	
	public JRadioButton getrrdbtnYesField(){
		return this.rdbtnYes;
	}
	
	/*public void setrdbtnYesField(JRadioButton rdbtnYes){
		this.rdbtnYes = rdbtnYes;
	}*/
	
	public JRadioButton getrdbtnNoField(){
		return this.rdbtnNo;
	}
	
	/*public void setrdbtnNoField(JRadioButton rdbtnNo){
		this.rdbtnNo = rdbtnNo;
	}*/
	
	public JTextField getcommitteeServedStartYr(){
		return this.committeeServedStartYr;
	}
	
	/*public void setcommitteeServedStartYr(JTextField committeeServedStartYr){
		this.committeeServedStartYr = committeeServedStartYr;
	}*/
	
	public JTextField getcommitteeServedEndYr(){
		return this.committeeServedEndYr;
	}
	
	/*public void setccommitteeServedEndYr(JTextField committeeServedEndYr){
		this.committeeServedEndYr = committeeServedEndYr;
	}*/
	
	public JCheckBox getcommitteeConferenceNameOOPSLA(){
		return this.committeeConferenceNameOOPSLA;
	}
	
	/*public void setcommitteeConferenceNameOOPSLA(JCheckBox committeeConferenceNameOOPSLA){
		this.committeeConferenceNameOOPSLA = committeeConferenceNameOOPSLA;
	}*/
	
	public JCheckBox getcommitteeConferenceNamePLDI(){
		return this.committeeConferenceNamePLDI;
	}
	
	/*public void setcommitteeConferenceNamePLDI(JCheckBox committeeConferenceNamePLDI){
		this.committeeConferenceNamePLDI = committeeConferenceNamePLDI;
	}*/
	
	public JCheckBox getcommitteeConferenceNameECOOP(){
		return this.committeeConferenceNameECOOP;
	}
	
	/*public void setcommitteeConferenceNameECOOP(JCheckBox committeeConferenceNameECOOP){
		this.committeeConferenceNameECOOP = committeeConferenceNameECOOP;
	}*/
	
	public JCheckBox getcommitteeConferenceNameICFP(){
		return this.committeeConferenceNameICFP;
	}
	
	/*public void setcommitteeConferenceNameICFP(JCheckBox committeeConferenceNameICFP){
		this.committeeConferenceNameICFP = committeeConferenceNameICFP;
	}*/
	
	public JTextField getpaperTitleField(){
		return this.paperTitleField;
	}
	
	/*public void setpaperTitleField(JTextField paperTitleField){
		this.paperTitleField = paperTitleField;
	}*/
	
	public CustomTextField getkeywordsField(){
		return this.keywordsField;
	}
	
	/*public void setkeywordsField(CustomTextField keywordsField){
		this.keywordsField = keywordsField;
	}*/
	
	public JCheckBox getkpaperJournalNameTOPLAS(){
		return this.paperJournalNameTOPLAS;
	}
	
	/*public void setpaperJournalNameTOPLAS(JCheckBox paperJournalNameTOPLAS){
		this.paperJournalNameTOPLAS = paperJournalNameTOPLAS;
	}*/
	
	public JCheckBox getpaperJournalNameTSE(){
		return this.paperJournalNameTSE;
	}
	
	/*public void setpaperJournalNameTSE(JCheckBox paperJournalNameTSE){
		this.paperJournalNameTSE = paperJournalNameTSE;
	}*/
	
	public JTextField getpaperPublicationStartYr(){
		return this.paperPublicationStartYr;
	}
	
	/*public void setpaperPublicationStartYr(JTextField paperPublicationStartYr){
		this.paperPublicationStartYr = paperPublicationStartYr;
	}*/
	
	public JTextField getpaperPublicationEndYr(){
		return this.paperPublicationEndYr;
	}
	
	public void setppaperPublicationEndYr(JTextField paperPublicationEndYr){
		this.paperPublicationEndYr = paperPublicationEndYr;
	}
	
	public JTextField getexactNumOfPapersField(){
		return this.exactNumOfPapersField;
	}
	
	/*public void setexactNumOfPapersField(JTextField exactNumOfPapersField){
		this.exactNumOfPapersField = exactNumOfPapersField;
	}*/
	
	public JTextField getnumOfPapersStartField(){
		return this.numOfPapersStartField;
	}
	
	/*public void setnumOfPapersStartField(JTextField numOfPapersStartField){
		this.numOfPapersStartField = numOfPapersStartField;
	}*/
	
	public JTextField getnumOfPapersEndFields(){
		return this.numOfPapersEndField;
	}
	
	/*public void setnumOfPapersEndField(JTextField numOfPapersEndField){
		this.numOfPapersEndField = numOfPapersEndField;
	}*/
	
	public JRadioButton getrdbtnExact(){
		return this.rdbtnExact;
	}
	
	/*public void setrdbtnExact(JRadioButton rdbtnExact){
		this.rdbtnExact = rdbtnExact;
	}*/
	
	public JRadioButton getrdbtnRange(){
		return this.rdbtnRange;
	}
	
	/*public void setrdbtnRange(JRadioButton rdbtnRange){
		this.rdbtnRange = rdbtnRange;
	}*/
	
	
	// method to initialize the UI panel
	private JPanel initFields() {
		JPanel panel = new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tabbedPane.add("Author Parameters", AuthorParamtersUI());
		tabbedPane.add("Paper Parameters", PaperParamtersUI());

		panel.add(tabbedPane, BorderLayout.NORTH);
		return panel;
	}

	// create the UI for Authorparameters panel
	private Component AuthorParamtersUI() {

		JPanel panel = new JPanel();

		JPanel authorPanel = new JPanel();
		authorPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Author", 
				TitledBorder.LEFT, TitledBorder.TOP,  new Font("Tahoma",Font.PLAIN,20), new Color(64, 64, 64)));
		authorPanel.setLayout(new MigLayout("", "[]10[]", "[]10[]10[]10[]"));

		JLabel authorNameLabel = new JLabel("Author Name");
		authorNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(authorNameLabel, "flowx,cell 0 0");
		authorNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(authorNameField, "cell 1 0");


		JLabel schoolNameLabel = new JLabel("School Name");
		schoolNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		authorPanel.add(schoolNameLabel, "cell 0 1");
		schoolNameField.setFont(new Font("Tahoma", Font.PLAIN, 24));      
		schoolNameField.setPlaceholder("enter name of schools separated by comma");
		authorPanel.add(schoolNameField, "cell 1 1");

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

		JPanel committeePanel = new JPanel();
		committeePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Committee", 
				TitledBorder.LEFT, TitledBorder.TOP,  new Font("Tahoma",Font.PLAIN,20), new Color(64, 64, 64)));
		committeePanel.setLayout(new MigLayout("", "[]10[]", "[]10[]10[]10[]"));

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

		JLabel committeeConferenceLabel = new JLabel("For Conference(s)");
		committeeConferenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(committeeConferenceLabel, "flowx,cell 0 2");
	
		committeeConferenceNameOOPSLA.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeConferenceNamePLDI.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeConferenceNameECOOP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeeConferenceNameICFP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Box committeeConfBox = Box.createHorizontalBox();
		committeeConfBox.add(committeeConferenceNameOOPSLA);
		committeeConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		committeeConfBox.add(committeeConferenceNamePLDI);
		committeeConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		committeeConfBox.add(committeeConferenceNameECOOP);
		committeeConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		committeeConfBox.add(committeeConferenceNameICFP);
		committeePanel.add(committeeConfBox, "cell 1 2");

		panel.add(authorPanel);
		panel.add(committeePanel);

		JLabel blankLabel = new JLabel("");
		blankLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		committeePanel.add(blankLabel, "cell 0 3");

		return panel;
	}

	// create the UI for paper parameters panel
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
		paperConferenceNameOOPSLA.setFont(new Font("Tahoma", Font.PLAIN, 24));
		paperConferenceNamePLDI.setFont(new Font("Tahoma", Font.PLAIN, 24));
		paperConferenceNameECOOP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		paperConferenceNameICFP.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Box paperConfBox = Box.createHorizontalBox();
		paperConfBox.add(paperConferenceNameOOPSLA);
		paperConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		paperConfBox.add(paperConferenceNamePLDI);
		paperConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		paperConfBox.add(paperConferenceNameECOOP);
		paperConfBox.add(Box.createRigidArea(new Dimension(10, 10)));
		paperConfBox.add(paperConferenceNameICFP);
		panel.add(paperConfBox);


		JLabel paperJournalNamesLabel = new JLabel("Journal Name(s)");
		paperJournalNamesLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(paperJournalNamesLabel, "cell 2 1");      
		
		paperJournalNameTOPLAS.setFont(new Font("Tahoma", Font.PLAIN, 24));
		paperJournalNameTSE.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Box paperJournalBox = Box.createHorizontalBox();
		paperJournalBox.add(paperJournalNameTOPLAS);
		paperJournalBox.add(Box.createRigidArea(new Dimension(10, 10)));
		paperJournalBox.add(paperJournalNameTSE);
		panel.add(paperJournalBox);


		return panel;
	}


	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(authorNameField.getText().trim().isEmpty()
						&& (schoolNameField.getText().trim().isEmpty() 
								|| schoolNameField.getText().equals("enter name of schools separated by comma"))
						&& thesisBtnGroup.getSelection() == null
						&& (numOfPapersRadioBtnGroup.getSelection() == null
							|| (rdbtnExact.isSelected() && exactNumOfPapersField.getText().trim().isEmpty())
							|| (rdbtnRange.isSelected() && numOfPapersStartField.getText().trim().isEmpty()
									&& numOfPapersEndField.getText().trim().isEmpty()))
						&& servedInCommitteeBeforeBtnGroup.getSelection() == null
						&& committeeServedStartYr.getText().trim().isEmpty()
						&& committeeServedEndYr.getText().trim().isEmpty()
						
						&& !committeeConferenceNameOOPSLA.isSelected()
						&& !committeeConferenceNamePLDI.isSelected()
						&& !committeeConferenceNameECOOP.isSelected()
						&& !committeeConferenceNameICFP.isSelected()
					
						&& paperTitleField.getText().trim().isEmpty()
						&& (keywordsField.getText().trim().isEmpty()
								||keywordsField.getText().equals("enter keywords separated by comma"))
						&& paperPublicationStartYr.getText().trim().isEmpty()
						&& paperPublicationEndYr.getText().trim().isEmpty()
						
						&& !paperConferenceNameOOPSLA.isSelected()
						&& !paperConferenceNamePLDI.isSelected()
						&& !paperConferenceNameECOOP.isSelected()
						&& !paperConferenceNameICFP.isSelected()
						
						&& !paperJournalNameTOPLAS.isSelected()
						&& !paperJournalNameTSE.isSelected()){
					JOptionPane.showMessageDialog(SearchUI.this,
							"Please enter at least 1 search value",
							"Missing Information!",
							JOptionPane.ERROR_MESSAGE);
				}
		
				else{
					if(validateExactNumberOfPapers() 
							&& validateNumOfPapersStartField()
							&& validateNumOfPapersEndField()
							&& validatePublicationStartYear()
							&& validatePublicationEndYear()
							&& validateCommitteeServedStartYr()
							&& validateCommitteeServedEndYr()
							&& validateStartEndRange()){
					SearchParameters searchParamObj = new SearchParameters();

					if(!authorNameField.getText().trim().isEmpty()){
						searchParamObj.setAuthorName(authorNameField.getText().trim());
					}

					if(!schoolNameField.getText().trim().isEmpty()
							&& !schoolNameField.getText().equals("enter name of schools separated by comma")){
						searchParamObj.setSchoolNameList(new ArrayList<String>
						(Arrays.asList(schoolNameField.getText().trim().split(","))));
					}

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

					searchParamObj.setExactNumberOfPapers(-1);
					searchParamObj.setNumberOfPapersStartRange(-1);
					searchParamObj.setNumberOfPapersEndRange(-1);
					if(numOfPapersRadioBtnGroup.getSelection() != null){
						if(!exactNumOfPapersField.getText().trim().isEmpty() 
								|| !numOfPapersStartField.getText().trim().isEmpty()
								|| !numOfPapersEndField.getText().trim().isEmpty()){
							switch(numOfPapersRadioBtnGroup.getSelection().getActionCommand()){
							case "Exactly": 
								searchParamObj.setExactNumberOfPapers(Integer.parseInt(exactNumOfPapersField.getText()));
								break;
							case "Range" : 
								if(!numOfPapersStartField.getText().trim().isEmpty()){
									searchParamObj.setNumberOfPapersStartRange(Integer.parseInt(numOfPapersStartField.getText()));
								}
								if(!numOfPapersEndField.getText().trim().isEmpty()){
									searchParamObj.setNumberOfPapersEndRange(Integer.parseInt(numOfPapersEndField.getText()));
								}						   
								break;
							}
						}

					}

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

					searchParamObj.setServedInCommitteeStartYear(-1);
					searchParamObj.setServedInCommitteeEndYear(-1);
					if(!committeeServedStartYr.getText().trim().isEmpty())
						searchParamObj.setServedInCommitteeStartYear(Integer.parseInt(committeeServedStartYr.getText().trim()));
					if(!committeeServedEndYr.getText().trim().isEmpty())
						searchParamObj.setServedInCommitteeEndYear(Integer.parseInt(committeeServedEndYr.getText().trim()));

					if(committeeConferenceNameOOPSLA.isSelected()
							|| committeeConferenceNamePLDI.isSelected()
							|| committeeConferenceNameECOOP.isSelected()
							|| committeeConferenceNameICFP.isSelected()){
						ArrayList<String> committeeConfNames = new ArrayList<String>();
						if(committeeConferenceNameOOPSLA.isSelected())committeeConfNames.add("OOPSLA");
						if(committeeConferenceNamePLDI.isSelected())committeeConfNames.add("PLDI");
						if(committeeConferenceNameECOOP.isSelected())committeeConfNames.add("ECOOP");
						if(committeeConferenceNameICFP.isSelected())committeeConfNames.add("ICFP");
						searchParamObj.setCommitteeConferenceNameList(committeeConfNames);
					}
	
					if(searchParamObj.getCommitteeConferenceNameList() != null
							|| searchParamObj.getServedInCommitteeStartYear() != -1
							|| searchParamObj.getServedInCommitteeEndYear() != -1){
						if(searchParamObj.hasServedInCommitteeBefore() == null)
						searchParamObj.setServedInCommitteeBefore("Yes");
					}

					if(!paperTitleField.getText().trim().isEmpty()){
						searchParamObj.setPaperTitle(paperTitleField.getText().trim());
					}

					if(!keywordsField.getText().trim().isEmpty()
							&& !keywordsField.getText().trim().equals("enter keywords separated by comma")){
						searchParamObj.setKeywords(new ArrayList<String>
						(Arrays.asList(keywordsField.getText().trim().split(","))));
					}

					searchParamObj.setStartYearOfPublication(-1);
					searchParamObj.setEndYearOfPublication(-1);
					if(!paperPublicationStartYr.getText().trim().isEmpty())
						searchParamObj.setStartYearOfPublication(Integer.parseInt(paperPublicationStartYr.getText().trim()));
					if(!paperPublicationEndYr.getText().trim().isEmpty())
						searchParamObj.setEndYearOfPublication(Integer.parseInt(paperPublicationEndYr.getText().trim()));

					if(paperConferenceNameOOPSLA.isSelected()
							|| paperConferenceNamePLDI.isSelected()
							|| paperConferenceNameECOOP.isSelected()
							|| paperConferenceNameICFP.isSelected()){
						ArrayList<String> paperConfNames = new ArrayList<String>();
						if(paperConferenceNameOOPSLA.isSelected())paperConfNames.add("OOPSLA");
						if(paperConferenceNamePLDI.isSelected())paperConfNames.add("PLDI");
						if(paperConferenceNameECOOP.isSelected())paperConfNames.add("ECOOP");
						if(paperConferenceNameICFP.isSelected())paperConfNames.add("ICFP");
						searchParamObj.setPaperConferenceNameList(paperConfNames);
					}

					
					if(paperJournalNameTOPLAS.isSelected()
							|| paperJournalNameTSE.isSelected()){
						ArrayList<String> paperJournalNames = new ArrayList<String>();
						if(paperJournalNameTOPLAS.isSelected())paperJournalNames.add("TOPLAS");
						if(paperJournalNameTSE.isSelected())paperJournalNames.add("TSE");
						searchParamObj.setPaperJournalNameList(paperJournalNames);
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
					//					tabbedPane.setSelectedIndex(0);


					parentFrame.getContentPane().add(tabbedPane);
				} else {
					String message = "Please enter numbers whereever applicable.";
					JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.ERROR_MESSAGE);
				}
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

				servedInCommitteeBeforeBtnGroup.clearSelection();
				rdbtnYes.setSelected(false);
				rdbtnNo.setSelected(false);

				committeeServedStartYr.setText("");
				committeeServedEndYr.setText("");

				committeeConferenceNameOOPSLA.setSelected(false);
				committeeConferenceNamePLDI.setSelected(false);
				committeeConferenceNameECOOP.setSelected(false);
				committeeConferenceNameICFP.setSelected(false);

				paperTitleField.setText("");

				keywordsField.setPlaceholder("enter keywords separated by comma");

				paperPublicationStartYr.setText("");
				paperPublicationEndYr.setText("");

				paperConferenceNameOOPSLA.setSelected(false);
				paperConferenceNamePLDI.setSelected(false);
				paperConferenceNameECOOP.setSelected(false);
				paperConferenceNameICFP.setSelected(false);

				paperJournalNameTOPLAS.setSelected(false);
				paperJournalNameTSE.setSelected(false);
			}
		});

		panel.add(searchButton);
		panel.add(clearAllButton);

		return panel;
	}


	private void setFieldsData(SearchParameters enteredSearchParams) {

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
			ArrayList<String> committeeConfNames = enteredSearchParams.getCommitteeConferenceNameList();
			if(committeeConfNames.contains("OOPSLA"))committeeConferenceNameOOPSLA.setSelected(true);
			if(committeeConfNames.contains("PLDI"))committeeConferenceNamePLDI.setSelected(true);
			if(committeeConfNames.contains("ECOOP"))committeeConferenceNameECOOP.setSelected(true);
			if(committeeConfNames.contains("ICFP"))committeeConferenceNameICFP.setSelected(true);
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
			ArrayList<String> paperConfNames = enteredSearchParams.getPaperConferenceNameList();
			if(paperConfNames.contains("OOPSLA"))paperConferenceNameOOPSLA.setSelected(true);
			if(paperConfNames.contains("PLDI"))paperConferenceNamePLDI.setSelected(true);
			if(paperConfNames.contains("ECOOP"))paperConferenceNameECOOP.setSelected(true);
			if(paperConfNames.contains("ICFP"))paperConferenceNameICFP.setSelected(true);
		}

		if(enteredSearchParams.getPaperJournalNameList() != null){
			ArrayList<String> paperJournalNames = enteredSearchParams.getPaperJournalNameList();
			if(paperJournalNames.contains("TOPLAS"))paperJournalNameTOPLAS.setSelected(true);
			if(paperJournalNames.contains("TSE"))paperJournalNameTSE.setSelected(true);
		}
	}
	
	// validate the exactnumberofpapers search parameter
	public boolean validateExactNumberOfPapers(){
		if(exactNumOfPapersField.getText().trim().isEmpty())
			return true;
		
		return validatePapers(exactNumOfPapersField.getText().toString());
	}
	
	// method to validate number of papers entered by the user
	public boolean validatePapers(String number){
			String regex = "[0-9]+"; 
			return number.matches(regex);		
		}
	
	public boolean validateNumOfPapersStartField(){
		if(numOfPapersStartField.getText().trim().isEmpty())
			return true;
		return validatePapers(numOfPapersStartField.getText().toString());
	}
	
	public boolean validateNumOfPapersEndField(){
		if(numOfPapersEndField.getText().trim().isEmpty())
			return true;
		return validatePapers(numOfPapersEndField.getText().toString());
	}
	
	//validates whether the start publication year is of 4 numerial digits
	public boolean validatePublicationStartYear(){
		if(paperPublicationStartYr.getText().trim().isEmpty())
			return true;
		return validateDigits(paperPublicationStartYr.getText().toString());
	}
	
	
	public boolean validateDigits(String year){
		String regex = "[0-9][0-9][0-9][0-9]";
		return year.matches(regex);
	}
	
	// validates whether the end publication year is of 4 numerial digits
	public boolean validatePublicationEndYear(){
		if(paperPublicationEndYr.getText().trim().isEmpty())
			return true;
		return validateDigits(paperPublicationEndYr.getText().toString());
	}
	
	public boolean validateCommitteeServedStartYr(){
		if(committeeServedStartYr.getText().trim().isEmpty())
			return true;
		return validateDigits(committeeServedStartYr.getText().toString());
	}
	
	public boolean validateCommitteeServedEndYr(){
		if(committeeServedEndYr.getText().trim().isEmpty())
			return true;
		return validateDigits(committeeServedEndYr.getText().toString());
	}
	
	public boolean validateStartEndRange(){
		if(!numOfPapersStartField.getText().trim().isEmpty() && !numOfPapersEndField.getText().trim().isEmpty()){
			if(Integer.parseInt(numOfPapersEndField.getText()) < Integer.parseInt(numOfPapersStartField.getText())){
				return false;
			}
		} 
		return true;
		
	}
}