package vessel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MDrive {
	private static List<Prototype> prototypes;

	private static int[] HULL_TYPES = { //
			99, // small craft
			100, 200, 400, 600, 800, 1000, // 6 - standard designs
			2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, // 9 - frigate
			20000, 30000, 40000, 50000, 75000, 100000, // 6 - destroyer
			200000, 300000, 400000, 500000, 700000, 900000, 1000000 }; // 7 - cruiser

	/*
	 * INITIALIZATION
	 */
	static {
		prototypes = new ArrayList<Prototype>();

		for (int i = 1; i < HULL_TYPES.length; ++i) {
			for (int j = 1; j <= 6; ++j) {
				prototypes.add(new Prototype(HULL_TYPES[i], j));

			}
		}

	}

	/*
	 * INNER CLASS - PROTOTYPE
	 */
	public static class Prototype {
		private static double[] percent = { 0.02, 0.05, 0.08, 0.11, 0.14, 0.17 };

		// FIELDS
		private int potential;
		private int displacement;

		// CONSTRUCTOR
		public Prototype(int hull, int potential) {
			this.potential = potential;

			this.displacement = (int) (hull * percent[potential - 1]);
		}
	}

	/*
	 * INNER CLASS - INSTANCE
	 */
	public static class Instance {
		private Prototype prototype;

		public Instance(Prototype prototype) {
			this.prototype = prototype;
		}

		@Override
		public String toString() {
			String potential = "" + prototype.potential;
			String displacement = "" + prototype.displacement;

			return String.format("Maneuver-%s (%s tons)", potential, displacement);
		}
	}

	/*
	 * STATIC METHODS
	 */
	public static int costEstimator(int hull, int potential) {
		double[] multiplier = { 1.5, 0.7, 0.5, 0.5, 0.5, 0.5 };

		return (int) (hull * multiplier[potential - 1]);
	}

	public static MDrive.Instance minimumDrive(int hull, int potential) {
		Prototype current, last = null;
		for (Iterator<Prototype> it = prototypes.iterator(); it.hasNext();) {
			current = it.next();

			if (current.displacement == hull && current.potential == potential) {
				last = current;
				break;
			}
		}

		if (last == null)
			return null;

		return new Instance(last);
	}

	public static void prototypePrint() {
		String output;

		Prototype current;
		Instance drive;
		int cost;
		for (Iterator<Prototype> it = prototypes.iterator(); it.hasNext();) {
			current = it.next();
			cost = costEstimator(current.displacement, current.potential);

			drive = new Instance(current);
			output = String.format("%s %d MCr", drive.toString(), cost);
			System.out.println(output);
		}

		System.out.println("Total prototypes: " + prototypes.size());
	}
}
