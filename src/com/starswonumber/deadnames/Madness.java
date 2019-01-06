package com.starswonumber.deadnames;

public enum Madness {
	ABSTENTION, ANGUISH, ARETE, BEAUTY, CONNECTION, CONSTRUCTION, CONVERSION, CORRUPTION, DECEIT, DEGRADATION, DISASSEMBLY, ESCAPE, ESOTERICA, EXCLUSION, FORGETTING, HATRED, HEALING, HUNGER, INVERSION, KNOWING, LUDDISM, MEMORIAL, NIHILISM, PLEASURE, PROTECTION, REPRODUCTION, RULE, SACRIFICE, SCHISM, SELFISHNESS, SERVITUDE, SHAME, STASIS, SURVIVAL, TORMENT, UNITY;

	private static final Madness[] MADNESSES = { ABSTENTION, ANGUISH, ARETE, BEAUTY, CONNECTION, CONSTRUCTION,
			CONVERSION, CORRUPTION, DECEIT, DEGRADATION, DISASSEMBLY, ESCAPE, ESOTERICA, EXCLUSION, FORGETTING, HATRED,
			HEALING, HUNGER, INVERSION, KNOWING, LUDDISM, MEMORIAL, NIHILISM, PLEASURE, PROTECTION, REPRODUCTION, RULE,
			SACRIFICE, SCHISM, SELFISHNESS, SERVITUDE, SHAME, STASIS, SURVIVAL, TORMENT, UNITY };

	/*
	 * STATIC METHODS
	 */
	public static Madness[] madnesses() {
		return MADNESSES;
	}

}
