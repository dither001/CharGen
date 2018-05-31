
public enum Deity {
	ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD, LOLTH, MELORA, MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR;

	
	private static final Deity[] DEATH_GODS = { RAVEN_QUEEN, TOROG, VECNA };
	private static final Deity[] KNOWLEDGE_GODS = { ERATHIS, IOUN, MORADIN, VECNA }; 
	private static final Deity[] LIFE_GODS = { BAHAMUT, PELOR, RAVEN_QUEEN }; 
	private static final Deity[] LIGHT_GODS = { CORELLON, PELOR }; 
	private static final Deity[] NATURE_GODS = { MELORA }; 
	private static final Deity[] TEMPEST_GODS = { GRUUMSH, KORD, MELORA }; 
	private static final Deity[] TRICKERY_GODS = { ASMODEUS, AVANDRA, LOLTH, SEHANINE, THARIZDUN, TIAMAT, ZEHIR }; 
	private static final Deity[] WAR_GODS = { BAHAMUT, BANE, GRUUMSH, MORADIN, TIAMAT }; 
	
	// static methods
	public static Deity selectDeity(Actor actor) {
		Alignment ali = actor.getAli();
		Class job = actor.getJob();
		Race race = actor.getRace();
		Deity deity;
		
		if (job.equals(Class.CLERIC)) {
			deity = domainToDeity(actor);
		} else {
			Deity[] gods = { racialDeity(race), idealDeity(ali), jobDeity(job), randomDeity() };
			deity = gods[Dice.roll(4) - 1];
		}
		
		return deity;
	}

	public static Deity domainToDeity(Actor actor) {
		Deity deity = null;
		Class.Subclass domain = actor.getArchetype();
		
		if (domain.equals(Class.Subclass.DEATH)) {
			deity = DEATH_GODS[Dice.roll(DEATH_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.KNOWLEDGE)) {
			deity = KNOWLEDGE_GODS[Dice.roll(KNOWLEDGE_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.LIFE)) {
			deity = LIFE_GODS[Dice.roll(LIFE_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.LIGHT)) {
			deity = LIGHT_GODS[Dice.roll(LIGHT_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.NATURE)) {
			deity = NATURE_GODS[Dice.roll(NATURE_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.TEMPEST)) {
			deity = TEMPEST_GODS[Dice.roll(TEMPEST_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.TRICKERY)) {
			deity = TRICKERY_GODS[Dice.roll(TRICKERY_GODS.length) - 1];
		} else if (domain.equals(Class.Subclass.WAR)) {
			deity = WAR_GODS[Dice.roll(WAR_GODS.length) - 1];
		}
		
		return deity;
	}
	
	public static Deity randomDeity() {
		Deity[] gods = { ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD, LOLTH, MELORA,
				MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR };

		return gods[Dice.roll(20) - 1];
	}

	public static Deity racialDeity(Race race) {
		Deity deity;

		switch (race) {
		case HUMAN:
			deity = PELOR;
			break;
		case DRAGONBORN:
			deity = (Dice.roll(2) == 1) ? BAHAMUT : TIAMAT;
			break;
		case DWARF:
			deity = MORADIN;
			break;
		case HIGH_ELF:
			deity = CORELLON;
			break;
		case WOOD_ELF:
			deity = SEHANINE;
			break;
		case DARK_ELF:
			deity = LOLTH;
			break;
		case GNOME:
			deity = ERATHIS;
			break;
		case HALFLING:
			deity = AVANDRA;
			break;
		case HALF_ORC:
			deity = GRUUMSH;
			break;
		case TIEFLING:
			deity = ASMODEUS;
			break;
		default:
			deity = AVANDRA;
			break;
		}

		return deity;
	}

	public static Deity idealDeity(Alignment ali) {
		Deity deity;
		int dieRoll = Dice.roll(6) - 1;

		Deity[] lawfulGods = { ASMODEUS, BAHAMUT, BANE, ERATHIS, MORADIN, RAVEN_QUEEN };
		Deity[] goodGods = { AVANDRA, BAHAMUT, CORELLON, MORADIN, PELOR, SEHANINE };
		Deity[] neutralGods = { ERATHIS, IOUN, KORD, MELORA, RAVEN_QUEEN, SEHANINE };
		Deity[] evilGods = { ASMODEUS, BANE, TIAMAT, TOROG, VECNA, ZEHIR };
		Deity[] chaoticGods = { GRUUMSH, KORD, LOLTH, THARIZDUN, TIAMAT, TOROG };

		if (ali.equals(Alignment.LAWFUL)) {
			deity = lawfulGods[dieRoll];
		} else if (ali.equals(Alignment.GOOD)) {
			deity = goodGods[dieRoll];
		} else if (ali.equals(Alignment.NEUTRAL)) {
			deity = neutralGods[dieRoll];
		} else if (ali.equals(Alignment.CHAOTIC)) {
			deity = chaoticGods[dieRoll];
		} else {
			// default is an evil god
			deity = evilGods[dieRoll];
		}

		return deity;
	}

	public static Deity jobDeity(Class job) {
		Deity deity;
		
		switch (job) {
		case BARBARIAN: deity = GRUUMSH; break;
		case BARD: deity = SEHANINE; break;
		case CLERIC: deity = ERATHIS; break;
		case DRUID: deity = KORD; break;
		case FIGHTER: deity = BANE; break;
		case MONK: deity = IOUN; break;
		case PALADIN: deity = BAHAMUT; break;
		case RANGER: deity = MELORA; break;
		case ROGUE: deity = ZEHIR; break;
		case SORCERER: deity = CORELLON; break;
		case WARLOCK: deity = ASMODEUS; break;
		case WIZARD: deity = VECNA; break;
		default: deity = AVANDRA; break;
		}
		
		
		return deity;
	}
}
