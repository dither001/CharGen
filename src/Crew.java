
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Crew {
	public enum Type {
		ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
	}

	public enum Rep {
		AMBITIOUS, BRUTAL, DARING, HONORABLE, PROFESSIONAL, SAVVY, SUBTLE, STRANGE
	}

	public enum District {
		BARROWCLEFT, BRIGHTSTONE, CHARHOLLOW, CHARTERHALL, COALRIDGE, CROWS_FOOT, THE_DOCKS, DUNSLOUGH, NIGHTMARKET, SILKSHORE, SIX_TOWERS, WHITECROWN
	}

	public enum Estate {
		UNDERWORLD, INSTITUTION, LABOR_TRADE, CITIZENRY, THE_FRINGE
	}

	public enum Faction {
		BARROWCLEFT, BILLHOOKS, BLUECOATS, BRIGADE, BRIGHTSTONE, CABBIES, CHARHOLLOW, CHARTERHALL, CHURCH_OF_ECSTASY, CIRCLE_OF_FLAME, CITY_COUNCIL, COALRIDGE, CROWS, CROWS_FOOT, CYPHERS, DAGGER_ISLES_CONSULATE, DEATHLANDS_SCAVENGERS, DIMMER_SISTERS, DOCKERS, DOCKS, DUNSLOUGH, FOG_HOUNDS, FORGOTTEN_GODS, FOUNDATION, GONDOLIERS, GRAY_CLOAKS, GRINDERS, HIVE, HORDE, IMPERIAL_MILITARY, INK_RAKES, INSPECTORS, IRONHOOK_PRISON, IRUVIAN_CONSULATE, LABORERS, LAMPBLACKS, LEVIATHAN_HUNTERS, LORD_SCURLOCK, LOST, MINISTRY_OF_PRESERVATION, NIGHTMARKET, PATH_OF_ECHOES, RAIL_JACKS, RECONCILED, RED_SASHES, SAILORS, SERVANTS, SEVEROSI_CONSULATE, SILKSHORE, SILVER_NAILS, SIX_TOWERS, SKOVLAN_CONSULATE, SKOVLANDER_REFUGEES, SPARKWRIGHTS, SPIRIT_WARDENS, ULF_IRONBORN, UNSEEN, WEEPING_LADY, WHITECROWN, WRAITHS, VULTURES
	}

	public enum Claim {
		LAIR, TURF_1, TURF_2, TURF_3, TURF_4, TURF_5, TURF_6, TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM, VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS, BARRACKS, TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES, BLUECOAT_CONFEDERATES, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER, SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR, PERSONAL_CLOTHIER, LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B, INTERROGATION_CHAMBER, GAMBLING_DEN, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET
	}

	public enum Upgrade {
		C2_COHORT_1, C2_COHORT_2, C2_COHORT_3, C2_COHORT_4, 
		
		BOAT_HOUSE_1, BOAT_HOUSE_2, CARRIAGE_HOUSE_1, CARRIAGE_HOUSE_2, HIDDEN_LAIR, LIVING_QUARTERS, SECURE_LAIR_1, SECURE_LAIR_2, TRAINING_INSIGHT, TRAINING_PROWESS, TRAINING_RESOLVE, TRAINING_PERSONAL, STORAGE_VAULT_1, STORAGE_VAULT_2, WORKSHOP, C4_MASTERY, QUALITY_DOCUMENTS, QUALITY_GEAR, QUALITY_IMPLEMENTS, QUALITY_SUPPLIES, QUALITY_TOOLS, QUALITY_WEAPONS, ELITE_SKULKS, ELITE_THUGS, ELITE_ROVERS, ELITE_ADEPTS, ELITE_ROOKS, ASSASSIN_RIGGING, BRAVOS_RIGGING, CULT_RIGGING, HAWKERS_RIGGING, THIEF_RIGGING, SMUGGLER_RIGGING, IRONHOOK_CONTACTS, C3_HARDENED, C3_COMPOSED, RITUAL_SANCTUM, MAPS_AND_KEYS, CAMOUFLAGE, BARGE
	}

	public enum CohortType {
		ADEPT, ROOK, ROVER, SKULK, THUG, EXPERT
	}

	public enum CohortEdge {
		FEARSOME, INDEPENDENT, LOYAL, TENACIOUS
	}

	public enum CohortFlaw {
		PRINCIPLED, SAVAGE, UNRELIABLE, WILD
	}

	// claims by crew type
	private static final Claim[] ASSASSIN_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TRAINING_ROOMS,
			Claim.VICE_DEN, Claim.FIXER, Claim.INFORMANTS, Claim.HAGFISH_FARM, Claim.VICTIM_TROPHIES,
			Claim.COVER_OPERATION, Claim.PROTECTION_RACKET, Claim.INFIRMARY, Claim.ENVOY, Claim.COVER_IDENTITIES_A,
			Claim.CITY_RECORDS };
	private static final Claim[] BRAVO_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TURF_3, Claim.TURF_4,
			Claim.INFORMANTS, Claim.INFIRMARY, Claim.PROTECTION_RACKET, Claim.BARRACKS, Claim.TERRORIZED_CITIZENS,
			Claim.FIGHTING_PITS, Claim.BLUECOAT_INTIMIDATION, Claim.STREET_FENCE, Claim.WAREHOUSES,
			Claim.BLUECOAT_CONFEDERATES };
	private static final Claim[] CULT_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TURF_3, Claim.TURF_4,
			Claim.VICE_DEN, Claim.CLOISTER, Claim.OFFERTORY, Claim.ANCIENT_OBELISK, Claim.ANCIENT_TOWER,
			Claim.SPIRIT_WELL, Claim.ANCIENT_GATE, Claim.SANCTUARY, Claim.SACRED_NEXUS, Claim.ANCIENT_ALTAR };
	private static final Claim[] HAWKER_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TURF_3, Claim.TURF_4,
			Claim.INFORMANTS, Claim.VICE_DEN, Claim.COVER_OPERATION, Claim.PERSONAL_CLOTHIER, Claim.LOCAL_GRAFT,
			Claim.LOOKOUTS, Claim.LUXURY_VENUE, Claim.FOREIGN_MARKET, Claim.SURPLUS_CACHES, Claim.COVER_IDENTITIES_B };
	private static final Claim[] SHADOW_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TURF_3,
			Claim.GAMBLING_DEN, Claim.INFORMANTS, Claim.LOOKOUTS, Claim.HAGFISH_FARM, Claim.INFIRMARY,
			Claim.INTERROGATION_CHAMBER, Claim.LOYAL_FENCE, Claim.TAVERN, Claim.DRUG_DEN, Claim.COVERT_DROPS,
			Claim.SECRET_PATHWAYS };
	private static final Claim[] SMUGGLER_CLAIMS = { Claim.LAIR, Claim.TURF_1, Claim.TURF_2, Claim.TURF_3, Claim.TURF_4,
			Claim.VICE_DEN, Claim.TAVERN, Claim.ANCIENT_GATE, Claim.INFORMANTS, Claim.COVER_OPERATION, Claim.WAREHOUSES,
			Claim.SIDE_BUSINESS, Claim.LUXURY_FENCE, Claim.SECRET_ROUTES, Claim.FLEET };

	// upgrades
	private static final Upgrade[] GENERIC_UPGRADES = { Upgrade.BOAT_HOUSE_1, Upgrade.BOAT_HOUSE_2,
			Upgrade.CARRIAGE_HOUSE_1, Upgrade.CARRIAGE_HOUSE_2, Upgrade.HIDDEN_LAIR, Upgrade.LIVING_QUARTERS,
			Upgrade.SECURE_LAIR_1, Upgrade.SECURE_LAIR_2, Upgrade.TRAINING_INSIGHT, Upgrade.TRAINING_PROWESS,
			Upgrade.TRAINING_RESOLVE, Upgrade.TRAINING_PERSONAL, Upgrade.STORAGE_VAULT_1, Upgrade.STORAGE_VAULT_2,
			Upgrade.WORKSHOP, Upgrade.C4_MASTERY, Upgrade.QUALITY_DOCUMENTS, Upgrade.QUALITY_GEAR,
			Upgrade.QUALITY_IMPLEMENTS, Upgrade.QUALITY_SUPPLIES, Upgrade.QUALITY_TOOLS, Upgrade.QUALITY_WEAPONS };

	// upgrades by crew type
	private static final Upgrade[] ASSASSIN_UPGRADES = { Upgrade.ASSASSIN_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_SKULKS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED, };
	private static final Upgrade[] BRAVOS_UPGRADES = { Upgrade.BRAVOS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROVERS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED, };
	private static final Upgrade[] CULT_UPGRADES = { Upgrade.CULT_RIGGING, Upgrade.RITUAL_SANCTUM, Upgrade.ELITE_ADEPTS,
			Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED, };
	private static final Upgrade[] HAWKERS_UPGRADES = { Upgrade.HAWKERS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_THUGS, Upgrade.C3_COMPOSED, };
	private static final Upgrade[] SHADOWS_UPGRADES = { Upgrade.THIEF_RIGGING, Upgrade.MAPS_AND_KEYS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_SKULKS, Upgrade.C3_COMPOSED, };
	private static final Upgrade[] SMUGGLERS_UPGRADES = { Upgrade.SMUGGLER_RIGGING, Upgrade.CAMOUFLAGE,
			Upgrade.ELITE_ROVERS, Upgrade.BARGE, Upgrade.C3_COMPOSED };

	// static fields
	private static final Type[] ALL_TYPES = { Type.ASSASSINS, Type.BRAVOS, Type.CULT, Type.HAWKERS, Type.SHADOWS,
			Type.SMUGGLERS };
	private static final Rep[] ALL_REPS = { Rep.AMBITIOUS, Rep.BRUTAL, Rep.DARING, Rep.HONORABLE, Rep.PROFESSIONAL,
			Rep.SAVVY, Rep.SUBTLE, Rep.STRANGE };
	private static final District[] ALL_DISTRICTS = { District.BARROWCLEFT, District.BRIGHTSTONE, District.CHARHOLLOW,
			District.CHARTERHALL, District.COALRIDGE, District.CROWS_FOOT, District.THE_DOCKS, District.DUNSLOUGH,
			District.NIGHTMARKET, District.SILKSHORE, District.SIX_TOWERS, District.WHITECROWN };
	private static final Faction[] ALL_FACTIONS = { Faction.BARROWCLEFT, Faction.BILLHOOKS, Faction.BLUECOATS,
			Faction.BRIGADE, Faction.BRIGHTSTONE, Faction.CABBIES, Faction.CHARHOLLOW, Faction.CHARTERHALL,
			Faction.CHURCH_OF_ECSTASY, Faction.CIRCLE_OF_FLAME, Faction.CITY_COUNCIL, Faction.COALRIDGE, Faction.CROWS,
			Faction.CROWS_FOOT, Faction.CYPHERS, Faction.DAGGER_ISLES_CONSULATE, Faction.DEATHLANDS_SCAVENGERS,
			Faction.DIMMER_SISTERS, Faction.DOCKERS, Faction.DOCKS, Faction.DUNSLOUGH, Faction.FOG_HOUNDS,
			Faction.FORGOTTEN_GODS, Faction.FOUNDATION, Faction.GONDOLIERS, Faction.GRAY_CLOAKS, Faction.GRINDERS,
			Faction.HIVE, Faction.HORDE, Faction.IMPERIAL_MILITARY, Faction.INK_RAKES, Faction.INSPECTORS,
			Faction.IRONHOOK_PRISON, Faction.IRUVIAN_CONSULATE, Faction.LABORERS, Faction.LAMPBLACKS,
			Faction.LEVIATHAN_HUNTERS, Faction.LORD_SCURLOCK, Faction.LOST, Faction.MINISTRY_OF_PRESERVATION,
			Faction.NIGHTMARKET, Faction.PATH_OF_ECHOES, Faction.RAIL_JACKS, Faction.RECONCILED, Faction.RED_SASHES,
			Faction.SAILORS, Faction.SERVANTS, Faction.SEVEROSI_CONSULATE, Faction.SILKSHORE, Faction.SILVER_NAILS,
			Faction.SIX_TOWERS, Faction.SKOVLAN_CONSULATE, Faction.SKOVLANDER_REFUGEES, Faction.SPARKWRIGHTS,
			Faction.SPIRIT_WARDENS, Faction.ULF_IRONBORN, Faction.UNSEEN, Faction.WEEPING_LADY, Faction.WHITECROWN,
			Faction.WRAITHS, Faction.VULTURES };

	//
	private static final Faction[] CITIZENRY = { Faction.BARROWCLEFT, Faction.BRIGHTSTONE, Faction.CHARHOLLOW,
			Faction.CHARTERHALL, Faction.COALRIDGE, Faction.CROWS_FOOT, Faction.DOCKS, Faction.DUNSLOUGH,
			Faction.NIGHTMARKET, Faction.SILKSHORE, Faction.SIX_TOWERS, Faction.WHITECROWN };
	private static final Faction[] INSTITUTIONS = { Faction.BLUECOATS, Faction.BRIGADE, Faction.CITY_COUNCIL,
			Faction.DAGGER_ISLES_CONSULATE, Faction.IMPERIAL_MILITARY, Faction.INSPECTORS, Faction.IRONHOOK_PRISON,
			Faction.IRUVIAN_CONSULATE, Faction.LEVIATHAN_HUNTERS, Faction.MINISTRY_OF_PRESERVATION,
			Faction.SEVEROSI_CONSULATE, Faction.SKOVLAN_CONSULATE, Faction.SPARKWRIGHTS, Faction.SPIRIT_WARDENS };
	private static final Faction[] LABOR_TRADE = { Faction.CABBIES, Faction.CYPHERS, Faction.DOCKERS,
			Faction.FOUNDATION, Faction.GONDOLIERS, Faction.INK_RAKES, Faction.LABORERS, Faction.RAIL_JACKS,
			Faction.SAILORS, Faction.SERVANTS };
	private static final Faction[] THE_FRINGE = { Faction.CHURCH_OF_ECSTASY, Faction.DEATHLANDS_SCAVENGERS,
			Faction.FORGOTTEN_GODS, Faction.HORDE, Faction.PATH_OF_ECHOES, Faction.RECONCILED,
			Faction.SKOVLANDER_REFUGEES, Faction.WEEPING_LADY };
	private static final Faction[] UNDERWORLD = { Faction.BILLHOOKS, Faction.CIRCLE_OF_FLAME, Faction.CROWS,
			Faction.DIMMER_SISTERS, Faction.FOG_HOUNDS, Faction.GRAY_CLOAKS, Faction.GRINDERS, Faction.HIVE,
			Faction.LAMPBLACKS, Faction.LORD_SCURLOCK, Faction.LOST, Faction.RED_SASHES, Faction.SILVER_NAILS,
			Faction.ULF_IRONBORN, Faction.UNSEEN, Faction.WRAITHS, Faction.VULTURES };

	//
	private static ArrayList<Crew> factions;

	// initialization
	static {
		factions = new ArrayList<Crew>();
		factions.add(new Crew(Faction.BARROWCLEFT, 2, true));
		factions.add(new Crew(Faction.BILLHOOKS, 2, false,
				new Crew.Faction[] { Faction.BLUECOATS, Faction.MINISTRY_OF_PRESERVATION },
				new Crew.Faction[] { Faction.ULF_IRONBORN, Faction.CROWS_FOOT, Faction.DOCKS }));
		factions.add(new Crew(
				Faction.BLUECOATS, 3, true, new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.BILLHOOKS, Faction.CROWS,
						Faction.IRONHOOK_PRISON, Faction.LORD_SCURLOCK, Faction.UNSEEN },
				new Crew.Faction[] { Faction.IMPERIAL_MILITARY }));
		factions.add(new Crew(Faction.BRIGADE, 2, true));
		factions.add(new Crew(Faction.BRIGHTSTONE, 4, true));
		factions.add(new Crew(Faction.CABBIES, 2, false));
		factions.add(new Crew(Faction.CHARHOLLOW, 1, true));
		factions.add(new Crew(Faction.CHARTERHALL, 4, true));
		factions.add(new Crew(Faction.CHURCH_OF_ECSTASY, 4, true,
				new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.LEVIATHAN_HUNTERS, Faction.SPIRIT_WARDENS },
				new Crew.Faction[] { Faction.PATH_OF_ECHOES, Faction.RECONCILED }));
		factions.add(new Crew(
				Faction.CIRCLE_OF_FLAME, 3, true, new Crew.Faction[] { Faction.FORGOTTEN_GODS, Faction.PATH_OF_ECHOES,
						Faction.CITY_COUNCIL, Faction.FOUNDATION },
				new Crew.Faction[] { Faction.HIVE, Faction.SILVER_NAILS }));
		factions.add(new Crew(Faction.CITY_COUNCIL, 5, true,
				new Crew.Faction[] { Faction.BLUECOATS, Faction.CHURCH_OF_ECSTASY, Faction.CIRCLE_OF_FLAME,
						Faction.LORD_SCURLOCK, Faction.BRIGADE, Faction.CABBIES, Faction.SPARKWRIGHTS,
						Faction.FOUNDATION },
				new Crew.Faction[] { Faction.IMPERIAL_MILITARY, Faction.INSPECTORS, Faction.MINISTRY_OF_PRESERVATION,
						Faction.RECONCILED }));
		factions.add(new Crew(Faction.COALRIDGE, 2, false));
		factions.add(new Crew(Faction.CROWS, 2, false,
				new Crew.Faction[] { Faction.BLUECOATS, Faction.SAILORS, Faction.LOST, Faction.CROWS_FOOT },
				new Crew.Faction[] { Faction.HIVE, Faction.INSPECTORS, Faction.DOCKERS }));
		factions.add(new Crew(Faction.CROWS_FOOT, 2, true));
		factions.add(new Crew(Faction.CYPHERS, 2, true));
		factions.add(new Crew(Faction.DAGGER_ISLES_CONSULATE, 1, true));
		factions.add(new Crew(Faction.DEATHLANDS_SCAVENGERS, 2, false,
				new Crew.Faction[] { Faction.FORGOTTEN_GODS, Faction.GONDOLIERS, Faction.SPIRIT_WARDENS },
				new Crew.Faction[] { Faction.IRONHOOK_PRISON }));
		factions.add(new Crew(Faction.DIMMER_SISTERS, 2, true,
				new Crew.Faction[] { Faction.FORGOTTEN_GODS, Faction.FOUNDATION },
				new Crew.Faction[] { Faction.SPIRIT_WARDENS, Faction.RECONCILED }));
		factions.add(new Crew(Faction.DOCKERS, 3, true));
		factions.add(new Crew(Faction.DOCKS, 2, true));
		factions.add(new Crew(Faction.DUNSLOUGH, 1, false));
		factions.add(new Crew(Faction.FOG_HOUNDS, 1, false, new Crew.Faction[] { Faction.DOCKERS, Faction.LAMPBLACKS },
				new Crew.Faction[] { Faction.BLUECOATS, Faction.VULTURES }));
		factions.add(new Crew(Faction.FORGOTTEN_GODS, 3, false));
		factions.add(new Crew(Faction.FOUNDATION, 4, true));
		factions.add(new Crew(Faction.GONDOLIERS, 3, true,
				new Crew.Faction[] { Faction.LAMPBLACKS, Faction.BARROWCLEFT, Faction.BRIGHTSTONE, Faction.CHARHOLLOW,
						Faction.CHARTERHALL, Faction.COALRIDGE, Faction.CROWS_FOOT, Faction.DOCKS, Faction.DUNSLOUGH,
						Faction.NIGHTMARKET, Faction.SILKSHORE, Faction.SIX_TOWERS, Faction.WHITECROWN },
				new Crew.Faction[] { Faction.RED_SASHES, Faction.SPIRIT_WARDENS }));
		factions.add(new Crew(Faction.GRAY_CLOAKS, 2, true, new Crew.Faction[] { Faction.INSPECTORS },
				new Crew.Faction[] { Faction.BLUECOATS, Faction.LEVIATHAN_HUNTERS }));
		factions.add(new Crew(Faction.GRINDERS, 2, false, new Crew.Faction[] { Faction.ULF_IRONBORN, Faction.DOCKERS },
				new Crew.Faction[] { Faction.BLUECOATS, Faction.IMPERIAL_MILITARY, Faction.LEVIATHAN_HUNTERS,
						Faction.SAILORS, Faction.SILVER_NAILS }));
		factions.add(new Crew(Faction.HIVE, 4, true,
				new Crew.Faction[] { Faction.MINISTRY_OF_PRESERVATION, Faction.DAGGER_ISLES_CONSULATE },
				new Crew.Faction[] { Faction.CIRCLE_OF_FLAME, Faction.UNSEEN, Faction.CROWS, Faction.WRAITHS }));
		factions.add(new Crew(Faction.HORDE, 3, true));
		factions.add(new Crew(Faction.IMPERIAL_MILITARY, 6, true));
		factions.add(new Crew(Faction.INK_RAKES, 2, false));
		factions.add(new Crew(Faction.INSPECTORS, 3, true));
		factions.add(new Crew(Faction.IRONHOOK_PRISON, 4, true));
		factions.add(new Crew(Faction.IRUVIAN_CONSULATE, 3, true));
		factions.add(new Crew(Faction.LABORERS, 3, false));
		factions.add(new Crew(Faction.LAMPBLACKS, 2, false,
				new Crew.Faction[] { Faction.FOG_HOUNDS, Faction.GONDOLIERS, Faction.IRONHOOK_PRISON },
				new Crew.Faction[] { Faction.RED_SASHES, Faction.BLUECOATS, Faction.CABBIES }));
		factions.add(new Crew(Faction.LEVIATHAN_HUNTERS, 5, true,
				new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.CHURCH_OF_ECSTASY, Faction.SAILORS, Faction.DOCKERS,
						Faction.SPARKWRIGHTS },
				new Crew.Faction[] { Faction.GRINDERS, Faction.MINISTRY_OF_PRESERVATION, Faction.PATH_OF_ECHOES }));
		factions.add(
				new Crew(
						Faction.LORD_SCURLOCK, 3, true, new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.BLUECOATS,
								Faction.INSPECTORS, Faction.FORGOTTEN_GODS },
						new Crew.Faction[] { Faction.SPIRIT_WARDENS }));
		factions.add(new Crew(Faction.LOST, 1, false,
				new Crew.Faction[] { Faction.LABORERS, Faction.COALRIDGE, Faction.DUNSLOUGH, Faction.CROWS },
				new Crew.Faction[] { Faction.BLUECOATS, Faction.BILLHOOKS }));
		factions.add(new Crew(
				Faction.MINISTRY_OF_PRESERVATION, 5, true, new Crew.Faction[] { Faction.BILLHOOKS,
						Faction.IMPERIAL_MILITARY, Faction.RAIL_JACKS, Faction.SPARKWRIGHTS },
				new Crew.Faction[] { Faction.LEVIATHAN_HUNTERS }));
		factions.add(new Crew(Faction.NIGHTMARKET, 2, true));
		factions.add(new Crew(Faction.PATH_OF_ECHOES, 3, true));
		factions.add(new Crew(Faction.RAIL_JACKS, 2, false));
		factions.add(new Crew(Faction.RECONCILED, 3, true,
				new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.GONDOLIERS },
				new Crew.Faction[] { Faction.CHURCH_OF_ECSTASY, Faction.SPIRIT_WARDENS, Faction.SPARKWRIGHTS }));
		factions.add(new Crew(Faction.RED_SASHES, 2, false,
				new Crew.Faction[] { Faction.IRUVIAN_CONSULATE, Faction.PATH_OF_ECHOES, Faction.DOCKERS,
						Faction.CABBIES, Faction.INSPECTORS },
				new Crew.Faction[] { Faction.LAMPBLACKS, Faction.BLUECOATS, Faction.GONDOLIERS }));
		factions.add(new Crew(Faction.SAILORS, 3, false));
		factions.add(new Crew(Faction.SERVANTS, 2, false));
		factions.add(new Crew(Faction.SEVEROSI_CONSULATE, 1, true));
		factions.add(new Crew(Faction.SILKSHORE, 2, true));
		factions.add(new Crew(Faction.SILVER_NAILS, 3, true,
				new Crew.Faction[] { Faction.IMPERIAL_MILITARY, Faction.SAILORS, Faction.SEVEROSI_CONSULATE },
				new Crew.Faction[] { Faction.CIRCLE_OF_FLAME, Faction.GRINDERS, Faction.SKOVLAN_CONSULATE,
						Faction.SKOVLANDER_REFUGEES, Faction.SPIRIT_WARDENS }));
		factions.add(new Crew(Faction.SIX_TOWERS, 3, false));
		factions.add(new Crew(Faction.SKOVLAN_CONSULATE, 3, false));
		factions.add(new Crew(Faction.SKOVLANDER_REFUGEES, 3, false));
		factions.add(new Crew(Faction.SPARKWRIGHTS, 4, true,
				new Crew.Faction[] { Faction.CITY_COUNCIL, Faction.LEVIATHAN_HUNTERS,
						Faction.MINISTRY_OF_PRESERVATION },
				new Crew.Faction[] { Faction.PATH_OF_ECHOES, Faction.RECONCILED, Faction.FOUNDATION }));
		factions.add(new Crew(Faction.SPIRIT_WARDENS, 4, true,
				new Crew.Faction[] { Faction.CHURCH_OF_ECSTASY, Faction.DEATHLANDS_SCAVENGERS },
				new Crew.Faction[] { Faction.DIMMER_SISTERS, Faction.GONDOLIERS, Faction.LORD_SCURLOCK,
						Faction.SILVER_NAILS, Faction.UNSEEN, Faction.PATH_OF_ECHOES, Faction.RECONCILED }));
		factions.add(new Crew(Faction.ULF_IRONBORN, 1, true, new Crew.Faction[] { Faction.GRINDERS },
				new Crew.Faction[] { Faction.COALRIDGE, Faction.BILLHOOKS }));
		factions.add(new Crew(
				Faction.UNSEEN, 4, true, new Crew.Faction[] { Faction.BLUECOATS, Faction.IRONHOOK_PRISON,
						Faction.FORGOTTEN_GODS, Faction.CYPHERS },
				new Crew.Faction[] { Faction.INK_RAKES, Faction.SPIRIT_WARDENS }));
		factions.add(new Crew(Faction.WEEPING_LADY, 2, true));
		factions.add(new Crew(Faction.WHITECROWN, 5, true));
		factions.add(new Crew(Faction.WRAITHS, 2, false, new Crew.Faction[] { Faction.CABBIES },
				new Crew.Faction[] { Faction.BLUECOATS, Faction.INSPECTORS, Faction.HIVE }));
		factions.add(new Crew(Faction.VULTURES, 1, false));

	}

	// fields
	private String name;
	private Estate estate;
	private Type type;
	private HashSet<Rep> rep;

	private int tier;
	private boolean holdStrong;
	private int coin;
	private int exp;

	//
	private String lair;
	private EnumSet<Claim> claims;
	private HashMap<Upgrade, Faction> upgrades;
	private int turf;
	//
	private EnumSet<Faction> npcAllies;
	private EnumSet<Faction> npcEnemies;

	//
	private int heat;
	private int wantedLevel;
	private boolean atWar;
	private HashMap<Faction, Integer> ships;

	/*
	 * Hunting grounds provide +1d6 gather information and a free downtime activity
	 * to contribute to an operation of your preferred type; when you claim turf,
	 * you expand the size and/or type of your hunting grounds
	 */
	private String huntingGrounds;
	//
	private HashSet<Faction> huntingGroundsBoss;
	private int huntingGroundSize;
	private String operation;
	private HashSet<Score.Activity> favoredOps;

	// constructors
	public Crew() {
		// TODO - create additional constructors
		this.type = randomCrewType();
		this.tier = 0;
		this.holdStrong = true;
		this.coin = 2;
		this.exp = 0;

		//
		this.rep = new HashSet<Rep>();
		rep.add(randomReputation());

		//
		this.claims = EnumSet.noneOf(Claim.class);
		claims.add(Claim.LAIR);
		upgrades = new HashMap<Upgrade, Faction>();
		this.turf = 0;
		//
		this.npcAllies = EnumSet.noneOf(Crew.Faction.class);
		this.npcEnemies = EnumSet.noneOf(Crew.Faction.class);

		//
		this.heat = 0;
		this.wantedLevel = 0;
		this.atWar = false;

		//
		this.huntingGroundSize = 1;
		this.favoredOps = new HashSet<Score.Activity>();
		favoredOps.add(Score.randomActivity(type));

		// setup ships
		ships = new HashMap<Faction, Integer>();
		for (int i = 0; i < ALL_FACTIONS.length; ++i) {
			ships.put(ALL_FACTIONS[i], 0);
		}

		this.huntingGroundsBoss = new HashSet<Faction>();
		ArrayList<Faction> shipSetup = new ArrayList<Faction>();
		while (shipSetup.size() < 3) {
			shipSetup.add(randomFactionEnum());
		}
		Faction f = shipSetup.get(0);
		huntingGroundsBoss.add(f);

		int dice = Dice.roll(3);
		// hunting grounds
		if (dice == 1) {
			coin -= 1;
		} else if (dice == 2) {
			coin -= 2;
			increaseShip(f);
		} else {
			decreaseShip(f);
		}

		// upgrade one
		dice = Dice.roll(2);
		f = shipSetup.get(1);
		increaseShip(f);
		if (dice == 1 && coin > 0) {
			coin -= 1;
			increaseShip(f);
		}

		// upgrade two
		dice = Dice.roll(2);
		f = shipSetup.get(2);
		decreaseShip(f);
		if (dice == 1 || coin < 1) {
			decreaseShip(f);
		} else if (coin > 0) {
			coin -= 1;
		}
	}

	public Crew(Crew.Faction name, int tier, boolean hold) {
		this(name, tier, hold, new Crew.Faction[0], new Crew.Faction[0]);
	}

	public Crew(Crew.Faction name, int tier, boolean hold, Crew.Faction[] listAllies, Crew.Faction[] listEnemies) {
		/*
		 * FIXME - I need to add more to faction initialization, but this works
		 */
		this.name = name.toString();
		// this.estate = estate;
		this.tier = tier;
		this.holdStrong = hold;
		//
		this.claims = EnumSet.noneOf(Claim.class);
		claims.add(Claim.LAIR);
		//
		this.npcAllies = EnumSet.noneOf(Crew.Faction.class);
		if (listAllies.length > 0) {
			for (Crew.Faction el : listAllies)
				npcAllies.add(el);
		}

		this.npcEnemies = EnumSet.noneOf(Crew.Faction.class);
		if (listEnemies.length > 0) {
			for (Crew.Faction el : listEnemies)
				npcEnemies.add(el);
		}
	}

	// methods
	public void advance() {
		boolean canAdvance = true;

		if (holdStrong() != true)
			canAdvance = false;

		if (exp < 12 - turf)
			canAdvance = false;

		if (coin < (tier + 1) * 8)
			canAdvance = false;

		if (canAdvance) {
			++tier;
			holdStrong = false;
			coin -= (tier + 1) * 8;
			exp -= 12 - turf;
		}
	}

	public Faction preferredTarget() {
		// ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
		// CITIZENRY, INSTITUTION, LABOR_TRADE, THE_FRINGE, UNDERWORLD
		int[] targets = { 20, 20, 20, 20, 20 };

		/*
		 * ASSASSINS institutions & underworld CULT citizens & fringe HAWKERS citizens &
		 * labor SHADOWS institutions & underworld SMUGGLERS labor & fringe
		 */
		if (type.equals(Type.ASSASSINS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.BRAVOS)) {
			targets[0] = 20; // citizens
			targets[1] = 20; // institution
			targets[2] = 20; // labor & trade
			targets[3] = 20; // the fringe
			targets[4] = 20; // underworld
		} else if (type.equals(Type.CULT)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.HAWKERS)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.SHADOWS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.SMUGGLERS)) {
			targets[0] = 10; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		}

		int dice = Dice.roll(100);
		Faction faction;
		if (dice < 1 + targets[0]) {
			System.out.println(1 + targets[0] + " / " + dice);
			faction = randomCitizenryEnum();
		} else if (dice < 1 + targets[0] + targets[1]) {
			System.out.println(1 + targets[0] + targets[1] + " / " + dice);
			faction = randomInstitutionEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + " / " + dice);
			faction = randomLaborTradeEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2] + targets[3]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + targets[3] + " / " + dice);
			faction = randomFringeEnum();
		} else {
			System.out.println(100 + " / " + dice);
			faction = randomUnderworldEnum();
		}

		return faction;
	}

	public boolean increaseShip(Faction faction) {
		boolean increased = false;
		int v = ships.get(faction);

		if (v < 3) {
			ships.put(faction, v + 1);
			increased = true;
		}

		checkAtWar();
		return increased;
	}

	public boolean decreaseShip(Faction faction) {
		boolean decreased = false;
		int v = ships.get(faction);

		if (v > -3) {
			ships.put(faction, v - 1);
			decreased = true;
		}

		checkAtWar();
		return decreased;
	}

	private void checkAtWar() {
		atWar = (ships.values().contains(-3)) ? true : false;
	}

	public String getName() {
		return name;
	}

	public Type getCrewType() {
		return type;
	}

	public Set<Rep> getReputation() {
		return rep;
	}

	public int getTier() {
		return tier;
	}

	public void addEXP(int gains) {
		exp = (exp + gains > 12) ? 12 : exp + gains;
	}

	public void addCoin(int gains) {
		// TODO - do I need to do something else with this?
		coin += gains;
	}

	public boolean holdStrong() {
		boolean isHoldStrong = holdStrong;
		if (atWar)
			isHoldStrong = false;

		return isHoldStrong;
	}

	public boolean holdWeak() {
		boolean isHoldWeak = (holdStrong != true);
		if (atWar)
			isHoldWeak = true;

		return isHoldWeak;
	}

	public boolean atWar() {
		return atWar;
	}

	public boolean atPeace() {
		return (atWar != true);
	}

	public Set<Faction> getNonZeroShips() {
		HashSet<Faction> nonZeroShips = new HashSet<Faction>();

		Faction f;
		int v;
		for (Iterator<Faction> it = ships.keySet().iterator(); it.hasNext();) {
			f = it.next();
			v = ships.get(f);
			if (v != 0)
				nonZeroShips.add(f);
		}

		return nonZeroShips;
	}

	public Set<Faction> getAllies() {
		return npcAllies;
	}

	public Set<Faction> getEnemies() {
		return npcEnemies;
	}

	@Override
	public String toString() {
		Set<Crew.Faction> set = getNonZeroShips();
		String list = "";

		Iterator<Crew.Faction> it = set.iterator();
		Crew.Faction faction;
		int status;
		String name;
		for (int i = 0; i < set.size(); ++i) {
			faction = it.next();
			name = faction.toString();
			status = ships.get(faction);
			name = String.format("%2d %s", status, name);
			list += (i + 1 < set.size()) ? name + "\n" : name;
		}

		String string;
		// string = String.format("name %s %s coin: %2d %n%s", rep.toString(),
		// type.toString(), coin, list);
		string = String.format("name %s %s %ntier: %2d || rep: %2d || coin: %3d", rep.toString(), type.toString(), tier,
				exp, coin);

		return string;
	}

	public String simplifiedToString() {
		String string = String.format("%s (tier %d)", name, tier);

		return string;
	}

	public List<Faction>[] nonNeutralStatus() {
		List<Faction>[] array = (List<Faction>[]) new ArrayList[6];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Faction>();
		}

		Set<Faction> set = new HashSet<Faction>(ships.keySet());
		Faction faction;
		int status;
		for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
			faction = it.next();
			status = ships.get(faction);

			if (status + 3 < 3) {
				array[status + 3].add(faction);
			} else if (status + 3 > 3) {
				array[status + 2].add(faction);
			}
		}

		return array;
	}

	public List<Faction> alliesList() {
		List<Faction> list = new ArrayList<Faction>();
		Set<Faction> set = new HashSet<Faction>(ships.keySet());
		Faction faction;
		for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
			faction = it.next();
			if (ships.get(faction) > 2)
				list.add(faction);
		}

		return list;
	}

	public List<Faction> friendliesList() {
		List<Faction> list = new ArrayList<Faction>();
		Set<Faction> set = new HashSet<Faction>(ships.keySet());
		Faction faction;
		for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
			faction = it.next();
			if (ships.get(faction) > 0)
				list.add(faction);
		}

		return list;
	}

	public List<Faction> hostilesList() {
		List<Faction> list = new ArrayList<Faction>();
		Set<Faction> set = new HashSet<Faction>(ships.keySet());
		Faction faction;
		for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
			faction = it.next();
			if (ships.get(faction) < 0)
				list.add(faction);
		}

		return list;
	}

	public List<Faction> enemiesList() {
		List<Faction> list = new ArrayList<Faction>();
		Set<Faction> set = new HashSet<Faction>(ships.keySet());
		Faction faction;
		for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
			faction = it.next();
			if (ships.get(faction) < -2)
				list.add(faction);
		}

		return list;
	}

	// static methods
	public static ArrayList<Crew> getFactions() {
		return factions;
	}

	public static Crew getCrewByFaction(Faction faction) {
		int length = factions.size();

		String name;
		Crew crew = null;
		for (int i = 0; i < length; ++i) {
			crew = factions.get(i);
			name = crew.name.toString();
			if (name.endsWith(faction.toString())) {
				break;
			}
		}

		return crew;
	}

	public static Faction getFactionNameByString(String string) {
		Faction[] array = ALL_FACTIONS;
		Faction faction = null;

		for (int i = 0; i < array.length; ++i) {
			// ignore "Faction."
			if (array[i].toString().endsWith(string))
				faction = array[i];
		}

		return faction;
	}

	public static Crew randomFaction() {
		int length = factions.size();
		Crew[] array = new Crew[length];
		for (int i = 0; i < length; ++i) {
			array[i] = factions.get(i);
		}

		Crew choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Faction randomFactionEnum() {
		Faction[] array = ALL_FACTIONS;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Faction randomCitizenryEnum() {
		Faction[] array = CITIZENRY;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Faction randomInstitutionEnum() {
		Faction[] array = INSTITUTIONS;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Faction randomLaborTradeEnum() {
		Faction[] array = LABOR_TRADE;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Faction randomFringeEnum() {
		Faction[] array = THE_FRINGE;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Faction randomUnderworldEnum() {
		Faction[] array = UNDERWORLD;
		Faction choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Type randomCrewType() {
		Type[] array = ALL_TYPES;
		Type choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static District randomDistrict() {
		District[] array = ALL_DISTRICTS;
		District choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Rep randomReputation() {
		Rep[] array = ALL_REPS;
		Rep choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomUpgrade() {
		Upgrade[] array = GENERIC_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomAssassinUpgrade() {
		Upgrade[] array = ASSASSIN_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomBravosUpgrade() {
		Upgrade[] array = BRAVOS_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomCultUpgrade() {
		Upgrade[] array = CULT_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomHawkerUpgrade() {
		Upgrade[] array = HAWKERS_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomShadowUpgrade() {
		Upgrade[] array = SHADOWS_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Upgrade randomSmugglerUpgrade() {
		Upgrade[] array = SMUGGLERS_UPGRADES;
		Upgrade choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	/*
	 * COHORTS - INNER CLASS
	 */

	private class Cohort {
		CohortType type;

		Cohort(CohortType type) {
			this.type = type;
		}

		boolean isExpert() {
			return (type.equals(CohortType.EXPERT));
		}

		boolean isGang() {
			return (type.equals(CohortType.EXPERT) != true);
		}
	}

	/*
	 * COMPARATOR - INNER CLASSES
	 */
	class TiersDescending implements Comparator<Crew> {
		@Override
		public int compare(Crew crew1, Crew crew2) {
			return crew1.tier - crew2.tier;
		}
	}
}
