
public enum Armor {
	NONE, BARBARIAN, MAGE, MONK, PADDED(11), LEATHER(11), STUDDED(12), HIDE(12, 2), CHAIN_SHIRT(13, 2), SCALE_MAIL(14,
			2), BREASTPLATE(14, 2), HALF_PLATE(15, 2), RING_MAIL(14, 0), CHAIN_MAIL(16, 0), SPLINT(17, 0), PLATE(18, 0);

	// fields
	private int armorClass;
	private int maxDexterity;

	// constructors
	Armor() {
		this(10);
	}

	Armor(int armorClass) {
		this(armorClass, 10);
	}

	Armor(int armorClass, int maxDexterity) {
		this.setArmorClass(armorClass);
		this.setMaxDexterity(maxDexterity);
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getMaxDexterity() {
		return maxDexterity;
	}

	public void setMaxDexterity(int maxDexterity) {
		this.maxDexterity = maxDexterity;
	}

}
