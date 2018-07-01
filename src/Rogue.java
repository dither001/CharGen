import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Rogue {
	public enum Playbook {
		CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER
	}

	public enum Attribute {
		INSIGHT, PROWESS, RESOLVE
	}

	public enum Rating {
		ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK
	}

	public enum Vice {
		FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD
	}

	public enum Trauma {
		COLD, HAUNTED, OBSESSED, PARANOID, RECKLESS, SOFT, UNSTABLE, VICIOUS
	}

	// static fields
	private static final int MAX_STRESS = 9;

	private static final String[] NAMES = { "Adric", "Aldo", "Amosen", "Andrel", "Arden", "Arlyn", "Arquo", "Arvus",
			"Ashlyn", "Branon", "Brace", "Brance", "Brena", "Bricks", "Candra", "Carissa", "Carro", "Casslyn",
			"Cavelle", "Clave", "Corille", "Cross", "Crowl", "Cyrene", "Daphnia", "Drav", "Edlun", "Emeline", "Grine",
			"Helles", "Hix", "Holtz", "Kamelin", "Kelyr", "Kobb", "Kristov", "Laudius", "Lauria", "Lenia", "Lizete",
			"Lorette", "Lucella", "Lynthia", "Mara", "Milos", "Morlan", "Myre", "Narcus", "Naria", "Noggs", "Odrienne",
			"Orlan", "Phin", "Polonia", "Quess", "Remira", "Ring", "Roethe", "Sesereth", "Sethla", "Skannon", "Stavrul",
			"Stev", "Syra", "Talitha", "Tesslyn", "Thena", "Timoth", "Tocker", "Una", "Vaurin", "Veleris", "Veretta",
			"Vestine", "Vey", "Volette", "Vond", "Weaver", "Wester", "Zamira" };

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

	private static final Trauma[] TRAUMA_CONDITIONS = { Trauma.COLD, Trauma.HAUNTED, Trauma.OBSESSED, Trauma.PARANOID,
			Trauma.RECKLESS, Trauma.SOFT, Trauma.UNSTABLE, Trauma.VICIOUS };

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
	private int coin;
	private int stash;

	//
	private int stress;
	private int threshold;
	private boolean stressedOut;
	private EnumSet<Trauma> trauma;

	// constructors
	public Rogue() {
		// TODO
		this.name = randomName();
		this.playbook = randomPlaybook();

		// requires playbook
		this.attributes = attributesInit(playbook);
		this.playbookXP = 0;
		this.insightXP = 0;
		this.prowessXP = 0;
		this.resolveXP = 0;
		this.coin = 0;
		this.stash = 0;

		this.vice = randomVice();
		this.stress = 0;
		this.threshold = Dice.roll(4) + 5;
		this.stressedOut = false;
		this.trauma = EnumSet.noneOf(Trauma.class);
	}

	public int getAttribute(Attribute attribute) {
		int value = 0;
		if (attribute.equals(Attribute.INSIGHT))
			value = getInsight();
		else if (attribute.equals(Attribute.PROWESS))
			value = getProwess();
		else if (attribute.equals(Attribute.RESOLVE))
			value = getResolve();

		return value;
	}

	public Rating getRandomRogueRating() {
		Set<Rating> set = attributes.keySet();

		return Dice.randomFromSet(set);
	}

	public int getRating(Rating rating) {
		int score;
		if (attributes.containsKey(rating))
			score = attributes.get(rating);
		else
			score = 0;

		return score;
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

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getStash() {
		return stash;
	}

	public void setStash(int stash) {
		this.stash = stash;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		this.stress = stress;

		// TODO
		if (stress >= MAX_STRESS)
			stressedOut = true;
	}

	public int getThreshold() {
		return threshold;
	}

	public boolean stressedOut() {
		return stressedOut;
	}

	public boolean goodToGo() {
		return (stressedOut != true);
	}

	public void resolveStress() {
		if (stress >= MAX_STRESS) {
			stress = 0;
			stressedOut = false;

			Trauma candidate = randomTrauma();
			while (trauma.contains(candidate)) {
				candidate = randomTrauma();
			}

			// TODO - testing
			System.out.println(this + " gained the " + candidate + " trauma condition.");
			trauma.add(candidate);
		}
	}

	public EnumSet<Trauma> getTrauma() {
		return trauma;
	}

	@Override
	public String toString() {
		return String.format("%s the %s (%d)", name, playbook, stress);
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

		String string = String.format("%s the %s (Vice: %s) %nInsight: %d || Prowess: %d || Resolve: %d %n%s", name,
				playbook, vice, getInsight(), getProwess(), getResolve(), ratings);

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

	public static Rogue bestRogueForAction(Rating rating, List<Rogue> team) {
		/*
		 * TODO - create additional criteria for choosing the best rogue
		 * 
		 */
		Rogue candidate, choice = null;
		int number;

		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			candidate = it.next();
			number = candidate.getRating(rating);

			if (choice == null && candidate.goodToGo()) {
				choice = candidate;
			} else if (choice != null && number > choice.getRating(rating) && candidate.goodToGo()) {
				choice = candidate;
			}
		}

		return choice;
	}

	public static String randomName() {
		return Dice.randomFromArray(NAMES);
	}

	public static Playbook randomPlaybook() {
		return Dice.randomFromArray(PLAYBOOKS);
	}

	public static Attribute randomAttribute() {
		Attribute[] array = new Attribute[] { Attribute.INSIGHT, Attribute.PROWESS, Attribute.RESOLVE };
		return Dice.randomFromArray(array);
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

	public static Trauma randomTrauma() {
		return Dice.randomFromArray(TRAUMA_CONDITIONS);
	}

	/*
	 * COMPARATORS
	 * 
	 */
	public static class CoinAscending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue1.coin - rogue2.coin;
		}
	}

	public static class CoinDescending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue2.coin - rogue1.coin;
		}
	}

	public static class StressAscending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue1.stress - rogue2.stress;
		}
	}

	public static class StressDescending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue2.stress - rogue1.stress;
		}
	}
}
