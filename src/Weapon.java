
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
import java.util.HashSet;

public enum Weapon implements Item, Proficiency {
	UNARMED(1, Energy.BLUDGEONING, Hand.LIGHT),
	CLUB(4, Energy.BLUDGEONING, Hand.LIGHT),
	DAGGER(4, Energy.PIERCING, Hand.LIGHT),
	GREATCLUB(8, Energy.BLUDGEONING, Hand.TWO),
	HANDAXE(6, Energy.SLASHING, Hand.LIGHT),
	JAVELIN(6, Energy.PIERCING, Hand.ONE),
	LIGHT_HAMMER(4, Energy.BLUDGEONING, Hand.LIGHT),
	MACE(6, Energy.BLUDGEONING, Hand.ONE),
	QUARTERSTAFF(6, Energy.BLUDGEONING, Hand.VERSATILE),
	SICKLE(4, Energy.SLASHING, Hand.LIGHT),
	SPEAR(6, Energy.PIERCING, Hand.VERSATILE),
	LIGHT_CROSSBOW(8, Energy.PIERCING, Hand.TWO),
	DART(4, Energy.PIERCING, Hand.ONE),
	SHORTBOW(6, Energy.PIERCING, Hand.TWO),
	SLING(4, Energy.BLUDGEONING, Hand.ONE),
	BATTLEAXE(8, Energy.SLASHING, Hand.VERSATILE),
	FLAIL(8, Energy.BLUDGEONING, Hand.ONE),
	GLAIVE(10, Energy.SLASHING, Hand.HEAVY),
	GREATAXE(12, Energy.SLASHING, Hand.HEAVY),
	GREATSWORD(2, 6, Energy.SLASHING, Hand.HEAVY),
	HALBERD(10, Energy.SLASHING, Hand.HEAVY),
	LANCE(12, Energy.PIERCING, Hand.HEAVY),
	LONGSWORD(8, Energy.SLASHING, Hand.VERSATILE),
	MAUL(2, 6, Energy.BLUDGEONING, Hand.HEAVY),
	MORNINGSTAR(8, Energy.PIERCING, Hand.ONE),
	PIKE(10, Energy.PIERCING, Hand.HEAVY),
	RAPIER(8, Energy.PIERCING, Hand.ONE),
	SCIMITAR(6, Energy.SLASHING, Hand.LIGHT),
	SHORTSWORD(6, Energy.PIERCING, Hand.LIGHT),
	TRIDENT(6, Energy.PIERCING, Hand.VERSATILE),
	WAR_PICK(8, Energy.PIERCING, Hand.ONE),
	WARHAMMER(8, Energy.BLUDGEONING, Hand.VERSATILE),
	WHIP(4, Energy.SLASHING, Hand.ONE),
	BLOWGUN(1, Energy.PIERCING, Hand.ONE),
	HAND_CROSSBOW(6, Energy.PIERCING, Hand.LIGHT),
	HEAVY_CROSSBOW(10, Energy.PIERCING, Hand.HEAVY),
	LONGBOW(8, Energy.PIERCING, Hand.HEAVY),
	NET(4, Energy.BLUDGEONING, Hand.ONE),
	SHIELD(4, Energy.BLUDGEONING, Hand.ONE);

	// fields
	private static final Weapon DEFAULT_WEAPON = UNARMED;
	
	// these arrays can't be initialized in my static block for some reason
	private static final Weapon[] oneHandedMeleeBludgeoning = { WARHAMMER, QUARTERSTAFF, MACE, LIGHT_HAMMER, CLUB };
	private static final Weapon[] oneHandedMeleePiercing = { SPEAR, TRIDENT, WAR_PICK, MORNINGSTAR, RAPIER, JAVELIN, SHORTSWORD, DAGGER };
	private static final Weapon[] oneHandedMeleeSlashing = { WHIP, BATTLEAXE, LONGSWORD, SCIMITAR, HANDAXE, SICKLE };
	
	private static final Weapon[] twoHandedMeleeBludgeoning = { MAUL, GREATCLUB, WARHAMMER, QUARTERSTAFF };
	private static final Weapon[] twoHandedMeleePiercing = { PIKE, SPEAR, TRIDENT };
	private static final Weapon[] twoHandedMeleeSlashing = { HALBERD, GLAIVE, GREATAXE, GREATSWORD, BATTLEAXE, LONGSWORD };
	
	private static final Weapon[] ranged = { LONGBOW, SHORTBOW, HEAVY_CROSSBOW, LIGHT_CROSSBOW, HAND_CROSSBOW, SLING, DART, BLOWGUN };

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

	// constructors
	private Weapon() {
		this(1, 4, Energy.BLUDGEONING, Hand.ONE);
	}

	private Weapon(int face, Energy damage, Hand hand) {
		this(1, face, damage, hand);
	}

	private Weapon(int dice, int face, Energy damage, Hand hand) {
		this.dice = dice;
		this.face = face;
		this.damage = damage;
		this.hand = hand;
	}

	// methods
	public boolean requiresTwoHands() {
		return hand.equals(Hand.TWO);
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

	public static HashSet<Proficiency> getProficiency(Archetype job) {
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
			if (gear.hasWeapon(array[i]) && gear.canUseWeapon(array[i])) {
				System.out.println("Has " + array[i].toString());
				bestWeapon = array[i];
				break;
			}
		}
		
		if (bestWeapon.equals(DEFAULT_WEAPON)) {
			System.out.println("Couldn't find a melee weapon.");
		}
		
		return bestWeapon;
	}

	public static Weapon bestMeleeWeapon(Actor actor) {
		Weapon bestWeapon = DEFAULT_WEAPON;

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
		
		if (bestWeapon.equals(DEFAULT_WEAPON)) {
			System.out.println("Couldn't find a melee weapon.");
		}
		
		return bestWeapon;
	}

	public static Weapon bestRangedWeapon(Actor actor) {
		Weapon[] array = ranged;
		Inventory gear = actor.getInventory();
		Weapon bestWeapon = DEFAULT_WEAPON;

		for (int i = 0; i < array.length; ++i) {
			if (gear.hasWeapon(array[i]) && gear.canUseWeapon(array[i])) {
				System.out.println("Has " + array[i].toString());
				bestWeapon = array[i];
				break;
			}
		}
		
		if (bestWeapon.equals(DEFAULT_WEAPON)) {
			System.out.println("Couldn't find a ranged weapon.");
		}
		
		return bestWeapon;
	}
}
