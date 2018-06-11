
/*
 * TODO - I realized that I could create a MAP of spells known by class (e.g. HashMap<Class, HashSet<Spells>>) -- I totally need to do this in a future update or refactor because it's just... so much better.
 */

import java.util.HashSet;

public class Spellcasting {

	// static fields
	// used by ELDRITCH_KNIGHT fighter and ARCANE_TRICKSTER rogue
	private static final int[][] FIGHTER_SLOTS = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 0, 0 }, { 3, 0, 0, 0 },
			{ 3, 0, 0, 0 }, { 3, 0, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 2, 0, 0 }, { 4, 3, 0, 0 },
			{ 4, 3, 0, 0 }, { 4, 3, 0, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 2, 0 }, { 4, 3, 3, 0 },
			{ 4, 3, 3, 0 }, { 4, 3, 3, 0 }, { 4, 3, 3, 1 }, { 4, 3, 3, 1 } };

	// used by paladin and ranger
	private static final int[][] PALADIN_SLOTS = { { 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 3, 0, 0, 0, 0 },
			{ 3, 0, 0, 0, 0 }, { 4, 2, 0, 0, 0 }, { 4, 2, 0, 0, 0 }, { 4, 3, 0, 0, 0 }, { 4, 3, 0, 0, 0 },
			{ 4, 3, 2, 0, 0 }, { 4, 3, 2, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 0, 0 }, { 4, 3, 3, 1, 0 },
			{ 4, 3, 3, 1, 0 }, { 4, 3, 3, 2, 0 }, { 4, 3, 3, 2, 0 }, { 4, 3, 3, 3, 1 }, { 4, 3, 3, 3, 1 },
			{ 4, 3, 3, 3, 2 }, { 4, 3, 3, 3, 2 } };

	// used exclusively by warlock
	private static final int[][] WARLOCK_SLOTS = { { 1, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0 }, { 0, 2, 0, 0, 0 },
			{ 0, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0 }, { 0, 0, 2, 0, 0 }, { 0, 0, 0, 2, 0 }, { 0, 0, 0, 2, 0 },
			{ 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 2 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 },
			{ 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 3 }, { 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 },
			{ 0, 0, 0, 0, 4 }, { 0, 0, 0, 0, 4 } };

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
	private int[] spellSlots;
	private HashSet<Spells> spellsKnown;

	// cantrips
	private HashSet<Spells> bardCantrips = null;
	private HashSet<Spells> clericCantrips = null;
	private HashSet<Spells> druidCantrips = null;
	private HashSet<Spells> fighterCantrips = null;
	private HashSet<Spells> rogueCantrips = null;
	private HashSet<Spells> sorcererCantrips = null;
	private HashSet<Spells> warlockCantrips = null;
	private HashSet<Spells> wizardCantrips = null;

	// constructors
	public Spellcasting(Actor actor) {
		this.owner = actor;
		this.job = actor.getJob();
		this.archetype = actor.getArchetype();
		int level = actor.getLevel();
		this.spellSlots = getSpellSlots(actor, level);

		spellsKnown = new HashSet<Spells>();
		if (job.equals(Class.BARD)) {
			spellsKnown = Spells.bardSetup(owner);
			bardCantrips = Spells.bardCantripSetup(owner);
		} else if (job.equals(Class.CLERIC)) {
			// TODO
			clericCantrips = Spells.clericCantripSetup(owner);
		} else if (job.equals(Class.DRUID)) {
			// TODO
			druidCantrips = Spells.druidCantripSetup(owner);
		} else if (job.equals(Class.SORCERER)) {
			// TODO
			sorcererCantrips = Spells.sorcererCantripSetup(owner);
		} else if (job.equals(Class.WARLOCK)) {
			// TODO
			warlockCantrips = Spells.warlockCantripSetup(owner);
		} else if (job.equals(Class.WIZARD)) {
			// TODO - needs cantrips
			spellsKnown = Spells.spellbookSetup(owner);
			wizardCantrips = Spells.wizardCantripSetup(owner);
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

	public HashSet<Spells> getCantrips() {
		HashSet<Spells> cantrips = new HashSet<Spells>();

		if (bardCantrips != null)
			cantrips.addAll(bardCantrips);

		if (clericCantrips != null)
			cantrips.addAll(clericCantrips);

		if (druidCantrips != null)
			cantrips.addAll(druidCantrips);

		if (fighterCantrips != null)
			cantrips.addAll(fighterCantrips);

		if (rogueCantrips != null)
			cantrips.addAll(rogueCantrips);

		if (sorcererCantrips != null)
			cantrips.addAll(sorcererCantrips);

		if (warlockCantrips != null)
			cantrips.addAll(warlockCantrips);

		if (wizardCantrips != null)
			cantrips.addAll(wizardCantrips);

		return cantrips;
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
