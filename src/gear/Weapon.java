package gear;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.norvendae.rules.dnd5e.Energy;
import com.norvendae.rules.misc.Dice;

import actor.Actor;
import actor.Class;
import actor.Option;
import actor.Player;
import milieu.Names;

public enum Weapon {
	UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER, MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD, ARROW, BOLT, BULLET, NEEDLE, CRYSTAL, ORB, ROD, ARCANE_STAFF, WAND, MISTLETOE, TOTEM, WOODEN_STAFF, YEW_WAND, AMULET, EMBLEM, RELIQUARY;

	public static class Prototype {
		private Weapon weapon;
		private WeaponClass type;
		private int dice, faces;
		private Energy energy;
		private WeaponHands hand;
		private EnumSet<WeaponTrait> traits;

		//
		public Prototype(Weapon weapon, WeaponClass type, int face, Energy energy, WeaponHands hand, WeaponTrait... args) {
			this(weapon, type, 1, face, energy, hand, args);
		}

		public Prototype(Weapon weapon, WeaponClass type, int dice, int face, Energy energy, WeaponHands hand, WeaponTrait... args) {
			this.weapon = weapon;
			this.type = type;
			this.dice = dice;
			this.faces = face;
			this.energy = energy;
			this.hand = hand;

			this.traits = EnumSet.noneOf(WeaponTrait.class);
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
		private EnumSet<WeaponTrait> traits;
		private int dice, faces;

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
			this.name = Names.stringToName(weapon.toString());
			this.prototype = weaponPrototypeMap.get(weapon);

			this.traits = EnumSet.copyOf(prototype.traits);
			this.dice = prototype.dice;
			this.faces = prototype.faces;
			this.quantity = quantity;

			// TODO - take into account magic
			if (prototype.type.equals(WeaponClass.SHIELD))
				this.armorBonus = 2;
			else
				this.armorBonus = 0;

			// TODO - take into account magic
			this.attackBonus = 0;
		}

		// methods
		public String getName() {
			return name;
		}

		public Weapon getWeapon() {
			return prototype.weapon;
		}

		public WeaponClass getType() {
			return prototype.type;
		}

		public int getDice() {
			return dice;
		}

		public void setDice(int dice) {
			this.dice = dice;
		}

		public int getFaces() {
			return faces;
		}

		public void setFaces(int faces) {
			this.faces = faces;
		}

		public boolean isShield() {
			return prototype.weapon.equals(Weapon.SHIELD);
		}

		public boolean rangedOnly() {
			boolean rangedOnly = false;
			if (traits.contains(WeaponTrait.AMMUNITION))
				rangedOnly = true;

			return rangedOnly;
		}

		public boolean rangedOrThrown() {
			boolean rangedOrThrown = false;
			if (traits.contains(WeaponTrait.AMMUNITION) || traits.contains(WeaponTrait.THROWN))
				rangedOrThrown = true;

			return rangedOrThrown;
		}

		public boolean useDexterity() {
			boolean useDexterity = false;
			if (traits.contains(WeaponTrait.AMMUNITION) || traits.contains(WeaponTrait.FINESSE))
				useDexterity = true;

			return useDexterity;
		}

		public int getAverageDamage() {
			return ((faces + 1) * dice / 2);
		}

		public Energy getDamageType() {
			return prototype.energy;
		}

		public WeaponHands getHand() {
			return prototype.hand;
		}

		public boolean oneHandUseable() {
			return prototype.hand.equals(WeaponHands.LIGHT) || prototype.hand.equals(WeaponHands.ONE)
					|| prototype.hand.equals(WeaponHands.VERSATILE);
		}

		public boolean stackable() {
			return traits.contains(WeaponTrait.STACKABLE);
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
			return dice + "d" + faces;
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

	private static final Weapon[] ALL_WEAPONS = { UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER,
			MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, BATTLEAXE, FLAIL, GLAIVE,
			GREATAXE, GREATSWORD, HALBERD, LANCE, LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD,
			TRIDENT, WAR_PICK, WARHAMMER, WHIP, BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET, SHIELD };

	private static final Weapon[] SIMPLE_MELEE = { UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER,
			MACE, QUARTERSTAFF, SICKLE, SPEAR };
	private static final Weapon[] SIMPLE_RANGED = { LIGHT_CROSSBOW, DART, SHORTBOW, SLING };
	private static final Weapon[] SIMPLE_WEAPONS = { UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER,
			MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING };

	private static final Weapon[] MARTIAL_MELEE = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE,
			LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP };
	private static final Weapon[] MARTIAL_RANGED = { BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };
	private static final Weapon[] MARTIAL_WEAPONS = { BATTLEAXE, FLAIL, GLAIVE, GREATAXE, GREATSWORD, HALBERD, LANCE,
			LONGSWORD, MAUL, MORNINGSTAR, PIKE, RAPIER, SCIMITAR, SHORTSWORD, TRIDENT, WAR_PICK, WARHAMMER, WHIP,
			BLOWGUN, HAND_CROSSBOW, HEAVY_CROSSBOW, LONGBOW, NET };

	private static final Weapon[] DRUID_WEAPONS = { UNARMED, CLUB, DAGGER, DART, JAVELIN, MACE, QUARTERSTAFF, SCIMITAR,
			SICKLE, SLING, SPEAR };
	private static final Weapon[] ROGUE_WEAPONS = { UNARMED, CLUB, DAGGER, GREATCLUB, HANDAXE, JAVELIN, LIGHT_HAMMER,
			MACE, QUARTERSTAFF, SICKLE, SPEAR, LIGHT_CROSSBOW, DART, SHORTBOW, SLING, LONGSWORD, RAPIER, SHORTSWORD,
			HAND_CROSSBOW };
	private static final Weapon[] MAGE_WEAPONS = { UNARMED, DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW };

	static {
		weaponPrototypeMap = new HashMap<Weapon, Prototype>();
		weaponPrototypeMap.put(UNARMED, new Prototype(UNARMED, WeaponClass.UNARMED, 1, 1, Energy.BLUDGEONING, WeaponHands.LIGHT));
		weaponPrototypeMap.put(CLUB, new Prototype(CLUB, WeaponClass.CLUB, 1, 4, Energy.BLUDGEONING, WeaponHands.LIGHT));
		weaponPrototypeMap.put(DAGGER,
				new Prototype(DAGGER, WeaponClass.DAGGER, 1, 4, Energy.PIERCING, WeaponHands.LIGHT, WeaponTrait.FINESSE, WeaponTrait.THROWN));
		weaponPrototypeMap.put(GREATCLUB, new Prototype(GREATCLUB, WeaponClass.CLUB, 1, 8, Energy.BLUDGEONING, WeaponHands.TWO));
		weaponPrototypeMap.put(HANDAXE,
				new Prototype(HANDAXE, WeaponClass.AXE, 1, 6, Energy.SLASHING, WeaponHands.LIGHT, WeaponTrait.THROWN));
		weaponPrototypeMap.put(JAVELIN,
				new Prototype(JAVELIN, WeaponClass.SPEAR, 1, 6, Energy.PIERCING, WeaponHands.ONE, WeaponTrait.THROWN, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(LIGHT_HAMMER,
				new Prototype(LIGHT_HAMMER, WeaponClass.MACE, 1, 4, Energy.BLUDGEONING, WeaponHands.LIGHT));
		weaponPrototypeMap.put(MACE, new Prototype(MACE, WeaponClass.MACE, 1, 6, Energy.BLUDGEONING, WeaponHands.ONE));
		weaponPrototypeMap.put(QUARTERSTAFF,
				new Prototype(QUARTERSTAFF, WeaponClass.STAFF, 1, 6, Energy.BLUDGEONING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(SICKLE, new Prototype(SICKLE, WeaponClass.SPECIAL, 1, 4, Energy.SLASHING, WeaponHands.LIGHT));
		weaponPrototypeMap.put(SPEAR,
				new Prototype(SPEAR, WeaponClass.SPEAR, 1, 6, Energy.PIERCING, WeaponHands.VERSATILE, WeaponTrait.THROWN));
		weaponPrototypeMap.put(LIGHT_CROSSBOW, new Prototype(LIGHT_CROSSBOW, WeaponClass.CROSSBOW, 1, 8, Energy.PIERCING,
				WeaponHands.TWO, WeaponTrait.AMMUNITION, WeaponTrait.LOADING));
		weaponPrototypeMap.put(DART, new Prototype(DART, WeaponClass.SPECIAL, 1, 4, Energy.PIERCING, WeaponHands.ONE, WeaponTrait.FINESSE,
				WeaponTrait.THROWN, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(SHORTBOW,
				new Prototype(SHORTBOW, WeaponClass.BOW, 1, 6, Energy.PIERCING, WeaponHands.TWO, WeaponTrait.AMMUNITION));
		weaponPrototypeMap.put(SLING,
				new Prototype(SLING, WeaponClass.SPECIAL, 1, 4, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.AMMUNITION));
		weaponPrototypeMap.put(BATTLEAXE, new Prototype(BATTLEAXE, WeaponClass.AXE, 1, 8, Energy.SLASHING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(FLAIL, new Prototype(FLAIL, WeaponClass.SPECIAL, 1, 8, Energy.BLUDGEONING, WeaponHands.ONE));
		weaponPrototypeMap.put(GLAIVE,
				new Prototype(GLAIVE, WeaponClass.POLEARM, 1, 10, Energy.SLASHING, WeaponHands.HEAVY, WeaponTrait.REACH));
		weaponPrototypeMap.put(GREATAXE, new Prototype(GREATAXE, WeaponClass.AXE, 1, 12, Energy.SLASHING, WeaponHands.HEAVY));
		weaponPrototypeMap.put(GREATSWORD, new Prototype(GREATSWORD, WeaponClass.SWORD, 1, 6, Energy.SLASHING, WeaponHands.HEAVY));
		weaponPrototypeMap.put(HALBERD,
				new Prototype(HALBERD, WeaponClass.POLEARM, 1, 10, Energy.SLASHING, WeaponHands.HEAVY, WeaponTrait.REACH));
		weaponPrototypeMap.put(LANCE,
				new Prototype(LANCE, WeaponClass.SPECIAL, 1, 12, Energy.PIERCING, WeaponHands.HEAVY, WeaponTrait.REACH));
		weaponPrototypeMap.put(LONGSWORD, new Prototype(LONGSWORD, WeaponClass.SWORD, 1, 8, Energy.SLASHING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(MAUL, new Prototype(MAUL, WeaponClass.MACE, 2, 6, Energy.BLUDGEONING, WeaponHands.HEAVY));
		weaponPrototypeMap.put(MORNINGSTAR, new Prototype(MORNINGSTAR, WeaponClass.MACE, 1, 8, Energy.PIERCING, WeaponHands.ONE));
		weaponPrototypeMap.put(PIKE,
				new Prototype(PIKE, WeaponClass.POLEARM, 1, 10, Energy.PIERCING, WeaponHands.HEAVY, WeaponTrait.REACH));
		weaponPrototypeMap.put(RAPIER,
				new Prototype(RAPIER, WeaponClass.SWORD, 1, 8, Energy.PIERCING, WeaponHands.ONE, WeaponTrait.FINESSE));
		weaponPrototypeMap.put(SCIMITAR,
				new Prototype(SCIMITAR, WeaponClass.SWORD, 1, 6, Energy.SLASHING, WeaponHands.LIGHT, WeaponTrait.FINESSE));
		weaponPrototypeMap.put(SHORTSWORD,
				new Prototype(SHORTSWORD, WeaponClass.SWORD, 1, 6, Energy.PIERCING, WeaponHands.LIGHT, WeaponTrait.FINESSE));
		weaponPrototypeMap.put(TRIDENT,
				new Prototype(TRIDENT, WeaponClass.SPEAR, 1, 6, Energy.PIERCING, WeaponHands.VERSATILE, WeaponTrait.THROWN));
		weaponPrototypeMap.put(WAR_PICK, new Prototype(WAR_PICK, WeaponClass.AXE, 1, 8, Energy.PIERCING, WeaponHands.ONE));
		weaponPrototypeMap.put(WARHAMMER,
				new Prototype(WARHAMMER, WeaponClass.MACE, 1, 8, Energy.BLUDGEONING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(WHIP,
				new Prototype(WHIP, WeaponClass.SPECIAL, 1, 4, Energy.SLASHING, WeaponHands.ONE, WeaponTrait.FINESSE, WeaponTrait.REACH));
		weaponPrototypeMap.put(BLOWGUN,
				new Prototype(BLOWGUN, WeaponClass.SPECIAL, 1, 1, Energy.PIERCING, WeaponHands.ONE, WeaponTrait.AMMUNITION, WeaponTrait.LOADING));
		weaponPrototypeMap.put(HAND_CROSSBOW, new Prototype(HAND_CROSSBOW, WeaponClass.CROSSBOW, 1, 6, Energy.PIERCING,
				WeaponHands.LIGHT, WeaponTrait.AMMUNITION, WeaponTrait.LOADING));
		weaponPrototypeMap.put(HEAVY_CROSSBOW, new Prototype(HEAVY_CROSSBOW, WeaponClass.CROSSBOW, 1, 10, Energy.PIERCING,
				WeaponHands.HEAVY, WeaponTrait.AMMUNITION, WeaponTrait.LOADING));
		weaponPrototypeMap.put(LONGBOW,
				new Prototype(LONGBOW, WeaponClass.BOW, 1, 8, Energy.PIERCING, WeaponHands.HEAVY, WeaponTrait.AMMUNITION));
		weaponPrototypeMap.put(NET, new Prototype(NET, WeaponClass.SPECIAL, 1, 4, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.THROWN));
		weaponPrototypeMap.put(SHIELD, new Prototype(SHIELD, WeaponClass.SHIELD, 1, 4, Energy.BLUDGEONING, WeaponHands.LIGHT));
		weaponPrototypeMap.put(ARROW, new Prototype(ARROW, WeaponClass.AMMUNITION, 1, 1, Energy.PIERCING, WeaponHands.LIGHT,
				WeaponTrait.IMPROVISED, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(BOLT, new Prototype(BOLT, WeaponClass.AMMUNITION, 1, 1, Energy.PIERCING, WeaponHands.LIGHT,
				WeaponTrait.IMPROVISED, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(BULLET, new Prototype(BULLET, WeaponClass.AMMUNITION, 1, 1, Energy.BLUDGEONING, WeaponHands.LIGHT,
				WeaponTrait.IMPROVISED, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(NEEDLE, new Prototype(NEEDLE, WeaponClass.AMMUNITION, 1, 1, Energy.PIERCING, WeaponHands.LIGHT,
				WeaponTrait.IMPROVISED, WeaponTrait.STACKABLE));
		weaponPrototypeMap.put(CRYSTAL,
				new Prototype(CRYSTAL, WeaponClass.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(ORB,
				new Prototype(ORB, WeaponClass.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(ROD,
				new Prototype(ROD, WeaponClass.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(ARCANE_STAFF,
				new Prototype(ARCANE_STAFF, WeaponClass.STAFF, 1, 6, Energy.BLUDGEONING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(WAND,
				new Prototype(WAND, WeaponClass.ARCANE_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(MISTLETOE,
				new Prototype(MISTLETOE, WeaponClass.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(TOTEM,
				new Prototype(TOTEM, WeaponClass.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(WOODEN_STAFF,
				new Prototype(WOODEN_STAFF, WeaponClass.STAFF, 1, 6, Energy.BLUDGEONING, WeaponHands.VERSATILE));
		weaponPrototypeMap.put(YEW_WAND,
				new Prototype(YEW_WAND, WeaponClass.DRUID_FOCUS, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(AMULET,
				new Prototype(AMULET, WeaponClass.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(EMBLEM,
				new Prototype(EMBLEM, WeaponClass.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));
		weaponPrototypeMap.put(RELIQUARY,
				new Prototype(RELIQUARY, WeaponClass.HOLY_SYMBOL, 1, 1, Energy.BLUDGEONING, WeaponHands.ONE, WeaponTrait.IMPROVISED));

	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Instance unarmed(Player actor) {
		Instance weapon;
		if (actor.getJob().equals(Class.MONK))
			weapon = monkUnarmedStrike(actor);
		else
			weapon = new Instance(UNARMED);

		return weapon;
	}

	public static Instance monkUnarmedStrike(Player actor) {
		Instance weapon = new Instance(UNARMED);
		weapon.traits.add(WeaponTrait.FINESSE);

		EnumSet<Option.Feature> features = actor.getFeatures();
		if (features.contains(Option.Feature.MARTIAL_ARTS_D10))
			weapon.setFaces(10);
		else if (features.contains(Option.Feature.MARTIAL_ARTS_D8))
			weapon.setFaces(8);
		else if (features.contains(Option.Feature.MARTIAL_ARTS_D6))
			weapon.setFaces(6);
		else
			weapon.setFaces(4);

		return weapon;
	}

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

	private static void setupSimpleWeapons(Player actor) {
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

	private static void setupAllWeapons(Player actor) {
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

	private static void setupMageWeapons(Player actor) {
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

	private static void setupDruidWeapons(Player actor) {
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

	private static void setupRogueWeapons(Player actor) {
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

	public static void setupWeaponProficiency(Player actor) {

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
	 * INNER CLASSES
	 * 
	 */
	public static class WeaponSet implements Item.ItemSet<Instance> {
		private ArrayList<Instance> list;
		private Actor owner;

		// CONSTRUCTORS
		public WeaponSet(Actor owner) {
			this(owner, new ArrayList<Instance>());
		}

		public WeaponSet(Actor owner, Collection<Instance> c) {
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
		public boolean addAll(Collection<? extends Instance> c) {
			boolean addedAll = true;

			for (Iterator<? extends Instance> it = c.iterator(); it.hasNext();) {
				if (list.add(it.next()) != true)
					addedAll = false;
			}

			return addedAll;
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
		public boolean isEmpty() {
			return list.isEmpty();
		}

		@Override
		public Iterator<Instance> iterator() {
			return list.iterator();
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
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
		public int size() {
			return list.size();
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
	public static class SortByTrait implements Comparator<Instance> {
		private WeaponTrait trait;

		public SortByTrait(WeaponTrait trait) {
			this.trait = trait;
		}

		@Override
		public int compare(Instance left, Instance right) {
			int one = (left.traits.contains(trait)) ? 1 : 0;
			int two = (right.traits.contains(trait)) ? 1 : 0;

			return two - one;
		}
	}

	public static class SortByUseability implements Comparator<Weapon.Instance> {
		EnumSet<Weapon> weaponProf;

		public SortByUseability(Player actor) {
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
			if (one.getHand().equals(WeaponHands.VERSATILE))
				left += 5;
			else if (one.getHand().equals(WeaponHands.ONE))
				left += 2;
			else if (one.getHand().equals(WeaponHands.LIGHT))
				left += 1;

			if (two.getHand().equals(WeaponHands.VERSATILE))
				right += 5;
			else if (two.getHand().equals(WeaponHands.ONE))
				right += 2;
			else if (two.getHand().equals(WeaponHands.LIGHT))
				right += 1;

			left += (one.isShield()) ? 10 : 0;
			right += (two.isShield()) ? 10 : 0;

			return right - left;
		}

	}

}
