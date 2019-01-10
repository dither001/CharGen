package com.dnd5e.gear;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.norvendae.misc.Dice;

import actor.Actor;
import actor.Class;
import actor.Combat;
import actor.Player;

public class Inventory {
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Player owner;

	private ArrayList<Armor.Instance> armorList;
	private Weapon.WeaponSet weaponSet;
	// private ArrayList<Tool.Instance> tools;

	private Armor.Instance bodyArmor;
	private Weapon.Instance mainHand;
	private Weapon.Instance offHand;
	// Ring ring1, ring2, ring3;
	// ArrayList<> wondrous;

	/*
	 * CONSTRUCTORS
	 */
	public Inventory(Player owner) {
		this.owner = owner;
	}

	/*
	 * INSTANCE METHODS
	 */
	public boolean hasOwner() {
		return owner != null;
	}

	public Set<Weapon.Instance> getWeapons() {
		return weaponSet;
	}

	public void setWeapons(Weapon.WeaponSet list) {
		this.weaponSet = new Weapon.WeaponSet(owner, list);
	}

	public List<Armor.Instance> getArmor() {
		return armorList;
	}

	public void setArmor(List<Armor.Instance> list) {
		this.armorList = new ArrayList<Armor.Instance>(list);
	}

	//
	public List<Weapon.Instance> weaponList() {
		List<Weapon.Instance> list = new ArrayList<Weapon.Instance>();
		list.addAll(weaponSet.list());

		Weapon.SortByUseability sort = new Weapon.SortByUseability(owner);
		Collections.sort(list, sort);

		return list;
	}

	/*
	 * FIXME - OLD STUFF
	 * 
	 */
	private void optimizeArmor() {
		if (clearBodyArmor() && armorList.size() > 0) {
			// TODO - needs to sort list
			List<Armor.Instance> list = filterArmorForUseable(owner);
			if (list.size() > 0) {
				Armor.Instance armor = list.get(0);
				equipArmor(armor);
				// System.out.println("Equipped " + armor.toString());
			}
		}
	}

	private void optimizeWeapons() {
		if (clearMainHand() && clearOffHand() && weaponSet.size() > 0) {
			Combat.Role role = Combat.getRoleFromClass(owner.getJob());

			// TODO - needs to sort list
			List<Weapon.Instance> list = Dice.setToList(filterWeaponsForUseable(owner));
			if (list.size() > 0 && role.equals(Combat.Role.STRIKER)) {
				Weapon.Instance weapon = list.get(0);
				equipMainHand(weapon);
				// System.out.println("Equipped " + weapon.toString());

			} else if (list.size() > 0
					&& (role.equals(Combat.Role.DEFENDER) || role.equals(Combat.Role.LEADER))) {
				Weapon.SortByDefender sortMethod = new Weapon.SortByDefender();
				Collections.sort(list, sortMethod);

				Weapon.Instance slotOne = list.get(0), slotTwo;
				slotTwo = (list.size() > 1) ? list.get(1) : null;
				if (slotOne.isShield() && (slotTwo != null && slotTwo.oneHandUseable())) {
					equipMainHand(slotTwo);
					// System.out.println("Equipped " + slotTwo.toString());
					equipOffHand(slotOne);
					// System.out.println("Equipped " + slotOne.toString());
				} else {
					equipMainHand(slotOne);
					// System.out.println("Equipped " + slotOne.toString());
				}

			} else if (list.size() > 0 && role.equals(Combat.Role.LEADER)) {

			} else if (list.size() > 0 && role.equals(Combat.Role.CONTROLLER)) {

			}
		}
	}

	private void equipMainHand(Weapon.Instance weapon) {
		EnumSet<Weapon> weaponProf = owner.getWeaponProficiency();
		Weapon check = weapon.getWeapon();

		if (mainHand == null && weaponProf.contains(check))
			mainHand = weapon;
		else if ((mainHand != null && mainHand.notCursed()) && weaponProf.contains(check))
			mainHand = weapon;
	}

	private void equipOffHand(Weapon.Instance weapon) {
		EnumSet<Weapon> weaponProf = owner.getWeaponProficiency();
		Weapon check = weapon.getWeapon();

		boolean ideal = (weapon.oneHandUseable()) ? true : false;
		if (ideal && offHand == null && weaponProf.contains(check))
			offHand = weapon;
		else if ((offHand != null && offHand.notCursed()) && weaponProf.contains(check))
			offHand = weapon;
	}

	private void equipArmor(Armor.Instance armor) {
		EnumSet<Armor> armorProf = owner.getArmorProficiency();
		Armor check = armor.getArmor();

		if (bodyArmor == null && armorProf.contains(check))
			bodyArmor = armor;
		else if ((bodyArmor != null && bodyArmor.notCursed()) && armorProf.contains(check))
			bodyArmor = armor;
	}

	/*
	 * 
	 * 
	 */
	public boolean canUseArmor(Armor armor) {
		boolean canUse = false;
		if (owner.getArmorProficiency().contains(armor))
			canUse = true;

		return canUse;
	}

	public boolean wearingArmor() {
		return bodyArmor != null;
	}

	public boolean holdingMainHand() {
		return mainHand != null;
	}

	public boolean holdingOffHand() {
		return offHand != null;
	}

	public boolean usingShield() {
		return (offHand != null && offHand.isShield());
	}

	public boolean clearEquipment() {
		boolean cleared = true;

		cleared = (clearMainHand()) ? cleared : false;
		cleared = (clearOffHand()) ? cleared : false;
		cleared = (clearBodyArmor()) ? cleared : false;

		return cleared;
	}

	private boolean clearMainHand() {
		boolean cleared = true;

		if (mainHand != null && mainHand.isCursed())
			cleared = false;
		else
			mainHand = null;

		return cleared;
	}

	private boolean clearOffHand() {
		boolean cleared = true;

		if (offHand != null && offHand.isCursed())
			cleared = false;
		else
			offHand = null;

		return cleared;
	}

	private boolean clearBodyArmor() {
		boolean cleared = true;

		if (bodyArmor != null && bodyArmor.isCursed())
			cleared = false;
		else
			bodyArmor = null;

		return cleared;
	}

	public Weapon.Instance getMainHand() {
		return mainHand;
	}

	public Weapon.Instance getOffHand() {
		return offHand;
	}

	public int getBodyArmorBonus() {
		int ac, armor, dexMod = owner.getDexterityModifier(), maxDex;
		if (bodyArmor != null && bodyArmor.heavyArmor()) {
			armor = bodyArmor.getArmorClass();
			ac = armor;
		} else if (bodyArmor != null && bodyArmor.mediumArmor()) {
			armor = bodyArmor.getArmorClass();
			maxDex = bodyArmor.getMaxDexterity();
			ac = (dexMod > maxDex) ? armor + maxDex : armor + dexMod;
		} else if (bodyArmor != null && bodyArmor.lightArmor()) {
			armor = bodyArmor.getArmorClass();
			ac = armor + dexMod;
		} else {
			ac = 10 + dexMod;
		}

		return ac;
	}

	public int getShieldArmorBonus() {
		return offHand.getArmorBonus();
	}

	private static Set<Weapon.Instance> filterWeaponsForUseable(Player actor) {
		Set<Weapon.Instance> set, filteredList = new HashSet<Weapon.Instance>();
		set = actor.getInventory().getWeapons();

		EnumSet<Weapon> weaponProf = actor.getWeaponProficiency();
		Weapon.Instance instance;
		for (Iterator<Weapon.Instance> it = set.iterator(); it.hasNext();) {
			instance = it.next();
			if (weaponProf.contains(instance.getWeapon()))
				filteredList.add(instance);
		}

		return filteredList;
	}

	private static List<Armor.Instance> filterArmorForUseable(Player actor) {
		List<Armor.Instance> armorList, filteredList = new ArrayList<Armor.Instance>();
		armorList = actor.getInventory().getArmor();

		EnumSet<Armor> armorProf = actor.getArmorProficiency();
		Armor.Instance instance;
		for (Iterator<Armor.Instance> it = armorList.iterator(); it.hasNext();) {
			instance = it.next();
			if (armorProf.contains(instance.getArmor()))
				filteredList.add(instance);
		}

		return filteredList;
	}

	public String toStringDetailed() {
		String string = "";

		// weapon line
		string += weaponSet.toString();
		// armor line
		if (armorList.size() > 0)
			string += String.format("%n%s", armorList.toString());

		return string;
	}

	/*
	 * STARTING GEAR
	 * 
	 */
	private static void randomSimpleHelper(Weapon.WeaponSet list) {
		Weapon.Instance weapon = new Weapon.Instance(Weapon.randomSimpleWeapon());
		list.add(weapon);

		if (weapon.rangedOrThrown())
			list.add(Weapon.getAmmunition(weapon.getWeapon()));
	}

	private static void randomMartialHelper(Weapon.WeaponSet list) {
		Weapon.Instance weapon = new Weapon.Instance(Weapon.randomMartialWeapon());
		list.add(weapon);

		if (weapon.rangedOrThrown())
			list.add(Weapon.getAmmunition(weapon.getWeapon()));
	}

	public static void setupStartingGear(Player actor) {
		Inventory inventory = new Inventory(actor);

		// create lists
		Weapon.WeaponSet weaponList = new Weapon.WeaponSet(actor);
		List<Armor.Instance> armorList = new ArrayList<Armor.Instance>();

		//
		EnumSet<Armor> armorProf = actor.getArmorProficiency();
		EnumSet<Weapon> weaponProf = actor.getWeaponProficiency();

		int dice;
		Weapon.Instance weapon;

		Class job = actor.getJob();
		if (job.equals(Class.BARBARIAN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.GREATAXE));
			} else {
				// random martial melee
				weapon = new Weapon.Instance(Weapon.randomMartialMelee());
				weaponList.add(weapon);

			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
				weaponList.add(new Weapon.Instance(Weapon.HANDAXE));
			} else {
				randomSimpleHelper(weaponList);

			}

			// TODO - add explorer's pack

			// receive 4 javelins
			weaponList.add(new Weapon.Instance(Weapon.JAVELIN, 4));
		} else if (job.equals(Class.BARD)) {
			dice = Dice.roll(3);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.RAPIER));
			} else if (dice == 2) {
				weaponList.add(new Weapon.Instance(Weapon.LONGSWORD));
			} else {
				// random simple weapon
				randomSimpleHelper(weaponList);

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
				randomSimpleHelper(weaponList);

			}

			// TODO - add priest's or explorer's pack
			// TODO - receive shield + holy symbol
			weaponList.add(new Weapon.Instance(Weapon.SHIELD));
		} else if (job.equals(Class.DRUID)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				randomSimpleHelper(weaponList);

			} else {
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weaponList.add(new Weapon.Instance(Weapon.SCIMITAR));
			} else {
				weapon = new Weapon.Instance(Weapon.randomSimpleMelee());
				weaponList.add(weapon);
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
				randomMartialHelper(weaponList);
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));

			} else {
				randomSimpleHelper(weaponList);

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
				randomSimpleHelper(weaponList);

			}

			// TODO - add dungeoneer's or explorer's pack
			// TODO - receive 10 darts
			weaponList.add(new Weapon.Instance(Weapon.DART, 10));

		} else if (job.equals(Class.PALADIN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				randomMartialHelper(weaponList);
				weaponList.add(new Weapon.Instance(Weapon.SHIELD));

			} else {
				randomMartialHelper(weaponList);
				randomMartialHelper(weaponList);

			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 5 javelins
				weaponList.add(new Weapon.Instance(Weapon.JAVELIN, 5));
			} else {
				randomSimpleHelper(weaponList);

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
				randomSimpleHelper(weaponList);
				randomSimpleHelper(weaponList);

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
				randomSimpleHelper(weaponList);

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
				randomSimpleHelper(weaponList);

			}

			// TODO - component pouch or arcane focus
			// TODO - add dungeoneer's or scholar's pack
			armorList.add(new Armor.Instance(Armor.LEATHER_ARMOR));
			randomSimpleHelper(weaponList);
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

		// has to happen -AFTER- inventory assigned to character
		inventory.optimizeArmor();
		inventory.optimizeWeapons();
	}

	/*
	 * COMPARATOR
	 * 
	 */
}
