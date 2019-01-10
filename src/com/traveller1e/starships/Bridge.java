package com.traveller1e.starships;

public class Bridge {
	private int displacement;

	public Bridge(int hull) {
		this.displacement = (int) (hull * 0.02);

		//
		if (displacement < 20)
			this.displacement = 20;

	}

	/*
	 * INSTANCE METHODS
	 */
	@Override
	public String toString() {
		return String.format("Bridge %d-tons", displacement);
	}

	/*
	 * STATIC METHODS
	 */
	public static int costEstimator(int hull) {
		return hull * 5000;
	}
}
