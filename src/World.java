import java.util.EnumSet;
import java.util.List;

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

	/*
	 * 
	 * 
	 */
	public EnumSet<TradeCodes> getTradeCodes();

	public void setTradeCodes(EnumSet<TradeCodes> set);

	public EnumSet<Tag> getWorldTags();

	public void setWorldTags(EnumSet<Tag> set);

	public EnumSet<Base> getWorldFacilities();

	public void setWorldFacilities(EnumSet<Base> set);

	public Group getGroup();

	public int getOrbit();

	public Type getType();

	public char getSpaceport();

	public void setSpaceport(char c);

	public int getTechLevel();

	public void setTechLevel(int techLevel);

	public byte[] getAttributes();

	public void setAttribute(int index, int value);

	public List<Planetoid> getMoons();

	/*
	 * 
	 * 
	 */
	public default void setupGovernment() {
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
			if (dice < 0)
				dice = 0;

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
		setupWorldTags(this);

		/*
		 * END SETUP
		 */
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

		// ABANDONED_COLONY
		if (pop == 0)
			set.add(Tag.ABANDONED_COLONY);

		// ALIEN_RUINS
		// ALTERED_HUMANITY
		// TODO

		// AREA_51
		if (gov == 10 && law >= 9)
			set.add(Tag.AREA_51);

		// HOSTILE_SPACE
		if (gov == 0 || gov == 7 || gov == 10)
			set.add(Tag.HOSTILE_SPACE);
		else if (law == 0 || law >= 9)
			set.add(Tag.HOSTILE_SPACE);

		// POLICE_STATE
		// RESTRICTIVE_LAWS
		dice = Dice.roll(3);
		if (law >= 9 && dice == 1)
			set.add(Tag.POLICE_STATE);
		else if (law >= 9 && dice == 1)
			set.add(Tag.RESTRICTIVE_LAWS);

		// BADLANDS_WORLD
		// DESERT_WORLD
		dice = Dice.roll(3);
		if (dice == 1 && (atmo >= 2 && hydro == 0))
			set.add(Tag.BADLANDS_WORLD);
		else if (dice == 2 && (atmo >= 2 && hydro == 0))
			set.add(Tag.DESERT_WORLD);

		// BUBBLE_CITIES
		if (pop > 0 && (atmo == 0 || hydro == 10))
			set.add(Tag.BUBBLE_CITIES);

		// CIVIL_WAR
		// COLD_WAR
		dice = Dice.roll(3);
		if (gov == 7 && dice == 1)
			set.add(Tag.CIVIL_WAR);
		else if (gov == 7 && dice == 2)
			set.add(Tag.COLD_WAR);

		// COLONIZED_POPULATION
		if (gov == 6)
			set.add(Tag.COLONIZED_POPULATION);

		// EUGENIC_CULT
		if (techLevel >= 13)
			set.add(Tag.EUGENIC_CULT);

		// EXCHANGE_CONSULATE
		if (pop >= 9 && techLevel >= 11)
			set.add(Tag.EXCHANGE_CONSULATE);

		// FERAL_WORLD
		if (pop <= 7 && techLevel < 2)
			set.add(Tag.FERAL_WORLD);

		// FLYING_CITIES
		// FORBIDDEN_TECH
		// TODO

		// FREAK_GEOLOGY
		dice = Dice.roll(2, 6);
		if (size != 8 && dice == 12)
			set.add(Tag.FREAK_GEOLOGY);

		// FREAK_WEATHER
		dice = Dice.roll(2, 6);
		if (atmo != 6 && dice == 12)
			set.add(Tag.FREAK_WEATHER);

		// FRIENDLY_FOE
		// TODO

		// GOLD_RUSH
		dice = Dice.roll(2, 6);
		if (dice == 12)
			set.add(Tag.GOLD_RUSH);

		// HATRED
		// TODO

		// HEAVY_INDUSTRY
		if (tradeCodes.contains(TradeCodes.IN))
			set.add(Tag.HEAVY_INDUSTRY);

		// HEAVY_MINING
		if (bases.contains(Base.MINE))
			set.add(Tag.DESERT_WORLD);

		// HOSTILE_BIOSPHERE
		// TODO

		// LOCAL_SPECIALTY
		dice = Dice.roll(2, 6);
		if (dice == 12)
			set.add(Tag.LOCAL_SPECIALTY);

		// LOCAL_TECH
		dice = Dice.roll(2, 6);
		if (techLevel >= 13 && dice == 12)
			set.add(Tag.LOCAL_TECH);

		// MAJOR_SPACEYARD
		dice = Dice.roll(2, 6);
		if (spaceport == 'A' && dice == 12)
			set.add(Tag.MAJOR_SPACEYARD);

		// MINIMAL_CONTACT
		// RADICAL_SEXISM
		// TODO

		// OCEANIC_WORLD
		// SEAGOING_CITIES
		dice = Dice.roll(3);
		if (hydro >= 8 && dice == 1)
			set.add(Tag.OCEANIC_WORLD);
		else if (hydro >= 8 && dice == 2)
			set.add(Tag.SEAGOING_CITIES);

		// OUT_OF_CONTACT
		// TODO

		// OUTPOST_WORLD
		if (pop <= 2)
			set.add(Tag.OUTPOST_WORLD);

		// PERIMETER_AGENCY
		// PILGRIMAGE_SITE
		// TODO

		// PRECEPTOR_ARCHIVE
		// PRETECH_CULTISTS
		// PRIMITIVE_ALIENS
		// PSIONICS_FEAR
		// PSIONICS_WORSHIP
		// PSIONICS_ACADEMY
		// QUARANTINE_WORLD
		// TODO

		// RADIOACTIVE_WORLD
		if (orbit < habitableZone && atmo < 4)
			set.add(Tag.RADIOACTIVE_WORLD);
		
		// REGIONAL_HEGEMON
		// RIGID_CULTURE
		// SEALED_MENACE
		// SECTARIANS
		// SECRET_MASTERS
		// TODO

		// SEISMIC_INSTABILITY
		dice = Dice.roll(2, 6);
		if (size != 8 && dice == 12)
			set.add(Tag.SEISMIC_INSTABILITY);

		// THEOCRACY
		if (gov == 13)
			set.add(Tag.THEOCRACY);

		// TOMB_WORLD
		// TRADE_HUB
		// TYRANNY
		// UNBRAKED_AI
		// WARLORDS
		// XENOPHILES
		// XENOPHOBES
		// ZOMBIES
		// TODO

		// while (set.size() < 2) {
		// set.add(Dice.randomFromArray(ALL_TAGS));
		// }

		world.setWorldTags(set);
	}
}
