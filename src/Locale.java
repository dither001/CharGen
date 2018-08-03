import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Locale {
	//
	private static final int MAXIMUM_CAPACITY;
	private static final int MAXIMUM_NEIGHBORS;
	private static final int MAXIMUM_RADIUS;

	private static final Point[] ORIGIN_POINTS;

	/*
	 * INITIALIZATION
	 */
	static {
		MAXIMUM_CAPACITY = 6;
		MAXIMUM_NEIGHBORS = 6;
		MAXIMUM_RADIUS = 2;

		ORIGIN_POINTS = new Point[] { new Point(-1, 1), new Point(2, 1), new Point(-1, -1), new Point(-1, -2),
				new Point(2, -2), new Point(-2, -1), new Point(1, -2), new Point(2, -1), new Point(0, -1),
				new Point(1, 2), new Point(-1, 0), new Point(-1, 2), new Point(1, -1), new Point(0, 1),
				new Point(0, -2), new Point(1, 1), new Point(1, 0), new Point(0, 2), new Point(-2, 2), new Point(0, 0),
				new Point(-2, 0), new Point(-2, 1), new Point(2, 0), new Point(-2, -2), new Point(2, 2) };
	}

	/*
	 * INSTANCE FIELDS
	 */
	private Cluster home;
	private int localeID;
	private Point origin;
	private Set<Locale> neighbors;

	public Locale(Point origin, Cluster home) {
		this.origin = origin;
		this.home = home;

		//
		this.localeID = home.lifetimeLocales++;
		this.neighbors = new HashSet<Locale>();
	}

	/*
	 * INSTANCE METHODS
	 */
	private void neighborSetup() {
		Set<Point> set = home.findPoints(origin.adjacent());

		Locale locale;
		for (Iterator<Point> it = set.iterator(); it.hasNext();) {
			locale = home.pointMap.get(it.next());

			if (locale != null)
				neighbors.add(locale);
		}
	}

	public String toString() {
		String string = String.format("Locale %d (%d, %d)", localeID, origin.x, origin.y);

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("This is Locale %d (%d, %d) Neighbors: %d", localeID, origin.x, origin.y,
				neighbors.size());

		string += "\n" + neighbors.toString();
		return string;
	}

	public Set<Locale> neighborSet() {
		return neighbors;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Cluster cluster() {
		return new Cluster();
	}

	private static List<Point> orderedPoints() {
		List<Point> list = Dice.arrayToList(ORIGIN_POINTS);

		class sort implements Comparator<Point> {
			@Override
			public int compare(Point point1, Point point2) {
				int leftX = (point1.x < 0) ? -point1.x : point1.x;
				int rightX = (point2.x < 0) ? -point2.x : point2.x;
				//
				int leftY = (point1.y < 0) ? -point1.y : point1.y;
				int rightY = (point2.y < 0) ? -point2.y : point2.y;

				//
				int left = leftX + leftY, right = rightX + rightY;

				//
				return left - right;
			}
		}

		Collections.sort(list, new sort());

		return list;
	}

	/*
	 * INNER CLASS - CLUSTER
	 * 
	 */
	public static class Cluster {
		// instance fields

		private int lifetimeLocales;
		private Map<Point, Locale> pointMap;

		// constructors
		public Cluster() {
			//
			this.pointMap = new HashMap<Point, Locale>();

			for (Point el : orderedPoints()) {
				// setup locations
				pointMap.put(el, new Locale(el, this));
			}

			Locale locale;
			for (Iterator<Point> it = pointMap.keySet().iterator(); it.hasNext();) {
				locale = pointMap.get(it.next());
				// setup neighbors
				locale.neighborSetup();
			}
		}

		// instance methods
		public List<Locale> localeList() {
			List<Locale> list = new ArrayList<Locale>(pointMap.values());

			// anonymous comparator
			class sort implements Comparator<Locale> {
				@Override
				public int compare(Locale left, Locale right) {
					return left.localeID - right.localeID;
				}
			}

			Collections.sort(list, new sort());

			return list;
		}

		private Set<Point> findPoints(Set<Point> set) {
			Set<Point> workingSet = new HashSet<Point>();

			Point current, candidate;
			Iterator<Point> it1, it2;
			for (it1 = set.iterator(); it1.hasNext();) {
				current = it1.next();

				for (it2 = pointMap.keySet().iterator(); it2.hasNext();) {
					candidate = it2.next();

					if (current.x == candidate.x && current.y == candidate.y) {
						workingSet.add(candidate);
						break;
					}
				}
			}

			return workingSet;
		}

	}

	/*
	 * INNER CLASS - BUILDING
	 * 
	 */
	private static class Building {
		private Faction owner;
		private byte tier;
		private byte size;

		// constructor
		public Building(Faction owner) {
			this.owner = owner;
			this.tier = 0;
			this.size = 1;
		}

	}

	/*
	 * INNER CLASS - POINT
	 * 
	 */
	public static class Point {
		private int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			boolean equals = false;

			if (o == this)
				return true;

			if (o instanceof Point != true)
				return false;

			Point point = (Point) o;
			equals = point.x == this.x && point.y == this.x;

			return equals;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		public Set<Point> adjacent() {
			Set<Point> set = new HashSet<Point>();

			//
			Point[] points = new Point[6];
			points[0] = new Point(x + 1, y);
			points[1] = new Point(x, y - 1);
			points[2] = new Point(x - 1, y - 1);
			points[3] = new Point(x - 1, y);
			points[4] = new Point(x, y + 1);
			points[5] = new Point(x + 1, y + 1);

			//
			boolean inBounds;
			for (Point el : points) {
				inBounds = true;

				if (el.x < -MAXIMUM_RADIUS || el.x > MAXIMUM_RADIUS)
					inBounds = false;

				if (el.y < -MAXIMUM_RADIUS || el.y > MAXIMUM_RADIUS)
					inBounds = false;

				if (inBounds)
					set.add(el);
			}

			return set;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

}
