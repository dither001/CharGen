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
			gov = (mainGov == 6) ? 6 : Dice.roll(6);

			if (mainGov >= 7)
				gov += 2;

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

			getGroup().setStarPort(starport);
		} else {

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
			boolean mainIndustrial = getMainWorld().getTradeCodes().contains(TradeCodes.IN);

			idealPop = false;
			if (pop >= 2)
				idealPop = true;

			if (mainIndustrial && idealPop)
				facilities.add(Base.MINE);
		}

		setWorldFacilities(facilities);

		/*
		 * FIXME - WORLD TAGS
		 */
		EnumSet<Tag> tags = EnumSet.noneOf(Tag.class);
		// FIXME - there should be some logic behind this; not just random
		while (tags.size() < 2) {
			tags.add(Dice.randomFromArray(ALL_TAGS));
		}
		setWorldTags(tags);

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

	public default void setupTags() {

	}
}
