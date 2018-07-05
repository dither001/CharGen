import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;

public enum Weapon {
	UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD, ARROW, BOLT, BULLET, NEEDLE, CRYSTAL, ORB, ROD, ARCANE_STAFF, WAND, MISTLETOE, TOTEM, WOODEN_STAFF, YEW_WAND, AMULET, EMBLEM, RELIQUARY;

	public enum Type {
		UNARMED, CLUB, DAGGER, AXE, SPEAR, MACE, STAFF, SPECIAL, CROSSBOW, BOW, POLEARM, SWORD, SHIELD, AMMUNITION, ARCANE_FOCUS, DRUID_FOCUS, HOLY_SYMBOL
	};

	public enum Trait {
		FINESSE, REACH, THROWN, AMMUNITION, LOADING, IMPROVISED
	};

	public enum Hand {
		LIGHT, ONE, VERSATILE, TWO, HEAVY
	};

	public static class Prototype {
		private Weapon weapon;
		private Type type;
		private int dice, faces;
		private Energy energy;
		private Hand hand;
		private EnumSet<Trait> traits;

		public Prototype(Weapon weapon, Type type, int face, Energy energy, Hand hand, Trait... args) {
			this(weapon, type, 1, face, energy, hand, args);
		}

		public Prototype(Weapon weapon, Type type, int dice, int face, Energy energy, Hand hand, Trait... args) {
			this.weapon = weapon;
			this.type = type;
			this.dice = dice;
			this.faces = face;
			this.energy = energy;
			this.hand = hand;

			this.traits = EnumSet.noneOf(Trait.class);
			for (int i = 0; i < args.length; ++i) {
				traits.add(args[i]);
			}
		}

		public Weapon getWeapon() {
			return weapon;
		}

		public Type getType() {
			return type;
		}

		public int getAverageDamage() {
			return faces / 2 * dice;
		}

		public Energy getDamageType() {
			return energy;
		}

		public Hand getHand() {
			return hand;
		}
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static HashMap<Weapon, Prototype> weapons;

	public static final Weapon DEFAULT_WEAPON = UNARMED;

	private static final Weapon[] ALL_WEAPONS = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE,
			GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT,
			WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD };

	private static final Weapon[] SIMPLE_MELEE = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR };
	// private static final Weapon[] SIMPLE_RANGED = { LIGHT_CROSSBOW, DART,
	// SHORTBOW, SLING };
	private static final Weapon[] SIMPLE_WEAPONS = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING };

	private static final Weapon[] MARTIAL_MELEE = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE,
			LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP };
	// private static final Weapon[] MARTIAL_RANGED = { BLOWGUN, HAND_CROSSBOW,
	// HEAVY_CROSSBOW, LONGBOW, NET };
	private static final Weapon[] MARTIAL_WEAPONS = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE,
			LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP,
			BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };

	private static final Weapon[] DRUID_WEAPONS = { CLUB, DAGGER, DART, JAVELIN, MACE, QUARTERSTAFF, SCIMITAR, SICKLE,
			SLING, SPEAR };
	private static final Weapon[] ROGUE_WEAPONS = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, LONGSWORD, RAPIER, SHORTSWORD,
			HAND_CROSSBOW };
	private static final Weapon[] MAGE_WEAPONS = { DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW };

	static {
		weapons = new HashMap<Weapon, Prototype>();
		weapons.put(UNARMED, new Prototype(UNARMED, Type.UNARMED, 1, 1, Energy.BLUDGEONING, Hand.LIGHT));
		weapons.put(CLUB, new Prototype(CLUB, Type.CLUB, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weapons.put(DAGGER,
				new Prototype(DAGGER, Type.DAGGER, 1, 4, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE, Trait.THROWN));
		weapons.put(GREATCLUB, new Prototype(GREATCLUB, Type.CLUB, 1, 8, Energy.BLUDGEONING, Hand.TWO));
		weapons.put(HANDAXE, new Prototype(HANDAXE, Type.AXE, 1, 6, Energy.SLASHING, Hand.LIGHT, Trait.THROWN));
		weapons.put(JAVELIN, new Prototype(JAVELIN, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.ONE, Trait.THROWN));
		weapons.put(LIGHT_HAMMER, new Prototype(LIGHT_HAMMER, Type.MACE, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weapons.put(MACE, new Prototype(MACE, Type.MACE, 1, 6, Energy.BLUDGEONING, Hand.ONE));
		weapons.put(QUARTERSTAFF, new Prototype(QUARTERSTAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weapons.put(SICKLE, new Prototype(SICKLE, Type.SPECIAL, 1, 4, Energy.SLASHING, Hand.LIGHT));
		weapons.put(SPEAR, new Prototype(SPEAR, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN));
		weapons.put(LIGHT_CROSSBOW, new Prototype(LIGHT_CROSSBOW, Type.CROSSBOW, 1, 8, Energy.PIERCING, Hand.TWO,
				Trait.AMMUNITION, Trait.LOADING));
		weapons.put(DART,
				new Prototype(DART, Type.SPECIAL, 1, 4, Energy.PIERCING, Hand.ONE, Trait.FINESSE, Trait.THROWN));
		weapons.put(SHORTBOW, new Prototype(SHORTBOW, Type.BOW, 1, 6, Energy.PIERCING, Hand.TWO, Trait.AMMUNITION));
		weapons.put(SLING, new Prototype(SLING, Type.SPECIAL, 1, 4, Energy.BLUDGEONING, Hand.ONE, Trait.AMMUNITION));
		weapons.put(BATTLEAXE, new Prototype(BATTLEAXE, Type.AXE, 1, 8, Energy.SLASHING, Hand.VERSATILE));
		weapons.put(FLAIL, new Prototype(FLAIL, Type.SPECIAL, 1, 8, Energy.BLUDGEONING, Hand.ONE));
		weapons.put(GLAIVE, new Prototype(GLAIVE, Type.POLEARM, 1, 10, Energy.SLASHING, Hand.HEAVY, Trait.REACH));
		weapons.put(GREATAXE, new Prototype(GREATAXE, Type.AXE, 1, 12, Energy.SLASHING, Hand.HEAVY));
		weapons.put(GREATSWORD, new Prototype(GREATSWORD, Type.SWORD, 1, 6, Energy.SLASHING, Hand.HEAVY));
		weapons.put(HALBERD, new Prototype(HALBERD, Type.POLEARM, 1, 10, Energy.SLASHING, Hand.HEAVY, Trait.REACH));
		weapons.put(LANCE, new Prototype(LANCE, Type.SPECIAL, 1, 12, Energy.PIERCING, Hand.HEAVY, Trait.REACH));
		weapons.put(LONGSWORD, new Prototype(LONGSWORD, Type.SWORD, 1, 8, Energy.SLASHING, Hand.VERSATILE));
		weapons.put(MAUL, new Prototype(MAUL, Type.MACE, 2, 6, Energy.BLUDGEONING, Hand.HEAVY));
		weapons.put(MORNINGSTAR, new Prototype(MORNINGSTAR, Type.MACE, 1, 8, Energy.PIERCING, Hand.ONE));
		weapons.put(PIKE, new Prototype(PIKE, Type.POLEARM, 1, 10, Energy.PIERCING, Hand.HEAVY, Trait.REACH));
		weapons.put(RAPIER, new Prototype(RAPIER, Type.SWORD, 1, 8, Energy.PIERCING, Hand.ONE, Trait.FINESSE));
		weapons.put(SCIMITAR, new Prototype(SCIMITAR, Type.SWORD, 1, 6, Energy.SLASHING, Hand.LIGHT, Trait.FINESSE));
		weapons.put(SHORTSWORD,
				new Prototype(SHORTSWORD, Type.SWORD, 1, 6, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE));
		weapons.put(TRIDENT, new Prototype(TRIDENT, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN));
		weapons.put(WAR_PICK, new Prototype(WAR_PICK, Type.AXE, 1, 8, Energy.PIERCING, Hand.ONE));
		weapons.put(WARHAMMER, new Prototype(WARHAMMER, Type.MACE, 1, 8, Energy.BLUDGEONING, Hand.VERSATILE));
		weapons.put(WHIP,
				new Prototype(WHIP, Type.SPECIAL, 1, 4, Energy.SLASHING, Hand.ONE, Trait.FINESSE, Trait.REACH));
		weapons.put(BLOWGUN,
				new Prototype(BLOWGUN, Type.SPECIAL, 1, 1, Energy.PIERCING, Hand.ONE, Trait.AMMUNITION, Trait.LOADING));
		weapons.put(HAND_CROSSBOW, new Prototype(HAND_CROSSBOW, Type.CROSSBOW, 1, 6, Energy.PIERCING, Hand.LIGHT,
				Trait.AMMUNITION, Trait.LOADING));
		weapons.put(HEAVY_CROSSBOW, new Prototype(HEAVY_CROSSBOW, Type.CROSSBOW, 1, 10, Energy.PIERCING, Hand.HEAVY,
				Trait.AMMUNITION, Trait.LOADING));
		weapons.put(LONGBOW, new Prototype(LONGBOW, Type.BOW, 1, 8, Energy.PIERCING, Hand.HEAVY, Trait.AMMUNITION));
		weapons.put(NET, new Prototype(NET, Type.SPECIAL, 1, 4, Energy.BLUDGEONING, Hand.ONE, Trait.THROWN));
		weapons.put(SHIELD, new Prototype(SHIELD, Type.SHIELD, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weapons.put(ARROW, new Prototype(ARROW, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT, Trait.IMPROVISED));
		weapons.put(BOLT, new Prototype(BOLT, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT, Trait.IMPROVISED));
		weapons.put(BULLET,
				new Prototype(BULLET, Type.AMMUNITION, 1, 1, Energy.BLUDGEONING, Hand.LIGHT, Trait.IMPROVISED));
		weapons.put(NEEDLE,
				new Prototype(NEEDLE, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT, Trait.IMPROVISED));
		weapons.put(CRYSTAL,
				new Prototype(CRYSTAL, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(ORB, new Prototype(ORB, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(ROD, new Prototype(ROD, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(ARCANE_STAFF, new Prototype(ARCANE_STAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weapons.put(WAND, new Prototype(WAND, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(MISTLETOE,
				new Prototype(MISTLETOE, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(TOTEM,
				new Prototype(TOTEM, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(WOODEN_STAFF, new Prototype(WOODEN_STAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weapons.put(YEW_WAND,
				new Prototype(YEW_WAND, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(AMULET,
				new Prototype(AMULET, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(EMBLEM,
				new Prototype(EMBLEM, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weapons.put(RELIQUARY,
				new Prototype(RELIQUARY, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));

	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static void setupSimpleMelee(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = SIMPLE_MELEE;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupSimpleWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = SIMPLE_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupMageWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = MAGE_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupDruidWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = DRUID_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupRogueWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = ROGUE_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupMartialMelee(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = MARTIAL_MELEE;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupMartialWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = MARTIAL_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupAllWeapons(Actor actor) {
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null)
			set = EnumSet.noneOf(Weapon.class);
		else
			set = actor.getWeaponProficiency();

		Weapon[] array = ALL_WEAPONS;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setWeaponProficiency(set);
	}

	public static void setupWeaponProficiency(Actor actor) {
		Class job = actor.getJob();

		if (job.equals(Class.BARBARIAN)) {
			setupAllWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.BARD)) {
			setupRogueWeapons(actor);
		} else if (job.equals(Class.CLERIC)) {
			setupSimpleWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.DRUID)) {
			setupDruidWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.FIGHTER)) {
			setupAllWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.MONK)) {
			setupSimpleWeapons(actor);
			actor.getWeaponProficiency().add(SHORTSWORD);
		} else if (job.equals(Class.PALADIN)) {
			setupAllWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.RANGER)) {
			setupAllWeapons(actor);
			actor.getWeaponProficiency().add(SHIELD);
		} else if (job.equals(Class.ROGUE)) {
			setupRogueWeapons(actor);
		} else if (job.equals(Class.SORCERER)) {
			setupMageWeapons(actor);
		} else if (job.equals(Class.WARLOCK)) {
			setupSimpleWeapons(actor);
		} else if (job.equals(Class.WIZARD)) {
			setupMageWeapons(actor);
		}

		EnumSet<Armor> set;
		if (actor.getArmorProficiency() == null) {
			set = EnumSet.noneOf(Armor.class);
			actor.setArmorProficiency(set);
		}
	}

	// public static Weapon[] handsAscendingArray() {
	// Weapon[] array = Arrays.copyOf(ALL_WEAPONS, ALL_WEAPONS.length);
	// HandsCompareAscending sortMethod = new HandsCompareAscending();
	// Arrays.sort(array, sortMethod);
	//
	// return array;
	// }

	// public static Weapon[] handsDescendingArray() {
	// Weapon[] array = Arrays.copyOf(ALL_WEAPONS, ALL_WEAPONS.length);
	// HandsCompareDescending sortMethod = new HandsCompareDescending();
	// Arrays.sort(array, sortMethod);
	//
	// return array;
	// }

	/*
	 * COMPARATOR CLASSES
	 */
	private static class HandsCompareAscending implements Comparator<Prototype> {
		// TODO - needs testing
		@Override
		public int compare(Prototype w1, Prototype w2) {
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

	private static class HandsCompareDescending implements Comparator<Prototype> {
		// TODO - needs testing
		@Override
		public int compare(Prototype w1, Prototype w2) {
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
