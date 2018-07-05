import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public enum Armor {
	UNARMORED, BARBARIAN, MAGE, MONK, PADDED_ARMOR, LEATHER_ARMOR, STUDDED_LEATHER, HIDE_ARMOR, CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE, HALF_PLATE, RING_MAIL, CHAIN_MAIL, SPLINT_MAIL, PLATE_MAIL;

	public static class Prototype {
		private Armor armor;
		private int armorClass, maxDexterity;

		public Prototype(Armor armor, int armorClass, int maxDexterity) {
			this.armor = armor;
			this.armorClass = armorClass;
			this.maxDexterity = maxDexterity;
		}

		public boolean equals(Prototype other) {
			boolean equals = false;
			if (other.armor.equals(this.armor))
				equals = true;

			return equals;
		}
	}

	public static class Instance {
		private String name;
		private Prototype prototype;

		public Instance(Armor armor) {
			this.prototype = armorPrototypeMap.get(armor);
		}

		public String getName() {
			return name;
		}

		public Armor getArmor() {
			return prototype.armor;
		}

		public int getArmorClass() {
			return prototype.armorClass;
		}

		public int getMaxDexterity() {
			return prototype.maxDexterity;
		}

		@Override
		public String toString() {
			return prototype.armor.toString();
		}
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	public static HashMap<Armor, Prototype> armorPrototypeMap;

	private static final Armor[] LIGHT_ARMOR = { PADDED_ARMOR, LEATHER_ARMOR, STUDDED_LEATHER };
	private static final Armor[] MEDIUM_ARMOR = { HIDE_ARMOR, CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE, HALF_PLATE };
	private static final Armor[] HEAVY_ARMOR = { RING_MAIL, CHAIN_MAIL, SPLINT_MAIL, PLATE_MAIL };
	private static final Armor[] LIGHT_MEDIUM = { PADDED_ARMOR, LEATHER_ARMOR, STUDDED_LEATHER, HIDE_ARMOR, CHAIN_SHIRT,
			SCALE_MAIL, BREASTPLATE, HALF_PLATE };
	private static final Armor[] LIGHT_MEDIUM_HEAVY = { PADDED_ARMOR, LEATHER_ARMOR, STUDDED_LEATHER, HIDE_ARMOR,
			CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE, HALF_PLATE, RING_MAIL, CHAIN_MAIL, SPLINT_MAIL, PLATE_MAIL };

	static {
		armorPrototypeMap = new HashMap<Armor, Prototype>();
		armorPrototypeMap.put(PADDED_ARMOR, new Prototype(PADDED_ARMOR, 11, 10));
		armorPrototypeMap.put(LEATHER_ARMOR, new Prototype(LEATHER_ARMOR, 11, 10));
		armorPrototypeMap.put(STUDDED_LEATHER, new Prototype(STUDDED_LEATHER, 12, 10));
		armorPrototypeMap.put(HIDE_ARMOR, new Prototype(HIDE_ARMOR, 12, 2));
		armorPrototypeMap.put(CHAIN_SHIRT, new Prototype(CHAIN_SHIRT, 13, 2));
		armorPrototypeMap.put(SCALE_MAIL, new Prototype(SCALE_MAIL, 14, 2));
		armorPrototypeMap.put(BREASTPLATE, new Prototype(BREASTPLATE, 14, 2));
		armorPrototypeMap.put(HALF_PLATE, new Prototype(HALF_PLATE, 15, 2));
		armorPrototypeMap.put(RING_MAIL, new Prototype(RING_MAIL, 14, 0));
		armorPrototypeMap.put(CHAIN_MAIL, new Prototype(CHAIN_MAIL, 16, 0));
		armorPrototypeMap.put(SPLINT_MAIL, new Prototype(SPLINT_MAIL, 17, 0));
		armorPrototypeMap.put(PLATE_MAIL, new Prototype(PLATE_MAIL, 18, 0));
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static List<Armor> getLightArmorList() {
		List<Armor> list = new ArrayList<Armor>();
		Armor[] array = LIGHT_ARMOR;
		for (Armor el : array) {
			list.add(el);
		}

		return list;
	}

	public static List<Armor> getMediumArmorList() {
		List<Armor> list = new ArrayList<Armor>();
		Armor[] array = MEDIUM_ARMOR;
		for (Armor el : array) {
			list.add(el);
		}

		return list;
	}

	public static List<Armor> getHeavyArmorList() {
		List<Armor> list = new ArrayList<Armor>();
		Armor[] array = HEAVY_ARMOR;
		for (Armor el : array) {
			list.add(el);
		}

		return list;
	}

	public static int getArmorClass(Armor armor) {
		int armorClass = 10;

		if (armor.equals(UNARMORED))
			armorClass = 10;
		else if (armor.equals(BARBARIAN))
			armorClass = 10;
		else if (armor.equals(MAGE))
			armorClass = 13;
		else if (armor.equals(MONK))
			armorClass = 10;
		else if (armor.equals(PADDED_ARMOR))
			armorClass = 11;
		else if (armor.equals(LEATHER_ARMOR))
			armorClass = 11;
		else if (armor.equals(STUDDED_LEATHER))
			armorClass = 12;
		else if (armor.equals(HIDE_ARMOR))
			armorClass = 12;
		else if (armor.equals(CHAIN_SHIRT))
			armorClass = 13;
		else if (armor.equals(SCALE_MAIL))
			armorClass = 14;
		else if (armor.equals(BREASTPLATE))
			armorClass = 14;
		else if (armor.equals(HALF_PLATE))
			armorClass = 15;
		else if (armor.equals(RING_MAIL))
			armorClass = 14;
		else if (armor.equals(CHAIN_MAIL))
			armorClass = 16;
		else if (armor.equals(SPLINT_MAIL))
			armorClass = 17;
		else if (armor.equals(PLATE_MAIL))
			armorClass = 18;

		return armorClass;
	}

	public static int getMaximumDexterity(Armor armor) {
		int maxDex = 10;

		if (armor.equals(UNARMORED) || armor.equals(MAGE))
			maxDex = 10;
		else if (armor.equals(BARBARIAN) || armor.equals(MONK))
			maxDex = 10;
		else if (armor.equals(PADDED_ARMOR) || armor.equals(LEATHER_ARMOR) || armor.equals(STUDDED_LEATHER))
			maxDex = 10;
		else if (armor.equals(HIDE_ARMOR) || armor.equals(CHAIN_SHIRT) || armor.equals(SCALE_MAIL)
				|| armor.equals(BREASTPLATE) || armor.equals(HALF_PLATE))
			maxDex = 2;
		else if (armor.equals(RING_MAIL) || armor.equals(CHAIN_MAIL) || armor.equals(SPLINT_MAIL)
				|| armor.equals(PLATE_MAIL))
			maxDex = 0;

		return maxDex;
	}

	private static void setupLightArmor(Actor actor) {
		EnumSet<Armor> set;
		if (actor.getArmorProficiency() == null)
			set = EnumSet.noneOf(Armor.class);
		else
			set = actor.getArmorProficiency();

		Armor[] array = LIGHT_ARMOR;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setArmorProficiency(set);
	}

	private static void setupMediumArmor(Actor actor) {
		EnumSet<Armor> set;
		if (actor.getArmorProficiency() == null)
			set = EnumSet.noneOf(Armor.class);
		else
			set = actor.getArmorProficiency();

		Armor[] array = LIGHT_MEDIUM;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setArmorProficiency(set);
	}

	private static void setupHeavyArmor(Actor actor) {
		EnumSet<Armor> set;
		if (actor.getArmorProficiency() == null)
			set = EnumSet.noneOf(Armor.class);
		else
			set = actor.getArmorProficiency();

		Armor[] array = LIGHT_MEDIUM_HEAVY;
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}

		actor.setArmorProficiency(set);
	}

	public static void setupArmorProficiency(Actor actor) {
		Class job = actor.getJob();

		if (job.equals(Class.BARBARIAN))
			setupMediumArmor(actor);
		else if (job.equals(Class.BARD))
			setupLightArmor(actor);
		else if (job.equals(Class.CLERIC))
			setupMediumArmor(actor);
		else if (job.equals(Class.DRUID))
			setupMediumArmor(actor);
		else if (job.equals(Class.FIGHTER))
			setupHeavyArmor(actor);
		else if (job.equals(Class.PALADIN))
			setupHeavyArmor(actor);
		else if (job.equals(Class.RANGER))
			setupMediumArmor(actor);
		else if (job.equals(Class.ROGUE))
			setupLightArmor(actor);
		else if (job.equals(Class.WARLOCK))
			setupLightArmor(actor);

		EnumSet<Armor> set;
		if (actor.getArmorProficiency() == null) {
			set = EnumSet.noneOf(Armor.class);
			actor.setArmorProficiency(set);
		}
	}

}
