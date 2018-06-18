
import java.util.EnumSet;
import java.util.HashSet;
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

	// static fields
	private static final Type[] ALL_TYPES = { Type.ASSASSINS, Type.BRAVOS, Type.CULT, Type.HAWKERS, Type.SHADOWS,
			Type.SMUGGLERS };
	private static final Rep[] ALL_REPS = { Rep.AMBITIOUS, Rep.BRUTAL, Rep.DARING, Rep.HONORABLE, Rep.PROFESSIONAL,
			Rep.SAVVY, Rep.SUBTLE, Rep.STRANGE };
	private static final District[] ALL_DISTRICTS = { District.BARROWCLEFT, District.BRIGHTSTONE, District.CHARHOLLOW,
			District.CHARTERHALL, District.COALRIDGE, District.CROWS_FOOT, District.THE_DOCKS, District.DUNSLOUGH,
			District.NIGHTMARKET, District.SILKSHORE, District.SIX_TOWERS, District.WHITECROWN };

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
	private int heat;
	private int wantedLevel;
	private boolean atWar;

	/*
	 * Hunting grounds provide +1d6 gather information and a free downtime activity
	 * to contribute to an operation of your preferred type; when you claim turf,
	 * you expand the size and/or type of your hunting grounds
	 */
	private String huntingGrounds;
	private int huntingGroundSize;
	private String operation;
	private HashSet<Score.Activity> favoredOps;

	// constructors
	public Crew() {
		// TODO - create additional constructors
		this.type = randomCrewType();
		this.tier = 0;
		this.holdStrong = true;

		//
		this.rep = new HashSet<Rep>();
		rep.add(randomReputation());

		//
		this.claims = EnumSet.noneOf(Score.Claim.class);
		claims.add(Score.Claim.LAIR);
		this.turf = 0;

		//
		this.heat = 0;
		this.wantedLevel = 0;
		this.atWar = false;

		//
		this.huntingGroundSize = 1;
		this.favoredOps = new HashSet<Score.Activity>();
		favoredOps.add(Score.randomActivity(type));
	}

	public Crew(String name, Estate estate, int tier, boolean hold) {
		this.name = name;
		this.estate = estate;
		this.tier = tier;
		this.holdStrong = hold;
	}

	// methods
	public void advance() {
		boolean canAdvance = true;

		if (holdStrong != true)
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

	public Type getCrewType() {
		return type;
	}

	public Set<Rep> getReputation() {
		return rep;
	}

	public boolean readyForAdvance() {
		boolean ready = false;

		ready = true;

		return ready;
	}

	public boolean holdStrong() {
		return holdStrong;
	}

	public boolean holdWeak() {
		return (holdStrong != true);
	}

	public boolean atWar() {
		return atWar;
	}

	public boolean atPeace() {
		return (atWar != true);
	}

	@Override
	public String toString() {
		String string = String.format("name %s %s", rep.toString(), type.toString());

		return string;
	}

	// static methods
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
