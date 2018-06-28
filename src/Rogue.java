import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Rogue {
	public enum Playbook {
		CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER
	}

	public enum Rating {
		ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK
	}

	public enum Vice {
		FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD
	}

	// static fields
	private static final Playbook[] PLAYBOOKS = { Playbook.CUTTER, Playbook.HOUND, Playbook.LEECH, Playbook.LURK,
			Playbook.SLIDE, Playbook.SPIDER, Playbook.WHISPER };

	private static final Rating[] RATINGS = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.FINESSE,
			Rating.HUNT, Rating.PROWL, Rating.SKIRMISH, Rating.STUDY, Rating.SURVEY, Rating.SWAY, Rating.TINKER,
			Rating.WRECK };
	private static final Rating[] INSIGHT = { Rating.HUNT, Rating.STUDY, Rating.SURVEY, Rating.TINKER };
	private static final Rating[] PROWESS = { Rating.FINESSE, Rating.PROWL, Rating.SKIRMISH, Rating.WRECK };
	private static final Rating[] RESOLVE = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.SWAY };

	private static final Vice[] VICES = { Vice.FAITH, Vice.GAMBLING, Vice.LUXURY, Vice.OBLIGATION, Vice.PLEASURE,
			Vice.STUPOR, Vice.WEIRD };

	// instance fields
	private String name;
	private Playbook playbook;
	private HashMap<Rating, Integer> attributes;

	//
	private Vice vice;

	//
	private int playbookXP;
	private int insightXP;
	private int prowessXP;
	private int resolveXP;

	// constructors
	public Rogue() {
		// TODO
		this.name = "Default";
		this.playbook = randomPlaybook();

		// requires playbook
		this.attributes = attributesInit(playbook);
		this.playbookXP = 0;
		this.insightXP = 0;
		this.prowessXP = 0;
		this.resolveXP = 0;

		this.vice = randomVice();
	}

	public int getInsight() {
		Rating[] array = INSIGHT;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getProwess() {
		Rating[] array = PROWESS;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getResolve() {
		Rating[] array = RESOLVE;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public String toStringDetailed() {
		String ratings = "";

		Rating rating;
		for (Iterator<Rating> it = attributes.keySet().iterator(); it.hasNext();) {
			rating = it.next();
			ratings += rating + " (" + attributes.get(rating) + ")";
			if (it.hasNext())
				ratings += ", ";
		}

		String string = String.format("%s %s (%s) %nInsight: %d || Prowess: %d || Resolve: %d %n%s", name, playbook,
				vice, getInsight(), getProwess(), getResolve(), ratings);

		return string;
	}

	// static methods
	private static HashMap<Rating, Integer> attributesInit(Playbook playbook) {
		HashMap<Rating, Integer> init = new HashMap<Rating, Integer>();

		if (playbook.equals(Playbook.CUTTER)) {
			init.put(Rating.SKIRMISH, 2);
			init.put(Rating.COMMAND, 1);

		} else if (playbook.equals(Playbook.HOUND)) {
			init.put(Rating.HUNT, 2);
			init.put(Rating.SURVEY, 1);

		} else if (playbook.equals(Playbook.LEECH)) {
			init.put(Rating.TINKER, 2);
			init.put(Rating.WRECK, 1);

		} else if (playbook.equals(Playbook.LURK)) {
			init.put(Rating.PROWL, 2);
			init.put(Rating.FINESSE, 1);

		} else if (playbook.equals(Playbook.SLIDE)) {
			init.put(Rating.SWAY, 2);
			init.put(Rating.CONSORT, 1);

		} else if (playbook.equals(Playbook.SPIDER)) {
			init.put(Rating.CONSORT, 2);
			init.put(Rating.STUDY, 1);

		} else if (playbook.equals(Playbook.WHISPER)) {
			init.put(Rating.ATTUNE, 2);
			init.put(Rating.STUDY, 1);

		}

		int points = 4, dice;
		Rating choice;
		while (points > 0) {
			dice = (points > 1 && Dice.roll(2) == 1) ? 2 : 1;
			choice = randomRating();

			if (init.containsKey(choice) != true) {
				init.put(choice, dice);
				points -= dice;
			}
		}

		return init;
	}

	public static Playbook randomPlaybook() {
		return Dice.randomFromArray(PLAYBOOKS);
	}

	public static Rating randomRating() {
		return Dice.randomFromArray(RATINGS);
	}

	public static Rating randomInsight() {
		return Dice.randomFromArray(INSIGHT);
	}

	public static Rating randomProwess() {
		return Dice.randomFromArray(PROWESS);
	}

	public static Rating randomResolve() {
		return Dice.randomFromArray(RESOLVE);
	}

	public static Vice randomVice() {
		return Dice.randomFromArray(VICES);
	}

}
