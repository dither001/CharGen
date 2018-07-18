import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Group {
	private Star[] stars;

	private byte maxOrbits;
	private int habitableZone, unavailableZones, innerZone;
	private Set<Planetoid> planets;
	private Set<Planetoid> spaceObjects;
	private Planetoid mainWorld;

	// orbits is a temporary variable; localize to constructor when finished
	byte[] orbits;
	int outerMostOrbit;
	List<Integer> available;
	int capturedPlanets;
	// int gasGiants;
	List<Planetoid> populousWorlds;

	public Group() {
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
		stars[0] = primary;
		for (int i = 1; i < stars.length; ++i) {
			stars[i] = new Star(primary, i);
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
		orbits = new byte[maxOrbits];

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
		outerMostOrbit = orbitNumber - 1;

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
		capturedPlanets = Dice.roll(6);
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

		available = new ArrayList<Integer>(orbits.length);
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

		// gas giant placement
		while (gasGiants > 0) {
			contains = Dice.containsIntegerOrGreater(habitableZone, available);
			planet = (Dice.roll(2) == 1) ? World.Type.LARGE_GIANT : World.Type.SMALL_GIANT;

			if (contains && available.get(0) >= habitableZone) {
				planets.add(new Planetoid(planet, available.remove(0), this));
				--gasGiants;
			} else if (contains && available.get(0) < habitableZone) {
				Collections.rotate(available, 1);

			} else {
				planets.add(new Planetoid(planet, ++outerMostOrbit, this));
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
					--asteroids;
				}
			}

			if (available.size() > 0 && asteroids > 0) {
				planets.add(new Planetoid(planet, available.remove(0), this));
				--asteroids;

			}
			// END
		}

		// remaining available orbits
		planet = World.Type.STANDARD;
		while (available.size() > 0) {
			planets.add(new Planetoid(planet, available.remove(0), this));
		}

		/*
		 * MAIN WORLD DESIGNATION
		 */
		// spaceObjects
		spaceObjects = spaceObjectSet(planets);
		populousWorlds = Dice.setToList(worldSet());
		Planetoid.MainWorldSort mainSort = new Planetoid.MainWorldSort();
		Collections.sort(populousWorlds, mainSort);

		/*
		 * END OF CONSTRUCTOR
		 */
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
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
			if (it.next().isAsteroidBelt())
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
		return populousWorlds.size();
	}

	public char getPrimaryStarColor() {
		return stars[0].color;
	}

	public int getNumberOfOrbits() {
		/*
		 * FIXME - "orbits" is temporarily global and will be localized to the
		 * constructor once completed; thereafter this method should be
		 * "number of planetary objects" or something like that
		 */
		return orbits.length;
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

	public String toStringDetailed() {
		String star; // = String.format("Main world: %n%s %n- - -", mainWorld);

		star = "\n" + stars[0].color + stars[0].size;
		// String string = String.format("%s (U: %2d || I: %2d >> H:%2d)", star,
		// unavailableZones, this.innerZone,
		// habitableZone);
		String string = String.format("%s (H: %d)", star, habitableZone);

		for (int i = 1; i < stars.length; ++i) {
			star = "" + stars[i].color + stars[i].size;
			string += String.format(", %s (%2d)", star, stars[i].orbit);

		}

		int maxOrbits = orbits.length;
		int gasGiants = gasGiants();
		int asteroids = asteroids();

		/*
		 * PLANET LIST & SORT BY ORBIT ASCENDING
		 */
		String worlds = "";
		List<Planetoid> planetList = Dice.setToList(planets);
		Planetoid.OrbitAscending orbitSort = new Planetoid.OrbitAscending();
		Collections.sort(planetList, orbitSort);
		if (planetList.size() > 0) {
			for (Planetoid el : planetList)
				worlds += "\n" + el.toStringDetailed();
		}

		String populous = String.format("%n- - - %nPopulous Worlds: %d / %d", populousWorlds.size(),
				spaceObjects.size());
		if (populousWorlds.size() > 0) {
			for (Planetoid el : populousWorlds)
				populous += "\n" + el.toString();
		}

		// String etc = String.format("%nOrbits: %d || Giants: %d || Asteroids: %d ||
		// Captured: %d", maxOrbits, gasGiants,
		// asteroids, capturedPlanets);
		return string + worlds + populous;
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

	/*
	 * STAR INNER CLASS
	 * 
	 */
	private class Star {
		/*
		 * INSTANCE FIELDS
		 */
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

	}

}
