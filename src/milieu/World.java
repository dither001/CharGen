package milieu;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.norvendae.rules.misc.Dice;

public interface World extends Persistent {
	/*
	 * STATIC FIELDS
	 * 
	 */
	public static final String[] GOVERNMENT_TYPES = { "No Government", "Corporation", "Democracy, Participating",
			"Oligarchy, Self-perpetuating", "Democracy, Representative", "Technocracy, Feudal", "Captive Government",
			"Balkanization", "Bureaucracy, Civil Service", "Bureaucracy, Impersonal", "Dictatorship, Charismatic",
			"Dictatorship, Non-charismatic", "Oligarchy, Charismatic", "Dictatorship, Religious" };

	public static int worldIndex = 0;

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	public static class OrbitAscending implements Comparator<World> {

		@Override
		public int compare(World left, World right) {
			return left.getOrbit() - right.getOrbit();
		}

	}

	public static class SubOrbitAscending implements Comparator<World> {

		@Override
		public int compare(World left, World right) {
			return left.getSubOrbit() - right.getSubOrbit();
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
	public int getOrbit();

	public void setOrbit(int orbit);

	public int getSubOrbit();

	public void setSubOrbit(int subOrbit);

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

	// public Set<Planetoid> getMoons();
	//
	// public int totalMoons();

	/*
	 * DEFAULT METHODS
	 */

	// public default boolean hasMoons() {
	// boolean hasMoons = false;
	// if (getMoons().size() > 0)
	// hasMoons = true;
	//
	// return hasMoons;
	// }

	// public default List<Planetoid> orderedMoonList() {
	// List<Planetoid> moonList = Dice.setToList(getMoons());
	// World.SubOrbitAscending moonSort = new World.SubOrbitAscending();
	// Collections.sort(moonList, moonSort);
	//
	// return moonList;
	// }

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
