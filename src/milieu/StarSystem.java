package milieu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import rules.Dice;

public class StarSystem {
	private static int FAR_ORBIT = 32;

	private static final String[] POPULATIONS = { "Scattered or no inhabitants", "Dozens of inhabitants",
			"Hundreds of inhabitants", "Thousands of inhabitants", "Tens of thousands", "Hundreds of thousands",
			"Millions of inhabitants", "Tens of millions", "Hundreds of millions", "Billions of inhabitants",
			"Tens of billions" };

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	protected int index;
	protected boolean isPersistent;
	protected boolean hasChanged;

	private List<Star> stars;

	private int maxOrbits;
	private List<Planetoid> planets;
	// private List<Planetoid> spaceObjects;
	private int namedObjects;
	private int populousWorlds;

	/*
	 * CONVENIENCE
	 */
	private Planetoid mainWorld;
	private Set<WorldTag> commonTags;
	private Economy economy;

	/*
	 * CONSTRUCTOR
	 * 
	 */
	public StarSystem(int sector, int subsector, int starSystem) {
		namedObjects = 0;
		int dice;

		/*
		 * STAR GENERATION
		 * 
		 */
		stars = new ArrayList<Star>();
		int numberOfStars;
		dice = Dice.roll(2, 6);
		if (dice < 8)
			numberOfStars = 1;
		else if (dice < 12)
			numberOfStars = 2;
		else
			numberOfStars = 3;

		// primary star
		Star primary = new Star(sector, subsector, starSystem, 0);
		stars.add(primary);
		++namedObjects;

		// companion star(s)
		if (numberOfStars > 1) {
			// determine orbit
			int orbit = 0;

			for (int i = 1; i < numberOfStars; ++i) {
				dice = (i == 2) ? Dice.roll(2, 6) : Dice.roll(2, 6) + 4;
				if (dice < 4)
					orbit = 0;
				else if (dice < 7)
					orbit = (dice - 3);
				else if (dice < 12)
					orbit = (dice - 3 + Dice.roll(6));
				else
					orbit = FAR_ORBIT;

				stars.add(new Star(sector, subsector, starSystem, orbit, primary));
				++namedObjects;
			}
		}

		/*
		 * VALIDATING ORBITS OF SECOND/THIRD STARS
		 */
		if (numberOfStars > 2) {
			Star second = stars.get(1), third = stars.get(2);
			if (second.orbit <= FAR_ORBIT && third.orbit <= second.orbit)
				third.orbit = second.orbit * 2;
		}

		// determine companion(s) of far stars
		for (int i = 1; i <= numberOfStars; ++i) {
			if (numberOfStars > i && stars.get(i).orbit >= 32) {
				Star distant = stars.get(i);
				int orbit = distant.orbit;

				boolean companion = false;
				if (Dice.roll(2, 6) > 7)
					companion = true;

				if (companion) {
					dice = Dice.roll(2, 6) - 4;
					if (dice < 4)
						orbit = orbit + 0;
					else if (dice < 7)
						orbit = orbit + (dice - 3);
					else
						orbit = orbit + (dice - 3 + Dice.roll(6));

					stars.add(new Star(sector, subsector, starSystem, orbit, primary));
					++namedObjects;

				}
			}

		}

		/*
		 * WORLD GENERATION
		 */
		for (int j = 0; j < stars.size(); ++j) {
			Star star = stars.get(j);
			int[] orbits = new int[star.maxOrbits];

			// determine range of available orbits
			int habitableZone = Star.habitableZone(primary);
			int unavailableZones = Star.unavailableZone(primary);

			int companionA = 0, companionB = 0;
			if (stars.size() > 1) {// && stars[1] != null) {
				companionA = stars.get(1).orbit / 2;
				companionB = stars.get(1).orbit + 2;
			}

			int companion2A = 0, companion2B = 0;
			if (stars.size() > 2) {// && stars[2] != null) {
				companion2A = stars.get(2).orbit / 2;
				companion2B = stars.get(2).orbit + 2;
			}

			//
			int orbitNumber = unavailableZones;
			for (int i = 0; i < orbits.length; ++i) {
				if (orbitNumber < companionA) {
					orbits[i] = orbitNumber++;

				} else if (orbitNumber == companionA && orbits.length > 1) {
					orbitNumber = companionB;
					orbits[i] = orbitNumber++;

				} else if (companion2A > 0 && orbitNumber < companion2A) {
					orbits[i] = orbitNumber++;

				} else if (companion2A > 0 && orbitNumber == companion2A) {
					orbitNumber = companion2B;
					orbits[i] = orbitNumber++;

				} else {
					orbits[i] = orbitNumber++;

				}
			}

			int outermostOrbit = orbitNumber - 1;

			// determine start of inner zone (if applicable)
			int innerZone = Star.innerZone(star);

			// determine number of empty orbits
			int emptyOrbits = Dice.roll(6);
			if (primary.color == 'B' || primary.color == 'A')
				++emptyOrbits;

			if (emptyOrbits > 4) {
				emptyOrbits = Dice.roll(6);
				if (primary.color == 'B' || primary.color == 'A')
					++emptyOrbits;

				emptyOrbits = (emptyOrbits < 3) ? 1 : (emptyOrbits > 3) ? 3 : 2;

			} else {
				emptyOrbits = 0;
			}

			if (emptyOrbits > maxOrbits)
				emptyOrbits = maxOrbits;

			// determine number of captured planets - 0
			int capturedPlanets = Dice.roll(6);
			if (primary.color == 'B' || primary.color == 'A')
				++capturedPlanets;

			if (capturedPlanets > 4) {
				if (primary.color == 'B' || primary.color == 'A')
					++capturedPlanets;

				capturedPlanets = (capturedPlanets < 3) ? 1 : (capturedPlanets > 4) ? 3 : 2;

			} else {
				capturedPlanets = 0;
			}

			// determine number of gas giants
			int gasGiants = Dice.roll(2, 6);

			if (gasGiants < 10) {
				gasGiants = Dice.roll(2, 6);

				if (gasGiants < 4)
					gasGiants = 1;
				else if (gasGiants < 6)
					gasGiants = 2;
				else if (gasGiants < 8)
					gasGiants = 3;
				else if (gasGiants < 11)
					gasGiants = 4;
				else
					gasGiants = 5;

			} else {
				gasGiants = 0;
			}

			if (gasGiants > maxOrbits - emptyOrbits)
				gasGiants = maxOrbits - emptyOrbits;

			// determine number of asteroid belts
			int asteroids = Dice.roll(2, 6) - gasGiants;

			if (asteroids < 7) {
				asteroids = Dice.roll(2, 6) - gasGiants;

				if (asteroids < 1)
					asteroids = 3;
				else if (asteroids < 7)
					asteroids = 2;
				else
					asteroids = 1;

			} else {
				asteroids = 0;
			}

			List<Integer> available = new ArrayList<Integer>(orbits.length);
			for (int i = 0; i < orbits.length; ++i) {
				available.add(new Integer(orbits[i]));
			}
			Collections.shuffle(available);

			/*
			 * KNOWN OBJECT PLACEMENT
			 */
			planets = new ArrayList<Planetoid>();
			WorldType planet;
			boolean contains = Dice.containsIntegerOrGreater(habitableZone, available);

			// empty orbit placement
			planet = WorldType.EMPTY;
			while (emptyOrbits > 0) {
				planets.add(new Planetoid(available.remove(0), star, planet));
				--emptyOrbits;
			}

			// captured world placement
			planet = WorldType.CAPTURED;
			while (capturedPlanets > 0) {
				planets.add(new Planetoid(Dice.roll(2, 6), star, planet));
				++namedObjects;
				--capturedPlanets;
			}

			// gas giant placement
			while (gasGiants > 0) {
				contains = Dice.containsIntegerOrGreater(habitableZone, available);
				planet = (Dice.roll(2) == 1) ? WorldType.LARGE_GIANT : WorldType.SMALL_GIANT;

				if (contains && available.get(0) >= habitableZone) {
					planets.add(new Planetoid(available.remove(0), star, planet));
					++namedObjects;
					--gasGiants;
				} else if (contains && available.get(0) < habitableZone) {
					Collections.rotate(available, 1);

				} else {
					planets.add(new Planetoid(++outermostOrbit, star, planet));
					++namedObjects;
					--gasGiants;
				}
			}

			// asteroid placement
			List<Planetoid> worldlist = filterForGasGiants(planets);
			Collections.shuffle(worldlist);

			int candidate;
			planet = WorldType.ASTEROID;

			Iterator<Planetoid> it = worldlist.iterator();
			Planetoid prospective;
			while (available.size() > 0 && asteroids > 0) {
				while (it.hasNext()) {
					prospective = it.next();
					candidate = prospective.getOrbit() - 1;
					contains = available.contains(candidate);

					if (contains) {
						int index = available.indexOf(candidate);
						planets.add(new Planetoid(available.remove(index), star, planet));
						++namedObjects;
						--asteroids;
					}
				}

				if (available.size() > 0 && asteroids > 0) {
					planets.add(new Planetoid(available.remove(0), star, planet));
					++namedObjects;
					--asteroids;

				}
			}

			// remaining available orbits
			planet = WorldType.STANDARD;
			while (available.size() > 0) {
				planets.add(new Planetoid(available.remove(0), star, planet));
				++namedObjects;
			}

			// END OF J-LOOP
		}

		/*
		 * MAIN WORLD & GOVERNMENT DESIGNATION
		 */
		// space objects
		// spaceObjects = spaceObjectList(planets);

		List<Planetoid> pops = listWorlds();
		Planetoid.MainWorldSort mainSort = new Planetoid.MainWorldSort();
		Collections.sort(pops, mainSort);
		this.populousWorlds = pops.size();

		if (populousWorlds > 0) {
			this.mainWorld = pops.get(0);

			for (Iterator<Planetoid> it = pops.iterator(); it.hasNext();) {
				it.next().governmentSetup(mainWorld);
			}
		}

		/*
		 * CULL WORLD TAGS
		 */
		// count world tags
		HashMap<WorldTag, Integer> tagCount = new HashMap<WorldTag, Integer>();
		Set<WorldTag> tags;
		WorldTag tag;
		int value;

		Planetoid prospective;
		for (Iterator<Planetoid> it = pops.iterator(); it.hasNext();) {
			prospective = it.next();
			tags = prospective.getWorldTags();

			for (Iterator<WorldTag> its = tags.iterator(); its.hasNext();) {
				tag = its.next();

				if (tagCount.containsKey(tag)) {
					value = tagCount.get(tag);
					tagCount.replace(tag, value + 1);
				} else {
					tagCount.put(tag, 1);
				}
			}
		}

		// establish common tags
		commonTags = EnumSet.noneOf(WorldTag.class);
		double percent = pops.size();
		double threshold = (percent < 11) ? 0.4 : (percent < 21) ? 0.34 : 0.2;
		for (Iterator<WorldTag> its = tagCount.keySet().iterator(); its.hasNext();) {
			tag = its.next();

			value = tagCount.get(tag);
			if (value >= 3 && value / percent < threshold != true)
				commonTags.add(tag);
		}

		// remove common tags
		for (Iterator<Planetoid> it = pops.iterator(); it.hasNext();) {
			it.next().getWorldTags().removeAll(commonTags);
		}

		// world tag cleanup
		for (Iterator<Planetoid> it = pops.iterator(); it.hasNext();) {
			WorldTag.pruneWorldTags(commonTags, it.next());
		}

		/*
		 * NAMING CONVENTIONS
		 */
		nameSetup(this);

		/*
		 * ECONOMY SETUP
		 */
//		this.economy = null;
//		if (mainWorld != null && mainWorld.getPopulation() > 5)
//			this.economy = new Economy((World) mainWorld, this);

		/*
		 * END OF CONSTRUCTOR
		 */
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public boolean isPersistent() {
		return isPersistent;
	}

	public boolean hasChanged() {
		return hasChanged;
	}

	public int getIndex() {
		return index;
	}

	public List<Star> starList() {
		return stars;
	}

	public List<Planetoid> getSpaceObjects() {
		return null;
	}

	public List<Planetoid> listGasGiants() {
		return filterForGasGiants(new ArrayList<Planetoid>(getSpaceObjects()));
	}

	public List<Planetoid> listAllWorlds() {
		return new ArrayList<Planetoid>(getSpaceObjects());
	}

	public List<Planetoid> listWorlds() {
		return filterForWorlds(new ArrayList<Planetoid>(getSpaceObjects()));
	}

	public List<Planetoid> listNonWorlds() {
		return filterForNonWorlds(new ArrayList<Planetoid>(getSpaceObjects()));
	}

	public List<Planetoid> listPopulated() {
		return filterForPopulous(new ArrayList<Planetoid>(getSpaceObjects()));
	}

	/*
	 * FIXME - BELOW ARE METHODS TO REFACTOR
	 * 
	 */
	public World mainWorld() {
		return mainWorld;
	}

	public int numEmptyOrbits() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (WorldType.isEmpty(it.next()))
				++counter;
		}

		return counter;
	}

	public int numGasGiants() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (WorldType.isGasGiant(it.next()))
				++counter;
		}

		return counter;
	}

	// public Set<Planetoid> getGasGiants() {
	// Set<Planetoid> set = new HashSet<Planetoid>();
	//
	// Planetoid candidate;
	// for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
	// candidate = it.next();
	//
	// if (candidate.isGasGiant())
	// set.add(candidate);
	// }
	//
	// return set;
	// }

	public int numAsteroids() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (WorldType.isAsteroid(it.next()))
				++counter;
		}

		return counter;
	}

	public int numCaptured() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (WorldType.isCaptured(it.next()))
				++counter;
		}

		return counter;
	}

	public int numPopulated() {
		// FIXME - not sure if working
		return listPopulated().size();
	}

	public char getPrimaryStarColor() {
		return stars.get(0).color;
	}

	private List<Planetoid> orderedPlanetList() {
		List<Planetoid> planetList = new ArrayList<Planetoid>(planets);

		World.OrbitAscending worldSort = new World.OrbitAscending();
		Collections.sort(planetList, worldSort);

		return planetList;
	}

	public String toStringDetailed() {
		String star = "";
		star += String.format("Main world: %s", mainWorld.getName());
		star += (commonTags.size() > 0) ? String.format("%n%s", commonTags) : "";
		star += String.format("%nStarport: %s || Tech Level: %d || %s", mainWorld.getSpaceport(),
				mainWorld.getTechLevel(), POPULATIONS[mainWorld.getPopulation()]);
		star += String.format("%nGovernment: %s", mainWorld.governmentType());
		star += String.format("%nTrade Codes: %s", mainWorld.getTradeCodes());

		if (economy != null)
			star += "\n" + economy.toStringDetailed();

		//
		star += String.format("%n%n%s", mainWorld);
		star += "\n- - -";

		star += "\n" + stars.get(0).color + stars.get(0).size;
		// String string = String.format("%s (U: %2d || I: %2d >> H:%2d)", star,
		// unavailableZones, this.innerZone,
		// habitableZone);
		String string = String.format("%s", star);

		for (int i = 1; i < stars.size(); ++i) {
			star = "" + stars.get(i).color + stars.get(i).size;
			string += String.format(", %s (%2d)", star, stars.get(i).orbit);

		}

		/*
		 * PLANET LIST & SORT BY ORBIT ASCENDING
		 */
		String worlds = "";
		List<Planetoid> planetList = new ArrayList<Planetoid>(planets);
		World.OrbitAscending worldSort = new World.OrbitAscending();
		Collections.sort(planetList, worldSort);
		if (planetList.size() > 0) {
			for (Planetoid el : planetList)
				worlds += "\n" + el.toStringDetailed();
		}

		// String etc = String.format("%nOrbits: %d || Giants: %d || Asteroids: %d ||
		// Captured: %d", maxOrbits, gasGiants,
		// asteroids, capturedPlanets);
		return string + worlds;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	private static List<Planetoid> spaceObjectList(List<Planetoid> planets) {
		List<Planetoid> workingList = new ArrayList<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {

			candidate = it.next();
			workingList.add(candidate);
			// if (candidate.hasMoons()) {
			// for (Iterator<Planetoid> its = candidate.getMoons().iterator();
			// its.hasNext();) {
			//
			// workingList.add(its.next());
			// }
			// }
		}

		return workingList;
	}

	private static List<Planetoid> filterForGasGiants(List<Planetoid> planets) {
		List<Planetoid> workingList = new ArrayList<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (WorldType.isGasGiant(candidate))
				workingList.add(candidate);
		}

		return workingList;
	}

	private static List<Planetoid> filterForWorlds(List<Planetoid> planets) {
		List<Planetoid> workingList = new ArrayList<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (WorldType.isWorld(candidate))
				workingList.add(candidate);
		}

		return workingList;
	}

	private static List<Planetoid> filterForNonWorlds(List<Planetoid> planets) {
		List<Planetoid> workingList = new ArrayList<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (!WorldType.isWorld(candidate))
				workingList.add(candidate);
		}

		return workingList;
	}

	private static List<Planetoid> filterForPopulous(List<Planetoid> planets) {
		List<Planetoid> workingList = new ArrayList<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (WorldType.isWorld(candidate) && candidate.getPopulation() > 0)
				workingList.add(candidate);
		}

		return workingList;
	}

	private static void nameSetup(StarSystem group) {
		List<String> nameList = Names.worldNameList();
		//
		while (nameList.size() < group.namedObjects) {
			nameList = Names.worldNameList();
		}

		Collections.shuffle(nameList);
		Iterator<String> nameIt = nameList.iterator();
		String current;

		for (Star el : group.stars) {
			el.setName(nameIt.next());
		}

		/*
		 * PLANET LIST & SORT BY ORBIT ASCENDING
		 */
		Planetoid planet, moon;
		int moonCounter;
		for (Iterator<Planetoid> it = group.orderedPlanetList().iterator(); it.hasNext();) {
			planet = it.next();

			if (WorldType.nameable(planet) && !WorldType.isMoon(planet)) {
				current = Names.nameShorten(nameIt.next());
				planet.setName(current);

				// TODO - testing
				// System.out.println("Named world " + previous);

				moonCounter = 2;
				// for (Iterator<Planetoid> moonIt = planet.orderedMoonList().iterator();
				// moonIt.hasNext();) {
				// moon = moonIt.next();
				//
				// if (moon.nameable())
				// moon.setName(current + " " + Names.romanNumeral(moonCounter++));
				//
				// }

			}

		}

	}

}
