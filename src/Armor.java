import java.util.Arrays;
import java.util.HashSet;

public enum Armor implements Item, Proficiency {
	UNARMORED, BARBARIAN, MAGE(13), MONK, PADDED(11), LEATHER(11), STUDDED(12), HIDE(12, 2), CHAIN_SHIRT(13,
			2), SCALE_MAIL(14, 2), BREASTPLATE(14,
					2), HALF_PLATE(15, 2), RING_MAIL(14, 0), CHAIN_MAIL(16, 0), SPLINT(17, 0), PLATE(18, 0);

	// public enum Weight {
	// NONE, BARBARIAN, MONK, WARLOCK, MAGE, LIGHT, MEDIUM, HEAVY
	// };

	// fields
	private static final Armor BEST_HEAVY_ARMOR;
	private static final Armor BEST_MEDIUM_ARMOR;
	private static final Armor BEST_LIGHT_ARMOR;

	private int armorClass;
	private int maxDexterity;

	// initialization
	static {
		BEST_HEAVY_ARMOR = PLATE;
		BEST_MEDIUM_ARMOR = HALF_PLATE;
		BEST_LIGHT_ARMOR = STUDDED;
	}

	// constructors
	Armor() {
		this(10);
	}

	Armor(int armorClass) {
		this(armorClass, 10);
	}

	Armor(int armorClass, int maxDexterity) {
		this.setArmorClass(armorClass);
		this.setMaxDexterity(maxDexterity);
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getMaxDexterity() {
		return maxDexterity;
	}

	public void setMaxDexterity(int maxDexterity) {
		this.maxDexterity = maxDexterity;
	}

	public boolean canUseLightArmor(HashSet<Proficiency> proficiency) {
		boolean canUseArmor = false;
		canUseArmor = proficiency.contains(STUDDED);
		return canUseArmor;
	}

	public boolean canUseMediumArmor(HashSet<Proficiency> proficiency) {
		boolean canUseArmor = false;
		canUseArmor = proficiency.contains(BREASTPLATE);
		return canUseArmor;
	}

	public boolean canUseHeavyArmor(HashSet<Proficiency> proficiency) {
		boolean canUseArmor = false;
		canUseArmor = proficiency.contains(PLATE);
		return canUseArmor;
	}

	// static methods
	public static int getArmorClass(Armor armor) {
		return armor.getArmorClass();
	}

	public static HashSet<Proficiency> lightArmorSet() {
		Armor[] armor = { PADDED, LEATHER, STUDDED };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(armor));
		return list;
	}

	public static HashSet<Proficiency> mediumArmorSet() {
		Armor[] armor = { HIDE, CHAIN_SHIRT, SCALE_MAIL, BREASTPLATE, HALF_PLATE };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(armor));
		return list;
	}

	public static HashSet<Proficiency> heavyArmorSet() {
		Armor[] armor = { RING_MAIL, CHAIN_MAIL, SPLINT, PLATE };
		HashSet<Proficiency> list = new HashSet<Proficiency>();

		list.addAll(Arrays.asList(armor));
		return list;
	}

	public static HashSet<Proficiency> getProficiency(Archetype job) {
		HashSet<Proficiency> list = new HashSet<Proficiency>();
		list.add(UNARMORED);

		switch (job) {
		case BARBARIAN:
			list.add(BARBARIAN);
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			break;
		case BARD:
			list.addAll(lightArmorSet());
			break;
		case CLERIC:
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			break;
		case DRUID:
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			break;
		case FIGHTER:
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			list.addAll(heavyArmorSet());
			break;
		case MONK:
			list.add(MONK);
			break;
		case PALADIN:
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			list.addAll(heavyArmorSet());
			break;
		case RANGER:
			list.addAll(lightArmorSet());
			list.addAll(mediumArmorSet());
			break;
		case ROGUE:
			list.addAll(lightArmorSet());
			break;
		case SORCERER:
			list.add(MAGE);
			break;
		case WARLOCK:
			list.add(MAGE);
			list.addAll(lightArmorSet());
			break;
		case WIZARD:
			list.add(MAGE);
			break;
		default:
			break;
		}

		return list;
	}

	public static Armor bestHeavyArmor() {
		return BEST_HEAVY_ARMOR;
	}

	public static Armor bestMediumArmor() {
		return BEST_MEDIUM_ARMOR;
	}

	public static Armor bestLightArmor() {
		return BEST_LIGHT_ARMOR;
	}

	public static Armor nextBestArmor() {
		return nextBestArmor(bestHeavyArmor());
	}

	public static Armor nextBestArmor(Armor previous) {
		Armor nextBest;

		switch (previous) {
		case PLATE:
			nextBest = SPLINT;
			break;
		case SPLINT:
			nextBest = CHAIN_MAIL;
			break;
		case CHAIN_MAIL:
			nextBest = RING_MAIL;
			break;
		case RING_MAIL:
			nextBest = HALF_PLATE;
			break;
		case HALF_PLATE:
			nextBest = BREASTPLATE;
			break;
		case BREASTPLATE:
			nextBest = SCALE_MAIL;
			break;
		case SCALE_MAIL:
			nextBest = CHAIN_SHIRT;
			break;
		case CHAIN_SHIRT:
			nextBest = HIDE;
			break;
		case HIDE:
			nextBest = STUDDED;
			break;
		case STUDDED:
			nextBest = LEATHER;
			break;
		case LEATHER:
			nextBest = PADDED;
			break;
		default:
			nextBest = UNARMORED;
			break;
		}

		return nextBest;
	}
}
