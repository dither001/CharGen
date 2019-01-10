package com.norvendae.misc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Names {

	/*
	 * STATIC METHODS
	 * 
	 */
	public static List<String> worldNameList() {
		Pantheon pantheon = Dice.randomFromSet(Pantheon.getPantheons());

		return pantheon.nameList();
	}

	public static String nameShorten(String name) {
		String string = "";

		int length = name.length();
		if (length > 9 && (name.contains(" ") || name.contains("_") || name.contains("-"))) {
			// FIXME - for some reason, not all names processed are shortened
			// System.out.println("Name is longer than 9 characters");
			for (int i = length - 1; i > -1; --i) {
				if (name.charAt(i) == ' ' || name.charAt(i) == '_' || name.charAt(i) == '-')
					string = name.substring(i + 1);
			}

		} else {
			string = name;

		}

		return string;
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
	 * STATIC CLASSES
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
