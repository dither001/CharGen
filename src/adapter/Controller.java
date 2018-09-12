package adapter;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.io.File;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.SQLiteData;
import view.Frame;

public class Controller {
	public static JPanel errorPanel;
	public int dataRequested;

	//
	public static String[] worldProfileHeader = { "Name", "Orbit", "Suborbit", "Size", "Atmo", "Hydro%", "Pop", "Gov#", "Law" };
	private SQLiteData database;
	private Frame frame;
	
	public void start() {
		errorPanel = new JPanel();
		dataRequested = 0;
		//
		database = new SQLiteData(this);
		
	}
	
}
