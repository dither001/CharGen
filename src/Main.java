
public class Main {
	private static Actor[] actors = new Actor[30];

	public static void main(String[] args) {
		// TODO
		
		rollCharacters();
	}

	public static void rollCharacters() {
		for (int i = 0; i < actors.length; ++i) {
			actors[i] = new Actor();
			System.out.print(actors[i].getAbilities().toString());
			System.out.print(" " + actors[i].getAli().toString());
			System.out.print(" " + actors[i].getRace().toString());
			System.out.print(" " + actors[i].getJob().toString());
			System.out.print(" " + actors[i].getDeity().toString());
			System.out.print(" (" + actors[i].getEXPRate() + ")");
			System.out.println("");
		}
	}
	
	public static void testCR() {
		int[] hitPoints = { 6, 35, 49, 70, 85, 100, 115, 130, 145, 160, 175, 190, 205, 220, 235, 250, 265, 280, 295,
				310, 325, 340, 355, 400, 445, 490, 535, 580, 625, 670, 715, 760, 805, 850 };
		int armorClass = 13;
		
		for (int i = 0; i < hitPoints.length; ++i) {
			armorClass = i / 4 + 10;
			System.out.println(ChallengeRating.defenseRating(hitPoints[i], armorClass));
		}
		
		int[] damage = { 0, 3, 5, 8, 14, 20, 26, 32, 38, 44, 50, 56, 62, 68, 74, 80, 86, 92, 98, 104, 110, 116, 122, 140,
				158, 176, 194, 212, 230, 248, 266, 284, 302, 320 };
		int attackBonus = 10;
		
		for (int i = 0; i < damage.length; ++i) {
			attackBonus = i / 4;
			System.out.println(ChallengeRating.offenseRating(damage[i], attackBonus));
		}
	}
	
}
