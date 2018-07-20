import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
			prototypes.put(SECURITY_PERSONNEL, new Prototype(Asset.SECURITY_PERSONNEL, Type.FORCE, Type.MILITARY, 1, 3, 2,
					0, new Attack(Type.FORCE, Type.FORCE, 1, 3, 1), new Attack(1, 4, 0)));
			prototypes.put(HITMEN, new Prototype(Asset.HITMEN, Type.FORCE, Type.EXPERT, 1, 1, 2, 0,
					new Attack(Type.FORCE, Type.CUNNING, 1, 6, 0), null));
			prototypes.put(MILITIA_UNIT, new Prototype(Asset.MILITIA_UNIT, Type.FORCE, Type.MILITARY, 1, 4, 4, 3,
					new Attack(Type.FORCE, Type.FORCE, 1, 6, 0), new Attack(1, 4, 1)));
			prototypes.put(BASE_FORCE, new Prototype(Asset.BASE_FORCE, Type.FORCE, Type.SPECIAL, 1, 0, 0, 0, null, null));
			prototypes.put(HEAVY_DROP_ASSETS,
					new Prototype(Asset.HEAVY_DROP_ASSETS, Type.FORCE, Type.FACILITY, 2, 6, 4, 4, null, null));
			prototypes.put(ELITE_SKIRMISHERS, new Prototype(Asset.ELITE_SKIRMISHERS, Type.FORCE, Type.MILITARY, 2, 5, 5, 4,
					new Attack(Type.FORCE, Type.FORCE, 2, 4, 0), new Attack(1, 4, 1)));
			prototypes.put(HARDENED_PERSONNEL, new Prototype(Asset.HARDENED_PERSONNEL, Type.FORCE, Type.EXPERT, 2, 4, 4, 3,
					null, new Attack(1, 4, 1)));
			prototypes.put(GUERILLA_POPULACE, new Prototype(Asset.GUERILLA_POPULACE, Type.FORCE, Type.MILITARY, 2, 6, 4, 0,
					new Attack(Type.FORCE, Type.CUNNING, 1, 4, 1), null));
			prototypes.put(ZEALOTS, new Prototype(Asset.ZEALOTS, Type.FORCE, Type.EXPERT, 3, 4, 6, 0,
					new Attack(Type.FORCE, Type.FORCE, 2, 6, 0), new Attack(2, 6, 0)));
			prototypes.put(CUNNING_TRAP,
					new Prototype(Asset.CUNNING_TRAP, Type.FORCE, Type.TACTIC, 3, 2, 5, 0, null, new Attack(1, 6, 3)));
			prototypes.put(COUNTERINTEL_UNIT, new Prototype(Asset.COUNTERINTEL_UNIT, Type.FORCE, Type.EXPERT, 3, 4, 6, 4,
					new Attack(Type.CUNNING, Type.CUNNING, 1, 4, 1), new Attack(1, 6, 0)));
			prototypes.put(BEACHHEAD_LANDERS,
					new Prototype(Asset.BEACHHEAD_LANDERS, Type.FORCE, Type.FACILITY, 4, 10, 10, 4, null, null));
			prototypes.put(EXTENDED_THEATER,
					new Prototype(Asset.EXTENDED_THEATER, Type.FORCE, Type.FACILITY, 4, 10, 10, 4, null, null));
			prototypes.put(STRIKE_FLEET, new Prototype(Asset.STRIKE_FLEET, Type.FORCE, Type.STARSHIP, 4, 8, 12, 4,
					new Attack(Type.FORCE, Type.FORCE, 2, 6, 0), new Attack(1, 8, 0)));
			prototypes.put(POSTECH_INFANTRY, new Prototype(Asset.POSTECH_INFANTRY, Type.FORCE, Type.MILITARY, 4, 12, 8, 4,
					new Attack(Type.FORCE, Type.FORCE, 1, 8, 0), new Attack(1, 8, 0)));
			prototypes.put(BLOCKADE_FLEET, new Prototype(Asset.BLOCKADE_FLEET, Type.FORCE, Type.STARSHIP, 5, 8, 10, 4,
					new Attack(Type.FORCE, Type.WEALTH, 1, 6, 0), null));
			prototypes.put(PRETECH_LOGISTICS,
					new Prototype(Asset.PRETECH_LOGISTICS, Type.FORCE, Type.FACILITY, 5, 6, 14, 0, null, null));
			prototypes.put(PSYCHIC_ASSASSINS, new Prototype(Asset.PSYCHIC_ASSASSINS, Type.FORCE, Type.EXPERT, 5, 4, 12, 4,
					new Attack(Type.CUNNING, Type.CUNNING, 2, 6, 2), null));
			prototypes.put(PRETECH_INFANTRY, new Prototype(Asset.PRETECH_INFANTRY, Type.FORCE, Type.MILITARY, 6, 16, 20, 5,
					new Attack(Type.FORCE, Type.FORCE, 2, 8, 0), new Attack(2, 8, 2)));
			prototypes.put(PLANETARY_DEFENSES, new Prototype(Asset.PLANETARY_DEFENSES, Type.FORCE, Type.FACILITY, 6, 20, 18,
					4, null, new Attack(2, 6, 6)));
			prototypes.put(GRAVTANK_FORMATION, new Prototype(Asset.GRAVTANK_FORMATION, Type.FORCE, Type.MILITARY, 6, 14, 25,
					5, new Attack(Type.FORCE, Type.FORCE, 2, 10, 4), new Attack(1, 10, 0)));
			prototypes.put(DEEP_STRIKE_LANDERS,
					new Prototype(Asset.DEEP_STRIKE_LANDERS, Type.FORCE, Type.FACILITY, 7, 10, 25, 5, null, null));
			prototypes.put(INTEGRAL_PROTOCOLS, new Prototype(Asset.INTEGRAL_PROTOCOLS, Type.FORCE, Type.FACILITY, 7, 10, 20,
					5, null, new Attack(2, 8, 2)));
			prototypes.put(SPACE_MARINES, new Prototype(Asset.SPACE_MARINES, Type.FORCE, Type.MILITARY, 7, 16, 30, 5,
					new Attack(Type.FORCE, Type.FORCE, 2, 8, 2), new Attack(2, 8, 0)));
			prototypes.put(CAPITAL_FLEET, new Prototype(Asset.CAPITAL_FLEET, Type.FORCE, Type.STARSHIP, 8, 30, 40, 5,
					new Attack(Type.FORCE, Type.FORCE, 3, 10, 4), new Attack(3, 8, 0)));

			/*
			 * CUNNING ASSET PROTOTYPES
			 */
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

		public Attack(Type force, Type force2, int i, int j, int k) {
			this(force, force2, (byte) i, (byte) j, (byte) k);
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
