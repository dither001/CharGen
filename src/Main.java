
public class Main {
	private static Actor[] actors = new Actor[30];
	
	public static void main(String[] args) {
		// TODO
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

}
