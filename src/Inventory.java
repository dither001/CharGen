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
	// ArrayList<Gear> wondrous;

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

	public int calcArmorClass() {
		int totalAC = 10, armorClass = 10;
		Archetype job = owner.getJob();

		int dexMod = owner.getAbilities().getDEXMod();
		int conMod = owner.getAbilities().getCONMod();
		int wisMod = owner.getAbilities().getWISMod();
		int maxDex = 10;

		if (equippedArmor()) {
			armorClass = armor.getArmor().getArmorClass();
			maxDex = armor.getArmor().getMaxDexterity();
			if (dexMod > maxDex)
				dexMod = maxDex;
		}

		if (job.equals(Archetype.MONK)) {
			totalAC = 10 + dexMod + wisMod;
		} else if (job.equals(Archetype.BARBARIAN)) {
			if (equippedArmor()) {
				totalAC = maxDex > 0 ? armorClass + dexMod : armorClass;
			} else {
				totalAC = 10 + dexMod + conMod;
			}
		} else if (owner.getSkills().contains(Armor.MAGE)) {
			totalAC = 13 + dexMod;
		} else {
			if (equippedArmor()) {
				totalAC = maxDex > 0 ? armorClass + dexMod : armorClass;
			} else {
				totalAC = 10 + dexMod;
			}
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
			Archetype job = owner.getJob();
			int dexMod = owner.getAbilities().getDEXMod();
			int conMod = owner.getAbilities().getCONMod();

			if (armory.size() < 1)
				return;

			if (job.equals(Archetype.MONK)) {
				return;
			} else if (job.equals(Archetype.BARBARIAN)) {
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
			if (candidate.getArmor().equals(best))
				optimal = candidate;
		}

		return optimal;
	}

	public void equipArmor(GameArmor armor) {
		this.armor = armor;
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
		Archetype job = actor.getJob();
		AbilityArray abilities = actor.getAbilities();
		HashSet<Proficiency> skills = actor.getSkills();

		int dice;
		if (job.equals(Archetype.BARBARIAN)) {
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
		} else if (job.equals(Archetype.BARD)) {
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
		} else if (job.equals(Archetype.CLERIC)) {
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
		} else if (job.equals(Archetype.DRUID)) {
			// first choice
			dice = Dice.roll(2);
			if (true) {
				// TODO - 50/50 for weapon or wooden shield
				weapons.add(GameWeapon.randomSimpleWeapon());
			}

			// second choice
			dice = Dice.roll(2);
			if (dice == 1) {
				weapons.add(GameWeapon.getWeapon("Scimitar"));
			} else {
				weapons.add(GameWeapon.randomSimpleMelee());
			}

			// TODO - receive explorer's pack + druid focus
			armory.add(GameArmor.getArmor("Leather Armor"));
		} else if (job.equals(Archetype.FIGHTER)) {
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
				// TODO - wooden shield
				weapons.add(GameWeapon.randomMartialWeapon());
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
		} else if (job.equals(Archetype.MONK)) {
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
		} else if (job.equals(Archetype.PALADIN)) {
			// first choice
			dice = Dice.roll(2);
			if (dice == 1) {
				// TODO - wooden shield
				weapons.add(GameWeapon.randomMartialWeapon());
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
		} else if (job.equals(Archetype.RANGER)) {
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
		} else if (job.equals(Archetype.ROGUE)) {
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
		} else if (job.equals(Archetype.SORCERER)) {
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
		} else if (job.equals(Archetype.WARLOCK)) {
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
		} else if (job.equals(Archetype.WIZARD)) {
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
