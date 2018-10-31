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

	public EnumSet<TradeCodes> getTradeCodes();

	public void setTradeCodes(EnumSet<TradeCodes> set);

	public Set<Faction> getFactions();

	public void setFactions(Set<Faction> factions);

	public EnumSet<WorldTag> getWorldTags();

	public void setWorldTags(EnumSet<WorldTag> set);

	public EnumSet<Base> getWorldFacilities();

	public void setWorldFacilities(EnumSet<Base> set);

//	public StarSystem getGroup();

	public Type getType();

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
	 * TODO - this is the first big automated initialization of a world after it has
	 * been generated; there will probably have to be another step afterward, which
	 * creates the factions. I'm not actually sure why this is here and not in the
	 * Planetoid object because it represents "implementation"
	 * 
	 */

	public default void factionSetup() {
		// TODO
		HashSet<Faction> factions = new HashSet<Faction>();

		// int pop = getPopulation();
		// for (int i = 0; i < pop; ++i) {
		// factions.add(new Society(this));
		//
		// }

		setFactions(factions);
	}

	public default boolean isWorld() {
		boolean isWorld = true;
		Type type = getType();

		if (type.equals(Type.EMPTY))
			isWorld = false;

		if (type.equals(Type.RING))
			isWorld = false;

		if (type.equals(Type.SMALL_GIANT) || type.equals(Type.LARGE_GIANT))
			isWorld = false;

		return isWorld;
	}

	public default boolean nameable() {
		boolean nameable = true;
		Type type = getType();

		if (type.equals(Type.EMPTY))
			nameable = false;

		if (type.equals(Type.RING))
			nameable = false;

		return nameable;
	}

//	public boolean isMainWorld() {
//		boolean isMainWorld = false;
//		Planetoid mainWorld = getGroup().getMainWorld();
//		if (this.equals(mainWorld))
//			isMainWorld = true;
//
//		return isMainWorld;
//	}

//	public default Planetoid getMainWorld() {
//		return getGroup().getMainWorld();
//	}

//	public default int getHabitableZone() {
//		return getGroup().getHabitableZone();
//	}

	public default boolean isEmpty() {
		return getType().equals(Type.EMPTY);
	}

	public default boolean notEmpty() {
		return getType().equals(Type.EMPTY) != true;
	}

	public default boolean isGasGiant() {
		return getType().equals(Type.SMALL_GIANT) || getType().equals(Type.LARGE_GIANT);
	}

	public default boolean largeGiant() {
		return getType().equals(Type.LARGE_GIANT);
	}

	public default boolean smallGiant() {
		return getType().equals(Type.SMALL_GIANT);
	}

	public default boolean notGasGiant() {
		return getType().equals(Type.SMALL_GIANT) != true && getType().equals(Type.LARGE_GIANT) != true;
	}

	public default boolean isCaptured() {
		return getType().equals(Type.CAPTURED);
	}

	public default boolean isMoon() {
		return getType().equals(Type.SATELLITE);
	}

	public default boolean notMoon() {
		return getType().equals(Type.SATELLITE) != true;
	}

	public default boolean isRing() {
		return getType().equals(Type.RING);
	}

	public default boolean notRing() {
		return getType().equals(Type.RING) != true;
	}

	public default boolean isAsteroid() {
		return getType().equals(Type.ASTEROID);
	}

	public default boolean notAsteroid() {
		return getType().equals(Type.ASTEROID) != true;
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
