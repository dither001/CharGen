
public enum Feature {
	ABILITY_BONUS_4, ABILITY_BONUS_6, ABILITY_BONUS_8, ABILITY_BONUS_10, ABILITY_BONUS_12, ABILITY_BONUS_14, ABILITY_BONUS_16, ABILITY_BONUS_19, STR_BONUS_4, STR_BONUS_6, STR_BONUS_8, STR_BONUS_10, STR_BONUS_12, STR_BONUS_14, STR_BONUS_16, STR_BONUS_19, DEX_BONUS_4, DEX_BONUS_6, DEX_BONUS_8, DEX_BONUS_10, DEX_BONUS_12, DEX_BONUS_14, DEX_BONUS_16, DEX_BONUS_19, CON_BONUS_4, CON_BONUS_6, CON_BONUS_8, CON_BONUS_10, CON_BONUS_12, CON_BONUS_14, CON_BONUS_16, CON_BONUS_19, INT_BONUS_4, INT_BONUS_6, INT_BONUS_8, INT_BONUS_10, INT_BONUS_12, INT_BONUS_14, INT_BONUS_16, INT_BONUS_19, WIS_BONUS_4, WIS_BONUS_6, WIS_BONUS_8, WIS_BONUS_10, WIS_BONUS_12, WIS_BONUS_14, WIS_BONUS_16, WIS_BONUS_19, CHA_BONUS_4, CHA_BONUS_6, CHA_BONUS_8, CHA_BONUS_10, CHA_BONUS_12, CHA_BONUS_14, CHA_BONUS_16, CHA_BONUS_19, RAGE, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE, EXTRA_ATTACK_1, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2, PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION;

	private static final Feature[] BARBARIAN = { RAGE, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE,
			EXTRA_ATTACK_1, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2,
			PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION };

	public static void barbarian(Actor actor) {
		int level = actor.getLevel();

		if (level == 1) {
			actor.getFeatures().add(RAGE);
			actor.getFeatures().add(UNARMORED_DEFENSE_BARBARIAN);
		} else if (level == 2) {
			actor.getFeatures().add(RECKLESS_ATTACK);
			actor.getFeatures().add(DANGER_SENSE);
		} else if (level == 3) {

		} else if (level == 4) {
			actor.getFeatures().add(ABILITY_BONUS_4);
		} else if (level == 5) {
			actor.getFeatures().add(EXTRA_ATTACK_1);
			actor.getFeatures().add(FAST_MOVEMENT);
		} else if (level == 6) {

		} else if (level == 7) {
			actor.getFeatures().add(FERAL_INSTINCT);
		} else if (level == 8) {
			actor.getFeatures().add(ABILITY_BONUS_8);
		} else if (level == 9) {
			actor.getFeatures().add(BRUTAL_CRITICAL_1);
		} else if (level == 10) {

		} else if (level == 11) {
			actor.getFeatures().add(RELENTLESS_RAGE);
		} else if (level == 12) {
			actor.getFeatures().add(ABILITY_BONUS_12);
		} else if (level == 13) {
			actor.getFeatures().add(BRUTAL_CRITICAL_2);
		} else if (level == 14) {

		} else if (level == 15) {
			actor.getFeatures().add(PERSISTENT_RAGE);
		} else if (level == 16) {
			actor.getFeatures().add(ABILITY_BONUS_16);
		} else if (level == 17) {
			actor.getFeatures().add(BRUTAL_CRITICAL_3);
		} else if (level == 18) {
			actor.getFeatures().add(INDOMITABLE_MIGHT);
		} else if (level == 19) {
			actor.getFeatures().add(ABILITY_BONUS_19);
		} else if (level == 20) {
			actor.getFeatures().add(PRIMAL_CHAMPION);
		}

	}

	public static void abilityImprovement() {
		Feature[] ability4 = { STR_BONUS_4, DEX_BONUS_4, CON_BONUS_4, INT_BONUS_4, WIS_BONUS_4, CHA_BONUS_4 };
		Feature[] ability6 = { STR_BONUS_6, DEX_BONUS_6, CON_BONUS_6, INT_BONUS_6, WIS_BONUS_6, CHA_BONUS_6 };
		Feature[] ability8 = { STR_BONUS_8, DEX_BONUS_8, CON_BONUS_8, INT_BONUS_8, WIS_BONUS_8, CHA_BONUS_8 };
		Feature[] ability10 = { STR_BONUS_10, DEX_BONUS_10, CON_BONUS_10, INT_BONUS_10, WIS_BONUS_10, CHA_BONUS_10 };
		Feature[] ability12 = { STR_BONUS_12, DEX_BONUS_12, CON_BONUS_12, INT_BONUS_12, WIS_BONUS_12, CHA_BONUS_12 };
		Feature[] ability14 = { STR_BONUS_14, DEX_BONUS_14, CON_BONUS_14, INT_BONUS_14, WIS_BONUS_14, CHA_BONUS_14 };
		Feature[] ability16 = { STR_BONUS_16, DEX_BONUS_16, CON_BONUS_16, INT_BONUS_16, WIS_BONUS_16, CHA_BONUS_16 };
		Feature[] ability19 = { STR_BONUS_19, DEX_BONUS_19, CON_BONUS_19, INT_BONUS_19, WIS_BONUS_19, CHA_BONUS_19 };

	}

	public static void blank(Actor actor) {
		int level = actor.getLevel();

		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {
			actor.getFeatures().add(ABILITY_BONUS_4);
		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {
			actor.getFeatures().add(ABILITY_BONUS_8);
		} else if (level == 9) {

		} else if (level == 10) {

		} else if (level == 11) {

		} else if (level == 12) {
			actor.getFeatures().add(ABILITY_BONUS_12);
		} else if (level == 13) {

		} else if (level == 14) {

		} else if (level == 15) {

		} else if (level == 16) {
			actor.getFeatures().add(ABILITY_BONUS_16);
		} else if (level == 17) {

		} else if (level == 18) {

		} else if (level == 19) {
			actor.getFeatures().add(ABILITY_BONUS_19);
		} else if (level == 20) {

		}

	}
}
