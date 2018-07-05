import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Inventory {
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Actor owner;

	private ArrayList<Armor.Instance> armorList;
	private ArrayList<Weapon.Instance> weaponList;
	// private ArrayList<Tool.Instance> tools;

	// Helmet brow;
	// Mask face;
	// Necklace neck;
	// private Armor.Instance armor;
	private Weapon.Instance mainHand;
	private Weapon.Instance offHand;
	// Gloves gloves;
	// Bracers bracers;
	// Belt belt;
	// Boots boots;
	// Ring ring1, ring2, ring3;
	// ArrayList<> wondrous;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Inventory(Actor owner) {
		this.owner = owner;

		// FIXME - testing
		// String string = String.format("%s %s", owner.getRace(), owner.getJob());
		// System.out.println("New inventory created for: " + string);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public boolean hasOwner() {
		return owner != null;
	}

	// public boolean wearingArmor() {
	// return armor != null;
	// }

	public boolean holdingMainHand() {
		return mainHand != null;
	}

	public boolean holdingOffHand() {
		return offHand != null;
	}

	public List<Weapon.Instance> getWeapons() {
		return weaponList;
	}

	public void setWeapons(List<Weapon.Instance> list) {
		this.weaponList = new ArrayList<Weapon.Instance>(list);
	}

	public List<Armor.Instance> getArmor() {
		return armorList;
	}

	public void setArmor(List<Armor.Instance> list) {
		this.armorList = new ArrayList<Armor.Instance>(list);
	}

	public String toStringDetailed() {
		String string = "";

		// weapon line
		string += weaponList.toString();
		// armor line
		string += String.format("%n%s", armorList.toString());

		return string;
	}

	/*
	 * STARTING GEAR
	 * 
	 */
	public static void setupStartingGear(Actor actor) {
		Inventory inventory = new Inventory(actor);

		// create lists
		List<Weapon.Instance> weaponList = new ArrayList<Weapon.Instance>();
		List<Armor.Instance> armorList = new ArrayList<Armor.Instance>();

		//
		EnumSet<Armor> armorProf = actor.getArmorProficiency();
		EnumSet<Weapon> weaponProf = actor.getWeaponProficiency();

		int dice;
		Weapon weapon;
		Armor armor;

		Class job = actor.getJob();
		if (job.equals(Class.BARBARIAN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.GREATAXE));
			} else {
				// random martial melee
				weapon = Weapon.randomMartialMelee();
				weaponList.add(new Weapon.Instance(weapon));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
			} else {
				weapon = Weapon.randomSimpleWeapon();
				weaponList.add(new Weapon.Instance(weapon));
			}

			// TODO - add explorer's pack

			// receive 4 javelins
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN));
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN));
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN));
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN));
		} else if (job.equals(Class.BARD)) {
			dice = Dice.roll(3);
			if (dice == 1) {
				weapon = Weapon.RAPIER;
				weaponList.add(new Weapon.Instance(Weapon.RAPIER));
			} else if (dice == 2) {
				weapon = Weapon.LONGSWORD;
				weaponList.add(new Weapon.Instance(Weapon.LONGSWORD));
			} else {
				// random simple weapon
				weapon = Weapon.randomSimpleWeapon();
				weaponList.add(new Weapon.Instance(weapon));
			}

			// TODO - add diplomat's or entertainer's pack
			// TODO - add lute or any instrument

			// receive leather armor + dagger
			armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));

		} else if (job.equals(Class.CLERIC)) {
			int strength = actor.getStrength();
			int dexterity = actor.getDexterity();

			// first choice
			if (weaponProf.contains(Weapon.WARHAMMER)) {
				weaponList.add(new Weapon.Instance(Weapon.WARHAMMER));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.MACE));
			}

			// second choice
			if (dexterity > 15) {
				armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			} else if (armorProf.contains(Armor.CHAIN_MAIL) && strength > 12) {
				armorList.add(new Armor.Instance(Armor.CHAIN_MAIL));
			} else {
				armorList.add(new Armor.Instance(Armor.SCALE_MAIL));
			}

			// third choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 bolts
				weaponList.add(new Weapon.Instance(Weapon.LIGHT_CROSSBOW));
			} else {
				weapon = Weapon.randomSimpleWeapon();
				weaponList.add(new Weapon.Instance(weapon));
			}

			// TODO - add priest's or explorer's pack
			// TODO - receive shield + holy symbol
			weaponList.add(new Weapon.Instance(Weapon.SHIELD));
		}

		inventory.setWeapons(weaponList);
		inventory.setArmor(armorList);
		actor.setInventory(inventory);
	}

	// } else if (job.equals(Class.DRUID)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.randomDruidSimple());
	// } else {
	// weaponProf.add(GameWeapon.getWeapon("Shield"));
	// }
	//
	// // second choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.getWeapon("Scimitar"));
	// } else {
	// weaponProf.add(GameWeapon.randomDruidMelee());
	// }
	//
	// // TODO - receive explorer's pack + druid focus
	// armory.add(GameArmor.getArmor("Leather Armor"));
	// } else if (job.equals(Class.FIGHTER)) {
	// // first choice
	// int strength = actor.getStrength();
	// int dexterity = actor.getDexterity();
	// if (strength < 13 || dexterity > 15) {
	// // TODO - receive 20 arrows
	// armory.add(GameArmor.getArmor("Leather Armor"));
	// weaponProf.add(GameWeapon.getWeapon("Longbow"));
	// } else {
	// armory.add(GameArmor.getArmor("Chain Mail"));
	// }
	//
	// // second choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.randomMartialWeapon());
	// weaponProf.add(GameWeapon.getWeapon("Shield"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleWeapon());
	// }
	//
	// // third choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// // TODO - receive 20 bolts
	// weaponProf.add(GameWeapon.getWeapon("Light Crossbow"));
	// } else {
	// weaponProf.add(GameWeapon.getWeapon("Handaxe"));
	// weaponProf.add(GameWeapon.getWeapon("Handaxe"));
	// }
	//
	// // TODO - add dungeoneer's or explorer's pack
	// } else if (job.equals(Class.MONK)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.getWeapon("Shortsword"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleWeapon());
	// }
	//
	// // TODO - add dungeoneer's or explorer's pack
	// // TODO - receive 10 darts
	// weaponProf.add(GameWeapon.getWeapon("Dart"));
	// } else if (job.equals(Class.PALADIN)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.randomMartialWeapon());
	// weaponProf.add(GameWeapon.getWeapon("Shield"));
	// } else {
	// weaponProf.add(GameWeapon.randomMartialWeapon());
	// weaponProf.add(GameWeapon.randomMartialWeapon());
	// }
	//
	// // second choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// // TODO - receive 5 javelins
	// weaponProf.add(GameWeapon.getWeapon("Javelin"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleMelee());
	// }
	//
	// // TODO - add priest's or explorer's pack
	// // TODO - receive holy symbol
	// armory.add(GameArmor.getArmor("Chain Mail"));
	// } else if (job.equals(Class.RANGER)) {
	// // first choice
	// int dexterity = actor.getDexterity();
	// if (dexterity > 15) {
	// armory.add(GameArmor.getArmor("Leather Armor"));
	// } else {
	// armory.add(GameArmor.getArmor("Scale Mail"));
	// }
	//
	// // second choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.getWeapon("Shortsword"));
	// weaponProf.add(GameWeapon.getWeapon("Shortsword"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleMelee());
	// weaponProf.add(GameWeapon.randomSimpleMelee());
	// }
	//
	// // third choice
	// // TODO - add dungeoneer's or explorer's pack
	//
	// // TODO - receive longbow + 20 arrows
	// weaponProf.add(GameWeapon.getWeapon("Longbow"));
	// } else if (job.equals(Class.ROGUE)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.getWeapon("Rapier"));
	// } else {
	// weaponProf.add(GameWeapon.getWeapon("Shortsword"));
	// }
	//
	// // second choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// // TODO - receive 20 arrows
	// weaponProf.add(GameWeapon.getWeapon("Shortbow"));
	// } else {
	// weaponProf.add(GameWeapon.getWeapon("Shortsword"));
	// }
	//
	// // TODO - add burglar's or dungeoneer's or explorer's pack
	// // TODO - receive thieves' tool
	// armory.add(GameArmor.getArmor("Leather Armor"));
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// } else if (job.equals(Class.SORCERER)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// // TODO - receives 20 bolts
	// weaponProf.add(GameWeapon.getWeapon("Light Crossbow"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleWeapon());
	// }
	//
	// // TODO - component pouch or arcane focus
	// // TODO - add dungeoneer's or explorer's pack
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// } else if (job.equals(Class.WARLOCK)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// // TODO - receives 20 bolts
	// weaponProf.add(GameWeapon.getWeapon("Light Crossbow"));
	// } else {
	// weaponProf.add(GameWeapon.randomSimpleWeapon());
	// }
	//
	// // TODO - component pouch or arcane focus
	// // TODO - add dungeoneer's or scholar's pack
	// armory.add(GameArmor.getArmor("Leather Armor"));
	// weaponProf.add(GameWeapon.randomSimpleWeapon());
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// } else if (job.equals(Class.WIZARD)) {
	// // first choice
	// dice = Dice.roll(2);
	// if (dice == 1) {
	// weaponProf.add(GameWeapon.getWeapon("Quarterstaff"));
	// } else {
	// weaponProf.add(GameWeapon.getWeapon("Dagger"));
	// }
	//
	// // TODO - component pouch or arcane focus
	// // TODO - add scholar's or explorer's pack
	// // TODO - receive spellbook
	// }
	// }

}
