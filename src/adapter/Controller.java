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

		starSystemSetup(0);
		// NUM_OF_SUBSECTORS
		// for (int i = 0; i < 1; ++i)
		// subsectorSetup(0, i);
	}

	private void starSystemSetup(int starsystem) {
		StarSystem starSystem = new StarSystem(0, 0, 0);

		for (Iterator<Star> it = starSystem.starList().iterator(); it.hasNext();)
			database.addStar(starIndex++, it.next());

		for (Iterator<Planetoid> it = starSystem.listWorlds().iterator(); it.hasNext();)
			database.addWorld(worldIndex++, starsystem, it.next());
	}

	// private void subsectorSetup(int sector, int subsector) {
	// StarSystem starSystem;
	//
	// for (int i = 0; i < 80; ++i) {
	// if (Dice.roll(2) == 2) {
	// starSystem = new StarSystem(sector, subsector, i);
	//
	// for (Iterator<Star> it = starSystem.starList().iterator(); it.hasNext();)
	// database.addStar(starIndex++, it.next());
	//
	// for (Iterator<Planetoid> it = starSystem.listWorlds().iterator();
	// it.hasNext();)
	// database.addWorld(worldIndex++, it.next());
	// }
	//
	// }
	//
	// }

}
