
//	BERSERKER(BARBARIAN), BEAR_TOTEM(BARBARIAN), EAGLE_TOTEM(BARBARIAN), WOLF_TOTEM(BARBARIAN), LORE_COLLEGE(BARD), VALOR_COLLEGE(BARD), 
//	DEATH(CLERIC), KNOWLEDGE(CLERIC), LIFE(CLERIC), LIGHT(CLERIC), NATURE(CLERIC), TEMPEST(CLERIC), TRICKERY(CLERIC), WAR(CLERIC), 
//	LAND_CIRCLE(DRUID), MOON_CIRCLE(DRUID), CHAMPION(FIGHTER), BATTLE_MASTER(FIGHTER), ELDRITCH_KNIGHT(FIGHTER), 
//	OPEN_HAND(MONK), SHADOW_WAY(MONK), FOUR_ELEMENTS(MONK), DEVOTION_OATH(PALADIN), ANCIENTS_OATH(PALADIN), VENGEANCE_OATH(PALADIN), OATHBREAKER(PALADIN),
//	HUNTER(RANGER), BEAST_MASTER(RANGER), THIEF(ROGUE), ASSASSIN(ROGUE), ARCANE_TRICKSTER(ROGUE),
//	DRAGON_ORIGIN(SORCERER), CHAOS_ORIGIN(SORCERER), FEY_PACT(WARLOCK), FIEND_PACT(WARLOCK), STAR_PACT(WARLOCK),
//	ABJURER(WIZARD), CONJUROR(WIZARD), DIVINER(WIZARD), ENCHANTER(WIZARD), EVOKER(WIZARD), ILLUSIONIST(WIZARD), NECROMANCER(WIZARD), TRANSMUTER(WIZARD);

public enum Class {
	BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

	// static fields
	private static final float BEST_XP = 1.10f;
	private static final float GOOD_XP = 1.05f;
	private static final float BAD_XP = 0.90f;
	private static final float WORST_XP = 0.80f;

	/*
	 * STATIC METHODS
	 * 
	 */
	public static int getHitDie(Class job) {
		int hitDie = 8;

		if (job.equals(BARBARIAN))
			hitDie = 12;
		else if (job.equals(BARD))
			hitDie = 8;
		else if (job.equals(CLERIC))
			hitDie = 8;
		else if (job.equals(DRUID))
			hitDie = 8;
		else if (job.equals(FIGHTER))
			hitDie = 10;
		else if (job.equals(MONK))
			hitDie = 8;
		else if (job.equals(PALADIN))
			hitDie = 10;
		else if (job.equals(RANGER))
			hitDie = 10;
		else if (job.equals(ROGUE))
			hitDie = 8;
		else if (job.equals(SORCERER))
			hitDie = 6;
		else if (job.equals(WARLOCK))
			hitDie = 8;
		else if (job.equals(WIZARD))
			hitDie = 6;

		return hitDie;
	}

	public static int getNumberOfSkills(Class job) {
		int jobSkills = 2;

		if (job.equals(BARBARIAN))
			jobSkills = 2;
		else if (job.equals(BARD))
			jobSkills = 3;
		else if (job.equals(CLERIC))
			jobSkills = 2;
		else if (job.equals(DRUID))
			jobSkills = 2;
		else if (job.equals(FIGHTER))
			jobSkills = 2;
		else if (job.equals(MONK))
			jobSkills = 2;
		else if (job.equals(PALADIN))
			jobSkills = 2;
		else if (job.equals(RANGER))
			jobSkills = 3;
		else if (job.equals(ROGUE))
			jobSkills = 4;
		else if (job.equals(SORCERER))
			jobSkills = 2;
		else if (job.equals(WARLOCK))
			jobSkills = 2;
		else if (job.equals(WIZARD))
			jobSkills = 2;

		return jobSkills;
	}

	public static Actor.Ability getPrimaryAbility(Class job) {
		Actor.Ability ability = Actor.Ability.STRENGTH;

		if (job.equals(BARBARIAN))
			ability = Actor.Ability.STRENGTH;
		else if (job.equals(BARD))
			ability = Actor.Ability.CHARISMA;
		else if (job.equals(CLERIC))
			ability = Actor.Ability.WISDOM;
		else if (job.equals(DRUID))
			ability = Actor.Ability.WISDOM;
		else if (job.equals(FIGHTER))
			ability = Actor.Ability.STRENGTH;
		else if (job.equals(MONK))
			ability = Actor.Ability.DEXTERITY;
		else if (job.equals(PALADIN))
			ability = Actor.Ability.STRENGTH;
		else if (job.equals(RANGER))
			ability = Actor.Ability.DEXTERITY;
		else if (job.equals(ROGUE))
			ability = Actor.Ability.DEXTERITY;
		else if (job.equals(SORCERER))
			ability = Actor.Ability.CHARISMA;
		else if (job.equals(WARLOCK))
			ability = Actor.Ability.CHARISMA;
		else if (job.equals(WIZARD))
			ability = Actor.Ability.INTELLIGENCE;

		return ability;
	}


	public static Class selectClass(Actor actor) {
		Class job;
		Actor.Alignment ali = actor.getAlignment();

		int dexterity, intelligence, wisdom, charisma;
		dexterity = actor.getDexterity();
		intelligence = actor.getIntelligence();
		wisdom = actor.getWisdom();
		charisma = actor.getCharisma();

		int dice = Dice.roll(3);
		if (charisma > 13 && dice == 1) {
			job = BARD;
		} else if (charisma > 13 && dice == 2) {
			job = SORCERER;
		} else if (charisma > 13 && dice == 3) {
			job = WARLOCK;
		} else if (wisdom > 13 && dice == 1) {
			job = CLERIC;
		} else if (wisdom > 13 && dice == 2) {
			job = DRUID;
		} else if (wisdom > 13 && dice == 3) {
			job = MONK;
		} else if (intelligence > 13) {
			job = WIZARD;
		} else if (dexterity > 13) {
			job = RANGER;
		} else if (ali.equals(Actor.Alignment.LAWFUL) || ali.equals(Actor.Alignment.GOOD)) {
			job = PALADIN;
		} else if (ali.equals(Actor.Alignment.CHAOTIC) && dice < 3) {
			job = BARBARIAN;
		} else if (dice < 3){
			job = FIGHTER;
		} else {
			job = ROGUE;
		}

		return job;
	}

	public static float getPrimeRequisite(Actor actor) {
		float expRate = 1.00f;
		int prime;
		Class job = actor.getJob();

		if (job.equals(RANGER) || job.equals(ROGUE)) {
			prime = actor.getDexterity();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(WIZARD)) {
			prime = actor.getIntelligence();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(CLERIC) || job.equals(DRUID) || job.equals(MONK)) {
			prime = actor.getWisdom();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else if (job.equals(BARD) || job.equals(SORCERER) || job.equals(WARLOCK)) {
			prime = actor.getCharisma();
			expRate = (prime > 14) ? BEST_XP : (prime > 12) ? GOOD_XP : expRate;
			expRate = (prime < 6) ? WORST_XP : (prime < 9) ? BAD_XP : expRate;
		} else {
			prime = actor.getStrength();
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
