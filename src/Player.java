import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Player implements Actor {

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private byte[] abilityScores;
	private byte[] abilityCeiling;
	private Size size;
	private Creature creature;
	private byte[] hitDice;
	private boolean isFemale;

	private Race race;
	private Class job;
	private Class.Subclass archetype;
	private EnumSet<Spell> spellsKnown;

	private Deity god;
	private Actor.Alignment alignment;
	private Career.Profile career;

	// proficiency
	private EnumSet<Armor> armor;
	private EnumSet<Weapon> weapons;
	private EnumSet<Skill> skills;
	private EnumSet<Option.Feature> features;
	private EnumSet<Language> languages;

	// gear
	private Inventory inventory;
	private CombatBlock combat;

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
		abilityScores = Dice.rollAbilities();
		abilityCeiling = new byte[] { 20, 20, 20, 20, 20, 20 };
		alignment = Actor.Alignment.random();

		//
		job = Class.selectClass(this);
		archetype = Class.Subclass.selectSubclass(this);
		race = Race.selectRace();
		god = Deity.selectDeity(this);
		career = new Career.Profile(this);
		isFemale = (abilityScores[2] > abilityScores[0]) ? true : false;
		name = Race.randomName(isFemale, race);

		//
		this.level = 1;
		this.experience = 0;
		this.hitDice = Dice.rollHitDice(job);

		// always setup class skills first b/c they're the least flexible
		Skill.setupClassSkills(this);
		// others to follow can choose alternative skills
		Skill.setupCareerSkills(this);
		Skill.setupRacialSkills(this);
		Actor.Language.setupLanguages(this);
		Armor.setupArmorProficiency(this);
		Weapon.setupWeaponProficiency(this);
		Class.setupClassFeatures(this);
		Spell.setupSpellsKnown(this);

		// must be after race is determined
		this.size = (race.equals(Race.HALFLING) || race.equals(Race.GNOME)) ? Size.SMALL : Size.MEDIUM;
		this.creature = Creature.HUMANOID;

		// inventory setup
		Inventory.setupStartingGear(this);
		CombatBlock.setupCombatBlock(this);
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
		Dice.AlphabeticalDescending<Skill> sort = new Dice.AlphabeticalDescending<Skill>();

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
		// combat line
		string += "\n" + combat.toStringDetailed();
		// abilities line
		string += "\n" + abilitiesToString();
		// skills line
//		string += "\n" + skillsToString();
		// armor line
		// string += "\n" + armor.toString();
		// weapon line
		// string += "\n" + weapons.toString();
		// languages line
//		string += "\n" + languages.toString();
		// inventory line
		string += "\n" + inventory.toStringDetailed();
		// features line
		if (features.size() > 0)
			string += "\n" + features.toString();
		// profile line
		// string += "\n" + career.toStringDetailed();
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
	public boolean polymorphed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean magicUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean weaponUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean armorUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Size getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Creature getCreatureType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	public Deity getDeity() {
		return god;
	}

	@Override
	public byte[] getHitDice() {
		return hitDice;
	}

	@Override
	public EnumSet<Option.Feature> getFeatures() {
		return features;
	}

	@Override
	public void setFeatures(EnumSet<Option.Feature> features) {
		this.features = features;
	}

	@Override
	public Class getJob() {
		return job;
	}

	@Override
	public Class.Subclass getArchetype() {
		return archetype;
	}

	@Override
	public Race getRace() {
		return race;
	}

	@Override
	public EnumSet<Spell> getSpellsKnown() {
		return spellsKnown;
	}

	@Override
	public void setSpellsKnown(EnumSet<Spell> spellsKnown) {
		this.spellsKnown = spellsKnown;
	}

	@Override
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
	public EnumSet<Speed> getSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAbilityScores() {
		return abilityScores;
	}

	@Override
	public byte[] getAbilityCeiling() {
		return abilityCeiling;
	}

	@Override
	public byte[] getSavingThrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Skill> getSkills() {
		return skills;
	}

	@Override
	public void setSkills(EnumSet<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public EnumSet<Armor> getArmorProficiency() {
		return armor;
	}

	@Override
	public void setArmorProficiency(EnumSet<Armor> armor) {
		this.armor = armor;
	}

	@Override
	public EnumSet<Weapon> getWeaponProficiency() {
		return weapons;
	}

	@Override
	public void setWeaponProficiency(EnumSet<Weapon> weapons) {
		this.weapons = weapons;
	}

	@Override
	public EnumSet<Energy> getResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Energy> getImmunity() {
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

	@Override
	public EnumSet<Language> getLanguages() {
		return languages;
	}

	@Override
	public void setLanguages(EnumSet<Language> languages) {
		this.languages = languages;
	}

	@Override
	public CombatBlock getCombatBlock() {
		return combat;
	}

	@Override
	public void setCombatBlock(CombatBlock combat) {
		this.combat = combat;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
