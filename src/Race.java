import java.util.EnumSet;

public enum Race implements Option {
	HUMAN, DRAGONBORN, HALF_ELF, HALF_ORC, TIEFLING,
	// two dwarf subraces
	HILL_DWARF, MOUNTAIN_DWARF,
	// three elf subraces
	HIGH_ELF, WOOD_ELF, DARK_ELF,
	// two gnome subraces
	FOREST_GNOME, TINKER_GNOME,
	// two half subraces
	LIGHTFOOT_HALFLING, STOUTHEART_HALFLING;

	/*
	 * LANGUAGE ENUM
	 */
	public static enum Language {
		COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING, ORCISH, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN, UNDERCOMMON, SUPERNAL, DRUIDIC, THIEVES_CANT;

		private static final Language[] NONSECRET_LANGUAGES = { COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN,
				HALFLING, ORCISH, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN,
				UNDERCOMMON };
		private static final Language[] COMMON_LANGUAGES = { COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING,
				ORCISH };
		private static final Language[] EXOTIC_LANGUAGES = { ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL,
				PRIMORDIAL, SYLVAN, UNDERCOMMON };

		public static Language randomCommonLanguage() {
			return Dice.randomFromArray(COMMON_LANGUAGES);
		}

		public static Language randomExoticLanguage() {
			return Dice.randomFromArray(EXOTIC_LANGUAGES);
		}

		public static void addLanguage(Language language, Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.add(language);
			actor.setLanguages(languages);
		}

		public static void learnNonsecretLanguage(Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.addAll((EnumSet<Language>) Dice.addToSet(NONSECRET_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

		public static void learnCommonLanguage(Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() != null)
				languages = actor.getLanguages();
			else
				languages = EnumSet.noneOf(Language.class);

			languages.addAll((EnumSet<Language>) Dice.addToSet(COMMON_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

		public static void learnExoticLanguage(Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() == null)
				languages = EnumSet.noneOf(Language.class);
			else
				languages = actor.getLanguages();

			languages.addAll((EnumSet<Language>) Dice.addToSet(EXOTIC_LANGUAGES, languages));
			actor.setLanguages(languages);
		}

		public static void setupLanguages(Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() == null)
				languages = EnumSet.noneOf(Language.class);
			else
				languages = actor.getLanguages();
			int skillsToAdd = 0;

			Race race = actor.getRace();
			if (race.equals(HUMAN)) {
				languages.add(COMMON);
				skillsToAdd = +1;

			} else if (race.equals(DRAGONBORN)) {
				languages.add(COMMON);
				languages.add(DRACONIC);

			} else if (race.equals(HILL_DWARF)) {
				languages.add(COMMON);
				languages.add(DWARVISH);

			} else if (race.equals(DARK_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);

			} else if (race.equals(HIGH_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);
				skillsToAdd = +1;

			} else if (race.equals(WOOD_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);

			} else if (race.equals(LIGHTFOOT_HALFLING)) {
				languages.add(COMMON);
				languages.add(HALFLING);

			} else if (race.equals(FOREST_GNOME)) {
				languages.add(COMMON);
				languages.add(GNOMISH);

			} else if (race.equals(HALF_ELF)) {
				languages.add(COMMON);
				skillsToAdd += 1;

			} else if (race.equals(HALF_ORC)) {
				languages.add(COMMON);
				languages.add(ORCISH);

			} else if (race.equals(TIEFLING)) {
				languages.add(COMMON);
				languages.add(INFERNAL);

			}

			languages.addAll((EnumSet<Language>) Dice.addToSet(skillsToAdd, COMMON_LANGUAGES, languages));
			actor.setLanguages(languages);
		}
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static final String[] DRAGONBORN_FEMALE = { "AKRA", "AASATHRA", "ANTRARA", "ARAVA", "BIRI", "BLENDAETH",
			"BURANA", "CHASSATH", "DAAR", "DENTRATHA", "DOUDRA", "DRIINDAR", "EGGREN", "FARIDEH", "FINDEX", "FURRELE",
			"GESRETHE", "GILKASS", "HARANN", "HAVILAR", "HETHRESS", "HILLANOT", "JAXI", "JEZEAN", "JHERI", "KADANA",
			"KAVA", "KORINN", "MEGREN", "MIJIRA", "MISHANN", "NALA", "NUTHRA", "PERRA", "POGRNIX", "PYXRIN", "QUESPA",
			"RAIANN", "REZENA", "RULOTH", "SAPHARA", "SAVARAN", "SORA", "SURINA", "SYNTHRIN", "TATYAN", "THAVA",
			"UADJIT", "VEZERA", "ZYKROFF" };
	private static final String[] DRAGONBORN_MALE = { "ADREZ", "ARJHAN", "AZZAKH", "BALASAR", "BARADAD", "BHARASH",
			"BIDREKED", "DADALAN", "DAZZAZN", "DIRECRIS", "DONAAR", "FAX", "GARGAX", "GHESH", "GORBUNDUS", "GREETHEN",
			"HESKAN", "HIRRATHAK", "IDREX", "KALADAN", "KERKAD", "KIIRITH", "KRIV", "MAAGOG", "MEDRASH", "MEHEN",
			"MOZIKTH", "MREKSH", "MUGRUNDEN", "NADARR", "NITHTHER", "NORTKRUUTH", "NYKKAN", "PANJED", "PATRIN",
			"PIJJRIK", "QUARETHON", "RATHKRAN", "RHOGAR", "RIVAAN", "SETHREKAR", "SHAMASH", "SHEDINN", "SRORTHEN",
			"TARHUN", "TORINN", "TRYNNICUS", "VALOREAN", "VRONDISS", "ZENDAAR" };
	private static final String[] DWARF_FEMALE = { "ANBERA", "ARTIN", "AUDHILD", "BALIFRA", "BARBENA", "BARDRYN",
			"BOLHILD", "DAGNAL", "DARIFF", "DELRE", "DIESA", "ELDETH", "ERIDRED", "FALKRUNN", "FALLTHRA", "FINELLEN",
			"GILLYDD", "GUNNLODA", "GURDIS", "HELGRET", "HELJA", "HLIN", "ILDE", "JARANA", "KATHRA", "KILIA",
			"KRISTRYD", "LIFTRASA", "MARASTYR", "MARDRED", "MORANA", "NALAED", "NORA", "NURKANA", "ORIFF", "OVINA",
			"RISWYNN", "SANNL", "THERLIN", "THODRIS", "TORBERA", "TORDRID", "TORGGA", "URSHAR", "VALIDA", "VISTRA",
			"VONANA", "WERYDD", "WHURDRED", "YURGUNN" };
	private static final String[] DWARF_MALE = { "ADRIK", "ALBERICH", "BAERN", "BARENDD", "BELORIL", "BROTTOR", "DAIN",
			"DALGAL", "DARRAK", "DELG", "DUERGATH", "DWORIC", "EBERK", "EINKIL", "ELAIM", "ERIAS", "FALLOND", "FARGRIM",
			"GARDAIN", "GILTHUR", "GIMGEN", "GIMURT", "HARBEK", "KILDRAK", "KILVAR", "MORGRAN", "MORKRAL", "NALRAL",
			"NORDAK", "NURAVAL", "OLORIC", "OLUNT", "ORSIK", "OSKAR", "RANGRIM", "REIRAK", "RURIK", "TAKLINN",
			"THORADIN", "THORIN", "THRADAL", "TORDEK", "TRAUBON", "TRAVOK", "ULFGAR", "URAIM", "VEIT", "VONBIN",
			"VONDAL", "WHURBIN" };
	private static final String[] ELF_FEMALE = { "ADRIE", "AHINAR", "ALTHAEA", "ANASTRIANNA", "ANDRASTE", "ANTINUA",
			"ARARA", "BAELITAE", "BETHRYNNA", "BIREL", "CAELYNN", "CHAEDI", "CLAIRA", "DARA", "DRUSILA", "ELAMA",
			"ENNA", "FARAL", "FELOSIAL", "HATAE", "IELENIA", "ILANIS", "IRANN", "JARSALI", "JELENNETH", "KEYLETH",
			"LSHANNA", "LIA", "MAIATHAH", "MALQUIS", "MERIELE", "MIALEE", "MYATHETHIL", "NAIVARA", "QUELENNA",
			"QUILLATHE", "RIDARO", "SARIEL", "SHANAIRLA", "SHAVA", "SILAQUI", "SUMNES", "THEIRASTRA", "THIALA",
			"TIAATHQUE", "TRAUMLAM", "VADANIA", "VALANTHE", "VALNA", "XANAPHIA" };
	private static final String[] ELF_MALE = { "ADRAN", "AELAR", "AERDETH", "AHVAIN", "ARAMIL", "ARANNIS", "AUST",
			"AZAKI", "BEIRO", "BERRIAN", "CAELDRIM", "CARRIC", "DAYERETH", "DREALI", "EFFERIL", "EIRAVEL", "ENIALIS",
			"ERDAN", "EREVAN", "FIVIN", "GALINNDAN", "GENNAL", "HADARAI", "HALIMATH", "HEIAN", "HIMO", "IMMERAL",
			"IVELLIOS", "KORFEL", "LAMLIS", "LAUCIAN", "LUCAN", "MINDARTIS", "NAAL", "NUTAE", "PAELIAS", "PEREN",
			"QUARION", "RIARDON", "ROLEN", "SOVELISS", "SUHNAE", "THAMIOR", "THARIVOL", "THEREN", "THERIASTIS",
			"THERVAN", "UTHEMAR", "VANUATH", "VARIS" };
	private static final String[] GNOME_FEMALE = { "ABALABA", "BIMPNOTTIN", "BREENA", "BUVVIE", "CALLYBON", "CARAMIP",
			"CARLIN", "CUMPEN", "DALABA", "DONELLA", "DUVAMIL", "ELLA", "ELLYJOYBELL", "ELLYWICK", "ENIDDA", "LILLI",
			"LOOPMOTTIN", "LORILLA", "LUTHRA", "MARDNAB", "MEENA", "MENNY", "MUMPENA", "NISSA", "NUMBA", "NYX", "ODA",
			"OPPAH", "ORLA", "PANANA", "PYNTLE", "QUILLA", "RANALA", "REDDLEPOP", "ROYWYN", "SALANOP", "SHAMIL",
			"SIFFRESS", "SYMMA", "TANA", "TENENA", "TERVAROUND", "TIPPLETOE", "ULLA", "UNVERA", "VELOPTIMA", "VIRRA",
			"WAYWOCKET", "YEBE", "ZANNA" };
	private static final String[] GNOME_MALE = { "ALSTON", "ALVYN", "ANVERTH", "ARUMAWANN", "BILBRON", "BODDYNOCK",
			"BROCC", "BURGELL", "COCKABY", "CRAMPERNAP", "DABBLEDOB", "DLEBEAN", "DIMBLE", "EBERDEB", "ELDON", "ERKY",
			"FABLEN", "FIBBLESTIB", "FONKIN", "FROUSE", "FRUG", "GERBO", "GIMBLE", "GLIM", "IGDEN", "JABBLE", "JEBEDDO",
			"KELLEN", "KIPPER", "NAMFOODLE", "OPPLEBY", "ORRYN", "PAGGEN", "PALLABAR", "POG", "QUALEN", "RIBBLES",
			"RIMPLE", "ROONDAR", "SAPPLY", "SEEBO", "SENTEQ", "SINDRI", "UMPEN", "WARRYN", "WIGGENS", "WOBBLES",
			"WRENN", "ZAFFRAB", "ZOOK" };
	private static final String[] HALFLING_FEMALE = { "ALAIN", "ANDRY", "ANNE", "BELLA", "BLOSSOM", "BREE", "CALLIE",
			"CHENNA", "CORA", "DEE", "DELL", "EIDA", "ERAN", "EUPHEMIA", "GEORGINA", "GYNNIE", "HARRIET", "JASMINE",
			"JILLIAN", "JO", "KITHRI", "LAVINIA", "LIDDA", "MAEGAN", "MARIGOLD", "MERLA", "MYRIA", "NEDDA", "NIKKI",
			"NORA", "OLIVIA", "PAELA", "PEARL", "PENNIE", "PHILOMENA", "PORTIA", "ROBBIE", "ROSE", "SARAL", "SERAPHINA",
			"SHAENA", "STACEE", "TAWNA", "THEA", "TRYM", "TYNA", "VANI", "VERNA", "WELLA", "WILLOW" };
	private static final String[] HALFLING_MALE = { "ALTON", "ANDER", "BERNIE", "BOBBIN", "CADE", "CALLUS", "CORRIN",
			"DANNAD", "DANNIEL", "EDDIE", "EGART", "ELDON", "ERRICH", "FILDO", "FINNAN", "FRANKLIN", "GARRET", "GARTH",
			"GILBERT", "GOB", "HAROL", "IGOR", "JASPER", "KEITH", "KEVIN", "LAZAM", "LERRY", "LINDAL", "LYLE", "MERRIC",
			"MICAN", "MILO", "MORRIN", "NEBIN", "NEVIL", "OSBORN", "OSTRAN", "OSWALT", "PERRIN", "POPPY", "REED",
			"ROSCOE", "SAM", "SHARDON", "TYE", "ULMO", "WELLBY", "WENDEL", "WENNER", "WES" };
	private static final String[] HALF_ORC_FEMALE = { "ARHA", "BAGGI", "BENDOO", "BILGA", "BRAKKA", "CREEGA", "DRENNA",
			"EKK", "EMEN", "ENGOING", "FISTULA", "GAAKI", "GORGA", "GRAI", "GREEBA", "GRIGI", "GYNK", "HRATHY", "HURU",
			"ILGA", "KABBARG", "KANSIF", "LAGAZI", "LEZRE", "MURGEN", "MURDOOK", "MYEV", "NAGRETTE", "NEEGA", "NELLA",
			"NOGU", "OOLAH", "OOTAH", "OVAK", "OWNKA", "PUYET", "REEZA", "SHAUTHA", "SILGRE", "SUTHA", "TAGGA", "TAWAR",
			"TOMPH", "UBADA", "VANCHU", "VOLA", "VOLEN", "VORKA", "YEVELDA", "ZAGGA" };
	private static final String[] HALF_ORC_MALE = { "ARGRAN", "BRAAK", "BRUG", "CAGAK", "DENCH", "DORN", "DREN",
			"DRUUK", "FENG", "GELL", "GNARSH", "GRUMBAR", "GUBRASH", "HAGREN", "HENK", "HOGAR", "HOLG", "IMSH",
			"KARASH", "KARG", "KETH", "KORAG", "KRUSK", "LUBASH", "MEGGED", "MHURREN", "MORD", "MORG", "NIL", "NYBARG",
			"ODORR", "OHR", "RENDAR", "RESH", "RONT", "RRATH", "SARK", "SCRAG", "SHEGGEN", "SHUMP", "TANGLAR", "TARAK",
			"THAR", "THOKK", "TRAG", "UGARTH", "VARG", "VILBERG", "YURK", "ZED" };
	private static final String[] TIEFLING_FEMALE = { "AKTA", "ANAKIS", "ARMARA", "ASTARO", "AYM", "AZZA", "BELETH",
			"BRYSEIS", "BUNE", "CRIELLA", "DAMAIA", "DECARABIA", "EA", "GADREEL", "GOMORY", "HECAT", "ISHTE",
			"JEZEBETH", "KALI", "KALLISTA", "KASDEYA", "LERISSA", "LILITH", "MAKARIA", "MANEA", "MARKOSIAN", "MASTEMA",
			"NAAMAH", "NEMEIA", "NIJA", "ORIANNA", "OSAH", "PHELAIA", "PROSPERINE", "PURAH", "PYRA", "RIETA", "RONOBE",
			"RONWE", "SEDDIT", "SEERE", "SEKHMET", "SEMYAZA", "SHAVA", "SHAX", "SORATH", "UZZA", "VAPULA", "VEPAR",
			"VERIN" };
	private static final String[] TIEFLING_MALE = { "ABAD", "AHRIM", "AKMEN", "AMNON", "ANDRAM", "ASTAR", "BALAM",
			"BARAKAS", "BATHIN", "CAIM", "CHEM", "CIMER", "CRESSEL", "DAMAKOS", "EKEMON", "EURON", "FENRIZ", "FORCAS",
			"HABOR", "IADOS", "KAIRON", "LEUCIS", "MAMNEN", "MANTUS", "MARBAS", "MELECH", "MERIHIM", "MODEAN", "MORDAI",
			"MORMO", "MORTHOS", "NICOR", "NIRGEL", "ORIAX", "PAYMON", "PELAIOS", "PURSON", "QEMUEL", "RAAM", "RIMMON",
			"SAMMAL", "SKAMOS", "TETHREN", "THAMUZ", "THERAI", "VALAFAR", "VASSAGO", "XAPPAN", "ZEPAR", "ZEPHAN" };

	/*
	 * HUMAN NAMES
	 */
	private static final String[] ENGLISH_FEMALE = { "ADELAIDE", "AGATHA", "AGNES", "ALICE", "ALINE", "ANNE", "AVELINA",
			"AVICE", "BEATRICE", "CECILY", "EGELINA", "ELEANOR", "ELIZABETH", "ELLA", "ELOISE", "ELYSANDE", "EMENY",
			"EMMA", "EMMELINE", "ERMINA", "EVA", "GALIENA", "GEVA", "GISELLE", "GRISELDA", "HADWISA", "HELEN",
			"HERLEVA", "HUGOLINA", "IDA", "ISABELLA", "JACOBA", "JANE", "JOAN", "JULIANA", "KATHERINE", "MARGERY",
			"MARY", "MATILDA", "MAYNILD", "MILICENT", "ORIEL", "ROHESIA", "ROSALIND", "ROSAMUND", "SARAH", "SUSANNAH",
			"SYBIL", "WILLIAMINA", "YVONNE" };
	private static final String[] ENGLISH_MALE = { "ADAM", "ADELARD", "ALDOUS", "ANSELM", "ARNOLD", "BERNARD",
			"BERTRAM", "CHARLES", "CLEREBOLD", "CONRAD", "DIGGORY", "DROGO", "EVERARD", "FREDERICK", "GEOFFREY",
			"GERALD", "GILBERT", "GODFREY", "GUNTER", "GUY", "HENRY", "HEWARD", "HUBERT", "HUGH", "JOCELYN", "JOHN",
			"LANCE", "MANFRED", "MILES", "NICHOLAS", "NORMAN", "ODO", "PERCIVAL", "PETER", "RALF", "RANDAL", "RAYMOND",
			"REYNARD", "RICHARD", "ROBERT", "ROGER", "ROLAND", "ROLF", "SIMON", "THEOBALD", "THEODORIC", "THOMAS",
			"TIMM", "WILLIAM", "WYMAR" };

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Race selectRace() {
		Race race;
		int dice = Dice.roll(100);

		if (dice < 41) {
			race = HUMAN;
		} else if (dice < 41 + 15) {
			// DWARF
			dice = Dice.roll(3);
			if (dice < 3)
				race = Race.HILL_DWARF;
			else
				race = Race.MOUNTAIN_DWARF;

		} else if (dice < 41 + 30) {
			// ELF
			dice = Dice.roll(6);
			if (dice < 4)
				race = Race.WOOD_ELF;
			else if (dice < 6)
				race = Race.HIGH_ELF;
			else
				race = Race.DARK_ELF;

		} else if (dice < 41 + 45) {
			// HALFLING
			dice = Dice.roll(3);
			if (dice < 3)
				race = Race.LIGHTFOOT_HALFLING;
			else
				race = Race.STOUTHEART_HALFLING;

		} else {
			// other races
			dice = Dice.roll(10);
			if (dice == 1 || dice == 2 || dice == 3)
				race = Race.HALF_ELF;
			else if (dice == 4 || dice == 5)
				race = Race.DRAGONBORN;
			else if (dice == 6 || dice == 7)
				race = Race.TIEFLING;
			else if (dice == 8)
				race = Race.FOREST_GNOME;
			else if (dice == 9)
				race = Race.TINKER_GNOME;
			else
				race = Race.HALF_ORC;

		}

		return race;
	}

	public static void applyRacialFeatures(Actor actor) {
		applyRacialFeatures(selectRace(), actor);
	}

	private static void applyRacialFeatures(Race race, Actor actor) {
		actor.setRace(race);

		// all races grant languages
		EnumSet<Language> languages;
		if (actor.getLanguages() != null)
			languages = actor.getLanguages();
		else
			languages = EnumSet.noneOf(Language.class);

		// all races grant features
		EnumSet<Feature> features;
		if (actor.getFeatures() != null)
			features = actor.getFeatures();
		else
			features = EnumSet.noneOf(Feature.class);

		// most races grant skills
		EnumSet<Skill> skills;
		if (actor.getSkills() != null)
			skills = actor.getSkills();
		else
			skills = EnumSet.noneOf(Skill.class);

		// some races grant armor proficiency
		EnumSet<Armor> armor;
		if (actor.getArmorProficiency() != null)
			armor = actor.getArmorProficiency();
		else
			armor = EnumSet.noneOf(Armor.class);

		// some races grant weapon proficiency
		EnumSet<Weapon> weapons;
		if (actor.getWeaponProficiency() != null)
			weapons = actor.getWeaponProficiency();
		else
			weapons = EnumSet.noneOf(Weapon.class);

		// some races grant spells
		EnumSet<Spell> spellsKnown;
		if (actor.getSpellsKnown() != null)
			spellsKnown = actor.getSpellsKnown();
		else
			spellsKnown = EnumSet.noneOf(Spell.class);

		/*
		 * 
		 */
		if (race.equals(HILL_DWARF) || race.equals(MOUNTAIN_DWARF)) {
			/*
			 * DWARF FEATURES
			 */
			actor.setConstitution(actor.getConstitution() + 2);
			features.add(Feature.DWARVEN_ENCUMBRANCE);
			features.add(Feature.DARKVISION_60);
			//
			features.add(Feature.DWARF_WEAPON_TRAINING);
			weapons.add(Weapon.BATTLEAXE);
			weapons.add(Weapon.HANDAXE);
			weapons.add(Weapon.LIGHT_HAMMER);
			weapons.add(Weapon.WARHAMMER);
			//
			skills.add(Dice.randomFromArray(Skill.DWARF_TOOLS));
			features.add(Feature.STONECUNNING);
			//
			languages.add(Language.COMMON);
			languages.add(Language.DWARVISH);

			if (race.equals(HILL_DWARF)) {
				actor.setWisdom(actor.getWisdom() + 1);
				//
				features.add(Feature.DWARVEN_TOUGHNESS);

			} else if (race.equals(MOUNTAIN_DWARF)) {
				actor.setStrength(actor.getStrength() + 2);
				//
				features.add(Feature.DWARF_ARMOR_TRAINING);
				armor.addAll(Armor.getLightArmorList());
				armor.addAll(Armor.getMediumArmorList());
			}

		} else if (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF)) {
			/*
			 * ELF FEATURES
			 */
			actor.setDexterity(actor.getDexterity() + 2);
			// TODO
			// skills.add(Skill.PERCEPTION);
			features.add(Feature.FEY_ANCESTRY);
			features.add(Feature.ELF_TRANCE);
			//
			languages.add(Language.COMMON);
			languages.add(Language.ELVISH);

			if (race.equals(HIGH_ELF)) {
				actor.setIntelligence(actor.getIntelligence() + 1);
				features.add(Feature.DARKVISION_60);
				//
				features.add(Feature.ELF_WEAPON_TRAINING);
				weapons.add(Weapon.LONGSWORD);
				weapons.add(Weapon.LONGBOW);
				weapons.add(Weapon.SHORTSWORD);
				weapons.add(Weapon.SHORTBOW);
				//
				Spell.addCantripKnown(Class.WIZARD, spellsKnown);
				languages.addAll(Dice.addToSet(Language.COMMON_LANGUAGES, languages));

			} else if (race.equals(WOOD_ELF)) {
				actor.setWisdom(actor.getWisdom() + 1);
				features.add(Feature.DARKVISION_60);
				//
				features.add(Feature.ELF_WEAPON_TRAINING);
				weapons.add(Weapon.LONGSWORD);
				weapons.add(Weapon.LONGBOW);
				weapons.add(Weapon.SHORTSWORD);
				weapons.add(Weapon.SHORTBOW);

			} else if (race.equals(DARK_ELF)) {
				actor.setCharisma(actor.getCharisma() + 1);
				features.add(Feature.DARKVISION_120);
				features.add(Feature.SUNLIGHT_SENSITIVITY);
				//
				features.add(Feature.DROW_WEAPON_TRAINING);
				weapons.add(Weapon.HAND_CROSSBOW);
				weapons.add(Weapon.RAPIER);
				weapons.add(Weapon.SHORTSWORD);
				//
				spellsKnown.add(Spell.DANCING_LIGHTS);

			}

		} else if (race.equals(Race.LIGHTFOOT_HALFLING) || race.equals(Race.STOUTHEART_HALFLING)) {
			/*
			 * HALFLING FEaTURES
			 */
			actor.setDexterity(actor.getDexterity() + 2);
			features.add(Feature.HALFLING_LUCK);
			features.add(Feature.HALFLING_BRAVERY);
			features.add(Feature.HALFLING_NIMBLENESS);
			//
			languages.add(Language.COMMON);
			languages.add(Language.HALFLING);

			if (race.equals(LIGHTFOOT_HALFLING)) {
				actor.setCharisma(actor.getCharisma() + 1);
				//
				features.add(Feature.NATURALLY_STEALTHY);

			} else if (race.equals(STOUTHEART_HALFLING)) {
				actor.setConstitution(actor.getConstitution() + 1);
				//
				features.add(Feature.STOUT_RESILIENCE);

			}

		} else if (race.equals(Race.HUMAN)) {
			/*
			 * HUMAN FEATURES
			 */
			actor.setStrength(actor.getStrength() + 1);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
			//
			languages.add(Language.COMMON);
			languages.addAll(Dice.addToSet(Language.COMMON_LANGUAGES, languages));

		} else if (race.equals(Race.DRAGONBORN)) {
			/*
			 * DRAGONBORN FEATURES
			 */
			actor.setStrength(actor.getStrength() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
			//
			Option.dragonbornAncestry(actor);
			languages.add(Language.COMMON);
			languages.add(Language.DRACONIC);

		} else if (race.equals(Race.FOREST_GNOME) || race.equals(Race.TINKER_GNOME)) {
			/*
			 * GNOME FEATURES
			 */
			actor.setIntelligence(actor.getIntelligence() + 2);
			features.add(Feature.DARKVISION_60);
			features.add(Feature.GNOME_CUNNING);
			//
			languages.add(Language.COMMON);
			languages.add(Language.GNOMISH);

			if (race.equals(Race.FOREST_GNOME)) {
				actor.setDexterity(actor.getDexterity() + 1);
				//
				features.add(Feature.NATURAL_ILLUSIONIST);
				spellsKnown.add(Spell.MINOR_ILLUSION);
				features.add(Feature.SPEAK_WITH_SMALL_BEASTS);

			} else if (race.equals(Race.TINKER_GNOME)) {
				actor.setConstitution(actor.getConstitution() + 1);
				//
				features.add(Feature.ARTIFICERS_LORE);
				features.add(Feature.GNOME_TINKER);

			}

		} else if (race.equals(Race.HALF_ELF)) {
			/*
			 * HALF-ELF FEATURES
			 */
			actor.setCharisma(actor.getCharisma() + 2);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			//
			features.add(Feature.DARKVISION_60);
			features.add(Feature.FEY_ANCESTRY);
			languages.add(Language.COMMON);
			languages.add(Language.ELVISH);
			languages.addAll(Dice.addToSet(Language.COMMON_LANGUAGES, languages));

		} else if (race.equals(Race.HALF_ORC)) {
			/*
			 * HALF-ORC FEATURES
			 */
			actor.setStrength(actor.getStrength() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			//
			features.add(Feature.DARKVISION_60);
			languages.add(Language.COMMON);
			languages.add(Language.ORCISH);
			//
			features.add(Feature.HALF_ORC_MENACE);
			// TODO
			// skills.add(Skill.INTIMIDATION);
			features.add(Feature.RELENTLESS_ENDURANCE);
			features.add(Feature.SAVAGE_ATTACKS);

		} else if (race.equals(Race.TIEFLING)) {
			/*
			 * TIEFLING FEATURES
			 */
			actor.setCharisma(actor.getCharisma() + 2);
			actor.setIntelligence(actor.getIntelligence() + 1);
			languages.add(Language.COMMON);
			languages.add(Language.INFERNAL);
			//
			features.add(Feature.DARKVISION_60);
			features.add(Feature.HELLISH_RESISTANCE);
			features.add(Feature.INFERNAL_LEGACY);

		}

		actor.setLanguages(languages);
		actor.setFeatures(features);
		actor.setWeaponProficiency(weapons);
		actor.setArmorProficiency(armor);
		actor.setSkills(skills);
		actor.setSpellsKnown(spellsKnown);
	}

	public static String randomName(boolean isFemale, Race race) {
		String[] array = null;

		if (isFemale && race.equals(Race.DRAGONBORN))
			array = DRAGONBORN_FEMALE;
		else if (race.equals(Race.DRAGONBORN))
			array = DRAGONBORN_MALE;
		else if (isFemale && (race.equals(Race.HILL_DWARF) || race.equals(Race.MOUNTAIN_DWARF)))
			array = DWARF_FEMALE;
		else if (race.equals(Race.HILL_DWARF) || race.equals(Race.MOUNTAIN_DWARF))
			array = DWARF_MALE;
		else if (isFemale && (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF)))
			array = ELF_FEMALE;
		else if (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF))
			array = ELF_MALE;
		else if (isFemale && (race.equals(Race.FOREST_GNOME) || race.equals(Race.TINKER_GNOME)))
			array = GNOME_FEMALE;
		else if (race.equals(Race.FOREST_GNOME) || race.equals(Race.TINKER_GNOME))
			array = GNOME_MALE;
		else if (isFemale && (race.equals(Race.LIGHTFOOT_HALFLING) || race.equals(Race.STOUTHEART_HALFLING)))
			array = HALFLING_FEMALE;
		else if (race.equals(Race.LIGHTFOOT_HALFLING) || race.equals(Race.STOUTHEART_HALFLING))
			array = HALFLING_MALE;
		else if (isFemale && race.equals(Race.HALF_ORC))
			array = HALF_ORC_FEMALE;
		else if (race.equals(Race.HALF_ORC))
			array = HALF_ORC_MALE;
		else if (isFemale && race.equals(Race.TIEFLING))
			array = TIEFLING_FEMALE;
		else if (race.equals(Race.TIEFLING))
			array = TIEFLING_MALE;
		else if (isFemale && (race.equals(Race.HUMAN) || race.equals(Race.HALF_ELF)))
			array = ENGLISH_FEMALE;
		else if (race.equals(Race.HUMAN) || race.equals(Race.HALF_ELF))
			array = ENGLISH_MALE;

		return Dice.randomFromArray(array);
	}
}
