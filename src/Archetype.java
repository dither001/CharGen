
public enum Archetype {
	BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

	// methods
	public static Archetype selectArchetype(Actor actor) {
		Archetype archetype;
		Alignment ali = actor.getAli();

		int DEX, INT, WIS, CHA;
		DEX = actor.getDEX();
		INT = actor.getINT();
		WIS = actor.getWIS();
		CHA = actor.getCHA();

		if (CHA > 14) {
			archetype = SORCERER;
		} else if (CHA > 12) {
			archetype = WARLOCK;
		} else if (WIS > 14) {
			archetype = DRUID;
		} else if (WIS > 12) {
			archetype = CLERIC;
		} else if (INT > 14) {
			archetype = WIZARD;
		} else if (INT > 12) {
			archetype = BARD;
		} else if (DEX > 14) {
			archetype = RANGER;
		} else if (DEX > 12) {
			archetype = ROGUE;
		} else if (ali.equals(Alignment.LAW)) {
			archetype = MONK;
		} else if (ali.equals(Alignment.GOOD)) {
			archetype = PALADIN;
		} else if (ali.equals(Alignment.CHAOS)) {
			archetype = BARBARIAN;
		} else {
			archetype = FIGHTER;
		}

		return archetype;
	}

	public static float primaryAttribute(Actor actor) {
		float expRate = 1.00f;
		int prime;
		AbilityArray abilities = actor.getAbilities();
		Archetype job = actor.getJob();

		if (job.equals(ROGUE)) {
			prime = abilities.getDEX();
			expRate = (prime > 14) ? 1.10f : (prime > 12) ? 1.05f : expRate;
			expRate = (prime < 6) ? 0.80f : (prime < 9) ? 0.90f : expRate;
		} else if (job.equals(WIZARD)) {
			prime = abilities.getINT();
			expRate = (prime > 14) ? 1.10f : (prime > 12) ? 1.05f : expRate;
			expRate = (prime < 6) ? 0.80f : (prime < 9) ? 0.90f : expRate;
		} else if (job.equals(CLERIC) || job.equals(DRUID)) {
			prime = abilities.getWIS();
			expRate = (prime > 14) ? 1.10f : (prime > 12) ? 1.05f : expRate;
			expRate = (prime < 6) ? 0.80f : (prime < 9) ? 0.90f : expRate;
		} else if (job.equals(BARD) || job.equals(SORCERER) || job.equals(WARLOCK)) {
			prime = abilities.getCHA();
			expRate = (prime > 14) ? 1.10f : (prime > 12) ? 1.05f : expRate;
			expRate = (prime < 6) ? 0.80f : (prime < 9) ? 0.90f : expRate;
		} else {
			prime = abilities.getSTR();
			expRate = (prime > 14) ? 1.10f : (prime > 12) ? 1.05f : expRate;
			expRate = (prime < 6) ? 0.80f : (prime < 9) ? 0.90f : expRate;
		}

		return expRate;
	}
}
