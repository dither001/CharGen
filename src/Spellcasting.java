
/*
 * TODO - I realized that I could create a MAP of spells known by class (e.g. HashMap<Class, HashSet<Spells>>) -- I totally need to do this in a future update or refactor because it's just... so much better.
 */

import java.util.HashMap;
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
	private HashMap<Class, HashSet<Spell>> spellsKnown;
	private HashSet<Spell> tempKnown;

	// cantrips
	private HashSet<Spell> bardCantrips = null;
	private HashSet<Spell> clericCantrips = null;
	private HashSet<Spell> druidCantrips = null;
	private HashSet<Spell> fighterCantrips = null;
	private HashSet<Spell> rogueCantrips = null;
	private HashSet<Spell> sorcererCantrips = null;
	private HashSet<Spell> warlockCantrips = null;
	private HashSet<Spell> wizardCantrips = null;

	// constructors
	public Spellcasting(Actor actor) {
		this.spellsKnown = new HashMap<Class, HashSet<Spell>>();
		this.owner = actor;
		this.job = actor.getJob();
		this.archetype = actor.getArchetype();

		int level = actor.getLevel();
		this.spellSlots = getSpellSlots(actor, level);
		// TODO - need spells known for EACH class
		this.tempKnown = new HashSet<Spell>();

		// begin class spell setup
		if (job.equals(Class.BARD)) {
			tempKnown = Spell.bardSetup(owner);
			bardCantrips = Spell.bardCantripSetup(owner);
		}

		if (job.equals(Class.CLERIC)) {
			// TODO
			clericCantrips = Spell.clericCantripSetup(owner);
		}

		if (job.equals(Class.DRUID)) {
			// TODO
			druidCantrips = Spell.druidCantripSetup(owner);
		}

		if (archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)) {
			// TODO - no spells or cantrips until 3rd level!
			fighterCantrips = new HashSet<Spell>();
			// fighterCantrips = Spells.fighterCantripSetup(owner);
		}

		if (archetype.equals(Class.Subclass.ARCANE_TRICKSTER)) {
			// TODO - no spells or cantrips until 3rd level!
			rogueCantrips = new HashSet<Spell>();
			// rogueCantrips = Spells.rogueCantripSetup(owner);
		}

		if (job.equals(Class.SORCERER)) {
			// TODO
			sorcererCantrips = Spell.sorcererCantripSetup(owner);
		}

		if (job.equals(Class.WARLOCK)) {
			// TODO
			warlockCantrips = Spell.warlockCantripSetup(owner);
		}

		if (job.equals(Class.WIZARD)) {
			// TODO - needs cantrips
			tempKnown = Spell.spellbookSetup(owner);
			wizardCantrips = Spell.wizardCantripSetup(owner);
		}

		/*
		 * FIXME - REQUIRES "VERIFY SPELL SELECTION" method since I don't
		 * currently support a HashMap <Class, SpellsKnown> I need to make
		 * sure that I have a "verify the character has the correct number of
		 * spells / cantrips" method that creates as little overlap as
		 * possible between their spell lists
		 */

		/*
		 * FIXME - REQUIRES "ADD NEW CANTRIP" methods each class initializes
		 * new cantrips upon character creation, but has no way to add cantrips
		 * as they advance in level; this MUST BE CORRECTED by checking against
		 * the race/class features they've gained and adding new ones; likely
		 * EVERY time a new spell is added, I will have to run my "verify spell
		 * selection" method()
		 */
	}

	// methods
	public boolean hasSpells() {
		return tempKnown.size() > 0;
	}

	public boolean hasCantrips() {
		boolean hasCantrips = false;

		if (bardCantrips != null && bardCantrips.size() > 0)
			hasCantrips = true;

		if (clericCantrips != null && clericCantrips.size() > 0)
			hasCantrips = true;

		if (druidCantrips != null && druidCantrips.size() > 0)
			hasCantrips = true;

		if (fighterCantrips != null && fighterCantrips.size() > 0)
			hasCantrips = true;

		if (rogueCantrips != null && rogueCantrips.size() > 0)
			hasCantrips = true;

		if (sorcererCantrips != null && sorcererCantrips.size() > 0)
			hasCantrips = true;

		if (warlockCantrips != null && warlockCantrips.size() > 0)
			hasCantrips = true;

		if (wizardCantrips != null && wizardCantrips.size() > 0)
			hasCantrips = true;

		return hasCantrips;
	}

	public void update() {
		/*
		 * TODO - if I ever implement multiclassing, this method will need to
		 * check to make sure WHICH class leveled up before updating spells
		 */
		if (job.equals(Class.BARD)) {
			tempKnown = Spell.bardUpdate(owner);
		} else if (job.equals(Class.WIZARD)) {
			tempKnown = Spell.spellbookUpdate(owner);
		}
	}

	public HashSet<Spell> getSpellsKnown() {
		return tempKnown;
	}

	public HashSet<Spell> getCantrips() {
		HashSet<Spell> cantrips = new HashSet<Spell>();

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
