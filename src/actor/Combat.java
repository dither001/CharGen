package actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gear.Inventory;
import gear.Weapon;
import magic.Spell;

public class Combat implements Option {
	public enum Role {
		STRIKER, DEFENDER, LEADER, CONTROLLER
	}

	public enum AttackMode {
		MELEE_ATTACK, RANGED_ATTACK, SPELL_ATTACK, SPELL_SAVE
	}

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	// inventory get
	private Inventory gear;

	// feature get
	private EnumSet<Feature> features;
	private EnumSet<Spell> spells;

	// ability get
	private int strengthBonus, dexterityBonus, constitutionBonus;
	private int intelligenceBonus, wisdomBonus, charismaBonus;

	private Player owner;
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
	public Combat(Player owner) {
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
		this.strengthBonus = owner.getStrengthModifier();
		this.dexterityBonus = owner.getDexterityModifier();
		this.constitutionBonus = owner.getConstitutionModifier();
		this.intelligenceBonus = owner.getIntelligenceModifier();
		this.wisdomBonus = owner.getWisdomModifier();
		this.charismaBonus = owner.getCharismaModifier();

		//
		calcArmorClass();
		calcHitPoints();
		bounty = toStringCR();
	}

	public static void setupCombat(Player actor) {
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
		combat.strengthBonus = actor.getStrengthModifier();
		combat.dexterityBonus = actor.getDexterityModifier();
		combat.constitutionBonus = actor.getConstitutionModifier();
		combat.intelligenceBonus = actor.getIntelligenceModifier();
		combat.wisdomBonus = actor.getWisdomModifier();
		combat.charismaBonus = actor.getCharismaModifier();

		/*
		 * FIXME - clean up loose ends
		 */

		combat.calcArmorClass();
		combat.calcHitPoints();

		//
		actor.setCombat(combat);
		// CR must be calculated AFTER combat block has been set
		combat.bounty = combat.toStringCR();
	}

	private void calcHitPoints() {
		// TODO - doesn't take into account magical bonuses or other features
		int level = owner.getLevel();
		int[] hitDice = owner.getHitDice();
		int constitution = owner.getConstitutionModifier();

		int hp = 0, perLevel = constitution;

		// check features
		EnumSet<Feature> features = owner.getFeatures();
		if (features.contains(Feature.DRACONIC_RESILIENCE))
			perLevel += 1;

		if (features.contains(Feature.STOUT_RESILIENCE))
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
		int ac = 10 + dexterityBonus;

		if (owner.notWearingArmor()) {
			/*
			 * UNARMORED
			 */
			if (features.contains(Feature.UNARMORED_BARBARIAN)) {
				// barbarian
				ac = 10 + dexterityBonus + constitutionBonus;

				if (owner.usingShield())
					ac += gear.getShieldArmorBonus();

			} else if (features.contains(Feature.UNARMORED_MONK)) {
				// monk
				if (owner.notUsingShield())
					ac = 10 + dexterityBonus + wisdomBonus;
				else
					ac = 10 + dexterityBonus + gear.getShieldArmorBonus();

			} else if (spells.contains(Spell.MAGE_ARMOR) || features.contains(Feature.DRACONIC_RESILIENCE)) {
				if (owner.notUsingShield())
					ac = 13 + dexterityBonus;
				else
					ac = 13 + dexterityBonus + gear.getShieldArmorBonus();

			}

		} else {
			/*
			 * ARMORED
			 */
			if (owner.notUsingShield())
				ac = gear.getBodyArmorBonus();
			else
				ac = gear.getBodyArmorBonus() + gear.getShieldArmorBonus();

		}

		// final step
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
	public Attack unarmedAttack() {
		return new Attack(owner, Weapon.unarmed(owner));
	}

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

	/*
	 * INNER CLASS - ATTACK
	 * 
	 */
	public class Attack {
		private Player subject;
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
		public Attack(Player subject, Weapon.Instance weapon) {
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

		public Attack(Player subject, Spell spell) {
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
			Subclass archetype = owner.getArchetype();

			int abilityBonus = 0, proficiency = owner.proficiency();

			if (weaponAttack()) {
				// check for Finesse, -OR- check if ranged only
				if (weapon.useDexterity() && dexterityBonus >= strengthBonus) {
					abilityBonus = dexterityBonus;
				} else if (weapon.rangedOnly()) {
					abilityBonus = dexterityBonus;
				} else {
					abilityBonus = strengthBonus;
				}

			} else if (spellAttack()) {
				// confirm if attack or save spell; get primary ability
				if (job.equals(Class.BARD) || job.equals(Class.PALADIN) || job.equals(Class.SORCERER)
						|| job.equals(Class.WARLOCK)) {
					abilityBonus = charismaBonus;
				} else if (job.equals(Class.CLERIC) || job.equals(Class.DRUID) || job.equals(Class.MONK)
						|| job.equals(Class.RANGER)) {
					abilityBonus = wisdomBonus;
				} else if (job.equals(Class.WIZARD) || archetype.equals(Subclass.ELDRITCH_KNIGHT)
						|| archetype.equals(Subclass.ARCANE_TRICKSTER)) {
					abilityBonus = intelligenceBonus;
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
			Subclass archetype = owner.getArchetype();
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
				} else if (job.equals(Class.WIZARD) || archetype.equals(Subclass.ELDRITCH_KNIGHT)
						|| archetype.equals(Subclass.ARCANE_TRICKSTER)) {
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
	 * INNER CLASS - COMPARATOR
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
	 * INNER CLASS - Challenge Rating
	 * 
	 */
	public static class ChallengeRating {
		// fields
		private static final int[] REWARDS = { 10, 25, 50, 100, 200, 450, 700, 1100, 1800, 2300, 2900, 3900, 5000, 5900,
				7200, 8400, 10000, 11500, 13000, 15000, 18000, 20000, 22000, 25000, 33000, 41000, 50000, 62000, 75000,
				90000, 105000, 120000, 135000, 155000 };

		// methods
		public static int evaluateCR(Player actor) {
			Combat.Attack bestAttack;
			if (actor.combat() != null)
				bestAttack = actor.combat().bestAttack();
			else
				bestAttack = actor.combat().unarmedAttack();

			int atk = bestAttack.attackBonus();
			int dmg = bestAttack.averageDamage();

			int hp = actor.getHitPoints();
			int ac = actor.getArmorClass();

			int offense, defense, finalRating = 0;
			defense = defenseRating(hp, ac);
			offense = offenseRating(dmg, atk);

			finalRating = (offense + defense) / 2;
			return finalRating;
		}

		public static int defenseRating(int hp, int ac) {
			int defenseRating = hitPointsToCR(hp);
			int armorRating = armorClassToCR(ac);
			int hitPointArmor = hitPointsToAC(hp);

			if (hitPointArmor > ac) {
				for (int i = defenseRating - armorRating; i > 1; i -= 2) {
					--defenseRating;
				}
			} else if (hitPointArmor < ac) {
				for (int i = armorRating - defenseRating; i > 1; i -= 2) {
					++defenseRating;
				}
			}

			return defenseRating;
		}

		private static int hitPointsToCR(int hp) {
			int rating = 31;
			int[] hitPoints = { 6, 35, 49, 70, 85, 100, 115, 130, 145, 160, 175, 190, 205, 220, 235, 250, 265, 280, 295,
					310, 325, 340, 355, 400, 445, 490, 535, 580, 625, 670, 715, 760, 805, 850 };

			for (int i = 0; i < hitPoints.length; ++i) {
				if (hp > 850) {
					break;
				} else if (hp <= hitPoints[i]) {
					rating = i - 3;
					break;
				}
			}

			return rating;
		}

		private static int hitPointsToAC(int hp) {
			int armorClass = 13;

			int rating = hitPointsToCR(hp);
			if (rating == 4) {
				armorClass = 14;
			} else if (rating <= 7) {
				armorClass = 15;
			} else if (rating <= 9) {
				armorClass = 16;
			} else if (rating <= 12) {
				armorClass = 17;
			} else if (rating <= 16) {
				armorClass = 18;
			} else if (rating <= 30) {
				armorClass = 19;
			} else if (rating > 30) {
				armorClass = 20;
			}

			return armorClass;
		}

		private static int armorClassToCR(int ac) {
			int rating = 17;

			if (ac <= 13) {
				rating = 0;
			} else if (ac <= 14) {
				rating = 4;
			} else if (ac <= 15) {
				rating = 5;
			} else if (ac <= 16) {
				rating = 8;
			} else if (ac <= 17) {
				rating = 10;
			} else if (ac <= 18) {
				rating = 13;
			} else if (ac > 18) {
				rating = 17;
			}

			return rating;
		}

		public static int offenseRating(int dmg, int atk) {
			int offenseRating = damageToCR(dmg);
			int attackRating = attackToCR(atk);
			int damageAttack = damageToAttack(dmg);

			if (damageAttack > atk) {
				for (int i = offenseRating - attackRating; i > 1; i -= 2) {
					--offenseRating;
				}
			} else if (damageAttack < atk) {
				for (int i = attackRating - offenseRating; i > 1; i -= 2) {
					++offenseRating;
				}
			}

			return offenseRating;
		}

		private static int damageToCR(int dmg) {
			int rating = 31;
			int[] damage = { 0, 3, 5, 8, 14, 20, 26, 32, 38, 44, 50, 56, 62, 68, 74, 80, 86, 92, 98, 104, 110, 116, 122,
					140, 158, 176, 194, 212, 230, 248, 266, 284, 302, 320 };

			for (int i = 0; i < damage.length; ++i) {
				if (dmg > 320) {
					break;
				} else if (dmg <= damage[i]) {
					rating = i - 3;
					break;
				}
			}

			return rating;
		}

		private static int damageToAttack(int dmg) {
			int attackBonus = 3;

			int rating = damageToCR(dmg);
			if (rating == 3) {
				attackBonus = 4;
			} else if (rating == 4) {
				attackBonus = 5;
			} else if (rating <= 7) {
				attackBonus = 6;
			} else if (rating <= 10) {
				attackBonus = 7;
			} else if (rating <= 15) {
				attackBonus = 8;
			} else if (rating == 16) {
				attackBonus = 9;
			} else if (rating <= 20) {
				attackBonus = 10;
			} else if (rating <= 23) {
				attackBonus = 11;
			} else if (rating <= 26) {
				attackBonus = 12;
			} else if (rating <= 29) {
				attackBonus = 13;
			} else if (rating > 29) {
				attackBonus = 14;
			}

			return attackBonus;
		}

		private static int attackToCR(int attack) {
			int rating = 31;

			if (attack <= 3) {
				rating = 0;
			} else if (attack <= 4) {
				rating = 3;
			} else if (attack <= 5) {
				rating = 4;
			} else if (attack <= 6) {
				rating = 5;
			} else if (attack <= 7) {
				rating = 8;
			} else if (attack <= 8) {
				rating = 11;
			} else if (attack <= 9) {
				rating = 16;
			} else if (attack <= 10) {
				rating = 17;
			} else if (attack <= 11) {
				rating = 21;
			} else if (attack <= 12) {
				rating = 24;
			} else if (attack <= 13) {
				rating = 27;
			} else if (attack <= 14) {
				rating = 30;
			}

			return rating;
		}

		public static int challengeToXP(int rating) {
			int exp = (rating < -3) ? 0 : (rating > 30) ? REWARDS[30] : REWARDS[rating + 3];
			return exp;
		}
	}
}
