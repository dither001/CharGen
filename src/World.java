import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface World {
	public enum Type {
		EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT
	}

	public enum Base {
		NAVY, SCOUT, FARM, MINE, COLONY, LAB, MILITARY
	}

	public enum Tag {
		ABANDONED_COLONY, ALIEN_RUINS, ALTERED_HUMANITY, AREA_51, BADLANDS_WORLD, BUBBLE_CITIES, CIVIL_WAR, COLD_WAR, COLONIZED_POPULATION, DESERT_WORLD, EUGENIC_CULT, EXCHANGE_CONSULATE, FERAL_WORLD, FLYING_CITIES, FORBIDDEN_TECH, FREAK_GEOLOGY, FREAK_WEATHER, FRIENDLY_FOE, GOLD_RUSH, HATRED, HEAVY_INDUSTRY, HEAVY_MINING, HOSTILE_BIOSPHERE, HOSTILE_SPACE, LOCAL_SPECIALTY, LOCAL_TECH, MAJOR_SPACEYARD, MINIMAL_CONTACT, RADICAL_SEXISM, OCEANIC_WORLD, OUT_OF_CONTACT, OUTPOST_WORLD, PERIMETER_AGENCY, PILGRIMAGE_SITE, POLICE_STATE, PRECEPTOR_ARCHIVE, PRETECH_CULTISTS, PRIMITIVE_ALIENS, PSIONICS_FEAR, PSIONICS_WORSHIP, PSIONICS_ACADEMY, QUARANTINE_WORLD, RADIOACTIVE_WORLD, REGIONAL_HEGEMON, RESTRICTIVE_LAWS, RIGID_CULTURE, SEAGOING_CITIES, SEALED_MENACE, SECTARIANS, SEISMIC_INSTABILITY, SECRET_MASTERS, THEOCRACY, TOMB_WORLD, TRADE_HUB, TYRANNY, UNBRAKED_AI, WARLORDS, XENOPHILES, XENOPHOBES, ZOMBIES
	}

	public static final Tag[] ALL_TAGS = { Tag.ABANDONED_COLONY, Tag.ALIEN_RUINS, Tag.ALTERED_HUMANITY, Tag.AREA_51,
			Tag.BADLANDS_WORLD, Tag.BUBBLE_CITIES, Tag.CIVIL_WAR, Tag.COLD_WAR, Tag.COLONIZED_POPULATION,
			Tag.DESERT_WORLD, Tag.EUGENIC_CULT, Tag.EXCHANGE_CONSULATE, Tag.FERAL_WORLD, Tag.FLYING_CITIES,
			Tag.FORBIDDEN_TECH, Tag.FREAK_GEOLOGY, Tag.FREAK_WEATHER, Tag.FRIENDLY_FOE, Tag.GOLD_RUSH, Tag.HATRED,
			Tag.HEAVY_INDUSTRY, Tag.HEAVY_MINING, Tag.HOSTILE_BIOSPHERE, Tag.HOSTILE_SPACE, Tag.LOCAL_SPECIALTY,
			Tag.LOCAL_TECH, Tag.MAJOR_SPACEYARD, Tag.MINIMAL_CONTACT, Tag.RADICAL_SEXISM, Tag.OCEANIC_WORLD,
			Tag.OUT_OF_CONTACT, Tag.OUTPOST_WORLD, Tag.PERIMETER_AGENCY, Tag.PILGRIMAGE_SITE, Tag.POLICE_STATE,
			Tag.PRECEPTOR_ARCHIVE, Tag.PRETECH_CULTISTS, Tag.PRIMITIVE_ALIENS, Tag.PSIONICS_FEAR, Tag.PSIONICS_WORSHIP,
			Tag.PSIONICS_ACADEMY, Tag.QUARANTINE_WORLD, Tag.RADIOACTIVE_WORLD, Tag.REGIONAL_HEGEMON,
			Tag.RESTRICTIVE_LAWS, Tag.RIGID_CULTURE, Tag.SEAGOING_CITIES, Tag.SEALED_MENACE, Tag.SECTARIANS,
			Tag.SEISMIC_INSTABILITY, Tag.SECRET_MASTERS, Tag.THEOCRACY, Tag.TOMB_WORLD, Tag.TRADE_HUB, Tag.TYRANNY,
			Tag.UNBRAKED_AI, Tag.WARLORDS, Tag.XENOPHILES, Tag.XENOPHOBES, Tag.ZOMBIES };

	public static final Tag[] COMMON_TAGS = { Tag.ABANDONED_COLONY, Tag.ALIEN_RUINS, Tag.ALTERED_HUMANITY, Tag.AREA_51,
			Tag.EUGENIC_CULT, Tag.FORBIDDEN_TECH, Tag.FRIENDLY_FOE, Tag.HATRED, Tag.LOCAL_SPECIALTY,
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
			return left.getOrbit() - right.getOrbit();
		}

	}

	public static class SubOrbitAscending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
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

	public Group getGroup();

	public int getOrbit();

	public int getSubOrbit();

	public Type getType();

	public char getSpaceport();

	public void setSpaceport(char c);

	public int getTechLevel();

	public void setTechLevel(int techLevel);

	public byte[] getAttributes();

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
	public default void governmentSetup() {
		int size = getSize();
		int atmo = getAtmosphere();
		int hydro = getHydrosphere();
		int pop = getPopulation();

		int dice;

		/*
		 * GOVERNMENT, LAW LEVEL
		 */
		int gov, law;
		if (isMainWorld()) {
			/*
			 * GOVERNMENT
			 */
			dice = Dice.roll(2, 6) - 7 + pop;

			// validation step
			dice = (dice < 0) ? 0 : (dice > 14) ? 14 : dice;

			gov = dice;

			/*
			 * LAW LEVEL
			 */
			dice = Dice.roll(2, 6) - 7 + gov;

			// validation step
			if (dice < 0)
				dice = 0;

			law = dice;
		} else {
			Planetoid main = getMainWorld();
			int mainGov = main.getGovernment(), mainLaw = main.getLawLevel();

			/*
			 * GOVERNMENT
			 */
			gov = Dice.roll(6);

			if (mainGov == 6)
				gov += pop;
			else if (mainGov >= 7)
				gov += 1;

			if (gov == 1)
				gov = 0;
			else if (gov == 2)
				gov = 1;
			else if (gov == 3)
				gov = 2;
			else if (gov == 4)
				gov = 3;
			else if (gov >= 5)
				gov = 6;

			// validation step
			if (pop == 0)
				gov = 0;

			/*
			 * LAW LEVEL
			 */
			law = Dice.roll(6) - 3 + mainLaw;

			// validation step
			if (gov == 0 || law < 0)
				law = 0;

		}

		setGovernment(gov);
		setLawLevel(law);

		/*
		 * STARPORT / SPACEPORT
		 */
		char starport = 'X';
		if (isMainWorld()) {
			dice = Dice.roll(2, 6);

			if (dice >= 10)
				starport = 'A';
			else if (dice == 8 || dice == 9)
				starport = 'B';
			else if (dice == 6 || dice == 7)
				starport = 'C';
			else if (dice == 5)
				starport = 'D';
			else if (dice == 3 || dice == 4)
				starport = 'E';
			else
				starport = 'X';

			setSpaceport(starport);
			getGroup().setStarPort(starport);
		} else {
			dice = Dice.roll(6);

			if (pop >= 6)
				dice += 2;
			else if (pop <= 1)
				dice -= 2;

			if (dice >= 10)
				starport = 'A';
			else if (dice == 8 || dice == 9)
				starport = 'B';
			else if (dice == 6 || dice == 7)
				starport = 'C';
			else if (dice == 5)
				starport = 'D';
			else if (dice == 3 || dice == 4)
				starport = 'E';
			else
				starport = 'X';

			setSpaceport(starport);
		}

		/*
		 * TECH LEVEL
		 */
		int techLevel;
		if (isMainWorld()) {
			techLevel = Dice.roll(6);

			// starport bonus
			if (starport == 'A')
				techLevel += 6;
			else if (starport == 'B')
				techLevel += 4;
			else if (starport == 'C')
				techLevel += 2;
			else if (starport == 'X')
				techLevel -= 4;

			// size bonus
			if (size == 0 || size == 1)
				techLevel += 2;
			else if (size == 2 || size == 3 || size == 4)
				techLevel += 1;

			// atmosphere bonus
			if (atmo >= 0 && atmo <= 3)
				techLevel += 1;
			else if (atmo >= 10 && atmo <= 14)
				techLevel += 1;

			// hydrosphere bonus
			if (hydro == 9)
				techLevel += 1;
			else if (hydro == 10)
				techLevel += 2;

			// population bonus
			if (pop >= 1 && pop <= 5)
				techLevel += 1;
			else if (pop == 9)
				techLevel += 2;
			else if (pop == 10)
				techLevel += 4;

			// government bonus
			if (gov == 0 || gov == 5)
				techLevel += 1;
			else if (gov == 13)
				techLevel -= 2;

			setTechLevel(techLevel);
		} else {
			int mainTechLevel = getMainWorld().getTechLevel();
			setTechLevel(mainTechLevel - 1);

		}

		/*
		 * TRADE CODES
		 */
		TradeCodes.setupTradeCodes(this);

		/*
		 * WORLD FACILITIES
		 */
		EnumSet<Base> facilities = EnumSet.noneOf(Base.class);

		if (isMainWorld()) {
			// NAVAL BASE
			dice = Dice.roll(2, 6);
			if (starport != 'A' && starport != 'B')
				dice -= 12;

			if (dice > 7)
				facilities.add(Base.NAVY);

			// SCOUT BASE
			dice = Dice.roll(2, 6);

			if (starport == 'A')
				dice -= 3;
			else if (starport == 'B')
				dice -= 2;
			else if (starport == 'C')
				dice -= 1;
			else if (starport == 'E' || starport == 'X')
				dice -= 12;

			if (dice > 6)
				facilities.add(Base.SCOUT);

		} else {
			Planetoid main = getMainWorld();

			/*
			 * FARM
			 */
			boolean idealAtmo = false, idealHydro = false, idealPop = false;

			if (atmo >= 4 && atmo <= 9)
				idealAtmo = true;

			if (hydro >= 4 && hydro <= 8)
				idealHydro = true;

			if (pop >= 2)
				idealPop = true;

			if (idealAtmo && idealHydro && idealPop && getOrbit() == getHabitableZone())
				facilities.add(Base.FARM);

			/*
			 * MINE
			 */
			boolean mainIndustrial = main.getTradeCodes().contains(TradeCodes.IN);

			idealPop = false;
			if (pop >= 2)
				idealPop = true;

			if (mainIndustrial && idealPop)
				facilities.add(Base.MINE);

			/*
			 * COLONY
			 */
			if (gov == 6 && pop >= 5)
				facilities.add(Base.COLONY);

			/*
			 * LABORATORY
			 */
			int mainTechLevel = main.getTechLevel();

			dice = Dice.roll(2, 6);
			if (main.getTechLevel() >= 10)
				dice += 2;
			else if (mainTechLevel <= 8)
				dice -= 12;

			if (dice >= 11) {
				facilities.add(Base.LAB);
				setTechLevel(mainTechLevel);
			}

			/*
			 * MILITARY
			 */
			boolean mainPoor = main.getTradeCodes().contains(TradeCodes.PO);

			dice = Dice.roll(2, 6);
			if (main.getPopulation() >= 8)
				dice += 1;

			if (atmo == main.getAtmosphere())
				dice += 2;

			if (mainPoor != true && dice >= 12)
				facilities.add(Base.MILITARY);

		}

		setWorldFacilities(facilities);

		/*
		 * FIXME - WORLD TAGS
		 */
		World.setupWorldTags(this);

	}

	public default void factionSetup() {
		// TODO
		HashSet<Faction> factions = new HashSet<Faction>();
		int pop = getPopulation();

		for (int i = 0; i < pop; ++i) {
			factions.add(new Society(this));

		}

		setFactions(factions);
	}

	public default boolean isWorld() {
		boolean isWorld = true;
		Type type = getType();

		if (type.equals(Type.EMPTY))
			isWorld = false;

		if (type.equals(Type.ASTEROID))
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

		if (type.equals(Type.ASTEROID))
			nameable = false;

		if (type.equals(Type.RING))
			nameable = false;

		return nameable;
	}

	public default boolean isMainWorld() {
		boolean isMainWorld = false;
		Planetoid mainWorld = getGroup().getMainWorld();
		if (this.equals(mainWorld))
			isMainWorld = true;

		return isMainWorld;
	}

	public default Planetoid getMainWorld() {
		return getGroup().getMainWorld();
	}

	public default int getHabitableZone() {
		return getGroup().getHabitableZone();
	}

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

	public default boolean isAsteroidBelt() {
		return getType().equals(Type.ASTEROID);
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
		int orbit = world.getOrbit(), habitableZone = world.getHabitableZone();

		char spaceport = world.getSpaceport();
		int techLevel = world.getTechLevel();
		EnumSet<TradeCodes> tradeCodes = world.getTradeCodes();
		EnumSet<Base> bases = world.getWorldFacilities();

		// quick booleans
		boolean habitable = (orbit == habitableZone) ? true : false;
		boolean tainted = (atmo == 2 || atmo == 4 || atmo == 7 || atmo > 8) ? true : false;
		boolean noColony = (bases.contains(Base.COLONY) != true) ? true : false;
		boolean militaryLab = (bases.contains(Base.LAB) || bases.contains(Base.MILITARY)) ? true : false;
		boolean connected = (spaceport == 'A' || spaceport == 'B') ? true : false;
		boolean backwater = (spaceport == 'D' || spaceport == 'E') ? true : false;
		boolean idealSize, idealAtmo, idealHydro, idealPop, idealGov, idealLaw;

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
			set.add(Tag.HATRED);

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
		if (orbit < habitableZone && atmo < 4)
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

	public static void pruneWorldTags(World world) {
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
			while (workingSet.size() < 2) {
				workingSet.add(Dice.randomFromArray(COMMON_TAGS));
			}

			world.setWorldTags(workingSet);
		} else {
			// System.out.println("Cut down the list from " + workingSet.size());
			EnumSet<Tag> tempSet = EnumSet.noneOf(Tag.class);

			while (tempSet.size() < 2) {
				tempSet.add(Dice.randomFromSet(workingSet));
			}

			world.setWorldTags(tempSet);
		}

	}

}
