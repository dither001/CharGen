import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Combat implements Option {
	public enum Role {
		STRIKER, DEFENDER, LEADER, CONTROLLER
	}

	public enum AttackMode {
		MELEE_ATTACK, RANGED_ATTACK, SPELL_ATTACK, SPELL_SAVE
	}

	/*
	 * INNER CLASS - ATTACK
	 * 
	 */
	public class Attack {
		private Actor subject;
		//
		private boolean isWeaponAttack;
		private boolean proficient;
		private AttackMode attackMode;
		private Weapon.Instance weapon;
		private Spell spell;
		//
		private int attackBonus;
		private int averageDamage, damageModifier;
		private String dice;

		/*
		 * CONSTRUCTORS
		 */
		public Attack(Actor subject, Weapon.Instance weapon) {
			this.subject = subject;
			this.weapon = weapon;
			//
			this.isWeaponAttack = true;
			if (subject.getWeaponProficiency().contains(weapon.getWeapon()))
				this.proficient = true;
			else
				this.proficient = false;

			//
			calcAttackBonus();
			calcAverageDamage();
		}

		public Attack(Actor subject, Spell spell) {
			this.subject = subject;
			this.spell = spell;
			//
			this.isWeaponAttack = false;
			this.proficient = true;

			//
			calcAttackBonus();
			calcAverageDamage();
		}

		/*
		 * INSTANCE METHODS
		 */
		public int attackBonus() {
			return attackBonus;
		}

		public int averageDamage() {
			return averageDamage;
		}

		@Override
		public String toString() {
			EnumSet<Feature> features = owner.getFeatures();

			// begin
			String string;

			//
			String attack = (attackBonus >= 0) ? "+" + attackBonus : "" + attackBonus;
			String name;
			if (weaponAttack())
				name = "Weapon " + weapon.toString();
			else
				name = "Spell " + spell.toString();

			//
			String damageString = "", damageAvg, damageMod;
			damageAvg = (averageDamage > 0) ? " (" + averageDamage + ")" : " (0)";
			damageMod = (damageModifier > 0) ? "+" + damageModifier : (damageModifier < 0) ? "" + damageModifier : "";

			if (weaponAttack()) {
				damageString = String.format("%s%s%s", dice, damageMod, damageAvg);

			} else if (spellAttack()) {
				if (spell.equals(Spell.ELDRITCH_BLAST) && features.contains(Feature.AGONIZING_BLAST))
					damageString = String.format("%s%s%s", dice, damageMod, damageAvg);
				else
					damageString = String.format("%s%s", dice, damageAvg);

			}

			string = String.format("%s %s %s", name, attack, damageString);

			if (weaponAttack() && owner.getJob().equals(Class.ROGUE) && weapon.useDexterity())
				string += " w/Sneak Attack";

			return string;
		}

		public boolean equals(Attack other) {
			boolean equals = false;
			if (other.attackMode.equals(this.attackMode) && this.weaponAttack() && other.weaponAttack()) {
				// TODO - maybe

			} else if (other.attackMode.equals(this.attackMode) && this.spellAttack() && other.spellAttack()) {
				// TODO - maybe

			}

			return equals;
		}

		private boolean weaponAttack() {
			return isWeaponAttack;
		}

		private boolean spellAttack() {
			return isWeaponAttack != true;
		}

		private void calcAttackBonus() {
			Class job = owner.getJob();
			Class.Subclass archetype = owner.getArchetype();

			int abilityBonus = 0, proficiency = owner.getProficiencyBonus();
			int strength = owner.getStrengthModifier(), dexterity = owner.getDexterityModifier(),
					intelligence = owner.getIntelligenceModifier(), wisdom = owner.getWisdomModifier(),
					charisma = owner.getCharismaModifier();

			if (weaponAttack()) {
				// check for Finesse, -OR- check if ranged only
				if (weapon.useDexterity() && dexterity >= strength) {
					abilityBonus = dexterity;
				} else if (weapon.rangedOnly()) {
					abilityBonus = dexterity;
				} else {
					abilityBonus = strength;
				}

			} else if (spellAttack()) {
				// confirm if attack or save spell; get primary ability
				if (job.equals(Class.BARD) || job.equals(Class.PALADIN) || job.equals(Class.SORCERER)
						|| job.equals(Class.WARLOCK)) {
					abilityBonus = charisma;
				} else if (job.equals(Class.CLERIC) || job.equals(Class.DRUID) || job.equals(Class.MONK)
						|| job.equals(Class.RANGER)) {
					abilityBonus = wisdom;
				} else if (job.equals(Class.WIZARD) || archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)
						|| archetype.equals(Class.Subclass.ARCANE_TRICKSTER)) {
					abilityBonus = intelligence;
				}

			}

			/*
			 * MASSIVE TODO - needs magic, traits, features, and other bonuses
			 */

			if (attackMode != null && attackMode.equals(AttackMode.SPELL_SAVE)) {
				this.attackBonus = 8 + proficiency + abilityBonus;
			} else {
				this.attackBonus = (proficient) ? proficiency + abilityBonus : abilityBonus;

			}
		}

		private void calcAverageDamage() {
			Class job = owner.getJob();
			Class.Subclass archetype = owner.getArchetype();
			EnumSet<Feature> features = owner.getFeatures();

			int level = owner.getLevel(), abilityBonus = 0, baseDamage = 0;
			int strength = owner.getStrengthModifier(), dexterity = owner.getDexterityModifier(),
					intelligence = owner.getIntelligenceModifier(), wisdom = owner.getWisdomModifier(),
					charisma = owner.getCharismaModifier();

			if (weaponAttack()) {
				if (weapon.useDexterity() && dexterity >= strength) {
					abilityBonus = dexterity;
				} else if (weapon.rangedOnly()) {
					abilityBonus = dexterity;
				} else {
					abilityBonus = strength;
				}

				this.dice = weapon.getDiceString();
				baseDamage = weapon.getAverageDamage();

			} else if (spellAttack()) {
				// confirm if attack or save spell; get primary ability
				if (job.equals(Class.BARD) || job.equals(Class.PALADIN) || job.equals(Class.SORCERER)
						|| job.equals(Class.WARLOCK)) {
					abilityBonus = charisma;
				} else if (job.equals(Class.CLERIC) || job.equals(Class.DRUID) || job.equals(Class.MONK)
						|| job.equals(Class.RANGER)) {
					abilityBonus = wisdom;
				} else if (job.equals(Class.WIZARD) || archetype.equals(Class.Subclass.ELDRITCH_KNIGHT)
						|| archetype.equals(Class.Subclass.ARCANE_TRICKSTER)) {
					abilityBonus = intelligence;
				}

				this.dice = Spell.getDiceString(spell);
				baseDamage = Spell.getAverageDamage(spell);

				if (spell.equals(Spell.ELDRITCH_BLAST) && features.contains(Feature.AGONIZING_BLAST)) {
					this.damageModifier = abilityBonus;
					baseDamage += charisma;
				}

			}

			/*
			 * MASSIVE TODO - needs magic, traits, features, and other bonuses
			 */

			if (weaponAttack()) {
				this.damageModifier = abilityBonus;

				if (features.contains(Feature.EXTRA_ATTACK_3))
					this.averageDamage = (baseDamage + abilityBonus) * 4;
				else if (features.contains(Feature.EXTRA_ATTACK_2))
					this.averageDamage = (baseDamage + abilityBonus) * 3;
				else if (features.contains(Feature.EXTRA_ATTACK_1))
					this.averageDamage = (baseDamage + abilityBonus) * 2;
				else
					this.averageDamage = baseDamage + abilityBonus;

				// sneak attack bonus
				if (weapon.useDexterity() && features.contains(Feature.SNEAK_ATTACK_1)) {
					this.averageDamage += ((owner.getLevel() + 1) / 2 * 7 / 2);

				}

			} else if (spellAttack()) {

				if (Spell.cantrip(spell) && level >= 17)
					this.averageDamage = baseDamage * 4;
				else if (Spell.cantrip(spell) && level >= 11)
					this.averageDamage = baseDamage * 3;
				else if (Spell.cantrip(spell) && level >= 5)
					this.averageDamage = baseDamage * 2;
				else
					this.averageDamage = baseDamage;

			}
		}

	}

	/*
	 * INNER CLASS - COMPARTOR
	 */
	private class SortAttackThenDamage implements Comparator<Attack> {

		@Override
		public int compare(Attack arg1, Attack arg2) {
			int sort = 0;

			// first attempt, "simplest"
			int left = arg1.attackBonus + (arg1.averageDamage / 2);
			int right = arg2.attackBonus + (arg2.averageDamage / 2);
			sort = right - left;

			if (sort == 0) {
				left = arg1.attackBonus + arg1.averageDamage;
				right = arg2.attackBonus + arg2.averageDamage;
				sort = right - left;
			}

			return sort;
		}

	}

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	// inventory get
	private Inventory gear;;

	// feature get
	private EnumSet<Feature> features;
	private EnumSet<Spell> spells;

	// ability get
	private int strength, dexterity, constitution;
	private int intelligence, wisdom, charisma;

	private Actor owner;
	private Role role;
	private AttackMode preferredAttack;
	private Weapon.Instance preferredWeapon;
	private Spell preferredCantrip;

	private int armorClass, hitPoints;
	private int attackBonus;
	private int averageDamage, damageModifier;

	private String bounty;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Combat(Actor owner) {
		this.owner = owner;
	}

	/*
	 * INTEGRAL METHODS
	 * 
	 */
	public void update() {
		// inventory
		this.gear = owner.getInventory();

		// features
		if (owner.getFeatures() != null)
			this.features = owner.getFeatures();
		else
			this.features = EnumSet.noneOf(Feature.class);

		// spells
		if (owner.getSpellsKnown() != null)
			this.spells = owner.getSpellsKnown();
		else
			this.spells = EnumSet.noneOf(Spell.class);

		// ability scores
		this.strength = owner.getStrength();
		this.dexterity = owner.getDexterity();
		this.constitution = owner.getConstitution();
		this.intelligence = owner.getIntelligence();
		this.wisdom = owner.getWisdom();
		this.charisma = owner.getCharisma();

		//
		calcArmorClass();
		calcHitPoints();
		bounty = toStringCR();
	}

	public static void setupCombatBlock(Actor actor) {
		// initialization
		Combat combat = new Combat(actor);

		// inventory
		combat.gear = actor.getInventory();

		// features
		if (actor.getFeatures() != null)
			combat.features = actor.getFeatures();
		else
			combat.features = EnumSet.noneOf(Feature.class);

		// spells
		if (actor.getSpellsKnown() != null)
			combat.spells = actor.getSpellsKnown();
		else
			combat.spells = EnumSet.noneOf(Spell.class);

		// ability scores
		combat.strength = actor.getStrength();
		combat.dexterity = actor.getDexterity();
		combat.constitution = actor.getConstitution();
		combat.intelligence = actor.getIntelligence();
		combat.wisdom = actor.getWisdom();
		combat.charisma = actor.getCharisma();

		/*
		 * FIXME - clean up loose ends
		 */

		combat.calcArmorClass();
		combat.calcHitPoints();
		combat.bounty = combat.toStringCR();
		//
		actor.setCombatBlock(combat);
	}

	private void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		byte[] hitDice = owner.getHitDice();
		int constitution = owner.getConstitutionModifier();

		int hp = 0, perLevel = constitution;

		// check features
		EnumSet<Feature> features = owner.getFeatures();
		if (features.contains(Feature.DRACONIC_RESILIENCE))
			perLevel += 1;

		//
		for (int i = 0; i < level; ++i) {
			hp += (hitDice[i] + perLevel > 0) ? hitDice[i] + perLevel : 1;
		}

		// final step
		this.hitPoints = hp;
	}

	private void calcArmorClass() {
		// and begin
		int ac = 10 + dexterity;

		if (owner.notWearingArmor()) {

		} else {

		}

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

		} else if (job.equals(Class.SORCERER) && owner.notWearingArmor()) {
			boolean draconic = owner.getFeatures().contains(Feature.DRACONIC_RESILIENCE);

			if (draconic)
				ac = 13 + dexterity;
			else if (spells.contains(Spell.MAGE_ARMOR))
				ac = 13 + dexterity;

		} else {
			// all others

			if (owner.wearingArmor() && owner.usingShield())
				ac = gear.getBodyArmorBonus() + gear.getShieldArmorBonus();
			else if (owner.notWearingArmor() && spells.contains(Spell.MAGE_ARMOR))
				ac = 13 + dexterity;
			else
				ac = gear.getBodyArmorBonus();
		}

		this.armorClass = ac;
	}

	private void relaventFeaturesUpdate() {
		/*
		 * I'm considering a massive boolean list for "combat relevant features" so I
		 * don't have to run contains() EVERY single time I want to check for relevant
		 * features; if this is good, I'll probably migrate to the Option/Feature object
		 * and create an inner "CombatFeatures" object that contains ALL this stuff
		 */

	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public boolean hasOwner() {
		return owner != null;
	}

	public Attack bestAttack() {
		return orderedAttackList().get(0);
	}

	public String topThreeAttacks() {
		String string = "";
		List<Attack> list = orderedAttackList();

		if (list.size() > 0)
			string += "\n" + list.get(0).toString();

		if (list.size() > 1)
			string += "\n" + list.get(1).toString();

		if (list.size() > 2)
			string += "\n" + list.get(2).toString();

		return string;
	}

	public List<Attack> orderedAttackList() {
		List<Attack> list = new ArrayList<Attack>();

		// every character has an unarmed attack
		list.add(new Attack(owner, Weapon.unarmed(owner)));

		Weapon.Instance weapon;
		for (Iterator<Weapon.Instance> it = owner.getInventory().weaponList().iterator(); it.hasNext();) {
			weapon = it.next();

			list.add(new Attack(owner, weapon));
		}

		Spell spell;
		for (Iterator<Spell> it = owner.getSpellsKnown().iterator(); it.hasNext();) {
			spell = it.next();

			if (Spell.isCombatSpell(spell))
				list.add(new Attack(owner, spell));
		}

		SortAttackThenDamage sort = new SortAttackThenDamage();
		Collections.sort(list, sort);

		return list;
	}

	public Set<Attack> attackSet() {
		Set<Attack> set = new HashSet<Attack>();

		Weapon.Instance weapon;
		for (Iterator<Weapon.Instance> it = owner.getInventory().weaponList().iterator(); it.hasNext();) {
			weapon = it.next();

			set.add(new Attack(owner, weapon));
		}

		Spell spell;
		for (Iterator<Spell> it = owner.getSpellsKnown().iterator(); it.hasNext();) {
			spell = it.next();

			if (Spell.isCombatSpell(spell))
				set.add(new Attack(owner, spell));
		}

		return set;
	}

	/*
	 * FIXME - LOTS OF OLD STUFF
	 * 
	 */

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
		return String.format("CR %s (%-2d exp) ", string, experienceValue);
	}

	public String toStringDetailed() {
		String string = "", armor = null, attack = "";

		Inventory gear = owner.getInventory();
		if (gear.wearingArmor())
			armor = "armor";

		if (gear.usingShield())
			armor = (armor != null) ? armor + ", shield" : "shield";

		if (armor != null)
			armor = " (" + armor + ")";
		else
			armor = "";

		string = String.format("AC %2d%s || %2d hp || %s", armorClass, armor, hitPoints, bounty);
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

}
