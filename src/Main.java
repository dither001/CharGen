import java.util.HashSet;
import java.util.Iterator;

public class Main {
	private static int PCS_TO_ROLL = 100;
	private static int SPELLBOOK_LEVEL = 17;

	private static Actor[] actors = new Actor[PCS_TO_ROLL];

	public static void main(String[] args) {
		// TODO

		for (int i = 0; i < 10; ++i) {
			System.out.println(Career.randomMeans());
			System.out.println(Career.randomMotive());
			System.out.println(Career.randomOpportunity());
			System.out.println();
		}
		
		
		
		
		// encounterSpread();

		// for (int i = 0; i < PCS_TO_ROLL; ++i) {
		// rollOneSpellbook(7);
		// }

		// rollCharacters();
		// for (int i = 0; i < PCS_TO_ROLL; ++i) {
		// rollOneCharacter();
		// }
	}

	public static void encounterSpread() {
		int one, two, three, seven, eleven, fifteen;
		for (int i = 1; i < 21; ++i) {
			one = ChallengeRating.deadlyEncounter(i, 1);
			two = ChallengeRating.deadlyEncounter(i, 2);
			three = ChallengeRating.deadlyEncounter(i, 3);
			seven = ChallengeRating.deadlyEncounter(i, 7);
			eleven = ChallengeRating.deadlyEncounter(i, 11);
			fifteen = ChallengeRating.deadlyEncounter(i, 15);
			// System.out.println("One " + one + " || Two " + two);
			System.out.printf("level %2d: one %2d || two %2d || three %2d || seven %2d || eleven %2d || fifteen %2d%n",
					i, one, two, three, seven, eleven, fifteen);
		}
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

	public static void rollOneCharacter() {
		Actor actor = new Actor();
		int rating = ChallengeRating.evaluateCR(actor);

		System.out.print("Name");
		System.out.print(" " + actor.getAli().toString());
		System.out.print(" " + actor.getRace().toString());
		System.out.print(" " + actor.getCareer().toString());
		System.out.print(" " + actor.getJob().toString());
		System.out.print(" (" + actor.getArchetype().toString() + ")");
		System.out.print(" " + actor.getDeity().toString());
		System.out.print(" (" + actor.getEXPRate() + ")");

		// System.out.println("");
		// System.out.print(actor.getTraitOne() + "\n");
		// System.out.print(actor.getTraitTwo() + "\n");
		// System.out.print(actor.getIdeal() + "\n");
		// System.out.print(actor.getBond() + "\n");
		// System.out.print(actor.getFlaw());

		System.out.println("");
		System.out.print("AC " + actor.getArmorClass());
		System.out.print(" hp " + actor.getHitPoints());
		System.out.print(" atk " + actor.getAttackBonus());
		System.out.print(" dmg " + actor.getAverageDamage());
		System.out.print(" || CR " + rating);
		System.out.print(" exp " + ChallengeRating.challengeToXP(rating));

		if (actor.getInventory().equippedWeapon()) {
			System.out.println("");
			System.out.print("Wielding " + actor.getInventory().getMainHand().toString());
		}

		if (actor.getInventory().equippedShield()) {
			System.out.println("");
			System.out.print("Wielding " + actor.getInventory().getOffHand().toString());
		}

		System.out.println("");
		System.out.print(actor.getAbilities().toString());
		System.out.println("");
		System.out.print(actor.getInventory().toString());
		System.out.println("");
		// System.out.print(actor.getSkills().toString());
		// System.out.println("");
		System.out.println("");

	}

	public static void rollCharacters() {
		int rating;
		for (int i = 0; i < actors.length; ++i) {
			actors[i] = new Actor();
			rating = ChallengeRating.evaluateCR(actors[i]);

			System.out.print("Name");
			System.out.print(" " + actors[i].getAli().toString());
			System.out.print(" " + actors[i].getRace().toString());
			System.out.print(" " + actors[i].getCareer().toString());
			System.out.print(" " + actors[i].getJob().toString());
			System.out.print(" (" + actors[i].getArchetype().toString() + ")");
			System.out.print(" " + actors[i].getDeity().toString());
			System.out.print(" (" + actors[i].getEXPRate() + ")");

			System.out.println("");
			System.out.print("AC " + actors[i].getArmorClass());
			System.out.print(" hp " + actors[i].getHitPoints());
			System.out.print(" atk " + actors[i].getAttackBonus());
			System.out.print(" dmg " + actors[i].getAverageDamage());
			System.out.print(" || CR " + rating);
			System.out.print(" exp " + ChallengeRating.challengeToXP(rating));

			if (actors[i].getInventory().equippedWeapon()) {
				System.out.println("");
				System.out.print("Wielding " + actors[i].getInventory().getMainHand().toString());
			}
			System.out.println("");
			System.out.print(actors[i].getAbilities().toString());
			System.out.println("");
			System.out.print(actors[i].getInventory().toString());
			System.out.println("");
			// System.out.print(actors[i].getSkills().toString());
			// System.out.println("");
			System.out.println("");

		}
	}
}
