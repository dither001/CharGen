import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class Dice {
	// static fields
	private static final Random RAND = new Random();

	// static methods
	public static int roll(int faces) {
		return roll(1, faces);
	}

	public static int roll(int dice, int faces) {
		int result = 0;

		dice = (dice < 1) ? 1 : dice;
		faces = (faces < 1) ? 1 : faces;

		for (int i = 0; i < dice; ++i) {
			result += RAND.nextInt(faces) + 1;
		}

		return result;
	}

	public static byte[] rollAbilities() {
		byte[] array = new byte[] { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < array.length; ++i) {
			array[i] += roll(3, 6);
		}

		return array;
	}

	public static int advantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);

		return (firstRoll > secondRoll) ? firstRoll : secondRoll;
	}

	public static int disadvantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);

		return (firstRoll < secondRoll) ? firstRoll : secondRoll;
	}

	public static byte[] rollHitDice(Class job) {
		int hitDieSize = Class.getHitDie(job);

		byte[] hitDice = new byte[20];
		for (int i = 0; i < hitDice.length; ++i) {
			hitDice[i] = (byte) roll(hitDieSize);
		}

		return hitDice;
	}

	public static int[] fortune() {
		return fortune(0, 6);
	}

	public static int[] fortune(int dice) {
		return fortune(dice, 6);
	}

	public static int[] fortune(int dice, int faces) {
		int[] fortune = new int[faces];

		if (dice > 0) {
			for (int i = 0; i < dice; ++i) {
				++fortune[roll(faces) - 1];
			}
		} else {
			++fortune[disadvantage(faces) - 1];
		}

		return fortune;
	}

	public static <T> T randomFromArray(T[] array) {
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	@SuppressWarnings("unchecked")
	public static <T> T randomFromList(List<T> list) {
		T[] array = (T[]) new Object[list.size()];
		list.toArray(array);
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	@SuppressWarnings("unchecked")
	public static <T> T randomFromSet(Set<T> set) {
		T[] array = (T[]) new Object[set.size()];
		set.toArray(array);
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] setToArray(Set<T> set) {
		T[] array = (T[]) new Object[set.size()];

		Iterator<T> it = set.iterator();
		for (int i = 0; i < array.length; ++i) {
			array[i] = it.next();
		}

		return array;
	}

	public static <T> List<T> setToList(Set<T> set) {
		return new ArrayList<T>(set);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> sublistFromList(int length, List<T> list) {
		T[] array = (T[]) new Object[list.size()];
		list.toArray(array);

		List<T> sublist = new ArrayList<T>();
		T choice;
		while (sublist.size() < length) {
			choice = array[Dice.roll(array.length) - 1];

			if (sublist.contains(choice) != true)
				sublist.add(choice);
		}

		return sublist;
	}

	public static <T> Set<T> addToSetFromArray(int toAdd, Set<T> set, T[] array) {
		int added = 0;
		T candidate;

		while (added < toAdd) {
			candidate = randomFromArray(array);

			if (set.contains(candidate) != true) {
				set.add(candidate);
				++added;
			}

		}

		return set;
	}

	public static <T> Set<T> addToSetOrElseFromArray(int toAdd, T[] element, Set<T> set, T[] array) {
		int added = 0;

		for (int i = 0; i < element.length; ++i) {
			if (set.contains(element[i]) != true) {
				set.add(element[i]);
				++added;
			}
		}

		T candidate;
		while (added < toAdd) {
			candidate = randomFromArray(array);

			if (set.contains(candidate) != true) {
				set.add(candidate);
				++added;
			}

		}

		return set;
	}

	/*
	 * STATIC CLASS
	 * 
	 */
	public static class AlphabeticalDescending<T> implements Comparator<T> {

		@Override
		public int compare(T object1, T object2) {
			int compare = 0;
			String string1 = object1.toString(), string2 = object2.toString();
			int length1 = string1.length(), length2 = string2.length();
			int length = (length1 < length2) ? length1 : length2;

			char c1, c2;
			for (int i = 0; i < length; ++i) {
				c1 = string1.charAt(i);
				c2 = string2.charAt(i);

				if (c1 < c2) {
					compare = c1 - c2;
					break;
				}
			}

			return compare;
		}
	}
}
