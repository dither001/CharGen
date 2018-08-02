import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ladder implements Faction {
	public enum Estate {
		POLITICAL, MILITARY, ACADEMIC, SPIRITUAL, BUSINESS, CRIMINAL
		// soldier (Military)
		// noble (Political)
		// scholar (Academic),
		// priest (Spiritual)
		// clerk (Business)
		// outlaw (Criminal)

		// artist, farmer, worker
		// nomad, drifter, sailor
	}

	/*
	 * STATIC FIELDS
	 */
	static Set<Ladder> ladders;

	static {
		ladders = new HashSet<Ladder>();
	}

	/*
	 * INSTANCE FIELDS
	 */
	private World homeworld;
	private Actor leader;
	//
	private int lifetimeMembers;
	private Map<Actor, Status> standing;
	private Set<Actor> currentMembers;
	private Set<Actor> formerMembers;
	//
	private byte[] scores;

	/*
	 * CONSTRUCTORS
	 */
	public Ladder() {
		this.lifetimeMembers = 0;
		this.currentMembers = new HashSet<Actor>();
		this.standing = new HashMap<Actor, Status>();
		//
		this.scores = Dice.rollAbilities();
	}

	/*
	 * INNER CLASS - member STATUS
	 */
	private class Status {
		Actor owner;
		int memberID;
		//
		boolean active;
		boolean busy;
		//
		int rank;
		int cooldown;
		int fame;
		int infamy;

		// constructors
		public Status(Actor owner) {
			this(true, owner);
		}

		public Status(boolean active, Actor owner) {
			this.owner = owner;
			this.memberID = lifetimeMembers++;
			//
			this.active = active;
			//
			this.rank = 0;
			this.cooldown = 0;
			this.fame = 0;
			this.infamy = 0;
		}

		// methods
		@Override
		public String toString() {
			String string = String.format("", owner.toString());

			return string;
		}

		boolean inactive() {
			return active != true;
		}

		void activate() {
			this.active = true;
		}

		void deactivate() {
			this.active = false;
		}

		boolean available() {
			return busy != true;
		}

		void makeBusy() {
			this.busy = true;
		}

		void release() {
			this.busy = false;
		}
	}

	/*
	 * INSTANCE METHODS
	 */
	public void update() {
		/*
		 * TODO - updates cooldown of all active members; active doesn't mean current,
		 * neither does former mean inactive, for what may seem like unintuitive
		 * reasons. "You never really stop being a marine," et cetera.
		 */
		Status current;
		for (Iterator<Status> it = standing.values().iterator(); it.hasNext();) {
			current = it.next();

			if (current.active)
				++current.cooldown;
		}
	}

	/*
	 * CURRENT MEMBERS
	 */
	public boolean addCurrentMember(Actor actor) {
		Set<Actor> set = currentMembers;

		boolean added = addToSet(actor, set);
		if (added)
			standing.put(actor, new Status(actor));

		return added;
	}

	public boolean addAllCurrentMembers(Collection<Actor> c) {
		Set<Actor> set = currentMembers;
		boolean added = addAllToSet(c, set);

		Actor actor;
		for (Iterator<Actor> it = c.iterator(); it.hasNext();) {
			actor = it.next();

			if (standing.containsKey(actor) != true)
				standing.put(actor, new Status(actor));
		}

		return added;
	}

	public Set<Actor> currentMemberSet() {
		return currentMembers;
	}

	/*
	 * FORMER MEMBERS
	 */
	public boolean addFormerMember(Actor actor) {
		Set<Actor> set = formerMembers;

		boolean added = addToSet(actor, set);
		if (added)
			standing.put(actor, new Status(actor));

		return added;
	}

	public boolean addAllFormerMembers(Collection<Actor> c) {
		Set<Actor> set = formerMembers;
		boolean added = addAllToSet(c, set);

		Actor actor;
		for (Iterator<Actor> it = c.iterator(); it.hasNext();) {
			actor = it.next();

			if (standing.containsKey(actor) != true)
				standing.put(actor, new Status(actor));
		}

		return added;
	}

	public Set<Actor> formerMemberSet() {
		return formerMembers;
	}

	/*
	 * TODO
	 * 
	 */
	@Override
	public void setCurrentMembers(Collection<Actor> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFormerMembers(Collection<Actor> c) {
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
	public List<String> nameList() {
		// TODO Auto-generated method stub
		return null;
	}

	// helper methods
	private <T> boolean addToSet(T el, Set<T> set) {
		boolean added = false;
		if (set.add(el))
			added = true;

		return added;
	}

	private <T> boolean addAllToSet(Collection<T> el, Set<T> set) {
		boolean added = false;
		if (set.addAll(el))
			added = true;

		return added;
	}

	// getters and setters
	public World getHomeworld() {
		return homeworld;
	}

	public void setHomeworld(World homeworld) {
		this.homeworld = homeworld;
	}

	public Actor getLeader() {
		return leader;
	}

	public void setLeader(Actor leader) {
		this.leader = leader;
	}

	public Set<Actor> standingKeySet() {
		return standing.keySet();
	}

	public Set<Status> standingValueSet() {
		return (Set<Status>) standing.values();
	}
	/*
	 * STATIC METHODS
	 */

}
