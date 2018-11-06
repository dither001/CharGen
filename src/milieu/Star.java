package milieu;

import rules.Dice;

public class Star implements Address {
	/*
	 * PERSISTENT FIELDS
	 */
	protected int index;
	protected boolean isPersistent;
	protected boolean hasChanged;

	protected int sector;
	protected int subsector;
	protected int cluster;
	
	protected int starsystem;
	protected int orbit;
	protected int suborbit;

	protected String name;
	protected char size, color;
	protected int maxOrbits;

	/*
	 * NON-PERSISTENT FIELDS
	 */
	protected int sizeRoll, typeRoll;

	/*
	 * CONSTRUCTORS
	 */
	public Star(int sector, int subsector, int cluster, int orbit) {
		this(sector, subsector, cluster, orbit, null);
	}

	public Star(int sector, int subsector, int cluster, int orbit, Star primary) {
		this.sector = sector;
		this.subsector = subsector;
		this.cluster = cluster;
		this.orbit = orbit;
		this.isPersistent = false;
		this.hasChanged = false;

		// determine spectral class
		typeRoll = ((primary == null) ? Dice.roll(2, 6) : Dice.roll(2, 6) + primary.typeRoll);
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
		sizeRoll = ((primary == null) ? Dice.roll(2, 6) : Dice.roll(2, 6) + primary.sizeRoll);
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

		/*
		 * WORK IN PROGRESS
		 * 
		 */
		maxOrbits = Dice.roll(2, 6);
		if (size == 2)
			maxOrbits += 8;
		else if (size == 3)
			maxOrbits += 4;

		if (color == 'K')
			maxOrbits -= 2;
		else if (color == 'M')
			maxOrbits -= 4;

		if (primary != null && orbit > 0 && maxOrbits > orbit / 2)
			maxOrbits /= 2;

		maxOrbits = (maxOrbits < 0) ? 0 : maxOrbits;
		// int[] orbits = new int[maxOrbits];

	}

	public boolean isPersistent() {
		return isPersistent;
	}

	public boolean hasChanged() {
		return hasChanged;
	}

	public int getIndex() {
		return index;
	}

	public int starsystem() {
		return starsystem;
	}
	
	@Override
	public int sector() {
		return sector;
	}

	@Override
	public int subsector() {
		return subsector;
	}

	@Override
	public int cluster() {
		return cluster;
	}

	@Override
	public int orbit() {
		return orbit;
	}

	@Override
	public int suborbit() {
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.hasChanged = true;
	}

	public char getSize() {
		return size;
	}

	public char getColor() {
		return color;
	}

	public int maxOrbits() {
		return maxOrbits;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static int habitableZone(Star star) {
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

	public static int unavailableZone(Star star) {
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

	public static int innerZone(Star star) {
		int innerZoneStart = unavailableZone(star);

		if (habitableZone(star) <= 0)
			innerZoneStart = -1;

		return innerZoneStart;
	}
}
