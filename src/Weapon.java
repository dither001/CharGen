//	UNARMED(1, 1, Energy.BLUDGEONING, Hand.LIGHT),
//	CLUB(1, 4, Energy.BLUDGEONING, Hand.LIGHT),
//	DAGGER(1, 4, Energy.PIERCING, Hand.LIGHT),
//	GREATCLUB(1, 8, Energy.SLASHING, Hand.TWO),
//	HANDAXE(1, 6, Energy.SLASHING, Hand.LIGHT),
//	JAVELIN(1, 6, Energy.PIERCING, Hand.ONE),
//	LIGHT_HAMMER(1, 4, Energy.BLUDGEONING, Hand.LIGHT),
//	MACE(1, 6, Energy.BLUDGEONING, Hand.ONE),
//	QUARTERSTAFF(1, 6, Energy.BLUDGEONING, Hand.VERSATILE),
//	SICKLE(1, 4, Energy.SLASHING, Hand.LIGHT),
//	SPEAR(1, 6, Energy.PIERCING, Hand.VERSATILE),
//	LIGHT_CROSSBOW(1, 8, Energy.PIERCING, Hand.TWO),
//	DART(1, 4, Energy.PIERCING, Hand.ONE),
//	SHORTBOW(1, 6, Energy.PIERCING, Hand.TWO),
//	SLING(1, 4, Energy.BLUDGEONING, Hand.ONE),
//	BATTLEAXE(1, 8, Energy.SLASHING, Hand.VERSATILE),
//	FLAIL(1, 8, Energy.BLUDGEONING, Hand.ONE),
//	GLAIVE(1, 10, Energy.SLASHING, Hand.HEAVY),
//	GREATAXE(1, 12, Energy.SLASHING, Hand.HEAVY),
//	GREATSWORD(2, 6, Energy.SLASHING, Hand.HEAVY),
//	HALBERD(1, 10, Energy.SLASHING, Hand.HEAVY),
//	LANCE(1, 12, Energy.PIERCING, Hand.HEAVY),
//	LONGSWORD(1, 8, Energy.SLASHING, Hand.VERSATILE),
//	MAUL(2, 6, Energy.BLUDGEONING, Hand.HEAVY),
//	MORNINGSTAR(1, 8, Energy.PIERCING, Hand.ONE),
//	PIKE(1, 10, Energy.PIERCING, Hand.HEAVY),
//	RAPIER(1, 8, Energy.PIERCING, Hand.ONE),
//	SCIMITAR(1, 6, Energy.SLASHING, Hand.LIGHT),
//	SHORTSWORD(1, 6, Energy.PIERCING, Hand.LIGHT),
//	TRIDENT(1, 6, Energy.PIERCING, Hand.VERSATILE),
//	WAR_PICK(1, 8, Energy.PIERCING, Hand.ONE),
//	WARHAMMER(1, 8, Energy.SLASHING, Hand.VERSATILE),
//	WHIP(1, 4, Energy.SLASHING, Hand.ONE),
//	BLOWGUN(1, 1, Energy.PIERCING, Hand.ONE),
//	HAND_CROSSBOW(1, 6, Energy.PIERCING, Hand.LIGHT),
//	HEAVY_CROSSBOW(1, 10, Energy.PIERCING, Hand.HEAVY),
//	LONGBOW(1, 8, Energy.PIERCING, Hand.HEAVY),
//	NET(1, 4, Energy.BLUDGEONING, Hand.ONE);

import java.util.Arrays;
import java.util.EnumSet;

public enum Weapon implements Equippable {
	UNARMED(1, 1, Energy.BLUDGEONING, Hand.LIGHT),
	CLUB(1, 4, Energy.BLUDGEONING, Hand.LIGHT),
	DAGGER(1, 4, Energy.PIERCING, Hand.LIGHT),
	GREATCLUB(1, 8, Energy.SLASHING, Hand.TWO),
	HANDAXE(1, 6, Energy.SLASHING, Hand.LIGHT),
	JAVELIN(1, 6, Energy.PIERCING, Hand.ONE),
	LIGHT_HAMMER(1, 4, Energy.BLUDGEONING, Hand.LIGHT),
	MACE(1, 6, Energy.BLUDGEONING, Hand.ONE),
	QUARTERSTAFF(1, 6, Energy.BLUDGEONING, Hand.VERSATILE),
	SICKLE(1, 4, Energy.SLASHING, Hand.LIGHT),
	SPEAR(1, 6, Energy.PIERCING, Hand.VERSATILE),
	LIGHT_CROSSBOW(1, 8, Energy.PIERCING, Hand.TWO),
	DART(1, 4, Energy.PIERCING, Hand.ONE),
	SHORTBOW(1, 6, Energy.PIERCING, Hand.TWO),
	SLING(1, 4, Energy.BLUDGEONING, Hand.ONE),
	BATTLEAXE(1, 8, Energy.SLASHING, Hand.VERSATILE),
	FLAIL(1, 8, Energy.BLUDGEONING, Hand.ONE),
	GLAIVE(1, 10, Energy.SLASHING, Hand.HEAVY),
	GREATAXE(1, 12, Energy.SLASHING, Hand.HEAVY),
	GREATSWORD(2, 6, Energy.SLASHING, Hand.HEAVY),
	HALBERD(1, 10, Energy.SLASHING, Hand.HEAVY),
	LANCE(1, 12, Energy.PIERCING, Hand.HEAVY),
	LONGSWORD(1, 8, Energy.SLASHING, Hand.VERSATILE),
	MAUL(2, 6, Energy.BLUDGEONING, Hand.HEAVY),
	MORNINGSTAR(1, 8, Energy.PIERCING, Hand.ONE),
	PIKE(1, 10, Energy.PIERCING, Hand.HEAVY),
	RAPIER(1, 8, Energy.PIERCING, Hand.ONE),
	SCIMITAR(1, 6, Energy.SLASHING, Hand.LIGHT),
	SHORTSWORD(1, 6, Energy.PIERCING, Hand.LIGHT),
	TRIDENT(1, 6, Energy.PIERCING, Hand.VERSATILE),
	WAR_PICK(1, 8, Energy.PIERCING, Hand.ONE),
	WARHAMMER(1, 8, Energy.SLASHING, Hand.VERSATILE),
	WHIP(1, 4, Energy.SLASHING, Hand.ONE),
	BLOWGUN(1, 1, Energy.PIERCING, Hand.ONE),
	HAND_CROSSBOW(1, 6, Energy.PIERCING, Hand.LIGHT),
	HEAVY_CROSSBOW(1, 10, Energy.PIERCING, Hand.HEAVY),
	LONGBOW(1, 8, Energy.PIERCING, Hand.HEAVY),
	NET(1, 4, Energy.BLUDGEONING, Hand.ONE);

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

	private Weapon(int dice, int face, Energy damage, Hand hand) {
		this.dice = dice;
		this.face = face;
		this.damage = damage;
		this.hand = hand;
	}

	// static methods
	public static EnumSet<Weapon> simpleMeleeSet() {
		Weapon[] weapon = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR,
				UNARMED };
		EnumSet<Weapon> list = EnumSet.noneOf(Weapon.class);

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static EnumSet<Weapon> simpleRangedSet() {
		Weapon[] weapon = { LIGHT_CROSSBOW, DART, SHORTBOW, SLING };
		EnumSet<Weapon> list = EnumSet.noneOf(Weapon.class);

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static EnumSet<Weapon> martialMeleeSet() {
		Weapon[] weapon = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL,
				MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP };
		EnumSet<Weapon> list = EnumSet.noneOf(Weapon.class);

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static EnumSet<Weapon> martialRangedSet() {
		Weapon[] weapon = { BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };
		EnumSet<Weapon> list = EnumSet.noneOf(Weapon.class);

		list.addAll(Arrays.asList(weapon));
		return list;
	}

	public static EnumSet<Weapon> getProficiency(Weight weight) {
		EnumSet<Weapon> list = EnumSet.noneOf(Weapon.class);
		list.add(UNARMED);

		if (weight.equals(Weight.SIMPLE)) {
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
		} else if (weight.equals(Weight.MARTIAL)) {
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.addAll(martialMeleeSet());
			list.addAll(martialRangedSet());
		} else if (weight.equals(Weight.DRUID)) {
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
		} else if (weight.equals(Weight.MONK)) {
			list.addAll(simpleMeleeSet());
			list.addAll(simpleRangedSet());
			list.add(SHORTSWORD);
		} else if (weight.equals(Weight.ROGUE)) {
			list.addAll(simpleMeleeSet());
			list.add(LONGSWORD);
			list.add(RAPIER);
			list.add(SHORTSWORD);
			list.add(HAND_CROSSBOW);
		} else if (weight.equals(Weight.SORCERER)) {
			list.add(DAGGER);
			list.add(DART);
			list.add(QUARTERSTAFF);
			list.add(SLING);
			list.add(LIGHT_CROSSBOW);
		}

		return list;
	}
}
