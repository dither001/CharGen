import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Ladder<T> implements Set<T> {
	// static fields
	private static int LOWEST_RANK;
	private static int NODES_TO_ACT;

	// fields
	private ArrayList<Node> members;
	private ArrayList<Node> formerMembers;
	private Node hierarch;
	private int lowestRank;
	private int turnsTaken;

	// initialization
	static {
		LOWEST_RANK = 6;
		NODES_TO_ACT = 4; // TODO - DEFAULT IS FOUR
	}

	// constructors
	public Ladder() {
		members = new ArrayList<Node>();
		formerMembers = new ArrayList<Node>();
		lowestRank = 1;
		turnsTaken = 0;
	}

	// methods
	public void updateTime() {
		for (Node el : members) {
			++el.age;
			++el.turnsSinceLastAction;
		}

		for (Node el : formerMembers) {
			++el.age;
			++el.turnsSinceLastAction;
		}
	}

	public void updatePower(int rank, int increase) {
		ArrayList<Node> peers = peersOfRank(rank);

		for (Node el : peers)
			el.power += increase;
	}

	public Node getHierarch() {
		return (hierarch != null) ? hierarch : null;
	}

	public void setHierarch(Node hierarch) {
		this.hierarch = hierarch;
	}

	public int hierarchTurns() {
		return (hierarch != null) ? hierarch.actionsTaken : 0;
	}

	public boolean majorityUnknown() {
		boolean majorityUnknown = false;

		float nobodies = 0f;
		for (Node el : members)
			nobodies += (el.unknown()) ? 1 : 0;

		if (nobodies / members.size() > 0.4)
			majorityUnknown = true;

		return majorityUnknown;
	}

	public int peerageVacancies(int rank) {
		return rank - peersOfRank(rank).size();
	}

	/*
	 * LIST-CREATION METHODS
	 */
	public List<Node> freeAndActive() {
		List<Node> list = new ArrayList<Node>();

		for (Node el : members) {
			if (el.isActive && el.isFree())
				list.add(el);
		}

		return list;
	}

	public List<Node> everyoneExcept(List<Node> exceptions) {
		List<Node> list = new ArrayList<Node>(members);
		list.removeAll(exceptions);

		return list;
	}

	public ArrayList<Node> peersOfRank(int rank) {
		ArrayList<Node> peers = new ArrayList<Node>();

		for (Node el : members) {
			if (el.rank == rank)
				peers.add(el);
		}

		return peers;
	}

	public Node findWeakerThan(Node actor) {
		Node candidate;

		ArrayList<Node> nodes = new ArrayList<Node>(members);
		RankCompareDescending compareRank = new RankCompareDescending();
		Collections.sort(nodes, compareRank);

		candidate = null;
		// FIXME - currently this loop never breaks because the "acting" characters
		// have too low a power to trigger all the conditions to break the loop; before
		// I can continue, I need to have some other actions for them to take besides
		// challenge higher-ranked characters
		for (Node el : nodes) {
			if (el != null && el.isFree() && actor.power > el.power && el.rank > actor.rank) {
				candidate = el;
				break;
			}
		}

		if (candidate != null)
			candidate.setBusy();
		return candidate;
	}

	public List<Node> weakestPeers(int rank) {
		List<Node> list = new ArrayList<Node>(peersOfRank(rank));
		PowerCompareAscending readyRank = new PowerCompareAscending();
		Collections.sort(list, readyRank);

		return list;
	}

	/*
	 * TURN METHODS
	 */
	public void update() {
		// TODO
		++turnsTaken;
		updateTime();
		System.out.println("Turn #" + turnsTaken);
		List<Node> actors = readyForAction();
		if (actors.contains(hierarch))
			System.out.println("The hierarch is in play. (Turn #" + (1 + hierarch.actionsTaken) + ")");

		// mark actors as busy
		for (Node el : actors)
			el.setBusy();

		// give actors something to do
		for (Node el : actors) {
			chooseAction(el);
		}

		// set actors free
		for (Node el : actors) {
			el.actionsTaken += 1;
			el.turnsSinceLastAction = 0;
			el.setFree();
		}

		// update turns since last acted
		List<Node> toUpdate = everyoneExcept(actors);
		for (Node el : toUpdate)
			el.turnsSinceLastAction += 1;

	}

	public List<Node> readyForAction() {
		List<Node> list = new ArrayList<Node>(members);
		List<Node> ready;

		if (list.size() < NODES_TO_ACT) {
			ready = list;
		} else {
			ready = new ArrayList<Node>();

			ReadyCompareDescending readyRank = new ReadyCompareDescending();
			Collections.sort(list, readyRank);

			for (int i = 0; i < NODES_TO_ACT; ++i) {
				ready.add(list.get(i));
			}
		}

		return ready;
	}

	public void powerCascade(int rank) {
		// TODO
		int increase = 1;
		for (int i = rank; i > 0; --i) {
			updatePower(i, increase);
			++increase;
			// System.out.println("Rank " + i + " powered up.");
		}
	}

	/*
	 * MEMBER ACTIONS
	 */
	public void chooseAction(Node node) {
		int dice;

		if (node.unknown() && majorityUnknown()) {
			dice = Dice.roll(3);

			if (dice == 1) {
				debut(node);
			} else {
				// TODO
				doNothing(node);
			}
		} else {
			// TODO
			doNothing(node);
		}
	}

	public void debut(Node node) {
		node.identify();
		node.power += 2;
		// TODO

		System.out.println(node.toString() + " has appeared!");
	}

	public void doNothing(Node node) {
		// TODO
		System.out.println(node.toString() + " does nothing.");
	}

	public void fight(Node challenger) {
		// TODO

		// testing A
		System.out.println();
		System.out.println(challenger.toString());

		// testing B
		List<Node> nodes = new ArrayList<Node>(peersOfRank(challenger.rank));
		PowerCompareAscending powerRank = new PowerCompareAscending();
		Collections.sort(nodes, powerRank);

		//

	}

	public void challenge(Node challenger) {
		// testing A
		System.out.println();
		System.out.println(challenger.toString());

		// testing B
		List<Node> nodes = new ArrayList<Node>(peersOfRank(challenger.rank - 1));
		ArrayList<Node> ready = new ArrayList<Node>();

		PowerCompareAscending powerRank = new PowerCompareAscending();
		Collections.sort(nodes, powerRank);

		System.out.println();
		System.out.println(nodes.toString());

		// testing C
		Node target = nodes.get(0);
		System.out.println();
		System.out.println(target.toString());

		// actual challenge
		int result = challenger.dominate(target);
		if (result > 4) {
			members.remove(target);
			++challenger.rank;
			challenger.turnsSinceLastAction = 0;
			System.out.println(target + " has been exiled. (" + result + ")");
		} else if (result < -4) {
			members.remove(challenger);
			challenger.turnsSinceLastAction /= 2;
			System.out.println(challenger + " has been exiled. (" + result + ")");
		}

		// System.out.println("Actors in ladder: " + size());
	}

	@Override
	public String toString() {
		// TODO
		String memberStrings = "";
		List<Node> list = new ArrayList<Node>(members);
		ReadyCompareDescending compareReady = new ReadyCompareDescending();
		Collections.sort(list, compareReady);

		for (Iterator<Node> it = list.iterator(); it.hasNext();)
			memberStrings += it.next().toString() + "\n";

		return memberStrings;
	}

	// Set methods
	@Override
	public boolean add(T e) {
		Node candidate;
		boolean added = false;

		if (peerageVacancies(lowestRank) > 0) {
			candidate = new Node(e);
			added = members.add(candidate);

			hierarch = (candidate.rank == 1) ? candidate : hierarch;
		} else if (lowestRank < LOWEST_RANK) {
			++lowestRank;
			candidate = new Node(e);
			added = members.add(candidate);
			powerCascade(lowestRank - 1);
		} else {
			candidate = new Node(e);
			Node defender = weakestPeers(lowestRank).get(0);

			if (candidate.dominate(defender) > 0) {
				members.remove(defender);
				added = members.add(candidate);
			}
		}

		if (added) {
			updateTime();
			// System.out.println("Newest member " + candidate.toString() + " of rank " +
			// lowestRank);
		}

		return added;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		formerMembers.addAll(members);
		members.clear();
	}

	@Override
	public boolean contains(Object o) {
		boolean contains = false;

		Node candidate;
		for (Iterator<Node> it = members.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.data.equals(o)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return members.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) members.iterator();
	}

	@Override
	public boolean remove(Object o) {
		boolean removed = members.remove(o);

		if (removed)
			formerMembers.add(new Node((T) o));

		return removed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return members.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	// inner class
	public class Node implements Comparable<Node> {
		private T data;
		private int rank;
		private boolean identified;
		private int power;
		private int age;

		//
		private boolean isActive;
		private boolean isBusy;
		private int turnsSinceLastAction;
		private int actionsTaken;

		// constructors
		public Node(T data) {
			this(data, lowestRank, false, 0, 0);
		}

		public Node(T data, int rank, boolean identified, int power, int age) {
			this.data = data;
			this.rank = rank;
			this.identified = identified;
			this.power = power;
			this.age = age;

			//
			this.isActive = true;
			this.isBusy = false;
			this.turnsSinceLastAction = 0;
			this.actionsTaken = 0;
		}

		// methods
		public T getData() {
			return data;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public boolean unknown() {
			return (identified == false);
		}

		public void identify() {
			identified = true;
		}

		public int getPower() {
			return power;
		}

		public void setPower(int power) {
			this.power = power;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public boolean isActive() {
			return isActive;
		}

		public boolean isInactive() {
			return (isActive != true);
		}

		public boolean isBusy() {
			return isBusy;
		}

		public boolean isFree() {
			return (isBusy != true);
		}

		public void setBusy() {
			this.isBusy = true;
		}

		public void setFree() {
			this.isBusy = false;
		}

		public int getTurnsSinceLastAction() {
			return turnsSinceLastAction;
		}

		public void setTurnsSinceLastAction(int turns) {
			this.turnsSinceLastAction = turns;
		}

		@Override
		public int compareTo(Node other) {
			return this.age - other.age;
		}

		@Override
		public String toString() {
			// return String.format("Rank: %2d || Power: %2d || Age: %2d || Known: %s",
			// rank, power, age, identified);

			String known = (identified) ? "" : "Unknown ";
			// return String.format("%sAge/Power/Rank %2d/%2d/%2d (last turn %3d) (act? %d)
			// %s", known, age, power, rank,
			// turnsSinceLastAction, timeToAct(), data.toString());

			return String.format("%s%2d/%2d/%2d %s (priority %d)", known, age, power, rank, data.toString(), timeToAct());

			// return String.format("%s%2d/%2d/%2d %s", known, age, power, rank,
			// data.toString());
		}

		public int timeToAct() {
			return turnsSinceLastAction / ((lowestRank - rank > 0) ? lowestRank - rank : 1);
		}

		public int dominate(Node target) {
			// System.out.println(target.toString() + " challenged by " + this.toString());
			boolean dominated = false;

			int challenger = (unknown()) ? Dice.disadvantage(20) + power : Dice.roll(20) + power;
			int defender = (target.unknown()) ? Dice.disadvantage(20) + target.power : Dice.roll(20) + target.power;

			dominated = (challenger > defender);
			// String winner = (dominated) ? "Challenger" : "Defender";
			// System.out.println(winner + " successful.");

			if (dominated)
				++this.power;
			else
				++target.power;

			this.setFree();
			target.setFree();
			return challenger - defender;
		}
	}

	class AgeCompareDescending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n2.age - n1.age;
		}
	}

	class PowerCompareAscending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n1.power - n2.power;
		}
	}

	class PowerCompareDescending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n2.power - n1.power;
		}
	}

	class RankCompareAscending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n1.rank - n2.rank;
		}
	}

	class RankCompareDescending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n2.rank - n1.rank;
		}
	}

	class ReadyCompareAscending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			int ready = n1.timeToAct(), waiting = n2.timeToAct();
			return ready - waiting;
		}
	}

	class ReadyCompareDescending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			int ready = n1.timeToAct(), waiting = n2.timeToAct();
			return waiting - ready;
		}
	}

	class TurnsCompareAscending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n1.actionsTaken - n2.actionsTaken;
		}
	}

	class TurnsCompareDescending implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			return n2.actionsTaken - n1.actionsTaken;
		}
	}

	public void printReady() {
		List<Node> nodes = new ArrayList<Node>(members);
		ReadyCompareDescending readyRank = new ReadyCompareDescending();
		Collections.sort(nodes, readyRank);

		for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
			System.out.println(it.next().toString());
		}

	}

	public void printTurnsDescending() {
		List<Node> nodes = new ArrayList<Node>(members);
		TurnsCompareDescending turnsRank = new TurnsCompareDescending();
		Collections.sort(nodes, turnsRank);

		Node current;
		for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
			current = it.next();
			System.out.println("(" + current.actionsTaken + ") " + current.toString());
		}

	}

}
