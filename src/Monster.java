import java.util.EnumSet;

public class Monster implements Actor {

	/*
	 * 
	 */
	public enum Order {
		/*
		 * INVERTEBRATE
		 */
		SPONGIFORM, //
		JELLYFISH, CORAL, HYDRAZOAN, // cnidarian
		FLATWORM, //
		NEMATODE, //
		RINGWORM, LEECH, // annelids
		// arthropoda
		TRILOBITE,
		LOBSTER, SHRIMP, BARNACLE, // crustaceans
		CENTIPEDE, MILLIPEDE, // myriapoda
		SPIDER, CRAB, TICK, MITE, SCORPION, HARVESTMAN, // arachnids
		/*
		 * INSECTS
		 */
		BEETLE, FLY, MOTH, WASP, // four major branches of insects
		ANT, BEE, BUTTERFLY, COCKROACH, CRICKET, DRAGONFLY, EARWIG, FLEA, LOCUST, LOUSE, MANTIS, TERMITE, //
		SQUID, SNAIL, SLUG, OCTOPUS, CUTTLEFISH, // mollusca
		// echinodermata - "spiny skins"; regeneration, radial symmetry
		STARFISH, // "asteroidea"
		URCHIN, // including sand dollars and sea biscuits
		HOLOTHURID, // sea cucumber
	}
	
	
	
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private byte[] abilityScores;
	private Size size;
	private Creature creature;
	private byte[] hitDice;
	private boolean isFemale;

	
	
	
	
	/*
	 * 
	 * 
	 */
	@Override
	public String toStringDetailed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Size creatureSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Creature creatureType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alignment alignment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deity getDeity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getHitDice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Option.Feature> getFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFeatures(EnumSet<Option.Feature> features) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class getJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class.Subclass getArchetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Race getRace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRace(Race race) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Spell> getSpellsKnown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpellsKnown(EnumSet<Spell> spellsKnown) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Career.Profile getCareer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getExperience() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExp(int exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Speed> getSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAbilityScores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getAbilityCeiling() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getSavingThrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Skill> getSkills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSkills(EnumSet<Skill> skills) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Armor> getArmorProficiency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArmorProficiency(EnumSet<Armor> armor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Weapon> getWeaponProficiency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWeaponProficiency(EnumSet<Weapon> weapons) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<Energy> getResistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Energy> getImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditionImmunity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Condition> getConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Sense> getSenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumSet<Race.Language> getLanguages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLanguages(EnumSet<Race.Language> languages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Combat combat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCombat(Combat combat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

}
