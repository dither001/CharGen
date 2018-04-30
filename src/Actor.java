
/*
 * TODO - humans make up a majority (40%) of all characters; evil is the
 * majority alignment (40%), which means that a significant number of NPCs
 * generated will probably be a) evil, b) human, and c) fighters.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.Vector;

public class Actor {
	// objects
	private AbilityArray abilities;
	private Alignment ali;
	private Archetype job;
	private Race race;
	private Deity god;
	private HashSet<Proficiency> skills;

	// gear
	private Inventory inventory;
	// private Gear armor;
	// private Gear mainHand;
	// private Gear offHand;

	// fields
	private String name;
	private int experience;
	private int level;
	private float expRate;

	// constructors
	public Actor() {
		// FIXME
		// armor = null; mainHand = null; offHand = null;

		abilities = new AbilityArray();
		skills = new HashSet<Proficiency>();

		ali = Alignment.selectALI();
		// JOB requires ability array
		job = Archetype.selectArchetype(this);
		// RACE is chosen specifically after JOB
		race = Race.selectRace();
		// GOD requires ALI, JOB, and RACE
		god = Deity.selectDeity(this);

		// skills.addAll(Armor.getProficiency(job));
		// skills.addAll(Weapon.getProficiency(job));
		skills.addAll(Skills.getProficiency(job));

		// TODO
		expRate = Archetype.getPrimeRequisite(this);

		// last step, initialize inventory (?) and equip stuff
		inventory = new Inventory();
		inventory.startingGear(this);
	}

	// methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmorClass() {
		return 0;
	} // TODO

	public int getHitPoints() {
		return 0;
	} // TODO

	public int getAttackBonus() {
		return 0;
	} // TODO

	public int getDamage() {
		return 0;
	} // TODO

	public int getEXP() {
		return experience;
	}

	public void setEXP(int exp) {
		this.experience = exp;
	}

	public int getLevel() {
		return level;
	}

	public AbilityArray getAbilities() {
		return abilities;
	}

	public HashSet<Proficiency> getSkills() {
		return skills;
	}

	public Alignment getAli() {
		return ali;
	}

	public Archetype getJob() {
		return job;
	}

	public Race getRace() {
		return race;
	}

	public Deity getDeity() {
		return god;
	}

	public float getEXPRate() {
		return expRate;
	}

	public Inventory getInventory() {
		return inventory;
	}

	// static methods
	public static boolean checkNextLevel(Actor actor) {
		int[] requires = { 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000,
				165000, 195000, 225000, 265000, 305000, 355000 };
		int totalXP = actor.getEXP();
		int currentLevel = actor.getLevel();
		int nextLevel = 20;
		boolean advanced = false;

		if (currentLevel < 20) {
			// iterate through experience chart
			for (int i = 0; i < requires.length; ++i) {
				if (totalXP < requires[i]) {
					nextLevel = i + 1;
					break;
				}
			}

			// compare "next" level to current level
			if (nextLevel - currentLevel > 0) {
				// DING!
				advanced = true;
				if (nextLevel - currentLevel > 1) {
					/*
					 * You can only advance one level at a time (per adventure). EXP in excess of
					 * the required amount is wasted. If you have more than you need, then your EXP
					 * is set to one less than what is needed to advance to the level -AFTER- the
					 * one you just gained (so, you can earn like, 1.99% levels at once).
					 */
					nextLevel = currentLevel + 1;
					actor.setEXP(requires[nextLevel] - 1);
				}
			}
		}

		return advanced;
	}

}
