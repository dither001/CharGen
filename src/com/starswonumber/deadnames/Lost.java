package com.starswonumber.deadnames;

import com.norvendae.misc.Dice;

public enum Lost {
	TRANSHUMAN, EXTRATERRESTRIAL, SYNTHETIC, TRANSDIMENSIONAL;

	/*
	 * STATIC METHODS
	 */
	public static Lost randomLost() {
		Lost lost = null;

		int dice = Dice.roll(10);
		if (dice <= 4)
			lost = TRANSHUMAN;
		else if (dice <= 7)
			lost = EXTRATERRESTRIAL;
		else if (dice <= 9)
			lost = SYNTHETIC;
		else
			lost = TRANSDIMENSIONAL;

		return lost;
	}
}
