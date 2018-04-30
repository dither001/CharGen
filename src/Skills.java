
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

		switch (job) {
		case BARBARIAN:
			jobSkills = barbarian;
			break;
		case BARD:
			jobSkills = allSkills;
			break;
		case CLERIC:
			jobSkills = cleric;
			break;
		case DRUID:
			jobSkills = druid;
			break;
		case FIGHTER:
			jobSkills = fighter;
			break;
		case MONK:
			jobSkills = monk;
			break;
		case PALADIN:
			jobSkills = paladin;
			break;
		case RANGER:
			jobSkills = ranger;
			break;
		case ROGUE:
			jobSkills = rogue;
			break;
		case SORCERER:
			jobSkills = sorcerer;
			break;
		case WARLOCK:
			jobSkills = warlock;
			break;
		case WIZARD:
			jobSkills = wizard;
			break;
		default:
			jobSkills = null;
			break;
		}

		return jobSkills;
	}
}
