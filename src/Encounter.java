
public class Encounter {
	// static fields
	private static final int[] REWARDS = { 10, 25, 50, 100, 200, 450, 700, 1100, 1800, 2300, 2900, 3900, 5000, 5900,
			7200, 8400, 10000, 11500, 13000, 15000, 18000, 20000, 22000, 25000, 33000, 41000, 50000, 62000, 75000,
			90000, 105000, 120000, 135000, 155000 };

	private static final int[] EASY = { 25, 50, 75, 125, 250, 300, 350, 450, 550, 600, 800, 1000, 1100, 1250, 1400,
			1600, 2000, 2100, 2400, 2800 };
	private static final int[] MEDIUM = { 50, 100, 150, 250, 500, 600, 750, 900, 1100, 1200, 1600, 2000, 2200, 2500,
			2800, 3200, 3900, 4200, 4900, 5700 };
	private static final int[] HARD = { 75, 150, 225, 375, 750, 900, 1100, 1400, 1600, 1900, 2400, 3000, 3400, 3800,
			4300, 4800, 5900, 6300, 7300, 8500 };
	private static final int[] DEADLY = { 100, 200, 400, 500, 1100, 1400, 1700, 2100, 2400, 2800, 3600, 4500, 5100,
			5700, 6400, 7200, 8800, 9500, 10900, 12700 };

	private static final int[] DEFAULT_DIFFICULTY = MEDIUM;

	private static final String[] GOALS = { "Make peace.", "Protect object/character.", "Extract object/person.", "Run the gauntlet.", "Infiltrate location.", "Stop magic ritual.", "Neutralize single target." };
	private static final String[] AGENDAS = { "Agent/Spy", "Aide/Helper", "Ambush/Decoy", "Broker/Trader", "Captive/Slave", "Denizen/Local", "Enigma/Trickster", "Explorer/Lost", "Guardian", "Hunter/Prey", "Monster", "Outcast/Refugee" };

	// static methods
	public static int monsterEncounter(int APL, int numberOfPlayers, int difficulty, int numberOfEnemies) {
		double[] multiplier = { 0.5, 1, 1.5, 2, 2.5, 3, 4, 5 };
		int rating = 1, index = 0, budget;
		int[] threshold = MEDIUM;

		if (difficulty == 1)
			threshold = EASY;
		else if (difficulty == 2)
			threshold = MEDIUM;
		else if (difficulty == 3)
			threshold = HARD;
		else if (difficulty == 4)
			threshold = DEADLY;

		budget = threshold[APL - 1] * numberOfPlayers;

		if (numberOfEnemies == 1)
			index = 1;
		else if (numberOfEnemies == 2)
			index = 2;
		else if (numberOfEnemies > 2 && numberOfEnemies < 7)
			index = 3;
		else if (numberOfEnemies > 6 && numberOfEnemies < 11)
			index = 4;
		else if (numberOfEnemies > 10 && numberOfEnemies < 15)
			index = 5;
		else if (numberOfEnemies > 14)
			index = 6;

		if (numberOfPlayers < 3)
			++index;
		else if (numberOfPlayers > 5)
			--index;

		for (int i = REWARDS.length - 1; i > 0; --i) {
			if ((int) (REWARDS[i] * multiplier[index]) <= budget) {
				rating = i;
				break;
			}
		}

		return rating - 3;
	}

	public static int mediumEncounter(int APL, int numberOfEnemies) {
		return monsterEncounter(APL, 4, 2, numberOfEnemies);
	}

	public static int deadlyEncounter(int APL, int numberOfEnemies) {
		return monsterEncounter(APL, 4, 4, numberOfEnemies);
	}

	public static int soloMediumEncounter(int APL) {
		return mediumEncounter(APL, 1);
	}
	
	public static String randomAgenda() {
		return AGENDAS[Dice.roll(AGENDAS.length) - 1];
	}

	public static String reaction() {
		String[] reaction = { "Hostile", "Unfriendly", "Unfriendly", "Unfriendly", "Indifferent", "Indifferent", "Indifferent", "Friendly", "Friendly", "Friendly", "Helpful"};
		return reaction[Dice.roll(2,6) - 2];
	}
}
