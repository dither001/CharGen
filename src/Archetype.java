import java.util.EnumSet;

//BARBARIAN(Ability.STRENGTH, 12, Armor.getProficiency(Armor.Weight.BARBARIAN)),
//BARD(Ability.CHARISMA, 8, Armor.getProficiency(Armor.Weight.LIGHT)),
//CLERIC(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MEDIUM)),
//DRUID(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MEDIUM)),
//FIGHTER(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.HEAVY)),
//MONK(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MONK)),
//PALADIN(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.HEAVY)),
//RANGER(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.MEDIUM)),
//ROGUE(Ability.DEXTERITY, 8, Armor.getProficiency(Armor.Weight.LIGHT)),
//SORCERER(Ability.CHARISMA, 6, Armor.getProficiency(Armor.Weight.MAGE)),
//WARLOCK(Ability.CHARISMA, 8, Armor.getProficiency(Armor.Weight.WARLOCK)),
//WIZARD(Ability.INTELLIGENCE, 6, Armor.getProficiency(Armor.Weight.MAGE));

public enum Archetype {
	BARBARIAN(Ability.STRENGTH, 12, Armor.getProficiency(Armor.Weight.BARBARIAN)),
	BARD(Ability.CHARISMA, 8, Armor.getProficiency(Armor.Weight.LIGHT)),
	CLERIC(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MEDIUM)),
	DRUID(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MEDIUM)),
	FIGHTER(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.HEAVY)),
	MONK(Ability.WISDOM, 8, Armor.getProficiency(Armor.Weight.MONK)),
	PALADIN(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.HEAVY)),
	RANGER(Ability.STRENGTH, 10, Armor.getProficiency(Armor.Weight.MEDIUM)),
	ROGUE(Ability.DEXTERITY, 8, Armor.getProficiency(Armor.Weight.LIGHT)),
	SORCERER(Ability.CHARISMA, 6, Armor.getProficiency(Armor.Weight.MAGE)),
	WARLOCK(Ability.CHARISMA, 8, Armor.getProficiency(Armor.Weight.WARLOCK)),
	WIZARD(Ability.INTELLIGENCE, 6, Armor.getProficiency(Armor.Weight.MAGE));

	// static fields
	private static final float BEST_XP = 1.10f;
	private static final float GOOD_XP = 1.05f;
	private static final float BAD_XP = 0.90f;
	private static final float WORST_XP = 0.80f;

	// fields
	private Ability primeRequisite;
	private int hitDie;
	private EnumSet<Armor> armorProficiency;

	// constructors
	Archetype(Ability ability, int hitDie, EnumSet<Armor> armorProficiency) {
		this.primeRequisite = ability;
		this.setHitDie(hitDie);
		this.armorProficiency = armorProficiency;
	}

	// methods
	public int getHitDie() {
		return hitDie;
	}

	public void setHitDie(int hitDie) {
		this.hitDie = hitDie;
	}

	public EnumSet<Armor> getArmorProficiency() {
		return armorProficiency;
	}

	public void addArmorProficiency(Armor armor) {
		this.armorProficiency.add(armor);
	}

	public void addAllArmorProficiency(EnumSet<Armor> armor) {
		this.armorProficiency.addAll(armor);
	}

	// static methods
	public static Archetype selectArchetype(Actor actor) {
		Archetype archetype;
		Alignment ali = actor.getAli();

		int DEX, INT, WIS, CHA;
		DEX = actor.getDEX();
		INT = actor.getINT();
		WIS = actor.getWIS();
		CHA = actor.getCHA();

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
		} else if (ali.equals(Alignment.LAW)) {
			archetype = MONK;
		} else if (ali.equals(Alignment.GOOD)) {
			archetype = PALADIN;
		} else if (ali.equals(Alignment.CHAOS)) {
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
		Armor armor = Armor.NONE; // TODO

		// special case for N/PCs who get bonus proficiencies
		EnumSet<Armor> proficiency = actor.getJob().armorProficiency;
		boolean canUseHeavy = armor.canUseHeavyArmor(proficiency);
		boolean canUseMedium = armor.canUseMediumArmor(proficiency);
		boolean canUseLight = armor.canUseLightArmor(proficiency);

		Archetype job = actor.getJob();
		int strScore = actor.getSTR();
		int dexMod = actor.getDEXMod();

		if (job.equals(SORCERER) || job.equals(WARLOCK) || job.equals(WIZARD)) {
			armor = Armor.MAGE;
			// end sorcerer/warlock/wizard
		} else if (job.equals(BARBARIAN)) {
			int conMod = actor.getCONMod();
			if (conMod > 0 && dexMod > 3) {
				armor = Armor.BARBARIAN;
			} else {
				armor = Armor.BREASTPLATE;
			}
			// end barbarian
		} else if (job.equals(DRUID)) {
			if (dexMod > 3) {
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
			if (canUseHeavy && strScore > 14) {
				armor = Armor.PLATE;
			} else if (canUseHeavy && strScore > 12) {
				armor = Armor.CHAIN_MAIL;
			} else if (canUseHeavy) {
				armor = Armor.BREASTPLATE;
			} else {
				// tries to apply light -THEN- medium armor
				if (canUseMedium && dexMod > 3) {
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
