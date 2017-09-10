package com.msd.userinterface;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.msd.queryengine.AuthorBackgroundDetailsQuery;
import com.msd.queryengine.CommitteeQuery;
import com.msd.queryengine.model.Author;
import com.msd.queryengine.services.SimilarSearchService;
import net.miginfocom.swing.MigLayout;

/**
 * The Class SearchSimilarAuthorUI.
 */
public class SearchSimilarAuthorUI extends JDialog{
	
	/** The author name. */
	private String authorName;
	
	/** The search service obj. */
	private SimilarSearchService searchServiceObj = new SimilarSearchService();
	
	/** The co authors list. */
	private List<Author> coAuthorsList;
	
	/** The affiliated authors list. */
	private List<Author> affiliatedAuthorsList;
	
	/** The same region authors list. */
	private List<Author> sameRegionAuthorsList;
	
	/** The same research area authors list. */
	private List<Author> sameResearchAreaAuthorsList;
	
	/** The affiliated uni of author. */
	private String affiliatedUniOfAuthor;
	
	/** The region of author. */
	private String regionOfAuthor;
	
	/** The research area of author. */
	private List<String> researchAreaOfAuthor;
	
	/** The split pane. */
	private JSplitPane splitPane;
	
	/** The table. */
	public JTable table;
	
	/** The search btn group. */
	ButtonGroup searchBtnGroup = new ButtonGroup();
	
	/** The rdbtn co author. */
	private JRadioButton rdbtnCoAuthor = new JRadioButton();
	
	/** The rdbtn affiliated uni. */
	private JRadioButton rdbtnAffiliatedUni = new JRadioButton();
	
	/** The rdbtn region. */
	private JRadioButton rdbtnRegion = new JRadioButton();
	
	/** The rdbtn research. */
	private JRadioButton rdbtnResearch = new JRadioButton();
	
	/** The search button. */
	private JButton searchButton = new JButton("Search");
	
	/** The add to list button. */
	private JButton addToListButton = new JButton("Shortlist Author");

	/**
	 * Instantiates a new search similar author UI.
	 *
	 * @param authorName the author name
	 */
	public SearchSimilarAuthorUI(String authorName){
		this.authorName = authorName;
		
		AuthorBackgroundDetailsQuery queryObj = new AuthorBackgroundDetailsQuery();
		this.affiliatedUniOfAuthor = queryObj.getAffiliatedUniversityOfAuthor(authorName);
		this.regionOfAuthor = queryObj.getRegionOfAuthor(authorName);
		this.researchAreaOfAuthor = queryObj.getResearchAreaOfAuthor(authorName);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		Rectangle bounds = gc.getBounds();
		setSize(bounds.width, bounds.height-50);
		setLocation(bounds.x, bounds.y);
		
	    setResizable(false);
	    
	    setTitle("Search Similar Authors");
	    
	    setLayout(new MigLayout("fill"));
	    
	    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(initFields());
		splitPane.setRightComponent(displayInitialSearchMessage());
		splitPane.setDividerLocation(0.8);
		splitPane.setEnabled(false);
		
		add(splitPane, "grow, push, span");
		
		setModalityType(ModalityType.APPLICATION_MODAL);
	    
	    setVisible(true);
	}
	
	
	/**
	 * Initializes the contents of the Search Similar Author UI panel.
	 *
	 * @return the j panel
	 */
	private JPanel initFields(){
		JPanel panel = new JPanel(new MigLayout());
		
		JLabel headingLabel = new JLabel("Search authors similar to " + authorName);
		headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.add(headingLabel, "wrap");
		
		JLabel heading1Label = new JLabel("based on:");
		heading1Label.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.add(heading1Label, "wrap");
		
		rdbtnCoAuthor.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnCoAuthor.setActionCommand("CoAuthor");
		rdbtnCoAuthor.setText("Co-Authors");
		
		rdbtnAffiliatedUni.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnAffiliatedUni.setActionCommand("AffiliatedUni");
		rdbtnAffiliatedUni.setText("Affiliated with " 
									+ (affiliatedUniOfAuthor == null ? "same university" : affiliatedUniOfAuthor));
		if(affiliatedUniOfAuthor == null){
			rdbtnAffiliatedUni.setEnabled(false);
		}
		
		rdbtnRegion.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnRegion.setActionCommand("Region");
		rdbtnRegion.setText("Belonging to "
				+ (regionOfAuthor == null ? "same Region" : regionOfAuthor)); 
		if(regionOfAuthor == null){
			rdbtnRegion.setEnabled(false);
		}
		
		boolean researchAreaFlag = false;
		rdbtnResearch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtnResearch.setActionCommand("Research");
		if(researchAreaOfAuthor.size() == 0){
			rdbtnResearch.setEnabled(false);
			rdbtnResearch.setText("Having same Research Area"); 
		}
		else{
			researchAreaFlag = true;
			rdbtnResearch.setText("Having Research Area(s) in: "); 
		}
		
		searchBtnGroup.add(rdbtnCoAuthor);
		searchBtnGroup.add(rdbtnAffiliatedUni);
		searchBtnGroup.add(rdbtnRegion);
		searchBtnGroup.add(rdbtnResearch);
		
		panel.add(rdbtnCoAuthor, "wrap");
		panel.add(rdbtnAffiliatedUni, "wrap");
		panel.add(rdbtnRegion, "wrap");
		panel.add(rdbtnResearch, "wrap");
		
		if(researchAreaFlag){
			Box vertBox = Box.createVerticalBox();
			for (String area : researchAreaOfAuthor) {
				JLabel areaLabel = new JLabel(area);
				areaLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
				vertBox.add(areaLabel);
			}
			panel.add(vertBox, "gapleft 25, wrap");
		}
		
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchBtnGroup.getSelection() == null){
					JOptionPane jOptionPane = new JOptionPane("Please select at least 1 value",
							JOptionPane.ERROR_MESSAGE);
					
					 final JDialog dialog = jOptionPane.createDialog(SearchSimilarAuthorUI.this, "Search Similar Author");

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
				else{
					if(searchBtnGroup.getSelection().getActionCommand().equals("CoAuthor")){
						if(coAuthorsList == null){
							coAuthorsList = searchServiceObj.similarSearch("coauthor",authorName);
						}
						renderSearchResults(coAuthorsList, "CoAuthor");
					}
					else if(searchBtnGroup.getSelection().getActionCommand().equals("AffiliatedUni")){
						if(affiliatedAuthorsList == null){
							affiliatedAuthorsList = searchServiceObj.similarSearch("university",authorName);
						}
						renderSearchResults(affiliatedAuthorsList, "AffiliatedUni");
					}
					else if(searchBtnGroup.getSelection().getActionCommand().equals("Region")){
						if(sameRegionAuthorsList == null){
							sameRegionAuthorsList = searchServiceObj.similarSearch("region",authorName);
						}
						renderSearchResults(sameRegionAuthorsList, "Region");
					}
					else if(searchBtnGroup.getSelection().getActionCommand().equals("Research")){
						if(sameResearchAreaAuthorsList == null){
							sameResearchAreaAuthorsList = searchServiceObj.similarSearch("researcharea",authorName);
						}
						renderSearchResults(sameResearchAreaAuthorsList, "Research");
					}
				}
			}
		});
		
		panel.add(searchButton, "align center");
		
		return panel;
	}
	
	
	/**
	 * Display initial search message.
	 *
	 * @return the j panel
	 */
	private JPanel displayInitialSearchMessage(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Please select the search criteria");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(label);
		
		return panel;
	}
	
	
	/**
	 * Render search results obtained based on the searchType.
	 *
	 * @param searchResultsList the search results list
	 * @param searchType the search type
	 */
	private void renderSearchResults(List<Author> searchResultsList, String searchType) {
		if(searchType.equals("CoAuthor")){
			searchResultsList.sort(new PublicationTitleComparator());
		}
		
		final List<Author> finalSearchResultsList = searchResultsList;
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Author Name");
		columnNames.addElement("Publication Name");
		columnNames.addElement("Publication Type");
		columnNames.addElement("Title");
		columnNames.addElement("Year");

		Vector<Vector> data = new Vector<Vector>();
		for (Author details : searchResultsList) {
			if(!details.getName().equals(authorName)){
				Vector<String> row = new Vector<String>();
				row.addElement(details.getName());
				row.addElement(details.getPublicationName());
				row.addElement(details.getType());
				row.addElement(details.getTitle());
				row.addElement(String.valueOf(details.getYear()));
				data.addElement(row);
			}			
		}

		table = new JTable(data, columnNames){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 24));
		table.setRowHeight(table.getRowHeight() + 10);
		table.setFillsViewportHeight(true);
		resizeColumnWidth(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(table);
		
		JLabel heading = new JLabel();
		
		if(searchType.equals("CoAuthor")){
			heading = new JLabel("Following are the authors who have co-authored papers with " + authorName);
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		else if(searchType.equals("AffiliatedUni")){
			heading = new JLabel("Following are the authors who are affiliated with " + authorName + "'s University");
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		else if(searchType.equals("Region")){
			heading = new JLabel("Following are the authors belonging to same region as " + authorName);
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		else if(searchType.equals("Research")){
			heading = new JLabel("Following are the authors who have same Research Area as " + authorName);
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		

		addToListButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		addToListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* On selecting a row in the results table, add the selected authors 
				 * to the list of authors being selected for committee
				 * and also add them to the shortlistcommittee table in the database only if the author does'nt
				 * already exist.
				 */
				if(table.getSelectedRow() != -1){
					ArrayList<Author> authorsToBeAddedToDB = new ArrayList<Author>();
					for (int rowIndex : table.getSelectedRows()) {
						if(!Main.getCurrentCommittee().contains(finalSearchResultsList.get(rowIndex))){
							authorsToBeAddedToDB.add(new Author((String)table.getValueAt(rowIndex, 0)));
						}
						
					}
								
					CommitteeQuery cq = new CommitteeQuery();
					if(!cq.addCommitteeMembers(authorsToBeAddedToDB)){
						String message = "Adding Failed. Author " 
											+ (String)table.getValueAt(table.getSelectedRow(), 0)
											+ " already exists in List!";
						
						JOptionPane jOptionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
						final JDialog dialog = jOptionPane.createDialog(SearchSimilarAuthorUI.this, 
								"Search Similar Author");

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

					} else {
						
						CommitteeQuery comQuery = new CommitteeQuery();
						Main.setCurrentCommittee(comQuery.getUsersCommitteeList());
				
						JOptionPane jOptionPane = new JOptionPane("Successfully added "
								+ (String)table.getValueAt(table.getSelectedRow(), 0)
								+ " to List", JOptionPane.PLAIN_MESSAGE);
						final JDialog dialog = jOptionPane.createDialog(SearchSimilarAuthorUI.this, 
								"Search Similar Author");

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
		
					table.clearSelection();
				}
				else{
					JOptionPane jOptionPane = new JOptionPane("Please select at least 1 Author",
							JOptionPane.ERROR_MESSAGE);
					final JDialog dialog = jOptionPane.createDialog(SearchSimilarAuthorUI.this, "Search Similar Author");

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
		});
		
		JLabel totalRecords = new JLabel("Total Records: " + finalSearchResultsList.size());
		totalRecords.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		panel.add(heading, "align center, wrap");
		panel.add(scrollPane, "grow, push, span");
		panel.add(totalRecords, "align center, wrap");
		
		if(finalSearchResultsList.size() > 0){
			panel.add(addToListButton, "align center, wrap");
		}		
		
		splitPane.setRightComponent(panel);
	}
	
	
	/**
	 * Resize column width of the table which displays the list of authors and their details.
	 *
	 * @param table the table
	 */
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			if(width > 300)
				width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}


class PublicationTitleComparator implements Comparator<Author>{
	@Override
	public int compare(Author author1, Author author2) {
		return author1.getTitle().compareTo(author2.getTitle());
	}

}
