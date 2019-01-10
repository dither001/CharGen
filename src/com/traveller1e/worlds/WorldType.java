package com.traveller1e.worlds;

public enum WorldType {
	EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT, LARGE_GIANT;

	// static fields
	public static final WorldType[] WORLD_TYPES = { EMPTY, STANDARD, ASTEROID, CAPTURED, SATELLITE, RING, SMALL_GIANT,
			LARGE_GIANT };

	/*
	 * STATIC METHODS
	 */
	public static boolean isWorld(World world) {
		WorldType type = world.getType();
		boolean isWorld = true;

		if (type.equals(EMPTY))
			isWorld = false;

		if (type.equals(RING))
			isWorld = false;

		if (type.equals(SMALL_GIANT) || type.equals(LARGE_GIANT))
			isWorld = false;

		return isWorld;
	}

	public static boolean nameable(World world) {
		WorldType type = world.getType();
		boolean nameable = true;

		if (type.equals(EMPTY))
			nameable = false;

		if (type.equals(RING))
			nameable = false;

		return nameable;
	}

	public static boolean canHaveMoons(World world) {
		WorldType type = world.getType();
		boolean canHaveMoons = true;

		if (type.equals(ASTEROID))
			canHaveMoons = false;

		if (type.equals(EMPTY))
			canHaveMoons = false;

		if (type.equals(RING))
			canHaveMoons = false;

		if (type.equals(SATELLITE))
			canHaveMoons = false;

		return canHaveMoons;
	}

	public static boolean isEmpty(World world) {
		return world.getType().equals(EMPTY);
	}

	// public static boolean notEmpty(World world) {
	// return world.getType().equals(EMPTY) != true;
	// }

	public static boolean isGasGiant(World world) {
		WorldType type = world.getType();
		return type.equals(SMALL_GIANT) || type.equals(LARGE_GIANT);
	}

	public static boolean largeGiant(World world) {
		return world.getType().equals(LARGE_GIANT);
	}

	public static boolean smallGiant(World world) {
		return world.getType().equals(SMALL_GIANT);
	}

	// public static boolean notGasGiant(World world) {
	// WorldType type = world.getType();
	// return !type.equals(SMALL_GIANT) && !type.equals(LARGE_GIANT);
	// }

	public static boolean isCaptured(World world) {
		return world.getType().equals(CAPTURED);
	}

	public static boolean isMoon(World world) {
		return world.getType().equals(SATELLITE);
	}

	// public static boolean notMoon(World world) {
	// return world.getType().equals(SATELLITE) != true;
	// }

	public static boolean isRing(World world) {
		return world.getType().equals(RING);
	}

	// public static boolean notRing(World world) {
	// return world.getType().equals(RING) != true;
	// }

	public static boolean isAsteroid(World world) {
		return world.getType().equals(ASTEROID);
	}

	// public static boolean notAsteroid(World world) {
	// return world.getType().equals(ASTEROID) != true;
	// }

	public static WorldType matchType(String string) {
		WorldType match = null;
		WorldType[] array = WORLD_TYPES;

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