import java.util.Arrays;

public enum Career {
	ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN;

	// static methods
	public static Career randomCareer() {
		Career candidate;
		Career[] careers = { ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN };
		
		candidate = careers[Dice.roll(careers.length) - 1];
		return candidate;
	}
}
