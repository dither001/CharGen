import java.util.HashMap;

/*
 * I want to implement a slightly choosier strategy for assigning (even) random backgrounds to characters. I want to organize
 * backgrounds by social class (upper, lower, etc), and make sure the majority of characters are lower class. Some races, which
 * don't properly represent civilized peoples such as half-elves and half-orcs, won't even have upper classes.
 * 
 *  I think like, 80% of all human characters will be lower-class, with 5% being upper class and the rest being middle class.
 */

public enum Career {
	ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN;

	public static class Profile {
		private Actor owner;
		private Career career;

		// traits, ideals, bonds, flaws
		private String trait1;
		private String trait2;
		private String ideal;
		private String bond;
		private String flaw;

		public Profile(Actor owner) {
			this.owner = owner;

			this.career = Career.randomCareer();
			this.trait1 = Career.randomTrait(career);
			this.trait2 = Career.randomTrait(career, trait1);
			this.ideal = Career.randomIdeal(career);
			this.bond = Career.randomBond(career);
			this.flaw = Career.randomFlaw(career);
		}

		public boolean equals(Career other) {
			boolean equals = false;
			if (other.equals(this.career))
				equals = true;

			return equals;
		}

		public String toStringDetailed() {
			return String.format("Career-%s %n%s %n%s %n%s %n%s %n%s", career, trait1, trait2, ideal, bond, flaw);
		}
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static HashMap<String, String> traits = new HashMap<String, String>();

	//
	private static final Career[] ALL_CAREERS = { ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN,
			HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN };

	private static final String[] ACOLYTE_TRAITS = { "ACOLYTE_TRAIT_1", "ACOLYTE_TRAIT_2", "ACOLYTE_TRAIT_3",
			"ACOLYTE_TRAIT_4", "ACOLYTE_TRAIT_5", "ACOLYTE_TRAIT_6", "ACOLYTE_TRAIT_7", "ACOLYTE_TRAIT_8" };
	private static final String[] ACOLYTE_IDEALS = { "ACOLYTE_IDEAL_1", "ACOLYTE_IDEAL_2", "ACOLYTE_IDEAL_3",
			"ACOLYTE_IDEAL_4", "ACOLYTE_IDEAL_5", "ACOLYTE_IDEAL_6" };
	private static final String[] ACOLYTE_BONDS = { "ACOLYTE_BOND_1", "ACOLYTE_BOND_2", "ACOLYTE_BOND_3",
			"ACOLYTE_BOND_4", "ACOLYTE_BOND_5", "ACOLYTE_BOND_6" };
	private static final String[] ACOLYTE_FLAWS = { "ACOLYTE_FLAW_1", "ACOLYTE_FLAW_2", "ACOLYTE_FLAW_3",
			"ACOLYTE_FLAW_4", "ACOLYTE_FLAW_5", "ACOLYTE_FLAW_6" };
	private static final String[] CHARLATAN_TRAITS = { "CHARLATAN_TRAIT_1", "CHARLATAN_TRAIT_2", "CHARLATAN_TRAIT_3",
			"CHARLATAN_TRAIT_4", "CHARLATAN_TRAIT_5", "CHARLATAN_TRAIT_6", "CHARLATAN_TRAIT_7", "CHARLATAN_TRAIT_8" };
	private static final String[] CHARLATAN_IDEALS = { "CHARLATAN_IDEAL_1", "CHARLATAN_IDEAL_2", "CHARLATAN_IDEAL_3",
			"CHARLATAN_IDEAL_4", "CHARLATAN_IDEAL_5", "CHARLATAN_IDEAL_6" };
	private static final String[] CHARLATAN_BONDS = { "CHARLATAN_BOND_1", "CHARLATAN_BOND_2", "CHARLATAN_BOND_3",
			"CHARLATAN_BOND_4", "CHARLATAN_BOND_5", "CHARLATAN_BOND_6" };
	private static final String[] CHARLATAN_FLAWS = { "CHARLATAN_FLAW_1", "CHARLATAN_FLAW_2", "CHARLATAN_FLAW_3",
			"CHARLATAN_FLAW_4", "CHARLATAN_FLAW_5", "CHARLATAN_FLAW_6" };
	private static final String[] CRIMINAL_TRAITS = { "CRIMINAL_TRAIT_1", "CRIMINAL_TRAIT_2", "CRIMINAL_TRAIT_3",
			"CRIMINAL_TRAIT_4", "CRIMINAL_TRAIT_5", "CRIMINAL_TRAIT_6", "CRIMINAL_TRAIT_7", "CRIMINAL_TRAIT_8" };
	private static final String[] CRIMINAL_IDEALS = { "CRIMINAL_IDEAL_1", "CRIMINAL_IDEAL_2", "CRIMINAL_IDEAL_3",
			"CRIMINAL_IDEAL_4", "CRIMINAL_IDEAL_5", "CRIMINAL_IDEAL_6" };
	private static final String[] CRIMINAL_BONDS = { "CRIMINAL_BOND_1", "CRIMINAL_BOND_2", "CRIMINAL_BOND_3",
			"CRIMINAL_BOND_4", "CRIMINAL_BOND_5", "CRIMINAL_BOND_6" };
	private static final String[] CRIMINAL_FLAWS = { "CRIMINAL_FLAW_1", "CRIMINAL_FLAW_2", "CRIMINAL_FLAW_3",
			"CRIMINAL_FLAW_4", "CRIMINAL_FLAW_5", "CRIMINAL_FLAW_6" };
	private static final String[] ENTERTAINER_TRAITS = { "ENTERTAINER_TRAIT_1", "ENTERTAINER_TRAIT_2",
			"ENTERTAINER_TRAIT_3", "ENTERTAINER_TRAIT_4", "ENTERTAINER_TRAIT_5", "ENTERTAINER_TRAIT_6",
			"ENTERTAINER_TRAIT_7", "ENTERTAINER_TRAIT_8" };
	private static final String[] ENTERTAINER_IDEALS = { "ENTERTAINER_IDEAL_1", "ENTERTAINER_IDEAL_2",
			"ENTERTAINER_IDEAL_3", "ENTERTAINER_IDEAL_4", "ENTERTAINER_IDEAL_5", "ENTERTAINER_IDEAL_6" };
	private static final String[] ENTERTAINER_BONDS = { "ENTERTAINER_BOND_1", "ENTERTAINER_BOND_2",
			"ENTERTAINER_BOND_3", "ENTERTAINER_BOND_4", "ENTERTAINER_BOND_5", "ENTERTAINER_BOND_6" };
	private static final String[] ENTERTAINER_FLAWS = { "ENTERTAINER_FLAW_1", "ENTERTAINER_FLAW_2",
			"ENTERTAINER_FLAW_3", "ENTERTAINER_FLAW_4", "ENTERTAINER_FLAW_5", "ENTERTAINER_FLAW_6" };
	private static final String[] FOLK_HERO_TRAITS = { "FOLK_HERO_TRAIT_1", "FOLK_HERO_TRAIT_2", "FOLK_HERO_TRAIT_3",
			"FOLK_HERO_TRAIT_4", "FOLK_HERO_TRAIT_5", "FOLK_HERO_TRAIT_6", "FOLK_HERO_TRAIT_7", "FOLK_HERO_TRAIT_8" };
	private static final String[] FOLK_HERO_IDEALS = { "FOLK_HERO_IDEAL_1", "FOLK_HERO_IDEAL_2", "FOLK_HERO_IDEAL_3",
			"FOLK_HERO_IDEAL_4", "FOLK_HERO_IDEAL_5", "FOLK_HERO_IDEAL_6" };
	private static final String[] FOLK_HERO_BONDS = { "FOLK_HERO_BOND_1", "FOLK_HERO_BOND_2", "FOLK_HERO_BOND_3",
			"FOLK_HERO_BOND_4", "FOLK_HERO_BOND_5", "FOLK_HERO_BOND_6" };
	private static final String[] FOLK_HERO_FLAWS = { "FOLK_HERO_FLAW_1", "FOLK_HERO_FLAW_2", "FOLK_HERO_FLAW_3",
			"FOLK_HERO_FLAW_4", "FOLK_HERO_FLAW_5", "FOLK_HERO_FLAW_6" };
	private static final String[] GUILD_ARTISAN_TRAITS = { "GUILD_ARTISAN_TRAIT_1", "GUILD_ARTISAN_TRAIT_2",
			"GUILD_ARTISAN_TRAIT_3", "GUILD_ARTISAN_TRAIT_4", "GUILD_ARTISAN_TRAIT_5", "GUILD_ARTISAN_TRAIT_6",
			"GUILD_ARTISAN_TRAIT_7", "GUILD_ARTISAN_TRAIT_8" };
	private static final String[] GUILD_ARTISAN_IDEALS = { "GUILD_ARTISAN_IDEAL_1", "GUILD_ARTISAN_IDEAL_2",
			"GUILD_ARTISAN_IDEAL_3", "GUILD_ARTISAN_IDEAL_4", "GUILD_ARTISAN_IDEAL_5", "GUILD_ARTISAN_IDEAL_6" };
	private static final String[] GUILD_ARTISAN_BONDS = { "GUILD_ARTISAN_BOND_1", "GUILD_ARTISAN_BOND_2",
			"GUILD_ARTISAN_BOND_3", "GUILD_ARTISAN_BOND_4", "GUILD_ARTISAN_BOND_5", "GUILD_ARTISAN_BOND_6" };
	private static final String[] GUILD_ARTISAN_FLAWS = { "GUILD_ARTISAN_FLAW_1", "GUILD_ARTISAN_FLAW_2",
			"GUILD_ARTISAN_FLAW_3", "GUILD_ARTISAN_FLAW_4", "GUILD_ARTISAN_FLAW_5", "GUILD_ARTISAN_FLAW_6" };
	private static final String[] HERMIT_TRAITS = { "HERMIT_TRAIT_1", "HERMIT_TRAIT_2", "HERMIT_TRAIT_3",
			"HERMIT_TRAIT_4", "HERMIT_TRAIT_5", "HERMIT_TRAIT_6", "HERMIT_TRAIT_7", "HERMIT_TRAIT_8" };
	private static final String[] HERMIT_IDEALS = { "HERMIT_IDEAL_1", "HERMIT_IDEAL_2", "HERMIT_IDEAL_3",
			"HERMIT_IDEAL_4", "HERMIT_IDEAL_5", "HERMIT_IDEAL_6" };
	private static final String[] HERMIT_BONDS = { "HERMIT_BOND_1", "HERMIT_BOND_2", "HERMIT_BOND_3", "HERMIT_BOND_4",
			"HERMIT_BOND_5", "HERMIT_BOND_6" };
	private static final String[] HERMIT_FLAWS = { "HERMIT_FLAW_1", "HERMIT_FLAW_2", "HERMIT_FLAW_3", "HERMIT_FLAW_4",
			"HERMIT_FLAW_5", "HERMIT_FLAW_6" };
	private static final String[] NOBLE_TRAITS = { "NOBLE_TRAIT_1", "NOBLE_TRAIT_2", "NOBLE_TRAIT_3", "NOBLE_TRAIT_4",
			"NOBLE_TRAIT_5", "NOBLE_TRAIT_6", "NOBLE_TRAIT_7", "NOBLE_TRAIT_8" };
	private static final String[] NOBLE_IDEALS = { "NOBLE_IDEAL_1", "NOBLE_IDEAL_2", "NOBLE_IDEAL_3", "NOBLE_IDEAL_4",
			"NOBLE_IDEAL_5", "NOBLE_IDEAL_6" };
	private static final String[] NOBLE_BONDS = { "NOBLE_BOND_1", "NOBLE_BOND_2", "NOBLE_BOND_3", "NOBLE_BOND_4",
			"NOBLE_BOND_5", "NOBLE_BOND_6" };
	private static final String[] NOBLE_FLAWS = { "NOBLE_FLAW_1", "NOBLE_FLAW_2", "NOBLE_FLAW_3", "NOBLE_FLAW_4",
			"NOBLE_FLAW_5", "NOBLE_FLAW_6" };
	private static final String[] OUTLANDER_TRAITS = { "OUTLANDER_TRAIT_1", "OUTLANDER_TRAIT_2", "OUTLANDER_TRAIT_3",
			"OUTLANDER_TRAIT_4", "OUTLANDER_TRAIT_5", "OUTLANDER_TRAIT_6", "OUTLANDER_TRAIT_7", "OUTLANDER_TRAIT_8" };
	private static final String[] OUTLANDER_IDEALS = { "OUTLANDER_IDEAL_1", "OUTLANDER_IDEAL_2", "OUTLANDER_IDEAL_3",
			"OUTLANDER_IDEAL_4", "OUTLANDER_IDEAL_5", "OUTLANDER_IDEAL_6" };
	private static final String[] OUTLANDER_BONDS = { "OUTLANDER_BOND_1", "OUTLANDER_BOND_2", "OUTLANDER_BOND_3",
			"OUTLANDER_BOND_4", "OUTLANDER_BOND_5", "OUTLANDER_BOND_6" };
	private static final String[] OUTLANDER_FLAWS = { "OUTLANDER_FLAW_1", "OUTLANDER_FLAW_2", "OUTLANDER_FLAW_3",
			"OUTLANDER_FLAW_4", "OUTLANDER_FLAW_5", "OUTLANDER_FLAW_6" };
	private static final String[] SAGE_TRAITS = { "SAGE_TRAIT_1", "SAGE_TRAIT_2", "SAGE_TRAIT_3", "SAGE_TRAIT_4",
			"SAGE_TRAIT_5", "SAGE_TRAIT_6", "SAGE_TRAIT_7", "SAGE_TRAIT_8" };
	private static final String[] SAGE_IDEALS = { "SAGE_IDEAL_1", "SAGE_IDEAL_2", "SAGE_IDEAL_3", "SAGE_IDEAL_4",
			"SAGE_IDEAL_5", "SAGE_IDEAL_6" };
	private static final String[] SAGE_BONDS = { "SAGE_BOND_1", "SAGE_BOND_2", "SAGE_BOND_3", "SAGE_BOND_4",
			"SAGE_BOND_5", "SAGE_BOND_6" };
	private static final String[] SAGE_FLAWS = { "SAGE_FLAW_1", "SAGE_FLAW_2", "SAGE_FLAW_3", "SAGE_FLAW_4",
			"SAGE_FLAW_5", "SAGE_FLAW_6" };
	private static final String[] SAILOR_TRAITS = { "SAILOR_TRAIT_1", "SAILOR_TRAIT_2", "SAILOR_TRAIT_3",
			"SAILOR_TRAIT_4", "SAILOR_TRAIT_5", "SAILOR_TRAIT_6", "SAILOR_TRAIT_7", "SAILOR_TRAIT_8" };
	private static final String[] SAILOR_IDEALS = { "SAILOR_IDEAL_1", "SAILOR_IDEAL_2", "SAILOR_IDEAL_3",
			"SAILOR_IDEAL_4", "SAILOR_IDEAL_5", "SAILOR_IDEAL_6" };
	private static final String[] SAILOR_BONDS = { "SAILOR_BOND_1", "SAILOR_BOND_2", "SAILOR_BOND_3", "SAILOR_BOND_4",
			"SAILOR_BOND_5", "SAILOR_BOND_6" };
	private static final String[] SAILOR_FLAWS = { "SAILOR_FLAW_1", "SAILOR_FLAW_2", "SAILOR_FLAW_3", "SAILOR_FLAW_4",
			"SAILOR_FLAW_5", "SAILOR_FLAW_6" };
	private static final String[] SOLDIER_TRAITS = { "SOLDIER_TRAIT_1", "SOLDIER_TRAIT_2", "SOLDIER_TRAIT_3",
			"SOLDIER_TRAIT_4", "SOLDIER_TRAIT_5", "SOLDIER_TRAIT_6", "SOLDIER_TRAIT_7", "SOLDIER_TRAIT_8" };
	private static final String[] SOLDIER_IDEALS = { "SOLDIER_IDEAL_1", "SOLDIER_IDEAL_2", "SOLDIER_IDEAL_3",
			"SOLDIER_IDEAL_4", "SOLDIER_IDEAL_5", "SOLDIER_IDEAL_6" };
	private static final String[] SOLDIER_BONDS = { "SOLDIER_BOND_1", "SOLDIER_BOND_2", "SOLDIER_BOND_3",
			"SOLDIER_BOND_4", "SOLDIER_BOND_5", "SOLDIER_BOND_6" };
	private static final String[] SOLDIER_FLAWS = { "SOLDIER_FLAW_1", "SOLDIER_FLAW_2", "SOLDIER_FLAW_3",
			"SOLDIER_FLAW_4", "SOLDIER_FLAW_5", "SOLDIER_FLAW_6" };
	private static final String[] URCHIN_TRAITS = { "URCHIN_TRAIT_1", "URCHIN_TRAIT_2", "URCHIN_TRAIT_3",
			"URCHIN_TRAIT_4", "URCHIN_TRAIT_5", "URCHIN_TRAIT_6", "URCHIN_TRAIT_7", "URCHIN_TRAIT_8" };
	private static final String[] URCHIN_IDEALS = { "URCHIN_IDEAL_1", "URCHIN_IDEAL_2", "URCHIN_IDEAL_3",
			"URCHIN_IDEAL_4", "URCHIN_IDEAL_5", "URCHIN_IDEAL_6" };
	private static final String[] URCHIN_BONDS = { "URCHIN_BOND_1", "URCHIN_BOND_2", "URCHIN_BOND_3", "URCHIN_BOND_4",
			"URCHIN_BOND_5", "URCHIN_BOND_6" };
	private static final String[] URCHIN_FLAWS = { "URCHIN_FLAW_1", "URCHIN_FLAW_2", "URCHIN_FLAW_3", "URCHIN_FLAW_4",
			"URCHIN_FLAW_5", "URCHIN_FLAW_6" };

	// villainous schemes
	private static final String[] ALL_SCHEMES = { "IMMORTALITY_1", "IMMORTALITY_2", "IMMORTALITY_3", "IMMORTALITY_4",
			"INFLUENCE_1", "INFLUENCE_2", "INFLUENCE_3", "INFLUENCE_4", "MAGIC_1", "MAGIC_2", "MAGIC_3", "MAGIC_4",
			"MAGIC_5", "MAGIC_6", "MAYHEM_1", "MAYHEM_2", "MAYHEM_3", "MAYHEM_4", "MAYHEM_5", "MAYHEM_6", "PASSION_1",
			"PASSION_2", "PASSION_3", "PASSION_4", "POWER_1", "POWER_2", "POWER_3", "POWER_4", "REVENGE_1", "REVENGE_2",
			"REVENGE_3", "REVENGE_4", "WEALTH_1", "WEALTH_2", "WEALTH_3", "WEALTH_4" };
	private static final String[] IMMORTALITY_ONLY = { "IMMORTALITY_1", "IMMORTALITY_2", "IMMORTALITY_3",
			"IMMORTALITY_4" };
	private static final String[] INFLUENCE_ONLY = { "INFLUENCE_1", "INFLUENCE_2", "INFLUENCE_3", "INFLUENCE_4" };
	private static final String[] MAGIC_ONLY = { "MAGIC_1", "MAGIC_2", "MAGIC_3", "MAGIC_4", "MAGIC_5", "MAGIC_6" };
	private static final String[] MAYHEM_ONLY = { "MAYHEM_1", "MAYHEM_2", "MAYHEM_3", "MAYHEM_4", "MAYHEM_5",
			"MAYHEM_6" };
	private static final String[] PASSION_ONLY = { "PASSION_1", "PASSION_2", "PASSION_3", "PASSION_4" };
	private static final String[] POWER_ONLY = { "POWER_1", "POWER_2", "POWER_3", "POWER_4" };
	private static final String[] REVENGE_ONLY = { "REVENGE_1", "REVENGE_2", "REVENGE_3", "REVENGE_4" };
	private static final String[] WEALTH_ONLY = { "WEALTH_1", "WEALTH_2", "WEALTH_3", "WEALTH_4" };

	// villainous methods
	// private static String[] ALL_METHODS = { "DEVASTATION_1", "DEVASTATION_2",
	// "DEVASTATION_3", "DEVASTATION_4", "METHOD_A", "METHOD_B", "COERCION_1",
	// "COERCION_2", "COERCION_3", "COERCION_4", "COERCION_5", "COERCION_6",
	// "COERCION_7", "COERCION_8", "COERCION_9", "COERCION_10", "CON_GAME_1",
	// "CON_GAME_2", "CON_GAME_3", "CON_GAME_4", "CON_GAME_5", "CON_GAME_6",
	// "DEFAMATION_1", "DEFAMATION_2", "DEFAMATION_3", "DEFAMATION_4", "METHOD_C",
	// "EXECUTION_1", "EXECUTION_2", "EXECUTION_3", "EXECUTION_4", "EXECUTION_5",
	// "EXECUTION_6", "EXECUTION_7", "EXECUTION_8", "METHOD_D", "METHOD_E",
	// "MAGIC_MAYHEM_1", "MAGIC_MAYHEM_2", "MAGIC_MAYHEM_3", "MAGIC_MAYHEM_4",
	// "MAGIC_MAYHEM_5", "MAGIC_MAYHEM_6", "MAGIC_MAYHEM_7", "MAGIC_MAYHEM_8",
	// "MURDER_1", "MURDER_2", "MURDER_3", "MURDER_4", "MURDER_5", "MURDER_6",
	// "MURDER_7", "MURDER_8", "MURDER_9", "MURDER_10", "METHOD_F", "POLITICS_1",
	// "POLITICS_2", "POLITICS_3", "POLITICS_4", "POLITICS_5", "POLITICS_6",
	// "RELIGION_1", "RELIGION_2", "RELIGION_3", "RELIGION_4", "METHOD_G",
	// "THIEVERY_1", "THIEVERY_2", "THIEVERY_3", "THIEVERY_4", "THIEVERY_5",
	// "THIEVERY_6", "THIEVERY_7", "THIEVERY_8", "THIEVERY_9", "THIEVERY_10",
	// "TORTURE_1", "TORTURE_2", "TORTURE_3", "TORTURE_4", "TORTURE_5", "TORTURE_6",
	// "VICE_1", "VICE_2", "VICE_3", "VICE_4", "WARFARE_1", "WARFARE_2",
	// "WARFARE_3", "WARFARE_4", "WARFARE_5", "WARFARE_6" };
	// "METHOD_A", "METHOD_B", "METHOD_C", "METHOD_D", "METHOD_E", "METHOD_F",
	// "METHOD_G"
	private static final String[] DEVASTATION_ONLY = { "DEVASTATION_1", "DEVASTATION_2", "DEVASTATION_3",
			"DEVASTATION_4" };
	private static final String[] COERCION_ONLY = { "COERCION_1", "COERCION_2", "COERCION_3", "COERCION_4",
			"COERCION_5", "COERCION_6", "COERCION_7", "COERCION_8", "COERCION_9", "COERCION_10" };
	private static final String[] CON_GAME_ONLY = { "CON_GAME_1", "CON_GAME_2", "CON_GAME_3", "CON_GAME_4",
			"CON_GAME_5", "CON_GAME_6" };
	private static final String[] DEFAMATION_ONLY = { "DEFAMATION_1", "DEFAMATION_2", "DEFAMATION_3", "DEFAMATION_4" };
	private static final String[] EXECUTION_ONLY = { "EXECUTION_1", "EXECUTION_2", "EXECUTION_3", "EXECUTION_4",
			"EXECUTION_5", "EXECUTION_6", "EXECUTION_7", "EXECUTION_8" };
	private static final String[] MAGIC_MAYHEM_ONLY = { "MAGIC_MAYHEM_1", "MAGIC_MAYHEM_2", "MAGIC_MAYHEM_3",
			"MAGIC_MAYHEM_4", "MAGIC_MAYHEM_5", "MAGIC_MAYHEM_6", "MAGIC_MAYHEM_7", "MAGIC_MAYHEM_8" };
	private static final String[] MURDER_ONLY = { "MURDER_1", "MURDER_2", "MURDER_3", "MURDER_4", "MURDER_5",
			"MURDER_6", "MURDER_7", "MURDER_8", "MURDER_9", "MURDER_10" };
	private static final String[] POLITICS_ONLY = { "POLITICS_1", "POLITICS_2", "POLITICS_3", "POLITICS_4",
			"POLITICS_5", "POLITICS_6" };
	private static final String[] RELIGION_ONLY = { "RELIGION_1", "RELIGION_2", "RELIGION_3", "RELIGION_4" };
	private static final String[] THIEVERY_ONLY = { "THIEVERY_1", "THIEVERY_2", "THIEVERY_3", "THIEVERY_4",
			"THIEVERY_5", "THIEVERY_6", "THIEVERY_7", "THIEVERY_8", "THIEVERY_9", "THIEVERY_10" };
	private static final String[] TORTURE_ONLY = { "TORTURE_1", "TORTURE_2", "TORTURE_3", "TORTURE_4", "TORTURE_5",
			"TORTURE_6" };
	private static final String[] VICE_ONLY = { "VICE_1", "VICE_2", "VICE_3", "VICE_4" };
	private static final String[] WARFARE_ONLY = { "WARFARE_1", "WARFARE_2", "WARFARE_3", "WARFARE_4", "WARFARE_5",
			"WARFARE_6" };

	// initialize
	static {
		// traits, ideals, bonds, and flaws from Player's Handbook backgrounds
		traits.put("ACOLYTE_TRAIT_1",
				"I idolize a particular hero of my faith, and constantly refer to that person's deeds and example.");
		traits.put("ACOLYTE_TRAIT_2",
				"I can find common ground between the fiercest enemies, empathizing with them and always working toward peace.");
		traits.put("ACOLYTE_TRAIT_3",
				"I see omens in every event and action. The gods try to speak to us, we just need to listen.");
		traits.put("ACOLYTE_TRAIT_4", "Nothing can shake my optimistic attitude.");
		traits.put("ACOLYTE_TRAIT_5", "I quote (or misquote) sacred texts and proverbs in almost every situation.");
		traits.put("ACOLYTE_TRAIT_6",
				"I am tolerant (or intolerant) of other faiths and respect (or condemn) the worship of other gods.");
		traits.put("ACOLYTE_TRAIT_7",
				"I've enjoyed fine food, drink, and high society among my temple's elite. Rough living grates on me.");
		traits.put("ACOLYTE_TRAIT_8",
				"I've spent so long in the temple that I have little practical experience dealing with people in the outside world.");
		traits.put("ACOLYTE_IDEAL_1",
				"Tradition. The ancient traditions of worship and sacrifice must be preserved and upheld. (Lawful)");
		traits.put("ACOLYTE_IDEAL_2",
				"Charity. I always try to help those in need, no matter what the personal cost. (Good)");
		traits.put("ACOLYTE_IDEAL_3",
				"Change. We must help bring about the changes the gods are constantly working in the world. (Chaotic)");
		traits.put("ACOLYTE_IDEAL_4",
				"Power. I hope to one day rise to the top of my faith's religious hierarchy. (Lawful)");
		traits.put("ACOLYTE_IDEAL_5",
				"Faith. I trust that my deity will guide my actions. I have faith that if I work hard, things will go well. (Lawful)");
		traits.put("ACOLYTE_IDEAL_6",
				"Aspiration. I seek to prove myself worthy of my god's favor by matching my action against his or her teachings. (Any)");
		traits.put("ACOLYTE_BOND_1", "I would die to recover an ancient relic of my faith that was lost long ago.");
		traits.put("ACOLYTE_BOND_2",
				"I will someday get revenge on the corrupt temple hierarchy who branded me a heretic.");
		traits.put("ACOLYTE_BOND_3", "I owe my life to the priest who took me in when my parents died.");
		traits.put("ACOLYTE_BOND_4", "Everything I do is for the common people.");
		traits.put("ACOLYTE_BOND_5", "I will do anything to protect the temple where I served.");
		traits.put("ACOLYTE_BOND_6",
				"I seek to preserve a sacred text that my enemies consider heretical and seek to destroy.");
		traits.put("ACOLYTE_FLAW_1", "I judge others harshly, and myself even more severely.");
		traits.put("ACOLYTE_FLAW_2", "I put too much trust in those who wield power within my temple's hierarchy.");
		traits.put("ACOLYTE_FLAW_3",
				"My piety sometimes leads me to blindly trust those that profess faith in my god.");
		traits.put("ACOLYTE_FLAW_4", "I am inflexible in my thinking.");
		traits.put("ACOLYTE_FLAW_5", "I am suspicious of strangers and expect the worst of them.");
		traits.put("ACOLYTE_FLAW_6",
				"Once I pick a goal, I become obsessed with it to the detriment of everything else in my life.");
		traits.put("CHARLATAN_TRAIT_1", "I fall in and out of love easily, and am always pursuing someone.");
		traits.put("CHARLATAN_TRAIT_2",
				"I have a joke for every occasion, especially occasions where humor is inappropriate.");
		traits.put("CHARLATAN_TRAIT_3", "Flattery is my preferred trick for getting what I want.");
		traits.put("CHARLATAN_TRAIT_4", "I'm a born gambler who can't resist taking a risk for a potential payoff.");
		traits.put("CHARLATAN_TRAIT_5", "I lie about almost everything, even when there's no good reason to.");
		traits.put("CHARLATAN_TRAIT_6", "Sarcasm and insults are my weapons of choice.");
		traits.put("CHARLATAN_TRAIT_7",
				"I keep multiple holy symbols on me and invoke whatever deity might come in useful at any given moment.");
		traits.put("CHARLATAN_TRAIT_8", "I pocket anything I see that might have some value.");
		traits.put("CHARLATAN_IDEAL_1", "Independence. I am a free spirit--no one tells me what to do. (Chaotic)");
		traits.put("CHARLATAN_IDEAL_2",
				"Fairness. I never target people who can't afford to lose a few coins. (Lawful)");
		traits.put("CHARLATAN_IDEAL_3",
				"Charity. I distribute the money I acquire to the people who really need it. (Good)");
		traits.put("CHARLATAN_IDEAL_4", "Creativity. I never run the same con twice. (Chaotic)");
		traits.put("CHARLATAN_IDEAL_5",
				"Friendship. Material goods come and go. Bonds of friendship last forever. (Good)");
		traits.put("CHARLATAN_IDEAL_6", "Aspiration. I'm determined to make something of myself. (Any)");
		traits.put("CHARLATAN_BOND_1",
				"I fleeced the wrong person and must work to ensure that this individual never crosses paths with me or those I care about.");
		traits.put("CHARLATAN_BOND_2",
				"I owe everything to my mentor--a horrible person who's probably rotting in jail somewhere.");
		traits.put("CHARLATAN_BOND_3",
				"Somewhere out there, I have a child who doesn't know me. I'm making the world better for him or her.");
		traits.put("CHARLATAN_BOND_4",
				"I come from a noble family, and one day I'll reclaim my lands and title from those who stole them from me.");
		traits.put("CHARLATAN_BOND_5", "A powerful person killed someone I love. Some day soon, I'll have my revenge.");
		traits.put("CHARLATAN_BOND_6",
				"I swindled and ruined a person who didn't deserve it. I seek to atone for my misdeeds but might never be able to forgive myself.");
		traits.put("CHARLATAN_FLAW_1", "I can't resist a pretty face.");
		traits.put("CHARLATAN_FLAW_2",
				"I'm always in debt. I spend my ill-gotten gains on decadent luxuries faster than I bring them in.");
		traits.put("CHARLATAN_FLAW_3", "I'm convinced that no one could ever fool me the way I fool others.");
		traits.put("CHARLATAN_FLAW_4",
				"I'm too greedy for my own good. I can't resist taking a risk if there's money involved.");
		traits.put("CHARLATAN_FLAW_5", "I can't resist swindling people who are more powerful than me.");
		traits.put("CHARLATAN_FLAW_6",
				"I hate to admit it and will hate myself for it, but I'll run and preserve my own hide if the going gets tough.");
		traits.put("CRIMINAL_TRAIT_1", "I always have a plan for what to do when things go wrong.");
		traits.put("CRIMINAL_TRAIT_2",
				"I am always calm, no matter what the situation. I never raise my voice or let my emotions control me.");
		traits.put("CRIMINAL_TRAIT_3",
				"The first thing I do in a new place is note the locations of everything valuable--or where such things could be hidden.");
		traits.put("CRIMINAL_TRAIT_4", "I would rather make a new friend than a new enemy.");
		traits.put("CRIMINAL_TRAIT_5",
				"I am incredibly slow to trust. Those who seem the fairest often have the most to hide.");
		traits.put("CRIMINAL_TRAIT_6", "I don't pay attention to the risks in a situation. Never tell me the odds.");
		traits.put("CRIMINAL_TRAIT_7", "The best way to get me to do something is to tell me I can't do it.");
		traits.put("CRIMINAL_TRAIT_8", "I blow up at the slightest insult.");
		traits.put("CRIMINAL_IDEAL_1", "Honor. I don't steal from others in the trade. (Lawful)");
		traits.put("CRIMINAL_IDEAL_2",
				"Freedom. Chains are meant to be broken, as are those who would forge them. (Chaotic)");
		traits.put("CRIMINAL_IDEAL_3", "Charity. I steal from the wealthy so that I can help people in need. (Good)");
		traits.put("CRIMINAL_IDEAL_4", "Greed. I will do whatever it takes to become wealthy. (Evil)");
		traits.put("CRIMINAL_IDEAL_5",
				"People. I'm loyal to my friends, not to any ideals, and everyone else can take a trip down the Styx for all I care. (Neutral)");
		traits.put("CRIMINAL_IDEAL_6", "Redemption. There's a spark of good in everyone. (Good)");
		traits.put("CRIMINAL_BOND_1", "I'm trying to pay off an old debt I owe to a generous benefactor.");
		traits.put("CRIMINAL_BOND_2", "My ill-gotten gains go to support my family.");
		traits.put("CRIMINAL_BOND_3", "Something important was taken from me, and I aim to steal it back.");
		traits.put("CRIMINAL_BOND_4", "I will become the greatest thief that ever lived.");
		traits.put("CRIMINAL_BOND_5", "I'm guilty of a terrible crime. I hope I can redeem myself for it.");
		traits.put("CRIMINAL_BOND_6",
				"Someone I loved died because of a mistake I made. That will never happen again.");
		traits.put("CRIMINAL_FLAW_1",
				"When I see something valuable, I can't think about anything but how to steal it.");
		traits.put("CRIMINAL_FLAW_2",
				"When faced with a choice between money and my friends, I usually choose the money.");
		traits.put("CRIMINAL_FLAW_3", "If there's a plan, I'll forget it. If I don't forget it, I'll ignore it.");
		traits.put("CRIMINAL_FLAW_4", "I have a 'tell' that reveals when I'm lying.");
		traits.put("CRIMINAL_FLAW_5", "I turn tail and run when things look bad.");
		traits.put("CRIMINAL_FLAW_6",
				"An innocent person is in prison for a crime that I committed. I'm okay with that.");
		traits.put("ENTERTAINER_TRAIT_1", "I know a story relevant to almost every situation.");
		traits.put("ENTERTAINER_TRAIT_2", "Whenever I come to a new place, I collect local rumors and spread gossip.");
		traits.put("ENTERTAINER_TRAIT_3", "I'm a hopeless romantic, always searching for that 'special someone.' ");
		traits.put("ENTERTAINER_TRAIT_4",
				"Nobody stays angry at me or around me for long, since I can defuse any amount of tension.");
		traits.put("ENTERTAINER_TRAIT_5", "I love a good insult, even one directed at me.");
		traits.put("ENTERTAINER_TRAIT_6", "I get bitter if I'm not the center of attention.");
		traits.put("ENTERTAINER_TRAIT_7", "I'll settle for nothing less than perfection.");
		traits.put("ENTERTAINER_TRAIT_8", "I change my mood or my mind as quickly as I change key in a song.");
		traits.put("ENTERTAINER_IDEAL_1", "Beauty. When I perform, I make the world better than it was. (Good)");
		traits.put("ENTERTAINER_IDEAL_2",
				"Tradition. The stories, legends, and songs of the past must never be forgotten, for they teach us who we are. (Lawful)");
		traits.put("ENTERTAINER_IDEAL_3", "Creativity. The world is in need of new ideas and bold action. (Chaotic)");
		traits.put("ENTERTAINER_IDEAL_4", "Greed. I'm only in it for the money and fame. (Evil)");
		traits.put("ENTERTAINER_IDEAL_5",
				"People. I like seeing the smiles on people's faces when I perform. That's all that matters. (Neutral)");
		traits.put("ENTERTAINER_IDEAL_6",
				"Honesty. Art should reflect the soul; it should come from within and reveal who we really are. (Any)");
		traits.put("ENTERTAINER_BOND_1",
				"My instrument is my most treasured possession, and it reminds me of someone I love.");
		traits.put("ENTERTAINER_BOND_2", "Someone stole my precious instrument, and someday I'll get it back.");
		traits.put("ENTERTAINER_BOND_3", "I want to be famous, whatever it takes.");
		traits.put("ENTERTAINER_BOND_4",
				"I idolize a hero of the old tales and measure my deeds against that person's.");
		traits.put("ENTERTAINER_BOND_5", "I will do anything to prove myself superior to my hated rival.");
		traits.put("ENTERTAINER_BOND_6", "I would do anything for the other members of my old troupe.");
		traits.put("ENTERTAINER_FLAW_1", "I'll do anything to win fame and renown.");
		traits.put("ENTERTAINER_FLAW_2", "I'm a sucker for a pretty face.");
		traits.put("ENTERTAINER_FLAW_3",
				"A scandal prevents me from ever going home again. That kind of trouble seems to follow me around.");
		traits.put("ENTERTAINER_FLAW_4",
				"I once satirized a noble who still wants my head. It was a mistake that I will likely repeat.");
		traits.put("ENTERTAINER_FLAW_5",
				"I have trouble keeping my true feelings hidden. My sharp tongue lands me in trouble.");
		traits.put("ENTERTAINER_FLAW_6", "Despite my best efforts, I am unreliable to my friends.");
		traits.put("FOLK_HERO_TRAIT_1", "I judge people by their actions, not their words.");
		traits.put("FOLK_HERO_TRAIT_2", "If someone is in trouble, I'm always ready to lend help.");
		traits.put("FOLK_HERO_TRAIT_3",
				"When I set my mind to something. I follow through no matter what gets in my way.");
		traits.put("FOLK_HERO_TRAIT_4",
				"I have a strong sense of fair play and always try to find the most equitable solution to arguments.");
		traits.put("FOLK_HERO_TRAIT_5",
				"I'm confident in my own abilities and do what I can to instill confidence in others.");
		traits.put("FOLK_HERO_TRAIT_6", "Thinking is for other people. I prefer action.");
		traits.put("FOLK_HERO_TRAIT_7", "I misuse long words in an attempt to sound smarter.");
		traits.put("FOLK_HERO_TRAIT_8", "I get bored easily. When am I going to get on with my destiny?");
		traits.put("FOLK_HERO_IDEAL_1", "Respect. People deserve to be treated with dignity and respect. (Good)");
		traits.put("FOLK_HERO_IDEAL_2",
				"Fairness. No one should get preferential treatment before the law, and no one is above the law. (Lawful)");
		traits.put("FOLK_HERO_IDEAL_3", "Freedom. Tyrants must not be allowed to oppress the people. (Chaotic)");
		traits.put("FOLK_HERO_IDEAL_4", "Might. If I bevcome strong, I can take what I want--what I deserve. (Evil)");
		traits.put("FOLK_HERO_IDEAL_5", "Sincerity. There's no good in pretending to be something I'm not. (Neutral)");
		traits.put("FOLK_HERO_IDEAL_6", "Destiny. Nothing and no one can steer me away from my higher calling. (Any)");
		traits.put("FOLK_HERO_BOND_1",
				"I have a family, but no idea where they are. One day, I hope to see them again.");
		traits.put("FOLK_HERO_BOND_2", "I worked the land, I love the land, and I will protect the land.");
		traits.put("FOLK_HERO_BOND_3",
				"A proud noble once gave me a horrible beating, and I will take my revent on any bully I encounter.");
		traits.put("FOLK_HERO_BOND_4",
				"My tools are symbols of my past life, and I carry them so that I will never forget my roots.");
		traits.put("FOLK_HERO_BOND_5", "I protect those who cannot protect themselves.");
		traits.put("FOLK_HERO_BOND_6", "I wish my childhood sweetheart had come with me to pursue my destiny.");
		traits.put("FOLK_HERO_FLAW_1", "The tyrant who rules my land will stop at nothing to see me killed.");
		traits.put("FOLK_HERO_FLAW_2",
				"I'm convinced of the significance of my destiny, and blind to my shortcomings and the risk of failure.");
		traits.put("FOLK_HERO_FLAW_3",
				"The people who knew me when I was young know my shameful secret, so I can never go home again.");
		traits.put("FOLK_HERO_FLAW_4", "I have a weakness for the vices of the city, especially hard drink.");
		traits.put("FOLK_HERO_FLAW_5",
				"Secretly, I believe that things would be better if I were a tyrant lording over the land.");
		traits.put("FOLK_HERO_FLAW_6", "I have trouble trusting in my allies.");
		traits.put("GUILD_ARTISAN_TRAIT_1",
				"I believe that anything worth doing is worth doing right. I can't help it, I'm a perfectionist.");
		traits.put("GUILD_ARTISAN_TRAIT_2", "I'm a snob who looks down on those who can't appreciate fine art.");
		traits.put("GUILD_ARTISAN_TRAIT_3", "I always want to know how things work and what makes people tick.");
		traits.put("GUILD_ARTISAN_TRAIT_4", "I'm full of witty aphorisms and have a proverb for every occasion.");
		traits.put("GUILD_ARTISAN_TRAIT_5", "I'm rude to people who lack my commitment to hard work and fair play.");
		traits.put("GUILD_ARTISAN_TRAIT_6", "I like to talk at length about my profession.");
		traits.put("GUILD_ARTISAN_TRAIT_7",
				"I don't part with my money easily and will haggle tirelessly to get the best deal possible.");
		traits.put("GUILD_ARTISAN_TRAIT_8",
				"I'm well known for my work, and I want to make sure everyone appreciates it. I'm always taken aback when people haven't heard of me.");
		traits.put("GUILD_ARTISAN_IDEAL_1",
				"Community. It is the duty of all civilized people to strengthen the bonds of community and the security of civilization. (Lawful)");
		traits.put("GUILD_ARTISAN_IDEAL_2",
				"Generosity. My talents were given to me so that I could use them to benefit the world. (Good)");
		traits.put("GUILD_ARTISAN_IDEAL_3",
				"Freedom. Everyone should be free to pursue his or her own livelihood. (Chaotic)");
		traits.put("GUILD_ARTISAN_IDEAL_4", "Greed. I'm only in it for the money. (Evil)");
		traits.put("GUILD_ARTISAN_IDEAL_5",
				"People. I'm committed to the people I care about, not to ideals. (Neutral)");
		traits.put("GUILD_ARTISAN_IDEAL_6", "Aspiration. I work hard to be the best there is at my craft. (Any)");
		traits.put("GUILD_ARTISAN_BOND_1",
				"The workshop where I learned my trade is the most important place in the world to me.");
		traits.put("GUILD_ARTISAN_BOND_2",
				"I created a great work for someone, and then found them unworthy to receive it. I'm still looking for someone worthy.");
		traits.put("GUILD_ARTISAN_BOND_3", "I owe my guild a great debt for forging me into the person I am today.");
		traits.put("GUILD_ARTISAN_BOND_4", "I pursue wealth to secure someone's love.");
		traits.put("GUILD_ARTISAN_BOND_5",
				"One day I will return to my guild and prove that I am the greatest artisan of them all.");
		traits.put("GUILD_ARTISAN_BOND_6",
				"I will get revenge on the evil forces that destroyed my place of business and ruined my livelihood.");
		traits.put("GUILD_ARTISAN_FLAW_1", "I'll do anything to get my hands on something rare or priceless.");
		traits.put("GUILD_ARTISAN_FLAW_2", "I'm quick to assume that someone is trying to cheat me.");
		traits.put("GUILD_ARTISAN_FLAW_3", "No one must ever learn that I once stole money from the guild coffers.");
		traits.put("GUILD_ARTISAN_FLAW_4", "I'm never satisfied with what I have. I always want more.");
		traits.put("GUILD_ARTISAN_FLAW_5", "I would kill to acquire a noble title.");
		traits.put("GUILD_ARTISAN_FLAW_6",
				"I'm horribly jealous of anyone who can outshine my handiwork. Everywhere I go, I'm surrounded by rivals.");
		traits.put("HERMIT_TRAIT_1",
				"I've been isolated for so long that I rarely speak, preferring gestures and the occasional grunt.");
		traits.put("HERMIT_TRAIT_2", "I am utterly serene, even in the face of disaster.");
		traits.put("HERMIT_TRAIT_3",
				"The leader of my community had something wise to say on every topic, and I am eager to share that wisdom.");
		traits.put("HERMIT_TRAIT_4", "I feel tremendous empathy for all who suffer.");
		traits.put("HERMIT_TRAIT_5", "I'm oblivious to etiquette and social expectations.");
		traits.put("HERMIT_TRAIT_6", "I connect everything that happens to me to a grand, cosmic plan.");
		traits.put("HERMIT_TRAIT_7",
				"I often get lost in my own thoughts and contemplation, becoming oblivious to my surroundings.");
		traits.put("HERMIT_TRAIT_8", "I am working on a grand philosophical theory and love sharing my ideas.");
		traits.put("HERMIT_IDEAL_1",
				"Greater Good. My gifts are meant to be shared with all, not used for my own benefit. (Good)");
		traits.put("HERMIT_IDEAL_2",
				"Logic. Emotions must not cloud our sense of what is right and true, or our logical thinking. (Lawful)");
		traits.put("HERMIT_IDEAL_3", "Free Thinking. Inquiry and curiosity are the pillars of progress. (Chaotic)");
		traits.put("HERMIT_IDEAL_4",
				"Power. Solitude and contemplation are paths toward mystical or magical power. (Evil)");
		traits.put("HERMIT_IDEAL_5",
				"Live and Let Live. Meddling in the affairs of others only causes trouble. (Neutral)");
		traits.put("HERMIT_IDEAL_6", "Self-Knowledge. If you know yourself, there's nothing left to know. (Any)");
		traits.put("HERMIT_BOND_1",
				"Nothing is more important than the other members of my hermitage, order, or association.");
		traits.put("HERMIT_BOND_2",
				"I entered seclusion to hide from the ones who might still be hunting me. I must someday confront them.");
		traits.put("HERMIT_BOND_3",
				"I'm still seeking the enlightenment I pursued in my seclusion, and it still eludes me.");
		traits.put("HERMIT_BOND_4", "I entered seclusion because I loved someone I could not have.");
		traits.put("HERMIT_BOND_5", "Should my discovery come to light, it could bring ruin to the world.");
		traits.put("HERMIT_BOND_6", "My isolation gave me great insight into a great evil that only I can destroy.");
		traits.put("HERMIT_FLAW_1", "Now that I've returned to the world, I enjoy its delights a little too much.");
		traits.put("HERMIT_FLAW_2",
				"I harbor dark, bloodthirsty thoughts that my isolation and meditation failed to quell.");
		traits.put("HERMIT_FLAW_3", "I am dogmatic in my thoughts and philosophy.");
		traits.put("HERMIT_FLAW_4", "I let me need to win arguments overshadow friendships and harmony.");
		traits.put("HERMIT_FLAW_5", "I'd risk too much to uncover a lost bit of knowledge.");
		traits.put("HERMIT_FLAW_6", "I like keeping secrets and won't share them with anyone.");
		traits.put("NOBLE_TRAIT_1",
				"My eloquent flattery makes everyone I talk to feel like the most wonderful and important person in the world.");
		traits.put("NOBLE_TRAIT_2", "The common folk love me for my kindness and generosity.");
		traits.put("NOBLE_TRAIT_3",
				"No one could doubt by looking at my regal bearing that I am a cut above the unwashed masses.");
		traits.put("NOBLE_TRAIT_4", "I take great pains to always look my best and follow the latest fashions.");
		traits.put("NOBLE_TRAIT_5",
				"I don't like to get my hands dirty, and I won't be caught dead in unsuitable accomodations.");
		traits.put("NOBLE_TRAIT_6",
				"Despite my noble birth, I do not place myself above other folk. We all have the same blood.");
		traits.put("NOBLE_TRAIT_7", "My favor, once lost, is lost forever.");
		traits.put("NOBLE_TRAIT_8", "If you do me an injury, I will crush you, ruin your name, and salt your fields.");
		traits.put("NOBLE_IDEAL_1",
				"Respect. Respect is due to me because of my position, but all people regardless of station deserve to be treated with dignity. (Good)");
		traits.put("NOBLE_IDEAL_2",
				"Responsibility. It is my duty to respect the authority of those above me, just as those below me must respect mine. (Lawful)");
		traits.put("NOBLE_IDEAL_3",
				"Independence. I must prove that I can handle myself without the coddling of my family. (Chaotic)");
		traits.put("NOBLE_IDEAL_4", "Power. If I can attain more power, no one will tell me what to do. (Evil)");
		traits.put("NOBLE_IDEAL_5", "Family. Blood runs thicker than water. (Any)");
		traits.put("NOBLE_IDEAL_6",
				"Noble Obligation. It is my duty to protect and care for the people beneath me. (Good)");
		traits.put("NOBLE_BOND_1", "I will face any challenge to win the approval of my family.");
		traits.put("NOBLE_BOND_2", "My house's alliance with another noble family must be sustained at all costs.");
		traits.put("NOBLE_BOND_3", "Nothing is more important than the other members of my family.");
		traits.put("NOBLE_BOND_4", "I am in love with the heir of a family that my family despises.");
		traits.put("NOBLE_BOND_5", "My loyalty to my sovereign is unwavering.");
		traits.put("NOBLE_BOND_6", "The common folk must see me as a hero of the people.");
		traits.put("NOBLE_FLAW_1", "I secretly believe that everyone is beneath me.");
		traits.put("NOBLE_FLAW_2", "I hide a truly scandalous secret that could ruin my family forever.");
		traits.put("NOBLE_FLAW_3",
				"I too often hear veiled insults and threats in every word addressed to me, and I'm quick to anger.");
		traits.put("NOBLE_FLAW_4", "I have an insatiable desire for carnal pleasures.");
		traits.put("NOBLE_FLAW_5", "In fact, the world does revolve around me.");
		traits.put("NOBLE_FLAW_6", "By my words and actions, I often bring shame to my family.");
		traits.put("OUTLANDER_TRAIT_1", "I'm driven by a wanderlust that led me away from home.");
		traits.put("OUTLANDER_TRAIT_2", "I watch over my friends as if they were a litter of newborn pups.");
		traits.put("OUTLANDER_TRAIT_3",
				"I once ran twenty-five miles without stopping to warn my clan of an approaching orc horde. I'd do it again if I had to.");
		traits.put("OUTLANDER_TRAIT_4", "I have a lesson for every situation, drawn from observing nature.");
		traits.put("OUTLANDER_TRAIT_5",
				"I place no stock in wealthy or well-mannered folk. Money and manners won't save you from a hungry owlbear.");
		traits.put("OUTLANDER_TRAIT_6",
				"I'm always picking things up, absently fiddling with them, and sometimes accidentally breaking them.");
		traits.put("OUTLANDER_TRAIT_7", "I feel far more comfortable around animals than people.");
		traits.put("OUTLANDER_TRAIT_8", "I was, in fact, raised by wolves.");
		traits.put("OUTLANDER_IDEAL_1",
				"Change. Life is like the seasons, in constant change, and we must change with it. (Chaotic)");
		traits.put("OUTLANDER_IDEAL_2",
				"Greater Good. It is each person's responsibility to make the most happiness for the whole tribe. (Good)");
		traits.put("OUTLANDER_IDEAL_3", "Honor. If I dishonor myself, I dishonor my whole clan. (Lawful)");
		traits.put("OUTLANDER_IDEAL_4", "Might. The strongest are meant to rule. (Evil)");
		traits.put("OUTLANDER_IDEAL_5",
				"Nature. The natural world is more important than all the constructs of civilization. (Neutral)");
		traits.put("OUTLANDER_IDEAL_6", "Glory. I must earn glory in battle, for myself and my clan. (Any)");
		traits.put("OUTLANDER_BOND_1",
				"My family, clan, or tribe is the most important thing in my life, even when they are far from me.");
		traits.put("OUTLANDER_BOND_2", "An injury to the unspoiled wilderness of my home is an injury to me.");
		traits.put("OUTLANDER_BOND_3", "I will bring terrible wrath down on the evildoers who destroyed my homeland.");
		traits.put("OUTLANDER_BOND_4",
				"I am the last of my tribe, and it is up to me to ensure their names enter legend.");
		traits.put("OUTLANDER_BOND_5",
				"I suffer awful visions of a coming disaster and will do anything to prevent it.");
		traits.put("OUTLANDER_BOND_6", "It is my duty to provide children to sustain my tribe.");
		traits.put("OUTLANDER_FLAW_1", "I am too enamored of ale, wine, and other intoxicants.");
		traits.put("OUTLANDER_FLAW_2", "There's no room for caution in a life lived to the fullest.");
		traits.put("OUTLANDER_FLAW_3",
				"I remember every insult I've received and nurse a silent resentment toward anyone who's ever wronged me.");
		traits.put("OUTLANDER_FLAW_4", "I am slow to trust members of other races, tribes, and societies.");
		traits.put("OUTLANDER_FLAW_5", "Violence is my answer to almost any challenge.");
		traits.put("OUTLANDER_FLAW_6",
				"Don't expect me to save those who can't save themselves. It is nature's way that the strong thrive and the weak perish.");
		traits.put("SAGE_TRAIT_1", "I use polysyllabic words that convey the impression of great erudition.");
		traits.put("SAGE_TRAIT_2",
				"I've read every book in the world's greatest libraries, or I like to boast that I have.");
		traits.put("SAGE_TRAIT_3",
				"I'm used to helping out those who aren't as smart sa I am, and I patiently explain anything and everything to others.");
		traits.put("SAGE_TRAIT_4", "There's nothing I like more than a good mystery.");
		traits.put("SAGE_TRAIT_5",
				"I'm willing to listen to every side of an argument before I make my own judgement.");
		traits.put("SAGE_TRAIT_6",
				"I… speak… slowly… when talking… to idiots, … which… almost… everyone… is… compared… to me.");
		traits.put("SAGE_TRAIT_7", "I am horribly, horribly awkward in social situations.");
		traits.put("SAGE_TRAIT_8", "I'm convinced that people are always trying to steal my secrets.");
		traits.put("SAGE_IDEAL_1", "Knowledge. The path to power and self-improvement is through knowledge. (Neutral)");
		traits.put("SAGE_IDEAL_2", "Beauty. What is beautiful points us beyond itself toward what is true. (Good)");
		traits.put("SAGE_IDEAL_3", "Logic. Emotions must not cloud our logical thinking. (Lawful)");
		traits.put("SAGE_IDEAL_4",
				"No Limits. Nothing should fetter the infinite possibility inherent in all existence. (Chaotic)");
		traits.put("SAGE_IDEAL_5", "Power. Knowledge is the path to power and domination. (Evil)");
		traits.put("SAGE_IDEAL_6", "Self-Improvement. The goal of a life of study is the betterment of oneself. (Any)");
		traits.put("SAGE_BOND_1", "It is my duty to protect my students.");
		traits.put("SAGE_BOND_2",
				"I have an ancient text that holds terrible secrets that must not fall into the wrong hands.");
		traits.put("SAGE_BOND_3", "I work to preserve a library, university, scriptorium, or monastery.");
		traits.put("SAGE_BOND_4", "My life's work is a series of tomes related to a specific field of lore.");
		traits.put("SAGE_BOND_5", "I've been searching my whole life for the answer to a certain question.");
		traits.put("SAGE_BOND_6", "I sold my soul for knowledge. I hope to do great deeds and win it back.");
		traits.put("SAGE_FLAW_1", "I am easily distracted by the promise of information.");
		traits.put("SAGE_FLAW_2",
				"Most people scream and run when they see a demon. I stop and take notes on its anatomy.");
		traits.put("SAGE_FLAW_3", "Unlocking an ancient mystery is worth the price of civilization.");
		traits.put("SAGE_FLAW_4", "I overlook obvious solutions in favor of complicated ones.");
		traits.put("SAGE_FLAW_5", "I speak without really thinking through my words, invariably insulting others.");
		traits.put("SAGE_FLAW_6", "I can't keep a secret to save my life, or anyone else's.");
		traits.put("SAILOR_TRAIT_1", "My friends know they can rely on me, no matter what.");
		traits.put("SAILOR_TRAIT_2", "I work hard so that I can play hard when the work is done.");
		traits.put("SAILOR_TRAIT_3", "I enjoy sailing into new ports and making new friends over a flagon of ale.");
		traits.put("SAILOR_TRAIT_4", "I stretch the truth for the sake of a good story.");
		traits.put("SAILOR_TRAIT_5", "To me, a tavern brawl is a nice way to get to know a new city.");
		traits.put("SAILOR_TRAIT_6", "I never pass up a friendly wager.");
		traits.put("SAILOR_TRAIT_7", "My language is as foul as an otyugh nest.");
		traits.put("SAILOR_TRAIT_8", "I like a job well done, especially if I can convince someone else to do it.");
		traits.put("SAILOR_IDEAL_1",
				"Respect. The thing that keeps a ship together is mutual respect between captain and crew. (Good)");
		traits.put("SAILOR_IDEAL_2", "Fairness. We all do the work, so we all share in the rewards. (Lawful)");
		traits.put("SAILOR_IDEAL_3",
				"Freedom. The sea is freedom, the freedom to go anywhere and do anything. (Chaotic)");
		traits.put("SAILOR_IDEAL_4", "Mastery. I'm a predator, and the other ships on the sea are my prey. (Evil)");
		traits.put("SAILOR_IDEAL_5", "People. I'm committed to my crewmates, not to ideals. (Neutral)");
		traits.put("SAILOR_IDEAL_6", "Aspiration. Someday I'll own my own ship and chart my own destiny. (Any)");
		traits.put("SAILOR_BOND_1", "I'm loyal to my captain first, everything else second.");
		traits.put("SAILOR_BOND_2", "The ship is most important; crewmates and captains come and go.");
		traits.put("SAILOR_BOND_3", "I'll always remember my first ship.");
		traits.put("SAILOR_BOND_4", "In a harbor town, I have a paramour whose eyes nearly stole me from the sea.");
		traits.put("SAILOR_BOND_5", "I was cheated out of my fair share of the profits, and I want to get my due.");
		traits.put("SAILOR_BOND_6",
				"Ruthless pirates murdered my captain and crewmates, plundered our ship, and left me to die. Vengeance will be mine.");
		traits.put("SAILOR_FLAW_1", "I follow orders, even if I think they're wrong.");
		traits.put("SAILOR_FLAW_2", "I'll say anything to avoid having to do extra work.");
		traits.put("SAILOR_FLAW_3",
				"Once someone questions my courage, I never back down no matter how dangerous the situation.");
		traits.put("SAILOR_FLAW_4", "Once I start drinking, it's hard for me to stop.");
		traits.put("SAILOR_FLAW_5", "I can't help but pocket loose coins and other trinkets I come across.");
		traits.put("SAILOR_FLAW_6", "My pride will probably lead to my destruction.");
		traits.put("SOLDIER_TRAIT_1", "I'm always polite and respectful.");
		traits.put("SOLDIER_TRAIT_2",
				"I'm haunted by memories of war. I can't get the images of violence out of my mind.");
		traits.put("SOLDIER_TRAIT_3", "I've lost too many friends, and I'm slow to make new ones.");
		traits.put("SOLDIER_TRAIT_4",
				"I'm full of inspiring and cautionary tales from my military experience relevant to almost every combat situation.");
		traits.put("SOLDIER_TRAIT_5", "I can stare down a hell hound without flinching.");
		traits.put("SOLDIER_TRAIT_6", "I enjoy being strong and like breaking things.");
		traits.put("SOLDIER_TRAIT_7", "I have a crude sense of humor.");
		traits.put("SOLDIER_TRAIT_8",
				"I face problems head-on. A simple, direct solution is the best path to success.");
		traits.put("SOLDIER_IDEAL_1", "Greater Good. Our lot is to lay down our lives in defense of others. (Good)");
		traits.put("SOLDIER_IDEAL_2", "Responsibility. I do what I must and obey just authority. (Lawful)");
		traits.put("SOLDIER_IDEAL_3",
				"Independence. When people follow orders blindly, they embrace a kind of tyranny. (Chaotic)");
		traits.put("SOLDIER_IDEAL_4", "Might. In life as in war, the stronger force wins. (Evil)");
		traits.put("SOLDIER_IDEAL_5",
				"Live and Let Live. Ideals aren't worth killing over or going to war for. (Neutral)");
		traits.put("SOLDIER_IDEAL_6", "Nation. My city, nation, or people are all that matter. (Any)");
		traits.put("SOLDIER_BOND_1", "I would still lay down my life for the people I served with.");
		traits.put("SOLDIER_BOND_2",
				"Someone saved my life on the battlefield. To this day, I will never leave a friend behind.");
		traits.put("SOLDIER_BOND_3", "My honor is my life.");
		traits.put("SOLDIER_BOND_4",
				"I'll never forget the crushing defeat my company suffered or the enemies who dealt it.");
		traits.put("SOLDIER_BOND_5", "Those who fight beside me are those worth dying for.");
		traits.put("SOLDIER_BOND_6", "I fight for those who cannot fight for themselves.");
		traits.put("SOLDIER_FLAW_1", "The monstrous enemy we face in battle still leaves me quivering with fear.");
		traits.put("SOLDIER_FLAW_2", "I have little respect for anyone who is not a proven warrior.");
		traits.put("SOLDIER_FLAW_3",
				"I made a terrible mistake in battle that cost many lives; and I would do anything to keep that mistake secret.");
		traits.put("SOLDIER_FLAW_4", "My hatred of my enemies is blind and unreasoning.");
		traits.put("SOLDIER_FLAW_5", "I obery the law, even if the law causes misery.");
		traits.put("SOLDIER_FLAW_6", "I'd rather eat my armor than admit when I'm wrong.");
		traits.put("URCHIN_TRAIT_1", "I hide scraps of food and trinkets away in my pockets.");
		traits.put("URCHIN_TRAIT_2", "I ask a lot of questions.");
		traits.put("URCHIN_TRAIT_3", "I like to squeeze into small places where no one else can get to me.");
		traits.put("URCHIN_TRAIT_4",
				"I sleep with my back to a wall or tree, with everything I own wrapped in a bundle in my arms.");
		traits.put("URCHIN_TRAIT_5", "I eat like a pig and have bad manners.");
		traits.put("URCHIN_TRAIT_6", "I think anyone who's nice to me is hiding evil intent.");
		traits.put("URCHIN_TRAIT_7", "I don't like to bathe.");
		traits.put("URCHIN_TRAIT_8", "I bluntly say what other people are hinting at or hiding.");
		traits.put("URCHIN_IDEAL_1", "Respect. All people, rich or poor, deserve respect. (Good)");
		traits.put("URCHIN_IDEAL_2",
				"Community. We have to take care of each other, because no one else is going to do it. (Lawful)");
		traits.put("URCHIN_IDEAL_3",
				"Change. The low are lifted up, and the high and mighty are brought down. Change is the nature of things. (Chaotic)");
		traits.put("URCHIN_IDEAL_4",
				"Retribution. The rich need to be shown what life and death are like in the gutters. (Evil)");
		traits.put("URCHIN_IDEAL_5", "People. I help the people who help me; that's what keeps us alive. (Neutral)");
		traits.put("URCHIN_IDEAL_6", "Aspiration. I'm going to prove that I'm worthy of a better life.");
		traits.put("URCHIN_BOND_1", "My town or city is my home, and I'll fight to defend it.");
		traits.put("URCHIN_BOND_2", "I sponsor an orphanage to keep others from enduring what I was forced to endure.");
		traits.put("URCHIN_BOND_3", "I owe my survival to another urchin who taught me to live on the streets.");
		traits.put("URCHIN_BOND_4", "I owe a debt I can never repay to the person who took pity on me.");
		traits.put("URCHIN_BOND_5",
				"I escaped my life of poverty by robbing an important person, and I'm wanted for it.");
		traits.put("URCHIN_BOND_6", "No one else should have to endure the hardships I've been through.");
		traits.put("URCHIN_FLAW_1", "If I'm outnumbered, I will run away from a fight.");
		traits.put("URCHIN_FLAW_2",
				"Gold seems like a lot of money to me, and I'll do just about anything for more of it.");
		traits.put("URCHIN_FLAW_3", "I will never fully trust anyone other than myself.");
		traits.put("URCHIN_FLAW_4", "I'd rather kill someone in their sleep then fight fair.");
		traits.put("URCHIN_FLAW_5", "It isn't stealing if I need it more than someone else.");
		traits.put("URCHIN_FLAW_6", "People who can't take care of themselves get what they deerve.");

		// villainous schemes from Dungeon Master's Guide
		traits.put("IMMORTALITY_1", "Acquire a legendary item to prolong life.");
		traits.put("IMMORTALITY_2", "Ascend to godhood.");
		traits.put("IMMORTALITY_3", "Become undead or obtain a younger body.");
		traits.put("IMMORTALITY_4", "Steal a planar creature's essence.");
		traits.put("INFLUENCE_1", "Seize a position of power or title.");
		traits.put("INFLUENCE_2", "Win a contest or tournament.");
		traits.put("INFLUENCE_3", "Win favor with a powerful individual.");
		traits.put("INFLUENCE_4", "Place a pawn in a position of power.");
		traits.put("MAGIC_1", "Obtain an ancient artifact.");
		traits.put("MAGIC_2", "Build a construct or magical device.");
		traits.put("MAGIC_3", "Carry out a deity's wishes.");
		traits.put("MAGIC_4", "Offer sacrifices to a deity.");
		traits.put("MAGIC_5", "Contact a lost deity or power.");
		traits.put("MAGIC_6", "Open a gate to another world.");
		traits.put("MAYHEM_1", "Fulfill an apocalyptic prophesy.");
		traits.put("MAYHEM_2", "Enact the vengeful will of a god or patron.");
		traits.put("MAYHEM_3", "Spread a vile contagion.");
		traits.put("MAYHEM_4", "Overthrow a government.");
		traits.put("MAYHEM_5", "Trigger a natural disaster.");
		traits.put("MAYHEM_6", "Utterly destroy a bloodline or clan.");
		traits.put("PASSION_1", "Prolong the life of a loved one.");
		traits.put("PASSION_2", "Prove worthy of another person's love.");
		traits.put("PASSION_3", "Raise or restore a dead loved one.");
		traits.put("PASSION_4", "Destroy rivals for another person's affection.");
		traits.put("POWER_1", "Conquer a region or incite a rebellion.");
		traits.put("POWER_2", "Seize control of an army.");
		traits.put("POWER_3", "Become the power behind the throne.");
		traits.put("POWER_4", "Gain the favor of a ruler.");
		traits.put("REVENGE_1", "Avenge a past humiliatoin or insult.");
		traits.put("REVENGE_2", "Avenge a past imprisonment or injury.");
		traits.put("REVENGE_3", "Avenge the death of a loved one.");
		traits.put("REVENGE_4", "Retrieve stolen property and punish the thief.");
		traits.put("WEALTH_1", "Control natural resources or trade.");
		traits.put("WEALTH_2", "Marry into wealth.");
		traits.put("WEALTH_3", "Plunder ancient ruins.");
		traits.put("WEALTH_4", "Steal land or goods or money.");

		// villainous methods from Dungeons Master's Guide
		traits.put("DEVASTATION_1", "Devastation: Blight (disease) kills crops.");
		traits.put("DEVASTATION_2", "Devastation: Crop failure.");
		traits.put("DEVASTATION_3", "Devastation: Drought kills crops.");
		traits.put("DEVASTATION_4", "Devastation: Food stolen or destroyed (famine).");
		traits.put("METHOD_A", "Assault or beatings.");
		traits.put("METHOD_B", "Assassination or bounty hunting.");
		traits.put("COERCION_1", "Coercion: Bribery.");
		traits.put("COERCION_2", "Coercion: Enticement/seduction.");
		traits.put("COERCION_3", "Coercion: Eviction.");
		traits.put("COERCION_4", "Coercion: Imprisonment.");
		traits.put("COERCION_5", "Coercion: Kidnapping.");
		traits.put("COERCION_6", "Coercion: Legal intimidation.");
		traits.put("COERCION_7", "Coercion: Press gangs.");
		traits.put("COERCION_8", "Coercion: Shackling.");
		traits.put("COERCION_9", "Coercion: Slavery.");
		traits.put("COERCION_10", "Coercion: Threats or harassment.");
		traits.put("CON_GAME_1", "Con Game: Breach of contract.");
		traits.put("CON_GAME_2", "Con Game: Cheating.");
		traits.put("CON_GAME_3", "Con Game: Fast talking.");
		traits.put("CON_GAME_4", "Con Game: Fine print.");
		traits.put("CON_GAME_5", "Con Game: Fraud or swindling.");
		traits.put("CON_GAME_6", "Con Game: Quackery or tricks.");
		traits.put("DEFAMATION_1", "Defamation: Framing.");
		traits.put("DEFAMATION_2", "Defamation: Gossip or slander.");
		traits.put("DEFAMATION_3", "Defamation: Humiliation.");
		traits.put("DEFAMATION_4", "Defamation: Libel or insults.");
		traits.put("METHOD_C", "Dueling.");
		traits.put("EXECUTION_1", "Execution: Beheading.");
		traits.put("EXECUTION_2", "Execution: Burning at the stake.");
		traits.put("EXECUTION_3", "Execution: Burying alive.");
		traits.put("EXECUTION_4", "Execution: Crucifixion.");
		traits.put("EXECUTION_5", "Execution: Drawing and quartering.");
		traits.put("EXECUTION_6", "Execution: Hanging.");
		traits.put("EXECUTION_7", "Execution: Impalement.");
		traits.put("EXECUTION_8", "Execution: Sacrifice (living)");
		traits.put("METHOD_D", "Impersonation or disguise.");
		traits.put("METHOD_E", "Lying or perjury.");
		traits.put("MAGIC_MAYHEM_1", "Magic Mayhem: Hauntings.");
		traits.put("MAGIC_MAYHEM_2", "Magic Mayhem: Illusions.");
		traits.put("MAGIC_MAYHEM_3", "Magic Mayhem: Infernal bargains.");
		traits.put("MAGIC_MAYHEM_4", "Magic Mayhem: Mind control.");
		traits.put("MAGIC_MAYHEM_5", "Magic Mayhem: Petrification.");
		traits.put("MAGIC_MAYHEM_6", "Magic Mayhem: Raising or animating the dead.");
		traits.put("MAGIC_MAYHEM_7", "Magic Mayhem: Summoning monsters.");
		traits.put("MAGIC_MAYHEM_8", "Magic Mayhem: Weather control.");
		traits.put("MURDER_1", "Murder: Assassination.");
		traits.put("MURDER_2", "Murder: Cannibalism.");
		traits.put("MURDER_3", "Murder: Dismemberment.");
		traits.put("MURDER_4", "Murder: Drowning.");
		traits.put("MURDER_5", "Murder: Electrocution.");
		traits.put("MURDER_6", "Murder: Euthanasia (involuntary).");
		traits.put("MURDER_7", "Murder: Disease.");
		traits.put("MURDER_8", "Murder: Poisoning.");
		traits.put("MURDER_9", "Murder: Stabbing.");
		traits.put("MURDER_10", "Murder: Strangulation or suffocation.");
		traits.put("METHOD_F", "Neglect.");
		traits.put("POLITICS_1", "Politics: Betrayal or treason.");
		traits.put("POLITICS_2", "Politics: Conspiracy.");
		traits.put("POLITICS_3", "Politics: Espionage or spying.");
		traits.put("POLITICS_4", "Politics: Genocide.");
		traits.put("POLITICS_5", "Politics: Oppression.");
		traits.put("POLITICS_6", "Politics: Raising taxes.");
		traits.put("RELIGION_1", "Religion: Curses.");
		traits.put("RELIGION_2", "Religion: Desecration.");
		traits.put("RELIGION_3", "Religion: False gods.");
		traits.put("RELIGION_4", "Religion: Heresy or cults.");
		traits.put("METHOD_G", "Stalking.");
		traits.put("THIEVERY_1", "Thievery: Arson.");
		traits.put("THIEVERY_2", "Thievery: Blackmail or extortion.");
		traits.put("THIEVERY_3", "Thievery: Burglary (nonviolent).");
		traits.put("THIEVERY_4", "Thievery: Counterfeiting.");
		traits.put("THIEVERY_5", "Thievery: Highway robbery.");
		traits.put("THIEVERY_6", "Thievery: Looting (destructive).");
		traits.put("THIEVERY_7", "Thievery: Mugging.");
		traits.put("THIEVERY_8", "Thievery: Poaching.");
		traits.put("THIEVERY_9", "Thievery: Seizing property.");
		traits.put("THIEVERY_10", "Thievery: Smuggling.");
		traits.put("TORTURE_1", "Torture: Acid.");
		traits.put("TORTURE_2", "Torture: Blinding.");
		traits.put("TORTURE_3", "Torture: Branding.");
		traits.put("TORTURE_4", "Torture: Racking.");
		traits.put("TORTURE_5", "Torture: Thumbscrews.");
		traits.put("TORTURE_6", "Torture: Whipping.");
		traits.put("VICE_1", "Vice: Adultery.");
		traits.put("VICE_2", "Vice: Drugs or alcohol.");
		traits.put("VICE_3", "Vice: Gambling.");
		traits.put("VICE_4", "Vice: Seduction.");
		traits.put("WARFARE_1", "Warfare: Ambush.");
		traits.put("WARFARE_2", "Warfare: Invasion.");
		traits.put("WARFARE_3", "Warfare: Massacre.");
		traits.put("WARFARE_4", "Warfare: Mercenaries.");
		traits.put("WARFARE_5", "Warfare: Rebellion.");
		traits.put("WARFARE_6", "Warfare: Terrorism.");

		// framing events from Dungeon Master's Guide
		traits.put("EVENT_1", "Anniversary of a monarch's reign.");
		traits.put("EVENT_2", "Anniversary of an important event.");
		traits.put("EVENT_3", "Arena event.");
		traits.put("EVENT_4", "Arrival of a caravan or ship.");
		traits.put("EVENT_5", "Arrival of a circus.");
		traits.put("EVENT_6", "Arrival of an important personage.");
		traits.put("EVENT_7", "Arrival of marching modrons.");
		traits.put("EVENT_8", "Artistic performance.");
		traits.put("EVENT_9", "Athletic event.");
		traits.put("EVENT_10", "Birth of a child.");
		traits.put("EVENT_11", "Birthday of an important personage.");
		traits.put("EVENT_12", "Civic festival.");
		traits.put("EVENT_13", "Appearance of a comet.");
		traits.put("EVENT_14", "Memorial of a past tragedy.");
		traits.put("EVENT_15", "Consecration of a new temple.");
		traits.put("EVENT_16", "Coronation.");
		traits.put("EVENT_17", "Council meeting.");
		traits.put("EVENT_18", "Equinox or solstice.");
		traits.put("EVENT_19", "Execution.");
		traits.put("EVENT_20", "Fertility festival.");
		traits.put("EVENT_21", "Full moon.");
		traits.put("EVENT_22", "Funeral.");
		traits.put("EVENT_23", "Graduation of cadets or wizards.");
		traits.put("EVENT_24", "Harvest festival.");
		traits.put("EVENT_25", "Holy day.");
		traits.put("EVENT_26", "Investiture of a knight or other noble.");
		traits.put("EVENT_27", "Lunar eclipse.");
		traits.put("EVENT_28", "Midsummer festival.");
		traits.put("EVENT_29", "Midwinter festival.");
		traits.put("EVENT_30", "Migration of monsters.");
		traits.put("EVENT_31", "Monarch's ball.");
		traits.put("EVENT_32", "New moon.");
		traits.put("EVENT_33", "New year.");
		traits.put("EVENT_34", "Pardoning of a prisoner.");
		traits.put("EVENT_35", "Planar conjunction.");
		traits.put("EVENT_36", "Planetary alignment.");
		traits.put("EVENT_37", "Priestly investiture.");
		traits.put("EVENT_38", "Procession of ghosts.");
		traits.put("EVENT_39", "Memorial for soldier lost in war.");
		traits.put("EVENT_40", "Royal address or proclamation.");
		traits.put("EVENT_41", "Royal audience day.");
		traits.put("EVENT_42", "Signing of a treaty.");
		traits.put("EVENT_43", "Solar eclipse.");
		traits.put("EVENT_44", "Tournament.");
		traits.put("EVENT_45", "Trial.");
		traits.put("EVENT_46", "Violent uprising.");
		traits.put("EVENT_47", "Wedding or anniversary.");
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Career randomCareer() {
		return Dice.randomFromArray(ALL_CAREERS);
	}

	public static Career selectCareer(Actor actor) {
		// TODO - additional selection methods
		Career candidate;
		candidate = ALL_CAREERS[Dice.roll(ALL_CAREERS.length) - 1];

		return candidate;
	}

	public static String randomTrait(Career career) {
		String trait = "";
		int dice;

		if (career.equals(ACOLYTE)) {
			dice = Dice.roll(ACOLYTE_TRAITS.length) - 1;
			trait = traits.get(ACOLYTE_TRAITS[dice]);
		} else if (career.equals(CHARLATAN)) {
			dice = Dice.roll(CHARLATAN_TRAITS.length) - 1;
			trait = traits.get(CHARLATAN_TRAITS[dice]);
		} else if (career.equals(CRIMINAL)) {
			dice = Dice.roll(CRIMINAL_TRAITS.length) - 1;
			trait = traits.get(CRIMINAL_TRAITS[dice]);
		} else if (career.equals(ENTERTAINER)) {
			dice = Dice.roll(ENTERTAINER_TRAITS.length) - 1;
			trait = traits.get(ENTERTAINER_TRAITS[dice]);
		} else if (career.equals(FOLK_HERO)) {
			dice = Dice.roll(FOLK_HERO_TRAITS.length) - 1;
			trait = traits.get(FOLK_HERO_TRAITS[dice]);
		} else if (career.equals(GUILD_ARTISAN)) {
			dice = Dice.roll(GUILD_ARTISAN_TRAITS.length) - 1;
			trait = traits.get(GUILD_ARTISAN_TRAITS[dice]);
		} else if (career.equals(HERMIT)) {
			dice = Dice.roll(HERMIT_TRAITS.length) - 1;
			trait = traits.get(HERMIT_TRAITS[dice]);
		} else if (career.equals(NOBLE)) {
			dice = Dice.roll(NOBLE_TRAITS.length) - 1;
			trait = traits.get(NOBLE_TRAITS[dice]);
		} else if (career.equals(OUTLANDER)) {
			dice = Dice.roll(OUTLANDER_TRAITS.length) - 1;
			trait = traits.get(OUTLANDER_TRAITS[dice]);
		} else if (career.equals(SAGE)) {
			dice = Dice.roll(SAGE_TRAITS.length) - 1;
			trait = traits.get(SAGE_TRAITS[dice]);
		} else if (career.equals(SAILOR)) {
			dice = Dice.roll(SAILOR_TRAITS.length) - 1;
			trait = traits.get(SAILOR_TRAITS[dice]);
		} else if (career.equals(SOLDIER)) {
			dice = Dice.roll(SOLDIER_TRAITS.length) - 1;
			trait = traits.get(SOLDIER_TRAITS[dice]);
		} else if (career.equals(URCHIN)) {
			dice = Dice.roll(URCHIN_TRAITS.length) - 1;
			trait = traits.get(URCHIN_TRAITS[dice]);
		}

		return trait;
	}

	public static String randomTrait(Career career, String trait) {
		String secondTrait = trait;

		while (secondTrait.compareTo(trait) == 0) {
			secondTrait = randomTrait(career);
		}

		return secondTrait;
	}

	public static String randomIdeal(Career career) {
		String trait = "";
		int dice;

		if (career.equals(ACOLYTE)) {
			dice = Dice.roll(ACOLYTE_IDEALS.length) - 1;
			trait = traits.get(ACOLYTE_IDEALS[dice]);
		} else if (career.equals(CHARLATAN)) {
			dice = Dice.roll(CHARLATAN_IDEALS.length) - 1;
			trait = traits.get(CHARLATAN_IDEALS[dice]);
		} else if (career.equals(CRIMINAL)) {
			dice = Dice.roll(CRIMINAL_IDEALS.length) - 1;
			trait = traits.get(CRIMINAL_IDEALS[dice]);
		} else if (career.equals(ENTERTAINER)) {
			dice = Dice.roll(ENTERTAINER_IDEALS.length) - 1;
			trait = traits.get(ENTERTAINER_IDEALS[dice]);
		} else if (career.equals(FOLK_HERO)) {
			dice = Dice.roll(FOLK_HERO_IDEALS.length) - 1;
			trait = traits.get(FOLK_HERO_IDEALS[dice]);
		} else if (career.equals(GUILD_ARTISAN)) {
			dice = Dice.roll(GUILD_ARTISAN_IDEALS.length) - 1;
			trait = traits.get(GUILD_ARTISAN_IDEALS[dice]);
		} else if (career.equals(HERMIT)) {
			dice = Dice.roll(HERMIT_IDEALS.length) - 1;
			trait = traits.get(HERMIT_IDEALS[dice]);
		} else if (career.equals(NOBLE)) {
			dice = Dice.roll(NOBLE_IDEALS.length) - 1;
			trait = traits.get(NOBLE_IDEALS[dice]);
		} else if (career.equals(OUTLANDER)) {
			dice = Dice.roll(OUTLANDER_IDEALS.length) - 1;
			trait = traits.get(OUTLANDER_IDEALS[dice]);
		} else if (career.equals(SAGE)) {
			dice = Dice.roll(SAGE_IDEALS.length) - 1;
			trait = traits.get(SAGE_IDEALS[dice]);
		} else if (career.equals(SAILOR)) {
			dice = Dice.roll(SAILOR_IDEALS.length) - 1;
			trait = traits.get(SAILOR_IDEALS[dice]);
		} else if (career.equals(SOLDIER)) {
			dice = Dice.roll(SOLDIER_IDEALS.length) - 1;
			trait = traits.get(SOLDIER_IDEALS[dice]);
		} else if (career.equals(URCHIN)) {
			dice = Dice.roll(URCHIN_IDEALS.length) - 1;
			trait = traits.get(URCHIN_IDEALS[dice]);
		}

		return trait;
	}

	public static String randomBond(Career career) {
		String trait = "";
		int dice;

		if (career.equals(ACOLYTE)) {
			dice = Dice.roll(ACOLYTE_BONDS.length) - 1;
			trait = traits.get(ACOLYTE_BONDS[dice]);
		} else if (career.equals(CHARLATAN)) {
			dice = Dice.roll(CHARLATAN_BONDS.length) - 1;
			trait = traits.get(CHARLATAN_BONDS[dice]);
		} else if (career.equals(CRIMINAL)) {
			dice = Dice.roll(CRIMINAL_BONDS.length) - 1;
			trait = traits.get(CRIMINAL_BONDS[dice]);
		} else if (career.equals(ENTERTAINER)) {
			dice = Dice.roll(ENTERTAINER_BONDS.length) - 1;
			trait = traits.get(ENTERTAINER_BONDS[dice]);
		} else if (career.equals(FOLK_HERO)) {
			dice = Dice.roll(FOLK_HERO_BONDS.length) - 1;
			trait = traits.get(FOLK_HERO_BONDS[dice]);
		} else if (career.equals(GUILD_ARTISAN)) {
			dice = Dice.roll(GUILD_ARTISAN_BONDS.length) - 1;
			trait = traits.get(GUILD_ARTISAN_BONDS[dice]);
		} else if (career.equals(HERMIT)) {
			dice = Dice.roll(HERMIT_BONDS.length) - 1;
			trait = traits.get(HERMIT_BONDS[dice]);
		} else if (career.equals(NOBLE)) {
			dice = Dice.roll(NOBLE_BONDS.length) - 1;
			trait = traits.get(NOBLE_BONDS[dice]);
		} else if (career.equals(OUTLANDER)) {
			dice = Dice.roll(OUTLANDER_BONDS.length) - 1;
			trait = traits.get(OUTLANDER_BONDS[dice]);
		} else if (career.equals(SAGE)) {
			dice = Dice.roll(SAGE_BONDS.length) - 1;
			trait = traits.get(SAGE_BONDS[dice]);
		} else if (career.equals(SAILOR)) {
			dice = Dice.roll(SAILOR_BONDS.length) - 1;
			trait = traits.get(SAILOR_BONDS[dice]);
		} else if (career.equals(SOLDIER)) {
			dice = Dice.roll(SOLDIER_BONDS.length) - 1;
			trait = traits.get(SOLDIER_BONDS[dice]);
		} else if (career.equals(URCHIN)) {
			dice = Dice.roll(URCHIN_BONDS.length) - 1;
			trait = traits.get(URCHIN_BONDS[dice]);
		}

		return trait;
	}

	public static String randomFlaw(Career career) {
		String trait = "";
		int dice;

		if (career.equals(ACOLYTE)) {
			dice = Dice.roll(ACOLYTE_FLAWS.length) - 1;
			trait = traits.get(ACOLYTE_FLAWS[dice]);
		} else if (career.equals(CHARLATAN)) {
			dice = Dice.roll(CHARLATAN_FLAWS.length) - 1;
			trait = traits.get(CHARLATAN_FLAWS[dice]);
		} else if (career.equals(CRIMINAL)) {
			dice = Dice.roll(CRIMINAL_FLAWS.length) - 1;
			trait = traits.get(CRIMINAL_FLAWS[dice]);
		} else if (career.equals(ENTERTAINER)) {
			dice = Dice.roll(ENTERTAINER_FLAWS.length) - 1;
			trait = traits.get(ENTERTAINER_FLAWS[dice]);
		} else if (career.equals(FOLK_HERO)) {
			dice = Dice.roll(FOLK_HERO_FLAWS.length) - 1;
			trait = traits.get(FOLK_HERO_FLAWS[dice]);
		} else if (career.equals(GUILD_ARTISAN)) {
			dice = Dice.roll(GUILD_ARTISAN_FLAWS.length) - 1;
			trait = traits.get(GUILD_ARTISAN_FLAWS[dice]);
		} else if (career.equals(HERMIT)) {
			dice = Dice.roll(HERMIT_FLAWS.length) - 1;
			trait = traits.get(HERMIT_FLAWS[dice]);
		} else if (career.equals(NOBLE)) {
			dice = Dice.roll(NOBLE_FLAWS.length) - 1;
			trait = traits.get(NOBLE_FLAWS[dice]);
		} else if (career.equals(OUTLANDER)) {
			dice = Dice.roll(OUTLANDER_FLAWS.length) - 1;
			trait = traits.get(OUTLANDER_FLAWS[dice]);
		} else if (career.equals(SAGE)) {
			dice = Dice.roll(SAGE_FLAWS.length) - 1;
			trait = traits.get(SAGE_FLAWS[dice]);
		} else if (career.equals(SAILOR)) {
			dice = Dice.roll(SAILOR_FLAWS.length) - 1;
			trait = traits.get(SAILOR_FLAWS[dice]);
		} else if (career.equals(SOLDIER)) {
			dice = Dice.roll(SOLDIER_FLAWS.length) - 1;
			trait = traits.get(SOLDIER_FLAWS[dice]);
		} else if (career.equals(URCHIN)) {
			dice = Dice.roll(URCHIN_FLAWS.length) - 1;
			trait = traits.get(URCHIN_FLAWS[dice]);
		}

		return trait;
	}

	public static String meansMotiveOpportunity() {
		String string = "Dramatic Conflict";
		
		string += String.format("%n%s", randomMotive());
		string += String.format("%n%s", randomMeans());
		string += String.format("%n%s", randomOpportunity());
		
		return string;
	}

	public static String randomMotive() {
		// TODO - additional selection methods
		// String candidate = traits.get(Dice.randomFromArray(ALL_SCHEMES));

		return "Motive: " + traits.get(Dice.randomFromArray(ALL_SCHEMES));
	}

	public static String randomMeans() {
		// TODO - additional selection methods
		String[] array,
				generics = { "METHOD_A", "METHOD_B", "METHOD_C", "METHOD_D", "METHOD_E", "METHOD_F", "METHOD_G" };
		int dice = Dice.roll(20);

		if (dice == 1)
			array = DEVASTATION_ONLY;
		else if (dice == 2)
			array = COERCION_ONLY;
		else if (dice == 3)
			array = CON_GAME_ONLY;
		else if (dice == 4)
			array = DEFAMATION_ONLY;
		else if (dice == 5)
			array = EXECUTION_ONLY;
		else if (dice == 6)
			array = MAGIC_MAYHEM_ONLY;
		else if (dice == 7)
			array = MURDER_ONLY;
		else if (dice == 8)
			array = POLITICS_ONLY;
		else if (dice == 9)
			array = RELIGION_ONLY;
		else if (dice == 10)
			array = THIEVERY_ONLY;
		else if (dice == 11)
			array = TORTURE_ONLY;
		else if (dice == 12)
			array = VICE_ONLY;
		else if (dice == 13)
			array = WARFARE_ONLY;
		else
			array = generics;

		String candidate;
		candidate = traits.get(array[Dice.roll(array.length) - 1]);

		return "Means: " + candidate;
	}

	public static String randomOpportunity() {
		String[] events = { "EVENT_1", "EVENT_2", "EVENT_3", "EVENT_4", "EVENT_5", "EVENT_6", "EVENT_7", "EVENT_8",
				"EVENT_9", "EVENT_10", "EVENT_11", "EVENT_12", "EVENT_13", "EVENT_14", "EVENT_15", "EVENT_16",
				"EVENT_17", "EVENT_18", "EVENT_19", "EVENT_20", "EVENT_21", "EVENT_22", "EVENT_23", "EVENT_24",
				"EVENT_25", "EVENT_26", "EVENT_27", "EVENT_28", "EVENT_29", "EVENT_30", "EVENT_31", "EVENT_32",
				"EVENT_33", "EVENT_34", "EVENT_35", "EVENT_36", "EVENT_37", "EVENT_38", "EVENT_39", "EVENT_40",
				"EVENT_41", "EVENT_42", "EVENT_43", "EVENT_44", "EVENT_45", "EVENT_46", "EVENT_47" };
		int dice = Dice.roll(events.length) - 1;

		String candidate;
		if (Dice.roll(50) < 50)
			candidate = traits.get(events[dice]);
		else
			candidate = traits.get(events[dice]) + " -AND- " + traits.get(events[Dice.roll(events.length) - 1]);

		return "Opportunity: " + candidate;
	}
}
