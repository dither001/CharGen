
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	private static final Skills[] ALL_SKILLS = { ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY, INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION, SLEIGHT_OF_HAND, STEALTH, SURVIVAL };

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
	public static Proficiency randomSkill(Actor actor) {
		HashSet<Proficiency> actorSkills = actor.getSkills();
		ArrayList<Skills> list = new ArrayList<Skills>(Arrays.asList(ALL_SKILLS));
		Collections.shuffle(list);

		Proficiency skill = list.get(0);
		list.remove(0);
		while (actorSkills.contains(skill)) {
			skill = list.get(0);
			list.remove(0);
		}
		
		return skill;
	}
	
	public static HashSet<Proficiency> getProficiency(Class job) {
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

	public static HashSet<Proficiency> filterForSkills(Actor actor) {
		HashSet<Proficiency> set = new HashSet<Proficiency>(actor.getSkills());
		set.retainAll(Arrays.asList(ALL_SKILLS));
		
		return set;
	}
	
	public static Skills[] getSkillArray() {
		return ALL_SKILLS;
	}

	public static HashSet<Proficiency> getSkillSet() {
		HashSet<Proficiency> list = new HashSet<Proficiency>();
		list.addAll(Arrays.asList(ALL_SKILLS));
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
					candidates[i] = ALL_SKILLS[Dice.roll(ALL_SKILLS.length) - 1];
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

	public static Skills[] jobSkills(Class job) {
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

		if (job.equals(Class.BARBARIAN))
			jobSkills = barbarian;
		else if (job.equals(Class.BARD))
			jobSkills = ALL_SKILLS;
		else if (job.equals(Class.CLERIC))
			jobSkills = cleric;
		else if (job.equals(Class.DRUID))
			jobSkills = druid;
		else if (job.equals(Class.FIGHTER))
			jobSkills = fighter;
		else if (job.equals(Class.MONK))
			jobSkills = monk;
		else if (job.equals(Class.PALADIN))
			jobSkills = paladin;
		else if (job.equals(Class.RANGER))
			jobSkills = ranger;
		else if (job.equals(Class.ROGUE))
			jobSkills = rogue;
		else if (job.equals(Class.SORCERER))
			jobSkills = sorcerer;
		else if (job.equals(Class.WARLOCK))
			jobSkills = warlock;
		else if (job.equals(Class.WIZARD))
			jobSkills = wizard;
		else
			jobSkills = ALL_SKILLS;

		return jobSkills;
	}
}
