
public enum Ability {
	STRENGTH(0), DEXTERITY(1), CONSTITUTION(2), INTELLIGENCE(3), WISDOM(4), CHARISMA(5);

	// fields
	private final int ID;

	// constructors
	Ability(int id) {
		this.ID = id;
	}

	// methods
	public int getID() {
		return ID;
	}

	public static Ability get(int id) {
		Ability get;

		switch (id) {
		case 1:
			get = DEXTERITY;
			break;
		case 2:
			get = CONSTITUTION;
			break;
		case 3:
			get = INTELLIGENCE;
			break;
		case 4:
			get = WISDOM;
			break;
		case 5:
			get = CHARISMA;
			break;
		default:
			get = STRENGTH;
			break;
		}

		return get;
	}
}
