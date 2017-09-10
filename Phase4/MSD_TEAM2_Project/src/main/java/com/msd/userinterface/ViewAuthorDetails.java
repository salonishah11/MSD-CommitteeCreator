package com.msd.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.msd.queryengine.SystemUserQuery;
import com.msd.queryengine.ViewAuthorDetailsQuery;
import com.msd.queryengine.model.Author;
import com.msd.queryengine.model.AuthorCommitteeDetails;
import com.msd.queryengine.model.AuthorPublications;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

/**
 * The Class ViewAuthorDetails.
 */
public class ViewAuthorDetails extends JFrame {

	/** The author name. */
	private String authorName;
	
	/** The authors details result. */
	private List<AuthorPublications> authorsDetailsResult;
	
	/** The authors committee details. */
	private List<AuthorCommitteeDetails> authorsCommitteeDetails;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The table. */
	private JTable table;
	
	/** The search similar author btn. */
	private JButton searchSimilarAuthorBtn = new JButton("Search Similar Authors");

	/** The view committee details button. */
	private JButton viewCommitteeDetailsButton = new JButton("View Details");

	/**
	 * Instantiates a new view author details.
	 *
	 * @param authorName the author name
	 */
	public ViewAuthorDetails(String authorName){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.authorName = authorName;

		ViewAuthorDetailsQuery queryObj = new ViewAuthorDetailsQuery();

		authorsDetailsResult = queryObj.getAuthorPublicationDetails(authorName);
		authorsCommitteeDetails = queryObj.getAuthorCommitteeDetails(authorName);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		Rectangle bounds = gc.getBounds();
		setSize(1000, bounds.height-50);
		setResizable(false);
		setLocationRelativeTo(null);

		setTitle("Author Details");

		setLayout(new BorderLayout());
		add(initFields());

		setVisible(true);
	}


	/**
	 * Inits the fields.
	 *
	 * @return the j panel
	 */
	private JPanel initFields() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill"));

		viewCommitteeDetailsButton.setVisible(false);
		viewCommitteeDetailsButton.setEnabled(false);

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

		JLabel heading = new JLabel("Various Publications by Author: " + authorName);
		heading.setFont(new Font("Tahoma", Font.BOLD, 24));

		JLabel numOfPublications = new JLabel("Number of Publications: " + authorsDetailsResult.size());
		numOfPublications.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel servedInCommitteeBefore = new JLabel("Served in Committee Before: " 
				+ ((authorsCommitteeDetails.size() > 0) ? "Yes" : "No"));
		servedInCommitteeBefore.setFont(new Font("Tahoma", Font.PLAIN, 24));

		viewCommitteeDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		Box committeeBox = Box.createHorizontalBox();
		committeeBox.add(servedInCommitteeBefore);
		committeeBox.add(Box.createRigidArea(new Dimension(15, 0)));
		committeeBox.add(viewCommitteeDetailsButton);

		if(authorsCommitteeDetails.size() > 0){
			viewCommitteeDetailsButton.setVisible(true);
			viewCommitteeDetailsButton.setEnabled(true);
		}

		viewCommitteeDetailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AuthorCommitteeDetailsView(authorName, authorsCommitteeDetails);

			}
		});

		panel.add(heading, "align center, wrap");
		panel.add(numOfPublications, "wrap");
		panel.add(committeeBox, "wrap");
		panel.add(scrollPane, "grow, push, span");

		searchSimilarAuthorBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchSimilarAuthorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchSimilarAuthorUI(authorName);

			}
		});

		panel.add(searchSimilarAuthorBtn, "align center");

		return panel;
	}


	/**
	 * Inits the buttons.
	 *
	 * @return the j panel
	 */
	private JPanel initButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

		searchSimilarAuthorBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchSimilarAuthorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchSimilarAuthorUI(authorName);

			}
		});

		panel.add(searchSimilarAuthorBtn);

		return panel;
	}


	/**
	 * Resize column width of the table which displays the details of author.
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
