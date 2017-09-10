package com.msd.userinterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.msd.queryengine.CommitteeQuery;
import com.msd.queryengine.model.Author;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

/**
 * The Class SearchResults.
 */
public class SearchResults extends JPanel {

	/** The parent frame. */
	private JFrame parentFrame;

	/** The authors search result. */
	private List<Author> authorsSearchResult;

	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The table. */
	private JTable table;
	
	/** The add to list button. */
	private JButton addToListButton = new JButton("Shortlist Author");
	
	/** The view author details button. */
	private JButton viewAuthorDetailsButton = new JButton("View Author Details");
	
	/**
	 * Instantiates a new search results for displaying list of authors.
	 *
	 * @param resultList the result list
	 */
	public SearchResults(List<Author> resultList){
		this.parentFrame = Main.getMainFrame();

		this.authorsSearchResult = resultList;
		if(resultList.size() > 0){
			setLayout(new MigLayout("align 50% 50%"));
			add(initFields(resultList), "wrap");
			add(initButtons(), "dock south");
		}else{
			JLabel noResults = new JLabel("No Records Found!!");
			noResults.setFont(new Font("Tahoma", Font.PLAIN, 40));
			add(noResults, new FlowLayout(FlowLayout.CENTER, 3, 3));
		}
	}

	/**
	 * Inits the buttons.
	 * On selecting a row in the results table, add the selected authors 
	 * to the list of authors being selected for committee and also add them to the shortlistcommittee
	 * in the database only if the author does'nt already exist in the database.
	 * 
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		addToListButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		addToListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					ArrayList<Author> authorsToBeAddedToDB = new ArrayList<Author>();
					for (int rowIndex : table.getSelectedRows()) {
						if(!Main.getCurrentCommittee().contains(authorsSearchResult.get(rowIndex))){
							authorsToBeAddedToDB.add(authorsSearchResult.get(rowIndex));
						}
						
					}
					
					CommitteeQuery cq = new CommitteeQuery();
					if(!cq.addCommitteeMembers(authorsToBeAddedToDB)){
						String message = "Adding Failed. Author " 
											+ authorsSearchResult.get(table.getSelectedRow()).getName()
											+ " already exists in List!";
						JOptionPane jOptionPane =new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
						runDisposeInBackground(jOptionPane);
						
					} else {
						CommitteeQuery comQuery = new CommitteeQuery();
						Main.setCurrentCommittee(comQuery.getUsersCommitteeList());
										
						JOptionPane jOptionPane = new JOptionPane("Successfully added "
								+ authorsSearchResult.get(table.getSelectedRow()).getName()
								+ " to List",
								JOptionPane.PLAIN_MESSAGE);
						runDisposeInBackground(jOptionPane);
					}
		
					table.clearSelection();
				}
				else{
					
					JOptionPane jOptionPane = new JOptionPane("Please select at least 1 Author",
							JOptionPane.ERROR_MESSAGE);
					runDisposeInBackground(jOptionPane);
				}

			}
		});
		
		viewAuthorDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		viewAuthorDetailsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					new ViewAuthorDetails(authorsSearchResult.get(table.getSelectedRow()).getName());
				}
				else{
					JOptionPane jOptionPane = new JOptionPane("Please select at least 1 Author to view",
							JOptionPane.ERROR_MESSAGE);
					runDisposeInBackground(jOptionPane);
				}
			}
		});
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(addToListButton);
		buttonBox.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonBox.add(viewAuthorDetailsButton);

		panel.add(buttonBox);
		return panel;
	}

	/**
	 * Inits the table to display the list of authors fetched by executing the search query
	 *
	 * @param resultList the result list
	 * @return the j panel
	 */
	private JPanel initFields(List<Author> resultList) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("align 50% 50%"));

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Author Name");

		Vector<Vector> data = new Vector<Vector>();
		for (Author a : resultList) {
			Vector<String> authorRow = new Vector<String>();
			authorRow.addElement(a.getName());
			data.addElement(authorRow);
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

		scrollPane = new JScrollPane(table);
		
		JLabel totalRecords = new JLabel("Total Records: " + resultList.size());
		totalRecords.setFont(new Font("Tahoma", Font.PLAIN, 20));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		panel.add(scrollPane, "wrap");
		panel.add(totalRecords, componentConstraints);

		return panel;
	}
	
	/**
	 * Resize column width of the resultant table based on the width of the data in various columns.
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
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	
	/**
	 * Gets the adds the to list button.
	 *
	 * @return the adds the to list button
	 */
	public JButton getAddToListButton() {
		return addToListButton;
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	public void runDisposeInBackground(JOptionPane jOptionPane){
		final JDialog dialog = jOptionPane.createDialog(parentFrame, "Search Results");

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
