import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Ship {
	// static fields
	private static ShipSet ships;

	static {
		ships = new ShipSet();
	}

	// instance fields
	private Crew crew1, crew2;
	private boolean allies;

	// constructors
	public Ship(Crew.Faction faction1, Crew.Faction faction2, boolean allies) {
		Crew crew1 = Crew.getCrewByFaction(faction1);
		Crew crew2 = Crew.getCrewByFaction(faction2);

		this.crew1 = crew1;
		this.crew2 = crew2;
		this.allies = allies;
	}

	public boolean equals(Ship other) {
		boolean equals = true;

		if (other.crew1.notSameAs(crew1) && other.crew1.notSameAs(crew2))
			equals = false;

		if (other.crew2.notSameAs(crew1) && other.crew2.notSameAs(crew2))
			equals = false;

		return equals;
	}

	public boolean allies() {
		return allies;
	}

	public boolean enemies() {
		return (allies != true);
	}

	/*
	 * SHIP SET - INNER CLASS
	 */
	private static class ShipSet {
		static HashSet<Ship> set;

		static {
			set = new HashSet<Ship>();
		}

		public boolean add(Ship e) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean contains(Ship e) {
			// TODO Auto-generated method stub
			boolean contains = false;
			for (Iterator<Ship> it = set.iterator(); it.hasNext();) {
				
			}

			return contains;
		}

		public Iterator<Ship> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

	}
}