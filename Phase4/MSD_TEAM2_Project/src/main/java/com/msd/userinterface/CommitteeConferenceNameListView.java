package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.msd.queryengine.PublicationListQuery;
import com.msd.queryengine.model.AuthorPublications;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

/**
 * The Class CommitteeConferenceNameListView.
 */
public class CommitteeConferenceNameListView extends JFrame{

	/** The commitee conf name list. */
	private List<String> commiteeConfNameList;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The table. */
	private JTable table;
	
	/** The logger. */
	private Logger logger = LogManager.getLogger(CommitteeConferenceNameListView.class);

	/**
	 * Instantiates a new committee conference name list view.
	 */
	public CommitteeConferenceNameListView(){
		PublicationListQuery queryObj = new PublicationListQuery();
		commiteeConfNameList = queryObj.getCommitteeConferenceNameList();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(440, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Committee Conferences");

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(initFields());

		setVisible(true);
	}

	/**
	 * Inits the fields of JPanel for list of conferences for which the committee members are 
	 * associated with
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Name");
		Vector<Vector> data = new Vector<Vector>();
		for (String name : commiteeConfNameList) {
			Vector<String> row = new Vector<String>();
			row.addElement(name);
			data.addElement(row);
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
		table.setRowSelectionAllowed(false);
		resizeColumnWidth(table);

		scrollPane = new JScrollPane(table);

		JLabel totalRecords = new JLabel("Total Conferences: " + commiteeConfNameList.size());
		totalRecords.setFont(new Font("Tahoma", Font.PLAIN, 20));
		CC componentConstraints = new CC();
		componentConstraints.alignX("center").spanX();
		panel.add(totalRecords, componentConstraints);
		panel.add(scrollPane, "grow, push, span");

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
}
