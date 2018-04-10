
/* 
 * TODO - create enumerated set of abilities and revise class to use EnumSet
 * that assigns a value to each ability in the Set. Create a getter that
 * accepts an enum as a parameter and returns the corresponding value.
 */

public class AbilityArray {
	// fields
	private final int NUMBER_OF_ABILITIES = 6;
	private byte[] abilities;

	// constructors
	public AbilityArray() {
		abilities = new byte[NUMBER_OF_ABILITIES];
		
		for (int i = 0; i < NUMBER_OF_ABILITIES; ++i) {
			abilities[i] = (byte) Dice.roll(3, 6);
		}
	}

	// methods
	public int setSTR(int val) {return abilities[0] = (byte) val;}	
	public int setDEX(int val) {return abilities[1] = (byte) val;}
	public int setCON(int val) {return abilities[2] = (byte) val;}
	public int setINT(int val) {return abilities[3] = (byte) val;}
	public int setWIS(int val) {return abilities[4] = (byte) val;}
	public int setCHA(int val) {return abilities[5] = (byte) val;}

	public int getSTR() {return abilities[0];}
	public int getDEX() {return abilities[1];}
	public int getCON() {return abilities[2];}
	public int getINT() {return abilities[3];}
	public int getWIS() {return abilities[4];}
	public int getCHA() {return abilities[5];}

	public int getSTRMod() {return (abilities[0] > 9) ? (abilities[0] - 10) / 2 : (abilities[0] - 11) / 2;} 
	public int getDEXMod() {return (abilities[1] > 9) ? (abilities[1] - 10) / 2 : (abilities[1] - 11) / 2;} 
	public int getCONMod() {return (abilities[2] > 9) ? (abilities[2] - 10) / 2 : (abilities[2] - 11) / 2;} 
	public int getINTMod() {return (abilities[3] > 9) ? (abilities[3] - 10) / 2 : (abilities[3] - 11) / 2;} 
	public int getWISMod() {return (abilities[4] > 9) ? (abilities[4] - 10) / 2 : (abilities[4] - 11) / 2;} 
	public int getCHAMod() {return (abilities[5] > 9) ? (abilities[5] - 10) / 2 : (abilities[5] - 11) / 2;} 
	 
}
