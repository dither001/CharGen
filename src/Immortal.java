import java.util.Collection;
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
		// DAWN WAR GODS (4e D&D)
		ASMODEUS, AVANDRA, BAHAMUT, BANE, CORELLON, ERATHIS, GRUUMSH, IOUN, KORD, LOLTH, MELORA, MORADIN, PELOR, RAVEN_QUEEN, SEHANINE, THARIZDUN, TIAMAT, TOROG, VECNA, ZEHIR,
		// JOTNAR (Norse)
		AEGIR, ALFARIN, ALSVART, AM, AMA, AMGERD, ANGEYJA, ANGRBODA, ASVID, ATLA, AURBODA, AURGELMIR, AURGRIMNIR, AURNIR, AURVANDIL, BAKRAUF, BARA, BAUGI, BEINVID, BELI, BERGELMIR, BESTLA, BILLINGR, BJORGOLF, BLAIN, BLAPTHVARI, BLODUGHADDA, BOLTHORN, BRANDINGI, BRIMIR, BRYJA, BUSEYRA, BYLEIPTR, BYLEIST, BYLGJA, DOFRI, DROFN, DUFA, DUMBR, DURNIR, EGGTHER, EIMGEITIR, EISTLA, EISURFALDA, ELD, EYRGJAFA, FALA, FARBAUTI, FENJA, MENJA, FENRIR, FJOLVERK, FJOLVOR, FLEGG, FORAD, FORNJOT, FYRNIR, GANGLATI, GANGLOT, GANGR, GEIRROD, GEITIR, GEITLA, GERD, GESTILJA, GEYSA, GILLING, GJALP, GREIP, GLAM, GLAUM, GLAUMAR, GLUMRA, GNEIP, GNEIPA, GNISSA, GRID, GRIMLING, GRIMNIR, GROTTINTANNA, GRYLA, GUNNLOD, GUSIR, GYLLIR, GYMIR, HAERA, HAFLI, HALA, HARDGREIP, HARDVERK, HASTIGI, HATI, HEFRING, HEIDREK, HEL, HELBLINDI, HELREGINN, HENGIKEPTA, HENGJANKJAPTA, HERKIR, HERKJA, HIMINGLAEVA, GLOI, HOLGABRUD, HORN, HRAESVELGR, HRAUDNIR, HRAUDUNG, HRIMGERD, HRIMGRIMNIR, HRIMNIR, HRIMTHURS, HRINGVOLNIR, HRIPSTOD, HROAR, HRODR, HRODVITNIR, HROKKVIR, HRONN, HROSSTHJOF, HRUGA, HRUNGNIR, HRYGGDA, HRYM, HUNDALF, HVAL, HVEDRA, HVEDRUNG, HYMIR, HYNDLA, HYROKKIN, IDI, IM, IMA, IMD, IMGERD, IVIDJA, JARNGLUMRA, JARNSAXA, JARNVIDJA, JORMUNGAND, KALDGRANI, KARI, KEILA, KJALLANDI, KOLGA, KOTT, KRAKA, KYRMIR, LAUFEY, LEIFI, LEIKN, LEIRVOR, LITR, LJOTA, LODINFINGRA, LOGI, LOKI, LOPTR, LUT, MANAGARM, MARGERD, MIDJUNG, MIMIR, MODGUD, MOGTHRASIR, MOKKURKALFI, MORN, MUNNHARPA, MYRKRIDA, NAGLFARI, NAL, NARFI, NARI, NATI, NORFI, NOTT, OFLUGBARDA, OFLUGBARDI, OFOTI, OGLADNIR, OLVALDI, ONDUD, OSGRUI, OSKRUD, RANGBEIN, RIFINGAFLA, RINDR, RUNGNIR, RYG, SAEKARLSMULI, SALFANG, SAMENDIL, SIGYN, SIMUL, SIVOR, SKADI, SKAERIR, SKALLI, SKERKIR, SKOLL, SKRATI, SKRIKJA, SKRYMIR, SOKKMIMIR, SOM, SPRETTING, STARKAD, STIGANDI, STORVERK, STUMI, SURTR, SUTTUNG, SVARANG, SVART, SVASUD, SVEIPINFALDA, SVIVOR, THISTILBARDI, THJAZI, THORN, THRASIR, THRIGEITIR, THRIVALDI, THRUDGELMIR, THRYM, THURBORD, ULFRUN, UNN, UTGARDA_LOKI, VAFTHRUDNIR, VAGNHOFDI, VALI, VANARGAND, VANDIL, VARDRUN, VASAD, VER, VIDBLINDI, VIDDI, VIDGYMNIR, VIGGLOD, VIND, VINDLONI, VINDSVAL, VINGNIR, VINGRIP, VIPAR, VORNIR, YMIR, YMSI,
		// Enochian Angels 1500s
		AAAN, AADT, AAETPIO, AANAA, AAODT, AAOXAIF, AAVAN, AAVNA, AAX, ABAMO, ABAOZ, ABMO, ABOZ, ACAR, ACCA, ACMBICU, ACPS, ACRAR, ACUCA, ACUPS, ACZINOR, ADAEOET, ADI, ADIRE, ADNOP, ADOPT, ADOPA, ADOTA, ADRE, ADTA, AGB, AGLM, AGMLM, AHAOZPI, AIAOAI_OIIIT, AIGRA, AIRA, AMOX, AMSOX, ANCRO, ANIMOTIX, ANPIEL, ANVAA, APA, APAHR, APDOCE, APHR, APLST, APM, APST, ARIZL, ARN, ARZL, ARYLIC, ATAAD, ATDIM, ATO, AVTOTAR, BARADIEL, BARNABAS, BATAIVA, BIVHD, BRAP, BRIAP, BRCN, CADAAMP, CAMAEL, CASSIEL, CMS, CNABR, CNBR, CPSA, CPUSA, DIARI, DIMT, DIMNT, DIOM, DIRI, DIXOM, DMX, DMITRI, DOLOP, DONPA, DOOP, DOPA, DTAA, DTOAA, DXAGZ, DXGZ, EAC, EBOZA, ECAOP, ECANUS, ECOP, EDLPRNAA, EPHRA, ERG, ERN, ERUBEY, ERZLA, EUTPA, EXARP, EXR, FAAX, FMND, GABRIEL, GALGALLIEL, GBAL, GBEAL, GLMA, GLMMA, GMDNM, HABIORO, HBR, HCNBR, HCOMA, HIPOTGA, HRAAP, HRAP, HROAN, HRU, HTMORDA, HUA, HXGZD, ICZHIHA, IMNTD, IMTD, IZAZ, IZINR, IZIXP, IZNR, IZRAZ, IZXP, KOKABIEL, LAIRZ, LANG, LAOAXRP, LARZ, LAVAVOTH, LEAOC, LEVANAEL, LIGDISA, LMAG, LMMAG, LSRAPHM, MASGM, MICHAEL, MIZ, MMA, MSAL, MSMAL, MTDI, MTNDI, MTO, NAAA, NANTA, NAOO, NAOOO, NAVAA, NBARC, NBRC, NDAZN, NDZN, NHDD, NHODD, NLIRX, NLRX, NPAT, NRCOA, NROA, OACNR, OANR, OAP, OBGOTA_AABACO, OCNM, OMAGG, OMGG, OMIA, OMSIA, ONA, ONH, ONP, OODPZ, OOPZ, OPAD, OPAMN, OPAMA, OPHANIEL, OPMN, OPNA, OPNAD, ORMN, ORO_IBAH_AOZPI, ORPMN, OTOI, OTROI, OYAUB, OYUB, OZAAB, OZAB, PACO, PADO, PAEOC, PAICO, PIZ, PMAGL, PMOX, PMZOX, PPSAC, RAAGIOSL, RAPH, RAPHAEL, RBNH, RBZNH, RCANB, RCNB, RDA, RGAN, RGOAN, RLEMU, RLMU, RPA, RRB, RRL, RSI, RSONI, RUOI, RUROI, RXAO, RXINL, RXNL, RXP, RXPAO, RZILA, RZLA, RZIONR_NRZFM, SAIINOU, SAIINOV, SAAIZ, SAUCP, SACP, SCMIO, SIOSP, SISP, SHONDA, SLGAIOL, SONIZNT, TDIM, TDNIM, TPAU, TPLAU, URIEL, USPSN, USSN, UTIPA, UTPA, UVB, VAASA, VASA, VOLXDO_SIODA, XAI, XCZ, XDZ, XGAZD, XGZD, XII, XNILR, XOM, XOY, XPA, XPAXN, XPCN, XRINH, XRNH, XXAN, YASEN, ZAABO, ZABO, ZARNAAH, ZARZI, ZARZILG, ZAZI, ZDAXG, ZDXG, ZEDEKIEL, ZINGGEN, ZIRACAH, ZIRZ, ZIZA, ZURCHOL

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

		@Override
		public String toString() {
			String string;

			// string = String.format("%s %s %s", entity, alignment.toString(),
			// domains.toString());
			string = String.format("%-15s %s", entity, domains.toString());

			return string;
		}
	}

	/*
	 * STATIC FIELDS
	 */
	private static HashMap<Entity, Prototype> greaterImmortals;
	private static HashMap<Entity, Prototype> lesserImmortals;

	private static final Domain[] DOMAINS = new Domain[] { Domain.DEATH, Domain.KNOWLEDGE, Domain.LIFE, Domain.LIGHT,
			Domain.NATURE, Domain.TEMPEST, Domain.TRICKERY, Domain.WAR };

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

	/*
	 * ENOCHIAN ANGELS (John Dee: 1500s)
	 */
	private static final Entity[] ENOCHIAN_ANGELS = { Entity.AAAN, Entity.AADT, Entity.AAETPIO, Entity.AANAA,
			Entity.AAODT, Entity.AAOXAIF, Entity.AAVAN, Entity.AAVAN, Entity.AAVNA, Entity.AAX, Entity.ABAMO,
			Entity.ABAOZ, Entity.ABMO, Entity.ABOZ, Entity.ACAR, Entity.ACCA, Entity.ACMBICU, Entity.ACPS, Entity.ACRAR,
			Entity.ACUCA, Entity.ACUPS, Entity.ACZINOR, Entity.ADAEOET, Entity.ADI, Entity.ADIRE, Entity.ADNOP,
			Entity.ADOPT, Entity.ADOPA, Entity.ADOTA, Entity.ADRE, Entity.ADTA, Entity.AGB, Entity.AGLM, Entity.AGMLM,
			Entity.AHAOZPI, Entity.AIAOAI_OIIIT, Entity.AIGRA, Entity.AIRA, Entity.AMOX, Entity.AMSOX, Entity.ANCRO,
			Entity.ANIMOTIX, Entity.ANPIEL, Entity.ANVAA, Entity.APA, Entity.APAHR, Entity.APDOCE, Entity.APHR,
			Entity.APLST, Entity.APM, Entity.APST, Entity.ARIZL, Entity.ARN, Entity.ARZL, Entity.ARYLIC, Entity.ATAAD,
			Entity.ATDIM, Entity.ATO, Entity.AVTOTAR, Entity.BARADIEL, Entity.BARNABAS, Entity.BATAIVA, Entity.BIVHD,
			Entity.BRAP, Entity.BRIAP, Entity.BRCN, Entity.CADAAMP, Entity.CAMAEL, Entity.CASSIEL, Entity.CMS,
			Entity.CNABR, Entity.CNBR, Entity.CPSA, Entity.CPUSA, Entity.DIARI, Entity.DIMT, Entity.DIMNT, Entity.DIOM,
			Entity.DIRI, Entity.DIXOM, Entity.DMX, Entity.DMITRI, Entity.DOLOP, Entity.DONPA, Entity.DOOP, Entity.DOPA,
			Entity.DTAA, Entity.DTOAA, Entity.DXAGZ, Entity.DXGZ, Entity.EAC, Entity.EBOZA, Entity.ECAOP, Entity.ECANUS,
			Entity.ECOP, Entity.EDLPRNAA, Entity.EPHRA, Entity.ERG, Entity.ERN, Entity.ERUBEY, Entity.ERZLA,
			Entity.EUTPA, Entity.EXARP, Entity.EXR, Entity.FAAX, Entity.FMND, Entity.GABRIEL, Entity.GALGALLIEL,
			Entity.GBAL, Entity.GBEAL, Entity.GLMA, Entity.GLMMA, Entity.GMDNM, Entity.HABIORO, Entity.HBR,
			Entity.HCNBR, Entity.HCOMA, Entity.HIPOTGA, Entity.HRAAP, Entity.HRAP, Entity.HROAN, Entity.HRU,
			Entity.HTMORDA, Entity.HUA, Entity.HXGZD, Entity.ICZHIHA, Entity.IMNTD, Entity.IMTD, Entity.IZAZ,
			Entity.IZINR, Entity.IZIXP, Entity.IZNR, Entity.IZRAZ, Entity.IZXP, Entity.KOKABIEL, Entity.LAIRZ,
			Entity.LANG, Entity.LAOAXRP, Entity.LARZ, Entity.LAVAVOTH, Entity.LEAOC, Entity.LEVANAEL, Entity.LIGDISA,
			Entity.LMAG, Entity.LMMAG, Entity.LSRAPHM, Entity.MASGM, Entity.MICHAEL, Entity.MIZ, Entity.MMA,
			Entity.MSAL, Entity.MSMAL, Entity.MTDI, Entity.MTNDI, Entity.MTO, Entity.NAAA, Entity.NANTA, Entity.NAOO,
			Entity.NAOOO, Entity.NAVAA, Entity.NBARC, Entity.NBRC, Entity.NDAZN, Entity.NDZN, Entity.NHDD, Entity.NHODD,
			Entity.NLIRX, Entity.NLRX, Entity.NPAT, Entity.NRCOA, Entity.NROA, Entity.OACNR, Entity.OANR, Entity.OAP,
			Entity.OBGOTA_AABACO, Entity.OCNM, Entity.OMAGG, Entity.OMGG, Entity.OMIA, Entity.OMSIA, Entity.ONA,
			Entity.ONH, Entity.ONP, Entity.OODPZ, Entity.OOPZ, Entity.OPAD, Entity.OPAMN, Entity.OPAMA, Entity.OPHANIEL,
			Entity.OPMN, Entity.OPNA, Entity.OPNAD, Entity.ORMN, Entity.ORO_IBAH_AOZPI, Entity.ORPMN, Entity.OTOI,
			Entity.OTROI, Entity.OYAUB, Entity.OYUB, Entity.OZAAB, Entity.OZAB, Entity.PACO, Entity.PADO, Entity.PAEOC,
			Entity.PAICO, Entity.PIZ, Entity.PMAGL, Entity.PMOX, Entity.PMZOX, Entity.PPSAC, Entity.RAAGIOSL,
			Entity.RAPH, Entity.RAPHAEL, Entity.RBNH, Entity.RBZNH, Entity.RCANB, Entity.RCNB, Entity.RDA, Entity.RGAN,
			Entity.RGOAN, Entity.RLEMU, Entity.RLMU, Entity.RPA, Entity.RRB, Entity.RRL, Entity.RSI, Entity.RSONI,
			Entity.RUOI, Entity.RUROI, Entity.RXAO, Entity.RXINL, Entity.RXNL, Entity.RXP, Entity.RXPAO, Entity.RZILA,
			Entity.RZLA, Entity.RZIONR_NRZFM, Entity.SAIINOU, Entity.SAIINOV, Entity.SAAIZ, Entity.SAUCP, Entity.SACP,
			Entity.SCMIO, Entity.SIOSP, Entity.SISP, Entity.SHONDA, Entity.SLGAIOL, Entity.SONIZNT, Entity.TDIM,
			Entity.TDNIM, Entity.TPAU, Entity.TPLAU, Entity.URIEL, Entity.USPSN, Entity.USSN, Entity.UTIPA, Entity.UTPA,
			Entity.UVB, Entity.VAASA, Entity.VASA, Entity.VOLXDO_SIODA, Entity.XAI, Entity.XCZ, Entity.XDZ,
			Entity.XGAZD, Entity.XGZD, Entity.XII, Entity.XNILR, Entity.XOM, Entity.XOY, Entity.XPA, Entity.XPAXN,
			Entity.XPCN, Entity.XRINH, Entity.XRNH, Entity.XXAN, Entity.YASEN, Entity.ZAABO, Entity.ZABO,
			Entity.ZARNAAH, Entity.ZARZI, Entity.ZARZILG, Entity.ZAZI, Entity.ZDAXG, Entity.ZDXG, Entity.ZEDEKIEL,
			Entity.ZINGGEN, Entity.ZIRACAH, Entity.ZIRZ, Entity.ZIZA, Entity.ZURCHOL };

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
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.JOTUNN, Alignment.NEUTRAL));
		}

		// ENOCHIAN ANGELS
		array = ENOCHIAN_ANGELS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.ANGEL, Alignment.GOOD));
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

	public static Collection<Prototype> getLesserBeings() {
		return lesserImmortals.values();
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

	private static Domain[] domainRandomizer() {
		Domain[] array = null;

		int dice = Dice.roll(10);
		if (dice < 5)
			array = new Domain[1];
		else if (dice == 5 || dice == 6 || dice == 7)
			array = new Domain[2];
		else if (dice == 8 || dice == 9)
			array = new Domain[3];
		else
			array = new Domain[4];

		dice = Dice.roll(10);
		if (array.length == 1 && dice < 5) {
			array[0] = Domain.NATURE;
		} else if (array.length == 1 && (dice == 5 || dice == 6 || dice == 7)) {
			array[0] = (Dice.roll(2) == 1) ? Domain.LIFE : Domain.LIGHT;
		} else if (array.length == 1 && (dice == 8 || dice == 9)) {
			array[0] = (Dice.roll(2) == 1) ? Domain.TRICKERY : Domain.WAR;
		} else if (array.length == 1) {
			array[0] = Dice.randomFromArray(DOMAINS);
		} else {
			// all bets are off
			EnumSet<Domain> set = EnumSet.noneOf(Domain.class);
			while (set.size() < array.length) {
				set.add(Dice.randomFromArray(DOMAINS));
			}

			Iterator<Domain> it = set.iterator();
			for (int i = 0; i < array.length; ++i) {
				array[i] = it.next();
			}
		}

		return array;
	}

	private static Prototype lesserBeing(Entity entity, Lesser lesser, Alignment axis) {
		Alignment[][] axes = new Alignment[][] {
				// LAWFUL
				{ Alignment.LAWFUL, Alignment.NEUTRAL }, { Alignment.LAWFUL, Alignment.GOOD },
				{ Alignment.LAWFUL, Alignment.EVIL },
				// CHAOTIC
				{ Alignment.CHAOTIC, Alignment.NEUTRAL }, { Alignment.CHAOTIC, Alignment.GOOD },
				{ Alignment.CHAOTIC, Alignment.EVIL },
				// GOOD
				{ Alignment.GOOD, Alignment.NEUTRAL }, { Alignment.GOOD, Alignment.CHAOTIC },
				{ Alignment.GOOD, Alignment.LAWFUL },
				// EVIL
				{ Alignment.EVIL, Alignment.NEUTRAL }, { Alignment.EVIL, Alignment.CHAOTIC },
				{ Alignment.EVIL, Alignment.LAWFUL } };

		Alignment[] corners = new Alignment[] { Alignment.LAWFUL, Alignment.CHAOTIC, Alignment.GOOD, Alignment.EVIL };

		//
		Alignment[] alignment = null;
		int dice = Dice.roll(10);
		if (axis.equals(Alignment.LAWFUL)) {
			alignment = axes[0];
		} else if (axis.equals(Alignment.CHAOTIC)) {
			alignment = axes[1];
		} else if (axis.equals(Alignment.GOOD)) {
			alignment = axes[2];
		} else if (axis.equals(Alignment.EVIL)) {
			alignment = axes[3];
		} else if (dice < 5) {
			alignment = new Alignment[] { Alignment.NEUTRAL };
		} else {
			alignment = new Alignment[] { Alignment.NEUTRAL, Dice.randomFromArray(corners) };
		}

		return new Prototype(entity, lesser, alignment, domainRandomizer());
	}
}
