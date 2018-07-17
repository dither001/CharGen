
public interface World {
	public enum Type {
		EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, SMALL_GIANT, LARGE_GIANT
	}

	/*
	 * 
	 * 
	 */
	public Type getType();
	
	public byte[] getAttributes();

	/*
	 * 
	 * 
	 */
	public default boolean isEmpty() {
		return getType().equals(Type.EMPTY);
	}

	public default boolean notEmpty() {
		return getType().equals(Type.EMPTY) != true;
	}

	public default boolean isGasGiant() {
		return getType().equals(Type.SMALL_GIANT) || getType().equals(Type.LARGE_GIANT);
	}

	public default boolean notGasGiant() {
		return getType().equals(Type.SMALL_GIANT) != true && getType().equals(Type.LARGE_GIANT) != true;
	}

	public default boolean isCaptured() {
		return getType().equals(Type.CAPTURED);
	}

	public default boolean isMoon() {
		return getType().equals(Type.SATELLITE);
	}

	public default boolean isAsteroidBelt() {
		return getType().equals(Type.ASTEROID);
	}


}
