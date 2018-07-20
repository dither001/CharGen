import java.util.HashMap;
import java.util.Iterator;

public enum Asset {
	// FORCE
	SECURITY_PERSONNEL, HITMEN, MILITIA_UNIT, BASE_FORCE, HEAVY_DROP_ASSETS, ELITE_SKIRMISHERS, HARDENED_PERSONNEL, GUERILLA_POPULACE, ZEALOTS, CUNNING_TRAP, COUNTERINTEL_UNIT, BEACHHEAD_LANDERS, EXTENDED_THEATER, STRIKE_FLEET, POSTECH_INFANTRY, BLOCKADE_FLEET, PRETECH_LOGISTICS, PSYCHIC_ASSASSINS, PRETECH_INFANTRY, PLANETARY_DEFENSES, GRAVTANK_FORMATION, DEEP_STRIKE_LANDERS, INTEGRAL_PROTOCOLS, SPACE_MARINES, CAPITAL_FLEET,
	// CUNNING
	SMUGGLERS, INFORMERS, FALSE_FRONT, BASE_CUNNING, LOBBYISTS, SABOTEURS, BLACKMAIL, SEDUCTRESS, CYBERNINJAS, STEALTH, COVERT_SHIPPING, PARTY_MACHINE, VANGUARD_CADRES, TRIPWIRE_CELLS, SEDITIONISTS, ORGANIZATION_MOLES, CRACKED_COMMS, BOLTHOLES, TRANSPORT_LOCKDOWN, COVERT_TRANSIT_NET, DEMAGOGUE, POPULAR_MOVEMENT, BOOK_OF_SECRETS, TREACHERY, PANOPTICON_MATRIX,
	// WEALTH
	FRANCHISE, HARVESTERS, LOCAL_INVESTMENTS, BASE_WEALTH, FREIGHTER_CONTRACT, LAWYERS, UNION_TOUGHS, SURVEYORS, POSTECH_INDUSTRY, LABORATORY, MERCENARIES, SHIPPING_COMBINE, MONOPOLY, MEDICAL_CENTER, BANK, MARKETERS, PRETECH_RESEARCHERS, BLOCKADE_RUNNERS, VENTURE_CAPITAL, RND_DEPARTMENT, COMMODITIES_BROKER, PRETECH_MANUFACTORY, HOSTILE_TAKEOVER, TRANSIT_WEB, SCAVENGER_FLEET
	// END
	;

	public enum Type {
		//
		FORCE, CUNNING, WEALTH,
		//
		MILITARY, EXPERT, SPECIAL, FACILITY, TACTIC, STARSHIP
	}

	public static class Prototype {
		private static HashMap<Asset, Prototype> prototypes;

		private Asset asset;
		private byte tier;
		private Type category;
		private Type type;

		private byte hitPoints;
		private byte cost;
		private byte techLevel;

		private Attack attack;
		private Attack counter;

		public Prototype(Asset asset, Type category, Type type, byte tier, byte hitPoints, byte cost, byte techLevel,
				Attack attack, Attack counter) {

			this.asset = asset;
			this.category = category;
			this.type = type;
			this.tier = tier;

			this.hitPoints = hitPoints;
			this.cost = cost;
			this.techLevel = techLevel;

			this.attack = attack;
			this.counter = counter;
		}

		public Prototype(Asset asset, Type category, Type type, int tier, int hitPoints, int cost, int techLevel,
				Attack attack, Attack counter) {
			this(asset, category, type, (byte) tier, (byte) hitPoints, (byte) cost, (byte) techLevel, attack, counter);
		}

		static {
			prototypes = new HashMap<Asset, Prototype>();

			/*
			 * FORCE ASSET PROTOTYPES
			 */
			prototypes.put(SECURITY_PERSONNEL, new Prototype(Asset.SECURITY_PERSONNEL, Type.FORCE, Type.MILITARY, 1, 3,
					2, 0, new Attack(Type.FORCE, Type.FORCE, 1, 3, 1), new Attack(1, 4, 0)));
			prototypes.put(HITMEN, new Prototype(Asset.HITMEN, Type.FORCE, Type.EXPERT, 1, 1, 2, 0,
					new Attack(Type.FORCE, Type.CUNNING, 1, 6, 0), null));
			prototypes.put(MILITIA_UNIT, new Prototype(Asset.MILITIA_UNIT, Type.FORCE, Type.MILITARY, 1, 4, 4, 3,
					new Attack(Type.FORCE, Type.FORCE, 1, 6, 0), new Attack(1, 4, 1)));
			prototypes.put(BASE_FORCE,
					new Prototype(Asset.BASE_FORCE, Type.FORCE, Type.SPECIAL, 1, 0, 0, 0, null, null));
			prototypes.put(HEAVY_DROP_ASSETS,
					new Prototype(Asset.HEAVY_DROP_ASSETS, Type.FORCE, Type.FACILITY, 2, 6, 4, 4, null, null));
			prototypes.put(ELITE_SKIRMISHERS, new Prototype(Asset.ELITE_SKIRMISHERS, Type.FORCE, Type.MILITARY, 2, 5, 5,
					4, new Attack(Type.FORCE, Type.FORCE, 2, 4, 0), new Attack(1, 4, 1)));
			prototypes.put(HARDENED_PERSONNEL, new Prototype(Asset.HARDENED_PERSONNEL, Type.FORCE, Type.EXPERT, 2, 4, 4,
					3, null, new Attack(1, 4, 1)));
			prototypes.put(GUERILLA_POPULACE, new Prototype(Asset.GUERILLA_POPULACE, Type.FORCE, Type.MILITARY, 2, 6, 4,
					0, new Attack(Type.FORCE, Type.CUNNING, 1, 4, 1), null));
			prototypes.put(ZEALOTS, new Prototype(Asset.ZEALOTS, Type.FORCE, Type.EXPERT, 3, 4, 6, 0,
					new Attack(Type.FORCE, Type.FORCE, 2, 6, 0), new Attack(2, 6, 0)));
			prototypes.put(CUNNING_TRAP,
					new Prototype(Asset.CUNNING_TRAP, Type.FORCE, Type.TACTIC, 3, 2, 5, 0, null, new Attack(1, 6, 3)));
			prototypes.put(COUNTERINTEL_UNIT, new Prototype(Asset.COUNTERINTEL_UNIT, Type.FORCE, Type.EXPERT, 3, 4, 6,
					4, new Attack(Type.CUNNING, Type.CUNNING, 1, 4, 1), new Attack(1, 6, 0)));
			prototypes.put(BEACHHEAD_LANDERS,
					new Prototype(Asset.BEACHHEAD_LANDERS, Type.FORCE, Type.FACILITY, 4, 10, 10, 4, null, null));
			prototypes.put(EXTENDED_THEATER,
					new Prototype(Asset.EXTENDED_THEATER, Type.FORCE, Type.FACILITY, 4, 10, 10, 4, null, null));
			prototypes.put(STRIKE_FLEET, new Prototype(Asset.STRIKE_FLEET, Type.FORCE, Type.STARSHIP, 4, 8, 12, 4,
					new Attack(Type.FORCE, Type.FORCE, 2, 6, 0), new Attack(1, 8, 0)));
			prototypes.put(POSTECH_INFANTRY, new Prototype(Asset.POSTECH_INFANTRY, Type.FORCE, Type.MILITARY, 4, 12, 8,
					4, new Attack(Type.FORCE, Type.FORCE, 1, 8, 0), new Attack(1, 8, 0)));
			prototypes.put(BLOCKADE_FLEET, new Prototype(Asset.BLOCKADE_FLEET, Type.FORCE, Type.STARSHIP, 5, 8, 10, 4,
					new Attack(Type.FORCE, Type.WEALTH, 1, 6, 0), null));
			prototypes.put(PRETECH_LOGISTICS,
					new Prototype(Asset.PRETECH_LOGISTICS, Type.FORCE, Type.FACILITY, 5, 6, 14, 0, null, null));
			prototypes.put(PSYCHIC_ASSASSINS, new Prototype(Asset.PSYCHIC_ASSASSINS, Type.FORCE, Type.EXPERT, 5, 4, 12,
					4, new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 2), null));
			prototypes.put(PRETECH_INFANTRY, new Prototype(Asset.PRETECH_INFANTRY, Type.FORCE, Type.MILITARY, 6, 16, 20,
					5, new Attack(Type.FORCE, Type.FORCE, 2, 8, 0), new Attack(2, 8, 2)));
			prototypes.put(PLANETARY_DEFENSES, new Prototype(Asset.PLANETARY_DEFENSES, Type.FORCE, Type.FACILITY, 6, 20,
					18, 4, null, new Attack(2, 6, 6)));
			prototypes.put(GRAVTANK_FORMATION, new Prototype(Asset.GRAVTANK_FORMATION, Type.FORCE, Type.MILITARY, 6, 14,
					25, 5, new Attack(Type.FORCE, Type.FORCE, 2, 10, 4), new Attack(1, 10, 0)));
			prototypes.put(DEEP_STRIKE_LANDERS,
					new Prototype(Asset.DEEP_STRIKE_LANDERS, Type.FORCE, Type.FACILITY, 7, 10, 25, 5, null, null));
			prototypes.put(INTEGRAL_PROTOCOLS, new Prototype(Asset.INTEGRAL_PROTOCOLS, Type.FORCE, Type.FACILITY, 7, 10,
					20, 5, null, new Attack(2, 8, 2)));
			prototypes.put(SPACE_MARINES, new Prototype(Asset.SPACE_MARINES, Type.FORCE, Type.MILITARY, 7, 16, 30, 5,
					new Attack(Type.FORCE, Type.FORCE, 2, 8, 2), new Attack(2, 8, 0)));
			prototypes.put(CAPITAL_FLEET, new Prototype(Asset.CAPITAL_FLEET, Type.FORCE, Type.STARSHIP, 8, 30, 40, 5,
					new Attack(Type.FORCE, Type.FORCE, 3, 10, 4), new Attack(3, 8, 0)));

			/*
			 * CUNNING ASSET PROTOTYPES
			 */
			prototypes.put(SMUGGLERS, new Prototype(Asset.SMUGGLERS, Type.FORCE, Type.STARSHIP, 1, 4, 2, 4,
					new Attack(Type.CUNNING, Type.WEALTH, 1, 4, 0), null));
			prototypes.put(INFORMERS, new Prototype(Asset.INFORMERS, Type.FORCE, Type.EXPERT, 1, 3, 2, 0,
					new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(FALSE_FRONT,
					new Prototype(Asset.FALSE_FRONT, Type.FORCE, Type.FACILITY, 1, 2, 1, 0, null, null));
			prototypes.put(BASE_CUNNING,
					new Prototype(Asset.BASE_CUNNING, Type.FORCE, Type.SPECIAL, 1, 0, 0, 0, null, null));
			prototypes.put(LOBBYISTS, new Prototype(Asset.LOBBYISTS, Type.FORCE, Type.EXPERT, 2, 4, 4, 0,
					new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(SABOTEURS, new Prototype(Asset.SABOTEURS, Type.FORCE, Type.EXPERT, 2, 6, 5, 0,
					new Attack(Type.CUNNING, Type.CUNNING, 2, 4, 0), null));
			prototypes.put(BLACKMAIL, new Prototype(Asset.BLACKMAIL, Type.FORCE, Type.TACTIC, 2, 4, 4, 0,
					new Attack(Type.CUNNING, Type.CUNNING, 1, 4, 1), null));
			prototypes.put(SEDUCTRESS, new Prototype(Asset.SEDUCTRESS, Type.FORCE, Type.EXPERT, 2, 4, 4, 0,
					new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(CYBERNINJAS, new Prototype(Asset.CYBERNINJAS, Type.FORCE, Type.EXPERT, 3, 4, 6, 4,
					new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 0), null));
			prototypes.put(STEALTH, new Prototype(Asset.STEALTH, Type.FORCE, Type.TACTIC, 3, 0, 3, 0, null, null));
			prototypes.put(COVERT_SHIPPING,
					new Prototype(Asset.COVERT_SHIPPING, Type.FORCE, Type.FACILITY, 3, 4, 8, 4, null, null));
			prototypes.put(PARTY_MACHINE, new Prototype(Asset.PARTY_MACHINE, Type.FORCE, Type.FACILITY, 4, 10, 10, 0,
					new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 0), new Attack(1, 6, 0)));
			prototypes.put(VANGUARD_CADRES, new Prototype(Asset.VANGUARD_CADRES, Type.FORCE, Type.MILITARY, 4, 12, 8, 3,
					new Attack(Type.CUNNING, Type.CUNNING, 1, 6, 0), new Attack(1, 6, 0)));
			prototypes.put(TRIPWIRE_CELLS, new Prototype(Asset.TRIPWIRE_CELLS, Type.FORCE, Type.EXPERT, 4, 8, 12, 4,
					null, new Attack(1, 4, 0)));
			prototypes.put(SEDITIONISTS,
					new Prototype(Asset.SEDITIONISTS, Type.FORCE, Type.EXPERT, 4, 8, 12, 0, null, null));
			prototypes.put(ORGANIZATION_MOLES, new Prototype(Asset.ORGANIZATION_MOLES, Type.FORCE, Type.TACTIC, 5, 8,
					10, 0, new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 0), null));
			prototypes.put(CRACKED_COMMS,
					new Prototype(Asset.CRACKED_COMMS, Type.FORCE, Type.TACTIC, 5, 6, 14, 0, null, null));
			prototypes.put(BOLTHOLES,
					new Prototype(Asset.BOLTHOLES, Type.FORCE, Type.FACILITY, 5, 6, 12, 4, null, new Attack(2, 6, 0)));
			prototypes.put(TRANSPORT_LOCKDOWN, new Prototype(Asset.TRANSPORT_LOCKDOWN, Type.FORCE, Type.TACTIC, 6, 10,
					20, 4, new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(COVERT_TRANSIT_NET,
					new Prototype(Asset.COVERT_TRANSIT_NET, Type.FORCE, Type.FACILITY, 6, 15, 18, 4, null, null));
			prototypes.put(DEMAGOGUE, new Prototype(Asset.DEMAGOGUE, Type.FORCE, Type.EXPERT, 6, 10, 20, 0,
					new Attack(Type.CUNNING, Type.CUNNING, 2, 8, 0), new Attack(1, 8, 0)));
			prototypes.put(POPULAR_MOVEMENT, new Prototype(Asset.POPULAR_MOVEMENT, Type.FORCE, Type.TACTIC, 7, 16, 25,
					4, new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 0), new Attack(1, 6, 0)));
			prototypes.put(BOOK_OF_SECRETS, new Prototype(Asset.BOOK_OF_SECRETS, Type.FORCE, Type.TACTIC, 7, 10, 20, 4,
					null, new Attack(2, 8, 0)));
			prototypes.put(TREACHERY, new Prototype(Asset.TREACHERY, Type.FORCE, Type.TACTIC, 7, 5, 10, 0,
					new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(PANOPTICON_MATRIX, new Prototype(Asset.PANOPTICON_MATRIX, Type.FORCE, Type.FACILITY, 8, 20,
					30, 5, null, new Attack(1, 6, 0)));

			/*
			 * WEALTH ASSET PROTOTYPES
			 */
			prototypes.put(FRANCHISE, new Prototype(Asset.FRANCHISE, Type.FORCE, Type.FACILITY, 1, 3, 2, 2,
					new Attack(Type.WEALTH, Type.WEALTH, 1, 4, 0), new Attack(1, 4, -1)));
			prototypes.put(HARVESTERS,
					new Prototype(Asset.HARVESTERS, Type.FORCE, Type.FACILITY, 1, 4, 2, 0, null, new Attack(1, 4, 0)));
			prototypes.put(LOCAL_INVESTMENTS, new Prototype(Asset.LOCAL_INVESTMENTS, Type.FORCE, Type.FACILITY, 1, 2, 1,
					2, new Attack(Type.WEALTH, Type.WEALTH, 1, 4, -1), null));
			prototypes.put(BASE_WEALTH,
					new Prototype(Asset.BASE_WEALTH, Type.FORCE, Type.SPECIAL, 1, 0, 0, 0, null, null));
			prototypes.put(FREIGHTER_CONTRACT, new Prototype(Asset.FREIGHTER_CONTRACT, Type.FORCE, Type.STARSHIP, 2, 4,
					5, 4, new Attack(Type.WEALTH, Type.WEALTH, 1, 4, 0), null));
			prototypes.put(LAWYERS, new Prototype(Asset.LAWYERS, Type.FORCE, Type.EXPERT, 2, 4, 6, 0,
					new Attack(Type.CUNNING, Type.WEALTH, 2, 4, 0), new Attack(1, 6, 0)));
			prototypes.put(UNION_TOUGHS, new Prototype(Asset.UNION_TOUGHS, Type.FORCE, Type.MILITARY, 2, 6, 4, 0,
					new Attack(Type.WEALTH, Type.FORCE, 1, 4, 1), new Attack(1, 4, 0)));
			prototypes.put(SURVEYORS,
					new Prototype(Asset.SURVEYORS, Type.FORCE, Type.EXPERT, 2, 4, 4, 4, null, new Attack(1, 4, 0)));
			prototypes.put(POSTECH_INDUSTRY, new Prototype(Asset.POSTECH_INDUSTRY, Type.FORCE, Type.FACILITY, 3, 4, 8,
					4, null, new Attack(1, 4, 0)));
			prototypes.put(LABORATORY,
					new Prototype(Asset.LABORATORY, Type.FORCE, Type.FACILITY, 3, 4, 6, 0, null, null));
			prototypes.put(MERCENARIES, new Prototype(Asset.MERCENARIES, Type.FORCE, Type.MILITARY, 3, 6, 8, 4,
					new Attack(Type.WEALTH, Type.FORCE, 2, 4, 2), new Attack(1, 6, 0)));
			prototypes.put(SHIPPING_COMBINE, new Prototype(Asset.SHIPPING_COMBINE, Type.FORCE, Type.FACILITY, 4, 10, 10,
					4, null, new Attack(1, 6, 0)));
			prototypes.put(MONOPOLY, new Prototype(Asset.MONOPOLY, Type.FORCE, Type.FACILITY, 4, 12, 8, 3,
					new Attack(Type.WEALTH, Type.WEALTH, 1, 6, 0), new Attack(1, 6, 0)));
			prototypes.put(MEDICAL_CENTER,
					new Prototype(Asset.MEDICAL_CENTER, Type.FORCE, Type.FACILITY, 4, 8, 12, 4, null, null));
			prototypes.put(BANK, new Prototype(Asset.BANK, Type.FORCE, Type.FACILITY, 4, 8, 12, 3, null, null));
			prototypes.put(MARKETERS, new Prototype(Asset.MARKETERS, Type.FORCE, Type.TACTIC, 5, 8, 10, 0,
					new Attack(Type.CUNNING, Type.WEALTH, 1, 6, 0), null));
			prototypes.put(PRETECH_RESEARCHERS,
					new Prototype(Asset.PRETECH_RESEARCHERS, Type.FORCE, Type.EXPERT, 5, 6, 14, 4, null, null));
			prototypes.put(BLOCKADE_RUNNERS, new Prototype(Asset.BLOCKADE_RUNNERS, Type.FORCE, Type.STARSHIP, 5, 6, 12,
					4, null, new Attack(2, 4, 0)));
			prototypes.put(VENTURE_CAPITAL, new Prototype(Asset.VENTURE_CAPITAL, Type.FORCE, Type.FACILITY, 6, 10, 15,
					4, new Attack(Type.WEALTH, Type.WEALTH, 2, 6, 0), new Attack(1, 6, 0)));
			prototypes.put(RND_DEPARTMENT,
					new Prototype(Asset.RND_DEPARTMENT, Type.FORCE, Type.FACILITY, 6, 15, 18, 4, null, null));
			prototypes.put(COMMODITIES_BROKER, new Prototype(Asset.COMMODITIES_BROKER, Type.FORCE, Type.EXPERT, 6, 10,
					20, 0, new Attack(Type.WEALTH, Type.WEALTH, 2, 8, 0), new Attack(1, 8, 0)));
			prototypes.put(PRETECH_MANUFACTORY,
					new Prototype(Asset.PRETECH_MANUFACTORY, Type.FORCE, Type.FACILITY, 7, 16, 25, 5, null, null));
			prototypes.put(HOSTILE_TAKEOVER, new Prototype(Asset.HOSTILE_TAKEOVER, Type.FORCE, Type.TACTIC, 7, 10, 20,
					4, new Attack(Type.WEALTH, Type.WEALTH, 2, 10, 0), new Attack(2, 8, 0)));
			prototypes.put(TRANSIT_WEB, new Prototype(Asset.TRANSIT_WEB, Type.FORCE, Type.FACILITY, 7, 5, 15, 5,
					new Attack(Type.CUNNING, Type.CUNNING), null));
			prototypes.put(SCAVENGER_FLEET, new Prototype(Asset.SCAVENGER_FLEET, Type.FORCE, Type.STARSHIP, 8, 20, 30,
					5, new Attack(Type.WEALTH, Type.WEALTH, 2, 10, 4), new Attack(2, 10, 0)));

		}

		@Override
		public String toString() {
			String string = String.format("%s (tier-%d) %s %s", asset, tier, category, type);

			return string;
		}

		/*
		 * STATIC METHODS
		 */
		public static Iterator<Prototype> iterator() {
			return prototypes.values().iterator();
		}

	}

	public static class Attack {
		private Type offense;
		private Type defense;

		private byte dice;
		private byte face;
		private byte bonus;

		public Attack() {
			this(null, null, 0, 0, 0);
		}

		public Attack(Type offense, Type defense) {
			this(offense, defense, 0, 0, 0);
		}

		public Attack(byte dice, byte face, byte bonus) {
			this(null, null, dice, face, bonus);
		}

		public Attack(Type offense, Type defense, byte dice, byte face, byte bonus) {
			this.offense = offense;
			this.defense = defense;

			this.dice = dice;
			this.face = face;
			this.bonus = bonus;
		}

		public Attack(Type offense, Type defense, int i, int j, int k) {
			this(offense, defense, (byte) i, (byte) j, (byte) k);
		}

		public Attack(int i, int j, int k) {
			this(null, null, (byte) i, (byte) j, (byte) k);
		}
	}

	/*
	 * STATIC FIELDS
	 */

	/*
	 * INITIALIZATION
	 */

	/*
	 * STATIC METHODS
	 * 
	 */
}
