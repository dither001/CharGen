package com.norvendae.rules.dnd5e;

import java.util.EnumSet;

import com.norvendae.rules.misc.Dice;

import actor.Player;

public enum Language {
		COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING, ORCISH, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN, UNDERCOMMON, SUPERNAL, DRUIDIC, THIEVES_CANT;

	private static final Language[] NONSECRET_LANGUAGES = { COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN,
				HALFLING, ORCISH, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN,
				UNDERCOMMON };
		private static final Language[] COMMON_LANGUAGES = { COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN,
				HALFLING, ORCISH };
		private static final Language[] EXOTIC_LANGUAGES = { ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL,
				PRIMORDIAL, SYLVAN, UNDERCOMMON };

		/*
		 * STATIC METHODS
		 */
		public static Language[] commonLanguages() {
			return COMMON_LANGUAGES;
		}

		public static Language[] exoticLanguages() {
			return EXOTIC_LANGUAGES;
		}

		public static Language[] nonsecretLanguages() {
			return NONSECRET_LANGUAGES;
		}

		public static Language randomCommonLanguage() {
			return Dice.randomFromArray(COMMON_LANGUAGES);
		}

		public static Language randomExoticLanguage() {
			return Dice.randomFromArray(EXOTIC_LANGUAGES);
		}

		public static void addLanguage(Language language, Player actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.add(language);
			actor.setLanguages(languages);
		}

		public static void learnNonsecretLanguage(Player actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.addAll((EnumSet<Language>) Dice.randomAddToSet(NONSECRET_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

		public static void learnCommonLanguage(Player actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.addAll((EnumSet<Language>) Dice.randomAddToSet(COMMON_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

		public static void learnExoticLanguage(Player actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() == null)
				languages = EnumSet.noneOf(Language.class);
			else
				languages = actor.getLanguages();

			languages.addAll((EnumSet<Language>) Dice.randomAddToSet(EXOTIC_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

//		public static void setupLanguages(Player actor) {
//			EnumSet<Language> languages;
//			if (actor.getLanguages() == null)
//				languages = EnumSet.noneOf(Language.class);
//			else
//				languages = actor.getLanguages();
//			int skillsToAdd = 0;
//
//			Race race = actor.getRace();
//			if (race.equals(HUMAN)) {
//				languages.add(COMMON);
//				skillsToAdd = +1;
//
//			} else if (race.equals(DRAGONBORN)) {
//				languages.add(COMMON);
//				languages.add(DRACONIC);
//
//			} else if (race.equals(HILL_DWARF)) {
//				languages.add(COMMON);
//				languages.add(DWARVISH);
//
//			} else if (race.equals(DARK_ELF)) {
//				languages.add(COMMON);
//				languages.add(ELVISH);
//
//			} else if (race.equals(HIGH_ELF)) {
//				languages.add(COMMON);
//				languages.add(ELVISH);
//				skillsToAdd = +1;
//
//			} else if (race.equals(WOOD_ELF)) {
//				languages.add(COMMON);
//				languages.add(ELVISH);
//
//			} else if (race.equals(LIGHTFOOT_HALFLING)) {
//				languages.add(COMMON);
//				languages.add(HALFLING);
//
//			} else if (race.equals(FOREST_GNOME)) {
//				languages.add(COMMON);
//				languages.add(GNOMISH);
//
//			} else if (race.equals(HALF_ELF)) {
//				languages.add(COMMON);
//				skillsToAdd += 1;
//
//			} else if (race.equals(HALF_ORC)) {
//				languages.add(COMMON);
//				languages.add(ORCISH);
//
//			} else if (race.equals(TIEFLING)) {
//				languages.add(COMMON);
//				languages.add(INFERNAL);
//
//			}
//
//			languages.addAll((EnumSet<Language>) Dice.randomAddToSet(skillsToAdd, COMMON_LANGUAGES, languages));
//			actor.setLanguages(languages);
//		}
	}