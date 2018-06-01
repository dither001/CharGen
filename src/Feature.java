import java.util.HashSet;

public enum Feature {
	ABILITY_BONUS_4, ABILITY_BONUS_6, ABILITY_BONUS_8, ABILITY_BONUS_10, ABILITY_BONUS_12, ABILITY_BONUS_14, ABILITY_BONUS_16, ABILITY_BONUS_19, STR_BONUS_4, STR_BONUS_6, STR_BONUS_8, STR_BONUS_10, STR_BONUS_12, STR_BONUS_14, STR_BONUS_16, STR_BONUS_19, DEX_BONUS_4, DEX_BONUS_6, DEX_BONUS_8, DEX_BONUS_10, DEX_BONUS_12, DEX_BONUS_14, DEX_BONUS_16, DEX_BONUS_19, CON_BONUS_4, CON_BONUS_6, CON_BONUS_8, CON_BONUS_10, CON_BONUS_12, CON_BONUS_14, CON_BONUS_16, CON_BONUS_19, INT_BONUS_4, INT_BONUS_6, INT_BONUS_8, INT_BONUS_10, INT_BONUS_12, INT_BONUS_14, INT_BONUS_16, INT_BONUS_19, WIS_BONUS_4, WIS_BONUS_6, WIS_BONUS_8, WIS_BONUS_10, WIS_BONUS_12, WIS_BONUS_14, WIS_BONUS_16, WIS_BONUS_19, CHA_BONUS_4, CHA_BONUS_6, CHA_BONUS_8, CHA_BONUS_10, CHA_BONUS_12, CHA_BONUS_14, CHA_BONUS_16, CHA_BONUS_19, RAGE, RAGE_PER_DAY_2, RAGE_PER_DAY_3, RAGE_PER_DAY_4, RAGE_PER_DAY_5, RAGE_PER_DAY_6, RAGE_PER_DAY_99, RAGE_BONUS_2, RAGE_BONUS_3, RAGE_BONUS_4, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE, EXTRA_ATTACK_1, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2, PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION;

	private static final Feature[] BARBARIAN = { RAGE, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE,
			EXTRA_ATTACK_1, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2,
			PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION };

	public static HashSet<Feature> getClassFeatures(Actor actor) {
		HashSet<Feature> list = new HashSet<Feature>();
		// TODO
		Class job = actor.getJob();

		if (job.equals(Class.BARBARIAN)) {
			list.addAll(barbarian(actor));
		}

		return list;
	}

	public static HashSet<Feature> getRacialFeatures(Actor actor) {
		HashSet<Feature> list = new HashSet<Feature>();
		// TODO

		return list;
	}

	public static HashSet<Feature> barbarian(Actor actor) {
		HashSet<Feature> list = new HashSet<Feature>();
		int level = actor.getLevel();

		if (level == 1) {
			list.add(RAGE);
			list.add(RAGE_PER_DAY_2);
			list.add(RAGE_BONUS_2);
			list.add(UNARMORED_DEFENSE_BARBARIAN);
		} else if (level == 2) {
			list.add(RECKLESS_ATTACK);
			list.add(DANGER_SENSE);
		} else if (level == 3) {
			list.add(RAGE_PER_DAY_3);
		} else if (level == 4) {
			list.add(ABILITY_BONUS_4);
		} else if (level == 5) {
			list.add(EXTRA_ATTACK_1);
			list.add(FAST_MOVEMENT);
			list.add(RAGE_PER_DAY_4);
		} else if (level == 6) {

		} else if (level == 7) {
			list.add(FERAL_INSTINCT);
		} else if (level == 8) {
			list.add(ABILITY_BONUS_8);
		} else if (level == 9) {
			list.add(BRUTAL_CRITICAL_1);
			list.add(RAGE_BONUS_3);
		} else if (level == 10) {

		} else if (level == 11) {
			list.add(RELENTLESS_RAGE);
		} else if (level == 12) {
			list.add(ABILITY_BONUS_12);
			list.add(RAGE_PER_DAY_5);
		} else if (level == 13) {
			list.add(BRUTAL_CRITICAL_2);
		} else if (level == 14) {

		} else if (level == 15) {
			list.add(PERSISTENT_RAGE);
		} else if (level == 16) {
			list.add(ABILITY_BONUS_16);
			list.add(RAGE_BONUS_4);
		} else if (level == 17) {
			list.add(BRUTAL_CRITICAL_3);
			list.add(RAGE_PER_DAY_6);
		} else if (level == 18) {
			list.add(INDOMITABLE_MIGHT);
		} else if (level == 19) {
			list.add(ABILITY_BONUS_19);
		} else if (level == 20) {
			list.add(PRIMAL_CHAMPION);
			list.add(RAGE_PER_DAY_99);
		}

		return list;
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

	public static HashSet<Feature> blank(Actor actor) {
		HashSet<Feature> list = new HashSet<Feature>();
		int level = actor.getLevel();

		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {
			list.add(ABILITY_BONUS_4);
		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {
			list.add(ABILITY_BONUS_8);
		} else if (level == 9) {

		} else if (level == 10) {

		} else if (level == 11) {

		} else if (level == 12) {
			list.add(ABILITY_BONUS_12);
		} else if (level == 13) {

		} else if (level == 14) {

		} else if (level == 15) {

		} else if (level == 16) {
			list.add(ABILITY_BONUS_16);
		} else if (level == 17) {

		} else if (level == 18) {

		} else if (level == 19) {
			list.add(ABILITY_BONUS_19);
		} else if (level == 20) {

		}

		return list;
	}
}
