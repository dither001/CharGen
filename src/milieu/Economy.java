package milieu;

import java.util.EnumSet;

import rules.Dice;

public class Economy {
	/*
	 * STATIC FIELDS
	 */
	private static final int[][] TOTAL_DEMAND = { //
			{ 0, 0, 0, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7 }, //
			{ 0, 0, 0, 1, 2, 2, 3, 4, 5, 5, 6, 6, 8, 8, 9, 9 }, //
			{ 0, 0, 1, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 11 }, //
			{ 0, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 }, //
			{ 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }, //
			{ 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }, //
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, //
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, //
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, //
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, //
			{ 1, 2, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 }, //
			{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17 }, //
			{ 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18 }, //
			{ 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19 }, //
			{ 1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 17, 18, 19, 20 }, //
			{ 1, 2, 4, 5, 7, 8, 9, 11, 12, 13, 15, 16, 18, 19, 21, 22 } };

	private static double[] EXPORT_BENEFIT = { 0.2, 0.2, 0.3, 0.3, 0.4, 0.4, 0.4, 0.4, 0.5, 0.5, 0.6 };
	private static double[] IMPORT_BENEFIT = { 0.3, 0.3, 0.4, 0.4, 0.5, 0.5, 0.5, 0.5, 0.6, 0.6, 0.7 };

	private static double[] LABOR_BASE = { 0.0000001, 0.000001, 0.00001, 0.0001, 0.001, 0.01, 0.1, 1, 10, 100, 1000,
			10000, 100000, 1000000, 10000000 };

	//
	private int resources;
	private int labor;
	private int infrastructure;
	private int culture;

	//
	private double resourcesAvailable;
	private double tradeBenefit;
	private double grossWorldProduct;

	//
	private char starport;
	private int techLevel;
	private int population;

	private boolean agricultural, asteroid, barren, desert, fluidOceans, highPopulation, iceCapped, industrial,
			lowPopulation, nonAgricultural, nonIndustrial, poor, rich, vacuum, waterWorld;

	/*
	 * CONSTRUCTORS
	 */
	public Economy(World world, StarSystem starsystem) {
		EnumSet<TradeCode> tradeCodes = world.getTradeCodes();
		starport = world.getSpaceport();
		techLevel = world.getTechLevel();
		population = world.getPopulation();

		// AG(0), AS(1), BA(2), DE(3), FL(4), HI(5), IC(6), IN(7), LO(8), NA(9), NI(10),
		// PO(11), RI(12), VA(13), WA(14);
		agricultural = (tradeCodes.contains(TradeCode.AG)) ? true : false;
		asteroid = (tradeCodes.contains(TradeCode.AS)) ? true : false;
		barren = (tradeCodes.contains(TradeCode.BA)) ? true : false;
		desert = (tradeCodes.contains(TradeCode.DE)) ? true : false;
		fluidOceans = (tradeCodes.contains(TradeCode.FL)) ? true : false;
		highPopulation = (tradeCodes.contains(TradeCode.HI)) ? true : false;
		iceCapped = (tradeCodes.contains(TradeCode.IC)) ? true : false;
		industrial = (tradeCodes.contains(TradeCode.IN)) ? true : false;
		lowPopulation = (tradeCodes.contains(TradeCode.LO)) ? true : false;
		nonAgricultural = (tradeCodes.contains(TradeCode.NA)) ? true : false;
		nonIndustrial = (tradeCodes.contains(TradeCode.NI)) ? true : false;
		poor = (tradeCodes.contains(TradeCode.PO)) ? true : false;
		rich = (tradeCodes.contains(TradeCode.RI)) ? true : false;
		vacuum = (tradeCodes.contains(TradeCode.VA)) ? true : false;
		waterWorld = (tradeCodes.contains(TradeCode.WA)) ? true : false;

		/*
		 * RESOURCES
		 * 
		 */
		if (asteroid || barren || poor)
			resources = Dice.roll(1, 6) - 1;
		else
			resources = Dice.roll(2, 6) - 2;

		// trade factor
		if (agricultural)
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

		// starport factor
		if (starport == 'A')
			resources += 2;
		else if (resources == 'B')
			++resources;

		// tech level / resource worlds
		if (techLevel >= 8) {
			resources += starsystem.numAsteroids();
			resources += starsystem.numGasGiants();

			// don't count main world if it's an asteroid
			if (asteroid)
				--resources;
		}

		// validation step
		if (resources < 0)
			resources = 0;
		if (resources > 15)
			resources = 15;

		/*
		 * LABOR
		 * 
		 */
		labor = population - 1;

		if (labor < 0)
			labor = 0;

		/*
		 * INFRASTRUCTURE
		 * 
		 */
		if (!barren) {
			infrastructure = Dice.roll(2, 6) - 2;
		} else {
			int dice = Dice.roll(6);
			if (dice < 3)
				infrastructure = 0;
			else if (dice < 5)
				infrastructure = 1;
			else
				infrastructure = 2;
		}

		// trade factor
		if (asteroid)
			--infrastructure;
		if (highPopulation)
			++infrastructure;
		if (industrial)
			infrastructure += 2;
		if (lowPopulation)
			--infrastructure;
		if (nonIndustrial)
			infrastructure -= 2;
		if (poor)
			infrastructure -= 2;
		if (rich)
			infrastructure += 2;
		if (waterWorld)
			--infrastructure;

		// starport factor
		if (starport == 'A')
			infrastructure += 4;
		else if (starport == 'B')
			infrastructure += 3;
		else if (starport == 'C')
			infrastructure += 2;
		else if (starport == 'D')
			infrastructure += 1;

		// validation step
		if (infrastructure < 0)
			infrastructure = 0;

		/*
		 * CULTURE
		 * 
		 */
		if (!barren) {
			culture = Dice.roll(2, 6) - 2;
		} else {
			if (Dice.roll(6) < 5)
				infrastructure = 0;
			else
				infrastructure = 1;
		}

		// trade factor
		if (agricultural)
			--culture;
		if (asteroid)
			++culture;
		if (desert)
			++culture;
		if (fluidOceans)
			++culture;
		if (iceCapped)
			++culture;
		if (nonAgricultural)
			--culture;
		if (nonIndustrial)
			--culture;
		if (poor)
			++culture;
		if (rich)
			++culture;
		if (vacuum)
			++culture;

		// validation step
		if (culture < 0)
			culture = 0;

		// last steps
		this.calcPlanetaryDemand();
		this.calcGrossWorldProduct();
	}

	/*
	 * INSTANCE METHODS
	 */
	public double grossWorldProduct() {
		return grossWorldProduct;
	}

	/*
	 * PRIVATE METHODS
	 */
	private void calcPlanetaryDemand() {
		//
		int baseDemand = (population > 3) ? resources : population;
		int totalDemand = Dice.roll(2, 6);
		int deficitDemand, excessDemand;

		// population factor
		if (population == 0 || population == 1)
			totalDemand -= 3;
		else if (population == 2 || population == 3)
			totalDemand -= 2;
		else if (population == 4 || population == 5)
			totalDemand -= 1;
		else if (population == 7 || population == 8)
			totalDemand += 1;
		else if (population == 9 || population == 10)
			totalDemand += 2;
		else if (population > 10)
			totalDemand += 3;

		// culture factor
		if (culture == 0 || culture == 1)
			totalDemand -= 3;
		else if (culture == 2 || culture == 3)
			totalDemand -= 2;
		else if (culture == 4 || culture == 5)
			totalDemand -= 1;
		else if (culture == 8 || culture == 9 || culture == 10)
			totalDemand += 1;
		else if (culture == 11 || culture == 12 || culture == 13)
			totalDemand += 2;
		else if (culture > 13)
			totalDemand += 3;

		// validation step
		if (totalDemand < 0)
			totalDemand = 0;
		if (totalDemand > 15)
			totalDemand = 15;

		// determine total demand
		totalDemand = TOTAL_DEMAND[totalDemand][baseDemand];

		/*
		 * check resources against infrastructure
		 */
		if (infrastructure > resources) {
			if (totalDemand > resources) {
				deficitDemand = totalDemand - resources;
				resourcesAvailable = resources - deficitDemand;
				excessDemand = 0;
			} else {
				resourcesAvailable = resources;
				excessDemand = resources - totalDemand;
				deficitDemand = 0;
			}
		} else {
			if (totalDemand > resources) {
				resourcesAvailable = resources;
				deficitDemand = totalDemand - resources;
				excessDemand = 0;
			} else {
				resourcesAvailable = totalDemand;
				excessDemand = resources - totalDemand;
				deficitDemand = 0;
			}
		}

		// validation step
		if (resourcesAvailable < 0)
			resourcesAvailable = 0;
		infrastructure -= deficitDemand / 10;

		// Applies Excess/Deficit demand as import/export
		if (excessDemand > 0)
			tradeBenefit = excessDemand * IMPORT_BENEFIT[Dice.roll(2, 6) - 2];
		else if (deficitDemand > 0)
			tradeBenefit = deficitDemand * EXPORT_BENEFIT[Dice.roll(2, 6) - 2];

		// "Resource Trade Rules"
		resourcesAvailable += tradeBenefit;
	}

	public void calcGrossWorldProduct() {
		// resources exploitable
		double resourcesExploitable = 0;
		resourcesExploitable = techLevel * 0.1 * resourcesAvailable;

		// labor factor
		double laborFactor = LABOR_BASE[labor] * population;

		grossWorldProduct = (resourcesExploitable * laborFactor * infrastructure) / (culture + 1);
	}

	// //
	// private double resourcesAvailable;
	// private double tradeBenefit;
	// private double grossWorldProduct;

	public String toStringDetailed() {
		String s = String.format("Resources: %d || Labor: %d || Infrastructure: %d || Culture: %d", resources, labor,
				infrastructure, culture);
		String t = String.format("%nR/A: %.1f || Labor Base: %.3f || GWP: %.3f", resourcesAvailable, LABOR_BASE[labor],
				grossWorldProduct);

		return String.format("%s%s", s, t);
	}

}
