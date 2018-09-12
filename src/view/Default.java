package view;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import adapter.Controller;
import model.WorldTable;

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

		// set = controller.getData();

		try {
			this.dataSet = new JTable(WorldTable.buildTable(set));

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
		// setUpLayout();
		// setUpListeners();

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
