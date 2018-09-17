package milieu;

import rules.Dice;

public class Star {
	/*
	 * INSTANCE FIELDS
	 */
	protected int index;
	protected boolean isPersistent;
	protected boolean hasChanged;

	protected int hexAddress;
	protected String name;
	protected int orbit;
	protected char size, color;

	protected int sizeRoll, typeRoll;

	/*
	 * CONSTRUCTORS
	 */
	public Star(int hexAddress) {
		this(hexAddress, null, 0, false);
	}

	public Star(int hexAddress, Star primary, int index, boolean isPersistent) {
		this.hexAddress = hexAddress;
		this.isPersistent = isPersistent;
		this.hasChanged = false;

		int dice;

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

		// determine orbit
		orbit = 0;
		if (primary != null) {
			dice = (index == 1) ? Dice.roll(2, 6) : Dice.roll(2, 6) + 4;
			if (dice < 4)
				orbit = 0;
			else if (dice < 7)
				orbit = (dice - 3);
			else if (dice < 12)
				orbit = (dice - 3 + Dice.roll(6));
			else
				orbit = 49;

		}

		/*
		 * WORK IN PROGRESS
		 * 
		 */
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

	public int hexAddress() {
		return hexAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.hasChanged = true;
	}

	public int getOrbit() {
		return orbit;
	}

	public char getSize() {
		return size;
	}

	public char getColor() {
		return color;
	}
}
