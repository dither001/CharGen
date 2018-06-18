import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	private static int PCS_TO_ROLL = 50;
	private static int SPELLBOOK_LEVEL = 17;

	//
	private static final Scanner INPUT = new Scanner(System.in);
	private static int proceed = 1;

	public static void main(String[] args) {
		// TODO

		printFactionStuff();

		// Crew crew = new Crew();
		// while (proceed == 1) {
		// rollScore(crew);
		//
		//
		// proceed = Integer.parseInt(INPUT.nextLine());
		// if (proceed != 0)
		// proceed = 1;
		// }

		// for (int i = 0; i < PCS_TO_ROLL; ++i) {
		// scoresByLength();
		// System.out.println();
		// }

		// Weapon[] weapons = Weapon.handsDescendingArray();
		// for (Weapon el : weapons)
		// System.out.println(el.toString());

		// levelUpTest();

		// setupActorLadder();

		// encounterSpread();

		// for (int i = 0; i < PCS_TO_ROLL; ++i) {
		// rollOneSpellbook(7);
		// }

	}

	public static void printFactionStuff() {
		List<Crew> list = Crew.getFactions();
		for (Iterator<Crew> it = list.iterator(); it.hasNext();) {
			System.out.println(it.next().getAllies().toString());
		}
	}

	public static void characterAdvance(Class job) {
		Actor test = new Actor();
		while (test.getJob().equals(job) != true) {
			test = new Actor();
		}

		rollCharacter(test);
		System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));

		for (int i = 0; test.getLevel() < 20 && i < 50; ++i) {
			test.gainEXP(((i + 1000) * (i + 1)) + test.getEXP());
			test.advance();
			System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));
			rollCharacter(test);
		}
	}

	public static void spellbookTest() {
		Actor test = new Actor();
		while (test.getJob().equals(Class.WIZARD) != true) {
			test = new Actor();
		}

		rollCharacter(test);
		System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));

		for (int i = 0; test.getLevel() < 20 && i < 50; ++i) {
			test.gainEXP(((i + 1000) * (i + 1)) + test.getEXP());
			test.advance();
			System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));
			rollCharacter(test);
		}
	}

	public static void levelUpTest() {
		Actor test = new Actor();
		while (test.getJob().equals(Class.BARBARIAN) != true) {
			test = new Actor();
		}

		rollCharacter(test);
		System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));

		for (int i = 0; test.getLevel() < 20 && i < 50; ++i) {
			test.gainEXP(((i + 1000) * (i + 1)) + test.getEXP());
			test.advance();
			System.out.println(String.format("%2d: %7d", test.getLevel(), test.getEXP()));
			rollCharacter(test);
		}
	}

	public static void setupActorLadder() {
		Vector<Actor> actors = new Vector<Actor>();
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			actors.add(new Actor());
		}

		Ladder<Actor> ladder = new Ladder<Actor>();
		for (int i = 0; i < actors.size(); ++i) {
			ladder.add(actors.get(i));

			// if (i > 20)
			// System.out.println(ladder.size());
		}

		// System.out.println();
		// System.out.println(ladder.toString());
		System.out.println();
		System.out.println("Hierarch: " + ladder.getHierarch().toString());
		System.out.println();

		// test contains
		// int contains = 0;
		// for (int i = 0; i < actors.size(); ++i) {
		// contains += (ladder.contains(actors.get(i))) ? 1 : 0;
		// }
		// System.out.println("Ladder contains " + contains + " of " + actors.size() + "
		// actors added.");

		// for (int i = 0; i < 10; ++i) {
		// ladder.updateTurn();
		//
		// System.out.println();
		// System.out.println(ladder.toString());
		// }

		int previous = 0;
		while (ladder.hierarchTurns() < 20) {
			ladder.update();

			System.out.println();
			if (ladder.hierarchTurns() == previous) {
				// System.out.println(ladder.toString());
				++previous;
			}
		}
		ladder.printTurnsDescending();

		// System.out.println(ladder.readyToAct().toString());

	}

	public static void agendaReaction() {
		System.out.println("Agenda: " + Encounter.randomAgenda());
		System.out.println("Reaction: " + Encounter.reaction());
		System.out.println();
	}

	public static void encounterSpread() {
		int one, two, three, seven, eleven, fifteen;
		for (int i = 1; i <= 20; ++i) {
			one = Encounter.mediumEncounter(i, 1);
			two = Encounter.mediumEncounter(i, 2);
			three = Encounter.mediumEncounter(i, 3);
			seven = Encounter.mediumEncounter(i, 7);
			eleven = Encounter.mediumEncounter(i, 11);
			fifteen = Encounter.mediumEncounter(i, 15);
			// System.out.println("One " + one + " || Two " + two);
			System.out.printf("level %2d: one %2d || two %2d || three %2d || seven %2d || eleven %2d || fifteen %2d%n",
					i, one, two, three, seven, eleven, fifteen);
		}
	}

	public static void rollVillainousPlot() {
		System.out.println(Career.randomMeans());
		System.out.println(Career.randomMotive());
		System.out.println(Career.randomOpportunity());
		System.out.println();
	}

	public static void rollOneSpellbook() {
		rollOneSpellbook(SPELLBOOK_LEVEL);
	}

	public static void rollOneSpellbook(int bookLevel) {
		HashSet<Spells> spellbook = Spells.randomWizardSpellbook(bookLevel);
		Spells[][] spellsByLevel = new Spells[10][8];

		Spells current;
		int level;
		for (Iterator<Spells> it = spellbook.iterator(); it.hasNext();) {
			current = it.next();
			level = current.getLevel();

			for (int i = 0; i < 8; ++i) {
				if (spellsByLevel[level][i] == null) {
					spellsByLevel[level][i] = current;
					break;
				}
			}
		}

		int spellsPrinted;
		for (Spells[] em : spellsByLevel) {
			spellsPrinted = 0;
			for (Spells el : em) {
				if (el != null) {
					++spellsPrinted;
					System.out.print(el.toString() + ", ");
				}
			}

			if (spellsPrinted > 0)
				System.out.println();
		}

		System.out.println();
	}

	public static void rollCrew() {
		Crew crew = new Crew();
		System.out.println(crew.toString());
	}

	public static void rollScore() {
		Crew crew = new Crew();
		rollScore(crew);
	}

	public static void rollScore(Crew crew) {
		System.out.println(crew.toString());
		Score score = new Score(crew);
		System.out.println(score.toString());
		score.action();
	}

	public static void scoresByLength() {
		double scoresToRoll = 1000;
		int totalScenes = 0;
		int[] scores = new int[50];

		for (int i = 0; i < scores.length; ++i) {
			scores[i] = 0;
		}

		for (int i = 0; i < scoresToRoll; ++i) {
			++scores[simulateScore()];
		}

		String string = "";
		for (int i = 1; i < scores.length; ++i) {
			string = String.format("Actions (%2d): %4d (%6.1f%%)", i, scores[i], scores[i] / scoresToRoll * 100);
			if (scores[i] > 0) {
				totalScenes += scores[i];
				System.out.println(string);
			}
		}

		System.out.println("Total:        " + totalScenes);
	}

	public static int simulateScore() {
		int finalScore;
		Crew crew = new Crew();
		System.out.println(crew.toString());
		System.out.println();
		Score score = new Score(crew);
		while (score.unresolved()) {
			score.advance();
		}
		finalScore = score.getScene();
		// System.out.println("Scene count: " + finalScore);

		return finalScore;
	}

	public static void rollCharacter() {
		rollCharacter(new Actor());
	}

	public static void rollCharacter(Class job) {
		Actor test = new Actor();
		while (test.getJob().equals(job) != true) {
			test = new Actor();
		}

		rollCharacter(test);
	}

	public static void rollArcaneTrickster() {
		Actor test = new Actor();
		while (test.getArchetype().equals(Class.Subclass.ARCANE_TRICKSTER) != true) {
			test = new Actor();
		}

		rollCharacter(test);
	}

	public static void rollEldritchKnight() {
		Actor test = new Actor();
		while (test.getArchetype().equals(Class.Subclass.ELDRITCH_KNIGHT) != true) {
			test = new Actor();
		}

		rollCharacter(test);
	}

	public static void rollCharacter(Actor actor) {
		int ac = actor.getArmorClass(), hp = actor.getHitPoints();
		int atk = actor.getAttackBonus(), dmg = actor.getAverageDamage();

		int rating = ChallengeRating.evaluateCR(actor);
		int dCR = ChallengeRating.defenseRating(hp, ac);
		int oCR = ChallengeRating.offenseRating(atk, dmg);

		System.out.print("Name");
		System.out.print(" " + actor.getAli().toString());
		System.out.print(" " + actor.getRace().toString());
		System.out.print(" " + actor.getCareer().toString());
		System.out.print(" " + actor.getJob().toString());
		System.out.print(" (" + actor.getArchetype().toString() + ")");
		System.out.print(" " + actor.getDeity().toString());
		System.out.print(" (" + actor.getEXPRate() + ")");

		// System.out.println();
		// System.out.print(actor.getTraitOne() + "\n");
		// System.out.print(actor.getTraitTwo() + "\n");
		// System.out.print(actor.getIdeal() + "\n");
		// System.out.print(actor.getBond() + "\n");
		// System.out.print(actor.getFlaw());

		System.out.println("");
		// String s = String.format("AC %2d hp %3d atk +%-2d (%2d) || oCR %2d dCR %2d",
		// ac, hp, atk, dmg, oCR, dCR);
		String s = String.format("AC %2d hp %3d atk %2d (%2d) || CR %2d", ac, hp, atk, dmg, rating);
		System.out.print(s);
		System.out.print(" || exp " + ChallengeRating.challengeToXP(rating));

		if (actor.getInventory().equippedWeapon()) {
			GameWeapon weapon = actor.getInventory().getMainHand();

			System.out.println("");
			System.out.print("Wielding " + weapon.toString());

			Weapon.Trait[] traits = weapon.getBasicTraits();
			if (traits.length > 0) {
				for (int i = 0; i < traits.length; ++i) {
					if (i == 0)
						System.out.print(" [" + traits[i]);

					if (i > 0)
						System.out.print(", " + traits[i]);

					if (i == traits.length - 1)
						System.out.print("]");
				}
			}
		}

		if (actor.getInventory().equippedOffHand()) {
			GameWeapon weapon = actor.getInventory().getOffHand();

			System.out.println("");
			System.out.print("Wielding " + weapon.toString());

			Weapon.Trait[] traits = weapon.getBasicTraits();
			if (traits.length > 0) {
				for (int i = 0; i < traits.length; ++i) {
					if (i == 0)
						System.out.print(" [" + traits[i]);

					if (i > 0)
						System.out.print(", " + traits[i]);

					if (i == traits.length - 1)
						System.out.print("]");
				}
			}
		}

		// TODO - used below
		Spellcasting spellcasting = actor.getSpellcasting();
		// HashSet<Proficiency> skills = Skills.filterForSkills(actor);

		System.out.println("");
		System.out.print(actor.getAbilities().toString());
		// System.out.println("");
		// System.out.print(actor.getInventory().toString());
		System.out.println("");
		System.out.print(actor.getSkills().toString());
		System.out.println("");
		System.out.print(actor.getFeatures().toString());
		if (spellcasting != null) {
			if (spellcasting.hasSpells()) {
				System.out.println("");
				System.out.println("Spells known: " + spellcasting.getSpellsKnown().size());
				System.out.println(spellcasting.getSpellsKnown().toString());
			}
			if (spellcasting.hasCantrips())
				System.out.println(actor.getCantrips().toString());
		}

		System.out.println("");
		System.out.println("");

	}

}
