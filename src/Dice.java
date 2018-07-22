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

	public static boolean containsIntegerOrGreater(int integer, List<Integer> list) {
		boolean contains = false;

		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			if (it.next() >= integer) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	public static boolean containsLessThanInteger(int integer, List<Integer> list) {
		boolean contains = false;

		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			if (it.next() < integer) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	public static String stringToName(String string) {
		String name = "";

		char current, previous = ' ', next = 0;
		char[] array = string.toLowerCase().toCharArray();
		for (int i = 0; i < array.length; ++i) {
			current = array[i];

			if (i == 0 || previous == ' ') {
				current = Character.toUpperCase(current);

			} else if (current == '_') {
				next = (i + 1 <= array.length) ? array[i + 1] : 0;
				if (next != 0 && (next == 'e' || next == 'i')) {
					current = '-';

				} else {
					current = ' ';

				}

			} else {
				current = Character.toLowerCase(current);

			}

			name += current;
			previous = current;
		}

		return name;
	}

	public static String romanNumeral(int integer) {
		String numeral = "";

		int next;
		while (integer > 0) {
			if (integer < 10) {
				next = integer % 10;
				integer -= next;

				if (next == 9)
					numeral += "IX";
				else if (next == 8)
					numeral += "VIII";
				else if (next == 7)
					numeral += "VII";
				else if (next == 6)
					numeral += "VI";
				else if (next == 5)
					numeral += "V";
				else if (next == 4)
					numeral += "IV";
				else if (next == 3)
					numeral += "III";
				else if (next == 2)
					numeral += "II";
				else if (next == 1)
					numeral += "I";

			} else if (integer < 100) {
				next = integer % 100;

				if (next >= 90) {
					numeral += "XC";
					integer -= 90;

				} else if (next >= 80) {
					numeral += "LXXX";
					integer -= 80;

				} else if (next >= 70) {
					numeral += "LXX";
					integer -= 70;

				} else if (next >= 60) {
					numeral += "LX";
					integer -= 60;

				} else if (next >= 50) {
					numeral += "L";
					integer -= 50;

				} else if (next >= 40) {
					numeral += "XL";
					integer -= 40;

				} else if (next >= 30) {
					numeral += "XXX";
					integer -= 30;

				} else if (next >= 20) {
					numeral += "XX";
					integer -= 20;

				} else if (next >= 10) {
					numeral += "X";
					integer -= 10;

				}

			} else if (integer < 1000) {
				next = integer % 1000;

				if (next >= 900) {
					numeral += "CM";
					integer -= 900;

				} else if (next >= 800) {
					numeral += "DCCC";
					integer -= 800;

				} else if (next >= 700) {
					numeral += "DCC";
					integer -= 700;

				} else if (next >= 600) {
					numeral += "DC";
					integer -= 600;

				} else if (next >= 500) {
					numeral += "D";
					integer -= 500;

				} else if (next >= 400) {
					numeral += "CD";
					integer -= 400;

				} else if (next >= 300) {
					numeral += "CCC";
					integer -= 300;

				} else if (next >= 200) {
					numeral += "CC";
					integer -= 200;

				} else if (next >= 100) {
					numeral += "C";
					integer -= 100;

				}
			}

		}

		return numeral;
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
