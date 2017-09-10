package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.msd.queryengine.model.AuthorCommitteeDetails;
import com.msd.queryengine.model.AuthorPublications;

import net.miginfocom.swing.MigLayout;

/**
 * The Class AuthorCommitteeDetailsView.
 */
public class AuthorCommitteeDetailsView extends JFrame {

	/** The author name. */
	private String authorName;
	
	/** The authors committee details. */
	private List<AuthorCommitteeDetails> authorsCommitteeDetails;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new author committee details view.
	 *
	 * @param authorName the author name
	 * @param resultList the result list of authors in the committee
	 */
	public AuthorCommitteeDetailsView(String authorName, List<AuthorCommitteeDetails> resultList){
		this.authorName = authorName;
		this.authorsCommitteeDetails = resultList;
		setSize(700, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Committee Details");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(initFields());
		setVisible(true);
	}

	/**
	 * Inits the fields of the JPanel for AuthorCommitteeDetails view
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

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
		table.setRowSelectionAllowed(false);

		scrollPane = new JScrollPane(table);

		JLabel heading = new JLabel(authorName + " served in following Committees");
		heading.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel numOfCommittees = new JLabel("Served in " + authorsCommitteeDetails.size() + " Committees");
		numOfCommittees.setFont(new Font("Tahoma", Font.PLAIN, 24));

		panel.add(heading, "align center, wrap");
		panel.add(numOfCommittees, "wrap");
		panel.add(scrollPane, "grow, push, span");

		return panel;
	}

	/**
	 * Resize table which displays the list of committee members
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
