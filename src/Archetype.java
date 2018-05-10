//	BERSERKER(Class.BARBARIAN), TOTEM_WARRIOR(Class.BARBARIAN), LORE_COLLEGE(Class.BARD), VALOR_COLLEGE(Class.BARD), 
//	DEATH(Class.CLERIC), KNOWLEDGE(Class.CLERIC), LIFE(Class.CLERIC), LIGHT(Class.CLERIC), NATURE(Class.CLERIC), TEMPEST(Class.CLERIC), TRICKERY(Class.CLERIC), WAR(Class.CLERIC), 
//	LAND_CIRCLE(Class.DRUID), MOON_CIRCLE(Class.DRUID), CHAMPION(Class.FIGHTER), BATTLE_MASTER(Class.FIGHTER), ELDRITCH_KNIGHT(Class.FIGHTER), 
//	OPEN_HAND(Class.MONK), SHADOW_WAY(Class.MONK), FOUR_ELEMENTS(Class.MONK), DEVOTION_OATH(Class.PALADIN), ANCIENTS_OATH(Class.PALADIN), VENGEANCE_OATH(Class.PALADIN), 
//	HUNTER(Class.RANGER), BEAST_MASTER(Class.RANGER), THIEF(Class.ROGUE), ASSASSIN(Class.ROGUE), ARCANE_TRICKSTER(Class.ROGUE),
//	DRAGON_ORIGIN(Class.SORCERER), CHAOS_ORIGIN(Class.SORCERER), FEY_PACT(Class.WARLOCK), FIEND_PACT(Class.WARLOCK), STAR_PACT(Class.WARLOCK),
//	ABJURER(Class.WIZARD), CONJUROR(Class.WIZARD), DIVINER(Class.WIZARD), ENCHANTER(Class.WIZARD), EVOKER(Class.WIZARD), ILLUSIONIST(Class.WIZARD), NECROMANCER(Class.WIZARD), TRANSMUTER(Class.WIZARD);

public enum Archetype {
	BERSERKER(Class.BARBARIAN), TOTEM_WARRIOR(Class.BARBARIAN), LORE_COLLEGE(Class.BARD), VALOR_COLLEGE(Class.BARD), 
	DEATH(Class.CLERIC), KNOWLEDGE(Class.CLERIC), LIFE(Class.CLERIC), LIGHT(Class.CLERIC), NATURE(Class.CLERIC), TEMPEST(Class.CLERIC), TRICKERY(Class.CLERIC), WAR(Class.CLERIC), 
	LAND_CIRCLE(Class.DRUID), MOON_CIRCLE(Class.DRUID), CHAMPION(Class.FIGHTER), BATTLE_MASTER(Class.FIGHTER), ELDRITCH_KNIGHT(Class.FIGHTER), 
	OPEN_HAND(Class.MONK), SHADOW_WAY(Class.MONK), FOUR_ELEMENTS(Class.MONK), DEVOTION_OATH(Class.PALADIN), ANCIENTS_OATH(Class.PALADIN), VENGEANCE_OATH(Class.PALADIN), 
	HUNTER(Class.RANGER), BEAST_MASTER(Class.RANGER), THIEF(Class.ROGUE), ASSASSIN(Class.ROGUE), ARCANE_TRICKSTER(Class.ROGUE),
	DRAGON_ORIGIN(Class.SORCERER), CHAOS_ORIGIN(Class.SORCERER), FEY_PACT(Class.WARLOCK), FIEND_PACT(Class.WARLOCK), STAR_PACT(Class.WARLOCK),
	ABJURER(Class.WIZARD), CONJUROR(Class.WIZARD), DIVINER(Class.WIZARD), ENCHANTER(Class.WIZARD), EVOKER(Class.WIZARD), ILLUSIONIST(Class.WIZARD), NECROMANCER(Class.WIZARD), TRANSMUTER(Class.WIZARD);

	// fields
	private final Class job;

	// arrays
	private static final Archetype[] barbarians = { Archetype.BERSERKER, Archetype.TOTEM_WARRIOR };
	private static final Archetype[] bards = { Archetype.LORE_COLLEGE, Archetype.VALOR_COLLEGE };
	private static final Archetype[] clerics = { Archetype.DEATH, Archetype.KNOWLEDGE, Archetype.LIFE, Archetype.LIGHT,
			Archetype.NATURE, Archetype.TEMPEST, Archetype.TRICKERY, Archetype.WAR };
	private static final Archetype[] druids = { Archetype.LAND_CIRCLE, Archetype.MOON_CIRCLE };
	private static final Archetype[] fighters = { Archetype.CHAMPION, Archetype.BATTLE_MASTER,
			Archetype.ELDRITCH_KNIGHT };
	private static final Archetype[] monks = { Archetype.OPEN_HAND, Archetype.SHADOW_WAY, Archetype.FOUR_ELEMENTS };
	private static final Archetype[] paladins = { Archetype.DEVOTION_OATH, Archetype.ANCIENTS_OATH,
			Archetype.VENGEANCE_OATH };
	private static final Archetype[] rangers = { Archetype.HUNTER, Archetype.BEAST_MASTER };
	private static final Archetype[] rogues = { Archetype.THIEF, Archetype.ASSASSIN, Archetype.ARCANE_TRICKSTER };
	private static final Archetype[] sorcerers = { Archetype.DRAGON_ORIGIN, Archetype.CHAOS_ORIGIN };
	private static final Archetype[] warlocks = { Archetype.FEY_PACT, Archetype.FIEND_PACT, Archetype.STAR_PACT };
	private static final Archetype[] wizards = { Archetype.ABJURER, Archetype.CONJUROR, Archetype.DIVINER,
			Archetype.ENCHANTER, Archetype.EVOKER, Archetype.ILLUSIONIST, Archetype.NECROMANCER, Archetype.TRANSMUTER };

	// constructors
	private Archetype(Class job) {
		this.job = job;
	}

	// methods
	public boolean hasSameJob(Class job) {
		return job.equals(this.job);
	}

	public boolean hasSameJob(Archetype archetype) {
		return archetype.job.equals(this.job);
	}

	public Class getJob() {
		return job;
	}

	// static methods
	public static Archetype selectArchetype(Actor actor) {
		Class job = actor.getJob();

		Archetype archetype = null;
		int dice;

		if (job.equals(Class.BARBARIAN)) {
			dice = Dice.roll(barbarians.length) - 1;
			archetype = barbarians[dice];
		} else if (job.equals(Class.BARD)) {
			dice = Dice.roll(bards.length) - 1;
			archetype = bards[dice];
		} else if (job.equals(Class.CLERIC)) {
			dice = Dice.roll(clerics.length) - 1;
			archetype = clerics[dice];
		} else if (job.equals(Class.DRUID)) {
			dice = Dice.roll(druids.length) - 1;
			archetype = druids[dice];
		} else if (job.equals(Class.FIGHTER)) {
			dice = Dice.roll(100);

			if (dice < 51) {
				archetype = fighters[0]; // 50% of fighters are champions
			} else if (dice < 81) {
				archetype = fighters[1]; // 30% of fighters are battle masters
			} else {
				archetype = fighters[2]; // 20% of fighters are eldritch knights
			}
		} else if (job.equals(Class.MONK)) {
			dice = Dice.roll(monks.length) - 1;
			archetype = monks[dice];
		} else if (job.equals(Class.PALADIN)) {
			dice = Dice.roll(paladins.length) - 1;
			archetype = paladins[dice];
		} else if (job.equals(Class.RANGER)) {
			dice = Dice.roll(rangers.length) - 1;
			archetype = rangers[dice];
		} else if (job.equals(Class.ROGUE)) {
			dice = Dice.roll(100);

			if (dice < 51) {
				archetype = rogues[0]; // 50% of rogues are thieves
			} else if (dice < 81) {
				archetype = rogues[1]; // 30% of rogues are assassins
			} else {
				archetype = rogues[2]; // 20% of rogues are arcane tricksters
			}
		} else if (job.equals(Class.SORCERER)) {
			dice = Dice.roll(sorcerers.length) - 1;
			archetype = sorcerers[dice];
		} else if (job.equals(Class.WARLOCK)) {
			dice = Dice.roll(100);

			if (dice < 41) {
				archetype = warlocks[1]; // 40% of warlocks have fiend pacts
			} else if (dice < 71) {
				archetype = warlocks[0]; // 30% of warlocks have fey pacts
			} else {
				archetype = warlocks[2]; // 30% of warlocks have star pacts
			}
		} else if (job.equals(Class.WIZARD)) {
			dice = Dice.roll(100);

			if (dice < 41) {
				archetype = wizards[2]; // 40% of wizards are diviners
			} else if (dice < 56) {
				archetype = wizards[4]; // 15% of wizards are evokers
			} else if (dice < 71) {
				archetype = wizards[5]; // 15% of wizards are illusionists
			} else if (dice < 81) {
				archetype = wizards[1]; // 10% of wizards are conjurors
			} else if (dice < 86) {
				archetype = wizards[0]; // 5% of wizards are abjurers
			} else if (dice < 91) {
				archetype = wizards[3]; // 5% of wizards are enchanters
			} else if (dice < 96) {
				archetype = wizards[6]; // 5% of wizards are necromancers
			} else {
				archetype = wizards[7]; // 5% of wizards are transmuters
			}
		}

		return archetype;
	}

	public static Class getJob(Archetype archetype) {
		return archetype.job;
	}
}
