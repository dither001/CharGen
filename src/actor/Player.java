package actor;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.norvendae.rules.dnd5e.*;
import com.norvendae.rules.misc.Dice;

import gear.Armor;
import gear.Inventory;
import gear.Weapon;
import magic.Spell;
import milieu.Names;
import milieu.Names.AlphabeticalDescending;

public class Player implements Actor {

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private int[] abilityScores;
	private int[] abilityCeiling;
	private Size size;
	private CreatureType creature;
	private int[] hitDice;
	private boolean isFemale;

	private Race race;
	private Class job;
	private Subclass archetype;
	private EnumSet<Spell> spellsKnown;

	private Deity god;
	private Alignment alignment;
	private Career.Profile career;

	// proficiency
	private EnumSet<Armor> armor;
	private EnumSet<Weapon> weapons;
	private EnumSet<Skill> skills;
	private EnumSet<Option.Feature> features;
	private EnumSet<Language> languages;

	// gear
	private Inventory inventory;
	private Combat combat;

	// basic
	private String name;
	private int experience;
	private int level;
	private float expRate;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Player() {
		//
		abilityScores = Dice.rollAbilities3d6();
		abilityCeiling = new int[] { 20, 20, 20, 20, 20, 20 };
		alignment = Alignment.random();

		//
		job = Class.selectClass(this);
		archetype = Subclass.selectSubclass(this);
		Race.applyRacialFeatures(this);
		god = Deity.selectDeity(this);
		career = new Career.Profile(this);
		isFemale = (abilityScores[2] > abilityScores[0]) ? true : false;
		name = Race.randomName(isFemale, race);

		//
		this.level = 1;
		this.experience = 0;

		// pre-roll hit dice
		int hitDieSize = Class.getHitDie(job);
		int[] rolls = new int[20];
		for (int i = 0; i < rolls.length; ++i) {
			rolls[i] = Dice.roll(hitDieSize);
		}

		this.hitDice = rolls;

		// always setup class skills first b/c they're the least flexible
		// Skill.setupClassSkills(this);
		// others to follow can choose alternative skills
		Career.applyCareerFeatures(this);
		// Skill.setupRacialSkills(this);
		Armor.setupArmorProficiency(this);
		Weapon.setupWeaponProficiency(this);
		Class.setupClassFeatures(this);
		Spell.setupSpellsKnown(this);

		// must be after race is determined
		this.size = (race.equals(Race.LIGHTFOOT_HALFLING) || race.equals(Race.FOREST_GNOME)) ? Size.SMALL : Size.MEDIUM;
		this.creature = CreatureType.HUMANOID;

		// inventory setup
		Inventory.setupStartingGear(this);
		Combat.setupCombat(this);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void advance() {
		int[] requires = { 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000,
				165000, 195000, 225000, 265000, 305000, 355000 };
		int currentLevel = getLevel(), currentEXP = getExperience();

		boolean advanced = false;
		if (currentLevel < 20 && currentEXP >= requires[currentLevel]) {
			setLevel(currentLevel + 1);
			advanced = true;

			if (currentLevel < 19 && currentEXP >= requires[currentLevel + 1])
				currentEXP = requires[currentLevel + 1] - 1;
		}

		if (advanced) {
			// TODO
			Class.updateClassFeatures(this);
			combat().update();

		}
	}

	@Override
	public String toString() {
		String string;
		if (job != null)
			string = String.format("%s the %s", name, job);
		else
			string = name;

		return string;
	}

	private String abilitiesToString() {
		String string = String.format("[%2d, %2d, %2d, %2d, %2d, %2d]", abilityScores[0], abilityScores[1],
				abilityScores[2], abilityScores[3], abilityScores[4], abilityScores[5]);

		return string;
	}

	private String skillsToString() {
		List<Skill> skillsList = Dice.setToList(skills);
		Names.AlphabeticalDescending<Skill> sort = new Names.AlphabeticalDescending<Skill>();

		Collections.sort(skillsList, sort);
		String string = String.format("%s", skills.toString());

		return string;
	}

	@Override
	public String toStringDetailed() {
		String string = String.format("%s the level %d %s %s (%s)", name, level, race, job, archetype);

		// creature line
		// String sex = (isFemale) ? "female" : "male";
		// string += String.format("%n%s %s %s (%s) %s follower of %s", size, sex,
		// creature, race, alignment, god);

		// profile line
		// string += "\n\n" + career.toStringDetailed() + "\n";

		// combat line
		string += "\n" + combat.toStringDetailed();

		// abilities line
		string += "\n" + abilitiesToString();

		// attacks line
		string += combat.topThreeAttacks();

		// skills line
		// string += "\nSkills: " + skills.size();
		string += "\n" + skillsToString();

		// armor line
		// string += "\n" + armor.toString();

		// weapon line
		// string += "\n" + weapons.toString();

		// languages line
		string += "\n" + languages.toString();

		// inventory line
		string += "\n" + inventory.toStringDetailed();

		// features line
		if (features.size() > 0)
			string += "\n" + features.toString();

		// spells block
		if (spellsKnown.size() > 0) {
			EnumSet<Spell> cantripsKnown = EnumSet.copyOf(Spell.retainSpellsOfTier(0, spellsKnown));
			EnumSet<Spell> otherKnown = EnumSet.copyOf(Spell.retainSpellsExceptOfTier(0, spellsKnown));

			if (cantripsKnown.size() > 0) {
				string += "\nCantrips Known: " + cantripsKnown.size();
				string += "\n" + cantripsKnown.toString();

			}

			if (otherKnown.size() > 0) {
				string += "\nSpells Known: " + otherKnown.size();
				string += "\n" + otherKnown.toString();

			}

		}

		return string;
	}

	@Override
	public Size creatureSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreatureType creatureType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alignment alignment() {
		return alignment;
	}

	public Deity getDeity() {
		return god;
	}

	@Override
	public int[] getHitDice() {
		return hitDice;
	}

	public EnumSet<Option.Feature> getFeatures() {
		return features;
	}

	public void setFeatures(EnumSet<Option.Feature> features) {
		this.features = features;
	}

	public Class getJob() {
		return job;
	}

	public Subclass getArchetype() {
		return archetype;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public EnumSet<Spell> getSpellsKnown() {
		return spellsKnown;
	}

	public void setSpellsKnown(EnumSet<Spell> spellsKnown) {
		this.spellsKnown = spellsKnown;
	}

	public Career.Profile getCareer() {
		return career;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getExperience() {
		return experience;
	}

	@Override
	public void setExp(int exp) {
		this.experience = exp;
	}

	@Override
	public EnumSet<MovementType> getSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAbilityScores() {
		return abilityScores;
	}

	@Override
	public int[] getAbilityCeiling() {
		return abilityCeiling;
	}

	@Override
	public int[] getSavingThrows() {
		// TODO Auto-generated method stub
		return null;
	}

	public EnumSet<Skill> getSkills() {
		return skills;
	}

	public void setSkills(EnumSet<Skill> skills) {
		this.skills = skills;
	}

	public EnumSet<Armor> getArmorProficiency() {
		return armor;
	}

	public void setArmorProficiency(EnumSet<Armor> armor) {
		this.armor = armor;
	}

	public EnumSet<Weapon> getWeaponProficiency() {
		return weapons;
	}

	public void setWeaponProficiency(EnumSet<Weapon> weapons) {
		this.weapons = weapons;
	}

	@Override
	public EnumSet<Energy> getResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditionImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Sense> getSenses() {
		// TODO Auto-generated method stub
		return null;
	}

	public EnumSet<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(EnumSet<Language> languages) {
		this.languages = languages;
	}

	public Combat combat() {
		return combat;
	}

	public void setCombat(Combat combat) {
		this.combat = combat;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/*
	 * 
	 */
	public boolean wearingArmor() {
		return (getInventory().wearingArmor());
	}

	public boolean notWearingArmor() {
		return (getInventory().wearingArmor() != true);
	}

	public boolean usingShield() {
		return (getInventory().usingShield());
	}

	public boolean notUsingShield() {
		return (getInventory().usingShield() != true);
	}

	public int getArmorClass() {
		return combat().getArmorClass();
	}

	public int getHitPoints() {
		return combat().getHitPoints();
	}

	/*
	 * 
	 */
	public boolean hasJob(Class job) {
		boolean hasJob = false;
		if (getJob() != null && getJob().equals(job))
			hasJob = true;

		return hasJob;
	}

	public boolean isBarbarian() {
		Class job = Class.BARBARIAN;

		return hasJob(job);
	}

	public boolean isBard() {
		Class job = Class.BARD;

		return hasJob(job);
	}

	public boolean isCleric() {
		Class job = Class.CLERIC;

		return hasJob(job);
	}

	public boolean isDruid() {
		Class job = Class.DRUID;

		return hasJob(job);
	}

	public boolean isFighter() {
		Class job = Class.FIGHTER;

		return hasJob(job);
	}

	public boolean isMonk() {
		Class job = Class.MONK;

		return hasJob(job);
	}

	public boolean isPaladin() {
		Class job = Class.PALADIN;

		return hasJob(job);
	}

	public boolean isRanger() {
		Class job = Class.RANGER;

		return hasJob(job);
	}

	public boolean isRogue() {
		Class job = Class.ROGUE;

		return hasJob(job);
	}

	public boolean isSorcerer() {
		Class job = Class.SORCERER;

		return hasJob(job);
	}

	public boolean isWarlock() {
		Class job = Class.WARLOCK;

		return hasJob(job);
	}

	public boolean isWizard() {
		Class job = Class.WIZARD;

		return hasJob(job);
	}

	@Override
	public void setAbilityScores(int[] abilityScores) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAbilityCeiling(int[] abilityCeiling) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResistance(EnumSet<Energy> resistance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Energy> getEnergyImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnergyImmunity(EnumSet<Energy> energyImmunity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConditionImmunity(EnumSet<Condition> conditionImmunity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConditions(EnumSet<Condition> conditions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSenses(EnumSet<Sense> senses) {
		// TODO Auto-generated method stub
		
	}

}
