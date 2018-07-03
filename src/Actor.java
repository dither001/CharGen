import java.util.Collection;
import java.util.EnumSet;

public interface Actor {
	public enum Ability {
		STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA
	}

	public enum Size {
		TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
	}

	public enum Creature {
		ABERRATION, BEAST, CELESTIAL, CONSTRUCT, DRAGON, ELEMENTAL, FAERIE, FIEND, GIANT, HUMAN, MONSTER, OOZE, PLANT, UNDEAD
	}

	public enum Alignment {
		LAWFUL, GOOD, NEUTRAL, CHAOTIC, EVIL;

		public static Alignment random() {
			Alignment choice;

			int dice = Dice.roll(100);
			if (dice < 16) {
				// law represents (15%)
				choice = LAWFUL;
			} else if (dice < 31) {
				// good represents (15%)
				choice = GOOD;
			} else if (dice < 46) {
				// neutral represents (15%)
				choice = NEUTRAL;
			} else if (dice < 86) {
				// evil represents majority (40%)
				choice = EVIL;
			} else {
				// chaos represents (15%)
				choice = CHAOTIC;
			}

			return choice;
		}

	}

	public enum Speed {
		WALK, BURROW, CLIMB, FLY, SWIM
	}

	public enum Condition {
		BLINDED, CHARMED, DEAFENED, FRIGHTENED, GRAPPLED, INCAPACITATED, INVISIBLE, PARALYZED, PETRIFIED, POISONED, PRONE, RESTRAINED, STUNNED, UNCONSCIOUS
	}

	public enum Sense {
		SIGHT, HEARING, SCENT, TOUCH, TASTE, BLINDSIGHT, DARKVISION, TREMORSENSE, TRUESIGHT
	}

	public enum Language {
		COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING, ORCISH, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN, UNDERCOMMON, SUPERNAL, DRUIDIC, THIEVES_CANT;

		private static final Language[] COMMON_LANGUAGES = { COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING,
				ORCISH };
		private static final Language[] EXOTIC_LANGUAGES = { ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL,
				PRIMORDIAL, SYLVAN, UNDERCOMMON };

		public static Language randomCommonLanguage() {
			return Dice.randomFromArray(COMMON_LANGUAGES);
		}

		public static Language randomExoticLanguage() {
			return Dice.randomFromArray(EXOTIC_LANGUAGES);
		}
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public String toStringDetailed();

	public boolean polymorphed();

	public boolean magicUser();

	public boolean weaponUser();

	public boolean armorUser();

	public Size getSize();

	public Creature getCreatureType();

	public Alignment getAlignment();

	public Deity getDeity();

	public byte[] getHitDice();

	public Class getJob();

	public Class.Subclass getArchetype();

	public Race getRace();

	public Career getCareer();

	public int getLevel();

	public void setLevel(int level);

	public int getExp();

	public void setExp(int exp);

	public EnumSet<Speed> getSpeed();

	public byte[] getAbilities();

	public int getProficiencyBonus();

	public byte[] getSavingThrows();

	public EnumSet<Skill> getSkills();

	public void setSkills(EnumSet<Skill> skills);

	public EnumSet<Energy> getResistance();

	public EnumSet<Energy> getImmunity();

	public EnumSet<Condition> getConditionImmunity();

	public EnumSet<Condition> getConditions();

	public EnumSet<Sense> getSenses();

	public EnumSet<Language> getLanguages();

	public int getChallengeRating();

	/*
	 * DEFAULT METHODS
	 * 
	 */
	public default boolean hasCondition(Condition condition) {
		return getConditions().contains(condition);
	}

	public default boolean hasAllConditions(Collection<Condition> c) {
		return getConditions().containsAll(c);
	}

	public default int getStrength() {
		return getAbilities()[0];
	}

	public default int getDexterity() {
		return getAbilities()[1];
	}

	public default int getConstitution() {
		return getAbilities()[2];
	}

	public default int getIntelligence() {
		return getAbilities()[3];
	}

	public default int getWisdom() {
		return getAbilities()[4];
	}

	public default int getCharisma() {
		return getAbilities()[5];
	}

	public default void setStrength(int bonus) {
		int ability = getAbilities()[0];
		getAbilities()[0] = (byte) (ability + bonus);
	}

	public default void setDexterity(int bonus) {
		int ability = getAbilities()[1];
		getAbilities()[1] = (byte) (ability + bonus);
	}

	public default void setConstitution(int bonus) {
		int ability = getAbilities()[2];
		getAbilities()[2] = (byte) (ability + bonus);
	}

	public default void setIntelligence(int bonus) {
		int ability = getAbilities()[3];
		getAbilities()[3] = (byte) (ability + bonus);
	}

	public default void setWisdom(int bonus) {
		int ability = getAbilities()[4];
		getAbilities()[4] = (byte) (ability + bonus);
	}

	public default void setCharisma(int bonus) {
		int ability = getAbilities()[5];
		getAbilities()[5] = (byte) (ability + bonus);
	}

	public default int getStrMod() {
		int ability = getAbilities()[0];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getDexMod() {
		int ability = getAbilities()[1];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getConMod() {
		int ability = getAbilities()[2];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getIntMod() {
		int ability = getAbilities()[3];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getWisMod() {
		int ability = getAbilities()[4];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getChaMod() {
		int ability = getAbilities()[5];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getStrSave() {
		return getSavingThrows()[0];
	}

	public default int getDexSave() {
		return getSavingThrows()[1];
	}

	public default int getConSave() {
		return getSavingThrows()[2];
	}

	public default int getIntSave() {
		return getSavingThrows()[3];
	}

	public default int getWisSave() {
		return getSavingThrows()[4];
	}

	public default int getChaSave() {
		return getSavingThrows()[5];
	}

}
