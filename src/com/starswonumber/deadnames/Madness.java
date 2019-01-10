package com.starswonumber.deadnames;

public enum Madness {
	ABSTENTION, ANGUISH, ARETE, BEAUTY, CONNECTION, CONSTRUCTION, CONVERSION, CORRUPTION, DECEIT, DEGRADATION, DISASSEMBLY, ESCAPE, ESOTERICA, EXCLUSION, FORGETTING, HATRED, HEALING, HUNGER, INVERSION, KNOWING, LUDDISM, MEMORIAL, NIHILISM, PLEASURE, PROTECTION, REPRODUCTION, RULE, SACRIFICE, SCHISM, SELFISHNESS, SERVITUDE, SHAME, STASIS, SURVIVAL, TORMENT, UNITY;

	private static final Madness[] MADNESSES = { ABSTENTION, ANGUISH, ARETE, BEAUTY, CONNECTION, CONSTRUCTION,
			CONVERSION, CORRUPTION, DECEIT, DEGRADATION, DISASSEMBLY, ESCAPE, ESOTERICA, EXCLUSION, FORGETTING, HATRED,
			HEALING, HUNGER, INVERSION, KNOWING, LUDDISM, MEMORIAL, NIHILISM, PLEASURE, PROTECTION, REPRODUCTION, RULE,
			SACRIFICE, SCHISM, SELFISHNESS, SERVITUDE, SHAME, STASIS, SURVIVAL, TORMENT, UNITY };

	private static final String[] DESCRIPTIONS = { "Greatness is in poverty. To have and need nothing is best.",
			"Personal suffering is magnificence. Sought exotic forms of self-torment.",
			"Excellence in strength, wisdom, and beauty is the only worthy goal.",
			"All life is directed toward the creation of beauty in form, action, and meaning.",
			"Things must be connected--places, people, ideas, objects. All must be linked.",
			"They must build--structures, objects, social organizations.",
			"The world is to be shaped into a pattern more to their liking.",
			"Whatever is believed must be compromised. All virtues made hypocrisies.",
			"All things must be lies. The truth of anything must always be concealed.",
			"They alone are worthy sentience--all others must be brought low.",
			"They break things down into component parts--whether objects or creatures.",
			"They must escape this world they inhabit. All efforts are directed toward flight.",
			"They have a secret art or hidden wisdom--all effort is toward expressing it.",
			"All intrusions of the metadimensional or alien are an abomination.",
			"Some ideas must be buried and destroyed--any who know must be silenced.",
			"All else in the world is worthy only of unremitting hatred and violence.",
			"The world is forever broken and in need of repair--whether body or spirit.",
			"Consumption is life. Eat of the living, or the inanimate, or of ephemeral ideas.",
			"Roll again--their Madness is to stamp out that motive in all other creatures.",
			"They exist to learn--secrets or mundane truths. They interrogate the world.",
			"Only knowledge is pure. All artifice and technology must be eliminated.",
			"Living testament to another race or idea--must glorify and echo it.",
			"Existence is suffering. They must end all out of mercy for the living.",
			"There is no greater purpose than joy. Existence is focused on finding it.",
			"All must be defended from a thing, whether they can appreciate it or not.",
			"Their purpose is to create more of their kind with no regard for limits.",
			"The world must be rectified beneath their guidance, even if only to stand still.",
			"Life is what one expends on behalf of another--whether a person or cause.",
			"They hate each other, either individually or in factions--all is struggle.",
			"The only worthy goal is the exaltation of self, no matter the cost.",
			"They must obey, and the justification of existence is in perfect obedience.",
			"They are filth. They are monsters. They must accept their worthlessness.",
			"They must not change in any way. All must be the same, forever.",
			"All focus and vigor is turned to self-preservation and elimination of peril.",
			"Only in the pain of others is the self affirmed. Power lies in causing pain.",
			"Everything must be brought into perfect harmony with a greater purpose." };

	/*
	 * STATIC METHODS
	 */
	public static Madness[] madnesses() {
		return MADNESSES;
	}
	
	public static String[] descriptions() {
		return DESCRIPTIONS;
	}

	public static int getIndex(Madness madness) {
		int index = -1;
		
		switch (madness) {
		case ABSTENTION:
			index = 0;
			break;
		case ANGUISH:
			index = 1;
			break;
		case ARETE:
			index = 2;
			break;
		case BEAUTY:
			index = 3;
			break;
		case CONNECTION:
			index = 4;
			break;
		case CONSTRUCTION:
			index = 5;
			break;
		case CONVERSION:
			index = 6;
			break;
		case CORRUPTION:
			index = 7;
			break;
		case DECEIT:
			index = 8;
			break;
		case DEGRADATION:
			index = 9;
			break;
		case DISASSEMBLY:
			index = 10;
			break;
		case ESCAPE:
			index = 11;
			break;
		case ESOTERICA:
			index = 12;
			break;
		case EXCLUSION:
			index = 13;
			break;
		case FORGETTING:
			index = 14;
			break;
		case HATRED:
			index = 15;
			break;
		case HEALING:
			index = 16;
			break;
		case HUNGER:
			index = 17;
			break;
		case INVERSION:
			index = 18;
			break;
		case KNOWING:
			index = 19;
			break;
		case LUDDISM:
			index = 20;
			break;
		case MEMORIAL:
			index = 21;
			break;
		case NIHILISM:
			index = 22;
			break;
		case PLEASURE:
			index = 23;
			break;
		case PROTECTION:
			index = 24;
			break;
		case REPRODUCTION:
			index = 25;
			break;
		case RULE:
			index = 26;
			break;
		case SACRIFICE:
			index = 27;
			break;
		case SCHISM:
			index = 28;
			break;
		case SELFISHNESS:
			index = 29;
			break;
		case SERVITUDE:
			index = 30;
			break;
		case SHAME:
			index = 31;
			break;
		case STASIS:
			index = 32;
			break;
		case SURVIVAL:
			index = 33;
			break;
		case TORMENT:
			index = 34;
			break;
		case UNITY:
			index = 35;
			break;
		default:
			break;		
		}
		
		return index;
	}
}
