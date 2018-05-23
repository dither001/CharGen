import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class FixedSet<T> implements Set<T> {
	private Vector<T> elements;
	private final int MAX_SIZE;

	public FixedSet(int MAX_SIZE) {
		elements = new Vector<T>();
		this.MAX_SIZE = MAX_SIZE;
	}

	@Override
	public boolean add(T e) {
		boolean added = false;
		if (elements.size() < MAX_SIZE)
			added = elements.add(e);

		return added;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean addedAll = true;

		for (Iterator<? extends T> it = c.iterator(); it.hasNext();) {
			addedAll = elements.add(it.next());
		}

		return addedAll;
	}

	@Override
	public void clear() {
		elements.clear();
	}

	@Override
	public boolean contains(Object o) {
		return elements.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return elements.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return elements.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

}
