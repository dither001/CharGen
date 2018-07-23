import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Pantheon implements Faction {
	/*
	 * STATIC FIELDS
	 */
	private static Set<Pantheon> pantheons;

	static {
		pantheons = new HashSet<Pantheon>();

		Immortal.Lesser[] array = Immortal.getPantheons();
		Set<Immortal.Instance> set;
		Immortal.Instance god;
		Pantheon pantheon;

		for (int i = 0; i < array.length; ++i) {
			pantheon = new Pantheon(array[i]);
			pantheons.add(pantheon);

			set = Immortal.filterByPantheon(array[i]);
			for (Iterator<Immortal.Instance> it = set.iterator(); it.hasNext();) {
				god = it.next();
				pantheon.add(god);

			}

		}

	}

	/*
	 * INSTANCE FIELDS
	 */
	private Immortal.Lesser affiliation;
	private String name;
	private Immortal.Instance leader;

	private Set<Immortal.Instance> members;
	private int membership;

	/*
	 * CONSTRUCTORS
	 */
	public Pantheon(Immortal.Lesser affiliation) {
		this.members = new HashSet<Immortal.Instance>();
		membership = 0;

		//
		this.name = Names.stringToName(affiliation.toString());

		Set<Immortal.Instance> set = Immortal.filterByPantheon(affiliation);
		Immortal.Instance instance;
		for (Iterator<Immortal.Instance> it = set.iterator(); it.hasNext();) {
			instance = it.next();

			add(instance);
		}
	}

	/*
	 * 
	 */
	public void add(Immortal.Instance immortal) {
		if (immortal != null && members.contains(immortal) != true) {
			members.add(immortal);
			++membership;

		}
	}

	@Override
	public String toString() {
		String string = String.format("%s", name);

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Set<Pantheon> getPantheons() {
		return pantheons;
	}

	public static List<String> getNameList() {
		return Dice.randomFromSet(pantheons).nameList();
	}

	/*
	 * INTERFACE METHODS
	 * 
	 */
	@Override
	public World getHomeworld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHomeworld(World homeworld) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Tag> getFactionTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetFactionTags(EnumSet<Tag> tags) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Actor> getMembers() {
		return new HashSet<Actor>(members);
	}

	@Override
	public int membership() {
		return membership;
	}

	@Override
	public boolean addMember(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAllMembers(Collection<Actor> actors) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void setMembers(Set<Actor> members) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> nameList() {
		List<String> nameList = new ArrayList<String>();
		String name;
		for (Iterator<Immortal.Instance> it = members.iterator(); it.hasNext();) {
			name = it.next().getName();
			if (name != null)
				nameList.add(name);
		}

		return nameList;
	}

}
