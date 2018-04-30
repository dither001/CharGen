import java.util.HashMap;
import java.util.HashSet;

public class GameArmor extends GameItem {
	private static HashMap<String, GameArmor> armory = new HashMap<String, GameArmor>();

	// fields
	private Armor armor;

	// constructors
	public GameArmor(String name, Armor armor, float baseWeight, int baseValue) {
		// typical item entry
		this(name, armor, baseWeight, baseValue, false, false);
	}

	public GameArmor(String name, Armor armor, float baseWeight, Rarity rarity, boolean requiresAttunement,
			boolean isCursed) {
		// typical magic item entry
		this(name, armor, baseWeight, rarity.getBaseValue(), requiresAttunement, isCursed);
	}

	public GameArmor(String name, Armor armor, float baseWeight, int baseValue, boolean requiresAttunement,
			boolean isCursed) {
		this.armor = armor;

		this.name = name;
		this.baseValue = baseValue;
		this.baseWeight = baseWeight;
		this.rarity = Rarity.getRarity(baseValue);
		this.requiresAttunement = requiresAttunement;
		this.isCursed = isCursed;

		armory.put(name, this);
	}

	// initialization
	static {
		// ARMOR
		new GameArmor("Padded Armor", Armor.PADDED, 8f, 500);
		new GameArmor("Leather Armor", Armor.LEATHER, 10f, 1000);
		new GameArmor("Studded Leather", Armor.STUDDED, 13f, 4500);

		new GameArmor("Hide Armor", Armor.HIDE, 12f, 1000);
		new GameArmor("Chain Shirt", Armor.CHAIN_SHIRT, 20f, 5000);
		new GameArmor("Scale Mail", Armor.SCALE_MAIL, 45f, 5000);
		new GameArmor("Breastplate", Armor.BREASTPLATE, 20f, 40000);
		new GameArmor("Half Plate", Armor.HALF_PLATE, 40f, 75000);

		new GameArmor("Ring Mail", Armor.RING_MAIL, 40f, 3000);
		new GameArmor("Chain Mail", Armor.CHAIN_MAIL, 55f, 7500);
		new GameArmor("Splint Mail", Armor.SPLINT, 60f, 20000);
		new GameArmor("Plate Mail", Armor.PLATE, 65f, 150000);
	}

	// static methods
	public static GameArmor getArmor(String armor) {
		return armory.get(armor);
	}

	// methods
	public Armor getArmor() {
		return armor;
	}
	
	public boolean canEquip(HashSet<Proficiency> skills) {
		boolean canEquip = false;
		if (skills.contains(armor)) {
			canEquip = true;
		}
		
		return canEquip;
	}

	public int reduce() {
		int totalBonus;
		totalBonus = this.getArmor().getArmorClass();
		// TODO - need to include bonuses from magic, etc
		return totalBonus;
	}
}
