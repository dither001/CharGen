package milieu;

public enum Type {
	EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT;

	public static Type matchType(String string) {
		Type match = null;
		Type[] array = new Type[] { EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT };

		for (int i = 0; i < array.length; ++i) {
			if (array[i].toString().compareToIgnoreCase(string) == 0) {
				match = array[i];
				break;

			}
		}

		return match;
	}
}