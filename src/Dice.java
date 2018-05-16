import java.util.Random;

public class Dice {
	// fields
	private static final Random RAND = new Random();

	// methods
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
	
	public static int advantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);
		
		return (firstRoll > secondRoll) ? firstRoll : secondRoll;
	}
	
	public static int disadvantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);
		
		return (firstRoll > secondRoll) ? secondRoll : firstRoll;
	}

}
