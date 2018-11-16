package milieu;

public interface Persistent {
	/*
	 * was this object loaded from the database?
	 */
	public boolean isPersistent();

	/*
	 * has it been changed since it was loaded?
	 */
	public boolean hasChanged();

	/*
	 * what is this object's database index?
	 */
	public int getIndex();
}
