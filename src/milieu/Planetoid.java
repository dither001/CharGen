package milieu;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import milieu.Base;
import milieu.TradeCode;
import rules.Dice;

public class Planetoid implements World {
	/*
	 * PERSISTENT FIELDS
	 */
	private int index;
	private boolean isPersistent;
	private boolean hasChanged;

	//
	private int starSystem;
	private int orbit;
	private int suborbit;

	private boolean isMainWorld;
	private String name;
	private WorldType type;
	private boolean habitable;
	//
	private int[] scores;
	private char spaceport;
	private int techLevel;
	//
	private EnumSet<Base> worldFacilities;
	private EnumSet<WorldTag> worldTags;

	// convenience
	// private StarSystem starSystem;
	private Planetoid parent;
	private Set<Planetoid> moons;
	private int totalMoons;
	private EnumSet<TradeCode> tradeCodes;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Planetoid(int orbit, Star homeStar) {
		this(orbit, homeStar, WorldType.STANDARD);
	}

	public Planetoid(int orbit, Star homeStar, WorldType type) {
		this(orbit, homeStar, null, type);
	}

	public Planetoid(int orbit, Star homeStar, Planetoid home, WorldType type) {
		// this.cluster = cluster;

		this.parent = home;
		this.type = type;
		this.orbit = orbit;
		this.suborbit = orbit;
		this.name = type.toString();

		/*
		 * INITIALIZE NEW WORLD
		 */
		if (isWorld()) {
			int habitableZone = Star.habitableZone(homeStar);
			this.habitable = this.orbit == habitableZone;

			this.scores = new int[] { 0, 0, 0, 0, 0, 0 };

			/*
			 * SIZE
			 */
			scores[0] = (Dice.roll(2, 6) - 2);
			if (orbit == 0)
				scores[0] -= 5;
			else if (orbit == 1)
				scores[0] -= 4;
			else if (orbit == 2)
				scores[0] -= 2;

			if (homeStar.color == 'M')
				scores[0] -= 2;

			if (isMoon() && home.largeGiant())
				scores[0] = (Dice.roll(2, 6) - 4);
			else if (isMoon() && home.smallGiant())
				scores[0] = (Dice.roll(2, 6) - 6);
			else if (isMoon())
				scores[0] = (home.getSize() - Dice.roll(6));

			// validation step
			if (type.equals(WorldType.SATELLITE) && scores[0] == 0) {
				this.type = WorldType.RING;
				name = this.type.toString();
			}

			if (isAsteroid())
				scores[0] = 0;
			else if (notRing() && scores[0] < 1)
				scores[0] = 1;

			/*
			 * ATMOSPHERE
			 */
			scores[1] = (Dice.roll(2, 6) - 7 + scores[0]);
			if (isMoon() && orbit < habitableZone)
				scores[1] -= 4;
			else if (orbit < habitableZone)
				scores[1] -= 2;
			else if (orbit > habitableZone)
				scores[1] -= 4;

			if (isMoon() && scores[0] < 2)
				scores[1] = 0;
			else if (scores[0] == 0)
				scores[1] = 0;

			if (orbit > habitableZone + 1 && Dice.roll(2, 6) == 12)
				scores[1] = 10;

			// validation step
			if (scores[1] < 0)
				scores[1] = 0;

			/*
			 * HYDROGRAPHY
			 */
			scores[2] = (Dice.roll(2, 6) - 7 + scores[0]);
			if (orbit < habitableZone || scores[0] < 2)
				scores[2] = 0;
			else if (isMoon() && orbit > habitableZone)
				scores[2] -= 4;
			else if (orbit > habitableZone)
				scores[2] -= 2;

			if (scores[1] < 2 || scores[1] > 9)
				scores[2] -= 4;

			// validation step
			if (scores[2] < 0)
				scores[2] = 0;

			/*
			 * POPULATION
			 */
			scores[3] = (Dice.roll(2, 6) - 2);
			if (orbit < habitableZone)
				scores[3] -= 5;
			else if (orbit > habitableZone)
				scores[3] -= 3;

			if (scores[1] != 0 && scores[1] != 5 && scores[1] != 6 && scores[1] != 8)
				scores[3] -= 2;

			// validation step
			if (isRing() || scores[3] < 0)
				scores[3] = 0;

		}

		/*
		 * SATELLITES
		 */
		totalMoons = 0;
		if (type.equals(WorldType.SATELLITE) || type.equals(WorldType.ASTEROID)) {
			moons = new HashSet<Planetoid>();

		} else {
			moons = new HashSet<Planetoid>();

			int satellites = 0;
			if (type.equals(WorldType.LARGE_GIANT))
				satellites = Dice.roll(2, 6);
			else if (type.equals(WorldType.SMALL_GIANT))
				satellites = Dice.roll(2, 6) - 4;
			else if (isWorld() && scores[0] > 0)
				satellites = Dice.roll(1, 6) - 3;

			// validation step
			if (satellites < 0)
				satellites = 0;

			while (satellites > 0) {
				moons.add(new Planetoid(orbit, homeStar, this, WorldType.SATELLITE));
				--satellites;

			}

			int dice;
			Planetoid moon;
			Set<Integer> orbits = new HashSet<Integer>();
			for (Iterator<Planetoid> it = moons.iterator(); it.hasNext();) {
				moon = it.next();

				if (moon.isRing()) {
					dice = Dice.roll(6);
					if (dice < 4 && orbits.contains(1) != true)
						orbits.add(moon.suborbit = 1);
					else if ((dice == 4 || dice == 5) && orbits.contains(2) != true)
						orbits.add(moon.suborbit = 2);
					else if ((dice == 6) && orbits.contains(3) != true)
						orbits.add(moon.suborbit = 3);
					else {
						int i = 1;
						while (orbits.contains(i)) {
							++i;
						}

						orbits.add(moon.suborbit = i);
					}

				} else {
					dice = Dice.roll(2, 6);

					if (dice < 8) {
						dice = Dice.roll(2, 6) + 1;
						while (orbits.contains(dice)) {
							dice = Dice.roll(2, 6) + 1;
						}

						orbits.add(moon.suborbit = dice);
					} else {
						dice = (Dice.roll(2, 6) + 1) * 5;
						while (orbits.contains(dice)) {
							dice = (Dice.roll(2, 6) + 1) * 5;
						}

						orbits.add(moon.suborbit = dice);
					}

					++totalMoons;
				}

			}

		}

	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void governmentSetup(World mainWorld) {
		if (mainWorld.equals(this))
			this.isMainWorld = true;

		int size = getSize();
		int atmo = getAtmosphere();
		int hydro = getHydrosphere();
		int pop = getPopulation();

		int dice;

		/*
		 * GOVERNMENT, LAW LEVEL
		 */
		int gov, law;
		if (mainWorld()) {
			/*
			 * GOVERNMENT
			 */
			dice = Dice.roll(2, 6) - 7 + pop;

			// validation step
			dice = (dice < 0) ? 0 : (dice > 14) ? 14 : dice;

			gov = dice;

			/*
			 * LAW LEVEL
			 */
			dice = Dice.roll(2, 6) - 7 + gov;

			// validation step
			if (dice < 0)
				dice = 0;

			law = dice;
		} else {
			int mainGov = mainWorld.getGovernment(), mainLaw = mainWorld.getLawLevel();

			/*
			 * GOVERNMENT
			 */
			gov = Dice.roll(6);

			if (mainGov == 6)
				gov += pop;
			else if (mainGov >= 7)
				gov += 1;

			if (gov == 1)
				gov = 0;
			else if (gov == 2)
				gov = 1;
			else if (gov == 3)
				gov = 2;
			else if (gov == 4)
				gov = 3;
			else if (gov >= 5)
				gov = 6;

			// validation step
			if (pop == 0)
				gov = 0;

			/*
			 * LAW LEVEL
			 */
			law = Dice.roll(6) - 3 + mainLaw;

			// validation step
			if (gov == 0 || law < 0)
				law = 0;

		}

		setGovernment(gov);
		setLawLevel(law);

		/*
		 * STARPORT / SPACEPORT
		 */
		char starport = 'X';
		if (mainWorld()) {
			dice = Dice.roll(2, 6);

			if (dice >= 10)
				starport = 'A';
			else if (dice == 8 || dice == 9)
				starport = 'B';
			else if (dice == 6 || dice == 7)
				starport = 'C';
			else if (dice == 5)
				starport = 'D';
			else if (dice == 3 || dice == 4)
				starport = 'E';
			else
				starport = 'X';

			this.spaceport = starport;
			// setSpaceport(starport);
		} else {
			dice = Dice.roll(6);

			if (pop >= 6)
				dice += 2;
			else if (pop == 1)
				dice -= 2;
			else if (pop == 0)
				dice -= 3;

			if (dice >= 6)
				starport = 'F';
			else if (dice == 4 || dice == 5)
				starport = 'G';
			else if (dice == 3)
				starport = 'H';
			else
				starport = 'Y';

			this.spaceport = starport;
			// setSpaceport(starport);
		}

		/*
		 * TECH LEVEL
		 */
		int techLevel;
		if (mainWorld()) {
			techLevel = Dice.roll(6);

			// starport bonus
			if (starport == 'A')
				techLevel += 6;
			else if (starport == 'B')
				techLevel += 4;
			else if (starport == 'C')
				techLevel += 2;
			else if (starport == 'X')
				techLevel -= 4;

			// size bonus
			if (size == 0 || size == 1)
				techLevel += 2;
			else if (size == 2 || size == 3 || size == 4)
				techLevel += 1;

			// atmosphere bonus
			if (atmo >= 0 && atmo <= 3)
				techLevel += 1;
			else if (atmo >= 10 && atmo <= 14)
				techLevel += 1;

			// hydrosphere bonus
			if (hydro == 9)
				techLevel += 1;
			else if (hydro == 10)
				techLevel += 2;

			// population bonus
			if (pop >= 1 && pop <= 5)
				techLevel += 1;
			else if (pop == 9)
				techLevel += 2;
			else if (pop == 10)
				techLevel += 4;

			// government bonus
			if (gov == 0 || gov == 5)
				techLevel += 1;
			else if (gov == 13)
				techLevel -= 2;

			setTechLevel(techLevel);
		} else {
			int mainTechLevel = mainWorld.getTechLevel();
			setTechLevel(mainTechLevel - 1);

		}

		/*
		 * TRADE CODES
		 */
		TradeCode.setupTradeCodes(this);

		/*
		 * WORLD FACILITIES
		 */
		Base.setupFacilities(this, mainWorld);
//		EnumSet<Base> facilities = EnumSet.noneOf(Base.class);

		/*
		 * FIXME - WORLD TAGS
		 */
		WorldTag.setupWorldTags(this);

	}

	public String nameString() {
		String string;

		string = String.format("%s", (notAsteroid()) ? name : "The " + name + " belt");
		string = String.format("%s (%s)", string, (isMoon() || isRing()) ? orbit + "." + suborbit : orbit);

		return string;
	}

	public String scoreString() {
		String string;
		//
		int size = scores[0], atmo = scores[1];
		int hydro = scores[2], pop = scores[3];
		int gov = scores[4], law = scores[5];

		string = String.format("[%2d,%2d,%2d, %2d,%2d,%2d]", size, atmo, hydro, pop, gov, law);

		return string;
	}

	@Override
	public String toString() {
		String string = nameString();

		if (isWorld()) {
			if (type.equals(WorldType.SATELLITE))
				string = String.format("%-28s %s", "* " + string, scoreString());
			else
				string = String.format("%-28s %s", string, scoreString());

		} else if (isRing()) {

			string = String.format("%-32s", "* " + string);
		}

		// facilities
		String facilities = (getWorldFacilities() != null) ? String.format(" %s", getWorldFacilities()) : "";
		// world tags
		String tags = (worldTags != null) ? String.format(" %s", worldTags) : "";
		string += String.format("%-24s %s", facilities, tags);

		return string;
	}

	public String toStringDetailed() {
		String string = nameString();

		if (isWorld()) {
			if (type.equals(WorldType.SATELLITE))
				string = String.format("%-28s %s", "* " + string, scoreString());
			else
				string = String.format("%-28s %s", string, scoreString());
		} else if (isRing()) {

			string = String.format("%-32s", "* " + string);
		}

		// facilities
		String facilities = (getWorldFacilities() != null) ? String.format(" %s", getWorldFacilities()) : "";
		// world tags
		String tags = (worldTags != null) ? String.format(" %s", worldTags) : "";
		string += String.format("%-24s %s", facilities, tags);

		if (hasMoons()) {
			List<Planetoid> moonList = Dice.setToList(moons);
			World.SubOrbitAscending moonSort = new World.SubOrbitAscending();
			Collections.sort(moonList, moonSort);

			String satellites = "";
			for (Planetoid el : moonList) {
				satellites += String.format("%n%s", el);

			}

			string += satellites;
			// } else if (notMoon() && (isWorld() || isGasGiant())) {
			// string += " (no natural satellites)";
		}

		return string;
	}

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	public static class MainWorldSort implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			// first sort by population
			int compare = right.getPopulation() - left.getPopulation();

			// next try habitable zone
			if (compare == 0) {
				if (left.orbit == right.orbit)
					compare = 0;
				else if (left.habitable())
					compare = -1;
				else if (right.habitable())
					compare = 1;

			}

			// then try proximity to primary star
			if (compare == 0)
				compare = left.orbit - right.orbit;

			return compare;
		}

	}

	/*
	 * FIXME - INTERFACE MATHODS (move later)
	 * 
	 */
	public int starsystem() {
		return starSystem;
	}
	
	public int orbit() {
		return orbit;
	}

	public int suborbit() {
		return suborbit;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public boolean isPersistent() {
		return isPersistent;
	}

	@Override
	public boolean habitable() {
		return habitable;
	}

	@Override
	public boolean mainWorld() {
		return isMainWorld;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public EnumSet<TradeCode> getTradeCodes() {
		return tradeCodes;
	}

	@Override
	public void setTradeCodes(EnumSet<TradeCode> set) {
		this.tradeCodes = set;
	}

	@Override
	public EnumSet<WorldTag> getWorldTags() {
		return worldTags;
	}

	@Override
	public void setWorldTags(EnumSet<WorldTag> set) {
		this.worldTags = set;
	}

	@Override
	public EnumSet<Base> getWorldFacilities() {
		return worldFacilities;
	}

	@Override
	public void setWorldFacilities(EnumSet<Base> set) {
		this.worldFacilities = set;
	}

	@Override
	public WorldType getType() {
		return type;
	}

	@Override
	public char getSpaceport() {
		return spaceport;
	}

	@Override
	public void setSpaceport(char c) {
		this.spaceport = c;
	}

	@Override
	public int getTechLevel() {
		return techLevel;
	}

	@Override
	public void setTechLevel(int techLevel) {
		this.techLevel = techLevel;
	}

	@Override
	public int[] getAttributes() {
		return scores;
	}

	@Override
	public void setAttribute(int index, int value) {
		this.scores[index] = value;
	}

	@Override
	public Planetoid getParent() {
		return parent;
	}

	@Override
	public Set<Planetoid> getMoons() {
		return moons;
	}

	@Override
	public int totalMoons() {
		return totalMoons;
	}

}
