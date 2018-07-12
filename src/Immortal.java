import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
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
		 * CANAANITE pantheon: Elohim or "sons of El"
		 */
		ELOHIM,
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
		OLYMPIAN, CTHONIAN, TITAN, CYCLOPS, CANTAUR, ERIS,
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
		// Canaanite gods: Elohim, "sons of El"
		ANAT, ASHERAH, ASTARTE, ATHIRAT, ATTAR, BETH_HORON, DAGON, ELYON, ERETZ, ESHMUN, HADAD, HAMMON, HERMON, ISHAT, KHIKHIBI, KOTHAR_WA_KHASIS, KOTHARAT, LOTAN, MARQOD, MELQART, MISOR, MOLOCH, MOT, NIKKAL_WA_IB, QADESHTU, RESHEPH, SHAHAR, SHALIM, SHAMAYIM, SHAPASH, SHEOL, SYDYK, TAAUTUS, TANIT, YAM, YARIKH,
		/*
		 * JUDAISM
		 */
		// Jewish angels
		CAMAEL, CASSIEL, GABRIEL, MICHAEL, OPHANIEL, ABADDON, ADRIEL, ANAEL, HANIEL, ARARIEL, ARIEL, AZAZEL, BARAQIEL, CHERUBIEL, DANIEL, DUMAH, EREMIEL, GADREEL, HADRANIEL, HAHASIAH, HASMAL, HESEDIEL, ZADKIEL, HOFNIEL, IMAMIAH, JEHOEL, JEQUN, JERAHMEEL, JOPHIEL, KEPHAREL, KERUBIEL, KUSHIEL, LELIEL, HELEL, MEBAHIAH, METATRON, MIKAIL, NANAEL, NETZACH, NITHAEL, NURIEL, PHANUEL, POYEL, PRAVUIL, PURIEL, QAPHSIEL, RADUERIEL, RAGUEL, AZRAEL, RAPHAEL, RAZIEL, REMIEL, RIKBIEL, SACHIEL, SAHAQUIEL, SABRIEL, SANDALPHON, SARIEL, SCHEMHAMPHARAE, SERAPHIEL, SHAMSIEL, SIDRIEL, TEMELUCHUS, URIEL, UZIEL, VASIARIAH, VEHUEL, ZAPHKIEL, ZEPHANIEL, ZEPHON, ZOPHIEL,
		// Jewish demons
		ABEZETHIBOU, ABYZOU, AGALIAREPT, AGRAT_BAT_MAHLAT, AGRIEL, ARMAROS, ASB_EL, BALBERITH, BEELZEBUB, BEHEMOTH, BELIAL, DANJAL, EISHETH, GADER_EL, GRIGORI, KASADYA, LEVIATHAN, LILIN, MASTEMA, NAAMAH, ONOSKELIS, PENEMUE, RAHAB, SAMAEL, SEMYAZA, SHEDIM, TANNIN, YEQON,
		/*
		 * HELLENIC
		 */
		// OLYMPIANS
		APHRODITE, APOLLO, ARES, ARTEMIS, ATHENA, DEMETER, DIONYSUS, HEPHAESTUS, HERA, HERMES, HESTIA, ZEUS,
		// TITANS
		ARKE, ASTERIA, ASTRAEUS, ASTRAIOS, ATLAS, AURA, CLYMENE, COEUS, CRIUS, CRONUS, DIONE, EOS, EPIMETHEUS, EURYBIA, HELIOS, HYPERION, IAPETUS, LELANTOS, LETO, MENOETIUS, METIS, MNEMOSYNE, OCEANUS, PALLAS, PERSES, PHOEBE, PROMETHEUS, RHEA, SELENE, TETHYS, THEIA, THEMIS,
		// Talos: Bronze Giant made by Hephaestus
		TALOS,
		// Greek cyclopes
		AGRIUS, ALCYONEUS, ALOADAE, ALPOS, ANAX, ANTAEUS, ANTIPHATES, ARGES, ARGOS_PANOPTES, ARISTAEUS, AZEUS, BRIAREUS, BRONTES, CACUS, CHRYSAOR, COTTUS, CYMOPOLEIA, DAMASEN, DAMYSUS, ECHIDNADES, ELATREUS, ENCELADUS, EPHIALTES, EURYALUS, EURYMEDON, GERYON, GYES, HALIMEDES, HOPLODAMUS, HYLLUS, LEON, MYLINUS, OEOLYCA, OREUS, ORION, OTUS, PERIBOEA, POLYBOTES, POLYPHEMUS, PORPHYRION, STEROPES, SYCEUS, TITYUS, TRACHIUS,
		// Christian angels
		BARACHIEL, JEGUDIEL, LUCIFER, MURIEL, PAHALIAH, SARATHIEL, SELAPHIEL, WORMWOOD, ZACHARIEL,
		// Christian demons
		AGARES, HABORYM, ALASTOR, AMAYMON, AMDUSIAS, ANCITIF, ANDREALPHUS, ANDROMALIUS, ASTAROTH, BAAL, BALAM, BAPHOMET, BARBAS, BARBATOS, BATHIN, BELETH, BELPHEGOR, BEHERIT, BIFRONS, BOTIS, BUER, BUNE, CAIM, CIMEJES, CORSON, CROCELL, KIMARIS, PROCELL, DANTALION, DECARABIA, DEMOGORGON, ELIGOS, FOCALOR, FORCAS, FORNEUS, FURFUR, GAAP, GAMIGIN, GLASYA_LABOLAS, GREMORY, GUSION, HAAGENTI, HALPHAS, MALTHUS, HAURES, FLAVROS, INCUBUS, IPOS, KRAMPUS, LEGION, LEONARD, LERAJE, LUCIFUGE_ROFOCALE, MAMMON, MARAX, MARCHOSIAS, MEPHISTOPHELES, MERIHEM, MURMUR, NABERIUS, ORCUS, ORIAX, OROBAS, OSE, PAIMON, PHENEX, PITHIUS, PRUFLAS, RAUM, RONOVE, SABNOCK, SALEOS, SEIR, SHAX, SITRI, STOLAS, SUCCUBUS, SURGAT, UKOBACH, VALAC, VALEFAR, VAPULA, VASSAGO, VEPAR, VINE, ZAGAN, ZEPAR, ZIMINIAR,
		// Islamic angels
		ARTIYA_IL, DARDA_IL, HARUT, HAMALAT_AL_ARSH, ISRAFIL, KIRAMAN_KATIBIN, MAALIK, MARUT, MUNKAR, NAKIR,
		// Islamic demons
		DAJJAL, EBLIS,
		// JOTNAR (Norse)
		AEGIR, ALFARIN, ALSVART, AM, AMA, AMGERD, ANGEYJA, ANGRBODA, ASVID, ATLA, AURBODA, AURGELMIR, AURGRIMNIR, AURNIR, AURVANDIL, BAKRAUF, BARA, BAUGI, BEINVID, BELI, BERGELMIR, BESTLA, BILLINGR, BJORGOLF, BLAIN, BLAPTHVARI, BLODUGHADDA, BOLTHORN, BRANDINGI, BRIMIR, BRYJA, BUSEYRA, BYLEIPTR, BYLEIST, BYLGJA, DOFRI, DROFN, DUFA, DUMBR, DURNIR, EGGTHER, EIMGEITIR, EISTLA, EISURFALDA, ELD, EYRGJAFA, FALA, FARBAUTI, FENJA, MENJA, FENRIR, FJOLVERK, FJOLVOR, FLEGG, FORAD, FORNJOT, FYRNIR, GANGLATI, GANGLOT, GANGR, GEIRROD, GEITIR, GEITLA, GERD, GESTILJA, GEYSA, GILLING, GJALP, GREIP, GLAM, GLAUM, GLAUMAR, GLUMRA, GNEIP, GNEIPA, GNISSA, GRID, GRIMLING, GRIMNIR, GROTTINTANNA, GRYLA, GUNNLOD, GUSIR, GYLLIR, GYMIR, HAERA, HAFLI, HALA, HARDGREIP, HARDVERK, HASTIGI, HATI, HEFRING, HEIDREK, HEL, HELBLINDI, HELREGINN, HENGIKEPTA, HENGJANKJAPTA, HERKIR, HERKJA, HIMINGLAEVA, GLOI, HOLGABRUD, HORN, HRAESVELGR, HRAUDNIR, HRAUDUNG, HRIMGERD, HRIMGRIMNIR, HRIMNIR, HRIMTHURS, HRINGVOLNIR, HRIPSTOD, HROAR, HRODR, HRODVITNIR, HROKKVIR, HRONN, HROSSTHJOF, HRUGA, HRUNGNIR, HRYGGDA, HRYM, HUNDALF, HVAL, HVEDRA, HVEDRUNG, HYMIR, HYNDLA, HYROKKIN, IDI, IM, IMA, IMD, IMGERD, IVIDJA, JARNGLUMRA, JARNSAXA, JARNVIDJA, JORMUNGAND, KALDGRANI, KARI, KEILA, KJALLANDI, KOLGA, KOTT, KRAKA, KYRMIR, LAUFEY, LEIFI, LEIKN, LEIRVOR, LITR, LJOTA, LODINFINGRA, LOGI, LOKI, LOPTR, LUT, MANAGARM, MARGERD, MIDJUNG, MIMIR, MODGUD, MOGTHRASIR, MOKKURKALFI, MORN, MUNNHARPA, MYRKRIDA, NAGLFARI, NAL, NARFI, NARI, NATI, NORFI, NOTT, OFLUGBARDA, OFLUGBARDI, OFOTI, OGLADNIR, OLVALDI, ONDUD, OSGRUI, OSKRUD, RANGBEIN, RIFINGAFLA, RINDR, RUNGNIR, RYG, SAEKARLSMULI, SALFANG, SAMENDIL, SIGYN, SIMUL, SIVOR, SKADI, SKAERIR, SKALLI, SKERKIR, SKOLL, SKRATI, SKRIKJA, SKRYMIR, SOKKMIMIR, SOM, SPRETTING, STARKAD, STIGANDI, STORVERK, STUMI, SURTR, SUTTUNG, SVARANG, SVART, SVASUD, SVEIPINFALDA, SVIVOR, THISTILBARDI, THJAZI, THORN, THRASIR, THRIGEITIR, THRIVALDI, THRUDGELMIR, THRYM, THURBORD, ULFRUN, UNN, UTGARDA_LOKI, VAFTHRUDNIR, VAGNHOFDI, VALI, VANARGAND, VANDIL, VARDRUN, VASAD, VER, VIDBLINDI, VIDDI, VIDGYMNIR, VIGGLOD, VIND, VINDLONI, VINDSVAL, VINGNIR, VINGRIP, VIPAR, VORNIR, YMIR, YMSI,
		// Enochian Angels 1500s
		AAAN, AADT, AAETPIO, AANAA, AAODT, AAOXAIF, AAVAN, AAVNA, AAX, ABAMO, ABAOZ, ABMO, ABOZ, ACAR, ACCA, ACMBICU, ACPS, ACRAR, ACUCA, ACUPS, ACZINOR, ADAEOET, ADI, ADIRE, ADNOP, ADOPT, ADOPA, ADOTA, ADRE, ADTA, AGB, AGLM, AGMLM, AHAOZPI, AIAOAI_OIIIT, AIGRA, AIRA, AMOX, AMSOX, ANCRO, ANIMOTIX, ANPIEL, ANVAA, APA, APAHR, APDOCE, APHR, APLST, APM, APST, ARIZL, ARN, ARZL, ARYLIC, ATAAD, ATDIM, ATO, AVTOTAR, BARADIEL, BARNABAS, BATAIVA, BIVHD, BRAP, BRIAP, BRCN, CADAAMP, CMS, CNABR, CNBR, CPSA, CPUSA, DIARI, DIMT, DIMNT, DIOM, DIRI, DIXOM, DMX, DMITRI, DOLOP, DONPA, DOOP, DOPA, DTAA, DTOAA, DXAGZ, DXGZ, EAC, EBOZA, ECAOP, ECANUS, ECOP, EDLPRNAA, EPHRA, ERG, ERN, ERUBEY, ERZLA, EUTPA, EXARP, EXR, FAAX, FMND, GALGALLIEL, GBAL, GBEAL, GLMA, GLMMA, GMDNM, HABIORO, HBR, HCNBR, HCOMA, HIPOTGA, HRAAP, HRAP, HROAN, HRU, HTMORDA, HUA, HXGZD, ICZHIHA, IMNTD, IMTD, IZAZ, IZINR, IZIXP, IZNR, IZRAZ, IZXP, KOKABIEL, LAIRZ, LANG, LAOAXRP, LARZ, LAVAVOTH, LEAOC, LEVANAEL, LIGDISA, LMAG, LMMAG, LSRAPHM, MASGM, MIZ, MMA, MSAL, MSMAL, MTDI, MTNDI, MTO, NAAA, NANTA, NAOO, NAOOO, NAVAA, NBARC, NBRC, NDAZN, NDZN, NHDD, NHODD, NLIRX, NLRX, NPAT, NRCOA, NROA, OACNR, OANR, OAP, OBGOTA_AABACO, OCNM, OMAGG, OMGG, OMIA, OMSIA, ONA, ONH, ONP, OODPZ, OOPZ, OPAD, OPAMN, OPAMA, OPMN, OPNA, OPNAD, ORMN, ORO_IBAH_AOZPI, ORPMN, OTOI, OTROI, OYAUB, OYUB, OZAAB, OZAB, PACO, PADO, PAEOC, PAICO, PIZ, PMAGL, PMOX, PMZOX, PPSAC, RAAGIOSL, RAPH, RBNH, RBZNH, RCANB, RCNB, RDA, RGAN, RGOAN, RLEMU, RLMU, RPA, RRB, RRL, RSI, RSONI, RUOI, RUROI, RXAO, RXINL, RXNL, RXP, RXPAO, RZILA, RZLA, RZIONR_NRZFM, SAIINOU, SAIINOV, SAAIZ, SAUCP, SACP, SCMIO, SIOSP, SISP, SHONDA, SLGAIOL, SONIZNT, TDIM, TDNIM, TPAU, TPLAU, USPSN, USSN, UTIPA, UTPA, UVB, VAASA, VASA, VOLXDO_SIODA, XAI, XCZ, XDZ, XGAZD, XGZD, XII, XNILR, XOM, XOY, XPA, XPAXN, XPCN, XRINH, XRNH, XXAN, YASEN, ZAABO, ZABO, ZARNAAH, ZARZI, ZARZILG, ZAZI, ZDAXG, ZDXG, ZEDEKIEL, ZINGGEN, ZIRACAH, ZIRZ, ZIZA, ZURCHOL

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
			string = String.format("%-20s %s", entity, domains.toString());

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
	 * CANAANITE - ELOHIM
	 */
	private static final Entity[] ELOHIM = { Entity.ANAT, Entity.ASHERAH, Entity.ASTARTE, Entity.ATHIRAT, Entity.ATTAR,
			Entity.BETH_HORON, Entity.DAGON, Entity.ELYON, Entity.ERETZ, Entity.ESHMUN, Entity.HADAD, Entity.HAMMON,
			Entity.HERMON, Entity.ISHAT, Entity.KHIKHIBI, Entity.KOTHAR_WA_KHASIS, Entity.KOTHARAT, Entity.LOTAN,
			Entity.MARQOD, Entity.MELQART, Entity.MISOR, Entity.MOLOCH, Entity.MOT, Entity.NIKKAL_WA_IB,
			Entity.QADESHTU, Entity.RESHEPH, Entity.SHAHAR, Entity.SHALIM, Entity.SHAMAYIM, Entity.SHAPASH,
			Entity.SHEOL, Entity.SYDYK, Entity.TAAUTUS, Entity.TANIT, Entity.YAM, Entity.YARIKH };

	/*
	 * HELLENIC - GREEK/OLYMPIAN
	 */
	private static final Entity[] OLYMPIANS = { Entity.APHRODITE, Entity.APOLLO, Entity.ARES, Entity.ARTEMIS,
			Entity.ATHENA, Entity.DEMETER, Entity.DIONYSUS, Entity.HEPHAESTUS, Entity.HERA, Entity.HERMES,
			Entity.HESTIA, Entity.ZEUS };
	private static final Entity[] TITANS = { Entity.ARKE, Entity.ASTERIA, Entity.ASTRAEUS, Entity.ASTRAIOS,
			Entity.ATLAS, Entity.AURA, Entity.CLYMENE, Entity.COEUS, Entity.CRIUS, Entity.CRONUS, Entity.DIONE,
			Entity.EOS, Entity.EPIMETHEUS, Entity.EURYBIA, Entity.HELIOS, Entity.HYPERION, Entity.IAPETUS,
			Entity.LELANTOS, Entity.LETO, Entity.MENOETIUS, Entity.METIS, Entity.MNEMOSYNE, Entity.OCEANUS,
			Entity.PALLAS, Entity.PERSES, Entity.PHOEBE, Entity.PROMETHEUS, Entity.RHEA, Entity.SELENE, Entity.TETHYS,
			Entity.THEIA, Entity.THEMIS };
	private static final Entity[] CYCLOPES = { Entity.AGRIUS, Entity.ALCYONEUS, Entity.ALOADAE, Entity.ALPOS,
			Entity.ANAX, Entity.ANTAEUS, Entity.ANTIPHATES, Entity.ARGES, Entity.ARGOS_PANOPTES, Entity.ARISTAEUS,
			Entity.AZEUS, Entity.BRIAREUS, Entity.BRONTES, Entity.CACUS, Entity.CHRYSAOR, Entity.COTTUS,
			Entity.CYMOPOLEIA, Entity.DAMASEN, Entity.DAMYSUS, Entity.ECHIDNADES, Entity.ELATREUS, Entity.ENCELADUS,
			Entity.EPHIALTES, Entity.EURYALUS, Entity.EURYMEDON, Entity.GERYON, Entity.GYES, Entity.HALIMEDES,
			Entity.HOPLODAMUS, Entity.HYLLUS, Entity.LEON, Entity.MYLINUS, Entity.OEOLYCA, Entity.OREUS, Entity.ORION,
			Entity.OTUS, Entity.PERIBOEA, Entity.POLYBOTES, Entity.POLYPHEMUS, Entity.PORPHYRION, Entity.STEROPES,
			Entity.SYCEUS, Entity.TITYUS, Entity.TRACHIUS };

	/*
	 * JUDAISM
	 */
	private static final Entity[] JEWISH_ANGELS = { Entity.CAMAEL, Entity.CASSIEL, Entity.GABRIEL, Entity.MICHAEL,
			Entity.OPHANIEL, Entity.ABADDON, Entity.ADRIEL, Entity.ANAEL, Entity.HANIEL, Entity.ARARIEL, Entity.ARIEL,
			Entity.AZAZEL, Entity.BARAQIEL, Entity.CHERUBIEL, Entity.DANIEL, Entity.DUMAH, Entity.EREMIEL,
			Entity.GADREEL, Entity.HADRANIEL, Entity.HAHASIAH, Entity.HASMAL, Entity.HESEDIEL, Entity.ZADKIEL,
			Entity.HOFNIEL, Entity.IMAMIAH, Entity.JEHOEL, Entity.JEQUN, Entity.JERAHMEEL, Entity.JOPHIEL,
			Entity.KEPHAREL, Entity.KERUBIEL, Entity.KUSHIEL, Entity.LELIEL, Entity.HELEL, Entity.MEBAHIAH,
			Entity.METATRON, Entity.MIKAIL, Entity.NANAEL, Entity.NETZACH, Entity.NITHAEL, Entity.NURIEL,
			Entity.PENEMUE, Entity.PHANUEL, Entity.POYEL, Entity.PRAVUIL, Entity.PURIEL, Entity.QAPHSIEL,
			Entity.RADUERIEL, Entity.RAGUEL, Entity.AZRAEL, Entity.RAPHAEL, Entity.RAZIEL, Entity.REMIEL,
			Entity.RIKBIEL, Entity.SACHIEL, Entity.SAHAQUIEL, Entity.SABRIEL, Entity.SAMAEL, Entity.SANDALPHON,
			Entity.SARIEL, Entity.SCHEMHAMPHARAE, Entity.SERAPHIEL, Entity.SHAMSIEL, Entity.SIDRIEL, Entity.TEMELUCHUS,
			Entity.URIEL, Entity.UZIEL, Entity.VASIARIAH, Entity.VEHUEL, Entity.ZAPHKIEL, Entity.ZEPHANIEL,
			Entity.ZEPHON, Entity.ZOPHIEL };
	private static final Entity[] JEWISH_DEMONS = { Entity.ABEZETHIBOU, Entity.ABYZOU, Entity.AGALIAREPT,
			Entity.AGRAT_BAT_MAHLAT, Entity.AGRIEL, Entity.ARMAROS, Entity.ASB_EL, Entity.BALBERITH, Entity.BEELZEBUB,
			Entity.BEHEMOTH, Entity.BELIAL, Entity.DANJAL, Entity.EISHETH, Entity.GADER_EL, Entity.GRIGORI,
			Entity.KASADYA, Entity.LEVIATHAN, Entity.LILIN, Entity.MASTEMA, Entity.NAAMAH, Entity.ONOSKELIS,
			Entity.PENEMUE, Entity.RAHAB, Entity.SAMAEL, Entity.SEMYAZA, Entity.SHEDIM, Entity.TANNIN, Entity.YEQON };

	/*
	 * CHRISTIANITY
	 */
	private static final Entity[] CHRISTIAN_ANGELS = { Entity.BARACHIEL, Entity.JEGUDIEL, Entity.LUCIFER, Entity.MURIEL,
			Entity.PAHALIAH, Entity.SARATHIEL, Entity.SELAPHIEL, Entity.WORMWOOD, Entity.ZACHARIEL };
	private static final Entity[] CHRISTIAN_DEMONS = { Entity.AGARES, Entity.HABORYM, Entity.ALASTOR, Entity.AMAYMON,
			Entity.AMDUSIAS, Entity.ANCITIF, Entity.ANDREALPHUS, Entity.ANDROMALIUS, Entity.ASTAROTH, Entity.BAAL,
			Entity.BALAM, Entity.BAPHOMET, Entity.BARBAS, Entity.BARBATOS, Entity.BATHIN, Entity.BELETH,
			Entity.BELPHEGOR, Entity.BEHERIT, Entity.BIFRONS, Entity.BOTIS, Entity.BUER, Entity.BUNE, Entity.CAIM,
			Entity.CIMEJES, Entity.CORSON, Entity.CROCELL, Entity.KIMARIS, Entity.PROCELL, Entity.DANTALION,
			Entity.DECARABIA, Entity.DEMOGORGON, Entity.ELIGOS, Entity.FOCALOR, Entity.FORCAS, Entity.FORNEUS,
			Entity.FURFUR, Entity.GAAP, Entity.GAMIGIN, Entity.GLASYA_LABOLAS, Entity.GREMORY, Entity.GUSION,
			Entity.HAAGENTI, Entity.HALPHAS, Entity.MALTHUS, Entity.HAURES, Entity.FLAVROS, Entity.INCUBUS, Entity.IPOS,
			Entity.KRAMPUS, Entity.LEGION, Entity.LEONARD, Entity.LERAJE, Entity.LUCIFUGE_ROFOCALE, Entity.MAMMON,
			Entity.MARAX, Entity.MARCHOSIAS, Entity.MEPHISTOPHELES, Entity.MERIHEM, Entity.MURMUR, Entity.NABERIUS,
			Entity.ORCUS, Entity.ORIAX, Entity.OROBAS, Entity.OSE, Entity.PAIMON, Entity.PHENEX, Entity.PITHIUS,
			Entity.PRUFLAS, Entity.RAUM, Entity.RONOVE, Entity.SABNOCK, Entity.SALEOS, Entity.SEIR, Entity.SHAX,
			Entity.SITRI, Entity.STOLAS, Entity.SUCCUBUS, Entity.SURGAT, Entity.UKOBACH, Entity.VALAC, Entity.VALEFAR,
			Entity.VAPULA, Entity.VASSAGO, Entity.VEPAR, Entity.VINE, Entity.ZAGAN, Entity.ZEPAR, Entity.ZIMINIAR };

	/*
	 * ISLAM
	 */
	private static final Entity[] ISLAMIC_ANGELS = { Entity.ARTIYA_IL, Entity.DARDA_IL, Entity.HARUT,
			Entity.HAMALAT_AL_ARSH, Entity.ISRAFIL, Entity.KIRAMAN_KATIBIN, Entity.MAALIK, Entity.MARUT, Entity.MUNKAR,
			Entity.NAKIR };
	private static final Entity[] ISLAMIC_DEMONS = { Entity.DAJJAL, Entity.EBLIS };

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

	/*
	 * INITIALIZATION
	 * 
	 */
	static {
		greaterImmortals = new HashMap<Entity, Prototype>();
		lesserImmortals = new HashMap<Entity, Prototype>();
		Entity[] array = null;

		/*
		 * DAWN WAR GODS
		 * 
		 */
		lesserImmortals.put(Entity.ASMODEUS, new Prototype(Entity.ASMODEUS, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.AVANDRA, new Prototype(Entity.AVANDRA, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.BAHAMUT, new Prototype(Entity.BAHAMUT, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.GOOD }, new Domain[] { Domain.LIFE, Domain.WAR }));
		lesserImmortals.put(Entity.BANE, new Prototype(Entity.BANE, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.WAR }));
		lesserImmortals.put(Entity.CORELLON, new Prototype(Entity.CORELLON, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.ERATHIS, new Prototype(Entity.ERATHIS, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.NEUTRAL }, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.GRUUMSH, new Prototype(Entity.GRUUMSH, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TEMPEST, Domain.WAR }));
		lesserImmortals.put(Entity.IOUN, new Prototype(Entity.IOUN, Lesser.DEMIURGE,
				new Alignment[] { Alignment.NEUTRAL }, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.KORD, new Prototype(Entity.KORD, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.NEUTRAL }, new Domain[] { Domain.TEMPEST }));
		lesserImmortals.put(Entity.LOLTH, new Prototype(Entity.LOLTH, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.MELORA, new Prototype(Entity.MELORA, Lesser.DEMIURGE,
				new Alignment[] { Alignment.NEUTRAL }, new Domain[] { Domain.NATURE, Domain.TEMPEST }));
		lesserImmortals.put(Entity.MORADIN, new Prototype(Entity.MORADIN, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.GOOD }, new Domain[] { Domain.KNOWLEDGE, Domain.WAR }));
		lesserImmortals.put(Entity.PELOR, new Prototype(Entity.PELOR, Lesser.DEMIURGE,
				new Alignment[] { Alignment.NEUTRAL, Alignment.GOOD }, new Domain[] { Domain.LIFE, Domain.LIGHT }));
		lesserImmortals.put(Entity.RAVEN_QUEEN, new Prototype(Entity.RAVEN_QUEEN, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.NEUTRAL }, new Domain[] { Domain.DEATH, Domain.LIFE }));
		lesserImmortals.put(Entity.SEHANINE, new Prototype(Entity.SEHANINE, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.GOOD }, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.THARIZDUN, new Prototype(Entity.THARIZDUN, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.TIAMAT, new Prototype(Entity.TIAMAT, Lesser.DEMIURGE,
				new Alignment[] { Alignment.LAWFUL, Alignment.EVIL }, new Domain[] { Domain.TRICKERY, Domain.WAR }));
		lesserImmortals.put(Entity.TOROG, new Prototype(Entity.TOROG, Lesser.DEMIURGE,
				new Alignment[] { Alignment.NEUTRAL, Alignment.EVIL }, new Domain[] { Domain.DEATH }));
		lesserImmortals.put(Entity.VECNA,
				new Prototype(Entity.VECNA, Lesser.DEMIURGE, new Alignment[] { Alignment.NEUTRAL, Alignment.EVIL },
						new Domain[] { Domain.DEATH, Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.ZEHIR, new Prototype(Entity.ZEHIR, Lesser.DEMIURGE,
				new Alignment[] { Alignment.CHAOTIC, Alignment.EVIL }, new Domain[] { Domain.DEATH, Domain.TRICKERY }));

		/*
		 * ADD LESSER BEINGS
		 */

		/*
		 * CANAANITE - ELOHIM
		 */
		lesserImmortals.put(Entity.ANAT,
				lesserBeing(Entity.ANAT, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.WAR }));
		lesserImmortals.put(Entity.ASHERAH,
				lesserBeing(Entity.ASHERAH, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.ASTARTE, lesserBeing(Entity.ASTARTE, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIFE, Domain.WAR }));
		lesserImmortals.put(Entity.ATHIRAT,
				lesserBeing(Entity.ATHIRAT, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.ATTAR, lesserBeing(Entity.ATTAR, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIGHT, Domain.TRICKERY }));
		lesserImmortals.put(Entity.BETH_HORON,
				lesserBeing(Entity.BETH_HORON, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.DEATH }));
		lesserImmortals.put(Entity.DAGON, lesserBeing(Entity.DAGON, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIFE, Domain.NATURE }));
		lesserImmortals.put(Entity.ELYON, lesserBeing(Entity.ELYON, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIGHT, Domain.NATURE }));
		lesserImmortals.put(Entity.ERETZ, lesserBeing(Entity.ERETZ, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIFE, Domain.NATURE }));
		lesserImmortals.put(Entity.ESHMUN, lesserBeing(Entity.ESHMUN, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.KNOWLEDGE, Domain.LIFE }));
		lesserImmortals.put(Entity.HADAD,
				lesserBeing(Entity.HADAD, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.TEMPEST }));
		lesserImmortals.put(Entity.HAMMON, lesserBeing(Entity.HAMMON, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIFE, Domain.NATURE }));
		lesserImmortals.put(Entity.HERMON,
				lesserBeing(Entity.HERMON, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.ISHAT,
				lesserBeing(Entity.ISHAT, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.NATURE }));
		lesserImmortals.put(Entity.KHIKHIBI,
				lesserBeing(Entity.KHIKHIBI, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.KOTHAR_WA_KHASIS, lesserBeing(Entity.KOTHAR_WA_KHASIS, Lesser.ELOHIM,
				Alignment.NEUTRAL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.KOTHARAT, lesserBeing(Entity.KOTHARAT, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.NATURE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.LOTAN,
				lesserBeing(Entity.LOTAN, Lesser.DRAGON, Alignment.NEUTRAL, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.MARQOD,
				lesserBeing(Entity.MARQOD, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.MELQART, lesserBeing(Entity.MELQART, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.KNOWLEDGE, Domain.LIGHT }));
		lesserImmortals.put(Entity.MISOR,
				lesserBeing(Entity.MISOR, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.MOLOCH,
				lesserBeing(Entity.MOLOCH, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.MOT, lesserBeing(Entity.MOT, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.DEATH, Domain.TRICKERY }));
		lesserImmortals.put(Entity.NIKKAL_WA_IB,
				lesserBeing(Entity.NIKKAL_WA_IB, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.QADESHTU,
				lesserBeing(Entity.QADESHTU, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.RESHEPH, lesserBeing(Entity.RESHEPH, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.DEATH, Domain.NATURE }));
		lesserImmortals.put(Entity.SHAHAR,
				lesserBeing(Entity.SHAHAR, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.SHALIM, lesserBeing(Entity.SHALIM, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.DEATH, Domain.TRICKERY }));
		lesserImmortals.put(Entity.SHAMAYIM, lesserBeing(Entity.SHAMAYIM, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.LIGHT, Domain.NATURE }));
		lesserImmortals.put(Entity.SHAPASH,
				lesserBeing(Entity.SHAPASH, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.SHEOL, lesserBeing(Entity.SHEOL, Lesser.ELOHIM, Alignment.NEUTRAL,
				new Domain[] { Domain.DEATH, Domain.NATURE }));
		lesserImmortals.put(Entity.SYDYK,
				lesserBeing(Entity.SYDYK, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.TAAUTUS,
				lesserBeing(Entity.TAAUTUS, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.TANIT,
				lesserBeing(Entity.TANIT, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.LIFE, Domain.WAR }));
		lesserImmortals.put(Entity.YAM, lesserBeing(Entity.YAM, Lesser.DRAGON, Alignment.NEUTRAL,
				new Domain[] { Domain.TEMPEST, Domain.TRICKERY }));
		lesserImmortals.put(Entity.YARIKH,
				lesserBeing(Entity.YARIKH, Lesser.ELOHIM, Alignment.NEUTRAL, new Domain[] { Domain.TRICKERY }));

		/*
		 * INITIALIZE COSMIC ENTITIES
		 * 
		 */

		// JEWISH ANGELS
		array = JEWISH_ANGELS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.ANGEL, Alignment.LAWFUL));
		}
		array = JEWISH_DEMONS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.DEMON, Alignment.CHAOTIC));
		}

		// HELLENIC - GREEK
		lesserImmortals.put(Entity.APHRODITE, lesserBeing(Entity.APHRODITE, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.TRICKERY, Domain.WAR }));
		lesserImmortals.put(Entity.APOLLO, lesserBeing(Entity.APOLLO, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.LIGHT }));
		lesserImmortals.put(Entity.ARES,
				lesserBeing(Entity.ARES, Lesser.OLYMPIAN, Alignment.LAWFUL, new Domain[] { Domain.DEATH, Domain.WAR }));
		lesserImmortals.put(Entity.ARTEMIS, lesserBeing(Entity.ARTEMIS, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.NATURE, Domain.WAR }));
		lesserImmortals.put(Entity.ATHENA, lesserBeing(Entity.ATHENA, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.WAR }));
		lesserImmortals.put(Entity.DEMETER, lesserBeing(Entity.DEMETER, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.LIFE, Domain.NATURE }));
		lesserImmortals.put(Entity.DIONYSUS, lesserBeing(Entity.DIONYSUS, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.NATURE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.HEPHAESTUS, lesserBeing(Entity.HEPHAESTUS, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.NATURE }));
		lesserImmortals.put(Entity.HERA, lesserBeing(Entity.HERA, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.LIFE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.HERMES, lesserBeing(Entity.HERMES, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.HESTIA, lesserBeing(Entity.HESTIA, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.LIFE, Domain.LIGHT }));
		lesserImmortals.put(Entity.ZEUS, lesserBeing(Entity.ZEUS, Lesser.OLYMPIAN, Alignment.LAWFUL,
				new Domain[] { Domain.LIGHT, Domain.TEMPEST }));

		/*
		 * HELLENIC TITANS
		 */
		lesserImmortals.put(Entity.ARKE, lesserBeing(Entity.ARKE, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.ASTERIA, lesserBeing(Entity.ASTERIA, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.ASTRAEUS, lesserBeing(Entity.ASTRAEUS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.ASTRAIOS, lesserBeing(Entity.ASTRAIOS, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.ATLAS, lesserBeing(Entity.ATLAS, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.AURA,
				lesserBeing(Entity.AURA, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.CLYMENE, lesserBeing(Entity.CLYMENE, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.COEUS,
				lesserBeing(Entity.COEUS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.CRIUS,
				lesserBeing(Entity.CRIUS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.CRONUS, lesserBeing(Entity.CRONUS, Lesser.TITAN, Alignment.NEUTRAL));
		lesserImmortals.put(Entity.DIONE,
				lesserBeing(Entity.DIONE, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.EOS,
				lesserBeing(Entity.EOS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.EPIMETHEUS, lesserBeing(Entity.EPIMETHEUS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.EURYBIA,
				lesserBeing(Entity.EURYBIA, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.TEMPEST }));
		lesserImmortals.put(Entity.HELIOS,
				lesserBeing(Entity.HELIOS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.HYPERION,
				lesserBeing(Entity.HYPERION, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.IAPETUS,
				lesserBeing(Entity.IAPETUS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.DEATH }));
		lesserImmortals.put(Entity.LELANTOS,
				lesserBeing(Entity.LELANTOS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.LETO,
				lesserBeing(Entity.LETO, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIFE }));
		lesserImmortals.put(Entity.MENOETIUS, lesserBeing(Entity.MENOETIUS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.DEATH, Domain.WAR }));
		lesserImmortals.put(Entity.METIS,
				lesserBeing(Entity.METIS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.MNEMOSYNE,
				lesserBeing(Entity.MNEMOSYNE, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.OCEANUS,
				lesserBeing(Entity.OCEANUS, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.TEMPEST }));
		lesserImmortals.put(Entity.PALLAS, lesserBeing(Entity.PALLAS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.WAR }));
		lesserImmortals.put(Entity.PERSES,
				lesserBeing(Entity.PERSES, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.WAR }));
		lesserImmortals.put(Entity.PHOEBE,
				lesserBeing(Entity.PHOEBE, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.KNOWLEDGE }));
		lesserImmortals.put(Entity.PROMETHEUS, lesserBeing(Entity.PROMETHEUS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.KNOWLEDGE, Domain.TRICKERY }));
		lesserImmortals.put(Entity.RHEA,
				lesserBeing(Entity.RHEA, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIFE, Domain.NATURE }));
		lesserImmortals.put(Entity.SELENE,
				lesserBeing(Entity.SELENE, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.TRICKERY }));
		lesserImmortals.put(Entity.TETHYS, lesserBeing(Entity.TETHYS, Lesser.TITAN, Alignment.LAWFUL,
				new Domain[] { Domain.NATURE, Domain.TEMPEST }));
		lesserImmortals.put(Entity.THEIA,
				lesserBeing(Entity.THEIA, Lesser.TITAN, Alignment.LAWFUL, new Domain[] { Domain.LIGHT }));
		lesserImmortals.put(Entity.THEMIS, lesserBeing(Entity.THEMIS, Lesser.TITAN, Alignment.NEUTRAL));

		// TALOS is a bronze giant built by Hephaestus to guard Crete
		lesserImmortals.put(Entity.TALOS, lesserBeing(Entity.TALOS, Lesser.ABOMINATION, Alignment.NEUTRAL));

		array = CYCLOPES;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.CYCLOPS, Alignment.NEUTRAL));
		}

		// CHRISTIAN
		array = CHRISTIAN_ANGELS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.ANGEL, Alignment.GOOD));
		}
		array = CHRISTIAN_DEMONS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.DEMON, Alignment.EVIL));
		}

		// ISLAMIC ANGELS
		array = ISLAMIC_ANGELS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.ANGEL, Alignment.GOOD));
		}
		array = ISLAMIC_DEMONS;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.DEMON, Alignment.EVIL));
		}

		// JOTNAR
		array = JOTNAR;
		for (int i = 0; i < array.length; ++i) {
			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.JOTUNN, Alignment.CHAOTIC));
		}

		// ENOCHIAN ANGELS
		array = ENOCHIAN_ANGELS;
//		for (int i = 0; i < array.length; ++i) {
//			lesserImmortals.put(array[i], lesserBeing(array[i], Lesser.ANGEL, Alignment.GOOD));
//		}
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

		for (Iterator<Entity> it = lesserImmortals.keySet().iterator(); it.hasNext();) {
			candidate = it.next();
			prototype = lesserImmortals.get(candidate);
			if (prototype.alignment.contains(alignment))
				set.add(candidate);
		}

		return set;
	}

	public static Collection<Prototype> getLesserBeings() {
		return lesserImmortals.values();
	}

	public static String lesserBeingsAlignmentToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0 };
		Prototype prototype;
		for (Iterator<Prototype> it = lesserImmortals.values().iterator(); it.hasNext();) {
			prototype = it.next();

			if (prototype.alignment.contains(Alignment.LAWFUL))
				++array[0];

			if (prototype.alignment.contains(Alignment.GOOD))
				++array[1];

			if (prototype.alignment.contains(Alignment.NEUTRAL))
				++array[2];

			if (prototype.alignment.contains(Alignment.EVIL))
				++array[3];

			if (prototype.alignment.contains(Alignment.CHAOTIC))
				++array[4];
		}

		String string = String.format("  Lawful: %d %n    Good: %d %n Neutral: %d %n    Evil: %d %n Chaotic: %d ",
				array[0], array[1], array[2], array[3], array[4]);
		return string;
	}

	public static String alignmentGridStatsToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		double total = lesserImmortals.size();

		Prototype prototype;
		for (Iterator<Prototype> it = lesserImmortals.values().iterator(); it.hasNext();) {
			prototype = it.next();

			if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.GOOD))
				++array[0];
			else if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.NEUTRAL))
				++array[1];
			else if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.EVIL))
				++array[2];
			else if (prototype.alignment.contains(Alignment.NEUTRAL) && prototype.alignment.contains(Alignment.GOOD))
				++array[3];
			else if (prototype.alignment.contains(Alignment.NEUTRAL) && prototype.alignment.contains(Alignment.EVIL))
				++array[5];
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.GOOD))
				++array[6];
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.NEUTRAL))
				++array[7];
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.EVIL))
				++array[8];
			else if (prototype.alignment.contains(Alignment.LAWFUL) != true
					&& prototype.alignment.contains(Alignment.CHAOTIC) != true
					&& prototype.alignment.contains(Alignment.GOOD) != true
					&& prototype.alignment.contains(Alignment.EVIL) != true)
				++array[4];
		}

		String[] ali = new String[] { "LG", "LN", "LE", "NG", "TN", "NE", "CG", "CN", "CE" };
		String string = "";
		for (int i = 0; i < array.length; ++i) {
			string += String.format("%s: %5d (%5.1f %%)", ali[i], array[i], array[i] / total * 100);
			if (i == 0 || i == 1 || i == 3 || i == 4 || i == 6 || i == 7)
				string += " || ";

			if (i == 2 || i == 5 || i == 8)
				string += "\n";
		}

		return string;
	}

	public static String beingsByGridToString() {
		List<Entity>[] array = (List<Entity>[]) new ArrayList[9];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Entity>();
		}

		Prototype prototype;
		for (Iterator<Prototype> it = lesserImmortals.values().iterator(); it.hasNext();) {
			prototype = it.next();

			if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.GOOD))
				array[0].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.NEUTRAL))
				array[1].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.LAWFUL) && prototype.alignment.contains(Alignment.EVIL))
				array[2].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.NEUTRAL) && prototype.alignment.contains(Alignment.GOOD))
				array[3].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.NEUTRAL) && prototype.alignment.contains(Alignment.EVIL))
				array[5].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.GOOD))
				array[6].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.NEUTRAL))
				array[7].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.CHAOTIC) && prototype.alignment.contains(Alignment.EVIL))
				array[8].add(prototype.entity);
			else if (prototype.alignment.contains(Alignment.LAWFUL) != true
					&& prototype.alignment.contains(Alignment.CHAOTIC) != true
					&& prototype.alignment.contains(Alignment.GOOD) != true
					&& prototype.alignment.contains(Alignment.EVIL) != true)
				array[4].add(prototype.entity);
		}

		String[] ali = new String[] { "Lawful Good", "Lawful Neutral", "Lawful Evil", "Neutral Good", "True Neutral",
				"Neutral Evil", "Chaotic Good", "Chaotic Neutral", "Chaotic Evil" };
		String string = "";
		for (int i = 0; i < array.length; ++i) {
			string += String.format("%16s (%4d): %s%n", ali[i], array[i].size(), array[i].toString());
		}

		return string;
	}

	public static String lesserBeingsDomainToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		Prototype prototype;
		for (Iterator<Prototype> it = lesserImmortals.values().iterator(); it.hasNext();) {
			prototype = it.next();

			if (prototype.domains.contains(Domain.DEATH))
				++array[0];

			if (prototype.domains.contains(Domain.KNOWLEDGE))
				++array[1];

			if (prototype.domains.contains(Domain.LIFE))
				++array[2];

			if (prototype.domains.contains(Domain.LIGHT))
				++array[3];

			if (prototype.domains.contains(Domain.NATURE))
				++array[4];

			if (prototype.domains.contains(Domain.TEMPEST))
				++array[5];

			if (prototype.domains.contains(Domain.TRICKERY))
				++array[6];

			if (prototype.domains.contains(Domain.WAR))
				++array[7];
		}
		String[] domains = { "Death", "Knowledge", "Life", "Light", "Nature", "Tempest", "Trickery", "War" };
		String string = "";

		int total = lesserImmortals.size();
		for (int i = 0; i < array.length; ++i) {
			string += String.format("%10s: %4d%n", domains[i], array[i]);
		}
		string += String.format("%15s%n", "- - - ");
		string += String.format("%10s: %4d%n", "Total", total);

		// string = String.format("%10s: %4d %n%10s: %4d %n%10s: %4d %n%10s: %4d %n%10s:
		// %4d %n%10s: %4d %n%10s: %4d %n%10s: %4d %n",
		// "Death", array[0], "Knowledge", array[1], "Life", array[2], "Light",
		// array[3], "Nature", array[4], "Tempest", array[5], "Trickery", array[6],
		// "War", array[7]);
		return string;
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

	private static Alignment[] alignmentRandomizer(Alignment axis) {
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
			alignment = (dice < 5) ? axes[0] : (dice < 8) ? axes[1] : axes[2];
		} else if (axis.equals(Alignment.CHAOTIC)) {
			alignment = (dice < 5) ? axes[3] : (dice < 8) ? axes[4] : axes[5];
		} else if (axis.equals(Alignment.GOOD)) {
			alignment = (dice < 5) ? axes[6] : (dice < 8) ? axes[7] : axes[8];
		} else if (axis.equals(Alignment.EVIL)) {
			// System.out.println("Evil entity generated.");
			alignment = (dice < 5) ? axes[9] : (dice < 8) ? axes[10] : axes[11];
		} else if (dice < 5) {
			alignment = new Alignment[] { Alignment.NEUTRAL };
		} else {
			alignment = new Alignment[] { Alignment.NEUTRAL, Dice.randomFromArray(corners) };
		}

		return alignment;
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

	private static Prototype lesserBeing(Entity entity, Lesser lesser, Alignment axis, Domain[] domains) {
		return new Prototype(entity, lesser, alignmentRandomizer(axis), domains);
	}

	private static Prototype lesserBeing(Entity entity, Lesser lesser, Alignment axis) {
		return new Prototype(entity, lesser, alignmentRandomizer(axis), domainRandomizer());
	}
}
