import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Player implements Actor {
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
	 * INSTANCE FIELDS
	 * 
	 */
	private byte[] abilityScores;
	private byte[] abilityCeiling;
	private Size size;
	private Creature creature;
	private byte[] hitDice;
	private boolean isFemale;

	private Race race;
	private Class job;
	private Class.Subclass archetype;
	private EnumSet<Spell> spellsKnown;

	private Deity god;
	private Actor.Alignment alignment;
	private Career.Profile career;

	// proficiency
	private EnumSet<Armor> armor;
	private EnumSet<Weapon> weapons;
	private EnumSet<Skill> skills;
	private EnumSet<Option.Feature> features;
	private EnumSet<Language> languages;

	// gear
	private Inventory inventory;
	private CombatBlock combat;

	// basic
	private String name;
	private int experience;
	private int level;
	private float expRate;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Player() {
		//
		abilityScores = Dice.rollAbilities();
		abilityCeiling = new byte[] { 20, 20, 20, 20, 20, 20 };
		alignment = Actor.Alignment.random();

		//
		job = Class.selectClass(this);
		archetype = Class.Subclass.selectSubclass(this);
		race = Race.selectRace();
		god = Deity.selectDeity(this);
		career = new Career.Profile(this);
		isFemale = (abilityScores[2] > abilityScores[0]) ? true : false;
		name = randomName(isFemale, race);

		//
		this.level = 1;
		this.experience = 0;
		this.hitDice = Dice.rollHitDice(job);

		//
		Skill.setupClassSkills(this);
		Skill.setupCareerSkills(this);
		Skill.setupRacialSkills(this);
		Actor.Language.setupLanguages(this);
		Armor.setupArmorProficiency(this);
		Weapon.setupWeaponProficiency(this);
		Class.setupClassFeatures(this);
		Spell.setupSpellsKnown(this);

		// final step after class/subclass chosen
		Class.additionalSetup(this);

		// must be after race is determined
		this.size = (race.equals(Race.HALFLING) || race.equals(Race.GNOME)) ? Size.SMALL : Size.MEDIUM;
		this.creature = Creature.HUMANOID;

		// inventory setup
		Inventory.setupStartingGear(this);
		CombatBlock.setupCombatBlock(this);
	}

	@Override
	public String toString() {
		String string;
		if (job != null)
			string = String.format("%s the %s", name, job);
		else
			string = name;

		return string;
	}

	private String abilitiesToString() {
		String string = String.format("[%2d, %2d, %2d, %2d, %2d, %2d]", abilityScores[0], abilityScores[1],
				abilityScores[2], abilityScores[3], abilityScores[4], abilityScores[5]);

		return string;
	}

	private String skillsToString() {
		List<Skill> skillsList = Dice.listFromSet(skills);
		Dice.AlphabeticalDescending<Skill> sort = new Dice.AlphabeticalDescending<Skill>();

		Collections.sort(skillsList, sort);
		String string = String.format("%s", skills.toString());

		return string;
	}

	@Override
	public String toStringDetailed() {
		String string = String.format("%s the level %d %s %s (%s)", name, level, race, job, archetype);

		// creature line
		String sex = (isFemale) ? "female" : "male";
		string += String.format("%n%s %s %s (%s) %s follower of %s", size, sex, creature, race, alignment, god);
		// combat line
		string += "\n" + combat.toStringDetailed();
		// abilities line
		string += "\n" + abilitiesToString();
		// skills line
		// string += "\n" + skillsToString();
		// armor line
		// string += "\n" + armor.toString();
		// weapon line
		// string += "\n" + weapons.toString();
		// languages line
		// string += "\n" + languages.toString();
		// inventory line
		string += "\n" + inventory.toStringDetailed();
		// features line
		if (features.size() > 0)
			string += "\n" + features.toString();
		// profile line
		// string += "\n" + career.toStringDetailed();
		if (spellsKnown.size() > 0)
			string += "\n" + spellsKnown.toString();

		return string;
	}

	@Override
	public boolean polymorphed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean magicUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean weaponUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean armorUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Size getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Creature getCreatureType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	public Deity getDeity() {
		return god;
	}

	@Override
	public byte[] getHitDice() {
		return hitDice;
	}

	@Override
	public EnumSet<Option.Feature> getFeatures() {
		return features;
	}

	@Override
	public void setFeatures(EnumSet<Option.Feature> features) {
		this.features = features;
	}

	@Override
	public Class getJob() {
		return job;
	}

	@Override
	public Class.Subclass getArchetype() {
		return archetype;
	}

	@Override
	public Race getRace() {
		return race;
	}

	@Override
	public EnumSet<Spell> getSpellsKnown() {
		return spellsKnown;
	}

	@Override
	public void setSpellsKnown(EnumSet<Spell> spellsKnown) {
		this.spellsKnown = spellsKnown;
	}

	@Override
	public Career.Profile getCareer() {
		return career;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getExperience() {
		return experience;
	}

	@Override
	public void setExp(int exp) {
		this.experience = exp;
	}

	@Override
	public EnumSet<Speed> getSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAbilityScores() {
		return abilityScores;
	}

	@Override
	public byte[] getAbilityCeiling() {
		return abilityCeiling;
	}

	@Override
	public byte[] getSavingThrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Skill> getSkills() {
		return skills;
	}

	@Override
	public void setSkills(EnumSet<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public EnumSet<Armor> getArmorProficiency() {
		return armor;
	}

	@Override
	public void setArmorProficiency(EnumSet<Armor> armor) {
		this.armor = armor;
	}

	@Override
	public EnumSet<Weapon> getWeaponProficiency() {
		return weapons;
	}

	@Override
	public void setWeaponProficiency(EnumSet<Weapon> weapons) {
		this.weapons = weapons;
	}

	@Override
	public EnumSet<Energy> getResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Energy> getImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditionImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Sense> getSenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Language> getLanguages() {
		return languages;
	}

	@Override
	public void setLanguages(EnumSet<Language> languages) {
		this.languages = languages;
	}

	@Override
	public CombatBlock getCombatBlock() {
		return combat;
	}

	@Override
	public void setCombatBlock(CombatBlock combat) {
		this.combat = combat;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	private static String randomName(boolean isFemale, Race race) {
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
