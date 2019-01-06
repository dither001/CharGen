package com.starswonumber.deadnames;

public enum Motivation {
	CREATED, DEFIANCE, DELUSION, HISTORICAL, IDEOLOGICAL, IMPUTED, INTRINSIC, RELICT, SUBSISTENCE, THEOLOGICAL;

	private static final Motivation[] MOTIVATIONS = { CREATED, DEFIANCE, DELUSION, HISTORICAL, IDEOLOGICAL, IMPUTED,
			INTRINSIC, RELICT, SUBSISTENCE, THEOLOGICAL };

	/*
	 * STATIC METHODS
	 */
	public static Motivation[] motivations() {
		return MOTIVATIONS;
	}
}
