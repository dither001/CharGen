import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
		 * ANCESTOR, SAINT, DEMIGOD
		 */
		ANCESTOR, SAINT, DEMIGOD,
		/*
		 * DEMIURGES include the majority of monotheistic deities, like ABRAXAS, ALLAH,
		 * and YAHWEH who are almost always specifically associated with Creation
		 */
		DEMIURGE,
		/*
		 * ANGELS are "lawful" servants of the gods, and they are led by a group of
		 * powerful angels like a council of sorts; DEMONS are "chaotic," they are led
		 * by powerful "demon princes"; DEVILS may or may not be a higher order of
		 * demons capable of independent thought and action
		 */
		ANGEL, DEMON, DEVIL,
		/*
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
		 * definition or anything list consistent depiction; ELEMENTALS include the
		 * majority of beings called "primordials" in D&D 4e
		 */
		ABOMINATION, ELEMENTAL,
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

		// static
		public static Prototype jotunnRandomizer(Entity entity) {
			Alignment[] alignment = null;
			int dice = Dice.roll(4);
			if (dice == 1 || dice == 2)
				alignment = new Alignment[] { Alignment.CHAOTIC, Alignment.NEUTRAL };
			else if (dice == 3)
				alignment = new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL };
			else if (dice == 4)
				alignment = new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD };

			Domain[] domains = null;
			dice = Dice.roll(6);
			if (dice == 1)
				domains = new Domain[] { Domain.NATURE };
			else if (dice == 2)
				domains = new Domain[] { Domain.NATURE, Domain.DEATH };
			else if (dice == 3)
				domains = new Domain[] { Domain.NATURE, Domain.TEMPEST };
			else if (dice == 4)
				domains = new Domain[] { Domain.NATURE, Domain.TRICKERY };
			else if (dice == 5)
				domains = new Domain[] { Domain.TEMPEST };
			else if (dice == 6)
				domains = new Domain[] { Domain.TRICKERY };

			return new Prototype(entity, Lesser.JOTUNN, alignment, domains);
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
		lesserImmortals.put(Entity.AEGIR, Prototype.jotunnRandomizer(Entity.AEGIR));
		lesserImmortals.put(Entity.ALFARIN, Prototype.jotunnRandomizer(Entity.ALFARIN));
		lesserImmortals.put(Entity.ALSVART, Prototype.jotunnRandomizer(Entity.ALSVART));
		lesserImmortals.put(Entity.AM, Prototype.jotunnRandomizer(Entity.AM));
		lesserImmortals.put(Entity.AMA, Prototype.jotunnRandomizer(Entity.AMA));
		lesserImmortals.put(Entity.AMGERD, Prototype.jotunnRandomizer(Entity.AMGERD));
		lesserImmortals.put(Entity.ANGEYJA, Prototype.jotunnRandomizer(Entity.ANGEYJA));
		lesserImmortals.put(Entity.ANGRBODA, Prototype.jotunnRandomizer(Entity.ANGRBODA));
		lesserImmortals.put(Entity.ASVID, Prototype.jotunnRandomizer(Entity.ASVID));
		lesserImmortals.put(Entity.ATLA, Prototype.jotunnRandomizer(Entity.ATLA));
		lesserImmortals.put(Entity.AURBODA, Prototype.jotunnRandomizer(Entity.AURBODA));
		lesserImmortals.put(Entity.AURGELMIR, Prototype.jotunnRandomizer(Entity.AURGELMIR));
		lesserImmortals.put(Entity.AURGRIMNIR, Prototype.jotunnRandomizer(Entity.AURGRIMNIR));
		lesserImmortals.put(Entity.AURNIR, Prototype.jotunnRandomizer(Entity.AURNIR));
		lesserImmortals.put(Entity.AURVANDIL, Prototype.jotunnRandomizer(Entity.AURVANDIL));
		lesserImmortals.put(Entity.BAKRAUF, Prototype.jotunnRandomizer(Entity.BAKRAUF));
		lesserImmortals.put(Entity.BARA, Prototype.jotunnRandomizer(Entity.BARA));
		lesserImmortals.put(Entity.BAUGI, Prototype.jotunnRandomizer(Entity.BAUGI));
		lesserImmortals.put(Entity.BEINVID, Prototype.jotunnRandomizer(Entity.BEINVID));
		lesserImmortals.put(Entity.BELI, Prototype.jotunnRandomizer(Entity.BELI));
		lesserImmortals.put(Entity.BERGELMIR, Prototype.jotunnRandomizer(Entity.BERGELMIR));
		lesserImmortals.put(Entity.BESTLA, Prototype.jotunnRandomizer(Entity.BESTLA));
		lesserImmortals.put(Entity.BILLINGR, Prototype.jotunnRandomizer(Entity.BILLINGR));
		lesserImmortals.put(Entity.BJORGOLF, Prototype.jotunnRandomizer(Entity.BJORGOLF));
		lesserImmortals.put(Entity.BLAIN, Prototype.jotunnRandomizer(Entity.BLAIN));
		lesserImmortals.put(Entity.BLAPTHVARI, Prototype.jotunnRandomizer(Entity.BLAPTHVARI));
		lesserImmortals.put(Entity.BLODUGHADDA, Prototype.jotunnRandomizer(Entity.BLODUGHADDA));
		lesserImmortals.put(Entity.BOLTHORN, Prototype.jotunnRandomizer(Entity.BOLTHORN));
		lesserImmortals.put(Entity.BRANDINGI, Prototype.jotunnRandomizer(Entity.BRANDINGI));
		lesserImmortals.put(Entity.BRIMIR, Prototype.jotunnRandomizer(Entity.BRIMIR));
		lesserImmortals.put(Entity.BRYJA, Prototype.jotunnRandomizer(Entity.BRYJA));
		lesserImmortals.put(Entity.BUSEYRA, Prototype.jotunnRandomizer(Entity.BUSEYRA));
		lesserImmortals.put(Entity.BYLEIPTR, Prototype.jotunnRandomizer(Entity.BYLEIPTR));
		lesserImmortals.put(Entity.BYLEIST, Prototype.jotunnRandomizer(Entity.BYLEIST));
		lesserImmortals.put(Entity.BYLGJA, Prototype.jotunnRandomizer(Entity.BYLGJA));
		lesserImmortals.put(Entity.DOFRI, Prototype.jotunnRandomizer(Entity.DOFRI));
		lesserImmortals.put(Entity.DROFN, Prototype.jotunnRandomizer(Entity.DROFN));
		lesserImmortals.put(Entity.DUFA, Prototype.jotunnRandomizer(Entity.DUFA));
		lesserImmortals.put(Entity.DUMBR, Prototype.jotunnRandomizer(Entity.DUMBR));
		lesserImmortals.put(Entity.DURNIR, Prototype.jotunnRandomizer(Entity.DURNIR));
		lesserImmortals.put(Entity.EGGTHER, Prototype.jotunnRandomizer(Entity.EGGTHER));
		lesserImmortals.put(Entity.EIMGEITIR, Prototype.jotunnRandomizer(Entity.EIMGEITIR));
		lesserImmortals.put(Entity.EISTLA, Prototype.jotunnRandomizer(Entity.EISTLA));
		lesserImmortals.put(Entity.EISURFALDA, Prototype.jotunnRandomizer(Entity.EISURFALDA));
		lesserImmortals.put(Entity.ELD, Prototype.jotunnRandomizer(Entity.ELD));
		lesserImmortals.put(Entity.EYRGJAFA, Prototype.jotunnRandomizer(Entity.EYRGJAFA));
		lesserImmortals.put(Entity.FALA, Prototype.jotunnRandomizer(Entity.FALA));
		lesserImmortals.put(Entity.FARBAUTI, Prototype.jotunnRandomizer(Entity.FARBAUTI));
		lesserImmortals.put(Entity.FENJA, Prototype.jotunnRandomizer(Entity.FENJA));
		lesserImmortals.put(Entity.MENJA, Prototype.jotunnRandomizer(Entity.MENJA));
		lesserImmortals.put(Entity.FENRIR, Prototype.jotunnRandomizer(Entity.FENRIR));
		lesserImmortals.put(Entity.FJOLVERK, Prototype.jotunnRandomizer(Entity.FJOLVERK));
		lesserImmortals.put(Entity.FJOLVOR, Prototype.jotunnRandomizer(Entity.FJOLVOR));
		lesserImmortals.put(Entity.FLEGG, Prototype.jotunnRandomizer(Entity.FLEGG));
		lesserImmortals.put(Entity.FORAD, Prototype.jotunnRandomizer(Entity.FORAD));
		lesserImmortals.put(Entity.FORNJOT, Prototype.jotunnRandomizer(Entity.FORNJOT));
		lesserImmortals.put(Entity.FYRNIR, Prototype.jotunnRandomizer(Entity.FYRNIR));
		lesserImmortals.put(Entity.GANGLATI, Prototype.jotunnRandomizer(Entity.GANGLATI));
		lesserImmortals.put(Entity.GANGLOT, Prototype.jotunnRandomizer(Entity.GANGLOT));
		lesserImmortals.put(Entity.GANGR, Prototype.jotunnRandomizer(Entity.GANGR));
		lesserImmortals.put(Entity.GEIRROD, Prototype.jotunnRandomizer(Entity.GEIRROD));
		lesserImmortals.put(Entity.GEITIR, Prototype.jotunnRandomizer(Entity.GEITIR));
		lesserImmortals.put(Entity.GEITLA, Prototype.jotunnRandomizer(Entity.GEITLA));
		lesserImmortals.put(Entity.GERD, Prototype.jotunnRandomizer(Entity.GERD));
		lesserImmortals.put(Entity.GESTILJA, Prototype.jotunnRandomizer(Entity.GESTILJA));
		lesserImmortals.put(Entity.GEYSA, Prototype.jotunnRandomizer(Entity.GEYSA));
		lesserImmortals.put(Entity.GILLING, Prototype.jotunnRandomizer(Entity.GILLING));
		lesserImmortals.put(Entity.GJALP, Prototype.jotunnRandomizer(Entity.GJALP));
		lesserImmortals.put(Entity.GREIP, Prototype.jotunnRandomizer(Entity.GREIP));
		lesserImmortals.put(Entity.GLAM, Prototype.jotunnRandomizer(Entity.GLAM));
		lesserImmortals.put(Entity.GLAUM, Prototype.jotunnRandomizer(Entity.GLAUM));
		lesserImmortals.put(Entity.GLAUMAR, Prototype.jotunnRandomizer(Entity.GLAUMAR));
		lesserImmortals.put(Entity.GLUMRA, Prototype.jotunnRandomizer(Entity.GLUMRA));
		lesserImmortals.put(Entity.GNEIP, Prototype.jotunnRandomizer(Entity.GNEIP));
		lesserImmortals.put(Entity.GNEIPA, Prototype.jotunnRandomizer(Entity.GNEIPA));
		lesserImmortals.put(Entity.GNISSA, Prototype.jotunnRandomizer(Entity.GNISSA));
		lesserImmortals.put(Entity.GRID, Prototype.jotunnRandomizer(Entity.GRID));
		lesserImmortals.put(Entity.GRIMLING, Prototype.jotunnRandomizer(Entity.GRIMLING));
		lesserImmortals.put(Entity.GRIMNIR, Prototype.jotunnRandomizer(Entity.GRIMNIR));
		lesserImmortals.put(Entity.GROTTINTANNA, Prototype.jotunnRandomizer(Entity.GROTTINTANNA));
		lesserImmortals.put(Entity.GRYLA, Prototype.jotunnRandomizer(Entity.GRYLA));
		lesserImmortals.put(Entity.GUNNLOD, Prototype.jotunnRandomizer(Entity.GUNNLOD));
		lesserImmortals.put(Entity.GUSIR, Prototype.jotunnRandomizer(Entity.GUSIR));
		lesserImmortals.put(Entity.GYLLIR, Prototype.jotunnRandomizer(Entity.GYLLIR));
		lesserImmortals.put(Entity.GYMIR, Prototype.jotunnRandomizer(Entity.GYMIR));
		lesserImmortals.put(Entity.HAERA, Prototype.jotunnRandomizer(Entity.HAERA));
		lesserImmortals.put(Entity.HAFLI, Prototype.jotunnRandomizer(Entity.HAFLI));
		lesserImmortals.put(Entity.HALA, Prototype.jotunnRandomizer(Entity.HALA));
		lesserImmortals.put(Entity.HARDGREIP, Prototype.jotunnRandomizer(Entity.HARDGREIP));
		lesserImmortals.put(Entity.HARDVERK, Prototype.jotunnRandomizer(Entity.HARDVERK));
		lesserImmortals.put(Entity.HASTIGI, Prototype.jotunnRandomizer(Entity.HASTIGI));
		lesserImmortals.put(Entity.HATI, Prototype.jotunnRandomizer(Entity.HATI));
		lesserImmortals.put(Entity.HEFRING, Prototype.jotunnRandomizer(Entity.HEFRING));
		lesserImmortals.put(Entity.HEIDREK, Prototype.jotunnRandomizer(Entity.HEIDREK));
		lesserImmortals.put(Entity.HEL, Prototype.jotunnRandomizer(Entity.HEL));
		lesserImmortals.put(Entity.HELBLINDI, Prototype.jotunnRandomizer(Entity.HELBLINDI));
		lesserImmortals.put(Entity.HELREGINN, Prototype.jotunnRandomizer(Entity.HELREGINN));
		lesserImmortals.put(Entity.HENGIKEPTA, Prototype.jotunnRandomizer(Entity.HENGIKEPTA));
		lesserImmortals.put(Entity.HENGJANKJAPTA, Prototype.jotunnRandomizer(Entity.HENGJANKJAPTA));
		lesserImmortals.put(Entity.HERKIR, Prototype.jotunnRandomizer(Entity.HERKIR));
		lesserImmortals.put(Entity.HERKJA, Prototype.jotunnRandomizer(Entity.HERKJA));
		lesserImmortals.put(Entity.HIMINGLAEVA, Prototype.jotunnRandomizer(Entity.HIMINGLAEVA));
		lesserImmortals.put(Entity.GLOI, Prototype.jotunnRandomizer(Entity.GLOI));
		lesserImmortals.put(Entity.HOLGABRUD, Prototype.jotunnRandomizer(Entity.HOLGABRUD));
		lesserImmortals.put(Entity.HORN, Prototype.jotunnRandomizer(Entity.HORN));
		lesserImmortals.put(Entity.HRAESVELGR, Prototype.jotunnRandomizer(Entity.HRAESVELGR));
		lesserImmortals.put(Entity.HRAUDNIR, Prototype.jotunnRandomizer(Entity.HRAUDNIR));
		lesserImmortals.put(Entity.HRAUDUNG, Prototype.jotunnRandomizer(Entity.HRAUDUNG));
		lesserImmortals.put(Entity.HRIMGERD, Prototype.jotunnRandomizer(Entity.HRIMGERD));
		lesserImmortals.put(Entity.HRIMGRIMNIR, Prototype.jotunnRandomizer(Entity.HRIMGRIMNIR));
		lesserImmortals.put(Entity.HRIMNIR, Prototype.jotunnRandomizer(Entity.HRIMNIR));
		lesserImmortals.put(Entity.HRIMTHURS, Prototype.jotunnRandomizer(Entity.HRIMTHURS));
		lesserImmortals.put(Entity.HRINGVOLNIR, Prototype.jotunnRandomizer(Entity.HRINGVOLNIR));
		lesserImmortals.put(Entity.HRIPSTOD, Prototype.jotunnRandomizer(Entity.HRIPSTOD));
		lesserImmortals.put(Entity.HROAR, Prototype.jotunnRandomizer(Entity.HROAR));
		lesserImmortals.put(Entity.HRODR, Prototype.jotunnRandomizer(Entity.HRODR));
		lesserImmortals.put(Entity.HRODVITNIR, Prototype.jotunnRandomizer(Entity.HRODVITNIR));
		lesserImmortals.put(Entity.HROKKVIR, Prototype.jotunnRandomizer(Entity.HROKKVIR));
		lesserImmortals.put(Entity.HRONN, Prototype.jotunnRandomizer(Entity.HRONN));
		lesserImmortals.put(Entity.HROSSTHJOF, Prototype.jotunnRandomizer(Entity.HROSSTHJOF));
		lesserImmortals.put(Entity.HRUGA, Prototype.jotunnRandomizer(Entity.HRUGA));
		lesserImmortals.put(Entity.HRUNGNIR, Prototype.jotunnRandomizer(Entity.HRUNGNIR));
		lesserImmortals.put(Entity.HRYGGDA, Prototype.jotunnRandomizer(Entity.HRYGGDA));
		lesserImmortals.put(Entity.HRYM, Prototype.jotunnRandomizer(Entity.HRYM));
		lesserImmortals.put(Entity.HUNDALF, Prototype.jotunnRandomizer(Entity.HUNDALF));
		lesserImmortals.put(Entity.HVAL, Prototype.jotunnRandomizer(Entity.HVAL));
		lesserImmortals.put(Entity.HVEDRA, Prototype.jotunnRandomizer(Entity.HVEDRA));
		lesserImmortals.put(Entity.HVEDRUNG, Prototype.jotunnRandomizer(Entity.HVEDRUNG));
		lesserImmortals.put(Entity.HYMIR, Prototype.jotunnRandomizer(Entity.HYMIR));
		lesserImmortals.put(Entity.HYNDLA, Prototype.jotunnRandomizer(Entity.HYNDLA));
		lesserImmortals.put(Entity.HYROKKIN, Prototype.jotunnRandomizer(Entity.HYROKKIN));
		lesserImmortals.put(Entity.IDI, Prototype.jotunnRandomizer(Entity.IDI));
		lesserImmortals.put(Entity.IM, Prototype.jotunnRandomizer(Entity.IM));
		lesserImmortals.put(Entity.IMA, Prototype.jotunnRandomizer(Entity.IMA));
		lesserImmortals.put(Entity.IMD, Prototype.jotunnRandomizer(Entity.IMD));
		lesserImmortals.put(Entity.IMGERD, Prototype.jotunnRandomizer(Entity.IMGERD));
		lesserImmortals.put(Entity.IVIDJA, Prototype.jotunnRandomizer(Entity.IVIDJA));
		lesserImmortals.put(Entity.JARNGLUMRA, Prototype.jotunnRandomizer(Entity.JARNGLUMRA));
		lesserImmortals.put(Entity.JARNSAXA, Prototype.jotunnRandomizer(Entity.JARNSAXA));
		lesserImmortals.put(Entity.JARNVIDJA, Prototype.jotunnRandomizer(Entity.JARNVIDJA));
		lesserImmortals.put(Entity.JORMUNGAND, Prototype.jotunnRandomizer(Entity.JORMUNGAND));
		lesserImmortals.put(Entity.KALDGRANI, Prototype.jotunnRandomizer(Entity.KALDGRANI));
		lesserImmortals.put(Entity.KARI, Prototype.jotunnRandomizer(Entity.KARI));
		lesserImmortals.put(Entity.KEILA, Prototype.jotunnRandomizer(Entity.KEILA));
		lesserImmortals.put(Entity.KJALLANDI, Prototype.jotunnRandomizer(Entity.KJALLANDI));
		lesserImmortals.put(Entity.KOLGA, Prototype.jotunnRandomizer(Entity.KOLGA));
		lesserImmortals.put(Entity.KOTT, Prototype.jotunnRandomizer(Entity.KOTT));
		lesserImmortals.put(Entity.KRAKA, Prototype.jotunnRandomizer(Entity.KRAKA));
		lesserImmortals.put(Entity.KYRMIR, Prototype.jotunnRandomizer(Entity.KYRMIR));
		lesserImmortals.put(Entity.KRAKA, Prototype.jotunnRandomizer(Entity.KRAKA));
		lesserImmortals.put(Entity.KYRMIR, Prototype.jotunnRandomizer(Entity.KYRMIR));
		lesserImmortals.put(Entity.LAUFEY, Prototype.jotunnRandomizer(Entity.LAUFEY));
		lesserImmortals.put(Entity.LEIFI, Prototype.jotunnRandomizer(Entity.LEIFI));
		lesserImmortals.put(Entity.LEIKN, Prototype.jotunnRandomizer(Entity.LEIKN));
		lesserImmortals.put(Entity.LEIRVOR, Prototype.jotunnRandomizer(Entity.LEIRVOR));
		lesserImmortals.put(Entity.LITR, Prototype.jotunnRandomizer(Entity.LITR));
		lesserImmortals.put(Entity.LJOTA, Prototype.jotunnRandomizer(Entity.LJOTA));
		lesserImmortals.put(Entity.LODINFINGRA, Prototype.jotunnRandomizer(Entity.LODINFINGRA));
		lesserImmortals.put(Entity.LOGI, Prototype.jotunnRandomizer(Entity.LOGI));
		lesserImmortals.put(Entity.LOKI, Prototype.jotunnRandomizer(Entity.LOKI));
		lesserImmortals.put(Entity.LOPTR, Prototype.jotunnRandomizer(Entity.LOPTR));
		lesserImmortals.put(Entity.LUT, Prototype.jotunnRandomizer(Entity.LUT));
		lesserImmortals.put(Entity.MANAGARM, Prototype.jotunnRandomizer(Entity.MANAGARM));
		lesserImmortals.put(Entity.MARGERD, Prototype.jotunnRandomizer(Entity.MARGERD));
		lesserImmortals.put(Entity.MIDJUNG, Prototype.jotunnRandomizer(Entity.MIDJUNG));
		lesserImmortals.put(Entity.MIMIR, Prototype.jotunnRandomizer(Entity.MIMIR));
		lesserImmortals.put(Entity.MODGUD, Prototype.jotunnRandomizer(Entity.MODGUD));
		lesserImmortals.put(Entity.MOGTHRASIR, Prototype.jotunnRandomizer(Entity.MOGTHRASIR));
		lesserImmortals.put(Entity.MOKKURKALFI, Prototype.jotunnRandomizer(Entity.MOKKURKALFI));
		lesserImmortals.put(Entity.MORN, Prototype.jotunnRandomizer(Entity.MORN));
		lesserImmortals.put(Entity.MUNNHARPA, Prototype.jotunnRandomizer(Entity.MUNNHARPA));
		lesserImmortals.put(Entity.MYRKRIDA, Prototype.jotunnRandomizer(Entity.MYRKRIDA));
		lesserImmortals.put(Entity.NAGLFARI, Prototype.jotunnRandomizer(Entity.NAGLFARI));
		lesserImmortals.put(Entity.NAL, Prototype.jotunnRandomizer(Entity.NAL));
		lesserImmortals.put(Entity.NARFI, Prototype.jotunnRandomizer(Entity.NARFI));
		lesserImmortals.put(Entity.NARI, Prototype.jotunnRandomizer(Entity.NARI));
		lesserImmortals.put(Entity.NATI, Prototype.jotunnRandomizer(Entity.NATI));
		lesserImmortals.put(Entity.NORFI, Prototype.jotunnRandomizer(Entity.NORFI));
		lesserImmortals.put(Entity.NOTT, Prototype.jotunnRandomizer(Entity.NOTT));
		lesserImmortals.put(Entity.OFLUGBARDA, Prototype.jotunnRandomizer(Entity.OFLUGBARDA));
		lesserImmortals.put(Entity.OFLUGBARDI, Prototype.jotunnRandomizer(Entity.OFLUGBARDI));
		lesserImmortals.put(Entity.OFOTI, Prototype.jotunnRandomizer(Entity.OFOTI));
		lesserImmortals.put(Entity.OGLADNIR, Prototype.jotunnRandomizer(Entity.OGLADNIR));
		lesserImmortals.put(Entity.OLVALDI, Prototype.jotunnRandomizer(Entity.OLVALDI));
		lesserImmortals.put(Entity.ONDUD, Prototype.jotunnRandomizer(Entity.ONDUD));
		lesserImmortals.put(Entity.OSGRUI, Prototype.jotunnRandomizer(Entity.OSGRUI));
		lesserImmortals.put(Entity.OSKRUD, Prototype.jotunnRandomizer(Entity.OSKRUD));
		lesserImmortals.put(Entity.RANGBEIN, Prototype.jotunnRandomizer(Entity.RANGBEIN));
		lesserImmortals.put(Entity.RIFINGAFLA, Prototype.jotunnRandomizer(Entity.RIFINGAFLA));
		lesserImmortals.put(Entity.RINDR, Prototype.jotunnRandomizer(Entity.RINDR));
		lesserImmortals.put(Entity.RUNGNIR, Prototype.jotunnRandomizer(Entity.RUNGNIR));
		lesserImmortals.put(Entity.RYG, Prototype.jotunnRandomizer(Entity.RYG));
		lesserImmortals.put(Entity.SAEKARLSMULI, Prototype.jotunnRandomizer(Entity.SAEKARLSMULI));
		lesserImmortals.put(Entity.SALFANG, Prototype.jotunnRandomizer(Entity.SALFANG));
		lesserImmortals.put(Entity.SAMENDIL, Prototype.jotunnRandomizer(Entity.SAMENDIL));
		lesserImmortals.put(Entity.SIGYN, Prototype.jotunnRandomizer(Entity.SIGYN));
		lesserImmortals.put(Entity.SIMUL, Prototype.jotunnRandomizer(Entity.SIMUL));
		lesserImmortals.put(Entity.SIVOR, Prototype.jotunnRandomizer(Entity.SIVOR));
		lesserImmortals.put(Entity.SKADI, Prototype.jotunnRandomizer(Entity.SKADI));
		lesserImmortals.put(Entity.SKAERIR, Prototype.jotunnRandomizer(Entity.SKAERIR));
		lesserImmortals.put(Entity.SKALLI, Prototype.jotunnRandomizer(Entity.SKALLI));
		lesserImmortals.put(Entity.SKERKIR, Prototype.jotunnRandomizer(Entity.SKERKIR));
		lesserImmortals.put(Entity.SKOLL, Prototype.jotunnRandomizer(Entity.SKOLL));
		lesserImmortals.put(Entity.SKRATI, Prototype.jotunnRandomizer(Entity.SKRATI));
		lesserImmortals.put(Entity.SKRIKJA, Prototype.jotunnRandomizer(Entity.SKRIKJA));
		lesserImmortals.put(Entity.SKRYMIR, Prototype.jotunnRandomizer(Entity.SKRYMIR));
		lesserImmortals.put(Entity.SOKKMIMIR, Prototype.jotunnRandomizer(Entity.SOKKMIMIR));
		lesserImmortals.put(Entity.SOM, Prototype.jotunnRandomizer(Entity.SOM));
		lesserImmortals.put(Entity.SPRETTING, Prototype.jotunnRandomizer(Entity.SPRETTING));
		lesserImmortals.put(Entity.STARKAD, Prototype.jotunnRandomizer(Entity.STARKAD));
		lesserImmortals.put(Entity.STIGANDI, Prototype.jotunnRandomizer(Entity.STIGANDI));
		lesserImmortals.put(Entity.STORVERK, Prototype.jotunnRandomizer(Entity.STORVERK));
		lesserImmortals.put(Entity.STUMI, Prototype.jotunnRandomizer(Entity.STUMI));
		lesserImmortals.put(Entity.SURTR, Prototype.jotunnRandomizer(Entity.SURTR));
		lesserImmortals.put(Entity.SUTTUNG, Prototype.jotunnRandomizer(Entity.SUTTUNG));
		lesserImmortals.put(Entity.SVARANG, Prototype.jotunnRandomizer(Entity.SVARANG));
		lesserImmortals.put(Entity.SVART, Prototype.jotunnRandomizer(Entity.SVART));
		lesserImmortals.put(Entity.SVASUD, Prototype.jotunnRandomizer(Entity.SVASUD));
		lesserImmortals.put(Entity.SVEIPINFALDA, Prototype.jotunnRandomizer(Entity.SVEIPINFALDA));
		lesserImmortals.put(Entity.SVIVOR, Prototype.jotunnRandomizer(Entity.SVIVOR));
		lesserImmortals.put(Entity.THISTILBARDI, Prototype.jotunnRandomizer(Entity.THISTILBARDI));
		lesserImmortals.put(Entity.THJAZI, Prototype.jotunnRandomizer(Entity.THJAZI));
		lesserImmortals.put(Entity.THORN, Prototype.jotunnRandomizer(Entity.THORN));
		lesserImmortals.put(Entity.THRASIR, Prototype.jotunnRandomizer(Entity.THRASIR));
		lesserImmortals.put(Entity.THRIGEITIR, Prototype.jotunnRandomizer(Entity.THRIGEITIR));
		lesserImmortals.put(Entity.THRIVALDI, Prototype.jotunnRandomizer(Entity.THRIVALDI));
		lesserImmortals.put(Entity.THRUDGELMIR, Prototype.jotunnRandomizer(Entity.THRUDGELMIR));
		lesserImmortals.put(Entity.THRYM, Prototype.jotunnRandomizer(Entity.THRYM));
		lesserImmortals.put(Entity.THURBORD, Prototype.jotunnRandomizer(Entity.THURBORD));
		lesserImmortals.put(Entity.ULFRUN, Prototype.jotunnRandomizer(Entity.ULFRUN));
		lesserImmortals.put(Entity.UNN, Prototype.jotunnRandomizer(Entity.UNN));
		lesserImmortals.put(Entity.UTGARDA_LOKI, Prototype.jotunnRandomizer(Entity.UTGARDA_LOKI));
		lesserImmortals.put(Entity.VAFTHRUDNIR, Prototype.jotunnRandomizer(Entity.VAFTHRUDNIR));
		lesserImmortals.put(Entity.VAGNHOFDI, Prototype.jotunnRandomizer(Entity.VAGNHOFDI));
		lesserImmortals.put(Entity.VALI, Prototype.jotunnRandomizer(Entity.VALI));
		lesserImmortals.put(Entity.VANARGAND, Prototype.jotunnRandomizer(Entity.VANARGAND));
		lesserImmortals.put(Entity.VANDIL, Prototype.jotunnRandomizer(Entity.VANDIL));
		lesserImmortals.put(Entity.VARDRUN, Prototype.jotunnRandomizer(Entity.VARDRUN));
		lesserImmortals.put(Entity.VASAD, Prototype.jotunnRandomizer(Entity.VASAD));
		lesserImmortals.put(Entity.VER, Prototype.jotunnRandomizer(Entity.VER));
		lesserImmortals.put(Entity.VIDBLINDI, Prototype.jotunnRandomizer(Entity.VIDBLINDI));
		lesserImmortals.put(Entity.VIDDI, Prototype.jotunnRandomizer(Entity.VIDDI));
		lesserImmortals.put(Entity.VIDGYMNIR, Prototype.jotunnRandomizer(Entity.VIDGYMNIR));
		lesserImmortals.put(Entity.VIGGLOD, Prototype.jotunnRandomizer(Entity.VIGGLOD));
		lesserImmortals.put(Entity.VIND, Prototype.jotunnRandomizer(Entity.VIND));
		lesserImmortals.put(Entity.VINDLONI, Prototype.jotunnRandomizer(Entity.VINDLONI));
		lesserImmortals.put(Entity.VINDSVAL, Prototype.jotunnRandomizer(Entity.VINDSVAL));
		lesserImmortals.put(Entity.VINGNIR, Prototype.jotunnRandomizer(Entity.VINGNIR));
		lesserImmortals.put(Entity.VINGRIP, Prototype.jotunnRandomizer(Entity.VINGRIP));
		lesserImmortals.put(Entity.VIPAR, Prototype.jotunnRandomizer(Entity.VIPAR));
		lesserImmortals.put(Entity.VORNIR, Prototype.jotunnRandomizer(Entity.VORNIR));
		lesserImmortals.put(Entity.YMIR, Prototype.jotunnRandomizer(Entity.YMIR));
		lesserImmortals.put(Entity.YMSI, Prototype.jotunnRandomizer(Entity.YMSI));

	}

	/*
	 * STATIC METHODS
	 */
	public static Set<Entity> powersOfAlignment(Alignment alignment) {
		Set<Entity> set = new HashSet<Entity>();

		Entity candidate;
		Prototype prototype;
		for (Iterator<Entity> it = greaterImmortals.keySet().iterator(); it.hasNext();) {
			candidate = it.next();
			prototype = greaterImmortals.get(candidate);
			if (prototype.alignment.contains(alignment))
				set.add(candidate);
		}

		// for (Iterator<Entity> it = lesserImmortals.keySet().iterator();
		// it.hasNext();) {
		// candidate = it.next();
		// prototype = lesserImmortals.get(candidate);
		// if (prototype.alignment.contains(alignment))
		// set.add(candidate);
		// }

		return set;
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
