import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Score {
	public enum Goal {
		// CLIMB from "social climb;" SHAKE from "shaking someone else"
		CLIMB, CLAIM, SHAKE
	}

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

	private static final Effect[] INCITING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] RISING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] TURNING_EFFECTS = { Effect.GREAT, Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] FALLING_EFFECTS = { Effect.GREAT, Effect.STANDARD };

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
	private Crew client;
	private Crew target;
	private Goal goal;
	private Plan plan;
	private Activity activity;

	//
	private Clock window;
	private Clock openingMove;
	private Clock primaryObjective;
	private Clock getawayMove;

	//
	private Act act;
	private int scene;
	private int tension;
	private Approach[][] beats;

	private ArrayList<Action> actions;

	// constructors
	public Score(Crew crew, Crew client, Crew target) {
		this(crew, client, target, Goal.CLIMB);
	}

	public Score(Crew crew, Crew client, Crew target, Goal goal) {
		/*
		 * TODO - I need to figure out (at some point) how to differentiate between a
		 * job given by another crew and a job given by a single patron
		 */
		this.crew = crew;
		this.client = client;
		this.target = target;
		this.goal = goal;
		this.plan = randomPlan();
		this.activity = randomActivity(crew.getCrewType());

		//
		this.window = new Clock(Clock.Length.FOUR);
		this.openingMove = new Clock(Clock.Length.FOUR);
		this.primaryObjective = new Clock();
		this.getawayMove = new Clock();

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
				if (openingMove.expired()) {
					actions.add(new Action(primaryObjective, start));

				} else {
					actions.add(new Action(openingMove, start));

				}
			} else {
				// TODO - DEBUG
				// objectiveCheck();
				if (getawayMove.expired()) {
					/*
					 * FIXME - currently here for debug only; replace with "end score"
					 */
					// actions.add(new Action(getawayMove, randomPosition()));
					System.out.println("Clean getaway.");
					break;

				} else if (primaryObjective.expired()) {
					actions.add(new Action(getawayMove, randomPosition()));

				} else if (openingMove.expired()) {
					actions.add(new Action(primaryObjective, randomPosition()));

				} else {
					actions.add(new Action(openingMove, randomPosition()));

				}
			}

			// while mission not resolved, advance()
			advance();
		}

		if (window.expired())
			System.out.println(" " + " " + " Window closed.");

		// TODO
		System.out.println();
		new Downtime(this);
	}

	public boolean advance() {
		boolean advance = false;

		if (window.expired() != true) {
			int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
			if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
				window.countDown();
				advance = true;
				// while I still use the act structure...
				act = nextAct();
			}
		}

		// if (act.equals(Act.RELEASE) != true) {
		// int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
		// // System.out.println(String.format("Scene: %2d", scene));
		//
		// if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
		// act = nextAct();
		// advance = true;
		// System.out.println(act.toString());
		// }
		// }

		return advance;
	}

	public Position engagementRoll() {
		/*
		 * TODO - work out advantages and disadvantages and "actual" dice
		 */
		Position position;
		int[] results = Dice.fortune(Dice.roll(3));
		// System.out.println("Engagement roll.");

		if (results[5] > 1) {
			// critical result clears the opening
			position = Position.CONTROLLED;
			openingMove.clear();

		} else if (results[5] > 0) {
			// success results in controlled situation
			position = Position.CONTROLLED;

		} else if (results[3] > 0 || results[4] > 0) {
			// partial results in risky position
			position = Position.RISKY;

		} else {
			// failure results in desperate situation
			position = Position.DESPERATE;

		}

		// System.out.println(position);
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

	public boolean patronage() {
		return (crew.sameAs(client) != true);
	}

	public boolean unresolved() {
		// return (act.equals(Act.RELEASE) != true);
		return (window.expired() != true);
	}

	public void objectiveCheck() {
		if (getawayMove.expired()) {
			System.out.println(" " + " " + " We're clear.");

		} else if (primaryObjective.expired()) {
			System.out.println(" " + " " + " Primary objective complete.");

		} else if (openingMove.expired()) {
			System.out.println(" " + " " + " We're in.");

		} else {
			System.out.println(" " + " " + " Approaching objective.");

		}
	}

	@Override
	public String toString() {
		String string = String.format("name %s [%s] %s", plan.toString(), detail(plan), activity.toString());
		string += "\nTarget: " + target.simplifiedToString();

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
		// Activity[] array = ALL_ACTIVITIES;
		// Activity choice = array[Dice.roll(array.length) - 1];

		return Dice.randomFromArray(ALL_ACTIVITIES);
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
	 * ACTION - INNER CLASS
	 */
	private class Action {
		// fields
		Clock clock;
		Approach approach;
		Position position;
		Effect effect;

		//
		Result result;

		// constructors
		public Action(Clock clock) {
			this(clock, randomApproach(), randomPosition(), randomEffect());
		}

		public Action(Clock clock, Position position) {
			this(clock, pseudoRandomApproach(act, plan, crew, beats), position, effectsByAct(act));
		}

		public Action(Clock clock, Approach approach, Position position, Effect effect) {
			this.clock = clock;
			this.approach = approach;
			this.position = position;
			this.effect = effect;

			// TODO - need input from actor's attributes
			resolve(Dice.roll(3));
			// System.out.println(toString());
		}

		// methods
		public void resolve(int dice) {
			int[] results = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < dice; ++i) {
				++results[Dice.roll(6) - 1];
			}

			// PART ONE
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

			// PART TWO
			if (effect.equals(Effect.EXTREME)) {
				clock.countDown(4);
			} else if (effect.equals(Effect.GREAT)) {
				clock.countDown(3);

			} else if (effect.equals(Effect.STANDARD)) {
				clock.countDown(2);

			} else if (effect.equals(Effect.LIMITED)) {
				clock.countDown(1);

			} else if (effect.equals(Effect.ZERO)) {
				// FIXME - FOR DEBUG ONLY: NO EFFECT

			}
			// System.out.println(" " + " " + " Remaining: " + clock.remaining());

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
			string = String.format("Scene %2d: %s check -%s", scene, approach, result);

			// standard
			// string = String.format("Scene %2d: %s check -%s (%2d)", scene, approach,
			// result, tension);

			// verbose
			// string = String.format("Scene %2d: %s %s check -%s (%2d)", scene, position,
			// approach, result, tension);

			return string;
		}
	}

	/*
	 * DOWNTIME - INNER CLASS
	 */
	private class Downtime {
		public Downtime(Score score) {
			/*
			 * FIXME - this ArrayList is to keep track of EACH faction status that changes;
			 * eventually when I rewrite the system, there will be no such thing as a
			 * "zero reputation," because "everyone knows everybody." All that changes is
			 * which SIDE of the friend/enemy spectrum characters fall on at present
			 */
			ArrayList<Crew> changes = new ArrayList<Crew>();

			Crew crew = score.crew;
			Crew client = score.client;
			Crew target = score.target;

			// resolve client status
			if (patronage() && primaryObjective.expired()) {
				changes.add(client);
				crew.increaseShip(client);
				// TODO - testing
				System.out.println("Client (" + client.toString() + ") satisfied");
			} else if (patronage() && window.expired()) {
				changes.add(client);
				crew.decreaseShip(client);
				// TODO - testing
				System.out.println("Client (" + client.toString() + ") disappointed");
			}

			// additional reputation changes
			Set<Crew> allies = target.npcAllyGet();
			// FIXME - testing
			for (Crew el : allies) {
				System.out.println(el);
			}

			Crew targetAlly;
			for (Iterator<Crew> it = allies.iterator(); it.hasNext();) {
				targetAlly = it.next();
				changes.add(targetAlly);
				crew.decreaseShip(targetAlly);
				System.out.println(targetAlly + " status decreased");

				// FIXME - testing
				// if (Dice.roll(2) == 1) {
				// // allies of the target like you less
				// changes.add(targetAlly);
				// crew.decreaseShip(targetAlly);
				// System.out.println(targetAlly + " status decreased");
				// }
			}

			Set<Crew> enemies = target.npcEnemyGet();
			// FIXME - testing
			for (Crew el : enemies) {
				System.out.println(el);
			}

			Crew targetEnemy;
			for (Iterator<Crew> it = enemies.iterator(); it.hasNext();) {

				targetEnemy = it.next();
				changes.add(targetEnemy);
				crew.increaseShip(targetEnemy);
				System.out.println(targetEnemy + " status increased");
				// FIXME - testing
				// if (Dice.roll(2) == 1) {
				// // enemies of the target like you more
				// changes.add(targetEnemy);
				// crew.increaseShip(targetEnemy);
				// System.out.println(targetEnemy + " status increased");
				// }
			}

			// rep boost
			int crewTier = crew.getTier();
			int targetTier = target.getTier();
			int exp = (targetTier - crewTier + 2 < 1) ? 0 : targetTier - crewTier + 2;
			if (exp > 0) {
				changes.add(target);
				crew.decreaseShip(target);
				System.out.println(target + " status decreased");

				//
				crew.addEXP(exp);
				System.out.println("Rep gained: " + exp);
			}

			// payoff
			if (score.primaryObjective.expired()) {
				// TODO - perform payoff
				int payoff = Dice.roll(2, 6);
				System.out.println("Coin gained: " + payoff);
				crew.addCoin(payoff);
			}

		}
	}
}
