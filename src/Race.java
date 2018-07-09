
public enum Race {
	HUMAN, DRAGONBORN, DWARF, HIGH_ELF, WOOD_ELF, DARK_ELF, GNOME, HALFLING, HALF_ELF, HALF_ORC, TIEFLING;

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

	// methods
	public static Race selectRace() {
		Race race;
		int dice = Dice.roll(100);

		if (dice < 41) {
			race = HUMAN;
		} else if (dice < 47) {
			race = DRAGONBORN;
		} else if (dice < 53) {
			race = DWARF;
		} else if (dice < 59) {
			race = HIGH_ELF;
		} else if (dice < 65) {
			race = WOOD_ELF;
		} else if (dice < 71) {
			race = DARK_ELF;
		} else if (dice < 77) {
			race = GNOME;
		} else if (dice < 83) {
			race = HALF_ELF;
		} else if (dice < 89) {
			race = HALF_ORC;
		} else if (dice < 95) {
			race = HALFLING;
		} else {
			race = TIEFLING;
		}

		return race;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static void applyRacialBonus(Actor actor, Race race) {
		if (race.equals(HUMAN)) {
			actor.setStrength(actor.getStrength() + 1);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(DRAGONBORN)) {
			actor.setStrength(actor.getStrength() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		} else if (race.equals(DWARF)) {
			actor.setConstitution(actor.getConstitution() + 2);
			actor.setStrength(actor.getStrength() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
		} else if (race.equals(HIGH_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setIntelligence(actor.getIntelligence() + 1);
		} else if (race.equals(WOOD_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
		} else if (race.equals(DARK_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(HALFLING)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(GNOME)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
		} else if (race.equals(HALF_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		} else if (race.equals(HALF_ORC)) {
			actor.setStrength(actor.getStrength() + 2);
			actor.setConstitution(actor.getConstitution() + 2);
		} else if (race.equals(TIEFLING)) {
			actor.setIntelligence(actor.getIntelligence() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		}
	}

	public static String randomName(boolean isFemale, Race race) {
		String[] array = null;

		if (isFemale && race.equals(Race.DRAGONBORN))
			array = DRAGONBORN_FEMALE;
		else if (race.equals(Race.DRAGONBORN))
			array = DRAGONBORN_MALE;
		else if (isFemale && race.equals(Race.DWARF))
			array = DWARF_FEMALE;
		else if (race.equals(Race.DWARF))
			array = DWARF_MALE;
		else if (isFemale && (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF)))
			array = ELF_FEMALE;
		else if (race.equals(Race.DARK_ELF) || race.equals(Race.HIGH_ELF) || race.equals(Race.WOOD_ELF))
			array = ELF_MALE;
		else if (isFemale && race.equals(Race.GNOME))
			array = GNOME_FEMALE;
		else if (race.equals(Race.GNOME))
			array = GNOME_MALE;
		else if (isFemale && race.equals(Race.HALFLING))
			array = HALFLING_FEMALE;
		else if (race.equals(Race.HALFLING))
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
