package com.dnd5e.gear;
import com.norvendae.misc.Dice;

public enum Tool {
	// professions
	ALCHEMIST, BREWER, CALLIGRAPHER, CARPENTER, CARTOGRAPHER, COBBLER, COOK, GLASSBLOWER, JEWELER, LEATHERWORKER, MASON, NAVIGATOR, PAINTER, POTTER, SMITH, TINKER, WEAVER, WOODCARVER,
	// tools & kits
	DISGUISE_KIT, FORGERY_KIT, CHESS_SET, GAMING_DICE, PLAYING_CARDS, HERBALISM_KIT, POISONER_KIT, THIEVES_TOOLS,
	// instruments
	BAGPIPES, DRUM, DULCIMER, FLUTE, LUTE, LYRE, HORN, PAN_FLUTE, SHAWM, VIOL,
	// vehicles
	CARRIAGE, CART, CHARIOT, SLED, WAGON, GALLEY, KEELBOAT, LONGSHIP, ROWBOAT, SAILING_SHIP, WARSHIP;

	// static methods
	public static Tool randomArtisanToolKit() {
		Tool[] array = { ALCHEMIST, BREWER, CALLIGRAPHER, CARPENTER, CARTOGRAPHER, COBBLER, COOK, GLASSBLOWER, JEWELER,
				LEATHERWORKER, MASON, PAINTER, POTTER, SMITH, TINKER, WEAVER, WOODCARVER };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}

	public static Tool randomGamingPieces() {
		Tool[] array = { CHESS_SET, GAMING_DICE, PLAYING_CARDS };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}

	public static Tool randomLandVehicle() {
		Tool[] array = { CARRIAGE, CART, CHARIOT, SLED, WAGON };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}

	public static Tool randomWaterVehicle() {
		Tool[] array = { GALLEY, KEELBOAT, LONGSHIP, ROWBOAT, SAILING_SHIP, WARSHIP };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}

	public static Tool randomMusicalInstrument() {
		Tool[] array = { BAGPIPES, DRUM, DULCIMER, FLUTE, LUTE, LYRE, HORN, PAN_FLUTE, SHAWM, VIOL };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}

	public static Tool randomToolKit() {
		Tool[] array = { DISGUISE_KIT, FORGERY_KIT, HERBALISM_KIT, NAVIGATOR, POISONER_KIT, THIEVES_TOOLS };
		Tool t = array[Dice.roll(array.length) - 1];

		return t;
	}
}
