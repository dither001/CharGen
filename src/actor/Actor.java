package actor;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import com.norvendae.rules.dnd5e.*;

//

public interface Actor {
	/*
	 * INSTANCE METHODS
	 * 
	 */
	public String toStringDetailed();

	public Size creatureSize();

	public CreatureType creatureType();

	public Alignment alignment();

	public byte[] getHitDice();

	public int getLevel();

	public void setLevel(int level);

	public int getExperience();

	public void setExp(int exp);

	public EnumSet<MovementType> getSpeed();

	public byte[] getAbilityScores();

	public byte[] getAbilityCeiling();

	public byte[] getSavingThrows();

	public EnumSet<Energy> getResistance();

	public EnumSet<Energy> getImmunity();

	public EnumSet<Condition> getConditionImmunity();

	public EnumSet<Condition> getConditions();

	public EnumSet<Sense> getSenses();

	/*
	 * DEFAULT METHODS
	 * 
	 */
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

//	public default Set<Weapon.Instance> getWeapons() {
//		return getInventory().getWeapons();
//	}
//
//	public default Weapon.Instance getHeldWeapon() {
//		return getInventory().getMainHand();
//	}
//
//	public default boolean wearingArmor() {
//		return (getInventory().wearingArmor());
//	}
//
//	public default boolean notWearingArmor() {
//		return (getInventory().wearingArmor() != true);
//	}
//
//	public default boolean usingShield() {
//		return (getInventory().usingShield());
//	}
//
//	public default boolean notUsingShield() {
//		return (getInventory().usingShield() != true);
//	}
//
//	public default int getArmorClass() {
//		return combat().getArmorClass();
//	}
//
//	public default int getHitPoints() {
//		return combat().getHitPoints();
//	}
//
//	public default Combat.AttackMode getPreferredAttackType() {
//		return combat().getPreferredAttackType();
//	}
//
//	public default int getAttackBonus() {
//		return combat().getAttackBonus();
//	}
//
//	public default int getAverageDamage() {
//		return combat().getAverageDamage();
//	}
//
//	public default int getHighestSpellDamage() {
//		Spell s = Spell.highestDamagingSpell(getSpellsKnown());
//
//		return Spell.getAverageDamage(s);
//	}
//
//	public default int getChallengeRating() {
//		return combat().getChallengeRating();
//	}

}
