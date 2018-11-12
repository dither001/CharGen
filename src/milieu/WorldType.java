package milieu;

public enum WorldType {
	EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT;

	// static fields
	public static final WorldType[] WORLD_TYPES = { EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT };
	
	/*
	 * STATIC METHODS
	 */
	public static WorldType matchType(String string) {
		WorldType match = null;
		WorldType[] array = new WorldType[] { EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT };

		for (int i = 0; i < array.length; ++i) {
			if (array[i].toString().compareToIgnoreCase(string) == 0) {
				match = array[i];
				break;

			}
		}

		return match;
	}
	
	public static int typeIndex(WorldType type) {
		int index = 0;

		switch (type) {
		case EMPTY:
			index = 0;
			break;
		case STANDARD:
			index = 1;
			break;
		case ASTEROID:
			index = 2;
			break;
		case CAPTURED:
			index = 3;
			break;
		case SATELLITE:
			index = 4;
			break;
		case RING:
			index = 5;
			break;
		case SMALL_GIANT:
			index = 6;
			break;
		case LARGE_GIANT:
			index = 7;
			break;
		default:
			break;
		}
		
		return index;
	}
}