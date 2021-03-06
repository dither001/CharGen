package adapter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.dnd5e.definitions.*;
import com.norvendae.misc.Dice;
import com.starswonumber.deadnames.*;
import com.starswonumber.definitions.WorldTag;
import com.traveller1e.starships.MDrive;
import com.traveller1e.starships.Spaceship;
import com.traveller1e.worlds.Figure;
import com.traveller1e.worlds.Star;
import com.traveller1e.worlds.StarSystem;

import actor.*;
import actor.Class;

public class Main {
	private static int PCS_TO_ROLL = 50;

	//
	private static final Scanner INPUT = new Scanner(System.in);
	private static int proceed = 1;

	public static void main(String[] args) {
		// TODO

		// databaseStart();

		// testMainWorld();
		// testFigure();

		// System.out.println(Motivation.motivations().length);
		// testLostTypes();

		Madness madness = null;
		for (int i = 0; i < 50; ++i) {
			madness = Dice.randomFromArray(Madness.madnesses());
			System.out.println(madness.toString() + " (" + Madness.getIndex(madness) + ")");
		}

		// int maxLength = 0, current = 0;
		// for (WorldTag el : WorldTag.ALL_TAGS) {
		// current = el.toString().length();
		// maxLength = current > maxLength ? current : maxLength;
		// }
		// System.out.println(maxLength);

		// MDrive.prototypePrint();
		// for (int i = 0; i < 50; ++i) {
		// System.out.println(Spaceship.randomSpaceship().toStringDetailed());
		// System.out.println();
		// }

		// rollCharacters();
		// rollOneCharacter();
		// rollCharactersOfClass(Class.BARBARIAN);
		// rollCharactersOfArchetype(Class.Subclass.KNOWLEDGE);
		// levelUpTest(20);

		// for (int i = 0; i < 50; ++i) {
		// levelUpTestDisplayOnly(20, Class.WIZARD);
		// }

		// for (Pantheon el : Pantheon.getPantheons()) {
		// System.out.println(el.toString() + " (" + el.membership() + ")");
		// }

		// System.out.println(Immortal.beingsByGridToString());
		// System.out.println(Immortal.alignmentGridStatsToString());
		// System.out.println(Immortal.domainStatsToString());
		// Immortal.nameLength();

		// jobProportions();
		// raceProportions();
		// alignmentProportions();
		// deityProportions();
		// rollCharacters();

	}

	public static void databaseStart() {
		Controller controller = new Controller();
		controller.start();
	}

	public static void testFigure() {
		Figure group = new Figure();

		while (group.getMainWorld() == null) {
			group = new Figure();
		}

		System.out.println(group.toStringDetailed());
		System.out.println();
	}

	public static void testMainWorld() {
		StarSystem group = new StarSystem(0, 0, 0);

		while (group.mainWorld() == null) {
			group = new StarSystem(0, 0, 0);
		}

		System.out.println(group.toStringDetailed());
		System.out.println();
	}

	public static void testLostTypes() {
		double total = 10000;
		int[] types = new int[] { 0, 0, 0, 0 };
		for (int i = 0; i < total; ++i) {
			Lost lost = Lost.randomLost();

			if (lost.equals(Lost.TRANSHUMAN))
				++types[0];
			else if (lost.equals(Lost.EXTRATERRESTRIAL))
				++types[1];
			else if (lost.equals(Lost.SYNTHETIC))
				++types[2];
			else
				++types[3];
		}

		System.out.println("Transhuman " + (types[0] / total));
		System.out.println("Extraterrestrial " + (types[1] / total));
		System.out.println("Synthetic " + (types[2] / total));
		System.out.println("Metadimensional " + (types[3] / total));

	}

	public static void powersByAlignment() {
		Alignment[] ali = new Alignment[] { Alignment.LAWFUL, Alignment.GOOD, Alignment.NEUTRAL, Alignment.EVIL,
				Alignment.CHAOTIC };

		for (int i = 0; i < ali.length; ++i) {
			System.out.print(ali[i] + ": ");

			for (Iterator<?> it = Immortal.powersOfAlignment(ali[i]).iterator(); it.hasNext();) {
				System.out.print(it.next());
				if (it.hasNext())
					System.out.print(", ");
			}
			System.out.println();

		}

	}

	public static void levelUpTest(int level) {
		levelUpTest(level, Class.randomJob());
	}

	public static void levelUpTest(Class job) {
		levelUpTest(1, job);
	}

	public static void levelUpTest(int level, Class job) {
		Player player;
		player = new Player();
		while (player.getJob().equals(job) != true) {
			player = new Player();

		}

		for (int i = 1; i <= level; ++i) {
			System.out.println(player.toStringDetailed());
			System.out.println();

			player.setExp(500000);
			player.advance();
		}

		System.out.print("Done.");
	}

	public static void levelUpTestDisplayOnly(int level) {
		levelUpTestDisplayOnly(level, Class.randomJob());
	}

	public static void levelUpTestDisplayOnly(int level, Class job) {
		Player player;
		player = new Player();
		while (player.getJob().equals(job) != true) {
			player = new Player();
		}

		for (int i = 1; i < level; ++i) {
			player.setExp(500000);
			player.advance();
		}

		System.out.println(player.toStringDetailed());
		System.out.println();
	}

	public static void levelUpTestDisplayOnly(int level, Subclass job) {
		Player player;
		player = new Player();
		while (player.getArchetype().equals(job) != true) {
			player = new Player();
		}

		for (int i = 1; i < level; ++i) {
			player.setExp(500000);
			player.advance();
		}

		System.out.println(player.toStringDetailed());
		System.out.println();
	}

	public static void rollCharactersOfArchetype(Subclass job) {
		Player player;
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			player = new Player();
			while (player.getArchetype().equals(job) != true) {
				player = new Player();

			}
			System.out.println(player.toStringDetailed());
			System.out.println();
		}

		System.out.print("Done.");
	}

	public static void rollCharactersOfClass(Class job) {
		Player player;
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			player = new Player();
			while (player.getJob().equals(job) != true) {
				player = new Player();

			}
			System.out.println(player.toStringDetailed());
			System.out.println();
		}

		System.out.print("Done.");
	}

	public static void rollCharacters() {
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			rollOneCharacter();
		}
	}

	public static void rollOneCharacter() {
		Player player = new Player();
		System.out.println(player.toStringDetailed());
		System.out.println();
	}

	// public static void deityProportions() {
	// int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	// 0, 0, 0 };
	// Deity[] gods = new Deity[] { Deity.ASMODEUS, Deity.AVANDRA, Deity.BAHAMUT,
	// Deity.BANE, Deity.CORELLON,
	// Deity.ERATHIS, Deity.GRUUMSH, Deity.IOUN, Deity.KORD, Deity.LOLTH,
	// Deity.MELORA, Deity.MORADIN,
	// Deity.PELOR, Deity.RAVEN_QUEEN, Deity.SEHANINE, Deity.THARIZDUN,
	// Deity.TIAMAT, Deity.TOROG, Deity.VECNA,
	// Deity.ZEHIR };
	//
	// Player player;
	// for (int i = 0; i < PCS_TO_ROLL; ++i) {
	// player = new Player();
	//
	// if (player.getDeity().equals(Deity.ASMODEUS))
	// ++array[0];
	// else if (player.getDeity().equals(Deity.AVANDRA))
	// ++array[1];
	// else if (player.getDeity().equals(Deity.BAHAMUT))
	// ++array[2];
	// else if (player.getDeity().equals(Deity.BANE))
	// ++array[3];
	// else if (player.getDeity().equals(Deity.CORELLON))
	// ++array[4];
	// else if (player.getDeity().equals(Deity.ERATHIS))
	// ++array[5];
	// else if (player.getDeity().equals(Deity.GRUUMSH))
	// ++array[6];
	// else if (player.getDeity().equals(Deity.IOUN))
	// ++array[7];
	// else if (player.getDeity().equals(Deity.KORD))
	// ++array[8];
	// else if (player.getDeity().equals(Deity.LOLTH))
	// ++array[9];
	// else if (player.getDeity().equals(Deity.MELORA))
	// ++array[10];
	// else if (player.getDeity().equals(Deity.MORADIN))
	// ++array[11];
	// else if (player.getDeity().equals(Deity.PELOR))
	// ++array[12];
	// else if (player.getDeity().equals(Deity.RAVEN_QUEEN))
	// ++array[13];
	// else if (player.getDeity().equals(Deity.SEHANINE))
	// ++array[14];
	// else if (player.getDeity().equals(Deity.THARIZDUN))
	// ++array[15];
	// else if (player.getDeity().equals(Deity.TIAMAT))
	// ++array[16];
	// else if (player.getDeity().equals(Deity.TOROG))
	// ++array[17];
	// else if (player.getDeity().equals(Deity.VECNA))
	// ++array[18];
	// else if (player.getDeity().equals(Deity.ZEHIR))
	// ++array[19];
	// }
	//
	// for (int i = 0; i < gods.length; ++i) {
	// String string = String.format("%12s %5d (%5.1f)", gods[i], array[i], (0.0 +
	// array[i]) / PCS_TO_ROLL * 100);
	// System.out.println(string);
	// }
	// }

	public static void jobProportions() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		Player player;
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			player = new Player();

			if (player.getJob().equals(Class.BARBARIAN))
				++array[0];
			else if (player.getJob().equals(Class.BARD))
				++array[1];
			else if (player.getJob().equals(Class.CLERIC))
				++array[2];
			else if (player.getJob().equals(Class.DRUID))
				++array[3];
			else if (player.getJob().equals(Class.FIGHTER))
				++array[4];
			else if (player.getJob().equals(Class.MONK))
				++array[5];
			else if (player.getJob().equals(Class.PALADIN))
				++array[6];
			else if (player.getJob().equals(Class.RANGER))
				++array[7];
			else if (player.getJob().equals(Class.ROGUE))
				++array[8];
			else if (player.getJob().equals(Class.SORCERER))
				++array[9];
			else if (player.getJob().equals(Class.WARLOCK))
				++array[10];
			else if (player.getJob().equals(Class.WIZARD))
				++array[11];
		}

		Class[] jobs = new Class[] { Class.BARBARIAN, Class.BARD, Class.CLERIC, Class.DRUID, Class.FIGHTER, Class.MONK,
				Class.PALADIN, Class.RANGER, Class.ROGUE, Class.SORCERER, Class.WARLOCK, Class.WIZARD };
		for (int i = 0; i < jobs.length; ++i) {
			String string = String.format("%10s %5d (%5.1f)", jobs[i], array[i], (0.0 + array[i]) / PCS_TO_ROLL * 100);
			System.out.println(string);
		}
	}

	public static void raceProportions() {
		int[] array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int elves = 0;

		Player player;
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			player = new Player();

			if (player.getRace().equals(Race.HUMAN))
				++array[0];
			else if (player.getRace().equals(Race.HILL_DWARF))
				++array[1];
			else if (player.getRace().equals(Race.HIGH_ELF)) {
				++array[2];
				++elves;

			} else if (player.getRace().equals(Race.WOOD_ELF)) {
				++array[3];
				++elves;

			} else if (player.getRace().equals(Race.LIGHTFOOT_HALFLING))
				++array[4];
			else if (player.getRace().equals(Race.DRAGONBORN))
				++array[5];
			else if (player.getRace().equals(Race.DARK_ELF)) {
				++array[6];
				++elves;

			} else if (player.getRace().equals(Race.FOREST_GNOME))
				++array[7];
			else if (player.getRace().equals(Race.HALF_ELF))
				++array[8];
			else if (player.getRace().equals(Race.HALF_ORC))
				++array[9];
			else if (player.getRace().equals(Race.TIEFLING))
				++array[10];
		}

		Race[] races = new Race[] { Race.HUMAN, Race.HILL_DWARF, Race.HIGH_ELF, Race.WOOD_ELF, Race.LIGHTFOOT_HALFLING,
				Race.DRAGONBORN, Race.DARK_ELF, Race.FOREST_GNOME, Race.HALF_ELF, Race.HALF_ORC, Race.TIEFLING };
		String string;
		for (int i = 0; i < races.length; ++i) {
			string = String.format("%12s %5d (%5.1f)", races[i], array[i], (0.0 + array[i]) / PCS_TO_ROLL * 100);
			System.out.println(string);
		}

		System.out.println();
		string = String.format("%12s %5d (%5.1f)", "Total Elves", elves, (0.0 + elves) / PCS_TO_ROLL * 100);
		System.out.println(string);
	}

	public static void alignmentProportions() {
		int[] array = new int[] { 0, 0, 0, 0, 0 };

		Player player;
		for (int i = 0; i < PCS_TO_ROLL; ++i) {
			player = new Player();

			if (player.alignment().equals(Alignment.LAWFUL))
				++array[0];
			else if (player.alignment().equals(Alignment.GOOD))
				++array[1];
			else if (player.alignment().equals(Alignment.NEUTRAL))
				++array[2];
			else if (player.alignment().equals(Alignment.EVIL))
				++array[3];
			else if (player.alignment().equals(Alignment.CHAOTIC))
				++array[4];
		}

		Alignment[] alignments = new Alignment[] { Alignment.LAWFUL, Alignment.GOOD, Alignment.NEUTRAL, Alignment.EVIL,
				Alignment.CHAOTIC };
		for (int i = 0; i < alignments.length; ++i) {
			String string = String.format("%10s %5d (%5.1f)", alignments[i], array[i],
					(0.0 + array[i]) / PCS_TO_ROLL * 100);
			System.out.println(string);
		}
	}

}
