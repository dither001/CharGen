
/* 
 * TODO - create enumerated set of abilities and revise class to use EnumSet
 * that assigns a value to each ability in the Set. Create a getter that
 * accepts an enum as a parameter and returns the corresponding value.
 */

/*
 * TODO - eventually this class will be used to create monsters of specific
 * ability score arrays for use in polymorph and other unforeseen things
 */

/*
 * TODO - I'm going to need to figure out how best to increase ability score
 * maximums without causing other problems. The normal limit is 20 and the
 * maximum upper limit is 30.
 */

import java.util.HashSet;

public class AbilityArray {
	// static fields
	private static final int NUMBER_OF_ABILITIES = 6;
	private static final byte[] DEFAULT_MAX_SCORE = { 20, 20, 20, 20, 20, 20 };
	private static final byte[] UPPER_LIMIT = { 30, 30, 30, 30, 30, 30 };

	// instance fields
	private Actor owner;
	private byte[] personalMax;
	private byte[] abilities;
	private byte[] originalScores;

	// constructors
	// public AbilityArray() {
	// abilities = new byte[NUMBER_OF_ABILITIES];
	// personalMax = new byte[NUMBER_OF_ABILITIES];
	//
	// for (int i = 0; i < NUMBER_OF_ABILITIES; ++i) {
	// abilities[i] = (byte) Dice.roll(3, 6);
	// personalMax[i] = 20;
	// }
	// }

	public AbilityArray(int... args) {
		this(null, args);
	}

	public AbilityArray(Actor owner, int... args) {
		this.owner = owner;
		abilities = new byte[NUMBER_OF_ABILITIES];
		originalScores = new byte[NUMBER_OF_ABILITIES];
		personalMax = new byte[NUMBER_OF_ABILITIES];

		for (int i = 0; i < NUMBER_OF_ABILITIES; ++i) {
			if (i < args.length) {
				abilities[i] = (args[i] < UPPER_LIMIT[i]) ? (byte) args[i] : UPPER_LIMIT[i];
			} else {
				abilities[i] = (byte) Dice.roll(3, 6);
			}

			originalScores[i] = abilities[i];
			personalMax[i] = (abilities[i] > 20) ? abilities[i] : 20;
		}
	}

	// methods
	public boolean hasOwner() {
		return owner != null;
	}

	public AbilityArray current() {
		int[] current = new int[NUMBER_OF_ABILITIES];

		current[0] = getSTR();
		current[1] = getDEX();
		current[2] = getCON();
		current[3] = getINT();
		current[4] = getWIS();
		current[5] = getCHA();

		return new AbilityArray(owner, current);
	}

	public int setSTR(int val) {
		return abilities[0] = (byte) val;
	}

	public int setDEX(int val) {
		return abilities[1] = (byte) val;
	}

	public int setCON(int val) {
		return abilities[2] = (byte) val;
	}

	public int setINT(int val) {
		return abilities[3] = (byte) val;
	}

	public int setWIS(int val) {
		return abilities[4] = (byte) val;
	}

	public int setCHA(int val) {
		return abilities[5] = (byte) val;
	}

	/*
	 * GETTERS
	 */
	public void updateScores() {
		// TODO - I don't know how retainAll works; I think it removes everything except
		// the desired things; if not, I'm going to have to find something else
		int strBonuses = 0, dexBonuses = 0, conBonuses = 0;
		int intBonuses = 0, wisBonuses = 0, chaBonuses = 0;
		if (hasOwner()) {
			HashSet<Feature> features;
			
			// STRENGTH
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getSTRBonuses());
			strBonuses = features.size() * 2;
			// DEXTERITY
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getDEXBonuses());
			dexBonuses = features.size() * 2;
			// CONSTITUTION
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getCONBonuses());
			conBonuses = features.size() * 2;
			// INTELLIGENCE
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getINTBonuses());
			intBonuses = features.size() * 2;
			// WISDOM
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getWISBonuses());
			wisBonuses = features.size() * 2;
			// CHARISMA
			features = new HashSet<Feature>(owner.getFeatures());
			features.retainAll(Feature.getCHABonuses());
			chaBonuses = features.size() * 2;
		}

		abilities[0] = (byte) (originalScores[0] + strBonuses);
		abilities[1] = (byte) (originalScores[1] + dexBonuses);
		abilities[2] = (byte) (originalScores[2] + conBonuses);
		abilities[3] = (byte) (originalScores[3] + intBonuses);
		abilities[4] = (byte) (originalScores[4] + wisBonuses);
		abilities[5] = (byte) (originalScores[5] + chaBonuses);
	}

	public int getSTR() {
		return abilities[0];
	}

	public int getDEX() {
		return abilities[1];
	}

	public int getCON() {
		return abilities[2];
	}

	public int getINT() {
		return abilities[3];
	}

	public int getWIS() {
		return abilities[4];
	}

	public int getCHA() {
		return abilities[5];
	}

	/*
	 * MAX SCORE GETTERS
	 */
	public int getMaxSTR() {
		return personalMax[0];
	}

	public int getMaxDEX() {
		return personalMax[1];
	}

	public int getMaxCON() {
		return personalMax[2];
	}

	public int getMaxINT() {
		return personalMax[3];
	}

	public int getMaxWIS() {
		return personalMax[4];
	}

	public int getMaxCHA() {
		return personalMax[5];
	}

	public int getSTRMod() {
		return (abilities[0] > 9) ? (abilities[0] - 10) / 2 : (abilities[0] - 11) / 2;
	}

	public int getDEXMod() {
		return (abilities[1] > 9) ? (abilities[1] - 10) / 2 : (abilities[1] - 11) / 2;
	}

	public int getCONMod() {
		return (abilities[2] > 9) ? (abilities[2] - 10) / 2 : (abilities[2] - 11) / 2;
	}

	public int getINTMod() {
		return (abilities[3] > 9) ? (abilities[3] - 10) / 2 : (abilities[3] - 11) / 2;
	}

	public int getWISMod() {
		return (abilities[4] > 9) ? (abilities[4] - 10) / 2 : (abilities[4] - 11) / 2;
	}

	public int getCHAMod() {
		return (abilities[5] > 9) ? (abilities[5] - 10) / 2 : (abilities[5] - 11) / 2;
	}

	@Override
	public String toString() {
		String string = "";
		int STR = abilities[0], DEX = abilities[1], CON = abilities[2];
		int INT = abilities[3], WIS = abilities[4], CHA = abilities[5];
		string = String.format("[%2d, %2d, %2d, %2d, %2d, %2d]", STR, DEX, CON, INT, WIS, CHA);

		return string;
	}
}