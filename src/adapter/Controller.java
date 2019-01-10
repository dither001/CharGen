package adapter;

/*
 * Adapted from code by Ariana Fairbanks
 * 
 */

import java.io.File;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.norvendae.misc.Dice;

import model.SQLiteData;
import view.Frame;

public class Controller {
	private static final int NUM_OF_SUBSECTORS = 16;
	private static int starIndex = 0, worldIndex = 0;

	public static JPanel errorPanel;
	public int dataRequested;

	//
	public static String[] starProfileHeader = { "Name", "Orbit", "Size", "Color" };
	public static String[] worldProfileHeader = { "Name", "Orbit", "Suborbit", "Size", "Atmo", "Hydro%", "Pop", "Gov#",
			"Law" };
	private SQLiteData database;
	private Frame frame;

	/*
	 * INSTANCE METHODS
	 */
	public void start() {
		errorPanel = new JPanel();
		dataRequested = 0;
		//

		//
		System.out.println("Adding stars.");
		databaseSetup();
		System.out.println("Finished stars.");

		/*
		 * So, this creates the visible frame that can be interacted with by a user
		 */
		// frame = new Frame(this);

	}

	public ResultSet getStarData() {
		return database.getStarData();
	}

	private void databaseSetup() {
		database = new SQLiteData(this);

		database.addSubsector(0);
	}

}
