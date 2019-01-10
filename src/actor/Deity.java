package actor;

import com.dnd5e.definitions.*;
import com.norvendae.misc.Dice;

public enum Deity {
	ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD, LOLTH, MELORA, MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR;

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static final Deity[] ALL_GODS = { ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD,
			LOLTH, MELORA, MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR };

	//
	private static final Deity[] LAWFUL_GODS = { ASMODEUS, BAHAMUT, BANE, ERATHIS, MORADIN, RAVEN_QUEEN, TIAMAT };
	private static final Deity[] GOOD_GODS = { AVANDRA, BAHAMUT, CORELLON, MORADIN, PELOR, SEHANINE };
	private static final Deity[] NEUTRAL_GODS = { ERATHIS, IOUN, KORD, MELORA, PELOR, RAVEN_QUEEN, TOROG, VECNA };
	private static final Deity[] EVIL_GODS = { ASMODEUS, BANE, GRUUMSH, LOLTH, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR };
	private static final Deity[] CHAOTIC_GODS = { AVANDRA, CORELLON, GRUUMSH, KORD, LOLTH, SEHANINE, THARIZDUN, ZEHIR };

	//
	private static final Deity[] DEATH_GODS = { RAVEN_QUEEN, TOROG, VECNA, ZEHIR };
	private static final Deity[] KNOWLEDGE_GODS = { ERATHIS, IOUN, MORADIN, VECNA };
	private static final Deity[] LIFE_GODS = { BAHAMUT, PELOR, RAVEN_QUEEN };
	private static final Deity[] LIGHT_GODS = { CORELLON, PELOR };
	private static final Deity[] NATURE_GODS = { MELORA };
	private static final Deity[] TEMPEST_GODS = { GRUUMSH, KORD, MELORA };
	private static final Deity[] TRICKERY_GODS = { ASMODEUS, AVANDRA, LOLTH, SEHANINE, THARIZDUN, TIAMAT, ZEHIR };
	private static final Deity[] WAR_GODS = { BAHAMUT, BANE, GRUUMSH, MORADIN, TIAMAT };

	// static methods
	public static Deity selectDeity(Player actor) {
		Alignment ali = actor.alignment();
		Class job = actor.getJob();
		Race race = actor.getRace();
		Deity deity;

		if (job.equals(Class.CLERIC)) {
			deity = domainToDeity(actor);
		} else {
			Deity[] gods = { racialDeity(race), idealDeity(ali), jobDeity(job), randomDeity() };
			deity = Dice.randomFromArray(gods);
		}

		return deity;
	}

	public static Deity domainToDeity(Player actor) {
		Deity[] array = null;
		Subclass domain = actor.getArchetype();

		if (domain.equals(Subclass.DEATH))
			array = DEATH_GODS;
		else if (domain.equals(Subclass.KNOWLEDGE))
			array = KNOWLEDGE_GODS;
		else if (domain.equals(Subclass.LIFE))
			array = LIFE_GODS;
		else if (domain.equals(Subclass.LIGHT))
			array = LIGHT_GODS;
		else if (domain.equals(Subclass.NATURE))
			array = NATURE_GODS;
		else if (domain.equals(Subclass.TEMPEST))
			array = TEMPEST_GODS;
		else if (domain.equals(Subclass.TRICKERY))
			array = TRICKERY_GODS;
		else if (domain.equals(Subclass.WAR))
			array = WAR_GODS;

		return Dice.randomFromArray(array);
	}

	public static Deity randomDeity() {
		return Dice.randomFromArray(ALL_GODS);
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
		case HILL_DWARF:
			deity = MORADIN;
			break;
		case MOUNTAIN_DWARF:
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
		case FOREST_GNOME:
			deity = MELORA;
			break;
		case TINKER_GNOME:
			deity = ERATHIS;
			break;
		case LIGHTFOOT_HALFLING:
			deity = AVANDRA;
			break;
		case STOUTHEART_HALFLING:
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
		Deity[] array = null;

		if (ali.equals(Alignment.LAWFUL)) {
			array = LAWFUL_GODS;
		} else if (ali.equals(Alignment.GOOD)) {
			array = GOOD_GODS;
		} else if (ali.equals(Alignment.NEUTRAL)) {
			array = NEUTRAL_GODS;
		} else if (ali.equals(Alignment.CHAOTIC)) {
			array = CHAOTIC_GODS;
		} else {
			array = EVIL_GODS;
		}

		return Dice.randomFromArray(array);
	}

	public static Deity jobDeity(Class job) {
		Deity deity;

		switch (job) {
		case BARBARIAN:
			deity = GRUUMSH;
			break;
		case BARD:
			deity = SEHANINE;
			break;
		case CLERIC:
			deity = ERATHIS;
			break;
		case DRUID:
			deity = KORD;
			break;
		case FIGHTER:
			deity = BANE;
			break;
		case MONK:
			deity = IOUN;
			break;
		case PALADIN:
			deity = BAHAMUT;
			break;
		case RANGER:
			deity = MELORA;
			break;
		case ROGUE:
			deity = ZEHIR;
			break;
		case SORCERER:
			deity = CORELLON;
			break;
		case WARLOCK:
			deity = ASMODEUS;
			break;
		case WIZARD:
			deity = VECNA;
			break;
		default:
			deity = AVANDRA;
			break;
		}

		return deity;
	}
}
