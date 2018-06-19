
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
	private EnumSet<Score.Claim> claims;
	private int turf;
	//
	private EnumSet<Faction> allies;
	private EnumSet<Faction> enemies;

	//
	private int heat;
	private int wantedLevel;
	private boolean atWar;
	private HashMap<Crew.Faction, Integer> ships;

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
		this.claims = EnumSet.noneOf(Score.Claim.class);
		claims.add(Score.Claim.LAIR);
		this.turf = 0;
		//
		this.allies = EnumSet.noneOf(Crew.Faction.class);
		this.enemies = EnumSet.noneOf(Crew.Faction.class);

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
			shipSetup.add(randomFaction());
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
		this.claims = EnumSet.noneOf(Score.Claim.class);
		claims.add(Score.Claim.LAIR);
		//
		this.allies = EnumSet.noneOf(Crew.Faction.class);
		if (listAllies.length > 0) {
			for (Crew.Faction el : listAllies)
				allies.add(el);
		}

		this.enemies = EnumSet.noneOf(Crew.Faction.class);
		if (listEnemies.length > 0) {
			for (Crew.Faction el : listEnemies)
				enemies.add(el);
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

	public Type getCrewType() {
		return type;
	}

	public Set<Rep> getReputation() {
		return rep;
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
		return allies;
	}

	public Set<Faction> getEnemies() {
		return enemies;
	}

	@Override
	public String toString() {
		Set<Crew.Faction> set = getNonZeroShips();
		String s = "";
		
		Iterator<Crew.Faction> it = set.iterator();
		Crew.Faction faction;
		int status;
		String name;
		for (int i = 0; i < set.size(); ++i) {
			faction = it.next();
			name = faction.toString();
			status = ships.get(faction);
			name = String.format("%2d %s", status, name);
			s += (i + 1 < set.size()) ? name + "\n" : name;
		}
		
		String string = String.format("name %s %s coin: %2d %n%s", rep.toString(), type.toString(), coin, s);

		return string;
	}

	// static methods
	public static ArrayList<Crew> getFactions() {
		return factions;
	}

	public static Faction randomFaction() {
		Faction[] array = ALL_FACTIONS;
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
}