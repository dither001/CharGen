package milieu;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rules.Dice;

public interface World {
	public enum Base {
		NAVY, SCOUT, FARM, MINE, COLONY, LAB, MILITARY
	}

	public enum TradeCodes {
		AG, AS, BA, DE, FL, HI, IC, IN, LO, NA, NI, PO, RI, VA, WA;

		public static void setupTradeCodes(World world) {
			EnumSet<TradeCodes> set = EnumSet.noneOf(TradeCodes.class);

			int size = world.getSize();
			int atmo = world.getAtmosphere();
			int hydro = world.getHydrosphere();
			int pop = world.getPopulation();
			int gov = world.getGovernment();
			int law = world.getLawLevel();

			/*
			 * AGRICULTURE (AG)
			 */
			boolean idealAtmo = false;
			if (atmo >= 4 && atmo <= 9)
				idealAtmo = true;

			boolean idealHydro = false;
			if (hydro >= 4 && hydro <= 8)
				idealHydro = true;

			boolean idealPop = false;
			if (pop >= 5 && pop <= 7)
				idealPop = true;

			if (idealAtmo && idealHydro && idealPop)
				set.add(AG);

			/*
			 * ASTEROID (AS)
			 */
			if (size == 0 && atmo == 0 && hydro == 0)
				set.add(AS);

			/*
			 * BARREN (BA)
			 */
			if (pop == 0 && gov == 0 && law == 0)
				set.add(BA);

			/*
			 * DESERT (DE)
			 */
			if (atmo >= 2 && hydro == 0)
				set.add(DE);

			/*
			 * FLUID OCEANS (FL)
			 */
			if (atmo >= 10 && hydro >= 1)
				set.add(FL);

			/*
			 * HIGH POPULATION (HI)
			 */
			if (pop >= 9)
				set.add(HI);

			/*
			 * ICE-CAPPED (IC)
			 */
			if (atmo <= 1 && hydro >= 1)
				set.add(IC);

			/*
			 * INDUSTRIAL (IN)
			 */
			idealAtmo = false;
			if (atmo <= 2 || atmo == 4 || atmo == 7 || atmo == 9)
				idealAtmo = true;

			if (idealAtmo && pop >= 9)
				set.add(IN);

			/*
			 * LOW POPULATION (LO)
			 */
			if (pop <= 3)
				set.add(LO);

			/*
			 * NON-AGRICULTURAL (NA)
			 */
			idealAtmo = false;
			if (atmo <= 3)
				idealAtmo = true;

			idealHydro = false;
			if (hydro <= 3)
				idealHydro = true;

			idealPop = false;
			if (pop >= 6)
				idealPop = true;

			if (idealAtmo && idealHydro && idealPop)
				set.add(NA);

			/*
			 * POOR WORLD (PO)
			 */
			idealAtmo = false;
			if (atmo >= 2 && atmo <= 5)
				idealAtmo = true;

			idealHydro = false;
			if (hydro <= 3)
				idealHydro = true;

			if (idealAtmo && idealHydro)
				set.add(PO);

			/*
			 * RICH WORLD (RI)
			 */
			idealAtmo = false;
			if (atmo == 6 || atmo == 8)
				idealAtmo = true;

			idealPop = false;
			if (pop == 6 || pop == 7 || pop == 8)
				idealPop = true;

			boolean idealGov = false;
			if (gov >= 4 && gov <= 9)
				idealGov = true;

			if (idealAtmo && idealPop && idealGov)
				set.add(RI);

			/*
			 * VACUUM WORLD (VA)
			 */
			if (atmo == 0)
				set.add(VA);

			/*
			 * WATER WORLD (WA)
			 */
			if (hydro == 10)
				set.add(WA);

			world.setTradeCodes(set);
		}
	}

	public enum Tag {
		ABANDONED_COLONY, ALIEN_RUINS, ALTERED_HUMANITY, AREA_51, BADLANDS_WORLD, BUBBLE_CITIES, CIVIL_WAR, COLD_WAR, COLONIZED_POPULATION, DESERT_WORLD, EUGENIC_CULT, EXCHANGE_CONSULATE, FERAL_WORLD, FLYING_CITIES, FORBIDDEN_TECH, FREAK_GEOLOGY, FREAK_WEATHER, FRIENDLY_FOE, GOLD_RUSH, RADICAL_RACISM, HEAVY_INDUSTRY, HEAVY_MINING, HOSTILE_BIOSPHERE, HOSTILE_SPACE, LOCAL_SPECIALTY, LOCAL_TECH, MAJOR_SPACEYARD, MINIMAL_CONTACT, RADICAL_SEXISM, OCEANIC_WORLD, OUT_OF_CONTACT, OUTPOST_WORLD, PERIMETER_AGENCY, PILGRIMAGE_SITE, POLICE_STATE, PRECEPTOR_ARCHIVE, PRETECH_CULTISTS, PRIMITIVE_ALIENS, PSIONICS_FEAR, PSIONICS_WORSHIP, PSIONICS_ACADEMY, QUARANTINE_WORLD, RADIOACTIVE_WORLD, REGIONAL_HEGEMON, RESTRICTIVE_LAWS, RIGID_CULTURE, SEAGOING_CITIES, SEALED_MENACE, SECTARIANS, SEISMIC_INSTABILITY, SECRET_MASTERS, THEOCRACY, TOMB_WORLD, TRADE_HUB, TYRANNY, UNBRAKED_AI, WARLORDS, XENOPHILES, XENOPHOBES, ZOMBIES
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static int worldIndex = 0;

	public static final Tag[] ALL_TAGS = { Tag.ABANDONED_COLONY, Tag.ALIEN_RUINS, Tag.ALTERED_HUMANITY, Tag.AREA_51,
			Tag.BADLANDS_WORLD, Tag.BUBBLE_CITIES, Tag.CIVIL_WAR, Tag.COLD_WAR, Tag.COLONIZED_POPULATION,
			Tag.DESERT_WORLD, Tag.EUGENIC_CULT, Tag.EXCHANGE_CONSULATE, Tag.FERAL_WORLD, Tag.FLYING_CITIES,
			Tag.FORBIDDEN_TECH, Tag.FRIENDLY_FOE, Tag.FREAK_GEOLOGY, Tag.FREAK_WEATHER, Tag.GOLD_RUSH,
			Tag.RADICAL_RACISM, Tag.HEAVY_INDUSTRY, Tag.HEAVY_MINING, Tag.HOSTILE_BIOSPHERE, Tag.HOSTILE_SPACE,
			Tag.LOCAL_SPECIALTY, Tag.LOCAL_TECH, Tag.MAJOR_SPACEYARD, Tag.MINIMAL_CONTACT, Tag.RADICAL_SEXISM,
			Tag.OCEANIC_WORLD, Tag.OUT_OF_CONTACT, Tag.OUTPOST_WORLD, Tag.PERIMETER_AGENCY, Tag.PILGRIMAGE_SITE,
			Tag.POLICE_STATE, Tag.PRECEPTOR_ARCHIVE, Tag.PRETECH_CULTISTS, Tag.PRIMITIVE_ALIENS, Tag.PSIONICS_FEAR,
			Tag.PSIONICS_WORSHIP, Tag.PSIONICS_ACADEMY, Tag.QUARANTINE_WORLD, Tag.RADIOACTIVE_WORLD,
			Tag.REGIONAL_HEGEMON, Tag.RESTRICTIVE_LAWS, Tag.RIGID_CULTURE, Tag.SEAGOING_CITIES, Tag.SEALED_MENACE,
			Tag.SECRET_MASTERS, Tag.SECTARIANS, Tag.SEISMIC_INSTABILITY, Tag.THEOCRACY, Tag.TOMB_WORLD, Tag.TRADE_HUB,
			Tag.TYRANNY, Tag.UNBRAKED_AI, Tag.WARLORDS, Tag.XENOPHILES, Tag.XENOPHOBES, Tag.ZOMBIES };

	public static final Tag[] COMMON_TAGS = { Tag.ABANDONED_COLONY, Tag.ALIEN_RUINS, Tag.ALTERED_HUMANITY, Tag.AREA_51,
			Tag.EUGENIC_CULT, Tag.FORBIDDEN_TECH, Tag.FRIENDLY_FOE, Tag.RADICAL_RACISM, Tag.LOCAL_SPECIALTY,
			Tag.PERIMETER_AGENCY, Tag.PILGRIMAGE_SITE, Tag.PRECEPTOR_ARCHIVE, Tag.PRETECH_CULTISTS,
			Tag.QUARANTINE_WORLD, Tag.RADICAL_SEXISM, Tag.SEALED_MENACE, Tag.SECTARIANS, Tag.XENOPHILES, Tag.XENOPHOBES,
			Tag.ZOMBIES };

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

	public EnumSet<Tag> getWorldTags();

	public void setWorldTags(EnumSet<Tag> set);

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
	public static Tag randomTag() {
		return Dice.randomFromArray(ALL_TAGS);
	}

	public static void setupWorldTags(World world) {
		if (world.isWorld() != true)
			return;

		EnumSet<Tag> set;
		if (world.getWorldTags() != null)
			set = EnumSet.copyOf(world.getWorldTags());
		else
			set = EnumSet.noneOf(Tag.class);

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
			set.add(Tag.ABANDONED_COLONY);

		// ALIEN_RUINS
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(Tag.ALIEN_RUINS);

		// ALTERED_HUMANITY
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(Tag.ALTERED_HUMANITY);

		// AREA_51
		if (militaryLab && gov > 7 && Dice.roll(2, 6) >= 10)
			set.add(Tag.AREA_51);

		// BADLANDS_WORLD
		if (pop > 3 && tainted && hydro < 5)
			set.add(Tag.BADLANDS_WORLD);

		// BUBBLE_CITIES
		if (pop > 5 && noColony && (atmo == 0 || tainted))
			set.add(Tag.BUBBLE_CITIES);

		// CIVIL_WAR
		if (pop > 5 && gov == 7 && Dice.roll(2, 6) >= 10)
			set.add(Tag.CIVIL_WAR);

		// COLD_WAR
		if (pop > 7 && gov == 7 && Dice.roll(2, 6) >= 8)
			set.add(Tag.COLD_WAR);

		// COLONIZED_POPULATION
		if (bases.contains(Base.COLONY) && Dice.roll(2, 6) >= 8)
			set.add(Tag.COLONIZED_POPULATION);

		// DESERT_WORLD
		if ((atmo == 6 || atmo == 7) && hydro < 4)
			set.add(Tag.DESERT_WORLD);

		// EUGENIC_CULT
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(Tag.EUGENIC_CULT);

		// EXCHANGE_CONSULATE
		if (pop > 8 && connected && techLevel > 12)
			set.add(Tag.EXCHANGE_CONSULATE);

		// FERAL_WORLD
		if (pop > 5 && techLevel < 2)
			set.add(Tag.FERAL_WORLD);

		// FLYING_CITIES
		if (pop > 5 && noColony && atmo == 10)
			set.add(Tag.FLYING_CITIES);

		// FORBIDDEN_TECH
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(Tag.FORBIDDEN_TECH);

		// FREAK_GEOLOGY
		if (size < 8 && militaryLab && Dice.roll(2, 6) >= 10)
			set.add(Tag.FREAK_GEOLOGY);

		// FREAK_WEATHER
		if (tainted && militaryLab && Dice.roll(2, 6) >= 10)
			set.add(Tag.FREAK_WEATHER);

		// FRIENDLY_FOE
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(Tag.FRIENDLY_FOE);

		// GOLD_RUSH
		if (pop < 5 && bases.contains(Base.MINE) && Dice.roll(2, 6) >= 10)
			set.add(Tag.GOLD_RUSH);

		// HATRED
		if (pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.RADICAL_RACISM);

		// HEAVY_INDUSTRY
		if (tradeCodes.contains(TradeCodes.IN))
			set.add(Tag.HEAVY_INDUSTRY);

		// HEAVY_MINING
		if (bases.contains(Base.MINE) && Dice.roll(2, 6) >= 8)
			set.add(Tag.HEAVY_MINING);

		// HOSTILE_BIOSPHERE
		idealAtmo = (atmo >= 4 && atmo <= 8) ? true : false;
		idealHydro = (hydro >= 4 && hydro <= 8) ? true : false;
		if (habitable && idealAtmo && idealHydro && Dice.roll(2, 6) >= 8)
			set.add(Tag.HOSTILE_BIOSPHERE);

		// HOSTILE_SPACE
		idealGov = (gov == 0 || gov == 7) ? true : false;
		idealLaw = (law == 0 || law >= 9) ? true : false;
		if (idealGov && idealLaw && Dice.roll(2, 6) >= 10)
			set.add(Tag.HOSTILE_SPACE);

		// LOCAL_SPECIALTY
		if (pop > 3 && Dice.roll(2, 6) >= 10)
			set.add(Tag.LOCAL_SPECIALTY);

		// LOCAL_TECH
		if (pop > 3 && techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(Tag.LOCAL_TECH);

		// MAJOR_SPACEYARD
		if (spaceport == 'A' && Dice.roll(2, 6) >= 10)
			set.add(Tag.MAJOR_SPACEYARD);

		// MINIMAL_CONTACT
		if (backwater && gov > 7 && Dice.roll(2, 6) >= 10)
			set.add(Tag.MINIMAL_CONTACT);

		// OCEANIC_WORLD
		if (tradeCodes.contains(TradeCodes.FL))
			set.add(Tag.OCEANIC_WORLD);

		// OUT_OF_CONTACT
		if (backwater && Dice.roll(2, 6) >= 10)
			set.add(Tag.OUT_OF_CONTACT);

		// OUTPOST_WORLD
		if (pop > 0 && pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.OUTPOST_WORLD);

		// PERIMETER_AGENCY
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.PERIMETER_AGENCY);

		// PILGRIMAGE_SITE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.PILGRIMAGE_SITE);

		// POLICE_STATE
		if (gov > 7 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(Tag.POLICE_STATE);

		// PRECEPTOR_ARCHIVE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.PRECEPTOR_ARCHIVE);

		// PRETECH_CULTISTS
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.PRETECH_CULTISTS);

		// PRIMITIVE_ALIENS
		if (bases.contains(Base.COLONY) && Dice.roll(2, 6) == 12)
			set.add(Tag.PRIMITIVE_ALIENS);

		// PSIONICS_ACADEMY
		if (pop > 5 && Dice.roll(2, 6) == 12)
			set.add(Tag.PSIONICS_ACADEMY);

		// PSIONICS_FEAR
		// PSIONICS_WORSHIP
		dice = Dice.roll(2, 6);
		if (pop < 5 && dice <= 3)
			set.add(Tag.PSIONICS_FEAR);
		if (pop < 5 && dice >= 11)
			set.add(Tag.PSIONICS_WORSHIP);

		// QUARANTINE_WORLD
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(Tag.QUARANTINE_WORLD);

		// RADICAL_SEXISM
		if (pop < 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.RADICAL_SEXISM);

		// RADIOACTIVE_WORLD
		if (!world.habitable() && atmo < 4)
			set.add(Tag.RADIOACTIVE_WORLD);

		// REGIONAL_HEGEMON
		if (pop > 8 && connected && techLevel > 12)
			set.add(Tag.REGIONAL_HEGEMON);

		// RESTRICTIVE_LAWS
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(Tag.RESTRICTIVE_LAWS);

		// RIGID_CULTURE
		if (pop > 5 && law > 9 && Dice.roll(2, 6) >= 10)
			set.add(Tag.RIGID_CULTURE);

		// SEAGOING_CITIES
		if (pop > 5 && hydro == 10)
			set.add(Tag.SEAGOING_CITIES);

		// SEALED_MENACE
		if (pop > 5 && Dice.roll(2, 6) >= 10)
			set.add(Tag.SEALED_MENACE);

		// SECRET_MASTERS
		if (pop > 7 && gov > 0 && Dice.roll(2, 6) >= 10)
			set.add(Tag.SECRET_MASTERS);

		// SECTARIANS
		if (pop > 5 && gov == 7 && Dice.roll(2, 6) >= 10)
			set.add(Tag.SECTARIANS);

		// SEISMIC_INSTABILITY
		if (size < 5 && bases.contains(Base.MINE) && Dice.roll(2, 6) >= 10)
			set.add(Tag.SEISMIC_INSTABILITY);

		// THEOCRACY
		if (gov == 13 && Dice.roll(2, 6) >= 8)
			set.add(Tag.THEOCRACY);

		// TRADE_HUB
		if (connected && Dice.roll(2, 6) >= 10)
			set.add(Tag.TRADE_HUB);

		// TOMB_WORLD
		if (pop < 3 && Dice.roll(2, 6) >= 10)
			set.add(Tag.TOMB_WORLD);

		// TYRANNY
		if (gov > 7 && law > 9 && Dice.roll(2, 6) >= 8)
			set.add(Tag.TYRANNY);

		// UNBRAKED_AI
		if (techLevel > 12 && Dice.roll(2, 6) >= 10)
			set.add(Tag.UNBRAKED_AI);

		// WARLORDS
		idealGov = (gov == 0 || gov == 7) ? true : false;
		if (pop > 5 && idealGov && law == 0 && Dice.roll(2, 6) >= 10)
			set.add(Tag.WARLORDS);

		// XENOPHILES
		// XENOPHOBES
		dice = Dice.roll(2, 6);
		if (pop < 5 && dice <= 3)
			set.add(Tag.XENOPHILES);
		if (pop < 5 && dice >= 11)
			set.add(Tag.XENOPHOBES);

		// ZOMBIES
		if (pop < 5 && backwater && Dice.roll(2, 6) >= 10)
			set.add(Tag.ZOMBIES);

		world.setWorldTags(set);
	}

	public static void pruneWorldTags(Set<Tag> common, World world) {
		if (world.isWorld() != true)
			return;

		EnumSet<Tag> workingSet;
		if (world.getWorldTags() != null)
			workingSet = EnumSet.copyOf(world.getWorldTags());
		else
			workingSet = EnumSet.noneOf(Tag.class);

		if (workingSet.size() == 2) {
			// System.out.println("Returned with the right number.");
			return;
		} else if (workingSet.size() < 2) {
			// System.out.println("Added one or two.");
			Tag candidate;
			while (workingSet.size() < 2) {
				candidate = Dice.randomFromArray(COMMON_TAGS);
				while (common.contains(candidate)) {
					candidate = Dice.randomFromArray(COMMON_TAGS);
				}

				workingSet.add(candidate);
			}

			world.setWorldTags(workingSet);
		} else {
			// System.out.println("Cut down the list from " + workingSet.size());
			EnumSet<Tag> tempSet = EnumSet.noneOf(Tag.class);

			Tag candidate = Dice.randomFromArray(COMMON_TAGS);
			while (tempSet.size() < 2) {
				candidate = Dice.randomFromArray(COMMON_TAGS);
				while (common.contains(candidate)) {
					candidate = Dice.randomFromArray(COMMON_TAGS);
				}

				tempSet.add(Dice.randomFromSet(workingSet));
			}

			world.setWorldTags(tempSet);
		}

	}

}
