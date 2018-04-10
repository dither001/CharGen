
/*
 * TODO - humans make up a majority (40%) of all characters; evil is the
 * majority alignment (40%), which means that a significant number of NPCs
 * generated will probably be a) evil, b) human, and c) fighters.
 */

public class Actor {
	private AbilityArray abilities;
	private Alignment ali;
	private Archetype job;
	private Race race;
	private Deity god;

	// constructors
	public Actor() {
		abilities = new AbilityArray();

		ali = Alignment.selectALI();
		// JOB requires ability array
		job = Archetype.selectArchetype(this);
		// RACE is chosen specifically after JOB
		race = Race.selectRace();
		// GOD requires ALI, JOB, and RACE
		god = Deity.selectDeity(this);
	}
	
	// methods
	public int getSTR() {return abilities.getSTR();}
	public int getDEX() {return abilities.getDEX();}
	public int getCON() {return abilities.getCON();}
	public int getINT() {return abilities.getINT();}
	public int getWIS() {return abilities.getWIS();}
	public int getCHA() {return abilities.getCHA();}

	public Alignment getAli() {return ali;}
	public Archetype getJob() {return job;}
	public Race getRace() {return race;}
	public Deity getDeity() {return god;}
}
