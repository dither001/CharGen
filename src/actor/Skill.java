package actor;
import java.util.EnumSet;

import com.norvendae.rules.misc.Dice;

public enum Skill {
	/*
	 * ADVENTURING SKILLS
	 */
	ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY, INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION, SLEIGHT_OF_HAND, STEALTH, SURVIVAL,
	/*
	 * KITS, TOOLS, INSTRUMENTS, and VEHICLES
	 */
	ALCHEMY_TOOLS, BREWING_TOOLS, CALLIGRAPHY_TOOLS, CARPENTER_TOOLS, CARTOGRAPHY_TOOLS, COBBLER_TOOLS, MESS_KIT, GLASSBLOWER_TOOLS, JEWELER_TOOLS, LEATHERWORKING_TOOLS, MASONRY_TOOLS, NAVIGATOR_TOOLS, PAINTING_TOOLS, POTTING_TOOLS, SMITHING_TOOLS, TINKERS_TOOLS, WEAVING_TOOLS, WOODCARVING_TOOLS,
	// kits
	DISGUISE_KIT, FORGERY_KIT, CHESS_SET, GAMING_DICE, PLAYING_CARDS, HERBALISM_KIT, POISONER_KIT, THIEVES_TOOLS,
	// instruments
	BAGPIPES, DRUM, DULCIMER, FLUTE, LUTE, LYRE, HORN, PAN_FLUTE, SHAWM, VIOL,
	// vehicles
	CARRIAGE, CART, CHARIOT, SLED, WAGON, GALLEY, KEELBOAT, LONGSHIP, ROWBOAT, SAILING_SHIP, WARSHIP;

	// static fields
	private static final Skill[] COMMON_SKILLS = { ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY,
			INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION,
			SLEIGHT_OF_HAND, STEALTH, SURVIVAL };

	// class skills
	private static final Skill[] BARBARIAN_SKILLS = { ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION,
			SURVIVAL };
	private static final Skill[] CLERIC_SKILLS = { HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION };
	private static final Skill[] DRUID_SKILLS = { ARCANA, ANIMAL_HANDLING, INSIGHT, MEDICINE, NATURE, PERCEPTION,
			RELIGION, SURVIVAL };
	private static final Skill[] FIGHTER_SKILLS = { ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY, INSIGHT,
			INTIMIDATION, PERCEPTION, SURVIVAL };
	private static final Skill[] MONK_SKILLS = { ACROBATICS, ATHLETICS, HISTORY, INSIGHT, RELIGION, STEALTH };
	private static final Skill[] PALADIN_SKILLS = { ATHLETICS, INSIGHT, INTIMIDATION, MEDICINE, PERSUASION, RELIGION };
	private static final Skill[] RANGER_SKILLS = { ANIMAL_HANDLING, ATHLETICS, INSIGHT, INVESTIGATION, NATURE,
			PERCEPTION, STEALTH, SURVIVAL };
	private static final Skill[] ROGUE_SKILLS = { ACROBATICS, ATHLETICS, DECEPTION, INSIGHT, INTIMIDATION,
			INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION, SLEIGHT_OF_HAND, STEALTH };
	private static final Skill[] SORCERER_SKILLS = { ARCANA, DECEPTION, INSIGHT, INTIMIDATION, PERSUASION, RELIGION };
	private static final Skill[] WARLOCK_SKILLS = { ARCANA, DECEPTION, HISTORY, INTIMIDATION, INVESTIGATION, NATURE,
			RELIGION };
	private static final Skill[] WIZARD_SKILLS = { ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION };

	// vehicle skills
	private static final Skill[] PROFESSION_SKILLS = { ALCHEMY_TOOLS, BREWING_TOOLS, CALLIGRAPHY_TOOLS, CARPENTER_TOOLS,
			CARTOGRAPHY_TOOLS, COBBLER_TOOLS, MESS_KIT, GLASSBLOWER_TOOLS, JEWELER_TOOLS, LEATHERWORKING_TOOLS,
			MASONRY_TOOLS, NAVIGATOR_TOOLS, PAINTING_TOOLS, POTTING_TOOLS, SMITHING_TOOLS, TINKERS_TOOLS, WEAVING_TOOLS,
			WOODCARVING_TOOLS };
	private static final Skill[] TOOL_SKILLS = { DISGUISE_KIT, FORGERY_KIT, CHESS_SET, GAMING_DICE, PLAYING_CARDS,
			HERBALISM_KIT, POISONER_KIT, THIEVES_TOOLS };
	private static final Skill[] GAMING_SKILLS = { CHESS_SET, GAMING_DICE, PLAYING_CARDS };
	private static final Skill[] INSTRUMENT_SKILLS = { BAGPIPES, DRUM, DULCIMER, FLUTE, LUTE, LYRE, HORN, PAN_FLUTE,
			SHAWM, VIOL };
	private static final Skill[] VEHICLE_SKILLS = { CARRIAGE, CART, CHARIOT, SLED, WAGON, GALLEY, KEELBOAT, LONGSHIP,
			ROWBOAT, SAILING_SHIP, WARSHIP };
	//
	private static final Skill[] LAND_VEHICLES = { CARRIAGE, CART, CHARIOT, SLED, WAGON };
	private static final Skill[] WATER_VEHICLES = { GALLEY, KEELBOAT, LONGSHIP, ROWBOAT, SAILING_SHIP, WARSHIP };

	// special use
	protected static final Skill[] DWARF_TOOLS = { BREWING_TOOLS, MASONRY_TOOLS, SMITHING_TOOLS };

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Skill randomSkill() {
		return Dice.randomFromArray(COMMON_SKILLS);
	}

	public static Skill[] commonSkills() {
		return COMMON_SKILLS;
	}

	public static Skill randomProfessionSkill() {
		return Dice.randomFromArray(PROFESSION_SKILLS);
	}

	public static Skill[] professionSkills() {
		return PROFESSION_SKILLS;
	}

	public static Skill randomToolSkill() {
		return Dice.randomFromArray(TOOL_SKILLS);
	}

	public static Skill[] toolSkills() {
		return TOOL_SKILLS;
	}

	public static Skill randomgGamingSkill() {
		return Dice.randomFromArray(GAMING_SKILLS);
	}

	public static Skill[] gamingSkills() {
		return GAMING_SKILLS;
	}

	public static Skill randomInstrumentSkill() {
		return Dice.randomFromArray(INSTRUMENT_SKILLS);
	}

	public static Skill[] instrumentSkills() {
		return INSTRUMENT_SKILLS;
	}

	public static Skill randomVehicleSkill() {
		return Dice.randomFromArray(VEHICLE_SKILLS);
	}

	public static Skill[] vehicleSkills() {
		return VEHICLE_SKILLS;
	}
	
	public static Skill[] landVehicles() {
		return LAND_VEHICLES;
	}

	public static Skill[] waterVehicles() {
		return WATER_VEHICLES;
	}

	public static boolean testSkillFromArray(Skill[] array, Player actor) {
		boolean canAdd = false, added = false;

		EnumSet<Skill> skills = actor.getSkills();
		for (Skill el : array) {
			if (skills.contains(el) != true) {
				canAdd = true;
				break;
			}
		}

		Skill candidate;
		while (canAdd && added != true) {
			candidate = Dice.randomFromArray(array);
			if (skills.contains(candidate) != true) {
				skills.add(candidate);
				added = true;
				actor.setSkills(skills);
			}
		}

		return added;
	}

	public static Skill testRandomSkill(Player actor) {
		// FIXME - broken method; it becomes infinite if you have all class skills
		EnumSet<Skill> skills = actor.getSkills();
		Class job = actor.getJob();

		Skill test;
		while (true) {
			test = randomClassSkill(job);

			if (skills.contains(test) != true)
				break;
		}

		return test;
	}

	public static Skill randomClassSkill(Class job) {
		Skill[] array;

		if (job.equals(Class.BARBARIAN))
			array = BARBARIAN_SKILLS;
		else if (job.equals(Class.BARD))
			array = COMMON_SKILLS;
		else if (job.equals(Class.CLERIC))
			array = CLERIC_SKILLS;
		else if (job.equals(Class.DRUID))
			array = DRUID_SKILLS;
		else if (job.equals(Class.FIGHTER))
			array = FIGHTER_SKILLS;
		else if (job.equals(Class.MONK))
			array = MONK_SKILLS;
		else if (job.equals(Class.PALADIN))
			array = PALADIN_SKILLS;
		else if (job.equals(Class.RANGER))
			array = RANGER_SKILLS;
		else if (job.equals(Class.ROGUE))
			array = ROGUE_SKILLS;
		else if (job.equals(Class.SORCERER))
			array = SORCERER_SKILLS;
		else if (job.equals(Class.WARLOCK))
			array = WARLOCK_SKILLS;
		else if (job.equals(Class.WIZARD))
			array = WIZARD_SKILLS;
		else
			array = COMMON_SKILLS;

		return Dice.randomFromArray(array);
	}

	public static void setupClassSkills(Player actor) {
		EnumSet<Skill> skills;
		if (actor.getSkills() == null)
			skills = EnumSet.noneOf(Skill.class);
		else
			skills = actor.getSkills();

		Class job = actor.getJob();
		int addedSkills = 0, skillsToAdd = Class.getNumberOfSkills(job);
		// TODO
		Skill[] array;

		if (actor.isBarbarian())
			array = BARBARIAN_SKILLS;
		else if (actor.isBard())
			array = COMMON_SKILLS;
		else if (actor.isCleric())
			array = CLERIC_SKILLS;
		else if (actor.isDruid())
			array = DRUID_SKILLS;
		else if (actor.isFighter())
			array = FIGHTER_SKILLS;
		else if (actor.isMonk())
			array = MONK_SKILLS;
		else if (actor.isPaladin())
			array = PALADIN_SKILLS;
		else if (actor.isRanger())
			array = RANGER_SKILLS;
		else if (actor.isRogue())
			array = ROGUE_SKILLS;
		else if (actor.isSorcerer())
			array = SORCERER_SKILLS;
		else if (actor.isWarlock())
			array = WARLOCK_SKILLS;
		else if (actor.isWizard())
			array = WIZARD_SKILLS;
		else
			array = COMMON_SKILLS;

		Skill candidate;
		while (addedSkills < skillsToAdd) {
			candidate = Dice.randomFromArray(array);

			if (skills.contains(candidate) != true) {
				skills.add(candidate);
				++addedSkills;
			}
		}

		actor.setSkills(skills);
	}

	// public static void setupCareerSkills(Player actor) {
	// EnumSet<Skill> skills;
	// if (actor.getSkills() == null)
	// skills = EnumSet.noneOf(Skill.class);
	// else
	// skills = actor.getSkills();
	//
	// Career.Profile job = actor.getCareer();
	// int addedSkills = 0, skillsToAdd = 2;
	// // TODO
	// Skill[] array = null;
	//
	// if (job.equals(Career.ACOLYTE))
	// array = ACOLYTE_SKILLS;
	// else if (job.equals(Career.CHARLATAN))
	// array = CHARLATAN_SKILLS;
	// else if (job.equals(Career.CRIMINAL))
	// array = CRIMINAL_SKILLS;
	// else if (job.equals(Career.ENTERTAINER))
	// array = ENTERTAINER_SKILLS;
	// else if (job.equals(Career.FOLK_HERO))
	// array = FOLK_HERO_SKILLS;
	// else if (job.equals(Career.GUILD_ARTISAN))
	// array = GUILD_ARTISAN_SKILLS;
	// else if (job.equals(Career.HERMIT))
	// array = HERMIT_SKILLS;
	// else if (job.equals(Career.NOBLE))
	// array = NOBLE_SKILLS;
	// else if (job.equals(Career.OUTLANDER))
	// array = OUTLANDER_SKILLS;
	// else if (job.equals(Career.SAGE))
	// array = SAGE_SKILLS;
	// else if (job.equals(Career.SAILOR))
	// array = SAILOR_SKILLS;
	// else if (job.equals(Career.SOLDIER))
	// array = SOLDIER_SKILLS;
	// else if (job.equals(Career.URCHIN))
	// array = URCHIN_SKILLS;
	//
	// if (skills.contains(array[0]) != true) {
	// skills.add(array[0]);
	// ++addedSkills;
	// }
	//
	// if (skills.contains(array[1]) != true) {
	// skills.add(array[1]);
	// ++addedSkills;
	// }
	//
	// Skill candidate;
	// while (addedSkills < skillsToAdd) {
	// candidate = randomSkill();
	//
	// if (skills.contains(candidate) != true) {
	// skills.add(candidate);
	// ++addedSkills;
	// }
	//
	// }
	//
	// actor.setSkills(skills);
	// }

	// public static void setupRacialSkills(Player actor) {
	// EnumSet<Skill> skills;
	// if (actor.getSkills() == null)
	// skills = EnumSet.noneOf(Skill.class);
	// else
	// skills = actor.getSkills();
	//
	// Race race = actor.getRace();
	// int addedSkills = 0, skillsToAdd = 0;
	// // TODO
	//
	// if (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) ||
	// race.equals(Race.WOOD_ELF)) {
	// // all elves receive Perception (or a random skill)
	// skillsToAdd = 1;
	//
	// if (skills.contains(PERCEPTION) != true) {
	// skills.add(PERCEPTION);
	// ++addedSkills;
	// }
	//
	// } else if (race.equals(Race.HALF_ELF)) {
	// // all half-elves receive two random skills
	// skillsToAdd = 2;
	//
	// } else if (race.equals(Race.HALF_ORC)) {
	// // all half-orcs receive Intimidation (or a random skill)
	// skillsToAdd = 2;
	//
	// if (skills.contains(INTIMIDATION) != true) {
	// skills.add(INTIMIDATION);
	// ++addedSkills;
	// }
	// }
	//
	// Skill candidate;
	// while (addedSkills < skillsToAdd) {
	// candidate = randomSkill();
	//
	// if (skills.contains(candidate) != true) {
	// skills.add(candidate);
	// ++addedSkills;
	// }
	//
	// }
	//
	// actor.setSkills(skills);
	// }
}
