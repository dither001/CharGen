import java.util.EnumSet;

public abstract class Player implements Actor {

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
	private Career.Profile profile;

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

		//
		armor = EnumSet.noneOf(Armor.class);
		weapons = EnumSet.noneOf(Weapon.class);
		skills = EnumSet.noneOf(Skill.class);
		features = EnumSet.noneOf(Feature.class);

		//
		abilities = Dice.rollAbilities();
		alignment = Actor.Alignment.random();

		//
		job = Class.selectClass(this);
		archetype = Class.Subclass.selectSubclass(this);
		race = Race.selectRace();
		god = Deity.selectDeity(this);

		//
		
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

	/*
	 * INSTANCE METHODS
	 * 
	*/
}
