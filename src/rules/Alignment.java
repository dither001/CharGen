package rules;

public enum Alignment {
	LAWFUL, GOOD, NEUTRAL, CHAOTIC, EVIL;

	public static Alignment random() {
		// lawful (15%), good (15%, neutral (15%), evil (40%), chaotic (15%)
		int[] array = new int[] { 15, 15, 15, 40, 15 };
		Alignment choice;

		int dice = Dice.roll(100);
		if (dice < 1 + array[0])
			choice = LAWFUL;
		else if (dice < 1 + array[0] + array[1])
			choice = GOOD;
		else if (dice < 1 + array[0] + array[1] + array[2])
			choice = NEUTRAL;
		else if (dice < 1 + array[0] + array[1] + array[2] + array[3])
			choice = EVIL;
		else
			choice = CHAOTIC;

		return choice;
	}
}
