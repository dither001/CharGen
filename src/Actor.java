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

		public static void setupLanguages(Actor actor) {
			EnumSet<Language> languages;
			if (actor.getLanguages() == null)
				languages = EnumSet.noneOf(Language.class);
			else
				languages = actor.getLanguages();
			int skillsToAdd = 0;

			Race race = actor.getRace();
			if (race.equals(Race.HUMAN)) {
				languages.add(COMMON);
				skillsToAdd = +1;

			} else if (race.equals(Race.DRAGONBORN)) {
				languages.add(COMMON);
				languages.add(DRACONIC);

			} else if (race.equals(Race.DWARF)) {
				languages.add(COMMON);
				languages.add(DWARVISH);

			} else if (race.equals(Race.DARK_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);

			} else if (race.equals(Race.HIGH_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);
				skillsToAdd = +1;

			} else if (race.equals(Race.WOOD_ELF)) {
				languages.add(COMMON);
				languages.add(ELVISH);

			} else if (race.equals(Race.HALFLING)) {
				languages.add(COMMON);
				languages.add(HALFLING);

			} else if (race.equals(Race.GNOME)) {
				languages.add(COMMON);
				languages.add(GNOMISH);

			} else if (race.equals(Race.HALF_ELF)) {
				languages.add(COMMON);
				skillsToAdd += 1;

			} else if (race.equals(Race.HALF_ORC)) {
				languages.add(COMMON);
				languages.add(ORCISH);

			} else if (race.equals(Race.TIEFLING)) {
				languages.add(COMMON);
				languages.add(INFERNAL);

			}

			Class job = actor.getJob();
			Class.Subclass archetype = actor.getArchetype();
			if (job.equals(Class.DRUID)) {
				languages.add(DRUIDIC);

			} else if (job.equals(Class.ROGUE)) {
				languages.add(THIEVES_CANT);

			} else if (archetype.equals(Class.Subclass.KNOWLEDGE)) {
				skillsToAdd += 2;

			} else if (archetype.equals(Class.Subclass.DRAGON_ORIGIN)) {
				Language[] array = new Language[] { DRACONIC };
				languages.addAll(
						(EnumSet<Language>) Dice.addToSetOrElseFromArray(1, array, languages, COMMON_LANGUAGES));

			}

			languages.addAll((EnumSet<Language>) Dice.addToSetFromArray(skillsToAdd, languages, COMMON_LANGUAGES));
			actor.setLanguages(languages);
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

	public Spellcasting getSpellCasting();

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

	public EnumSet<Armor> getArmorProficiency();

	public void setArmorProficiency(EnumSet<Armor> armor);

	public EnumSet<Weapon> getWeaponProficiency();

	public void setWeaponProficiency(EnumSet<Weapon> weapons);

	public EnumSet<Energy> getResistance();

	public EnumSet<Energy> getImmunity();

	public EnumSet<Condition> getConditionImmunity();

	public EnumSet<Condition> getConditions();

	public EnumSet<Sense> getSenses();

	public EnumSet<Language> getLanguages();

	public void setLanguages(EnumSet<Language> languages);

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
