import java.util.HashMap;

public class GameWeapon extends GameItem {
	private static HashMap<String, GameWeapon> weapons = new HashMap<String, GameWeapon>();

	// fields
	private Weapon weapon;

	// constructors
	public GameWeapon(String name, Weapon weapon, float baseWeight, int baseValue) {
		// typical item entry
		this(name, weapon, baseWeight, baseValue, false, false);
	}

	public GameWeapon(String name, Weapon weapon, float baseWeight, Rarity rarity, boolean requiresAttunement,
			boolean isCursed) {
		// typical magic item entry
		this(name, weapon, baseWeight, rarity.getBaseValue(), requiresAttunement, isCursed);
	}

	public GameWeapon(String name, Weapon weapon, float baseWeight, int baseValue, boolean requiresAttunement,
			boolean isCursed) {
		this.weapon = weapon;

		this.name = name;
		this.baseValue = baseValue;
		this.baseWeight = baseWeight;
		this.rarity = Rarity.getRarity(baseValue);
		this.requiresAttunement = requiresAttunement;
		this.isCursed = isCursed;

		weapons.put(name, this);
	}

	// initialization
	static {
		// WEAPONS
		new GameWeapon("Club", Weapon.CLUB, 2f, 10);
		new GameWeapon("Dagger", Weapon.DAGGER, 1f, 200);
		new GameWeapon("Greatclub", Weapon.GREATCLUB, 10f, 20);
		new GameWeapon("Handaxe", Weapon.HANDAXE, 2f, 500);
		new GameWeapon("Javelin", Weapon.JAVELIN, 2f, 50);
		new GameWeapon("Light Hammer", Weapon.LIGHT_HAMMER, 2f, 200);
		new GameWeapon("Mace", Weapon.MACE, 4f, 500);
		new GameWeapon("Quarterstaff", Weapon.QUARTERSTAFF, 4f, 20);
		new GameWeapon("Sickle", Weapon.SICKLE, 2f, 100);
		new GameWeapon("Spear", Weapon.SPEAR, 3f, 100);

		new GameWeapon("Light Crossbow", Weapon.LIGHT_CROSSBOW, 5f, 2500);
		new GameWeapon("Dart", Weapon.DART, 0.25f, 5);
		new GameWeapon("Shortbow", Weapon.SHORTBOW, 2f, 2500);
		new GameWeapon("Sling", Weapon.SLING, 0.01f, 10);

		new GameWeapon("Battleaxe", Weapon.BATTLEAXE, 4f, 1000);
		new GameWeapon("Flail", Weapon.FLAIL, 2f, 1000);
		new GameWeapon("Glaive", Weapon.GLAIVE, 6f, 2000);
		new GameWeapon("Greataxe", Weapon.GREATAXE, 7f, 3000);
		new GameWeapon("Greatsword", Weapon.GREATSWORD, 6f, 5000);
		new GameWeapon("Halberd", Weapon.HALBERD, 6f, 2000);
		new GameWeapon("Lance", Weapon.LANCE, 6f, 1000);
		new GameWeapon("Longsword", Weapon.LONGSWORD, 3f, 1500);
		new GameWeapon("Maul", Weapon.MAUL, 10f, 1000);
		new GameWeapon("Morningstar", Weapon.MORNINGSTAR, 4f, 1500);
		new GameWeapon("Pike", Weapon.PIKE, 18f, 500);
		new GameWeapon("Rapier", Weapon.RAPIER, 2f, 2500);
		new GameWeapon("Scimitar", Weapon.SCIMITAR, 3f, 2500);
		new GameWeapon("Shortsword", Weapon.SHORTSWORD, 2f, 1000);
		new GameWeapon("Trident", Weapon.TRIDENT, 4f, 500);
		new GameWeapon("War Pick", Weapon.WAR_PICK, 2f, 500);
		new GameWeapon("Warhammer", Weapon.WARHAMMER, 4f, 1500);
		new GameWeapon("Whip", Weapon.WHIP, 3f, 200);

		new GameWeapon("Blowgun", Weapon.BLOWGUN, 1f, 1000);
		new GameWeapon("Hand Crossbow", Weapon.HAND_CROSSBOW, 3f, 7500);
		new GameWeapon("Heavy Crossbow", Weapon.HEAVY_CROSSBOW, 18f, 5000);
		new GameWeapon("Longbow", Weapon.LONGBOW, 2f, 5000);
		new GameWeapon("Net", Weapon.NET, 3f, 100);
	}

	// static methods
	public static GameWeapon getWeapon(String weapon) {
		return weapons.get(weapon);
	}

	public static GameWeapon randomSimpleMelee() {
		String[] array = { "Club", "Dagger", "Greatclub", "Handaxe", "Javelin", "Light Hammer", "Mace", "Quarterstaff",
				"Sickle", "Spear" };
		String key = array[Dice.roll(array.length) - 1];
		GameWeapon gw = weapons.get(key);

		return gw;
	}

	public static GameWeapon randomSimpleRanged() {
		String[] array = { "Light Crossbow", "Dart", "Shortbow", "Sling" };
		String key = array[Dice.roll(array.length) - 1];
		GameWeapon gw = weapons.get(key);

		return gw;
	}

	public static GameWeapon randomSimpleWeapon() {
		GameWeapon gw;
		if (Dice.roll(2) == 1) {
			gw = randomSimpleMelee();
		} else {
			gw = randomSimpleRanged();
		}

		return gw;
	}

	public static GameWeapon randomMartialMelee() {
		// array doesn't include the lance for obvious(?) reasons
		String[] array = { "Battleaxe", "Flail", "Glaive", "Greatsword", "Halberd", "Longsword", "Maul", "Morningstar",
				"Pike", "Rapier", "Scimitar", "Shortsword", "Trident", "War Pick", "Warhammer", "Whip" };
		String key = array[Dice.roll(array.length) - 1];
		GameWeapon gw = weapons.get(key);

		return gw;
	}

	public static GameWeapon randomMartialRanged() {
		String[] array = { "Blowgun", "Hand Crossbow", "Heavy Crossbow", "Longbow", "Net" };
		String key = array[Dice.roll(array.length) - 1];
		GameWeapon gw = weapons.get(key);

		return gw;
	}

	public static GameWeapon randomMartialWeapon() {
		GameWeapon gw;
		if (Dice.roll(2) == 1) {
			gw = randomMartialMelee();
		} else {
			gw = randomMartialRanged();
		}

		return gw;
	}
}
