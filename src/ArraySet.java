import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Set<T> {
	// static fields
	private static final int DEFAULT_SIZE = 21;
	private static final int[] TIER_INDICES = { 0, 0, 1, 3, 7, 15, DEFAULT_SIZE };
	private static final int HIGHEST_TIER = 1;
	private static final int LOWEST_TIER = TIER_INDICES.length - 2;

	// instance fields
	private T[] structure;
	private int numberOfElements;

	// constructors
	public ArraySet() {
		structure = (T[]) new Object[DEFAULT_SIZE];
		numberOfElements = 0;
	}

	// methods
	public int validateTier(int tier) {
		return tier = (tier < HIGHEST_TIER) ? HIGHEST_TIER : (tier > LOWEST_TIER) ? LOWEST_TIER : tier;
	}

	public boolean isTierEmpty(int tier) {
		boolean isTierEmpty = true;

		for (int i = TIER_INDICES[tier]; i < TIER_INDICES[tier + 1]; ++i) {
			if (structure[i] != null) {
				isTierEmpty = false;
				break;
			}
		}

		return isTierEmpty;
	}

	public int highestEmptyTier() {
		int highestEmptyTier = -1;

		for (int i = LOWEST_TIER; i > 0; --i) {
			for (int j = TIER_INDICES[i]; j < TIER_INDICES[i + 1]; ++j) {
//				System.out.print(j + 1 + ", ");
				if (structure[j] != null)
					break;
			}
			highestEmptyTier = i;
//			System.out.println("Tier " + highestEmptyTier + " is empty.");
		}

		return highestEmptyTier;
	}

	public int firstAvailable() {
		int tier = LOWEST_TIER;

		while (firstAvailableInTier(tier - 1) != -1) {
			--tier;
		}

		return firstAvailableInTier(tier);
	}

	public int firstAvailableInTier(int tier) {
		int firstAvailable = -1;

		for (int i = TIER_INDICES[tier]; i < TIER_INDICES[tier + 1]; ++i) {
			if (structure[i] == null) {
				firstAvailable = i;
				break;
			}
		}

		return firstAvailable;
	}

	@Override
	public boolean add(T e) {
		boolean added = false;
		if (numberOfElements < structure.length) {
			int index = firstAvailable();
			structure[index] = e;
			System.out.println(index);
			++numberOfElements;
			added = true;
//			System.out.println("Added character to tier " + tier);
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
		for (int i = 0; i < structure.length; ++i)
			structure[i] = null;
	}

	@Override
	public boolean contains(Object o) {
		boolean contains = false;
		for (int i = 0; i < structure.length; ++i) {
			if (structure[i].equals(o)) {
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
		return (numberOfElements == 0);
	}

	@Override
	public Iterator<T> iterator() {
		return Arrays.asList(structure).iterator();
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
		return numberOfElements;
	}

	@Override
	public Object[] toArray() {
		return structure;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return (T[]) structure;
	}

}
