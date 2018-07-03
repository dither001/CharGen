import java.util.EnumSet;

public enum Skill {
	ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY, INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION, SLEIGHT_OF_HAND, STEALTH, SURVIVAL;

	// static fields
	private static final Skill[] ALL_SKILLS = { ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY,
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

	// background skills
	private static final Skill[] ACOLYTE_SKILLS = { INSIGHT, RELIGION };
	private static final Skill[] CHARLATAN_SKILLS = { DECEPTION, SLEIGHT_OF_HAND };
	private static final Skill[] CRIMINAL_SKILLS = { DECEPTION, STEALTH };
	private static final Skill[] ENTERTAINER_SKILLS = { ACROBATICS, PERFORMANCE };
	private static final Skill[] FOLK_HERO_SKILLS = { ANIMAL_HANDLING, SURVIVAL };
	private static final Skill[] GUILD_ARTISAN_SKILLS = { INSIGHT, PERSUASION };
	private static final Skill[] HERMIT_SKILLS = { MEDICINE, RELIGION };
	private static final Skill[] NOBLE_SKILLS = { HISTORY, PERSUASION };
	private static final Skill[] OUTLANDER_SKILLS = { ATHLETICS, SURVIVAL };
	private static final Skill[] SAGE_SKILLS = { ARCANA, HISTORY };
	private static final Skill[] SAILOR_SKILLS = { ATHLETICS, PERCEPTION };
	private static final Skill[] SOLDIER_SKILLS = { ATHLETICS, INTIMIDATION };
	private static final Skill[] URCHIN_SKILLS = { SLEIGHT_OF_HAND, STEALTH };

	// static methods
	public static Skill randomSkill() {
		return Dice.randomFromArray(ALL_SKILLS);
	}

	public static Skill randomClassSkill(Class job) {
		Skill[] array;

		if (job.equals(Class.BARBARIAN))
			array = BARBARIAN_SKILLS;
		else if (job.equals(Class.BARD))
			array = ALL_SKILLS;
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
			array = ALL_SKILLS;

		return Dice.randomFromArray(array);
	}

	public static void setupClassSkills(Actor actor) {
		Class job = actor.getJob();
		int addedSkills = 0, skillsToAdd = Class.getNumberOfSkills(job);
		// TODO
		EnumSet<Skill> skills = actor.getSkills();
		Skill[] array;

		if (job.equals(Class.BARBARIAN))
			array = BARBARIAN_SKILLS;
		else if (job.equals(Class.BARD))
			array = ALL_SKILLS;
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
			array = ALL_SKILLS;

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

	public static void setupCareerSkills(Actor actor) {
		Career job = actor.getCareer();
		int addedSkills = 0, skillsToAdd = 2;
		// TODO
		EnumSet<Skill> skills = actor.getSkills();
		Skill[] array = null;

		if (job.equals(Career.ACOLYTE))
			array = ACOLYTE_SKILLS;
		else if (job.equals(Career.CHARLATAN))
			array = CHARLATAN_SKILLS;
		else if (job.equals(Career.CRIMINAL))
			array = CRIMINAL_SKILLS;
		else if (job.equals(Career.ENTERTAINER))
			array = ENTERTAINER_SKILLS;
		else if (job.equals(Career.FOLK_HERO))
			array = FOLK_HERO_SKILLS;
		else if (job.equals(Career.GUILD_ARTISAN))
			array = GUILD_ARTISAN_SKILLS;
		else if (job.equals(Career.HERMIT))
			array = HERMIT_SKILLS;
		else if (job.equals(Career.NOBLE))
			array = NOBLE_SKILLS;
		else if (job.equals(Career.OUTLANDER))
			array = OUTLANDER_SKILLS;
		else if (job.equals(Career.SAGE))
			array = SAGE_SKILLS;
		else if (job.equals(Career.SAILOR))
			array = SAILOR_SKILLS;
		else if (job.equals(Career.SOLDIER))
			array = SOLDIER_SKILLS;
		else if (job.equals(Career.URCHIN))
			array = URCHIN_SKILLS;

		if (skills.contains(array[0]) != true) {
			skills.add(array[0]);
			++addedSkills;
		}

		if (skills.contains(array[1]) != true) {
			skills.add(array[1]);
			++addedSkills;
		}

		Skill candidate;
		while (addedSkills < skillsToAdd) {
			candidate = randomSkill();

			if (skills.contains(candidate) != true) {
				skills.add(candidate);
				++addedSkills;
			}

		}

		actor.setSkills(skills);
	}

	public static void setupRacialSkills(Actor actor) {
		Race race = actor.getRace();
		int addedSkills = 0, skillsToAdd = 0;
		// TODO
		EnumSet<Skill> skills = actor.getSkills();

		if (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF)) {
			skillsToAdd = 1;

			if (skills.contains(PERCEPTION) != true) {
				skills.add(PERCEPTION);
				++addedSkills;
			}
		} else if (race.equals(Race.HALF_ELF)) {
			skillsToAdd = 2;
		} else if (race.equals(Race.HALF_ORC)) {
			skillsToAdd = 2;

			if (skills.contains(INTIMIDATION) != true) {
				skills.add(INTIMIDATION);
				++addedSkills;
			}
		}

		Skill candidate;
		while (addedSkills < skillsToAdd) {
			candidate = randomSkill();

			if (skills.contains(candidate) != true) {
				skills.add(candidate);
				++addedSkills;
			}

		}

		actor.setSkills(skills);
	}

}
