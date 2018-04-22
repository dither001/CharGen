
public enum Rarity {
	MUNDANE(1), COMMON(5000), UNCOMMON(10000), RARE(50000), VERY_RARE(500000), LEGENDARY(5000000);

	// fields
	private final int baseValue;

	// constructors
	private Rarity(int baseValue) {
		this.baseValue = baseValue;
	}

	// methods
	public int getBaseValue() {
		return baseValue;
	}

	// static methods
	public static Rarity getRarity(int value) {
		Rarity rarity = MUNDANE;

		if (value > 5000000) {
			// 5 million copper
			rarity = LEGENDARY;
		} else if (value > 500000) {
			// 500 thousand copper
			rarity = VERY_RARE;
		} else if (value > 50000) {
			// 50 thousand copper
			rarity = RARE;
		} else if (value > 10000) {
			// 10 thousand copper
			rarity = UNCOMMON;
		} else if (value > 5000) {
			// 5 thousand copper
			rarity = COMMON;
		}

		return rarity;
	}
}
