
public class ChallengeRating {

	// methods
	public static int evaluateCR(Actor actor) {
		int offense, defense, finalRating = 0;
		
		
		
		// TODO
		return finalRating;
	}
	
	public static int healthCR(int hp) {
		int rating = 31;
		int[] hitPoints = { 6, 35, 49, 70, 85, 100, 115, 130, 145, 160, 175, 190, 205, 220, 235, 250, 265, 280, 295,
				310, 325, 340, 355, 400, 445, 490, 535, 580, 625, 670, 715, 760, 805, 850 };

		for (int i = 0; i < hitPoints.length; ++i) {
			if (hp > 850) {
				break;
			} else if (hitPoints[i] <= hp) {
				rating = i - 3;
				break;
			}
		}

		return rating;
	}

}
