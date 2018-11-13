package milieu;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rules.Dice;

public interface World {
	/*
	 * STATIC FIELDS
	 * 
	 */
	public static final String[] GOVERNMENT_TYPES = { "No Government", "Corporation",
			"Democracy, Participating", "Oligarchy, Self-perpetuating", "Democracy, Representative",
			"Technocracy, Feudal", "Captive Government", "Balkanization", "Bureaucracy, Civil Service",
			"Bureaucracy, Impersonal", "Dictatorship, Charismatic", "Dictatorship, Non-charismatic",
			"Oligarchy, Charismatic", "Dictatorship, Religious" };

	public static int worldIndex = 0;

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	public static class OrbitAscending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return left.orbit() - right.orbit();
		}

	}

	public static class SubOrbitAscending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return left.suborbit() - right.suborbit();
		}

	}

	public static class PopulationDescending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return right.getPopulation() - left.getPopulation();
		}

	}

	/*
	 * 
	 * 
	 */
	public int getIndex();

	public boolean isPersistent();

	public boolean habitable();

	public boolean mainWorld();

	public String getName();

	public void setName(String name);

	public EnumSet<TradeCode> getTradeCodes();

	public void setTradeCodes(EnumSet<TradeCode> set);

	public EnumSet<WorldTag> getWorldTags();

	public void setWorldTags(EnumSet<WorldTag> set);

	public EnumSet<Base> getWorldFacilities();

	public void setWorldFacilities(EnumSet<Base> set);

	// public StarSystem getGroup();

	public WorldType getType();

	public char getSpaceport();

	public void setSpaceport(char c);

	public int getTechLevel();

	public void setTechLevel(int techLevel);

	public int[] getAttributes();

	public void setAttribute(int index, int value);

	public Planetoid getParent();

	public Set<Planetoid> getMoons();

	public int totalMoons();

	/*
	 * DEFAULT METHODS
	 */
	public default boolean isWorld() {
		boolean isWorld = true;
		WorldType type = getType();

		if (type.equals(WorldType.EMPTY))
			isWorld = false;

		if (type.equals(WorldType.RING))
			isWorld = false;

		if (type.equals(WorldType.SMALL_GIANT) || type.equals(WorldType.LARGE_GIANT))
			isWorld = false;

		return isWorld;
	}

	public default boolean nameable() {
		boolean nameable = true;
		WorldType type = getType();

		if (type.equals(WorldType.EMPTY))
			nameable = false;

		if (type.equals(WorldType.RING))
			nameable = false;

		return nameable;
	}

	// public boolean isMainWorld() {
	// boolean isMainWorld = false;
	// Planetoid mainWorld = getGroup().getMainWorld();
	// if (this.equals(mainWorld))
	// isMainWorld = true;
	//
	// return isMainWorld;
	// }

	// public default Planetoid getMainWorld() {
	// return getGroup().getMainWorld();
	// }

	// public default int getHabitableZone() {
	// return getGroup().getHabitableZone();
	// }

	public default boolean isEmpty() {
		return getType().equals(WorldType.EMPTY);
	}

	public default boolean notEmpty() {
		return getType().equals(WorldType.EMPTY) != true;
	}

	public default boolean isGasGiant() {
		return getType().equals(WorldType.SMALL_GIANT) || getType().equals(WorldType.LARGE_GIANT);
	}

	public default boolean largeGiant() {
		return getType().equals(WorldType.LARGE_GIANT);
	}

	public default boolean smallGiant() {
		return getType().equals(WorldType.SMALL_GIANT);
	}

	public default boolean notGasGiant() {
		return getType().equals(WorldType.SMALL_GIANT) != true && getType().equals(WorldType.LARGE_GIANT) != true;
	}

	public default boolean isCaptured() {
		return getType().equals(WorldType.CAPTURED);
	}

	public default boolean isMoon() {
		return getType().equals(WorldType.SATELLITE);
	}

	public default boolean notMoon() {
		return getType().equals(WorldType.SATELLITE) != true;
	}

	public default boolean isRing() {
		return getType().equals(WorldType.RING);
	}

	public default boolean notRing() {
		return getType().equals(WorldType.RING) != true;
	}

	public default boolean isAsteroid() {
		return getType().equals(WorldType.ASTEROID);
	}

	public default boolean notAsteroid() {
		return getType().equals(WorldType.ASTEROID) != true;
	}

	public default boolean hasMoons() {
		boolean hasMoons = false;
		if (getMoons().size() > 0)
			hasMoons = true;

		return hasMoons;
	}

	public default List<Planetoid> orderedMoonList() {
		List<Planetoid> moonList = Dice.setToList(getMoons());
		World.SubOrbitAscending moonSort = new World.SubOrbitAscending();
		Collections.sort(moonList, moonSort);

		return moonList;
	}

	public default int getAttribute(int index) {
		// used by other attribute getters
		return getAttributes()[index];
	}

	public default int getSize() {
		int index = 0;
		return getAttribute(index);
	}

	public default int getAtmosphere() {
		int index = 1;
		return getAttribute(index);
	}

	public default int getHydrosphere() {
		int index = 2;
		return getAttribute(index);
	}

	public default int getPopulation() {
		int index = 3;
		return getAttribute(index);
	}

	public default int getGovernment() {
		int index = 4;
		return getAttribute(index);
	}

	public default String governmentType() {
		String[] governments = { "No Government", "Company/Corporation", "Democracy, Participating",
				"Oligarchy, Self-perpetuating", "Democracy, Representative", "Technocracy, Feudal",
				"Captive Government", "Balkanization", "Bureaucracy, Civil Service", "Bureaucracy, Impersonal",
				"Dictatorship, Charismatic", "Dictatorship, Non-charismatic", "Oligarchy, Charismatic",
				"Dictatorship, Religious" };

		return governments[getGovernment()];
	}

	public default int getLawLevel() {
		int index = 5;
		return getAttribute(index);
	}

	public default void setGovernment(int value) {
		int index = 4;
		setAttribute(index, value);
	}

	public default void setLawLevel(int value) {
		int index = 5;
		setAttribute(index, value);
	}
}
