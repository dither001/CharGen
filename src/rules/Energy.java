package rules;

public enum Energy {
	BLUDGEONING, PIERCING, SLASHING, FORCE, FIRE, COLD, LIGHTNING, ACID, RADIANT, THUNDER, NECROTIC, PSYCHIC, POISON;

	// static fields
	private static final Energy[] ENERGY_TYPES = { FORCE, FIRE, COLD, LIGHTNING, ACID, RADIANT, THUNDER, NECROTIC,
			PSYCHIC, POISON };

	// static methods
	public static Energy randomEnergyType() {
		return Dice.randomFromArray(ENERGY_TYPES);
	}
}
