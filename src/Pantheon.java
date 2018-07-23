import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Pantheon implements Faction {
	/*
	 * STATIC FIELDS
	 */
	private static Set<Pantheon> pantheons;

	static {
		pantheons = new HashSet<Pantheon>();
	}

	/*
	 * INSTANCE FIELDS
	 */
	private Immortal.Lesser affiliation;
	private String name;
	private Immortal.Instance leader;

	private Set<Immortal.Instance> members;

	/*
	 * CONSTRUCTORS
	 */
	public Pantheon(Immortal.Lesser affiliation) {
		this.members = new HashSet<Immortal.Instance>();

		//
		this.name = Dice.stringToName(affiliation.toString());

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
	public boolean add(Immortal.Instance immortal) {
		boolean added = false;
		if (members.contains(immortal) != true) {
			members.add(immortal);
			added = true;

		}

		return added;
	}

	@Override
	public String toString() {
		String string = name;

		return string;
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

	/*
	 * STATIC METHODS
	 */
	public Set<Pantheon> getPantheons() {
		return pantheons;
	}
}
