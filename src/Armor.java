import java.util.EnumSet;

public enum Armor {
	UNARMORED, BARBARIAN, MAGE, MONK, PADDED, LEATHER, STUDDED, HIDE, CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE, HALF_PLATE, RING_MAIL, CHAIN_MAIL, SPLINT, PLATE;

	// static fields
	private static final Armor[] LIGHT_ARMOR = { PADDED, LEATHER, STUDDED };
	// private static final Armor[] MEDIUM_ARMOR = { HIDE, CHAIN_SHIRT, SCALE_MAIL,
	// BREASTPLATE, HALF_PLATE };
	// private static final Armor[] HEAVY_ARMOR = { RING_MAIL, CHAIN_MAIL, SPLINT,
	// PLATE };
	private static final Armor[] LIGHT_MEDIUM = { PADDED, LEATHER, STUDDED, HIDE, CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE,
			HALF_PLATE };
	private static final Armor[] LIGHT_MEDIUM_HEAVY = { PADDED, LEATHER, STUDDED, HIDE, CHAIN_SHIRT, SCALE_MAIL,
			BREASTPLATE, HALF_PLATE, RING_MAIL, CHAIN_MAIL, SPLINT, PLATE };

	// static methods
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
		else if (armor.equals(PADDED))
			armorClass = 11;
		else if (armor.equals(LEATHER))
			armorClass = 11;
		else if (armor.equals(STUDDED))
			armorClass = 12;
		else if (armor.equals(HIDE))
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
		else if (armor.equals(SPLINT))
			armorClass = 17;
		else if (armor.equals(PLATE))
			armorClass = 18;

		return armorClass;
	}

	public static int getMaximumDexterity(Armor armor) {
		int maxDex = 10;

		if (armor.equals(UNARMORED) || armor.equals(MAGE))
			maxDex = 10;
		else if (armor.equals(BARBARIAN) || armor.equals(MONK))
			maxDex = 10;
		else if (armor.equals(PADDED) || armor.equals(LEATHER) || armor.equals(STUDDED))
			maxDex = 10;
		else if (armor.equals(HIDE) || armor.equals(CHAIN_SHIRT) || armor.equals(SCALE_MAIL)
				|| armor.equals(BREASTPLATE) || armor.equals(HALF_PLATE))
			maxDex = 2;
		else if (armor.equals(RING_MAIL) || armor.equals(CHAIN_MAIL) || armor.equals(SPLINT) || armor.equals(PLATE))
			maxDex = 0;

		return maxDex;
	}

	public static void setupLightArmor(Actor actor) {
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

	public static void setupMediumArmor(Actor actor) {
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

	public static void setupHeavyArmor(Actor actor) {
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
