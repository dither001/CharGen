
public interface Faction {
	public enum Trait {
		AMBITIOUS, ANIMISTIC, ASCETIC, CONFORMIST, COSMOPOLITAN, CRUEL, CURIOUS, ENVIOUS, FRACTIOUS, HARMONIOUS, HEDONISTITC, HONORABLE, INDIVIDUALISTIC, PACIFISTIC, RATIONAL, RESIGNED, SELF_CONFIDENT, SELF_LOATHING, SUSPICIOUS, WARLIKE
	}

	public enum Conflict {
		BIGOTRY, FREEDOM, INEQUALITY, LAND, NATIONALISM, PRIVATION, RESENTMENT, RESOURCES, SCHISM, WAR
	}

	/*
	 * STATIC FIELDS
	 */
	public static final Trait[] TRAITS = { Trait.AMBITIOUS, Trait.ANIMISTIC, Trait.ASCETIC, Trait.CONFORMIST,
			Trait.COSMOPOLITAN, Trait.CRUEL, Trait.CURIOUS, Trait.ENVIOUS, Trait.FRACTIOUS, Trait.HARMONIOUS,
			Trait.HEDONISTITC, Trait.HONORABLE, Trait.INDIVIDUALISTIC, Trait.PACIFISTIC, Trait.RATIONAL, Trait.RESIGNED,
			Trait.SELF_CONFIDENT, Trait.SELF_LOATHING, Trait.SUSPICIOUS, Trait.WARLIKE };

	public static final Conflict[] CONFLICTS = { Conflict.BIGOTRY, Conflict.FREEDOM, Conflict.INEQUALITY, Conflict.LAND,
			Conflict.NATIONALISM, Conflict.PRIVATION, Conflict.RESENTMENT, Conflict.RESOURCES, Conflict.SCHISM,
			Conflict.WAR };

	/*
	 * BIGOTRY
	 */
	public static final String[] BIGOTRY_DETAILS = {
			"A small but vigorous religion is considered a dangerous social menace.",
			"A particular ethnicity is thought to be unfit for more than brute labor.",
			"Locals from a particular region are viewed as stupid, treacherous, or violent.",
			"An engineered strain of humanity is counted unworthy of true human rights.",
			"Citizens of a conquered nation or community are viewed as contemptible weaklings.",
			"An alien enclave is regarded in the worst possible light, their traits exaggerated as moral flaws.",
			"The ruling elite and their functionaries view the common people as beasts.",
			"Forbidden tech has been used to breed a race of cognitively limited or physically disabled humans." };
	public static final String[] BIGOTRY_CONSTRAINTS = {
			"The scorned group has significant military or financial power.",
			"Tradition reserves certain important roles for members of the scorned group.",
			"The locals have a sense of paternalistic obligation toward their \"lessers.\"",
			"The oppressed have been convinced that their treatment is only just.",
			"The scorned group is prone to outbreaks of savage reprisal against the bigots.",
			"Both the scorned and the bigots have roughly equal influence and power, though perhaps in different spheres.",
			"The scorned have organized their own culture in ways that resist the bigots.",
			"A recent pogrom has provoked an uneasy sense of going too far among the bigots." };
	public static final String[] BIGOTRY_CHANGES = {
			"Intermarriage and social dealings with the scorned are forbidden.",
			"Communities have ritualized festivals of execration and mockery.",
			"Families celebrate the \"purity\" of their lineage.",
			"The scorned are forced to wander from community to community.",
			"A certain mode of dress or behavior common to the scorned is forbidden to others.",
			"The scorned are forbidden from accumulating wealth or property beyond what they need for bare survival.",
			"Contracts and promises to the scorned can be nullified when convenient.",
			"Members of the scorned are taken for concubines, though openly admitting as much is social suicide." };

	/*
	 * FREEDOM
	 */
	public static final String[] FREEDOM_DETAILS = {
			"Slavery is an accepted part of the society, provoking anger among native abolitionists.",
			"Speech is strictly regulated by the government.",
			"The government claims the right to restrict travel and social associations.",
			"Rigid castes compel natives into certain preordained careers or social roles.",
			"The domoinant culture is convinced that they have a perfect society, and any deviation from its norms is a seed of evil.",
			"Only the government or the eilte are permitted to own private property.",
			"Freedom of conscience does not exist; all are expected to subscribe to the religious beliefs of the dominant group.",
			"A minority seeks political independence from the greater society." };
	public static final String[] FREEDOM_CONSTRAINTS = {
			"The majority of citizens are sincerely convinced that the desired freedom is harmful.",
			"The freedom desired was once held, and has only been restricted recently.",
			"The rebels are reluctant to press their case out of fear or a desire for peace.",
			"The freedom was once held, and is blamed for some great disaster.",
			"The freedom is selectively extended to the powerful and important, blunting their concerns.",
			"Agitators paid by the ruling class paint the freedom as an invitation to chaos.",
			"Certain poor or unimportant areas of the planet have freedom, attracting the most vigorous proponents.",
			"The government or ruling class is constantly promising that the freedom will soon be recognized." };
	public static final String[] FREEDOM_CHANGES = { "Secret cells form in the society to advance the greater cause.",
			"Discussion of the conflict is forbidden in \"decent\" society.",
			"Supporters of the cause self-aggregate by marriage and association.",
			"Support for the cause becomes bound up in a specific religion or ethnicity.",
			"The government or dominant culture carefully watches outsiders lest they encourage the discontented.",
			"More than a few rulers and officials secretly sympathize with the malcontents.",
			"Supporters of the cause cultivate wildly implausible expectations of what the freedom will bring them.",
			"The malcontents, leaders have succumbed to cynicism and greed, and use their supporters as pawns for their ends." };

	/*
	 * INEQUALITY
	 */
	public static final String[] INEQUALITY_DETAILS = {
			"Venal mercantile powers corrupted the government--or vice versa--into ensuring that they held the vast majority of the planet's wealth.",
			"Most wealth was concentrated in the hands of a narrow caste who used the law to perpetuate their affluence.",
			"Certain castes or groups were considered to be distinctly superior.",
			"Onerous civic duties were imposed upon a particular disfavored group.",
			"Members of a particular ethnicity, religion, or caste are enslaved.",
			"A substantial portion of the population is considered unfit for self-government.",
			"Only certain groups or castes are permitted to run businesses or posses significant wealth.",
			"Only certain groups or castes are permitted to receive education beyond their work needs." };
	public static final String[] INEQUALITY_CONSTRAINTS = {
			"The oppression is tempered by fear of violent riots and uprisings by the oppressed.",
			"Rebels operate guerilla cells among the oppressed.",
			"The oppressors feel a paternalistic need to \"protect\" the oppressed.",
			"An outside power threatens to intervene if the oppression grows too great.",
			"The oppressed provide a vital service for their oppressors.",
			"The oppressed play various powerful oppressors against ach other.",
			"The oppressed are convinced by faith or culture that this is their proper lot.",
			"The elite among the oppressed are allowed entrance into the oppressor class." };
	public static final String[] INEQUALITY_CHANGES = {
			"The oppressed have cultural patterns that serve to cripple their own prosperity and freedom.",
			"Large meetings or groups of people are forbidden to assemble on this world.",
			"Different groups of oppressed despise each other as lapdogs or troublemakers.",
			"The oppressed share their own language and form of dress.",
			"The oppressors encourage various harmful habits in the oppressed.",
			"It is possible for a disgraced member of the elite to be degraded from their state.",
			"The same groups vary the patterns of oppressor and oppressed at different places or times.",
			"The oppressed have constructed a past where they once ruled--truth or fiction as it may be." };

	/*
	 * LAND
	 */
	public static final String[] LAND_DETAILS = {
			"The land has religious importance, and the owners of it control the local majority faith.",
			"The land constitutes the planet's first settlement, and holding it grants the possessor governmental control.",
			"Two populations fight over ancestral lands too small to now hold them both.",
			"Ancient deeds of possession seem to grant the land to more than one owner.",
			"The land is said to conceal ancient pretech relics or lost treasure.",
			"The land is rich in some precious resource.",
			"The land is just a proxy; one side simply wishes to destroy the other, and so uses the dispute as an excuse for conflict.",
			"The land is partly populated by members of a group that now wishes to seize it to \"protect their countrymen.\"" };
	public static final String[] LAND_CONSTRAINTS = {
			"The land has a fragile ecology or geology, and might be rendered worthless by serious fighting.",
			"The land is presently uneasily shared by both sides, though neither is satisfied.",
			"The current natives of the land detest both sides involved.",
			"The land's value is limited without certain presently-lost secrets.",
			"One side could afford to lose the land, and is considering cutting their losses.",
			"Control of the land seesaws back and forth with no permanent resolution.",
			"A third party stands ready to steal the land if the quarreling groups exhaust themselves in direct combat.",
			"Both sides are confident that their diplomats can force a concession, and are holding back from war at present." };
	public static final String[] LAND_CHANGES = {
			"Natives get very ugly toward strangers who fail to admit the obvious legitimacy of their claims.",
			"The locals have an acute sensitivity toward trespass of all kinds, and punish it ruthlessly.",
			"Much of the local music revolves around the disputed land and its preciousness.",
			"\"Refugees\" displaced for generations are used as political pawns.",
			"Guerilla fighting over the land is an honored tradition for youths.",
			"Outsiders are vigorously recruited to the struggle for possession.",
			"The locals ignore even objectively superior, more valuable land in favor of fighting over this foot of earth.",
			"The heirs of the land's former rulers are kept as bargaining chips by powerful factions." };

	/*
	 * NATIONALISM
	 */
	public static final String[] NATIONALISM_DETAILS = {
			"The division is the result of an old civil war with an indecisive outcome.",
			"Different ethnicities have formed the nations, and deal harshly with others.",
			"One of the nations is a breakaway province with a long list of grievances.",
			"One of the nations is a haven to a downtrodden class despised by others.",
			"The nations stem from different colonization attempts.",
			"A planetary government exists in name-only; the world is balkanized into numerous small states.",
			"Each state sees the others as dangerous rivals for resources and control.",
			"The nations appear almost identical in culture and ideals, but the locals insist that the tiny differences are of dramatic moral importance." };
	public static final String[] NATIONALISM_CONSTRAINTS = {
			"The nations are too evenly matched in military power to guarantee victory.",
			"The nations are too tightly economically interwoven to afford open war.",
			"A natural peril demands cooperation among the planet's nations.", "Natural barriers separate the nations.",
			"Strong peace parties exist in both nations, though they remain minorities.",
			"The nations recently fought an inconclusive war, and most fear a second round might finish them off.",
			"The current rulers are trying to cool down tensions between the nations.",
			"A religious reformer is calling upon both sides, faithful to calm their ardor." };
	public static final String[] NATIONALISM_CHANGES = { "Flags and national insignias are considered semi-sacred.",
			"Particular songs, colors, or objects are considered symbols of allegiance.",
			"Outsiders tend to be treated as spies for the enemy nation.", "War refugees live in brutal poverty.",
			"Social status is now based upon \"patriotic sacrifice.\"",
			"Membership in the state majority religion is considered necessary for all \"true patriots.\"",
			"Propaganda has convinced the locals of numerous complete falsehoods.",
			"Both sides are struggling fiercely to contain the other, trying to prevent them from forming alliances with outside powers or off-worlders." };

	/*
	 * PRIVATION
	 */
	public static final String[] PRIVATION_DETAILS = {
			"Food plants are overwhelmed by an alien disease that causes crop failure.",
			"Water on this world requires processing to remove trace poisons, and the processing is difficult.",
			"Only a few, insufficient areas of the planet can be used for agriculture.",
			"The atmosphere has trace impurities, and costly filtration or genetic therapy is needed to avoid respiratory disease.",
			"Drastic weather patterns restrict the habitable areas of the planet.",
			"Many areas of the planet are naturally radioactive, requiring costly filters.",
			"The local biosphere's crops provide minimal nutrition, requiring costly supplemental diets.",
			"The soil of the world is slightly toxic, requiring elaborate cleansing and filtration systems for homes and crops." };
	public static final String[] PRIVATION_CONSTRAINTS = {
			"Strong communal mores compel sharing on pain of exile or death.",
			"Marginal societal members are denied resources in order that the others may have enough.",
			"Contraception or infanticide is used to keep the population within rigid limits.",
			"Power and status in society revolves around producing or controlling the resource.",
			"Relic pretech is available to provide the resource, though it cannot be repaired.",
			"The privation is cyclical; the phenomenon abates or eases at times.",
			"Social groups struggle for control of the resources; the losers get a small share to prevent open warfare.",
			"The locals scavenge supplies from ruins or ancient caches to support their population." };
	public static final String[] PRIVATION_CHANGES = {
			"The neede resource becomes the foundation of their monetary system.",
			"For food- or water-limited societies, cannibalism or human dessication is accepted.",
			"Wasting the resource is a capital offense.",
			"Judicial punishments revolve around restricting resource access.",
			"The natives are bred or engineered to require less of the resource.",
			"Places where the resource is produced or purified are sacrosanct.",
			"Social ties all revolve around relationships with a particular resource provider.",
			"Prophets and demagogues rise based on their ability to provide more of a resource." };

	/*
	 * RESENTMENT
	 */
	public static final String[] RESENTMENT_DETAILS = {
			"An aggrieved group blames another group for a past planetary disaster.",
			"An aggrieved group still hates another group for a past war.",
			"The poor and moderately well-off of a world are animated by bitter envy.",
			"The wealthy elite of the world are convinced the poor are planning to revolt.",
			"One group hates another because the latter refused to aid the former in a time of crisis.",
			"One group is convinced the prosperity of another group comes from the theft of resources that rightly belong to them.",
			"One group nurses a bitter grievance over an ancient insult from a rival group.",
			"One group despises another, whom they are convinced should rightfully serve them were it not for an old treachery." };
	public static final String[] RESENTMENT_CONSTRAINTS = {
			"The aggrieved group is dependent upon those they resent for jobs or resources.",
			"The aggrieved group fears the consequences of open conflict, and so maintains public civility.",
			"The aggrieved group tries to show up their rivals through their own prosperity rather than acts against their enemies.",
			"The aggrieved group is trying to rally others to the justice of their cause.",
			"The aggrieved group is too weak and lacking in influence to seriously concern the objects of their ire.",
			"The aggrieved group has the will, but not the coherent leadership to act on its resentments.",
			"The aggrieved group uses the resentment simply as an excuse for selfish ambition.",
			"The aggrieved group recently tried to strike at its enemies but failed badly, and now licks its wounds." };
	public static final String[] RESENTMENT_CHANGES = {
			"Members of the rival groups rigorously avoid any social interaction.",
			"Members of the aggrieved group work very cheaply if the task involves harming their objects of loathing.",
			"The aggrieved are willing even to harm themselves so long as their rivals are hurt worse.",
			"The aggrieved will never permit a despised enemy to outdo them.",
			"The aggrieved make memorialization of the wrongs they suffered part of their religion.",
			"The aggrieved and those they despise have separate professional societies.",
			"The aggrieved have formed an impressive body of memorial art.",
			"The aggrieved have lost almost all of what once made them a distinct group except for the shared hate." };

	/*
	 * RESOURCES
	 */
	public static final String[] RESOURCES_DETAILS = {
			"One part of the society is convinced of the need for a communist dictatorship of the proletariat.",
			"One part of the society is ruthlessly laissez-faire, and is against any vestige of public charity or support.",
			"The locals anticipate ecological collapse or resource exhuation in the near future.",
			"Their ancestors consumed all the most available resources, leaving only the dregs for their children.",
			"Attaining the next tech level requires a heavy concentration of wealth with one of the competing merchant factions.",
			"Political legitimacy lies with the leader who can obtain the most resources for his clients.",
			"A substantial portion of local resources were lost or ruined by some disaster.",
			"The underclass is constantly railing for greater transfer payments from others." };
	public static final String[] RESOURCES_CONSTRAINTS = {
			"The society is still living off the capital of their ancestors.",
			"Recent discovery or development of substantial new supplies of resources have temporarily cooled the situation.",
			"The world is rich enough that even the poorest do not die of privation.",
			"Status rests on how much a person can give to their friends and servants.",
			"A recent extremist economic policy has collapsed, and left the survivors wary of radical solutions.",
			"At least one major faction is confident that either God or history will shortly deliver the resources to their control.",
			"Rigid social mores insist on a level of sharing with other members of society.",
			"Ritualized theft is permitted for certain people or classes." };
	public static final String[] RESOURCES_CHANGES = {
			"Ostentatious displays of wealth are expected from those who would rise above the common people.",
			"Social mores discourage entrepreneurship, as the wealthy are forced to share most of their wealth.",
			"Marriages and social bonds are all fundamentally economic in nature.",
			"Society has little use for credits as a form of currency, preferring tokens backed by commodities or real estate.",
			"Crimincals or exiles are forbidden from owning property; whatever they have can be taken from them by anyone.",
			"People can be bought and sold as indentured servants or slaves.",
			"Property laws are Byzantine and prone to legal chicanery.",
			"The clergy of the world have merged with the bankers and financiers." };

	/*
	 * SCHISM
	 */
	public static final String[] SCHISM_DETAILS = {
			"The world's dominant religion splinters into two or more mutually-anathematizing faiths.",
			"The majority religion develops a sect of zealous and rigorous radicals.",
			"The majority religion advocates a belief that is demonstrably false and harmful, inducing more moderate sects to form.",
			"The majority religion becomes corrupt and venal, provoking reform groups that may be overzealous.",
			"A new prophet rises with a creed in direct opposition to the majority faith.",
			"Alien relics or visitors provoke a synergy between their beliefs and sectarians of the majority faith.",
			"Hostile religious sects become closely identified with specific ethnicities or communities.",
			"Relics or evidence are uncovered that the world's majority faith is based on a hoax or scheme." };
	public static final String[] SCHISM_CONSTRAINTS = {
			"An outside religious organization is respected by all, and acts as diplomats and negotiators.",
			"One sect is so much stronger than the other that open warfare is suicidal.",
			"The most violent sects demand such painful sacrifices from adherents that they remain few in number.",
			"The sect leaders are actually calm-headed, but the common believers are often extremists.",
			"The competing sects all answer to the same supreme authority.",
			"The most violently radical believers are very isolated and avoid contact with others.",
			"The sects limit membership to certain ethnicities, professions, or communities, and so do not grow very large.",
			"The sects share control over a holy site or valuable resource, and know that fighting would destroy it." };
	public static final String[] SCHISM_CHANGES = {
			"The locals all wear clothing or insignia showing their religious affiliations.",
			"Communities are based on shared religion.", "Different sects dominate different professions.",
			"Different sects have assumed control of important resources.",
			"The sects condemn intermarriage or social contact with their opponents.",
			"Sect affiliation is shown by tattooing, scarification, or other permanent modification.",
			"Sect membership involves the rigorous taboo of some normal behavior.",
			"Religious beliefs are extremely private and discussing them in public is punished." };

	/*
	 * WAR
	 */
	public static final String[] WAR_DETAILS = {
			"Horrific violence engulfed almost every significant community on the planet.",
			"A slow-burn guerilla struggle went on between ruthless rebels and the central government.",
			"A brief, savage war of extermination broke out between two or more groups.",
			"A cold war between communities or factions regularly flared up.",
			"Highly ritualized combat went on between two or more groups, the fighting contained away from civilians.",
			"A faction acquired tech or military strength sufficient to goad a war with their rivals.",
			"Warfare became institutionalized in society, with factional combat the normal means of resolving conflicts.",
			"A brutal deadlock ground on for decades between evenly-matched groups." };
	public static final String[] WAR_CONSTRAINTS = {
			"The combatants fought each other out, subsiding out of war-weariness.",
			"The conflict was so destructive that those involved were essentially exterminated as meaningful groups.",
			"One side decisively conquered the other, crushing it as a military rival.",
			"One side lost its industrial or technological base, and collapsed.",
			"One side was betrayed by its leadership and was forced to surrender.",
			"One side is convinced it was betrayed by its leadership, when in truth it simply had lost.",
			"An outside party forced a peace between the combatants.",
			"An outside enemy or threat compelled the foes to ally against it." };
	public static final String[] WAR_CHANGES = {
			"The populace is inured to death; human life is cheap to them, even their own.",
			"The defeated group is demoralized and has no faith in their own culture.",
			"The horrors of war have made the populace highly averse to military action.",
			"The populace romanticizes life before the war as better and nobler than it was.",
			"Military organizations and formations have become important social groups.",
			"Many locals bear marks from old radiation, bioweapons, or the legacy of war's privation.",
			"Adults are all expected to have basic military training.",
			"Weaponry is strictly restricted to members of the military and security forces." };

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Conflict randomConflict() {
		return Dice.randomFromArray(CONFLICTS);
	}

	public static String completeConflict() {
		return completeConflict(randomConflict());
	}

	public static String completeConflict(Conflict conflict) {
		String string = conflict.toString();

		string += "\nDetail: " + conflictDetail(conflict);
		string += "\nConstraint: " + conflictConstraint(conflict);
		string += "\nChange: " + conflictChange(conflict);

		return string;
	}

	public static String conflictDetail(Conflict conflict) {
		String[] string = null;

		if (conflict.equals(Conflict.BIGOTRY))
			string = BIGOTRY_DETAILS;
		else if (conflict.equals(Conflict.FREEDOM))
			string = FREEDOM_DETAILS;
		else if (conflict.equals(Conflict.INEQUALITY))
			string = INEQUALITY_DETAILS;
		else if (conflict.equals(Conflict.LAND))
			string = LAND_DETAILS;
		else if (conflict.equals(Conflict.NATIONALISM))
			string = NATIONALISM_DETAILS;
		else if (conflict.equals(Conflict.PRIVATION))
			string = PRIVATION_DETAILS;
		else if (conflict.equals(Conflict.RESENTMENT))
			string = RESENTMENT_DETAILS;
		else if (conflict.equals(Conflict.RESOURCES))
			string = RESOURCES_DETAILS;
		else if (conflict.equals(Conflict.SCHISM))
			string = SCHISM_DETAILS;
		else if (conflict.equals(Conflict.WAR))
			string = WAR_DETAILS;

		return Dice.randomFromArray(string);
	}

	public static String conflictConstraint(Conflict conflict) {
		String[] string = null;

		if (conflict.equals(Conflict.BIGOTRY))
			string = BIGOTRY_CONSTRAINTS;
		else if (conflict.equals(Conflict.FREEDOM))
			string = FREEDOM_CONSTRAINTS;
		else if (conflict.equals(Conflict.INEQUALITY))
			string = INEQUALITY_CONSTRAINTS;
		else if (conflict.equals(Conflict.LAND))
			string = LAND_CONSTRAINTS;
		else if (conflict.equals(Conflict.NATIONALISM))
			string = NATIONALISM_CONSTRAINTS;
		else if (conflict.equals(Conflict.PRIVATION))
			string = PRIVATION_CONSTRAINTS;
		else if (conflict.equals(Conflict.RESENTMENT))
			string = RESENTMENT_CONSTRAINTS;
		else if (conflict.equals(Conflict.RESOURCES))
			string = RESOURCES_CONSTRAINTS;
		else if (conflict.equals(Conflict.SCHISM))
			string = SCHISM_CONSTRAINTS;
		else if (conflict.equals(Conflict.WAR))
			string = WAR_CONSTRAINTS;

		return Dice.randomFromArray(string);
	}

	public static String conflictChange(Conflict conflict) {
		String[] string = null;

		if (conflict.equals(Conflict.BIGOTRY))
			string = BIGOTRY_CHANGES;
		else if (conflict.equals(Conflict.FREEDOM))
			string = FREEDOM_CHANGES;
		else if (conflict.equals(Conflict.INEQUALITY))
			string = INEQUALITY_CHANGES;
		else if (conflict.equals(Conflict.LAND))
			string = LAND_CHANGES;
		else if (conflict.equals(Conflict.NATIONALISM))
			string = NATIONALISM_CHANGES;
		else if (conflict.equals(Conflict.PRIVATION))
			string = PRIVATION_CHANGES;
		else if (conflict.equals(Conflict.RESENTMENT))
			string = RESENTMENT_CHANGES;
		else if (conflict.equals(Conflict.RESOURCES))
			string = RESOURCES_CHANGES;
		else if (conflict.equals(Conflict.SCHISM))
			string = SCHISM_CHANGES;
		else if (conflict.equals(Conflict.WAR))
			string = WAR_CHANGES;

		return Dice.randomFromArray(string);
	}

}
