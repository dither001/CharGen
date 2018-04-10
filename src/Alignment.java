
/*
 * TODO - Characters generated here have only one alignment factor because I
 * don't see the point in giving them multiple, potentially conflicting
 * allegiances -ABOVE AND BEYOND- race, class, and deity; additionally, evil
 * represents the majority at 40%, and the others are 15% each
 */

public enum Alignment {
	LAW, GOOD, NEUTRAL, EVIL, CHAOS;

	// methods
	public static Alignment selectALI() {
		int ali = Dice.roll(100);

		if (ali < 16) {
			// law represents (15%)
			ali = 0;
		} else if (ali < 31) {
			// good represents (15%)
			ali = 1;
		} else if (ali < 46) {
			// neutral represents (15%)
			ali = 2;
		} else if (ali < 86) {
			// evil represents majority (40%)
			ali = 3;
		} else {
			// chaos represents (15%)
			ali = 4;
		}

		return selectALI(ali);
	}

	public static Alignment selectALI(int index) {
		Alignment ali[] = { LAW, GOOD, NEUTRAL, EVIL, CHAOS };
		index = (index < 0) ? 0 : (index > 4) ? 4 : index;

		return ali[index];
	}
}
