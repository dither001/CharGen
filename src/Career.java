
/*
 * I want to implement a slightly choosier strategy for assigning (even) random backgrounds to characters. I want to organize
 * backgrounds by social class (upper, lower, etc), and make sure the majority of characters are lower class. Some races, which
 * don't properly represent civilized peoples such as half-elves and half-orcs, won't even have upper classes.
 * 
 *  I think like, 80% of all human characters will be lower-class, with 5% being upper class and the rest being middle class.
 */

public enum Career {
	ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN;

	// fields
	private static Career[] allCareers = { ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT,
			NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN };

	// static methods
	public static Career randomCareer(Actor actor) {
		// TODO - additional selection methods
		Career candidate;
		candidate = allCareers[Dice.roll(allCareers.length) - 1];

		return candidate;
	}
}
