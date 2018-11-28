package actor;

import java.util.EnumSet;

import rules.*;

public abstract class Monster implements Actor {

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
	private CreatureType creature;
	private byte[] hitDice;
	private boolean isFemale;

	
	
	
	
	/*
	 * 
	 * 
	 */

}
