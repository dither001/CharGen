
public interface Option {
	public enum Feature {
		// ability bonus
		ABILITY_BONUS_4, ABILITY_BONUS_6, ABILITY_BONUS_8, ABILITY_BONUS_10, ABILITY_BONUS_12, ABILITY_BONUS_14, ABILITY_BONUS_16, ABILITY_BONUS_19,
		// strength bonus
		STR_BONUS_4, STR_BONUS_6, STR_BONUS_8, STR_BONUS_10, STR_BONUS_12, STR_BONUS_14, STR_BONUS_16, STR_BONUS_19,
		// dexterity bonus
		DEX_BONUS_4, DEX_BONUS_6, DEX_BONUS_8, DEX_BONUS_10, DEX_BONUS_12, DEX_BONUS_14, DEX_BONUS_16, DEX_BONUS_19,
		// constitution bonus
		CON_BONUS_4, CON_BONUS_6, CON_BONUS_8, CON_BONUS_10, CON_BONUS_12, CON_BONUS_14, CON_BONUS_16, CON_BONUS_19,
		// intelligence bonus
		INT_BONUS_4, INT_BONUS_6, INT_BONUS_8, INT_BONUS_10, INT_BONUS_12, INT_BONUS_14, INT_BONUS_16, INT_BONUS_19,
		// wisdom bonus
		WIS_BONUS_4, WIS_BONUS_6, WIS_BONUS_8, WIS_BONUS_10, WIS_BONUS_12, WIS_BONUS_14, WIS_BONUS_16, WIS_BONUS_19,
		// charisma bonus
		CHA_BONUS_4, CHA_BONUS_6, CHA_BONUS_8, CHA_BONUS_10, CHA_BONUS_12, CHA_BONUS_14, CHA_BONUS_16, CHA_BONUS_19,
		// expertise
		EXPERTISE_ATHLETICS, EXPERTISE_ACROBATICS, EXPERTISE_SLEIGHT_OF_HAND, EXPERTISE_STEALTH, EXPERTISE_ARCANA, EXPERTISE_HISTORY, EXPERTISE_INVESTIGATION, EXPERTISE_NATURE, EXPERTISE_RELIGION, EXPERTISE_ANIMAL_HANDLING, EXPERTISE_INSIGHT, EXPERTISE_MEDICINE, EXPERTISE_PERCEPTION, EXPERTISE_SURVIVAL, EXPERTISE_DECEPTION, EXPERTISE_INTIMIDATION, EXPERTISE_PERFORMANCE, EXPERTISE_PERSUASION,
		/*
		 * GENERIC & SHARED FEATURES
		 */
		EXTRA_ATTACK_1, EXTRA_ATTACK_2, EXTRA_ATTACK_3,
		/*
		 * BARBARIAN
		 */
		RAGE, RAGE_PER_DAY_2, RAGE_PER_DAY_3, RAGE_PER_DAY_4, RAGE_PER_DAY_5, RAGE_PER_DAY_6, RAGE_PER_DAY_99, RAGE_BONUS_2, RAGE_BONUS_3, RAGE_BONUS_4, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2, PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION,
		// berserker
		FRENZY, MINDLESS_RAGE, INTIMIDATING_PRESENCE, RETALIATION,
		// totem warrior
		SPIRIT_SEEKER, BEAR_SPIRIT_3, EAGLE_SPIRIT_3, WOLF_SPIRIT_3, BEAR_ASPECT_6, EAGLE_ASPECT_6, WOLF_ASPECT_6, SPIRIT_WALKER, BEAR_ATTUNEMENT_14, EAGLE_ATTUNEMENT_14, WOLF_ATTUNEMENT_14,
		/*
		 * BARD
		 */
		// FIXME - unused - BARD_CANTRIPS_2, BARD_CANTRIPS_3, BARD_CANTRIPS_4,
		RITUAL_CASTING_BARD, INSPIRATION_D6, JACK_OF_ALL_TRADES, SONG_OF_REST_D6, INSPIRATION_D8, FONT_OF_INSPIRATION, COUNTERCHARM, SONG_OF_REST_D8, INSPIRATION_D10, MAGICAL_SECRETS_10, SONG_OF_REST_D10, MAGICAL_SECRETS_14, INSPIRATION_D12, SONG_OF_REST_D12, MAGICAL_SECRETS_18, SUPERIOR_INSPIRATION,
		// lore college
		CUTTING_WORDS, MAGICAL_SECRETS_6, PEERLESS_SKILL,
		// valor college
		COMBAT_INSPIRATION, BATTLE_MAGIC, 
		/*
		 * CLERIC
		 */
		RITUAL_CASTING_CLERIC, CHANNEL_DIVINITY_1, CHANNEL_DIVINITY_2, CHANNEL_DIVINITY_3,
		TURN_UNDEAD, DIVINE_INTERVENTION_10, DIVINE_INTERVENTION_20, 
		DESTROY_UNDEAD_5, DESTROY_UNDEAD_8, DESTROY_UNDEAD_11, DESTROY_UNDEAD_14, DESTROY_UNDEAD_17,
		// knowledge
		BLESSINGS_OF_KNOWLEDGE, POTENT_SPELLCASTING, VISIONS_OF_THE_PAST, 
		KNOWLEDGE_OF_THE_AGES, READ_THOUGHTS, 
		// life
		PRESERVE_LIFE, 
		// light
		RADIANCE_OF_THE_DAWN, 
		// nature
		CHARM_ANIMALS_AND_PLANTS, 
		// tempest
		DESTRUCTIVE_WRATH,
		// trickery
		INVOKE_DUPLICITY, CLOAK_OF_SHADOWS, 
		// war
		GUIDED_STRIKE, WAR_GODS_BLESSING, 
		/*
		 * PALADIN
		 */
		DIVINE_SENSE, LAY_ON_HANDS, 
		// devotion
		SACRED_WEAPON, TURN_THE_UNHOLY, 
		// ancients
		NATURES_WRATH, TURN_THE_FAITHLESS, 
		// vengeance
		ABJURE_ENEMY, VOW_OF_ENMITY, 
		/*
		 * END OF FEATURES
		 */
		;

		protected static final Feature[] BARBARIAN = { RAGE, UNARMORED_DEFENSE_BARBARIAN, RECKLESS_ATTACK, DANGER_SENSE,
				EXTRA_ATTACK_1, FAST_MOVEMENT, FERAL_INSTINCT, BRUTAL_CRITICAL_1, RELENTLESS_RAGE, BRUTAL_CRITICAL_2,
				PERSISTENT_RAGE, BRUTAL_CRITICAL_3, INDOMITABLE_MIGHT, PRIMAL_CHAMPION };

		protected static final Feature[] BARD = { INSPIRATION_D6, JACK_OF_ALL_TRADES, SONG_OF_REST_D6, INSPIRATION_D8,
				FONT_OF_INSPIRATION, COUNTERCHARM, SONG_OF_REST_D8, INSPIRATION_D10, MAGICAL_SECRETS_10,
				SONG_OF_REST_D10, MAGICAL_SECRETS_14, INSPIRATION_D12, SONG_OF_REST_D12, MAGICAL_SECRETS_18,
				SUPERIOR_INSPIRATION };

		protected static final Feature[] STR_BONUSES = { STR_BONUS_4, STR_BONUS_6, STR_BONUS_8, STR_BONUS_10,
				STR_BONUS_12, STR_BONUS_14, STR_BONUS_16, STR_BONUS_19 };
		protected static final Feature[] DEX_BONUSES = { DEX_BONUS_4, DEX_BONUS_6, DEX_BONUS_8, DEX_BONUS_10,
				DEX_BONUS_12, DEX_BONUS_14, DEX_BONUS_16, DEX_BONUS_19 };
		protected static final Feature[] CON_BONUSES = { CON_BONUS_4, CON_BONUS_6, CON_BONUS_8, CON_BONUS_10,
				CON_BONUS_12, CON_BONUS_14, CON_BONUS_16, CON_BONUS_19 };
		protected static final Feature[] INT_BONUSES = { INT_BONUS_4, INT_BONUS_6, INT_BONUS_8, INT_BONUS_10,
				INT_BONUS_12, INT_BONUS_14, INT_BONUS_16, INT_BONUS_19 };
		protected static final Feature[] WIS_BONUSES = { WIS_BONUS_4, WIS_BONUS_6, WIS_BONUS_8, WIS_BONUS_10,
				WIS_BONUS_12, WIS_BONUS_14, WIS_BONUS_16, WIS_BONUS_19 };
		protected static final Feature[] CHA_BONUSES = { CHA_BONUS_4, CHA_BONUS_6, CHA_BONUS_8, CHA_BONUS_10,
				CHA_BONUS_12, CHA_BONUS_14, CHA_BONUS_16, CHA_BONUS_19 };

		protected static final Feature[] EXPERTISE = { EXPERTISE_ATHLETICS, EXPERTISE_ACROBATICS,
				EXPERTISE_SLEIGHT_OF_HAND, EXPERTISE_STEALTH, EXPERTISE_ARCANA, EXPERTISE_HISTORY,
				EXPERTISE_INVESTIGATION, EXPERTISE_NATURE, EXPERTISE_RELIGION, EXPERTISE_ANIMAL_HANDLING,
				EXPERTISE_INSIGHT, EXPERTISE_MEDICINE, EXPERTISE_PERCEPTION, EXPERTISE_SURVIVAL, EXPERTISE_DECEPTION,
				EXPERTISE_INTIMIDATION, EXPERTISE_PERFORMANCE, EXPERTISE_PERSUASION };

	}

}
