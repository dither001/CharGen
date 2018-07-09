//import java.util.Arrays;
import java.util.EnumSet;

public enum Feature {

	/*
	 * STATIC METHODS
	 * 
	 */
//	public static int cantripCount(Actor actor) {
//		// TODO - add cantrips array for each class as they're added
//		Feature[] bardCantrips = { BARD_CANTRIPS_2, BARD_CANTRIPS_3, BARD_CANTRIPS_4 };
//		int count = 0;
//		Class job = actor.getJob();
//
//		EnumSet<Feature> list = EnumSet.copyOf(actor.getFeatures());
//
//		if (job.equals(Class.BARD)) {
//			list.retainAll(Arrays.asList(bardCantrips));
//			count = list.size() + 1;
//		}
//
//		return count;
//	}

	public static EnumSet<Feature> getRacialFeatures(Actor actor) {
		EnumSet<Feature> list = EnumSet.noneOf(Feature.class);
		// TODO

		return list;
	}


	public static EnumSet<Feature> addRandomExpertise(int toAdd, Actor actor) {
		EnumSet<Skill> skills = actor.getSkills();
		EnumSet<Feature> features = actor.getFeatures();

		int added = 0;
		Feature expertise;
		while (added < toAdd) {
			expertise = matchExpertiseToSkill(Dice.randomFromSet(skills));

			if (features.contains(expertise) != true) {
				features.add(expertise);
				++added;
			}
		}

		return features;
	}

	private static Feature matchExpertiseToSkill(Skill skill) {
		Feature expertise = EXPERTISE_ACROBATICS;
		if (skill.equals(Skill.ACROBATICS))
			expertise = EXPERTISE_ACROBATICS;
		else if (skill.equals(Skill.ANIMAL_HANDLING))
			expertise = EXPERTISE_ANIMAL_HANDLING;
		else if (skill.equals(Skill.ARCANA))
			expertise = EXPERTISE_ARCANA;
		else if (skill.equals(Skill.ATHLETICS))
			expertise = EXPERTISE_ATHLETICS;
		else if (skill.equals(Skill.DECEPTION))
			expertise = EXPERTISE_DECEPTION;
		else if (skill.equals(Skill.HISTORY))
			expertise = EXPERTISE_HISTORY;
		else if (skill.equals(Skill.INSIGHT))
			expertise = EXPERTISE_INSIGHT;
		else if (skill.equals(Skill.INTIMIDATION))
			expertise = EXPERTISE_INTIMIDATION;
		else if (skill.equals(Skill.INVESTIGATION))
			expertise = EXPERTISE_INVESTIGATION;
		else if (skill.equals(Skill.MEDICINE))
			expertise = EXPERTISE_MEDICINE;
		else if (skill.equals(Skill.NATURE))
			expertise = EXPERTISE_NATURE;
		else if (skill.equals(Skill.PERCEPTION))
			expertise = EXPERTISE_PERCEPTION;
		else if (skill.equals(Skill.PERFORMANCE))
			expertise = EXPERTISE_PERFORMANCE;
		else if (skill.equals(Skill.PERSUASION))
			expertise = EXPERTISE_PERSUASION;
		else if (skill.equals(Skill.RELIGION))
			expertise = EXPERTISE_RELIGION;
		else if (skill.equals(Skill.SLEIGHT_OF_HAND))
			expertise = EXPERTISE_SLEIGHT_OF_HAND;
		else if (skill.equals(Skill.STEALTH))
			expertise = EXPERTISE_STEALTH;
		else if (skill.equals(Skill.SURVIVAL))
			expertise = EXPERTISE_SURVIVAL;

		return expertise;
	}

	private static Skill matchSkillToExpertise(Feature feature) {
		Skill skill = Skill.ACROBATICS;
		if (feature.equals(EXPERTISE_ACROBATICS))
			skill = Skill.ACROBATICS;
		else if (feature.equals(EXPERTISE_ANIMAL_HANDLING))
			skill = Skill.ANIMAL_HANDLING;
		else if (feature.equals(EXPERTISE_ARCANA))
			skill = Skill.ARCANA;
		else if (feature.equals(EXPERTISE_ATHLETICS))
			skill = Skill.ATHLETICS;
		else if (feature.equals(EXPERTISE_DECEPTION))
			skill = Skill.DECEPTION;
		else if (feature.equals(EXPERTISE_HISTORY))
			skill = Skill.HISTORY;
		else if (feature.equals(EXPERTISE_INSIGHT))
			skill = Skill.INSIGHT;
		else if (feature.equals(EXPERTISE_INTIMIDATION))
			skill = Skill.INTIMIDATION;
		else if (feature.equals(EXPERTISE_INVESTIGATION))
			skill = Skill.INVESTIGATION;
		else if (feature.equals(EXPERTISE_MEDICINE))
			skill = Skill.MEDICINE;
		else if (feature.equals(EXPERTISE_NATURE))
			skill = Skill.NATURE;
		else if (feature.equals(EXPERTISE_PERCEPTION))
			skill = Skill.PERCEPTION;
		else if (feature.equals(EXPERTISE_PERFORMANCE))
			skill = Skill.PERFORMANCE;
		else if (feature.equals(EXPERTISE_PERSUASION))
			skill = Skill.PERSUASION;
		else if (feature.equals(EXPERTISE_RELIGION))
			skill = Skill.RELIGION;
		else if (feature.equals(EXPERTISE_SLEIGHT_OF_HAND))
			skill = Skill.SLEIGHT_OF_HAND;
		else if (feature.equals(EXPERTISE_STEALTH))
			skill = Skill.STEALTH;
		else if (feature.equals(EXPERTISE_SURVIVAL))
			skill = Skill.SURVIVAL;

		return skill;
	}


	public static EnumSet<Feature> bard(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		EnumSet<Skill> skills = actor.getSkills();
		int level = actor.getLevel();

		if (level == 1) {
			set.add(INSPIRATION_D6);
		} else if (level == 2) {
			set.add(JACK_OF_ALL_TRADES);
			set.add(SONG_OF_REST_D6);
		} else if (level == 3) {
			// BARD COLLEGE
			set.addAll(addRandomExpertise(2, actor));
			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				skills.add(Skill.testRandomSkill(actor));
				skills.add(Skill.testRandomSkill(actor));
				skills.add(Skill.testRandomSkill(actor));
				set.add(CUTTING_WORDS);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				actor.getArmorProficiency().addAll(Armor.getMediumArmorList());
				actor.getWeaponProficiency().addAll(Weapon.getMartialWeaponList());
				actor.getWeaponProficiency().add(Weapon.SHIELD);
				set.add(COMBAT_INSPIRATION);
			}

		} else if (level == 4) {
			set.add(ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {
			set.add(INSPIRATION_D8);
			set.add(FONT_OF_INSPIRATION);
		} else if (level == 6) {
			// BARD COLLEGE
			set.add(COUNTERCHARM);
			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				set.add(MAGICAL_SECRETS_6);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				set.add(EXTRA_ATTACK_1);
			}

		} else if (level == 7) {
			// spells and nothing else
		} else if (level == 8) {
			set.add(ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {
			set.add(SONG_OF_REST_D8);
		} else if (level == 10) {
			// magical secrets
			set.addAll(addRandomExpertise(2, actor));
			set.add(INSPIRATION_D10);
			set.add(MAGICAL_SECRETS_10);
		} else if (level == 11) {
			// spells and nothing else
		} else if (level == 12) {
			set.add(ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {
			set.add(SONG_OF_REST_D10);
		} else if (level == 14) {
			// BARD COLLEGE
			// magical secrets
			set.add(MAGICAL_SECRETS_14);
			if (archetype.equals(Class.Subclass.LORE_COLLEGE)) {
				set.add(PEERLESS_SKILL);
			} else if (archetype.equals(Class.Subclass.VALOR_COLLEGE)) {
				set.add(BATTLE_MAGIC);
			}

		} else if (level == 15) {
			set.add(INSPIRATION_D12);
		} else if (level == 16) {
			set.add(ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {
			set.add(SONG_OF_REST_D12);
		} else if (level == 18) {
			// magical secrets
			set.add(MAGICAL_SECRETS_18);
		} else if (level == 19) {
			set.add(ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {
			set.add(SUPERIOR_INSPIRATION);
		}

		return set;
	}

	// public static void abilityImprovement() {
	// Feature[] ability4 = { STR_BONUS_4, DEX_BONUS_4, CON_BONUS_4, INT_BONUS_4,
	// WIS_BONUS_4, CHA_BONUS_4 };
	// Feature[] ability6 = { STR_BONUS_6, DEX_BONUS_6, CON_BONUS_6, INT_BONUS_6,
	// WIS_BONUS_6, CHA_BONUS_6 };
	// Feature[] ability8 = { STR_BONUS_8, DEX_BONUS_8, CON_BONUS_8, INT_BONUS_8,
	// WIS_BONUS_8, CHA_BONUS_8 };
	// Feature[] ability10 = { STR_BONUS_10, DEX_BONUS_10, CON_BONUS_10,
	// INT_BONUS_10, WIS_BONUS_10, CHA_BONUS_10 };
	// Feature[] ability12 = { STR_BONUS_12, DEX_BONUS_12, CON_BONUS_12,
	// INT_BONUS_12, WIS_BONUS_12, CHA_BONUS_12 };
	// Feature[] ability14 = { STR_BONUS_14, DEX_BONUS_14, CON_BONUS_14,
	// INT_BONUS_14, WIS_BONUS_14, CHA_BONUS_14 };
	// Feature[] ability16 = { STR_BONUS_16, DEX_BONUS_16, CON_BONUS_16,
	// INT_BONUS_16, WIS_BONUS_16, CHA_BONUS_16 };
	// Feature[] ability19 = { STR_BONUS_19, DEX_BONUS_19, CON_BONUS_19,
	// INT_BONUS_19, WIS_BONUS_19, CHA_BONUS_19 };
	//
	// }

	public static EnumSet<Feature> blank(Actor actor) {
		Class.Subclass archetype = actor.getArchetype();
		EnumSet<Feature> set = EnumSet.noneOf(Feature.class);
		int level = actor.getLevel();

		if (level == 1) {

		} else if (level == 2) {

		} else if (level == 3) {

		} else if (level == 4) {
			set.add(ABILITY_BONUS_4);
			set.add(abilityImprove(actor));
		} else if (level == 5) {

		} else if (level == 6) {

		} else if (level == 7) {

		} else if (level == 8) {
			set.add(ABILITY_BONUS_8);
			set.add(abilityImprove(actor));
		} else if (level == 9) {

		} else if (level == 10) {

		} else if (level == 11) {

		} else if (level == 12) {
			set.add(ABILITY_BONUS_12);
			set.add(abilityImprove(actor));
		} else if (level == 13) {

		} else if (level == 14) {

		} else if (level == 15) {

		} else if (level == 16) {
			set.add(ABILITY_BONUS_16);
			set.add(abilityImprove(actor));
		} else if (level == 17) {

		} else if (level == 18) {

		} else if (level == 19) {
			set.add(ABILITY_BONUS_19);
			set.add(abilityImprove(actor));
		} else if (level == 20) {

		}

		return set;
	}
}
