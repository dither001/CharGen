
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
		 * DEMIGODS are explicitly "lesser gods" like elevated heroes and whatnot
		 */
		DEMIGOD,
		/*
		 * ANGELS are "lawful" servants of the gods, and they are led by a group of
		 * powerful angels like a council of sorts
		 */
		ANGEL,
		/*
		 * DEMONS are "chaotic" beings, corrupted by evil and wholly destructive; they
		 * are led by powerful "demon princes"
		 */
		DEMON,
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
		 * ABOMINATIONS are a bizarre class of "created" beings that lack a formal
		 * definition or anything list consistent depiction
		 */
		ABOMINATION,
		/*
		 * India: DEVA/SURA, ASURA, YAKSHA/BHUTA, RAKSHASA
		 */
		ASURA, DEVA, YAKSHA, RAKSHASA,
		/*
		 * Middle east: JINNI (plural djinn)
		 */
		JINNI,
		/*
		 * Greek/Hellenic
		 */
		TITAN, OLYMPIAN,
		/*
		 * Norse/Germanic: JOTUNN, NORNS (include "fylgjas," "hamingjas," and valkyries)
		 * are always female, known to control the fates of men and gods)
		 */
		AESIR, VANIR, JOTUNN, NORN,
		/*
		 * Scots/Celt/Irish: SIDHE (alt. Scottish "Sith") are "chaotic" and led by
		 * powerful entities such as OBERON and TITANIA
		 */
		SIDHE,
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
	/*
	 * ELEMENTALS include a variety of creatures like the powerful SHAITAN, DJINN,
	 * GENIES, JANN, and a host of lesser creatures
	 */
	// ELEMENTAL,

}
