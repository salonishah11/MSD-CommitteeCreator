package main.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import javafx.scene.shape.Box;
import main.queryengine.Author;
import main.queryengine.CommitteeQuery;
import net.miginfocom.swing.MigLayout;

public class SearchResults extends JPanel {

	private JFrame parentFrame;

	private List<Author> authorsSearchResult;

	private JScrollPane scrollPane;
	private JTable table;
	private JButton addToListButton = new JButton("Add to List");

	
	// method to initialize the UI for displaying search results (list of authors)
	public SearchResults(List<Author> resultList){
		this.parentFrame = Main.getMainFrame();

		this.authorsSearchResult = resultList;
		if(resultList.size() > 0){
			setLayout(new BorderLayout(5, 5));
			
			add(initFields(resultList), BorderLayout.NORTH);
			add(initButtons(), BorderLayout.CENTER);
		}else{
			JLabel noResults = new JLabel("No Records Found!!");
			noResults.setFont(new Font("Tahoma", Font.PLAIN, 40));
			add(noResults, new FlowLayout(FlowLayout.CENTER, 3, 3));
		}
	
	}

	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		addToListButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		addToListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// upon selecting a row in the results table, add the selected authors 
				// to the list of authors being selected for committee
				// and all add them to the shortlistcommittee table in the database only if the author doesnt
				// exist in the database already
				if(table.getSelectedRows() != null){
					ArrayList<Author> authorsToBeAddedToDB = new ArrayList<Author>();
					for (int rowIndex : table.getSelectedRows()) {
						if(!Main.getCurrentCommittee().contains(authorsSearchResult.get(rowIndex))){
							authorsToBeAddedToDB.add(authorsSearchResult.get(rowIndex));
						}
						
					}
					//Add it to DB
					// add it to currentCommitteeList
				
					CommitteeQuery cq = new CommitteeQuery();
					if(!cq.addCommitteeMembers(authorsToBeAddedToDB)){
						String message = "Adding Failed. One/Many of the Authors already exists in List!";
						JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.ERROR_MESSAGE);
						
					} else {
						Main.setCurrentCommittee(authorsToBeAddedToDB);
						JOptionPane.showMessageDialog(SearchResults.this,
								"Successfully added to List",
								"Success!!",
								JOptionPane.OK_OPTION);		
						
					}
		
					table.clearSelection();
				}

			}
		});

		panel.add(addToListButton);

		return panel;
	}

	// method to initialize the table to display the list of authors fetched by exeuting the search query
	private JPanel initFields(List<Author> resultList) {
		JPanel panel = new JPanel();

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Author Name");
		columnNames.addElement("KeyVal");
		columnNames.addElement("Paper Title");
		columnNames.addElement("Year of Publication");
		columnNames.addElement("Paper Type");

		int i = 10;
		Vector<Vector> data = new Vector<Vector>();
		for (Author a : resultList) {
			Vector<String> authorRow = new Vector<String>();
			authorRow.addElement(a.getName());
			authorRow.addElement(a.getKeyVal());
			authorRow.addElement(a.getTitle());
			authorRow.addElement(String.valueOf(a.getYear()));
			authorRow.addElement(a.getType());
			data.addElement(authorRow);
		}

		table = new JTable(data, columnNames){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setLayout(new BorderLayout(0, 0));
		table.setRowHeight(table.getRowHeight() + 10);
		table.setFillsViewportHeight(true);
		resizeColumnWidth(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.setAutoCreateRowSorter(true);

		scrollPane = new JScrollPane(table);

		panel.add(scrollPane);

		return panel;
	}
	
	// method which handles the rezing of the resultant table based on the width of the data in varous columns
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
	
	
	public JButton getAddToListButton() {
		return addToListButton;
	}

	public JTable getTable() {
		return table;
	}

}
