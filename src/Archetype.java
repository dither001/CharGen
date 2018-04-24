//	BARBARIAN(Ability.STRENGTH, 12, 2),
//	BARD(Ability.CHARISMA, 8, 3),
//	CLERIC(Ability.WISDOM, 8, 2),
//	DRUID(Ability.WISDOM, 8, 2),
//	FIGHTER(Ability.STRENGTH, 10, 2),
//	MONK(Ability.WISDOM, 8, 2),
//	PALADIN(Ability.STRENGTH, 10, 2),
//	RANGER(Ability.STRENGTH, 10, 3),
//	ROGUE(Ability.DEXTERITY, 8, 4),
//	SORCERER(Ability.CHARISMA, 6, 2),
//	WARLOCK(Ability.CHARISMA, 8, 2),
//	WIZARD(Ability.INTELLIGENCE, 6, 2);

import java.util.HashSet;

public enum Archetype {
	BARBARIAN(Ability.STRENGTH, 12, 2),
	BARD(Ability.CHARISMA, 8, 3),
	CLERIC(Ability.WISDOM, 8, 2),
	DRUID(Ability.WISDOM, 8, 2),
	FIGHTER(Ability.STRENGTH, 10, 2),
	MONK(Ability.WISDOM, 8, 2),
	PALADIN(Ability.STRENGTH, 10, 2),
	RANGER(Ability.STRENGTH, 10, 3),
	ROGUE(Ability.DEXTERITY, 8, 4),
	SORCERER(Ability.CHARISMA, 6, 2),
	WARLOCK(Ability.CHARISMA, 8, 2),
	WIZARD(Ability.INTELLIGENCE, 6, 2);

	// static fields
	private static final float BEST_XP = 1.10f;
	private static final float GOOD_XP = 1.05f;
	private static final float BAD_XP = 0.90f;
	private static final float WORST_XP = 0.80f;

	// fields
	private Ability primeRequisite;
	/*
	 * TODO - prime requisite was added after general implementation of the
	 * Archetype class and its experience rate methods; eventually I want to have
	 * prime requisite be based more upon the abilities put out in the Ability class
	 * than (necessarily) the classic six ability scores of Dungeons & Dragons
	 */
	private int hitDie;
	private int numberOfSkills;

	// constructors
	Archetype(Ability ability, int hitDie, int numberOfSkills) {
		this.primeRequisite = ability;
		this.setHitDie(hitDie);
		this.numberOfSkills = numberOfSkills;
	}

	// methods
	public int getHitDie() {
		return hitDie;
	}

	public void setHitDie(int hitDie) {
		this.hitDie = hitDie;
	}

	public int getNumberOfSkills() {
		return numberOfSkills;
	}
	
	// static methods
	public static Archetype selectArchetype(Actor actor) {
		Archetype archetype;
		Alignment ali = actor.getAli();

		int DEX, INT, WIS, CHA;
		DEX = actor.getAbilities().getDEX();
		INT = actor.getAbilities().getINT();
		WIS = actor.getAbilities().getWIS();
		CHA = actor.getAbilities().getCHA();

		if (CHA > 14) {
			archetype = SORCERER;
		} else if (CHA > 12) {
			archetype = WARLOCK;
		} else if (WIS > 14) {
			archetype = DRUID;
		} else if (WIS > 12) {
			archetype = CLERIC;
		} else if (INT > 14) {
			archetype = WIZARD;
		} else if (INT > 12) {
			archetype = BARD;
		} else if (DEX > 14) {
			archetype = RANGER;
		} else if (DEX > 12) {
			archetype = ROGUE;
		} else if (ali.equals(Alignment.LAWFUL)) {
			archetype = MONK;
		} else if (ali.equals(Alignment.GOOD)) {
			archetype = PALADIN;
		} else if (ali.equals(Alignment.CHAOTIC)) {
			archetype = BARBARIAN;
		} else {
			archetype = FIGHTER;
		}

		return archetype;
	}

	public static float getPrimeRequisite(Actor actor) {
		float expRate = 1.00f;
		int prime;
		AbilityArray abilities = actor.getAbilities();
		Archetype job = actor.getJob();

		if (job.equals(ROGUE)) {
			prime = abilities.getDEX();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(WIZARD)) {
			prime = abilities.getINT();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(CLERIC) || job.equals(DRUID) || job.equals(MONK)) {
			prime = abilities.getWIS();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(BARD) || job.equals(SORCERER) || job.equals(WARLOCK)) {
			prime = abilities.getCHA();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else {
			prime = abilities.getSTR();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		}

		return expRate;
	}

	public static Armor selectArmor(Actor actor) {
		Armor armor = Armor.UNARMORED; // TODO

		// special case for N/PCs who get bonus proficiencies
		HashSet<Proficiency> skills = actor.getSkills();
		// EnumSet<Armor> proficiency = actor.getJob().armorProficiency;
		boolean canUseHeavy = armor.canUseHeavyArmor(skills);
		boolean canUseMedium = armor.canUseMediumArmor(skills);
		boolean canUseLight = armor.canUseLightArmor(skills);

		Archetype job = actor.getJob();
		int strength = actor.getAbilities().getSTR();
		int dexterity = actor.getAbilities().getDEX();
		int constitution = actor.getAbilities().getCON();

		if (job.equals(SORCERER) || job.equals(WARLOCK) || job.equals(WIZARD)) {
			armor = Armor.MAGE;
			// end sorcerer/warlock/wizard
		} else if (job.equals(BARBARIAN)) {
			if (constitution > 11 && dexterity > 15) {
				armor = Armor.BARBARIAN;
			} else {
				armor = Armor.BREASTPLATE;
			}
			// end barbarian
		} else if (job.equals(DRUID)) {
			if (dexterity > 15) {
				armor = Armor.STUDDED;
			} else {
				armor = Armor.HIDE;
			}
			// end druid
		} else if (job.equals(MONK)) {
			armor = Armor.MONK;
			// end monk
		} else {
			// first tries to apply heavy armor
			if (canUseHeavy && strength > 14) {
				armor = Armor.PLATE;
			} else if (canUseHeavy && strength > 12) {
				armor = Armor.CHAIN_MAIL;
			} else if (canUseHeavy) {
				armor = Armor.BREASTPLATE;
			} else {
				// tries to apply light -THEN- medium armor
				if (canUseMedium && dexterity > 15) {
					armor = Armor.STUDDED;
				} else if (canUseMedium) {
					armor = Armor.BREASTPLATE;
				} else {
					// finally tries to apply light armor
					if (canUseLight) {
						armor = Armor.STUDDED;
					}
				}
			}
			// end of applies armor
		}

		return armor;
	}
}
