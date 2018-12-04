package milieu;

import java.util.EnumSet;

import com.norvendae.rules.misc.Dice;

public enum Base {
	NAVY, SCOUT, FARM, MINE, COLONY, LAB, MILITARY;

	// static fields
	public static final Base[] BASE_TYPES = { NAVY, SCOUT, FARM, MINE, COLONY, LAB, MILITARY };

	// instance methods
	public int typeIndex() {
		return getBaseIndex(this);
	}

	/*
	 * STATIC METHODS
	 */
	public static void setupFacilities(World world, World mainWorld) {
		EnumSet<Base> set = EnumSet.noneOf(Base.class);

		// if not a world, set empty set and return
		if (!WorldType.isWorld(world)) {
			world.setWorldFacilities(set);
			return;
		}

		int size = world.getSize();
		int atmo = world.getAtmosphere();
		int hydro = world.getHydrosphere();
		int pop = world.getPopulation();
		int gov = world.getGovernment();
		int law = world.getLawLevel();
		char starport = world.getSpaceport();

		int dice;
		if (world.mainWorld()) {
			// NAVAL BASE
			dice = Dice.roll(2, 6);
			if (starport != 'A' && starport != 'B')
				dice -= 12;

			if (dice > 7)
				set.add(Base.NAVY);

			// SCOUT BASE
			dice = Dice.roll(2, 6);

			if (starport == 'A')
				dice -= 3;
			else if (starport == 'B')
				dice -= 2;
			else if (starport == 'C')
				dice -= 1;
			else if (starport == 'E' || starport == 'X')
				dice -= 12;

			if (dice > 6)
				set.add(Base.SCOUT);

		} else {
			/*
			 * FARM
			 */
			boolean idealAtmo = false, idealHydro = false, idealPop = false;

			if (atmo >= 4 && atmo <= 9)
				idealAtmo = true;

			if (hydro >= 4 && hydro <= 8)
				idealHydro = true;

			if (pop >= 2)
				idealPop = true;

			if (idealAtmo && idealHydro && idealPop && world.habitable())
				set.add(Base.FARM);

			/*
			 * MINE
			 */
			boolean mainIndustrial = false;
			if (mainWorld.getTradeCodes() != null)
				mainIndustrial = mainWorld.getTradeCodes().contains(TradeCode.IN);

			idealPop = false;
			if (pop >= 2)
				idealPop = true;

			if (mainIndustrial && idealPop)
				set.add(Base.MINE);

			/*
			 * COLONY
			 */
			if (gov == 6 && pop >= 5)
				set.add(Base.COLONY);

			/*
			 * LABORATORY
			 */
			int mainTechLevel = mainWorld.getTechLevel();

			dice = Dice.roll(2, 6);
			if (mainWorld.getTechLevel() >= 10)
				dice += 2;
			else if (mainTechLevel <= 8)
				dice -= 12;

			if (dice >= 11) {
				set.add(Base.LAB);
				world.setTechLevel(mainTechLevel);
			}

			/*
			 * MILITARY
			 */
			boolean mainPoor = false;
			if (mainWorld.getTradeCodes() != null)
				mainPoor = mainWorld.getTradeCodes().contains(TradeCode.PO);

			dice = Dice.roll(2, 6);
			if (mainWorld.getPopulation() >= 8)
				dice += 1;

			if (atmo == mainWorld.getAtmosphere())
				dice += 2;

			if (mainPoor != true && dice >= 12)
				set.add(Base.MILITARY);

		}

		world.setWorldFacilities(set);
	}

	public static int getBaseIndex(Base base) {
		int index = 0;

		switch (base) {
		case NAVY:
			index = 0;
			break;
		case SCOUT:
			index = 1;
			break;
		case FARM:
			index = 2;
			break;
		case MINE:
			index = 3;
			break;
		case COLONY:
			index = 4;
			break;
		case LAB:
			index = 5;
			break;
		case MILITARY:
			index = 6;
			break;
		default:
			break;
		}

		return index;
	}

}
