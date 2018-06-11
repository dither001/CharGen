import java.util.HashSet;

public class Spellcasting {

	// static fields
	// used by ELDRITCH_KNIGHT fighter and ARCANE_TRICKSTER rogue
	private static final int[][] FIGHTER_SLOTS = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 0, 0 }, { 3, 0, 0, 0 },
			{ 3, 0, 0, 0 }, { 3, 0, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 3, 0, 0 },
			{ 4, 3, 0, 0 }, { 4, 3, 0, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 3, 0 },
			{ 4, 3, 3, 0 }, { 4, 3, 3, 0 }, { 4, 3, 3, 1 }, { 4, 3, 3, 1 } };

	// used by paladin and ranger
	private static final int[][] PALADIN_SLOTS = { { 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0 },
			{ 4, 2, 0, 0, 0 }, { 4, 2, 0, 0, 0 }, { 4, 3, 0, 0, 0 }, { 4, 3, 0, 0, 0 }, { 4, 3, 2, 0, 0 },
			{ 4, 3, 2, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 1, 0 }, { 4, 3, 3, 1, 0 },
			{ 4, 3, 3, 2, 0 }, { 4, 3, 3, 2, 0 }, { 4, 3, 3, 3, 1 }, { 4, 3, 3, 3, 1 }, { 4, 3, 3, 3, 2 },
			{ 4, 3, 3, 3, 2 } };

	// used exclusively by warlock
	private static final int[][] WARLOCK_SLOTS = { { 1, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 0, 2, 0, 0, 0 }, { 0, 2, 0, 0, 0 },
			{ 0, 0, 2, 0, 0 }, { 0, 0, 2, 0, 0 }, { 0, 0, 0, 2, 0 }, { 0, 0, 0, 2, 0 }, { 0, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 },
			{ 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 },
			{ 0, 0, 0, 0, 4 } };

	// used by bard, cleric, druid, sorcerer, and wizard
	private static int[][] WIZARD_SLOTS = { { 2, 0, 0, 0, 0, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 2, 0, 0, 0, 0, 0, 0, 0 }, { 4, 3, 0, 0, 0, 0, 0, 0, 0 }, { 4, 3, 2, 0, 0, 0, 0, 0, 0 },
			{ 4, 3, 3, 0, 0, 0, 0, 0, 0 }, { 4, 3, 3, 1, 0, 0, 0, 0, 0 }, { 4, 3, 3, 2, 0, 0, 0, 0, 0 },
			{ 4, 3, 3, 3, 1, 0, 0, 0, 0 }, { 4, 3, 3, 3, 2, 0, 0, 0, 0 }, { 4, 3, 3, 3, 2, 1, 0, 0, 0 },
			{ 4, 3, 3, 3, 2, 1, 0, 0, 0 }, { 4, 3, 3, 3, 2, 1, 1, 0, 0 }, { 4, 3, 3, 3, 2, 1, 1, 0, 0 },
			{ 4, 3, 3, 3, 2, 1, 1, 1, 0 }, { 4, 3, 3, 3, 2, 1, 1, 1, 0 }, { 4, 3, 3, 3, 2, 1, 1, 1, 1 },
			{ 4, 3, 3, 3, 3, 1, 1, 1, 1 }, { 4, 3, 3, 3, 3, 2, 1, 1, 1 }, { 4, 3, 3, 3, 3, 2, 2, 1, 1 } };

	// fields
	private Actor owner;
	private Class job;
	private Class.Subclass archetype;
	private boolean cantrips;
	private int[] spellSlots;
	private HashSet<Spells> spellsKnown;

	// constructors
	public Spellcasting(Actor actor) {
		this(actor, false);
	}
	
	public Spellcasting(Actor actor, boolean cantrips) {
		this.owner = actor;
		this.job = actor.getJob();
		this.archetype = actor.getArchetype();
		this.cantrips = cantrips;

		spellsKnown = new HashSet<Spells>();
		if (cantrips) {
			
		} else {
			int level = actor.getLevel();
			this.spellSlots = getSpellSlots(actor, level);
			
			if (job.equals(Class.BARD))
				spellsKnown = Spells.bardSetup(owner);
			else if (job.equals(Class.WIZARD))
				spellsKnown = Spells.spellbookSetup(owner);

//			else if (job.equals(Class.SORCERER))
//				
//			else if (job.equals(Class.WARLOCK))
		}
	}

	// methods
	public boolean hasSpells() {
		return spellsKnown.size() > 0;
	}
	
	public void update() {
		if (job.equals(Class.BARD)) {
			spellsKnown = Spells.bardUpdate(owner);
		} else if (job.equals(Class.WIZARD)) {
			spellsKnown = Spells.spellbookUpdate(owner);
		}
	}

	public HashSet<Spells> getSpellsKnown() {
		return spellsKnown;
	}

	// static methods
	public static int[] getSpellSlots(Actor actor, int level) {
		Class job = actor.getJob();
		int[] slots = new int[0];
		int[][] array;

		if (job.equals(Class.BARD) || job.equals(Class.CLERIC) || job.equals(Class.DRUID) || job.equals(Class.SORCERER)
				|| job.equals(Class.WIZARD)) {
			array = WIZARD_SLOTS;
			slots = array[level];
		} else if (job.equals(Class.FIGHTER) || job.equals(Class.ROGUE)) {
			array = FIGHTER_SLOTS;
			slots = array[level];
		} else if (job.equals(Class.PALADIN) || job.equals(Class.RANGER)) {
			array = PALADIN_SLOTS;
			slots = array[level];
		} else if (job.equals(Class.WARLOCK)) {
			array = WARLOCK_SLOTS;
			slots = array[level];
		}

		return slots;
	}
}
