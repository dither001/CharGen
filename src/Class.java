
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

//	BERSERKER(BARBARIAN), BEAR_TOTEM(BARBARIAN), EAGLE_TOTEM(BARBARIAN), WOLF_TOTEM(BARBARIAN), LORE_COLLEGE(BARD), VALOR_COLLEGE(BARD), 
//	DEATH(CLERIC), KNOWLEDGE(CLERIC), LIFE(CLERIC), LIGHT(CLERIC), NATURE(CLERIC), TEMPEST(CLERIC), TRICKERY(CLERIC), WAR(CLERIC), 
//	LAND_CIRCLE(DRUID), MOON_CIRCLE(DRUID), CHAMPION(FIGHTER), BATTLE_MASTER(FIGHTER), ELDRITCH_KNIGHT(FIGHTER), 
//	OPEN_HAND(MONK), SHADOW_WAY(MONK), FOUR_ELEMENTS(MONK), DEVOTION_OATH(PALADIN), ANCIENTS_OATH(PALADIN), VENGEANCE_OATH(PALADIN), OATHBREAKER(PALADIN),
//	HUNTER(RANGER), BEAST_MASTER(RANGER), THIEF(ROGUE), ASSASSIN(ROGUE), ARCANE_TRICKSTER(ROGUE),
//	DRAGON_ORIGIN(SORCERER), CHAOS_ORIGIN(SORCERER), FEY_PACT(WARLOCK), FIEND_PACT(WARLOCK), STAR_PACT(WARLOCK),
//	ABJURER(WIZARD), CONJUROR(WIZARD), DIVINER(WIZARD), ENCHANTER(WIZARD), EVOKER(WIZARD), ILLUSIONIST(WIZARD), NECROMANCER(WIZARD), TRANSMUTER(WIZARD);

public enum Class {
	BARBARIAN(Ability.STRENGTH, 12, 2),
	BARD(Ability.INTELLIGENCE, 8, 3),
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
	private int experience;
	private int level;
	private float expRate;

	/*
	 * TODO - prime requisite was added after general implementation of the
	 * experience rate methods; eventually I want to have prime requisite be based
	 * more upon the abilities put out in the Ability class than (necessarily) the
	 * classic six ability scores of Dungeons & Dragons
	 */
	private int hitDie;
	private int numberOfSkills;

	// constructors
	Class(Ability ability, int hitDie, int numberOfSkills) {
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
	public static Class selectClass(Actor actor) {
		Class archetype;
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
		Class job = actor.getJob();

		if (job.equals(RANGER) || job.equals(ROGUE)) {
			prime = abilities.getDEX();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(BARD) || job.equals(WIZARD)) {
			prime = abilities.getINT();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(CLERIC) || job.equals(DRUID)) {
			prime = abilities.getWIS();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(SORCERER) || job.equals(WARLOCK)) {
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

	public enum Subclass {
		BERSERKER(BARBARIAN), BEAR_TOTEM(BARBARIAN), EAGLE_TOTEM(BARBARIAN), WOLF_TOTEM(BARBARIAN), LORE_COLLEGE(BARD), VALOR_COLLEGE(BARD), 
		DEATH(CLERIC), KNOWLEDGE(CLERIC), LIFE(CLERIC), LIGHT(CLERIC), NATURE(CLERIC), TEMPEST(CLERIC), TRICKERY(CLERIC), WAR(CLERIC), 
		LAND_CIRCLE(DRUID), MOON_CIRCLE(DRUID), CHAMPION(FIGHTER), BATTLE_MASTER(FIGHTER), ELDRITCH_KNIGHT(FIGHTER), 
		OPEN_HAND(MONK), SHADOW_WAY(MONK), FOUR_ELEMENTS(MONK), DEVOTION_OATH(PALADIN), ANCIENTS_OATH(PALADIN), VENGEANCE_OATH(PALADIN), OATHBREAKER(PALADIN),
		HUNTER(RANGER), BEAST_MASTER(RANGER), THIEF(ROGUE), ASSASSIN(ROGUE), ARCANE_TRICKSTER(ROGUE),
		DRAGON_ORIGIN(SORCERER), CHAOS_ORIGIN(SORCERER), FEY_PACT(WARLOCK), FIEND_PACT(WARLOCK), STAR_PACT(WARLOCK),
		ABJURER(WIZARD), CONJUROR(WIZARD), DIVINER(WIZARD), ENCHANTER(WIZARD), EVOKER(WIZARD), ILLUSIONIST(WIZARD), NECROMANCER(WIZARD), TRANSMUTER(WIZARD);

		// fields
		private final Class job;

		// arrays
		private static final Subclass[] barbarians = { BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM };
		private static final Subclass[] bards = { LORE_COLLEGE, VALOR_COLLEGE };
		private static final Subclass[] clerics = { DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR };
		private static final Subclass[] druids = { LAND_CIRCLE, MOON_CIRCLE };
		private static final Subclass[] fighters = { CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT };
		private static final Subclass[] monks = { OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS };
		private static final Subclass[] paladins = { DEVOTION_OATH, ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER };
		private static final Subclass[] rangers = { HUNTER, BEAST_MASTER };
		private static final Subclass[] rogues = { THIEF, ASSASSIN, ARCANE_TRICKSTER };
		private static final Subclass[] sorcerers = { DRAGON_ORIGIN, CHAOS_ORIGIN };
		private static final Subclass[] warlocks = { FEY_PACT, FIEND_PACT, STAR_PACT };
		private static final Subclass[] wizards = { ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER, ILLUSIONIST,
				NECROMANCER, TRANSMUTER };

		// constructors
		private Subclass(Class job) {
			this.job = job;
		}

		// methods
		public boolean hasSameJob(Class job) {
			return job.equals(this.job);
		}

		public boolean hasSameJob(Subclass Subclass) {
			return Subclass.job.equals(this.job);
		}

		public Class getJob() {
			return job;
		}

		// static methods
		public static Subclass selectSubclass(Actor actor) {
			Class job = actor.getJob();

			Subclass Subclass = null;
			int dice;

			if (job.equals(Class.BARBARIAN)) {
				dice = Dice.roll(barbarians.length) - 1;
				Subclass = barbarians[dice];
			} else if (job.equals(Class.BARD)) {
				dice = Dice.roll(bards.length) - 1;
				Subclass = bards[dice];
			} else if (job.equals(Class.CLERIC)) {
				dice = Dice.roll(clerics.length) - 1;
				Subclass = clerics[dice];
			} else if (job.equals(Class.DRUID)) {
				dice = Dice.roll(druids.length) - 1;
				Subclass = druids[dice];
			} else if (job.equals(Class.FIGHTER)) {
				dice = Dice.roll(100);

				if (dice < 51) {
					Subclass = fighters[0]; // 50% of fighters are champions
				} else if (dice < 81) {
					Subclass = fighters[1]; // 30% of fighters are battle masters
				} else {
					Subclass = fighters[2]; // 20% of fighters are eldritch knights
				}
			} else if (job.equals(Class.MONK)) {
				dice = Dice.roll(monks.length) - 1;
				Subclass = monks[dice];
			} else if (job.equals(Class.PALADIN)) {
				dice = Dice.roll(paladins.length) - 1;
				Subclass = paladins[dice];
			} else if (job.equals(Class.RANGER)) {
				dice = Dice.roll(rangers.length) - 1;
				Subclass = rangers[dice];
			} else if (job.equals(Class.ROGUE)) {
				dice = Dice.roll(100);

				if (dice < 51) {
					Subclass = rogues[0]; // 50% of rogues are thieves
				} else if (dice < 81) {
					Subclass = rogues[1]; // 30% of rogues are assassins
				} else {
					Subclass = rogues[2]; // 20% of rogues are arcane tricksters
				}
			} else if (job.equals(Class.SORCERER)) {
				dice = Dice.roll(sorcerers.length) - 1;
				Subclass = sorcerers[dice];
			} else if (job.equals(Class.WARLOCK)) {
				dice = Dice.roll(100);

				if (dice < 41) {
					Subclass = warlocks[1]; // 40% of warlocks have fiend pacts
				} else if (dice < 71) {
					Subclass = warlocks[0]; // 30% of warlocks have fey pacts
				} else {
					Subclass = warlocks[2]; // 30% of warlocks have star pacts
				}
			} else if (job.equals(Class.WIZARD)) {
				dice = Dice.roll(100);

				if (dice < 41) {
					Subclass = wizards[2]; // 40% of wizards are diviners
				} else if (dice < 56) {
					Subclass = wizards[4]; // 15% of wizards are evokers
				} else if (dice < 71) {
					Subclass = wizards[5]; // 15% of wizards are illusionists
				} else if (dice < 81) {
					Subclass = wizards[1]; // 10% of wizards are conjurors
				} else if (dice < 86) {
					Subclass = wizards[0]; // 5% of wizards are abjurers
				} else if (dice < 91) {
					Subclass = wizards[3]; // 5% of wizards are enchanters
				} else if (dice < 96) {
					Subclass = wizards[6]; // 5% of wizards are necromancers
				} else {
					Subclass = wizards[7]; // 5% of wizards are transmuters
				}
			}

			return Subclass;
		}

		public static Class getJob(Subclass Subclass) {
			return Subclass.job;
		}
	}

}
