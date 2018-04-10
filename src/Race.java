
public enum Race {
	HUMAN, DRAGONBORN, DWARF, HIGH_ELF, WOOD_ELF, DARK_ELF, GNOME, HALFLING, HALF_ELF, HALF_ORC, TIEFLING; 

	// methods
	public static Race selectRace() {
		Race race;
		int dice = Dice.roll(100);
		
		if (dice < 41) {
			race = HUMAN;
		} else if (dice < 47) {
			race = DRAGONBORN;
		} else if (dice < 53) {
			race = DWARF;
		} else if (dice < 59) {
			race = HIGH_ELF;
		} else if (dice < 65) {
			race = WOOD_ELF;
		} else if (dice < 71) {
			race = DARK_ELF;
		} else if (dice < 77) {
			race = DARK_ELF;
		} else if (dice < 83) {
			race = GNOME;
		} else if (dice < 89) {
			race = HALF_ELF;
		} else if (dice < 95) {
			race = HALF_ORC;
		} else {
			race = TIEFLING;
		}
		
		return race;
	}
	
	public static AbilityArray applyRacialBonus(AbilityArray abilities, Race race) {
		if (race.equals(HUMAN)) {
			abilities.setSTR(abilities.getSTR() + 1);
			abilities.setDEX(abilities.getDEX() + 1);
			abilities.setCON(abilities.getCON() + 1);
			abilities.setINT(abilities.getINT() + 1);
			abilities.setWIS(abilities.getWIS() + 1);
			abilities.setCHA(abilities.getCHA() + 1);
		} else if (race.equals(DRAGONBORN)) {
			abilities.setSTR(abilities.getSTR() + 2);
			abilities.setCHA(abilities.getCHA() + 2);
		} else if (race.equals(DWARF)) {
			abilities.setCON(abilities.getCON() + 2);
			abilities.setSTR(abilities.getSTR() + 1);
			abilities.setWIS(abilities.getWIS() + 1);
		} else if (race.equals(HIGH_ELF)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setINT(abilities.getINT() + 1);
		} else if (race.equals(WOOD_ELF)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setWIS(abilities.getWIS() + 1);
		} else if (race.equals(DARK_ELF)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setCHA(abilities.getCHA() + 1);
		} else if (race.equals(HALFLING)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setWIS(abilities.getWIS() + 1);
			abilities.setCHA(abilities.getCHA() + 1);
		} else if (race.equals(GNOME)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setCON(abilities.getCON() + 1);
			abilities.setINT(abilities.getINT() + 1);
		} else if (race.equals(HALF_ELF)) {
			abilities.setDEX(abilities.getDEX() + 2);
			abilities.setCHA(abilities.getCHA() + 2);
		} else if (race.equals(HALF_ORC)) {
			abilities.setSTR(abilities.getSTR() + 2);
			abilities.setCON(abilities.getCON() + 2);
		} else if (race.equals(TIEFLING)) {
			abilities.setINT(abilities.getINT() + 2);
			abilities.setCHA(abilities.getCHA() + 2);
		}
		
		return abilities;
	}
}
