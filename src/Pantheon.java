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
	private String name;
	private Actor leader;

	private Set<Actor> members;

	/*
	 * CONSTRUCTORS
	 */
	public Pantheon(String name) {
		this.members = new HashSet<Actor>();

		//
		this.name = name;

	}

	/*
	 * 
	 */
	public boolean add(Actor actor) {
		boolean added = false;
		if (members.contains(actor) != true) {
			members.add(actor);
			added = true;

		}

		return added;
	}

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
		return members;
	}

	@Override
	public boolean addMember(Actor actor) {
		return add(actor);
	}

	@Override
	public boolean addAllMembers(Collection<Actor> actors) {
		boolean addedAll = true;

		for (Iterator<Actor> it = actors.iterator(); it.hasNext();) {
			if (add(it.next()) != true)
				addedAll = false;

		}

		return addedAll;
	}

	@Override
	public void setMembers(Set<Actor> members) {
		this.members = members;
	}

	/*
	 * STATIC METHODS
	 */
	public Set<Pantheon> getPantheons() {
		return pantheons;
	}
}
