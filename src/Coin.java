
public enum Coin implements Item {
	COPPER(1), SILVER(10), ELECTRUM(50), GOLD(100), PLATINUM(1000);

	// fields
	private final int baseValue;

	// constructors
	private Coin(int baseValue) {
		this.baseValue = baseValue;
	}

	// methods
	public int getBaseValue() {
		return baseValue;
	}
}
