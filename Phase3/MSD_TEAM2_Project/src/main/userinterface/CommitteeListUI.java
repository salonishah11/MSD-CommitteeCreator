package main.userinterface;

import java.awt.BorderLayout;
import main.queryengine.Author;
import main.queryengine.CommitteeQuery;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import net.miginfocom.swing.MigLayout;

public class CommitteeListUI extends JPanel {

	private JFrame parentFrame;
	private User currentUser;
	
	private JScrollPane scrollPane;
	DefaultTableModel tableModel;
	public JTable table;
	private JPanel tablePanel = new JPanel();
	public JButton removeButton = new JButton("Remove");
	public JButton refreshButton = new JButton("Refresh");

	private ArrayList<Author> resultList;

	public CommitteeListUI(){
		parentFrame = Main.getMainFrame();
		currentUser = Main.getCurrentUser();
		resultList = CommitteeQuery.getUsersCommitteeList(currentUser);
		
		Main.setCurrentCommittee(resultList);
		
		setLayout(new BorderLayout(5, 5));
		add(initFields(), BorderLayout.NORTH);
		
//		if(resultList.size() > 0){			
//						
//		}
//		else{
//			JLabel noResults = new JLabel("No Shortlisted Committee Members!!");
//			noResults.setFont(new Font("Tahoma", Font.PLAIN, 40));
//			add(noResults, BorderLayout.NORTH);
//		}
		add(initButtons(), BorderLayout.CENTER);
		
	}

	// method to initialize the table and rows, populate the author records
	private JPanel initFields() {	
		
		if(resultList.size() > 0){		
			Vector<String> columnNames = new Vector<String>();
			columnNames.addElement("Author Name");
			columnNames.addElement("KeyVal");
			columnNames.addElement("Year of Publication");
			columnNames.addElement("Paper Type");

			Vector<Vector> data = new Vector<Vector>();
		
			for (Author a : resultList) {
				Vector<String> authorRow = new Vector<String>();
				authorRow.addElement(a.getName());
				authorRow.addElement(a.getKeyVal());
				authorRow.addElement(String.valueOf(a.getYear()));
				authorRow.addElement(a.getType());
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
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			scrollPane = new JScrollPane(table);

			tablePanel.add(scrollPane);			
		}
		else{
			JLabel noResults = new JLabel("No Shortlisted Committee Members!!");
			noResults.setFont(new Font("Tahoma", Font.PLAIN, 40));
			
			tablePanel.add(noResults, new FlowLayout(FlowLayout.CENTER, 3, 3));
		}
		
		return tablePanel;
	}

	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		removeButton.addActionListener(new ActionListener() {

			// upon selecting the row of the committee table, remove single or multiple authors 
			// from the committee of the current user
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
							JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.ERROR_MESSAGE);

						} else {
							String message = "Removal Successfull";
							JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.PLAIN_MESSAGE);

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
						JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.ERROR_MESSAGE);
					}
					
					
				}

			}
		});
		
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resultList = CommitteeQuery.getUsersCommitteeList(currentUser);							
				Main.setCurrentCommittee(resultList);
				
				tablePanel.removeAll();
				tablePanel.revalidate();
				tablePanel.repaint();
				
				tablePanel = initFields();
				
			}
		});

		panel.add(removeButton);
		panel.add(refreshButton);
		
		return panel;
	}
	
	// method to handle resizing of the table which displays the list of committee members
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
	
	
//	public void setResultList(ArrayList<Author> list){
//		this.resultList = list;
//	}
//
//	
//	public ArrayList<Author> getResultList(){
//		return resultList;
//	}
//	
//	
//	public User getCurrentUser() {
//		return currentUser;
//	}
//
//	public void setCurrentUser(User currentUser) {
//		this.currentUser = currentUser;
//	}
}
