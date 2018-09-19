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

import milieu.*;
import model.SQLiteData;
import rules.Dice;
import view.Frame;

public class Controller {
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

		sectorSetup(0);
	}

	private void sectorSetup(int sector) {
		int starIndex = 0, worldIndex = 0;
		StarSystem group;

		// subsector level
		for (int i = 0; i < 80; ++i) {
			if (Dice.roll(2) == 2) {
				group = new StarSystem(sector, i);

				// system level
				while (group.getMainWorld() == null)
					group = new StarSystem(sector, i);

				for (Iterator<Star> it = group.starList().iterator(); it.hasNext();)
					database.addStar(starIndex++, it.next());
			}

		}

	}

}
