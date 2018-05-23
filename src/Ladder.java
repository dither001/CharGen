import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Ladder<T> implements Set<T> {
	// static fields
	private static int LOWEST_RANK;

	// fields
	private Vector<Node> members;
	private Vector<Node> formerMembers;
	private int lowestRank;

	// initialization
	static {
		LOWEST_RANK = 6;
	}

	// constructors
	public Ladder() {
		members = new Vector<Node>();
		formerMembers = new Vector<Node>();
		lowestRank = 1;
	}

	// inner class
	public class Node implements Comparable<Node> {
		private T data;
		private int rank;
		private boolean identified;
		private int power;
		private int age;

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

		@Override
		public int compareTo(Node other) {
			int compares = 0;

			if (power > other.power)
				compares = 1;
			else if (power < other.power)
				compares = -1;

			return compares;
		}

		@Override
		public String toString() {
			// return String.format("Rank: %2d || Power: %2d || Age: %2d || Known: %s",
			// rank, power, age, identified);
			String known = (identified) ? "" : "Unknown ";
			return String.format("%sAge/Power/Rank %2d/%2d/%2d", known, age, power, rank);
		}

		public boolean dominate(Node target) {
			System.out.println(target.toString() + " challenged by " + this.toString());
			boolean dominated = false;

			int challenger = (unknown()) ? Dice.disadvantage(20) + power : Dice.roll(20) + power;
			int defender = (target.unknown()) ? Dice.disadvantage(20) + target.power : Dice.roll(20) + target.power;

			dominated = (challenger > defender);
			String winner = (dominated) ? "Challenger" : "Defender";
			System.out.println(winner + " successful.");

			if (dominated)
				++this.power;
			else
				++target.power;
			
			return dominated;
		}
	}

	public void updateAges() {
		for (Node el : members)
			++el.age;

		for (Node el : formerMembers)
			++el.age;
	}

	public int peerageVacancies(int rank) {
		return rank - allPeersOfRank(rank).size();
	}

	public Vector<Node> allPeersOfRank(int rank) {
		Vector<Node> peers = new Vector<Node>();

		Node candidate;
		for (Iterator<Node> it = members.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.rank == rank)
				peers.add(candidate);
		}

		return peers;
	}

	public Node weakestPeer(int rank) {
		Node candidate, weakling = null;
		for (Iterator<Node> it = allPeersOfRank(rank).iterator(); it.hasNext();) {
			candidate = it.next();

			if (weakling == null)
				weakling = candidate;

			if (candidate.power < weakling.power)
				weakling = candidate;
		}

		return weakling;
	}

	@Override
	public String toString() {
		String memberStrings = "";

		for (Iterator<Node> it = members.iterator(); it.hasNext();)
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
			System.out.println("Newest member " + candidate.toString());
		} else if (lowestRank < LOWEST_RANK) {
			++lowestRank;
			candidate = new Node(e);
			added = members.add(candidate);
			System.out.println("Newest member " + candidate.toString());
		} else {
			candidate = new Node(e);
			Node weakestPeer = weakestPeer(lowestRank);

			if (candidate.dominate(weakestPeer)) {
				members.remove(weakestPeer);
				added = members.add(candidate);
			}

			System.out.println("Newest member " + candidate.toString());
		}

		if (added)
			updateAges();

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
		return members.contains(o);
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
}
