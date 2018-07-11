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

	/*
	 * DAWN WAR
	 */
	private static final Entity[] DAWN_WAR = { Entity.ASMODEUS, Entity.AVANDRA, Entity.BAHAMUT, Entity.BANE,
			Entity.CORELLON, Entity.ERATHIS, Entity.GRUUMSH, Entity.IOUN, Entity.KORD, Entity.LOLTH, Entity.MELORA,
			Entity.MORADIN, Entity.PELOR, Entity.RAVEN_QUEEN, Entity.SEHANINE, Entity.THARIZDUN, Entity.TIAMAT,
			Entity.TOROG, Entity.VECNA, Entity.ZEHIR };

	/*
	 * NORSE
	 */
	private static final Entity[] JOTNAR = { Entity.AEGIR, Entity.ALFARIN, Entity.ALSVART, Entity.AM, Entity.AMA,
			Entity.AMGERD, Entity.ANGEYJA, Entity.ANGRBODA, Entity.ASVID, Entity.ATLA, Entity.AURBODA, Entity.AURGELMIR,
			Entity.AURGRIMNIR, Entity.AURNIR, Entity.AURVANDIL, Entity.BAKRAUF, Entity.BARA, Entity.BAUGI,
			Entity.BEINVID, Entity.BELI, Entity.BERGELMIR, Entity.BESTLA, Entity.BILLINGR, Entity.BJORGOLF,
			Entity.BLAIN, Entity.BLAPTHVARI, Entity.BLODUGHADDA, Entity.BOLTHORN, Entity.BRANDINGI, Entity.BRIMIR,
			Entity.BRYJA, Entity.BUSEYRA, Entity.BYLEIPTR, Entity.BYLEIST, Entity.BYLGJA, Entity.DOFRI, Entity.DROFN,
			Entity.DUFA, Entity.DUMBR, Entity.DURNIR, Entity.EGGTHER, Entity.EIMGEITIR, Entity.EISTLA,
			Entity.EISURFALDA, Entity.ELD, Entity.EYRGJAFA, Entity.FALA, Entity.FARBAUTI, Entity.FENJA, Entity.MENJA,
			Entity.FENRIR, Entity.FJOLVERK, Entity.FJOLVOR, Entity.FLEGG, Entity.FORAD, Entity.FORNJOT, Entity.FYRNIR,
			Entity.GANGLATI, Entity.GANGLOT, Entity.GANGR, Entity.GEIRROD, Entity.GEITIR, Entity.GEITLA, Entity.GERD,
			Entity.GESTILJA, Entity.GEYSA, Entity.GILLING, Entity.GJALP, Entity.GREIP, Entity.GLAM, Entity.GLAUM,
			Entity.GLAUMAR, Entity.GLUMRA, Entity.GNEIP, Entity.GNEIPA, Entity.GNISSA, Entity.GRID, Entity.GRIMLING,
			Entity.GRIMNIR, Entity.GROTTINTANNA, Entity.GRYLA, Entity.GUNNLOD, Entity.GUSIR, Entity.GYLLIR,
			Entity.GYMIR, Entity.HAERA, Entity.HAFLI, Entity.HALA, Entity.HARDGREIP, Entity.HARDVERK, Entity.HASTIGI,
			Entity.HATI, Entity.HEFRING, Entity.HEIDREK, Entity.HEL, Entity.HELBLINDI, Entity.HELREGINN,
			Entity.HENGIKEPTA, Entity.HENGJANKJAPTA, Entity.HERKIR, Entity.HERKJA, Entity.HIMINGLAEVA, Entity.GLOI,
			Entity.HOLGABRUD, Entity.HORN, Entity.HRAESVELGR, Entity.HRAUDNIR, Entity.HRAUDUNG, Entity.HRIMGERD,
			Entity.HRIMGRIMNIR, Entity.HRIMNIR, Entity.HRIMTHURS, Entity.HRINGVOLNIR, Entity.HRIPSTOD, Entity.HROAR,
			Entity.HRODR, Entity.HRODVITNIR, Entity.HROKKVIR, Entity.HRONN, Entity.HROSSTHJOF, Entity.HRUGA,
			Entity.HRUNGNIR, Entity.HRYGGDA, Entity.HRYM, Entity.HUNDALF, Entity.HVAL, Entity.HVEDRA, Entity.HVEDRUNG,
			Entity.HYMIR, Entity.HYNDLA, Entity.HYROKKIN, Entity.IDI, Entity.IM, Entity.IMA, Entity.IMD, Entity.IMGERD,
			Entity.IVIDJA, Entity.JARNGLUMRA, Entity.JARNSAXA, Entity.JARNVIDJA, Entity.JORMUNGAND, Entity.KALDGRANI,
			Entity.KARI, Entity.KEILA, Entity.KJALLANDI, Entity.KOLGA, Entity.KOTT, Entity.KRAKA, Entity.KYRMIR,
			Entity.LAUFEY, Entity.LEIFI, Entity.LEIKN, Entity.LEIRVOR, Entity.LITR, Entity.LJOTA, Entity.LODINFINGRA,
			Entity.LOGI, Entity.LOKI, Entity.LOPTR, Entity.LUT, Entity.MANAGARM, Entity.MARGERD, Entity.MIDJUNG,
			Entity.MIMIR, Entity.MODGUD, Entity.MOGTHRASIR, Entity.MOKKURKALFI, Entity.MORN, Entity.MUNNHARPA,
			Entity.MYRKRIDA, Entity.NAGLFARI, Entity.NAL, Entity.NARFI, Entity.NARI, Entity.NATI, Entity.NORFI,
			Entity.NOTT, Entity.OFLUGBARDA, Entity.OFLUGBARDI, Entity.OFOTI, Entity.OGLADNIR, Entity.OLVALDI,
			Entity.ONDUD, Entity.OSGRUI, Entity.OSKRUD, Entity.RANGBEIN, Entity.RIFINGAFLA, Entity.RINDR,
			Entity.RUNGNIR, Entity.RYG, Entity.SAEKARLSMULI, Entity.SALFANG, Entity.SAMENDIL, Entity.SIGYN,
			Entity.SIMUL, Entity.SIVOR, Entity.SKADI, Entity.SKAERIR, Entity.SKALLI, Entity.SKERKIR, Entity.SKOLL,
			Entity.SKRATI, Entity.SKRIKJA, Entity.SKRYMIR, Entity.SOKKMIMIR, Entity.SOM, Entity.SPRETTING,
			Entity.STARKAD, Entity.STIGANDI, Entity.STORVERK, Entity.STUMI, Entity.SURTR, Entity.SUTTUNG,
			Entity.SVARANG, Entity.SVART, Entity.SVASUD, Entity.SVEIPINFALDA, Entity.SVIVOR, Entity.THISTILBARDI,
			Entity.THJAZI, Entity.THORN, Entity.THRASIR, Entity.THRIGEITIR, Entity.THRIVALDI, Entity.THRUDGELMIR,
			Entity.THRYM, Entity.THURBORD, Entity.ULFRUN, Entity.UNN, Entity.UTGARDA_LOKI, Entity.VAFTHRUDNIR,
			Entity.VAGNHOFDI, Entity.VALI, Entity.VANARGAND, Entity.VANDIL, Entity.VARDRUN, Entity.VASAD, Entity.VER,
			Entity.VIDBLINDI, Entity.VIDDI, Entity.VIDGYMNIR, Entity.VIGGLOD, Entity.VIND, Entity.VINDLONI,
			Entity.VINDSVAL, Entity.VINGNIR, Entity.VINGRIP, Entity.VIPAR, Entity.VORNIR, Entity.YMIR, Entity.YMSI };

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
		Entity[] array = JOTNAR;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], jotunnRandomizer(array[i]));
		}

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
