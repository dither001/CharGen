
/*
 * TODO - I tested individual offense/defense rating calculations, but I have yet to test the final evaluation of CR based on attack and defense combined.
 */

public class ChallengeRating {
	// fields
	private static final int[] REWARDS = { 10, 25, 50, 100, 200, 450, 700, 1100, 1800, 2300, 2900, 3900, 5000, 5900,
			7200, 8400, 10000, 11500, 13000, 15000, 18000, 20000, 22000, 25000, 33000, 41000, 50000, 62000, 75000,
			90000, 105000, 120000, 135000, 155000 };

	private static final int[] EASY = { 25, 50, 75, 125, 250, 300, 350, 450, 550, 600, 800, 1000, 1100, 1250, 1400,
			1600, 2000, 2100, 2400, 2800 };
	private static final int[] MEDIUM = { 50, 100, 150, 250, 500, 600, 750, 900, 1100, 1200, 1600, 2000, 2200, 2500,
			2800, 3200, 3900, 4200, 4900, 5700 };
	private static final int[] HARD = { 75, 150, 225, 375, 750, 900, 1100, 1400, 1600, 1900, 2400, 3000, 3400, 3800,
			4300, 4800, 5900, 6300, 7300, 8500 };
	private static final int[] DEADLY = { 100, 200, 400, 500, 1100, 1400, 1700, 2100, 2400, 2800, 3600, 4500, 5100,
			5700, 6400, 7200, 8800, 9500, 10900, 12700 };

	private static final int[] DEFAULT_DIFFICULTY = MEDIUM;

	// methods
	public static int evaluateCR(Actor actor) {
		int hp = actor.getHitPoints();
		int ac = actor.getArmorClass();
		int dmg = actor.getAverageDamage();
		int atk = actor.getAttackBonus();

		int offense, defense, finalRating = 0;
		defense = defenseRating(hp, ac);
		offense = offenseRating(dmg, atk);

		finalRating = (offense + defense) / 2 - 4;
		return finalRating;
	}

	private static int defenseRating(int hp, int ac) {
		int defenseRating = hitPointsToCR(hp);
		int armorRating = armorClassToCR(ac);
		int hitPointArmor = hitPointsToAC(hp);

		if (hitPointArmor > ac) {
			for (int i = defenseRating - armorRating; i > 1; i -= 2) {
				--defenseRating;
			}
		} else if (hitPointArmor < ac) {
			for (int i = armorRating - defenseRating; i > 1; i -= 2) {
				++defenseRating;
			}
		}

		return defenseRating;
	}

	private static int hitPointsToCR(int hp) {
		int rating = 31;
		int[] hitPoints = { 6, 35, 49, 70, 85, 100, 115, 130, 145, 160, 175, 190, 205, 220, 235, 250, 265, 280, 295,
				310, 325, 340, 355, 400, 445, 490, 535, 580, 625, 670, 715, 760, 805, 850 };

		for (int i = 0; i < hitPoints.length; ++i) {
			if (hp > 850) {
				break;
			} else if (hp <= hitPoints[i]) {
				rating = i - 3;
				break;
			}
		}

		return rating;
	}

	private static int hitPointsToAC(int hp) {
		int armorClass = 13;

		int rating = hitPointsToCR(hp);
		if (rating == 4) {
			armorClass = 14;
		} else if (rating <= 7) {
			armorClass = 15;
		} else if (rating <= 9) {
			armorClass = 16;
		} else if (rating <= 12) {
			armorClass = 17;
		} else if (rating <= 16) {
			armorClass = 18;
		} else if (rating <= 30) {
			armorClass = 19;
		} else if (rating > 30) {
			armorClass = 20;
		}

		return armorClass;
	}

	private static int armorClassToCR(int ac) {
		int rating = 17;

		if (ac <= 13) {
			rating = 0;
		} else if (ac <= 14) {
			rating = 4;
		} else if (ac <= 15) {
			rating = 5;
		} else if (ac <= 16) {
			rating = 8;
		} else if (ac <= 17) {
			rating = 10;
		} else if (ac <= 18) {
			rating = 13;
		} else if (ac > 18) {
			rating = 17;
		}

		return rating;
	}

	private static int offenseRating(int dmg, int atk) {
		int offenseRating = damageToCR(dmg);
		int attackRating = attackToCR(atk);
		int damageAttack = damageToAttack(dmg);

		if (damageAttack > atk) {
			for (int i = offenseRating - attackRating; i > 1; i -= 2) {
				--offenseRating;
			}
		} else if (damageAttack < atk) {
			for (int i = attackRating - offenseRating; i > 1; i -= 2) {
				++offenseRating;
			}
		}

		return offenseRating;
	}

	private static int damageToCR(int dmg) {
		int rating = 31;
		int[] damage = { 0, 3, 5, 8, 14, 20, 26, 32, 38, 44, 50, 56, 62, 68, 74, 80, 86, 92, 98, 104, 110, 116, 122,
				140, 158, 176, 194, 212, 230, 248, 266, 284, 302, 320 };

		for (int i = 0; i < damage.length; ++i) {
			if (dmg > 320) {
				break;
			} else if (dmg <= damage[i]) {
				rating = i - 3;
				break;
			}
		}

		return rating;
	}

	private static int damageToAttack(int dmg) {
		int attackBonus = 3;

		int rating = damageToCR(dmg);
		if (rating == 3) {
			attackBonus = 4;
		} else if (rating == 4) {
			attackBonus = 5;
		} else if (rating <= 7) {
			attackBonus = 6;
		} else if (rating <= 10) {
			attackBonus = 7;
		} else if (rating <= 15) {
			attackBonus = 8;
		} else if (rating == 16) {
			attackBonus = 9;
		} else if (rating <= 20) {
			attackBonus = 10;
		} else if (rating <= 23) {
			attackBonus = 11;
		} else if (rating <= 26) {
			attackBonus = 12;
		} else if (rating <= 29) {
			attackBonus = 13;
		} else if (rating > 29) {
			attackBonus = 14;
		}

		return attackBonus;
	}

	private static int attackToCR(int attack) {
		int rating = 31;

		if (attack <= 3) {
			rating = 0;
		} else if (attack <= 4) {
			rating = 3;
		} else if (attack <= 5) {
			rating = 4;
		} else if (attack <= 6) {
			rating = 5;
		} else if (attack <= 7) {
			rating = 8;
		} else if (attack <= 8) {
			rating = 11;
		} else if (attack <= 9) {
			rating = 16;
		} else if (attack <= 10) {
			rating = 17;
		} else if (attack <= 11) {
			rating = 21;
		} else if (attack <= 12) {
			rating = 24;
		} else if (attack <= 13) {
			rating = 27;
		} else if (attack <= 14) {
			rating = 30;
		}

		return rating;
	}

	public static int challengeToXP(int rating) {
		int exp = (rating < -3) ? 0 : (rating > 30) ? REWARDS[30] : REWARDS[rating + 3];
		return exp;
	}

	public static int monsterEncounter(int APL, int numberOfPlayers, int difficulty, int numberOfEnemies) {
		double[] multiplier = { 0.5, 1, 1.5, 2, 2.5, 3, 4, 5 };
		int rating = 1, index = 0, budget;
		int[] threshold = MEDIUM;

		if (difficulty == 1)
			threshold = EASY;
		else if (difficulty == 2)
			threshold = MEDIUM;
		else if (difficulty == 3)
			threshold = HARD;
		else if (difficulty == 4)
			threshold = DEADLY;

		budget = threshold[APL - 1] * numberOfPlayers;

		if (numberOfEnemies == 1)
			index = 1;
		else if (numberOfEnemies == 2)
			index = 2;
		else if (numberOfEnemies > 2 && numberOfEnemies < 7)
			index = 3;
		else if (numberOfEnemies > 6 && numberOfEnemies < 11)
			index = 4;
		else if (numberOfEnemies > 10 && numberOfEnemies < 15)
			index = 5;
		else if (numberOfEnemies > 14)
			index = 6;

		if (numberOfPlayers < 3)
			++index;
		else if (numberOfPlayers > 5)
			--index;

		for (int i = REWARDS.length - 1; i > 0; --i) {
			if ((int) (REWARDS[i] * multiplier[index]) <= budget) {
				rating = i;
				break;
			}
		}

		return rating - 3;
	}

	public static int mediumEncounter(int APL, int numberOfEnemies) {
			return monsterEncounter(APL, 4, 2, numberOfEnemies);
	}

	public static int deadlyEncounter(int APL, int numberOfEnemies) {
		return monsterEncounter(APL, 4, 4, numberOfEnemies);
}

	// public static int mediumEncounter(int APL, int numberOfEnemies) {
	// return monsterEncounter(APL, numberOfEnemies);
	// }

	public static int soloMediumEncounter(int APL) {
		return mediumEncounter(APL, 1);
	}
}