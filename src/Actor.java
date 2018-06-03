
/*
 * TODO - humans make up a majority (40%) of all characters. Evil is the
 * majority alignment (40%). After sorting for CHA, WIS, INT, and DEX,
 * remaining characters are sorted by alignment: L, G, C, and N/E the
 * majority (40%) being fighters. A significant number of NPCs will most
 * probably be a) evil, b) human, and c) fighters.
 */

import java.util.HashSet;

public class Actor {
	// objects
	private AbilityArray abilities;
	private Alignment ali;
	private Class job;
	private Class.Subclass archetype;
	private Race race;
	private Deity god;
	private HashSet<Proficiency> skills;
	private Career career;
	private HashSet<Feature> features;

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

		abilities = new AbilityArray(this);
		skills = new HashSet<Proficiency>();
		features = new HashSet<Feature>();

		ali = Alignment.selectALI();
		// JOB requires ability array
		job = Class.selectClass(this);
		archetype = Class.Subclass.selectSubclass(this);
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

		// derived statistics
		experience = 0;
		level = 1;
		expRate = Class.getPrimeRequisite(this);

		hitDie = (byte) job.getHitDie();
		hitDice = new byte[20];
		for (int i = 0; i < hitDice.length; ++i)
			hitDice[i] = (byte) Dice.roll(hitDie);

		// race & class features (requires level)
		features.addAll(Feature.getClassFeatures(this));

		// initialize inventory, equip gear
		inventory = new Inventory();
		inventory.startingGear(this);
		inventory.optimizeArmor();
		inventory.optimizeWeapon();

		// combat block
		combat = new Combat(this);

		// traits, ideals, bonds, flaws
		trait1 = Career.randomTrait(career);
		trait2 = Career.randomTrait(career, trait1);
		ideal = Career.randomIdeal(career);
		bond = Career.randomBond(career);
		flaw = Career.randomFlaw(career);
	}

	// methods
	@Override
	public String toString() {
		String toString = String.format("%s %s %s", ali.toString(), race.toString(), job.toString());

		return toString;
	}

	public void advance() {
		boolean advanced = checkNextLevel(this);

		if (advanced) {
			features.addAll(Feature.getClassFeatures(this));
			abilities.updateScores();

			combat.update();
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */
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

	public byte[] getHitDice() {
		return hitDice;
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

	public void gainEXP(int exp) {
		this.experience = (int) (exp * expRate);
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
		return abilities.current();
	}

	public AbilityArray getBasicAbilities() {
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

	public HashSet<Feature> getFeatures() {
		return features;
	}

	public Alignment getAli() {
		return ali;
	}

	public Class getJob() {
		return job;
	}

	public Class.Subclass getArchetype() {
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
		int level = actor.getLevel();
		boolean advanced = false;

		if (level < 20 && totalXP >= requires[level]) {
			actor.level += 1;
			advanced = true;

			if (level < 19 && totalXP >= requires[level + 1])
				actor.experience = requires[level + 1] - 1;
		}

		return advanced;
	}

}
