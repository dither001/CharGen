
public abstract class GameItem {
	// fields
	protected String name;
	protected float baseWeight;
	protected int baseValue;
	protected Rarity rarity;

	// additional fields
	protected boolean requiresAttunement;
	protected boolean isCursed;

	// methods
	String getName() {
		return name;
	}

	float getBaseWeight() {
		return baseWeight;
	}

	int getBaseValue() {
		return baseValue;
	}

	Rarity getRarity() {
		return rarity;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
