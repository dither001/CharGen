import java.util.Comparator;

public class Planetoid implements World {
	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Group group;
	private Type type;
	private int orbit;

	private byte[] attributes;

	/*
	 * CONSTRUCTORS
	 * 
	 */
	public Planetoid(int orbit, Group group) {
		this(Type.STANDARD, orbit, group);
	}

	public Planetoid(Type type, int orbit, Group group) {
		this.group = group;
		this.type = type;
		this.orbit = orbit;
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public int getOrbit() {
		return orbit;
	}

	@Override
	public String toString() {
		String string = String.format("%s (%d)", type, orbit);

		return string;
	}

	/*
	 * COMPARATOR CLASSES
	 * 
	 */
	public static class OrbitAscending implements Comparator<Planetoid> {

		@Override
		public int compare(Planetoid left, Planetoid right) {
			return left.orbit - right.orbit;
		}

	}

	/*
	 * FIXME - INTERFACE MATHODS (move later)
	 * 
	 */
	@Override
	public byte[] getAttributes() {
		return attributes;
	}

	@Override
	public Type getType() {
		return type;
	}
}
