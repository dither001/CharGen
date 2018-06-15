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

	private enum Act {
		INCITING, RISING, TURNING, FALLING, RELEASE
	}

	// static fields
	private static final int ACTIONS_PER_SCORE = 10;

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

	// fields
	private Plan plan;
	private Activity activity;

	private Act act;
	private int scene;

	private ArrayList<Action> actions;

	// constructors
	public Score() {
		this(Crew.randomCrewType());
	}

	public Score(Crew.Type crew) {
		this.plan = randomPlan();
		this.activity = randomActivity(crew);

		//
		this.act = Act.INCITING;
		this.scene = 1;
		this.actions = new ArrayList<Action>();
	}

	// methods
	public void action() {
		while (unresolved()) {
			if (scene == 1) {
				// every mission begins with engagement roll
				Position start = engagementRoll();
				actions.add(new Action(act, start));
			} else {
				actions.add(new Action(act, randomPosition()));
			}

			// while mission not resolved, advance()
			advance();
		}
	}

	public boolean advance() {
		boolean advance = false;

		if (act.equals(Act.RELEASE) != true) {
			int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE);
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

	public static Approach randomApproach() {
		Approach[] array = APPROACHES;
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
	public class Action {
		// fields
		Act act;
		Approach approach;
		Position position;
		Effect effect;

		// constructors
		public Action() {
			this(randomAct(), randomApproach(), randomPosition(), randomEffect());
		}

		public Action(Position position) {
			this(randomAct(), randomApproach(), position, randomEffect());
		}

		public Action(Act act, Position position) {
			this(act, randomApproach(), position, effectsByAct(act));
		}

		public Action(Act act, Approach approach, Position position, Effect effect) {
			this.act = act;
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
				System.out.println("Critical success.");
				this.improveEffect();
			} else if (results[5] > 0) {
				// TODO - success
				System.out.println("Success.");
			} else if (results[3] > 0 || results[4] > 0) {
				// partial success
				System.out.println("Partial success.");
			} else {
				// failure
				System.out.println("Failure.");
			}
		}

		public Approach getApproach() {
			return approach;
		}

		public Position getPosition() {
			return position;
		}

		public Effect getEffect() {
			return effect;
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
			String string = String.format("Scene %2d: %s check", scene, approach.toString());
			return string;
		}
	}
}
