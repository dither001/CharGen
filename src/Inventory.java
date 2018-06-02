import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Inventory {
	// fields
	private Actor owner;
	private Vector<GameArmor> armory;
	private Vector<GameWeapon> weapons;
	// private Vector<GameTool> tools;

	// Helmet brow;
	// Mask face;
	// Necklace neck;
	private GameArmor armor;
	private GameWeapon mainHand;
	private GameWeapon offHand;
	// Gloves gloves;
	// Bracers bracers;
	// Belt belt;
	// Boots boots;
	// Ring ring1, ring2, ring3;
	// ArrayList<> wondrous;

	// constructors
	public Inventory() {
		armory = new Vector<GameArmor>();
		weapons = new Vector<GameWeapon>();
		// tools = new Vector<GameTool>();

		clearEquipment();
	}

	// static methods

	// methods
	public boolean hasOwner() {
		return owner != null;
	}

	public boolean equippedArmor() {
		return armor != null;
	}

	public boolean equippedWeapon() {
		return mainHand != null;
	}

	public boolean equippedOffHand() {
		return offHand != null;
	}

	public boolean equippedShield() {
		return offHand != null && offHand.getBaseWeaponType().equals(Weapon.SHIELD);
	}

	public int calcArmorClass() {
		int totalAC = 10, armorClass = 10;
		Class job = owner.getJob();

		int dexMod = owner.getAbilities().getDEXMod();
		int conMod = owner.getAbilities().getCONMod();
		int wisMod = owner.getAbilities().getWISMod();
		int maxDex = 10;

		if (equippedArmor()) {
			armorClass = armor.getBaseArmorType().getArmorClass();
			maxDex = armor.getBaseArmorType().getMaxDexterity();
			if (dexMod > maxDex)
				dexMod = maxDex;
		}

		if (job.equals(Class.MONK)) {
			totalAC = 10 + dexMod + wisMod;
		} else if (job.equals(Class.BARBARIAN)) {
			if (equippedArmor()) {
				totalAC = maxDex > 0 ? armorClass + dexMod : armorClass;
			} else {
				totalAC = 10 + dexMod + conMod;
			}

			// TODO - update this when magical shields have bonuses greater than +2
			totalAC += (equippedShield()) ? 2 : 0;
		} else if (owner.getSkills().contains(Armor.MAGE)) {
			totalAC = 13 + dexMod;

			// TODO - update this when magical shields have bonuses greater than +2
			totalAC += (equippedShield()) ? 2 : 0;
		} else {
			if (equippedArmor()) {
				totalAC = maxDex > 0 ? armorClass + dexMod : armorClass;
			} else {
				totalAC = 10 + dexMod;
			}

			// TODO - update this when magical shields have bonuses greater than +2
			totalAC += (equippedShield()) ? 2 : 0;
		}

		return totalAC;
	}

	public void clearEquipment() {
		if (hasOwner()) {
			if (armor != null && armor.isCursed != true) {
				armor = null;
			} else if (mainHand != null && mainHand.isCursed != true) {
				armor = null;
			} else if (offHand != null && offHand.isCursed != true) {
				armor = null;
			}
		}
	}

	public void optimizeArmor() {
		if (hasOwner()) {
			Class job = owner.getJob();
			int dexMod = owner.getAbilities().getDEXMod();
			int conMod = owner.getAbilities().getCONMod();

			if (armory.size() < 1)
				return;

			if (job.equals(Class.MONK)) {
				return;
			} else if (job.equals(Class.BARBARIAN)) {
				if (dexMod > 2 && conMod > 0) {
					return;
				} else {
					armor = bestAvailableArmor(Armor.bestHeavyArmor());
				}
			} else if (owner.getSkills().contains(Armor.MAGE)) {
				return;
			} else {
				if (dexMod > 2) {
					armor = bestAvailableArmor(Armor.bestLightArmor());
				} else {
					armor = bestAvailableArmor(Armor.bestHeavyArmor());
				}

			}

			// END OF METHOD
		}
	}

	private GameArmor bestAvailableArmor(Armor best) {
		HashSet<Proficiency> skills = owner.getSkills();
		GameArmor bestArmor = null;

		// find the heaviest armor the character can use
		while (skills.contains(best) != true) {
			// System.out.println("Cannot use " + best);
			best = Armor.nextBestArmor(best);
		}

		// find the heaviest usable armor in inventory
		while (bestArmor == null) {
			// System.out.println("Can equip " + best);
			bestArmor = armorHelper(best);
			best = Armor.nextBestArmor(best);
		}

		equipArmor(bestArmor);
		// System.out.println("Equipped " + bestArmor);

		return bestArmor;
	}

	private GameArmor armorHelper(Armor best) {
		GameArmor candidate, optimal = null;
		for (Iterator<GameArmor> it = armory.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.getBaseArmorType().equals(best))
				optimal = candidate;
		}

		return optimal;
	}

	public int calcAverageDamage() {
		/*
		 * FIXME - a lot more work needs to go into this, for calculating DPS
		 */
		int averageDamage;

		averageDamage = (equippedWeapon()) ? mainHand.getBaseWeaponType().averageDamage() : 0;

		return averageDamage;
	}

	public void optimizeWeapon() {
		if (hasOwner()) {
			Weapon bestWeapon = Weapon.DEFAULT_WEAPON;

			Class job = owner.getJob();
			boolean prefersMelee = false;

			if (job.equals(Class.BARBARIAN) || job.equals(Class.MONK))
				prefersMelee = true;
			else if (armor != null && armor.getBaseArmorType().getMaxDexterity() <= 2)
				prefersMelee = true;

			if (weapons.size() < 1) {
				return;
			} else if (weapons.size() == 1) {
				if (canUseWeapon(weapons.get(0).getBaseWeaponType())) {
					equipMainHand(weapons.get(0));
					return;
				}
			}

			if (prefersMelee) {
				// first try melee; failing that try ranged weapon
				if (hasWeaponOfType(Weapon.SHIELD)) {
					bestWeapon = Weapon.bestOneHandedMelee(owner);
					if (bestWeapon.equals(Weapon.DEFAULT_WEAPON))
						bestWeapon = Weapon.bestOneHandedRanged(owner);

					if (bestWeapon.equals(Weapon.DEFAULT_WEAPON)) {
						bestWeapon = Weapon.bestMeleeWeapon(owner);
						equipMainHand(firstWeaponOfType(bestWeapon));
					} else {
						equipMainHand(firstWeaponOfType(bestWeapon));
						equipOffHand(firstWeaponOfType(Weapon.SHIELD));
					}
					return;
				}

				bestWeapon = Weapon.bestMeleeWeapon(owner);
				if (bestWeapon.equals(Weapon.DEFAULT_WEAPON))
					bestWeapon = Weapon.bestRangedWeapon(owner);
			} else {
				// first try ranged; failing that try melee weapon
				bestWeapon = Weapon.bestRangedWeapon(owner);

				if (bestWeapon.equals(Weapon.DEFAULT_WEAPON)) {
					bestWeapon = Weapon.bestMeleeWeapon(owner);
				}
			}

			equipMainHand(firstWeaponOfType(bestWeapon));
			if (hasWeaponOfType(Weapon.SHIELD))
				equipOffHand(firstWeaponOfType(Weapon.SHIELD));
			// END OF METHOD
		}
	}

	private GameWeapon firstWeaponOfType(Weapon type) {
		GameWeapon candidate, firstWeapon = null;
		for (Iterator<GameWeapon> it = weapons.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.getBaseWeaponType().equals(type))
				firstWeapon = candidate;
		}

		return firstWeapon;
	}

	public boolean hasArmor(Armor armor) {
		boolean hasArmor = false;

		GameArmor candidate;
		for (Iterator<GameArmor> it = armory.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.getBaseArmorType().equals(armor)) {
				hasArmor = true;
				break;
			}
		}

		return hasArmor;
	}

	public boolean canUseArmor(Armor armor) {
		boolean canUseArmor = false;
		HashSet<Proficiency> skills = owner.getSkills();

		if (skills.contains(armor)) {
			canUseArmor = true;
		}

		return canUseArmor;
	}

	public void equipArmor(GameArmor armor) {
		this.armor = armor;
	}

	public boolean hasWeaponOfType(Weapon weapon) {
		boolean hasWeapon = false;

		GameWeapon candidate;
		for (Iterator<GameWeapon> it = weapons.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.getBaseWeaponType().equals(weapon)) {
				hasWeapon = true;
				break;
			}
		}

		return hasWeapon;
	}

	public boolean canUseWeapon(Weapon weapon) {
		boolean canUseWeapon = false;
		HashSet<Proficiency> skills = owner.getSkills();

		if (skills.contains(weapon)) {
			canUseWeapon = true;
		}

		return canUseWeapon;
	}

	public void equipMainHand(GameWeapon weapon) {
		if (weapon == null) {
			System.out.println("Failed to equip main hand.");
			return;
		}

		if (weapon.getBaseWeaponType().requiresTwoHands())
			offHand = null;

		// System.out.println("Equipped " + weapon.toString());
		mainHand = weapon;
	}

	public void equipOffHand(GameWeapon weapon) {
		if (mainHand != null && mainHand.getBaseWeaponType().requiresTwoHands()) {
			System.out.println("Failed to equip offhand.");
			return;
		} else
			offHand = weapon;
	}

	public Vector<GameArmor> getAllArmor() {
		return armory;
	}

	public Vector<GameWeapon> getAllWeapons() {
		return weapons;
	}

	// public Vector<Tool> getTools() {
	// return tools;
	// }

	//

	public void addArmor(GameArmor armor) {
		armory.add(armor);
	}

	public void addAllArmor(Collection<GameArmor> armor) {
		armory.addAll(armor);
	}

	public void addWeapon(GameWeapon weapon) {
		weapons.add(weapon);
	}

	public void addAllWeapons(Collection<GameWeapon> weapon) {
		weapons.addAll(weapon);
	}

	// public void addTool(Tool tool) {
	// tools.add(tool);
	// }
	//
	// public void addAllTools(Collection<Tool> tool) {
	// tools.addAll(tool);
	// }

	public GameWeapon getMainHand() {
		return mainHand;
	}

	public GameWeapon getOffHand() {
		return offHand;
	}

	@Override
	public String toString() {
		LinkedList<GameItem> list = new LinkedList<GameItem>();

		for (Iterator<GameArmor> it = armory.iterator(); it.hasNext();) {
			list.add(it.next());
		}

		for (Iterator<GameWeapon> it = weapons.iterator(); it.hasNext();) {
			list.add(it.next());
		}

		return list.toString();
	}

	/*
	 * STARTING GEAR
	 * 
	 * 
	 */

	public void startingGear(Actor actor) {
		owner = actor;
		Class job = actor.getJob();
		AbilityArray abilities = actor.getAbilities();
		HashSet<Proficiency> skills = actor.getSkills();

		int dice;
		if (job.equals(Class.BARBARIAN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Greataxe"));
			} else {
				weapons.add(GameWeapon.randomMartialMelee());
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Handaxe"));
				weapons.add(GameWeapon.getWeapon("Handaxe"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - add explorer's pack

			// receive 4 javelins
			weapons.add(GameWeapon.getWeapon("Javelin"));
			weapons.add(GameWeapon.getWeapon("Javelin"));
			weapons.add(GameWeapon.getWeapon("Javelin"));
			weapons.add(GameWeapon.getWeapon("Javelin"));
		} else if (job.equals(Class.BARD)) {
			dice = Dice.roll(3);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Rapier"));
			} else if (dice == 2) {
				weapons.add(GameWeapon.getWeapon("Longsword"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - add diplomat's or entertainer's pack
			// TODO - add lute or any instrument

			// receive leather armor + dagger
			armory.add(GameArmor.getArmor("Leather Armor"));
			weapons.add(GameWeapon.getWeapon("Dagger"));
		} else if (job.equals(Class.CLERIC)) {
			int strength = abilities.getSTR();
			int dexterity = abilities.getDEX();

			// first choice
			if (skills.contains(Weapon.WARHAMMER)) {
				weapons.add(GameWeapon.getWeapon("Warhammer"));
			} else {
				weapons.add(GameWeapon.getWeapon("Mace"));
			}

			// second choice
			if (dexterity > 15) {
				armory.add(GameArmor.getArmor("Leather Armor"));
			} else if (skills.contains(Armor.CHAIN_MAIL) && strength > 12) {
				armory.add(GameArmor.getArmor("Chain Mail"));
			} else {
				armory.add(GameArmor.getArmor("Scale Mail"));
			}

			// third choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 bolts
				weapons.add(GameWeapon.getWeapon("Light Crossbow"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - add priest's or explorer's pack
			// TODO - receive shield + holy symbol
			weapons.add(GameWeapon.getWeapon("Shield"));
		} else if (job.equals(Class.DRUID)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.randomDruidSimple());
			} else {
				weapons.add(GameWeapon.getWeapon("Shield"));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Scimitar"));
			} else {
				weapons.add(GameWeapon.randomDruidMelee());
			}

			// TODO - receive explorer's pack + druid focus
			armory.add(GameArmor.getArmor("Leather Armor"));
		} else if (job.equals(Class.FIGHTER)) {
			// first choice
			int strength = abilities.getSTR();
			int dexterity = abilities.getDEX();
			if (strength < 13 || dexterity > 15) {
				// TODO - receive 20 arrows
				armory.add(GameArmor.getArmor("Leather Armor"));
				weapons.add(GameWeapon.getWeapon("Longbow"));
			} else {
				armory.add(GameArmor.getArmor("Chain Mail"));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.randomMartialWeapon());
				weapons.add(GameWeapon.getWeapon("Shield"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// third choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 bolts
				weapons.add(GameWeapon.getWeapon("Light Crossbow"));
			} else {
				weapons.add(GameWeapon.getWeapon("Handaxe"));
				weapons.add(GameWeapon.getWeapon("Handaxe"));
			}

			// TODO - add dungeoneer's or explorer's pack
		} else if (job.equals(Class.MONK)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Shortsword"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - add dungeoneer's or explorer's pack
			// TODO - receive 10 darts
			weapons.add(GameWeapon.getWeapon("Dart"));
		} else if (job.equals(Class.PALADIN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.randomMartialWeapon());
				weapons.add(GameWeapon.getWeapon("Shield"));
			} else {
				weapons.add(GameWeapon.randomMartialWeapon());
				weapons.add(GameWeapon.randomMartialWeapon());
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 5 javelins
				weapons.add(GameWeapon.getWeapon("Javelin"));
			} else {
				weapons.add(GameWeapon.randomSimpleMelee());
			}

			// TODO - add priest's or explorer's pack
			// TODO - receive holy symbol
			armory.add(GameArmor.getArmor("Chain Mail"));
		} else if (job.equals(Class.RANGER)) {
			// first choice
			int dexterity = abilities.getDEX();
			if (dexterity > 15) {
				armory.add(GameArmor.getArmor("Leather Armor"));
			} else {
				armory.add(GameArmor.getArmor("Scale Mail"));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Shortsword"));
				weapons.add(GameWeapon.getWeapon("Shortsword"));
			} else {
				weapons.add(GameWeapon.randomSimpleMelee());
				weapons.add(GameWeapon.randomSimpleMelee());
			}

			// third choice
			// TODO - add dungeoneer's or explorer's pack

			// TODO - receive longbow + 20 arrows
			weapons.add(GameWeapon.getWeapon("Longbow"));
		} else if (job.equals(Class.ROGUE)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Rapier"));
			} else {
				weapons.add(GameWeapon.getWeapon("Shortsword"));
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receive 20 arrows
				weapons.add(GameWeapon.getWeapon("Shortbow"));
			} else {
				weapons.add(GameWeapon.getWeapon("Shortsword"));
			}

			// TODO - add burglar's or dungeoneer's or explorer's pack
			// TODO - receive thieves' tool
			armory.add(GameArmor.getArmor("Leather Armor"));
			weapons.add(GameWeapon.getWeapon("Dagger"));
			weapons.add(GameWeapon.getWeapon("Dagger"));
		} else if (job.equals(Class.SORCERER)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receives 20 bolts
				weapons.add(GameWeapon.getWeapon("Light Crossbow"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - component pouch or arcane focus
			// TODO - add dungeoneer's or explorer's pack
			weapons.add(GameWeapon.getWeapon("Dagger"));
			weapons.add(GameWeapon.getWeapon("Dagger"));
		} else if (job.equals(Class.WARLOCK)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - receives 20 bolts
				weapons.add(GameWeapon.getWeapon("Light Crossbow"));
			} else {
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// TODO - component pouch or arcane focus
			// TODO - add dungeoneer's or scholar's pack
			armory.add(GameArmor.getArmor("Leather Armor"));
			weapons.add(GameWeapon.randomSimpleWeapon());
			weapons.add(GameWeapon.getWeapon("Dagger"));
			weapons.add(GameWeapon.getWeapon("Dagger"));
		} else if (job.equals(Class.WIZARD)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Quarterstaff"));
			} else {
				weapons.add(GameWeapon.getWeapon("Dagger"));
			}

			// TODO - component pouch or arcane focus
			// TODO - add scholar's or explorer's pack
			// TODO - receive spellbook
		}
	}

}
