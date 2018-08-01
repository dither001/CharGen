import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public interface Actor {
	public enum Ability {
		STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA
	}

	public enum Size {
		TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
	}

	public enum Creature {
		ABERRATION, BEAST, CELESTIAL, CONSTRUCT, DRAGON, ELEMENTAL, FAERIE, FIEND, GIANT, HUMANOID, MONSTROSITY, OOZE, PLANT, UNDEAD
	}

	public enum Alignment {
		LAWFUL, GOOD, NEUTRAL, CHAOTIC, EVIL;

		public static Alignment random() {
			// lawful (15%), good (15%, neutral (15%), evil (40%), chaotic (15%)
			int[] array = new int[] { 15, 15, 15, 40, 15 };
			Alignment choice;

			int dice = Dice.roll(100);
			if (dice < 1 + array[0])
				choice = LAWFUL;
			else if (dice < 1 + array[0] + array[1])
				choice = GOOD;
			else if (dice < 1 + array[0] + array[1] + array[2])
				choice = NEUTRAL;
			else if (dice < 1 + array[0] + array[1] + array[2] + array[3])
				choice = EVIL;
			else
				choice = CHAOTIC;

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

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public String toStringDetailed();

	public Size creatureSize();

	public Creature creatureType();

	public Alignment alignment();

	public Deity getDeity();

	public byte[] getHitDice();

	public EnumSet<Option.Feature> getFeatures();

	public void setFeatures(EnumSet<Option.Feature> features);

	public Class getJob();

	public Class.Subclass getArchetype();

	public Race getRace();
	
	public void setRace(Race race);

	public EnumSet<Spell> getSpellsKnown();

	public void setSpellsKnown(EnumSet<Spell> spellsKnown);

	public Career.Profile getCareer();

	public int getLevel();

	public void setLevel(int level);

	public int getExperience();

	public void setExp(int exp);

	public EnumSet<Speed> getSpeed();

	public byte[] getAbilityScores();

	public byte[] getAbilityCeiling();

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

	public EnumSet<Race.Language> getLanguages();

	public void setLanguages(EnumSet<Race.Language> languages);

	public Combat combat();

	public void setCombat(Combat combat);

	public Inventory getInventory();

	public void setInventory(Inventory inventory);

	/*
	 * DEFAULT METHODS
	 * 
	 */
	public default void advance() {
		int[] requires = { 0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000,
				165000, 195000, 225000, 265000, 305000, 355000 };
		int currentLevel = getLevel(), currentEXP = getExperience();

		boolean advanced = false;
		if (currentLevel < 20 && currentEXP >= requires[currentLevel]) {
			setLevel(currentLevel + 1);
			advanced = true;

			if (currentLevel < 19 && currentEXP >= requires[currentLevel + 1])
				currentEXP = requires[currentLevel + 1] - 1;
		}

		if (advanced) {
			// TODO
			Class.updateClassFeatures(this);
			combat().update();

		}
	}

	public default int proficiency() {
		int bonus, level = getLevel();
		if (level > 16)
			bonus = 6;
		else if (level > 12)
			bonus = 5;
		else if (level > 8)
			bonus = 4;
		else if (level > 4)
			bonus = 3;
		else
			bonus = 2;

		return bonus;
	}

	public default boolean hasCondition(Condition condition) {
		return getConditions().contains(condition);
	}

	public default boolean hasAllConditions(Collection<Condition> c) {
		return getConditions().containsAll(c);
	}

	public default boolean hasJob(Class job) {
		boolean hasJob = false;
		if (getJob() != null && getJob().equals(job))
			hasJob = true;

		return hasJob;
	}

	public default boolean isBarbarian() {
		Class job = Class.BARBARIAN;

		return hasJob(job);
	}

	public default boolean isBard() {
		Class job = Class.BARD;

		return hasJob(job);
	}

	public default boolean isCleric() {
		Class job = Class.CLERIC;

		return hasJob(job);
	}

	public default boolean isDruid() {
		Class job = Class.DRUID;

		return hasJob(job);
	}

	public default boolean isFighter() {
		Class job = Class.FIGHTER;

		return hasJob(job);
	}

	public default boolean isMonk() {
		Class job = Class.MONK;

		return hasJob(job);
	}

	public default boolean isPaladin() {
		Class job = Class.PALADIN;

		return hasJob(job);
	}

	public default boolean isRanger() {
		Class job = Class.RANGER;

		return hasJob(job);
	}

	public default boolean isRogue() {
		Class job = Class.ROGUE;

		return hasJob(job);
	}

	public default boolean isSorcerer() {
		Class job = Class.SORCERER;

		return hasJob(job);
	}

	public default boolean isWarlock() {
		Class job = Class.WARLOCK;

		return hasJob(job);
	}

	public default boolean isWizard() {
		Class job = Class.WIZARD;

		return hasJob(job);
	}

	public default int getStrength() {
		return getAbilityScores()[0];
	}

	public default int getDexterity() {
		return getAbilityScores()[1];
	}

	public default int getConstitution() {
		return getAbilityScores()[2];
	}

	public default int getIntelligence() {
		return getAbilityScores()[3];
	}

	public default int getWisdom() {
		return getAbilityScores()[4];
	}

	public default int getCharisma() {
		return getAbilityScores()[5];
	}

	public default boolean setAbilityScore(int index, int bonus) {
		boolean set = false;
		int ability = getAbilityScores()[index], ceiling = getAbilityCeiling()[index];

		if (ability + bonus <= ceiling) {
			getAbilityScores()[index] = (byte) (ability + bonus);
			set = true;
		}

		return set;
	}

	public default boolean setStrength(int bonus) {
		return setAbilityScore(0, bonus);
	}

	public default boolean setDexterity(int bonus) {
		return setAbilityScore(1, bonus);
	}

	public default boolean setConstitution(int bonus) {
		return setAbilityScore(2, bonus);
	}

	public default boolean setIntelligence(int bonus) {
		return setAbilityScore(3, bonus);
	}

	public default boolean setWisdom(int bonus) {
		return setAbilityScore(4, bonus);
	}

	public default boolean setCharisma(int bonus) {
		return setAbilityScore(5, bonus);
	}

	public default boolean raiseAbilityMaximum(int index, int bonus) {
		boolean increased = false;
		int hardCap = 30, ceiling = getAbilityCeiling()[index];

		if (ceiling + bonus <= hardCap) {
			getAbilityCeiling()[index] = (byte) (ceiling + bonus);
			increased = true;
		}

		return increased;
	}

	public default boolean raiseMaxStrength(int bonus) {
		return raiseAbilityMaximum(0, bonus);
	}

	public default boolean raiseMaxDexterity(int bonus) {
		return raiseAbilityMaximum(1, bonus);
	}

	public default boolean raiseMaxConstitution(int bonus) {
		return raiseAbilityMaximum(2, bonus);
	}

	public default boolean raiseMaxIntelligence(int bonus) {
		return raiseAbilityMaximum(3, bonus);
	}

	public default boolean raiseMaxWisdom(int bonus) {
		return raiseAbilityMaximum(4, bonus);
	}

	public default boolean raiseMaxCharisma(int bonus) {
		return raiseAbilityMaximum(5, bonus);
	}

	public default int getAbilityModifier(int index) {
		int ability = getAbilityScores()[index];
		return (ability > 9) ? (ability - 10) / 2 : (ability - 11) / 2;
	}

	public default int getStrengthModifier() {
		return getAbilityModifier(0);
	}

	public default int getDexterityModifier() {
		return getAbilityModifier(1);
	}

	public default int getConstitutionModifier() {
		return getAbilityModifier(2);
	}

	public default int getIntelligenceModifier() {
		return getAbilityModifier(3);
	}

	public default int getWisdomModifier() {
		return getAbilityModifier(4);
	}

	public default int getCharismaModifier() {
		return getAbilityModifier(5);
	}

	public default int getSavingThrow(int index) {
		return getSavingThrows()[index];
	}

	public default int getStrengthSave() {
		return getSavingThrow(0);
	}

	public default int getDexteritySave() {
		return getSavingThrow(1);
	}

	public default int getConstitutionSave() {
		return getSavingThrow(2);
	}

	public default int getIntelligenceSave() {
		return getSavingThrow(3);
	}

	public default int getWisdomSave() {
		return getSavingThrow(4);
	}

	public default int getCharismaSave() {
		return getSavingThrow(5);
	}

	public default Set<Weapon.Instance> getWeapons() {
		return getInventory().getWeapons();
	}

	public default Weapon.Instance getHeldWeapon() {
		return getInventory().getMainHand();
	}

	public default boolean wearingArmor() {
		return (getInventory().wearingArmor());
	}

	public default boolean notWearingArmor() {
		return (getInventory().wearingArmor() != true);
	}

	public default boolean usingShield() {
		return (getInventory().usingShield());
	}

	public default boolean notUsingShield() {
		return (getInventory().usingShield() != true);
	}

	public default int getArmorClass() {
		return combat().getArmorClass();
	}

	public default int getHitPoints() {
		return combat().getHitPoints();
	}

	public default Combat.AttackMode getPreferredAttackType() {
		return combat().getPreferredAttackType();
	}

	public default int getAttackBonus() {
		return combat().getAttackBonus();
	}

	public default int getAverageDamage() {
		return combat().getAverageDamage();
	}

	public default int getHighestSpellDamage() {
		Spell s = Spell.highestDamagingSpell(getSpellsKnown());

		return Spell.getAverageDamage(s);
	}

	public default int getChallengeRating() {
		return combat().getChallengeRating();
	}

}
