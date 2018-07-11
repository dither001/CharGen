import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class Immortal implements Actor {
	/*
	 * Many, many gods exist, and comparatively few classed "primordials." It's my
	 * intent to class the majority of entities that are either abstract, elemental
	 * in nature, remained generally unclassed, or are otherwise "much older gods"
	 * as primordials. Many orders of "lower" beings were created "later."
	 */

	public enum Greater {
		// GODS & PRIMORDIALS are two sides of the same "Creation" coin
		GOD, PRIMORDIAL,
		// SPIRITS & VESTIGES are "neutral" and generally "free agents"
		SPIRIT, VESTIGE,
		// HORRORS destroy everything they touch
		HORROR
		/*
		 * PRIMODIALS: Serpents of Law & Chaos, the Brethren (e.g. Lady of Pain)
		 */

		/*
		 * SPIRITS: beast/plant lords in the Outlands; Primal Spirits in the World
		 */

		/*
		 * VESTIGES: slain & exiled gods and other cosmic entities
		 */

		/*
		 * HORRORS: the Obyriths; Azathoth, Nyarlathotep, Cthulhu & the Elder Gods;
		 * potentially includes Magic's "Eldrazi" and "Elder Dragons"
		 */
	}

	public enum Lesser {
		/*
		 * ANCESTOR
		 */
		ANCESTOR,
		/*
		 * SAINT
		 */
		SAINT,
		/*
		 * DEMIGODS are explicitly "lesser gods" like elevated heroes and whatnot
		 */
		DEMIGOD,
		/*
		 * DEMIURGES include the majority of monotheistic deities, like ABRAXAS, ALLAH,
		 * and YAHWEH who are almost always specifically associated with Creation
		 */
		DEMIURGE,
		/*
		 * ANGELS are "lawful" servants of the gods, and they are led by a group of
		 * powerful angels like a council of sorts
		 */
		ANGEL,
		/*
		 * DEMONS are "chaotic" beings, corrupted by evil and wholly destructive; they
		 * are led by powerful "demon princes"; DEVILS may or may not be a higher order
		 * of demons capable of independent thought and action
		 */
		DEMON, DEVIL,
		/*
		 * 
		 * true DRAGONS are all unique, such as the Mesopotamian Bahamut and Tiamat,
		 * Greek Python, Ladon, Hydra (et al), feathered serpents, the Japanese
		 * Yamata-no-Orochi, Tolkein's Smaug, and most of the named dragons from various
		 * editions of Dungeons & Dragons; therefore "Dragon" is not explicitly a
		 * creature type: it instead refers to a class of (usually selfish) cosmic
		 * beings that resemble lizards, snakes, and other reptiles
		 */
		DRAGON,
		/*
		 * UNDEAD are properly a form of lesser "immortal" creatures; includes ghosts,
		 * vampires, liches, mummies, and many others
		 */
		UNDEAD,
		/*
		 * WITCHES are found in almost every mythology, and are here consolidated
		 */
		WITCH,
		/*
		 * ABOMINATIONS are a bizarre class of "created" beings that lack a formal
		 * definition or anything list consistent depiction
		 */
		ABOMINATION,
		/*
		 * ELEMENTALS include the majority of beings called "primordials" in D&D 4e
		 */
		ELEMENTAL,
		/*
		 * India: DEVA/SURA, ASURA, YAKSHA/BHUTA, RAKSHASA
		 */
		ASURA, DEVA, YAKSHA, RAKSHASA,
		/*
		 * Middle east: JINNI (plural djinn)
		 */
		JINNI,
		/*
		 * Greek/Hellenic: CYCLOPS/GIGANTES is distinct from giant
		 */
		TITAN, OLYMPIAN, CYCLOPS, CTHONIAN,
		/*
		 * Norse/Germanic: JOTUNN, NORNS (include "fylgjas," "hamingjas," and valkyries)
		 * are always female, known to control the fates of men and gods)
		 */
		AESIR, VANIR, JOTUNN, NORN,
		/*
		 * Scots/Celt/Irish: SIDHE (alt. Scottish "Sith") are "chaotic" and led by
		 * powerful entities such as OBERON and TITANIA
		 */
		SIDHE, FOMORIAN,
		/*
		 * LOA
		 */
		LOA,
		/*
		 * West African mythology includes a variety of deities and spirits; ANANSI for
		 * example, is a spider-man trickster deity who features prominently; Western
		 * sources often city MAMI WATA as a single deity when in truth "they" are a
		 * pantheon of water deities (sometimes but not always female); SEE ALSO
		 * CARIBBEAN FOLKLORE
		 */
		MAMI_WATA,
		/*
		 * Japanese/Shinto: KAMI are explicitly known to be "of two mind," meaning they
		 * can be nurturing and/or destructive; MONONOKE are possessing spirits; ONI are
		 * Japanese giants; YOKAI include oni, and the "henge" or "mutated beings"
		 */
		KAMI, MONONOKE, SHINIGAMI, YOKAI
		/*
		 * Chinese ghosts and spirits are well-documented on Wikipedia (must read):
		 * https://en.wikipedia.org/wiki/Ghosts_in_Chinese_culture
		 */
	}

	private enum Domain {
		DEATH, KNOWLEDGE, LIFE, LIGHT, NATURE, TEMPEST, TRICKERY, WAR
	}

	private enum Entity {
		// DAWN WAR GODS
		ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD, LOLTH, MELORA, MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR,
		// JOTNAR
		AEGIR, ALFARIN, ALSVART, AM, AMA, AMGERD, ANGEYJA, ANGRBODA, ASVID, ATLA, AURBODA, AURGELMIR, AURGRIMNIR, AURNIR, AURVANDIL, BAKRAUF, BARA, BAUGI, BEINVID, BELI, BERGELMIR, BESTLA, BILLINGR, BJORGOLF, BLAIN, BLAPTHVARI, BLODUGHADDA, BOLTHORN, BRANDINGI, BRIMIR, BRYJA, BUSEYRA, BYLEIPTR, BYLEIST, BYLGJA, DOFRI, DROFN, DUFA, DUMBR, DURNIR, EGGTHER, EIMGEITIR, EISTLA, EISURFALDA, ELD, EYRGJAFA, FALA, FARBAUTI, FENJA, MENJA, FENRIR, FJOLVERK, FJOLVOR, FLEGG, FORAD, FORNJOT, FYRNIR, GANGLATI, GANGLOT, GANGR, GEIRROD, GEITIR, GEITLA, GERD, GESTILJA, GEYSA, GILLING, GJALP, GREIP, GLAM, GLAUM, GLAUMAR, GLUMRA, GNEIP, GNEIPA, GNISSA, GRID, GRIMLING, GRIMNIR, GROTTINTANNA, GRYLA, GUNNLOD, GUSIR, GYLLIR, GYMIR, HAERA, HAFLI, HALA, HARDGREIP, HARDVERK, HASTIGI, HATI, HEFRING, HEIDREK, HEL, HELBLINDI, HELREGINN, HENGIKEPTA, HENGJANKJAPTA, HERKIR, HERKJA, HIMINGLAEVA, GLOI, HOLGABRUD, HORN, HRAESVELGR, HRAUDNIR, HRAUDUNG, HRIMGERD, HRIMGRIMNIR, HRIMNIR, HRIMTHURS, HRINGVOLNIR, HRIPSTOD, HROAR, HRODR, HRODVITNIR, HROKKVIR, HRONN, HROSSTHJOF, HRUGA, HRUNGNIR, HRYGGDA, HRYM, HUNDALF, HVAL, HVEDRA, HVEDRUNG, HYMIR, HYNDLA, HYROKKIN, IDI, IM, IMA, IMD, IMGERD, IVIDJA, JARNGLUMRA, JARNSAXA, JARNVIDJA, JORMUNGAND, KALDGRANI, KARI, KEILA, KJALLANDI, KOLGA, KOTT, KRAKA, KYRMIR, LAUFEY, LEIFI, LEIKN, LEIRVOR, LITR, LJOTA, LODINFINGRA, LOGI, LOKI, LOPTR, LUT, MANAGARM, MARGERD, MIDJUNG, MIMIR, MODGUD, MOGTHRASIR, MOKKURKALFI, MORN, MUNNHARPA, MYRKRIDA, NAGLFARI, NAL, NARFI, NARI, NATI, NORFI, NOTT, OFLUGBARDA, OFLUGBARDI, OFOTI, OGLADNIR, OLVALDI, ONDUD, OSGRUI, OSKRUD, RANGBEIN, RIFINGAFLA, RINDR, RUNGNIR, RYG, SAEKARLSMULI, SALFANG, SAMENDIL, SIGYN, SIMUL, SIVOR, SKADI, SKAERIR, SKALLI, SKERKIR, SKOLL, SKRATI, SKRIKJA, SKRYMIR, SOKKMIMIR, SOM, SPRETTING, STARKAD, STIGANDI, STORVERK, STUMI, SURTR, SUTTUNG, SVARANG, SVART, SVASUD, SVEIPINFALDA, SVIVOR, THISTILBARDI, THJAZI, THORN, THRASIR, THRIGEITIR, THRIVALDI, THRUDGELMIR, THRYM, THURBORD, ULFRUN, UNN, UTGARDA_LOKI, VAFTHRUDNIR, VAGNHOFDI, VALI, VANARGAND, VANDIL, VARDRUN, VASAD, VER, VIDBLINDI, VIDDI, VIDGYMNIR, VIGGLOD, VIND, VINDLONI, VINDSVAL, VINGNIR, VINGRIP, VIPAR, VORNIR, YMIR, YMSI

	}

	public static class Prototype {
		private Entity entity;
		private Greater greater;
		private Lesser lesser;
		private EnumSet<Domain> domains;
		private EnumSet<Alignment> alignment;

		public Prototype(Entity entity, Greater greater, Alignment[] alignment, Domain[] domains) {
			this.greater = greater;

			// ditto
			this.entity = entity;
			this.alignment = EnumSet.noneOf(Alignment.class);
			if (alignment.length > 0) {
				for (Alignment el : alignment) {
					this.alignment.add(el);
				}
			}

			this.domains = EnumSet.noneOf(Domain.class);
			if (domains.length > 0) {
				for (Domain el : domains)
					this.domains.add(el);
			}
		}

		public Prototype(Entity entity, Lesser lesser, Alignment[] alignment, Domain[] domains) {
			this.lesser = lesser;

			// ditto
			this.entity = entity;
			this.alignment = EnumSet.noneOf(Alignment.class);
			if (alignment.length > 0) {
				for (Alignment el : alignment) {
					this.alignment.add(el);
				}
			}

			this.domains = EnumSet.noneOf(Domain.class);
			if (domains.length > 0) {
				for (Domain el : domains)
					this.domains.add(el);
			}
		}

		public boolean greater() {
			return (greater != null);
		}

		public boolean lesser() {
			return (lesser != null);
		}
	}

	/*
	 * STATIC FIELDS
	 */
	private static HashMap<Entity, Prototype> greaterImmortals;
	private static HashMap<Entity, Prototype> lesserImmortals;

	private static final Greater[] GREATER_IMMORTALS = { Greater.GOD, Greater.PRIMORDIAL, Greater.SPIRIT,
			Greater.VESTIGE, Greater.HORROR };
	private static final Lesser[] LESSER_IMMORTALS = { Lesser.ANCESTOR, Lesser.SAINT, Lesser.DEMIGOD, Lesser.DEMIURGE,
			Lesser.ANGEL, Lesser.DEMON, Lesser.DRAGON, Lesser.UNDEAD, Lesser.WITCH, Lesser.ABOMINATION,
			Lesser.ELEMENTAL, Lesser.ASURA, Lesser.DEVA, Lesser.YAKSHA, Lesser.RAKSHASA, Lesser.JINNI, Lesser.TITAN,
			Lesser.OLYMPIAN, Lesser.CYCLOPS, Lesser.CTHONIAN, Lesser.AESIR, Lesser.VANIR, Lesser.JOTUNN, Lesser.NORN,
			Lesser.SIDHE, Lesser.FOMORIAN, Lesser.LOA, Lesser.MAMI_WATA, Lesser.KAMI, Lesser.MONONOKE, Lesser.SHINIGAMI,
			Lesser.YOKAI };

	static {
		greaterImmortals = new HashMap<Entity, Prototype>();
		lesserImmortals = new HashMap<Entity, Prototype>();

		greaterImmortals.put(Entity.ASMODEUS, new Prototype(Entity.ASMODEUS, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		greaterImmortals.put(Entity.AVANDRA, new Prototype(Entity.AVANDRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.TRICKERY }));
		greaterImmortals.put(Entity.BAHAMUT, new Prototype(Entity.BAHAMUT, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.GOOD }, new Domain[] { Domain.LIFE, Domain.WAR }));
		greaterImmortals.put(Entity.BANE, new Prototype(Entity.BANE, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.WAR }));
		greaterImmortals.put(Entity.CORELLON, new Prototype(Entity.CORELLON, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.LIGHT }));
		greaterImmortals.put(Entity.ERATHIS, new Prototype(Entity.ERATHIS, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.NEUTRAL }, new Domain[] { Domain.KNOWLEDGE }));
		greaterImmortals.put(Entity.GRUUMSH, new Prototype(Entity.GRUUMSH, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TEMPEST, Domain.WAR }));
		greaterImmortals.put(Entity.IOUN, new Prototype(Entity.IOUN, Greater.GOD, new Alignment[] { Alignment.NEUTRAL },
				new Domain[] { Domain.KNOWLEDGE }));
		greaterImmortals.put(Entity.KORD, new Prototype(Entity.KORD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.NEUTRAL }, new Domain[] { Domain.TEMPEST }));
		greaterImmortals.put(Entity.LOLTH, new Prototype(Entity.LOLTH, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		greaterImmortals.put(Entity.MELORA, new Prototype(Entity.MELORA, Greater.GOD,
				new Alignment[] { Alignment.NEUTRAL }, new Domain[] { Domain.NATURE, Domain.TEMPEST }));
		greaterImmortals.put(Entity.MORADIN, new Prototype(Entity.MORADIN, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.GOOD }, new Domain[] { Domain.KNOWLEDGE, Domain.WAR }));
		greaterImmortals.put(Entity.PELOR, new Prototype(Entity.PELOR, Greater.GOD,
				new Alignment[] { Alignment.NEUTRAL, Alignment.GOOD }, new Domain[] { Domain.LIFE, Domain.LIGHT }));
		greaterImmortals.put(Entity.RAVEN_QUEEN, new Prototype(Entity.RAVEN_QUEEN, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.NEUTRAL }, new Domain[] { Domain.DEATH, Domain.LIFE }));
		greaterImmortals.put(Entity.SEHANINE, new Prototype(Entity.SEHANINE, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.TRICKERY }));
		greaterImmortals.put(Entity.THARIZDUN, new Prototype(Entity.THARIZDUN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		greaterImmortals.put(Entity.TIAMAT, new Prototype(Entity.TIAMAT, Greater.GOD,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.TRICKERY, Domain.WAR }));
		greaterImmortals.put(Entity.TOROG, new Prototype(Entity.TOROG, Greater.GOD,
				new Alignment[] { Alignment.NEUTRAL, Alignment.EVIL }, new Domain[] { Domain.DEATH }));
		greaterImmortals.put(Entity.VECNA,
				new Prototype(Entity.VECNA, Greater.GOD, new Alignment[] { Alignment.NEUTRAL, Alignment.EVIL },
						new Domain[] { Domain.DEATH, Domain.KNOWLEDGE }));
		greaterImmortals.put(Entity.ZEHIR, new Prototype(Entity.ZEHIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.DEATH, Domain.TRICKERY }));

		// JOTNAR
		lesserImmortals.put(Entity.AEGIR, new Prototype(Entity.AEGIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ALFARIN, new Prototype(Entity.ALFARIN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ALSVART, new Prototype(Entity.ALSVART, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AM, new Prototype(Entity.AM, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AMA, new Prototype(Entity.AMA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AMGERD, new Prototype(Entity.AMGERD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ANGEYJA, new Prototype(Entity.ANGEYJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ANGRBODA, new Prototype(Entity.ANGRBODA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ASVID, new Prototype(Entity.ASVID, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ATLA, new Prototype(Entity.ATLA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AURBODA, new Prototype(Entity.AURBODA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AURGELMIR, new Prototype(Entity.AURGELMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AURGRIMNIR, new Prototype(Entity.AURGRIMNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AURNIR, new Prototype(Entity.AURNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.AURVANDIL, new Prototype(Entity.AURVANDIL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BAKRAUF, new Prototype(Entity.BAKRAUF, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BARA, new Prototype(Entity.BARA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BAUGI, new Prototype(Entity.BAUGI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BEINVID, new Prototype(Entity.BEINVID, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BELI, new Prototype(Entity.BELI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BERGELMIR, new Prototype(Entity.BERGELMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BESTLA, new Prototype(Entity.BESTLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BILLINGR, new Prototype(Entity.BILLINGR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BJORGOLF, new Prototype(Entity.BJORGOLF, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BLAIN, new Prototype(Entity.BLAIN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BLAPTHVARI, new Prototype(Entity.BLAPTHVARI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BLODUGHADDA, new Prototype(Entity.BLODUGHADDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BOLTHORN, new Prototype(Entity.BOLTHORN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BRANDINGI, new Prototype(Entity.BRANDINGI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BRIMIR, new Prototype(Entity.BRIMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BRYJA, new Prototype(Entity.BRYJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BUSEYRA, new Prototype(Entity.BUSEYRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BYLEIPTR, new Prototype(Entity.BYLEIPTR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BYLEIST, new Prototype(Entity.BYLEIST, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.BYLGJA, new Prototype(Entity.BYLGJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.DOFRI, new Prototype(Entity.DOFRI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.DROFN, new Prototype(Entity.DROFN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.DUFA, new Prototype(Entity.DUFA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.DUMBR, new Prototype(Entity.DUMBR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.DURNIR, new Prototype(Entity.DURNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.EGGTHER, new Prototype(Entity.EGGTHER, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.EIMGEITIR, new Prototype(Entity.EIMGEITIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.EISTLA, new Prototype(Entity.EISTLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.EISURFALDA, new Prototype(Entity.EISURFALDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ELD, new Prototype(Entity.ELD, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.EYRGJAFA, new Prototype(Entity.EYRGJAFA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FALA, new Prototype(Entity.FALA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FARBAUTI, new Prototype(Entity.FARBAUTI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FENJA, new Prototype(Entity.FENJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MENJA, new Prototype(Entity.MENJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FENRIR, new Prototype(Entity.FENRIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FJOLVERK, new Prototype(Entity.FJOLVERK, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FJOLVOR, new Prototype(Entity.FJOLVOR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FLEGG, new Prototype(Entity.FLEGG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FORAD, new Prototype(Entity.FORAD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FORNJOT, new Prototype(Entity.FORNJOT, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.FYRNIR, new Prototype(Entity.FYRNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GANGLATI, new Prototype(Entity.GANGLATI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GANGLOT, new Prototype(Entity.GANGLOT, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GANGR, new Prototype(Entity.GANGR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GEIRROD, new Prototype(Entity.GEIRROD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GEITIR, new Prototype(Entity.GEITIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GEITLA, new Prototype(Entity.GEITLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GERD, new Prototype(Entity.GERD, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GESTILJA, new Prototype(Entity.GESTILJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GEYSA, new Prototype(Entity.GEYSA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GILLING, new Prototype(Entity.GILLING, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GJALP, new Prototype(Entity.GJALP, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GREIP, new Prototype(Entity.GREIP, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GLAM, new Prototype(Entity.GLAM, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GLAUM, new Prototype(Entity.GLAUM, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GLAUMAR, new Prototype(Entity.GLAUMAR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GLUMRA, new Prototype(Entity.GLUMRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GNEIP, new Prototype(Entity.GNEIP, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GNEIPA, new Prototype(Entity.GNEIPA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GNISSA, new Prototype(Entity.GNISSA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GRID, new Prototype(Entity.GRID, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GRIMLING, new Prototype(Entity.GRIMLING, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GRIMNIR, new Prototype(Entity.GRIMNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GROTTINTANNA, new Prototype(Entity.GROTTINTANNA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GRYLA, new Prototype(Entity.GRYLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GUNNLOD, new Prototype(Entity.GUNNLOD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GUSIR, new Prototype(Entity.GUSIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GYLLIR, new Prototype(Entity.GYLLIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GYMIR, new Prototype(Entity.GYMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HAERA, new Prototype(Entity.HAERA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HAFLI, new Prototype(Entity.HAFLI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HALA, new Prototype(Entity.HALA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HARDGREIP, new Prototype(Entity.HARDGREIP, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HARDVERK, new Prototype(Entity.HARDVERK, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HASTIGI, new Prototype(Entity.HASTIGI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HATI, new Prototype(Entity.HATI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HEFRING, new Prototype(Entity.HEFRING, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HEIDREK, new Prototype(Entity.HEIDREK, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HEL, new Prototype(Entity.HEL, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HELBLINDI, new Prototype(Entity.HELBLINDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HELREGINN, new Prototype(Entity.HELREGINN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HENGIKEPTA, new Prototype(Entity.HENGIKEPTA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HENGJANKJAPTA, new Prototype(Entity.HENGJANKJAPTA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HERKIR, new Prototype(Entity.HERKIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HERKJA, new Prototype(Entity.HERKJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HIMINGLAEVA, new Prototype(Entity.HIMINGLAEVA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.GLOI, new Prototype(Entity.GLOI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HOLGABRUD, new Prototype(Entity.HOLGABRUD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HORN, new Prototype(Entity.HORN, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRAESVELGR, new Prototype(Entity.HRAESVELGR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRAUDNIR, new Prototype(Entity.HRAUDNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRAUDUNG, new Prototype(Entity.HRAUDUNG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRIMGERD, new Prototype(Entity.HRIMGERD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRIMGRIMNIR, new Prototype(Entity.HRIMGRIMNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRIMNIR, new Prototype(Entity.HRIMNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRIMTHURS, new Prototype(Entity.HRIMTHURS, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRINGVOLNIR, new Prototype(Entity.HRINGVOLNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRIPSTOD, new Prototype(Entity.HRIPSTOD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HROAR, new Prototype(Entity.HROAR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRODR, new Prototype(Entity.HRODR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRODVITNIR, new Prototype(Entity.HRODVITNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HROKKVIR, new Prototype(Entity.HROKKVIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRONN, new Prototype(Entity.HRONN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HROSSTHJOF, new Prototype(Entity.HROSSTHJOF, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRUGA, new Prototype(Entity.HRUGA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRUNGNIR, new Prototype(Entity.HRUNGNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRYGGDA, new Prototype(Entity.HRYGGDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HRYM, new Prototype(Entity.HRYM, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HUNDALF, new Prototype(Entity.HUNDALF, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HVAL, new Prototype(Entity.HVAL, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HVEDRA, new Prototype(Entity.HVEDRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HVEDRUNG, new Prototype(Entity.HVEDRUNG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HYMIR, new Prototype(Entity.HYMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HYNDLA, new Prototype(Entity.HYNDLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.HYROKKIN, new Prototype(Entity.HYROKKIN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IDI, new Prototype(Entity.IDI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IM, new Prototype(Entity.IM, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IMA, new Prototype(Entity.IMA, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IMD, new Prototype(Entity.IMD, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IMGERD, new Prototype(Entity.IMGERD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.IVIDJA, new Prototype(Entity.IVIDJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.JARNGLUMRA, new Prototype(Entity.JARNGLUMRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.JARNSAXA, new Prototype(Entity.JARNSAXA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.JARNVIDJA, new Prototype(Entity.JARNVIDJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.JORMUNGAND, new Prototype(Entity.JORMUNGAND, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KALDGRANI, new Prototype(Entity.KALDGRANI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KARI, new Prototype(Entity.KARI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KEILA, new Prototype(Entity.KEILA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KJALLANDI, new Prototype(Entity.KJALLANDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KOLGA, new Prototype(Entity.KOLGA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KOTT, new Prototype(Entity.KOTT, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KRAKA, new Prototype(Entity.KRAKA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KYRMIR, new Prototype(Entity.KYRMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KRAKA, new Prototype(Entity.KRAKA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KYRMIR, new Prototype(Entity.KYRMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LAUFEY, new Prototype(Entity.LAUFEY, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LEIFI, new Prototype(Entity.LEIFI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LEIKN, new Prototype(Entity.LEIKN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LEIRVOR, new Prototype(Entity.LEIRVOR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LITR, new Prototype(Entity.LITR, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LJOTA, new Prototype(Entity.LJOTA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LODINFINGRA, new Prototype(Entity.LODINFINGRA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LOGI, new Prototype(Entity.LOGI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LOKI, new Prototype(Entity.LOKI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LOPTR, new Prototype(Entity.LOPTR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.LUT, new Prototype(Entity.LUT, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MANAGARM, new Prototype(Entity.MANAGARM, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MARGERD, new Prototype(Entity.MARGERD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MIDJUNG, new Prototype(Entity.MIDJUNG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MIMIR, new Prototype(Entity.MIMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MODGUD, new Prototype(Entity.MODGUD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MOGTHRASIR, new Prototype(Entity.MOGTHRASIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MOKKURKALFI, new Prototype(Entity.MOKKURKALFI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MORN, new Prototype(Entity.MORN, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MUNNHARPA, new Prototype(Entity.MUNNHARPA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.MYRKRIDA, new Prototype(Entity.MYRKRIDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NAGLFARI, new Prototype(Entity.NAGLFARI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NAL, new Prototype(Entity.NAL, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NARFI, new Prototype(Entity.NARFI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NARI, new Prototype(Entity.NARI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NATI, new Prototype(Entity.NATI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NORFI, new Prototype(Entity.NORFI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.NOTT, new Prototype(Entity.NOTT, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OFLUGBARDA, new Prototype(Entity.OFLUGBARDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OFLUGBARDI, new Prototype(Entity.OFLUGBARDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OFOTI, new Prototype(Entity.OFOTI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OGLADNIR, new Prototype(Entity.OGLADNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OLVALDI, new Prototype(Entity.OLVALDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ONDUD, new Prototype(Entity.ONDUD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OSGRUI, new Prototype(Entity.OSGRUI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.OSKRUD, new Prototype(Entity.OSKRUD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.RANGBEIN, new Prototype(Entity.RANGBEIN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.RIFINGAFLA, new Prototype(Entity.RIFINGAFLA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.RINDR, new Prototype(Entity.RINDR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.RUNGNIR, new Prototype(Entity.RUNGNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.RYG, new Prototype(Entity.RYG, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SAEKARLSMULI, new Prototype(Entity.SAEKARLSMULI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SALFANG, new Prototype(Entity.SALFANG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SAMENDIL, new Prototype(Entity.SAMENDIL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SIGYN, new Prototype(Entity.SIGYN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SIMUL, new Prototype(Entity.SIMUL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SIVOR, new Prototype(Entity.SIVOR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKADI, new Prototype(Entity.SKADI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKAERIR, new Prototype(Entity.SKAERIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKALLI, new Prototype(Entity.SKALLI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKERKIR, new Prototype(Entity.SKERKIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKOLL, new Prototype(Entity.SKOLL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKRATI, new Prototype(Entity.SKRATI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKRIKJA, new Prototype(Entity.SKRIKJA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SKRYMIR, new Prototype(Entity.SKRYMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SOKKMIMIR, new Prototype(Entity.SOKKMIMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SOM, new Prototype(Entity.SOM, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SPRETTING, new Prototype(Entity.SPRETTING, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.STARKAD, new Prototype(Entity.STARKAD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.STIGANDI, new Prototype(Entity.STIGANDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.STORVERK, new Prototype(Entity.STORVERK, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.STUMI, new Prototype(Entity.STUMI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SURTR, new Prototype(Entity.SURTR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SUTTUNG, new Prototype(Entity.SUTTUNG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SVARANG, new Prototype(Entity.SVARANG, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SVART, new Prototype(Entity.SVART, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SVASUD, new Prototype(Entity.SVASUD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SVEIPINFALDA, new Prototype(Entity.SVEIPINFALDA, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.SVIVOR, new Prototype(Entity.SVIVOR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THISTILBARDI, new Prototype(Entity.THISTILBARDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THJAZI, new Prototype(Entity.THJAZI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THORN, new Prototype(Entity.THORN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THRASIR, new Prototype(Entity.THRASIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THRIGEITIR, new Prototype(Entity.THRIGEITIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THRIVALDI, new Prototype(Entity.THRIVALDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THRUDGELMIR, new Prototype(Entity.THRUDGELMIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THRYM, new Prototype(Entity.THRYM, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.THURBORD, new Prototype(Entity.THURBORD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.ULFRUN, new Prototype(Entity.ULFRUN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.UNN, new Prototype(Entity.UNN, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.UTGARDA_LOKI, new Prototype(Entity.UTGARDA_LOKI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VAFTHRUDNIR, new Prototype(Entity.VAFTHRUDNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VAGNHOFDI, new Prototype(Entity.VAGNHOFDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VALI, new Prototype(Entity.VALI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VANARGAND, new Prototype(Entity.VANARGAND, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VANDIL, new Prototype(Entity.VANDIL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VARDRUN, new Prototype(Entity.VARDRUN, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VASAD, new Prototype(Entity.VASAD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VER, new Prototype(Entity.VER, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIDBLINDI, new Prototype(Entity.VIDBLINDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIDDI, new Prototype(Entity.VIDDI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIDGYMNIR, new Prototype(Entity.VIDGYMNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIGGLOD, new Prototype(Entity.VIGGLOD, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIND, new Prototype(Entity.VIND, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VINDLONI, new Prototype(Entity.VINDLONI, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VINDSVAL, new Prototype(Entity.VINDSVAL, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VINGNIR, new Prototype(Entity.VINGNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VINGRIP, new Prototype(Entity.VINGRIP, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VIPAR, new Prototype(Entity.VIPAR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.VORNIR, new Prototype(Entity.VORNIR, Greater.GOD,
				new Alignment[] { Alignment.CHAOTIC }, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.YMIR, new Prototype(Entity.YMIR, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.YMSI, new Prototype(Entity.YMSI, Greater.GOD, new Alignment[] { Alignment.CHAOTIC },
				new Domain[] { Domain.NATURE }));

	}

	/*
	 * STATIC METHODS
	 */
	public static List<Entity> powersOfAlignment(Alignment alignment) {
		List<Entity> list = new ArrayList<Entity>();

		Entity candidate;
		Prototype prototype;
		for (Iterator<Entity> it = greaterImmortals.keySet().iterator(); it.hasNext();) {
			candidate = it.next();
			prototype = greaterImmortals.get(candidate);
			if (prototype.alignment.contains(alignment))
				list.add(candidate);
		}

		return list;
	}

	public static Set<Greater> greaterImmortalTypes() {
		Set<Greater> set = EnumSet.noneOf(Greater.class);
		for (Greater el : GREATER_IMMORTALS)
			set.add(el);

		return set;
	}

	public static Set<Lesser> lesserImmortalTypes() {
		Set<Lesser> set = EnumSet.noneOf(Lesser.class);
		for (Lesser el : LESSER_IMMORTALS)
			set.add(el);

		return set;
	}
}
