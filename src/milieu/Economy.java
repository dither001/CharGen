package milieu;

import java.util.EnumSet;

import rules.Dice;

public class Economy {
	//
	private int resources;
	
	//
	private char spaceport;
	private int techLevel;
	private boolean agriculture, asteroid, barren, desert, fluidOceans, highPopulation, iceCapped, industrial,
			lowPopulation, nonAgricultural, nonIndustrial, poor, rich, vacuum, waterWorld;

	
	
	public Economy(World world) {
		EnumSet<TradeCodes> tradeCodes = world.getTradeCodes();
		spaceport = world.getSpaceport();
		techLevel = world.getTechLevel();

		// AG(0), AS(1), BA(2), DE(3), FL(4), HI(5), IC(6), IN(7), LO(8), NA(9), NI(10),
		// PO(11), RI(12), VA(13), WA(14);
		agriculture = (tradeCodes.contains(TradeCodes.AG)) ? true : false;
		asteroid = (tradeCodes.contains(TradeCodes.AS)) ? true : false;
		barren = (tradeCodes.contains(TradeCodes.BA)) ? true : false;
		desert = (tradeCodes.contains(TradeCodes.DE)) ? true : false;
		fluidOceans = (tradeCodes.contains(TradeCodes.FL)) ? true : false;
		highPopulation = (tradeCodes.contains(TradeCodes.HI)) ? true : false;
		iceCapped = (tradeCodes.contains(TradeCodes.IC)) ? true : false;
		industrial = (tradeCodes.contains(TradeCodes.IN)) ? true : false;
		lowPopulation = (tradeCodes.contains(TradeCodes.LO)) ? true : false;
		nonAgricultural = (tradeCodes.contains(TradeCodes.NA)) ? true : false;
		nonIndustrial = (tradeCodes.contains(TradeCodes.NI)) ? true : false;
		poor = (tradeCodes.contains(TradeCodes.PO)) ? true : false;
		rich = (tradeCodes.contains(TradeCodes.RI)) ? true : false;
		vacuum = (tradeCodes.contains(TradeCodes.VA)) ? true : false;
		waterWorld = (tradeCodes.contains(TradeCodes.WA)) ? true : false;

		// set resources
		if (asteroid || barren || poor)
			resources = Dice.roll(1, 6) - 1;
		else
			resources = Dice.roll(2, 6) - 2;

		// trade code factor
		if (agriculture)
			++resources;
		if (desert)
			--resources;
		if (fluidOceans)
			--resources;
		if (highPopulation)
			++resources;
		if (iceCapped)
			--resources;
		if (industrial)
			resources += 2;
		if (nonAgricultural)
			--resources;
		if (rich)
			++resources;
		if (vacuum)
			--resources;
		
		// spaceport factor
		if (spaceport == 'A')
			resources += 2;
		else if (resources == 'B')
			++resources;
		
		// tech level / resource worlds
		if (techLevel >= 8) {
			// TODO - count gas giants + planetoid belts
//			resources += resourceWorlds;
			
			// but don't count the main world if it's an asteroid
			if (asteroid)
				--resources;
		}
		
		// validation step
		if (resources < 0)
			resources = 0;
		if (resources > 15)
			resources = 15;
	}


}
