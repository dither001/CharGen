package milieu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import rules.Dice;

public class Group {
	private static final String[] POPULATIONS = { "Scattered or no inhabitants", "Dozens of inhabitants",
			"Hundreds of inhabitants", "Thousands of inhabitants", "Tens of thousands", "Hundreds of thousands",
			"Millions of inhabitants", "Tens of millions", "Hundreds of millions", "Billions of inhabitants",
			"Tens of billions" };

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Star[] stars;

	private byte maxOrbits;
	private int habitableZone, unavailableZones, innerZone;
	private Set<Planetoid> planets;
	private Set<Planetoid> spaceObjects;
	private int namedObjects;
	private int populousWorlds;

	private char starport;
	private Planetoid mainWorld;
	private Set<Faction> factions;

	private Set<World.Tag> commonTags;

	/*
	 * CONSTRUCTOR
	 * 
	 */
	public Group() {
		namedObjects = 0;
		int dice = Dice.roll(2, 6);

		// solitary, binary, or trinary system
		if (dice < 8)
			stars = new Star[1];
		else if (dice < 12)
			stars = new Star[2];
		else
			stars = new Star[3];

		// generate stars
		Star primary = new Star();
		++namedObjects;
		stars[0] = primary;
		for (int i = 1; i < stars.length; ++i) {
			stars[i] = new Star(primary, i);
			++namedObjects;
		}

		// verify that third star is farther from primary than second star
		if (stars.length > 2 && stars[2].orbit <= stars[1].orbit)
			stars[2].orbit = (byte) (stars[1].orbit * 2);

		// determine maximum orbits
		maxOrbits = (byte) Dice.roll(2, 6);
		if (primary.size == 2)
			maxOrbits += 8;
		else if (primary.size == 3)
			maxOrbits += 4;

		if (primary.color == 'K')
			maxOrbits -= 2;
		else if (primary.color == 'M')
			maxOrbits -= 4;

		maxOrbits = (maxOrbits < 0) ? 0 : maxOrbits;
		byte[] orbits = new byte[maxOrbits];

		// determine "location" of available orbits
		habitableZone = habitableZone(primary);
		unavailableZones = unavailableZone(primary);

		int companionA = 0, companionB = 0;
		int companion2A = 0, companion2B = 0;
		if (stars.length > 1 && stars[1] != null) {
			companionA = stars[1].orbit / 2;
			companionB = stars[1].orbit + 2;
		}
		if (stars.length > 2 && stars[2] != null) {
			companion2A = stars[2].orbit / 2;
			companion2B = stars[2].orbit + 2;
		}

		int orbitNumber = unavailableZones;
		for (int i = 0; i < orbits.length; ++i) {
			if (orbitNumber < companionA) {
				orbits[i] = (byte) orbitNumber++;

			} else if (orbitNumber == companionA && orbits.length > 1) {
				orbitNumber = companionB;
				orbits[i] = (byte) orbitNumber++;

			} else if (companion2A > 0 && orbitNumber < companion2A) {
				orbits[i] = (byte) orbitNumber++;

			} else if (companion2A > 0 && orbitNumber == companion2A) {
				orbitNumber = companion2B;
				orbits[i] = (byte) orbitNumber++;

			} else {
				orbits[i] = (byte) orbitNumber++;

			}

		}
		int outerMostOrbit = orbitNumber - 1;

		// determine start of inner zone (if applicable)
		innerZone = innerZone();

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

		// determine number of captured planets
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
		planets = new HashSet<Planetoid>();
		World.Type planet;
		boolean contains = Dice.containsIntegerOrGreater(habitableZone, available);

		// empty orbit placement
		planet = World.Type.EMPTY;
		while (emptyOrbits > 0) {
			planets.add(new Planetoid(planet, available.remove(0), this));
			--emptyOrbits;
		}

		// captured world placement
		planet = World.Type.CAPTURED;
		while (capturedPlanets > 0) {
			planets.add(new Planetoid(planet, Dice.roll(2, 6), this));
			++namedObjects;
			--capturedPlanets;
		}

		// gas giant placement
		while (gasGiants > 0) {
			contains = Dice.containsIntegerOrGreater(habitableZone, available);
			planet = (Dice.roll(2) == 1) ? World.Type.LARGE_GIANT : World.Type.SMALL_GIANT;

			if (contains && available.get(0) >= habitableZone) {
				planets.add(new Planetoid(planet, available.remove(0), this));
				++namedObjects;
				--gasGiants;
			} else if (contains && available.get(0) < habitableZone) {
				Collections.rotate(available, 1);

			} else {
				planets.add(new Planetoid(planet, ++outerMostOrbit, this));
				++namedObjects;
				--gasGiants;
			}
		}

		// asteroid placement
		List<Planetoid> worldlist = Dice.setToList(filterForGasGiants(planets));
		Collections.shuffle(worldlist);

		int candidate;
		planet = World.Type.ASTEROID;

		Iterator<Planetoid> it = worldlist.iterator();
		Planetoid prospective;
		while (available.size() > 0 && asteroids > 0) {
			while (it.hasNext()) {
				prospective = it.next();
				// System.out.println("Gas giant orbit: " + prospective.getOrbit());
				candidate = prospective.getOrbit() - 1;
				// System.out.println("Prospective orbit: " + candidate);
				contains = available.contains(candidate);
				// System.out.println("Contains candidate: " + contains);

				if (contains) {
					int index = available.indexOf(candidate);
					planets.add(new Planetoid(planet, available.remove(index), this));
					// System.out.println("Placed asteroid at: " + candidate);
					++namedObjects;
					--asteroids;
				}
			}

			if (available.size() > 0 && asteroids > 0) {
				planets.add(new Planetoid(planet, available.remove(0), this));
				++namedObjects;
				--asteroids;

			}
		}

		// remaining available orbits
		planet = World.Type.STANDARD;
		while (available.size() > 0) {
			planets.add(new Planetoid(planet, available.remove(0), this));
			++namedObjects;
		}

		/*
		 * MAIN WORLD & GOVERNMENT DESIGNATION
		 */
		// space objects
		spaceObjects = spaceObjectSet(planets);

		List<Planetoid> pops = Dice.setToList(worldSet());
		Planetoid.MainWorldSort mainSort = new Planetoid.MainWorldSort();
		Collections.sort(pops, mainSort);
		this.populousWorlds = pops.size();

		if (populousWorlds > 0) {
			mainWorld = pops.get(0);

			for (it = pops.iterator(); it.hasNext();) {
				it.next().governmentSetup();
			}
		}

		/*
		 * CULL WORLD TAGS
		 */
		// count world tags
		HashMap<World.Tag, Integer> tagCount = new HashMap<World.Tag, Integer>();
		Set<World.Tag> tags;
		World.Tag tag;
		int value;
		for (it = pops.iterator(); it.hasNext();) {
			prospective = it.next();
			tags = prospective.getWorldTags();

			for (Iterator<World.Tag> its = tags.iterator(); its.hasNext();) {
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
		commonTags = EnumSet.noneOf(World.Tag.class);
		double percent = pops.size();
		double threshold = (percent < 11) ? 0.4 : (percent < 21) ? 0.34 : 0.2;
		for (Iterator<World.Tag> its = tagCount.keySet().iterator(); its.hasNext();) {
			tag = its.next();

			value = tagCount.get(tag);
			if (value >= 3 && value / percent < threshold != true)
				commonTags.add(tag);
		}

		// remove common tags
		for (it = pops.iterator(); it.hasNext();) {
			it.next().getWorldTags().removeAll(commonTags);
		}

		// world tag cleanup
		for (it = pops.iterator(); it.hasNext();) {
			World.pruneWorldTags(commonTags, it.next());
		}

		/*
		 * NAMING CONVENTIONS
		 */
		nameSetup(this);

		/*
		 * FACTION SETUP
		 */
		factions = new HashSet<Faction>();

		for (it = pops.iterator(); it.hasNext();) {
			it.next().factionSetup();
		}

		for (it = pops.iterator(); it.hasNext();) {
			factions.addAll(it.next().getFactions());
		}

		/*
		 * END OF CONSTRUCTOR
		 */
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public char getStarport() {
		return starport;
	}

	public void setStarPort(char starPort) {
		this.starport = starPort;
	}

	public Planetoid getMainWorld() {
		return mainWorld;
	}

	public boolean isBinary() {
		return stars.length == 2;
	}

	public boolean isTrinary() {
		return stars.length == 3;
	}

	public boolean hasInnerZone() {
		return innerZone != -1;
	}

	public Set<Planetoid> spaceObjectSet() {
		return spaceObjects;
	}

	public Set<Planetoid> gasGiantSet() {
		Set<Planetoid> workingSet;
		workingSet = filterForGasGiants(spaceObjectSet());

		return workingSet;
	}

	public Set<Planetoid> worldSet() {
		Set<Planetoid> workingSet;
		workingSet = filterForWorlds(spaceObjectSet());

		return workingSet;
	}

	public Set<Planetoid> populatedSet() {
		Set<Planetoid> workingSet;
		workingSet = filterForPopulous(spaceObjectSet());

		return workingSet;
	}

	/*
	 * FIXME - BELOW ARE METHODS TO REFACTOR
	 * 
	 */

	public int emptyOrbits() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (it.next().isEmpty())
				++counter;
		}

		return counter;
	}

	public int gasGiants() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (it.next().isGasGiant())
				++counter;
		}

		return counter;
	}

	public Set<Planetoid> getGasGiants() {
		Set<Planetoid> set = new HashSet<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.isGasGiant())
				set.add(candidate);
		}

		return set;
	}

	public int asteroids() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (it.next().isAsteroid())
				++counter;
		}

		return counter;
	}

	public int capturedPlanets() {
		int counter = 0;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			if (it.next().isCaptured())
				++counter;
		}

		return counter;
	}

	public int populousWorlds() {
		// FIXME - not sure if working
		return populatedSet().size();
	}

	public char getPrimaryStarColor() {
		return stars[0].color;
	}

	public int numberOfUnavailableZones() {
		return unavailableZones;
	}

	public int getHabitableZone() {
		return habitableZone;
	}

	public int innerZone() {
		int innerZoneStart = unavailableZones;

		if (habitableZone <= 0)
			innerZoneStart = -1;

		return innerZoneStart;
	}

	private List<Planetoid> orderedPlanetList() {
		List<Planetoid> planetList = Dice.setToList(planets);
		World.OrbitAscending worldSort = new World.OrbitAscending();
		Collections.sort(planetList, worldSort);

		return planetList;
	}

	public String toStringDetailed() {
		String star = "";
		star += String.format("Main world: %s", mainWorld.getName());
		star += (commonTags.size() > 0) ? String.format("%n%s", commonTags) : "";
		star += String.format("%nStarport: %s || Tech Level: %d || %s", starport, mainWorld.getTechLevel(),
				POPULATIONS[mainWorld.getPopulation()]);
		star += String.format("%nGovernment: %s", mainWorld.governmentType());
		star += String.format("%nTrade Codes: %s", mainWorld.getTradeCodes());
		star += String.format("%n%s", mainWorld);
		star += "\n- - -";

		star += "\n" + stars[0].color + stars[0].size;
		// String string = String.format("%s (U: %2d || I: %2d >> H:%2d)", star,
		// unavailableZones, this.innerZone,
		// habitableZone);
		String string = String.format("%s (H: %d)", star, habitableZone);

		for (int i = 1; i < stars.length; ++i) {
			star = "" + stars[i].color + stars[i].size;
			string += String.format(", %s (%2d)", star, stars[i].orbit);

		}

		/*
		 * PLANET LIST & SORT BY ORBIT ASCENDING
		 */
		String worlds = "";
		List<Planetoid> planetList = Dice.setToList(planets);
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
	private static int habitableZone(Star star) {
		int habitableZone = 12;
		int sizeMod = 0;

		if (star.size == '0')
			sizeMod = 0;
		else if (star.size == '1')
			sizeMod = 1;
		else if (star.size == '2')
			sizeMod = 2;
		else if (star.size == '3')
			sizeMod = 3;
		else if (star.size == '4')
			sizeMod = 4;
		else if (star.size == '5')
			sizeMod = 5;
		else if (star.size == '6')
			sizeMod = 6;
		else if (star.size == 'D')
			sizeMod = 7;

		if (star.color == 'B')
			habitableZone -= sizeMod / 2;
		else if (star.color == 'A')
			habitableZone -= (sizeMod > 3) ? 6 : sizeMod * 2;
		else if (star.color == 'F')
			habitableZone -= (sizeMod + 3);
		else if (star.color == 'G')
			habitableZone -= (sizeMod < 5) ? (2 * sizeMod) - 1 : (2 * sizeMod);
		else if (star.color == 'K')
			habitableZone -= (sizeMod > 3) ? (2 * sizeMod) + 2 : sizeMod + 1;
		else if (star.color == 'M')
			habitableZone -= (sizeMod > 3) ? (2 * sizeMod) + 2 : sizeMod;

		if (habitableZone < 0)
			habitableZone = -1;

		return habitableZone;
	}

	private static int unavailableZone(Star star) {
		int unavailableZones = 7;
		int sizeMod = 0;

		if (star.size == '0')
			sizeMod = 0;
		else if (star.size == '1')
			sizeMod = 1;
		else if (star.size == '2')
			sizeMod = 2;
		else if (star.size == '3')
			sizeMod = 3;
		else if (star.size == '4')
			sizeMod = 4;
		else if (star.size == '5')
			sizeMod = 5;
		else if (star.size == '6')
			sizeMod = 6;
		else if (star.size == 'D')
			sizeMod = 7;

		if (star.color == 'M')
			unavailableZones -= (sizeMod > 3) ? sizeMod * 2 : sizeMod;
		else
			unavailableZones -= sizeMod * 2;

		if (unavailableZones < 0)
			unavailableZones = 0;

		return unavailableZones;
	}

	private static Set<Planetoid> spaceObjectSet(Set<Planetoid> planets) {
		Set<Planetoid> workingSet = new HashSet<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {

			candidate = it.next();
			workingSet.add(candidate);
			if (candidate.hasMoons()) {
				for (Iterator<Planetoid> its = candidate.getMoons().iterator(); its.hasNext();) {

					workingSet.add(its.next());
				}
			}
		}

		return workingSet;
	}

	private static Set<Planetoid> filterForGasGiants(Set<Planetoid> planets) {
		Set<Planetoid> workingSet = new HashSet<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.isGasGiant())
				workingSet.add(candidate);
		}

		return workingSet;
	}

	private static Set<Planetoid> filterForWorlds(Set<Planetoid> planets) {
		Set<Planetoid> workingSet = new HashSet<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.isWorld())
				workingSet.add(candidate);
		}

		return workingSet;
	}

	private static Set<Planetoid> filterForPopulous(Set<Planetoid> planets) {
		Set<Planetoid> workingSet = new HashSet<Planetoid>();

		Planetoid candidate;
		for (Iterator<Planetoid> it = planets.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.isWorld() && candidate.getPopulation() > 0)
				workingSet.add(candidate);
		}

		return workingSet;
	}

	private static void nameSetup(Group group) {
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

			if (planet.nameable() && planet.notMoon()) {
				current = Names.nameShorten(nameIt.next());
				planet.setName(current);

				// TODO - testing
				// System.out.println("Named world " + previous);

				moonCounter = 2;
				for (Iterator<Planetoid> moonIt = planet.orderedMoonList().iterator(); moonIt.hasNext();) {
					moon = moonIt.next();

					if (moon.nameable())
						moon.setName(current + " " + Names.romanNumeral(moonCounter++));

				}

			}

		}

	}

	/*
	 * STAR INNER CLASS
	 * 
	 */
	private class Star {
		/*
		 * INSTANCE FIELDS
		 */
		private String name;
		private byte sizeRoll, typeRoll;

		private char size, color;
		private byte orbit;

		/*
		 * CONSTRUCTORS
		 */
		public Star() {
			this(null, 0);
		}

		public Star(Star primary, int index) {
			int dice;

			// determine spectral class
			typeRoll = (byte) ((primary == null) ? Dice.roll(2, 6) : Dice.roll(2, 6) + primary.typeRoll);
			if (typeRoll == 2)
				color = 'A';
			else if (typeRoll < 8)
				color = 'M';
			else if (typeRoll < 9)
				color = 'K';
			else if (typeRoll < 10)
				color = 'G';
			else
				color = 'F';

			// determine size
			sizeRoll = (byte) ((primary == null) ? Dice.roll(2, 6) : Dice.roll(2, 6) + primary.sizeRoll);
			if (primary == null) {
				if (sizeRoll < 1)
					size = '0';
				else if (sizeRoll == 1)
					size = '1';
				else if (sizeRoll == 2)
					size = '2';
				else if (sizeRoll < 4)
					size = '3';
				else if (sizeRoll < 5)
					size = '4';
				else if (sizeRoll < 11)
					size = '5';
				else if (sizeRoll == 11)
					size = '6';
				else
					size = 'D';

			} else {
				if (sizeRoll < 1)
					size = '0';
				else if (sizeRoll == 1)
					size = '1';
				else if (sizeRoll == 2)
					size = '2';
				else if (sizeRoll == 3)
					size = '3';
				else if (sizeRoll == 4)
					size = '4';
				else if (sizeRoll == 5 || sizeRoll == 6)
					size = 'D';
				else if (sizeRoll == 7 || sizeRoll == 8)
					size = '5';
				else if (sizeRoll == 9)
					size = '6';
				else
					size = 'D';

			}

			// determine orbit
			orbit = 0;
			if (primary != null) {
				dice = (index == 1) ? Dice.roll(2, 6) : Dice.roll(2, 6) + 4;
				if (dice < 4)
					orbit = 0;
				else if (dice < 7)
					orbit = (byte) (dice - 3);
				else if (dice < 12)
					orbit = (byte) (dice - 3 + Dice.roll(6));
				else
					orbit = 49;

			}

			/*
			 * WORK IN PROGRESS
			 * 
			 */
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
