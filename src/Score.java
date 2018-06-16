import java.util.ArrayList;

public class Score {
	public enum Plan {
		ASSAULT, DECEPTION, STEALTH, OCCULT, SOCIAL, TRANSPORT
	}

	public enum Activity {
		ACCIDENT, DISAPPEARANCE, MURDER, RANSOM, BATTLE, EXTORTION, SABOTAGE, SMASH_N_GRAB, ACQUISITION, AUGURY, CONSECRATION, SACRIFICE, SALES, SUPPLY, SHOW_OF_FORCE, SOCIALIZE, BURGLARY, ESPIONAGE, ROBBERY, CARGO_ARMS, CARGO_CONTRABAND, CARGO_PEOPLE, CARGO_WEIRD
	}

	public enum Approach {
		ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK
	}

	public enum Position {
		CONTROLLED, RISKY, DESPERATE
	}

	public enum Effect {
		EXTREME, GREAT, STANDARD, LIMITED, ZERO
	}

	public enum Act {
		INCITING, RISING, TURNING, FALLING, RELEASE
	}

	public enum Result {
		CRITICAL, SUCCESS, PARTIAL, FAILURE
	}

	public enum Claim {
		LAIR, TURF_1, TURF_2, TURF_3, TURF_4, TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM, VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS, BARRACKS, TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES, BLUECOAT_CONFEDERATES, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER, SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR, PERSONAL_CLOTHIER, LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B, INTERROGATION_CHAMBER, GAMBLING_DEN, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET
	}

	// static fields
	private static final int ACTIONS_PER_SCORE = 20;

	private static final Plan[] ALL_PLANS = { Plan.ASSAULT, Plan.DECEPTION, Plan.STEALTH, Plan.OCCULT, Plan.SOCIAL,
			Plan.TRANSPORT };
	private static final String[] DETAILS = { "Point of attack.", "Method of deception.", "Point of infiltration.",
			"Arcane method.", "Social connection.", "Route & means." };
	private static final Approach[] APPROACHES = { Approach.ATTUNE, Approach.COMMAND, Approach.CONSORT,
			Approach.FINESSE, Approach.HUNT, Approach.PROWL, Approach.SKIRMISH, Approach.STUDY, Approach.SURVEY,
			Approach.SWAY, Approach.TINKER, Approach.WRECK };
	private static final Position[] POSITIONS = { Position.CONTROLLED, Position.RISKY, Position.DESPERATE };
	private static final Effect[] EFFECTS = { Effect.EXTREME, Effect.GREAT, Effect.STANDARD, Effect.LIMITED,
			Effect.ZERO };
	private static final Act[] ACTS = { Act.INCITING, Act.RISING, Act.TURNING, Act.FALLING, Act.RELEASE };

	private static final Activity[] ALL_ACTIVITIES = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM, Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE, Activity.SMASH_N_GRAB,
			Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION, Activity.SACRIFICE, Activity.SALES,
			Activity.SUPPLY, Activity.SHOW_OF_FORCE, Activity.SOCIALIZE, Activity.BURGLARY, Activity.ESPIONAGE,
			Activity.ROBBERY, Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND, Activity.CARGO_PEOPLE,
			Activity.CARGO_WEIRD };

	private static final Activity[] ASSASSIN_FLAVORS = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM };
	private static final Activity[] BRAVO_FLAVORS = { Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE,
			Activity.SMASH_N_GRAB };
	private static final Activity[] CULT_FLAVORS = { Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION,
			Activity.SACRIFICE };
	private static final Activity[] HAWKER_FLAVORS = { Activity.SALES, Activity.SUPPLY, Activity.SHOW_OF_FORCE,
			Activity.SOCIALIZE };
	private static final Activity[] SHADOW_FLAVORS = { Activity.BURGLARY, Activity.ESPIONAGE, Activity.ROBBERY,
			Activity.SABOTAGE };
	private static final Activity[] SMUGGLER_FLAVORS = { Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND,
			Activity.CARGO_PEOPLE, Activity.CARGO_WEIRD };

	private static final Effect[] INCITING_EFFECTS = { Effect.STANDARD, Effect.LIMITED, Effect.LIMITED, Effect.ZERO };
	private static final Effect[] RISING_EFFECTS = { Effect.LIMITED, Effect.LIMITED, Effect.ZERO };
	private static final Effect[] TURNING_EFFECTS = { Effect.EXTREME, Effect.GREAT, Effect.STANDARD, Effect.STANDARD,
			Effect.LIMITED, Effect.LIMITED };
	private static final Effect[] FALLING_EFFECTS = { Effect.EXTREME, Effect.GREAT, Effect.GREAT, Effect.STANDARD,
			Effect.STANDARD };

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

	// approaches by crew type
	private static final Approach[] ASSASSIN_APPROACH = { Approach.FINESSE, Approach.HUNT, Approach.PROWL,
			Approach.SKIRMISH, Approach.STUDY, Approach.TINKER };
	private static final Approach[] BRAVO_APPROACH = { Approach.COMMAND, Approach.CONSORT, Approach.FINESSE,
			Approach.SKIRMISH, Approach.SURVEY, Approach.WRECK };
	private static final Approach[] CULT_APPROACH = { Approach.ATTUNE, Approach.COMMAND, Approach.CONSORT,
			Approach.PROWL, Approach.STUDY, Approach.TINKER };
	private static final Approach[] HAWKER_APPROACH = { Approach.COMMAND, Approach.CONSORT, Approach.FINESSE,
			Approach.SURVEY, Approach.SWAY, Approach.WRECK };
	private static final Approach[] SHADOW_APPROACH = { Approach.COMMAND, Approach.FINESSE, Approach.PROWL,
			Approach.SURVEY, Approach.TINKER, Approach.WRECK };
	private static final Approach[] SMUGGLER_APPROACH = { Approach.CONSORT, Approach.FINESSE, Approach.PROWL,
			Approach.SURVEY, Approach.SWAY, Approach.TINKER };

	// approaches by plan
	private static final Approach[] ASSAULT_APPROACHES = { Approach.COMMAND, Approach.HUNT, Approach.SKIRMISH,
			Approach.SURVEY, Approach.WRECK };
	private static final Approach[] DECEPTION_APPROACHES = { Approach.CONSORT, Approach.FINESSE, Approach.PROWL,
			Approach.STUDY, Approach.SURVEY, Approach.SWAY };
	private static final Approach[] OCCULT_APPROACHES = { Approach.ATTUNE, Approach.COMMAND, Approach.CONSORT,
			Approach.STUDY, Approach.SWAY, Approach.TINKER };
	private static final Approach[] SOCIAL_APPROACHES = { Approach.COMMAND, Approach.CONSORT, Approach.FINESSE,
			Approach.STUDY, Approach.SURVEY, Approach.SWAY };
	private static final Approach[] STEALTH_APPROACHES = { Approach.FINESSE, Approach.HUNT, Approach.PROWL,
			Approach.STUDY, Approach.TINKER, Approach.WRECK };
	private static final Approach[] TRANSPORT_APPROACHES = { Approach.COMMAND, Approach.CONSORT, Approach.FINESSE,
			Approach.HUNT, Approach.PROWL, Approach.SURVEY, Approach.SWAY };

	// approaches by tension level
	private static final Approach[] HIGH_TENSION_APPROACH = { Approach.COMMAND, Approach.HUNT, Approach.PROWL,
			Approach.SKIRMISH, Approach.SWAY, Approach.WRECK };
	private static final Approach[] LOW_TENSION_APPROACH = { Approach.ATTUNE, Approach.CONSORT, Approach.FINESSE,
			Approach.STUDY, Approach.SURVEY, Approach.TINKER };

	// fields
	private Crew crew;
	private Plan plan;
	private Activity activity;

	private Act act;
	private int scene;
	private int tension;
	private Approach[][] beats;

	private ArrayList<Action> actions;

	// constructors
	public Score(Crew crew) {
		this.crew = crew;
		this.plan = randomPlan();
		this.activity = randomActivity(crew.getCrewType());

		//
		this.act = Act.INCITING;
		this.scene = 1;
		this.tension = 0;
		this.beats = randomBeats();

		//
		this.actions = new ArrayList<Action>();
	}

	// methods
	public void action() {
		while (unresolved()) {
			if (scene == 1) {
				// every mission begins with engagement roll
				Position start = engagementRoll();
				actions.add(new Action(start));
			} else {
				actions.add(new Action(randomPosition()));
			}

			// while mission not resolved, advance()
			advance();
		}
	}

	public boolean advance() {
		boolean advance = false;

		if (act.equals(Act.RELEASE) != true) {
			int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
			// System.out.println(String.format("Scene: %2d", scene));

			if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
				act = nextAct();
				advance = true;
				System.out.println(act.toString());
			}
		}

		return advance;
	}

	public Position engagementRoll() {
		// TODO - work out advantages and disadvantages
		Position position = Position.RISKY;

		int dice = Dice.roll(6);
		if (dice == 6)
			position = Position.CONTROLLED;
		else if (dice == 4 || dice == 5)
			position = Position.RISKY;
		else if (dice == 1 || dice == 2 || dice == 3)
			position = Position.DESPERATE;

		return position;
	}

	public Act getAct() {
		return act;
	}

	public int getScene() {
		return scene;
	}

	public Act nextAct() {
		Act nextAct = Act.INCITING;

		if (act.equals(Act.INCITING))
			nextAct = Act.RISING;
		else if (act.equals(Act.RISING))
			nextAct = Act.TURNING;
		else if (act.equals(Act.TURNING))
			nextAct = Act.FALLING;
		else if (act.equals(Act.FALLING))
			nextAct = Act.RELEASE;

		return nextAct;
	}

	public boolean unresolved() {
		return (act.equals(Act.RELEASE) != true);
	}

	@Override
	public String toString() {
		String string = String.format("name %s [%s] %s", plan.toString(), detail(plan), activity.toString());

		return string;
	}

	// static methods
	public static Plan randomPlan() {
		Plan[] array = ALL_PLANS;
		Plan choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	private static String detail(Plan plan) {
		String string = "";

		if (plan.equals(Plan.ASSAULT))
			string = DETAILS[0];
		else if (plan.equals(Plan.DECEPTION))
			string = DETAILS[1];
		else if (plan.equals(Plan.STEALTH))
			string = DETAILS[2];
		else if (plan.equals(Plan.OCCULT))
			string = DETAILS[3];
		else if (plan.equals(Plan.SOCIAL))
			string = DETAILS[4];
		else if (plan.equals(Plan.TRANSPORT))
			string = DETAILS[5];

		return string;
	}

	public static Approach[][] randomBeats() {
		Approach[][] beats = new Approach[4][];

		for (int i = 0; i < beats.length; ++i) {
			beats[i] = (Dice.roll(2) == 1) ? HIGH_TENSION_APPROACH : LOW_TENSION_APPROACH;
		}

		return beats;
	}

	public static Activity randomActivity() {
		Activity[] array = ALL_ACTIVITIES;
		Activity choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Activity randomActivity(Crew.Type crew) {
		Activity[] array = ALL_ACTIVITIES;
		if (crew.equals(Crew.Type.ASSASSINS))
			array = ASSASSIN_FLAVORS;
		else if (crew.equals(Crew.Type.BRAVOS))
			array = BRAVO_FLAVORS;
		else if (crew.equals(Crew.Type.CULT))
			array = CULT_FLAVORS;
		else if (crew.equals(Crew.Type.HAWKERS))
			array = HAWKER_FLAVORS;
		else if (crew.equals(Crew.Type.SHADOWS))
			array = SHADOW_FLAVORS;
		else if (crew.equals(Crew.Type.SMUGGLERS))
			array = SMUGGLER_FLAVORS;

		Activity choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Approach approachByCrewType(Crew.Type crew) {
		Approach[] array = APPROACHES;
		Approach choice = randomApproach();

		if (crew.equals(Crew.Type.ASSASSINS))
			array = ASSASSIN_APPROACH;
		else if (crew.equals(Crew.Type.BRAVOS))
			array = BRAVO_APPROACH;
		else if (crew.equals(Crew.Type.CULT))
			array = CULT_APPROACH;
		else if (crew.equals(Crew.Type.HAWKERS))
			array = HAWKER_APPROACH;
		else if (crew.equals(Crew.Type.SHADOWS))
			array = SHADOW_APPROACH;
		else if (crew.equals(Crew.Type.SMUGGLERS))
			array = SMUGGLER_APPROACH;

		choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Approach approachByPlan(Plan plan) {
		Approach choice = randomApproach();

		if (plan.equals(Plan.ASSAULT))
			choice = assaultApproach();
		else if (plan.equals(Plan.DECEPTION))
			choice = deceptionApproach();
		else if (plan.equals(Plan.OCCULT))
			choice = occultApproach();
		else if (plan.equals(Plan.SOCIAL))
			choice = socialApproach();
		else if (plan.equals(Plan.STEALTH))
			choice = stealthApproach();
		else if (plan.equals(Plan.TRANSPORT))
			choice = transportApproach();

		return choice;
	}

	public static Approach randomApproach() {
		Approach[] array = APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach pseudoRandomApproach(Act act, Plan plan, Crew crew, Approach[][] beats) {
		Crew.Type type = crew.getCrewType();
		Approach choice;
		int dice = Dice.roll(100);
		if (dice < 21) {
			// System.out.println(" " + " " + " What are we doing?");
			choice = approachByCrewType(crew.getCrewType());
		} else if (dice < 61) {
			// System.out.println(" " + " " + " Stick to the plan.");
			choice = approachByPlan(plan);
		} else if (dice < 81) {
			// System.out.println(" " + " " + " We don't have time.");
			Approach[] array = APPROACHES;

			if (act.equals(Act.INCITING))
				array = beats[0];
			else if (act.equals(Act.RISING))
				array = beats[1];
			else if (act.equals(Act.TURNING))
				array = beats[2];
			else if (act.equals(Act.FALLING))
				array = beats[3];

			choice = array[Dice.roll(array.length) - 1];
		} else {
			// System.out.println(" " + " " + " Ninjas attack.");
			Approach[] array = APPROACHES;
			choice = array[Dice.roll(array.length) - 1];
		}

		return choice;
	}

	public static Approach assaultApproach() {
		Approach[] array = ASSAULT_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach deceptionApproach() {
		Approach[] array = DECEPTION_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach occultApproach() {
		Approach[] array = OCCULT_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach socialApproach() {
		Approach[] array = SOCIAL_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach stealthApproach() {
		Approach[] array = STEALTH_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Approach transportApproach() {
		Approach[] array = TRANSPORT_APPROACHES;
		Approach choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Position randomPosition() {
		Position[] array = POSITIONS;
		Position choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Effect effectsByAct(Act act) {
		Effect choice = randomEffect();

		if (act.equals(Act.INCITING))
			choice = incitingEffect();
		else if (act.equals(Act.RISING))
			choice = risingEffect();
		else if (act.equals(Act.TURNING))
			choice = turningEffect();
		else if (act.equals(Act.FALLING))
			choice = fallingEffect();

		return choice;
	}

	public static Effect randomEffect() {
		Effect[] array = EFFECTS;
		Effect choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Effect incitingEffect() {
		Effect[] array = INCITING_EFFECTS;
		Effect choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Effect risingEffect() {
		Effect[] array = RISING_EFFECTS;
		Effect choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Effect turningEffect() {
		Effect[] array = TURNING_EFFECTS;
		Effect choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Effect fallingEffect() {
		Effect[] array = FALLING_EFFECTS;
		Effect choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Act randomAct() {
		Act[] array = ACTS;
		Act choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	/*
	 * INNER CLASS
	 */
	private class Action {
		// fields
		Approach approach;
		Position position;
		Effect effect;

		//
		Result result;

		// constructors
		public Action() {
			this(randomApproach(), randomPosition(), randomEffect());
		}

		public Action(Position position) {
			this(pseudoRandomApproach(act, plan, crew, beats), position, effectsByAct(act));
		}

		public Action(Approach approach, Position position, Effect effect) {
			this.approach = approach;
			this.position = position;
			this.effect = effect;

			// TODO - need input from actor's attributes
			resolve(Dice.roll(3));
			System.out.println(toString());
		}

		// methods
		public void resolve(int dice) {
			int[] results = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < dice; ++i) {
				++results[Dice.roll(6) - 1];
			}

			if (results[5] > 1) {
				// TODO - critical
				result = Result.CRITICAL;
				this.improveEffect();
				tension -= 2;
			} else if (results[5] > 0) {
				// TODO - success
				result = Result.SUCCESS;
				tension -= 1;
			} else if (results[3] > 0 || results[4] > 0) {
				// partial success
				result = Result.PARTIAL;
				tension += 1;
			} else {
				// failure
				result = Result.FAILURE;
				tension += 2;
			}
		}

		public void improveEffect() {
			// boolean improved = false;
			// if (effect.equals(Effect.EXTREME) != true)
			// improved = true;

			if (effect.equals(Effect.GREAT))
				effect = Effect.EXTREME;
			else if (effect.equals(Effect.STANDARD))
				effect = Effect.GREAT;
			else if (effect.equals(Effect.LIMITED))
				effect = Effect.STANDARD;
			else if (effect.equals(Effect.ZERO))
				effect = Effect.LIMITED;

			// return improved;
		}

		@Override
		public String toString() {
			String string = String.format("Scene %2d", scene);

			// minimal
			string = String.format("Scene %2d: %s check (%2d)", scene, approach, tension);

			// standard
			// string = String.format("Scene %2d: %s check -%s (%2d)", scene, approach,
			// result, tension);

			// verbose
			// string = String.format("Scene %2d: %s %s check -%s (%2d)", scene, position,
			// approach, result, tension);

			return string;
		}
	}
}
