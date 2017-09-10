package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msd.queryengine.CommitteeQuery;
import com.msd.queryengine.model.Author;
import com.msd.queryengine.model.AuthorCommitteeDetails;
import com.msd.queryengine.model.AuthorPublications;

import net.miginfocom.swing.MigLayout;

/**
 * The Class CommitteeListUI.
 */
public class CommitteeListUI extends JPanel {

	/** The parent frame. */
	private JFrame parentFrame;
	
	/** The current user. */
	private User currentUser;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The table model. */
	private DefaultTableModel tableModel;
	
	/** The table. */
	public JTable table;
	
	/** The table panel. */
	private JPanel tablePanel = new JPanel();
	
	/** The remove button. */
	public JButton removeButton = new JButton("Remove");
	
	/** The refresh button. */
	public JButton refreshButton = new JButton("Refresh");
	
	/** The view author details button. */
	private JButton viewAuthorDetailsButton = new JButton("View Details");
	
	/** The split pane. */
	JSplitPane splitPane;
	
	/** The logger. */
	private Logger logger = LogManager.getLogger(CommitteeListUI.class);
	
	/** The result list. */
	private static ArrayList<Author> resultList;

	/**
	 * Instantiates a new committee list UI.
	 */
	public CommitteeListUI(){
		parentFrame = Main.getMainFrame();
		currentUser = Main.getCurrentUser();
		CommitteeQuery comQuery = new CommitteeQuery();
		resultList = comQuery.getUsersCommitteeList();

		Main.setCurrentCommittee(resultList);
		setLayout(new MigLayout("fill"));

		Box committeeBox = Box.createVerticalBox();
		committeeBox.add(initFields());
		committeeBox.add(initButtons());
		committeeBox.add(Box.createRigidArea(new Dimension(0, 10)));

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(committeeBox);
		splitPane.setRightComponent(displayInitialStatistcsMessage());
		splitPane.setDividerLocation(0.8);
		splitPane.setEnabled(false);

		add(splitPane, "grow, push, span");
	}

	/**
	 * Inits the fields on JPanel for view for list of committee authors
	 *
	 * @return the j panel
	 */
	// method to initialize the table and rows, populate the author records
	private JPanel initFields() {	

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Author Name");
		Vector<Vector> data = new Vector<Vector>();

		for (Author a : resultList) {
			Vector<String> authorRow = new Vector<String>();
			authorRow.addElement(a.getName());
			data.addElement(authorRow);
		}

		tableModel = new DefaultTableModel(data, columnNames); 
		table = new JTable(tableModel){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 24));
		tablePanel.setLayout(new BorderLayout(0, 0));
		table.setRowHeight(table.getRowHeight() + 10);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resizeColumnWidth(table);
		scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);			
	
		return tablePanel;
	}

	/**
	 * Inits the buttons on the Commitee LIst UI panel
	 * Upon selecting the row of the committee table, remove single or multiple
	 * authors from the committee of the current user
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRows() != null){

					List<Author> authorsToBeRemovedFromDB = new ArrayList<Author>();
					for (int rowIndex : table.getSelectedRows()) {
						authorsToBeRemovedFromDB.add(resultList.get(rowIndex));
						tableModel.removeRow(rowIndex);
					}

					if(authorsToBeRemovedFromDB.size() > 0){
						//Remove it from DB
						CommitteeQuery cq = new CommitteeQuery();
						if(!cq.removeMembers(authorsToBeRemovedFromDB)){
							String message = "Removal Failed";
							JOptionPane jOptionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
							final JDialog dialog = jOptionPane.createDialog(parentFrame, "Shortlist Committee List");

						      new SwingWorker<Void, Void>() {

						         @Override
						         protected Void doInBackground() throws Exception {
						            Thread.sleep(3000); 

						            return null;
						         }

						         // this is called when background thread above has completed.
						         protected void done() {
						            dialog.dispose();
						         };
						      }.execute();

						      dialog.setVisible(true);

						} else {
							String message = "Removal Successful";
							JOptionPane jOptionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
							final JDialog dialog = jOptionPane.createDialog(parentFrame, "Shortlist Committee List");

						      new SwingWorker<Void, Void>() {

						         @Override
						         protected Void doInBackground() throws Exception {
						            Thread.sleep(3000); 

						            return null;
						         }

						         // this is called when background thread above has completed.
						         protected void done() {
						            dialog.dispose();
						         };
						      }.execute();

						      dialog.setVisible(true);
						}

						//remove from resultlist
						for(Author a: authorsToBeRemovedFromDB){
							resultList.remove(a);
						}
						Main.setCurrentCommittee(resultList);
						table.clearSelection();
					}
					else{
						String message = "Please select 1 Author to remove!";
						JOptionPane jOptionPane= new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
						final JDialog dialog = jOptionPane.createDialog(parentFrame, "SHortlist Committee List");

					      new SwingWorker<Void, Void>() {

					         @Override
					         protected Void doInBackground() throws Exception {
					            Thread.sleep(3000); 

					            return null;
					         }

					         // this is called when background thread above has completed.
					         protected void done() {
					            dialog.dispose();
					         };
					      }.execute();

					      dialog.setVisible(true);
					}
				}
			}
		});

		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommitteeQuery comQuery = new CommitteeQuery();
				resultList = comQuery.getUsersCommitteeList();							
				Main.setCurrentCommittee(resultList);

				tablePanel.removeAll();
				tablePanel.revalidate();
				tablePanel.repaint();
				tablePanel = initFields();
			}
		});

		viewAuthorDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		viewAuthorDetailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					renderStatistics(resultList.get(table.getSelectedRow()).getName());
				}
				else{
					JOptionPane jOptionPane = new JOptionPane("Please select at least 1 Author to view",
							JOptionPane.ERROR_MESSAGE);
					final JDialog dialog = jOptionPane.createDialog(parentFrame, "Shortlist Committee List");

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

		panel.add(removeButton);
		panel.add(refreshButton);
		panel.add(viewAuthorDetailsButton);

		return panel;
	}


	/**
	 * Display initial statistcs message.
	 *
	 * @return the j panel
	 */
	private JPanel displayInitialStatistcsMessage(){
		JPanel statisticsPanel = new JPanel();
		statisticsPanel.setLayout(new FlowLayout());

		JLabel label = new JLabel("Please select an author to View Statistics");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		statisticsPanel.add(label);

		return statisticsPanel;
	}


	/**
	 * Render statistics.
	 *
	 * @param authorName the author name
	 */
	private void renderStatistics(String authorName) {

		JSplitPane statisticsPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		statisticsPane.setTopComponent(renderPublicationStatistics(authorName));
		statisticsPane.setBottomComponent(renderCommitteeStatistics(authorName));
		statisticsPane.setDividerLocation(0.8);
		statisticsPane.setEnabled(false);
		statisticsPane.setResizeWeight(.5d);

		splitPane.setRightComponent(statisticsPane);
	}


	/**
	 * Render publication statistics.
	 *
	 * @param authorName the author name
	 * @return the j panel
	 */
	private JPanel renderPublicationStatistics(String authorName) {
		List<AuthorPublications> authorsDetailsResult = fetchData(authorName);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Publication Name");
		columnNames.addElement("Publication Type");
		columnNames.addElement("Title");
		columnNames.addElement("Year");

		Vector<Vector> data = new Vector<Vector>();
		for (AuthorPublications publication : authorsDetailsResult) {
			Vector<String> row = new Vector<String>();
			row.addElement(publication.getPublicationName());
			row.addElement(publication.getPublicationType());
			row.addElement(publication.getTitle());
			row.addElement(String.valueOf(publication.getYear()));
			data.addElement(row);
		}

		JTable pubTable = new JTable(data, columnNames){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		pubTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pubTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 24));
		pubTable.setRowHeight(pubTable.getRowHeight() + 10);
		pubTable.setFillsViewportHeight(true);
		resizeColumnWidth(pubTable);
		pubTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane pubScrollPane = new JScrollPane(pubTable);
		JLabel heading = new JLabel("Various Publications by Author: " + authorName);
		heading.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel numOfPublications = new JLabel("Number of Publications: " + authorsDetailsResult.size());
		numOfPublications.setFont(new Font("Tahoma", Font.PLAIN, 24));

		panel.add(heading, "align center, wrap");
		panel.add(numOfPublications, "wrap");
		panel.add(pubScrollPane, "grow, push, span");

		return panel;
	}


	/**
	 * Render committee statistics.
	 *
	 * @param authorName the author name
	 * @return the j panel
	 */
	private JPanel renderCommitteeStatistics(String authorName) {
		List<AuthorCommitteeDetails> authorsCommitteeDetails = servedInCommitteeDetails(authorName);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		if(authorsCommitteeDetails.size() > 0){
			Vector<String> columnNames = new Vector<String>();
			columnNames.addElement("Conference Name");
			columnNames.addElement("Year");

			Vector<Vector> data = new Vector<Vector>();
			for (AuthorCommitteeDetails details : authorsCommitteeDetails) {
				Vector<String> row = new Vector<String>();
				row.addElement(details.getConferenceName());
				row.addElement(String.valueOf(details.getYear()));
				data.addElement(row);
			}

			JTable commiteeTable = new JTable(data, columnNames){
				public boolean isCellEditable(int row, int column){
					return false;
				}
			};
			commiteeTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
			commiteeTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 24));
			commiteeTable.setRowHeight(commiteeTable.getRowHeight() + 10);
			commiteeTable.setFillsViewportHeight(true);
			resizeColumnWidth(commiteeTable);
			commiteeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane committeeScrollPane = new JScrollPane(commiteeTable);
			JLabel heading = new JLabel(authorName + " served in following Committees");
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));
			JLabel numOfCommittees = new JLabel("Served in " + authorsCommitteeDetails.size() + " Committees");
			numOfCommittees.setFont(new Font("Tahoma", Font.PLAIN, 24));

			panel.add(heading, "align center, wrap");
			panel.add(numOfCommittees, "wrap");
			panel.add(committeeScrollPane, "grow, push, span");
		}
		else{
			JLabel heading = new JLabel(authorName + " served in 0 Committees");
			heading.setFont(new Font("Tahoma", Font.BOLD, 24));

			panel.add(heading, "align center, wrap");
		}		

		return panel;
	}

	/**
	 * Resize column width of the table which displays the list of committee members
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

	/**
	 * Fetch list of all the publications of authors present in the committee 
	 * Searches in journals, conferences and thesis
	 *
	 * @param authorName the author name
	 * @return the list
	 */
	public List<AuthorPublications> fetchData(String authorName) {
		String query = "SELECT jp.event_name, jp.title, jp.year, jp.type" 
				+ " FROM dblp_full.author a" 
				+ " join dblp_full.journalauthor ja on a.id = ja.authorID"
				+ " join dblp_full.journalpaper jp on ja.paperid = jp.id"
				+ " where a.name = '"+ authorName +"'"
				+ " UNION"
				+ " select cp.event_name, cp.title, cp.year, cp.type"
				+ " from dblp_full.author a"
				+ " join dblp_full.conferenceauthor c on a.id = c.authorId"
				+ " join dblp_full.conferencepaper cp on cp.id = c.paperid"
				+ " where a.name = '"+ authorName +"'"
				+ " UNION"
				+ " select null, t.title, t.year, t.type"
				+ " from dblp_full.author a"
				+ " join dblp_full.thesisauthor ta on a.id = ta.authorID"
				+ " join dblp_full.thesis t on t.id = ta.paperid"
				+ " where a.name = '"+ authorName +"';";	

		Connection conn = getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			ArrayList<AuthorPublications> resultantList = new ArrayList<AuthorPublications>();
			while (rs.next()) {
				String pubName = rs.getString(1);
				String title = rs.getString(2);
				int year = rs.getInt(3);
				String pubType = rs.getString(4);

				AuthorPublications row = new AuthorPublications(pubName, title, year, pubType);
				resultantList.add(row);
			}

			return resultantList;
		} catch (SQLException e) {
			logger.error("Failed to fetch committee data");
		}

		return null;
	}


	/**
	 * If author has served in committee before,
	 * fetch details of committees where the author served previously.
	 * 
	 * @param authorName the author name
	 * @return the list
	 */
	public List<AuthorCommitteeDetails> servedInCommitteeDetails(String authorName){
		String query = "SELECT com.conferenceName, com.year "
				+ " FROM dblp_full.committee com "
				+ " where memberName = '" + authorName + "';";

		Connection conn = getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			ArrayList<AuthorCommitteeDetails> resultantList = new ArrayList<AuthorCommitteeDetails>();
			while (rs.next()) {
				String name = rs.getString(1);
				int year = rs.getInt(2);

				AuthorCommitteeDetails row = new AuthorCommitteeDetails(name, year);
				resultantList.add(row);
			}
			return resultantList;
		}
		catch (SQLException e) {
			logger.error("Failed to retrieve author's served in committee details");
		}

		return null;
	}


	/**
	 * Gets the connection to the database
	 *
	 * @return the connection
	 */
	private Connection getConnection() {
		Connection conn = null;

		if (conn == null) {
			try {
				String url = "jdbc:mysql://ec2-184-73-110-234.compute-1.amazonaws.com:3306/dblp_full";
				String user = "appuser";
				String password = "pass123";
				conn = getSpecificConnection(url, user, password);
				Statement stmnt = conn.createStatement();
				stmnt.executeUpdate("SET sql_mode=''");

			} catch (Exception e) {
				logger.error("Failed with some exception while getconnection " + e.getMessage());
			}
		}
		return conn;
	}


	/**
	 * Gets the connection to the database using the db.properties file
	 *
	 * @param url the url
	 * @param user the user
	 * @param password the password
	 * @return the specific connection
	 */
	private Connection getSpecificConnection(String url, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.error("JDBC connection to mysql failed with error: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Failed with some exception while getconnection " + e.getMessage());
		}
		return null;
	}
}