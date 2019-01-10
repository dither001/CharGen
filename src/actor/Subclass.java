package actor;

import com.norvendae.misc.Dice;

public enum Subclass {
	BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM, // barbarians
	LORE_COLLEGE, VALOR_COLLEGE, // bards
	DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR, // clerics
	LAND_CIRCLE, MOON_CIRCLE, // druids
	CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT, // fighters
	OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS, // monks
	DEVOTION_OATH, ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER, // paladins
	HUNTER, BEAST_MASTER, // rangers
	THIEF, ASSASSIN, ARCANE_TRICKSTER, // rogues
	DRAGON_ORIGIN, CHAOS_ORIGIN, // sorcerers
	FEY_PACT, FIEND_PACT, STAR_PACT, // warlocks
	ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER, ILLUSIONIST, NECROMANCER, TRANSMUTER; // wizards

	/*
	 * STATIC FIELDS
	 */
	private static final Subclass[] BARBARIAN_TYPES = { BERSERKER, BEAR_TOTEM, EAGLE_TOTEM, WOLF_TOTEM };
	private static final Subclass[] BARD_TYPES = { LORE_COLLEGE, VALOR_COLLEGE };
	private static final Subclass[] CLERIC_TYPES = { DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR };
	private static final Subclass[] DRUID_TYPES = { LAND_CIRCLE, MOON_CIRCLE };
	private static final Subclass[] FIGHTER_TYPES = { CHAMPION, BATTLE_MASTER, ELDRITCH_KNIGHT };
	private static final Subclass[] MONK_TYPES = { OPEN_HAND, SHADOW_WAY, FOUR_ELEMENTS };
	private static final Subclass[] PALADIN_TYPES = { DEVOTION_OATH, ANCIENTS_OATH, VENGEANCE_OATH, OATHBREAKER };
	private static final Subclass[] RANGER_TYPES = { HUNTER, BEAST_MASTER };
	private static final Subclass[] ROGUE_TYPES = { THIEF, ASSASSIN, ARCANE_TRICKSTER };
	private static final Subclass[] SORCERER_TYPES = { DRAGON_ORIGIN, CHAOS_ORIGIN };
	private static final Subclass[] WARLOCK_TYPES = { FEY_PACT, FIEND_PACT, STAR_PACT };
	private static final Subclass[] WIZARD_TYPES = { ABJURER, CONJUROR, DIVINER, ENCHANTER, EVOKER, ILLUSIONIST,
			NECROMANCER, TRANSMUTER };

	/*
	 * STATIC METHODS
	 */
	public static Subclass selectSubclass(Player actor) {
		Class job = actor.getJob();

		Subclass Subclass = null;
		int dice;

		if (job.equals(Class.BARBARIAN)) {
			dice = Dice.roll(BARBARIAN_TYPES.length) - 1;
			Subclass = BARBARIAN_TYPES[dice];
		} else if (job.equals(Class.BARD)) {
			dice = Dice.roll(BARD_TYPES.length) - 1;
			Subclass = BARD_TYPES[dice];
		} else if (job.equals(Class.CLERIC)) {
			dice = Dice.roll(CLERIC_TYPES.length) - 1;
			Subclass = CLERIC_TYPES[dice];
		} else if (job.equals(Class.DRUID)) {
			dice = Dice.roll(DRUID_TYPES.length) - 1;
			Subclass = DRUID_TYPES[dice];
		} else if (job.equals(Class.FIGHTER)) {
			dice = Dice.roll(100);

			if (dice < 21 && actor.getIntelligence() > 9) {
				Subclass = FIGHTER_TYPES[2]; // 20% of fighters are eldritch knights
			} else if (dice < 51) {
				Subclass = FIGHTER_TYPES[1]; // 30% of fighters are battle masters
			} else {
				Subclass = FIGHTER_TYPES[0]; // 50% of fighters are champions
			}
		} else if (job.equals(Class.MONK)) {
			dice = Dice.roll(MONK_TYPES.length) - 1;
			Subclass = MONK_TYPES[dice];
		} else if (job.equals(Class.PALADIN)) {
			dice = Dice.roll(PALADIN_TYPES.length) - 1;
			Subclass = PALADIN_TYPES[dice];
		} else if (job.equals(Class.RANGER)) {
			dice = Dice.roll(RANGER_TYPES.length) - 1;
			Subclass = RANGER_TYPES[dice];
		} else if (job.equals(Class.ROGUE)) {
			dice = Dice.roll(100);

			if (dice < 21 && actor.getIntelligence() > 9) {
				Subclass = ROGUE_TYPES[2]; // 20% of rogues are arcane tricksters
			} else if (dice < 51) {
				Subclass = ROGUE_TYPES[1]; // 30% of rogues are assassins
			} else {
				Subclass = ROGUE_TYPES[0]; // 50% of rogues are thieves
			}
		} else if (job.equals(Class.SORCERER)) {
			dice = Dice.roll(SORCERER_TYPES.length) - 1;
			Subclass = SORCERER_TYPES[dice];
		} else if (job.equals(Class.WARLOCK)) {
			dice = Dice.roll(100);

			if (dice < 41) {
				Subclass = WARLOCK_TYPES[1]; // 40% of warlocks have fiend pacts
			} else if (dice < 71) {
				Subclass = WARLOCK_TYPES[0]; // 30% of warlocks have fey pacts
			} else {
				Subclass = WARLOCK_TYPES[2]; // 30% of warlocks have star pacts
			}
		} else if (job.equals(Class.WIZARD)) {
			dice = Dice.roll(100);

			if (dice < 41) {
				Subclass = WIZARD_TYPES[2]; // 40% of wizards are diviners
			} else if (dice < 56) {
				Subclass = WIZARD_TYPES[4]; // 15% of wizards are evokers
			} else if (dice < 71) {
				Subclass = WIZARD_TYPES[5]; // 15% of wizards are illusionists
			} else if (dice < 81) {
				Subclass = WIZARD_TYPES[1]; // 10% of wizards are conjurors
			} else if (dice < 86) {
				Subclass = WIZARD_TYPES[0]; // 5% of wizards are abjurers
			} else if (dice < 91) {
				Subclass = WIZARD_TYPES[3]; // 5% of wizards are enchanters
			} else if (dice < 96) {
				Subclass = WIZARD_TYPES[6]; // 5% of wizards are necromancers
			} else {
				Subclass = WIZARD_TYPES[7]; // 5% of wizards are transmuters
			}
		}

		return Subclass;
	}

}