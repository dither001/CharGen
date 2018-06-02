
/*
 * TODO - I have an idea for a rewrite of the entire Armor/Weapon equip
 * algorithm. Next time, instead of cycling through switches, I want to create
 * a try-catch block that first attempts to locate said weapon/armor in the
 * inventory, then attempts to equip it. On success, the method ends. On
 * failure, it simply iterates through an ARRAY of the best armor/weapons
 * instead of using complicated switch hierarchies.
 */

//	UNARMED(1, Energy.BLUDGEONING, Hand.LIGHT),
//	CLUB(4, Energy.BLUDGEONING, Hand.LIGHT),
//	DAGGER(4, Energy.PIERCING, Hand.LIGHT),
//	GREATCLUB(8, Energy.PIERCING, Hand.TWO),
//	HANDAXE(6, Energy.SLASHING, Hand.LIGHT),
//	JAVELIN(6, Energy.PIERCING, Hand.ONE),
//	LIGHT_HAMMER(4, Energy.BLUDGEONING, Hand.LIGHT),
//	MACE(6, Energy.BLUDGEONING, Hand.ONE),
//	QUARTERSTAFF(6, Energy.BLUDGEONING, Hand.VERSATILE),
//	SICKLE(4, Energy.SLASHING, Hand.LIGHT),
//	SPEAR(6, Energy.PIERCING, Hand.VERSATILE),
//	LIGHT_CROSSBOW(8, Energy.PIERCING, Hand.TWO),
//	DART(4, Energy.PIERCING, Hand.ONE),
//	SHORTBOW(6, Energy.PIERCING, Hand.TWO),
//	SLING(4, Energy.BLUDGEONING, Hand.ONE),
//	BATTLEAXE(8, Energy.SLASHING, Hand.VERSATILE),
//	FLAIL(8, Energy.BLUDGEONING, Hand.ONE),
//	GLAIVE(10, Energy.SLASHING, Hand.HEAVY),
//	GREATAXE(12, Energy.SLASHING, Hand.HEAVY),
//	GREATSWORD(2, 6, Energy.SLASHING, Hand.HEAVY),
//	HALBERD(10, Energy.SLASHING, Hand.HEAVY),
//	LANCE(12, Energy.PIERCING, Hand.HEAVY),
//	LONGSWORD(8, Energy.SLASHING, Hand.VERSATILE),
//	MAUL(2, 6, Energy.BLUDGEONING, Hand.HEAVY),
//	MORNINGSTAR(8, Energy.PIERCING, Hand.ONE),
//	PIKE(10, Energy.PIERCING, Hand.HEAVY),
//	RAPIER(8, Energy.PIERCING, Hand.ONE),
//	SCIMITAR(6, Energy.SLASHING, Hand.LIGHT),
//	SHORTSWORD(6, Energy.PIERCING, Hand.LIGHT),
//	TRIDENT(6, Energy.PIERCING, Hand.VERSATILE),
//	WAR_PICK(8, Energy.PIERCING, Hand.ONE),
//	WARHAMMER(8, Energy.BLUDGEONING, Hand.VERSATILE),
//	WHIP(4, Energy.SLASHING, Hand.ONE),
//	BLOWGUN(1, Energy.PIERCING, Hand.ONE),
//	HAND_CROSSBOW(6, Energy.PIERCING, Hand.LIGHT),
//	HEAVY_CROSSBOW(10, Energy.PIERCING, Hand.HEAVY),
//	LONGBOW(8, Energy.PIERCING, Hand.HEAVY),
//	NET(4, Energy.BLUDGEONING, Hand.ONE),
//	SHIELD(4, Energy.BLUDGEONING, Hand.ONE);

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public enum Weapon implements Proficiency {
	UNARMED(1, Energy.BLUDGEONING, Hand.LIGHT),
	CLUB(4, Energy.BLUDGEONING, Hand.LIGHT),
	DAGGER(4, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE, Trait.THROWN),
	GREATCLUB(8, Energy.BLUDGEONING, Hand.TWO),
	HANDAXE(6, Energy.SLASHING, Hand.LIGHT, Trait.THROWN),
	JAVELIN(6, Energy.PIERCING, Hand.ONE, Trait.THROWN),
	LIGHT_HAMMER(4, Energy.BLUDGEONING, Hand.LIGHT, Trait.THROWN),
	MACE(6, Energy.BLUDGEONING, Hand.ONE),
	QUARTERSTAFF(6, Energy.BLUDGEONING, Hand.VERSATILE),
	SICKLE(4, Energy.SLASHING, Hand.LIGHT),
	SPEAR(6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN),
	LIGHT_CROSSBOW(8, Energy.PIERCING, Hand.TWO, Trait.AMMUNITION, Trait.LOADING),
	DART(4, Energy.PIERCING, Hand.ONE, Trait.FINESSE, Trait.THROWN),
	SHORTBOW(6, Energy.PIERCING, Hand.TWO, Trait.AMMUNITION),
	SLING(4, Energy.BLUDGEONING, Hand.ONE, Trait.AMMUNITION),
	BATTLEAXE(8, Energy.SLASHING, Hand.VERSATILE),
	FLAIL(8, Energy.BLUDGEONING, Hand.ONE),
	GLAIVE(10, Energy.SLASHING, Hand.HEAVY, Trait.REACH),
	GREATAXE(12, Energy.SLASHING, Hand.HEAVY),
	GREATSWORD(2, 6, Energy.SLASHING, Hand.HEAVY),
	HALBERD(10, Energy.SLASHING, Hand.HEAVY, Trait.REACH),
	LANCE(12, Energy.PIERCING, Hand.HEAVY, Trait.REACH),
	LONGSWORD(8, Energy.SLASHING, Hand.VERSATILE),
	MAUL(2, 6, Energy.BLUDGEONING, Hand.HEAVY),
	MORNINGSTAR(8, Energy.PIERCING, Hand.ONE),
	PIKE(10, Energy.PIERCING, Hand.HEAVY, Trait.REACH),
	RAPIER(8, Energy.PIERCING, Hand.ONE, Trait.FINESSE),
	SCIMITAR(6, Energy.SLASHING, Hand.LIGHT, Trait.FINESSE),
	SHORTSWORD(6, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE),
	TRIDENT(6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN),
	WAR_PICK(8, Energy.PIERCING, Hand.ONE),
	WARHAMMER(8, Energy.BLUDGEONING, Hand.VERSATILE),
	WHIP(4, Energy.SLASHING, Hand.ONE, Trait.FINESSE, Trait.REACH),
	BLOWGUN(1, Energy.PIERCING, Hand.ONE, Trait.AMMUNITION, Trait.LOADING),
	HAND_CROSSBOW(6, Energy.PIERCING, Hand.LIGHT, Trait.AMMUNITION, Trait.LOADING),
	HEAVY_CROSSBOW(10, Energy.PIERCING, Hand.HEAVY, Trait.AMMUNITION, Trait.LOADING),
	LONGBOW(8, Energy.PIERCING, Hand.HEAVY, Trait.AMMUNITION),
	NET(4, Energy.BLUDGEONING, Hand.ONE, Trait.THROWN),
	SHIELD(4, Energy.BLUDGEONING, Hand.ONE);

	// fields
	public static final Weapon DEFAULT_WEAPON = UNARMED;
	
	// these arrays can't be initialized in my static block for some reason
	public static final Weapon[] ALL_WEAPONS = { UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD };

	private static final Weapon[] oneHandedMeleeBludgeoning = { WARHAMMER, QUARTERSTAFF, MACE, LIGHT_HAMMER, CLUB };
	private static final Weapon[] oneHandedMeleePiercing = { SPEAR, TRIDENT, WAR_PICK, MORNINGSTAR, RAPIER, JAVELIN, SHORTSWORD, DAGGER };
	private static final Weapon[] oneHandedMeleeSlashing = { WHIP, BATTLEAXE, LONGSWORD, SCIMITAR, HANDAXE, SICKLE };
	
	private static final Weapon[] twoHandedMeleeBludgeoning = { MAUL, GREATCLUB, WARHAMMER, QUARTERSTAFF };
	private static final Weapon[] twoHandedMeleePiercing = { PIKE, SPEAR, TRIDENT };
	private static final Weapon[] twoHandedMeleeSlashing = { HALBERD, GLAIVE, GREATAXE, GREATSWORD, BATTLEAXE, LONGSWORD };
	
	private static final Weapon[] ranged = { LONGBOW, SHORTBOW, HEAVY_CROSSBOW, LIGHT_CROSSBOW, HAND_CROSSBOW, SLING, DART, BLOWGUN };
	private static final Weapon[] oneHandedRanged = { NET, HAND_CROSSBOW, SLING, DART, BLOWGUN };
	
	private static final Weapon[] druidWeapon = { SPEAR, QUARTERSTAFF, SCIMITAR, JAVELIN, MACE, SICKLE, CLUB, DAGGER, DART, SLING };
	private static final Weapon[] druidSimple = { SPEAR, QUARTERSTAFF, JAVELIN, MACE, SICKLE, CLUB, DAGGER, DART, SLING };

	public enum Trait { FINESSE, REACH, THROWN, AMMUNITION, LOADING };
	
	public enum Weight {
		SIMPLE, MARTIAL, DRUID, MONK, ROGUE, SORCERER
	};

	public enum Hand {
		LIGHT, ONE, VERSATILE, TWO, HEAVY
	};

	private int dice;
	private int face;
	private Energy damage;
	private Hand hand;
	private Trait[] traits;

	// constructors
	private Weapon() {
		this(1, 4, Energy.BLUDGEONING, Hand.ONE);
	}

	private Weapon(int face, Energy damage, Hand hand, Trait ... args) {
		this(1, face, damage, hand, args);
	}

	private Weapon(int dice, int face, Energy damage, Hand hand, Trait ... args) {
		this.dice = dice;
		this.face = face;
		this.damage = damage;
		this.hand = hand;

		traits = new Trait[args.length];
		for (int i = 0; i < args.length; ++i)
			traits[i] = args[i];
	}

	// methods
	public boolean contains(Trait trait) {
		boolean contains = false;
		if (traits.length > 0) {
			for (Trait el : traits)
				contains = (el.equals(trait)) ? true : false;
		}

		return contains;
	}
	
	public boolean requiresTwoHands() {
		return hand.equals(Hand.TWO);
	}

	public int averageDamage() {
		return face / 2 * dice;
	}

	public Trait[] getTraits() {
		return traits;
	}

	// static methods
	public static HashSet<Proficiency> simpleMeleeSet() {
		Weapon[] weapon = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR,
				UNARMED };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static HashSet<Proficiency> simpleRangedSet() {
		Weapon[] weapon = { LIGHT_CROSSBOW, DART, SHORTBOW, SLING };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static HashSet<Proficiency> martialMeleeSet() {
		Weapon[] weapon = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL,
				MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static HashSet<Proficiency> martialRangedSet() {
		Weapon[] weapon = { BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static HashSet<Proficiency> getProficiency(Class job) {
		HashSet<Proficiency> list = new HashSet<Proficiency>();
		list.add(UNARMED);

		switch (job) {
		case BARBARIAN:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
			list.add(SHIELD);
			break;
		case BARD:
			list.addAll(simpleMeleeSet());
			list.add(LONGSWORD);
			list.add(RAPIER);
			list.add(SHORTSWORD);
			list.add(HAND_CROSSBOW);
			break;
		case CLERIC:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.add(SHIELD);
			break;
		case DRUID:
			list.add(CLUB);
			list.add(DAGGER);
			list.add(DART);
			list.add(JAVELIN);
			list.add(MACE);
			list.add(QUARTERSTAFF);
			list.add(SCIMITAR);
			list.add(SICKLE);
			list.add(SLING);
			list.add(SPEAR);
			list.add(SHIELD);
			break;
		case FIGHTER:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
			list.add(SHIELD);
			break;
		case MONK:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.add(SHORTSWORD);
			break;
		case PALADIN:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
			list.add(SHIELD);
			break;
		case RANGER:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
			list.add(SHIELD);
			break;
		case ROGUE:
			list.addAll(simpleMeleeSet());
			list.add(LONGSWORD);
			list.add(RAPIER);
			list.add(SHORTSWORD);
			list.add(HAND_CROSSBOW);
			break;
		case SORCERER:
			list.add(DAGGER);
			list.add(DART);
			list.add(QUARTERSTAFF);
			list.add(SLING);
			list.add(LIGHT_CROSSBOW);
			break;
		case WARLOCK:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			break;
		case WIZARD:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			break;
		default:
			break;
		}

		return list;
	}

	public static Weapon bestOneHandedMeleeBludgeoning(Actor actor) {
		return bestMeleeWeapon(actor, Hand.ONE, Energy.BLUDGEONING);
	}

	public static Weapon bestOneHandedMeleePiercing(Actor actor) {
		return bestMeleeWeapon(actor, Hand.ONE, Energy.PIERCING);
	}

	public static Weapon bestOneHandedMeleeSlashing(Actor actor) {
		return bestMeleeWeapon(actor, Hand.ONE, Energy.SLASHING);
	}

	public static Weapon bestTwoHandedMeleeBludgeoning(Actor actor) {
		return bestMeleeWeapon(actor, Hand.TWO, Energy.BLUDGEONING);
	}

	public static Weapon bestTwoHandedMeleePiercing(Actor actor) {
		return bestMeleeWeapon(actor, Hand.TWO, Energy.PIERCING);
	}

	public static Weapon bestTwoHandedMeleeSlashing(Actor actor) {
		return bestMeleeWeapon(actor, Hand.TWO, Energy.SLASHING);
	}

	public static Weapon bestMeleeWeapon(Actor actor, Hand hand, Energy energy) {
		Weapon[] array = oneHandedMeleePiercing;
		
		if (hand.equals(Hand.ONE) && energy.equals(Energy.BLUDGEONING)) {
			array = oneHandedMeleeBludgeoning;
		} else if (hand.equals(Hand.ONE) && energy.equals(Energy.PIERCING)) {
			array = oneHandedMeleePiercing;
		} else if (hand.equals(Hand.ONE) && energy.equals(Energy.SLASHING)) {
			array = oneHandedMeleeSlashing;
		} else if (hand.equals(Hand.TWO) && energy.equals(Energy.BLUDGEONING)) {
			array = twoHandedMeleeBludgeoning;
		} else if (hand.equals(Hand.TWO) && energy.equals(Energy.PIERCING)) {
			array = twoHandedMeleePiercing;
		} else if (hand.equals(Hand.TWO) && energy.equals(Energy.SLASHING)) {
			array = twoHandedMeleeSlashing;
		}
		
		Inventory gear = actor.getInventory();
		Weapon bestWeapon = DEFAULT_WEAPON;

		for (int i = 0; i < array.length; ++i) {
			if (gear.hasWeaponOfType(array[i]) && gear.canUseWeapon(array[i])) {
//				System.out.println("Has " + array[i].toString());
				bestWeapon = array[i];
				break;
			}
		}
		
//		if (bestWeapon.equals(DEFAULT_WEAPON)) {
//			System.out.println("Couldn't find a melee weapon.");
//		}
		
		return bestWeapon;
	}

	public static Weapon bestMeleeWeapon(Actor actor) {
		Weapon bestWeapon = DEFAULT_WEAPON;

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.TWO, Energy.SLASHING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.TWO, Energy.PIERCING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.TWO, Energy.BLUDGEONING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.SLASHING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.PIERCING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.BLUDGEONING);
		
//		if (bestWeapon.equals(DEFAULT_WEAPON)) {
//			System.out.println("Couldn't find a melee weapon.");
//		}
		
		return bestWeapon;
	}

	public static Weapon bestOneHandedMelee(Actor actor) {
		Weapon bestWeapon = DEFAULT_WEAPON;

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.SLASHING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.PIERCING);

		if (bestWeapon.equals(DEFAULT_WEAPON))
			bestWeapon = bestMeleeWeapon(actor, Hand.ONE, Energy.BLUDGEONING);
		
//		if (bestWeapon.equals(DEFAULT_WEAPON)) {
//			System.out.println("Couldn't find a melee weapon.");
//		}
		
		return bestWeapon;
	}

	public static Weapon bestRangedWeapon(Actor actor) {
		Weapon[] array = ranged;
		Inventory gear = actor.getInventory();
		Weapon bestWeapon = DEFAULT_WEAPON;

		for (int i = 0; i < array.length; ++i) {
			if (gear.hasWeaponOfType(array[i]) && gear.canUseWeapon(array[i])) {
//				System.out.println("Has " + array[i].toString());
				bestWeapon = array[i];
				break;
			}
		}
		
//		if (bestWeapon.equals(DEFAULT_WEAPON)) {
//			System.out.println("Couldn't find a ranged weapon.");
//		}
		
		return bestWeapon;
	}

	public static Weapon bestOneHandedRanged(Actor actor) {
		Weapon[] array = oneHandedRanged;
		Inventory gear = actor.getInventory();
		Weapon bestWeapon = DEFAULT_WEAPON;

		for (int i = 0; i < array.length; ++i) {
			if (gear.hasWeaponOfType(array[i]) && gear.canUseWeapon(array[i])) {
//				System.out.println("Has " + array[i].toString());
				bestWeapon = array[i];
				break;
			}
		}
		
//		if (bestWeapon.equals(DEFAULT_WEAPON)) {
//			System.out.println("Couldn't find a ranged weapon.");
//		}
		
		return bestWeapon;
	}


	
	
	public static Weapon[] handsAscendingArray() {
		Weapon[] array = Arrays.copyOf(ALL_WEAPONS, ALL_WEAPONS.length);
		HandsCompareAscending sortMethod = new HandsCompareAscending();
		Arrays.sort(array, sortMethod);
		
		return array;
	}
	
	public static Weapon[] handsDescendingArray() {
		Weapon[] array = Arrays.copyOf(ALL_WEAPONS, ALL_WEAPONS.length);
		HandsCompareDescending sortMethod = new HandsCompareDescending();
		Arrays.sort(array, sortMethod);
		
		return array;
	}
	
	
	/*
	 * COMPARATOR CLASSES
	 */
	private static class HandsCompareAscending implements Comparator<Weapon> {
		// TODO - needs testing
		@Override
		public int compare(Weapon w1, Weapon w2) {
			int left = 0, right = 0;
			
			if (w1.hand.equals(Hand.LIGHT))
				left = 1;
			else if (w1.hand.equals(Hand.ONE))
				left = 2;
			else if (w1.hand.equals(Hand.VERSATILE))
				left = 3;
			else if (w1.hand.equals(Hand.TWO))
				left = 4;
			else if (w1.hand.equals(Hand.HEAVY))
				left = 5;

			if (w2.hand.equals(Hand.LIGHT))
				right = 1;
			else if (w2.hand.equals(Hand.ONE))
				right = 2;
			else if (w2.hand.equals(Hand.VERSATILE))
				right = 3;
			else if (w2.hand.equals(Hand.TWO))
				right = 4;
			else if (w2.hand.equals(Hand.HEAVY))
				right = 5;

			return left - right;
		}
	}

	private static class HandsCompareDescending implements Comparator<Weapon> {
		// TODO - needs testing
		@Override
		public int compare(Weapon w1, Weapon w2) {
			int left = 0, right = 0;
			
			if (w1.hand.equals(Hand.LIGHT))
				left = 1;
			else if (w1.hand.equals(Hand.ONE))
				left = 2;
			else if (w1.hand.equals(Hand.VERSATILE))
				left = 3;
			else if (w1.hand.equals(Hand.TWO))
				left = 4;
			else if (w1.hand.equals(Hand.HEAVY))
				left = 5;

			if (w2.hand.equals(Hand.LIGHT))
				right = 1;
			else if (w2.hand.equals(Hand.ONE))
				right = 2;
			else if (w2.hand.equals(Hand.VERSATILE))
				right = 3;
			else if (w2.hand.equals(Hand.TWO))
				right = 4;
			else if (w2.hand.equals(Hand.HEAVY))
				right = 5;

			return right - left;
		}
	}

}
