import java.util.HashMap;

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
	private boolean reqAttunement;
	private boolean isCursed;

	// constructors
	public Gear(String name, Item item, float baseWeight, int baseValue) {
		// typical item entry
		this(name, item, baseWeight, baseValue, false, false);
	}

	public Gear(String name, Item item, float baseWeight, Rarity rarity, boolean reqAttunement, boolean isCursed) {
		// typical magic item entry
		this(name, item, baseWeight, rarity.getBaseValue(), reqAttunement, isCursed);
	}

	public Gear(String name, Item item, float baseWeight, int baseValue, boolean reqAttunement, boolean isCursed) {
		this.name = name;
		this.baseValue = baseValue;
		this.baseWeight = baseWeight;
		this.item = item;
		this.rarity = Rarity.getRarity(baseValue);
		this.reqAttunement = reqAttunement;
		this.isCursed = isCursed;

		if (rarity.equals(Rarity.MUNDANE)) {
			mundane.put(name, this);
		} else if (rarity.equals(Rarity.COMMON)) {
			common.put(name, this);
		} else if (rarity.equals(Rarity.UNCOMMON)) {
			uncommon.put(name, this);
		} else if (rarity.equals(Rarity.RARE)) {
			rare.put(name, this);
		} else if (rarity.equals(Rarity.VERY_RARE)) {
			veryRare.put(name, this);
		} else if (rarity.equals(Rarity.LEGENDARY)) {
			legendary.put(name, this);
		}
	}

	// static methods
	public static boolean initialize() {
		boolean didTheThing = false;
		if (initialized != true) {
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
			
			didTheThing = true;
		}
		
		return didTheThing;
	}
}