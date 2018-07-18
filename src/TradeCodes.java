import java.util.EnumSet;

public enum TradeCodes {
	AG, AS, BA, DE, FL, HI, IC, IN, LO, NA, NI, PO, RI, VA, WA;

	public static void setupTradeCodes(World world) {
		EnumSet<TradeCodes> set = EnumSet.noneOf(TradeCodes.class);

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
}
