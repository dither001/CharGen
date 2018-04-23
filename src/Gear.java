import java.util.HashMap;
import java.util.Vector;

public class Gear {
	// static fields
	private static boolean initialized = false;
	private static HashMap<String, Gear> mundane = new HashMap<String, Gear>();
	private static HashMap<String, Gear> common = new HashMap<String, Gear>();
	private static HashMap<String, Gear> uncommon = new HashMap<String, Gear>();
	private static HashMap<String, Gear> rare = new HashMap<String, Gear>();
	private static HashMap<String, Gear> veryRare = new HashMap<String, Gear>();
	private static HashMap<String, Gear> legendary = new HashMap<String, Gear>();

	// fields
	private String name;
	private Item item;
	private float baseWeight;
	private int baseValue;
	private Rarity rarity;

	// additional fields
	private boolean requiresAttunement;
	private boolean isCursed;

	// constructors
	public Gear(String name, Item item, float baseWeight, int baseValue) {
		// typical item entry
		this(name, item, baseWeight, baseValue, false, false);
	}

	public Gear(String name, Item item, float baseWeight, Rarity rarity, boolean requiresAttunement, boolean isCursed) {
		// typical magic item entry
		this(name, item, baseWeight, rarity.getBaseValue(), requiresAttunement, isCursed);
	}

	public Gear(String name, Item item, float baseWeight, int baseValue, boolean requiresAttunement, boolean isCursed) {
		this.setName(name);
		this.setBaseValue(baseValue);
		this.setBaseWeight(baseWeight);
		this.setItem(item);
		this.rarity = Rarity.getRarity(baseValue);
		this.requiresAttunement = requiresAttunement;
		this.isCursed = isCursed;

		// TODO - I should actually be using this
		// if (rarity.equals(Rarity.MUNDANE)) {
		// mundane.put(name, this);
		// } else if (rarity.equals(Rarity.COMMON)) {
		// common.put(name, this);
		// } else if (rarity.equals(Rarity.UNCOMMON)) {
		// uncommon.put(name, this);
		// } else if (rarity.equals(Rarity.RARE)) {
		// rare.put(name, this);
		// } else if (rarity.equals(Rarity.VERY_RARE)) {
		// veryRare.put(name, this);
		// } else if (rarity.equals(Rarity.LEGENDARY)) {
		// legendary.put(name, this);
		// }

		mundane.put(name, this);
	}

	// methods
	public boolean requiresAttunement() {
		return requiresAttunement;
	}

	public boolean isCursed() {
		return isCursed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(float baseWeight) {
		this.baseWeight = baseWeight;
	}

	public int getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(int baseValue) {
		this.baseValue = baseValue;
	}

	// static methods
	public static void initialize() {
		if (initialized)
			return;

		// ARMOR
		new Gear("Padded Armor", Armor.PADDED, 8f, 500);
		new Gear("Leather Armor", Armor.LEATHER, 10f, 1000);
		new Gear("Studded Leather", Armor.STUDDED, 13f, 4500);

		new Gear("Hide Armor", Armor.HIDE, 12f, 1000);
		new Gear("Chain Shirt", Armor.CHAIN_SHIRT, 20f, 5000);
		new Gear("Scale Mail", Armor.SCALE_MAIL, 45f, 5000);
		new Gear("Breastplate", Armor.BREASTPLATE, 20f, 40000);
		new Gear("Half Plate", Armor.HALF_PLATE, 40f, 75000);

		new Gear("Ring Mail", Armor.RING_MAIL, 40f, 3000);
		new Gear("Chain Mail", Armor.CHAIN_MAIL, 55f, 7500);
		new Gear("Splint Mail", Armor.SPLINT, 60f, 20000);
		new Gear("Plate Mail", Armor.PLATE, 65f, 150000);

		// WEAPONS
		new Gear("Club", Weapon.CLUB, 2f, 10);
		new Gear("Dagger", Weapon.DAGGER, 1f, 200);
		new Gear("Greatclub", Weapon.GREATCLUB, 10f, 20);
		new Gear("Handaxe", Weapon.HANDAXE, 2f, 500);
		new Gear("Javelin", Weapon.JAVELIN, 2f, 50);
		new Gear("Light Hammer", Weapon.LIGHT_HAMMER, 2f, 200);
		new Gear("Mace", Weapon.MACE, 4f, 500);
		new Gear("Quarterstaff", Weapon.QUARTERSTAFF, 4f, 20);
		new Gear("Sickle", Weapon.SICKLE, 2f, 100);
		new Gear("Spear", Weapon.SPEAR, 3f, 100);

		new Gear("Light Crossbow", Weapon.LIGHT_CROSSBOW, 5f, 2500);
		new Gear("Dart", Weapon.DART, 0.25f, 5);
		new Gear("Shortbow", Weapon.SHORTBOW, 2f, 2500);
		new Gear("Sling", Weapon.SLING, 0.01f, 10);

		new Gear("Battleaxe", Weapon.BATTLEAXE, 4f, 1000);
		new Gear("Flail", Weapon.FLAIL, 2f, 1000);
		new Gear("Glaive", Weapon.GLAIVE, 6f, 2000);
		new Gear("Greataxe", Weapon.GREATAXE, 7f, 3000);
		new Gear("Greatsword", Weapon.GREATSWORD, 6f, 5000);
		new Gear("Halberd", Weapon.HALBERD, 6f, 2000);
		new Gear("Lance", Weapon.LANCE, 6f, 1000);
		new Gear("Longsword", Weapon.LONGSWORD, 3f, 1500);
		new Gear("Maul", Weapon.MAUL, 10f, 1000);
		new Gear("Morningstar", Weapon.MORNINGSTAR, 4f, 1500);
		new Gear("Pike", Weapon.PIKE, 18f, 500);
		new Gear("Rapier", Weapon.RAPIER, 2f, 2500);
		new Gear("Scimitar", Weapon.SCIMITAR, 3f, 2500);
		new Gear("Shortsword", Weapon.SHORTSWORD, 2f, 1000);
		new Gear("Trident", Weapon.TRIDENT, 4f, 500);
		new Gear("War Pick", Weapon.WAR_PICK, 2f, 500);
		new Gear("Warhammer", Weapon.WARHAMMER, 4f, 1500);
		new Gear("Whip", Weapon.WHIP, 3f, 200);

		new Gear("Blowgun", Weapon.BLOWGUN, 1f, 1000);
		new Gear("Hand Crossbow", Weapon.HAND_CROSSBOW, 3f, 7500);
		new Gear("Heavy Crossbow", Weapon.HEAVY_CROSSBOW, 18f, 5000);
		new Gear("Longbow", Weapon.LONGBOW, 2f, 5000);
		new Gear("Net", Weapon.NET, 3f, 100);

		initialized = true;
	}

	public static Gear randomSimpleMelee() {
		String[] array = { "Club", "Dagger", "Greatclub", "Handaxe", "Javelin", "Light Hammer", "Mace", "Quarterstaff",
				"Sickle", "Spear" };
		String key = array[Dice.roll(array.length) - 1];
		Gear g = mundane.get(key);

		return g;
	}

	public static Gear randomSimpleRanged() {
		String[] array = { "Light Crossbow", "Dart", "Shortbow", "Sling" };
		String key = array[Dice.roll(array.length) - 1];
		Gear g = mundane.get(key);

		return g;
	}

	public static Gear randomSimpleWeapon() {
		Gear g;
		if (Dice.roll(2) == 1) {
			g = randomSimpleMelee();
		} else {
			g = randomSimpleRanged();
		}

		return g;
	}

	public static Gear randomMartialMelee() {
		// array doesn't include the lance for obvious(?) reasons
		String[] array = { "Battleaxe", "Flail", "Glaive", "Greatsword", "Halberd", "Longsword", "Maul", "Morningstar",
				"Pike", "Rapier", "Scimitar", "Shortsword", "Trident", "War Pick", "Warhammer", "Whip" };
		String key = array[Dice.roll(array.length) - 1];
		Gear g = mundane.get(key);

		return g;
	}

	public static Vector<Gear> getStartingGear(Archetype job) {
		Vector<Gear> gear = new Vector<Gear>();

		switch (job) {
		case BARBARIAN:
			gear = getBarbarianGear();
			break;
		// case BARD: gear = getBardGear(); break;
		// case CLERIC: gear = getClericGear(); break;
		// case DRUID: gear = getDruidGear(); break;
		// case FIGHTER: gear = getFighterGear(); break;
		// case MONK: gear = getMonkGear(); break;
		// case PALADIN: gear = getPaladinGear(); break;
		// case RANGER: gear = getRangerGear(); break;
		// case ROGUE: gear = getRogueGear(); break;
		// case SORCERER: gear = getSorcererGear(); break;
		// case WARLOCK: gear = getWarlockGear(); break;
		// case WIZARD: gear = getWizardGear(); break;
		default:
			break;
		}

		return gear;
	}

	public static Vector<Gear> getBarbarianGear() {
		Vector<Gear> gear = new Vector<Gear>();
		int dice;

		// first choice
		dice = Dice.roll(2); 
		if (dice == 1) {
			gear.add(mundane.get("Greataxe"));
		} else {
			gear.add(randomMartialMelee());
		}

		// second choice
		dice = Dice.roll(2); 
		if (dice == 1) {
			gear.add(mundane.get("Handaxe"));
			gear.add(mundane.get("Handaxe"));
		} else {
			gear.add(randomSimpleWeapon());
		}

		// TODO - add explorer's pack

		// receive 4 javelins
		gear.add(mundane.get("Javelin"));
		gear.add(mundane.get("Javelin"));
		gear.add(mundane.get("Javelin"));
		gear.add(mundane.get("Javelin"));

		return gear;
	}

}