import java.util.HashSet;

public class Combat {
	public enum Role {
		STRIKER, DEFENDER, LEADER, CONTROLLER
	}

	// fields
	private Actor owner;
	private Role role;

	private int armorClass;
	private int hitPoints;
	private int attackBonus;
	private int averageDamage;

	// constructors
	public Combat(Actor owner) {
		this.owner = owner;

		AbilityArray abilities = owner.getAbilities();
		Class job = owner.getJob();
		Inventory inventory = owner.getInventory();

		if (job.equals(Class.BARBARIAN)) {
			this.role = Role.DEFENDER;
		} else if (job.equals(Class.BARD)) {
			this.role = Role.LEADER;
		} else if (job.equals(Class.CLERIC)) {
			this.role = Role.LEADER;
		} else if (job.equals(Class.DRUID)) {
			this.role = Role.LEADER;
		} else if (job.equals(Class.FIGHTER)) {
			this.role = Role.DEFENDER;
		} else if (job.equals(Class.MONK)) {
			this.role = Role.STRIKER;
		} else if (job.equals(Class.PALADIN)) {
			this.role = Role.DEFENDER;
		} else if (job.equals(Class.RANGER)) {
			this.role = Role.STRIKER;
		} else if (job.equals(Class.ROGUE)) {
			this.role = Role.STRIKER;
		} else if (job.equals(Class.SORCERER)) {
			this.role = Role.STRIKER;
		} else if (job.equals(Class.WARLOCK)) {
			this.role = Role.STRIKER;
		} else if (job.equals(Class.WIZARD)) {
			this.role = Role.CONTROLLER;
		}

		armorClass = inventory.calcArmorClass();

		hitPoints = Dice.roll(job.getHitDie()) + abilities.getCONMod();
		hitPoints = (hitPoints < 1) ? 1 : hitPoints;

		// attack bonus and average damage are weak approximations
		int proficiencyBonus = owner.proficiencyBonus();
		int str = abilities.getSTRMod(), dex = abilities.getDEXMod();
		int abilityMod = (str >= dex) ? str : dex;

		attackBonus = proficiencyBonus + abilityMod;
		averageDamage = inventory.calcAverageDamage() + abilityMod;
	}

	//
	public void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		byte[] hitDice = owner.getHitDice();
		int CON = owner.getAbilities().getCONMod();

		int hp = 0;
		for (int i = 0; i < level - 1; ++i) {
			hp += (hitDice[i] + CON > 0) ? hitDice[i] + CON : 1;
		}

		hitPoints = hp;
	}

	public void calcAttackBonus() {
		// TODO - doesn't take into account magical bonuses or other features
		AbilityArray abilities = owner.getAbilities();
		int str = abilities.getSTRMod(), dex = abilities.getDEXMod();
		int abilityMod = (str >= dex) ? str : dex;

		int proficiencyBonus = owner.proficiencyBonus();

		attackBonus = proficiencyBonus + abilityMod;
	}

	public void calcAverageDamage() {
		// TODO - doesn't take into account magical bonuses or other features
		AbilityArray abilities = owner.getAbilities();
		int str = abilities.getSTRMod(), dex = abilities.getDEXMod();
		int abilityMod = (str >= dex) ? str : dex;

		Inventory inventory = owner.getInventory();

		int extraAttackMultiplier = 1;
		HashSet<Feature> features = owner.getFeatures();
		// TODO
		if (features.contains(Feature.EXTRA_ATTACK_1))
			extraAttackMultiplier = 2;

		averageDamage = (inventory.calcAverageDamage() + abilityMod) * extraAttackMultiplier;
	}

	// basic getter methods
	public boolean hasOwner() {
		return owner != null;
	}

	public Role getRole() {
		return (role != null) ? role : Role.STRIKER;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getAttackBonus() {
		return attackBonus;
	}

	public int getAverageDamage() {
		return averageDamage;
	}
}
