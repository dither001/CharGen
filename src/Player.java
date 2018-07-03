import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Player implements Actor {

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private byte[] abilities;

	private Race race;
	private Class job;
	private Class.Subclass archetype;
	private Spellcasting spellcasting;

	private Deity god;
	private Actor.Alignment alignment;
	private Career career;

	// proficiency
	private EnumSet<Armor> armor;
	private EnumSet<Weapon> weapons;
	private EnumSet<Skill> skills;
	private EnumSet<Feature> features;

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
		// FIXME - random name generator
		name = "Default";

		// these have to be initialized before use
		// armor = EnumSet.noneOf(Armor.class);
		// weapons = EnumSet.noneOf(Weapon.class);
		// skills = EnumSet.noneOf(Skill.class);
		// features = EnumSet.noneOf(Feature.class);

		//
		abilities = Dice.rollAbilities();
		alignment = Actor.Alignment.random();

		//
		job = Class.selectClass(this);
		archetype = Class.Subclass.selectSubclass(this);
		race = Race.selectRace();
		god = Deity.selectDeity(this);
		career = Career.randomCareer();

		//
		Skill.setupClassSkills(this);
		Skill.setupCareerSkills(this);
		Skill.setupRacialSkills(this);

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

	private String skillsToString() {
		List<Skill> skillsList = Dice.listFromSet(skills);
		Dice.AlphabeticalDescending<Skill> sort = new Dice.AlphabeticalDescending<Skill>();
		
		Collections.sort(skillsList, sort);
		String string = String.format("%s", skills.toString());

		return string;
	}

	@Override
	public String toStringDetailed() {
		String string = String.format("%s the %s %s (%s) %s", name, race, job, archetype, career);

		// alignment, deity line
		string += String.format("%n%s follower of %s", alignment, god);
		// skills line
		string += "\n" + skillsToString();

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
		// TODO Auto-generated method stub
		return null;
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
	public Career getCareer() {
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
	public int getExp() {
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
	public byte[] getAbilities() {
		return abilities;
	}

	@Override
	public int getProficiencyBonus() {
		// TODO Auto-generated method stub
		return 0;
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
	public int getChallengeRating() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
}
