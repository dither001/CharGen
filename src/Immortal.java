import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Immortal {
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
		OLYMPIAN, CHTHONIAN, OCEANIC, TITAN, CYCLOPS, CENTAUR, NYMPH,
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
		ANAT, ASHERAH, ASTARTE, ATHIRAT, ATTAR, BETH_HORON, DAGAN, ELYON, ERETZ, ESHMUN, HADAD, HAMMON, HERMON, ISHAT, KHIKHIBI, KOTHAR_WA_KHASIS, KOTHARAT, LOTAN, MARQOD, MELQART, MISOR, MOLOCH, MOT, NIKKAL_WA_IB, QADESHTU, RESHEPH, SHAHAR, SHALIM, SHAMAYIM, SHAPASH, SHEOL, SYDYK, TAAUTUS, TANIT, YAM, YARIKH,
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
		// GREEK - PRIMORDIALS
		AETHER, AION, AITNA, ANANKE, ATHOS, ATROPOS, CHAOS, CHRONOS, CLOTHO, ECHIDNA, EREBUS, ERIS, EROS, EURYNOME, GAIA, HELIKON, HEMERA, HIMEROS, HYPNOS, KITAIRON, LACHESIS, NEMESIS, NYSOS, NYX, OLYMPUS, OPHION, OREIOS, PARNES, PHANES, PHYSIS, PONTUS, TARTARUS, THALASSA, THANATOS, TMOLUS, TYPHON, URANUS,
		// OLYMPIANS
		APHRODITE, APOLLO, ARES, ARTEMIS, ATHENA, DEMETER, DIONYSUS, HEPHAESTUS, HERA, HERMES, HESTIA, ZEUS,
		// CHTHONIANS
		ACHERON, AIAKOS, AMPHIARAUS, ANGELOS, ASKALAPHOS, CERBERUS, CHARON, GORGYRA, HADES, KEUTHONYMOS, KOKYTOS, LETHE, MACARIA, MELINOE, MENOETES, MINOS, ORPHNE, PERSEPHONE, PHLEGETHON, STYX, THADAMANTHYS, ZAGREUS,
		// OCEANIC
		POSEIDON, AEGAEON, ACHELOUS, AMPHITRITE, BENTHESIKYME, BRIZO, CETO, CHARYBDIS, CYMOPOLEIA, DELPHIN, EIDOTHEA, GLAUCUS, LEUCOTHEA, PSAMATHE, NEREUS, NERITES, PALAEMON, SANGARIUS, THETHYS, THAUMAS, THOOSA, TRITEIA, TRITON, ACIS, ALPHEUS, ASOPUS, CLADEUS, COCYTUS, EUROTAS, NILUS, PENEUS, SCAMANDER,
		// TITANS
		ARKE, ASTERIA, ASTRAEUS, ASTRAIOS, ATLAS, AURA, CLYMENE, COEUS, CRIUS, CRONUS, DIONE, EOS, EPIMETHEUS, EURYBIA, HELIOS, HYPERION, IAPETUS, LELANTOS, LETO, MENOETIUS, METIS, MNEMOSYNE, OCEANUS, PALLAS, PERSES, PROMETHEUS, RHEA, SELENE, TETHYS, THEIA, THEMIS,
		// Talos: Bronze Giant made by Hephaestus
		TALOS,
		// SPIRITS
		ACAMANTIS, ACESO, ACHELOIS, ACRATOPOTES, ACTAEA, ADIANTE, ADITE, ADONIS, ADRASTEA, AEGIUS, AEGLE, AEGYPTUS, AEOLUS, AESA, AGAPTOLEMUS, AGATHOS, AGDISTIS, AGENOR, AIDOS, AIOPIS, AKMON, ALCES, ALCMENOR, ALCYONE, ALECTRONA, ALETHEIA, ALEXIARES, ALKE, AMPHICOMONE, AMPHICTYONIS, AMYMONE, AMYNTOR, ANATOLE, ANAXIBIA, ANDROMACHUS, ANGELEIA, ANICETUS, ANTHEIA, ANTHELIA, ANTHOUSAI, ANTIMACHUS, ANTIOCHUS, ANTIPAPHUS, APARCTIAS, APHAEA, APHELIOTES, APHRODITUS, ARBELUS, ARCADIA, ARCHELAUS, ARETE, ARETHUSA, ARGESTES, ARGIUS, ARIADNE, ARISTONOOS, ARKTOS, ARMOASBUS, ARSALTE, ASCLEPIUS, ASTERIDES, ASTEROPE, ATHALIDES, ATHAMAS, ATHLETES, ATTIS, AUGE, AUTODICE, AUTOMATE, AUTONOE, AUXESIA, AUXO, BIA, BOREAS, BRITOMARTIS, BROMIUS, BRYCE, BUSIRIS, CAERUS, CAICIAS, CALLIDICE, CANTHUS, CARMANOR, CARME, CASSUS, CELAENO, CERAON, CERCETES, CHAETUS, CHALCODON, CHEIMON, CHLORIS, CHOINE, CHRYSIPPE, CHRYSIPPUS, CHRYSOTHEMIS, CHRYSUS, CHTHONIUS, CIRCIOS, CISSEUS, CLEODORA, CLEOPATRA, CLITE, CLITUS, CLYTUS, COMUS, COPREUS, CORYMBUS, CRITOMEDIA, CYAMITES, CYBELE, DAIPHRON, DAMNEUS, DAMONE, DAPLIDICE, DEIKAIOSYNE, DEIPNEUS, DEMARCHUS, DEMODITAS, DEMOPHILE, DESPOINA, DIKE, DINDYMENE, DIOCORYSTES, DIOXIPPE, DIPPODICE, DOLICHUS, DOLOS, DONAKIS, DORIUM, DRYAS, DYSIS, ECNOMINUS, EIAR, EILEITHYIA, EIRESIONE, ELECTRA, ELETE, EOSPHORUS, EPIALES, EPIONE, ERSA, ERYTHEA, EUBULE, EUCHENOR, EUCLEIA, EUDAEMON, EULABEIA, EUMELUS, EUMONIA, EUNOMIA, EUNOSTUS, EUPHEME, EUPORIE, EUPRAXIA, EURONOTUS, EUROPOME, EURUS, EURYBATES, EURYDAMAS, EURYLOCHUS, EURYSTHENES, EUSEBEIA, EUTHENIA, EVIDEA, EVIPPE, GELOS, GLAUCIPPE, GORGE, GORGOPHONE, GYMNASTIKA, HARMONIA, HARPOCRATES, HEBE, HECABE, HECATERUS, HEDONE, HEIMARMENE, HELICTA, HERMUS, HERO, HESPERA, HESPEREIA, HESPERUS, HESPERUSA, HIPPARTE, HIPPODAMIA, HIPPOLYTUS, HIPPOMEDUSA, HIPPOTHOUS, HOMONOIA, HORME, HYGIEIA, HYMENAEUS, HYPERANTUS, HYPERBIUS, HYPERIPPE, HYPERIUS, HYPERMNESTRA, IASO, ICHNAEA, IDAIOS, IDAS, IDMON, ILTONOMUS, IMBRUS, IPHIMEDUSA, IRIS, ISTRUS, ITEA, IYNX, KALOKAGATHIA, KALYPSO, KARPO, KRATOS, KROTOS, KYRBAS, LAMPUS, LEOS, LICHAS, LIPARA, LIPS, LITAE, LIXUS, LYNCEUS, MAIA, MAPSAURA, MATTON, MEDON, MENALCAS, MENEMACHUS, MERMESA, MEROPE, MESEMBRIA, METALCES, MIDANUS, MIDEA, MINEUS, MNESTRA, MONUSTE, MORPHEUS, MOUSIKA, MYRMIDONE, NELISA, NELO, NEPHELE, NIAUIUS, NIKE, NOMOS, NOTUS, NYMPHE, OBRIMUS, OCYPETE, ODIUS, OEME, OENEUS, OKYTHOOS, ORTHOSIE, PAEAN, PALAESTRA, PAMPHILUS, PAN, PANACEA, PANDIA, PANDION, PANTHIUS, PASSALOS, PEITHARCHIA, PEITHO, PENTHUS, PEPROMENE, PERIPHAS, PERISTHENES, PERIUS, PHAENON, PHAETHON, PHANTASOS, PHANTES, PHARTIS, PHERESPONDUS, PHEROUSA, PHILINUS, PHILOMELUS, PHILOPHROSYNE, PHILOTES, PHOBETOR, PIRENE, PISENOR, PISTIS, PLEXIPPUS, PLUTUS, PODARCE, PODASIMUS, POINE, POLEMOS, POLYBE, POLYCTOR, POLYDECTOR, POLYPHONTES, POLYXENA, POROS, POTAMON, POTHOS, PRAXIDIKE, PRIAPUS, PROTHEON, PRYMNEUS, PTHINOPORON, PUGNO, PYLARGE, PYRANTE, PYRANTIS, PYROEIS, PYRRHICHOS, SABAZIOS, SCAEA, SCYLLA, SKEIRON, SOPHROSYNE, SOTER, SOTERIA, SPONDE, STEROPE, STHENELE, STHENELUS, STILBON, STYGNE, TALTHYBIUS, TARA, TAYGETE, TECHNE, TELESPHORUS, TELETE, THALLO, THEANO, THEMISTAGORA, THEROS, THOOTES, THRASOS, TRIPTOLEMUS, TRITE, TYCHE, XANTHUS, ZELOS, ZEPHYRUS,
		// NYMPHS
		ABARBAREA, ACASTE, ACRETE, ADMETE, ADRASTEIA, ADRASTIA, AETHRA, AETNA, AGANIPPE, AGAUE, AGLAEA, AGLAIA, ALCINOE, AMALTHEA, AMATHEIA, AMBROSIA, AMPHINOME, AMPHIRHO, AMPHITHOE, ANCHIALE, ANCHIROE, ANNA_PERENNA, ANTHEDON, ANTHRACIA, AOIDE, APOLLONIS, APSEUDES, ARCHE, ARGIA, ARGIOPE, ARGYRA, ARNE, ASIA, ASTACIA, ASTERODIA, ATLANTIA, BATIA, BEROE, BORYSTHENIS, BRYUSA, BYBLIS, CALIADNE, CALLEIS, CALLIANASSA, CALLIANEIRA, CALLICHORE, CALLIOPE, CALLIPHAEIA, CALLIRHOE, CALYBE, CALYCE, CALYPSO, CAMARINA, CAMENTIS, CAPHEIRA, CARTHAGO, CASSOTIS, CEPHISSO, CERCEIS, CHARICLO, CHRLORIS, CHRYSE, CHRYSEIS, CHRYSOPELIA, CLEOCHARIA, CLETA, CLIO, CLITEMNESTE, CLONIA, CLYTIE, CNOSSIA, CORONIS, CORYCIA, CORYPHE, CRANAE, CRANTO, CRENEIS, CREUSA, CROCALE, CYANE, CYDIPPE, CYLLENE, CYMATOLEGE, CYMO, CYMODOCE, CYMOTHOE, CYNOSURA, CYRENE, DAEIRA, DANAIS, DAPHNIS, DAULIS, DEIOPEA, DEIOPOE, DERCETIS, DERO, DEXAMENE, DIOPATRA, DODONE, DORIS, DOTO, DROSERA, DRYMO, DRYOPE, DYNAMENE, ECHENAIS, ECHO, EGERIA, EIDYIA, EIONE, EPHYRA, EPIMELIA, ERATO, EREUTHO, ETHEMEA, EUAGORE, EUAGOREIS, EUARNE, EUCRANTE, EUDAIMONIA, EUDORA, EUDORE, EULIMENE, EUMOLPE, EUNICE, EUPETALE, EUPHROSYNE, EUPOMPE, EUROPA, EURYDICE, EURYTE, EUTERPE, EUTHYMIA, GALATEA, GALAXAURA, GALENE, GLAUCE, GLAUCONOME, HAGNO, HALIE, HALIMEDE, HECAERGE, HEGEMONE, HEGETORIA, HELICE, HESIONE, HESTYAEA, HIEROMNEME, HIMALIA, HIPPO, HIPPONOE, HORA, HYADES, HYALE, IACHE, IAERA, IANIRA, IANTHE, IASIS, IDA, IDAEA, IDE, IONE, IPHIANASSA, ISMENIS, ITHOME, LAOMEDEIA, LARA, LEIAGORE, LEUCIPPE, LEUCOTHOE, LIBYE, LIGEA, LIMNAEE, LIMNOREIA, LIRIOPE, LOTIS, LOXO, LYCASTE, LYCORIAS, LYGEA, LYRIS, LYSIANASSA, MACRIS, MAERA, MARICA, MELETE, MELIA, MELIBOEA, MELIE, MELITE, MELOBOSIS, MELPOMENE, MENESTHO, MENODICE, MENTHIS, MESE, MIDEIA, MNEME, MOPSOPIA, MORIA, MYRTOESSA, NAUSITHOE, NEDA, NEMERTES, NEOMERIS, NEREA, NESAEA, NESO, NETE, NICAEA, NOMIA, OCYNOE, OCYRHOE, OCYRRHOE, OENOE, OENONE, OINANTHE, OPIS, OREITHYIA, ORITHYIA, ORSEIS, OTHRIS, PAIDIA, PANDAISIA, PANNYCHIS, PANOPE, PANOPEA, PARIA, PASIPHAE, PASITHEA, PEGAEA, PENELOPE, PERSEIS, PETRAEA, PHAENNA, PHAENO, PHERUSA, PHIALE, PHIGALIA, PHILYRA, PHOEBE, PHRIXA, PHYLLODCE, PITYS, PLEIONE, PLEXAURA, PLEXAURE, PLOTO, POLYDORA, POLYHYMNIA, POLYMATHEIA, POLYNOME, POLYPHE, POLYXO, POMONA, PONTOMEDUSA, PONTOPOREIA, POULYNOE, PRAXITHEA, PRONOE, PROTHOE, PROTO, PROTOMEDEIA, PRYMNO, PSECAS, RHANIS, RHAPSO, RHENE, RHODEIA, RHODOPE, SAGARITIS, SALMACIS, SALMONIS, SAO, SEBETHIS, SILENE, SINOE, SITHNIDES, SOSE, SPEIO, SPHRAGITIDE, STESICHORE, STILBO, SYLLIS, SYNALLASIS, SYRINX, TELEDICE, TELESTO, TELPHUSA, TERPSICHORE, THALEIA, THALIA, THEISOA, THELXINOE, THEMISTO, THETIS, THISBE, THRAIKE, TITHOREA, TRYGIE, URANIA, UREA, VENILIA, XANTHO, ZEUXO,
		// CENTAURS
		ABAS, ACTOR, AGAMEDIDAS, AGELAUS, AGRAEUS, AGRON, ALCAEUS, ALETES, AMPHICTYON, AMPHION, AMPYX, AMYCUS, ANAXANDRA, ANCHIUS, APHAREUS, APHIDAS, ARAS, ARCTUS, AREOS, ARISTODEMUS, ASBOLUS, BELUS, BIENOR, BOEUS, BROTEAS, CASTALIUS, CECROPS, CELADON, CERYNES, CHARAXUS, CHIRON, CHROMIS, CISSUS, CISUS, CLANIS, CLEODAEUS, COMETES, CORESUS, CORYTHUS, CRANAUS, CRANTOR, CRENAEUS, CRESPHONTES, CTESIPPUS, CYLLARUS, DEIPHONTES, DEMOLEON, DICTYS, DORYLAS, DOUPON, DRYALUS, ECHECLUS, ELATUS, ELYMUS, ERICHTHONIUS, EUAGRUS, EURYNOMUS, EURYPYLUS, EURYTION, EURYTUS, EVENOR, EXADIUS, GEMON, GRYNEUS, HALESUS, HELOPS, HIPPASUS, HIPPOTES, HIPPOTION, HODITES, HOMADUS, HOPLEUS, HYLAEUS, HYLES, HYLONOME, HYPSICERUS, HYRNETHO, IMBREUS, INDUS, IOBACCHUS, IPHINOUS, ISOPLES, ISTHMIUS, LACESTADES, LAMIS, LATHRIA, LATREUS, LELEX, LENEUS, LENOBIUS, LYCABAS, LYCAON, LYCIDAS, LYCON, LYCOPES, LYCUS, MACAREUS, MARON, MARSYAS, MELANCHAETES, MELANEUS, MERMERUS, MIMAS, MONYCHUS, MOPSUS, NAPAEUS, NEDYMNUS, NESSUS, NESTOR, NINUS, OESTRUS, ONTHYRIUS, ORESTES, ORIUS, ORNEUS, PALAECHTHON, PEAUCEUS, PELASGUS, PELATES, PELEUS, PERIMEDES, PETRAEUS, PHAECOMES, PHAESTUS, PHALCES, PHEREUS, PHIGALUS, PHLEGRAEUS, PHLEGYAS, PHOLUS, PHORBAS, PHRIXUS, PHYLAS, PIRITHOUS, PITHOS, POEMENIUS, PROCLES, PROLOCHUS, PRONOMUS, PYLAIEUS, PYLENOR, PYRACMUS, PYRAETHUS, RHOECUS, RHOETUS, RHOPADLUS, RIPHEUS, ROMUS, SCIRTUS, SILENUS, STYPHELUS, TECTAPHUS, TELEBOAS, TEMENUS, THEREUS, THERSANDER, THIASUS, THRASYANOR, TYLLUS, UREUS,
		// AMAZONS - spirits of battle
		AELLA, AGAVE, ALCIBIE, ALCIPPE, ANTANDRE, ANTIANIRA, ANTIBROTE, ANTIOCHE, ANTIOPE, BREMUSA, CLONIE, DEIANIRA, DERIMACHEIA, DERINOE, DIXUPPE, ERIBOEA, EURYALE, EVANDRE, HARMOTHOE, HARPE, HIPPOLYTE, HIPPOTHOE, IPHINOME, LAOMACHE, LYCE, MARPE, MELANIPPE, MENIPPE, MOLPADIA, MYRINA, OCYALE, OTRERE, PENTHESILIA, PHILLIPPIS, POLEMUSA, TECMESSA, THERMODOSA, THOE,
		// ERIS - spirits of conflict
		ACHLYS, ACHOS, ADEPHAGIA, ADIKA, AERGIA, AGON, ALALA, ALASTOR, ALECTO, ALGEA, ALGOS, AMECHANIA, ANAIDEIA, ANAPLEKTE, ANDROKTASIAI, ANIA, ANTEROS, APORIA, ARAE, ATE, CORUS, DEIMOS, DYSNOMIA, DYSSEBEIA, EIRENE, EKECHEIRIA, ELEOS, ELPHIS, EPIPHRON, GERAS, HEDYLOGOS, HERMAPHRODITUS, HOMADOS, HORKOS, HYSIMNAI, IOKE, KAKIA, KER, KOALEMOS, KYDOIMOS, LIMOS, LOGOI, LUPE, LYSSA, MAKHAI, MANIA, MEGAERA, MOMUS, MOROS, NEIKEA, NOSOS, OIZYS, PALIOXIS, PALIXIS, PENIA, PHEME, PHOBOS, PHONOI, PHRIKE, PHTHONUS, PONOS, PROIOXIS, PROPHASIS, PSEUDEA, PTOCHEIA, STYGERE, TISIPHONE, SYNTRIBOS, SMARAGOS, ASBETOS, SABAKTES, OMODAMOS, ENYALIUS, ENYO, GLYCON, TARAXIPPUS,
		// Greek cyclopes
		AGRIUS, ALCYONEUS, ALOADAE, ALPOS, ANAX, ANTAEUS, ANTIPHATES, ARGES, ARGOS_PANOPTES, ARISTAEUS, AZEUS, BRIAREUS, BRONTES, CACUS, CHRYSAOR, COTTUS, DAMASEN, DAMYSUS, ECHIDNADES, ELATREUS, ENCELADUS, EPHIALTES, EURYALUS, EURYMEDON, GERYON, GYES, HALIMEDES, HOPLODAMUS, HYLLUS, LEON, MYLINUS, OEOLYCA, OREUS, ORION, OTUS, PERIBOEA, POLYBOTES, POLYPHEMUS, PORPHYRION, STEROPES, SYCEUS, TITYUS, TRACHIUS,
		// Greek witches
		ACESIDAS, ACMON, ACTAEUS, AELLOPE, AGLAOPE, AITNAIOS, ALKON, ARGYRON, ATABYRIUS, CELMIS, CHALCON, CHRYSON, CIRCE, CYLLENUS, CYRBAS, DAMNAMENEUS, DEINO, DELAS, DEMONAX, DEXITHEA, EMPUSA, EPIMEDES, HECATE, HIMEROPE, IASIUS, LAMIA, LEUCOSIA, LIGEIA, LYKTOS, LYSAGORA, MAKELO, MEDUSA, MEGALESIUS, MOLPE, MORMO, MYLAS, NICOTHOE, NIKON, ONNES, ORMENOS, PAEONAEUS, PARTHENOPE, PEISINOE, PEMPHREDO, PODARKE, PYRRHICHUS, RAIDNE, SIMON, SKELMIS, STHENO, TELES, THELCHETEREIA, THELXIOPE, TITIAS, TONNES,
		// Christian angels
		BARACHIEL, JEGUDIEL, LUCIFER, MURIEL, PAHALIAH, SARATHIEL, SELAPHIEL, WORMWOOD, ZACHARIEL,
		// Christian demons
		AGARES, HABORYM, AMAYMON, AMDUSIAS, ANCITIF, ANDREALPHUS, ANDROMALIUS, ASTAROTH, BAAL, BALAM, BAPHOMET, BARBAS, BARBATOS, BATHIN, BELETH, BELPHEGOR, BEHERIT, BIFRONS, BOTIS, BUER, BUNE, CAIM, CIMEJES, CORSON, CROCELL, KIMARIS, PROCELL, DANTALION, DECARABIA, DEMOGORGON, ELIGOS, FOCALOR, FORCAS, FORNEUS, FURFUR, GAAP, GAMIGIN, GLASYA_LABOLAS, GREMORY, GUSION, HAAGENTI, HALPHAS, MALTHUS, HAURES, FLAVROS, INCUBUS, IPOS, KRAMPUS, LEGION, LEONARD, LERAJE, LUCIFUGE_ROFOCALE, MAMMON, MARAX, MARCHOSIAS, MEPHISTOPHELES, MERIHEM, MURMUR, NABERIUS, ORCUS, ORIAX, OROBAS, OSE, PAIMON, PHENEX, PITHIUS, PRUFLAS, RAUM, RONOVE, SABNOCK, SALEOS, SEIR, SHAX, SITRI, STOLAS, SUCCUBUS, SURGAT, UKOBACH, VALAC, VALEFAR, VAPULA, VASSAGO, VEPAR, VINE, ZAGAN, ZEPAR, ZIMINIAR,
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
		/*
		 * STATIC FIELDS
		 */
		private static Map<Entity, Prototype> prototypes;

		static {
			prototypes = new HashMap<Entity, Prototype>();

			Entity[] array = null;
			Entity entity = null;
			Lesser lesser = null;
			Actor.Alignment ali = null;
			Domain domain = null;

			/*
			 * CANAANITE - ELOHIM
			 */
			array = ELOHIM;
			lesser = Lesser.ELOHIM;
			ali = Actor.Alignment.LAWFUL;
			domain = Domain.LIFE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * JEWISH ANGELS & DEMONS
			 */
			array = JEWISH_ANGELS;
			lesser = Lesser.ANGEL;
			ali = Actor.Alignment.LAWFUL;
			domain = Domain.LIGHT;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			array = JEWISH_DEMONS;
			lesser = Lesser.DEMON;
			ali = Actor.Alignment.EVIL;
			domain = Domain.DEATH;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * HELLENIC GODS & SPIRITS
			 */

			// TALOS is a bronze giant built by Hephaestus to guard Crete
			domain = Domain.DEATH;
			Prototype.prototypes.put(Entity.TALOS,
					new Prototype(Entity.TALOS, Lesser.ABOMINATION, Actor.Alignment.NEUTRAL, domain));

			// HELLENIC - PRIMORDIALS (Uranus et al.)
			array = Immortal.GREEK_PRIMORDIALS;
			lesser = Lesser.ELEMENTAL;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.DEATH;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - TITANS (Cronus et al.)
			array = Immortal.TITANS;
			lesser = Lesser.TITAN;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.KNOWLEDGE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - OLYMPIANS (Zeus et al.)
			array = Immortal.OLYMPIANS;
			lesser = Lesser.OLYMPIAN;
			ali = Actor.Alignment.LAWFUL;
			domain = Domain.LIGHT;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - CHTHONIANS (Hades et al.)
			array = Immortal.CHTHONIANS;
			lesser = Lesser.CHTHONIAN;
			ali = Actor.Alignment.EVIL;
			domain = Domain.DEATH;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - OCEANIC (Poseidon et al.)
			array = Immortal.OCEANIC;
			lesser = Lesser.OCEANIC;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.TEMPEST;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - Miscellaneous SPIRITS
			array = Immortal.HELLENIC_SPIRITS;
			lesser = Lesser.ELEMENTAL;
			ali = Actor.Alignment.NEUTRAL;
			domain = Domain.TRICKERY;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - AMAZONS (war spirits)
			array = Immortal.AMAZONS;
			lesser = Lesser.ELEMENTAL;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.WAR;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - ERIC (conflict spirits)
			array = Immortal.ERIS;
			lesser = Lesser.ELEMENTAL;
			ali = Actor.Alignment.EVIL;
			domain = Domain.WAR;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - NYMPHS (nature spirits)
			array = NYMPHS;
			lesser = Lesser.NYMPH;
			ali = Actor.Alignment.GOOD;
			domain = Domain.LIFE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - NYMPHS (nature spirits)
			array = CENTAURS;
			lesser = Lesser.CENTAUR;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.NATURE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - CYCLOPES
			array = CYCLOPES;
			lesser = Lesser.CYCLOPS;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.KNOWLEDGE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			// HELLENIC - NYMPHS (nature spirits)
			array = GREEK_WITCHES;
			lesser = Lesser.WITCH;
			ali = Actor.Alignment.EVIL;
			domain = Domain.TRICKERY;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * CHRISTIAN ANGELS & DEMONS
			 */
			array = CHRISTIAN_ANGELS;
			lesser = Lesser.ANGEL;
			ali = Actor.Alignment.GOOD;
			domain = Domain.LIGHT;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			array = CHRISTIAN_DEMONS;
			lesser = Lesser.DEMON;
			ali = Actor.Alignment.EVIL;
			domain = Domain.DEATH;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * ISLAMIC ANGELS & DEMONS
			 */
			array = ISLAMIC_ANGELS;
			lesser = Lesser.ANGEL;
			ali = Actor.Alignment.GOOD;
			domain = Domain.LIGHT;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			array = ISLAMIC_DEMONS;
			lesser = Lesser.DEMON;
			ali = Actor.Alignment.EVIL;
			domain = Domain.DEATH;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * NORSE - GODS & JOTNAR
			 */
			array = JOTNAR;
			lesser = Lesser.JOTUNN;
			ali = Actor.Alignment.CHAOTIC;
			domain = Domain.KNOWLEDGE;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

			/*
			 * ENOCHIAN ANGELS
			 */
			array = ENOCHIAN_ANGELS;
			lesser = Lesser.ANGEL;
			ali = Actor.Alignment.LAWFUL;
			domain = Domain.LIGHT;
			for (int i = 0; i < array.length; ++i) {
				Prototype.prototypes.put(array[i], new Prototype(array[i], lesser, ali, domain));
			}

		}

		/*
		 * INSTANCE FIELDS
		 */
		private Entity entity;
		private Lesser affiliation;
		private EnumSet<Domain> domains;
		private EnumSet<Actor.Alignment> alignment;

		/*
		 * CONSTRUCTOR
		 */
		public Prototype(Entity entity, Lesser affiliation, Actor.Alignment alignment, Domain domain) {
			this.entity = entity;
			this.affiliation = affiliation;

			Actor.Alignment[] ali = alignmentRandomizer(alignment);
			this.alignment = EnumSet.noneOf(Actor.Alignment.class);
			if (ali.length > 0) {
				for (Actor.Alignment el : ali) {
					this.alignment.add(el);
				}
			}

			Domain[] domains = domainRandomizer(domain);
			this.domains = EnumSet.noneOf(Domain.class);
			if (domains.length > 0) {
				for (Domain el : domains)
					this.domains.add(el);
			}
		}

	}

	public static class Instance implements Actor {
		private static Set<Instance> instances;

		static {
			instances = new HashSet<Instance>();

			for (Iterator<Prototype> it = Prototype.prototypes.values().iterator(); it.hasNext();) {
				instances.add(new Instance(it.next()));
			}
		}

		private Prototype prototype;
		//
		private String name;
		private Entity entity;
		private Lesser affiliation;

		private Greater greater;
		private EnumSet<Domain> domains;
		private EnumSet<Alignment> alignment;

		public Instance(Prototype prototype) {
			this.prototype = prototype;
			//
			this.name = Dice.stringToName(prototype.entity.toString());
			this.entity = prototype.entity;
			this.affiliation = prototype.affiliation;

			//
			this.domains = EnumSet.copyOf(prototype.domains);
			this.alignment = EnumSet.copyOf(prototype.alignment);

		}

		public String getName() {
			return name;
		}

		public boolean greater() {
			return (greater != null);
		}

		public boolean lesser() {
			return (greater == null);
		}

		@Override
		public String toString() {
			String string = String.format("%s", name);

			return string;
		}

		@Override
		public String toStringDetailed() {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Deity getDeity() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public byte[] getHitDice() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EnumSet<Option.Feature> getFeatures() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setFeatures(EnumSet<Option.Feature> features) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Class getJob() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Class.Subclass getArchetype() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Race getRace() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EnumSet<Spell> getSpellsKnown() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSpellsKnown(EnumSet<Spell> spellsKnown) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Career.Profile getCareer() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLevel() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setLevel(int level) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getExperience() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setExp(int exp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EnumSet<Speed> getSpeed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public byte[] getAbilityScores() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public byte[] getAbilityCeiling() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public byte[] getSavingThrows() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EnumSet<Skill> getSkills() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSkills(EnumSet<Skill> skills) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EnumSet<Armor> getArmorProficiency() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setArmorProficiency(EnumSet<Armor> armor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EnumSet<Weapon> getWeaponProficiency() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setWeaponProficiency(EnumSet<Weapon> weapons) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setLanguages(EnumSet<Language> languages) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public CombatBlock getCombatBlock() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setCombatBlock(CombatBlock combat) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Inventory getInventory() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setInventory(Inventory inventory) {
			// TODO Auto-generated method stub
			
		}

	}

	/*
	 * STATIC FIELDS
	 */
	private static final Domain[] DOMAINS = { Domain.DEATH, Domain.KNOWLEDGE, Domain.LIFE, Domain.LIGHT, Domain.NATURE,
			Domain.TEMPEST, Domain.TRICKERY, Domain.WAR };

	private static final Lesser[] PANTHEONS = { Lesser.ELOHIM, Lesser.ANGEL, Lesser.DEMON, Lesser.ELEMENTAL,
			Lesser.TITAN, Lesser.OLYMPIAN, Lesser.CHTHONIAN, Lesser.OCEANIC, Lesser.NYMPH, Lesser.CYCLOPS,
			Lesser.JOTUNN, Lesser.WITCH, Lesser.CENTAUR };

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
			Entity.BETH_HORON, Entity.DAGAN, Entity.ELYON, Entity.ERETZ, Entity.ESHMUN, Entity.HADAD, Entity.HAMMON,
			Entity.HERMON, Entity.ISHAT, Entity.KHIKHIBI, Entity.KOTHAR_WA_KHASIS, Entity.KOTHARAT, Entity.LOTAN,
			Entity.MARQOD, Entity.MELQART, Entity.MISOR, Entity.MOLOCH, Entity.MOT, Entity.NIKKAL_WA_IB,
			Entity.QADESHTU, Entity.RESHEPH, Entity.SHAHAR, Entity.SHALIM, Entity.SHAMAYIM, Entity.SHAPASH,
			Entity.SHEOL, Entity.SYDYK, Entity.TAAUTUS, Entity.TANIT, Entity.YAM, Entity.YARIKH };

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
	 * HELLENIC - GREEK/OLYMPIAN
	 */
	private static final Entity[] GREEK_PRIMORDIALS = { Entity.AETHER, Entity.AION, Entity.AITNA, Entity.ANANKE,
			Entity.ATHOS, Entity.ATROPOS, Entity.CHAOS, Entity.CHRONOS, Entity.CLOTHO, Entity.ECHIDNA, Entity.EREBUS,
			Entity.ERIS, Entity.EROS, Entity.EURYNOME, Entity.GAIA, Entity.HELIKON, Entity.HEMERA, Entity.HIMEROS,
			Entity.HYPNOS, Entity.KITAIRON, Entity.LACHESIS, Entity.NEMESIS, Entity.NYSOS, Entity.NYX, Entity.OLYMPUS,
			Entity.OPHION, Entity.OREIOS, Entity.PARNES, Entity.PHANES, Entity.PHYSIS, Entity.PONTUS, Entity.TARTARUS,
			Entity.THALASSA, Entity.THANATOS, Entity.TMOLUS, Entity.TYPHON, Entity.URANUS };
	private static final Entity[] TITANS = { Entity.ARKE, Entity.ASTERIA, Entity.ASTRAEUS, Entity.ASTRAIOS,
			Entity.ATLAS, Entity.AURA, Entity.CLYMENE, Entity.COEUS, Entity.CRIUS, Entity.CRONUS, Entity.DIONE,
			Entity.EOS, Entity.EPIMETHEUS, Entity.EURYBIA, Entity.HELIOS, Entity.HYPERION, Entity.IAPETUS,
			Entity.LELANTOS, Entity.LETO, Entity.MENOETIUS, Entity.METIS, Entity.MNEMOSYNE, Entity.OCEANUS,
			Entity.PALLAS, Entity.PERSES, Entity.PROMETHEUS, Entity.RHEA, Entity.SELENE, Entity.TETHYS, Entity.THEIA,
			Entity.THEMIS };
	private static final Entity[] OLYMPIANS = { Entity.APHRODITE, Entity.APOLLO, Entity.ARES, Entity.ARTEMIS,
			Entity.ATHENA, Entity.DEMETER, Entity.DIONYSUS, Entity.HEPHAESTUS, Entity.HERA, Entity.HERMES,
			Entity.HESTIA, Entity.ZEUS };
	private static final Entity[] CHTHONIANS = { Entity.ACHERON, Entity.AIAKOS, Entity.AMPHIARAUS, Entity.ANGELOS,
			Entity.ASKALAPHOS, Entity.CERBERUS, Entity.CHARON, Entity.GORGYRA, Entity.HADES, Entity.KEUTHONYMOS,
			Entity.KOKYTOS, Entity.LETHE, Entity.MACARIA, Entity.MELINOE, Entity.MENOETES, Entity.MINOS, Entity.ORPHNE,
			Entity.PERSEPHONE, Entity.PHLEGETHON, Entity.STYX, Entity.THADAMANTHYS, Entity.ZAGREUS };
	private static final Entity[] OCEANIC = { Entity.POSEIDON, Entity.AEGAEON, Entity.ACHELOUS, Entity.AMPHITRITE,
			Entity.BENTHESIKYME, Entity.BRIZO, Entity.CETO, Entity.CHARYBDIS, Entity.CYMOPOLEIA, Entity.DELPHIN,
			Entity.EIDOTHEA, Entity.GLAUCUS, Entity.LEUCOTHEA, Entity.PSAMATHE, Entity.NEREUS, Entity.NERITES,
			Entity.PALAEMON, Entity.SANGARIUS, Entity.THETHYS, Entity.THAUMAS, Entity.THOOSA, Entity.TRITEIA,
			Entity.TRITON, Entity.ACIS, Entity.ALPHEUS, Entity.ASOPUS, Entity.CLADEUS, Entity.COCYTUS, Entity.EUROTAS,
			Entity.NILUS, Entity.PENEUS, Entity.SCAMANDER };
	private static final Entity[] ERIS = { Entity.ACHLYS, Entity.ACHOS, Entity.ADEPHAGIA, Entity.ADIKA, Entity.AERGIA,
			Entity.AGON, Entity.ALALA, Entity.ALASTOR, Entity.ALECTO, Entity.ALGEA, Entity.ALGOS, Entity.AMECHANIA,
			Entity.ANAIDEIA, Entity.ANAPLEKTE, Entity.ANDROKTASIAI, Entity.ANIA, Entity.ANTEROS, Entity.APORIA,
			Entity.ARAE, Entity.ATE, Entity.CORUS, Entity.DEIMOS, Entity.DYSNOMIA, Entity.DYSSEBEIA, Entity.EIRENE,
			Entity.EKECHEIRIA, Entity.ELEOS, Entity.ELPHIS, Entity.EPIPHRON, Entity.GERAS, Entity.HEDYLOGOS,
			Entity.HERMAPHRODITUS, Entity.HOMADOS, Entity.HORKOS, Entity.HYSIMNAI, Entity.IOKE, Entity.KAKIA,
			Entity.KER, Entity.KOALEMOS, Entity.KYDOIMOS, Entity.LIMOS, Entity.LOGOI, Entity.LUPE, Entity.LYSSA,
			Entity.MAKHAI, Entity.MANIA, Entity.MEGAERA, Entity.MOMUS, Entity.MOROS, Entity.NEIKEA, Entity.NOSOS,
			Entity.OIZYS, Entity.PALIOXIS, Entity.PALIXIS, Entity.PENIA, Entity.PHEME, Entity.PHOBOS, Entity.PHONOI,
			Entity.PHRIKE, Entity.PHTHONUS, Entity.PONOS, Entity.PROIOXIS, Entity.PROPHASIS, Entity.PSEUDEA,
			Entity.PTOCHEIA, Entity.STYGERE, Entity.TISIPHONE, Entity.SYNTRIBOS, Entity.SMARAGOS, Entity.ASBETOS,
			Entity.SABAKTES, Entity.OMODAMOS, Entity.ENYALIUS, Entity.ENYO, Entity.GLYCON, Entity.TARAXIPPUS };
	private static final Entity[] AMAZONS = { Entity.AELLA, Entity.AGAVE, Entity.ALCIBIE, Entity.ALCIPPE,
			Entity.ANTANDRE, Entity.ANTIANIRA, Entity.ANTIBROTE, Entity.ANTIOCHE, Entity.ANTIOPE, Entity.BREMUSA,
			Entity.CLONIE, Entity.DEIANIRA, Entity.DERIMACHEIA, Entity.DERINOE, Entity.DIXUPPE, Entity.ERIBOEA,
			Entity.EURYALE, Entity.EVANDRE, Entity.HARMOTHOE, Entity.HARPE, Entity.HIPPOLYTE, Entity.HIPPOTHOE,
			Entity.IPHINOME, Entity.LAOMACHE, Entity.LYCE, Entity.MARPE, Entity.MELANIPPE, Entity.MENIPPE,
			Entity.MOLPADIA, Entity.MYRINA, Entity.OCYALE, Entity.OTRERE, Entity.PENTHESILIA, Entity.PHILLIPPIS,
			Entity.POLEMUSA, Entity.TECMESSA, Entity.THERMODOSA, Entity.THOE };
	private static final Entity[] HELLENIC_SPIRITS = { Entity.ACAMANTIS, Entity.ACESO, Entity.ACHELOIS,
			Entity.ACRATOPOTES, Entity.ACTAEA, Entity.ADIANTE, Entity.ADITE, Entity.ADONIS, Entity.ADRASTEA,
			Entity.AEGIUS, Entity.AEGLE, Entity.AEGYPTUS, Entity.AEOLUS, Entity.AESA, Entity.AGAPTOLEMUS,
			Entity.AGATHOS, Entity.AGDISTIS, Entity.AGENOR, Entity.AIDOS, Entity.AIOPIS, Entity.AKMON, Entity.ALCES,
			Entity.ALCMENOR, Entity.ALCYONE, Entity.ALECTRONA, Entity.ALETHEIA, Entity.ALEXIARES, Entity.ALKE,
			Entity.AMPHICOMONE, Entity.AMPHICTYONIS, Entity.AMYMONE, Entity.AMYNTOR, Entity.ANATOLE, Entity.ANAXIBIA,
			Entity.ANDROMACHUS, Entity.ANGELEIA, Entity.ANICETUS, Entity.ANTHEIA, Entity.ANTHELIA, Entity.ANTHOUSAI,
			Entity.ANTIMACHUS, Entity.ANTIOCHUS, Entity.ANTIPAPHUS, Entity.APARCTIAS, Entity.APHAEA, Entity.APHELIOTES,
			Entity.APHRODITUS, Entity.ARBELUS, Entity.ARCADIA, Entity.ARCHELAUS, Entity.ARETE, Entity.ARETHUSA,
			Entity.ARGESTES, Entity.ARGIUS, Entity.ARIADNE, Entity.ARISTONOOS, Entity.ARKTOS, Entity.ARMOASBUS,
			Entity.ARSALTE, Entity.ASCLEPIUS, Entity.ASTERIDES, Entity.ASTEROPE, Entity.ATHALIDES, Entity.ATHAMAS,
			Entity.ATHLETES, Entity.ATTIS, Entity.AUGE, Entity.AUTODICE, Entity.AUTOMATE, Entity.AUTONOE,
			Entity.AUXESIA, Entity.AUXO, Entity.BIA, Entity.BOREAS, Entity.BRITOMARTIS, Entity.BROMIUS, Entity.BRYCE,
			Entity.BUSIRIS, Entity.CAERUS, Entity.CAICIAS, Entity.CALLIDICE, Entity.CANTHUS, Entity.CARMANOR,
			Entity.CARME, Entity.CASSUS, Entity.CELAENO, Entity.CERAON, Entity.CERCETES, Entity.CHAETUS,
			Entity.CHALCODON, Entity.CHEIMON, Entity.CHLORIS, Entity.CHOINE, Entity.CHRYSIPPE, Entity.CHRYSIPPUS,
			Entity.CHRYSOTHEMIS, Entity.CHRYSUS, Entity.CHTHONIUS, Entity.CIRCIOS, Entity.CISSEUS, Entity.CLEODORA,
			Entity.CLEOPATRA, Entity.CLITE, Entity.CLITUS, Entity.CLYTUS, Entity.COMUS, Entity.COPREUS, Entity.CORYMBUS,
			Entity.CRITOMEDIA, Entity.CYAMITES, Entity.CYBELE, Entity.DAIPHRON, Entity.DAMNEUS, Entity.DAMONE,
			Entity.DAPLIDICE, Entity.DEIKAIOSYNE, Entity.DEIPNEUS, Entity.DEMARCHUS, Entity.DEMODITAS, Entity.DEMOPHILE,
			Entity.DESPOINA, Entity.DIKE, Entity.DINDYMENE, Entity.DIOCORYSTES, Entity.DIOXIPPE, Entity.DIPPODICE,
			Entity.DOLICHUS, Entity.DOLOS, Entity.DONAKIS, Entity.DORIUM, Entity.DRYAS, Entity.DYSIS, Entity.ECNOMINUS,
			Entity.EIAR, Entity.EILEITHYIA, Entity.EIRESIONE, Entity.ELECTRA, Entity.ELETE, Entity.EOSPHORUS,
			Entity.EPIALES, Entity.EPIONE, Entity.ERSA, Entity.ERYTHEA, Entity.EUBULE, Entity.EUCHENOR, Entity.EUCLEIA,
			Entity.EUDAEMON, Entity.EULABEIA, Entity.EUMELUS, Entity.EUMONIA, Entity.EUNOMIA, Entity.EUNOSTUS,
			Entity.EUPHEME, Entity.EUPORIE, Entity.EUPRAXIA, Entity.EURONOTUS, Entity.EUROPOME, Entity.EURUS,
			Entity.EURYBATES, Entity.EURYDAMAS, Entity.EURYLOCHUS, Entity.EURYSTHENES, Entity.EUSEBEIA, Entity.EUTHENIA,
			Entity.EVIDEA, Entity.EVIPPE, Entity.GELOS, Entity.GLAUCIPPE, Entity.GORGE, Entity.GORGOPHONE,
			Entity.GYMNASTIKA, Entity.HARMONIA, Entity.HARPOCRATES, Entity.HEBE, Entity.HECABE, Entity.HECATERUS,
			Entity.HEDONE, Entity.HEIMARMENE, Entity.HELICTA, Entity.HERMUS, Entity.HERO, Entity.HESPERA,
			Entity.HESPEREIA, Entity.HESPERUS, Entity.HESPERUSA, Entity.HIPPARTE, Entity.HIPPODAMIA, Entity.HIPPOLYTUS,
			Entity.HIPPOMEDUSA, Entity.HIPPOTHOUS, Entity.HOMONOIA, Entity.HORME, Entity.HYGIEIA, Entity.HYMENAEUS,
			Entity.HYPERANTUS, Entity.HYPERBIUS, Entity.HYPERIPPE, Entity.HYPERIUS, Entity.HYPERMNESTRA, Entity.IASO,
			Entity.ICHNAEA, Entity.IDAIOS, Entity.IDAS, Entity.IDMON, Entity.ILTONOMUS, Entity.IMBRUS,
			Entity.IPHIMEDUSA, Entity.IRIS, Entity.ISTRUS, Entity.ITEA, Entity.IYNX, Entity.KALOKAGATHIA,
			Entity.KALYPSO, Entity.KARPO, Entity.KRATOS, Entity.KROTOS, Entity.KYRBAS, Entity.LAMPUS, Entity.LEOS,
			Entity.LICHAS, Entity.LIPARA, Entity.LIPS, Entity.LITAE, Entity.LIXUS, Entity.LYNCEUS, Entity.MAIA,
			Entity.MAPSAURA, Entity.MATTON, Entity.MEDON, Entity.MENALCAS, Entity.MENEMACHUS, Entity.MERMESA,
			Entity.MEROPE, Entity.MESEMBRIA, Entity.METALCES, Entity.MIDANUS, Entity.MIDEA, Entity.MINEUS,
			Entity.MNESTRA, Entity.MONUSTE, Entity.MORPHEUS, Entity.MOUSIKA, Entity.MYRMIDONE, Entity.NELISA,
			Entity.NELO, Entity.NEPHELE, Entity.NIAUIUS, Entity.NIKE, Entity.NOMOS, Entity.NOTUS, Entity.NYMPHE,
			Entity.OBRIMUS, Entity.OCYPETE, Entity.ODIUS, Entity.OEME, Entity.OENEUS, Entity.OKYTHOOS, Entity.ORTHOSIE,
			Entity.PAEAN, Entity.PALAESTRA, Entity.PAMPHILUS, Entity.PAN, Entity.PANACEA, Entity.PANDIA, Entity.PANDION,
			Entity.PANTHIUS, Entity.PASSALOS, Entity.PEITHARCHIA, Entity.PEITHO, Entity.PENTHUS, Entity.PEPROMENE,
			Entity.PERIPHAS, Entity.PERISTHENES, Entity.PERIUS, Entity.PHAENON, Entity.PHAETHON, Entity.PHANTASOS,
			Entity.PHANTES, Entity.PHARTIS, Entity.PHERESPONDUS, Entity.PHEROUSA, Entity.PHILINUS, Entity.PHILOMELUS,
			Entity.PHILOPHROSYNE, Entity.PHILOTES, Entity.PHOBETOR, Entity.PIRENE, Entity.PISENOR, Entity.PISTIS,
			Entity.PLEXIPPUS, Entity.PLUTUS, Entity.PODARCE, Entity.PODASIMUS, Entity.POINE, Entity.POLEMOS,
			Entity.POLYBE, Entity.POLYCTOR, Entity.POLYDECTOR, Entity.POLYPHONTES, Entity.POLYXENA, Entity.POROS,
			Entity.POTAMON, Entity.POTHOS, Entity.PRAXIDIKE, Entity.PRIAPUS, Entity.PROTHEON, Entity.PRYMNEUS,
			Entity.PTHINOPORON, Entity.PUGNO, Entity.PYLARGE, Entity.PYRANTE, Entity.PYRANTIS, Entity.PYROEIS,
			Entity.PYRRHICHOS, Entity.SABAZIOS, Entity.SCAEA, Entity.SCYLLA, Entity.SKEIRON, Entity.SOPHROSYNE,
			Entity.SOTER, Entity.SOTERIA, Entity.SPONDE, Entity.STEROPE, Entity.STHENELE, Entity.STHENELUS,
			Entity.STILBON, Entity.STYGNE, Entity.TALTHYBIUS, Entity.TARA, Entity.TAYGETE, Entity.TECHNE,
			Entity.TELESPHORUS, Entity.TELETE, Entity.THALLO, Entity.THEANO, Entity.THEMISTAGORA, Entity.THEROS,
			Entity.THOOTES, Entity.THRASOS, Entity.TRIPTOLEMUS, Entity.TRITE, Entity.TYCHE, Entity.XANTHUS,
			Entity.ZELOS, Entity.ZEPHYRUS };
	private static final Entity[] NYMPHS = { Entity.ABARBAREA, Entity.ACASTE, Entity.ACRETE, Entity.ADMETE,
			Entity.ADRASTEIA, Entity.ADRASTIA, Entity.AETHRA, Entity.AETNA, Entity.AGANIPPE, Entity.AGAUE,
			Entity.AGLAEA, Entity.AGLAIA, Entity.ALCINOE, Entity.AMALTHEA, Entity.AMATHEIA, Entity.AMBROSIA,
			Entity.AMPHINOME, Entity.AMPHIRHO, Entity.AMPHITHOE, Entity.ANCHIALE, Entity.ANCHIROE, Entity.ANNA_PERENNA,
			Entity.ANTHEDON, Entity.ANTHRACIA, Entity.AOIDE, Entity.APOLLONIS, Entity.APSEUDES, Entity.ARCHE,
			Entity.ARGIA, Entity.ARGIOPE, Entity.ARGYRA, Entity.ARNE, Entity.ASIA, Entity.ASTACIA, Entity.ASTERODIA,
			Entity.ATLANTIA, Entity.BATIA, Entity.BEROE, Entity.BORYSTHENIS, Entity.BRYUSA, Entity.BYBLIS,
			Entity.CALIADNE, Entity.CALLEIS, Entity.CALLIANASSA, Entity.CALLIANEIRA, Entity.CALLICHORE, Entity.CALLIOPE,
			Entity.CALLIPHAEIA, Entity.CALLIRHOE, Entity.CALYBE, Entity.CALYCE, Entity.CALYPSO, Entity.CAMARINA,
			Entity.CAMENTIS, Entity.CAPHEIRA, Entity.CARTHAGO, Entity.CASSOTIS, Entity.CEPHISSO, Entity.CERCEIS,
			Entity.CHARICLO, Entity.CHRLORIS, Entity.CHRYSE, Entity.CHRYSEIS, Entity.CHRYSOPELIA, Entity.CLEOCHARIA,
			Entity.CLETA, Entity.CLIO, Entity.CLITEMNESTE, Entity.CLONIA, Entity.CLYTIE, Entity.CNOSSIA, Entity.CORONIS,
			Entity.CORYCIA, Entity.CORYPHE, Entity.CRANAE, Entity.CRANTO, Entity.CRENEIS, Entity.CREUSA, Entity.CROCALE,
			Entity.CYANE, Entity.CYDIPPE, Entity.CYLLENE, Entity.CYMATOLEGE, Entity.CYMO, Entity.CYMODOCE,
			Entity.CYMOTHOE, Entity.CYNOSURA, Entity.CYRENE, Entity.DAEIRA, Entity.DANAIS, Entity.DAPHNIS,
			Entity.DAULIS, Entity.DEIOPEA, Entity.DEIOPOE, Entity.DERCETIS, Entity.DERO, Entity.DEXAMENE,
			Entity.DIOPATRA, Entity.DODONE, Entity.DORIS, Entity.DOTO, Entity.DROSERA, Entity.DRYMO, Entity.DRYOPE,
			Entity.DYNAMENE, Entity.ECHENAIS, Entity.ECHO, Entity.EGERIA, Entity.EIDYIA, Entity.EIONE, Entity.EPHYRA,
			Entity.EPIMELIA, Entity.ERATO, Entity.EREUTHO, Entity.ETHEMEA, Entity.EUAGORE, Entity.EUAGOREIS,
			Entity.EUARNE, Entity.EUCRANTE, Entity.EUDAIMONIA, Entity.EUDORA, Entity.EUDORE, Entity.EULIMENE,
			Entity.EUMOLPE, Entity.EUNICE, Entity.EUPETALE, Entity.EUPHROSYNE, Entity.EUPOMPE, Entity.EUROPA,
			Entity.EURYDICE, Entity.EURYTE, Entity.EUTERPE, Entity.EUTHYMIA, Entity.GALATEA, Entity.GALAXAURA,
			Entity.GALENE, Entity.GLAUCE, Entity.GLAUCONOME, Entity.HAGNO, Entity.HALIE, Entity.HALIMEDE,
			Entity.HECAERGE, Entity.HEGEMONE, Entity.HEGETORIA, Entity.HELICE, Entity.HESIONE, Entity.HESTYAEA,
			Entity.HIEROMNEME, Entity.HIMALIA, Entity.HIPPO, Entity.HIPPONOE, Entity.HORA, Entity.HYADES, Entity.HYALE,
			Entity.IACHE, Entity.IAERA, Entity.IANIRA, Entity.IANTHE, Entity.IASIS, Entity.IDA, Entity.IDAEA,
			Entity.IDE, Entity.IONE, Entity.IPHIANASSA, Entity.ISMENIS, Entity.ITHOME, Entity.LAOMEDEIA, Entity.LARA,
			Entity.LEIAGORE, Entity.LEUCIPPE, Entity.LEUCOTHOE, Entity.LIBYE, Entity.LIGEA, Entity.LIMNAEE,
			Entity.LIMNOREIA, Entity.LIRIOPE, Entity.LOTIS, Entity.LOXO, Entity.LYCASTE, Entity.LYCORIAS, Entity.LYGEA,
			Entity.LYRIS, Entity.LYSIANASSA, Entity.MACRIS, Entity.MAERA, Entity.MARICA, Entity.MELETE, Entity.MELIA,
			Entity.MELIBOEA, Entity.MELIE, Entity.MELITE, Entity.MELOBOSIS, Entity.MELPOMENE, Entity.MENESTHO,
			Entity.MENODICE, Entity.MENTHIS, Entity.MESE, Entity.MIDEIA, Entity.MNEME, Entity.MOPSOPIA, Entity.MORIA,
			Entity.MYRTOESSA, Entity.NAUSITHOE, Entity.NEDA, Entity.NEMERTES, Entity.NEOMERIS, Entity.NEREA,
			Entity.NESAEA, Entity.NESO, Entity.NETE, Entity.NICAEA, Entity.NOMIA, Entity.OCYNOE, Entity.OCYRHOE,
			Entity.OCYRRHOE, Entity.OENOE, Entity.OENONE, Entity.OINANTHE, Entity.OPIS, Entity.OREITHYIA,
			Entity.ORITHYIA, Entity.ORSEIS, Entity.OTHRIS, Entity.PAIDIA, Entity.PANDAISIA, Entity.PANNYCHIS,
			Entity.PANOPE, Entity.PANOPEA, Entity.PARIA, Entity.PASIPHAE, Entity.PASITHEA, Entity.PEGAEA,
			Entity.PENELOPE, Entity.PERSEIS, Entity.PETRAEA, Entity.PHAENNA, Entity.PHAENO, Entity.PHERUSA,
			Entity.PHIALE, Entity.PHIGALIA, Entity.PHILYRA, Entity.PHOEBE, Entity.PHRIXA, Entity.PHYLLODCE,
			Entity.PITYS, Entity.PLEIONE, Entity.PLEXAURA, Entity.PLEXAURE, Entity.PLOTO, Entity.POLYDORA,
			Entity.POLYHYMNIA, Entity.POLYMATHEIA, Entity.POLYNOME, Entity.POLYPHE, Entity.POLYXO, Entity.POMONA,
			Entity.PONTOMEDUSA, Entity.PONTOPOREIA, Entity.POULYNOE, Entity.PRAXITHEA, Entity.PRONOE, Entity.PROTHOE,
			Entity.PROTO, Entity.PROTOMEDEIA, Entity.PRYMNO, Entity.PSECAS, Entity.RHANIS, Entity.RHAPSO, Entity.RHENE,
			Entity.RHODEIA, Entity.RHODOPE, Entity.SAGARITIS, Entity.SALMACIS, Entity.SALMONIS, Entity.SAO,
			Entity.SEBETHIS, Entity.SILENE, Entity.SINOE, Entity.SITHNIDES, Entity.SOSE, Entity.SPEIO,
			Entity.SPHRAGITIDE, Entity.STESICHORE, Entity.STILBO, Entity.SYLLIS, Entity.SYNALLASIS, Entity.SYRINX,
			Entity.TELEDICE, Entity.TELESTO, Entity.TELPHUSA, Entity.TERPSICHORE, Entity.THALEIA, Entity.THALIA,
			Entity.THEISOA, Entity.THELXINOE, Entity.THEMISTO, Entity.THETIS, Entity.THISBE, Entity.THRAIKE,
			Entity.TITHOREA, Entity.TRYGIE, Entity.URANIA, Entity.UREA, Entity.VENILIA, Entity.XANTHO, Entity.ZEUXO };
	private static final Entity[] CENTAURS = { Entity.ABAS, Entity.ACTOR, Entity.AGAMEDIDAS, Entity.AGELAUS,
			Entity.AGRAEUS, Entity.AGRON, Entity.ALCAEUS, Entity.ALETES, Entity.AMPHICTYON, Entity.AMPHION,
			Entity.AMPYX, Entity.AMYCUS, Entity.ANAXANDRA, Entity.ANCHIUS, Entity.APHAREUS, Entity.APHIDAS, Entity.ARAS,
			Entity.ARCTUS, Entity.AREOS, Entity.ARISTODEMUS, Entity.ASBOLUS, Entity.BELUS, Entity.BIENOR, Entity.BOEUS,
			Entity.BROTEAS, Entity.CASTALIUS, Entity.CECROPS, Entity.CELADON, Entity.CERYNES, Entity.CHARAXUS,
			Entity.CHIRON, Entity.CHROMIS, Entity.CISSUS, Entity.CISUS, Entity.CLANIS, Entity.CLEODAEUS, Entity.COMETES,
			Entity.CORESUS, Entity.CORYTHUS, Entity.CRANAUS, Entity.CRANTOR, Entity.CRENAEUS, Entity.CRESPHONTES,
			Entity.CTESIPPUS, Entity.CYLLARUS, Entity.DEIPHONTES, Entity.DEMOLEON, Entity.DICTYS, Entity.DORYLAS,
			Entity.DOUPON, Entity.DRYALUS, Entity.ECHECLUS, Entity.ELATUS, Entity.ELYMUS, Entity.ERICHTHONIUS,
			Entity.EUAGRUS, Entity.EURYNOMUS, Entity.EURYPYLUS, Entity.EURYTION, Entity.EURYTUS, Entity.EVENOR,
			Entity.EXADIUS, Entity.GEMON, Entity.GRYNEUS, Entity.HALESUS, Entity.HELOPS, Entity.HIPPASUS,
			Entity.HIPPOTES, Entity.HIPPOTION, Entity.HODITES, Entity.HOMADUS, Entity.HOPLEUS, Entity.HYLAEUS,
			Entity.HYLES, Entity.HYLONOME, Entity.HYPSICERUS, Entity.HYRNETHO, Entity.IMBREUS, Entity.INDUS,
			Entity.IOBACCHUS, Entity.IPHINOUS, Entity.ISOPLES, Entity.ISTHMIUS, Entity.LACESTADES, Entity.LAMIS,
			Entity.LATHRIA, Entity.LATREUS, Entity.LELEX, Entity.LENEUS, Entity.LENOBIUS, Entity.LYCABAS, Entity.LYCAON,
			Entity.LYCIDAS, Entity.LYCON, Entity.LYCOPES, Entity.LYCUS, Entity.MACAREUS, Entity.MARON, Entity.MARSYAS,
			Entity.MELANCHAETES, Entity.MELANEUS, Entity.MERMERUS, Entity.MIMAS, Entity.MONYCHUS, Entity.MOPSUS,
			Entity.NAPAEUS, Entity.NEDYMNUS, Entity.NESSUS, Entity.NESTOR, Entity.NINUS, Entity.OESTRUS,
			Entity.ONTHYRIUS, Entity.ORESTES, Entity.ORIUS, Entity.ORNEUS, Entity.PALAECHTHON, Entity.PEAUCEUS,
			Entity.PELASGUS, Entity.PELATES, Entity.PELEUS, Entity.PERIMEDES, Entity.PETRAEUS, Entity.PHAECOMES,
			Entity.PHAESTUS, Entity.PHALCES, Entity.PHEREUS, Entity.PHIGALUS, Entity.PHLEGRAEUS, Entity.PHLEGYAS,
			Entity.PHOLUS, Entity.PHORBAS, Entity.PHRIXUS, Entity.PHYLAS, Entity.PIRITHOUS, Entity.PITHOS,
			Entity.POEMENIUS, Entity.PROCLES, Entity.PROLOCHUS, Entity.PRONOMUS, Entity.PYLAIEUS, Entity.PYLENOR,
			Entity.PYRACMUS, Entity.PYRAETHUS, Entity.RHOECUS, Entity.RHOETUS, Entity.RHOPADLUS, Entity.RIPHEUS,
			Entity.ROMUS, Entity.SCIRTUS, Entity.SILENUS, Entity.STYPHELUS, Entity.TECTAPHUS, Entity.TELEBOAS,
			Entity.TEMENUS, Entity.THEREUS, Entity.THERSANDER, Entity.THIASUS, Entity.THRASYANOR, Entity.TYLLUS,
			Entity.UREUS, };
	private static final Entity[] CYCLOPES = { Entity.AGRIUS, Entity.ALCYONEUS, Entity.ALOADAE, Entity.ALPOS,
			Entity.ANAX, Entity.ANTAEUS, Entity.ANTIPHATES, Entity.ARGES, Entity.ARGOS_PANOPTES, Entity.ARISTAEUS,
			Entity.AZEUS, Entity.BRIAREUS, Entity.BRONTES, Entity.CACUS, Entity.CHRYSAOR, Entity.COTTUS,
			Entity.CYMOPOLEIA, Entity.DAMASEN, Entity.DAMYSUS, Entity.ECHIDNADES, Entity.ELATREUS, Entity.ENCELADUS,
			Entity.EPHIALTES, Entity.EURYALUS, Entity.EURYMEDON, Entity.GERYON, Entity.GYES, Entity.HALIMEDES,
			Entity.HOPLODAMUS, Entity.HYLLUS, Entity.LEON, Entity.MYLINUS, Entity.OEOLYCA, Entity.OREUS, Entity.ORION,
			Entity.OTUS, Entity.PERIBOEA, Entity.POLYBOTES, Entity.POLYPHEMUS, Entity.PORPHYRION, Entity.STEROPES,
			Entity.SYCEUS, Entity.TITYUS, Entity.TRACHIUS };
	private static final Entity[] GREEK_WITCHES = { Entity.ACESIDAS, Entity.ACMON, Entity.ACTAEUS, Entity.AELLOPE,
			Entity.AGLAOPE, Entity.AITNAIOS, Entity.ALKON, Entity.ARGYRON, Entity.ATABYRIUS, Entity.CELMIS,
			Entity.CHALCON, Entity.CHRYSON, Entity.CIRCE, Entity.CYLLENUS, Entity.CYRBAS, Entity.DAMNAMENEUS,
			Entity.DEINO, Entity.DELAS, Entity.DEMONAX, Entity.DEXITHEA, Entity.EMPUSA, Entity.EPIMEDES, Entity.HECATE,
			Entity.HIMEROPE, Entity.IASIUS, Entity.LAMIA, Entity.LEUCOSIA, Entity.LIGEIA, Entity.LYKTOS,
			Entity.LYSAGORA, Entity.MAKELO, Entity.MEDUSA, Entity.MEGALESIUS, Entity.MOLPE, Entity.MORMO, Entity.MYLAS,
			Entity.NICOTHOE, Entity.NIKON, Entity.ONNES, Entity.ORMENOS, Entity.PAEONAEUS, Entity.PARTHENOPE,
			Entity.PEISINOE, Entity.PEMPHREDO, Entity.PODARKE, Entity.PYRRHICHUS, Entity.RAIDNE, Entity.SIMON,
			Entity.SKELMIS, Entity.STHENO, Entity.TELES, Entity.THELCHETEREIA, Entity.THELXIOPE, Entity.TITIAS,
			Entity.TONNES

	};

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
	 * STATIC METHODS
	 */
	public static Set<Instance> filterByPantheon(Lesser lesser) {
		Set<Instance> workingSet = new HashSet<Instance>();

		Instance candidate;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.affiliation.equals(lesser))
				workingSet.add(candidate);

		}

		return workingSet;
	}

	public static Lesser randomCosmicAffiliation() {
		return Dice.randomFromArray(PANTHEONS);
	}

	public static Set<Instance> powersOfAlignment(Actor.Alignment alignment) {
		Set<Instance> set = new HashSet<Instance>();

		Instance instance;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			instance = it.next();
			if (instance.alignment.contains(alignment))
				set.add(instance);
		}

		return set;
	}

	public static String alignmentToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0 };

		Instance instance;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			instance = it.next();

			if (instance.alignment.contains(Actor.Alignment.LAWFUL))
				++array[0];

			if (instance.alignment.contains(Actor.Alignment.GOOD))
				++array[1];

			if (instance.alignment.contains(Actor.Alignment.NEUTRAL))
				++array[2];

			if (instance.alignment.contains(Actor.Alignment.EVIL))
				++array[3];

			if (instance.alignment.contains(Actor.Alignment.CHAOTIC))
				++array[4];
		}

		String string = String.format("  Lawful: %d %n    Good: %d %n Neutral: %d %n    Evil: %d %n Chaotic: %d ",
				array[0], array[1], array[2], array[3], array[4]);
		return string;
	}

	public static String alignmentGridStatsToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		double total = Instance.instances.size();

		Instance instance;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			instance = it.next();

			if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.GOOD))
				++array[0];
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.NEUTRAL))
				++array[1];
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.EVIL))
				++array[2];
			else if (instance.alignment.contains(Actor.Alignment.NEUTRAL) && instance.alignment.contains(Actor.Alignment.GOOD))
				++array[3];
			else if (instance.alignment.contains(Actor.Alignment.NEUTRAL) && instance.alignment.contains(Actor.Alignment.EVIL))
				++array[5];
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.GOOD))
				++array[6];
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.NEUTRAL))
				++array[7];
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.EVIL))
				++array[8];
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) != true
					&& instance.alignment.contains(Actor.Alignment.CHAOTIC) != true
					&& instance.alignment.contains(Actor.Alignment.GOOD) != true
					&& instance.alignment.contains(Actor.Alignment.EVIL) != true)
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

	@SuppressWarnings("unchecked")
	public static String beingsByGridToString() {
		Set<Instance>[] array = (Set<Instance>[]) new HashSet[9];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new HashSet<Instance>();
		}

		Instance instance;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			instance = it.next();

			if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.GOOD))
				array[0].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.NEUTRAL))
				array[1].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) && instance.alignment.contains(Actor.Alignment.EVIL))
				array[2].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.NEUTRAL) && instance.alignment.contains(Actor.Alignment.GOOD))
				array[3].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.NEUTRAL) && instance.alignment.contains(Actor.Alignment.EVIL))
				array[5].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.GOOD))
				array[6].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.NEUTRAL))
				array[7].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.CHAOTIC) && instance.alignment.contains(Actor.Alignment.EVIL))
				array[8].add(instance);
			else if (instance.alignment.contains(Actor.Alignment.LAWFUL) != true
					&& instance.alignment.contains(Actor.Alignment.CHAOTIC) != true
					&& instance.alignment.contains(Actor.Alignment.GOOD) != true
					&& instance.alignment.contains(Actor.Alignment.EVIL) != true)
				array[4].add(instance);
		}

		String[] ali = new String[] { "Lawful Good", "Lawful Neutral", "Lawful Evil", "Neutral Good", "True Neutral",
				"Neutral Evil", "Chaotic Good", "Chaotic Neutral", "Chaotic Evil" };
		String string = "";
		for (int i = 0; i < array.length; ++i) {
			string += String.format("%16s (%4d): %s%n", ali[i], array[i].size(), array[i].toString());
		}

		return string;
	}

	public static String domainStatsToString() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		Instance instance;
		for (Iterator<Instance> it = Instance.instances.iterator(); it.hasNext();) {
			instance = it.next();

			if (instance.domains.contains(Domain.DEATH))
				++array[0];

			if (instance.domains.contains(Domain.KNOWLEDGE))
				++array[1];

			if (instance.domains.contains(Domain.LIFE))
				++array[2];

			if (instance.domains.contains(Domain.LIGHT))
				++array[3];

			if (instance.domains.contains(Domain.NATURE))
				++array[4];

			if (instance.domains.contains(Domain.TEMPEST))
				++array[5];

			if (instance.domains.contains(Domain.TRICKERY))
				++array[6];

			if (instance.domains.contains(Domain.WAR))
				++array[7];
		}

		String[] domains = { "Death", "Knowledge", "Life", "Light", "Nature", "Tempest", "Trickery", "War" };
		String string = "";

		int total = Instance.instances.size();
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

	private static Actor.Alignment[] alignmentRandomizer(Actor.Alignment axis) {
		Actor.Alignment[][] axes = new Actor.Alignment[][] {
				// LAWFUL
				{ Actor.Alignment.LAWFUL, Actor.Alignment.NEUTRAL }, { Actor.Alignment.LAWFUL, Actor.Alignment.GOOD },
				{ Actor.Alignment.LAWFUL, Actor.Alignment.EVIL },
				// CHAOTIC
				{ Actor.Alignment.CHAOTIC, Actor.Alignment.NEUTRAL }, { Actor.Alignment.CHAOTIC, Actor.Alignment.GOOD },
				{ Actor.Alignment.CHAOTIC, Actor.Alignment.EVIL },
				// GOOD
				{ Actor.Alignment.GOOD, Actor.Alignment.NEUTRAL }, { Actor.Alignment.GOOD, Actor.Alignment.CHAOTIC },
				{ Actor.Alignment.GOOD, Actor.Alignment.LAWFUL },
				// EVIL
				{ Actor.Alignment.EVIL, Actor.Alignment.NEUTRAL }, { Actor.Alignment.EVIL, Actor.Alignment.CHAOTIC },
				{ Actor.Alignment.EVIL, Actor.Alignment.LAWFUL } };

				Actor.Alignment[] corners = new Actor.Alignment[] { Actor.Alignment.LAWFUL, Actor.Alignment.CHAOTIC, Actor.Alignment.GOOD, Actor.Alignment.EVIL };

		//
				Actor.Alignment[] alignment = null;
		int dice = Dice.roll(10);
		if (axis.equals(Actor.Alignment.LAWFUL)) {
			alignment = (dice < 5) ? axes[0] : (dice < 8) ? axes[1] : axes[2];
		} else if (axis.equals(Actor.Alignment.CHAOTIC)) {
			alignment = (dice < 5) ? axes[3] : (dice < 8) ? axes[4] : axes[5];
		} else if (axis.equals(Actor.Alignment.GOOD)) {
			alignment = (dice < 5) ? axes[6] : (dice < 8) ? axes[7] : axes[8];
		} else if (axis.equals(Actor.Alignment.EVIL)) {
			// System.out.println("Evil entity generated.");
			alignment = (dice < 5) ? axes[9] : (dice < 8) ? axes[10] : axes[11];
		} else if (dice < 5) {
			alignment = new Actor.Alignment[] { Actor.Alignment.NEUTRAL };
		} else {
			alignment = new Actor.Alignment[] { Actor.Alignment.NEUTRAL, Dice.randomFromArray(corners) };
		}

		return alignment;
	}

	private static Domain[] domainRandomizer(Domain domain) {
		// nature
		Domain[] earthDomains = new Domain[] { Domain.LIFE, Domain.TRICKERY, Domain.DEATH };
		// tempest
		Domain[] seaDomains = new Domain[] { Domain.NATURE, Domain.LIFE, Domain.WAR };
		// light
		Domain[] skyDomains = new Domain[] { Domain.TEMPEST, Domain.KNOWLEDGE, Domain.WAR };
		// war
		Domain[] warDomains = new Domain[] { Domain.DEATH, Domain.TRICKERY, Domain.TEMPEST };

		// death
		Domain[] deathDomains = new Domain[] { Domain.TRICKERY, Domain.TEMPEST, Domain.WAR };
		// life
		Domain[] lifeDomains = new Domain[] { Domain.NATURE, Domain.TRICKERY, Domain.WAR };
		// knowledge "tutelary"
		Domain[] tutelary = new Domain[] { Domain.LIFE, Domain.TRICKERY, Domain.LIGHT };
		// trickery
		Domain[] trickery = new Domain[] { Domain.KNOWLEDGE, Domain.NATURE, Domain.LIFE };

		Domain[] array = null;
		Domain[] domains = null;

		if (domain.equals(Domain.DEATH))
			domains = deathDomains;
		else if (domain.equals(Domain.KNOWLEDGE))
			domains = tutelary;
		else if (domain.equals(Domain.LIFE))
			domains = lifeDomains;
		else if (domain.equals(Domain.LIGHT))
			domains = skyDomains;
		else if (domain.equals(Domain.NATURE))
			domains = earthDomains;
		else if (domain.equals(Domain.TEMPEST))
			domains = seaDomains;
		else if (domain.equals(Domain.TRICKERY))
			domains = trickery;
		else if (domain.equals(Domain.WAR))
			domains = warDomains;

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
			array[0] = domain;
		} else if (array.length == 1 && (dice == 5 || dice == 6 || dice == 7)) {
			array[0] = (Dice.roll(2) == 1) ? domain : domains[0];
		} else if (array.length == 1 && (dice == 8 || dice == 9)) {
			dice = Dice.roll(3);
			array[0] = (dice == 1) ? domains[0] : (dice == 2) ? domains[1] : domains[2];
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

}
