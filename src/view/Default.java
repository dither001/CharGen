package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import adapter.Controller;
import model.TableBuilder;

public class Default extends JPanel {
	private static final long serialVersionUID = 7833894194480819845L;

	//
	private Controller controller;

	//
	private JFileChooser fileChoose;
	private GridBagLayout layout;
	private JTable dataSet;

	//
	private TableRowSorter<TableModel> rowSorter;
	private JTextField selectionField;
	private ListSelectionModel listSelectionModel;

	//

	/*
	 * CONSTRUCTORS
	 */
	public Default(Controller controller) {
		//
		this.controller = controller;

		fileChoose = new JFileChooser();
		layout = new GridBagLayout();

		//
		dataSet = new JTable();

		//
		setupTable();
	}

	/*
	 * INSTANCE METHODS
	 */
	private void setupTable() {
		ResultSet set = null;

		//
		set = controller.getStarData();

		try {
			this.dataSet = new JTable(TableBuilder.worldTable(set));

		} catch (SQLException e) {
			e.printStackTrace();

		}

		dataSet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSorter = new TableRowSorter<>(dataSet.getModel());
		selectionField = new JTextField();

		//
		dataSet.setRowSorter(rowSorter);
		listSelectionModel = dataSet.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
		dataSet.setSelectionModel(listSelectionModel);

		// resetValues();
		removeAll();
		revalidate();
		repaint();
		setupLayout();
		// setUpListeners();

	}

	private void setupLayout() {
		layout.rowHeights = new int[] { 0, 0, 36, 0, 0, 0, 0, 0, 0, 0 };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		layout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		layout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0 };
		setLayout(layout);
		setBorder(new LineBorder(new Color(0, 100, 0), 10));
		setForeground(new Color(105, 105, 105));
		setBackground(new Color(245, 245, 245));

		//
		dataSet.setForeground(Color.BLACK);
		dataSet.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		dataSet.setBackground(new Color(245, 250, 245));
		JTableHeader header = dataSet.getTableHeader();
		header.setReorderingAllowed(false);
		header.setForeground(new Color(0, 100, 0));
		header.setFont(new Font("Arial", Font.PLAIN, 20));
		header.setBackground(new Color(245, 245, 245));
		header.setBorder(new LineBorder(new Color(0, 128, 0), 2));
		// scrollPane = new JScrollPane(dataSet);
		// scrollPane.setViewportBorder(new LineBorder(new Color(0, 100, 0)));
		// scrollPane.getViewport().setForeground(Color.BLACK);
		// scrollPane.getViewport().setFont(new Font("Arial", Font.PLAIN, 20));
		// scrollPane.getViewport().setBackground(new Color(245, 250, 245));
		// scrollPane.setBorder(new LineBorder(new Color(0, 128, 0), 2));

		GridBagConstraints gbc_dataSet = new GridBagConstraints();
		gbc_dataSet.gridheight = 3;
		gbc_dataSet.gridwidth = 10;
		gbc_dataSet.gridy = 7;
		gbc_dataSet.insets = new Insets(0, 20, 20, 20);
		gbc_dataSet.fill = GridBagConstraints.BOTH;
		gbc_dataSet.gridx = 0;

	}

	private void updateValues() {
		// FIXME
		updateFields();
	}

	private void updateFields() {
		// FIXME

	}

	/*
	 * INNER CLASS - SharedListSelectionHandler
	 * 
	 */
	class SharedListSelectionHandler implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel model = (ListSelectionModel) e.getSource();

			if (!model.isSelectionEmpty()) {
				updateValues();

			}
		}
	}

}
