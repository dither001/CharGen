package model;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import adapter.Controller;

public class TableBuilder extends DefaultTableModel {
	private static final long serialVersionUID = -1675752138627589999L;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public TableBuilder(Vector<Vector<Object>> data, Vector<String> columnNames) {
		super(data, columnNames);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public static TableBuilder starTable(ResultSet records) throws SQLException {
		return buildTable(records, 4, Controller.starProfileHeader);
	}

	public static TableBuilder worldTable(ResultSet records) throws SQLException {
		return buildTable(records, 9, Controller.worldProfileHeader);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/*
	 * PRIVATE METHODS
	 */
	private static TableBuilder buildTable(ResultSet records, int columns, String[] headers) throws SQLException {
		Vector<String> columnNames = new Vector<String>();

		for (int i = 0; i < columns; i++) {
			columnNames.add(headers[i]);

		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (records.next()) {

			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columns; columnIndex++) {
				vector.add(records.getObject(columnIndex));

			}

			data.add(vector);
		}

		return new TableBuilder(data, columnNames);
	}

}
