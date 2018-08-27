package vessel;

import rules.Dice;

public class Spaceship {
	public enum Config {
		NEEDLE, WEDGE, CONE, CYLINDER, CLOSED, SPHERE, FLATTENED_SPHERE, DISTRIBUTED, PLANETOID, BUFFERED_PLANETOID
	}

	private static int[] HULL_TYPES = { //
			99, // small craft
			100, 200, 400, 600, 800, 1000, // 6 - standard designs
			2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, // 9 - frigate
			20000, 30000, 40000, 50000, 75000, 100000, // 6 - destroyer
			200000, 300000, 400000, 500000, 700000, 900000, 1000000 }; // 7 - cruiser

	/*
	 * INSTANCE FIELDS
	 */
	private int techLevel;
	private Config configuration;
	private boolean streamlined;

	// bridge
	private int hull;
	private Bridge bridge;

	// engineering
	private MDrive.Instance mdrive;

	// computer
	// crew

	// armor

	/*
	 * CONSTRUCTORS
	 */
	public Spaceship(int techLevel, int hull, Config config, Bridge bridge, MDrive.Instance mdrive) {
		this.techLevel = techLevel;

		//
		this.hull = hull;
		this.configuration = config;

		//
		this.bridge = bridge;

		// engineering
		this.mdrive = mdrive;
	}

	/*
	 * INSTANCE METHODS
	 */
	@Override
	public String toString() {
		return String.format("TL-%-2d %d-ton %s", techLevel, hull, configuration);
	}

	public String toStringDetailed() {
		String string = String.format("TL-%-2d %d-ton %s", techLevel, hull, configuration);

		// bridge
		string += "\n" + bridge.toString();
		// mdrive
		string += (mdrive != null) ? "\n" + mdrive.toString() : "";

		return string;
	}

	/*
	 * STATIC METHODS
	 */
	public static Spaceship randomSpaceship() {
		int techLevel = randomTechLevel();

		//
		int hull = randomHull();
		Config config = randomConfiguration(techLevel);

		//
		Bridge bridge = new Bridge(hull);

		//
		MDrive.Instance mdrive = MDrive.minimumDrive(hull, 1);

		return new Spaceship(techLevel, hull, config, bridge, mdrive);
	}

	private static int randomTechLevel() {
		int dice = Dice.roll(100), techLevel = 0;

		if (dice < 56) {
			if (Dice.roll(2) == 1)
				techLevel = 7;
			else
				techLevel = 8;

		} else if (dice < 86) {
			dice = Dice.roll(4);

			if (dice == 1)
				techLevel = 9;
			else if (dice == 2)
				techLevel = 10;
			else if (dice == 3)
				techLevel = 11;
			else
				techLevel = 12;

		} else if (dice < 96) {
			if (Dice.roll(2) == 1)
				techLevel = 7;
			else
				techLevel = 8;

		} else
			techLevel = 14 + Dice.roll(3);

		return techLevel;
	}

	private static int randomHull() {
		int[][] array = { { 100, 200, 400, 600, 800, 1000 }, //
				{ 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 }, //
				{ 20000, 30000, 40000, 50000, 75000, 100000 }, //
				{ 200000, 300000, 400000, 500000, 700000, 900000, 1000000 } };
		int dice = Dice.roll(100), hull = 0;

		if (dice < 56)
			hull = array[0][Dice.roll(array[0].length) - 1];
		else if (dice < 86)
			hull = array[1][Dice.roll(array[1].length) - 1];
		else if (dice < 96)
			hull = array[2][Dice.roll(array[2].length) - 1];
		else
			hull = array[3][Dice.roll(array[3].length) - 1];

		return hull;
	}

	private static Config randomConfiguration() {
		return randomConfiguration(0);
	}

	private static Config randomConfiguration(int techLevel) {
		Config config;
		int dice = Dice.roll(100) + techLevel;

		if (dice < 41) {
			if (Dice.roll(2) == 1)
				config = Config.PLANETOID;
			else
				config = Config.BUFFERED_PLANETOID;
		} else if (dice < 56)
			config = Config.DISTRIBUTED;
		else if (dice < 66)
			config = Config.CLOSED;
		else if (dice < 76)
			config = Config.SPHERE;
		else if (dice < 86)
			config = Config.FLATTENED_SPHERE;
		else if (dice < 91)
			config = Config.CYLINDER;
		else if (dice < 96)
			config = Config.CONE;
		else {
			if (Dice.roll(2) == 1)
				config = Config.NEEDLE;
			else
				config = Config.WEDGE;
		}

		return config;
	}

	private static double percentBatteries(int hull) {
		double percent = 0.0;

		if (hull < 20000)
			percent = 1.0;
		else if (hull < 30000)
			percent = 0.95;
		else if (hull < 40000)
			percent = 0.9;
		else if (hull < 50000)
			percent = 0.85;
		else if (hull < 75000)
			percent = 0.8;
		else if (hull < 100000)
			percent = 0.75;
		else if (hull < 200000)
			percent = 0.7;
		else if (hull < 300000)
			percent = 0.65;
		else if (hull < 400000)
			percent = 0.6;
		else if (hull < 500000)
			percent = 0.55;
		else
			percent = 0.5;

		return percent;
	}

}
