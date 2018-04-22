
public enum Energy {
	BLUDGEONING, PIERCING, SLASHING, FORCE, FIRE, COLD, LIGHTNING, ACID, RADIANT, THUNDER, NECROTIC, PSYCHIC, POISON;

	// static
	public static Energy randomEnergyType() {
		Energy[] energyTypes = { FORCE, FIRE, COLD, LIGHTNING, ACID, RADIANT, THUNDER, NECROTIC, PSYCHIC, POISON };
		return energyTypes[Dice.roll(1, 10) - 1];
	}
}
