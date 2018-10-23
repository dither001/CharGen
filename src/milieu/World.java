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

	/*
	 * STATIC METHODS
	 * 
	 */
	public static WorldTag randomTag() {
		return Dice.randomFromArray(WorldTag.ALL_TAGS);
	}

	public static void setupWorldTags(World world) {
		if (world.isWorld() != true)
			return;

		EnumSet<WorldTag> set;
		if (world.getWorldTags() != null)
			set = EnumSet.copyOf(world.getWorldTags());
		else
			set = EnumSet.noneOf(WorldTag.class);

		int dice;
		/*
		 * 
		 */
		int size = world.getSize(), atmo = world.getAtmosphere();
		int hydro = world.getHydrosphere(), pop = world.getPopulation();
		int gov = world.getGovernment(), law = world.getLawLevel();

		char spaceport = world.getSpaceport();
		int techLevel = world.getTechLevel();
		EnumSet<TradeCodes> tradeCodes = world.getTradeCodes();
		EnumSet<Base> bases = world.getWorldFacilities();

		// quick booleans
		boolean habitable = world.habitable();
		boolean tainted = (atmo == 2 || atmo == 4 || atmo == 7 || atmo > 8) ? true : false;
		boolean noColony = (bases.contains(Base.COLONY) != true) ? true : false;
		boolean militaryLab = (bases.contains(Base.LAB) || bases.contains(Base.MILITARY)) ? true : false;
		boolean connected = (spaceport == 'A' || spaceport == 'B') ? true : false;
		boolean backwater = (spaceport == 'D' || spaceport == 'E') ? true : false;
		boolean idealAtmo, idealHydro, idealGov, idealLaw; // idealSize, idealPop

		// ABANDONED_COLONY
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.ABANDONED_COLONY);

		// ALIEN_RUINS
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.ALIEN_RUINS);

		// ALTERED_HUMANITY
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.ALTERED_HUMANITY);

		// AREA_51
		if (militaryLab && gov > 7 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.AREA_51);

		// BADLANDS_WORLD
		if (pop > 3 && tainted && hydro < 5)
			set.add(WorldTag.BADLANDS_WORLD);

		// BUBBLE_CITIES
		if (pop > 5 && noColony && (atmo == 0 || tainted))
			set.add(WorldTag.BUBBLE_CITIES);

		// CIVIL_WAR
		if (pop > 5 && gov == 7 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.CIVIL_WAR);

		// COLD_WAR
		if (pop > 7 && gov == 7 && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.COLD_WAR);

		// COLONIZED_POPULATION
		if (bases.contains(Base.COLONY) && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.COLONIZED_POPULATION);

		// DESERT_WORLD
		if ((atmo == 6 || atmo == 7) && hydro < 4)
			set.add(WorldTag.DESERT_WORLD);

		// EUGENIC_CULT
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.EUGENIC_CULT);

		// EXCHANGE_CONSULATE
		if (pop > 8 && connected && techLevel > 12)
			set.add(WorldTag.EXCHANGE_CONSULATE);

		// FERAL_WORLD
		if (pop > 5 && techLevel < 2)
			set.add(WorldTag.FERAL_WORLD);

		// FLYING_CITIES
		if (pop > 5 && noColony && atmo == 10)
			set.add(WorldTag.FLYING_CITIES);

		// FORBIDDEN_TECH
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.FORBIDDEN_TECH);

		// FREAK_GEOLOGY
		if (size < 8 && militaryLab && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.FREAK_GEOLOGY);

		// FREAK_WEATHER
		if (tainted && militaryLab && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.FREAK_WEATHER);

		// FRIENDLY_FOE
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.FRIENDLY_FOE);

		// GOLD_RUSH
		if (pop < 5 && bases.contains(Base.MINE) && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.GOLD_RUSH);

		// HATRED
		if (pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.RADICAL_RACISM);

		// HEAVY_INDUSTRY
		if (tradeCodes.contains(TradeCodes.IN))
			set.add(WorldTag.HEAVY_INDUSTRY);

		// HEAVY_MINING
		if (bases.contains(Base.MINE) && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.HEAVY_MINING);

		// HOSTILE_BIOSPHERE
		idealAtmo = (atmo >= 4 && atmo <= 8) ? true : false;
		idealHydro = (hydro >= 4 && hydro <= 8) ? true : false;
		if (habitable && idealAtmo && idealHydro && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.HOSTILE_BIOSPHERE);

		// HOSTILE_SPACE
		idealGov = (gov == 0 || gov == 7) ? true : false;
		idealLaw = (law == 0 || law >= 9) ? true : false;
		if (idealGov && idealLaw && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.HOSTILE_SPACE);

		// LOCAL_SPECIALTY
		if (pop > 3 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.LOCAL_SPECIALTY);

		// LOCAL_TECH
		if (pop > 3 && techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.LOCAL_TECH);

		// MAJOR_SPACEYARD
		if (spaceport == 'A' && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.MAJOR_SPACEYARD);

		// MINIMAL_CONTACT
		if (backwater && gov > 7 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.MINIMAL_CONTACT);

		// OCEANIC_WORLD
		if (tradeCodes.contains(TradeCodes.FL))
			set.add(WorldTag.OCEANIC_WORLD);

		// OUT_OF_CONTACT
		if (backwater && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.OUT_OF_CONTACT);

		// OUTPOST_WORLD
		if (pop > 0 && pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.OUTPOST_WORLD);

		// PERIMETER_AGENCY
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.PERIMETER_AGENCY);

		// PILGRIMAGE_SITE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.PILGRIMAGE_SITE);

		// POLICE_STATE
		if (gov > 7 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.POLICE_STATE);

		// PRECEPTOR_ARCHIVE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.PRECEPTOR_ARCHIVE);

		// PRETECH_CULTISTS
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.PRETECH_CULTISTS);

		// PRIMITIVE_ALIENS
		if (bases.contains(Base.COLONY) && Dice.roll(2, 6) == 12)
			set.add(WorldTag.PRIMITIVE_ALIENS);

		// PSIONICS_ACADEMY
		if (pop > 5 && Dice.roll(2, 6) == 12)
			set.add(WorldTag.PSIONICS_ACADEMY);

		// PSIONICS_FEAR
		// PSIONICS_WORSHIP
		dice = Dice.roll(2, 6);
		if (pop < 5 && dice <= 3)
			set.add(WorldTag.PSIONICS_FEAR);
		if (pop < 5 && dice >= 11)
			set.add(WorldTag.PSIONICS_WORSHIP);

		// QUARANTINE_WORLD
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.QUARANTINE_WORLD);

		// RADICAL_SEXISM
		if (pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.RADICAL_SEXISM);

		// RADIOACTIVE_WORLD
		if (!world.habitable() && atmo < 4)
			set.add(WorldTag.RADIOACTIVE_WORLD);

		// REGIONAL_HEGEMON
		if (pop > 8 && connected && techLevel > 12)
			set.add(WorldTag.REGIONAL_HEGEMON);

		// RESTRICTIVE_LAWS
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.RESTRICTIVE_LAWS);

		// RIGID_CULTURE
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.RIGID_CULTURE);

		// SEAGOING_CITIES
		if (pop > 5 && hydro == 10)
			set.add(WorldTag.SEAGOING_CITIES);

		// SEALED_MENACE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.SEALED_MENACE);

		// SECRET_MASTERS
		if (pop > 7 && gov > 0 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.SECRET_MASTERS);

		// SECTARIANS
		if (pop > 5 && gov == 7 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.SECTARIANS);

		// SEISMIC_INSTABILITY
		if (size < 5 && bases.contains(Base.MINE) && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.SEISMIC_INSTABILITY);

		// THEOCRACY
		if (gov == 13 && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.THEOCRACY);

		// TRADE_HUB
		if (connected && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.TRADE_HUB);

		// TOMB_WORLD
		if (pop < 3 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.TOMB_WORLD);

		// TYRANNY
		if (gov > 7 && law > 9 && Dice.roll(2, 6) >= 8)
			set.add(WorldTag.TYRANNY);

		// UNBRAKED_AI
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.UNBRAKED_AI);

		// WARLORDS
		idealGov = (gov == 0 || gov == 7) ? true : false;
		if (pop > 5 && idealGov && law == 0 && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.WARLORDS);

		// XENOPHILES
		// XENOPHOBES
		dice = Dice.roll(2, 6);
		if (pop < 5 && dice <= 3)
			set.add(WorldTag.XENOPHILES);
		if (pop < 5 && dice >= 11)
			set.add(WorldTag.XENOPHOBES);

		// ZOMBIES
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(WorldTag.ZOMBIES);

		world.setWorldTags(set);
	}

	public static void pruneWorldTags(Set<WorldTag> common, World world) {
		if (world.isWorld() != true)
			return;

		EnumSet<WorldTag> workingSet;
		if (world.getWorldTags() != null)
			workingSet = EnumSet.copyOf(world.getWorldTags());
		else
			workingSet = EnumSet.noneOf(WorldTag.class);

		if (workingSet.size() == 2) {
			// System.out.println("Returned with the right number.");
			return;
		} else if (workingSet.size() < 2) {
			// System.out.println("Added one or two.");
			WorldTag candidate;
			while (workingSet.size() < 2) {
				candidate = Dice.randomFromArray(WorldTag.COMMON_TAGS);
				while (common.contains(candidate)) {
					candidate = Dice.randomFromArray(WorldTag.COMMON_TAGS);
				}

				workingSet.add(candidate);
			}

			world.setWorldTags(workingSet);
		} else {
			// System.out.println("Cut down the list from " + workingSet.size());
			EnumSet<WorldTag> tempSet = EnumSet.noneOf(WorldTag.class);

			WorldTag candidate = Dice.randomFromArray(WorldTag.COMMON_TAGS);
			while (tempSet.size() < 2) {
				candidate = Dice.randomFromArray(WorldTag.COMMON_TAGS);
				while (common.contains(candidate)) {
					candidate = Dice.randomFromArray(WorldTag.COMMON_TAGS);
				}

				tempSet.add(Dice.randomFromSet(workingSet));
			}

			world.setWorldTags(tempSet);
		}

	}

}
