//import java.util.Arrays;
import java.util.EnumSet;

public enum Feature {

	/*
	 * STATIC METHODS
	 * 
	 */
//	public static int cantripCount(Actor actor) {
//		// TODO - add cantrips array for each class as they're added
//		Feature[] bardCantrips = { BARD_CANTRIPS_2, BARD_CANTRIPS_3, BARD_CANTRIPS_4 };
//		int count = 0;
//		Class job = actor.getJob();
//
//		EnumSet<Feature> list = EnumSet.copyOf(actor.getFeatures());
//
//		if (job.equals(Class.BARD)) {
//			list.retainAll(Arrays.asList(bardCantrips));
//			count = list.size() + 1;
//		}
//
//		return count;
//	}

	public static EnumSet<Feature> getRacialFeatures(Actor actor) {
		EnumSet<Feature> list = EnumSet.noneOf(Feature.class);
		// TODO

		return list;
	}





	// public static void abilityImprovement() {
	// Feature[] ability4 = { STR_BONUS_4, DEX_BONUS_4, CON_BONUS_4, INT_BONUS_4,
	// WIS_BONUS_4, CHA_BONUS_4 };
	// Feature[] ability6 = { STR_BONUS_6, DEX_BONUS_6, CON_BONUS_6, INT_BONUS_6,
	// WIS_BONUS_6, CHA_BONUS_6 };
	// Feature[] ability8 = { STR_BONUS_8, DEX_BONUS_8, CON_BONUS_8, INT_BONUS_8,
	// WIS_BONUS_8, CHA_BONUS_8 };
	// Feature[] ability10 = { STR_BONUS_10, DEX_BONUS_10, CON_BONUS_10,
	// INT_BONUS_10, WIS_BONUS_10, CHA_BONUS_10 };
	// Feature[] ability12 = { STR_BONUS_12, DEX_BONUS_12, CON_BONUS_12,
	// INT_BONUS_12, WIS_BONUS_12, CHA_BONUS_12 };
	// Feature[] ability14 = { STR_BONUS_14, DEX_BONUS_14, CON_BONUS_14,
	// INT_BONUS_14, WIS_BONUS_14, CHA_BONUS_14 };
	// Feature[] ability16 = { STR_BONUS_16, DEX_BONUS_16, CON_BONUS_16,
	// INT_BONUS_16, WIS_BONUS_16, CHA_BONUS_16 };
	// Feature[] ability19 = { STR_BONUS_19, DEX_BONUS_19, CON_BONUS_19,
	// INT_BONUS_19, WIS_BONUS_19, CHA_BONUS_19 };
	//
	// }

	public static EnumSet<Feature> blank(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {
			set.add(ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {
			set.add(ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {

		} else if (level == 10) {

		} else if (level == 11) {

		} else if (level == 12) {
			set.add(ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {

		} else if (level == 14) {

		} else if (level == 15) {

		} else if (level == 16) {
			set.add(ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {

		} else if (level == 18) {

		} else if (level == 19) {
			set.add(ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {

		}

		return set;
	}
}
