import java.util.EnumSet;

public enum Class implements Option {
	BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

	/*
	 * STATIC FIELDS
	 * 
	 */
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

		int dice = Dice.roll(4);
		if (charisma > 11 && dice == 1)
			job = BARD;
		else if (charisma > 11 && dice == 2)
			job = SORCERER;
		else if (charisma > 11 && dice == 3)
			job = WARLOCK;
		else if (wisdom > 11 && dice == 1)
			job = CLERIC;
		else if (wisdom > 11 && dice == 2)
			job = DRUID;
		else if (wisdom > 11 && dice == 3)
			job = MONK;
		else if (intelligence > 11 && dice < 3)
			job = WIZARD;
		else if (dexterity > 11 && dice < 3)
			job = RANGER;
		else if (ali.equals(Actor.Alignment.GOOD))
			job = PALADIN;
		else if (ali.equals(Actor.Alignment.CHAOTIC))
			job = BARBARIAN;
		else if (ali.equals(Actor.Alignment.LAWFUL) || dice < 4)
			job = FIGHTER;
		else
			job = ROGUE;

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
		// barbarians
		BERSERKER(BARBARIAN), BEAR_TOTEM(BARBARIAN), EAGLE_TOTEM(BARBARIAN), WOLF_TOTEM(BARBARIAN),
		// bards
		LORE_COLLEGE(BARD), VALOR_COLLEGE(BARD),
		// clerics
		DEATH(CLERIC), KNOWLEDGE(CLERIC), LIFE(CLERIC), LIGHT(CLERIC), NATURE(CLERIC), TEMPEST(CLERIC), TRICKERY(
				CLERIC), WAR(CLERIC),
		// druids
		LAND_CIRCLE(DRUID), MOON_CIRCLE(DRUID),
		// fighters
		CHAMPION(FIGHTER), BATTLE_MASTER(FIGHTER), ELDRITCH_KNIGHT(FIGHTER),
		// monks
		OPEN_HAND(MONK), SHADOW_WAY(MONK), FOUR_ELEMENTS(MONK),
		// paladins
		DEVOTION_OATH(PALADIN), ANCIENTS_OATH(PALADIN), VENGEANCE_OATH(PALADIN), OATHBREAKER(PALADIN),
		// rangers
		HUNTER(RANGER), BEAST_MASTER(RANGER),
		// rogues
		THIEF(ROGUE), ASSASSIN(ROGUE), ARCANE_TRICKSTER(ROGUE),
		// sorcerers
		DRAGON_ORIGIN(SORCERER), CHAOS_ORIGIN(SORCERER),
		// warlocks
		FEY_PACT(WARLOCK), FIEND_PACT(WARLOCK), STAR_PACT(WARLOCK),
		// wizards
		ABJURER(WIZARD), CONJUROR(WIZARD), DIVINER(WIZARD), ENCHANTER(WIZARD), EVOKER(WIZARD), ILLUSIONIST(
				WIZARD), NECROMANCER(WIZARD), TRANSMUTER(WIZARD);

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

				if (dice < 21 && actor.getIntelligence() > 9) {
					Subclass = fighters[2]; // 20% of fighters are eldritch knights
				} else if (dice < 51) {
					Subclass = fighters[1]; // 30% of fighters are battle masters
				} else {
					Subclass = fighters[0]; // 50% of fighters are champions
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

				if (dice < 21 && actor.getIntelligence() > 9) {
					Subclass = rogues[2]; // 20% of rogues are arcane tricksters
				} else if (dice < 51) {
					Subclass = rogues[1]; // 30% of rogues are assassins
				} else {
					Subclass = rogues[0]; // 50% of rogues are thieves
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

	/*
	 * SETUP CLASS FEATURES & UPDATE CLASS FEATURES
	 * 
	 */
	public static void updateClassFeatures(Actor actor) {
		EnumSet<Feature> features;
		if (actor.getFeatures() == null)
			features = EnumSet.noneOf(Feature.class);
		else
			features = actor.getFeatures();

		// TODO
		Class job = actor.getJob();
		if (job.equals(Class.BARBARIAN)) {
			features.addAll(barbarian(actor));
		} else if (job.equals(Class.BARD)) {
			features.addAll(bard(actor));
		} else if (job.equals(Class.CLERIC)) {
			features.addAll(cleric(actor));
		} else if (job.equals(Class.DRUID)) {
			features.addAll(druid(actor));
		} else if (job.equals(Class.FIGHTER)) {
			features.addAll(fighter(actor));
			// } else if (job.equals(Class.MONK)) {
			// features.addAll(monk(actor));
			// } else if (job.equals(Class.PALADIN)) {
			// features.addAll(paladin(actor));
			// } else if (job.equals(Class.RANGER)) {
			// features.addAll(ranger(actor));
			// } else if (job.equals(Class.ROGUE)) {
			// features.addAll(rogue(actor));
			// } else if (job.equals(Class.SORCERER)) {
			// features.addAll(sorcerer(actor));
			// } else if (job.equals(Class.WARLOCK)) {
			// features.addAll(warlock(actor));
			// } else if (job.equals(Class.WIZARD)) {
			// features.addAll(wizard(actor));
		}

		actor.setFeatures(features);
	}

	public static void setupClassFeatures(Actor actor) {
		EnumSet<Feature> features;
		if (actor.getFeatures() == null)
			features = EnumSet.noneOf(Feature.class);
		else
			features = actor.getFeatures();

		// TODO
		Class job = actor.getJob();
		if (job.equals(Class.BARBARIAN)) {
			features.addAll(barbarian(actor));
		} else if (job.equals(Class.BARD)) {
			features.addAll(bard(actor));
		} else if (job.equals(Class.CLERIC)) {
			features.addAll(cleric(actor));
		} else if (job.equals(Class.DRUID)) {
			features.addAll(druid(actor));
		} else if (job.equals(Class.FIGHTER)) {
			features.addAll(fighter(actor));
			// } else if (job.equals(Class.MONK)) {
			// features.addAll(monk(actor));
			// } else if (job.equals(Class.PALADIN)) {
			// features.addAll(paladin(actor));
			// } else if (job.equals(Class.RANGER)) {
			// features.addAll(ranger(actor));
			// } else if (job.equals(Class.ROGUE)) {
			// features.addAll(rogue(actor));
			// } else if (job.equals(Class.SORCERER)) {
			// features.addAll(sorcerer(actor));
			// } else if (job.equals(Class.WARLOCK)) {
			// features.addAll(warlock(actor));
			// } else if (job.equals(Class.WIZARD)) {
			// features.addAll(wizard(actor));
		}

		actor.setFeatures(features);
	}

	/*
	 * BARBARIAN ADVANCEMENT
	 * 
	 */
	public static EnumSet<Feature> barbarian(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		if (level == 1) {
			set.add(Feature.RAGE);
			set.add(Feature.RAGE_PER_DAY_2);
			set.add(Feature.RAGE_BONUS_2);
			set.add(Feature.UNARMORED_DEFENSE_BARBARIAN);
		} else if (level == 2) {
			set.add(Feature.RECKLESS_ATTACK);
			set.add(Feature.DANGER_SENSE);
		} else if (level == 3) {
			// PRIMAL PATH
			set.add(Feature.RAGE_PER_DAY_3);
			if (archetype.equals(Class.Subclass.BERSERKER)) {
				set.add(Feature.FRENZY);
			} else if (archetype.equals(Class.Subclass.BEAR_TOTEM)) {
				set.add(Feature.SPIRIT_SEEKER);
				set.add(Feature.BEAR_SPIRIT_3);
			} else if (archetype.equals(Class.Subclass.EAGLE_TOTEM)) {
				set.add(Feature.SPIRIT_SEEKER);
				set.add(Feature.EAGLE_SPIRIT_3);
			} else if (archetype.equals(Class.Subclass.WOLF_TOTEM)) {
				set.add(Feature.SPIRIT_SEEKER);
				set.add(Feature.WOLF_SPIRIT_3);
			}
		} else if (level == 4) {
			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {
			set.add(Feature.EXTRA_ATTACK_1);
			set.add(Feature.FAST_MOVEMENT);
			set.add(Feature.RAGE_PER_DAY_4);
		} else if (level == 6) {
			// PRIMAL PATH
			if (archetype.equals(Class.Subclass.BERSERKER)) {
				set.add(Feature.MINDLESS_RAGE);
			} else if (archetype.equals(Class.Subclass.BEAR_TOTEM)) {
				set.add(Feature.BEAR_ASPECT_6);
			} else if (archetype.equals(Class.Subclass.EAGLE_TOTEM)) {
				set.add(Feature.EAGLE_ASPECT_6);
			} else if (archetype.equals(Class.Subclass.WOLF_TOTEM)) {
				set.add(Feature.WOLF_ASPECT_6);
			}
		} else if (level == 7) {
			set.add(Feature.FERAL_INSTINCT);
		} else if (level == 8) {
			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {
			set.add(Feature.BRUTAL_CRITICAL_1);
			set.add(Feature.RAGE_BONUS_3);
		} else if (level == 10) {
			// PRIMAL PATH
			if (archetype.equals(Class.Subclass.BERSERKER)) {
				set.add(Feature.INTIMIDATING_PRESENCE);
			} else if (archetype.equals(Class.Subclass.BEAR_TOTEM)) {
				set.add(Feature.SPIRIT_WALKER);
			} else if (archetype.equals(Class.Subclass.EAGLE_TOTEM)) {
				set.add(Feature.SPIRIT_WALKER);
			} else if (archetype.equals(Class.Subclass.WOLF_TOTEM)) {
				set.add(Feature.SPIRIT_WALKER);
			}
		} else if (level == 11) {
			set.add(Feature.RELENTLESS_RAGE);
		} else if (level == 12) {
			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
			set.add(Feature.RAGE_PER_DAY_5);
		} else if (level == 13) {
			set.add(Feature.BRUTAL_CRITICAL_2);
		} else if (level == 14) {
			// PRIMAL PATH
			if (archetype.equals(Class.Subclass.BERSERKER)) {
				set.add(Feature.RETALIATION);
			} else if (archetype.equals(Class.Subclass.BEAR_TOTEM)) {
				set.add(Feature.BEAR_ATTUNEMENT_14);
			} else if (archetype.equals(Class.Subclass.EAGLE_TOTEM)) {
				set.add(Feature.EAGLE_ATTUNEMENT_14);
			} else if (archetype.equals(Class.Subclass.WOLF_TOTEM)) {
				set.add(Feature.WOLF_ATTUNEMENT_14);
			}
		} else if (level == 15) {
			set.add(Feature.PERSISTENT_RAGE);
		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
			set.add(Feature.RAGE_BONUS_4);
		} else if (level == 17) {
			set.add(Feature.BRUTAL_CRITICAL_3);
			set.add(Feature.RAGE_PER_DAY_6);
		} else if (level == 18) {
			set.add(Feature.INDOMITABLE_MIGHT);
		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {
			set.add(Feature.PRIMAL_CHAMPION);
			set.add(Feature.RAGE_PER_DAY_99);
		}

		return set;
	}

	/*
	 * BARD ADVANCEMENT
	 * 
	 */
	public static EnumSet<Feature> bard(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		EnumSet<Skill> skills = actor.getSkills();
		int level = actor.getLevel();

		Class job = actor.getJob();
		EnumSet<Spell> spellsKnown;
		if (actor.getSpellsKnown() == null)
			spellsKnown = EnumSet.noneOf(Spell.class);
		else
			spellsKnown = actor.getSpellsKnown();

		if (level == 1) {
			set.add(Feature.INSPIRATION_D6);
		} else if (level == 2) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.JACK_OF_ALL_TRADES);
			set.add(Feature.SONG_OF_REST_D6);
		} else if (level == 3) {
			Spell.addToSpellsKnown(level, job, spellsKnown);

			// BARD COLLEGE
			set.addAll(addRandomExpertise(2, actor));
			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				skills.add(Skill.testRandomSkill(actor));
				skills.add(Skill.testRandomSkill(actor));
				skills.add(Skill.testRandomSkill(actor));
				set.add(Feature.CUTTING_WORDS);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				actor.getArmorProficiency().addAll(Armor.getMediumArmorList());
				actor.getWeaponProficiency().addAll(Weapon.getMartialWeaponList());
				actor.getWeaponProficiency().add(Weapon.SHIELD);
				set.add(Feature.COMBAT_INSPIRATION);
			}

		} else if (level == 4) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);
			Spell.addToSpellsKnown(level, job, spellsKnown);

			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {
			Spell.addToSpellsKnown(level, job, spellsKnown);

			set.add(Feature.INSPIRATION_D8);
			set.add(Feature.FONT_OF_INSPIRATION);
		} else if (level == 6) {
			Spell.addToSpellsKnown(level, job, spellsKnown);

			// BARD COLLEGE
			set.add(Feature.COUNTERCHARM);
			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				// magical secrets
				set.add(Feature.MAGICAL_SECRETS_6);
				Spell.addMagicalSecret(3, actor);
				Spell.addMagicalSecret(3, actor);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				set.add(Feature.EXTRA_ATTACK_1);
			}

		} else if (level == 7) {
			// spells and nothing else
			Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 8) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.SONG_OF_REST_D8);
		} else if (level == 10) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);

			// magical secrets
			set.addAll(addRandomExpertise(2, actor));
			set.add(Feature.INSPIRATION_D10);
			set.add(Feature.MAGICAL_SECRETS_10);
			Spell.addMagicalSecret(5, actor);
			Spell.addMagicalSecret(5, actor);
		} else if (level == 11) {
			// spells and nothing else
			Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 12) {
			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.SONG_OF_REST_D10);
		} else if (level == 14) {
			// BARD COLLEGE
			// magical secrets
			set.add(Feature.MAGICAL_SECRETS_14);
			Spell.addMagicalSecret(7, actor);
			Spell.addMagicalSecret(7, actor);

			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				set.add(Feature.PEERLESS_SKILL);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				set.add(Feature.BATTLE_MAGIC);
			}

		} else if (level == 15) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.INSPIRATION_D12);
		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {
			Spell.addToSpellsKnown(level, job, spellsKnown);
			set.add(Feature.SONG_OF_REST_D12);
		} else if (level == 18) {
			// magical secrets
			set.add(Feature.MAGICAL_SECRETS_18);
			Spell.addMagicalSecret(9, actor);
			Spell.addMagicalSecret(9, actor);
		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {
			set.add(Feature.SUPERIOR_INSPIRATION);
		}

		return set;
	}

	/*
	 * CLERIC ADVANCEMENT
	 * 
	 */
	public static EnumSet<Feature> cleric(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		Class job = actor.getJob();
		EnumSet<Spell> spellsKnown;
		if (actor.getSpellsKnown() == null)
			spellsKnown = EnumSet.noneOf(Spell.class);
		else
			spellsKnown = actor.getSpellsKnown();

		if (level == 1) {
			// divine domain
			if (archetype.equals(Class.Subclass.DEATH)) {
				//
				actor.getWeaponProficiency().addAll(Weapon.getMartialWeaponList());
				set.add(Feature.DIVINE_DOMAIN_DEATH);
				set.add(Feature.REAPER);
				Spell spell = Dice.randomFromSet(Spell.allSpellsOfSchool(0, Spell.School.NECROMANCY));
				Spell.addCantripOrElse(spell, CLERIC, actor);
			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				//
				Skill[] array = { Skill.ARCANA, Skill.HISTORY, Skill.NATURE, Skill.RELIGION };
				set.add(Feature.DIVINE_DOMAIN_KNOWLEDGE);
				set.add(Feature.BLESSINGS_OF_KNOWLEDGE);
				Actor.Language.learnNonsecretLanguage(actor);
				Actor.Language.learnNonsecretLanguage(actor);
				Skill.testSkillFromArray(array, actor);
				Skill.testSkillFromArray(array, actor);
			} else if (archetype.equals(Class.Subclass.LIFE)) {
				//
				actor.getArmorProficiency().addAll(Armor.getHeavyArmorList());
				set.add(Feature.DIVINE_DOMAIN_LIFE);
				set.add(Feature.DISCIPLE_OF_LIFE);
			} else if (archetype.equals(Class.Subclass.LIGHT)) {
				//
				Spell.addCantripOrElse(Spell.LIGHT, CLERIC, actor);
				set.add(Feature.DIVINE_DOMAIN_LIGHT);
				set.add(Feature.WARDING_FLARE);
			} else if (archetype.equals(Class.Subclass.NATURE)) {
				//
				Spell.addCantripKnown(DRUID, spellsKnown);
				set.add(Feature.DIVINE_DOMAIN_NATURE);
				actor.getArmorProficiency().addAll(Armor.getHeavyArmorList());
			} else if (archetype.equals(Class.Subclass.TEMPEST)) {
				//
				actor.getWeaponProficiency().addAll(Weapon.getMartialWeaponList());
				actor.getArmorProficiency().addAll(Armor.getHeavyArmorList());
				set.add(Feature.DIVINE_DOMAIN_TEMPEST);
				set.add(Feature.WRATH_OF_THE_STORM);
			} else if (archetype.equals(Class.Subclass.TRICKERY)) {
				//
				set.add(Feature.DIVINE_DOMAIN_TRICKERY);
				set.add(Feature.BLESSING_OF_THE_TRICKSTER);
			} else if (archetype.equals(Class.Subclass.WAR)) {
				//
				actor.getWeaponProficiency().addAll(Weapon.getMartialWeaponList());
				actor.getArmorProficiency().addAll(Armor.getHeavyArmorList());
				set.add(Feature.DIVINE_DOMAIN_WAR);
				set.add(Feature.WAR_PRIEST);
			}

		} else if (level == 2) {
			set.add(Feature.CHANNEL_DIVINITY_1);
			// divine domain
			if (archetype.equals(Class.Subclass.DEATH)) {
				//
				set.add(Feature.TOUCH_OF_DEATH);
			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				//
				set.add(Feature.KNOWLEDGE_OF_THE_AGES);
			} else if (archetype.equals(Class.Subclass.LIFE)) {
				//
				set.add(Feature.PRESERVE_LIFE);
			} else if (archetype.equals(Class.Subclass.LIGHT)) {
				//
				set.add(Feature.RADIANCE_OF_THE_DAWN);
			} else if (archetype.equals(Class.Subclass.NATURE)) {
				//
				set.add(Feature.CHARM_ANIMALS_AND_PLANTS);
			} else if (archetype.equals(Class.Subclass.TEMPEST)) {
				//
				set.add(Feature.DESTRUCTIVE_WRATH);
			} else if (archetype.equals(Class.Subclass.TRICKERY)) {
				//
				set.add(Feature.INVOKE_DUPLICITY);
			} else if (archetype.equals(Class.Subclass.WAR)) {
				//
				set.add(Feature.GUIDED_STRIKE);
			}

		} else if (level == 3) {

		} else if (level == 4) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);

			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {
			set.add(Feature.DESTROY_UNDEAD_5);

		} else if (level == 6) {
			set.add(Feature.CHANNEL_DIVINITY_2);
			// domain
			if (archetype.equals(Class.Subclass.DEATH)) {
				//
				set.add(Feature.INESCAPABLE_DESTRUCTION);
			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				//
				set.add(Feature.READ_THOUGHTS);
			} else if (archetype.equals(Class.Subclass.LIFE)) {
				//
				set.add(Feature.BLESSED_HEALER);
			} else if (archetype.equals(Class.Subclass.LIGHT)) {
				//
				set.add(Feature.IMPROVED_FLARE);
			} else if (archetype.equals(Class.Subclass.NATURE)) {
				//
				set.add(Feature.DAMPEN_ELEMENTS);
			} else if (archetype.equals(Class.Subclass.TEMPEST)) {
				//
				set.add(Feature.THUNDERBOLT_STRIKE);
			} else if (archetype.equals(Class.Subclass.TRICKERY)) {
				//
				set.add(Feature.CLOAK_OF_SHADOWS);
			} else if (archetype.equals(Class.Subclass.WAR)) {
				//
				set.add(Feature.WAR_GODS_BLESSING);
			}

		} else if (level == 7) {

		} else if (level == 8) {
			set.add(Feature.DESTROY_UNDEAD_8);
			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
			if (archetype.equals(Class.Subclass.DEATH)) {
				//
				set.add(Feature.DIVINE_STRIKE_DEATH);
			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				//
				set.add(Feature.POTENT_SPELLCASTING_CLERIC);
			} else if (archetype.equals(Class.Subclass.LIFE)) {
				//
				set.add(Feature.DIVINE_STRIKE_LIFE);
			} else if (archetype.equals(Class.Subclass.LIGHT)) {
				//
				set.add(Feature.POTENT_SPELLCASTING_CLERIC);
			} else if (archetype.equals(Class.Subclass.NATURE)) {
				//
				set.add(Feature.DIVINE_STRIKE_NATURE);
			} else if (archetype.equals(Class.Subclass.TEMPEST)) {
				//
				set.add(Feature.DIVINE_STRIKE_TEMPEST);
			} else if (archetype.equals(Class.Subclass.TRICKERY)) {
				//
				set.add(Feature.DIVINE_STRIKE_TRICKERY);
			} else if (archetype.equals(Class.Subclass.WAR)) {
				//
				set.add(Feature.DIVINE_STRIKE_WAR);
			}

		} else if (level == 9) {

		} else if (level == 10) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);
			set.add(Feature.DIVINE_INTERVENTION_10);

		} else if (level == 11) {
			set.add(Feature.DESTROY_UNDEAD_11);

		} else if (level == 12) {
			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {

		} else if (level == 14) {
			set.add(Feature.DESTROY_UNDEAD_14);

		} else if (level == 15) {

		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {
			set.add(Feature.DESTROY_UNDEAD_17);
			if (archetype.equals(Class.Subclass.DEATH)) {
				//
				set.add(Feature.IMPROVED_REAPER);
			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				//
				set.add(Feature.VISIONS_OF_THE_PAST);
			} else if (archetype.equals(Class.Subclass.LIFE)) {
				//
				set.add(Feature.SUPREME_HEALING);
			} else if (archetype.equals(Class.Subclass.LIGHT)) {
				//
				set.add(Feature.POTENT_SPELLCASTING_CLERIC);
			} else if (archetype.equals(Class.Subclass.NATURE)) {
				//
				set.add(Feature.MASTER_OF_NATURE);
			} else if (archetype.equals(Class.Subclass.TEMPEST)) {
				//
				set.add(Feature.STORMBORN);
			} else if (archetype.equals(Class.Subclass.TRICKERY)) {
				//
				set.add(Feature.IMPROVED_DUPLICITY);
			} else if (archetype.equals(Class.Subclass.WAR)) {
				//
				set.add(Feature.AVATAR_OF_BATTLE);
			}

		} else if (level == 18) {
			set.add(Feature.CHANNEL_DIVINITY_3);

		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {
			set.add(Feature.DIVINE_INTERVENTION_20);

		}

		return set;
	}

	/*
	 * DRUID ADVANCEMENT
	 * 
	 */
	public static EnumSet<Feature> druid(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		Class job = actor.getJob();
		EnumSet<Spell> spellsKnown;
		if (actor.getSpellsKnown() == null)
			spellsKnown = EnumSet.noneOf(Spell.class);
		else
			spellsKnown = actor.getSpellsKnown();

		if (level == 1) {
			Actor.Language.addLanguage(Actor.Language.DRUIDIC, actor);
			set.add(Feature.RITUAL_CASTING_DRUID);

		} else if (level == 2) {
			// moon circle gets one wild shape, everyone else gets another
			if (archetype.equals(Class.Subclass.MOON_CIRCLE)) {
				set.add(Feature.MOON_SHAPE_2);
				set.add(Feature.COMBAT_WILD_SHAPE);

			} else {
				set.add(Feature.WILD_SHAPE_2);

			}

			// land circle gets a bonus cantrip
			if (archetype.equals(Class.Subclass.LAND_CIRCLE))
				Spell.addCantripKnown(job, spellsKnown);

		} else if (level == 3) {
			if (archetype.equals(Class.Subclass.LAND_CIRCLE))
				set.addAll(Dice.addToSetFromArray(1, set, Feature.CIRCLE_SPELLS));

		} else if (level == 4) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);

			if (archetype.equals(Class.Subclass.MOON_CIRCLE) != true)
				set.add(Feature.WILD_SHAPE_4);

			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {

		} else if (level == 6) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE)) {
				set.add(Feature.MOON_SHAPE_6);
				set.add(Feature.PRIMAL_STRIKE);

			} else if (archetype.equals(Class.Subclass.LAND_CIRCLE))
				set.add(Feature.LANDS_STRIDE);

		} else if (level == 7) {

		} else if (level == 8) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE) != true)
				set.add(Feature.WILD_SHAPE_8);

			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE))
				set.add(Feature.MOON_SHAPE_9);

		} else if (level == 10) {
			// cantrip
			Spell.addCantripKnown(job, spellsKnown);

			if (archetype.equals(Class.Subclass.MOON_CIRCLE)) {
				set.add(Feature.ELEMENTAL_SHAPE);

			} else if (archetype.equals(Class.Subclass.LAND_CIRCLE)) {
				set.add(Feature.NATURES_WARD);

			}

		} else if (level == 11) {

		} else if (level == 12) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE))
				set.add(Feature.MOON_SHAPE_12);

			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {

		} else if (level == 14) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE)) {
				set.add(Feature.THOUSAND_FORMS);
			} else if (archetype.equals(Class.Subclass.LAND_CIRCLE))
				set.add(Feature.NATURES_SANCTUARY);

		} else if (level == 15) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE))
				set.add(Feature.MOON_SHAPE_15);

		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {

		} else if (level == 18) {
			if (archetype.equals(Class.Subclass.MOON_CIRCLE))
				set.add(Feature.MOON_SHAPE_18);

			set.add(Feature.TIMELESS_BODY);
			set.add(Feature.BEAST_SPELLS);
		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {
			set.add(Feature.ARCHDRUID);

		}

		return set;
	}

	/*
	 * FIGHTER ADVANCEMENT
	 * 
	 */
	public static EnumSet<Feature> fighter(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		Class job = actor.getJob();
		EnumSet<Spell> spellsKnown;
		if (actor.getSpellsKnown() == null)
			spellsKnown = EnumSet.noneOf(Spell.class);
		else
			spellsKnown = actor.getSpellsKnown();

		if (level == 1) {
			set.add(Feature.FIGHTING_STYLE);
			set.addAll(fighterFightingStyle(1, actor));
			set.add(Feature.SECOND_WIND);

		} else if (level == 2) {
			set.add(Feature.ACTION_SURGE_2);

		} else if (level == 3) {
			// Martial archetype
			if (archetype.equals(Class.Subclass.CHAMPION)) {
				set.add(Feature.IMPROVED_CRITICAL);

			} else if (archetype.equals(Class.Subclass.BATTLE_MASTER)) {
				set.add(Feature.COMBAT_SUPERIORITY);
				set.add(Feature.SUPERIORITY_D8);
				set.add(Feature.SUPERIORITY_DICE_4);
				set.addAll(addFighterManeuver(3, actor));
				set.add(Feature.STUDENT_OF_WAR);

			} else if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
				//
				set.add(Feature.WEAPON_BOND);
				Spell.addCantripKnown(job, spellsKnown);
				Spell.addCantripKnown(job, spellsKnown);
				Spell.addToSpellsKnown(level, job, spellsKnown);
				Spell.addToSpellsKnown(level, job, spellsKnown);
				Spell.addToSpellsKnown(level, job, spellsKnown);

			}

		} else if (level == 4) {
			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 5) {
			set.add(Feature.EXTRA_ATTACK_1);

		} else if (level == 6) {
			set.add(Feature.ABILITY_BONUS_6);
			set.add(abilityImprove(actor));

		} else if (level == 7) {
			// Martial archetype
			if (archetype.equals(Class.Subclass.CHAMPION)) {
				set.add(Feature.REMARKABLE_ATHLETE);

			} else if (archetype.equals(Class.Subclass.BATTLE_MASTER)) {
				set.add(Feature.SUPERIORITY_DICE_5);
				set.addAll(addFighterManeuver(2, actor));
				set.add(Feature.KNOW_YOUR_ENEMY);

			} else if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
				//
				set.add(Feature.WAR_MAGIC);
				Spell.addToSpellsKnown(level, job, spellsKnown);

			}

		} else if (level == 8) {
			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 9) {
			set.add(Feature.INDOMITABLE_1);

		} else if (level == 10) {
			// Martial archetype
			if (archetype.equals(Class.Subclass.CHAMPION)) {
				set.add(Feature.ADDITIONAL_FIGHTING_STYLE);
				set.addAll(fighterFightingStyle(1, actor));

			} else if (archetype.equals(Class.Subclass.BATTLE_MASTER)) {
				//
				set.add(Feature.SUPERIORITY_D10);
				set.addAll(addFighterManeuver(2, actor));

			} else if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
				set.add(Feature.ELDRITCH_STRIKE);
				Spell.addCantripKnown(job, spellsKnown);
				Spell.addToSpellsKnown(level, job, spellsKnown);

			}

		} else if (level == 11) {
			set.add(Feature.EXTRA_ATTACK_2);
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 12) {
			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));

		} else if (level == 13) {
			set.add(Feature.INDOMITABLE_2);
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 14) {
			set.add(Feature.ABILITY_BONUS_14);
			set.add(abilityImprove(actor));
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 15) {
			// Martial archetype
			if (archetype.equals(Class.Subclass.CHAMPION)) {
				set.add(Feature.SUPERIOR_CRITICAL);

			} else if (archetype.equals(Class.Subclass.BATTLE_MASTER)) {
				//
				set.add(Feature.SUPERIORITY_DICE_6);
				set.addAll(addFighterManeuver(2, actor));
				set.add(Feature.RELENTLESS);

			} else if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
				//
				set.add(Feature.ARCANE_CHARGE);

			}

		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 17) {
			set.add(Feature.ACTION_SURGE_17);
			set.add(Feature.INDOMITABLE_3);

		} else if (level == 18) {
			// Martial archetype
			if (archetype.equals(Class.Subclass.CHAMPION)) {
				set.add(Feature.SURVIVOR);

			} else if (archetype.equals(Class.Subclass.BATTLE_MASTER)) {
				//
				set.add(Feature.SUPERIORITY_D12);

			} else if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
				//
				set.add(Feature.WAR_MAGIC);

			}

		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		} else if (level == 20) {
			set.add(Feature.EXTRA_ATTACK_3);
			//
			if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT))
				Spell.addToSpellsKnown(level, job, spellsKnown);

		}

		return set;
	}

	/*
	 * CLASS TEMPLATE
	 * 
	 */
	public static EnumSet<Feature> blank(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		// Class job = actor.getJob();
		// EnumSet<Spell> spellsKnown;
		// if (actor.getSpellsKnown() == null)
		// spellsKnown = EnumSet.noneOf(Spell.class);
		// else
		// spellsKnown = actor.getSpellsKnown();

		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {
			set.add(Feature.ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {
			set.add(Feature.ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {

		} else if (level == 10) {

		} else if (level == 11) {

		} else if (level == 12) {
			set.add(Feature.ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {

		} else if (level == 14) {

		} else if (level == 15) {

		} else if (level == 16) {
			set.add(Feature.ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {

		} else if (level == 18) {

		} else if (level == 19) {
			set.add(Feature.ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {

		}

		return set;
	}

	/*
	 * SETUP HELPER METHODS & UPDATE HELPER METHODS
	 * 
	 */
	private static Feature abilityImprove(Actor actor) {
		Feature[] improvement = null;

		// determines index of improvement to return
		int level = actor.getLevel(), index = 0;
		if (level == 4)
			index = 0;
		else if (level == 6)
			index = 1;
		else if (level == 8)
			index = 2;
		else if (level == 10)
			index = 3;
		else if (level == 12)
			index = 4;
		else if (level == 14)
			index = 5;
		else if (level == 16)
			index = 6;
		else if (level == 19)
			index = 7;

		// int STR = actor.getStrength(), DEX = actor.getDexterity(), CON =
		// actor.getConstitution(),
		// INT = actor.getIntelligence(), WIS = actor.getWisdom(), CHA =
		// actor.getCharisma();

		Class job = actor.getJob();
		if (job.equals(Class.BARBARIAN)) {
			if (actor.setStrength(2)) {
				improvement = Feature.STR_BONUSES;
			} else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
		} else if (job.equals(Class.WIZARD)) {
			if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
		} else if (job.equals(Class.CLERIC) || job.equals(Class.DRUID)) {
			if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
		} else if (job.equals(Class.FIGHTER)) {
			if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
		} else if (job.equals(Class.MONK)) {
			if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
			else if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
		} else if (job.equals(Class.PALADIN)) {
			if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
		} else if (job.equals(Class.RANGER) || job.equals(Class.ROGUE)) {
			if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
		} else if (job.equals(Class.BARD) || job.equals(Class.SORCERER) || job.equals(Class.WARLOCK)) {
			if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setStrength(2))
				improvement = Feature.STR_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
		} else {
			if (actor.setStrength(2)) {
				improvement = Feature.STR_BONUSES;
			} else if (actor.setConstitution(2))
				improvement = Feature.CON_BONUSES;
			else if (actor.setDexterity(2))
				improvement = Feature.DEX_BONUSES;
			else if (actor.setCharisma(2))
				improvement = Feature.CHA_BONUSES;
			else if (actor.setWisdom(2))
				improvement = Feature.WIS_BONUSES;
			else if (actor.setIntelligence(2))
				improvement = Feature.INT_BONUSES;
		}

		return improvement[index];
	}

	public static EnumSet<Feature> addRandomExpertise(int toAdd, Actor actor) {
		EnumSet<Skill> skills = actor.getSkills();
		EnumSet<Feature> features = actor.getFeatures();

		int added = 0;
		Feature expertise;
		while (added < toAdd) {
			expertise = matchExpertiseToSkill(Dice.randomFromSet(skills));

			if (features.contains(expertise) != true) {
				features.add(expertise);
				++added;
			}
		}

		return features;
	}

	private static Feature matchExpertiseToSkill(Skill skill) {
		Feature expertise = null;
		if (skill.equals(Skill.ACROBATICS))
			expertise = Feature.EXPERTISE_ACROBATICS;
		else if (skill.equals(Skill.ANIMAL_HANDLING))
			expertise = Feature.EXPERTISE_ANIMAL_HANDLING;
		else if (skill.equals(Skill.ARCANA))
			expertise = Feature.EXPERTISE_ARCANA;
		else if (skill.equals(Skill.ATHLETICS))
			expertise = Feature.EXPERTISE_ATHLETICS;
		else if (skill.equals(Skill.DECEPTION))
			expertise = Feature.EXPERTISE_DECEPTION;
		else if (skill.equals(Skill.HISTORY))
			expertise = Feature.EXPERTISE_HISTORY;
		else if (skill.equals(Skill.INSIGHT))
			expertise = Feature.EXPERTISE_INSIGHT;
		else if (skill.equals(Skill.INTIMIDATION))
			expertise = Feature.EXPERTISE_INTIMIDATION;
		else if (skill.equals(Skill.INVESTIGATION))
			expertise = Feature.EXPERTISE_INVESTIGATION;
		else if (skill.equals(Skill.MEDICINE))
			expertise = Feature.EXPERTISE_MEDICINE;
		else if (skill.equals(Skill.NATURE))
			expertise = Feature.EXPERTISE_NATURE;
		else if (skill.equals(Skill.PERCEPTION))
			expertise = Feature.EXPERTISE_PERCEPTION;
		else if (skill.equals(Skill.PERFORMANCE))
			expertise = Feature.EXPERTISE_PERFORMANCE;
		else if (skill.equals(Skill.PERSUASION))
			expertise = Feature.EXPERTISE_PERSUASION;
		else if (skill.equals(Skill.RELIGION))
			expertise = Feature.EXPERTISE_RELIGION;
		else if (skill.equals(Skill.SLEIGHT_OF_HAND))
			expertise = Feature.EXPERTISE_SLEIGHT_OF_HAND;
		else if (skill.equals(Skill.STEALTH))
			expertise = Feature.EXPERTISE_STEALTH;
		else if (skill.equals(Skill.SURVIVAL))
			expertise = Feature.EXPERTISE_SURVIVAL;

		return expertise;
	}

	private static EnumSet<Feature> fighterFightingStyle(int toAdd, Actor actor) {
		Feature[] array = new Feature[] { Feature.STYLE_ARCHERY, Feature.STYLE_DEFENSE, Feature.STYLE_DUELING,
				Feature.STYLE_GREAT_WEAPON, Feature.STYLE_PROTECTION, Feature.STYLE_TWO_WEAPON };

		EnumSet<Feature> features;
		if (actor.getFeatures() != null)
			features = actor.getFeatures();
		else
			features = EnumSet.noneOf(Feature.class);

		int added = 0;
		Feature candidate;
		while (added < toAdd) {
			candidate = Dice.randomFromArray(array);

			if (features.contains(candidate) != true) {
				features.add(candidate);
				++added;
			}
		}

		return features;
	}

	private static EnumSet<Feature> addFighterManeuver(int toAdd, Actor actor) {
		Feature[] array = new Feature[] { Feature.COMMANDERS_STRIKE, Feature.DISARMING_ATTACK,
				Feature.DISTRACTING_STRIKE, Feature.EVASIVE_FOOTWORK, Feature.FEINTING_ATTACK, Feature.GOADING_ATTACK,
				Feature.LUNGING_ATTACK, Feature.MANEUVERING_ATTACK, Feature.MENACING_ATTACK, Feature.PARRY,
				Feature.PRECISION_ATTACK, Feature.PUSHING_ATTACK, Feature.RALLY, Feature.RIPOSTE,
				Feature.SWEEPING_ATTACK, Feature.TRIP_ATTACK };
		EnumSet<Feature> features = actor.getFeatures();

		int added = 0;
		Feature candidate;
		while (added < toAdd) {
			candidate = Dice.randomFromArray(array);

			if (features.add(candidate))
				++added;
		}

		return features;
	}

	// private static Skill matchSkillToExpertise(Feature feature) {
	// Skill skill = null;
	// if (feature.equals(Feature.EXPERTISE_ACROBATICS))
	// skill = Skill.ACROBATICS;
	// else if (feature.equals(Feature.EXPERTISE_ANIMAL_HANDLING))
	// skill = Skill.ANIMAL_HANDLING;
	// else if (feature.equals(Feature.EXPERTISE_ARCANA))
	// skill = Skill.ARCANA;
	// else if (feature.equals(Feature.EXPERTISE_ATHLETICS))
	// skill = Skill.ATHLETICS;
	// else if (feature.equals(Feature.EXPERTISE_DECEPTION))
	// skill = Skill.DECEPTION;
	// else if (feature.equals(Feature.EXPERTISE_HISTORY))
	// skill = Skill.HISTORY;
	// else if (feature.equals(Feature.EXPERTISE_INSIGHT))
	// skill = Skill.INSIGHT;
	// else if (feature.equals(Feature.EXPERTISE_INTIMIDATION))
	// skill = Skill.INTIMIDATION;
	// else if (feature.equals(Feature.EXPERTISE_INVESTIGATION))
	// skill = Skill.INVESTIGATION;
	// else if (feature.equals(Feature.EXPERTISE_MEDICINE))
	// skill = Skill.MEDICINE;
	// else if (feature.equals(Feature.EXPERTISE_NATURE))
	// skill = Skill.NATURE;
	// else if (feature.equals(Feature.EXPERTISE_PERCEPTION))
	// skill = Skill.PERCEPTION;
	// else if (feature.equals(Feature.EXPERTISE_PERFORMANCE))
	// skill = Skill.PERFORMANCE;
	// else if (feature.equals(Feature.EXPERTISE_PERSUASION))
	// skill = Skill.PERSUASION;
	// else if (feature.equals(Feature.EXPERTISE_RELIGION))
	// skill = Skill.RELIGION;
	// else if (feature.equals(Feature.EXPERTISE_SLEIGHT_OF_HAND))
	// skill = Skill.SLEIGHT_OF_HAND;
	// else if (feature.equals(Feature.EXPERTISE_STEALTH))
	// skill = Skill.STEALTH;
	// else if (feature.equals(Feature.EXPERTISE_SURVIVAL))
	// skill = Skill.SURVIVAL;
	//
	// return skill;
	// }
}
