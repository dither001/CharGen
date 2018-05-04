
//	ACROBATICS(Ability.DEXTERITY),
//	ANIMAL_HANDLING(Ability.WISDOM),
//	ARCANA(Ability.INTELLIGENCE),
//	ATHLETICS(Ability.STRENGTH),
//	DECEPTION(Ability.CHARISMA),
//	HISTORY(Ability.INTELLIGENCE),
//	INSIGHT(Ability.WISDOM),
//	INTIMIDATION(Ability.CHARISMA),
//	INVESTIGATION(Ability.INTELLIGENCE),
//	MEDICINE(Ability.WISDOM),
//	NATURE(Ability.INTELLIGENCE),
//	PERCEPTION(Ability.WISDOM),
//	PERFORMANCE(Ability.CHARISMA),
//	PERSUASION(Ability.CHARISMA),
//	RELIGION(Ability.INTELLIGENCE),
//	SLEIGHT_OF_HAND(Ability.DEXTERITY),
//	STEALTH(Ability.DEXTERITY),
//	SURVIVAL(Ability.WISDOM);

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public enum Skills implements Proficiency {
	ACROBATICS(Ability.DEXTERITY),
	ANIMAL_HANDLING(Ability.WISDOM),
	ARCANA(Ability.INTELLIGENCE),
	ATHLETICS(Ability.STRENGTH),
	DECEPTION(Ability.CHARISMA),
	HISTORY(Ability.INTELLIGENCE),
	INSIGHT(Ability.WISDOM),
	INTIMIDATION(Ability.CHARISMA),
	INVESTIGATION(Ability.INTELLIGENCE),
	MEDICINE(Ability.WISDOM),
	NATURE(Ability.INTELLIGENCE),
	PERCEPTION(Ability.WISDOM),
	PERFORMANCE(Ability.CHARISMA),
	PERSUASION(Ability.CHARISMA),
	RELIGION(Ability.INTELLIGENCE),
	SLEIGHT_OF_HAND(Ability.DEXTERITY),
	STEALTH(Ability.DEXTERITY),
	SURVIVAL(Ability.WISDOM);

	// static fields
	private static Skills[] allSkills = { ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY, INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION, SLEIGHT_OF_HAND, STEALTH, SURVIVAL };

	// fields
	Ability prime;

	// constructors
	private Skills() {
		this.prime = null;
	}

	private Skills(Ability prime) {
		this.prime = prime;
	}

	// static methods
	public static HashSet<Proficiency> getProficiency(Archetype job) {
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		int number = job.getNumberOfSkills();
		Skills[] jobSkills = jobSkills(job);
		Skills candidate;

		for (int i = 0; i < number;) {
			candidate = jobSkills[Dice.roll(jobSkills.length) - 1];
			if (list.contains(candidate) != true) {
				list.add(candidate);
				++i;
			}
		}

		return list;
	}

	public static Skills[] getSkillArray() {
		return allSkills;
	}

	public static HashSet<Proficiency> getSkillSet() {
		HashSet<Proficiency> list = new HashSet<Proficiency>();
		list.addAll(Arrays.asList(allSkills));
		return list;
	}

	public static HashSet<Proficiency> careerSkills(Actor actor) {
		HashSet<Proficiency> careerSkills = new HashSet<Proficiency>();

		HashSet<Proficiency> skills = actor.getSkills();
		Career career = actor.getCareer();
		Skills[] candidates = careerSkillsHelper(career);

		for (int i = 0; i < candidates.length; ++i) {
			if (skills.contains(candidates[i]) != true && careerSkills.contains(candidates[i]) != true) {
				careerSkills.add(candidates[i]);
			} else {
				while (skills.contains(candidates[i]) || careerSkills.contains(candidates[i])) {
					// TODO - should also choose from tool proficiencies and such
					candidates[i] = allSkills[Dice.roll(allSkills.length) - 1];
				}

				careerSkills.add(candidates[i]);
			}
		}

		return careerSkills;
	}

	private static Skills[] careerSkillsHelper(Career career) {
		Skills[] careerSkills = { ATHLETICS, SURVIVAL };

		Skills[] acolyte = { INSIGHT, RELIGION };
		Skills[] charlatan = { DECEPTION, SLEIGHT_OF_HAND };
		Skills[] criminal = { DECEPTION, STEALTH };
		Skills[] entertainer = { ACROBATICS, PERFORMANCE };
		Skills[] folkHero = { ANIMAL_HANDLING, SURVIVAL };
		Skills[] guildArtisan = { INSIGHT, PERSUASION };
		Skills[] hermit = { MEDICINE, RELIGION };
		Skills[] noble = { HISTORY, PERSUASION };
		Skills[] outlander = { ATHLETICS, SURVIVAL };
		Skills[] sage = { ARCANA, HISTORY };
		Skills[] sailor = { ATHLETICS, PERCEPTION };
		Skills[] soldier = { ATHLETICS, INTIMIDATION };
		Skills[] urchin = { SLEIGHT_OF_HAND, STEALTH };

		if (career.equals(Career.ACOLYTE))
			careerSkills = acolyte;
		else if (career.equals(Career.CHARLATAN))
			careerSkills = charlatan;
		else if (career.equals(Career.CRIMINAL))
			careerSkills = criminal;
		else if (career.equals(Career.ENTERTAINER))
			careerSkills = entertainer;
		else if (career.equals(Career.FOLK_HERO))
			careerSkills = folkHero;
		else if (career.equals(Career.GUILD_ARTISAN))
			careerSkills = guildArtisan;
		else if (career.equals(Career.HERMIT))
			careerSkills = hermit;
		else if (career.equals(Career.NOBLE))
			careerSkills = noble;
		else if (career.equals(Career.OUTLANDER))
			careerSkills = outlander;
		else if (career.equals(Career.SAGE))
			careerSkills = sage;
		else if (career.equals(Career.SAILOR))
			careerSkills = sailor;
		else if (career.equals(Career.SOLDIER))
			careerSkills = soldier;
		else if (career.equals(Career.URCHIN))
			careerSkills = urchin;

		return careerSkills;
	}

	public static Skills[] jobSkills(Archetype job) {
		Skills[] jobSkills;

		Skills[] barbarian = { ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL };
		Skills[] cleric = { HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION };
		Skills[] druid = { ARCANA, ANIMAL_HANDLING, INSIGHT, MEDICINE, NATURE, PERCEPTION, RELIGION, SURVIVAL };
		Skills[] fighter = { ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY, INSIGHT, INTIMIDATION, PERCEPTION,
				SURVIVAL };
		Skills[] monk = { ACROBATICS, ATHLETICS, HISTORY, INSIGHT, RELIGION, STEALTH };
		Skills[] paladin = { ATHLETICS, INSIGHT, INTIMIDATION, MEDICINE, PERSUASION, RELIGION };
		Skills[] ranger = { ANIMAL_HANDLING, ATHLETICS, INSIGHT, INVESTIGATION, NATURE, PERCEPTION, STEALTH, SURVIVAL };
		Skills[] rogue = { ACROBATICS, ATHLETICS, DECEPTION, INSIGHT, INTIMIDATION, INVESTIGATION, PERCEPTION,
				PERFORMANCE, PERSUASION, SLEIGHT_OF_HAND, STEALTH };
		Skills[] sorcerer = { ARCANA, DECEPTION, INSIGHT, INTIMIDATION, PERSUASION, RELIGION };
		Skills[] warlock = { ARCANA, DECEPTION, HISTORY, INTIMIDATION, INVESTIGATION, NATURE, RELIGION };
		Skills[] wizard = { ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION };

		if (job.equals(Archetype.BARBARIAN))
			jobSkills = barbarian;
		else if (job.equals(Archetype.BARD))
			jobSkills = allSkills;
		else if (job.equals(Archetype.CLERIC))
			jobSkills = cleric;
		else if (job.equals(Archetype.DRUID))
			jobSkills = druid;
		else if (job.equals(Archetype.FIGHTER))
			jobSkills = fighter;
		else if (job.equals(Archetype.MONK))
			jobSkills = monk;
		else if (job.equals(Archetype.PALADIN))
			jobSkills = paladin;
		else if (job.equals(Archetype.RANGER))
			jobSkills = ranger;
		else if (job.equals(Archetype.ROGUE))
			jobSkills = rogue;
		else if (job.equals(Archetype.SORCERER))
			jobSkills = sorcerer;
		else if (job.equals(Archetype.WARLOCK))
			jobSkills = warlock;
		else if (job.equals(Archetype.WIZARD))
			jobSkills = wizard;
		else
			jobSkills = allSkills;

		return jobSkills;
	}
}
