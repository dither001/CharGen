
public class Group {
	private Star[] stars;

	private byte maxOrbits;
	private int habitableZone, unavailableZones;
	private byte[] orbits;

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
			stars[2].orbit = (byte) (stars[1].orbit + 1);

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
			if (companionA > 0 && orbitNumber < companionA)
				orbits[i] = (byte) orbitNumber++;

		}

		// determine number of empty orbits
		int emptyOrbits = Dice.roll(3);

	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public boolean isBinary() {
		return stars.length == 2;
	}

	public boolean isTrinary() {
		return stars.length == 3;
	}

	public char getPrimaryStarColor() {
		return stars[0].color;
	}

	public int getUnavailableZones() {
		return unavailableZones;
	}

	public String toStringDetailed() {
		String star = "" + stars[0].color + stars[0].size;
		String string = String.format("%s (%2d || U: %2d >> H:%2d)", star, maxOrbits, unavailableZones, habitableZone);

		for (int i = 1; i < stars.length; ++i) {
			star = "" + stars[i].color + stars[i].size;
			string += String.format(", %s (%2d)", star, stars[i].orbit);

		}

		String orbitals = "\n[ ";
		for (int i = 0; i < orbits.length; ++i) {
			orbitals += orbits[i];
			if (i < orbits.length - 1)
				orbitals += ", ";
		}
		orbitals += " ]";

		return string; // + orbitals;
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
					orbit = 99;

			}

			/*
			 * WORK IN PROGRESS
			 * 
			 */
		}

	}

}
