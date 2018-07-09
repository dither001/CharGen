import java.util.Set;

public class CombatBlock {
	public enum Role {
		STRIKER, DEFENDER, LEADER, CONTROLLER
	}

	public enum AttackMode {
		MELEE_ATTACK, RANGED_ATTACK, SPELL_ATTACK, SPELL_SAVE
	}

	private class Attack {
		private Actor subject;
		private Weapon.Instance weapon;

		public Attack(Actor subject, Weapon.Instance weapon) {
			
		}
		
	}

	// fields
	private Actor owner;
	private Role role;
	private AttackMode preferredAttack;
	private Weapon.Instance preferredWeapon;
	private Spell preferredCantrip;

	private int armorClass;
	private int hitPoints;
	private int attackBonus;
	private int averageDamage, damageModifier;

	// constructors
	public CombatBlock(Actor owner) {
		this.owner = owner;
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void update() {
		calcArmorClass();
		calcHitPoints();
		calcAttackBonus();
		calcAverageDamage();
	}

	public boolean hasOwner() {
		return owner != null;
	}

	private void preferredAttackType() {
		Class job = owner.getJob();

		int STR = owner.getStrength(), DEX = owner.getDexterity(), CON = owner.getConstitution(),
				INT = owner.getIntelligence(), WIS = owner.getWisdom(), CHA = owner.getCharisma();
		if (job.equals(Class.BARBARIAN))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.BARD))
			preferredAttack = AttackMode.SPELL_ATTACK;
		else if (job.equals(Class.CLERIC))
			preferredAttack = AttackMode.SPELL_ATTACK;
		else if (job.equals(Class.DRUID))
			preferredAttack = AttackMode.SPELL_ATTACK;
		else if (job.equals(Class.FIGHTER))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.MONK))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.PALADIN))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.RANGER))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.ROGUE))
			preferredAttack = (STR > DEX) ? AttackMode.MELEE_ATTACK : AttackMode.RANGED_ATTACK;
		else if (job.equals(Class.SORCERER))
			preferredAttack = AttackMode.SPELL_ATTACK;
		else if (job.equals(Class.WARLOCK))
			preferredAttack = AttackMode.SPELL_ATTACK;
		else if (job.equals(Class.WIZARD))
			preferredAttack = AttackMode.SPELL_ATTACK;

	}

	private void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		byte[] hitDice = owner.getHitDice();
		int constitution = owner.getConstitutionModifier();

		int hp = 0, perLevel = constitution;
		for (int i = 0; i < level; ++i) {
			hp += (hitDice[i] + perLevel > 0) ? hitDice[i] + perLevel : 1;
		}

		this.hitPoints = hp;
	}

	private void calcArmorClass() {
		int dexterity = owner.getDexterityModifier(), constitution, wisdom, ac = 10 + dexterity;
		Inventory gear = owner.getInventory();

		Class job = owner.getJob();
		if (job.equals(Class.BARBARIAN)) {
			// barbarian unarmored defense
			constitution = owner.getConstitutionModifier();

			if (owner.notWearingArmor() && owner.usingShield())
				ac = 10 + dexterity + constitution + gear.getShieldArmorBonus();
			else if (owner.notWearingArmor())
				ac = 10 + dexterity + constitution;
			else if (owner.wearingArmor() && owner.usingShield())
				ac = gear.getBodyArmorBonus() + gear.getShieldArmorBonus();
			else if (owner.wearingArmor())
				ac = gear.getBodyArmorBonus();

		} else if (job.equals(Class.MONK)) {
			// monk unarmored defense
			wisdom = owner.getWisdomModifier();

			if (owner.notWearingArmor() && owner.notUsingShield())
				ac = 10 + dexterity + wisdom;

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
		int atk = 0, STR = owner.getStrengthModifier(), DEX = owner.getDexterityModifier(),
				INT = owner.getIntelligenceModifier(), WIS = owner.getWisdomModifier(),
				CHA = owner.getCharismaModifier();

		Class job = owner.getJob();
		Set<Spell> spellsKnown, cantrips;
		if (preferredAttack.equals(AttackMode.MELEE_ATTACK) || preferredAttack.equals(AttackMode.RANGED_ATTACK)) {
			preferredWeapon = owner.getInventory().getMainHand();

			if (preferredWeapon != null && preferredWeapon.useDexterity())
				atk += owner.getProficiencyBonus() + DEX + preferredWeapon.getAttackBonus();
			else if (preferredWeapon != null)
				atk += owner.getProficiencyBonus() + STR + preferredWeapon.getAttackBonus();

		} else if (preferredAttack.equals(AttackMode.SPELL_ATTACK)) {
			spellsKnown = owner.getSpellsKnown();
			cantrips = Spell.retainSpellsOfTier(0, spellsKnown);
			preferredCantrip = Spell.highestDamagingSpell(cantrips);

			if (job.equals(Class.BARD) || job.equals(Class.SORCERER) || job.equals(Class.WARLOCK))
				atk += owner.getProficiencyBonus() + CHA;
			else if (job.equals(Class.CLERIC) || job.equals(Class.DRUID))
				atk += owner.getProficiencyBonus() + WIS;
			else if (job.equals(Class.WIZARD))
				atk += owner.getProficiencyBonus() + INT;

		}

		this.attackBonus = atk;
	}

	private void calcAverageDamage() {
		int dmg = 0, STR = owner.getStrengthModifier(), DEX = owner.getDexterityModifier(),
				INT = owner.getIntelligenceModifier(), WIS = owner.getWisdomModifier(),
				CHA = owner.getCharismaModifier();

		Weapon.Instance weapon = owner.getInventory().getMainHand();
		if (preferredAttack.equals(AttackMode.MELEE_ATTACK) || preferredAttack.equals(AttackMode.RANGED_ATTACK)) {
			if (weapon != null && weapon.useDexterity()) {
				dmg += weapon.getAverageDamage() + DEX + weapon.getAttackBonus();
				damageModifier = DEX + weapon.getAttackBonus();
			} else if (weapon != null) {
				dmg += weapon.getAverageDamage() + STR + weapon.getAttackBonus();
				damageModifier = STR + weapon.getAttackBonus();
			}

		} else if (preferredAttack.equals(AttackMode.SPELL_ATTACK)) {
			if (preferredCantrip != null)
				dmg = Spell.getAverageDamage(preferredCantrip);

		}

		this.averageDamage = dmg;
	}

	public Role getRole() {
		return (role != null) ? role : Role.STRIKER;
	}

	public AttackMode getPreferredAttackType() {
		return preferredAttack;
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

	public int highestSpellDamage() {
		int spellDamage = 0;
		Set<Spell> spellsKnown = owner.getSpellsKnown();
		Spell bestSpell = Spell.highestDamagingSpell(spellsKnown);
		if (bestSpell != null)
			spellDamage = Spell.getAverageDamage(bestSpell);

		return spellDamage;
	}

	public int getChallengeRating() {
		return ChallengeRating.evaluateCR(owner);
	}

	private String toStringCR() {
		String string = "";

		int challengeRating = ChallengeRating.evaluateCR(owner);
		if (challengeRating > 0)
			string += challengeRating;
		else if (challengeRating == 0)
			string += "1/2";
		else if (challengeRating == -1)
			string += "1/4";
		else if (challengeRating == -2)
			string += "1/8";
		else if (challengeRating == -3)
			string += "0";

		int experienceValue = ChallengeRating.challengeToXP(challengeRating);
		return String.format("CR %s (%-2d) ", string, experienceValue);
	}

	public String toStringDetailed() {
		String string = "", attack = "";

		if (preferredAttack.equals(AttackMode.MELEE_ATTACK) || preferredAttack.equals(AttackMode.RANGED_ATTACK)) {
			attack = (preferredWeapon != null) ? preferredWeapon.toString() + " " : "Unarmed ";
			attack += (attackBonus > -1) ? "+" + attackBonus + " " : attackBonus + " ";
			attack += preferredWeapon.getDiceString();
			attack += (damageModifier > -1) ? "+" + damageModifier : damageModifier;
			attack += (averageDamage > 0) ? " (" + averageDamage + ")" : "";

			string = String.format("AC %2d || %2d hp || %s || %s", armorClass, hitPoints, attack, toStringCR());

		} else if (preferredAttack.equals(AttackMode.SPELL_ATTACK)) {
			attack = (preferredCantrip != null) ? preferredCantrip.toString() + " " : "Cantrip ";
			attack += (attackBonus > -1) ? "+" + attackBonus + " " : attackBonus + " ";
			attack += Spell.getDiceString(preferredCantrip);
			attack += (averageDamage > 0) ? " (" + averageDamage + ")" : "";

			string = String.format("AC %2d || %2d hp || %s || %s", armorClass, hitPoints, attack, toStringCR());

		}

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

		combat.preferredAttackType();
		combat.calcArmorClass();
		combat.calcHitPoints();
		combat.calcAttackBonus();
		combat.calcAverageDamage();
		//
		actor.setCombatBlock(combat);
	}
}
