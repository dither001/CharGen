package com.starswonumber.definitions;

import java.util.EnumSet;
import java.util.Set;

import com.norvendae.misc.Dice;
import com.traveller1e.worlds.Base;
import com.traveller1e.worlds.TradeCode;
import com.traveller1e.worlds.World;
import com.traveller1e.worlds.WorldType;

public enum WorldTag {
	ABANDONED_COLONY, ALIEN_RUINS, ALTERED_HUMANITY, AREA_51, BADLANDS_WORLD, BUBBLE_CITIES, CIVIL_WAR, COLD_WAR, COLONIZED_POPULATION, DESERT_WORLD, EUGENIC_CULT, EXCHANGE_CONSULATE, FERAL_WORLD, FLYING_CITIES, FORBIDDEN_TECH, FREAK_GEOLOGY, FREAK_WEATHER, FRIENDLY_FOE, GOLD_RUSH, HEAVY_INDUSTRY, HEAVY_MINING, HOSTILE_BIOSPHERE, HOSTILE_SPACE, LOCAL_SPECIALTY, LOCAL_TECH, MAJOR_SPACEYARD, MINIMAL_CONTACT, OCEANIC_WORLD, OUTPOST_WORLD, OUT_OF_CONTACT, PERIMETER_AGENCY, PILGRIMAGE_SITE, POLICE_STATE, PRECEPTOR_ARCHIVE, PRETECH_CULTISTS, PRIMITIVE_ALIENS, PSIONICS_ACADEMY, PSIONICS_FEAR, PSIONICS_WORSHIP, QUARANTINE_WORLD, RADICAL_RACISM, RADICAL_SEXISM, RADIOACTIVE_WORLD, REGIONAL_HEGEMON, RESTRICTIVE_LAWS, RIGID_CULTURE, SEAGOING_CITIES, SEALED_MENACE, SECRET_MASTERS, SECTARIANS, SEISMIC_INSTABILITY, THEOCRACY, TOMB_WORLD, TRADE_HUB, TYRANNY, UNBRAKED_AI, WARLORDS, XENOPHILES, XENOPHOBES, ZOMBIES;

	public static final WorldTag[] ALL_TAGS = { ABANDONED_COLONY, ALIEN_RUINS, ALTERED_HUMANITY, AREA_51,
			BADLANDS_WORLD, BUBBLE_CITIES, CIVIL_WAR, COLD_WAR, COLONIZED_POPULATION, DESERT_WORLD, EUGENIC_CULT,
			EXCHANGE_CONSULATE, FERAL_WORLD, FLYING_CITIES, FORBIDDEN_TECH, FREAK_GEOLOGY, FREAK_WEATHER, FRIENDLY_FOE,
			GOLD_RUSH, HEAVY_INDUSTRY, HEAVY_MINING, HOSTILE_BIOSPHERE, HOSTILE_SPACE, LOCAL_SPECIALTY, LOCAL_TECH,
			MAJOR_SPACEYARD, MINIMAL_CONTACT, OCEANIC_WORLD, OUTPOST_WORLD, OUT_OF_CONTACT, PERIMETER_AGENCY,
			PILGRIMAGE_SITE, POLICE_STATE, PRECEPTOR_ARCHIVE, PRETECH_CULTISTS, PRIMITIVE_ALIENS, PSIONICS_ACADEMY,
			PSIONICS_FEAR, PSIONICS_WORSHIP, QUARANTINE_WORLD, RADICAL_RACISM, RADICAL_SEXISM, RADIOACTIVE_WORLD,
			REGIONAL_HEGEMON, RESTRICTIVE_LAWS, RIGID_CULTURE, SEAGOING_CITIES, SEALED_MENACE, SECRET_MASTERS,
			SECTARIANS, SEISMIC_INSTABILITY, THEOCRACY, TOMB_WORLD, TRADE_HUB, TYRANNY, UNBRAKED_AI, WARLORDS,
			XENOPHILES, XENOPHOBES, ZOMBIES };

	public static final WorldTag[] COMMON_TAGS = { ABANDONED_COLONY, ALIEN_RUINS, ALTERED_HUMANITY, AREA_51,
			EUGENIC_CULT, FORBIDDEN_TECH, FRIENDLY_FOE, RADICAL_RACISM, LOCAL_SPECIALTY, PERIMETER_AGENCY,
			PILGRIMAGE_SITE, PRECEPTOR_ARCHIVE, PRETECH_CULTISTS, QUARANTINE_WORLD, RADICAL_SEXISM, SEALED_MENACE,
			SECTARIANS, XENOPHILES, XENOPHOBES, ZOMBIES };

	/*
	 * INSTANCE METHODS
	 */
	public int tagIndex() {
		return tagIndex(this);
	}

	/*
	 * STATIC METHODS
	 */
	public static String[] tagsToStrings() {
		int length = ALL_TAGS.length;
		String[] array = new String[length];
		for (int i = 0; i < length; ++i) {
			array[i] = ALL_TAGS[i].toString();
		}

		return array;
	}

	public static WorldTag getTagByIndex(int index) {
		WorldTag tag = null;
		if (index < ALL_TAGS.length)
			tag = ALL_TAGS[index];

		return tag;
	}

	public static void setupWorldTags(World world) {
		if (WorldType.isWorld(world) != true)
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
		EnumSet<TradeCode> tradeCodes = world.getTradeCodes();
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
		if (tradeCodes.contains(TradeCode.IN))
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
		if (tradeCodes.contains(TradeCode.FL))
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

	public static WorldTag randomTag() {
		return Dice.randomFromArray(WorldTag.ALL_TAGS);
	}

	public static void pruneWorldTags(Set<WorldTag> common, World world) {
		if (WorldType.isWorld(world) != true)
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

	public static int tagIndex(WorldTag tag) {
		int index = 0;

		switch (tag) {
		case ABANDONED_COLONY:
			index = 0;
			break;
		case ALIEN_RUINS:
			index = 1;
			break;
		case ALTERED_HUMANITY:
			index = 2;
			break;
		case AREA_51:
			index = 3;
			break;
		case BADLANDS_WORLD:
			index = 4;
			break;
		case BUBBLE_CITIES:
			index = 5;
			break;
		case CIVIL_WAR:
			index = 6;
			break;
		case COLD_WAR:
			index = 7;
			break;
		case COLONIZED_POPULATION:
			index = 8;
			break;
		case DESERT_WORLD:
			index = 9;
			break;
		case EUGENIC_CULT:
			index = 10;
			break;
		case EXCHANGE_CONSULATE:
			index = 11;
			break;
		case FERAL_WORLD:
			index = 12;
			break;
		case FLYING_CITIES:
			index = 13;
			break;
		case FORBIDDEN_TECH:
			index = 14;
			break;
		case FREAK_GEOLOGY:
			index = 15;
			break;
		case FREAK_WEATHER:
			index = 16;
			break;
		case FRIENDLY_FOE:
			index = 17;
			break;
		case GOLD_RUSH:
			index = 18;
			break;
		case HEAVY_INDUSTRY:
			index = 19;
			break;
		case HEAVY_MINING:
			index = 20;
			break;
		case HOSTILE_BIOSPHERE:
			index = 21;
			break;
		case HOSTILE_SPACE:
			index = 22;
			break;
		case LOCAL_SPECIALTY:
			index = 23;
			break;
		case LOCAL_TECH:
			index = 24;
			break;
		case MAJOR_SPACEYARD:
			index = 25;
			break;
		case MINIMAL_CONTACT:
			index = 26;
			break;
		case OCEANIC_WORLD:
			index = 27;
			break;
		case OUTPOST_WORLD:
			index = 28;
			break;
		case OUT_OF_CONTACT:
			index = 29;
			break;
		case PERIMETER_AGENCY:
			index = 30;
			break;
		case PILGRIMAGE_SITE:
			index = 31;
			break;
		case POLICE_STATE:
			index = 32;
			break;
		case PRECEPTOR_ARCHIVE:
			index = 33;
			break;
		case PRETECH_CULTISTS:
			index = 34;
			break;
		case PRIMITIVE_ALIENS:
			index = 35;
			break;
		case PSIONICS_ACADEMY:
			index = 36;
			break;
		case PSIONICS_FEAR:
			index = 37;
			break;
		case PSIONICS_WORSHIP:
			index = 38;
			break;
		case QUARANTINE_WORLD:
			index = 39;
			break;
		case RADICAL_RACISM:
			index = 40;
			break;
		case RADICAL_SEXISM:
			index = 41;
			break;
		case RADIOACTIVE_WORLD:
			index = 42;
			break;
		case REGIONAL_HEGEMON:
			index = 43;
			break;
		case RESTRICTIVE_LAWS:
			index = 44;
			break;
		case RIGID_CULTURE:
			index = 45;
			break;
		case SEAGOING_CITIES:
			index = 46;
			break;
		case SEALED_MENACE:
			index = 47;
			break;
		case SECRET_MASTERS:
			index = 48;
			break;
		case SECTARIANS:
			index = 49;
			break;
		case SEISMIC_INSTABILITY:
			index = 50;
			break;
		case THEOCRACY:
			index = 51;
			break;
		case TOMB_WORLD:
			index = 52;
			break;
		case TRADE_HUB:
			index = 53;
			break;
		case TYRANNY:
			index = 54;
			break;
		case UNBRAKED_AI:
			index = 55;
			break;
		case WARLORDS:
			index = 56;
			break;
		case XENOPHILES:
			index = 57;
			break;
		case XENOPHOBES:
			index = 58;
			break;
		case ZOMBIES:
			index = 59;
			break;
		default:
			break;

		}

		return index;
	}

}
