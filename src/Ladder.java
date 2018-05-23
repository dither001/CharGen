import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Ladder<T> implements Set<T> {
	// static fields
	private static int LOWEST_RANK;

	// fields
	private Vector<Node> nodes;
	private int lowestRank;

	// initialization
	static {
		LOWEST_RANK = 6;
	}

	// constructors
	public Ladder() {
		nodes = new Vector<Node>();
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
			this(data, 0, false, 0, 0);
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

		public int updateAge() {
			return ++age;
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
			return String.format("Rank: %2d || Power: %2d || Age: %2d || Known: %s", rank, power, age, identified);
		}
	}

	public void updateAges() {
		for (Node el : nodes)
			el.updateAge();
	}

	public int peerageVacancies(int rank) {
		return rank - allPeersOfRank(rank).size();
	}

	public Vector<Node> allPeersOfRank(int rank) {
		Vector<Node> peers = new Vector<Node>();

		Node candidate;
		for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.rank == rank)
				peers.add(candidate);
		}

		return peers;
	}

	@Override
	public boolean add(T e) {
		boolean added = nodes.add(new Node(e));
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
		nodes.clear();
	}

	@Override
	public boolean contains(Object o) {
		return nodes.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return nodes.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) nodes.iterator();
	}

	@Override
	public boolean remove(Object o) {
		boolean removed = nodes.remove(o);
		if (removed)
			updateAges();

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
		return nodes.size();
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
