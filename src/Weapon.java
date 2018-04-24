
//	UNARMED(1, Energy.BLUDGEONING, Hand.LIGHT),
//	CLUB(4, Energy.BLUDGEONING, Hand.LIGHT),
//	DAGGER(4, Energy.PIERCING, Hand.LIGHT),
//	GREATCLUB(8, Energy.SLASHING, Hand.TWO),
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
//	WARHAMMER(8, Energy.SLASHING, Hand.VERSATILE),
//	WHIP(4, Energy.SLASHING, Hand.ONE),
//	BLOWGUN(1, Energy.PIERCING, Hand.ONE),
//	HAND_CROSSBOW(6, Energy.PIERCING, Hand.LIGHT),
//	HEAVY_CROSSBOW(10, Energy.PIERCING, Hand.HEAVY),
//	LONGBOW(8, Energy.PIERCING, Hand.HEAVY),
//	NET(4, Energy.BLUDGEONING, Hand.ONE);

import java.util.Arrays;
import java.util.HashSet;

public enum Weapon implements Item, Proficiency {
	UNARMED(1, Energy.BLUDGEONING, Hand.LIGHT),
	CLUB(4, Energy.BLUDGEONING, Hand.LIGHT),
	DAGGER(4, Energy.PIERCING, Hand.LIGHT),
	GREATCLUB(8, Energy.SLASHING, Hand.TWO),
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
	WARHAMMER(8, Energy.SLASHING, Hand.VERSATILE),
	WHIP(4, Energy.SLASHING, Hand.ONE),
	BLOWGUN(1, Energy.PIERCING, Hand.ONE),
	HAND_CROSSBOW(6, Energy.PIERCING, Hand.LIGHT),
	HEAVY_CROSSBOW(10, Energy.PIERCING, Hand.HEAVY),
	LONGBOW(8, Energy.PIERCING, Hand.HEAVY),
	NET(4, Energy.BLUDGEONING, Hand.ONE);

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
			break;
		case FIGHTER:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
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
			break;
		case RANGER:
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
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

//	public static HashSet<Proficiency> getProficiency(Weight weight) {
//		HashSet<Proficiency> list = new HashSet<Proficiency>();
//		list.add(UNARMED);
//
//		if (weight.equals(Weight.SIMPLE)) {
//			list.addAll(simpleMeleeSet());
//			list.addAll(simpleRangedSet());
//		} else if (weight.equals(Weight.MARTIAL)) {
//			list.addAll(simpleMeleeSet());
//			list.addAll(simpleRangedSet());
//			list.addAll(martialMeleeSet());
//			list.addAll(martialRangedSet());
//		} else if (weight.equals(Weight.DRUID)) {
//			list.add(CLUB);
//			list.add(DAGGER);
//			list.add(DART);
//			list.add(JAVELIN);
//			list.add(MACE);
//			list.add(QUARTERSTAFF);
//			list.add(SCIMITAR);
//			list.add(SICKLE);
//			list.add(SLING);
//			list.add(SPEAR);
//		} else if (weight.equals(Weight.MONK)) {
//			list.addAll(simpleMeleeSet());
//			list.addAll(simpleRangedSet());
//			list.add(SHORTSWORD);
//		} else if (weight.equals(Weight.ROGUE)) {
//			list.addAll(simpleMeleeSet());
//			list.add(LONGSWORD);
//			list.add(RAPIER);
//			list.add(SHORTSWORD);
//			list.add(HAND_CROSSBOW);
//		} else if (weight.equals(Weight.SORCERER)) {
//			list.add(DAGGER);
//			list.add(DART);
//			list.add(QUARTERSTAFF);
//			list.add(SLING);
//			list.add(LIGHT_CROSSBOW);
//		}
//
//		return list;
//	}
}
