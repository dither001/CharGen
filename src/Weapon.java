
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public enum Weapon {
	UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD, ARROW, BOLT, BULLET, NEEDLE, CRYSTAL, ORB, ROD, ARCANE_STAFF, WAND, MISTLETOE, TOTEM, WOODEN_STAFF, YEW_WAND, AMULET, EMBLEM, RELIQUARY;

	public enum Type {
		UNARMED, CLUB, DAGGER, AXE, SPEAR, MACE, STAFF, SPECIAL, CROSSBOW, BOW, POLEARM, SWORD, SHIELD, AMMUNITION, ARCANE_FOCUS, DRUID_FOCUS, HOLY_SYMBOL
	};

	public enum Trait {
		FINESSE, REACH, THROWN, AMMUNITION, LOADING, IMPROVISED, STACKABLE
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

		//
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

		public boolean equals(Prototype other) {
			boolean equals = false;
			if (other.weapon.equals(this.weapon))
				equals = true;

			return equals;
		}
	}

	public static class Instance implements Item {
		private String name;
		private Prototype prototype;
		private int quantity;

		//
		private int attackBonus;
		private int armorBonus;

		/*
		 * 
		 */
		public Instance(Weapon weapon) {
			this(weapon, 1);
		}

		public Instance(Weapon weapon, int quantity) {
			// TODO
			this.name = "Default";

			this.prototype = weaponPrototypeMap.get(weapon);
			this.quantity = quantity;

			// TODO - take into account magic
			if (prototype.type.equals(Type.SHIELD))
				this.armorBonus = 2;
			else
				this.armorBonus = 0;

			// TODO - take into account magic
			attackBonus = 0;
		}

		// methods
		public String getName() {
			return name;
		}

		public Weapon getWeapon() {
			return prototype.weapon;
		}

		public Type getType() {
			return prototype.type;
		}

		public boolean isShield() {
			return prototype.weapon.equals(Weapon.SHIELD);
		}

		public boolean rangedOnly() {
			boolean rangedOnly = false;
			if (prototype.traits.contains(Trait.AMMUNITION))
				rangedOnly = true;

			return rangedOnly;
		}

		public boolean rangedOrThrown() {
			boolean rangedOrThrown = false;
			if (prototype.traits.contains(Trait.AMMUNITION) || prototype.traits.contains(Trait.THROWN))
				rangedOrThrown = true;

			return rangedOrThrown;
		}

		public boolean useDexterity() {
			boolean useDexterity = false;
			if (prototype.traits.contains(Trait.AMMUNITION) || prototype.traits.contains(Trait.FINESSE))
				useDexterity = true;

			return useDexterity;
		}

		public int getAverageDamage() {
			return prototype.faces / 2 * prototype.dice;
		}

		public Energy getDamageType() {
			return prototype.energy;
		}

		public Hand getHand() {
			return prototype.hand;
		}

		public boolean oneHandUseable() {
			return prototype.hand.equals(Hand.LIGHT) || prototype.hand.equals(Hand.ONE)
					|| prototype.hand.equals(Hand.VERSATILE);
		}

		public boolean stackable() {
			return prototype.traits.contains(Trait.STACKABLE);
		}

		public int getQuantity() {
			return quantity;
		}

		public void addToStack(int toAdd) {
			quantity += toAdd;
		}

		public void removeFromStack() {
			// TODO - needs more work
			--quantity;
		}

		public boolean stackEmpty() {
			return (quantity < 1);
		}

		public boolean sameAsWeapon(Weapon weapon) {
			boolean equals = false;
			if (weapon.equals(this.prototype.weapon))
				equals = true;

			return equals;
		}

		public boolean equals(Instance other) {
			boolean equals = false;
			if (other.prototype.weapon.equals(this.prototype.weapon))
				equals = true;

			return equals;
		}

		public String getDiceString() {
			return prototype.dice + "d" + prototype.faces;
		}

		@Override
		public String toString() {
			String string, thing = prototype.weapon.toString();

			if (quantity > 1)
				string = String.format("%s (%d)", thing, quantity);
			else
				string = thing;

			return string;
		}

		@Override
		public boolean isCursed() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getArmorBonus() {
			return armorBonus;
		}

		@Override
		public int getAttackBonus() {
			return attackBonus;
		}
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static HashMap<Weapon, Prototype> weaponPrototypeMap;

	public static final Weapon DEFAULT_WEAPON = UNARMED;

	private static final Weapon[] ALL_WEAPONS = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE,
			GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT,
			WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD };

	private static final Weapon[] SIMPLE_MELEE = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR };
	private static final Weapon[] SIMPLE_RANGED = { LIGHT_CROSSBOW, DART, SHORTBOW, SLING };
	private static final Weapon[] SIMPLE_WEAPONS = { CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE,
			QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING };

	private static final Weapon[] MARTIAL_MELEE = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE,
			LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP };
	private static final Weapon[] MARTIAL_RANGED = { BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };
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
		weaponPrototypeMap = new HashMap<Weapon, Prototype>();
		weaponPrototypeMap.put(UNARMED, new Prototype(UNARMED, Type.UNARMED, 1, 1, Energy.BLUDGEONING, Hand.LIGHT));
		weaponPrototypeMap.put(CLUB, new Prototype(CLUB, Type.CLUB, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weaponPrototypeMap.put(DAGGER,
				new Prototype(DAGGER, Type.DAGGER, 1, 4, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE, Trait.THROWN));
		weaponPrototypeMap.put(GREATCLUB, new Prototype(GREATCLUB, Type.CLUB, 1, 8, Energy.BLUDGEONING, Hand.TWO));
		weaponPrototypeMap.put(HANDAXE,
				new Prototype(HANDAXE, Type.AXE, 1, 6, Energy.SLASHING, Hand.LIGHT, Trait.THROWN));
		weaponPrototypeMap.put(JAVELIN,
				new Prototype(JAVELIN, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.ONE, Trait.THROWN, Trait.STACKABLE));
		weaponPrototypeMap.put(LIGHT_HAMMER,
				new Prototype(LIGHT_HAMMER, Type.MACE, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weaponPrototypeMap.put(MACE, new Prototype(MACE, Type.MACE, 1, 6, Energy.BLUDGEONING, Hand.ONE));
		weaponPrototypeMap.put(QUARTERSTAFF,
				new Prototype(QUARTERSTAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weaponPrototypeMap.put(SICKLE, new Prototype(SICKLE, Type.SPECIAL, 1, 4, Energy.SLASHING, Hand.LIGHT));
		weaponPrototypeMap.put(SPEAR,
				new Prototype(SPEAR, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN));
		weaponPrototypeMap.put(LIGHT_CROSSBOW, new Prototype(LIGHT_CROSSBOW, Type.CROSSBOW, 1, 8, Energy.PIERCING,
				Hand.TWO, Trait.AMMUNITION, Trait.LOADING));
		weaponPrototypeMap.put(DART, new Prototype(DART, Type.SPECIAL, 1, 4, Energy.PIERCING, Hand.ONE, Trait.FINESSE,
				Trait.THROWN, Trait.STACKABLE));
		weaponPrototypeMap.put(SHORTBOW,
				new Prototype(SHORTBOW, Type.BOW, 1, 6, Energy.PIERCING, Hand.TWO, Trait.AMMUNITION));
		weaponPrototypeMap.put(SLING,
				new Prototype(SLING, Type.SPECIAL, 1, 4, Energy.BLUDGEONING, Hand.ONE, Trait.AMMUNITION));
		weaponPrototypeMap.put(BATTLEAXE, new Prototype(BATTLEAXE, Type.AXE, 1, 8, Energy.SLASHING, Hand.VERSATILE));
		weaponPrototypeMap.put(FLAIL, new Prototype(FLAIL, Type.SPECIAL, 1, 8, Energy.BLUDGEONING, Hand.ONE));
		weaponPrototypeMap.put(GLAIVE,
				new Prototype(GLAIVE, Type.POLEARM, 1, 10, Energy.SLASHING, Hand.HEAVY, Trait.REACH));
		weaponPrototypeMap.put(GREATAXE, new Prototype(GREATAXE, Type.AXE, 1, 12, Energy.SLASHING, Hand.HEAVY));
		weaponPrototypeMap.put(GREATSWORD, new Prototype(GREATSWORD, Type.SWORD, 1, 6, Energy.SLASHING, Hand.HEAVY));
		weaponPrototypeMap.put(HALBERD,
				new Prototype(HALBERD, Type.POLEARM, 1, 10, Energy.SLASHING, Hand.HEAVY, Trait.REACH));
		weaponPrototypeMap.put(LANCE,
				new Prototype(LANCE, Type.SPECIAL, 1, 12, Energy.PIERCING, Hand.HEAVY, Trait.REACH));
		weaponPrototypeMap.put(LONGSWORD, new Prototype(LONGSWORD, Type.SWORD, 1, 8, Energy.SLASHING, Hand.VERSATILE));
		weaponPrototypeMap.put(MAUL, new Prototype(MAUL, Type.MACE, 2, 6, Energy.BLUDGEONING, Hand.HEAVY));
		weaponPrototypeMap.put(MORNINGSTAR, new Prototype(MORNINGSTAR, Type.MACE, 1, 8, Energy.PIERCING, Hand.ONE));
		weaponPrototypeMap.put(PIKE,
				new Prototype(PIKE, Type.POLEARM, 1, 10, Energy.PIERCING, Hand.HEAVY, Trait.REACH));
		weaponPrototypeMap.put(RAPIER,
				new Prototype(RAPIER, Type.SWORD, 1, 8, Energy.PIERCING, Hand.ONE, Trait.FINESSE));
		weaponPrototypeMap.put(SCIMITAR,
				new Prototype(SCIMITAR, Type.SWORD, 1, 6, Energy.SLASHING, Hand.LIGHT, Trait.FINESSE));
		weaponPrototypeMap.put(SHORTSWORD,
				new Prototype(SHORTSWORD, Type.SWORD, 1, 6, Energy.PIERCING, Hand.LIGHT, Trait.FINESSE));
		weaponPrototypeMap.put(TRIDENT,
				new Prototype(TRIDENT, Type.SPEAR, 1, 6, Energy.PIERCING, Hand.VERSATILE, Trait.THROWN));
		weaponPrototypeMap.put(WAR_PICK, new Prototype(WAR_PICK, Type.AXE, 1, 8, Energy.PIERCING, Hand.ONE));
		weaponPrototypeMap.put(WARHAMMER,
				new Prototype(WARHAMMER, Type.MACE, 1, 8, Energy.BLUDGEONING, Hand.VERSATILE));
		weaponPrototypeMap.put(WHIP,
				new Prototype(WHIP, Type.SPECIAL, 1, 4, Energy.SLASHING, Hand.ONE, Trait.FINESSE, Trait.REACH));
		weaponPrototypeMap.put(BLOWGUN,
				new Prototype(BLOWGUN, Type.SPECIAL, 1, 1, Energy.PIERCING, Hand.ONE, Trait.AMMUNITION, Trait.LOADING));
		weaponPrototypeMap.put(HAND_CROSSBOW, new Prototype(HAND_CROSSBOW, Type.CROSSBOW, 1, 6, Energy.PIERCING,
				Hand.LIGHT, Trait.AMMUNITION, Trait.LOADING));
		weaponPrototypeMap.put(HEAVY_CROSSBOW, new Prototype(HEAVY_CROSSBOW, Type.CROSSBOW, 1, 10, Energy.PIERCING,
				Hand.HEAVY, Trait.AMMUNITION, Trait.LOADING));
		weaponPrototypeMap.put(LONGBOW,
				new Prototype(LONGBOW, Type.BOW, 1, 8, Energy.PIERCING, Hand.HEAVY, Trait.AMMUNITION));
		weaponPrototypeMap.put(NET, new Prototype(NET, Type.SPECIAL, 1, 4, Energy.BLUDGEONING, Hand.ONE, Trait.THROWN));
		weaponPrototypeMap.put(SHIELD, new Prototype(SHIELD, Type.SHIELD, 1, 4, Energy.BLUDGEONING, Hand.LIGHT));
		weaponPrototypeMap.put(ARROW, new Prototype(ARROW, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT,
				Trait.IMPROVISED, Trait.STACKABLE));
		weaponPrototypeMap.put(BOLT, new Prototype(BOLT, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT,
				Trait.IMPROVISED, Trait.STACKABLE));
		weaponPrototypeMap.put(BULLET, new Prototype(BULLET, Type.AMMUNITION, 1, 1, Energy.BLUDGEONING, Hand.LIGHT,
				Trait.IMPROVISED, Trait.STACKABLE));
		weaponPrototypeMap.put(NEEDLE, new Prototype(NEEDLE, Type.AMMUNITION, 1, 1, Energy.PIERCING, Hand.LIGHT,
				Trait.IMPROVISED, Trait.STACKABLE));
		weaponPrototypeMap.put(CRYSTAL,
				new Prototype(CRYSTAL, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(ORB,
				new Prototype(ORB, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(ROD,
				new Prototype(ROD, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(ARCANE_STAFF,
				new Prototype(ARCANE_STAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weaponPrototypeMap.put(WAND,
				new Prototype(WAND, Type.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(MISTLETOE,
				new Prototype(MISTLETOE, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(TOTEM,
				new Prototype(TOTEM, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(WOODEN_STAFF,
				new Prototype(WOODEN_STAFF, Type.STAFF, 1, 6, Energy.BLUDGEONING, Hand.VERSATILE));
		weaponPrototypeMap.put(YEW_WAND,
				new Prototype(YEW_WAND, Type.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(AMULET,
				new Prototype(AMULET, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(EMBLEM,
				new Prototype(EMBLEM, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));
		weaponPrototypeMap.put(RELIQUARY,
				new Prototype(RELIQUARY, Type.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, Hand.ONE, Trait.IMPROVISED));

	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static Weapon randomSimpleMelee() {
		return Dice.randomFromArray(SIMPLE_MELEE);
	}

	public static Weapon randomSimpleRanged() {
		return Dice.randomFromArray(SIMPLE_RANGED);
	}

	public static Weapon randomSimpleWeapon() {
		return Dice.randomFromArray(SIMPLE_WEAPONS);
	}

	public static Weapon randomMartialMelee() {
		return Dice.randomFromArray(MARTIAL_MELEE);
	}

	public static Weapon randomMartialRanged() {
		return Dice.randomFromArray(MARTIAL_RANGED);
	}

	public static Weapon randomMartialWeapon() {
		return Dice.randomFromArray(MARTIAL_WEAPONS);
	}

	public static List<Weapon> getMartialWeaponList() {
		List<Weapon> list = new ArrayList<Weapon>();
		Weapon[] array = MARTIAL_WEAPONS;
		for (Weapon el : array) {
			list.add(el);
		}

		return list;
	}

	public static Instance getAmmunition(Weapon weapon) {
		Instance ammunition = null;

		if (weapon.equals(SLING))
			ammunition = new Instance(BULLET, 20);
		else if (weapon.equals(LONGBOW) || weapon.equals(SHORTBOW))
			ammunition = new Instance(ARROW, 20);
		else if (weapon.equals(HAND_CROSSBOW) || weapon.equals(LIGHT_CROSSBOW) || weapon.equals(HEAVY_CROSSBOW))
			ammunition = new Instance(BOLT, 20);
		else if (weapon.equals(BLOWGUN))
			ammunition = new Instance(NEEDLE, 50);
		else if (weapon.equals(DART))
			ammunition = new Instance(DART, 9);

		return ammunition;
	}

	private static void setupSimpleWeapons(Actor actor) {
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

	private static void setupAllWeapons(Actor actor) {
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

	private static void setupMageWeapons(Actor actor) {
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

	private static void setupDruidWeapons(Actor actor) {
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

	private static void setupRogueWeapons(Actor actor) {
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

	public static void setupWeaponProficiency(Actor actor) {

		// basic stuff
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

		// provided in case character has no class
		EnumSet<Weapon> set;
		if (actor.getWeaponProficiency() == null) {
			set = EnumSet.noneOf(Weapon.class);
			actor.setWeaponProficiency(set);
		}
	}

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	// private static class HandsAscending implements Comparator<Prototype> {
	// // TODO - needs testing
	// @Override
	// public int compare(Prototype w1, Prototype w2) {
	// int left = 0, right = 0;
	//
	// if (w1.hand.equals(Hand.LIGHT))
	// left = 1;
	// else if (w1.hand.equals(Hand.ONE))
	// left = 2;
	// else if (w1.hand.equals(Hand.VERSATILE))
	// left = 3;
	// else if (w1.hand.equals(Hand.TWO))
	// left = 4;
	// else if (w1.hand.equals(Hand.HEAVY))
	// left = 5;
	//
	// if (w2.hand.equals(Hand.LIGHT))
	// right = 1;
	// else if (w2.hand.equals(Hand.ONE))
	// right = 2;
	// else if (w2.hand.equals(Hand.VERSATILE))
	// right = 3;
	// else if (w2.hand.equals(Hand.TWO))
	// right = 4;
	// else if (w2.hand.equals(Hand.HEAVY))
	// right = 5;
	//
	// return left - right;
	// }
	// }
	//
	// private static class HandsDescending implements Comparator<Prototype> {
	// // TODO - needs testing
	// @Override
	// public int compare(Prototype w1, Prototype w2) {
	// int left = 0, right = 0;
	//
	// if (w1.hand.equals(Hand.LIGHT))
	// left = 1;
	// else if (w1.hand.equals(Hand.ONE))
	// left = 2;
	// else if (w1.hand.equals(Hand.VERSATILE))
	// left = 3;
	// else if (w1.hand.equals(Hand.TWO))
	// left = 4;
	// else if (w1.hand.equals(Hand.HEAVY))
	// left = 5;
	//
	// if (w2.hand.equals(Hand.LIGHT))
	// right = 1;
	// else if (w2.hand.equals(Hand.ONE))
	// right = 2;
	// else if (w2.hand.equals(Hand.VERSATILE))
	// right = 3;
	// else if (w2.hand.equals(Hand.TWO))
	// right = 4;
	// else if (w2.hand.equals(Hand.HEAVY))
	// right = 5;
	//
	// return right - left;
	// }
	// }

	public static class WeaponList implements Item.ItemList<Instance> {
		private ArrayList<Instance> list;
		private Actor owner;

		// CONSTRUCTORS
		public WeaponList(Actor owner) {
			this(owner, new ArrayList<Instance>());
		}

		public WeaponList(Actor owner, Collection<Instance> c) {
			this.owner = owner;
			this.list = new ArrayList<Instance>();
			addAll(c);
		}

		// ORIGINAL METHODS
		public Collection<Instance> list() {
			return (Collection<Instance>) list;
		}

		// LIST METHODS
		@Override
		public boolean add(Instance e) {
			boolean added = false;

			if (e != null && e.stackable()) {
				Instance instance;
				for (Iterator<Instance> it = this.iterator(); it.hasNext();) {
					instance = it.next();
					if (instance.stackable() && instance.equals(e)) {
						// System.out.println("Stacked");
						instance.addToStack(e.quantity);
						added = true;
					}
				}

				if (added != true) {
					// System.out.println("Start of stack");
					list.add(e);
				}

			} else if (e != null) {
				// if (e.stackable())
				// System.out.println("Added stackable");
				list.add(e);
			}

			return added;
		}

		@Override
		public void add(int index, Instance element) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean addAll(Collection<? extends Instance> c) {
			boolean addedAll = true;

			for (Iterator<? extends Instance> it = c.iterator(); it.hasNext();) {
				if (list.add(it.next()) != true)
					addedAll = false;
			}

			return addedAll;
		}

		@Override
		public boolean addAll(int index, Collection<? extends Instance> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			list.clear();
		}

		@Override
		public boolean contains(Object o) {
			return list.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			return list.containsAll(c);
		}

		@Override
		public Instance get(int index) {
			return list.get(index);
		}

		@Override
		public int indexOf(Object o) {
			return list.indexOf(o);
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

		@Override
		public Iterator<Instance> iterator() {
			return list.iterator();
		}

		@Override
		public int lastIndexOf(Object o) {
			return list.lastIndexOf(o);
		}

		@Override
		public ListIterator<Instance> listIterator() {
			return list.listIterator();
		}

		@Override
		public ListIterator<Instance> listIterator(int index) {
			return list.listIterator(index);
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Instance remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Instance set(int index, Instance element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			return list.size();
		}

		@Override
		public List<Instance> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			String string = "[";
			Instance instance;
			for (Iterator<Instance> it = this.iterator(); it.hasNext();) {
				instance = it.next();
				if (it.hasNext())
					string += instance.toString() + ", ";
				else
					string += instance.toString();
			}

			// System.out.println(list.size());
			return string + "]";
		}
	}

	/*
	 * COMPARATOR
	 * 
	 */
	public static class SortByWeaponTrait implements Comparator<Instance> {
		private Trait trait;

		public SortByWeaponTrait(Trait trait) {
			this.trait = trait;
		}

		@Override
		public int compare(Instance left, Instance right) {
			int one = (left.prototype.traits.contains(trait)) ? 1 : 0;
			int two = (right.prototype.traits.contains(trait)) ? 1 : 0;

			return two - one;
		}
	}

	public static class SortWeaponByUseability implements Comparator<Weapon.Instance> {
		EnumSet<Weapon> weaponProf;

		public SortWeaponByUseability(Actor actor) {
			this.weaponProf = actor.getWeaponProficiency();
		}

		@Override
		public int compare(Weapon.Instance w1, Weapon.Instance w2) {
			int left = (weaponProf.contains(w1.getWeapon())) ? 1 : 0;
			int right = (weaponProf.contains(w2.getWeapon())) ? 1 : 0;

			return right - left;
		}

	}

	public static class SortByDefender implements Comparator<Instance> {

		@Override
		public int compare(Instance one, Instance two) {
			int left = 0, right = 0;
			if (one.getHand().equals(Hand.VERSATILE))
				left += 5;
			else if (one.getHand().equals(Hand.ONE))
				left += 2;
			else if (one.getHand().equals(Hand.LIGHT))
				left += 1;

			if (two.getHand().equals(Hand.VERSATILE))
				right += 5;
			else if (two.getHand().equals(Hand.ONE))
				right += 2;
			else if (two.getHand().equals(Hand.LIGHT))
				right += 1;

			left += (one.isShield()) ? 10 : 0;
			right += (two.isShield()) ? 10 : 0;

			return right - left;
		}

	}

}
