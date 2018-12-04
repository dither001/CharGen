package milieu;

import java.util.EnumSet;
import java.util.Set;

import com.norvendae.rules.misc.Dice;

public enum TradeCode {
	AG, AS, BA, DE, FL, HI, IC, IN, LO, NA, NI, PO, RI, VA, WA;

	/*
	 * STATIC FIELDS
	 */
	public static final TradeCode[] TRADE_CODES = { AG, AS, BA, DE, FL, HI, IC, IN, LO, NA, NI, PO, RI, VA, WA };

	/*
	 * STATIC METHODS
	 */
	public int codeIndex() {
		return codeIndex(this);
	}

	public static void setupTradeCodes(World world) {
		EnumSet<TradeCode> set = EnumSet.noneOf(TradeCode.class);

		// if not a world, set empty set and return
		if (!WorldType.isWorld(world)) {
			world.setTradeCodes(set);
			return;
		}

		int size = world.getSize();
		int atmo = world.getAtmosphere();
		int hydro = world.getHydrosphere();
		int pop = world.getPopulation();
		int gov = world.getGovernment();
		int law = world.getLawLevel();

		/*
		 * AGRICULTURE (AG)
		 */
		boolean idealAtmo = false;
		if (atmo >= 4 && atmo <= 9)
			idealAtmo = true;

		boolean idealHydro = false;
		if (hydro >= 4 && hydro <= 8)
			idealHydro = true;

		boolean idealPop = false;
		if (pop >= 5 && pop <= 7)
			idealPop = true;

		if (idealAtmo && idealHydro && idealPop)
			set.add(AG);

		/*
		 * ASTEROID (AS)
		 */
		if (size == 0 && atmo == 0 && hydro == 0)
			set.add(AS);

		/*
		 * BARREN (BA)
		 */
		if (pop == 0 && gov == 0 && law == 0)
			set.add(BA);

		/*
		 * DESERT (DE)
		 */
		if (atmo >= 2 && hydro == 0)
			set.add(DE);

		/*
		 * FLUID OCEANS (FL)
		 */
		if (atmo >= 10 && hydro >= 1)
			set.add(FL);

		/*
		 * HIGH POPULATION (HI)
		 */
		if (pop >= 9)
			set.add(HI);

		/*
		 * ICE-CAPPED (IC)
		 */
		if (atmo <= 1 && hydro >= 1)
			set.add(IC);

		/*
		 * INDUSTRIAL (IN)
		 */
		idealAtmo = false;
		if (atmo <= 2 || atmo == 4 || atmo == 7 || atmo == 9)
			idealAtmo = true;

		if (idealAtmo && pop >= 9)
			set.add(IN);

		/*
		 * LOW POPULATION (LO)
		 */
		if (pop <= 3)
			set.add(LO);

		/*
		 * NON-AGRICULTURAL (NA)
		 */
		idealAtmo = false;
		if (atmo <= 3)
			idealAtmo = true;

		idealHydro = false;
		if (hydro <= 3)
			idealHydro = true;

		idealPop = false;
		if (pop >= 6)
			idealPop = true;

		if (idealAtmo && idealHydro && idealPop)
			set.add(NA);

		/*
		 * POOR WORLD (PO)
		 */
		idealAtmo = false;
		if (atmo >= 2 && atmo <= 5)
			idealAtmo = true;

		idealHydro = false;
		if (hydro <= 3)
			idealHydro = true;

		if (idealAtmo && idealHydro)
			set.add(PO);

		/*
		 * RICH WORLD (RI)
		 */
		idealAtmo = false;
		if (atmo == 6 || atmo == 8)
			idealAtmo = true;

		idealPop = false;
		if (pop == 6 || pop == 7 || pop == 8)
			idealPop = true;

		boolean idealGov = false;
		if (gov >= 4 && gov <= 9)
			idealGov = true;

		if (idealAtmo && idealPop && idealGov)
			set.add(RI);

		/*
		 * VACUUM WORLD (VA)
		 */
		if (atmo == 0)
			set.add(VA);

		/*
		 * WATER WORLD (WA)
		 */
		if (hydro == 10)
			set.add(WA);

		world.setTradeCodes(set);
	}

	public static int codeIndex(TradeCode code) {
		int index = 0;

		switch (code) {
		case AG:
			index = 0;
			break;
		case AS:
			index = 1;
			break;
		case BA:
			index = 2;
			break;
		case DE:
			index = 3;
			break;
		case FL:
			index = 4;
			break;
		case HI:
			index = 5;
			break;
		case IC:
			index = 6;
			break;
		case IN:
			index = 7;
			break;
		case LO:
			index = 8;
			break;
		case NA:
			index = 9;
			break;
		case NI:
			index = 10;
			break;
		case PO:
			index = 11;
			break;
		case RI:
			index = 12;
			break;
		case VA:
			index = 13;
			break;
		case WA:
			index = 14;
			break;
		default:
			break;

		}

		return index;
	}

	public static String codeName(TradeCode code) {
		String string = "";

		switch (code) {
		case AG:
			string = "Agricultural";
			break;
		case AS:
			string = "Asteroid";
			break;
		case BA:
			string = "Barren";
			break;
		case DE:
			string = "Desert";
			break;
		case FL:
			string = "Fluid Oceans";
			break;
		case HI:
			string = "High Population";
			break;
		case IC:
			string = "Ice-Capped";
			break;
		case IN:
			string = "Industrial";
			break;
		case LO:
			string = "Low Population";
			break;
		case NA:
			string = "Non-Agricultural";
			break;
		case NI:
			string = "Non-Industrial";
			break;
		case PO:
			string = "Poor";
			break;
		case RI:
			string = "Rich";
			break;
		case VA:
			string = "Vacuum";
			break;
		case WA:
			string = "Watery";
			break;
		default:
			break;

		}

		return string;
	}
}