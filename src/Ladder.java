import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Ladder<T> implements Set<T> {
	// static fields
	private static final int DEFAULT_SIZE = 21;
	private static final int[] TIER_INDICES = { 0, 0, 1, 3, 7, 15, DEFAULT_SIZE };

	// fields
	private T[] structure;

	private LinkedList<Member> members;
	private LinkedList<Member> removed;
	private Member hierarch;

	private int totalPower;

	// constructors
	Ladder() {
		structure = (T[]) new Object[DEFAULT_SIZE];
	}

	// inner classes
	public class Member implements Comparable<Member> {
		private Object data;
		private boolean identified;
		private int power;

		public Member(T data) {
			this(data, false, 1);
		}

		public Member(T data, boolean identified, int power) {
			this.data = data;
			this.identified = identified;
			this.power = power;
		}

		public void identify() {
			identified = true;
		}

		public boolean unknown() {
			return (identified == false);
		}

		public boolean dominate(Member target) {
			boolean dominated = false;

			int challenger = (unknown()) ? Dice.disadvantage(20) + power : Dice.roll(20) + power;
			int defender = (target.unknown()) ? Dice.disadvantage(20) + target.power : Dice.roll(20) + target.power;

			if (challenger > defender)
				dominated = true;

			return dominated;
		}

		@Override
		public int compareTo(Ladder<T>.Member o) {
			int compares = 0;

			if (power > o.power)
				compares = 1;
			else if (power < o.power)
				compares = -1;

			return compares;
		}
	}

	
	public boolean isTierEmpty(int tier) {
		boolean isTierEmpty = true;
		
		for (int i = TIER_INDICES[tier]; i < TIER_INDICES[tier+1]; ++i) {
			
		}
		
		
		
		return isTierEmpty;
	}
	
	
	
	
	@Override
	public boolean add(T e) {
		boolean added = false;

		if (members.size() < DEFAULT_SIZE) {
			members.add(new Member(e));
			added = true;
		} else {
			Member newcomer = new Member(e);
			// TODO - attempt dominate
		}

		return added;
	}

	private int firstIndexOfTier(int tier) {
		// input validation
		tier = (tier < 0) ? 1 : (tier > 6) ? 6 : tier;

		int index = 0;
		for (int i = 1; i < tier; ++i)
			index = (index + 1) * 2 + 1;

		// output validation
		if (index > structure.length - 1)
			index = structure.length - 1;

		return index;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		members.clear();
		removed.clear();
		
		for (int i = 0; i < structure.length; ++i)
			structure[i] = null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
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
