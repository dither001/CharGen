
public class Spellcasting {

	// static fields
	// used by ELDRITCH_KNIGHT fighter and ARCANE_TRICKSTER rogue
	private static int[][] fighterSlots = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 0, 0 }, { 3, 0, 0, 0 },
			{ 3, 0, 0, 0 }, { 3, 0, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 3, 0, 0 },
			{ 4, 3, 0, 0 }, { 4, 3, 0, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 3, 0 },
			{ 4, 3, 3, 0 }, { 4, 3, 3, 0 }, { 4, 3, 3, 1 }, { 4, 3, 3, 1 } };

	// used by paladin and ranger
	private static int[][] paladinSlots = { { 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0 },
			{ 4, 2, 0, 0, 0 }, { 4, 2, 0, 0, 0 }, { 4, 3, 0, 0, 0 }, { 4, 3, 0, 0, 0 }, { 4, 3, 2, 0, 0 },
			{ 4, 3, 2, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 1, 0 }, { 4, 3, 3, 1, 0 },
			{ 4, 3, 3, 2, 0 }, { 4, 3, 3, 2, 0 }, { 4, 3, 3, 3, 1 }, { 4, 3, 3, 3, 1 }, { 4, 3, 3, 3, 2 },
			{ 4, 3, 3, 3, 2 } };

	// used exclusively by warlock
	private static int[][] warlockSlots = { { 1, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 0, 2, 0, 0, 0 }, { 0, 2, 0, 0, 0 },
			{ 0, 0, 2, 0, 0 }, { 0, 0, 2, 0, 0 }, { 0, 0, 0, 2, 0 }, { 0, 0, 0, 2, 0 }, { 0, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 },
			{ 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 },
			{ 0, 0, 0, 0, 4 } };

	// used by bard, cleric, druid, sorcerer, and wizard
	private static int[][] wizardSlots = { { 2, 0, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 2, 0, 0, 0, 0, 0, 0, 0 }, { 4, 3, 0, 0, 0, 0, 0, 0, 0 }, { 4, 3, 2, 0, 0, 0, 0, 0, 0 },
			{ 4, 3, 3, 0, 0, 0, 0, 0, 0 }, { 4, 3, 3, 1, 0, 0, 0, 0, 0 }, { 4, 3, 3, 2, 0, 0, 0, 0, 0 },
			{ 4, 3, 3, 3, 1, 0, 0, 0, 0 }, { 4, 3, 3, 3, 2, 0, 0, 0, 0 }, { 4, 3, 3, 3, 2, 1, 0, 0, 0 },
			{ 4, 3, 3, 3, 2, 1, 0, 0, 0 }, { 4, 3, 3, 3, 2, 1, 1, 0, 0 }, { 4, 3, 3, 3, 2, 1, 1, 0, 0 },
			{ 4, 3, 3, 3, 2, 1, 1, 1, 0 }, { 4, 3, 3, 3, 2, 1, 1, 1, 0 }, { 4, 3, 3, 3, 2, 1, 1, 1, 1 },
			{ 4, 3, 3, 3, 3, 1, 1, 1, 1 }, { 4, 3, 3, 3, 3, 2, 1, 1, 1 }, { 4, 3, 3, 3, 3, 2, 2, 1, 1 } };

	// fields
	private Class job;
	private int[] spellSlots;

	// constructors
	public Spellcasting(Actor actor) {
		this.job = actor.getJob();

		int level = actor.getLevel();

		if (job.equals(Class.BARD) || job.equals(Class.CLERIC) || job.equals(Class.DRUID) || job.equals(Class.SORCERER)
				|| job.equals(Class.WIZARD)) {
			spellSlots = wizardSlots[level - 1];
		} else if (job.equals(Class.FIGHTER) || job.equals(Class.ROGUE)) {
			spellSlots = fighterSlots[level - 1];
		} else if (job.equals(Class.PALADIN) || job.equals(Class.RANGER)) {
			spellSlots = paladinSlots[level - 1];
		} else if (job.equals(Class.WARLOCK)) {
			spellSlots = warlockSlots[level - 1];
		}

		// END CONSTRUCTOR
	}
}
