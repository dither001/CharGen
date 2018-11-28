package milieu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import rules.Dice;

public class Figure implements Persistent {
	private static int FAR_ORBIT = 32;

	private static final String[] POPULATIONS = { "Scattered or no inhabitants", "Dozens of inhabitants",
			"Hundreds of inhabitants", "Thousands of inhabitants", "Tens of thousands", "Hundreds of thousands",
			"Millions of inhabitants", "Tens of millions", "Hundreds of millions", "Billions of inhabitants",
			"Tens of billions" };

	/*
	 * INSTANCE FIELDS
	 */
	private int index;
	private boolean isPersistent;
	private boolean hasChanged;

	private ArrayList<Star> stars;
	private ArrayList<World> worlds;

	private Star mainStar;
	private World mainWorld;
	private Economy economy;

	/*
	 * CONSTRUCTORS
	 */
	public Figure() {
		stars = new ArrayList<Star>();
		generateStars();

		mainStar = stars.get(0);
		worlds = new ArrayList<World>();

		List<World> worldList;
		MainWorldSort mainSort = new MainWorldSort();

		/*
		 * FIXME - need better condition / setup
		 */
		do {
			generateWorlds(mainStar);
			worldList = filterForPopulatedWorlds(worlds);
			Collections.sort(worldList, mainSort);

			if (worldList.size() > 0) {
				mainWorld = worldList.get(0);

				Planetoid current;
				for (Iterator<World> it = worlds.iterator(); it.hasNext();) {
					current = (Planetoid) it.next();

					current.governmentSetup(mainWorld);
					TradeCode.setupTradeCodes(current);
					Base.setupFacilities(current, mainWorld);
				}
			}

		} while (mainWorld != null && mainWorld.getPopulation() < 1);

		this.economy = null;
		if (mainWorld != null && mainWorld.getPopulation() > 5)
			this.economy = new Economy((World) mainWorld, this);

		nameSetup();
	}

	/*
	 * INSTANCE METHODS
	 */
	public String toStringDetailed() {
		String star = "";
		star += String.format("Main world: %s", mainWorld.getName());
		star += String.format("%nStarport: %s || Tech Level: %d || %s", mainWorld.getSpaceport(),
				mainWorld.getTechLevel(), POPULATIONS[mainWorld.getPopulation()]);
		star += String.format("%nTrade Codes: %s", mainWorld.getTradeCodes());

		star += String.format("%n");
		star += String.format("%nGovernment: %s", mainWorld.governmentType());
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

		// PLANET LIST & SORT BY ORBIT ASCENDING
		String ws = "";
		List<World> planetList = orderedPlanetList();
		if (planetList.size() > 0) {
			for (World el : planetList)
				ws += "\n" + ((Planetoid) el).toStringDetailed();
		}

		return string + ws;
	}

	public List<Star> starList() {
		return stars;
	}

	private List<World> orderedPlanetList() {
		List<World> worldList = new ArrayList<World>(worlds);

		OrbitAscending worldSort = new OrbitAscending();
		Collections.sort(worldList, worldSort);

		return worldList;
	}

	public World getMainWorld() {
		return mainWorld;
	}

	@Override
	public boolean isPersistent() {
		return isPersistent;
	}

	@Override
	public boolean hasChanged() {
		return hasChanged;
	}

	@Override
	public int getIndex() {
		return index;
	}

	public int numAsteroids() {
		int counter = 0;
		for (Iterator<World> it = worlds.iterator(); it.hasNext();) {
			if (WorldType.isAsteroid(it.next()))
				++counter;
		}

		return counter;
	}

	public int numGasGiants() {
		int counter = 0;
		for (Iterator<World> it = worlds.iterator(); it.hasNext();) {
			if (WorldType.isGasGiant(it.next()))
				++counter;
		}

		return counter;
	}

	/*
	 * PRIVATE METHODS
	 */
	private void generateStars() {
		int dice;

		// determine number of stars
		int numberOfStars;
		dice = Dice.roll(2, 6);
		if (dice < 8)
			numberOfStars = 1;
		else if (dice < 12)
			numberOfStars = 2;
		else
			numberOfStars = 3;

		// main star
		Star mainStar = new Star();
		stars.add(mainStar);

		// companion stars
		if (numberOfStars > 1) {
			int orbit = 0;

			for (int i = 1; i < numberOfStars; ++i) {
				dice = (i == 2) ? Dice.roll(2, 6) : Dice.roll(2, 6) + 4;
				if (dice < 4)
					orbit = 0;
				else if (dice < 7)
					orbit = (dice - 3);
				else if (dice < 12)
					orbit = (dice - 3) + Dice.roll(6);
				else
					orbit = FAR_ORBIT;

				stars.add(new Star(mainStar, orbit));
			}
		}

		// validate companion orbits
		if (numberOfStars > 2) {
			Star second = stars.get(1), third = stars.get(2);
			if (second.getOrbit() <= FAR_ORBIT && third.getOrbit() <= second.getOrbit())
				third.setOrbit(second.getOrbit() * 2);
		}

		// determine companion(s) of distant stars
		for (int i = 1; i <= numberOfStars; ++i) {
			if (numberOfStars > i && stars.get(i).getOrbit() >= 32) {
				Star distant = stars.get(i);
				int orbit = distant.getOrbit();

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

					stars.add(new Star(mainStar, orbit));
				}
			}
		}
	}

	private void generateWorlds(Star star) {
		int maxOrbits = star.getMaxOrbits();
		int[] orbitals = new int[maxOrbits];
		List<World> worldList;
		Iterator<World> worldIt;

		// determine range of available orbits
		int habitableZone = Star.habitableZone(star);
		int unavailableZones = Star.unavailableZone(star);

		int companionA = 0, companionB = 0;
		if (stars.size() > 1) {
			companionA = stars.get(1).orbit / 2;
			companionB = stars.get(1).orbit + 2;
		}

		int companion2A = 0, companion2B = 0;
		if (stars.size() > 2) {
			companion2A = stars.get(2).orbit / 2;
			companion2B = stars.get(2).orbit + 2;
		}

		int orbitNumber = unavailableZones;
		for (int i = 0; i < orbitals.length; ++i) {
			if (orbitNumber < companionA) {
				orbitals[i] = orbitNumber++;

			} else if (orbitNumber == companionA && orbitals.length > 1) {
				orbitNumber = companionB;
				orbitals[i] = orbitNumber++;

			} else if (companion2A > 0 && orbitNumber < companion2A) {
				orbitals[i] = orbitNumber++;

			} else if (companion2A > 0 && orbitNumber == companion2A) {
				orbitNumber = companion2B;
				orbitals[i] = orbitNumber++;

			} else {
				orbitals[i] = orbitNumber++;

			}
		}

		int outermostOrbit = orbitNumber - 1;

		// determine number of empty orbits
		int emptyOrbits = Dice.roll(6);
		if (star.color == 'B' || star.color == 'A')
			++emptyOrbits;

		if (emptyOrbits > 4) {
			emptyOrbits = Dice.roll(6);
			if (star.color == 'B' || star.color == 'A')
				++emptyOrbits;

			emptyOrbits = (emptyOrbits < 3) ? 1 : (emptyOrbits > 3) ? 3 : 2;

		} else {
			emptyOrbits = 0;
		}

		if (emptyOrbits > maxOrbits)
			emptyOrbits = maxOrbits;

		// determine number of captured planets
		int capturedPlanets = Dice.roll(6);
		if (star.color == 'B' || star.color == 'A')
			++capturedPlanets;

		if (capturedPlanets > 4) {
			if (star.color == 'B' || star.color == 'A')
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

		// list available orbits
		Stack<Integer> availableOrbits = new Stack<Integer>();
		// List<Integer> availableOrbits = new ArrayList<Integer>(orbitals.length);
		for (int i = 0; i < orbitals.length; ++i) {
			availableOrbits.add(new Integer(orbitals[i]));
		}
		Collections.shuffle(availableOrbits);

		/*
		 * KNOWN OBJECT PLACEMENT
		 */
		WorldType worldType;
		boolean contains = Dice.containsIntegerOrGreater(habitableZone, availableOrbits);

		// empty orbit placement
		worldType = WorldType.EMPTY;
		while (emptyOrbits > 0) {
			worlds.add(new Planetoid(availableOrbits.pop(), star, worldType));
			--emptyOrbits;
		}

		// captured world placement
		worldType = WorldType.CAPTURED;
		while (capturedPlanets > 0) {
			worlds.add(new Planetoid(Dice.roll(2, 6), star, worldType));
			--capturedPlanets;
		}

		// gas giant placement
		while (gasGiants > 0) {
			contains = Dice.containsIntegerOrGreater(habitableZone, availableOrbits);

			worldType = (Dice.roll(2) == 1) ? WorldType.LARGE_GIANT : WorldType.SMALL_GIANT;
			if (contains && availableOrbits.get(0) >= habitableZone) {
				worlds.add(new Planetoid(availableOrbits.pop(), star, worldType));
				--gasGiants;

			} else if (contains && availableOrbits.pop() < habitableZone) {
				Collections.rotate(availableOrbits, 1);

			} else {
				worlds.add(new Planetoid(++outermostOrbit, star, worldType));
				--gasGiants;
			}
		}

		// asteroid placement
		worldList = filterWorldsForType(worlds, WorldType.LARGE_GIANT, WorldType.SMALL_GIANT);
		Collections.shuffle(worldList);

		int candidate;
		worldType = WorldType.ASTEROID;

		worldIt = worldList.iterator();
		World prospective;
		while (availableOrbits.size() > 0 && asteroids > 0) {
			while (worldIt.hasNext()) {
				prospective = worldIt.next();
				candidate = prospective.getOrbit() - 1;
				contains = availableOrbits.contains(candidate);

				if (contains) {
					int index = availableOrbits.indexOf(candidate);
					worlds.add(new Planetoid(availableOrbits.remove(index), star, worldType));
					--asteroids;
				}
			}

			if (availableOrbits.size() > 0 && asteroids > 0) {
				worlds.add(new Planetoid(availableOrbits.pop(), star, worldType));
				--asteroids;

			}
		}

		// standard world placement
		worldType = WorldType.STANDARD;
		while (availableOrbits.size() > 0) {
			worlds.add(new Planetoid(availableOrbits.pop(), star, worldType));
		}

		// moon generation
		worldIt = worlds.iterator();
		List<World> moons = new ArrayList<World>();
		while (worldIt.hasNext()) {
			moons.addAll(((Planetoid) worldIt.next()).generateMoons(star));
		}
		worlds.addAll(moons);

	}

	private void nameSetup() {
		List<String> nameList = Names.worldNameList();
		//
		int namedObjects = stars.size() + worlds.size();
		while (nameList.size() < namedObjects) {
			nameList = Names.worldNameList();
		}

		Collections.shuffle(nameList);
		Iterator<String> nameIt = nameList.iterator();
		String currentName = "";

		for (Iterator<Star> it = stars.iterator(); it.hasNext();) {
			currentName = Names.nameShorten(nameIt.next());
			it.next().setName(currentName);
		}

		// PLANET LIST & SORT BY ORBIT ASCENDING
		World currentWorld;
		int moonCounter = 2;
		for (Iterator<World> it = orderedPlanetList().iterator(); it.hasNext();) {
			currentWorld = it.next();

			if (WorldType.nameable(currentWorld) && !WorldType.isMoon(currentWorld)) {
				currentName = Names.nameShorten(nameIt.next());
				currentWorld.setName(currentName);
				moonCounter = 2;
			} else if (WorldType.nameable(currentWorld)) {
				currentWorld.setName(currentName + " " + Names.romanNumeral(moonCounter));
				++moonCounter;
			}

		}

	}

	/*
	 * STATIC METHODS
	 */
	public static List<World> filterWorldsForType(List<World> worldList, WorldType... types) {
		List<World> workingList = new ArrayList<World>();

		World candidate;
		WorldType worldType;
		boolean isType;
		for (Iterator<World> it = worldList.iterator(); it.hasNext();) {
			candidate = it.next();

			if (types.length > 0) {
				worldType = candidate.getType();
				isType = false;

				for (WorldType t : types) {
					if (worldType.equals(t))
						isType = true;
				}

				if (isType)
					workingList.add(candidate);
			} else {
				workingList.add(candidate);
			}

		}

		return workingList;
	}

	public static List<World> filterForPopulatedWorlds(List<World> worldList) {
		List<World> workingList = new ArrayList<World>();

		World candidate;
		for (Iterator<World> it = worldList.iterator(); it.hasNext();) {
			candidate = it.next();

			if (WorldType.isWorld(candidate) && candidate.getPopulation() > 0)
				workingList.add(candidate);
		}

		return workingList;
	}

	/*
	 * COMPARATOR CLASSES
	 */
	public static class MainWorldSort implements Comparator<World> {

		@Override
		public int compare(World left, World right) {
			// compare population
			int compare = right.getPopulation() - left.getPopulation();

			// compare habitable zone
			if (compare == 0) {
				if (left.getOrbit() == right.getOrbit())
					compare = 0;
				else if (left.habitable())
					compare = -1;
				else if (right.habitable())
					compare = 1;

			}

			// compare proximity to star
			if (compare == 0)
				compare = left.getOrbit() - right.getOrbit();

			return compare;
		}
	}

	public static class OrbitAscending implements Comparator<World> {

		@Override
		public int compare(World left, World right) {
			int compare = left.getOrbit() - right.getOrbit();

			if (compare == 0)
				compare = left.getSubOrbit() - right.getSubOrbit();

			return compare;
		}

	}

}
