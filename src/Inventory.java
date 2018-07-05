import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Inventory {
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Actor owner;

	private ArrayList<Armor.Instance> armorList;
	private Weapon.WeaponList weaponList;
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

	public void setWeapons(Weapon.WeaponList list) {
		this.weaponList = new Weapon.WeaponList(list);
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
		if (armorList.size() > 0)
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
		Weapon.WeaponList weaponList = new Weapon.WeaponList();
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
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN, 4));
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
				weaponList.add(new Weapon.Instance(Weapon.BOLT, 20));
			} else {
				weapon = Weapon.randomSimpleWeapon();
				weaponList.add(new Weapon.Instance(weapon));
			}

			// TODO - add priest's or explorer's pack
			// TODO - receive shield + holy symbol
			weaponList.add(new Weapon.Instance(Weapon.SHIELD));
		} else if (job.equals(Class.DRUID)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.SCIMITAR));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleMelee()));
			}

			// TODO - receive explorer's pack + druid focus
			armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));

		} else if (job.equals(Class.FIGHTER)) {
			// first choice
			int strength = actor.getStrength();
			int dexterity = actor.getDexterity();
			if (strength < 13 || dexterity > 15) {
				// TODO - receive 20 arrows
				armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
				weaponList.add(new Weapon.Instance(Weapon.LONGBOW));
				weaponList.add(new Weapon.Instance(Weapon.ARROW, 20));
			} else {
				armorList.add(new Armor.Instance(Armor.CHAIN_MAIL));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.randomMartialWeapon()));
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			}

			// third choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 bolts
				weaponList.add(new Weapon.Instance(Weapon.LIGHT_CROSSBOW));
				weaponList.add(new Weapon.Instance(Weapon.BOLT, 20));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
			}

			// TODO - add dungeoneer's or explorer's pack

		} else if (job.equals(Class.MONK)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.SHORTSWORD));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			}

			// TODO - add dungeoneer's or explorer's pack
			// TODO - receive 10 darts
			weaponList.add(new Weapon.Instance(Weapon.DART));

		} else if (job.equals(Class.PALADIN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.randomMartialWeapon()));
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomMartialWeapon()));
				weaponList.add(new Weapon.Instance(Weapon.randomMartialWeapon()));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 5 javelins
				weaponList.add(new Weapon.Instance(Weapon.JAVELIN, 5));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleMelee()));
			}

			// TODO - add priest's or explorer's pack
			// TODO - receive holy symbol
			armorList.add(new Armor.Instance(Armor.CHAIN_MAIL));

		} else if (job.equals(Class.RANGER)) {
			// first choice
			int dexterity = actor.getDexterity();
			if (dexterity > 15) {
				armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			} else {
				armorList.add(new Armor.Instance(Armor.SCALE_MAIL));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.SHORTSWORD));
				weaponList.add(new Weapon.Instance(Weapon.SHORTSWORD));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleMelee()));
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleMelee()));
			}

			// third choice
			// TODO - add dungeoneer's or explorer's pack

			// TODO - receive longbow + 20 arrows
			weaponList.add(new Weapon.Instance(Weapon.LONGBOW));
			weaponList.add(new Weapon.Instance(Weapon.ARROW, 20));

		} else if (job.equals(Class.ROGUE)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.RAPIER));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.SHORTSWORD));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 arrows
				weaponList.add(new Weapon.Instance(Weapon.SHORTBOW));
				weaponList.add(new Weapon.Instance(Weapon.ARROW, 20));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.SHORTSWORD));
			}

			// TODO - add burglar's or dungeoneer's or explorer's pack
			// TODO - receive thieves' tool
			armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));

		} else if (job.equals(Class.SORCERER)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receives 20 bolts
				weaponList.add(new Weapon.Instance(Weapon.LIGHT_CROSSBOW));
				weaponList.add(new Weapon.Instance(Weapon.BOLT, 20));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			}

			// TODO - component pouch or arcane focus
			// TODO - add dungeoneer's or explorer's pack
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));

		} else if (job.equals(Class.WARLOCK)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receives 20 bolts
				weaponList.add(new Weapon.Instance(Weapon.LIGHT_CROSSBOW));
				weaponList.add(new Weapon.Instance(Weapon.BOLT, 20));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			}

			// TODO - component pouch or arcane focus
			// TODO - add dungeoneer's or scholar's pack
			armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			weaponList.add(new Weapon.Instance(Weapon.randomSimpleWeapon()));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));
			weaponList.add(new Weapon.Instance(Weapon.DAGGER));

		} else if (job.equals(Class.WIZARD)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.QUARTERSTAFF));
			} else {
				weaponList.add(new Weapon.Instance(Weapon.DAGGER));
			}

			// TODO - component pouch or arcane focus
			// TODO - add scholar's or explorer's pack
			// TODO - receive spellbook
		}

		inventory.setWeapons(weaponList);
		inventory.setArmor(armorList);
		actor.setInventory(inventory);
	}

}
