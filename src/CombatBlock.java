import java.util.HashSet;

public class CombatBlock {
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
	public CombatBlock(Actor owner) {
		this.owner = owner;
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

		// TODO - to prevent this from being called 20 times
		calcArmorClass();
		calcHitPoints();
		calcAttackBonus();
		calcAverageDamage();
	}

	//
	public void update() {
		calcArmorClass();
		calcHitPoints();
		calcAttackBonus();
		calcAverageDamage();
	}

	public void calcArmorClass() {
		// TODO
		Inventory inventory = owner.getInventory();
		armorClass = inventory.calcArmorClass();
	}

	public void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		byte[] hitDice = owner.getHitDice();
		int CON = owner.getConMod();

		int hp = 0;
		for (int i = 0; i < level; ++i) {
			hp += (hitDice[i] + CON > 0) ? hitDice[i] + CON : 1;
		}

		hitPoints = hp;
	}

	public void calcAttackBonus() {
		// TODO - doesn't take into account magical bonuses or other features
		int proficiencyBonus = owner.getProficiencyBonus();
		int str = owner.getStrMod(), dex = owner.getDexMod(), abilityMod = str;

		Inventory inventory = owner.getInventory();
		if (inventory.equippedWeapon()) {
			Weapon weapon = inventory.getMainHand().getBaseWeaponType();
			if (weapon.contains(Weapon.Trait.AMMUNITION))
				abilityMod = dex;
			else if (weapon.contains(Weapon.Trait.FINESSE))
				abilityMod = (str >= dex) ? str : dex;
			else
				abilityMod = str;
		}

		attackBonus = proficiencyBonus + abilityMod;
	}

	public void calcAverageDamage() {
		// TODO - doesn't take into account magical bonuses or other features
		int str = owner.getStrMod(), dex = owner.getDexMod(), abilityMod = str;

		Inventory inventory = owner.getInventory();
		if (inventory.equippedWeapon()) {
			Weapon weapon = inventory.getMainHand().getBaseWeaponType();
			if (weapon.contains(Weapon.Trait.AMMUNITION))
				abilityMod = dex;
			else if (weapon.contains(Weapon.Trait.FINESSE))
				abilityMod = (str >= dex) ? str : dex;
			else
				abilityMod = str;
		}

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
