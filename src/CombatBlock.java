
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
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void update() {
		// calcHitPoints();
	}

	public boolean hasOwner() {
		return owner != null;
	}

	private void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		byte[] hitDice = owner.getHitDice();
		int conMod = owner.getConstitutionModifier();

		int hp = 0, perLevel = conMod;
		for (int i = 0; i < level; ++i) {
			hp += (hitDice[i] + perLevel > 0) ? hitDice[i] + perLevel : 1;
		}

		this.hitPoints = hp;
	}

	private void calcArmorClass() {
		int dexMod = owner.getDexterityModifier(), conMod, wisMod, ac = 10 + dexMod;
		Inventory gear = owner.getInventory();

		Class job = owner.getJob();
		if (job.equals(Class.BARBARIAN)) {
			// barbarian unarmored defense
			conMod = owner.getConstitutionModifier();

			if (owner.notWearingArmor() && owner.usingShield())
				ac = 10 + dexMod + conMod + gear.getShieldArmorBonus();
			else if (owner.notWearingArmor())
				ac = 10 + dexMod + conMod;
			else if (owner.wearingArmor() && owner.usingShield())
				ac = gear.getBodyArmorBonus() + gear.getShieldArmorBonus();
			else if (owner.wearingArmor())
				ac = gear.getBodyArmorBonus();

		} else if (job.equals(Class.MONK)) {
			// monk unarmored defense
			wisMod = owner.getWisdomModifier();

			if (owner.notWearingArmor() && owner.notUsingShield())
				ac = 10 + dexMod + wisMod;

		} else {
			// all others

			if (owner.wearingArmor() && owner.usingShield())
				ac = gear.getBodyArmorBonus() + gear.getShieldArmorBonus();
			else
				ac = gear.getBodyArmorBonus();
		}

		this.armorClass = ac;
	}

	private void calcAttackBonus() {
		int atk = 0, strMod = owner.getStrengthModifier(), dexMod = owner.getDexterityModifier();
		Weapon.Instance weapon = owner.getInventory().getMainHand();

		if (weapon != null && weapon.useDexterity())
			atk += owner.getProficiencyBonus() + dexMod + weapon.getAttackBonus();
		else if (weapon != null)
			atk += owner.getProficiencyBonus() + strMod + weapon.getAttackBonus();

		this.attackBonus = atk;
	}

	private void calcAverageDamage() {
		int dmg = 0, strMod = owner.getStrengthModifier(), dexMod = owner.getDexterityModifier();
		Weapon.Instance weapon = owner.getInventory().getMainHand();

		if (weapon != null && weapon.useDexterity())
			dmg += weapon.getAverageDamage() + dexMod + weapon.getAttackBonus();
		else if (weapon != null)
			dmg += weapon.getAverageDamage() + strMod + weapon.getAttackBonus();

		this.averageDamage = dmg;
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

	public int getChallengeRating() {
		return ChallengeRating.evaluateCR(owner);
	}

	public String toStringDetailed() {
		int challengeRating = ChallengeRating.evaluateCR(owner);
		int experienceValue = ChallengeRating.challengeToXP(challengeRating);
		String string = String.format("AC %2d || %2d hp || %-2d (%2d) || CR %2d (%-2d)", armorClass, hitPoints,
				attackBonus, averageDamage, challengeRating, experienceValue);

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Role getRoleFromClass(Class job) {
		Role role = null;

		if (job.equals(Class.BARBARIAN))
			role = Role.DEFENDER;
		else if (job.equals(Class.BARD))
			role = Role.LEADER;
		else if (job.equals(Class.CLERIC))
			role = Role.LEADER;
		else if (job.equals(Class.DRUID))
			role = Role.LEADER;
		else if (job.equals(Class.FIGHTER))
			role = Role.DEFENDER;
		else if (job.equals(Class.MONK))
			role = Role.STRIKER;
		else if (job.equals(Class.PALADIN))
			role = Role.DEFENDER;
		else if (job.equals(Class.RANGER))
			role = Role.STRIKER;
		else if (job.equals(Class.ROGUE))
			role = Role.STRIKER;
		else if (job.equals(Class.SORCERER))
			role = Role.STRIKER;
		else if (job.equals(Class.WARLOCK))
			role = Role.STRIKER;
		else if (job.equals(Class.WIZARD))
			role = Role.CONTROLLER;

		return role;
	}

	public static void setupCombatBlock(Actor actor) {
		CombatBlock combat = new CombatBlock(actor);

		combat.calcArmorClass();
		combat.calcHitPoints();
		combat.calcAttackBonus();
		combat.calcAverageDamage();
		//
		actor.setCombatBlock(combat);
	}
}
