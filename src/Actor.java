
/*
 * TODO - humans make up a majority (40%) of all characters. Evil is the
 * majority alignment (40%). After sorting for CHA, WIS, INT, and DEX,
 * remaining characters are sorted by alignment: L, G, C, and N/E the
 * majority (40%) being fighters. A significant number of NPCs will most
 * probably be a) evil, b) human, and c) fighters.
 */

import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Collection;
//import java.util.Vector;

public class Actor {
	// objects
	private AbilityArray abilities;
	private Alignment ali;
	private Class job;
	private Archetype archetype;
	private Race race;
	private Deity god;
	private HashSet<Proficiency> skills;
	private Career career;

	// gear
	private Inventory inventory;
	private Combat combat;

	// fields
	private String name;
	private int experience;
	private byte level;
	private float expRate;

	private byte hitDie;
	private byte[] hitDice;
	
	private int armorClass;
	private int hitPoints;
	private int attackBonus;
	private int averageDamage;

	private String trait1;
	private String trait2;
	private String ideal;
	private String bond;
	private String flaw;

	// constructors
	public Actor() {
		// FIXME
		// armor = null; mainHand = null; offHand = null;

		abilities = new AbilityArray();
		skills = new HashSet<Proficiency>();

		ali = Alignment.selectALI();
		// JOB requires ability array
		job = Class.selectClass(this);
		archetype = Archetype.selectArchetype(this);
		// RACE is chosen specifically after JOB
		race = Race.selectRace();
		// GOD requires ALI, JOB, and RACE
		god = Deity.selectDeity(this);

		// skills, proficiency
		skills.addAll(Armor.getProficiency(job));
		skills.addAll(Weapon.getProficiency(job));
		skills.addAll(Skills.getProficiency(job));

		// choose background "career"
		career = Career.randomCareer(this);
		skills.addAll(Skills.careerSkills(this));

		// initialize inventory, equip gear
		inventory = new Inventory();
		inventory.startingGear(this);
		inventory.optimizeArmor();
		inventory.optimizeWeapon();

		// derived statistics
		experience = 0;
		level = 1;
		expRate = Class.getPrimeRequisite(this);

		hitDie = (byte) job.getHitDie();
		hitDice = new byte[20];
		for (int i = 0; i < hitDice.length; ++i)
			hitDice[i] = (byte) Dice.roll(hitDie);

		combat = new Combat(this);

		// traits, ideals, bonds, flaws
		trait1 = Career.randomTrait(career);
		trait2 = Career.randomTrait(career, trait1);
		ideal = Career.randomIdeal(career);
		bond = Career.randomBond(career);
		flaw = Career.randomFlaw(career);
	}

	// methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmorClass() {
		return combat.getArmorClass();
	}

	public int getHitPoints() {
		return combat.getHitPoints();
	}

	public int getAttackBonus() {
		return combat.getAttackBonus();
	}

	public int getAverageDamage() {
		return combat.getAverageDamage();
	}

	public int getEXP() {
		return experience;
	}

	public void setEXP(int exp) {
		this.experience = exp;
	}

	public int getLevel() {
		return level;
	}

	public int proficiencyBonus() {
		int proficiencyBonus;

		if (level > 16)
			proficiencyBonus = 6;
		else if (level > 12)
			proficiencyBonus = 5;
		else if (level > 8)
			proficiencyBonus = 4;
		else if (level > 4)
			proficiencyBonus = 3;
		else
			proficiencyBonus = 2;

		return proficiencyBonus;
	}

	public AbilityArray getAbilities() {
		return abilities;
	}

	/*
	 * GETTER FOR SKILLS AND SOME QUICK CHECKS
	 */
	public HashSet<Proficiency> getSkills() {
		return skills;
	}

	public boolean canUseHeavyArmor() {
		return skills.contains(Armor.PLATE);
	}

	public boolean canUseMediumArmor() {
		return skills.contains(Armor.HALF_PLATE);
	}

	public boolean canUseLightArmor() {
		return skills.contains(Armor.STUDDED);
	}

	public boolean canUseShield() {
		return skills.contains(Weapon.SHIELD);
	}

	public boolean canUseMartialMelee() {
		return skills.contains(Weapon.LONGSWORD);
	}

	public boolean canUseMartialRanged() {
		return skills.contains(Weapon.LONGBOW);
	}

	public boolean canUseSimpleMelee() {
		return skills.contains(Weapon.LIGHT_HAMMER);
	}

	public boolean canUseSimpleRanged() {
		return skills.contains(Weapon.SHORTBOW);
	}

	public Alignment getAli() {
		return ali;
	}

	public Class getJob() {
		return job;
	}

	public Archetype getArchetype() {
		return archetype;
	}

	public Race getRace() {
		return race;
	}

	public Deity getDeity() {
		return god;
	}

	public Career getCareer() {
		return career;
	}

	public float getEXPRate() {
		return expRate;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public String getTraitOne() {
		return trait1;
	}

	public String getTraitTwo() {
		return trait2;
	}

	public String getIdeal() {
		return ideal;
	}

	public String getBond() {
		return bond;
	}

	public String getFlaw() {
		return flaw;
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
