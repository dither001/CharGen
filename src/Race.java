
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
			race = GNOME;
		} else if (dice < 83) {
			race = HALF_ELF;
		} else if (dice < 89) {
			race = HALF_ORC;
		} else if (dice < 95) {
			race = HALFLING;
		} else {
			race = TIEFLING;
		}
		
		return race;
	}
	
	public static void applyRacialBonus(Actor actor, Race race) {
		if (race.equals(HUMAN)) {
			actor.setStrength(actor.getStrength() + 1);
			actor.setDexterity(actor.getDexterity() + 1);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(DRAGONBORN)) {
			actor.setStrength(actor.getStrength() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		} else if (race.equals(DWARF)) {
			actor.setConstitution(actor.getConstitution() + 2);
			actor.setStrength(actor.getStrength() + 1);
			actor.setWisdom(actor.getWisdom() + 1);
		} else if (race.equals(HIGH_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setIntelligence(actor.getIntelligence() + 1);
		} else if (race.equals(WOOD_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
		} else if (race.equals(DARK_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(HALFLING)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setWisdom(actor.getWisdom() + 1);
			actor.setCharisma(actor.getCharisma() + 1);
		} else if (race.equals(GNOME)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setConstitution(actor.getConstitution() + 1);
			actor.setIntelligence(actor.getIntelligence() + 1);
		} else if (race.equals(HALF_ELF)) {
			actor.setDexterity(actor.getDexterity() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		} else if (race.equals(HALF_ORC)) {
			actor.setStrength(actor.getStrength() + 2);
			actor.setConstitution(actor.getConstitution() + 2);
		} else if (race.equals(TIEFLING)) {
			actor.setIntelligence(actor.getIntelligence() + 2);
			actor.setCharisma(actor.getCharisma() + 2);
		}
	}
}
