
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;

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

	public static HashSet<Proficiency> getProficiency(Class job) {
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		int number = job.getNumberOfSkills();
		Skill[] jobSkills = jobSkills(job);
		Skill candidate;

		for (int i = 0; i < number;) {
			candidate = jobSkills[Dice.roll(jobSkills.length) - 1];
			if (list.contains(candidate) != true) {
				list.add(candidate);
				++i;
			}
		}

		return list;
	}

	public static HashSet<Proficiency> filterForSkills(Actor actor) {
		HashSet<Proficiency> set = new HashSet<Proficiency>(actor.getSkills());
		set.retainAll(Arrays.asList(ALL_SKILLS));

		return set;
	}

	public static Skill[] getSkillArray() {
		return ALL_SKILLS;
	}

	public static HashSet<Proficiency> getSkillSet() {
		HashSet<Proficiency> list = new HashSet<Proficiency>();
		list.addAll(Arrays.asList(ALL_SKILLS));
		return list;
	}

	public static EnumSet<Skill> careerSkills(Actor actor) {
		EnumSet<Skill> careerSkills = new HashSet<Proficiency>();

		EnumSet<Skill> skills = actor.getSkills();
		Career career = actor.getCareer();
		Skill[] candidates = careerSkillsHelper(career);

		for (int i = 0; i < candidates.length; ++i) {
			if (skills.contains(candidates[i]) != true && careerSkills.contains(candidates[i]) != true) {
				careerSkills.add(candidates[i]);
			} else {
				while (skills.contains(candidates[i]) || careerSkills.contains(candidates[i])) {
					// TODO - should also choose from tool proficiencies and such
					candidates[i] = ALL_SKILLS[Dice.roll(ALL_SKILLS.length) - 1];
				}

				careerSkills.add(candidates[i]);
			}
		}

		return careerSkills;
	}

	private static Skill[] careerSkillsHelper(Career career) {
		Skill[] careerSkills = { ATHLETICS, SURVIVAL };

		if (career.equals(Career.ACOLYTE))
			careerSkills = ACOLYTE_SKILLS;
		else if (career.equals(Career.CHARLATAN))
			careerSkills = CHARLATAN_SKILLS;
		else if (career.equals(Career.CRIMINAL))
			careerSkills = CRIMINAL_SKILLS;
		else if (career.equals(Career.ENTERTAINER))
			careerSkills = ENTERTAINER_SKILLS;
		else if (career.equals(Career.FOLK_HERO))
			careerSkills = FOLK_HERO_SKILLS;
		else if (career.equals(Career.GUILD_ARTISAN))
			careerSkills = GUILD_ARTISAN_SKILLS;
		else if (career.equals(Career.HERMIT))
			careerSkills = HERMIT_SKILLS;
		else if (career.equals(Career.NOBLE))
			careerSkills = NOBLE_SKILLS;
		else if (career.equals(Career.OUTLANDER))
			careerSkills = OUTLANDER_SKILLS;
		else if (career.equals(Career.SAGE))
			careerSkills = SAGE_SKILLS;
		else if (career.equals(Career.SAILOR))
			careerSkills = SAILOR_SKILLS;
		else if (career.equals(Career.SOLDIER))
			careerSkills = SOLDIER_SKILLS;
		else if (career.equals(Career.URCHIN))
			careerSkills = URCHIN_SKILLS;

		return careerSkills;
	}

	public static Skill[] jobSkills(Class job) {
		Skill[] jobSkills;

		if (job.equals(Class.BARBARIAN))
			jobSkills = BARBARIAN_SKILLS;
		else if (job.equals(Class.BARD))
			jobSkills = ALL_SKILLS;
		else if (job.equals(Class.CLERIC))
			jobSkills = CLERIC_SKILLS;
		else if (job.equals(Class.DRUID))
			jobSkills = DRUID_SKILLS;
		else if (job.equals(Class.FIGHTER))
			jobSkills = FIGHTER_SKILLS;
		else if (job.equals(Class.MONK))
			jobSkills = MONK_SKILLS;
		else if (job.equals(Class.PALADIN))
			jobSkills = PALADIN_SKILLS;
		else if (job.equals(Class.RANGER))
			jobSkills = RANGER_SKILLS;
		else if (job.equals(Class.ROGUE))
			jobSkills = ROGUE_SKILLS;
		else if (job.equals(Class.SORCERER))
			jobSkills = SORCERER_SKILLS;
		else if (job.equals(Class.WARLOCK))
			jobSkills = WARLOCK_SKILLS;
		else if (job.equals(Class.WIZARD))
			jobSkills = WIZARD_SKILLS;
		else
			jobSkills = ALL_SKILLS;

		return jobSkills;
	}
}
