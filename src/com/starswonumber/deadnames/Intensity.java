package com.starswonumber.deadnames;

public enum Intensity {
	DECAYING, FRESH, HIDDEN, MANIACAL, REASONED, RUINOUS, STRONG, TIC, TRIGGERED, VARIABLE;

	private static final Intensity[] INTENSITIES = { DECAYING, FRESH, HIDDEN, MANIACAL, REASONED, RUINOUS, STRONG, TIC,
			TRIGGERED, VARIABLE };

	/*
	 * STATIC METHODS
	 */
	public static Intensity[] intensities() {
		return INTENSITIES;
	}
}
