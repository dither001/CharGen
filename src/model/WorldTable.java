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

public class WorldTable extends DefaultTableModel {
	private static final long serialVersionUID = -1675752138627589999L;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public WorldTable(Vector<Vector<Object>> data, Vector<String> columnNames) {
		super(data, columnNames);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public static WorldTable buildTable(ResultSet records) throws SQLException {
		Vector<String> columnNames = new Vector<String>();
		int columnCount = 5;

		for (int column = 0; column < columnCount; column++) {
			columnNames.add(Controller.worldProfileHeader[column]);

		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (records.next()) {

			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(records.getObject(columnIndex));

			}

			data.add(vector);
		}

		return new WorldTable(data, columnNames);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
