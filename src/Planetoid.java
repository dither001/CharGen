import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class Planetoid implements World {
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private EnumSet<TradeCodes> tradeCodes;
	private EnumSet<Tag> worldTags;
	private EnumSet<Base> worldFacilities;

	private Group group;
	private Planetoid home;
	private Type type;
	private int orbit;

	private byte[] scores;
	private char spaceport;
	private byte techLevel;
	private List<Planetoid> moons;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Planetoid(int orbit, Group group) {
		this(Type.STANDARD, orbit, group);
	}

	public Planetoid(Type type, int orbit, Group group) {
		this(type, orbit, group, null);
	}

	public Planetoid(Type type, int orbit, Group group, Planetoid home) {
		this.group = group;
		this.home = home;
		this.type = type;
		this.orbit = orbit;

		/*
		 * INITIALIZE NEW WORLD
		 */
		if (isWorld()) {
			int habitableZone = group.getHabitableZone();
			this.scores = new byte[] { 0, 0, 0, 0, 0, 0 };

			/*
			 * SIZE
			 */
			scores[0] = (byte) (Dice.roll(2, 6) - 2);
			if (orbit == 0)
				scores[0] -= 5;
			else if (orbit == 1)
				scores[0] -= 4;
			else if (orbit == 1)
				scores[0] -= 2;

			if (group.getPrimaryStarColor() == 'M')
				scores[0] -= 2;

			if (isMoon() && home.largeGiant())
				scores[0] = (byte) (Dice.roll(2, 6) - 4);
			else if (isMoon() && home.smallGiant())
				scores[0] = (byte) (Dice.roll(2, 6) - 6);
			else if (isMoon())
				scores[0] = (byte) (home.getSize() - Dice.roll(6));

			// validation step
			if (type.equals(Type.SATELLITE) && scores[0] == 0)
				this.type = Type.RING;

			if (scores[0] < 0)
				scores[0] = 0;

			/*
			 * ATMOSPHERE
			 */
			scores[1] = (byte) (Dice.roll(2, 6) - 7 + scores[0]);
			if (isMoon() && orbit < habitableZone)
				scores[1] -= 4;
			else if (orbit < habitableZone)
				scores[1] -= 2;
			else if (orbit > habitableZone)
				scores[1] -= 4;

			if (isMoon() && scores[0] < 2)
				scores[1] = 0;
			else if (scores[0] == 0)
				scores[1] = 0;

			if (orbit > habitableZone + 1 && Dice.roll(2, 6) == 12)
				scores[1] = 10;

			// validation step
			if (scores[1] < 0)
				scores[1] = 0;

			/*
			 * HYDROGRAPHY
			 */
			scores[2] = (byte) (Dice.roll(2, 6) - 7 + scores[0]);
			if (orbit < habitableZone || scores[0] < 2)
				scores[2] = 0;
			else if (isMoon() && orbit > habitableZone)
				scores[2] -= 4;
			else if (orbit > habitableZone)
				scores[2] -= 2;

			if (scores[1] < 2 || scores[1] > 9)
				scores[2] -= 4;

			// validation step
			if (scores[2] < 0)
				scores[2] = 0;

			/*
			 * POPULATION
			 */
			scores[3] = (byte) (Dice.roll(2, 6) - 2);
			if (orbit < habitableZone)
				scores[3] -= 5;
			else if (orbit > habitableZone)
				scores[3] -= 3;

			if (scores[1] != 0 && scores[1] != 5 && scores[1] != 6 && scores[1] != 8)
				scores[3] -= 2;

			// validation step
			if (isRing() || scores[3] < 0)
				scores[3] = 0;
		}

		/*
		 * SATELLITES
		 */
		if (type.equals(Type.SATELLITE)) {
			moons = new ArrayList<Planetoid>();

		} else {
			moons = new ArrayList<Planetoid>();

			int satellites = 0;
			if (type.equals(Type.LARGE_GIANT))
				satellites = Dice.roll(2, 6);
			else if (type.equals(Type.SMALL_GIANT))
				satellites = Dice.roll(2, 6) - 4;
			else if (isWorld() && scores[0] > 0)
				satellites = Dice.roll(1, 6) - 3;

			// validation step
			if (satellites < 0)
				satellites = 0;

			while (satellites > 0) {
				moons.add(new Planetoid(Type.SATELLITE, orbit, group, this));
				--satellites;

			}
		}

	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	@Override
	public String toString() {
		String string = String.format("%s (%d)", type, orbit);

		if (isWorld()) {
			int size = scores[0], atmo = scores[1];
			int hydro = scores[2], pop = scores[3];
			int gov = scores[4], law = scores[5];

			String world = String.format("[%2d, %2d, %2d, %2d, %2d, %2d]", size, atmo, hydro, pop, gov, law);

			if (type.equals(Type.SATELLITE))
				string = String.format("%18s %s", "* " + string, world);
			else
				string = String.format("%-18s %s", string, world);
		} else if (isRing()) {

			string = String.format("   * %s", string);
		}

		// facilities
		String facilities = (worldFacilities != null) ? String.format(" %s", worldFacilities) : "";
		// world tags
		String tags = (worldTags != null) ? String.format(" %s", worldTags) : "";
		// habitable zone
		// if (isWorld() && orbit == group.getHabitableZone())
		// string += " ...in habitable zone";
		string += String.format("%-20s %s", facilities, tags);

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("%s (%d)", type, orbit);

		if (isWorld()) {
			int size = scores[0], atmo = scores[1];
			int hydro = scores[2], pop = scores[3];
			int gov = scores[4], law = scores[5];

			String world = String.format("[%2d, %2d, %2d, %2d, %2d, %2d]", size, atmo, hydro, pop, gov, law);

			if (type.equals(Type.SATELLITE))
				string = String.format("%18s %s", "* " + string, world);
			else
				string = String.format("%-18s %s", string, world);
		} else if (isRing()) {

			string = String.format("   * %s", string);
		}

		// facilities
		String facilities = (worldFacilities != null) ? String.format(" %s", worldFacilities) : "";
		// world tags
		String tags = (worldTags != null) ? String.format(" %s", worldTags) : "";
		// habitable zone
		// if (isWorld() && orbit == group.getHabitableZone())
		// string += " ...in habitable zone";
		string += String.format("%-20s %s", facilities, tags);

		if (hasMoons()) {
			String satellites = "";
			for (Planetoid el : moons)
				satellites += "\n" + el.toString();

			string += satellites;
			// } else if (notMoon() && (isWorld() || isGasGiant())) {
			// string += " (no natural satellites)";
		}

		return string;
	}

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	public static class OrbitAscending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return left.orbit - right.orbit;
		}

	}

	public static class PopulationDescending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return right.getPopulation() - left.getPopulation();
		}

	}

	public static class MainWorldSort implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			// first sort by population
			int compare = right.getPopulation() - left.getPopulation();

			// next try habitable zone
			if (compare == 0) {
				int habitable = right.group.getHabitableZone();

				if (left.orbit == right.orbit)
					compare = 0;
				else if (left.orbit == habitable)
					compare = -1;
				else if (right.orbit == habitable)
					compare = 1;

			}

			// then try proximity to primary star
			if (compare == 0)
				compare = left.orbit - right.orbit;

			return compare;
		}

	}

	/*
	 * FIXME - INTERFACE MATHODS (move later)
	 * 
	 */
	@Override
	public EnumSet<TradeCodes> getTradeCodes() {
		return tradeCodes;
	}

	@Override
	public void setTradeCodes(EnumSet<TradeCodes> set) {
		this.tradeCodes = set;
	}

	@Override
	public EnumSet<Tag> getWorldTags() {
		return worldTags;
	}

	@Override
	public void setWorldTags(EnumSet<Tag> set) {
		this.worldTags = set;
	}

	@Override
	public EnumSet<Base> getWorldFacilities() {
		return worldFacilities;
	}

	@Override
	public void setWorldFacilities(EnumSet<Base> set) {
		this.worldFacilities = set;
	}

	@Override
	public Group getGroup() {
		return group;
	}

	@Override
	public int getOrbit() {
		return orbit;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public char getSpaceport() {
		return spaceport;
	}

	@Override
	public void setSpaceport(char c) {
		this.spaceport = c;
	}

	@Override
	public int getTechLevel() {
		return techLevel;
	}

	@Override
	public void setTechLevel(int techLevel) {
		this.techLevel = (byte) techLevel;
	}

	@Override
	public byte[] getAttributes() {
		return scores;
	}

	@Override
	public void setAttribute(int index, int value) {
		this.scores[index] = (byte) value;
	}

	@Override
	public List<Planetoid> getMoons() {
		return moons;
	}

}
