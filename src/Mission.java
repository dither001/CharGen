import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mission {
	public enum Detail {
		ENEMIES, FRIENDS, COMPLICATIONS, THINGS, PLACES
	}

	private static Map<World.Tag, Map<Detail, Set<String>>> tagMap;

	/*
	 * INITIALIZATION
	 * 
	 */
	static {
		tagMap = new HashMap<World.Tag, Map<Detail, Set<String>>>();

		// initialize Tag Map
		World.Tag[] array = World.ALL_TAGS;
		for (World.Tag el : array) {
			tagMap.put(el, new HashMap<Detail, Set<String>>());
		}

		World.Tag worldTag = null;
		Detail detail = null;
		Set<String> set = new HashSet<String>();

		// "template" for use in formatting
		// tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.ABANDONED_COLONY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("crazed survivors");
		set.add("ruthless plunderers of the ruins");
		set.add("automated defense system");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("inquisitive stellar archaeologist");
		set.add("heir to the colony's property");
		set.add("local wanting the place cleaned out");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the local government wants the ruins to remain a secret");
		set.add("the locals claim ownership of it");
		set.add("the colony is crumbling and dangerous to navigate");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("long-lost property deeds");
		set.add("relic stolen by the colonists when they left");
		set.add("historical record of the colonization attempt");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("decaying habitation block");
		set.add("vine-covered town square");
		set.add("structure buried by an ancient landslide");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.ALIEN_RUINS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("customs inspector");
		set.add("worshipper of the ruins");
		set.add("hidden alien survivor");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("curious scholar");
		set.add("avaricious local resident");
		set.add("interstellar smuggler");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("traps in the ruins");
		set.add("remote location");
		set.add("paranoid customs officials");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("precious alien artifacts");
		set.add("objects left with the remains of a prior unsuccessful expedition");
		set.add("untranslated alien texts");
		set.add("untouched alien ruins");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("undersea ruin");
		set.add("orbital ruin");
		set.add("perfectly preserved alien building");
		set.add("alien mausoleum");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.ALTERED_HUMANITY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("biochauvinist local");
		set.add("local experimenter");
		set.add("mentally unstable mutant");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("local seeking a \"cure\"");
		set.add("curious xenophiliac");
		set.add("anthropological researcher");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("alteration is contagious");
		set.add("alteration is necessary for long-term survival");
		set.add("locals fear and mistrust non-local humans");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("original pretech mutagenic equipment");
		set.add("valuable biological byproduct from the mutants");
		set.add("\"cure\" for the altered genes");
		set.add("record of the original colonial genotypes");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("abandoned eugenics laboratory");
		set.add("an environment requiring the mutation for survival");
		set.add("a sacred site where the first local was transformed");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.AREA_51;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("suspicious government minder");
		set.add("free merchant who likes his local monopoly");
		set.add("local who wants a specimen for dissection");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("crusading offworld investigator");
		set.add("conspiracy-theorist local");
		set.add("idealistic government reformer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the government has a good reason to keep the truth concealed");
		set.add("the government ruthlessly oppresses the natives");
		set.add("the government is actually composed of offworlders");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("elaborate spy devices");
		set.add("memory erasure tech");
		set.add("possessions of the last offworlder who decided to spread the truth");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("desert airfield");
		set.add("deep subterranean bunker");
		set.add("hidden mountain valley");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.BADLANDS_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("mutated badlands fauna");
		set.add("desperate local");
		set.add("badlands raider chief");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("native desperately wishing to escape the world");
		set.add("scientist researching ecological repair methods");
		set.add("ruin scavenger");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("radioactivity");
		set.add("bioweapon traces");
		set.add("broken terrain");
		set.add("sudden local plague");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("maltech research core");
		set.add("functional pretech weaponry");
		set.add("an uncontaminated well");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("untouched oasis");
		set.add("ruined city");
		set.add("salt flat");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.BUBBLE_CITIES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("native dreading outsider contamination");
		set.add("saboteur from another bubble city");
		set.add("local official hostile to outsider ignorance of laws");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("local rebel against the city officials");
		set.add("maintenance chief in need of help");
		set.add("surveyor seeking new building sites");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("bubble rupture");
		set.add("failing atmosphere reprocessor");
		set.add("native revolt against officials");
		set.add("all-seeing surveillance cameras");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("pretech habitat technology");
		set.add("valuable industrial products");
		set.add("master key codes to a city's security system");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("city power core");
		set.add("surface of the bubble");
		set.add("hydroponics complex");
		set.add("warren-like hab block");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.CIVIL_WAR;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("faction commissar");
		set.add("angry native");
		set.add("conspiracy theorist who blames offworlders for the war");
		set.add("deserter looking out for himself");
		set.add("guerilla bandits");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("faction loyalist seeking aid");
		set.add("native caught in the crossfire");
		set.add("offworlder seeking passage off the planet");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the front rolls over the group");
		set.add("famine strikes");
		set.add("bandit infestations");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ammo dump");
		set.add("military cache");
		set.add("treasure buried for after the war");
		set.add("secret war plans");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("battle front");
		set.add("bombed-out town");
		set.add("rear-area red light district");
		set.add("propaganda broadcast tower");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.COLD_WAR;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("suspicious chief of intelligence");
		set.add("native who thinks the offworlders are with the other side");
		set.add("femme fatale");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("apolitical information broker");
		set.add("spy for the other side");
		set.add("unjustly accused innocent");
		set.add("\"he's a bastard but he's our bastard\" official");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("police sweep");
		set.add("low-level skirmishing");
		set.add("\"red scare\"");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("list of traitors in government");
		set.add("secret military plans");
		set.add("huge cache of weapons built up in preparation for war");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("seedy bar in a neutral area");
		set.add("political rally");
		set.add("isolated area where fighting is underway");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.COLONIZED_POPULATION;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("suspicious security personnel");
		set.add("offworlder-hating natives");
		set.add("local crime boss preying on rich offworlders");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("native resistance leader");
		set.add("colonial official seeking help");
		set.add("native caught between the two sides");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("natives won't talk to offworlders");
		set.add("colonial repression");
		set.add("misunderstood local customs");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("relic of the resistance movement");
		set.add("list of collaborators");
		set.add("precious substance extracted by colonial labor");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("deep wilderness resistance camp");
		set.add("city district off-limits to natives");
		set.add("colonial labor site");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.DESERT_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("raider chieftain");
		set.add("crazed hermit");
		set.add("angry isolationists");
		set.add("paranoid mineral prospector");
		set.add("strange desert beast");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("native guide");
		set.add("research biologist");
		set.add("aspiring terraformer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("sandstorms");
		set.add("water supply failure");
		set.add("native warfare over water rights");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("enormous water reservoir");
		set.add("map of hidden wells");
		set.add("pretech rainmaking equipment");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("oasis");
		set.add("the \"empty quarter\" of the desert");
		set.add("hidden underground cistern");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.EUGENIC_CULT;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("eugenic superiority fanatic");
		set.add("mentally unstable homo superior");
		set.add("mad eugenic scientist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("eugenic propagandist");
		set.add("biotechnical investigator");
		set.add("local seeking revenge on cult");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the altered cultists look human");
		set.add("the locals are terrified of any unusual physical appearance");
		set.add("the genetic modifications- and drawbacks- are are contagious with long exposure");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("serum that induces the alteration");
		set.add("elixir that reverses the alteration");
		set.add("pretech biotechnical databanks");
		set.add("list of secret cult sympathizers");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("eugenic breeding pit");
		set.add("isolated settlement of altered humans");
		set.add("public place inflitrated by cult sympathizers");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.EXCHANGE_CONSULATE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("corrupt Exchange official");
		set.add("indebted native who thinks the players are Exchange agents");
		set.add("Exchange official dunning the players for debts incurred");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("Consul in need of offworld help");
		set.add("local banker seeking to hurt his competition");
		set.add("Exchange diplomat");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the local Consulate has been corrupted");
		set.add("the Consulate is cut off from its funds");
		set.add("a powerful debtor refuses to pay");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("Exchange vault codes");
		set.add("wealth hidden to conceal it from a bankruptcy judgment");
		set.add("location of forgotten vault");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("Consulate meeting chamber");
		set.add("meeting site between fractious disputants");
		set.add("Exchange vault");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FERAL_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("decadent noble");
		set.add("mad cultist");
		set.add("xenophobic local");
		set.add("cannibal chief");
		set.add("maltech researcher");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("trapped offworlder");
		set.add("aspiring reformer");
		set.add("native wanting to avoid traditional flensing");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("horrific local \"celebration\"");
		set.add("inexpicable and repugnant social rules");
		set.add("taboo zones and people");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("terribly misused piece of pretech");
		set.add("wealth accumulated through brutal evildoing");
		set.add("valuable possession owned by luckless offworlder victim");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("atrocity amphitheater");
		set.add("traditional torture parlor");
		set.add("ordinary location twisted into something terrible");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FLYING_CITIES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("rival city pilot");
		set.add("tech thief attempting to steal offworlder gear");
		set.add("saboteur or scavenger plundering the city's tech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("maintenance tech in need of help");
		set.add("city defense force pilot");
		set.add("meteorological researcher");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("sudden storms");
		set.add("drastic altitude loss");
		set.add("rival city attacks");
		set.add("vital machinery breaks down");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("precious refined atmospheric gases");
		set.add("pretech grav engine plans");
		set.add("meteorological codex predicting future storms");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("underside of the city");
		set.add("the one calm place on the planet's surface");
		set.add("catwalks stretching over unimaginable gulfs below");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FORBIDDEN_TECH;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("mad scientist");
		set.add("maltech buyer from offworld");
		set.add("security enforcer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("victim of maltech");
		set.add("perimeter agent");
		set.add("investigator reporter");
		set.add("conventional arms merchant");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the maltech is being fabricated by an unbraked A.I.");
		set.add("the government depends on revenue from maltech sales to offworlders");
		set.add("citizens insist that it isn't really maltech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("maltech research data");
		set.add("the maltech itself");
		set.add("previous pretech equipment used to create it");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("horrific laboratory");
		set.add("hellscape sculpted by the maltech's use");
		set.add("government building meeting room");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FRIENDLY_FOE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("driven hater of all their kind");
		set.add("internal malcontent bent on creating conflict");
		set.add("secret master who seeks to lure trust");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("well-meaning bug-eyed monster");
		set.add("principled eugenic cultist");
		set.add("suspicious investigator");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the group actually is as harmless and benevolent as they seem");
		set.add("the group offers a vital service at the cost of moral compromise");
		set.add("the group still feels bonds of affiliation with their hostile brethren");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("forbidden xenotech");
		set.add("eugenic biotech template");
		set.add("evidence to convince others of their kind that they are right");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("reprurposed maltech laboratory");
		set.add("alien conclave building");
		set.add("widely-feared starship interior");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FREAK_GEOLOGY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("crank xenogeologist");
		set.add("cultist who believes it the work of aliens");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("research scientist");
		set.add("prospector");
		set.add("artist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("local conditions that no one remembers to tell offworlders about");
		set.add("lethal weather");
		set.add("seismic activity");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("unique crystal formations");
		set.add("hidden veins of a major mineral strike");
		set.add("deed to a location of great natural beauty");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("atop a bizarre geological formation");
		set.add("tourist resort catering to offworlders");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.FREAK_WEATHER;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("criminal using the weather as a cover");
		set.add("weather cultists convinced the offworlders are responsible for some disaster");
		set.add("native predators depdendent on the weather");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("meteorogical researcher");
		set.add("holodoc crew wanting shots of the weather");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the weather itself");
		set.add("malfunctioning pretech terraforming engines that cause the weather");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("wind-scoured deposits of precious minerals");
		set.add("holorecords of a spectacular and rare weather pattern");
		set.add("naturally-sculpted objects of intricate beauty");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("eye of the storm");
		set.add("the one sunlit place");
		set.add("terraforming control room");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.GOLD_RUSH;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("paranoid prospector");
		set.add("aspiring mining tycoon");
		set.add("rapacious merchant");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("claim-jumped miner");
		set.add("native alien");
		set.add("curious tourist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the strike is a hoax");
		set.add("the strike is of a dangerous toxic substance");
		set.add("export of the mineral is prohibited by the planetary government");
		set.add("the native aliens live around the strike's location");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("cases of the refined element");
		set.add("pretech mining equipment");
		set.add("a dead prospector's claim deed");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("secret mine");
		set.add("native alien village");
		set.add("processing plant");
		set.add("boom town");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.RADICAL_RACISM;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("native convinced that the offworlders are agents of Them");
		set.add("cynical politician in need of scapegoats");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("intelligence agent needing catspaws");
		set.add("holodoc producers needing \"an inside look\"");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the characters are wearing or using items from the hated people or world");
		set.add("the characters are known to have done business with the hate people");
		set.add("the characters \"look like\" the hated Others");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("proof of Their evildoing");
		set.add("reward for turning in enemy agents");
		set.add("relic stolen by Them years ago");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("war crimes museum");
		set.add("atrocity site");
		set.add("captured/decommissioned spaceship kept as a trophy");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.HEAVY_INDUSTRY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("tycoon monopolist");
		set.add("industrial spy");
		set.add("malcontent revolutionary");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("aspiring entrepreneur");
		set.add("worker union leader");
		set.add("ambitious inventor");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the factories are toxic");
		set.add("the resources extractable at their tech level are running out");
		set.add("the masses require the factory output for survival");
		set.add("the industries' major output is being obsoleted by offworld tech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("confidential industrial data");
		set.add("secret union membership lists");
		set.add("ownership shares in an industrial complex");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("factory floor");
		set.add("union meeting hall");
		set.add("toxic waste dump");
		set.add("R&D complex");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.HEAVY_MINING;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("mine boss");
		set.add("tunnel saboteur");
		set.add("subterranean predators");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("hermit prospector");
		set.add("offworld investor");
		set.add("miner's union representative");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the refinery equipment breaks down");
		set.add("tunnel collapse");
		set.add("silicate life forms growing in the miners' lungs");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("the mother lode");
		set.add("smuggled case of refined material");
		set.add("faked crystalline mineral samples");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("vertical mine face");
		set.add("tailing piles");
		set.add("roaring smelting complex");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.HOSTILE_BIOSPHERE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("local fauna");
		set.add("nature cultist");
		set.add("native aliens");
		set.add("callous labor overseer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("xenobiologist");
		set.add("tourist on safari");
		set.add("grizzled local guide");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("filter masks fail");
		set.add("parasitic alien infestation");
		set.add("crop greenhouses lose bio-integrity");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("valuable native biological extract");
		set.add("abandoned colony vault");
		set.add("remains of an unsuccessful expedition");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("deceptively peaceful glade");
		set.add("steaming polychrome jungle");
		set.add("nightfall when surrounded by Things");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.HOSTILE_SPACE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("alien raid leader");
		set.add("meteor-launching terrorists");
		set.add("paranoid local leader");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("astronomic researcher");
		set.add("local defense commander");
		set.add("early warning monitor agent");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the natives believe the danger is divine chastisement");
		set.add("the natives blame offworlders for the danger");
		set.add("the native eilte profit from the danger in some way");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("early warning of a raid or impact");
		set.add("abandoned riches in a disaster zone");
		set.add("key to a secure bunker");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("city watching an approaching asteroid");
		set.add("village burnt in an alien raid");
		set.add("massive ancient crater");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.LOCAL_SPECIALTY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("monopolist");
		set.add("offworlder seeking prohibition of the specialty");
		set.add("native who views the specialty as sacred");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("spy searching for the source");
		set.add("artisan seeking protection");
		set.add("exporter with problems");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the specialty is repugnant in nature");
		set.add("the crafters refuse to sell to offworlders");
		set.add("the specialty is made in a remote dangerous place");
		set.add("the crafters don't want to make the specialty anymore");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("the specialty itself");
		set.add("the secret recipe");
		set.add("sample of a new improved variety");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("secret manufactory");
		set.add("hidden cache");
		set.add("artistic competition for best artisan");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.LOCAL_TECH;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("keeper of the tech");
		set.add("offworld industrialist");
		set.add("automated defenses that suddenly come alive");
		set.add("native alien mentors");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("curious offworld scientist");
		set.add("eager tech buyer");
		set.add("native in need of technical help");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the tech is unreliable");
		set.add("the tech only works on this world");
		set.add("the tech has poorly-understood side effects");
		set.add("the tech is alien in nature");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("the tech itself");
		set.add("an unclaimed payment for a large shipment");
		set.add("the secret blueprints for its construction");
		set.add("an ancient alien R&D database");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("alien factory");
		set.add("lethal R&D center");
		set.add("tech brokerage vault");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.MAJOR_SPACEYARD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("enemy saboteur");
		set.add("industrial spy");
		set.add("scheming construction tycoon");
		set.add("aspiring ship hijacker");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("captain stuck in drydock");
		set.add("maintenance chief in need of help");
		set.add("mad innovator");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the spaceyard is an alien relic");
		set.add("the spaceyard is burning out from overuse");
		set.add("the spaceyard is alive");
		set.add("the spaceyard relies on maltech to function");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("intellectual property-locked pretech blueprints");
		set.add("override keys for activating old pretech facilities");
		set.add("a purchased but unclaimed spaceship");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("hidden shipyard bay");
		set.add("surface of a partially-completed ship");
		set.add("ship scrap graveyard");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.MINIMAL_CONTACT;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("customs official");
		set.add("xenophobic natives");
		set.add("existing merchant who doesn't like competition");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("aspiring tourist");
		set.add("anthropological researcher");
		set.add("offworld thief");
		set.add("religious missionary");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the locals carry a disease harmless to them and lethal to outsiders");
		set.add("the locals hide dark purposes from offworlders");
		set.add("the locals have something desperately needed but won't bring it into the treaty port");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("contraband trade goods");
		set.add("security perimeter codes");
		set.add("black market local products");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("treary port bar");
		set.add("black market zone");
		set.add("secret smuggler landing site");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.RADICAL_SEXISM;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("cultural fundamentalist");
		set.add("cultural missionary to offworlders");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("oppressed native");
		set.add("research scientist");
		set.add("offworld empancipationist");
		set.add("local reformer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the oppressed gender is restive against the customs");
		set.add("the oppressed gender largely supports the customs");
		set.add("the customs relate to some physical quality of the world");
		set.add("\"the oppressed gender has had maltech engineering done to \"tame\" them");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("aerosol reversion formula for undoing engineered docility");
		set.add("hidden history of the world");
		set.add("pretech engineering equipment");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("shrine to the virtues of the favored gender");
		set.add("security center for controlling the oppressed");
		set.add("genetic engineering lab");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.OCEANIC_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("pirate raider");
		set.add("violent \"salvager\" gang");
		set.add("tentacled sea monster");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("daredevil fisherman");
		set.add("sea hermit");
		set.add("sapient native life");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the liquid flux confuses grav engines too badly for them to function on this world");
		set.add("sea is corrosive or toxic");
		set.add("the seas are wracked by regular storms");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("buried pirate treasure");
		set.add("location of enormous schools of fish");
		set.add("pretech water purification equipment");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("the only island on the planet");
		set.add("floating spaceport");
		set.add("deck of storm-swept ship");
		set.add("undersea bubble city");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.OUT_OF_CONTACT;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("fearful local ruler");
		set.add("zealous native cleric");
		set.add("sinister power that has kept the world isolated");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("scheming native noble");
		set.add("heretical theologian");
		set.add("UFO cultist native");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("automatic defenses fire on ships that try to take off");
		set.add("the natives want to stay out of contact");
		set.add("the natives are highly vulnerable to offworld diseases");
		set.add("the native language is completely unlike any known to the group");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ancient pretech equipment");
		set.add("Terran relic brought from Earth");
		set.add("logs of the original colonists");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("long-lost colonial landing site");
		set.add("court of the local ruler");
		set.add("ancient defense battery controls");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.OUTPOST_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("space-mad outpost staffer");
		set.add("outpost commander who wants it to stay undiscovered");
		set.add("undercover saboteur");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("lonely staffer");
		set.add("fixated researcher");
		set.add("overtaxed maintenance chief");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the alien ruin defense systems are waking up");
		set.add("atmospheric disturbances trap the group inside the outpost for a month");
		set.add("pirates raid the outpost");
		set.add("the crew have become converts to a strange set of beliefs");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("alien relics");
		set.add("vital scientific data");
		set.add("secret corporate exploitation plans");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("grimy recreation room");
		set.add("refueling station");
		set.add("the only building on the planet");
		set.add("a \"starport\" of swept bare rock");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PERIMETER_AGENCY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("renegade agency director");
		set.add("maltech researcher");
		set.add("paranoid intelligence chief");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("agent in need of help");
		set.add("support staffer");
		set.add("\"unjustly\" targeted researcher");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the local Agency has gone rogue and now uses maltech");
		set.add("the Agency archives have been compromised");
		set.add("the Agency has been targeted by a maltech-using organization");
		set.add("the Agency's existence is unknown to the locals");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("Agency maltech research archives");
		set.add("Agency pretech spec-ops gear");
		set.add("file of blackmail on local politicians");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("interrogation room");
		set.add("smoky bar");
		set.add("maltech lab");
		set.add("secret Agency base");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PILGRIMAGE_SITE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("saboteur devoted to rival belief");
		set.add("bitter reformer who resents the current leadership");
		set.add("swindler conning the pilgrims");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("protector of the holy site");
		set.add("naïve offworlder pilgrim");
		set.add("outsider wanting to learn the sanctum's inner secrets");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the site is actually a fake");
		set.add("the site is run by corrupt and venal keepers");
		set.add("a natural disaster threatens the site");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ancient relic guarded at the site");
		set.add("proof the site is a fake");
		set.add("precious offering from a pilgrim");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("incense-scented sanctum");
		set.add("teeming crowd of pilgrims");
		set.add("imposing holy structure");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.POLICE_STATE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("secret police chief");
		set.add("scapegoating official");
		set.add("treacherous native informer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("rebel leader");
		set.add("offworld agitator");
		set.add("imprisoned victim");
		set.add("crime boss");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the natives largely believe in the righteousness of the state");
		set.add("the police state is automated and its \"rulers\" can't shut it down");
		set.add("the leaders foment a pogrom against \"offworlder spies\"");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("list of police informers");
		set.add("wealth taken from \"enemies of the state\"");
		set.add("Dear Leader's private stash");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("military parade");
		set.add("gulag");
		set.add("gray concrete housing block");
		set.add("surveillance center");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PRECEPTOR_ARCHIVE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("Luddite native");
		set.add("offworld merchant who wants the natives kept ignorant");
		set.add("religious zealot");
		set.add("corrupted First Speaker who wants to keep a monopoly on learning");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("Preceptor Adept missionary");
		set.add("offworld scholar");
		set.add("reluctant student");
		set.add("roving Preceptor Adept");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the local Archive has taken a very religious and mystical attitude toward their teaching");
		set.add("the Archive has maintained some replicable pretech science");
		set.add("the Archive has been corrupted and their teaching is incorrect");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("lost Archive database");
		set.add("ancient pretech teaching equipment");
		set.add("hidden cache of theologically unacceptable tech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("Archive lecture hall");
		set.add("experimental laboratory");
		set.add("student-local riot");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PRETECH_CULTISTS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("cult leader");
		set.add("artifact supplier");
		set.add("pretech smuggler");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("offworld scientist");
		set.add("robbed collector");
		set.add("cult heretic");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the cultists can actually replicate certain forms of pretech");
		set.add("the cultists abhor use of the devices as \"presumption on the holy\"");
		set.add("the cultists mistake the party's belongings for pretech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("pretech artifacts both functional and broken");
		set.add("religious-jargon laced pretech replication techniques");
		set.add("waylaid payment for pretech artifacts");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("shrine to nonfuctional pretech");
		set.add("smuggler's den");
		set.add("public procession showing a prized artifact");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PRIMITIVE_ALIENS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("hostile alien chief");
		set.add("human firebrand");
		set.add("dangerous local predator");
		set.add("alien religious zealot");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("colonist leader");
		set.add("peace-faction alien chief");
		set.add("planetary frontiersman");
		set.add("xenoresearcher");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the alien numbers are huge and can overwhelm the humans whenever they so choose");
		set.add("one group is trying to use the other to kill their political opponents");
		set.add("the aliens are incomprehensibly strange");
		set.add("one side commits an atrocity");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("alien religious icon");
		set.add("ancient alien-human treaty");
		set.add("alien technology");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("alien village");
		set.add("fortified human settlement");
		set.add("massacre site");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PSIONICS_FEAR;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("mental purity investigator");
		set.add("suspicious zealot");
		set.add("witch-finder");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("hidden psychic");
		set.add("trapped offworlder psychic");
		set.add("offworld educator");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("psychic potential is much more common here");
		set.add("some tech is mistaken as psitech");
		set.add("natives believe certain rituals and customs can protect them from psychic powers");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("hidden psitech cache");
		set.add("possessions of convicted psychics");
		set.add("reward for turning in a psychic");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("inquisitional chamber");
		set.add("lynching site");
		set.add("museum of psychic atrocities");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PSIONICS_WORSHIP;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("psychic inquisitor");
		set.add("haughty mind-noble");
		set.add("psychic slaver");
		set.add("feral prophet");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("offworlder psychic researcher");
		set.add("native rebel");
		set.add("offworld employer seeking psychics");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the psychic training is imperfect and the psychics all show significant mental illness");
		set.add("the psychics have developed a unique discipline");
		set.add("the will of a psychic is law");
		set.add("psychics in the party are kidnapped for \"enlightening\"");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ancient psitech");
		set.add("valuable psychic research records");
		set.add("permission for psychic training");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("psitech-imbued council chamber");
		set.add("temple to the mind");
		set.add("sanitarium-prison for feral psychics");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.PSIONICS_ACADEMY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("corrupt psychic instructor");
		set.add("renegade student");
		set.add("mad psychic researcher");
		set.add("resentful townie");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("offworld researcher");
		set.add("aspiting student");
		set.add("wealthy tourist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the academy curriculum kills a significant percentage of students");
		set.add("the faculty use students as research subjects");
		set.add("the students are indoctrinated as sleeper agents");
		set.add("the local natives hate the academy");
		set.add("the academy is part of a religion");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("secretly developed psitech");
		set.add("a runaway psychic mentor");
		set.add("psychic research prize");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("training grounds");
		set.add("experimental laboratory");
		set.add("school library");
		set.add("campus hangout");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.QUARANTINE_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("defense installation commander");
		set.add("suspicious patrol leader");
		set.add("crazed asteroid hermit");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("relative of a person trapped on the world");
		set.add("humanitarian relief official");
		set.add("treasure hunter");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the natives want to remain isolated");
		set.add("the quarantine is enforced by an ancient alien installation");
		set.add("the world is rife with maltech abominations");
		set.add("the bloackade is meant to starve everyone on the barren world");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("defense grid key");
		set.add("bribe for getting someone out");
		set.add("abandoned alien tech");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("bridge of a blockading ship");
		set.add("defense installation control room");
		set.add("refugee camp");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.RADIOACTIVE_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("bitter mutant");
		set.add("relic warlord");
		set.add("desperate would-be escapee");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("reckless prospector");
		set.add("offworld scavenger");
		set.add("biogenetic variety seeker");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the radioactivity is steadily growing worse");
		set.add("the planet's medical resources break down");
		set.add("the radioactivity has inexplicable effects on living creatures");
		set.add("the radioactivity is the product of a malfunctioning pretech manufactory");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ancient atomic weaponry");
		set.add("pretech anti-radioactivity drugs");
		set.add("untainted water supply");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("mutant-infested ruins");
		set.add("scorched glass plain");
		set.add("wilderness of bizarre native life");
		set.add("glowing barrens");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.REGIONAL_HEGEMON;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("ambitious general");
		set.add("colonial official seeking help");
		set.add("contemptuous noble");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("diplomat");
		set.add("offworld ambassador");
		set.add("foreign spy");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the hegemon's influence is all that's keeping a murderous war from breaking out on nearby worlds");
		set.add("the hegemon is decaying and losing its control");
		set.add("the government is riddled with spies");
		set.add("the hegemon is genuinely benign");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("diplomatic carte blanche");
		set.add("deed to an offworld estate");
		set.add("foreign aid grant");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("palace seat of government");
		set.add("salon teeming with spies");
		set.add("protest rally");
		set.add("military base");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.RESTRICTIVE_LAWS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("law enforcement officer");
		set.add("outraged native");
		set.add("native lawyer specializing in peeling offworlders");
		set.add("paid snitch");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("frustrated offworlder");
		set.add("repressed native");
		set.add("reforming crusader");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the laws change regularly in patterns only natives understand");
		set.add("the laws forbid some action vital to the party");
		set.add("the laws forbid the simple existence of some party members");
		set.add("the laws are secret to offworlders");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("complete legal codex");
		set.add("writ of diplomatic immunity");
		set.add("fine collection vault contents");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("courtroom");
		set.add("mob scene of outraged locals");
		set.add("legislative chamber");
		set.add("police station");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.RIGID_CULTURE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("rigid reactionary");
		set.add("wary ruler");
		set.add("regime idealogue");
		set.add("offended potentate");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("revolutionary agitator");
		set.add("ambitious peasant");
		set.add("frustrated merchant");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the cultural patterns are enforced by technological aids");
		set.add("the culture is run by a secret cabal of manipulators");
		set.add("the culture has explicit religious sanction");
		set.add("the culture evolved due to important necessities that have since been forgotten");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("precious traditional regalia");
		set.add("peasant tribute");
		set.add("opulent treasures of the ruling class");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("time-worn palace");
		set.add("low-caste clums");
		set.add("bandit den");
		set.add("reformist temple");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.SEAGOING_CITIES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("pirate city lord");
		set.add("mer-human raider chieftain");
		set.add("hostile landsman noble");
		set.add("enemy city saboteur");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("city navigator");
		set.add("scout captain");
		set.add("curious mer-human");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the seas are not water");
		set.add("the fish schools have vanished and the city faces starvation");
		set.add("terrible storms drive the city into the glacial regions");
		set.add("suicide ships ram the city's hull");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("giant pearls with mysterious chemical properties");
		set.add("buried treasure");
		set.add("vital repair materials");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("bridge of the city");
		set.add("storm-tossed sea");
		set.add("a bridge fashioned of many small boats");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.SEALED_MENACE;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("hostile outsider bent on freeing the menace");
		set.add("misguided fool who thinks he can use it");
		set.add("reckless researcher who thinks he can fix it");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("keeper of the menace");
		set.add("student of its nature");
		set.add("victim of the menace");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the menace would bring great wealth along with destruction");
		set.add("the menace is intelligent");
		set.add("the natives don't all believe in the menace");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("key to unlock the menace");
		set.add("precious byproduct of the menace");
		set.add("the secret of the menace's true nature");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("guarded fortress containing the menace");
		set.add("monitoring station");
		set.add("scene of a prior outbreak of the menace");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.SECRET_MASTERS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("an agent of the cabal");
		set.add("government official who wants no questions asked");
		set.add("willfully blinded local");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("paranoid conspiracy theorist");
		set.add("Machiavellian gamesman within the cabal");
		set.add("interstellar investigator");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the secret masters have a benign reason for wanting secrecy");
		set.add("the cabal fights openly amongst itself");
		set.add("the cabal is recruiting new members");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("a dossier of secrets on a government official");
		set.add("a briefcase of unmarked credit notes");
		set.add("the identity of a cabal member");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("smoke-filled room");
		set.add("shadowy alleyway");
		set.add("secret underground bunker");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.SECTARIANS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("paranoid believer");
		set.add("native convinced the party is working for the other side");
		set.add("absolutist ruler");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("reformist clergy");
		set.add("local peacekeeping official");
		set.add("offworld missionary");
		set.add("exhausted ruler");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the conflict has more than two sides");
		set.add("the sectarians hate each other for multiple reasons");
		set.add("the sectarians must cooperate or else life on this world is imperiled");
		set.add("the sectarians hate outsiders more than they hate each other");
		set.add("the differences in sects are incomprehensible to an outsider");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("ancient holy book");
		set.add("incontrovertible proof");
		set.add("offering to a local holy man");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("sectarian battlefield");
		set.add("crusading temple");
		set.add("philosopher's salon");
		set.add("bitterly divided village");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.SEISMIC_INSTABILITY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("earthquake cultist");
		set.add("hermit seismologist");
		set.add("burrowing native life form");
		set.add("earthquake-inducing saboteur");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("experimental construction firm owner");
		set.add("adventurous volcanologist");
		set.add("geothermal prospector");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the earthquakes are caused by malfunctioning pretech terraformers");
		set.add("they're caused by alien technology");
		set.add("they're restrained by alien technology that is being plundered by offworlders");
		set.add("the earthquakes are used to generate enormous amounts of energy");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("earthquake generator");
		set.add("earthquake suppressor");
		set.add("mineral formed at the core of the world");
		set.add("earthquake-proof bulding schematics");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("volcanic caldera");
		set.add("village during an earthquake");
		set.add("mud slide");
		set.add("earthquake opening super-heated steam fissures");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.THEOCRACY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("decadent priest-ruler");
		set.add("zealous inquisitor");
		set.add("relentless proselytizer");
		set.add("True Believer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("heretic");
		set.add("offworld theologian");
		set.add("atheistic merchant");
		set.add("desperate commoner");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the theocracy actually works well");
		set.add("the theocracy is decadent and hated by the common folk");
		set.add("the theocracy is divided into mutually hostile sects");
		set.add("the theocracy is led by aliens");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("precious holy text");
		set.add("martyr's bones");
		set.add("secret church records");
		set.add("ancient church treasures");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("glorious temple");
		set.add("austere monastery");
		set.add("academy for ideological indoctrination");
		set.add("decadent pleasure-cathedral");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.TOMB_WORLD;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("demented survivor tribe chieftain");
		set.add("avaricious scavenger");
		set.add("automated defense system");
		set.add("native predator");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("scavenger fleet captain");
		set.add("archaeologist");
		set.add("salvaging historian");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the ruins are full of booby-traps left by the final inhabitants");
		set.add("the world's atmosphere quickly degrades anything in an opened building");
		set.add("a handful of desperate natives survived the cataclysm");
		set.add("the structures are unstable and collapsing");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("lost pretech equipment");
		set.add("psitech caches");
		set.add("stores of unused munitions");
		set.add("ancient historical documents");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("crumbling hive-city");
		set.add("city square carpeted in bones");
		set.add("ruined hydroponic facility");
		set.add("cannibal tribe's lair");
		set.add("dead orbital jump gate");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.TRADE_HUB;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("cheating merchant");
		set.add("thieving dockworker");
		set.add("commercial spy");
		set.add("corrupt customs official");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("rich tourist");
		set.add("hardscrabble free trader");
		set.add("merchant prince in need of catspaws");
		set.add("friendly spaceport urchin");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("an outworlder faction schemes to seize the trade hub");
		set.add("saboteurs seek to blow up a rival's warehouses");
		set.add("enemies are blockading the trade routes");
		set.add("pirates lace the hub with spies");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("voucher for a warehouse's contents");
		set.add("insider trading information");
		set.add("case of precious offworld pharmaceuticals");
		set.add("box of legitimate tax stamps indicating customs dues have been paid");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("raucous bazaar");
		set.add("elegant restaurant");
		set.add("spaceport teeming with activity");
		set.add("foggy street lined with warehouses");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.TYRANNY;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("debauched autocrat");
		set.add("sneering bully-boy");
		set.add("soulless government official");
		set.add("occupying army officer");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("conspiring rebel");
		set.add("oppressed merchant");
		set.add("desperate peasant");
		set.add("inspiring religious leader");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the tyrant rules with vastly superior technology");
		set.add("the tyrant is a figurehead for a cabal of powerful men and women");
		set.add("the people are resigned to their suffering");
		set.add("the tyrant is hostile to \"meddlesome offworlders\"");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("plundered wealth");
		set.add("beautiful toys of the elite");
		set.add("regalia of rulership");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("impoverished village");
		set.add("protest rally massacre");
		set.add("decadent palace");
		set.add("religious hospital for the indigent");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.UNBRAKED_AI;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("AI cultist");
		set.add("maltech researcher");
		set.add("government official dependent on the AI");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("perimeter agent");
		set.add("AI researcher");
		set.add("braked AI");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the AI's presence is unknown to the locals");
		set.add("the locals depend on the AI for some vital service");
		set.add("the AI appears to be harmless");
		set.add("the AI ha fixated on the group's ship computer");
		set.add("the AI wants transport offworld");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("the room-sized AI core itself");
		set.add("maltech research files");
		set.add("perfectly tabulated blackmail on government officials");
		set.add("pretech computer circuitry");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("municipal computing banks");
		set.add("cult compound");
		set.add("repair center");
		set.add("ancient hardcopy");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.WARLORDS;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("warlord");
		set.add("avaricious lieutenant");
		set.add("expensive assassin");
		set.add("aspiring minion");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("vengeful commoner");
		set.add("government military officer");
		set.add("humanitarian aid official");
		set.add("village priest");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the warlords are willing to cooperate to fight mutual threats");
		set.add("the warlords favor specific religions or races over others");
		set.add("the warlords are using substantially more sophisticated tech than others");
		set.add("some of the warlords are better rulers than the government");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("weapons cache");
		set.add("buried plunder");
		set.add("a warlord's personal battle harness");
		set.add("captured merchant shipping");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("gory battlefield");
		set.add("burnt-out village");
		set.add("barbaric warlord palace");
		set.add("squalid refugee camp");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.XENOPHILES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("offworld xenophobe");
		set.add("suspicious alien leader");
		set.add("xenocultural imperialist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("benevolent alien");
		set.add("native malcontent");
		set.add("gone-native offworlder");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the enthusiasm is due to alien psionics or tech");
		set.add("the enthusiasm is based on a lie");
		set.add("the aliens strongly dislike their \"groupies\"");
		set.add("the aliens feel obliged to rule humanity for its own good");
		set.add("humans badly misunderstand the aliens");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("hybrid alien-human tech");
		set.add("exotic alien crafts");
		set.add("sophisticated xenolinguistic and xenocultural research data");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("alien district");
		set.add("alien-influenced human home");
		set.add("cultural festival celebrating alien artist");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.XENOPHOBES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("revulsed local ruler");
		set.add("native convinced some wrong was done to him");
		set.add("cynical demagogue");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("curious native");
		set.add("exiled former ruler");
		set.add("local desperately seeking offworlder help");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the natives are symptomless carriers of a contagious and dangerous disease");
		set.add("the natives are exceptionally vulnerable to offworld diseases");
		set.add("the natives require elaborate purification rituals after speaking to an offworlder or touching them");
		set.add("the local ruler has forbidden any mercantile dealings with offworlders");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("jealously-guarded precious relic");
		set.add("local product under export ban");
		set.add("esoteric local technology");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("sealed treaty port");
		set.add("public ritual not open to offworlders");
		set.add("outcast clum home");
		tagMap.get(worldTag).put(detail, set);

		worldTag = World.Tag.ZOMBIES;
		detail = Detail.ENEMIES;
		set = new HashSet<String>();
		set.add("soulles maltech biotechnology cult");
		set.add("sinister government agent");
		set.add("crazed zombie cultist");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.FRIENDS;
		set = new HashSet<String>();
		set.add("outbreak survivor");
		set.add("doctor searching for a cure");
		set.add("rebel against the secret malefactors");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.COMPLICATIONS;
		set = new HashSet<String>();
		set.add("the zombies retain human intelligence");
		set.add("the zombies can be cured");
		set.add("the process is voluntary among devotees");
		set.add("the condition is infectious");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.THINGS;
		set = new HashSet<String>();
		set.add("cure for the condition");
		set.add("alien artifact that causes it");
		set.add("details of the cult's conversion process");
		tagMap.get(worldTag).put(detail, set);

		detail = Detail.PLACES;
		set = new HashSet<String>();
		set.add("house with boarded-up windows");
		set.add("dead city");
		set.add("fortified bunker that was overrun from within");
		tagMap.get(worldTag).put(detail, set);

	}

	/*
	 * STATIC METHODS
	 */

	// ENEMIES, FRIENDS, COMPLICATIONS, THINGS, PLACES
	public static String getDetail(World.Tag tag, Detail detail) {
		return Dice.randomFromSet(tagMap.get(tag).get(detail));
	}

	public static String completeMissionDetail(World.Tag tag) {
		String string = "Mission details:", detail;

		detail = Dice.randomFromSet(tagMap.get(tag).get(Detail.ENEMIES));
		string += String.format("%nEnemies: %s", detail);

		detail = Dice.randomFromSet(tagMap.get(tag).get(Detail.FRIENDS));
		string += String.format("%nFriends: %s", detail);

		detail = Dice.randomFromSet(tagMap.get(tag).get(Detail.COMPLICATIONS));
		string += String.format("%nComplications: %s", detail);

		detail = Dice.randomFromSet(tagMap.get(tag).get(Detail.THINGS));
		string += String.format("%nThings: %s", detail);

		detail = Dice.randomFromSet(tagMap.get(tag).get(Detail.PLACES));
		string += String.format("%nPlaces: %s", detail);

		return string;
	}

	public static String mission(Set<World.Tag> tags) {
		return mission(-1, tags);
	}

	public static String mission(int index, Set<World.Tag> tags) {
		World.Tag tag = Dice.randomFromSet(tags);

		String[] array = new String[] {
				String.format("An [%s] seeks to rob a [%s] of some precious [%s] that he has desired for some time.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] has been discovered on property owned by a [%s] but a [%s] risks its destruction.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.FRIENDS),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format("A [%s] suddenly hits the party while they're out doing some innocuous activity.",
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"The players unwittingly offend or injure an [%s], incurring his or her wrath. A [%s] offers help in escaping the consequences.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Rumor speaks of the discovery of a precious [%s] in a distant [%s]. The players must get to it before an [%s] does.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An [%s] has connections with offworld pirates of slavers, and a [%s] has been captured by them.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] has been seized by violent revolutionaries or rebels, and a [%s] has been captured wby them.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] is in love with someone forbidden by social convention, and the two of them need help eloping.",
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"An [%s] wields tyrannical power over a [%s], relying on the bribery of corrupt local officials to escape consequences.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] has been lost in hostile wilderness, and the party must reach a [%s] to rescue them in the teeth of a dangerous [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"An [%s] has committed a grave offense against a PC or their family sometimes in the past. A [%s] shows the party a weakness in their defenses.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"The party is suddenly caught in a conflict between two warring families or political parties."),
				String.format(
						"The party is framed for a crime by an [%s], and must reach the sanctuary of a [%s] before they can regroup and find the [%s] that will prove their innocence.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] is threatened by a tragedy of sickness, legal calamity, or public humiliation, and the only one that seems able to save them is an [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A natural disaster or similar [%s] strikes a [%s] while the party is present, causing great loss of life and property unless the party is able to immediately respond to the injured and trapped.",
						getDetail(tag, Detail.COMPLICATIONS), getDetail(tag, Detail.PLACES)),
				String.format(
						"A [%s] with a young business has struck a cache of pretech, valuable minerals, or precious salvage. He or she needs the party to help him or her reach the [%s] where the valuables are.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES)),
				String.format(
						"An oppressed segment of society starts a sudden revolt in the [%s] the party is occupying. An [%s] simply lumps the party in with the rebels and tries to put the revolt down by force. A [%s] offers them a way to either help the rebels or clear their names.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A vulnerable [%s] has been targeted for abduction, and has need of guards. A sudden [%s] makes guarding them from the [%s] seeking their kidnapping much more difficult. If they're snatched, they must be rescued from a [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.COMPLICATIONS),
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"A mysterious [%s] offers the promise of some previous [%s], but access is very dangerous due to wildlife, hostile locals, or a dangerous environment.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] and an [%s] both have legal claim to a [%s], and seek to undermine the others' case. One is willing to do murder if he or she thinks they can get away with it.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS)),
				String.format(
						"An [%s] seeks the death of a [%s] by arranging the failure of his grav flyer or shuttlecraft in dangerous terrain while the party is coincidentally aboard. The party must survive the environment and bring proof of the crime out with them.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				// fixed this one
				String.format(
						"An [%s] seeks to slip word to a lover who is also being courted by an [%s]. A [%s] threatens to cause death or disgrace to the lover unless they either accept the rival's suit or are helped by the party.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"An [%s] is convinced that one of the party has committed adultery with their flirtatious spouse. He means to lure them to a [%s], trap them, and leave them to be killed by the dangers there.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"An [%s] has been driven insane by exotic recreational drugs or excessive use of psionics. He fixes on a PC as being his mortal nemesis, and plots elaborate deaths, attempting to conceal his involvement in the ensuing [%s].",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A [%s] has stolen a precious [%s] from an [%s] and fled to a dangerous, inaccessible [%s]. The party must rescue them and decide what to do with all parties involved.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.THINGS), getDetail(tag, Detail.ENEMIES),
						getDetail(tag, Detail.PLACES)),
				String.format(
						"An [%s] has realized that their brother or sister has engaged in a socially unacceptable affair with a [%s], and means to kill both of them unless stopped by the party.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] has accidentally caused the death of a family member and wants the party to help him hide the body or else stage an accidental death before his family realizes what has happened. A [%s] makes the task more difficult.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A [%s] is a follower of a zealous idealogue who plans to make a violent demonstration of the righteousness of his cause, creating a social [%s]. The follower will surely be killed in the aftermath if not protected or rescued by the party.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A vulnerable family member has been placed in a dangerous situation they have no hope of surviving, prompting your [%s] to take their place at the last moment. They will almost certainly die unless the party intervenes.",
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Suicide bombers detonate an explosive, chemical, or biological weapon in a [%s] occupied by the party where a precious [%s] is stored. The PCs must navigate throngs of terrified people before they become trapped in the collapse.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.THINGS)),
				String.format(
						"An [%s] who controls landing permits, oxygen rations, or some other important resource has a prejudice against one or more of the party members. He demands that they bring a [%s] from a dangerous [%s] before he'll give them what they need.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS), getDetail(tag, Detail.PLACES)),
				String.format(
						"A [%s] in a loveless marriage to an [%s] seeks to escape to be with their beloved, and contacts the party to snatch them from their spouse's guards at a prearranged [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"A [%s] seeks to elope with their lover, and contacts the party to help them meet their paramour at a remote, dangerous [%s]. On arrival, they find the lover is secretly an [%s] desirous of their removal and merely lured them out to meet their doom.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"The party receives or finds a [%s] which proves the crimes of an [%s], yet a [%s] was complicit in the crimes and will be punished as well if the authorities are involved. One of them will stop at nothing to get the item back.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] needs to get to a [%s] on time in order to complete a business contract, but an [%s] means to delay and hinder them until it's too late. The [%s] is a direct result of this interference.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A locked pretech stasis pod has been discovered by a [%s], along with directions to the hidden keycode to open it. The [%s] where the keycode is hidden is now owned by an [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A fierce schism has broken out in the local majority religion, and an [%s] is making a play to take control of the local hierarchy. A [%s] needs the party's help to acquire the [%s] needed for his cause to succeed.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.THINGS)),
				// fixed - added second enemy
				String.format(
						"A former [%s] has been given reason to repent for his treatment of a [%s], and secretly commissions the PCs to help overcome a [%s]. A different [%s] discovers the connection and tries to paint the PCs as double agents.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS),
						getDetail(tag, Detail.COMPLICATIONS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An alien or human with extremely peculiar spiritual belief seeks to visit a [%s] for their own reasons. An [%s] of their own kind attempts to stop them before they cab reach their destination and reveal the [%s] that was hidden their long ago.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s]'s sibling is an untrained psychic, and has been secretly using his or her powers to protect them from an [%s]. The neural damage has finally overwhelmed their sanity and they've kidnapped the very person they were protecting.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				// fixed -- added second enemy
				String.format(
						"A [%s] who is a skilled precognitive has just received a flash of impending atrocity to be committed by an [%s]. He or she needs the party to help them steal the [%s] that will prove the [%s]'s plans while dodging the assassins sent to eliminate the psychic.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] who is an exotic dancer is sought by an [%s] who won't take no for an answer. The dancer is secretly a Perimeter agent attempting to infiltrate a [%s] to destroy maltech research, and plots to use the party to help get him or her into the facility under the pretext of striking at the pursuer.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"A young woman on an interplanetary tour needs to hire local bodyguards. She turns out to be trained and powerful combat psychic, but inexperienced about local dangers, causing a social [%s] that threatens to get the whole group arrested.",
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A librarian [%s] has discovered an antique database with the coordinates of a long-lost pretech cache hidden in a [%s] sacred to a long-vanished religion. The librarian is totally unsuited for danger, but necessary to decipher the obscure religious iconography needed to unlock the cache. The cache is not the anticipated [%s] but something more dangerous to the finder.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A fragment of orbital debris clips a shuttle on the way in, and the spaceport is seriously damaged in the crash. The player's ship or the only vessel capable of getting them off-planet will be destroyed unless the players can organize a response to the dangerous chemical fires and radioactives contaminating the port. A [%s] is trapped somewhere in the control tower wreckage.",
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A [%s] is allied with a reformist religious group that seeks to break the grip of the current, oppressive hierarchy. The current hierarchs have a great deal of political support with the authorities, but the commoners resent them bitterly. The reformer seeks to secure a remote [%s] as a meeting-place for the theological rebels.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES)),
				String.format(
						"A microscopic black hole punctures an orbital station or starship above the world. Its interaction with the station's artificial gravity generators has thrown everything out of whack, and the station's become a minefield of dangerously high or zero gravity zones. It's tearing itself apart, and it's going to collapse soon. An [%s] seeks to escape aboard the last lifeboat and to Hell with everyone else. Meanwhile a [%s] is trying to save his engineer daughter from the radioactive, grav-unstable engine rooms.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"The planet has a sealed alien ruin, and an [%s]-led cult who worships the vanished builders. They're convinced that they have the secret to opening and controlling the power hidden inside the ruins, but they're only half-right. A [%s] has found evidence that shows that they'll only devastate the planet if they meddle with the alien power planet. The party has to get inside the ruins and shut down the engines before it's too late. Little do they realize that a few aliens survive inside, in a stasis field that will be broken by the ruin's opening.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"An [%s] and the group are suddenly trapped in a [%s] during an accident or [%s]. They must work together to escape before it's too late.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A telepathic [%s] has discovered that an [%s] was responsible for a recent atrocity. Telepathic evidence is useless on this world however, and if she's discovered to have scanned his mind she'll be lobotomized as a 'rogue psychic.' A [%s] might be enough to prove his guilt, if the party can figure out how to get to it without revealing their actions.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] is responsible for safeguarding a [%s], yet it is suddenly proven to be a fake. The party must find the real object and the [%s] who stole it or else the keeper will be punished as a thief.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.THINGS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] is bitten by a local animal while in a remote [%s]. The only antidote is back at civilization, yet a [%s] threatens to delay the group until it's too late.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A lethal plague has started among the residents of the town, but a [%s] is keeping aid from reaching them. An [%s] is taking advantage of the panic to hawk a fake cure at ruinous prices and a [%s] is taken in by him.",
						getDetail(tag, Detail.COMPLICATIONS), getDetail(tag, Detail.ENEMIES),
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A radical political party has started to institute pogroms against 'groups hostile to the people.' A [%s] is among those groups, and needs to get out of town before an [%s] uses the riot as cover to settle old scores.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An [%s] has sold the party an expensive but worthlessly flawed piece of equipment before lighting out for the back country. He and his plunder are holed up at a remote [%s].",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"A concert of offworld music is being held in town, and a [%s] is slated to be the star performer. Reactionary elements led by an [%s] plot to ruin the 'corrupting noise' with sabotage that risks getting performers killed. Meanwhile a crowd of ignorant offworlder fans have landed and are infuriating the locals.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An [%s] is wanted on a neighboring world for some heinous act, and a [%s] turns up as a bounty hunter ready to bring him in alive. This world refuses him to extradite him, so the capture and retrieval has to evade local law enforcement.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"An unanticipated solar storm blocks communications and grounds the poorly-shielded grav vehicle that brought the group to this remote [%s]. Then people start turning up dead; the storm has awoken a dangerous [%s].",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] has discovered a partially-complete schematic for an ancient pretech refinery unit that produces vast amounts of something precious on this world- water, oxygen, edible compounds, or the like. Several remote [%s]s on the planet are indicated as having the necessary pretech spare parts required to build the device. When finally assembled, embedded self-modification software in the [%s] modifies itself into a pretech combat bot. The salvage from it remains very valuable.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] ensnares the party where they are in an annoying but seemingly ordinary event. In actuality, an [%s] is using it as cover to strike at a [%s] or [%s] that happens to be where the PCs are.",
						getDetail(tag, Detail.COMPLICATIONS), getDetail(tag, Detail.ENEMIES),
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] has a cranky, temperamental artificial heart installed, and the doctor who put it in is the only one who really understands how it works. The heart has recently started to stutter but the doctor has vanished. An [%s] has snatched him to fit his elite assassins with very unsafe combat mods.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A local clinic is doing wonders in providing free health care to the poor. In truth, it's a front for an offworld eugenics cult, with useful 'specimens' kidnapped and shipped offworld while 'cremated remains' are given to the family. A [%s] is snatched by them, but the party knows they'd have never consented to cremation as the clinic staff claim.",
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Space pirates have cut a deal with an isolated backwoods settlement, off loading their plunder to merchants who meet them there. The party is entrusted with a [%s] to bring to a [%s] who is visiting family after a long absence, but is then kidnapped or killed before they can send word of the dealings.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A reclusive psychiatrist is offering treatment for violent mentally ill patients at a remote [%s]. His treatments seem to work, calming the subjects and returning them to rationality, though major memory loss is involved and some severe social clumsiness ensues. In actuality he's removed large portions of their brains to fit them with remote-control units slaved to an AI in his laboratory. He intends to use them as drones to acquire more 'subjects.'",
						getDetail(tag, Detail.PLACES)),
				String.format(
						"Vital medical supplies against an impending plague have been shipped in from offworld but the spike drive craft that was due to deliver them mis-jumped and has arrived in-system as a lifeless wreck transmitting a blind distress signal. Whoever gets there first can hold the whole planet hostage, and an [%s] means to do just that.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] has spent a substantial portion of their wealth on an ultra-modern new domicile, and invites the party to enjoy a weekend there. An [%s] has hacked the house's computer system to trap the inhabitants inside and use the automated fittings to kill them.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A mud slide, hurricane, earthquake, or other form of disaster strikes a remote settlement. The party is the closest group of responders, and must rescue the locals while dealing with the unearthed, malfunctioning pretech [%s] that threatens to cause an even greater calamity if not safely defused.",
						getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] has found a lost pretech installation, and needs help to loot it. By planetary law, the contents belong to the government.",
						getDetail(tag, Detail.FRIENDS)),
				String.format(
						"An [%s] mistakes the party for the kind of offworlders who will murder innocents for pay- assuming they aren't that kind, anyway. He's sloppy with the contact and unwittingly identifies himself, letting the players know that a [%s] will shortly die unless the employer is stopped.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A party member is identified as a prophesied savior for an oppressed faith or ethnicity. The believers obstinately refuse to believe any protestations to the contrary, and cynical [%s] in government decides the PC must die simply to prevent the risk of uprising. An equally cynical [%s] is determined to push the PC forward as a martyr because they're needed.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Alien beasts escape from a zoo and run wild through the spectators. The panicked owner offers large rewards for recapturing them live, but some of the beasts are quite dangerous."),
				String.format(
						"A trained psychic is accused of going feral by an [%s]. The psychic had already suffered severe neural damage before being found for training, so brain scans cannot establish signs of madness. The psychic seems unstable, but not violent- at least, on short acquaintance. The psychic offers a psychic PC the secrets of psychic mentorship training if they help him flee.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] is the token of rulership on this world, and it's gone missing. If it's not found rapidly, the existing ruler will be deposed. Evidence left at a [%s] suggests that an [%s] has it, but extralegal means are necessary to investigate fully.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"Psychics are vanishing, including a [%s]. They're being kidnapped by an ostensibly-rogue government researcher who is using them to research the lost psychic disciplines that helped enable pretech manufacturing, and being held at a remote [%s]. The snatcher is a small-time local [%s] with unnaturally ample resources.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.PLACES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A [%s] desperately seeks to hide evidence of some past crimes that will ruin his life should it come to light. An [%s] holds the [%s] that proves his involvement and blackmails him ruthlessly.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A courier mistakes the party for the wrong set of offworlders and wordlessly deposits a [%s] with them that implies something awful: med-frozen, child-sized human organs for example, or a private catalog of engineered human slaves. The courier's boss shortly realizes the error, and this [%s] tries to silence the PCs while preserving the [%s] where his evil is enacted.",
						getDetail(tag, Detail.THINGS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.PLACES)),
				String.format(
						"A slowboat system freighter is taken over by [%s] separatist terrorists at the same time as the planet's space defenses are taken offline by internal terrorist attacks. The freighter is aimed straight at the starport and will crash into it within hours if not stopped.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"Alien artifacts on the planet's surface start beaming signals into the system's asteroid belt. The signals provoke a social [%s] in panicked response, and an [%s] seeks to use the confusion to take over. The actual effect of the signals might be harmless, or might summon a long-lost alien AI warship to scourge life from the world.",
						getDetail(tag, Detail.COMPLICATIONS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An alien ambassador [%s] is targeted by xenophobe [%s] assassins. Relations are so fragile that if the ambassador even realizes that humans are making a serious effort to kill him, the result may be war.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A new religion is being preached by a [%s] on this planet. Existing faiths are not amused and an [%s] among the hierarchy is provoking the people to persecute the new believers, hoping for things to get out of hand.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An [%s] was once the patron of a [%s] until the latter was betrayed. Now they want revenge and they think they have the information necessary to pull it off.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Vital life support or medical equipment has been sabotaged by offworlders or zealots, and must be repaired before time runs out. The only possible source of parts is at a [%s] and the saboteurs can be expected to be working hard to get there and destroy them too.",
						getDetail(tag, Detail.PLACES)),
				String.format(
						"A [%s] is importing offworld tech that threatens to completely replace the offerings of an [%s] businessman. The rival seeks to sabotage the newcomer's stock, thus 'proving' its inferiority.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An Exchange diplomat is negotiating for the opening of a branch of the interstellar bank on this world. An [%s] among the local banks wants to show the world as being ungovernably unstable, so provokes the [%s] around the diplomat.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"An [%s] is infuriated by the uppity presumption of an ambitious [%s] of a lower social caste, and tries to pin a local [%s] on the results of his unnatural rejection of his proper place.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.FRIENDS),
						getDetail(tag, Detail.COMPLICATIONS)),
				String.format(
						"A [%s] is working for an offworld corporation to open a manufactory and is ignoring local traditions that privilege certain social or ethnic groups, giving jobs to the most qualified workers instead. An angry [%s] seeks to sabotage the factory.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An offworld musician who was revered as little less than a god on his homeworld requires bodyguards. He immediately acquires an [%s] on this world with his riotous ways, and his guards must keep him from getting arrested if they are to be paid.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"Atmospheric disturbances, dust storms, or other particulate clouds suddenly blow into town, leaving the settlement blind. An [%s] commits a murder during the darkness and attempts to frame the players as convenient scapegoats.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"An [%s] spikes the oxygen supply of an orbital station or unbreathable-atmosphere hab dome with hallucinogens as cover for a theft. Most victims are merely confused and disoriented but some become violent in their delusions. By chance the party's air supply was not contaminated.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"By coincidence, one of the party members is wearing clothing indicative of membership in a violent political group, and thus the party is treated in friendly fashion by a local [%s] for no obvious reason. They assume that the party will go along with some vicious crime without complaint, and the group isn't informed of what's in the offing until they're in deep.",
						getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A local ruler wishes offworlders to advise him of the quality of his execrable poetry- and is the sort to react very poorly to anything less than evidently sincere and fulsome praise. Failure to amuse the ruler results in the party being dumped in a dangerous [%s] to 'experience truly poetic solitude.'",
						getDetail(tag, Detail.PLACES)),
				String.format(
						"A [%s] among the locals is unreasonably convinced that offworlder tech can repair anything and has blithely promised a powerful local [%s] that the party can easily fix a damaged pretech [%s]. They've already invested heavily in expensive spare parts but the truly necessary pieces are kept in a still-dangerous pretech installation in a remote [%s].",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS),
						getDetail(tag, Detail.PLACES)),
				String.format(
						"The party's offworld comm gear picks up a chance transmission from the local government and automatically descrambles the primitive encryption key. The document is proof that an [%s] is government intends to commit an atrocity against a local village with a group of 'deniable' renegades in order to steal a valuable [%s] kept in the village.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS)),
				String.format(
						"A [%s] belongs to a persecuted faith, ethnicity, or social class, and appeals for the PCs to help a cell of rebels get offworld before the [%s] law enforcement finds them.",
						getDetail(tag, Detail.FRIENDS), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A part on the party's ship or the only other transport out has failed, and requires immediate replacement. The only available part is held by an [%s], who will only willingly relinquish it in exchange for a [%s] held by an innocent [%s] who will refuse to sell at any price.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.THINGS), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"Eugenics cultists are making engineered slaves out of genetic material gathered at a local brothel. Some of the unnaturally tempting slaves are being slipped among the prostitutes as baits to infatuate powerful officials, while others are being sold under the table to less scrupulous elites."),
				String.format(
						"Evidence has been unearthed at a [%s] that substantial portions of the planet are actually owned by members of an oppressed and persecuted group. The local courts have no intention of recognizing the rights but the codes with the ownership evidence would allow someone to completely bypass a number of antique pretech defenses around the planetary governor's palace. A [%s] wants the codes to pass among a rebel group.",
						getDetail(tag, Detail.PLACES), getDetail(tag, Detail.FRIENDS)),
				String.format(
						"A crop smut threatens the planet's agriculture, promising large-scale famine. A [%s] finds evidence that a secret government research station in the system's asteroid belt was conducting experiments in disease-resistant crop strains before the Cataclysm cut off communications with the station. The existing government considers it a wild goose chase, but the party might choose to help.",
						getDetail(tag, Detail.FRIENDS)),
				//
				String.format(
						"A grasping [%s] in local government seizes the party's ship for some trifling offense. The [%s] wants to end offworld trade, and is trying to scare other traders away. The starship is held within a military cordon and they're confident by the time other elements of the government countermand the order, other free traders will have been spooked off.",
						getDetail(tag, Detail.ENEMIES), getDetail(tag, Detail.ENEMIES)),
				String.format(
						"A seemingly useless trinket purchased by a PC turns out to be the security key to a lost pretech facility. It was sold by accident by a bungling and now-dead minion of a local [%s] who is hot after the party to 'reclaim' his property: preferably after the party defeats whatever automatic defenses and bots the facility might still support.",
						getDetail(tag, Detail.ENEMIES)) };

		String string;
		if (index > -1 && index < array.length)
			string = array[index];
		else
			string = array[Dice.roll(array.length) - 1];

		return string;
	}

}
