package com.starswonumber.deadnames;

import com.norvendae.rules.misc.*;

public abstract class Descriptor {
	private static final String[] LOST = { "Transhuman", "Extraterrestrial", "Synthetics", "Metadimensional" };

	// thirty-six madnesses
	private static final String[] MADNESS = { "Abstention. Greatness is in poverty. To have and need nothing is best.",
			"Anguish. Personal suffering is magnificence. Sought exotic forms of self-torment.",
			"Arete. Excellence in strength, wisdom, and beauty is the only worthy goal.",
			"Beauty. All life is directed toward the creation of beauty in form, action, and meaning.",
			"Connection. Things must be connected--places, people, ideas, objects. All must be linked.",
			"Construction. They must build--structures, objects, social organizations.",
			"Conversion. The world is to be shaped into a pattern more to their liking.",
			"Corruption. Whatever is believed must be compromised. All virtues made hypocrisies.",
			"Deceit. All things must be lies. The truth of anything must always be concealed.",
			"Degredation. They alone are worthy sentience--all others must be brought low.",
			"Disassembly. They break things down into component parts--whether objects or creatures.",
			"Escape. They must escape this world they inhabit. All efforts are directed toward flight.",
			"Esoterica. They have a secret art or hidden wisdom--all effort is toward expressing it.",
			"Exclusion. All intrusions of the metadimensional or alien are an abomination.",
			"Forgetting. Some ideas must be buried and destroyed--any who know must be silenced.",
			"Hatred. All else in the world is worthy only of unremitting hatred and violence.",
			"Healing. The world is forever broken and in need of repair--whether body or spirit.",
			"Hunger. Consumption is life. Eat of the living, or the inanimate, or of ephemeral ideas.",
			"Inversion. Roll again--their Madness is to stamp out that motive in all other creatures.",
			"Knowing. They exist to learn--secrets or mundane truths. They interrogate the world.",
			"Luddism. Only knowledge is pure. All artifice and technology must be eliminated.",
			"Memorial. Living testament to another race or idea--must glorify and echo it.",
			"Nihilism. Existence is suffering. They must end all out of mercy for the living.",
			"Pleasure. There is no greater purpose than joy. Existence is focused on finding it.",
			"Protection. All must be defended from a thing, whether they can appreciate it or not.",
			"Reproduction. Their purpose is to create more of their kind with no regard for limits.",
			"Rule. The world must be rectified beneath their guidance, even if only to stand still.",
			"Sacrifice. Life is what one expends on behalf of another--whether a person or cause.",
			"Schism. They hate each other, either individually or in factions--all is struggle.",
			"Selfishness. The only worthy goal is the exaltation of self, no matter the cost.",
			"Servitude. They must obey, and the justification of existence is in perfect obedience.",
			"Shame. They are filth. They are monsters. They must accept their worthlessness.",
			"Stasis. They must not change in any way. All must be the same, forever.",
			"Survival. All focus and vigor is turned to self-preservation and elimination of peril.",
			"Torment. Only in the pain of others is the self affirmed. Power lies in causing pain.",
			"Unity. Everything must be brought into perfect harmony with a greater purpose." };

	// ten intensities
	private static final String[] INTENSITY = {
			"Decaying. The madness was once overwhelming, but is now weaker and less compulsive.",
			"Fresh. The madness is a recent response to some event or need and has urgency.",
			"Hidden. The madness is strong, but they seek to mask or conceal it.",
			"Maniacal. They are barely capable of thinking in any way but with the madness.",
			"Reasoned. The madness is their usual purpose, albeit tempered so as to prove useful.",
			"Ruinous. The madness is so strong it is inimical to their immediate survival.",
			"Strong. The madness directs them in all things, but fails to consume their reason.",
			"Tic. The madness is noticeable but more an inclination than a driving force.",
			"Triggered. An underlying thing brings out the madness in overwhelming strength.",
			"Variable. A certain caste or type within the species is driven--others less so." };

	// ten motivations
	private static final String[] MOTIVATION = { "Created. They were engineered to focus on the madness.",
			"Defiance. Some force tried to stamp out a trait, inducing the madness.",
			"Delusion. A force deceived them into believing the madness was vital.",
			"Historical. An historical event drove them to embrace the madness.",
			"Ideological. A social philosophy pushed them to the madness.",
			"Imputed. Another species impressed the madness upon them.",
			"Intrinsic. The madness is a consequence of their basic nature.",
			"Relict. In their age, the madness was a crucial survival tool.",
			"Subsistence. The madness produces something vital for their survival.",
			"Theological. Religious concepts compel the madness." };

	private static final String[] ABERRATION = { "Assimilation. They need some precious matter as food.",
			"Assimilation. They turn other sentients into biomass to reproduce.",
			"Assimilation. They rewrite the DNA of other creatures to reproduce.",
			"Assimilation. They seek to impose their madness on all others.",
			"Assimilation. They capture and rework other structures into their own.",
			"Assimilation. They attach parts of other sentients to themselves.",
			"Assimilation. They seek to enslave all other lifeforms around them.",
			"Assimilation. They transform sentients into some valuable substance.",
			"Assimilation. Other creatures are aesthetically altered to suit.",
			"Assimilation. Knowledge must be reinterpreted to fit their ideals.",
			"Body Shaping. They were remade to mimic a beast's traits.",
			"Body Shaping. They have been fashioned to be perfectly identical.",
			"Body Shaping. All were made to an inhuman standard of beauty.",
			"Body Shaping. They were intentionally physically crippled somehow.",
			"Body Shaping. They have been rebuilt in completely new genders.",
			"Body Shaping. They have one sense or attribute greatly enhanced.",
			"Body Shaping. They were made much larger or smaller than normal.",
			"Body Shaping. Alien appendages or orifices grafted or opened.",
			"Body Shaping. Altered into artificial shapes by designer's intent.",
			"Body Shaping. They were refashioned to suit an alien environment.",
			"Terraformers. The world would be better underwater.",
			"Terraformers. Want to remake the world of another basic material",
			"Terraformers. They seek to radically transform the ecosphere.",
			"Terraformers. They build abominable creatures to their own ideals.",
			"Terraformers. All structures and objects must conform to their canon.",
			"Terraformers. They cannot survive in standard habitable environment.",
			"Terraformers. They terraform on behalf of aliens or metadimensionals.",
			"Terraformers. They attempt to build utopia regardless of the cost.",
			"Terraformers. They wish to eliminate particular thinkers/ideals.",
			"Terraformers. They wish to revive an ancient dead ecosystem.",
			"Connectivity. They can communicate with anything.", "Connectivity. They share senses among each other.",
			"Connectivity. Every group acts by instinctive consensus.",
			"Connectivity. Tech obeys their will, within limits of the device.",
			"Connectivity. Can draw knowledge and data from each other.",
			"Connectivity. They intuitively read the minds of others.",
			"Connectivity. They can bend non-sentient minds to their will.",
			"Connectivity. Social patterns and relationships are obvious to them.",
			"Connectivity. They can teleport to each other.",
			"Connectivity. The lesser must invariable obey the greater among them.",
			"Collectivist. Individuality is shunned as an evil.",
			"Collectivist. Identities associated with objects held, not bodies.",
			"Collectivist. Identities tied to the role being filled.",
			"Collectivist. Identities tied to preordained or destined purpose.",
			"Collectivist. Identities are objects of trade and barter.",
			"Collectivist. Selfhood is temporary, allowed only in dire situations.",
			"Collectivist. Only the ruling class is permitted to have identities.",
			"Collectivist. Individuality only appears at certain life stages.",
			"Collectivist. Groups and factions have identities, not individuals.",
			"Collectivist. Authorities can edit and control identities of lesser.",
			"Immortality. Seek to preserve society in absolute stasis.",
			"Immortality. Fight against aging and decay with fanatical zeal.",
			"Immortality. Fight on behalf of existing powers and authorities.",
			"Immortality. Attempt to halt anyone changing anything of importance.",
			"Immortality. Try to stamp out recovery of ancient knowledge.",
			"Immortality. Try to halt time on a grand scale.",
			"Immortality. Attempt to create stability via simplicity.",
			"Immortality. Hate all things that intrinsically change with time.",
			"Immortality. Attempt to conceal secrets that would change too much.",
			"Immortality. Attempt to repair all broken things.",
			"Thought Police. Specific emotions have been eliminated.",
			"Thought Police. Some ideas are impossible to comprehend.",
			"Thought Police. Made mentally crippled in some way.", "Thought Police. Helplessly obey the authorities.",
			"Thought Police. Perform certain actions at specific times/places.",
			"Thought Police. Sublime mental prowess in specific field(s).",
			"Thought Police. Events provoke a nonstandard emotional response.",
			"Thought Police. All or almost all emotions have been eliminated.",
			"Thought Police. Perfect sublimation of passion to rational thought.",
			"Thought Police. Some emotions induce physical torments or pleasures.",
			"Revisionist. They simply do not age or change with time.",
			"Revisionist. Gravity is of no consequence to them.",
			"Revisionist. They pass through solid objects at will.",
			"Revisionist. They can be almost any location they desire.",
			"Revisionist. They can cause minor changes in local reality at will.",
			"Revisionist. They control light--create illusions or become invisible.",
			"Revisionist. Mass is negligible--laugh off kinetic weapons.",
			"Revisionist. They ignore or even feed upon radioactivity and heat.",
			"Revisionist. They can be dismembered but cannot truly die.",
			"Revisionist. They can negate pretech effects nearby.",
			"Mememtic. They are obsessed with internal hierarchy and domination.",
			"Mememtic. They war against all concepts of \"possession.\"",
			"Mememtic. A physical need has utterly consumed their society.",
			"Mememtic. They are riven by schisms and savagely violent factions.",
			"Mememtic. They are viciously envious of modern humanity.",
			"Mememtic. Dangerously mistaken idea about the nature of the world.",
			"Mememtic. Their society believe only in compulsion, not cooperation.",
			"Mememtic. Torture and atrocity are considered glorious to inflict.",
			"Mememtic. Contact with outsiders is ritually defiling and traumatic.",
			"Mememtic. They hate their own society and others of the species.",
			"Anomalous. Things happen before they choose to do them.",
			"Anomalous. They have a habit of speaking in prophesy.",
			"Anomalous. They instinctively know what others expect of them.",
			"Anomalous. They are all one creature, duplicated in time.",
			"Anomalous. They can rewind unfortunate recent events.",
			"Anomalous. They can temporarily place others in temporal stasis.",
			"Anomalous. They can see past events in a location.",
			"Anomalous. They know the most likely immediate outcome of choices.",
			"Anomalous. Act via temporal duplicates--they hide their real selves.",
			"Anomalous. They seek and destroy any who meddle with time." };

	// put the others like metadimensionals in here

	// d12 general motifs
	private static final String[] VISUAL_MOTIF = { "Strength. Muscularity, size, and brute power.",
			"Agility. Litheness, perhaps with compact size.", "Symmetry. They have perfect proportions and form.",
			"Color. All their hues reference a single color.", "Beast. Their bodies are inspired by a type of animal.",
			"Allure. They are built for carnal enticement.", "Material. They have aspects of inanimate material.",
			"Allegiance. They are marked with signs of their creator's cause.",
			"Elemental. One or more classical elements inform their style.",
			"Decay. They have a degenerate or decayed aspect.", "Size. They are unusually large or small in scale.",
			"Unity. They all share the same basic appearance." };

	// d18 body formats
	private static final String[] BODY_FORMAT = { "Centauroid, either bestial or humanoid.",
			"Additional one to three limb pairs.", "Tentacular replacements or additions.",
			"Winged--membranous, feathered, or insectile.", "Absence of one or more limbs/extremities.",
			"Quadrupedal, but can stand upright.", "Amorphous or jelly-like.",
			"Acephalous, mouth in torso or extremity.", "Serpentine body flexibility.",
			"Pincers added or replacing hands.", "Large number of additional legs or arms.",
			"Finned or flippered limbs.", "Snakelike limbless or naga-esque form.", "Human or generally humanoid.",
			"Human or generally humanoid.", "Human or generally humanoid.", "Human or generally humanoid.",
			"Human or generally humanoid." };

	// d10 animal aspects
	private static final String[] ASPECT = { "Canine aspect.", "Piscene aspect.", "Insectile aspect.", "Feline aspect.",
			"Beetle-like aspect.", "Avian aspect.", "Reptilian aspect.", "Equine aspect.", "Worm-like aspect.",
			"Rodent aspect." };

	private static final String[] MOUTH = { "Human-like teeth.", "Fanged maw.", "Sucking proboscis.",
			"Chewing mandibles.", "Grinding jaws.", "Filter feeder.", "Digestive sac.", "Beak.", "Bone tooth ridges.",
			"Circular rasp." };

	private static final String[] FEELERS = { "Human-like fingers.", "Taloned claws.", "Small tentacles.",
			"Telekinetic grabbers.", "Prehensile extremities.", "Agile mouthparts.", "Secondary feet.",
			"Hair-fine strands.", "Delicate suckers.", "Both strong and fine types." };

	private static final String[] SPEED = { "Walking upright.", "Quadrupedal motion.", "Winged flight.",
			"Serpentine undulations.", "Telekinetic levitation.", "Swims in air or water.", "Creeps on the ground.",
			"Brachiates from heights.", "Natural jets or hovering.", "Short-range teleport." };

	private static final String[] SKIN = { "Soft skin.", "Leathery hide.", "Chromed or metallic hide.",
			"Stony and mineralized hide.", "Chitinous shell.", "Feathered skin.", "Scaled hide.", "Slimy skin.",
			"Furred skin.", "Bony plated hide." };

	private static final String[] LIMBS = { "Tentacles.", "Jointed human-like limbs.", "Digitigrade legs.",
			"Insectile limbs.", "Skeltally thin limbs.", "Artificial-seeming limbs.", "Coherent energy limbs.",
			"Bestially coarse limbs.", "Many-jointed limbs.", "Spidery limbs." };

	private static final String[] VOICE = { "Sweet, human-like voice.", "Buzzing voice.", "Metallic voice.",
			"Whispering voice.", "Fluting voice.", "Rasping voice.", "Polytonal voice.", "Harsh, cawing voice.",
			"Wet or burbling voice.", "Gestures only; no speech." };

	private static final String[] SMELL = { "Perfectly scentless.", "Musky scent.",
			"Smells like sewage or rotting flesh.", "Lemony or citrus scent.", "Acrid or ozone-like scent.",
			"Dusty scent.", "Floral scent.", "Spicy scent.", "Smells earthy, like cut grass.",
			"Smells like cinnamon." };

	public static String elderRace() {
		String s = String.format("%nMadness: %s %nIntensity: %s %nMotivation: %s ", MADNESS[Dice.roll(1, 36) - 1],
				INTENSITY[Dice.roll(1, 10) - 1], MOTIVATION[Dice.roll(1, 10) - 1]);

		// VISUAL_MOTIF[Dice.deeRoll(1,12) - 1];
		// BODY_FORMAT[Dice.deeRoll(1,18) - 1];
		// ASPECT[Dice.deeRoll(1,10) - 1];
		// MOUTH[Dice.deeRoll(1,10) - 1];
		// FEELERS[Dice.deeRoll(1,10) - 1];
		// SPEED[Dice.deeRoll(1,10) - 1];
		// SKIN[Dice.deeRoll(1,10) - 1];
		// LIMBS[Dice.deeRoll(1,10) - 1];
		// VOICE[Dice.deeRoll(1,10) - 1];
		// SMELL[Dice.deeRoll(1,10) - 1];
		return s;
	}

}
